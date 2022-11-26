package org.top.studentsapplication.service;

import org.springframework.stereotype.Service;
import org.top.studentsapplication.db.entity.Mark;
import org.top.studentsapplication.db.repository.MarksRepository;

import java.util.*;

@Service
public class MarkService {

    private final MarksRepository marksRepository;

    public MarkService(MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }

    // вывод списка оценок
    public List<Mark> listAllMarks() {
        return (List<Mark>) marksRepository.findAll();
    }

    // Получение оценку по id
    public Optional<Mark> getById(Integer id) {
        return marksRepository.findById(id);
    }

    // сохранить оценку в БД
    public Mark saveMark(Mark mark) {
        return marksRepository.save(mark);
    }

    // Редактирование оценки у студента
    public void updateMark(Mark mark) {
        Optional<Mark> optionalItem = marksRepository.findById(mark.getId());
        if (optionalItem.isPresent()) {
            Mark editedItem = optionalItem.get();

            if (!editedItem.equals(mark)) {
                editedItem.setDate(mark.getDate());
                editedItem.setAssessment(mark.getAssessment());
                marksRepository.save(editedItem);
            }
        }
    }

    // удалить оценку по id
    public void deleteMarkByID(Integer id) {
        Optional<Mark> result = marksRepository.findById(id);
        result.ifPresent(marksRepository::delete);
    }

    // Вернуть список оценок для определенного студента
    public List<Mark> listMarkStudentId(Integer id) {
        List<Mark> marks = (List<Mark>) marksRepository.findAll();
        List<Mark> result = new ArrayList<>();
        for (Mark assessment : marks
        ) {
            if (Objects.equals(assessment.getStudent().getId(), id)) {
                result.add(assessment);
            }
        }

        return result;

    }

    // получение среднего балла по каждому предмету студента по Id студента
    public List<String> findAvgMarksByStudentId(Integer studentId) {

        return marksRepository.findAvgMarksByStudentId(studentId).stream().toList();
    }

    // получения среднего балла студента по всем предметам по Id студента
    public Double avgScoreSubjectByStudentId(Integer studentId) {
        return marksRepository.avgScoreSubjectByStudentId(studentId);
    }
}



