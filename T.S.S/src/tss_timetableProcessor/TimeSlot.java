package tss_timetableProcessor;

import java.util.ArrayList;
import java.util.Scanner;

import tss_core.Course;

public class TimeSlot
{
	final public static int MONDAY = 0;
	final public static int TUESDAY = 1;
	final public static int WEDNESDAY = 2;
	final public static int THURSDAY = 3;
	final public static int FRIDAY = 4;
	final public static int SATURDAY = 5;

	private int beginTime, endTime;
	private Course course;
	private int day;

	public TimeSlot(int beginTime, int endTime, Course course, int day)
	{
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.course = course;
		this.day = day;

	}

	/**
	 * Checks if this TimeSlot clashes with the times of another TimeSlot.
	 * 
	 * @param timeSlot
	 *            The TimeSlot to compare with.
	 * @return True if TimeSlot has times in common.
	 */
	public boolean clashesWith(TimeSlot timeSlot)
	{
		return (timeSlot != null && this.getDay() == timeSlot.getDay() && (!(this.beginTime < timeSlot.beginTime && this.endTime < timeSlot.beginTime) || !(this.beginTime > timeSlot.endTime)));
	}

	public void setCourse(Course course)
	{
		this.course = course;
	}

	public int getBeginTime()
	{
		return beginTime;
	}

	public int getEndTime()
	{
		return endTime;
	}

	public Course getCourse()
	{
		return this.course;
	}

	public int getDay()
	{
		return this.day;
	}

	public int getSize()
	{
		return endTime - beginTime;
	}

	public ArrayList<TimeSlot> split()
	{
		ArrayList<TimeSlot> returnList = new ArrayList<TimeSlot>();
		for (int i = this.beginTime; i < this.endTime; ++i)
			returnList.add(new TimeSlot(i, i + 1, this.getCourse(), this.getDay()));
		return returnList;
	}

	public static String day(int day)
	{
		switch (day)
		{
		case MONDAY:
			return "Monday";
		case TUESDAY:
			return "Tuesday";
		case WEDNESDAY:
			return "Wednesday";
		case THURSDAY:
			return "Thursday";
		case FRIDAY:
			return "Friday";
		case SATURDAY:
			return "Saturday";
		default:
			return "";
		}
	}

	public static int day(String day)
	{
		if (day.equalsIgnoreCase("Monday"))
			return MONDAY;
		else if (day.equalsIgnoreCase("Tuesday"))
			return TUESDAY;
		else if (day.equalsIgnoreCase("Wednesday"))
			return WEDNESDAY;
		else if (day.equalsIgnoreCase("Thursday"))
			return THURSDAY;
		else if (day.equalsIgnoreCase("Friday"))
			return FRIDAY;
		else if (day.equalsIgnoreCase("Saturday"))
			return SATURDAY;
		else
			return -1;
	}

	public static TimeSlot parseTimeSlot(String string)
	{
		TimeSlot returnSlot = null;
		Scanner scan = new Scanner(string);
		int Day = day(scan.next());
		int beginTime = -1;
		int endTime = -1;
		
		try
		{
			String temp = scan.next();
			if(temp.length() == 5)
			beginTime = Integer.parseInt(String.valueOf(temp.charAt(0))+String.valueOf(temp.charAt(1)));
			else
				beginTime = Integer.parseInt(String.valueOf(temp.charAt(0)));
			temp = scan.next();
			if(temp.length() == 5)
			endTime = Integer.parseInt(String.valueOf(temp.charAt(0))+String.valueOf(temp.charAt(1)));
			else
				endTime = Integer.parseInt(String.valueOf(temp.charAt(0)));
			if(temp.length() == 5)
			{
				if(Integer.parseInt(String.valueOf(temp.charAt(3))+String.valueOf(temp.charAt(4))) > 0)
					++endTime;
			}
			else if(Integer.parseInt(String.valueOf(temp.charAt(2))+String.valueOf(temp.charAt(3))) > 0)
				++endTime;
			returnSlot = new TimeSlot(beginTime,endTime,null,Day);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if (Day == -1)
		{
			scan.close();
			return null;
		}
		scan.close();
		return returnSlot;
	}

}
