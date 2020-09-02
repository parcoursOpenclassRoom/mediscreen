package com.mediscreen.rapport.manager;

import com.mediscreen.rapport.entity.Note;
import com.mediscreen.rapport.entity.Patient;
import com.mediscreen.rapport.entity.Report;
import com.mediscreen.rapport.entity.Trigger;
import com.mediscreen.rapport.repository.NoteRepository;
import com.mediscreen.rapport.repository.PatientRepository;
import com.mediscreen.rapport.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportManagerImpl implements ReportManager {
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    TriggerManager triggerManager;


    @Override
    public Report save(Report report) {
        if(report.getId() == 0)
            report.setId(findTopByOrderByIdDesc() != null ? findTopByOrderByIdDesc().getId() + 1 : 1);
        return reportRepository.save(report);
    }

    @Override
    public Report find(int id) {
        return reportRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        reportRepository.delete(find(id));
    }

    @Override
    public List<Report> list() {
        return reportRepository.findAll();
    }

    @Override
    public Report findTopByOrderByIdDesc() {
        return reportRepository.findTopByOrderByIdDesc();
    }

    @Override
    public Report reportPatient(int idPatient) {
        Patient patient = patientRepository.findPatient(idPatient);
        return report(patient);
    }

    @Override
    public Report reportPatient(String familyName) {
        Patient patient = patientRepository.findPatientByName(familyName);
        return report(patient);
    }

    private Report report(Patient patient){
        List<Note> notes = noteRepository.findNoteByPatient(patient.getId());
        List<Trigger> triggers = triggerManager.list();
        int trigger = 0;

        for (Note note : notes) {
            for (Trigger trig : triggers) {
                if(note.getNotes().toUpperCase().contains(trig.getLibelle().toUpperCase()))
                    trigger++;
            }
        }
        String risk = getRisk(patient.getAge(), patient.getSex(), trigger);
        String desc = "Patient: "+ patient.getName() +" "+ patient.getFirstName() +" (age "+ patient.getAge() +") diabetes assessment is: "+ risk +"";
        Report report = new Report(patient.getId(), risk, desc);
        Report reportSave = save(report);
        if(reportSave != null)
            report = reportSave;
        report.setPatient(patient);
        return report;
    }

    private String getRisk(int age, String patSex, int trigger){
        String risk = "None";
        String sex = "M";
        if(age > 30){
            if(trigger == 2)
                risk = "Borderline";
            if(trigger == 6)
                risk = "In Danger";
            if(trigger >= 6)
                risk = "Early onset";
        }else{
            if(sex.equals(patSex)) {
                if(trigger == 3)
                    risk = "In Danger";
                if(trigger == 5)
                    risk = "Early onset";
            }else{
                if(trigger == 4)
                    risk = "In Danger";
                if(trigger == 7)
                    risk = "Early onset";
            }
        }
        return risk;
    }
}
