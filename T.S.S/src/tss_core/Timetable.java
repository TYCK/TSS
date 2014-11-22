package tss_core;

import java.util.ArrayList;

/**
 * 
 * @author Yerodin Richards
 *
 */
public class Timetable
{
	private TimeSlot[][] timeSlots;
	private int startHr, endHr;
	
	/**
	 * 
	 * @param startHr The earliest hour of the day that this timetable can record.
	 * @param endHr The latest hour of the day that this timetable can record.
	 */
	public Timetable(int startHr, int endHr)
	{
		this.startHr = startHr;
		this.endHr = endHr;
		timeSlots = new TimeSlot[6][endHr-startHr];
	}
	
	public TimeSlot[][] getTimeSlots()
	{
		return timeSlots;
	}
	
	public int getStartHr()
	{
		return startHr;
	}
	
	public int getEndHr()
	{
		return endHr;
	}
	
	/**
	 * 
	 * @param timeSlot
	 * @param forceOverwrite If true, if the TimeSlot to add clashes with any other TimeSlot,
	 * the old TimeSlots will be overwritten automatically.
	 * @return Returns the TimeSlot objects that the new TimeSlot clashes with or null.
	 */
	public ArrayList<TimeSlot> addTimeSlot(TimeSlot timeSlot, boolean forceOverwrite)
	{
		ArrayList<TimeSlot> returnArray = new ArrayList<TimeSlot>();
		int day = timeSlot.getDay();
		for(int i = 0; i < timeSlots[day].length;++i)
			if(timeSlot.clashesWith(timeSlots[day][i]))
				returnArray.add(timeSlots[day][i]);
		if(returnArray.size() == 0 || forceOverwrite)
		{
			ArrayList<TimeSlot> slotsToAdd = timeSlot.split();
			for(int i = 0; i < slotsToAdd.size();++i)
			{
				TimeSlot slotToAdd = slotsToAdd.get(i);
				timeSlots[slotToAdd.getDay()][this.getStartHr()-slotToAdd.getBeginTime()] = slotToAdd;
			}
		}
		if(returnArray.size() == 0)
			return null;
		else
			return returnArray;
			
			
				
				
	}
}
