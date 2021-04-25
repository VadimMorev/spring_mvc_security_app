package com.itstep.ckj13_mvc.controller;

import com.itstep.ckj13_mvc.entity.Note;
import com.itstep.ckj13_mvc.entity.User;
import com.itstep.ckj13_mvc.repo.NoteRepository;
import com.itstep.ckj13_mvc.repo.UserRepository;
import com.itstep.ckj13_mvc.service.NoteService;
//import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class ApiController {
    private final UserRepository userRepository;
    private final NoteService noteService;
    private final NoteRepository noteRepository;

    @Autowired
    public ApiController(UserRepository userRepository, NoteService noteService, NoteRepository noteRepository) {
        this.userRepository = userRepository;
        this.noteService = noteService;
        this.noteRepository = noteRepository;
    }

    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/notes")
    public List<EntityModel<Note>> getNotes() {
//        User user = userRepository.findByUsername(principal.getName());
//        return user.getNotes();
        var notes = noteService.findAll();
        var models = notes.stream().map(note->{
            var entityModel = EntityModel.of(note,linkTo(methodOn(ApiController.class).getNote(note.getId())).withRel("info"),
                    linkTo(methodOn(ApiController.class).deleteNote(note.getId())).withRel("delete"),
                    linkTo(methodOn(ApiController.class).changeNote(note)).withRel("edit"));
            return entityModel;
        }).collect(Collectors.toList());
        return models;
    }

    @PostMapping("/notes")
    public Note saveNote(@RequestBody Note note) {

        return noteService.save(note);
    }

    @GetMapping("notes/{id}")
    public EntityModel<Note> getNote(@PathVariable(name = "id") int id) {
        var note = noteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id does not exist: " + id));
        EntityModel<Note> response =  EntityModel.of(note, linkTo(methodOn(ApiController.class).getNotes()).withRel("notes"));
        return response;
    }

    @PutMapping("/notes")
    public Note changeNote(@RequestBody Note note) {
        return noteService.save(note);
    }

    @DeleteMapping("/notes/{id}")
    public Note deleteNote(@PathVariable Integer id) {
        Note note = noteService.findById(id);
        noteService.deleteById(id);
        return note;
    }

    @GetMapping("/notes/search")
    public Iterable<Note> searchNotes(@RequestParam(name = "word") String word, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<Note> userNotes = user.getNotes();
        List<Note> notes = noteService.search("%" + word + "%");
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            if (!userNotes.contains(note)) {
                notes.remove(note);
            }
        }
        return notes;
    }

}
