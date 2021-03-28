package com.itstep.ckj13_mvc.controller;

import com.itstep.ckj13_mvc.entity.Note;
import com.itstep.ckj13_mvc.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @Autowired


    @GetMapping("/create")
    public String create() {
        return "create-note";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute(name = "note") Note note) {
        noteService.save(note);
        return "redirect:/notes";
    }

    @GetMapping
    public String all(Model model) {
        model.addAttribute("notes", noteService.findAll());
        return "notes";
    }

    @GetMapping("/{id}")
    public String info(@PathVariable(name = "id") int id, Model model) {
        // ищем объект по ид
        Note note = noteService.findById(id);
        model.addAttribute("note", note);
        return "info";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        noteService.deleteById(id);
        return "redirect:/notes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("note", noteService.findById(id));
        return "edit-note";
    }
    @PostMapping("/edit")
    public String updateUser(Note note){
        noteService.save(note);
        return "redirect:/notes";
    }
    @GetMapping("/search")
    public String search(@RequestParam(name = "word") String word, Model model) {
        List<Note> notes = noteService.search("%"+word+"%");
        model.addAttribute("notes", notes);
        return "notes";
    }

    }


