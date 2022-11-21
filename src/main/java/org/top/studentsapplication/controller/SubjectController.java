package org.top.studentsapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.studentsapplication.db.entity.Group;
import org.top.studentsapplication.db.entity.Subject;
import org.top.studentsapplication.service.SubjectService;

import java.util.List;

@Controller
@RequestMapping(value = "/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // READ (получить все предметы)
    @GetMapping
    public String showAllSubjects(Model model) {
        List<Subject> listSubjects = subjectService.listAllSubjects();
        model.addAttribute("listSubjects", listSubjects);
        return "subjects-list";
    }

    // CREATE (добавить предмет)
    @GetMapping("/newSubject")
    public String showNewSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "subject-form";
    }

    // Обработчик для сохранения предмета
    @PostMapping("/newSubject")
    public String saveNewSubject(Subject subject, RedirectAttributes attrs) {

        Subject saved = subjectService.saveSubject(subject);

        attrs.addFlashAttribute("message",
                "Subject " + saved + " saved successfully");

        return "redirect:/subjects";
    }

    // DELETE (обработчик для удаления предмета)
    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable("id") Integer id, RedirectAttributes attrs) {

        subjectService.deleteSubjectByID(id);
        attrs.addFlashAttribute("message", "Subject deleted");
        return "redirect:/subjects";

    }

    // Обработчик для обновления предмета
    @PostMapping("/update")
    public String updateGroup(@ModelAttribute(value = "subject") Subject subject) {
        subjectService.updateSubject(subject);
        return "redirect:/subjects";
    }

    // UPDATE (редактирование полей группы)
    @GetMapping("/edit/{id}")
    public String showUpdateFormGroup(@PathVariable("id") Integer id, Model model) {
        Subject subject = subjectService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid subject Id:" + id));

        model.addAttribute("subject", subject);
        return "subject-update";
    }
}
