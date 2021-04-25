package com.itstep.ckj13_mvc.service;

import com.itstep.ckj13_mvc.entity.Note;
import com.itstep.ckj13_mvc.repo.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    public Note save(Note note){
        note.setDateOfCreation(LocalDateTime.now());
         return noteRepository.save(note);
    }
    public List<Note> findAll(){
        return noteRepository.findAll();
    }
    public Note findById(int id){
        return noteRepository.findById(id).get();
    }
    public List<Note> search(String word){
        return noteRepository.search(word);
    }
    public void deleteById(int id){
        noteRepository.deleteById(id);
    }
}
