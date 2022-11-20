package org.top.studentsapplication.service;

import org.springframework.stereotype.Service;
import org.top.studentsapplication.db.entity.Group;
import org.top.studentsapplication.db.entity.Subject;
import org.top.studentsapplication.db.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;

    }

    // вывод списка предметов
    public List<Subject> listAllSubjects() {
        return (List<Subject>) subjectRepository.findAll();
    }

    // Получение предмета по id
    public Optional<Subject> getById(Integer id) {
        return subjectRepository.findById(id);
    }

    // сохранить предмет в БД
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }


}
