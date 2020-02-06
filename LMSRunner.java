import java.util.ArrayList;
import java.util.Scanner;

/** LMSRunner.java - Start building ICT-LMS system 
 * Name: Pongpeera Sukasem
 * StudentID: 5988040
 * Section: 1 (Group 1)
 */

/*
 *  LMSRunner is a main class for Administrators and Students to access the LMS based on their role.
 *  In LMSRunner, your task is to implement the system asking which roles a user is.
 *  
 *  If a user is an Administrator, he/she can do the following tasks
 *  - Add/Update/Delete Course
 *  - Add/Update/Delete Instructor
 *  - Add/Update/Delete Student
 *  - Able to set grading criteria (e.g., attendance, project, quiz, midterm, final) to the Course.
 *  - Able to add score of students in each criterion to the system.
 *  - Able to print information of all instructors and their teaching courses.
 *  - Able to print information of all students and their registered courses.
 *  
 *  For Students, They can do the following tasks
 *  - Able to register to the existing courses (add by administrator).
 *  - Able to see the accumulated GPA (only the subject that they have completed).
 *  - Able to evaluate the score that a student need to do to get  ‘A’ for a particular subject (only work with subject that they haven’t completed).
 *  - Able to illustrate subject that a student is likely have a problem with (only work with subject that they haven’t completed).
 *  - Able to search names of the instructors who teach in the registered courses.
 *
 * */

public class LMSRunner {
	//CODE HERE
	private static ArrayList<RegCourse> listCOR;	//List of Courses
	private static ArrayList<Instructor> listINS;	//List of Instructors
	private static ArrayList<Student> listSTD;		//List of Students
	
	public static void printTeaCor(int inspos){//Prints out menu of Instructor listINS(intpos)'s teaching courses
		System.out.println("Available Courses: ");
		for(int i=0;i<listINS.get(inspos).getTeaching().size();i++){
				System.out.print("'"+i+"' - ");
				listINS.get(inspos).getTeaching().get(i).printCourseInfo();
		}
	}	
	
	public static void printRegCor(int stdpos){//Prints out menu of Student listSTD(intpos)'s registered courses
		System.out.println("Available Courses: ");
		for(int i=0;i<listSTD.get(stdpos).getRegisteredCourses().size();i++){
				System.out.print("'"+i+"' - ");
				if(listSTD.get(stdpos).getRegisteredCourses().get(i).isCompletedCourse() == false){
					System.out.print("(Not Completed)");
				}
				listSTD.get(stdpos).getRegisteredCourses().get(i).printCourseInfo();
		}
	}
	
	public static void printCor(){//Prints out menu of listCOR's Courses
		System.out.println("Available Courses: ");
		for(int i=0;i<listCOR.size();i++){
				System.out.print("'"+i+"' - ");
				listCOR.get(i).printCourseInfo();
		}
	}
	
	public static void printInst(){//Prints out menu of listINS's Instructors
		System.out.println("Available Instructors: ");
		for(int i=0;i<listINS.size();i++){
			System.out.println("'"+i+"' - "+listINS.get(i).getFirstName()+" "+listINS.get(i).getLastName());
		}
	}
	
	public static void printStd(){//Prints out menu of listSTD's Students
		System.out.println("Available Students: ");
		for(int i=0;i<listSTD.size();i++){
			System.out.println("'"+i+"' - "+listSTD.get(i).getFirstName()+" "+listSTD.get(i).getLastName());
		}
	}
	
