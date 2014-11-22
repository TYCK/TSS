package tss_core;


public class Person
{
   private String firstname;
   private String lastname;
   
   
    public Person(String f, String l)
    {
       firstname = f;
       lastname = l;
    }

    
    public String getfirstname()
    {
       return firstname;
    }
    
    
    public String getlastname()
    {
       return lastname;
    }
   
    
    public void setfirstname(String fname)
    {
       firstname = fname;
    }
    
    
    public void setlastname(String lname)
    {
       lname = lastname;
    }
}

