package com.example.application.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

@Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
}
    public List<Student> getStudents() {
       return studentRepository.findAll();

    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalArgumentException("Student already exists");
        }
        studentRepository.save(student);

    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalArgumentException("Student does not exist");
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        if(name.length()>0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if(email!=null && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> optionalStudent = studentRepository.findById(studentId);
            if(optionalStudent.isPresent()) {
                throw new IllegalStateException("Student already exists");
            }
            student.setEmail(email);
        }
    }
}
