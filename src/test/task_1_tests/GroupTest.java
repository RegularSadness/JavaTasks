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
        group.addMark("Vasya Pupkin", "Mathematics", 5 );
        group.addMark("Vasya Pupkin", "Mathematics", 4 );
    }

    @Test
    public void addMark() {
        Group group = new Group("213/3");
        group.addStudent("Vasya Pupkin");
        group.addStudent("Ivan Ivanov");
        group.addSubject("Mathematics");
        group.addSubject("Language");
//        group.addMark("Vasya Pupkin", "Mathematics", 5);
        group.handleStudent("Vasya Pupkin", (student)-> student.addMark("Mathematics", 5));
        group.addMark("Vasya Pupkin", "Language", 4);
        group.addMark("Ivan Ivanov", "Mathematics", 3);
        group.addMark("Ivan Ivanov", "Language", 2);
        assertEquals(5, (int) group.getMark("Vasya Pupkin", "Mathematics"));
        assertEquals(4, (int) group.getMark("Vasya Pupkin", "Language"));
        assertEquals(3, (int) group.getMark("Ivan Ivanov", "Mathematics"));
        assertEquals(2, (int) group.getMark("Ivan Ivanov", "Language"));


    }

    @Test (expected = RuntimeException.class)
    public void delMark() {
        Group group = new Group("213/3");
        group.addStudent("Vasya Pupkin");
        group.addSubject("Language");
        group.addMark("Vasya Pupkin", "Language", 5 );
        group.handleStudent("Vasya Pupkin", (student)-> student.deleteMark("Language"));
        group.deleteMark("Vasya Pupkin", "Language" );


    }

    @Test
    public void changeMark() {
        Group group = new Group("213/3");
        group.addStudent("Vasya Pupkin");
        group.addSubject("Mathematics");
        group.addMark("Vasya Pupkin", "Mathematics", 5);
        group.changeMark("Vasya Pupkin", "Mathematics", 2);
        assertEquals(2, (int) group.getMark("Vasya Pupkin", "Mathematics"));
    }

    @Test
    public void getMark() {
        Group group = new Group("213/3");
        group.addStudent("Vasya Pupkin");
        group.addStudent("Ivan Ivanov");
        group.addSubject("Mathematics");
        group.addSubject("Language");
        group.addMark("Vasya Pupkin", "Mathematics", 5);
        group.addMark("Vasya Pupkin", "Language", 4);
        group.addMark("Ivan Ivanov", "Mathematics", 3);
        group.addMark("Ivan Ivanov", "Language", 2);
        int actualMark = group.getMark("Vasya Pupkin", "Mathematics");
        int actualMark1 = group.getMark("Vasya Pupkin", "Language");
        int actualMark2 = group.getMark("Ivan Ivanov", "Mathematics");
        int actualMark3 = group.getMark("Ivan Ivanov", "Language");
        assertEquals(5, actualMark);
        assertEquals(4, actualMark1);
        assertEquals(3, actualMark2);
        assertEquals(2, actualMark3);
    }
}