	public static void updateStd(boolean add, int pos){// Adds/Updates Student's info
		String fn = "", ln = "", gen = "?";				//Inputs:boolean to check if adding or updating, position of change in listSTD
		int age = 0; 
		Student std;
		Scanner in = new Scanner(System.in);
		Scanner inint = new Scanner(System.in);
		System.out.print("Student First Name(String): "); fn = in.nextLine();
		System.out.print("Student Last Name(String):  "); ln = in.nextLine();
		System.out.print("Student Age(Integer):       "); age = inint.nextInt();
		while(true){
		System.out.print("Student Gender(1 String): ('M'/'m' = Male, 'F'/'f' = Female) "); 
			gen = in.nextLine();
			if(add == true){//add == true -> Adding new Student
				if(gen.equals("m") || gen.equals("M")){
					std = new Student(fn,ln,age,'M');
					listSTD.add(std);
					break;
				}
				else if(gen.equals("f") || gen.equals("F")){
					std = new Student(fn,ln,age,'F');
					listSTD.add(std);
					break;
				}
				else{
					System.out.println("Invalid option.");
				}
			}
			else{//add == false -> Updating Student listSTD(pos)
				if(gen.equals("m") || gen.equals("M")){
					std = new Student(fn,ln,age,'M');
					listSTD.set(pos,std);//Replace Student with a new one in position pos
					break;
				}
				else if(gen.equals("f") || gen.equals("F")){
					std = new Student(fn,ln,age,'F');
					listSTD.set(pos,std);
					break;
				}
				else{
					System.out.println("Invalid option.");
				}
			}
		}
		System.out.println(fn+" "+ln+", Age: "+age+", Gender: "+gen);
		System.out.println("Don't forget to add the student's registered courses!");
		return;
	}
	
	public static void updateInst(boolean add, int pos){// Adds/Updates Instructor's info
		String fn = "", ln = "", gen = "?";				//Inputs:boolean to check if adding or updating, position of change in listINS
		int age = 0; 
		Instructor inst;
		Scanner in = new Scanner(System.in);
		Scanner inint = new Scanner(System.in);
		System.out.print("Instructor First Name(String): "); fn = in.nextLine();
		System.out.print("Instructor Last Name(String):  "); ln = in.nextLine();
		System.out.print("Instructor Age(Integer):       "); age = inint.nextInt();
		while(true){
			System.out.print("Instructor Gender(1 String): ('M'/'m' = Male, 'F'/'f' = Female) "); 
			gen = in.nextLine();
			if(add == true){//add == true -> Adding new Instructor
				if(gen.equals("m") || gen.equals("M")){
					inst = new Instructor(fn,ln,age,'M');
					listINS.add(inst);
					break;
				}
				else if(gen.equals("f") || gen.equals("F")){
					inst = new Instructor(fn,ln,age,'F');
					listINS.add(inst);
					break;
				}
				else{
					System.out.println("Invalid option.");
				}
			}
			else{//add == false -> Updating Instructor listINS(pos)
				if(gen.equals("m") || gen.equals("M")){
					inst = new Instructor(fn,ln,age,'M');
					listINS.set(pos,inst);
					break;
				}
				else if(gen.equals("f") || gen.equals("F")){
					inst = new Instructor(fn,ln,age,'F');
					listINS.set(pos,inst);
					break;
				}
				else{
					System.out.println("Invalid option.");
				}
			}
		}
		System.out.println(fn+" "+ln+", Age: "+age+", Gender: "+gen);
		System.out.println("Don't forget to add the instructor's teaching subjects!");
		return;
	}
	
