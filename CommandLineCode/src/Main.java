import java.io.*;
import java.util.Comparator;
import  java.util.*;
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no. of Student to enter the details");
        int noOfStudents = sc.nextInt();
        int rollNo=0,ageOfStudent=0;
        String addressOfStudent="",nameOfStudent="",courseA="",courseB="",courseC="",courseD="",courseE="",courseF="";
        details[] student = new details[noOfStudents];

        for (int i = 0; i < noOfStudents; i++) {
            student[i] = new details();
            System.out.println("Enter the details of " + i + " Student");
             rollNo = sc.nextInt();
            ageOfStudent = sc.nextInt();
             nameOfStudent = sc.next();
             addressOfStudent = sc.next();
             courseA = sc.next();
             courseB = sc.next();
             courseC = sc.next();
             courseD = sc.next();
             courseE = sc.next();
             courseF = sc.next();
            Queue<String> course = new LinkedList<>();
//            while (course.size() <= 6) {
//                int l = course.size();
//                int k = 4 - l, terminate = 0;
//                if (l < 4)
//                    System.out.println("Please entre " + k + " more courses");
//                if (k <= 0) {
//                    System.out.println("enter 1 if you donot want to add anymore courses");
//                    terminate = sc.nextInt();
//                }
//                if (terminate == 1) break;
//                String specifiedCourse = sc.next();
//                course.add(specifiedCourse);
//            }
            student[i].setDetails(rollNo, ageOfStudent, nameOfStudent, addressOfStudent, courseA, courseB, courseC, courseD, courseE, courseF);

        }
        Arrays.sort(student, new details());
        details.displayDetails(student);
        System.out.println("enter the way you want to sort by age, name, rollno, etc.");
        int sortBy = sc.nextInt();
        if (sortBy == 1)
            Arrays.sort(student, new sortByAge());
        if (sortBy == 2)
            Arrays.sort(student, new sortByRollNo());
        if (sortBy == 3)
            Arrays.sort(student, new sortByAddress());

        details.displayDetails(student);
        System.out.println("Enter the rollno. you want to delete");
        int rollNoToDelete = sc.nextInt();
        boolean checkForRollNo = false;
        delete(rollNoToDelete, student, checkForRollNo);
        details.displayDetails(student);

        details obj = new details();
        obj.rollNoOfStudent = rollNo;
        obj.ageOfStudent = ageOfStudent;
        obj.nameOfStudent = nameOfStudent;
        obj.addressOfStudent = addressOfStudent;
        obj.courseA = courseA;
        obj.courseB = courseB;
        obj.courseC = courseC;
        obj.courseD = courseD;
        obj.courseE = courseE;
        obj.courseF = courseF;
//Saving the object in file

        File F = new File("obj.txt");
        FileOutputStream fileOut = new FileOutputStream(F);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(obj);

//        Retrive the object from file
        FileInputStream fis = new FileInputStream(F);
        ObjectInputStream ois = new ObjectInputStream(fis);
        details obj1 = (details) ois.readObject();

        System.out.println("Press any key to exit");
        sc.next();
        System.exit(0);

    }
    public static void delete(int rollNo, details[] student, boolean checkForRollNo){
        while (checkForRollNo != true)
        for(int i=0;i< student.length;i++){
            if(student[i].rollNoOfStudent == rollNo){
                checkForRollNo = true;
                for(int j=i;j<student.length-1;j++){
                    student[j] = student[j+1];
                }
                student[student.length-1] = null;
                break;
            }
        }
        if(checkForRollNo == false)
            System.out.println("Please enter correct roll no");
    }


}


class details implements Comparator<details>,Serializable{
    public int rollNoOfStudent, ageOfStudent;
    public String nameOfStudent, addressOfStudent, courseA, courseB, courseC, courseD, courseE, courseF;



    public void setDetails(int rollNoOfStudent, int ageOfStudent, String nameOfStudent, String addressOfStudent, String courseA, String courseB, String courseC, String courseD, String courseE, String courseF) {

    this.rollNoOfStudent = rollNoOfStudent;
    this.ageOfStudent = ageOfStudent;
    this.nameOfStudent = nameOfStudent;
    this.addressOfStudent = addressOfStudent;
    this.courseA = courseA;
    this.courseB  = courseB;
    this.courseC  = courseC;
    this.courseD = courseD;
    this.courseE = courseE;
    this.courseF = courseF;
}


    @Override
    public int compare(details o1, details o2) {
        if(o1.nameOfStudent != o2.nameOfStudent)
        return o1.nameOfStudent.compareTo(o2.nameOfStudent);
        return o1.rollNoOfStudent - o2.rollNoOfStudent;
    }

    public static void displayDetails(details[] student) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Name \t Roll Number \t Age \t Address \t Courses");
        System.out.println("----------------------------------------------------------");

        for(int i=0;i< student.length;i++){
            if(student[i] == null)break;
            System.out.print(student[i].nameOfStudent+"\t"+student[i].rollNoOfStudent+"\t"+student[i].ageOfStudent+"\t"+student[i].addressOfStudent+"\t");
            if(student[i].courseA != ""){
                System.out.print(student[i].courseA+",");
            }
            if(student[i].courseB != ""){
                System.out.print(student[i].courseB+",");
            }
            if(student[i].courseC != ""){
                System.out.print(student[i].courseC+",");
            }
            if(student[i].courseD != ""){
                System.out.print(student[i].courseD+",");
            }
            if(student[i].courseE != ""){
                System.out.print(student[i].courseE+",");
            }
            if(student[i].courseF != ""){
                System.out.print(student[i].courseF+",");
            }
            System.out.println();
        }
        System.out.println();
    }

}

class sortByAge implements Comparator<details>{
    @Override
    public int compare(details o1, details o2) {
        return o1.ageOfStudent - o2.ageOfStudent;
    }
}
class sortByRollNo implements Comparator<details>{

    @Override
    public int compare(details o1, details o2) {
        return o1.rollNoOfStudent - o2.rollNoOfStudent;
    }
}
class sortByAddress implements Comparator<details>{

    @Override
    public int compare(details o1, details o2) {
        return o1.addressOfStudent.compareTo(o2.addressOfStudent);
    }
}

