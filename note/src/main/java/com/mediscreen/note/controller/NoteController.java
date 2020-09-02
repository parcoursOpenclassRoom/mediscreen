package com.mediscreen.note.controller;

import com.mediscreen.note.entity.Note;
import com.mediscreen.note.manager.NoteManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patHistory")
public class NoteController {
    @Autowired
    NoteManager noteManager;

    @GetMapping
    public List<Note> findAll(){
        return noteManager.list();
    }

    @GetMapping("/{id}")
    public Note findPatient(@PathVariable int id){
        return noteManager.find(id);
    }

    @GetMapping("/patient/{id}")
    public List<Note> findNoteByPatient(@PathVariable int id){
        return noteManager.listByPatient(id);
    }

    @PostMapping
    public Note create(@RequestBody Note note){
        return noteManager.save(note);
    }

    @PostMapping("/add")
    public Note create(@RequestParam String patId){
        String[] parts = patId.split("¬e=");
        return noteManager.save(new Note(parts[1], Integer.parseInt(parts[0]) ));
    }

    @PutMapping
    public Note update(@RequestBody Note note){
        return noteManager.save(note);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        noteManager.delete(id);
    }
}
