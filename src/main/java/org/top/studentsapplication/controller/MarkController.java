package org.top.studentsapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    private final MarkService markService;
    private final StudentService studentService;

    private final SubjectService subjectService;


    @Autowired
    public MarkController(MarkService markService, StudentService studentService, SubjectService subjectService) {
        this.markService = markService;
        this.studentService = studentService;
        this.subjectService = subjectService;
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
    public String showNewMarkForm(Model model,@RequestParam(name = "date", required = false) String strDate) {
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

    // Обработчик для сохранения предмета
    @PostMapping("/newMark")
    public String saveNewSubject(Mark mark, RedirectAttributes attrs) {

        Mark saved = markService.saveMark(mark);

        attrs.addFlashAttribute("message",
                "Mark " + saved + " saved successfully");

        return "redirect:/marks";
    }

}
