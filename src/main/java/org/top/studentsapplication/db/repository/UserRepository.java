package org.top.studentsapplication.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.top.studentsapplication.db.entity.User;

// интерфейс для работы с БД с таблицей User
public interface UserRepository extends CrudRepository<User, Integer> {
}
