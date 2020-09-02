package com.mediscreen.rapport.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Report {
    @Id
    private int id;
    private int idPatient;
    private String risk;
    private String description;
    @Transient
    private Patient patient;

    public Report(){

    }

    public Report(int idPatient, String risk, String description, Patient patient) {
        this.idPatient = idPatient;
        this.risk = risk;
        this.description = description;
        this.patient = patient;
    }

    public Report(int idPatient, String risk, String description) {
        this.idPatient = idPatient;
        this.risk = risk;
        this.description = description;
    }
    public Report(int id, int idPatient, String risk, String description) {
        this.id = id;
        this.idPatient = idPatient;
        this.risk = risk;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
