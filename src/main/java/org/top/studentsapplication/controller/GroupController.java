package org.top.studentsapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.studentsapplication.db.entity.Group;
import org.top.studentsapplication.db.entity.Student;
import org.top.studentsapplication.db.repository.StudentsRepository;
import org.top.studentsapplication.service.GroupService;

import java.util.List;

@Controller
@RequestMapping(value = "/groups")
public class GroupController {
    @Autowired
    private StudentsRepository studentsRepository;
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // READ (получить все группы)
    @GetMapping
    public String showAllGroups(Model model) {
        List<Group> listGroups = groupService.listAllGroups();
        model.addAttribute("listGroups", listGroups);
        return "groups-list";
    }


    // CREATE (добавить группу)
    @GetMapping("/newGroup")
    public String showNewGroupForm(Model model) {
        model.addAttribute("group", new Group());
        return "group-form";
    }

    // Обработчик для сохранения группы
    @PostMapping("/newGroup")
    public String saveNewGroup(Group group, RedirectAttributes attrs) {
        // 1. сохраняем новую группу в БД
        Group saved = groupService.saveGroup(group);
        // 2. добавить сообщение о том, что группа добавлена
        attrs.addFlashAttribute("message",
                "Group " + saved + " saved successfully");
        // 3. выполнить перенаправление
        return "redirect:/groups";
    }

    // DELETE (обработчик для удаления группы)
    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") Integer id, RedirectAttributes attrs) {
        // зачистка в таблице студентов ссылок на группу с id =
        studentsRepository.clearGroupInStudentByGroup(id);
        groupService.deleteGroupByID(id);
        attrs.addFlashAttribute("message", "Group deleted");
        return "redirect:/groups";

    }

    // Обработчик для обновления студента
    @PostMapping("/update")
    public String updateGroup(@ModelAttribute(value = "group") Group group) {
        groupService.updateGroup(group);
        return "redirect:/groups";
    }

    // UPDATE (редактирование полей студента)
    @GetMapping("/edit/{id}")
    public String showUpdateFormGroup(@PathVariable("id") Integer id, Model model) {
        Group group = groupService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id:" + id));

        model.addAttribute("group", group);
        return "group-update";
    }

    // просмотр полной информации о группе
    @GetMapping("/details/{id}")
    public String infoGroup(@PathVariable("id") Integer id, Model model) {
        Group group = groupService.getById(id).get();
        model.addAttribute(group);
        return "group-info";

    }
}
