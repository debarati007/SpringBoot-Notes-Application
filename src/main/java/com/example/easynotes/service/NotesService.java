package com.example.easynotes.service;

import com.example.easynotes.exception.ElementNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotesService {
    @Autowired
    private NotesRepository notesRepository;

    public Note getNoteById(Long id) {
        return notesRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Could not find city with ID provided"));

    }


}
