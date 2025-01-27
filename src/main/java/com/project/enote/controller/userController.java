package com.project.enote.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.enote.model.notes;
import com.project.enote.model.user;
import com.project.enote.repository.userRepoInterface;
import com.project.enote.service.notesServiceInterface;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    private userRepoInterface userRepo;
    @Autowired
    private notesServiceInterface notesService;

    @ModelAttribute
    public user getUser(Principal p, Model m){
        String email = p.getName();
        user  u = userRepo.findByEmail(email);
        m.addAttribute("user",u);
        return u;
    }

    @GetMapping("/addNotes")
    public String addNotes(){
        return "add_notes";
    }

    @PostMapping("/saveNotes")
    public String saveNotes(@ModelAttribute notes n, HttpSession session, Principal p, Model m){
        n.setDate(LocalDate.now());
        n.setUser(getUser(p, m));
        notes saveNote = notesService.saveNotes(n);
        if (saveNote!=null) {
            session.setAttribute("msg", "Notes Saved Sucessfully.");
        }else{
            session.setAttribute("msg", "Something wrong on server.");
        }
        return "redirect:/user/addNotes";
    }

    @GetMapping("/editNotes/{id}")
    public String editNotes(@PathVariable int id, Model m){
        notes n = notesService.getNotesbyId(id);
        m.addAttribute("n", n);
        return "edit_notes";
    }

    @GetMapping("/viewNotes")
    public String viewNotes(Principal p, Model m){
        List<notes> notesList = notesService.getNotesByUser(getUser(p, m));
        m.addAttribute("notesList", notesList);
        return "view_notes";
    }
   
    @PostMapping("/updateNotes")
    public String updateNotes(@ModelAttribute notes n, HttpSession session, Principal p, Model m){
        n.setDate(LocalDate.now());
        n.setUser(getUser(p, m));
        notes saveNote = notesService.saveNotes(n);
        if (saveNote!=null) {
            session.setAttribute("msg", "Notes Updated Sucessfully.");
        }else{
            session.setAttribute("msg", "Something wrong on server.");
        }
        return "redirect:/user/viewNotes";
    }

    @GetMapping("/deleteNotes/{id}")
    public String deleteNote(@PathVariable int id, Model m, HttpSession session){
        boolean f = notesService.deleteNotes(id);
        if (f) {
            session.setAttribute("msg", "Notes Deleted Sucessfully.");
        }else{
            session.setAttribute("msg", "Something wrong on server.");
        }
        return "redirect:/user/viewNotes";
    }
}
