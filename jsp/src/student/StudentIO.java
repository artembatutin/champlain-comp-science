package student;

import javax.servlet.jsp.JspWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class StudentIO {

	private final static String PATH = "./students.txt";

	public static void addStudent(Student student) throws IOException {
		ArrayList<Student> cache = decode();
		cache.add(student);
		encode(cache);
	}

	public static void removeStudent(int id, JspWriter jsp) throws IOException {
		ArrayList<Student> cache = decode();
		Iterator<Student> it = cache.iterator();
		while(it.hasNext()) {
			Student s = it.next();
			if(s.getId() == id) {
				jsp.print("Removed " + s + "!");
				it.remove();
			}
		}
		encode(cache);
	}

	public static ArrayList<Student> decode() throws IOException {
		ArrayList<Student> students = new ArrayList<>();
		BufferedReader in = new BufferedReader(new FileReader(PATH));
		String line;
		while((line = in.readLine()) != null) {
			String[] parts = line.split("-");
			students.add(Student.parse(parts[0], parts[1], parts[2]));
		}
		return students;
	}

	public static void encode(ArrayList<Student> list) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(PATH));
		for(Student s : list) {
			out.write(s.toString());
			out.newLine();
		}
		out.close();
	}
}
