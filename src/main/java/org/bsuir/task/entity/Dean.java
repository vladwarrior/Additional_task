package org.bsuir.task.entity;

import java.util.List;
import java.util.Map;

public interface Dean {
    void enrollStudent(Group groupImpl, Student student);
    void deducateStudent(Student student);
    Map<Student, Group> getStudentsFromCity(List<Group> groups, String city);
    boolean moveStudentToOtherGroup(Student student, Group newGroup);
}
