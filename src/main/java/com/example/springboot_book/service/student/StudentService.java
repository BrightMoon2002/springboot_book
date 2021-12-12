package com.example.springboot_book.service.student;

import com.example.springboot_book.model.student.Student;
import com.example.springboot_book.repo.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class StudentService implements IStudentService{
    @Autowired
    private IStudentRepository studentRepository;
    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) throws IllegalArgumentException {
        return studentRepository.findById(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void remove(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Page<Student> findAllByNameContaining(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student saveT(Student student) {
        return studentRepository.save(student);
    }
}
