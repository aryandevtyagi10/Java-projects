class Student{
    String name;
    int marks;
}

public class array {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name= "Aryan";
        s1.marks = 98;

        Student s2 = new Student();
        s2.name= "Dev";
        s2.marks = 69;

        Student[] students = new Student[2];
        students[0]= s1;
        students[1]= s2;

        for (Student student : students) {
            System.out.println(student.name + " " + student.marks);
        }
    }
}