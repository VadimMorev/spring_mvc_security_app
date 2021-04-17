package com.itstep.ckj13_mvc.controller;

import com.itstep.ckj13_mvc.entity.Note;
import com.itstep.ckj13_mvc.entity.User;
import com.itstep.ckj13_mvc.repo.UserRepository;
import com.itstep.ckj13_mvc.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private NoteService noteService;
    private UserRepository userRepository;

    @Autowired
    public NoteController(NoteService noteService, UserRepository userRepository) {
        this.noteService = noteService;
        this.userRepository = userRepository;
    }

    @GetMapping("/create")
    public String create() {
        return "create-note";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute(name = "note") Note note, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        user.addNote(note);
        noteService.save(note);
        userRepository.save(user);
        return "redirect:/notes";
    }

    @GetMapping
    public String all(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("notes", user.getNotes());
        return "notes";
    }

    @GetMapping("/{id}")
    public String info(@PathVariable(name = "id") int id, Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<Note> notes = user.getNotes();
        Note note = noteService.findById(id);
        if (notes.contains(note)) {
            model.addAttribute("note", note);
            return "info";
        } else {
            return "redirect:/notes";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") int id, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<Note> notes = user.getNotes();
        Note note = noteService.findById(id);
        if (notes.contains(note)) {
            noteService.deleteById(id);
            return "redirect:/notes";
        } else {
            return "redirect:/notes";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") int id, Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<Note> notes = user.getNotes();
        Note note = noteService.findById(id);
        if (notes.contains(note)) {
            model.addAttribute("note", note);
            return "edit-note";
        } else {
            return "redirect:/notes";
        }
    }

    @PostMapping("/edit")
    public String updateUser(Note note, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        user.addNote(note);
        noteService.save(note);
        userRepository.save(user);
        return "redirect:/notes";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "word") String word, Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<Note> userNotes = user.getNotes();
        List<Note> notes = noteService.search("%" + word + "%");
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            if (!userNotes.contains(note)) {
                notes.remove(note);
            }
        }
        model.addAttribute("notes", notes);
        return "notes";
    }

}


