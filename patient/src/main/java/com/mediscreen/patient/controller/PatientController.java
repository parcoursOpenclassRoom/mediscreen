package com.mediscreen.patient.controller;

import com.mediscreen.patient.entity.Patient;
import com.mediscreen.patient.manager.PatientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientManager patientManager;

    @GetMapping
    public List<Patient> findAll(){
        return patientManager.list();
    }

    @GetMapping("/{id}")
    public Patient findPatient(@PathVariable int id){
        return patientManager.find(id);
    }

    @PostMapping
    public Patient create(@RequestBody Patient patient){
        return patientManager.save(patient);
    }

    @PutMapping
    public Patient update(@RequestBody Patient patient){
        return patientManager.save(patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
         patientManager.delete(id);
    }
}
