package tss_core;

import java.util.ArrayList;

public class Course
{
    private int Crn;
    private String title;
    private String Subject;
    private int code;
    private int credit;
    private ArrayList <TimeSlot> timeslotlist = new ArrayList <TimeSlot> ();
    private ArrayList<Course> Clist = new ArrayList <Course> ();
    private String Faculty;

    /**
     * @Authur Tevin Anderson 
     * Constructor for objects of class Course
     */
    
    public Course(int CRN, String titl, String subject, int co, ArrayList <TimeSlot> timeslot, int credits, ArrayList <Course> prereq, String faculty )
    {
        Crn = CRN;
        Subject = subject;
        title = titl;
        code = co;
        timeslotlist = timeslot;
        credit = credits;
        Clist = prereq;
        Faculty = faculty;
        
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
    public String gettitle()
    {
        return title;
    }
    
    /**
     * @return  the subject the course falls under 
     */
    public String getsubject()
    {
      return Subject;
    }
    
    /**
     * @return the code  
     */
    public int getCode()
    {
        return code;
    }
    
    /**
     * @return the time slots for the chosen course 
     */

public ArrayList <TimeSlot> gettimeslot()
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
  public ArrayList<Course> getprereq()
    {
        return Clist;
    }
    
    /**
     * @return the faculty the course is from  
     */
    public String getfaculty()
    {
        return Faculty;
    }
}
