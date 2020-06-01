package threadPackage;

import java.util.concurrent.BlockingDeque;


public class Producer implements Runnable {

    private final BlockingDeque<Student> queueBeforeDoor;
    private final BlockingDeque<Student> robotQueue;

    private final String subjectTitle;
    private final Robot robot;


    public Producer(BlockingDeque<Student> allStudentsQueue, Robot robot) {
        this.queueBeforeDoor = allStudentsQueue;
        this.robotQueue = robot.getQueue();
        this.subjectTitle = robot.getSubjectTitle();
        this.robot = robot;
    }

    public void run() {
        System.out.println(this.subjectTitle + ": producer started...");
        while (true) {
            try {
                Student student = this.queueBeforeDoor.takeFirst();
                //System.out.println("Test free adding in classroom");
                System.out.println(String.format("!! (Preparing) %s: %s", this.subjectTitle, student.studentName));
                robotQueue.put(student);
                String head = Robot.createHeadingString(this.subjectTitle, this.robot.getQueue().size(),
                        MainClass.classroomMaxSize);
                System.out.println(String.format("+ [%s] Student added : %s", head, student));
                Thread.sleep(MainClass.inviteDelay);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
