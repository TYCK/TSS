package tss_databaseCommunicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import tss_core.Course;
import tss_core.Student;
import tss_timetableProcessor.TimeSlot;
import tss_timetableProcessor.Timetable;

/**
 * 
 * @author Christopher Derrell
 * @author Yerodin Richards
 */
public class DatabaseProtocol
{

	final private String SERVER_IP = "localhost";
	public final static int SUCCESS = 1;
	final int FAILED = 0;
	final private String CHECK_CONNECTION_URL = "http://" + SERVER_IP
			+ "//TSS/appScripts//db_checkConnection.php";
	final private String GET_COURSE_URL = "http://" + SERVER_IP
			+ "//TSS//appScripts//db_getCourse.php";
	final private String SEARCH_COURSE_URL = "http://" + SERVER_IP
			+ "//TSS/appScripts//db_searchCourses.php";
	final private String GET_STUDENT_URL = "http://" + SERVER_IP
			+ "//TSS/appScripts//db_getStudent.php";
	final private String GET_SUBJECTS_URL = "http://" + SERVER_IP
			+ "//TSS/appScripts//db_getSubjects.php";
	final private String SUBMIT_REGISTRATION_URL = "http://" + SERVER_IP
			+ "//TSS/appScripts//db_registration.php";

	private JSONParser jParser = new JSONParser();
	private boolean connected;

	public DatabaseProtocol()
	{
	}

	/**
	 * Method to verify connection to php scripts
	 */
	public int connect()
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		JSONObject jObj = jParser.makeHttpRequest(CHECK_CONNECTION_URL, "GET", params);
		try
		{
			if (jObj.getInt("success") == 1)
			{
				connected = true;
				return SUCCESS;
			} else
			{
				connected = false;
				return FAILED;
			}
		} catch (Exception e)
		{
			connected = false;
			e.printStackTrace();
			return FAILED;
		}
	}

	/**
	 * Method to disconnect connection from php scripts
	 */
	public void disconnect()
	{
		connected = false;
	}

	/**
	 * A method designed to identify if there is a valid connection to the php
	 * scripts.
	 */
	public boolean isConnected()
	{
		return connected;
	}

	public Student getStudent(int ID)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ID", String.valueOf(ID)));
		JSONObject jObj = jParser.makeHttpRequest(GET_STUDENT_URL, "GET", params);
		try
		{
			if (jObj.getInt("success") == SUCCESS)
			{
				JSONObject student = jObj.getJSONArray("info").getJSONObject(0);
				ArrayList<String> coursesPassed = new ArrayList<String>();
				ArrayList<String> coursesRegistered = new ArrayList<String>();

				String rawPassed = student.getString("CoursesPassed");
				Scanner scan = new Scanner(rawPassed);
				while (scan.hasNext())
					coursesPassed.add(scan.next());
				scan.close();
				String rawRegistered = student.getString("CoursesRegistered");
				scan = new Scanner(rawRegistered);
				while (scan.hasNext())
					coursesRegistered.add(scan.next());
				scan.close();
				boolean cleared = false;
				int clrInt = student.getInt("FinanciallyCleared");
				if (clrInt == 1)
					cleared = true;
				return new Student(student.getInt("ID"), null, null, coursesPassed, cleared,
						coursesRegistered);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getAllSubjects()
	{
		ArrayList<String> returnArray = new ArrayList<String>();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		JSONObject jObj = jParser.makeHttpRequest(GET_SUBJECTS_URL, "GET", params);
		try
		{
			if (jObj.getInt("success") == 1)
			{
				JSONArray subjects = jObj.getJSONArray("subjects");
				for (int i = 0; i < subjects.length(); ++i)
				{
					JSONObject subject = subjects.getJSONObject(i);
					returnArray.add(subject.getInt("ID"), subject.getString("Abbreviation") + " - "
							+ subject.getString("Name"));
				}
			} else
				return null;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
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

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (course.getCRN() != -1)
			params.add(new BasicNameValuePair("CRN", String.valueOf(course.getCRN())));
		if (course.getCode() != null)
			params.add(new BasicNameValuePair("Code", course.getCode()));
		if (course.getTitle() != null)
			params.add(new BasicNameValuePair("Title", course.getTitle()));
		params.add(new BasicNameValuePair("Subject", String.valueOf(course.getSubject())));
		JSONObject jObj = jParser.makeHttpRequest(SEARCH_COURSE_URL, "GET", params);
		try
		{
			if (jObj.getInt("success") == 1)
			{
				JSONArray courses = jObj.getJSONArray("courses");
				for (int i = 0; i < courses.length(); ++i)
				{
					JSONObject jsonCourse = courses.getJSONObject(i);
					ArrayList <TimeSlot> slots = new ArrayList<TimeSlot>();
					String slotsString = jsonCourse.getString("Timeslots");
					Scanner scan = new Scanner(slotsString);
					while(scan.hasNext())
					{
						String temp = scan.next()+" "+scan.next()+" "+scan.next();
						slots.add(TimeSlot.parseTimeSlot(temp));
					}
					scan.close();
					ArrayList<String> prereq = new ArrayList<String>();
					String reqString = jsonCourse.getString("Prerequisites");
					scan = new Scanner(reqString);
					while(scan.hasNext())
					{
						prereq.add(scan.next());
					}
					scan.close();
					returnArray.add(new Course(jsonCourse.getInt("CRN"), jsonCourse
							.getString("Title"), jsonCourse.getString("Type"), jsonCourse
							.getInt("Subject"),jsonCourse.getString("Code"),slots,jsonCourse.getInt("Credits"),prereq,jsonCourse.getString("Location")));
				}
			} else
				return null;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return returnArray;
	}
}
