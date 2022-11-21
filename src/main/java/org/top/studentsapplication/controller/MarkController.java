package org.top.studentsapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.top.studentsapplication.db.entity.Mark;
import org.top.studentsapplication.service.MarkService;

import java.util.List;

@Controller
@RequestMapping(value = "marks")
public class MarkController {

    private final MarkService markService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    // READ (получить все оценки)
    @GetMapping
    public String showAllMarks(Model model) {
        List<Mark> listMarks = markService.listAllMarks();
        model.addAttribute("listSubjects", listMarks);
        return "marks-list";
    }

}
