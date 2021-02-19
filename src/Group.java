import java.util.Arrays;
import java.util.Objects;

public class Group {
    private String number;
    private String[] students;
    private Integer[][] marks;
    private String[] subjects;

    public Group(String number, int studentsSize, int subjectsSize) {
        this.number = number;
        this.students = new String[studentsSize];
        this.subjects = new String[subjectsSize];
        this.marks = new Integer[subjectsSize][studentsSize];
    }

    public String[] getStudents() {
        return students;
    }

    public Integer[][] getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "Group{" +
                "number='" + number + '\'' +
                ", students=" + Arrays.toString(students) +
                ", marks=" + Arrays.deepToString(marks) +
                ", subjects=" + Arrays.toString(subjects) +
                '}';
    }

    public void addStudent(String name) {
        boolean successAdded = false;
        for (int j = 0; j < students.length; j++) {
            if ((students[j] != null) && (students[j].equals(name))) {
                throw new RuntimeException("This student already exists.");
            }
        }
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = name;
                successAdded = true;
                break;
            }
        }
        if (!successAdded) {
            throw new RuntimeException("The number of students submitted, more than the number of seats in the group.");
        }
    }

    public void delStudent(String name) {
        for (int i = 0; i < students.length; i++) {
            if ((students[i] != null) && (students[i].equals(name))) {
                students[i] = null; //TODO Добавить exception, если именя не совпадают.
                for (int j = 0; j < subjects.length; j++) {
                    marks[j][i] = null;
                }
            }
        }
    }

    public void addSubject(String title) {
        boolean successAdded = false;
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i] == null) {
                subjects[i] = title;
                successAdded = true;
                break;
            }
        }
        if (!successAdded) {
            throw new RuntimeException("The number of subjects entered is greater than the limit.");
        }
    }

    public void delSubject(String title) {
        boolean successDeleted = false;
        for (int i = 0; i < subjects.length; i++) {
            if ((subjects[i] != null) && (subjects[i].equals(title))) {
                subjects[i] = null;
                successDeleted = true;
            }
        }
        if (!successDeleted) {
            throw new RuntimeException("Subject doesn't exists.");
        }
    }

    private int findStudentIndex(String student) {
        int studentIndex = -1;
        for (int i = 0; i < students.length; i++) {
            if (student.equals(students[i])) {
                studentIndex = i;
                break;
            }
        }
        if (studentIndex == -1) {
            throw new RuntimeException("The desired student was not found.");
        }
        return studentIndex;
    }

    private int findSubjectIndex(String subject) {
        int subjectIndex = -1;
        for (int j = 0; j < subjects.length; j++) {
            if (subject.equals(subjects[j])) {
                subjectIndex = j;
                break;
            }
        }
        if (subjectIndex == -1) {
            throw new RuntimeException("The desired subject was not found.");
        }
        return subjectIndex;
    }

    public void addMark(int mark, String student, String subject) {
        int studentIndex = findStudentIndex(student);
        int subjectIndex = findSubjectIndex(subject);
        if(marks[subjectIndex][studentIndex] != null) {
            throw new RuntimeException("There is already estimated.");
        }
        marks[subjectIndex][studentIndex] = mark;
    }

    public void delMark(String student, String subject) {
        int studentIndex = findStudentIndex(student);
        int subjectIndex = findSubjectIndex(subject);
        if (marks[subjectIndex][studentIndex] == null) {
            throw new RuntimeException("There is no estimate.");
        }
        marks[subjectIndex][studentIndex] = null;
    }

    public void changeMark(int mark, String student, String subject) {
        int studentIndex = findStudentIndex(student);
        int subjectIndex = findSubjectIndex(subject);
        marks[subjectIndex][studentIndex] = mark;
    }

    public int getMark(String student, String subject) {
        int studentIndex = findStudentIndex(student);
        int subjectIndex = findSubjectIndex(subject);
        return marks[subjectIndex][studentIndex];
    }
}