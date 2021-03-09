package com.example.demo.student;

import com.example.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1L, "James Bond"),
            new Student(2L, "Tony Stark"),
            new Student(3L, "Peter Parker")
    );

    @GetMapping(value = "/student/{studentId}")
    public Student getStudent(@PathVariable Long studentId) {
        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + studentId + " does not exist!"));
    }
}
