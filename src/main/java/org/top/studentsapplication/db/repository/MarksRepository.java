package org.top.studentsapplication.db.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.top.studentsapplication.db.entity.Mark;

import java.util.List;

public interface MarksRepository extends CrudRepository<Mark, Integer> {

    // получение среднего балла по каждому предмету студента по Id студента
    @Query("select s.subjectName, avg(a.assessment) from Mark a join Subject s on a.subject = s where a.student.id=:studentId group by a.subject.id")
    List<String> findAvgMarksByStudentId(@Param("studentId") Integer studentId);

    // получения среднего балла студента по всем предметам по Id студента
    @Query("select avg(a.assessment) from Mark a where a.student.id=:studentId")
    Double avgScoreSubjectByStudentId(@Param("studentId") Integer studentId);
}
