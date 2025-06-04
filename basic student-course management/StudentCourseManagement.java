import java.util.*;

class Course {
    private String courseId;
    private String courseName;

    public Course(String id, String name) {
        this.courseId = id;
        this.courseName = name;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return courseId + " - " + courseName;
    }
}

class Student {
    private String studentId;
    private String name;
    private List<Course> enrolledCourses;

    public Student(String id, String name) {
        this.studentId = id;
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void enrollInCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            System.out.println(name + " enrolled in " + course.getCourseName());
        } else {
            System.out.println(name + " is already enrolled in " + course.getCourseName());
        }
    }

    public void showCourses() {
        System.out.println("Courses for " + name + ":");
        for (Course c : enrolledCourses) {
            System.out.println("  - " + c);
        }
    }
}

public class StudentCourseManagement {
    static Map<String, Student> students = new HashMap<>();
    static Map<String, Course> courses = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Student Course Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Show Student Courses");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: addStudent(); break;
                case 2: addCourse(); break;
                case 3: enrollStudent(); break;
                case 4: showStudentCourses(); break;
                case 5: exit = true; break;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        students.put(id, new Student(id, name));
        System.out.println("Student added.");
    }

    static void addCourse() {
        System.out.print("Enter course ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        courses.put(id, new Course(id, name));
        System.out.println("Course added.");
    }

    static void enrollStudent() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        Course course = courses.get(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.enrollInCourse(course);
    }

    static void showStudentCourses() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);
        if (student != null) {
            student.showCourses();
        } else {
            System.out.println("Student not found.");
        }
    }
}