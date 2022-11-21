package org.top.studentsapplication.service;

import org.springframework.stereotype.Service;
import org.top.studentsapplication.db.entity.Mark;
import org.top.studentsapplication.db.entity.Student;
import org.top.studentsapplication.db.repository.MarksRepository;

import java.util.List;
import java.util.Optional;

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
        if (mark.getId() != null) {
            Optional<Mark> optionalItem = marksRepository.findById(mark.getId());
            if (optionalItem.isPresent()) {
                Mark editedItem = optionalItem.get();

                if (!editedItem.equals(mark)) {
                    editedItem.setStudent(mark.getStudent());
                    editedItem.setSubject(mark.getSubject());
                    editedItem.setDate(mark.getDate());
                    editedItem.setAssessment(mark.getAssessment());
                    marksRepository.save(editedItem);
                }
            }
        } else {
            marksRepository.save(mark);
        }
    }

    // удалить оценку по id
    public void deleteMarkByID(Integer id) {
        Optional<Mark> result = marksRepository.findById(id);
        result.ifPresent(marksRepository::delete);
    }

}

