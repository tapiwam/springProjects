package red1.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import red1.model.Student;
import red1.repo.StudentRepository;

@Controller
public class StudentController implements CommandLineRunner {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public StudentRepository studentRepository;

	@Override
	public void run(String... arg0) throws Exception {
		
		logger.info("Initializing Student Controller");
		
		Student student = new Student("Eng2015001", "John Doe", Student.Gender.MALE, 1);
		studentRepository.saveStudent(student);
				
				
		logger.info("Final Student List:");
		printLogMessages(studentRepository.findAllStudents());
		
	}
	
	private void printLogMessages(Map<String, Student> map){
		logger.info("==============================");

		map.forEach((k,v) -> {
			logger.info("@key=" + k + " @value=" + v);
		});
		
		logger.info("==============================");
	}

}
