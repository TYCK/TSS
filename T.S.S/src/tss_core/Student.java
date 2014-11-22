package tss_core;

import java.util.ArrayList;

/**
 * @author Tevin Anderson 
 */

public class Student extends Person
{
    private ArrayList <String> coursespassed = new ArrayList <String> ();
    private boolean cleared;
    private int ID;
    private ArrayList <Course> courseregistered = new ArrayList <String> ()

    public Student(int id, String firstName, String lastName,ArrayList <String> coursePassed, boolean financiallycleared), ArrayList <Course> registeredCourses)
    {
    	super(firstName,lastName);
        ID = id;
        coursespassed = coursePassed;
        cleared = financiallycleared;
        courseregistered = registeredCourses;
    }

    /**
     * @return  the courses passed by the student
     */
    public ArrayList <String> getCoursepassed()
    {
        return coursespassed;
    }

    /**
     * @return true  if student is cleared otherwise false
     */
    public boolean isfinclear()
    {
        if (cleared == true){
         return true;
        }
        else{
        return false;
        }
    }

    
    /**
     * @return  the students ID
     */
    public int getid()
    {
        return ID;
    }
    
    public ArrayList <Course> getregisteredc(){
         return courseregistered;
    } 
}
