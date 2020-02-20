public class Student {
    private String firstName;
    private String lastName;
    private Gender gender;
    public Student(String firstName, String lastName, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String toString() {
        return String.format("%s %s, %s", firstName, lastName, gender);
    }
}
enum Gender {
    MALE, FEMALE
}
