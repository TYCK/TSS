package tss_GUI.views;
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

import TSS.TSS;


public class SelectCourseView extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6293736574643564977L;
	private JLabel subjectLabel,courseNumLabel,titleLabel,campusLabel,courseLevelLabel, resultsLabel, selectedLabel;
	private JTextField courseNumField, titleField;
	private JList<String> subjectList, campusList, levelList, resultsList, registeredList;
	private JScrollPane subjectScroll, campusScroll, levelScroll, resultsScroll, registeredScroll;
	
	private String[] subjects = {"ACCT - Accounting","BIOL - Biology","COMP - Computer Science","CHEM - Chemistry","ECON - Economics",
			"ECNG - Elec & Comp Engineering","ELET - Electronics","INFO - Information Technology",
			"MATH - Mathematics","PHYS - Physics","PSYC - Psychology","SWEN - Software Engineering"};
	private String[] campuses = {"All","Mona","Mona - Bahamas","Mona - Bethlehem Teacher's College"};
	private String[] levels = {"All","Graduate","Undergraduate"};
	private String[] testResults = {"COMP1126 - Lecture","COMP1126 - Lab","COMP1126 - Tutorial",
			"COMP1127 - Lecture","COMP1127 - Lab","COMP1127 - Tutorial",
			"COMP1161 - Lecture","COMP1127 - Lab","COMP1127 - Tutorial",
			"COMP1210 - Lecture","COMP1210 - Tutorial","COMP1220 - Lecture","COMP1220 - Tutorial",
			"COMP2140 - Lecture","COMP2140 - Tutorial","COMP2190 - Lecture","COMP2190 - Tutorial"};
		
	private JButton searchButton, addButton, removeButton, generateButton;
	
	private JPanel searchPanel, resultPanel, registeredPanel;
	public SelectCourseView()
	{
		this.setLayout(new BorderLayout());
		subjectLabel = new JLabel("Subject:");
		subjectLabel.setForeground(TSS.textColor);
		courseNumLabel = new JLabel("Course Number:");
		courseNumLabel.setForeground(TSS.textColor);
		titleLabel = new JLabel("Title:");
		titleLabel.setForeground(TSS.textColor);
		campusLabel = new JLabel("Campus:");
		campusLabel.setForeground(TSS.textColor);
		courseLevelLabel = new JLabel("Course Level:");
		courseLevelLabel.setForeground(TSS.textColor);
		courseNumField = new JTextField(4);
		courseNumField.setPreferredSize(new Dimension(50,25));
		titleField = new JTextField(30);
		titleField.setPreferredSize(new Dimension(300,25));
		subjectList = new JList<String>(subjects);
		subjectList.setForeground(TSS.textColor);
		campusList = new JList<String>(campuses);
		campusList.setForeground(TSS.textColor);
		levelList = new JList<String>(levels);
		levelList.setForeground(TSS.textColor);
		
		subjectScroll = new JScrollPane(subjectList);
		subjectScroll.setPreferredSize(new Dimension(200,100));
		campusScroll = new JScrollPane(campusList);
		campusScroll.setPreferredSize(new Dimension(300,100));
		levelScroll = new JScrollPane(levelList);
		levelScroll.setPreferredSize(new Dimension(200,100));
		searchButton = new JButton("Search");
		searchButton.setForeground(TSS.textColor);
		searchButton.setBackground(new Color(255,255,255));
		
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
		searchPanel.setBackground(TSS.BACKDROP_COLOR);
		
		resultPanel = new JPanel();
		resultPanel.setLayout(new BoxLayout(resultPanel,BoxLayout.Y_AXIS));
		resultPanel.setPreferredSize(new Dimension(350,720));
		resultPanel.setMaximumSize(new Dimension(350,720));
		resultsLabel = new JLabel("Search Results");
		resultsLabel.setForeground(TSS.textColor);
		resultsList = new JList<String>(testResults);
		resultsList.setForeground(TSS.textColor);
		resultsScroll = new JScrollPane(resultsList);
//		resultsScroll.setPreferredSize(new Dimension(256,720));
		addButton = new JButton("Add Course");
		addButton.setForeground(TSS.textColor);
		addButton.setBackground(new Color(255,255,255));
		addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		resultPanel.add(resultsLabel);
		resultPanel.add(resultsScroll);
		resultPanel.add(addButton);
		resultPanel.setBackground(TSS.BACKDROP_COLOR);
		
		registeredPanel = new JPanel();
		registeredPanel.setLayout(new BoxLayout(registeredPanel,BoxLayout.Y_AXIS));
		registeredPanel.setPreferredSize(new Dimension(350,720));
		selectedLabel = new JLabel("Selected Courses:");
		selectedLabel.setForeground(TSS.textColor);
		registeredList = new JList<String>(testResults);
		registeredList.setForeground(TSS.textColor);
		registeredScroll = new JScrollPane(registeredList);
//		registeredScroll.setPreferredSize(new Dimension(256,720));
		removeButton = new JButton("Remove Course");
		removeButton.setForeground(TSS.textColor);
		removeButton.setBackground(new Color(255,255,255));
		removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		registeredPanel.add(selectedLabel);
		registeredPanel.add(registeredScroll);
		registeredPanel.add(removeButton);
		registeredPanel.setBackground(TSS.BACKDROP_COLOR);
		
		generateButton = new JButton("Generate Timetables");
		generateButton.setForeground(TSS.textColor);
		generateButton.setBackground(Color.white);
		generateButton.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{
				removeAll();
				add(new FilterAndTimetableView());
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
