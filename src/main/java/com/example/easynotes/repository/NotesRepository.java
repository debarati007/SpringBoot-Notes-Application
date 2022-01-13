package com.example.easynotes.repository;

import com.example.easynotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotesRepository extends JpaRepository<Note, Long> {
    @Override
    Optional<Note> findById(Long id);

}
