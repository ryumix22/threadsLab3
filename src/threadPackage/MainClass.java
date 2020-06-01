package threadPackage;

import java.util.concurrent.LinkedBlockingDeque;

public class MainClass {

    public static final String[] subjects = {"Вышмат", "ООП", "Физика"};
    public static final int numberOfStudents = 100;
    public final static int classroomMaxSize = 10;
    public final static int tasksPerTime = 5;
    public final static int timePerChecking = 2000;
    public final static int inviteDelay = 100;

    MainClass() {
        var students = new LinkedBlockingDeque<Student>();
        new Thread(new StudentsGenerator(students)).start();

        var separator = new StudentSeparator(subjects, students);
        new Thread(separator).start();

        for (String subject : MainClass.subjects) {
            Robot robot = new Robot(subject);
            Producer producer = robot.createProducer(separator.getStudentsBySubjectTitle(subject));

            new Thread(producer).start();
            new Thread(robot).start();
        }
    }

    public static void main(String[] args) {
        new MainClass();
    }
}
