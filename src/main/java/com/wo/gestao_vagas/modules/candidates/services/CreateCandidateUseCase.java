package com.wo.gestao_vagas.modules.candidates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wo.gestao_vagas.exception.UserFoundException;
import com.wo.gestao_vagas.modules.candidates.entities.CandidateEntity;
import com.wo.gestao_vagas.modules.candidates.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return candidateRepository.save(candidateEntity);
    }

}
