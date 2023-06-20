package student.managment.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import student.managment.entity.Student;

import student.managment.service.StudentService;

@Controller

public class StudentsController {

	private StudentService studentService;

	public StudentsController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	 
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students",studentService.getAllStudents());
		return "students";
	}
	@GetMapping("/students/new")
	public String createStudentform(Model model) {
		
		Student student=new Student();
		model.addAttribute("student",student);
		return "createStudent" ;
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student")Student student ) {
		
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id , Model model) {
		model.addAttribute("student",studentService.getStudentById(id));
		return "editStudent";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute ("student") Student student, Model model) {
		Student existingStudent =studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFullName(student.getFullName());
		existingStudent.setAddress(student.getAddress());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setDepartment(student.getDepartment());
		existingStudent.setPhoneNo(student.getPhoneNo());

		studentService.updateStudent(existingStudent);
		
		return "redirect:/students";
	}
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
		
	}
	
	
	
	
	
}


