package com.threepounds.caseproject.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity(name = "codes")
@NoArgsConstructor
@AllArgsConstructor
public class ValidationCode {
    @Id
    @Column
    @GeneratedValue
    private UUID id;
    @Column
    private UUID userId;
    @Column
    private String otp;
    @Column
    private boolean isUsed;
}
