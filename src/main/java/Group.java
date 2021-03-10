import java.util.ArrayList;
import java.util.List;

public class Group {
    private final String number;
    private final List<Student> students = new ArrayList<>();
    private final List<String> subjects = new ArrayList<>();


    public Group(String number) {
        this.number = number;
    }

    public void addStudent(String name) {
        students.add(new Student(name));
    }

    public void deleteStudent(String name) {
        students.remove(new Student(name));
    }

    public void addSubject(String subject) {
        subjects.add(subject);
    }

    public void deleteSubject(String subject) {
        subjects.remove(subject);
        for (Student student : students) {
            student.deleteMark(subject);
        }
    }

    private Student findStudent(String name) {
        for (Student student : students) {
            if (name.equals(student.getName())) {
                return student;
            }
        }
        return null;
    }

    public void addMark(String name, String subject, Integer mark) {
        if (!subjects.contains(subject)) {
            throw new RuntimeException("Subject doesn't exist");
        }
        Student student = findStudent(name);
        if (student == null) {
            throw new RuntimeException("Student doesn't exist");
        }
        student.addMark(subject, mark);
    }


    public void deleteMark(String name, String subject) {
        if (!subjects.contains(subject)) {
            throw new RuntimeException("Subject doesn't exist");
        }
        Student student = findStudent(name);
        if (student == null) {
            throw new RuntimeException("Student doesn't exist");
        }
        student.deleteMark(subject);
    }

    public void changeMark(String name, String subject, Integer mark) {
        if (!subjects.contains(subject)) {
            throw new RuntimeException("Subject doesn't exist");
        }
        Student student = findStudent(name);
        if (student == null) {
            throw new RuntimeException("Student doesn't exist");
        }
        student.changeMark(subject, mark);
    }

    public Integer getMark(String name, String subject) {
        Student student = findStudent(name);
        if (student == null) {
            throw new RuntimeException("Student doesn't exist");
        }
        return student.getMark(subject);
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<String> getSubjects() {
        return subjects;
    }

}