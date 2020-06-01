package threadPackage;
import java.util.concurrent.BlockingQueue;

public class StudentsGenerator implements Runnable {

    private final BlockingQueue<Student> students;
    public StudentsGenerator(BlockingQueue<Student> students){
        this.students = students;
    }

    @Override
    public void run() {
        for (int i = 0; i < MainClass.numberOfStudents; i++) {
            var student = Student.nextStudent();
            this.students.add(student);
        }
    }
}
