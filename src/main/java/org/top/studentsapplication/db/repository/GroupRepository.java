package org.top.studentsapplication.db.repository;


import org.springframework.data.repository.CrudRepository;
import org.top.studentsapplication.db.entity.Group;

public interface GroupRepository extends CrudRepository<Group, Integer> {
}
