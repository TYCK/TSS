package src.tss_databaseCommunicator;

import org.json.JSONObject;

import src.tss_core.*;
import java.util.*;
import java.net.URL;
import org.json.JSONException;
import java.net.URLEncoder;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLConnection;

/**
 * 
 * @author Christopher Derrell
 *
 */
public class DatabaseProtocol {

	String SERVER_IP;
	int SUCCESS;
	boolean connected;
	URL USERINFO_URL;
	URL COURSEINFO_URL = new URL();
	URL REGISTER_URL = new URL();
	URLConnection userInfo;
	ArrayList<Course> Timetable = new ArrayList<Course>();
	String address = "192.168.0.119";//The address of the wamp server

	// Arbitrary Variables, used as placeholders
	Student stud;
	SASData sash;
	Course difficult;

	/*
	 * Placeholder class to hold the information which the Php Data will send
	 * regarding student course in a way that this interface can understand. :)
	 */
	abstract class SASData {

	}

	public DatabaseProtocol() {
		SERVER_IP = "";
	}

	/*
	 * Method to initialize connection to php scripts
	 */
	public int connect(String address) 
	{	
		try
		{
			USERINFO_URL=new URL(address);
			userInfo = USERINFO_URL.openConnection();
			return 0;
		}
		catch (MalformedURLException mue)
		{
			System.out.println("! - Error Occured : URL Creation Failed - !");
			return 1;
		}
		catch (IOException ioe)
		{
			System.out.println("! - Error Occured : Connection Failed - !");
			return 2;
		}
	}

	/*
	 * Method to disconnect connection from php scripts
	 */
	public int disconnect() {
		return 0;
	};

	/*
	 * A method designed to identify if there is a valid connection to the php
	 * scripts.
	 */
	public boolean isConnected() {
		return false;
	};

	/*
	 * Returns a student's information from given id number.
	 */
	
	public Student getStudent(int ID) 
	{
		String idNumber = ""+ID;
		String param;
		param = "id="+URLEncoder.encode(idNumber, "UTF-8");
		switch (connect(address + param))//Sends the IP address of the server along with the parameters of the query to the server.
		{
			case 0:
				userInfo.setRequestProperty("Accept-Charset", "UTF-8");
				InputStream response = userInfo.getInputStream();
			
		}
		
		

		
		return stud;
	}
	
	public Course getCourse(int CRN) 
	{
		return difficult;
	}
	
	/*
	 * Registers a student with course information found in the given timetable
	 */
	public int registerStudent(Timetable time_tab) 
	{
		return 0;
	}

	/*
	 * Converts the message into a format which can be understood by the
	 * DatabaseProtocol.
	 */
	public String decode(SASData sasha) {

		return "";
	}

	/*
	 * Converts the message into a format which can be uploaded back to the PHP
	 * scripts.
	 */
	public SASData encode(String message) {
		return sash;
	}

	/*
	 * Takes many parameters (which will probably be included in class Course)
	 * and uses them to search database for course.
	 */
	public void searchCourse(String subject, int code, String level,
			String campus, String title, int CRN) {

	}
}
