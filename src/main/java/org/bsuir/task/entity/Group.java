package org.bsuir.task.entity;

import java.util.List;

public interface Group {
    void addStudent(Student student);
    void removeStudent(Student student);
    List<Student> getStudents();
    int getCourseNumber();
}
