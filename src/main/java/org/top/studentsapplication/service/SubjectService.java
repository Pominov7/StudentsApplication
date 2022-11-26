package org.top.studentsapplication.service;

import org.springframework.stereotype.Service;
import org.top.studentsapplication.db.entity.Group;
import org.top.studentsapplication.db.entity.Mark;
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

    // Редактирование предмета студента
    public void updateSubject(Subject subject) {
        if (subject.getId() != null) {
            Optional<Subject> optionalItem = subjectRepository.findById(subject.getId());
            if (optionalItem.isPresent()) {
                Subject editedItem = optionalItem.get();

                if (!editedItem.equals(subject)) {
                    editedItem.setSubjectName(subject.getSubjectName());
                    subjectRepository.save(editedItem);
                }
            }
        } else {
            subjectRepository.save(subject);
        }
    }

    // удалить предмет по id
    public void deleteSubjectByID(Integer id) {
        Optional<Subject> result = subjectRepository.findById(id);
        result.ifPresent(subjectRepository::delete);
    }

    // получения предмета по строке
    public List<Subject> findSubjectByContains(String match) {
        if (match == null || match.equals(""))
            return (List<Subject>) subjectRepository.findAll();
        return ((List<Subject>) subjectRepository.findAll())
                .stream()
                .filter(s -> s.getSubjectName().contains(match))
                .toList();
    }
}
