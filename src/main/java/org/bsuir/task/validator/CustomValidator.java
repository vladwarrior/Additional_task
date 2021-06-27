package org.bsuir.task.validator;

import org.bsuir.task.entity.Group;
import org.bsuir.task.entity.Student;

import java.util.List;

public class CustomValidator {

    private CustomValidator(){

    }

    public static boolean isStudentAlreadyInGroup(Group group, String fullName){
        List<Student> students = group.getStudents();

        for(Student student:students){
            if(student.getFullName().equals(fullName)){
                return true;
            }
        }
        return false;
    }
}