	public static void setScore(int stdpos,int corpos){//Sets Student's raw score in a subject
		System.out.println("Setting scores of this subject: ");//Inputs: Position of Student in listSTD
		Scanner inint = new Scanner(System.in);					//,position of registeredCourse of the Student
		int at = 0, qu = 0, pj = 0,mt = 0, fn = 0;
		//In each loop, if the input is not logically correct. the user may input again immediately
		while(true){
			System.out.print("Enter Attendance score(Integer), type -1 if the student has not done it yet): ");
			at = inint.nextInt();
			if(at > listSTD.get(stdpos).getRegisteredCourses().get(corpos).getFull_score_attendance() || at < -1){
				System.out.println("Invalid score, please re-input.");
			}
			else{
				break;
			}
		}
		while(true){
			System.out.print("Enter Quiz score(Integer), type -1 if the student has not done it yet):       ");
			qu = inint.nextInt();
			if(qu > listSTD.get(stdpos).getRegisteredCourses().get(corpos).getFull_score_quiz() || qu < -1){
				System.out.println("Invalid score, please re-input.");
			}
			else{
				break;
			}
		}
		while(true){
			System.out.print("Enter Projects score(Integer), type -1 if the student has not done it yet):   ");
			pj = inint.nextInt();
			if(pj > listSTD.get(stdpos).getRegisteredCourses().get(corpos).getFull_score_projects() || pj < -1){
				System.out.println("Invalid score, please re-input.");
			}
			else{
				break;
			}
		}
		while(true){
			System.out.print("Enter Midterm score(Integer), type -1 if the student has not done it yet):    ");
			mt = inint.nextInt();
			if(mt > listSTD.get(stdpos).getRegisteredCourses().get(corpos).getFull_score_midScore() || mt < -1){
				System.out.println("Invalid score, please re-input.");
			}
			else{
				break;
			}
		}
		while(true){
			System.out.print("Enter Final score(Integer), type -1 if the student has not done it yet):      ");
			fn = inint.nextInt();
			if(fn > listSTD.get(stdpos).getRegisteredCourses().get(corpos).getFull_score_finalScore() || fn < -1){
				System.out.println("Invalid score, please re-input.");
			}
			else{
				break;
			}
		}
		listSTD.get(stdpos).setAllScore(listSTD.get(stdpos).getRegisteredCourses().get(corpos).getCourseCode(), at, qu, pj, mt, fn);
		System.out.println("Raw score added-> AT: "+at+", QZ: "+qu+", PJ: "+pj+", MT: "+mt+", FN: "+fn+"");
		return;
	}
	
	public static void setFullScore(int pos){//Set a Course's fullScore, Input: Position in listCOR to set score
		System.out.println("Setting scores of this subject: ");
		Scanner inint = new Scanner(System.in);
		int pat = 0,pqu = 0,ppj = 0,pmt = 0,pfn = 0;
		double at = 0.0, qu = 0.0, pj = 0.0,mt = 0.0, fn = 0.0;
		System.out.print("Enter Attendance grading %(Integer):   ");
		pat = inint.nextInt();
		System.out.print("Enter Quiz grading %(Integer):         ");
		pqu = inint.nextInt();
		System.out.print("Enter Projects grading %(Integer):     ");
		ppj = inint.nextInt();
		System.out.print("Enter Midterm grading %(Integer):      ");
		pmt = inint.nextInt();
		System.out.print("Enter Final grading %(Integer):        ");
		pfn = inint.nextInt();
		
		System.out.print("Enter Attendance full score(Double):  ");
		at = inint.nextInt();
		System.out.print("Enter Quiz full score(Double):        ");
		qu = inint.nextInt();
		System.out.print("Enter Projects full score(Double):    ");
		pj = inint.nextInt();
		System.out.print("Enter Midterm full score(Double):     ");
		mt = inint.nextInt();
		System.out.print("Enter Final full score(Double):       ");
		fn = inint.nextInt();
		listCOR.get(pos).setCourseGrading(pat,pqu,ppj,pmt,pfn);
		listCOR.get(pos).setFullScore(at,qu,pj,mt,fn);
		System.out.println("Grading->    AT: "+pat+"%, QZ: "+pqu+"%, PJ: "+ppj+"%, MT: "+pmt+"%, FN: "+pfn+"%");
		System.out.println("Full Score-> AT: "+at+", QZ: "+qu+", PJ: "+pj+", MT: "+mt+", FN: "+fn+"");
		return;
	}
	
