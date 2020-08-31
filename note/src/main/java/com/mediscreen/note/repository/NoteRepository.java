package com.mediscreen.note.repository;

import com.mediscreen.note.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository  extends MongoRepository<Note, Integer> {
    Note save(Note note);
    Note findById(int id);
    void delete(Note note);
    List findAll();
    Note findTopByOrderByIdDesc();
    List findByIdPatient(int id);
}
