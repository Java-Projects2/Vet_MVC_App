package com.example.vet_mvc_app.clinics.service;

import com.example.vet_mvc_app.authentication.JWT.JwtService;
import com.example.vet_mvc_app.clinics.Repository.ClinicRepository;
import com.example.vet_mvc_app.clinics.controller.ClinicController;
import com.example.vet_mvc_app.clinics.dto.CreateClinicDto;
import com.example.vet_mvc_app.clinics.dto.ResponseClinicDto;
import com.example.vet_mvc_app.clinics.dto.UpdateClinicDto;
import com.example.vet_mvc_app.clinics.enitity.Clinic;
import com.example.vet_mvc_app.users.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicServiceImpl implements ClinicService {
    private final ClinicRepository clinicRepository;
    private final JwtService jwtService;

    public ClinicServiceImpl(ClinicRepository clinicRepository, JwtService jwtService) {
        this.clinicRepository = clinicRepository;
        this.jwtService = jwtService;
    }

    public Clinic createClinic(CreateClinicDto dto) {
        Clinic clinic = new Clinic();
        clinic.setName(dto.getName());
        clinic.setAddress(dto.getAddress());
        clinic.setPhone(dto.getPhone());
        clinic.setVet(dto.getVetId());
        return clinicRepository.save(clinic);
    }

    public List<ResponseClinicDto> getAll() {
        List<Clinic> clinics = clinicRepository.findAll();
        return clinics.stream().map(clinic -> {
            ResponseClinicDto dto = new ResponseClinicDto();
            dto.setId(clinic.getId());
            dto.setName(clinic.getName());
            dto.setAddress(clinic.getAddress());
            dto.setPhone(clinic.getPhone());
            dto.setVetName(clinic.getVet().getName());
            return dto;
        }).collect(Collectors.toList());
    }
/*
    public Clinic updateClinic(UpdateClinicDto dto) {
        Boolean isAdmin = jwtService.isUserAdminOrOwner(dto.getId());
        if (isAdmin) {
            Clinic clinic = user
        }
    }*/

}
