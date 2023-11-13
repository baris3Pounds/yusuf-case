package com.threepounds.caseproject.service;

import com.threepounds.caseproject.data.entity.ValidationCode;
import com.threepounds.caseproject.data.repository.ValidationCodeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ValidationCodeService {
    private final ValidationCodeRepository validationCodeRepository;

    public ValidationCodeService(ValidationCodeRepository validationCodeRepository) {
        this.validationCodeRepository = validationCodeRepository;
    }
    public ValidationCode create(ValidationCode validationCode){

       return validationCodeRepository.save(validationCode);
          }
     public Optional<ValidationCode> getCode(int code){
        return validationCodeRepository.findByCode(code);
     }
}