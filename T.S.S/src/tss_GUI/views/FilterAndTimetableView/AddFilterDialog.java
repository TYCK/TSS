package tss_GUI.views.FilterAndTimetableView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tss_GUI.GUIDefs;
import tss_timetableProcessor.Filter;

public class AddFilterDialog extends JDialog
{
	private Filter filter;
	private JPanel topPanel, centerPanel, bottomPanel;
	final private String[] types = { "Time Filter", "Course Filter" };
	final private String[] tods = { "AM", "PM" };
	private JLabel filterTypeLabel, noneBeforeLabel, noneAfterLabel,
			noneBetweenLabel, andLabel, onDaysLabel, monLabel, tueLabel,
			wedLabel, thuLabel, friLabel, satLabel, limitGapLabel;
	private JCheckBox noneBeforeCheck, noneAfterCheck, noneBetweenCheck,
			limitGapCheck, monCheck, tueCheck, wedCheck, thuCheck, friCheck,
			satCheck;
	private JComboBox<String> typeComboBox, todBefore, todAfter, todBetween1,
			todBetween2;
	private JTextField beforeHr, beforeMin, afterHr, afterMin, betweenHr1,
			betweenMin1, betweenHr2, betweenMin2, limitGap;
	private JButton addButton;
	private JPanel content;

