package red1.repo;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import red1.model.Student;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
	
	private static final String KEY = "Student";
    
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, Student> hashOps;
 
    @Autowired
    public StudentRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
 
    @PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }
     
    public void saveStudent(Student student) {
        hashOps.put(KEY, student.getId(), student);
    }
 
    public void updateStudent(Student student) {
        hashOps.put(KEY, student.getId(), student);
    }
 
    public Student findStudent(String id) {
        return (Student) hashOps.get(KEY, id);
    }
 
    public Map<String, Student> findAllStudents() {
        return hashOps.entries(KEY);
    }
 
    public void deleteStudent(String id) {
        hashOps.delete(KEY, id);
    }

}
