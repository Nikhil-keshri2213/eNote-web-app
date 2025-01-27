package com.project.enote.service;

import java.util.List;

import com.project.enote.model.notes;
import com.project.enote.model.user;

public interface notesServiceInterface {
    public notes saveNotes(notes notes);
    public notes getNotesbyId(int id);
    public List<notes> getNotesByUser(user user);
    public notes updateNotes(notes notes);
    public boolean deleteNotes(int id);
}
