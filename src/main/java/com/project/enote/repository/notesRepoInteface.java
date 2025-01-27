package com.project.enote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.enote.model.notes;
import com.project.enote.model.user;

public interface notesRepoInteface extends JpaRepository<notes,Integer>{
    public List<notes> findByUser(user user);
}
