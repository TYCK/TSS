package tss_core;

import java.awt.Dimension;

import javax.swing.JFrame;

import tss_GUI.views.SelectCourseView;

public class TSSCore extends JFrame
{
	final public static Dimension APPLICATION_PREFERRED_SIZE = new Dimension(1280,720);
	
	public TSSCore()
	{
		this.setContentPane(new SelectCourseView());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new TSSCore();
	}
	

}
