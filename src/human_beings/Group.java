package human_beings;

import comparator.StudentLastnameComparator;
import exceptions.GroupOverflowException;
import exceptions.NoSuchStudentException;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Create a Group class which contains an array of 10 objects of class Student. Implement add method, delete a student method and a find a student by last name method.
 * if trying to add 11th student, create own exception and handle it.
 * Define the toString() method for the group so that it lists the students in alphabetical order.
 */
public class Group {
    private Student[] students = new Student[10];
    private String groupNumber;

    public Group() {
    }

    public Group(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public void addStudent(Student student) throws GroupOverflowException {
        for (int i = 0; i < this.students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                student.setGroupNumber(this.groupNumber);
                System.out.println(student.getLastname() + " has been added");
                break;
            }
            if (students[this.students.length - 1] != null) {
                throw new GroupOverflowException();
            }
        }
    }

    public void deleteStudent(Long id) throws NoSuchStudentException {
        int count = 0;
        for (int i = 0; i < this.students.length; i++) {
            if (students[i] == null) {
                continue;
            }
            if (students[i].getId().equals(id)) {
                System.out.println(students[i].getLastname() + " has been deleted");
                students[i] = null;
                count++;
            }
        }
        if (count == 0) throw new NoSuchStudentException();
    }

    public Student searchStudent(String lastName) throws NoSuchStudentException {
        for (Student student : this.students) {
            if (student.getLastname().equals(lastName)) {
                return student;
            } else throw new NoSuchStudentException();
        }
        return null;
    }

    public static void sortStudentsByLastName(Student[] students) {
        Arrays.sort(students, Comparator.nullsLast(new StudentLastnameComparator()));
    }

    @Override
    public String toString() {
        Group.sortStudentsByLastName(this.students);
        return "Group[" +
                "group=" + Arrays.toString(students) +
                ", groupNumber='" + groupNumber + '\'' +
                ']';
    }
}