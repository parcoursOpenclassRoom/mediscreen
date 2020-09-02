package com.mediscreen.rapport.entity;

public class Note {
    private int id;
    private String notes;
    private int idPatient;

    public Note(String notes, int idPatient) {
        this.notes = notes;
        this.idPatient = idPatient;
    }

    public Note() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }
}
