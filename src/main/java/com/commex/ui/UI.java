
package com.commex.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarculaLaf;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

public class UI extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 2886087702481290289L;
	
	JButton openButton;
    JFileChooser fc;
	JTextField OpenLabel;

	public UI()
	{
		super(new GridLayout(1, 1));
		


		JTabbedPane tabbedPane = new JTabbedPane();

		tabbedPane.addTab("Image Creation", ImagePanel());
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = makeTextPanel("Panel #2");
		tabbedPane.addTab("Tab 2", panel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);


		add(tabbedPane);

		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
	protected JComponent ImagePanel()
	{
		JPanel panel = new JPanel(false);
		
		panel.setPreferredSize(new Dimension(400, 400));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.setBorder(new EmptyBorder(0, 10, 0, 10));

		fc = new JFileChooser();
		
		OpenLabel = new JTextField();
		OpenLabel.setText("<Some File>");
		OpenLabel.setColumns(20);

		openButton = new JButton("Select File");
		openButton.setPreferredSize(new Dimension(100,30));
		openButton.addActionListener(this);
		
		JLabel header = new JLabel("<html><h3>DOC1 Image Utility</h3></html>");
		String label = "This utility allows you to create DOC1 suitable images from Encapsulated PostScript files (.eps).";
		
		JTextArea textArea = new JTextArea(2, 30);
		textArea.setText(label);
	    textArea.setWrapStyleWord(true);
	    textArea.setLineWrap(true);
	    textArea.setOpaque(false);
	    textArea.setEditable(false);
	    textArea.setFocusable(false);
	    textArea.setBackground(UIManager.getColor("Label.background"));
	    textArea.setFont(UIManager.getFont("Label.font"));
	    textArea.setBorder(UIManager.getBorder("Label.border"));
		
	    panel.add(header);
		panel.add(textArea);
		panel.add(OpenLabel);
		panel.add(openButton);

		return panel;
	}
	public static JPanel createTextLabel(String text)
	{
        JLabel label = new JLabel();
        label.setText(text);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        
        return panel;
    }
	public void actionPerformed(ActionEvent e)
	{
		// Handle open button action.
		if (e.getSource() == openButton)
		{
			int returnVal = fc.showOpenDialog(UI.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				OpenLabel.setText(file.toString());

			}
		}
		
	}
	protected JComponent makeTextPanel(String text)
	{
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}
	private static void createAndShowGUI()
	{
		// Create and set up the window.

		JFrame.setDefaultLookAndFeelDecorated(true);

		JFrame frame = new JFrame("TabbedPaneDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new UI(), BorderLayout.CENTER);
		frame.setSize(new Dimension(400, 400));
		frame.setUndecorated(true);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args)
	{
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				// Turn off metal's use of bold fonts

				try
				{
					UIManager.setLookAndFeel(new FlatDarculaLaf());
				}
				catch (Exception ex)
				{
					System.err.println("Failed to initialize LaF");
				}

				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}
