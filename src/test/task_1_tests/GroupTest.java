import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void addStudent() {
        Group group = new Group("213/3");
        group.addStudent("Vasya Pupkin");
        group.addStudent("Ivan Ivanov");
        group.addStudent("Petr Petrov");
        List<Student> expected = Arrays.asList(
                new Student("Vasya Pupkin"),
                new Student("Ivan Ivanov"),
                new Student( "Petr Petrov")
        );
        Assert.assertEquals(expected, group.getStudents());
    }

    @Test
    public void delStudent() {
        Group group = new Group("213/3");
        group.addStudent("Vasya Pupkin");
        group.addStudent("Ivan Ivanov");
        group.addStudent("Petr Petrov");
        group.deleteStudent("Vasya Pupkin");
        group.deleteStudent("Petr Petrov");
        List<Student> expected = Collections.singletonList(
                new Student("Ivan Ivanov")
        );
        Assert.assertEquals(expected, group.getStudents());
    }

    @Test
    public void addSubject() {
        Group group = new Group("213/3");
        group.addSubject("Mathematics");
        group.addSubject("Language");
        group.addSubject("History");
        group.addSubject("Biology");
        List<String> expected = Arrays.asList("Mathematics", "Language", "History", "Biology");
        Assert.assertEquals(expected, group.getSubjects());
    }

    @Test
    public void delSubject() {
        Group group = new Group("213/3");
        group.addSubject("Mathematics");
        group.addSubject("Language");
        group.addSubject("History");
        group.deleteSubject("Language");
        group.deleteSubject("History");
        List<String> expected = Collections.singletonList("Mathematics");
        Assert.assertEquals(expected, group.getSubjects());
    }

    @Test(expected = RuntimeException.class)
    public void addMarkFail() {
        Group group = new Group("213/3");
        group.addStudent("Vasya Pupkin");
        group.addSubject("Mathematics");
        group.handleStudent("Vasya Pupkin", (student)-> student.addMark("Mathematics", 5));
        group.handleStudent("Vasya Pupkin", (student)-> student.addMark("Mathematics", 4));
    }

    @Test
    public void addMark() {
        Group group = new Group("213/3");
        group.addStudent("Vasya Pupkin");
        group.addSubject("Mathematics");
        group.handleStudent("Vasya Pupkin", (student)-> student.addMark("Mathematics", 5));
        assertEquals(5, (int) group.getMark("Vasya Pupkin", "Mathematics"));


    }

    @Test
    public void delMark() {
        Group group = new Group("213/3");
        group.addStudent("Vasya Pupkin");
        group.addSubject("Language");
        group.handleStudent("Vasya Pupkin", (student)-> student.addMark("Language", 5));
        group.handleStudent("Vasya Pupkin", (student)-> student.deleteMark("Language"));
    }

    @Test
    public void changeMark() {
        Group group = new Group("213/3");
        group.addStudent("Vasya Pupkin");
        group.addSubject("Mathematics");
        group.handleStudent("Vasya Pupkin", (student)-> student.addMark("Mathematics", 5));
        group.handleStudent("Vasya Pupkin", (student)-> student.changeMark("Mathematics", 2));
        assertEquals(2, (int) group.getMark("Vasya Pupkin", "Mathematics"));
    }

    @Test
    public void getMark() {
        Group group = new Group("213/3");
        group.addStudent("Vasya Pupkin");
        group.addSubject("Mathematics");
        group.handleStudent("Vasya Pupkin", (student)-> student.addMark("Mathematics", 5));
        int actualMark = group.getMark("Vasya Pupkin", "Mathematics");
        assertEquals(5, actualMark);
    }
}