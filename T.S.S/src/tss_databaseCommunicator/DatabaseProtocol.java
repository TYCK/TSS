package tss_databaseCommunicator;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import tss_core.Course;
import tss_core.Student;
import tss_core.Timetable;

/**
 * 
 * @author Christopher Derrell
 * @author Yerodin Richards
 */
public class DatabaseProtocol
{

	final private String SERVER_IP = "";
	final int SUCCESS = 1;
	private boolean connected;
	URL USERINFO_URL;
	// URL COURSEINFO_URL = new URL();
	// URL REGISTER_URL = new URL();


	public DatabaseProtocol()
	{
	}

	/*
	 * Method to initialize connection to php scripts
	 */
	public int connect(String address)
	{
		return 0;
	}

	/*
	 * Method to disconnect connection from php scripts
	 */
	public int disconnect()
	{
		return 0;
	};

	/*
	 * A method designed to identify if there is a valid connection to the php
	 * scripts.
	 */
	public boolean isConnected()
	{
		return connected;
	};

	/*
	 * Returns a student's information from given id number.
	 */

	public Student getStudent(int ID)
	{
		// String idNumber = ""+ID;
		// String param;
		// param = "id="+URLEncoder.encode(idNumber, "UTF-8");
		// switch (connect(address + param))//Sends the IP address of the server
		// along with the parameters of the query to the server.
		// {
		// case 0:
		// userInfo.setRequestProperty("Accept-Charset", "UTF-8");
		// InputStream response = userInfo.getInputStream();
		//
		// }

		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getAllSubjects()
	{
		ArrayList<String> returnArray = new ArrayList<String>();
		returnArray.add("ACCT - Accounting");
		returnArray.add("BIOL - Biology");
		returnArray.add("COMP - Computer Science");
		returnArray.add("CHEM - Chemistry");
		returnArray.add("ELET - Electronics");
		returnArray.add("ECON - Economics");
		returnArray.add("ECNG - Elec & Comp Engineering");
		returnArray.add("INFO - Information Technology");
		returnArray.add("MATH - Mathematics");
		returnArray.add("PHYS - Physics");
		returnArray.add("PSYC - Psychology");
		returnArray.add("SWEN - Software Engineering");
		//TEST VALUE UNTIL IMPLEMENTED
		return returnArray;
	}
	
	/**
	 * 
	 * @param CRN
	 * @return
	 */
	public Course getCourse(int CRN)
	{
		return null;
	}

	/**
	 * Registers a student with course information found in the given timetable
	 */
	public int registerStudent(Student student, Timetable timetable)
	{
		return 0;
	}



	/**
	 * Searches the database from the partial course information stored in a
	 * course object.
	 * 
	 * @author Yerodin Richards
	 * @return An ArrayList of courses that are similar to the partial course
	 *         information.
	 */
	public ArrayList<Course> searchCourse(Course course)
	{
		ArrayList<Course> returnArray = new ArrayList<Course>();
		returnArray.add(new Course(21548,"Introduction To Net-Centric Computing","M11","Computer Science","COMP2190",null,3,null,null));
		returnArray.add(new Course(23423,"Introduction To Software Engineering","M11","Computer Science","COMP2140",null,3,null,null));
		//TEST VALUE UNTIL IMPLEMENTED
		return returnArray;
				

	}
}
