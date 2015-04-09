package tss_GUI;

import javax.swing.*;
import java.awt.*;

/**
 * 
 * @author Yerodin Richards
 *
 */
public class LoadingDialog extends JDialog
{
	JLabel loadingText = new JLabel("Loading...");
	private int progress;
	public LoadingDialog(Component parent)
	{
		progress = 0;
		this.setLayout(new BorderLayout());
		this.add(loadingText);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		loadingText.setHorizontalAlignment(SwingConstants.CENTER);
		this.setLocationRelativeTo(parent);
		this.pack();
	}
	
	public void setLoadingProgress(int progress)
	{
		this.progress = progress;
	}
}
