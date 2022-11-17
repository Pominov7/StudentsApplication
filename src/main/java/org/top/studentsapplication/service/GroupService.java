package org.top.studentsapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.top.studentsapplication.db.entity.Group;
import org.top.studentsapplication.db.entity.Student;
import org.top.studentsapplication.db.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

// Сервис источника данных
@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    // вывод списка групп
    public List<Group> listAllGroups() {
        return (List<Group>) groupRepository.findAll();
    }

    // сохранить группу в БД
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }


    // удалить группу по id
    public void deleteGroupByID(Integer id) {
        // 1. найти группу для удаления
        Optional<Group> result = groupRepository.findById(id);
        // 2. если такая группа есть, то удалить её
        result.ifPresent(groupRepository::delete);
    }
}
