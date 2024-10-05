
package ver5;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


class PersonList {
    private ArrayList<Person> personList;
    Scanner keyboard = new Scanner(System.in);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
    public PersonList() {
        this.personList = new ArrayList<>();
    }
    public void addStudent(Student student) {
        personList.add(student);
        System.out.println("Student added: " + student.getFullName());
    }
    public void addTeacher(Teacher teacher) {
        personList.add(teacher);
        System.out.println("Teacher added: " + teacher.getFullName());
    }
    public void updatePersonById() throws ParseException{
        System.out.println("Enter PersonID need update:");
        String id = keyboard.nextLine();
        Person personToUpdate = null;
        for(Person person : personList){
            if(person.getId().equals(id)){
                personToUpdate = person;
                break;
            }
        }
        if(personToUpdate!=null){
            System.out.println("Enter new Full name:");
            String fullName = keyboard.nextLine();
            System.out.println("new DateOfBirth:");
            String dob = keyboard.nextLine();
            Date dateOfBirth = dateFormat.parse(dob);
            personToUpdate.setFullName(fullName);
            personToUpdate.setDateOfBirth(dateOfBirth);
            if(personToUpdate instanceof Student){
                System.out.println("Enter the new GPA:");
                float gpa = keyboard.nextFloat();
                keyboard.nextLine();
                System.out.println("Enter new Major:");
                String major = keyboard.nextLine();
                Student student = (Student) personToUpdate;
                student.setGpa(gpa);
                student.setMajor(major);
                System.out.println("Update Succesful!!!");
            } else if (personToUpdate instanceof Teacher){
                System.out.println("Enter the new department:");
                String department = keyboard.nextLine();
                System.out.println("new TeachingSubjet:");
                String teachingSubject = keyboard.nextLine();
                Teacher teacher = (Teacher) personToUpdate;
                teacher.setDepartment(department);
                teacher.setTeachingSubject(teachingSubject);
                System.out.println("Update Succesful!!!");
            } else {
                System.out.println("Person with ID:"+id+"not found.");
            }
        }
    }
    public void deletePersonById(String id) {
        personList.removeIf(person -> person.getId().equals(id));
        System.out.println("Deleted person with ID: " + id);
    }
    public Person findPersonById(String id) {
        for (Person person : personList) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }
    public void displayEveryone() {
        for (Person person : personList) {
            person.displayInfo();
        }
    }
    public Student findTopStudent() {
        Student topStudent = null;
        for (Person person : personList) {
            if (person instanceof Student) {
                Student student = (Student) person;
                if (topStudent == null || student.getGpa() > topStudent.getGpa()) {
                    topStudent = student;
                }
            }
        }
        return topStudent;
    }
    public Teacher findTeacherByDepartment(String department) {
        for (Person person : personList) {
            if (person instanceof Teacher) {
                Teacher teacher = (Teacher) person;
                if (teacher.getDepartment().equals(department)) {
                    return teacher;
                }
            }
        }
        return null;
    }
    public void checkBookBorrowing() {
        for (Person person : personList) {
            if (person.isBookOverdue()) {
                System.out.println(person.getFullName() + ": Overdue");
            } else {
                System.out.println(person.getFullName() + ": No overdue");
            }
        }
    }
}