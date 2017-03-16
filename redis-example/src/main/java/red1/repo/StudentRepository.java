package red1.repo;

import java.util.Map;

import red1.model.Student;

public interface StudentRepository {
	
	public void saveStudent(Student student);
 
    public void updateStudent(Student student);
 
    public Student findStudent(String id);
 
    public Map<String, Student> findAllStudents();
 
    public void deleteStudent(String id);

}
