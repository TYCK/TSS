package tss_GUI.views.SelectCourseView;

import tss_GUI.GUIDefs;
import tss_GUI.views.FilterAndTimetableView.FilterAndTimetableView;
import tss_core.Course;
import tss_core.TSSCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 
 * @author Yerodin Richards
 *
 */
public class SelectCourseView extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6293736574643564977L;
	private JLabel subjectLabel, courseNumLabel, titleLabel, campusLabel, courseLevelLabel,
			resultsLabel, selectedLabel;
	private JTextField courseNumField, titleField;
	private JList<String> subjectList, campusList, levelList;
	private DefaultListModel<Course> resultsListModel, registeredListModel;
	private JList<Course> resultsList, registeredList;
	private JScrollPane subjectScroll, campusScroll, levelScroll, resultsScroll, registeredScroll;
	private TSSCore tssCore;
	private ArrayList<Course> searchResults;
	private ArrayList<Course> coursesToRegister;

	private JButton searchButton, addButton, removeButton, generateButton;

	private JPanel searchPanel, resultPanel, registeredPanel;

	public SelectCourseView(final TSSCore tssCore, ArrayList<String> subjects, String[] levels,
			String[] campuses)
	{
		this.tssCore = tssCore;
		this.setLayout(new BorderLayout());
		searchResults = new ArrayList<Course>();

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
		courseNumField.setPreferredSize(new Dimension(50, 25));
		titleField = new JTextField(30);
		titleField.setPreferredSize(new Dimension(300, 25));
		ArrayList<String> subList = this.tssCore.getDatabaseProtocol().getAllSubjects();
		subjectList = new JList<String>(subList.toArray(new String[subList.size()]));
		subjectList.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		campusList = new JList<String>(campuses);
		campusList.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		levelList = new JList<String>(levels);
		levelList.setForeground(GUIDefs.COMMON_TEXT_COLOR);

		subjectScroll = new JScrollPane(subjectList);
		subjectScroll.setPreferredSize(new Dimension(200, 100));
		campusScroll = new JScrollPane(campusList);
		campusScroll.setPreferredSize(new Dimension(300, 100));
		levelScroll = new JScrollPane(levelList);
		levelScroll.setPreferredSize(new Dimension(200, 100));
		searchButton = new JButton("Search");
		searchButton.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		searchButton.setBackground(Color.WHITE);

		searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
		searchPanel.setPreferredSize(new Dimension(512, 720));

		subjectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		subjectScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
		courseNumLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		courseNumField.setAlignmentX(Component.CENTER_ALIGNMENT);
		courseNumField.setMaximumSize(new Dimension(searchPanel.getPreferredSize().width, 30));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleField.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleField.setMaximumSize(new Dimension(searchPanel.getPreferredSize().width, 30));
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
		resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
		resultPanel.setPreferredSize(new Dimension(350, 720));
		resultPanel.setMaximumSize(new Dimension(350, 720));
		resultsLabel = new JLabel("Search Results");
		resultsLabel.setForeground(GUIDefs.BOLD_TEXT_COLOR);
		resultsLabel.setFont(GUIDefs.BASE_HEADING_FONT);
		resultsListModel = new DefaultListModel<Course>();
		resultsList = new JList<Course>(resultsListModel);
		resultsList.setCellRenderer(new CourseCellRenderer());
		resultsList.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		resultsScroll = new JScrollPane(resultsList);
		// resultsScroll.setPreferredSize(new Dimension(256,720));
		addButton = new JButton("Add Course");
		addButton.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		addButton.setBackground(Color.WHITE);
		addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		resultPanel.add(resultsLabel);
		resultPanel.add(resultsScroll);
		resultPanel.add(addButton);
		resultPanel.setBackground(GUIDefs.BACKDROP_COLOR);

		registeredPanel = new JPanel();
		registeredPanel.setLayout(new BoxLayout(registeredPanel, BoxLayout.Y_AXIS));
		registeredPanel.setPreferredSize(new Dimension(350, 720));
		selectedLabel = new JLabel("Selected Courses:");
		selectedLabel.setForeground(GUIDefs.BOLD_TEXT_COLOR);
		selectedLabel.setFont(GUIDefs.BASE_HEADING_FONT);
		registeredListModel = new DefaultListModel<Course>();
		registeredList = new JList<Course>(registeredListModel);
		registeredList.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		registeredList.setCellRenderer(new CourseCellRenderer());
		registeredScroll = new JScrollPane(registeredList);
		// registeredScroll.setPreferredSize(new Dimension(256,720));
		removeButton = new JButton("Remove Course");
		removeButton.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		removeButton.setBackground(new Color(255, 255, 255));
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
				ArrayList<Course> courses = new ArrayList<Course>();
				if(registeredListModel.size() > 0)
				{
					for(int i = 0; i < registeredListModel.getSize(); ++i)
						courses.add(registeredListModel.get(i));
					
				}
				tssCore.setContentPane(new FilterAndTimetableView(tssCore, courses));
				tssCore.revalidate();
				tssCore.repaint();
			}

		});

		searchButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				tssCore.setLoading(true);
				if (!subjectList.isSelectionEmpty())
				{
					resultsListModel.clear();

					String title = titleField.getText();
					if (title.equalsIgnoreCase(""))
						title = null;
					String code = courseNumField.getText();
					if (code.equalsIgnoreCase(""))
						code = null;
					ArrayList<Course> courses = tssCore.getDatabaseProtocol().searchCourse(
							new Course(-1, title, null, subjectList.getSelectedIndex(), code, null,
									-1, null, null));
					if (courses != null)
					{
						for (int i = 0; i < courses.size(); ++i)
						{
							Course c = courses.get(i);
							boolean has = false;
							for (int j = 0; j < resultsListModel.size(); ++j)
							{
								Course compare = resultsListModel.get(j);
								if (c.getCode().equalsIgnoreCase(compare.getCode())
										&& c.getType().charAt(0) == compare.getType().charAt(0))
									has = true;
							}
							if (!has)
								resultsListModel.addElement(courses.get(i));
						}
					}
				}
				tssCore.setLoading(false);
			}
		});
		addButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (resultsList.isSelectionEmpty())
					return;
				Course c = resultsListModel.get(resultsList.getSelectedIndex());
				if (!registeredListModel.contains(c))
					registeredListModel.addElement(c);
			}

		});
		removeButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (registeredList.isSelectionEmpty())
					return;
				registeredListModel.remove(registeredList.getSelectedIndex());
			}

		});
		this.add(searchPanel, BorderLayout.WEST);
		this.add(resultPanel, BorderLayout.CENTER);
		this.add(registeredPanel, BorderLayout.EAST);
		this.add(generateButton, BorderLayout.SOUTH);
	}
}
