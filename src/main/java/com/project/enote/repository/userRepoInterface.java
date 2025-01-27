package com.project.enote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.enote.model.user;

public interface userRepoInterface extends JpaRepository<user,Integer>{
    public boolean existsByEmail(String email);
    public user findByEmail(String email);
}
