package com.mediscreen.note.manager;

import com.mediscreen.note.entity.Note;
import com.mediscreen.note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NoteManagerImpl implements NoteManager {
    @Autowired
    NoteRepository noteRepository;

    @Override
    public Note save(Note note) {
        if(note.getId() == 0)
            note.setId(findTopByOrderByIdDesc() != null ? findTopByOrderByIdDesc().getId() + 1 : 1);
        return noteRepository.save(note);
    }

    @Override
    public Note find(int id) {
        return noteRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        noteRepository.delete(find(id));
    }

    @Override
    public List<Note> list() {
        return noteRepository.findAll();
    }

    @Override
    public List<Note> listByPatient(int idPatient) {
        return noteRepository.findByIdPatient(idPatient);
    }

    @Override
    public Note findTopByOrderByIdDesc() {
        return noteRepository.findTopByOrderByIdDesc();
    }
}
