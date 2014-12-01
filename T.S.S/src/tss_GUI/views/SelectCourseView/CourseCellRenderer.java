package tss_GUI.views.SelectCourseView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import tss_GUI.GUIDefs;
import tss_core.Course;

/**
 * 
 * @author Yerodin Richards
 *
 */
public class CourseCellRenderer implements ListCellRenderer
{

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus)
	{
		Course course = (Course) value;
		JLabel title = new JLabel(course.getTitle());
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(GUIDefs.BOLD_TEXT_COLOR);
		title.setFont(GUIDefs.BASE_HEADING_FONT);
		JLabel code = new JLabel(course.getCode());
		code.setHorizontalAlignment(SwingConstants.LEFT);
		code.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		code.setFont(GUIDefs.BOLD_COMMON_FONT);
		JLabel credits = new JLabel(course.getCredit()+" Credits");
		credits.setHorizontalAlignment(SwingConstants.RIGHT);
		credits.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		credits.setFont(GUIDefs.BOLD_COMMON_FONT);
		JLabel type = null;
		char cType = course.getType().toLowerCase().charAt(0);
		if(cType == 'm')
			type = new JLabel("Lecture");
		else if(cType == 'b')
			type = new JLabel("Lab");
		else if(cType == 't')
			type = new JLabel("Tutorial");
		else
			type = new JLabel("Unkown");
		type.setHorizontalAlignment(SwingConstants.LEFT);
		type.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		type.setFont(GUIDefs.BOLD_COMMON_FONT);
		JPanel toRender = new JPanel();
		toRender.setLayout(new BorderLayout());
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(2,2));
		subPanel.add(code);
		subPanel.add(credits);
		subPanel.add(type);
		toRender.add(title,BorderLayout.NORTH);
		toRender.add(subPanel, BorderLayout.CENTER);
		toRender.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
		if(isSelected)
		{
			toRender.setBackground(GUIDefs.LIST_SELECTED_COLOR);
			subPanel.setBackground(GUIDefs.LIST_SELECTED_COLOR);
			title.setForeground(Color.WHITE);
			code.setForeground(Color.WHITE);
			credits.setForeground(Color.WHITE);
			type.setForeground(Color.WHITE);
		}
		
		return toRender;
		
	}

}
