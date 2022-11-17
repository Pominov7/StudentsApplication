package org.top.studentsapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.studentsapplication.db.entity.Group;
import org.top.studentsapplication.db.entity.Student;
import org.top.studentsapplication.service.GroupService;
import org.top.studentsapplication.service.StudentService;

import java.util.List;

@Controller
@RequestMapping(value = "/students")
public class StudentController {
    private final StudentService service;

    @Autowired
    private final GroupService groupService;

    public StudentController(StudentService service, GroupService groupService) {
        this.service = service;
        this.groupService = groupService;
    }

    // READ (получить всех студентов)
    @GetMapping
    public String showAllStudents(Model model) {
        List<Student> listStudents = service.listAllStudents();
        model.addAttribute("listStudents", listStudents);
        return "students-list";
    }

    // CREATE (добавить студента)
    @GetMapping("/new")
    public String showNewStudentForm(Model model) {
        model.addAttribute("student", new Student());

        //
        List<Group> groups = groupService.listAllGroups();
        model.addAttribute("groupsList", groups);
        return "student-form";
    }

    // Обработчик для сохранения студента
    @PostMapping("/new")
    public String saveNewStudent(Student student, RedirectAttributes attrs) {
        // 1. сохраняем нового студента в БД
        Student saved = service.saveStudent(student);
        // 2. добавить сообщение о том, что студент добавлен
        attrs.addFlashAttribute("message",
                "Student " + saved + " saved successfully");
        // 3. выполнить перенаправление
        return "redirect:/students";
    }

    // DELETE (обработчик для удаления студент)
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id, RedirectAttributes attrs) {
        service.deleteStudentByID(id);
        attrs.addFlashAttribute("message", "Student deleted");
        return "redirect:/students";

    }

    // Обработчик для обновления студента
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute(value = "student") Student student) {
        service.updateStudent(student);
        return "redirect:/students";
    }

    // UPDATE (редактирование полей студента)
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Student student = service.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("student", student);
        return "student-update";
    }

    @GetMapping("/details/{id}")
    public String infoStudent(@PathVariable("id") Integer id, Model model) {
        Student student = service.getById(id).get();
        model.addAttribute(student);
        return "student-info";

    }
}
