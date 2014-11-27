package tss_core;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tss_GUI.LoadingDialog;
import tss_GUI.views.SelectCourseView.SelectCourseView;
import tss_databaseCommunicator.DatabaseProtocol;
import tss_timetableProcessor.TimetableGenerator;

public class TSSCore extends JApplet
{
	//FOR TESTING ONLY
	final static int ID = 620063216;
	
	private static Student student;
	private static ArrayList<String> subjectList;
	final static public Dimension APPLICATION_PREFERRED_SIZE = new Dimension(1280,800);
	private DatabaseProtocol databaseProtocol;
	final private LoadingDialog LOADING_DIALOG = new LoadingDialog(this);
	private boolean loading = false;
	private SelectCourseView selectCourseView;
	final static String[] COURSE_LEVELS = {"All","Graduate","Undergraduate"};
	final static String[] CAMPUSES = {"All","Mona","Mona - Bahamas","Mona - Bethlehem Teacher's College"};
	
	public TSSCore()
	{
		this.setLoading(true);
		this.databaseProtocol = new DatabaseProtocol();
		databaseProtocol.connect();
		if(!databaseProtocol.isConnected())
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database", "Error", JOptionPane.ERROR_MESSAGE);
			System.out.println("error");
			System.exit(0);
		}
		TSSCore.student = databaseProtocol.getStudent(ID);
		TSSCore.subjectList = databaseProtocol.getAllSubjects();
			
		selectCourseView = new SelectCourseView(this,databaseProtocol.getAllSubjects(),COURSE_LEVELS,CAMPUSES);
		this.setContentPane(selectCourseView);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(APPLICATION_PREFERRED_SIZE);
//		this.setResizable(false);
//		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setLoading(false);
	}
	
	public void init()
	{
		new TSSCore();
	}
	
	public DatabaseProtocol getDatabaseProtocol()
	{
		return databaseProtocol;
	}
	
	public void setLoading(boolean loading)
	{
		if(loading)
		{
			this.LOADING_DIALOG.setVisible(true);
			this.loading = true;
			this.setFocusable(false);
			
		}
		else
		{
			this.loading = true;
			this.LOADING_DIALOG.setVisible(false);
			this.setFocusable(true);
		}
	}
	
	public void setLoadProgress(int progress)
	{
		this.LOADING_DIALOG.setLoadingProgress(progress);
	}
	
	public boolean isLoading()
	{
		return loading;
	}
	
	public SelectCourseView getSelectCourseView()
	{
		return selectCourseView;
	}
	
	public static ArrayList<String> getSubjectList()
	{
		return subjectList;
	}
	public static String SubjectFromInt(int ID, boolean discipline)
	{
		String toReturn = "";
		if(ID >= subjectList.size())
			return null;
		toReturn = subjectList.get(ID);
		if(discipline)
			return toReturn.substring(0, 4);
		else
			return toReturn;
	}
	

}