	public static void updateCourse(boolean add, int pos){// Adds/Updates Course info
		String id = "", name = "", opt = "";			  //Inputs:boolean to check if adding or updating, position of change in listCOR
		int creds = 0; 
		RegCourse course;
		Scanner in = new Scanner(System.in);
		Scanner inint = new Scanner(System.in);
		System.out.print("Course ID(String): "); id = in.nextLine();
		System.out.print("Course Name(String): "); name = in.nextLine();
		System.out.print("Course credits(Integer): "); creds = inint.nextInt();
		while(true){
			
			System.out.print("Is it a core course?(1 String): ('Y'/'y' = Yes,'N'/'n' = No) "); 
			opt = in.nextLine();
			if(add == true){//add == true -> New Course
				if(opt.equals("y") || opt.equals("Y")){
					course = new RegCourse(id,name,true,creds);
					listCOR.add(course);
					System.out.println(id+" - "+name+": "+creds+" credits, Is a core subject");
					setFullScore(listCOR.size()-1);//For convenience, after adding a new Course, you must
					break;						   // immediately add the Course's grading criterias.
				}
				else if(opt.equals("n") || opt.equals("N")){
					course = new RegCourse(id,name,false,creds);
					listCOR.add(course);
					System.out.println(id+" - "+name+": "+creds+" credits, Is not a core subject");
					setFullScore(listCOR.size()-1);
					break;
				}
				else{
					System.out.println("Invalid option.");
				}
			}
			else{//add == false -> Updating Course listCOR(pos)
				if(opt.equals("y") || opt.equals("Y")){
					course = new RegCourse(id,name,true,creds);
					listCOR.set(pos,course);
					System.out.println(id+" - "+name+": "+creds+" credits, Is a core subject");
					break;
				}
				else if(opt.equals("n") || opt.equals("N")){
					course = new RegCourse(id,name,false,creds);
					listCOR.set(pos,course);
					System.out.println(id+" - "+name+": "+creds+" credits, Is not a core subject");
					break;
				}
				else{
					System.out.println("Invalid option.");
				}
			}
		}
		return;
	}
	
