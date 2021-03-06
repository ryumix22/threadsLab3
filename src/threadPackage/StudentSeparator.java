package threadPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class StudentSeparator implements Runnable {

    Map<String, LinkedBlockingDeque<Student>> blockingQueueMap = new HashMap<>();
    BlockingDeque<Student> theAllStudents;

    public StudentSeparator(String[] subjects, BlockingDeque<Student> theAllStudents){
        for (String subject : subjects) {
            blockingQueueMap.put(subject, new LinkedBlockingDeque<>());
            this.theAllStudents = theAllStudents;
        }
    }

    public BlockingDeque<Student> getStudentsBySubjectTitle(String subjectTitle){
        return this.blockingQueueMap.get(subjectTitle);
    }

    @Override
    public void run() {
        while (true){
            try {
                var student = this.theAllStudents.take();
                //System.out.println("test free separation");
                blockingQueueMap.get(student.subjectTitle).putLast(student);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
