/* Name: Pongpeera Sukasem
 * StudentID: 5988040
 * Section: 1 (Group 1)
 */

/* Student is a class that collect registered courses information and subject of interest.
 * The objective of this class is to allow a students to be able to do the following tasks 
 * 
 *  - Register on the course.
 *  - Set scores for a course based on course code
 *  - Calculate the accummulate GPA
 *  - Show the subject that students may fail after the midterm exam
 *  - Method to calculate how much score students need to do to get an A 
 * */

import java.util.ArrayList;

public class Student extends Person {
	
	private ArrayList<String> subjectInterest = new ArrayList<String>();	//Create new ArrayLists to store objects
	private ArrayList<RegCourse> registeredCourses = new ArrayList<RegCourse>();
	
	// Constructor
	public Student(String firstName, String lastName, int age, char gender) {
		//CODE HERE
		super(firstName,lastName,age,gender);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAge(age);
		this.setGender(gender);
	}

	//Printing Students basic information @Overriding
	public void printInfo(){
		//CODE HERE
		super.printInfo();
		System.out.println("\n[Registered courses]");	//Print Students' RegisteredCourses
		for(int i=0;i<registeredCourses.size();i++){
			registeredCourses.get(i).printCourseInfo();
		}
	}
	
	//Method for students to register course
	public void RegisterCourse(RegCourse a){
		//CODE HERE
		registeredCourses.add(a);
	}
	
	//Method for student to add RAW scores on particular course code 
	public void setAllScore(String cCode, int attScore, int quiScore, int proScore,int miScore, int fiScore) {
		//CODE HERE
		int accumScore = attScore+quiScore+proScore+miScore+fiScore;	//Adding up all raw scores
		for(int i = 0; i<registeredCourses.size();i++){	
			if((registeredCourses.get(i).getCourseCode().equals(cCode))){
				registeredCourses.get(i).setCompletedCourse(true);	//Initialized as true, if any setters below has -1 input,
				registeredCourses.get(i).setAttendance(attScore);	//the setter will turn this into false
				registeredCourses.get(i).setQuiz(quiScore);
				registeredCourses.get(i).setProjects(proScore);
				registeredCourses.get(i).setMidScore(miScore);
				registeredCourses.get(i).setFinalScore(fiScore);
				registeredCourses.get(i).setAccumScore(accumScore);
				return;
			}
		}
	}
	
	//Method for converting accumulate score to an alphabet GRADE (e.g., A, B, C, D, F)
	// A=4.0, B=3.0, C=2.0, D=1.0, F=0.0
	public char Grading(RegCourse a){
		//CODE HERE
		char x = '?';
		if(a.isCompletedCourse() == true){
			if(a.getAccumScore() >= 80.0 && a.getAccumScore() <= 100.0){ //A: 80-100
				x = 'A';
			}
			else if(a.getAccumScore() >= 70.0 && a.getAccumScore() < 80.0){ //B: 70-79
				x = 'B';
			}
			else if(a.getAccumScore() >= 60 && a.getAccumScore() < 70.0){ //C: 60-69
				x = 'C';
			}
			else if(a.getAccumScore() >= 50 && a.getAccumScore() < 60.0){ //D: 50-59
				x = 'D';
			}
			else if(a.getAccumScore() >= 0.0 && a.getAccumScore() < 50){ //F: <50
				x = 'F';
			}
		}
		return x;
	}
	
	// Method for calculating accumulate GPA (only applied for completed courses)
	// The Accumulate GPA is calculated by (1) multiply each numeric grade value 
	// by the number of credits the course was defined. (2) add these number together  
	// (3) Divided this number by total number of credit a student took (only the completed course)
	public double accumGPA(){	
		double finalgpa = 0.0, totalcreds = 0.0, creds = 0.0;
		//CODE HERE
		for(int i=0;i<registeredCourses.size();i++){
			if(registeredCourses.get(i).isCompletedCourse() == true){// If the course is completed,
				switch(this.Grading(registeredCourses.get(i))){		 // The course's grade will be converted 
				case 'A':											 // into double & calculated
					creds += (4.0*(double)this.registeredCourses.get(i).getCourseCredit());	
					totalcreds += (double)this.registeredCourses.get(i).getCourseCredit();
					break;
				case 'B':	
					creds += (3.0*(double)this.registeredCourses.get(i).getCourseCredit());	
					totalcreds += (double)this.registeredCourses.get(i).getCourseCredit();
					break;
				case 'C':
					creds += (2.0*(double)this.registeredCourses.get(i).getCourseCredit());	
					totalcreds += (double)this.registeredCourses.get(i).getCourseCredit();
					break;
				case 'D':
					creds += (1.0*(double)this.registeredCourses.get(i).getCourseCredit());	
					totalcreds += (double)this.registeredCourses.get(i).getCourseCredit();
					break;
				case 'F':
					creds += 0.0;
					totalcreds += (double)this.registeredCourses.get(i).getCourseCredit();
					break;
				}
			}
		}
		finalgpa = creds / totalcreds;
		return finalgpa;
	}
	
