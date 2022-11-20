package org.top.studentsapplication.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.top.studentsapplication.db.entity.Mark;

public interface MarksRepository extends CrudRepository<Mark, Integer> {
}
