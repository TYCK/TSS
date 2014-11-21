package tss_core;

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
	
	public TimeSlot()
	{
		
	}
	
	/**
	 * Checks if this TimeSlot clashes with the times of another TimeSlot.
	 * @param timeSlot The TimeSlot to compare with.
	 * @return True if TimeSlot has times in common.
	 */
	public boolean clashesWith(TimeSlot timeSlot)
	{
		return !(this.getDay() == timeSlot.getDay() && ((this.beginTime < timeSlot.beginTime && this.endTime < timeSlot.beginTime) ||
			    (this.beginTime > timeSlot.endTime)));
	}
	
	public int getDay()
	{
		return this.day;
	}
	
	public int getSize()
	{
		return endTime-beginTime;
	}
	
	public ArrayList<TimeSlot> split()
	{
		
	}

}
