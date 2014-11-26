package tss_core;

import java.util.ArrayList;

import tss_timetableProcessor.TimeSlot;

public class Course
{
    private int Crn;
    private String title;
    private int Subject;
    private String code;
    private int credit;
    private ArrayList <TimeSlot> timeslotlist = new ArrayList <TimeSlot> ();
    private ArrayList<String> prereqlist = new ArrayList <String> ();
    private String location;
    private String type;
    

    /**
     * @Author Tevin Anderson 
     * Constructor for objects of class Course
     */
    
    public Course(int CRN, String Title,String Type, int subject, String Code, ArrayList <TimeSlot> timeslot, int credits, ArrayList<String> prereq, String Location)
    {
        Crn = CRN;
        Subject = subject;
        title = Title;
        code = Code;
        if(timeslot != null)
        for(int i = 0; i < timeslot.size(); ++i)
        	timeslot.get(i).setCourse(this);
        timeslotlist = timeslot;
        credit = credits;
        prereqlist = prereq;
        location = Location;
        type = Type;
        
        }

    /**
     * @return the course registration number 
     */
    public int getCRN()
    {
        return Crn;
    }
    
    /**
     * @return  the title of the course 
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * @return  the subject the course falls under 
     */
    public int getSubject()
    {
      return Subject;
    }
    
    /**
     * @return the code  
     */
    public String getCode()
    {
        return code;
    }
    
    /**
     * @return the time slots for the chosen course 
     */

public ArrayList <TimeSlot> getTimeslot()
    {
        return timeslotlist;
    }
    
    /**
     * @return the Credits allocated to the course
     */
    public int getCredit()
    {
        return credit;
    }
    
    /**
     * @return the prerequisite courses of the desired course
     */
  public ArrayList<String> getPrereq()
    {
        return prereqlist;
    }
    
  public String getLocation(){
	  return location;
	  
  }
 
  public String getType(){
	  return type;
	  
  }
}
