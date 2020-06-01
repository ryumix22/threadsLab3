package threadPackage;
import java.util.Random;

public class Student {

    static String[] names = {"Дима", "Вова", "Петя", "Саша", "Катя"};
    static int[] tasks = {10, 20, 100};
    static int lastCreated = 0;

    static Student nextStudent(){
        Random random = new Random();
        return new Student(tasks[random.nextInt(tasks.length)],
                MainClass.subjects[random.nextInt(MainClass.subjects.length)],
                names[random.nextInt(names.length)] + lastCreated++);
    }

    public int tasksCount;
    public String subjectTitle;
    public String studentName;

    public Student(int tasksCount, String subjectTitle, String studentName) {
        this.tasksCount = tasksCount;
        this.subjectTitle = subjectTitle;
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return String.format("%s(%s[%d])", this.studentName, this.subjectTitle, this.tasksCount);
    }
}
