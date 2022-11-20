package org.top.studentsapplication.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.top.studentsapplication.db.entity.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {
}
