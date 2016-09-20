package week4_chap4;

public enum ScoreLetter {
	A,
	B,
	C,
	D,
	F;
	
	public static ScoreLetter getLetter(int score) {
		if(score >= 90)
			return A;
		if(score >= 80)
			return B;
		if(score >= 70)
			return C;
		if(score >= 60)
			return D;
		return F;
	}
}
