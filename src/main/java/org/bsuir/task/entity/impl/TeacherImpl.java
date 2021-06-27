package org.bsuir.task.entity.impl;

import org.bsuir.task.entity.Group;
import org.bsuir.task.entity.Student;
import org.bsuir.task.entity.Teacher;
import org.bsuir.task.exception.CustomException;
import org.bsuir.task.repository.GroupRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherImpl implements Teacher {
    @Override
    public synchronized List<Student> getStudentsFromGroup(Group group) {

        List<Group> groupList = GroupRepository.getInstance().getGroups();

        for (Group currentGroup : groupList) {
            if (currentGroup.equals(group)) {
                return currentGroup.getStudents();
            }
        }
        throw new CustomException("Group doesn't exists");
    }

    @Override
    public synchronized Map<Student, Group> getStudentsByFullName(String fullName) {
        Map<Student, Group> result = new HashMap<>();
        List<Group> groupImplList =GroupRepository.getInstance().getGroups();

        for (Group group : groupImplList) {
            List<Student> students = group.getStudents();
            for (Student student : students) {
                if (student.getFullName().equals(fullName)) {
                    result.put(student, group);
                }
            }
        }
        return result;
    }
}
