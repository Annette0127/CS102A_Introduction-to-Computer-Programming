public class Course {
    private String name;
    private int credit;
    private String grade;
    private String teacher;

    public Course(String name) {
        this.name = name;
    }

    public Course(String name, int credit, String grade, String teacher) {
        this.name = name;
        this.credit = credit;
        this.grade = grade;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        if(grade.equals("senior") || grade.equals("junior") || grade.equals("freshman") || grade.equals("sophomore"))
        {this.grade = grade;}
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String toString() {
        return name;
    }

    public String getCourseLevel() {
        if (grade.equals("junior") || grade.equals("senior")) {
            return "major course";
        } else if (grade.equals("freshman") || grade.equals("sophomore")) {
            return "common course";
        } else {
            return "not valid query";
        }
    }

    public String getQualificationForCourse(String gra) {
        if (((gra.equals("junior") || gra.equals("senior")) && (grade.equals("junior") || grade.equals("senior"))) || ((gra.equals("freshman") || gra.equals("sophomore")) && (grade.equals("freshman") || grade.equals("sophomore")))) {
            return "You are qualified for the course";
        } else {
            return "You are not qualified for the course";
        }

    }

    public boolean isWithLab() {
        return credit == 3;
    }

}
