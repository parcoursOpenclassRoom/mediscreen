package com.mediscreen.rapport.repository;

import com.mediscreen.rapport.entity.PatientReport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-patient", url = "localhost:8081")
public interface PatientRepository {

    @GetMapping("/patient/{id}")
    PatientReport findPatient(@PathVariable int id);
    @GetMapping("/patient/family-name/{familyName}")
    PatientReport findPatientByName(@PathVariable String familyName);
}
