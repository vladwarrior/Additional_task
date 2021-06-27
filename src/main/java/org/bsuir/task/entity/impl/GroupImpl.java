package org.bsuir.task.entity.impl;

import org.bsuir.task.entity.Group;
import org.bsuir.task.entity.Student;
import org.bsuir.task.validator.CustomValidator;
import org.bsuir.task.exception.CustomException;

import java.util.ArrayList;
import java.util.List;

public class GroupImpl implements Group {
    private final List<Student> students;
    private int courseNumber;
    private String name;

    {
        students = new ArrayList<>();
    }

    public GroupImpl(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return List.copyOf(students);
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void addStudent(Student student) {
        if (!CustomValidator.isStudentAlreadyInGroup(this, student.getFullName())) {
            student.setStudying(true);
            students.add(student);
        } else {
            throw new CustomException("Student with such name is already in group!");
        }
    }

    public void removeStudent(Student student) {
        if (!students.remove(student)) {
            throw new CustomException("No such student");
        }
        student.setStudying(false);
    }
}
