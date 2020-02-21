import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NumberFormatException;

public class CourseTest_0 {
    public static void main(String[] args) {
        try {
            boolean p = true;
            Scanner sc = new Scanner(System.in);
            ArrayList<Course> list = new ArrayList<>();
            while (p) {
                System.out.println("Would you like to create some courses: yes or no ?");
                String sf = sc.nextLine();
                if (sf.equals("yes")) {
                    System.out.println("Please input the course name:");
                    String courseName = "";
                    if (sc.hasNext()) {
                        courseName = sc.nextLine();
                    }
                    int courseCredit;
                    while (true) {
                        System.out.println("Please input the course credit:");
                        if (sc.hasNextInt()) {
                            courseCredit = sc.nextInt();
                            if (courseCredit>0){break;}
                        }
                        sc.nextLine();
                    }
                    String courseGrade;
                    sc.nextLine();
                    while (true) {
                        System.out.println("Please enter the course grade:");
                        courseGrade = sc.nextLine();
                        if (courseGrade.equals("senior") || courseGrade.equals("junior") || courseGrade.equals("freshman") || courseGrade.equals("sophomore")) {
                            break;
                        }
                    }
                    System.out.println("Please input the course teacher:");
                    String courseTeacher;
                    if (sc.hasNext()) {
                        courseTeacher = sc.nextLine();
                        list.add(new Course(courseName, courseCredit, courseGrade, courseTeacher));
                    }
                } else if (sf.equals("no")) {
                    p = false;
                }
            }
            p = true;
            while (p) {
                System.out.println("Would you like to obtain course: yes or no ?");
                String sf = sc.nextLine();
                if (sf.equals("yes")) {
                    System.out.printf("There are %d courses in the system, please pick No.", list.size());
                    int a = sc.nextInt();
                    if (a >= list.size() || a < 0) {
                        System.out.println("You can not find this course.");
                    } else {
                        System.out.printf("The course level is: %s\n", list.get(a).getCourseLevel());
                        System.out.println("Is this course with lab? " + list.get(a).isWithLab());
                        String grade;
                        while (true) {
                            System.out.println("Please enter your grade:");
                            if (sc.hasNext()) {
                                grade = sc.nextLine();
                                if (grade.equals("senior") | grade.equals("junior") | grade.equals("freshman") | grade.equals("sophomore")) {
                                    break;
                                }
                            }
                        }
                        System.out.println("The result for your qualification to enroll in the course is: " + list.get(a).getQualificationForCourse(grade));
                    }
                    int credit;
                    while (true) {
                        System.out.println("Input a course credit:");
                        if (sc.hasNextInt()) {
                            credit = sc.nextInt();
                            break;
                        }
                    }
                    StringBuilder cou = new StringBuilder();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getCredit() == credit) {
                            cou.append(list.get(i).getName()).append(", ");
                        }
                    }
                    cou.delete(cou.length() - 2, cou.length());
                    System.out.print("The course with " + credit + " credits are [" + cou + "]\n");
                    cou.delete(0, cou.length());
                    System.out.println("Input a teacher's name:");
                    String teacher = sc.nextLine();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getTeacher().matches(".*" + teacher + ".*")) {
                            cou.append(list.get(i).getName()).append(", ");
                        }
                    }
                    cou.delete(cou.length() - 2, cou.length());
                    System.out.println("The course taught by " + teacher + " are [" + cou + "]");
                } else if (sf.equals("no")) {
                    p = false;
                }
            }
            p = true;
            while (p) {
                System.out.println("Would you like to obtain course information after removing certain courses: yes or no ?");
                String sf = sc.nextLine();
                if (sf.equals("yes")) {
                    System.out.println("Please pick the index of the course you want to remove:");
                    int index = sc.nextInt();
                    if (index >= list.size() || index < 0) {
                        System.out.println("You can not find this course.");
                    } else {
                        list.remove(index);
                        StringBuilder course = new StringBuilder("[");
                        for (int i = 0; i < list.size(); i++) {
                            course.append(list.get(i) + ", ");
                        }
                        if (course.length() > 1) {
                            course.delete(course.length() - 2, course.length());
                        }
                        course.append("]");
                        System.out.print(course);
                    }
                } else if (sf.equals("no")) {
                    p = false;
                }
            }
        } catch (InputMismatchException | IndexOutOfBoundsException | NumberFormatException error) {
            if (error instanceof IndexOutOfBoundsException) {
                System.out.println("None of courses satisfies the condition.");
            } else {
                System.out.print("Illegal input");
            }
        }
    }
}

