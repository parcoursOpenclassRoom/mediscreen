package com.mediscreen.patient.controller;

import com.mediscreen.patient.entity.Patient;
import com.mediscreen.patient.manager.PatientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @PostMapping("/add")
    public Patient create(@RequestParam String family, @RequestParam String given, @RequestParam String sex, @RequestParam String address , @RequestParam String phone, @RequestParam String dob) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
        return patientManager.save(new Patient(family, given, sex, d, address, phone));
    }

    @PutMapping
    public Patient update(@RequestBody Patient patient){
        return patientManager.save(patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
         patientManager.delete(id);
    }

    @GetMapping("/family-name/{familyName}")
    Patient findPatientByName(@PathVariable String familyName){
        return  patientManager.findByName(familyName);
    }
}
