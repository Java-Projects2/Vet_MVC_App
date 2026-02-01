package com.example.vet_mvc_app.clinics.dto;

import com.example.vet_mvc_app.users.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class CreateClinicDto {
    private String name;
    private String address;
    private String phone;
    private User vetId;
    private Instant createdAt;
    private Instant updatedAt;
}
