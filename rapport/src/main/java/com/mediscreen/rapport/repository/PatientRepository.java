package com.mediscreen.rapport.repository;

import com.mediscreen.rapport.entity.Patient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-patient")
@RibbonClient(name = "microservice-patient")
public interface PatientRepository {

    @GetMapping("/patient/{id}")
    Patient findPatient(@PathVariable int id);
    @GetMapping("/patient/family-name/{familyName}")
    Patient findPatientByName(@PathVariable String familyName);
}
