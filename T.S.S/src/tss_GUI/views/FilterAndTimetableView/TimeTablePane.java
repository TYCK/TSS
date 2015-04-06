package tss_GUI.views.FilterAndTimetableView;
import tss_GUI.GUIDefs;
import tss_core.TSSCore;
import tss_timetableProcessor.Timetable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class servers as the timetable gallery. It draws the timetables and allows the user to
 * scroll through the gallery.
 * @author Yerodin Richards
 * 
 *
 */
public class TimeTablePane extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timetable[] timetables;
	private int currentlySelected;
	private Image prevArrow, nextArrow;
	public TimeTablePane(final ArrayList<Timetable> timetables)
	{
		
		
		try
		{
			prevArrow = ImageIO.read(TSSCore.class.getClassLoader().getResourceAsStream("tss_GUI\\res\\scrollArrowl.png"));
			nextArrow = ImageIO.read(TSSCore.class.getClassLoader().getResourceAsStream("tss_GUI\\res\\scrollArrowr.png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.timetables = new Timetable[timetables.size()];
		int midpoint = (int) Math.ceil(timetables.size()/2);
		this.timetables[midpoint] =  timetables.get(0);
		int j = 1;
		for(int i = 1; i<timetables.size(); i = i+2)
		{
			this.timetables[midpoint-j] = timetables.get(i);
			++j;
		}
		j = 1;
		for(int i = 2; i<timetables.size(); i = i+2)
		{
			this.timetables[midpoint+j] = timetables.get(i);
			++j;
		}
		currentlySelected = midpoint;
		
		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(currentlySelected-1 >= 0 && e.getX() < prevArrow.getWidth(TimeTablePane.this))
				{
					currentlySelected -= 1;
					TimeTablePane.this.repaint();
				}
				else if(currentlySelected+1 < timetables.size() && e.getX() > getWidth()-nextArrow.getWidth(TimeTablePane.this))
				{
					currentlySelected += 1;
					TimeTablePane.this.repaint();
				}
			}
		}
		);
	}
	
	public void paint(Graphics g)
	{
		Image current = timetables[currentlySelected].generateImage();
//		double ratio = (double)this.getHeight()/(double)currentL.getHeight(null);
//		BufferedImage current = new BufferedImage(currentL.getWidth(this), this.getHeight(), BufferedImage.TYPE_INT_RGB);
//		Graphics tG = current.createGraphics();
//		tG.drawImage(currentL, 0, 0, currentL.getWidth(this), this.getHeight(), null);
//		tG.dispose();
		
		g.setColor(GUIDefs.BACKDROP_COLOR);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(current, (int) (.5F*this.getWidth()-.5*current.getWidth(this)), 0, this);
		g.setColor(Color.BLUE);
		g.drawRect((int) (.5F*this.getWidth()-.5*current.getWidth(this)), 0, current.getWidth(this), current.getHeight(this));
		if(timetables[currentlySelected].hasClashes())
			System.out.println("clashes");
		else
			System.out.println("no clashes");
		if(currentlySelected-1 >= 0 )
		{
			Image previous = timetables[currentlySelected-1].generateImage();
			g.drawImage(previous, (int) (.5F*this.getWidth()-.5*current.getWidth(this))-previous.getWidth(this)-20, 0, this);
			g.drawImage(prevArrow, 0, (int) (.5F*this.getHeight()-.5F*prevArrow.getHeight(this)), this);
		}
		if(currentlySelected+1 < timetables.length)
		{
			Image next = timetables[currentlySelected+1].generateImage();
			g.drawImage(next, (int) (.5F*this.getWidth()-.5*current.getWidth(this))+current.getWidth(this)+20, 0, this);
			g.drawImage(nextArrow, this.getWidth()-nextArrow.getWidth(this), (int) (.5F*this.getHeight()-.5F*nextArrow.getHeight(this)), this);
		}
	}


}
