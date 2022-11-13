package org.top.studentsapplication.service;

import org.springframework.stereotype.Service;
import org.top.studentsapplication.db.Student;
import org.top.studentsapplication.db.StudentsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentsRepository repository;

    public StudentService(StudentsRepository repository) {
        this.repository = repository;
    }

    // READ (получить всех студентов)
    public List<Student> listAllStudents() {
        return (List<Student>) repository.findAll();
    }

    // сохранить студента в БД
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    // удалить студента по id
    public void deleteStudentByID(Integer id) {
        // 1. найти студента для удаления
        Optional<Student> result = repository.findById(id);
        // 2. если такой студент есть, то удалить его
        result.ifPresent(repository::delete);
    }

    // Получение студента по id
    public Optional<Student> getById(Integer id){
        return repository.findById(id);
    }

    // Редактирование полей студента
    public void  updateStudent(Student student) {
        Optional<Student> optionalStudent = getById(student.getId());
        if (optionalStudent.isPresent()) {
            Student editedStudent = optionalStudent.get();
            if (!editedStudent.equals(student)) {
                editedStudent.setFirstName(student.getFirstName());
                editedStudent.setLastName(student.getLastName());
                repository.save(editedStudent);
            }
        }
    }


    public  Optional<Student> showInfoStudent(Integer id) {
        // 1. найти студента
     Optional<Student> student = repository.findById(id);
        // 2. если такой студент, то вывести все его поля
        return student;
    }
}
