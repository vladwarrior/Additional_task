package org.bsuir.task.entity.impl;

import org.bsuir.task.entity.Dean;
import org.bsuir.task.entity.Group;
import org.bsuir.task.entity.Student;
import org.bsuir.task.exception.CustomException;
import org.bsuir.task.repository.GroupRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeanImpl implements Dean {


    @Override
    public synchronized void enrollStudent(Group group, Student student) {
        group.addStudent(student);
    }

    @Override
    public synchronized void deducateStudent(Student student) {
        List<Group> groups = GroupRepository.getInstance().getGroups();

        for (Group group : groups){
            group.removeStudent(student);
            break;
        }
    }

    @Override
    public synchronized Map<Student, Group> getStudentsFromCity(List<Group> groups, String city) {
        List<Group> groupList = GroupRepository.getInstance().getGroups();
        Map<Student, Group> result = new HashMap<>();

        for (Group group : groupList) {
            List<Student> students = group.getStudents();

            for (Student student : students) {
                if (student.getCity().equals(city)) {
                    result.put(student, group);
                }
            }
        }

        return result;
    }

    @Override
    public synchronized boolean moveStudentToOtherGroup(Student student, Group newGroup) {
        List<Group> groups = GroupRepository.getInstance().getGroups();

        if (student.isStudying()) {
            if (removeStudentFromGroup(groups, student)) {
                addStudentToNewGroup(groups, student, newGroup);
            } else {
                throw new CustomException("Student not found!");
            }
        }
        return true;
    }

    private void addStudentToNewGroup(List<Group> groups, Student student, Group newGroup) {
        for (Group group : groups) {
            if (group.equals(newGroup)) {
                group.addStudent(student);
                return;
            }
        }
        throw new CustomException("No such group!");
    }

    private boolean removeStudentFromGroup(List<Group> groupImpls, Student student) {
        for (Group group : groupImpls) {
            List<Student> students = group.getStudents();

            for (Student currentStudent : students) {
                if (currentStudent.equals(student)) {
                    students.remove(currentStudent);
                    return true;
                }
            }
        }
        return false;
    }
}
