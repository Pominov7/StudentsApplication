package org.top.studentsapplication.service;

import org.springframework.stereotype.Service;
import org.top.studentsapplication.db.entity.Mark;
import org.top.studentsapplication.db.repository.MarksRepository;

import java.util.List;

@Service
public class MarkService {

    private final MarksRepository marksRepository;


    public MarkService(MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }

    public List<Mark> listAllMarks() {
        return (List<Mark>) marksRepository.findAll();
    }
}
