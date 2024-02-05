package com.Project.SpringAngular.controller;


import com.Project.SpringAngular.Service.StudentService;
import com.Project.SpringAngular.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private StudentService studentService;

    public static String uploadDirectory= System.getProperty("user.dir")+"/src/main/webapp/images";
    @GetMapping ("/")
    public String home () {
        return "Welcome to image uploading app in spring boot!!";
    }
    @PostMapping("/saveData")
    public Student saveStudent(@ModelAttribute Student student, @RequestParam("image") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, originalFilename);
        Files.write(fileNameAndPath, file.getBytes());
        student.setProfileImage(originalFilename);
        Student savedStudentData = studentService.saveStudentData(student);
        return savedStudentData;
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        return allStudents;
    }
    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        if (studentService.studentExists(id)) {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID " + id + " not found");
        }
    }

//    @PutMapping("/updateStudent/{id}")
//    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
//        if (studentService.studentExists(id)) {
//            // Retrieve the existing student
//            Student existingStudent = studentService.getStudentById(id);
//
//            // Update the existing student with the new details
//            existingStudent.setName(updatedStudent.getName());
//            existingStudent.setAddress(updatedStudent.getAddress());
//            existingStudent.setProfileImage(updatedStudent.getProfileImage());
//
//            // Save the updated student using the existing save method
//            Student updatedStudentData = studentService.saveStudentData(existingStudent);
//
//            return ResponseEntity.ok(updatedStudentData);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }

//    @PutMapping("/updateStudent/{id}")
//    public ResponseEntity<Student> updateStudent(
//            @PathVariable int id,
//            @RequestParam(value = "name") String name,
//            @RequestParam(value = "address") String address,
//            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage
//    ) {
//        if (studentService.studentExists(id)) {
//            // Retrieve the existing student
//            Student existingStudent = studentService.getStudentById(id);
//
//            // Update the existing student with the new details
//            existingStudent.setName(name);
//            existingStudent.setAddress(address);
//
//            // Update the profile image if provided
//            if (profileImage != null && !profileImage.isEmpty()) {
//                try {
//                    String originalFilename = profileImage.getOriginalFilename();
//                    Path fileNameAndPath = Paths.get(HomeController.uploadDirectory, originalFilename);
//                    Files.write(fileNameAndPath, profileImage.getBytes());
//                    existingStudent.setProfileImage(originalFilename);
//                } catch (IOException e) {
//                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//                }
//            }
//
//            // Save the updated student using the existing save method
//            Student updatedStudentData = studentService.saveStudentData(existingStudent);
//
//            return ResponseEntity.ok(updatedStudentData);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable int id,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "address") String address,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage,
            @RequestParam(value = "age") int age,
            @RequestParam(value = "description") String description
    ) {
        if (studentService.studentExists(id)) {
            Student existingStudent = studentService.getStudentById(id);
            existingStudent.setFirstName(firstName);
            existingStudent.setLastName(lastName);
            existingStudent.setAddress(address);
            existingStudent.setAge(age);
            existingStudent.setDescription(description);

            if (profileImage != null && !profileImage.isEmpty()) {
                try {
                    String originalFilename = profileImage.getOriginalFilename();
                    Path fileNameAndPath = Paths.get(HomeController.uploadDirectory, originalFilename);
                    Files.write(fileNameAndPath, profileImage.getBytes());
                    existingStudent.setProfileImage(originalFilename);
                } catch (IOException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                }
            }

            Student updatedStudentData = studentService.saveStudentData(existingStudent);

            return ResponseEntity.ok(updatedStudentData);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}