package com.mediscreen.rapport.manager;

import com.mediscreen.rapport.entity.Trigger;

import java.util.List;

/**
 * Trigger Action Manager
 */
public interface TriggerManager {
    /**
     * save and edit trigger
     * @param trigger
     * @return
     */
    Trigger save(Trigger trigger);

    /**
     * find trigger from id
     * @param id
     * @return
     */
    Trigger find(int id);

    /**
     * delete trigger from id
     * @param id
     */
    void delete(int id);

    /**
     * get all trigger
     * @return
     */
    List<Trigger> list();

    /**
     * get last record trigger
     * @return
     */
    Trigger findTopByOrderByIdDesc();

    /**
     * get trigger from libelle
     * @return
     */
    Trigger findByLibelle(String libelle);
}
