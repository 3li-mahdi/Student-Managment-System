package student.managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import student.managment.entity.Student;

public interface StudentRepository extends JpaRepository <Student,Long>{

}
