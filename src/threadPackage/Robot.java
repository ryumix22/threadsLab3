package threadPackage;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Robot implements Runnable {

    static String createHeadingString(String subjectTitle, int a, int b){
        return String.format("%s[(%d/%d]", subjectTitle, a, b);
    }


    private final BlockingDeque<Student> queue;
    private final String subjectTitle;


    public Robot(String subjectTitle) {
        this.subjectTitle = subjectTitle;
        this.queue  = new LinkedBlockingDeque<>(MainClass.classroomMaxSize);
    }


    public Producer createProducer(BlockingDeque<Student> students) {
        return new Producer(students, this);
    }


    public String getSubjectTitle() {
        return subjectTitle;
    }


    public BlockingDeque<Student> getQueue() {
        return queue;
    }


    @Override
    public void run() {
        System.out.println(this.subjectTitle + ": consumer started...");
        Student student;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                student = queue.takeFirst();
                queue.putFirst(student);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("Test free checking tasks");
            if ((student = queue.peek()) != null)
                try {
                    while (student.tasksCount > 0) {
                        student.tasksCount -= MainClass.tasksPerTime;
                        Thread.sleep(MainClass.timePerChecking);
                    }
                    String head = Robot.createHeadingString(subjectTitle, this.queue.size()-1,
                            MainClass.classroomMaxSize);
                    System.out.println(String.format("- [%s] robot removed: %s", head, student));
                    queue.poll();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        }
    }

}
