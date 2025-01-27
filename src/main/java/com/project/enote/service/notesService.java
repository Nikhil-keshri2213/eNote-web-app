package com.project.enote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.enote.model.notes;
import com.project.enote.model.user;
import com.project.enote.repository.notesRepoInteface;

@Service
public class notesService implements notesServiceInterface {

    @Autowired
    private notesRepoInteface noteRepo;


    @Override
    public notes saveNotes(notes notes) {
        return noteRepo.save(notes);
    }

    @Override
    public notes getNotesbyId(int id) {
        return noteRepo.findById(id).get();
    }

    @Override
    public List<notes> getNotesByUser(user user) {
        return noteRepo.findByUser(user);
    }

    @Override
    public notes updateNotes(notes notes) {
        return noteRepo.save(notes);
    }

    @Override
    public boolean deleteNotes(int id) {
        notes n = noteRepo.findById(id).get();
        if (n!=null) {
            noteRepo.delete(n);
            return true;
        }
        return false;
    }
    
}
