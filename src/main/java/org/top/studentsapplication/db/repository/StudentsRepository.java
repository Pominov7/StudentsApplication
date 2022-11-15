package org.top.studentsapplication.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.top.studentsapplication.db.entity.Student;

public interface StudentsRepository extends CrudRepository<Student, Integer> {
}
