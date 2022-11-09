package csv;

import exceptions.GroupOverflowException;
import human_beings.Group;
import human_beings.Student;

import java.io.*;

/**
 * Refine a class describing a group of students, by adding the ability to save the group to a file;
 * Implement the reverse process. Those. read data about group from a file, and on their basis create an object of type group.
 */
public class GroupCSVFileStorage implements CSVConverter.GroupCSV {

    @Override
    public void saveGroupToSCV(Group group) throws IOException {
        Student[] students = group.getStudents();
        StringBuilder sb = new StringBuilder();
        for (Student st :
                students) {
            if (st != null) {
                sb.append(st.toCSVString()).append("\n");
            }
        }
        File file = new File(group.getGroupNumber() + ".csv");
        Writer pw = new PrintWriter(file);
        pw.write(sb.toString());
        pw.close();
    }

    @Override
    public Group loadFGroupFromCSV(File fileSCV) throws IOException, GroupOverflowException {
        Group group = new Group();

        if (fileSCV.exists()) {
            Reader rd = new FileReader(fileSCV);
            char[] chars = new char[1000];

            rd.read(chars);
            rd.close();

            String[] csv = String.valueOf(chars).split("\n");

            for (int i = 0; i < csv.length - 1; i++) {
                if (!csv[i].equals("")) {
                    Student student = new Student().fromCSVString(csv[i]);
                    group.addStudent(student);
                    group.setGroupNumber(student.getGroupNumber());
                }
            }
        }
        return group;
    }


    @Override
    public File findFileByGroupName(String groupName, File workFolder) {
        return null;
    }
}