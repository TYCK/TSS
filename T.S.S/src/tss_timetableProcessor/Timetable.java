package tss_timetableProcessor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import tss_GUI.GUIDefs;
import tss_core.Course;

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
				System.out.println(slotToAdd.getBeginTime()+" "+this.getStartHr() +" "+ slotToAdd.getCourse().getCode());
				timeSlots[slotToAdd.getDay()][slotToAdd.getBeginTime()-this.getStartHr()] = slotToAdd;
			}
		}
		if(returnArray.size() == 0)
			return null;
		else
			return returnArray;
	}
	
	public Image generateImage()
	{
		BufferedImage returnImage = new BufferedImage(40+170*6,20+80*(endHr-startHr),BufferedImage.TYPE_INT_RGB);
		Graphics g = returnImage.getGraphics();
		g.setFont(GUIDefs.BASE_HEADING_FONT);

		//Draw Time Column
		g.setColor(GUIDefs.BACKDROP_COLOR);
		g.fillRect(0, 0, 40, 20);	
		g.setColor(Color.WHITE);
		g.drawLine(0, 20-2, 40, 20-2);
		int j = 0;
		for(int i = this.startHr; i <this.endHr;++i)
		{	
			g.setColor(GUIDefs.BACKDROP_COLOR);
			g.fillRect(0, j*80+20, 40, j*80+20+80);
			g.setColor(GUIDefs.BOLD_TEXT_COLOR);
			g.drawString(twenty4To12(i), 2, j*80+35);
			g.setColor(Color.WHITE);
			g.fillRect(0, 20+j*80-2, 40, 2);
//			g.drawLine(0, 20+j*80-2, 40, 20+j*80-2);
			++j;
		}
		
		//Draw Each Day column
		for(int i = 0; i < 6;++i)
		{
			String day = TimeSlot.day(i);
			g.setColor(GUIDefs.BACKDROP_COLOR);
			g.fillRect(i*170+40, 0,170, 20);	
			g.setColor(GUIDefs.BOLD_TEXT_COLOR);
			g.drawString(day, i*170+40+20, 15);
			g.setColor(Color.WHITE);
			g.fillRect(170*i+40, 0, 2, 20);
//			g.drawLine(170*i+40, 0, 170*i+40, 20);
			g.fillRect(170*i+40, 20-2, 170, 2);
//			g.drawLine(170*i+40, 20-2, 170*i+40+170, 20-2);
			
			j=0;
			for(int z= 0; z <(endHr-startHr);++z)
			{
				if(timeSlots[i][z] != null)
				{
					g.setColor(GUIDefs.BACKDROP_COLOR);
					g.fillRect(i*170+40, 80*z+20,170, 80);	
					g.setColor(Color.WHITE);
					g.fillRect(170*i+40, z*80+20, 2, 80);
					g.fillRect(170*i+40, z*80+20+80-2, 170, 2);
					Course course = timeSlots[i][z].getCourse();
					g.setColor(GUIDefs.BOLD_TEXT_COLOR);
					g.drawString(course.getCode()+"-"+course.getType(), i*170+40+3, 80*z+20+15);
					g.drawString(course.getCRN()+" Class", i*170+40+3, 80*z+20+3+25);
				}
				else
				{
					g.setColor(GUIDefs.BACKDROP_COLOR);
					g.fillRect(i*170+40, 80*z+20,170, 80);	
					g.setColor(Color.WHITE);
					g.fillRect(170*i+40, z*80+20, 2, 80);
					for(int a = 0; a < 4; ++ a)
					{
						g.fillRect(170*i+40, z*80+20+80-2-a*20, 170, 2);
					}
				}
			}
		}
		return returnImage;
	}
	
	public String twenty4To12(int twenty4)
	{
		String returnString = "";
		if( twenty4 > 12)
			return (twenty4-12)+"pm";
		else
			return twenty4+"am";
	}
}
