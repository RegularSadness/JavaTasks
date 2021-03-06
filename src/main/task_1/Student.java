import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Student {
    private final String name;
    private final Map<String, Integer> marks = new HashMap();

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addMark(String subject, int mark) {
        if (marks.containsKey(subject)) {
            throw new RuntimeException("Mark already exists");
        }
        marks.put(subject, mark);
    }

    public void deleteMark(String subject) {
        marks.remove(subject);
    }

    public void changeMark(String subject, int mark) {
        marks.put(subject, mark);
    }

    public int getMark(String subject) {
        return marks.get(subject);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
