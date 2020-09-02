package com.mediscreen.rapport.manager;

import com.mediscreen.rapport.entity.Note;
import com.mediscreen.rapport.entity.PatientReport;
import com.mediscreen.rapport.entity.Report;
import com.mediscreen.rapport.entity.Trigger;
import com.mediscreen.rapport.repository.NoteRepository;
import com.mediscreen.rapport.repository.PatientRepository;
import com.mediscreen.rapport.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map reportPatient(int idPatient) {
        PatientReport patientReport = patientRepository.findPatient(idPatient);
        return report(patientReport);
    }

    @Override
    public Map reportPatient(String familyName) {
        PatientReport patientReport = patientRepository.findPatientByName(familyName);
        return report(patientReport);
    }

    private Map report(PatientReport patientReport){
        Map result = new HashMap();
        List<Note> notes = noteRepository.findNoteByPatient(patientReport.getId());
        List<Trigger> triggers = triggerManager.list();
        int trigger = 0;

        for (Note note : notes) {
            for (Trigger trig : triggers) {
                if(note.getNotes().toUpperCase().contains(trig.getLibelle().toUpperCase()))
                    trigger++;
            }
        }
        String risk = getRisk(patientReport.getAge(), patientReport.getSex(), trigger);
        String status = "Patient: "+ patientReport.getName() +" "+ patientReport.getFirstName() +" (age "+ patientReport.getAge() +") diabetes assessment is: "+ risk +"";
        patientReport.setStatus(risk);
        result.put("msg", status);
        result.put("data", patientReport);
        return result;
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
