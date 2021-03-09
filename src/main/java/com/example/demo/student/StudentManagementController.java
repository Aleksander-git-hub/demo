package com.example.demo.student;

import com.example.demo.entity.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "management/api/v1")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1L, "James Bond"),
            new Student(2L, "Tony Stark"),
            new Student(3L, "Peter Parker")
    );

    @GetMapping(value = "/students")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudents() {
        System.out.println("getAllStudents");
        return STUDENTS;
    }

    @PostMapping(value = "/student")
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println("registerNewStudent");
        System.out.println(student);
    }

    @DeleteMapping(value = "/student/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable Long studentId) {
        System.out.println("deleteStudent");
        System.out.println(studentId);
    }

    @PutMapping(value = "/student/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable Long studentId,
                              @RequestBody Student student) {
        System.out.println("updateStudent");
        System.out.printf("%s %s%n", studentId, student);
    }
}