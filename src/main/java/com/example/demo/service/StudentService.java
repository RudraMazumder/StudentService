package com.example.demo.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.entities.CourseEntity;
import com.example.demo.repository.entities.StudentEntity;
import com.example.demo.utility.StudentUtility;
import com.example.models.Course;
import com.example.models.Student;

@Service
public class StudentService {

	private Logger logger = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	private StudentRepository studentDAO;

	@Autowired
	private CourseRepository courseDAO;
	
	@Autowired
	private AddressRepository addressDAO;

	public List<Student> getAllStudent() {
		List<StudentEntity> allStudent = studentDAO.findAll();
		return allStudent.stream().map(StudentUtility::toStudentModel).collect(Collectors.toList());
	}
	
	public List<Student> getPaginatedStudent(Integer skip, Integer top){
		Pageable paging = PageRequest.of(skip, top);
		Page<StudentEntity> studentList = studentDAO.findAll(paging);
		return studentList.stream().map(StudentUtility::toStudentModel).collect(Collectors.toList());
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveStudent(Student student) {
		StudentEntity studentEntity = StudentUtility.toStudentEntity(student);
		addressDAO.save(studentEntity.getAddress());
		studentDAO.save(studentEntity);
	}

	public void deleteStudent(Integer id) {
		studentDAO.deleteById(id);
	}

	public Student getById(Integer id) {
		StudentEntity student = studentDAO.getOne(id);
		return StudentUtility.toStudentModel(student);
	}

	public List<Course> getByStudent(Integer studentId) {
		Set<CourseEntity> courses = studentDAO.getOne(studentId).getCourses();
		return courses.stream().map(StudentUtility::toCourseModel).collect(Collectors.toList());
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public void enrollStudent(Integer studentId, List<Course> courseList) {
		Set<CourseEntity> courseEntitySet = courseList.stream().map(StudentUtility::toCourseEntity)
				.collect(Collectors.toSet());
		courseDAO.saveAll(courseEntitySet);
		StudentEntity student = studentDAO.getOne(studentId);
		student.setCourses(courseEntitySet);
		studentDAO.save(student);

	}

}
