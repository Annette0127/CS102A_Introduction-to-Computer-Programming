import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.IndexOutOfBoundsException;

public class CourseTest {
        public static void main(String[] args) {
            try {
                boolean p = true;
                Scanner sc = new Scanner(System.in).useDelimiter("\n+");
                ArrayList<Course> list = new ArrayList<>();
                while (p) {
                    System.out.println("Would you like to create some courses: yes or no ?");
                    String sf = sc.next();
                    if (sf.equals("yes")) {
                        System.out.println("Please input the course name:");
                        String courseName = sc.next();
                        int courseCredit = 0;
                        while (courseCredit <= 0) {
                            System.out.println("Please input the course credit:");
                            if (sc.hasNextInt()) {
                                courseCredit = sc.nextInt();
                            } else {
                                sc.next();
                            }
                        }
                        String courseGrade = "";
                        while (!(courseGrade.equals("senior") || courseGrade.equals("junior") || courseGrade.equals("freshman") || courseGrade.equals("sophomore"))) {
                            System.out.println("Please enter the course grade:");
                            courseGrade = sc.next();
                        }
                        System.out.println("Please input the course teacher:");
                        String courseTeacher = sc.next();
                        list.add(new Course(courseName, courseCredit, courseGrade, courseTeacher));
                    } else {
                        p = !sf.equals("no");
                    }
                }
                p = true;
                while (p) {
                    System.out.println("Would you like to obtain course information: yes or no ?");
                    String sf = sc.next();
                    if (sf.equals("yes")) {
                        System.out.printf("There are %d courses in the system, please pick No.", list.size());
                        int a = sc.nextInt();
                        if (a >= list.size() || a < 0) {
                            System.out.println("You can not find this course.");
                        } else {
                            System.out.printf("The course level is: %s\n", list.get(a).getCourseLevel());
                            System.out.println("Is this course with lab? " + list.get(a).isWithLab());
                            String grade = "";
                            while (!(grade.equals("senior") || grade.equals("junior") || grade.equals("freshman") || grade.equals("sophomore"))) {
                                System.out.println("Please enter your grade:");
                                grade = sc.next();
                            }
                            System.out.println("The result for your qualification to enroll in the course is: " + list.get(a).getQualificationForCourse(grade));
                        }
                        int credit = 0;
                        while (credit <= 0) {
                            System.out.println("Input a course credit:");
                            credit = sc.nextInt();
                        }
                        StringBuilder cou = new StringBuilder();
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getCredit() == credit) {
                                cou.append(list.get(i).getName()).append(", ");
                            }
                        }
                        cou.delete(cou.length() - 2, cou.length());
                        System.out.print("The courses with " + credit + " credits are [" + cou + "]\n");
                        cou.delete(0, cou.length());
                        System.out.println("Input a teacher's name:");
                        String teacher = sc.next();
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getTeacher().matches(".*" + teacher + ".*")) {
                                cou.append(list.get(i).getName()).append(", ");
                            }
                        }
                        cou.delete(cou.length() - 2, cou.length());
                        System.out.println("The courses taught by " + teacher + " are [" + cou + "]");
                    } else {
                        p = !sf.equals("no");
                    }
                }
                p = true;
                while (p) {
                    System.out.println("Would you like to obtain course information after removing certain courses: yes or no ?");
                    String sf = sc.next();
                    if (sf.equals("yes")) {
                        System.out.println("Please pick the index of the course you want to remove:");
                        int index = sc.nextInt();
                        if (index >= list.size() || index < 0) {
                            System.out.println("You can not find this course.");
                        } else {
                            list.remove(index);
                            StringBuilder course = new StringBuilder("[");
                            for (int i = 0; i < list.size(); i++) {
                                course.append(list.get(i)).append(", ");
                            }
                            if (course.length() > 1) {
                                course.delete(course.length() - 2, course.length());
                            }
                            course.append("]");
                            System.out.print("The remaining courses are "+course+"\n");
                        }
                    } else {
                        p = !sf.equals("no");
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

