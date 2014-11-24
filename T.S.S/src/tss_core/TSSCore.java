package tss_core;

import java.awt.Dimension;

import javax.swing.JFrame;

import tss_GUI.LoadingDialog;
import tss_GUI.views.SelectCourseView.SelectCourseView;
import tss_databaseCommunicator.DatabaseProtocol;

public class TSSCore extends JFrame
{
	//FOR TESTING ONLY
	final static int ID = 620063216;
	
	private static Student student;
	final static public Dimension APPLICATION_PREFERRED_SIZE = new Dimension(1280,720);
	private DatabaseProtocol databaseProtocol = new DatabaseProtocol();
	final private LoadingDialog LOADING_DIALOG = new LoadingDialog();
	private boolean loading = false;
	
	public TSSCore()
	{
		this.setContentPane(new SelectCourseView(this));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(APPLICATION_PREFERRED_SIZE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.student = databaseProtocol.getStudent(ID);
	}
	
	public static void main(String[] args)
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
	

}
