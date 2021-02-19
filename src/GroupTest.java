import org.junit.Test;

import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void addStudent() {
        Group group = new Group("213/3", 3, 3);
        group.addStudent("Vasya Pupkin");
        group.addStudent("Ivan Ivanov");
        group.addStudent("Petr Petrov");
        String[] expected = {"Vasya Pupkin", "Ivan Ivanov", "Petr Petrov"};
        assertArrayEquals(expected, group.getStudents());
    }

    @Test
    public void delStudent() {
        Group group = new Group("213/3", 3, 3);
        group.addStudent("Vasya Pupkin");
        group.addStudent("Ivan Ivanov");
        group.addStudent("Petr Petrov");
        group.delStudent("Vasya Pupkin");
        group.delStudent("Petr Petrov");
        String[] expected = {null, "Ivan Ivanov", null};
        assertArrayEquals(expected, group.getStudents());
    }

    @Test(expected = RuntimeException.class)
    public void addSubject() {
        Group group = new Group("213/3", 3, 3);
        group.addSubject("Mathematics");
        group.addSubject("Language");
        group.addSubject("History");
        group.addSubject("Biology");
    }

    @Test(expected = RuntimeException.class)
    public void delSubject() {
        Group group = new Group("213/3", 3, 3);
        group.delSubject("Mathematics");
        group.delSubject("Language");
        group.delSubject("History");
    }

    @Test(expected = RuntimeException.class)
    public void addMarkFail() {
        Group group = new Group("213/3", 3, 2);
        group.addStudent("Vasya Pupkin");
        group.addStudent("Ivan Ivanov");
        group.addStudent("Petr Petrov");
        group.addSubject("Mathematics");
        group.addSubject("Language");
        group.addMark(5, "Vasya Pupkin", "Mathematics" );
        group.addMark(2, "Vasya Pupkin", "Mathematics" );
        group.addMark(4, "Vasya Pupkin", "Language" );
        group.addMark(5, "Ivan Ivanov", "Mathematics" );
        group.addMark(4, "Ivan Ivanov", "Language" );
        group.addMark(5, "Petr Petrov", "Mathematics" );
        group.addMark(4, "Petr Petrov", "Language" );
    }

    @Test
    public void addMark() {
        Group group = new Group("213/3", 3, 2);
        group.addStudent("Vasya Pupkin");
        group.addStudent("Ivan Ivanov");
        group.addStudent("Petr Petrov");
        group.addSubject("Mathematics");
        group.addSubject("Language");
        group.addMark(5, "Vasya Pupkin", "Mathematics" );
        group.addMark(4, "Vasya Pupkin", "Language" );
        group.addMark(5, "Ivan Ivanov", "Mathematics" );
        group.addMark(4, "Ivan Ivanov", "Language" );
        group.addMark(5, "Petr Petrov", "Mathematics" );
        group.addMark(4, "Petr Petrov", "Language" );
        int[][] expected = {{5, 5, 5}, {4, 4, 4}};
        assertArrayEquals(expected, group.getMarks());
    }

    @Test
    public void delMark() {
        Group group = new Group("213/3", 3, 2);
        group.addStudent("Vasya Pupkin");
        group.addStudent("Ivan Ivanov");
        group.addStudent("Petr Petrov");
        group.addSubject("Mathematics");
        group.addSubject("Language");
        group.addMark(5, "Vasya Pupkin", "Mathematics" );
        group.addMark(4, "Vasya Pupkin", "Language" );
        group.addMark(5, "Ivan Ivanov", "Mathematics" );
        group.addMark(4, "Ivan Ivanov", "Language" );
        group.addMark(5, "Petr Petrov", "Mathematics" );
        group.addMark(4, "Petr Petrov", "Language" );
        group.delMark("Vasya Pupkin", "Mathematics" );
        group.delMark("Vasya Pupkin", "Language" );
        group.delMark("Ivan Ivanov", "Mathematics" );
        group.delMark("Ivan Ivanov", "Language" );
        group.delMark("Petr Petrov", "Mathematics" );
        group.delMark("Petr Petrov", "Language" );
        Integer[][] expected = {{null, null, null}, {null, null, null}};
        assertArrayEquals(expected, group.getMarks());
    }

    @Test(expected = RuntimeException.class)
    public void delMarkFail() {
        Group group = new Group("213/3", 3, 2);
        group.addStudent("Vasya Pupkin");
        group.addStudent("Ivan Ivanov");
        group.addStudent("Petr Petrov");
        group.addSubject("Mathematics");
        group.addSubject("Language");
        group.addMark(5, "Vasya Pupkin", "Mathematics");
        group.addMark(4, "Vasya Pupkin", "Language");
        group.addMark(5, "Ivan Ivanov", "Mathematics");
        group.addMark(4, "Ivan Ivanov", "Language");
        group.addMark(5, "Petr Petrov", "Mathematics");
        group.addMark(4, "Petr Petrov", "Language");
        group.delMark("Vasya Pupkin", "Mathematics");
        group.delMark("Vasya Pupkin", "Language");
        group.delMark("Ivan Ivanov", "Mathematics");
        group.delMark("Ivan Ivanov", "Language");
        group.delMark("Petr Petrov", "Mathematics");
        group.delMark("Petr Petrov", "Language");
        group.delMark("Petr Petrov", "Language");
    }

    @Test
    public void changeMark() {
        Group group = new Group("213/3", 3, 2);
        group.addStudent("Vasya Pupkin");
        group.addStudent("Ivan Ivanov");
        group.addStudent("Petr Petrov");
        group.addSubject("Mathematics");
        group.addSubject("Language");
        group.addMark(5, "Vasya Pupkin", "Mathematics");
        group.addMark(4, "Vasya Pupkin", "Language");
        group.addMark(5, "Ivan Ivanov", "Mathematics");
        group.addMark(4, "Ivan Ivanov", "Language");
        group.addMark(5, "Petr Petrov", "Mathematics");
        group.addMark(4, "Petr Petrov", "Language");
        group.changeMark(2, "Vasya Pupkin", "Mathematics");
        int[][] expected = {{2, 5, 5}, {4, 4, 4}};
        assertArrayEquals(expected, group.getMarks());
    }

    @Test
    public void getMark() {
        Group group = new Group("213/3", 3, 2);
        group.addStudent("Vasya Pupkin");
        group.addStudent("Ivan Ivanov");
        group.addStudent("Petr Petrov");
        group.addSubject("Mathematics");
        group.addSubject("Language");
        group.addMark(5, "Vasya Pupkin", "Mathematics");
        group.addMark(4, "Vasya Pupkin", "Language");
        group.addMark(5, "Ivan Ivanov", "Mathematics");
        group.addMark(4, "Ivan Ivanov", "Language");
        group.addMark(5, "Petr Petrov", "Mathematics");
        group.addMark(4, "Petr Petrov", "Language");
        int actualMark = group.getMark("Vasya Pupkin", "Mathematics");
        assertTrue(actualMark == 5);
    }
}