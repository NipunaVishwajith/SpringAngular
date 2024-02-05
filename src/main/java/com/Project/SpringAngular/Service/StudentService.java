package com.Project.SpringAngular.Service;

import com.Project.SpringAngular.entity.Student;
import com.Project.SpringAngular.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudentData(Student student) {
        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public boolean studentExists(int id) {
        return studentRepository.existsById(id);
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }


}
