package week4_chap4;

public class Score {
	
	private final int grade;
	private final ScoreLetter letter;
	
	Score(int grade) {
		this.grade = grade;
		this.letter = ScoreLetter.getLetter(grade);
	}
	
	public int getGrade() {
		return grade;
	}
	
	public ScoreLetter getLetter() {
		return letter;
	}
	
	@Override
	public String toString() {
		return "Score: " + grade + " with the letter " + letter;
	}
}
