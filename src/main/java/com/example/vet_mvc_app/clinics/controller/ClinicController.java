package com.example.vet_mvc_app.clinics.controller;

import com.example.vet_mvc_app.clinics.dto.CreateClinicDto;
import com.example.vet_mvc_app.clinics.dto.ResponseClinicDto;
import com.example.vet_mvc_app.clinics.enitity.Clinic;
import com.example.vet_mvc_app.clinics.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.util.List;

@Controller
@RequestMapping("/clinics")
public class ClinicController {
    private final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PostMapping
    public String createClinic(@ModelAttribute("clinicDto") CreateClinicDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "clinics";
        }
        Clinic clinic = clinicService.createClinic(dto);
        model.addAttribute("clinic", clinic);
        return "clinics/success";
    }

    @GetMapping
    public String getAll(Model model) {
        List<ResponseClinicDto> clincs = clinicService.getAll();
        model.addAttribute("clincs" ,clincs);
        return "clinics";
    }



}
