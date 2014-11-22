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

    public Student(int id, String firstName, String lastName,ArrayList <String> coursePassed, boolean financiallycleared)
    {
    	super(firstName,lastName);
        ID = id;
        coursespassed = coursePassed;
        cleared = financiallycleared;
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
}
