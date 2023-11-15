
package com.threepounds.caseproject.service;
        import com.threepounds.caseproject.data.entity.ValidationCode;
        import com.threepounds.caseproject.data.repository.ConfirmRepository;
        import com.threepounds.caseproject.data.repository.ValidationCodeRepository;
        import org.springframework.stereotype.Service;
        import java.util.Optional;
        import java.util.UUID;
@Service
public class ConfirmService {
    private final ConfirmRepository confirmRepository;
    public ConfirmService(ConfirmRepository confirmRepository) {
        this.confirmRepository = confirmRepository;
    }

    public Optional<ValidationCode> getUser_id(UUID user_id){
        return confirmRepository.findById(user_id);
    }

}