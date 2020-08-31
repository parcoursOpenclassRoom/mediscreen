package com.mediscreen.note.manager;

import com.mediscreen.note.entity.Note;

import java.util.List;

/**
 * Note Action Manager
 */
public interface NoteManager {
    /**
     * save and edit note
     * @param note
     * @return
     */
    Note save(Note note);

    /**
     * find note from id
     * @param id
     * @return
     */
    Note find(int id);

    /**
     * delete note from id
     * @param id
     */
    void delete(int id);

    /**
     * get all note
     * @return
     */
    List<Note> list();

    /**
     * get note from id patient
     * @return
     */
    List<Note> listByPatient(int idPatient);

    /**
     * get last record note
     * @return
     */
    Note findTopByOrderByIdDesc();
}
