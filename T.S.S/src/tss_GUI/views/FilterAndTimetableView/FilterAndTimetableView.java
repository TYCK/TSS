package tss_GUI.views.FilterAndTimetableView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import tss_GUI.GUIDefs;
import tss_GUI.views.SelectCourseView.SelectCourseView;
import tss_core.Course;
import tss_core.TSSCore;

public class FilterAndTimetableView extends JPanel
{
	private TSSCore tssCore;
	private JPanel topPanel;
	private TimeTablePane timetablePanel;
	private JPanel lowerButtonPanel;

	private JLabel filterLabel, errorLabel;
	private JScrollPane filterScroll, errorScroll;
	private JButton backButton, addFilterButton, deleteFilterButton,
			requestOverrideButton, downloadButton, registerButton;


	String[] sampleFilters = { "Time Filter1 - none before 9AM on TUE,WED, THU " };
	
	private  AddFilterDialogPanel addFilterDialogPanel = new AddFilterDialogPanel();

	public FilterAndTimetableView(TSSCore tssCore, ArrayList<Course> courses)
	{
		this.tssCore = tssCore;
		backButton = new JButton("Back To Select Courses");
		backButton.setPreferredSize(new Dimension(200, 100));
		backButton.setBackground(Color.white);
		backButton.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		filterLabel = new JLabel("Filters");
		filterLabel.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		filterScroll = new JScrollPane(new JList<String>(sampleFilters));
		filterScroll.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		filterScroll.setPreferredSize(new Dimension(450, 70));
		addFilterButton = new JButton("Add Filter");
		addFilterButton.setBackground(Color.white);	
		addFilterButton.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		addFilterButton.setPreferredSize(new Dimension(225, 30));
		deleteFilterButton = new JButton("Delete Filter");
		deleteFilterButton.setBackground(Color.white);
		deleteFilterButton.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		deleteFilterButton.setPreferredSize(new Dimension(225, 30));
		errorLabel = new JLabel("Errors");
		errorLabel.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		errorScroll = new JScrollPane();
		errorScroll.setPreferredSize(new Dimension(590, 70));
		requestOverrideButton = new JButton("Request Override");
		requestOverrideButton.setBackground(Color.white);
		requestOverrideButton.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		requestOverrideButton.setPreferredSize(new Dimension(300, 30));

		topPanel = new JPanel();
		topPanel.add(backButton);
		topPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
				Color.white, Color.white));
		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(new BorderLayout());
		filterPanel.add(filterLabel, BorderLayout.NORTH);
		filterPanel.add(filterScroll, BorderLayout.CENTER);
		JPanel filterButtonPanel = new JPanel();
		filterButtonPanel.add(addFilterButton);
		filterButtonPanel.add(deleteFilterButton);
		filterPanel.add(filterButtonPanel, BorderLayout.SOUTH);
		topPanel.add(filterPanel);
		JPanel errorPanel = new JPanel();
		errorPanel.setLayout(new BorderLayout());
		errorPanel.add(errorLabel, BorderLayout.NORTH);
		errorPanel.add(errorScroll, BorderLayout.CENTER);
		errorPanel.add(requestOverrideButton, BorderLayout.SOUTH);
		topPanel.add(errorPanel);
		topPanel.setBackground(GUIDefs.BACKDROP_COLOR);

		timetablePanel = new TimeTablePane();
		timetablePanel.setBackground(GUIDefs.BACKDROP_COLOR);

		registerButton = new JButton("Register Selected Timetable");
		registerButton.setBackground(Color.white);
		registerButton.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		registerButton.setSize(new Dimension(TSSCore.APPLICATION_PREFERRED_SIZE.width,30));
		
		lowerButtonPanel = new JPanel();
		lowerButtonPanel.setLayout(new BorderLayout());
		downloadButton = new JButton("Download Selected Timetable");
		downloadButton.setBackground(Color.white);
		downloadButton.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		lowerButtonPanel.add(downloadButton,BorderLayout.NORTH);
		lowerButtonPanel.add(registerButton,BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(timetablePanel, BorderLayout.CENTER);
		this.add(lowerButtonPanel, BorderLayout.SOUTH);
		
		addFilterButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				JDialog addDialog = new JDialog();
				addDialog.add(addFilterDialogPanel);
				addDialog.setModal(true);
				addDialog.pack();
				addDialog.setLocationRelativeTo(null);
				addDialog.setTitle("Add Filter");
				addDialog.setVisible(true);
			}
		}
		);

		backButton.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{
				removeAll();
				add(new SelectCourseView(tssCore));
				revalidate();
				repaint();
			}

		});
	}
	
}
