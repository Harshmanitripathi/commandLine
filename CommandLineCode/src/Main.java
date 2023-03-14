import java.io.*;
import java.util.Comparator;
import  java.util.*;
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no. of Student to enter the details");
        int noOfStudents = sc.nextInt();
        int rollNo=0,ageOfStudent=0;
        String addressOfStudent="",nameOfStudent="";
        boolean courseA = false,courseB= false,courseC = false,courseD=false,courseE = false,courseF = false;
        details[] student = new details[noOfStudents];

        for (int i = 0; i < noOfStudents; i++) {
            courseA = false;
            courseB = false;
            courseC = false;
            courseD = false;
            courseE = false;
            courseF = false;
            student[i] = new details();
            System.out.println("Enter the details of " + i + " Student");
             rollNo = sc.nextInt();
            ageOfStudent = sc.nextInt();
             nameOfStudent = sc.next();
             addressOfStudent = sc.next();
//             courseA = sc.next();
//             courseB = sc.next();
//             courseC = sc.next();
//             courseD = sc.next();
//             courseE = sc.next();
//             courseF = sc.next();
            Queue<String> course = new LinkedList<>();
            System.out.println("enter the no. of courses in digit, you want to take out of A,B,C,D,E,F");
            int noOfCourses  = sc.nextInt();
            if(noOfCourses<4){
                System.out.println("Please enter at least 4 courses");
                while(noOfCourses<4)
                    noOfCourses = sc.nextInt();
            }
            Set<String> setOfCourses = new HashSet<String>();
            setOfCourses.add("A");
            setOfCourses.add("B");
            setOfCourses.add("C");
            setOfCourses.add("D");
            setOfCourses.add("E");
            setOfCourses.add("F");
            for(int k=0;k<noOfCourses;k++){
                System.out.print("Enter course out of ");
                setOfCourses.remove(course.peek());
                System.out.println(setOfCourses);
                String elementCourse = sc.next();
                if(course.contains(elementCourse) == true){
                    System.out.println("You have previously entered this course try another");
                    k--;
                }
                else
                    course.add(elementCourse);
            }
//            System.out.println("I am out of the for loop");
            while (!course.isEmpty()) {
//                System.out.println("I am inside while");
                System.out.println(course.peek());
                if(course.peek().equals("A")){
                    courseA=true;
                    course.remove();
                }
                else if(course.peek().equals("B")){
                    courseB = true;
                    course.remove();
                }
                else if(course.peek().equals("C")){
                    courseC = true;
                    course.remove();
                }
                else if(course.peek().equals("D")){
                    courseD = true;
                    course.remove();
                }
                else if(course.peek().equals("E")){
                    courseE = true;
                    course.remove();
                }
                else if(course.peek().equals("F")){
                    courseF = true;
                    course.remove();
                }

            }
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

//        details obj = new details(student);
//        obj.rollNoOfStudent = rollNo;
//        obj.ageOfStudent = ageOfStudent;
//        obj.nameOfStudent = nameOfStudent;
//        obj.addressOfStudent = addressOfStudent;
//        obj.courseA = courseA;
//        obj.courseB = courseB;
//        obj.courseC = courseC;
//        obj.courseD = courseD;
//        obj.courseE = courseE;
//        obj.courseF = courseF;
//Saving the object in file

        File F = new File("obj.txt");
        FileOutputStream fileOut = new FileOutputStream(F);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(student);

//        Retrive the object from file
        FileInputStream fis = new FileInputStream(F);
        ObjectInputStream ois = new ObjectInputStream(fis);
        details obj1 = (details) ois.readObject();
//        int[] showingDataStored = (int[]) ois.readObject();
        ois.close();
        fis.close();
        System.out.println("Showing data which was saved in txt file");
//        System.out.println(obj1.rollNoOfStudent);


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
    public String nameOfStudent, addressOfStudent;
    boolean courseA, courseB, courseC, courseD, courseE, courseF;



    public void setDetails(int rollNoOfStudent, int ageOfStudent, String nameOfStudent, String addressOfStudent, boolean courseA, boolean courseB, boolean courseC, boolean courseD, boolean courseE, boolean courseF) {

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
            if(student[i].courseA == true){
                System.out.print("A ,");
            }
            if(student[i].courseB == true){
                System.out.print("B ,");
            }
            if(student[i].courseC == true){
                System.out.print("C ,");
            }
            if(student[i].courseD == true){
                System.out.print("D ,");
            }
            if(student[i].courseE == true){
                System.out.print("E ,");
            }
            if(student[i].courseF == true){
                System.out.print("F");
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

