package com.example.springboot_book.controller.student;

import com.example.springboot_book.model.student.Student;
import com.example.springboot_book.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping
    public ModelAndView showAll() {
        ModelAndView model = new ModelAndView("student/list");
        Iterable<Student> students = studentService.findAll();
        model.addObject("studentList", students);
        if (students != null) {
            model.addObject("message", "No student on the list");
        } else {
            model.addObject("message", "");
        }
        return model;
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<Student>> getAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> createNew(@RequestBody Student student) {
        studentService.save(student);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }



}
