package com.mediscreen.rapport;

import com.mediscreen.rapport.entity.Note;
import com.mediscreen.rapport.entity.Patient;
import com.mediscreen.rapport.entity.Report;
import com.mediscreen.rapport.entity.Trigger;
import com.mediscreen.rapport.manager.ReportManagerImpl;
import com.mediscreen.rapport.manager.TriggerManager;
import com.mediscreen.rapport.repository.NoteRepository;
import com.mediscreen.rapport.repository.PatientRepository;
import com.mediscreen.rapport.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ReportTest {
    @Mock
    PatientRepository patientRepository;
    @Mock
    NoteRepository noteRepository;
    @Mock
    TriggerManager triggerManager;
    @Mock
    ReportRepository reportRepository;
    @InjectMocks
    ReportManagerImpl reportManager;

    @BeforeEach
    public void setupTest(){
        Patient patient = new Patient(1, "Ferguson", "Lucas", "M", new Date(), "2 Warren Street ", "387-866-1399");
        List<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger("Hémoglobine A1C"));
        triggers.add(new Trigger("Microalbumine"));
        triggers.add(new Trigger("Taille"));
        Report report = new Report(patient.getId(), "None", "" );
        when(patientRepository.findPatient(patient.getId())).thenReturn(patient);
        when(triggerManager.list()).thenReturn(triggers);
        when(reportRepository.save(report)).thenReturn(report);
    }

    @Test
    public void reportPatientNoneTest(){
        List<Note> notes = new ArrayList<>();
        Patient patient = new Patient(1, "Ferguson", "Lucas", "M", new Date(), "2 Warren Street ", "387-866-1399");
        notes.add(new Note("Patient states that they are 'feeling terrific' Weight at or below ", patient.getId()));
        when(noteRepository.findNoteByPatient(patient.getId())).thenReturn(notes);


        Report report = new Report(patient.getId(), "None", "" );
        Report report1 =  reportManager.reportPatient(patient.getId());
       assertEquals(report.getRisk(), report1.getRisk());
    }

    @Test
    public void reportPetientBorderlineTest() throws ParseException {
        List<Note> notes = new ArrayList<>();
        Patient patient = new Patient(1, "Ferguson", "Lucas", "M", new SimpleDateFormat("yyyy-MM-dd").parse("1980-01-01"), "2 Warren Street ", "387-866-1399");
        notes.add(new Note("Patient states that they are Microalbumine Weight at or below ", patient.getId()));
        notes.add(new Note("Patient states that they are Taille Weight at or below ", patient.getId()));
        when(noteRepository.findNoteByPatient(patient.getId())).thenReturn(notes);
        when(patientRepository.findPatient(patient.getId())).thenReturn(patient);


        Report report = new Report(patient.getId(), "Borderline", "" );
        Report report1 =  reportManager.reportPatient(patient.getId());
        assertEquals(report.getRisk(), report1.getRisk());
    }

    @Test
    public void reportPetientInDangerTest()  {
        List<Note> notes = new ArrayList<>();
        Patient patient = new Patient(1, "Ferguson", "Lucas", "M", new Date(), "2 Warren Street ", "387-866-1399");
        notes.add(new Note("Patient states that they are Microalbumine Weight at or below ", patient.getId()));
        notes.add(new Note("Patient states that they are Taille Weight at Hémoglobine A1C or below ", patient.getId()));
        when(noteRepository.findNoteByPatient(patient.getId())).thenReturn(notes);
        when(patientRepository.findPatient(patient.getId())).thenReturn(patient);


        Report report = new Report(patient.getId(), "In Danger", "" );
        Report report1 =  reportManager.reportPatient(patient.getId());
        assertEquals(report.getRisk(), report1.getRisk());
    }

    @Test
    public void reportPetientEarlyOnsetTest()  {
        List<Note> notes = new ArrayList<>();
        Patient patient = new Patient(1, "Ferguson", "Lucas", "M", new Date(), "2 Warren Street ", "387-866-1399");
        notes.add(new Note("Patient states that they are Microalbumine Weight at or below ", patient.getId()));
        notes.add(new Note("Patient states that they are Microalbumine Weight Taille at or below ", patient.getId()));
        notes.add(new Note("Patient states that they are Taille Weight at Hémoglobine A1C or below ", patient.getId()));
        when(noteRepository.findNoteByPatient(patient.getId())).thenReturn(notes);
        when(patientRepository.findPatient(patient.getId())).thenReturn(patient);


        Report report = new Report(patient.getId(), "Early onset", "" );
        Report report1 =  reportManager.reportPatient(patient.getId());
        assertEquals(report.getRisk(), report1.getRisk());
    }
}
