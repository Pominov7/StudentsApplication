package org.top.studentsapplication.service;

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

    // Получение группы по id
    public Optional<Group> getById(Integer id) {
        return groupRepository.findById(id);
    }

    // удалить группу по id
    public void deleteGroupByID(Integer id) {
        // 1. найти группу для удаления
        Optional<Group> result = groupRepository.findById(id);
        // 2. если такая группа есть, то удалить её
        result.ifPresent(groupRepository::delete);
    }


    // Редактирование полей группы
    public void updateGroup(Group group) {
        Optional<Group> optionalGroup = getById(group.getId());
        if (optionalGroup.isPresent()) {
            Group editedGroup = optionalGroup.get();
            if (!editedGroup.equals(group)) {
                editedGroup.setGroupName(group.getGroupName());
                groupRepository.save(editedGroup);
            }
        }
    }

    // получения группы по строке
    public List<Group> findGroupByContains(String match) {
        if (match == null || match.equals(""))
            return (List<Group>) groupRepository.findAll();
        return ((List<Group>) groupRepository.findAll())
                .stream()
                .filter(s -> s.getGroupName().contains(match))
                .toList();
    }
}
