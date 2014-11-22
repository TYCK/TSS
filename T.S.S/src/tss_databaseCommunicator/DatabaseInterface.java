package src.tss_databaseCommunicator;

import src.tss_core.*;
import java.util.*;
import java.net.URL;

public class DatabaseInterface {

	String SERVER_IP;
	int SUCCESS;
	boolean connected;
	URL USERINFO_URL,COURSEINFO_URL,REGISTER_URL;
	ArrayList<Course> Timetable =  new ArrayList<Course>();
	
	//Arbitrary Variables, used as placeholders
	int arb_int=0;
	String arb_str="";
	Student stud;
	SASData sash;
	Course difficult;
	
	/*
	 * Placeholder class to hold the information which the Php Data will send regarding student course
	 * in a way that this interface can understand. :)
	 */
	abstract class SASData
	{
	
		
	}
	
	public DatabaseInterface()
	{
		SERVER_IP="";
		
		
	}

	/*
	 * Method to initialize connection to php scripts
	 */
	public int connect(){return 0;};
	/*
	 *  Method to disconnect connection from php scripts
	 */
	public int disconnect(){return 0;};
	
	/*
	 * A method designed to identify if there is a valid connection to the php scripts.
	 */
	public boolean isConnected(){return false;};
	/*
	 * Returns a student's information from  given id number.
	 */
	public Student getStudent(int ID){return stud;};

	/*
	 * Registers a student with course information found in the given timetable
	 */
	public int registerStudent(Timetable time_tab){return 0;};
	
	
	/*
	 * Converts the message into a format which can be understood by the DatabaseProtocol.
	 */
	public String decode(SASData sasha){return "";};

	/*
	 * Converts the message into a format which can be uploaded back to the PHP scripts.
	 */
	public SASData encode(String message) {return sash;};

	
	/*
	 * Takes many parameters (which will probably be included in class Course) and uses them to search database for course.
	 */
	public void searchCourse(String subject,int code,String level,String campus,String title,int CRN) {};
	
	public Course getCourse(int CRN){return difficult;};
}
