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

	private static final Integer batchSize = 100;
	private static final Integer printSize = 10;

	@Override
	public void run(String... arg0) throws Exception {

		logger.info("Initializing Student Controller");
		studentRepository.deleteAll();

		// Load and count entries
		logger.info("================ Example 1 ===============" );
		example1();
		
		// Count, delete and count entries
		logger.info("================ Example 2 ===============" );
		example2();
		
		
		// Load and print all entries
		logger.info("================ Example 3 ===============" );
		example3();
		
		// Close off
		logger.info("Closing Student Controller");
	}

	// load and count
	private void example1() {
		loadEntries();
		logger.info("Final Student Count:" + studentRepository.count());
	}

	// Delete and count
	private void example2() {
		logger.info("Student Count:" + studentRepository.count());
		studentRepository.deleteAll();
		logger.info("Final Student Count:" + studentRepository.count());
	}
	
	// Load and print all entries
	private void example3() {
		
		loadEntries();
		
		logger.info("Student Count:" + studentRepository.count());
		
		printLogMessages(studentRepository.findAllStudents(), printSize );
		// printLogMessages(studentRepository.findStudent("S" + (batchSize-10)));
		
		studentRepository.deleteAll();
		logger.info("Final Student Count:" + studentRepository.count());
	}

	private void loadEntries() {
		for (int i = 0; i < batchSize; i++) {
			Student student = new Student("S" + i, "John Doe " + i, Student.Gender.MALE, 1);
			studentRepository.saveStudent(student);

			if (i % 50000 == 0) {
				logger.info("Pushed " + i + " records.");
			}
		}
	}

	private void printLogMessages(Map<String, Student> map, Integer limit) {
		logger.info("==============================");

		Integer c = 0;
		for(String k: map.keySet()){
			logger.info("@key=" + k + " @value=" + map.get(k));
			
			if(c++ >= limit)
				break;
		}
		
		logger.info("==============================");
	}

	private void printLogMessages(Student S) {
		logger.info("==============================");

		logger.info("@key=" + S.getId() + " @value=" + S);

		logger.info("==============================");
	}

}
