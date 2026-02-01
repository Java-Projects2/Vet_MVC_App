package com.example.vet_mvc_app.clinics.service;

import com.example.vet_mvc_app.clinics.dto.CreateClinicDto;
import com.example.vet_mvc_app.clinics.dto.ResponseClinicDto;
import com.example.vet_mvc_app.clinics.dto.UpdateClinicDto;
import com.example.vet_mvc_app.clinics.enitity.Clinic;

import java.util.List;

public interface ClinicService {
    public Clinic createClinic(CreateClinicDto dto);

    public List<ResponseClinicDto> getAll();

/*
    public Clinic updateClinic(UpdateClinicDto dto);
*/

}