	public static void adminCase(){//Case for Administrators
		while(true){
			Scanner in = new Scanner(System.in);
			String choice = "";
			int num = 0;
			//Most of the if-else statements are menus detailing the members of ArrayLists implemented
			System.out.println("\nAvailable functions for Administrator:");
			System.out.println("'1': Add/Update/Delete Courses			  	'2': Add/Update/Delete Instructor");
			System.out.println("'3': Add/Update/Delete Student			  	'4': Set a Course's grading criterias");
			System.out.println("'5': Set an Instructor's teaching courses 		'6': Remove an Instructor's teaching courses");
			System.out.println("'7': Add a Student's scores				'8': Print all Instructors' infos");
			System.out.println("'9': Print all Students' infos		  		'Q'/'q': Return to main menu");
			choice = in.nextLine();
			switch(choice){
			case "1":  //Add/Update/Delete Courses
				System.out.println("[Add/Update/Delete Courses]");
				System.out.println("'1': Add a new Course		'2': Update a Course(Change info)");
				System.out.println("'3': Remove a Course		'Q'/'q': Go back");
				choice = in.nextLine();
				switch(choice){
				case "1"://Add a new Course
					System.out.println("[Add a new Course]");
					updateCourse(true,99);
					break;
					
				case "2"://Update a Course(Change info)
					System.out.println("[Update a Course]");
					if(listCOR.size() == 0){
						System.out.println("No courses added yet, returning...");
					}
					else{
						printCor();
						System.out.print("Select a Course to update by typing the number in front of it: ");
						num = in.nextInt();
						if(num >= listCOR.size() || num < 0){
							System.out.println("Position does not exist.");
						}
						else{
							updateCourse(false,num);
						}
					}
					break;
					
				case "3"://Remove a Course
					System.out.println("[Remove a Course]");
					if(listCOR.size() == 0){
						System.out.println("No courses added yet, returning...");
					}
					else{
						printCor();
						System.out.print("Select a Course to remove by typing the number in front of it: ");
						num = in.nextInt();
						if(num >= listCOR.size() || num < 0){
							System.out.println("Position does not exist.");
						}
						else{
							listCOR.remove(num);
						}
					}
					break;
					
				case "Q": case"q":  break;
				default: System.out.println("Invalid input."); break;
				}
				break;
			
			case "2":  //Add/Update/Delete Instructor
				System.out.println("[Add/Update/Delete Instructors]");
				System.out.println("'1': Add a new Instructor		'2': Update an Instructor(Instructor info)");
				System.out.println("'3': Remove an Instructor		'Q'/'q': Go back");
				choice = in.nextLine();
				switch(choice){
				case "1":
					System.out.println("[Add a new Instructor]");
					updateInst(true,99);
					break;
					
				case "2":
					System.out.println("[Update an Instructor]");
					if(listINS.size() == 0){
						System.out.println("No Instructors added yet, returning...");
					}
					else{
						printInst();
						System.out.print("Select an Instructor to update by typing the number in front of their name: ");
						num = in.nextInt();
						if(num >= listINS.size() || num < 0){
							System.out.println("Position does not exist.");
						}
						else{
							updateInst(false,num);
						}
					}
					break;
					
				case "3":
					System.out.println("[Remove an Instructor]");
					if(listINS.size() == 0){
						System.out.println("No Instructors added yet, returning...");
					}
					else{
						printInst();
						System.out.print("Select an Instructor to remove by typing the number in front of their name: ");
						num = in.nextInt();
						if(num >= listINS.size() || num < 0){
							System.out.println("Position does not exist.");
						}
						else{
							listINS.remove(num);
						}
					}
					break;
					
				case "Q": case"q":  break;
				default: System.out.println("Invalid input."); break;
				}
				break;
			
			case "3":  //Add/Update/Delete Student
				System.out.println("[Add/Update/Delete Students]");
				System.out.println("'1': Add a new Student		'2': Update a Student(Student info)");
				System.out.println("'3': Remove a Student		'Q'/'q': Go back");
				choice = in.nextLine();
				switch(choice){
				case "1":
					System.out.println("[Add a new Student]");
					updateStd(true,99);
					break;
					
				case "2":
					System.out.println("[Update a Student]");
					if(listSTD.size() == 0){
						System.out.println("No Students added yet, returning...");
					}
					else{
						printStd();
						System.out.print("Select a Student to update by typing the number in front of their name: ");
						num = in.nextInt();
						if(num >= listSTD.size() || num < 0){
							System.out.println("Position does not exist.");
						}
						else{
							updateStd(false,num);
						}
					}
					break;
					
				case "3":
					System.out.println("[Remove a Student]");
					if(listSTD.size() == 0){
						System.out.println("No Students added yet, returning...");
					}
					else{
						printStd();
						System.out.print("Select a Student to remove by typing the number in front of their name: ");
						num = in.nextInt();
						if(num >= listSTD.size() || num < 0){
							System.out.println("Position does not exist.");
						}
						else{
							listSTD.remove(num);
						}
					}
					break;
					
				case "Q": case"q":  break;
				default: System.out.println("Invalid input."); break;
				}
				break;
				
			case "4":  //Set a Course's grading criteria
				System.out.println("[Set a Course's grading criterias]");
				if(listCOR.size() == 0){
					System.out.println("No courses added yet, returning...");
				}
				else{
					printCor();
					System.out.print("Select a Course to set grading criterias by typing the number in front of it: ");
					num = in.nextInt();
					if(num >= listCOR.size() || num < 0){
						System.out.println("Position does not exist.");
					}
					else{
					setFullScore(num);
					}
				}
				break;
				
			case "5":  //Set an Instructor's teaching courses
				System.out.println("[Set an Instructor's teaching courses]");
				if(listINS.size() == 0){
					System.out.println("No Instructors added yet, returning...");
				}
				else{
					printInst();
					System.out.print("Select an Instructor to add teaching course by typing the number in front of their name: ");
					num = in.nextInt();
					if(num >= listINS.size() || num < 0){
						System.out.println("Position does not exist.");
					}
					else{
						if(listCOR.size() == 0){
							System.out.println("No Courses added yet, returning...");
						}
						else{
							printCor();
							System.out.print("Select a Course to add to the selected Instructor by typing the number in front of it: ");
							int num2 = in.nextInt();
							if(num2 >= listCOR.size() || num2 < 0){
								System.out.println("Position does not exist.");
							}
							else{
								boolean dupe = false;//Flag to check if there is a duplicate
								for(int i=0;i<listINS.get(num).getTeaching().size();i++){
									if(listINS.get(num).getTeaching().get(i).getCourseCode().equals(listCOR.get(num2).getCourseCode())){
										dupe = true;
									}
								}
								if(dupe == true){
									System.out.println("You are already teaching in this course. Returning...");
									dupe = false;
									break;
								}
								RegCourse add = listCOR.get(num2);
								listINS.get(num).getTeaching().add(add);
								System.out.println();
							}
						}
					}
				}
				break;
				
			case "6": //Remove an Instructor's teaching courses
				System.out.println("[Remove an Instructor's teaching courses]");
				if(listINS.size() == 0){
					System.out.println("No Instructors added yet, returning...");
				}
				else{
					printInst();
					System.out.print("Select an Instructor to remove teaching course by typing the number in front of their name: ");
					num = in.nextInt();
					if(num >= listINS.size() || num < 0){
						System.out.println("Position does not exist.");
					}
					else{
						if(listINS.get(num).getTeaching().size() == 0){
							System.out.println("No Courses added yet, returning...");
						}
						else{
							printTeaCor(num);
							System.out.print("Select a Course to remove from the selected Instructor by typing the number in front of it: ");
							int num2 = in.nextInt();
							if(num2 >= listINS.get(num).getTeaching().size() || num2 < 0){
								System.out.println("Position does not exist.");
							}
							else{
								listINS.get(num).getTeaching().remove(num2);
							}
						}
					}
				}
				break;
			
			case "7":  //Add Student's score
				System.out.println("[Add a Student's score]");
				if(listSTD.size() == 0){
					System.out.println("No Students added yet, returning...");
				}
				else{
					printStd();
					System.out.print("Select a Student to add score by typing the number in front of it: ");
					num = in.nextInt();
					if(num >= listSTD.size() || num < 0){
						System.out.println("Position does not exist.");
					}
					else{
						if(listSTD.get(num).getRegisteredCourses().size() == 0){
							System.out.println("No registered courses added yet, returning...");
						}
						else{
							printRegCor(num);
							System.out.print("Select a Course to add score to by typing the number in front of it: ");
							int num2 = in.nextInt();
							if(num2 >= listSTD.get(num).getRegisteredCourses().size() || num2 < 0){
								System.out.println("Position does not exist.");
							}
							else{
							setScore(num,num2);
							}
						}
					}
				}
				break;
			
			case "8":  //Print all Instructors' info
				for(int i=0;i<listINS.size();i++){
					System.out.print("\nInstructor "+(i+1)+": ");
					listINS.get(i).printInfo();
				}
				break;
			
			case "9":  //Print all Students' info
				for(int i=0;i<listSTD.size();i++){
					System.out.print("\nStudent "+(i+1)+": ");
					listSTD.get(i).printInfo();
				}
				break;
			
			case "Q": case"q": return; 
			default: System.out.println("Invalid input."); break;
			}
		}
	}
	
