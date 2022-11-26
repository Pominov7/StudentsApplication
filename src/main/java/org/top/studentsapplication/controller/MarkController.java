package org.top.studentsapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.studentsapplication.db.entity.Mark;
import org.top.studentsapplication.db.entity.Student;
import org.top.studentsapplication.db.entity.Subject;
import org.top.studentsapplication.service.MarkService;
import org.top.studentsapplication.service.StudentService;
import org.top.studentsapplication.service.SubjectService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "marks")
public class MarkController {
    @Autowired
    private MarkService markService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;

    }

    // READ (получить все оценки)
    @GetMapping
    public String showAllMarks(Model model) {
        List<Mark> listMarks = markService.listAllMarks();
        model.addAttribute("listMarks", listMarks);
        return "marks-list";
    }

    // CREATE (добавить оценку)
    @GetMapping("/newMark")
    public String showNewMarkForm(Model model, @RequestParam(name = "date", required = false) String strDate) {
        model.addAttribute("mark", new Mark());
        Mark item = new Mark();
        if (strDate != null) {
            item.setDate(LocalDate.parse(strDate));
        }
        List<Student> studentsList = studentService.listAllStudents();
        model.addAttribute("studentsList", studentsList);
        List<Subject> subjectList = subjectService.listAllSubjects();
        model.addAttribute("subjectList", subjectList);
        return "mark-form";
    }

    // Обработчик для сохранения оценки
    @PostMapping("/newMark")
    public String saveNewMark(Mark mark, RedirectAttributes attrs) {

        Mark saved = markService.saveMark(mark);

        attrs.addFlashAttribute("message",
                "Mark " + saved + " saved successfully");

        return "redirect:/marks";
    }

    // UPDATE (редактирование оценки)
    @GetMapping("/edit/{id}")
    public String showUpdateForm(Model model, @RequestParam(name = "date", required = false) String strDate
            , @PathVariable("id") Integer id) {
        Mark mark;
        if (id != null) {
            mark = markService.getById(id).get();
        } else {
            mark = new Mark();
            if (strDate != null) {
                mark.setDate(LocalDate.parse(strDate));
            }
        }

        model.addAttribute("mark", mark);

        List<Student> studentsList = studentService.listAllStudents();
        model.addAttribute("studentsList", studentsList);

        List<Subject> subjectList = subjectService.listAllSubjects();
        model.addAttribute("subjectList", subjectList);

        return "mark-update";
    }


    // Обработчик для обновления оценки
    @PostMapping("/update")
    public String updateMark(@ModelAttribute(value = "mark") Mark mark) {
        markService.updateMark(mark);
        return "redirect:/marks";
    }

    // DELETE (обработчик для удаления оценки у студента)
    @GetMapping("/delete/{id}")
    public String deleteMark(@PathVariable("id") Integer id, RedirectAttributes attrs) {
        markService.deleteMarkByID(id);
        attrs.addFlashAttribute("message", "Mark deleted");
        return "redirect:/marks";
    }
}
