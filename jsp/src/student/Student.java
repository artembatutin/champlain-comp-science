package student;

public class Student {

	private int id;
	private String name;
	private int grade;

	public Student() {
		//Not setting constructor.
	}

	public Student(int id, String name, int grade) {
		this.setId(id);
		this.setName(name);
		this.setGrade(grade);
	}

	public static Student parse(String id, String name, String grade) {
		int inId = Integer.parseInt(id);
		int inGrade = Integer.parseInt(grade);
		return new Student(inId, name, inGrade);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return id + "-" + name + "-" + grade;
	}
}