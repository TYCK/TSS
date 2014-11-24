package tss_GUI.views.SelectCourseView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import tss_GUI.GUIDefs;
import tss_GUI.LoadingDialog;
import tss_GUI.views.FilterAndTimetableView.FilterAndTimetableView;
import tss_core.Course;
import tss_core.TSSCore;


public class SelectCourseView extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6293736574643564977L;
	private JLabel subjectLabel,courseNumLabel,titleLabel,campusLabel,courseLevelLabel, resultsLabel, selectedLabel;
	private JTextField courseNumField, titleField;
	private JList<String> subjectList, campusList, levelList;
	private JList<Course> resultsList, registeredList;
	private JScrollPane subjectScroll, campusScroll, levelScroll, resultsScroll, registeredScroll;
	private TSSCore tssCore;
	
	private String[] subjects = {"ACCT - Accounting","BIOL - Biology","COMP - Computer Science","CHEM - Chemistry","ECON - Economics",
			"ECNG - Elec & Comp Engineering","ELET - Electronics","INFO - Information Technology",
			"MATH - Mathematics","PHYS - Physics","PSYC - Psychology","SWEN - Software Engineering"};
	private String[] campuses = {"All","Mona","Mona - Bahamas","Mona - Bethlehem Teacher's College"};
	private String[] levels = {"All","Graduate","Undergraduate"};
	private Course[] testResults = {new Course(21548,"Introduction To Net-Centric Computing","M11","Computer Science","COMP2190",null,3,null,null),
									new Course(23423,"Introduction To Software Engineering","M11","Computer Science","COMP2140",null,3,null,null)};
		
	private JButton searchButton, addButton, removeButton, generateButton;
	
	private JPanel searchPanel, resultPanel, registeredPanel;
	public SelectCourseView(TSSCore tssCore)
	{
		this.tssCore = tssCore;
		this.setLayout(new BorderLayout());
		subjectLabel = new JLabel("Subject:");
		subjectLabel.setForeground(GUIDefs.BOLD_TEXT_COLOR);
		subjectLabel.setFont(GUIDefs.BASE_HEADING_FONT);
		courseNumLabel = new JLabel("Course Number:");
		courseNumLabel.setForeground(GUIDefs.BOLD_TEXT_COLOR);
		courseNumLabel.setFont(GUIDefs.BASE_HEADING_FONT);
		titleLabel = new JLabel("Title:");
		titleLabel.setForeground(GUIDefs.BOLD_TEXT_COLOR);
		titleLabel.setFont(GUIDefs.BASE_HEADING_FONT);
		campusLabel = new JLabel("Campus:");
		campusLabel.setForeground(GUIDefs.BOLD_TEXT_COLOR);
		campusLabel.setFont(GUIDefs.BASE_HEADING_FONT);
		courseLevelLabel = new JLabel("Course Level:");
		courseLevelLabel.setForeground(GUIDefs.BOLD_TEXT_COLOR);
		courseLevelLabel.setFont(GUIDefs.BASE_HEADING_FONT);
		courseNumField = new JTextField(4);
		courseNumField.setPreferredSize(new Dimension(50,25));
		titleField = new JTextField(30);
		titleField.setPreferredSize(new Dimension(300,25));
		subjectList = new JList<String>(subjects);
		subjectList.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		campusList = new JList<String>(campuses);
		campusList.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		levelList = new JList<String>(levels);
		levelList.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		
		subjectScroll = new JScrollPane(subjectList);
		subjectScroll.setPreferredSize(new Dimension(200,100));
		campusScroll = new JScrollPane(campusList);
		campusScroll.setPreferredSize(new Dimension(300,100));
		levelScroll = new JScrollPane(levelList);
		levelScroll.setPreferredSize(new Dimension(200,100));
		searchButton = new JButton("Search");
		searchButton.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		searchButton.setBackground(Color.WHITE);
		
		searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel,BoxLayout.Y_AXIS));
		searchPanel.setPreferredSize(new Dimension(512,720));
		
		subjectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		subjectScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
		courseNumLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		courseNumField.setAlignmentX(Component.CENTER_ALIGNMENT);
		courseNumField.setMaximumSize(new Dimension(searchPanel.getPreferredSize().width,30));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleField.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleField.setMaximumSize(new Dimension(searchPanel.getPreferredSize().width,30));
		courseLevelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		levelScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
		campusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		campusScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchPanel.add(subjectLabel);
		searchPanel.add(subjectScroll);
		searchPanel.add(courseNumLabel);
		searchPanel.add(courseNumField);
		searchPanel.add(titleLabel);
		searchPanel.add(titleField);
		searchPanel.add(courseLevelLabel);
		searchPanel.add(levelScroll);
		searchPanel.add(campusLabel);
		searchPanel.add(campusScroll);
		searchPanel.add(searchButton);
		searchPanel.setBackground(GUIDefs.BACKDROP_COLOR);
		
		resultPanel = new JPanel();
		resultPanel.setLayout(new BoxLayout(resultPanel,BoxLayout.Y_AXIS));
		resultPanel.setPreferredSize(new Dimension(350,720));
		resultPanel.setMaximumSize(new Dimension(350,720));
		resultsLabel = new JLabel("Search Results");
		resultsLabel.setForeground(GUIDefs.BOLD_TEXT_COLOR);
		resultsLabel.setFont(GUIDefs.BASE_HEADING_FONT);
		resultsList = new JList<Course>(testResults);
		resultsList.setCellRenderer(new CourseCellRenderer());
		resultsList.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		resultsScroll = new JScrollPane(resultsList);
//		resultsScroll.setPreferredSize(new Dimension(256,720));
		addButton = new JButton("Add Course");
		addButton.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		addButton.setBackground(Color.WHITE);
		addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		resultPanel.add(resultsLabel);
		resultPanel.add(resultsScroll);
		resultPanel.add(addButton);
		resultPanel.setBackground(GUIDefs.BACKDROP_COLOR);
		
		registeredPanel = new JPanel();
		registeredPanel.setLayout(new BoxLayout(registeredPanel,BoxLayout.Y_AXIS));
		registeredPanel.setPreferredSize(new Dimension(350,720));
		selectedLabel = new JLabel("Selected Courses:");
		selectedLabel.setForeground(GUIDefs.BOLD_TEXT_COLOR);
		selectedLabel.setFont(GUIDefs.BASE_HEADING_FONT);
		registeredList = new JList<Course>(testResults);
		registeredList.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		registeredList.setCellRenderer(new CourseCellRenderer());
		registeredScroll = new JScrollPane(registeredList);
//		registeredScroll.setPreferredSize(new Dimension(256,720));
		removeButton = new JButton("Remove Course");
		removeButton.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		removeButton.setBackground(new Color(255,255,255));
		removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		registeredPanel.add(selectedLabel);
		registeredPanel.add(registeredScroll);
		registeredPanel.add(removeButton);
		registeredPanel.setBackground(GUIDefs.BACKDROP_COLOR);
		
		generateButton = new JButton("Generate Timetables");
		generateButton.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		generateButton.setBackground(Color.WHITE);
		generateButton.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{
				removeAll();
				add(new FilterAndTimetableView(tssCore,null));
				revalidate();
				repaint();
			}
				
		});	
		
		this.add(searchPanel,BorderLayout.WEST);
		this.add(resultPanel,BorderLayout.CENTER);
		this.add(registeredPanel,BorderLayout.EAST);
		this.add(generateButton,BorderLayout.SOUTH);
	}
}