	public AddFilterDialog()
	{
		content = new JPanel();
		content.setLayout(new BorderLayout());
		initUI();
		placeUI();

		addButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Filter f = new Filter('t');
				f.setDays(monCheck.isSelected(), tueCheck.isSelected(),
						wedCheck.isSelected(), thuCheck.isSelected(),
						friCheck.isSelected(), satCheck.isSelected());
				int beforeh = -1, beforem = -1;
				int afterh = -1, afterm = -1;
				int between1h = -1, between1m = -1;
				int between2h = -1, between2m = -1;
				int limit = -1;
				boolean error = false;
				if (noneBeforeCheck.isSelected())
				{
					try
					{
						beforeh = Integer.parseInt(beforeHr.getText());
						beforeHr.setBackground(Color.white);
					} catch (Exception ee)
					{
						beforeHr.setBackground(Color.red);
						error = true;
					}
					try
					{
						beforem = Integer.parseInt(beforeMin.getText());
						beforeMin.setBackground(Color.white);
					} catch (Exception ee)
					{
						beforeMin.setBackground(Color.red);
						error = true;
					}
				}
				if (noneAfterCheck.isSelected())
				{

					try
					{
						afterh = Integer.parseInt(afterHr.getText());
						afterHr.setBackground(Color.white);
					} catch (Exception ee)
					{
						afterHr.setBackground(Color.red);
						error = true;
					}
					try
					{
						afterm = Integer.parseInt(afterMin.getText());
						afterMin.setBackground(Color.white);
					} catch (Exception ee)
					{
						afterMin.setBackground(Color.red);
						error = true;
					}
				}
				if (noneBetweenCheck.isSelected())
				{
					try
					{
						between1h = Integer.parseInt(betweenHr1.getText());
						betweenHr1.setBackground(Color.white);
					} catch (Exception ee)
					{
						betweenHr1.setBackground(Color.red);
						error = true;
					}
					try
					{
						between1m = Integer.parseInt(betweenMin1.getText());
						betweenMin1.setBackground(Color.white);
					} catch (Exception ee)
					{
						betweenMin1.setBackground(Color.red);
						error = true;
					}
					try
					{
						between2h = Integer.parseInt(betweenHr2.getText());
						betweenHr2.setBackground(Color.white);
					} catch (Exception ee)
					{
						betweenHr2.setBackground(Color.red);
						error = true;
					}
					try
					{
						between2m = Integer.parseInt(betweenMin2.getText());
						betweenMin2.setBackground(Color.white);
					} catch (Exception ee)
					{
						betweenMin2.setBackground(Color.red);
						error = true;
					}
				}
				if (limitGapCheck.isSelected())
				{
					try
					{
						limit = Integer.parseInt(limitGap.getText());
						limitGap.setBackground(Color.white);
					} catch (Exception ee)
					{
						limitGap.setBackground(Color.red);
						error = true;
					}
				}
				if(!monCheck.isSelected() && !tueCheck.isSelected() && !wedCheck.isSelected() && !thuCheck.isSelected() && !friCheck.isSelected()
						&& !satCheck.isSelected())
				{
					error = true;
					monCheck.setBackground(Color.red);
					tueCheck.setBackground(Color.red);
					wedCheck.setBackground(Color.red);
					thuCheck.setBackground(Color.red);
					friCheck.setBackground(Color.red);
					satCheck.setBackground(Color.red);
				}
				else
				{
					monCheck.setBackground(Color.WHITE);
					tueCheck.setBackground(Color.WHITE);
					wedCheck.setBackground(Color.WHITE);
					thuCheck.setBackground(Color.WHITE);
					friCheck.setBackground(Color.WHITE);
					satCheck.setBackground(Color.WHITE);
				}
				if (!error)
				{
					if (afterm > 0)
						++afterh;
					f.setNoneAfter(noneAfterCheck.isSelected(), afterh);
					f.setNoneBefore(noneBeforeCheck.isSelected(), beforeh);
					if (between1m > 0)
						++between1h;
					f.setNoneBetween(noneBetweenCheck.isSelected(), between1h,
							between2h);
					f.setLimit(limitGapCheck.isSelected(), limit);
					if(f != null &&  f.getAfter() == -1 && f.getBefore() == -1 && f.getBeteween() == null && f.getGapLimit() == -1)
						f=null;
						
					filter = f;
					setVisible(false);
				}
			}

		});
		this.add(content);
		this.setModal(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setTitle("Add Filter");
	}

	public void initUI()
	{
		filterTypeLabel = new JLabel("Filter Type");
		noneBeforeLabel = new JLabel("None Before");
		noneAfterLabel = new JLabel("None After");
		noneBetweenLabel = new JLabel("None Between");
		limitGapLabel = new JLabel("Limit Gaps Between Classses");
		filterTypeLabel.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		noneBeforeLabel.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		noneAfterLabel.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		noneBetweenLabel.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		limitGapLabel.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		onDaysLabel = new JLabel("On Days");
		onDaysLabel.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		andLabel = new JLabel("And");
		andLabel.setAlignmentX(CENTER_ALIGNMENT);
		andLabel.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		monLabel = new JLabel("Monday");
		tueLabel = new JLabel("Tuesday");
		wedLabel = new JLabel("Wednesday");
		thuLabel = new JLabel("Thursday");
		friLabel = new JLabel("Friday");
		satLabel = new JLabel("Saturday");
		monLabel.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		tueLabel.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		wedLabel.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		thuLabel.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		friLabel.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		satLabel.setForeground(GUIDefs.COMMON_TEXT_COLOR);
		noneBeforeCheck = new JCheckBox();
		noneAfterCheck = new JCheckBox();
		noneBetweenCheck = new JCheckBox();
		limitGapCheck = new JCheckBox();
		monCheck = new JCheckBox();
		tueCheck = new JCheckBox();
		wedCheck = new JCheckBox();
		thuCheck = new JCheckBox();
		friCheck = new JCheckBox();
		satCheck = new JCheckBox();
		typeComboBox = new JComboBox<String>(types);
		typeComboBox.setMaximumSize(new Dimension(100, 20));
		typeComboBox.setAlignmentX(CENTER_ALIGNMENT);
		typeComboBox.setBackground(Color.white);
		beforeHr = new JTextField(2);
		beforeMin = new JTextField(2);
		afterHr = new JTextField(2);
		afterMin = new JTextField(2);
		betweenHr1 = new JTextField(2);
		betweenMin1 = new JTextField(2);
		betweenHr2 = new JTextField(2);
		betweenMin2 = new JTextField(2);
		limitGap = new JTextField(2);
		todBefore = new JComboBox<String>(tods);
		todAfter = new JComboBox<String>(tods);
		todBetween1 = new JComboBox<String>(tods);
		todBetween2 = new JComboBox<String>(tods);
		todBefore.setBackground(Color.white);
		todAfter.setBackground(Color.white);
		todBetween1.setBackground(Color.white);
		todBetween2.setBackground(Color.white);

		filterTypeLabel.setAlignmentX(CENTER_ALIGNMENT);
		onDaysLabel.setAlignmentX(CENTER_ALIGNMENT);

		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		centerPanel = new JPanel();
		bottomPanel = new JPanel();

		topPanel.setAlignmentX(CENTER_ALIGNMENT);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		bottomPanel.setAlignmentX(CENTER_ALIGNMENT);

		addButton = new JButton("Add Filter");
		addButton.setBackground(Color.white);
		addButton.setForeground(GUIDefs.COMMON_TEXT_COLOR);
	}

	public void placeUI()
	{
		topPanel.add(filterTypeLabel);
		topPanel.add(typeComboBox);

		JPanel noneBeforePanel = new JPanel();
		noneBeforePanel.add(beforeHr);
		noneBeforePanel.add(new JLabel(":"));
		noneBeforePanel.add(beforeMin);
		noneBeforePanel.add(todBefore);

		JPanel noneAfterPanel = new JPanel();
		noneAfterPanel.add(afterHr);
		noneAfterPanel.add(new JLabel(":"));
		noneAfterPanel.add(afterMin);
		noneAfterPanel.add(todAfter);

		JPanel noneBetweenPanel = new JPanel();
		noneBetweenLabel.setAlignmentY(TOP_ALIGNMENT);
		noneBetweenPanel.add(noneBetweenCheck);
		noneBetweenPanel.add(noneBetweenLabel);
		JPanel betweenPanel = new JPanel();
		betweenPanel.setLayout(new BoxLayout(betweenPanel, BoxLayout.Y_AXIS));
		JPanel line1 = new JPanel();
		line1.add(betweenHr1);
		line1.add(new JLabel(":"));
		line1.add(betweenMin1);
		line1.add(todBetween1);
		JPanel line2 = new JPanel();
		line2.add(betweenHr2);
		line2.add(new JLabel(":"));
		line2.add(betweenMin2);
		line2.add(todBetween2);
		betweenPanel.add(line1);
		betweenPanel.add(andLabel);
		betweenPanel.add(line2);

		JPanel gapPanel = new JPanel();
		gapPanel.add(limitGap);
		gapPanel.add(new JLabel("hrs"));

		JPanel onDaysPanel = new JPanel();
		onDaysPanel.setLayout(new BoxLayout(onDaysPanel, BoxLayout.Y_AXIS));
		JPanel dayLine1 = new JPanel();
		dayLine1.add(monCheck);
		dayLine1.add(monLabel);
		dayLine1.add(tueCheck);
		dayLine1.add(tueLabel);
		dayLine1.add(wedCheck);
		dayLine1.add(wedLabel);
		JPanel dayLine2 = new JPanel();
		dayLine2.add(thuCheck);
		dayLine2.add(thuLabel);
		dayLine2.add(friCheck);
		dayLine2.add(friLabel);
		dayLine2.add(satCheck);
		dayLine2.add(satLabel);
		onDaysPanel.add(onDaysLabel);
		onDaysPanel.add(dayLine1);
		onDaysPanel.add(dayLine2);

		JPanel beforeCheckPanel = new JPanel();
		beforeCheckPanel.add(noneBeforeCheck);
		beforeCheckPanel.add(noneBeforeLabel);
		centerPanel.add(beforeCheckPanel);
		centerPanel.add(noneBeforePanel);

		JPanel afterCheckPanel = new JPanel();
		afterCheckPanel.add(noneAfterCheck);
		afterCheckPanel.add(noneAfterLabel);

		JPanel limitGapPanel = new JPanel();
		limitGapPanel.add(limitGapCheck);
		limitGapPanel.add(limitGapLabel);

		centerPanel.add(afterCheckPanel);
		centerPanel.add(noneAfterPanel);
		centerPanel.add(noneBetweenPanel);
		centerPanel.add(betweenPanel);
		centerPanel.add(limitGapPanel);
		centerPanel.add(gapPanel);
		centerPanel.add(onDaysPanel);

		bottomPanel.add(addButton);

		content.add(topPanel, BorderLayout.NORTH);
		content.add(centerPanel, BorderLayout.CENTER);
		content.add(bottomPanel, BorderLayout.SOUTH);
	}

	public Filter getFilter()
	{
		Filter f = filter;
		filter = null;
		return f;

	}

	public void Show()
	{
		filter = null;
		this.setVisible(true);
	}
}