	// Method for checking and printing course that students may have problems
	// The severe subject is calculated by accumulate score < half of a current full score
	// E.g., Assume that  the Object-Oriented programming has grading criteria as attendance=10%, quiz=10%, project=20%, midterm=30%, final=30% 
	// Currently your score is attendance=50/100, quiz=4/10, project=45/100, midterm 48/100, finalexam = 0/100 (haven't done final exam) .
	// The accumulate score = (50*10)/100 + (4*10)/10 + (45*20)/100 + (48*30)/100 + 0 
	//	                    =  5 + 4 + 9 + 14.4 + 0  = 32.4
	// Therefore, the accumulate score (32.4) less than half of a current grading criteria score which is (35)
	// Student may have a problem with this subject at the end of the course (after final exam).
	public void severeSubject(){
		//CODE HERE
		boolean print = true;	//Marker to check if [Severe subject] has been printed
		for(int i = 0; i<registeredCourses.size();i++){
			if(registeredCourses.get(i).getAccumScore() < registeredCourses.get(i).getFullScore()/2 && registeredCourses.get(i).isCompletedCourse() == false){
					if(print == true){	// ^Check if accumScore < fullScore/2 and if the subject is not yet completed
						System.out.println("\n[Severe subject]");
						registeredCourses.get(i).printCourseInfo();
						print = false;
					}
					else{
						registeredCourses.get(i).printCourseInfo();
					}
			}
		}
	}

	// Method to calculate on how much score a student need to work on to get an A on a given course
	// To get an 'A', students must have score more than 80%
	public void howToGetASubject(String cCode){
		//CODE HERE
		for(int i = 0; i<registeredCourses.size();i++){	
			if((registeredCourses.get(i).getCourseCode().equals(cCode))){//Comparing the codes
				System.out.println("\n["+registeredCourses.get(i).getCourseCode()+"-"+registeredCourses.get(i).getCourseName()+"]");
				if(registeredCourses.get(i).isCompletedCourse() == true){//Checks if the course is completed or not
					System.out.println("You have already completed this course.");
					return;
				}
				else if(registeredCourses.get(i).getAccumScore() < (registeredCourses.get(i).getFullScore()/2)){
					System.out.println("You can't get A for this subject.");//Score < half = no chance for A
					return;
				}
				else{
					System.out.println("You need "+(80.0-registeredCourses.get(i).getAccumScore())+" score more to get an A for this subject.");
					return;//A is at least 80 so -> 80-accumScore
				}
			}
		}
	}
	
	//Method to lists all instructors that teach the registered subjects
	public void relevantInstructor(ArrayList<Instructor> listINS){
		//CODE HERE
		System.out.println("\n[Relevant Instructor]");
		ArrayList<String> print = new ArrayList<String>();
		int n=0, flag=0;
		for(int i = 0;i<listINS.size();i++){//Comparing each Instructor's teaching subjects' codes w/ this Student's registered courses
			for(int j = 0;j<listINS.get(i).getTeaching().size();j++){
				for(int k = 0;k<registeredCourses.size();k++){
					if(listINS.get(i).getTeaching().get(j).getCourseCode().equals(registeredCourses.get(k).getCourseCode())){
						String temp = listINS.get(i).getFirstName()+" "+listINS.get(i).getLastName();//temp String to check for duplicates
						for(int l=0;l<print.size();l++){//Check for duplicates
							if(temp.equals(print.get(l))){
								flag = 0;//Duplicate
							}
							else{
								flag = 1;//Not duplicate
							}
						}
						if(flag == 1){//If new, add it into printing ArrayList
						print.add(listINS.get(i).getFirstName()+" "+listINS.get(i).getLastName());
						n++;
						}
						flag = 1;
					}
				}
			}
		}
		for(int i=0;i<print.size();i++){//Printing all names
			System.out.println(print.get(i));
		}
	}

	public ArrayList<RegCourse> getRegisteredCourses() {
		return registeredCourses;
	}

}
