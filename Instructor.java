/** Name: Pongpeera Sukasem
 * StudentID: 5988040
 * Section: 1 (Group 1)
 */


import java.util.*;

public class Instructor extends Person {

	private ArrayList<Integer> skill = new ArrayList<Integer>();	//Create 3 new objects for storage
	private ArrayList<String> researchInterest = new ArrayList<String>();
	private ArrayList<RegCourse> teaching = new ArrayList<RegCourse>();
	
	public Instructor(String firstName, String lastName, int age, char gender) {
	//CODE HERE
		super(firstName,lastName,age,gender);	//Utilizing superclass' constructor
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAge(age);
		this.setGender(gender);
	}
	
	//Other relevant methods should be defined here
	
	//add a teaching course
	public void setTeaching(RegCourse course) {
	//CODE HERE	
		this.teaching.add(course);
	}
	
    //Printing Instructor information @Overridding
    public void printInfo(){
    //CODE HERE
    	System.out.println();
    	super.printInfo();
    	System.out.println("\n[Teaching Courses]");	//Print Instructor's relevant courses
    	for(int i=0;i<teaching.size();i++){
    		teaching.get(i).printCourseInfo();
    	}
    }

	public ArrayList<RegCourse> getTeaching() {
		return teaching;
	}

}
