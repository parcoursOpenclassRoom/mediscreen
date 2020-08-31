package com.mediscreen.note;

import com.mediscreen.note.entity.Note;
import com.mediscreen.note.manager.NoteManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NoteITTest {
    @Autowired
    NoteManager noteManager;

    @Test
    public void NoteManagerTest() {
        Note note = new Note(1, "Test Note", 1);

        // Save
        note = noteManager.save(note);
        assertNotNull(note.getId());
        assertEquals(note.getNotes(), "Test Note", "Test Note");

        // Update
        note.setIdPatient(2);
        note = noteManager.save(note);
        assertEquals(note.getIdPatient(), 2, 2);

        // Find
        List<Note> listResult = noteManager.list();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = note.getId();
        noteManager.delete(id);
        note = noteManager.find(id);
        assertNull(note);
    }
}
