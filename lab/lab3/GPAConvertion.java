public class GPAConvertion {

	public static void main(String[] args) {
		float gpa;
		float grade = Float.parseFloat(args[0]);

		if (grade >= 90) {
			gpa = 4.0f;
		} else if (grade >= 80) {
			gpa = 3.0f;
		} else if (grade >= 70) {
			gpa = 2.0f;
		} else if (grade >= 60) {
			gpa = 1.0f;
		} else if (grade > 0) {
			gpa = 0.0f;
		} else {
			System.out.println("Invalide grade");
			return;
		}

		System.out.printf("Your score is %.1f, the GPA is %.1f\n", grade, gpa);

	}

}
