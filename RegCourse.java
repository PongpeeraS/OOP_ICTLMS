/** Name: Pongpeera Sukasem
 * StudentID: 5988040
 * Section: 1 (Group 1)
 *
 */

/* RegCourse is a class that collect information about registered course of each student.
 * This class extends from the class Course which contain basic course information
 * that are courseCode, courseName, courseCredit, Percentage of grading criteria
 * and Full score of each criteria.
 * 
 * The RegCourse need to collect more specific information about the scores of each student regarding
 * the grading criteria which are quiz, attendance, projects, midScore, finalScore. The detail of these information
 * are provided below.
 * */
public class RegCourse extends Course {
	
	private double quiz;
	private double attendance;
	private double projects;
	private double midScore;
	private double finalScore;
	
	private double accumScore;
	private boolean completedCourse;
	
	//Created variables
	private double fullScore;

	//Constructor to setup an object of a registered course
	public RegCourse(String code, String cname, boolean core, int cCredit){
		//CODE HERE
		super(code,cname,core,cCredit);
		this.setCourseCode(code);
		this.setCourseName(cname);
		this.setCoreCourse(core);
		this.setCourseCredit(cCredit);
	}
	
	//Other relevant methods should be defined here
	
	//Printing Course Information 
	public void printCourseInfo(){
		//CODE HERE
		System.out.println(this.getCourseCode()+" - "+this.getCourseName());
	}

	public void setQuiz(double quiz) {
		this.quiz = quiz;
		if(this.quiz == -1){
			completedCourse = false;
		}
	}

	public void setAttendance(double attendance) {
		this.attendance = attendance;
		if(this.attendance == -1){
			completedCourse = false;
		}
	}

	public void setProjects(double projects) {
		this.projects = projects;
		if(this.attendance == -1){
			completedCourse = false;
		}
	}

	public void setMidScore(double midScore) {
		this.midScore = midScore;
		if(this.midScore == -1){
			completedCourse = false;
		}
	}

	public void setFinalScore(double finalScore) {
		this.finalScore = finalScore;
		if(this.finalScore == -1){
			completedCourse = false;
		}
	}

	public void setAccumScore(double accumScore) {
		this.accumScore = accumScore;
	}
	
	public boolean isCompletedCourse() {
		return completedCourse;
	}

	public void setCompletedCourse(boolean completedCourse) {
		this.completedCourse = completedCourse;
	}
	
	//Created/Overridden Methods
	public double getAccumScore() {
		double attPct=0.0, quizPct=0.0, projPct=0.0, midPct=0.0, finPct=0.0;
		//Each criteria will check whether the percentage or fullScore is 0, or attendance = -1
		//If so, the percent & fullScore will be 0.
		if(this.getAttendancePercent() == 0 || this.getFull_score_attendance() == 0|| this.attendance == -1){
			attPct = 0;
		}
		else{
			attPct = (this.attendance*this.getAttendancePercent())/this.getFull_score_attendance();
		}
		
		if(this.getQuizPercent() == 0 || this.getFull_score_quiz() == 0 || this.quiz == -1){
			quizPct = 0;
		}
		else{
			quizPct = (this.quiz*this.getQuizPercent())/this.getFull_score_quiz();
		}
		
		if(this.getProjPercent() == 0 || this.getFull_score_projects() == 0 || this.projects == -1){
			projPct = 0;
		}
		else{
			projPct = (this.projects*this.getProjPercent())/this.getFull_score_projects();
		}
		
		if(this.getMidtermPercent() == 0 || this.getFull_score_midScore() == 0 || this.midScore == -1){
			midPct = 0;
		}
		else{
			midPct = (this.midScore*this.getMidtermPercent())/this.getFull_score_midScore();
		}
		
		if(this.getFinalPercent() == 0 || this.getFull_score_finalScore() == 0 || this.finalScore == -1){
			finPct = 0;
		}
		else{
			finPct = (this.finalScore*this.getFinalPercent())/this.getFull_score_finalScore();
		}
		
		accumScore = attPct + quizPct + projPct + midPct + finPct;
		return accumScore;
	}

	public double getFullScore() {
		fullScore=(double)this.getAttendancePercent()+(double)this.getQuizPercent()+(double)this.getProjPercent()+(double)this.getMidtermPercent()+(double)this.getFinalPercent();
		//If the percentage or fullScore is 0, or attendance = -1, the combined fullScore will be deducted by (criteria)%
		if(this.getAttendancePercent() == 0 || this.getFull_score_attendance() == 0 || this.attendance == -1){
			fullScore -= (double)this.getAttendancePercent();
		}
		if(this.getQuizPercent() == 0 || this.getFull_score_quiz() == 0 || this.quiz == -1){
			fullScore -= (double)this.getQuizPercent();
		}
		if(this.getProjPercent() == 0 || this.getFull_score_projects() == 0 || this.projects == -1){
			fullScore -= (double)this.getProjPercent();
		}
		if(this.getMidtermPercent() == 0 || this.getFull_score_midScore() == 0 || this.midScore == -1){
			fullScore -= (double)this.getMidtermPercent();
		}
		if(this.getFinalPercent() == 0 || this.getFull_score_finalScore() == 0 || this.finalScore == -1){
			fullScore -= (double)this.getFinalPercent();
		}
		return fullScore;
	}

}
