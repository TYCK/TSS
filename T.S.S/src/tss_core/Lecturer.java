package tss_core;

import java.util.ArrayList;

public class Lecturer extends Person 
{
    private ArrayList <Course>  courses= new ArrayList <Course>();
    
    public Lecturer(String firstName, String lastName, ArrayList<Course>  C)
    {
    	super(firstName, lastName);
       courses = C;
    }

    /**
     * @return a list of courses that the lecturer is asigne
     */
    public ArrayList<Course> getCourses()
    {
        return courses;
    }
    
    /**
     * @ the method basically adds courses to lecturer's list
     */
    public void addCourses(Course Core)
    {
        courses.add(Core);
    }
    
}