	public static void studentCase(){
		int stdnum = 0, num = 0;
		Scanner in = new Scanner(System.in);
		Scanner inint = new Scanner(System.in);
		String choice = "";
		if(listSTD.size() == 0){
			System.out.println("No Students added yet, returning...");
			return;
		}
		else{
			printStd();
			System.out.print("Select a Student to use functions by typing the number in front of it: ");
			stdnum = inint.nextInt();
			if(stdnum >= listSTD.size() || stdnum < 0){
				System.out.println("Position does not exist.");
			}				
			else{
				while(true){
					System.out.println("\nAvailable functions for Student:");
					System.out.println("'1': Register to the existing Courses			  	'2': See accumulated GPA");
					System.out.println("'3': Evaluate the score needed to get ‘A’			'4': Severe Subjects");
					System.out.println("'5': Search relevant Instructors 				'Q'/'q': Return to main menu");
					choice = in.nextLine();
					switch(choice){
					case "1":	//Register to the existing Courses
						System.out.println("[Register to the existing Courses]");
						if(listCOR.size() == 0){
							System.out.println("No courses added yet, returning...");
						}
						else{
							printCor();
							System.out.print("Select a Course to register by typing the number in front of it: ");
							num = inint.nextInt();
							if(num >= listCOR.size() || num < 0){
								System.out.println("Position does not exist.");
								break;
							}
							else{
								boolean dupe = false;//Flag to check if there is a duplicate
								for(int i=0;i<listSTD.get(stdnum).getRegisteredCourses().size();i++){
									if(listCOR.get(num).getCourseCode().equals(listSTD.get(stdnum).getRegisteredCourses().get(i).getCourseCode())){
										dupe = true;
									}
								}
								if(dupe == true){
									System.out.println("You have already registered in this Course. Returning...");
									dupe = false;
									break;
								}
								else{
									RegCourse add = listCOR.get(num);//Scores must be reset to become incomplete again
									add.setAttendance(-1);
									add.setQuiz(-1);
									add.setProjects(-1);
									add.setMidScore(-1);
									add.setFinalScore(-1);
									listSTD.get(stdnum).getRegisteredCourses().add(add);
								}
							}
						}
						break;
					case "2":	//See accumulated GPA
						System.out.println("[Accumulate GPA] : "+listSTD.get(stdnum).accumGPA()+"\n");
						break;
					case "3":	//Evaluate the score needed to get ‘A’
						if(listSTD.get(stdnum).getRegisteredCourses().size() == 0){
							System.out.println("No courses added yet, returning...");
						}
						else{
							printRegCor(stdnum);
							System.out.print("Select a Registered Course to check how to get 'A' by typing the number in front of it: ");
							num = inint.nextInt();
							if(num >= listCOR.size() || num < 0){
								System.out.println("Position does not exist.");
							}
							else{
								listSTD.get(stdnum).howToGetASubject(listSTD.get(stdnum).getRegisteredCourses().get(num).getCourseCode());
							}
						}
						break;
					case "4":	//Severe Subjects
						listSTD.get(stdnum).severeSubject();
						break;
					case "5":	//Search relevant Instructors
						listSTD.get(stdnum).relevantInstructor(listINS);
						break;
					case "q": case "Q": return;
					default: System.out.println("Invalid input."); break;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		listCOR = new ArrayList<RegCourse>();
		listINS = new ArrayList<Instructor>();
		listSTD = new ArrayList<Student>();
		Scanner in = new Scanner(System.in);
		String choice = "";
		
		choice = "";
		while(true){
			System.out.println("--------------Welcome to ICT-LMS--------------");
			System.out.println("Press 'A'/'a' to use Administrator functions.");
			System.out.println("Press 'S'/'s' to use Student functions.");
			System.out.println("Press 'Q'/'q' to exit the program.");
			choice = in.nextLine();
			switch(choice){
			case "a": case "A": adminCase(); break;
			case "s": case "S": studentCase(); break;
			case "q": case "Q": System.out.println("Exiting program..."); in.close(); return; 
			default: System.out.println("Invalid input."); break;
			}
		}	
	}
}
