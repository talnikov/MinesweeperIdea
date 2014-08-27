package com.makhasoeva.minesweeper.View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.makhasoeva.minesweeper.Model.Board;

/* UI.java requires no other files. */
public class UI {
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Minesweeper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setJMenuBar(menuBuild());

		frame.getContentPane().add(gridBuild());

		// JLabel emptyLabel = new JLabel("");
		// emptyLabel.setPreferredSize(new Dimension(575, 500));
		// /frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	private static JPanel gridBuild() {
		JPanel panel = new JPanel();
		panel.setName("MainPanel");

		panel.setLayout(new GridLayout(Board.COLS, Board.ROWS));
		String COVERED_SQUARE_IMAGE = "resources/coveredSquare.JPG";
		ImageIcon buttonIcon = new ImageIcon(COVERED_SQUARE_IMAGE);

		// TODO scale buttonIcon when button size changes. some ideas above
		// Image img = buttonIcon.getImage() ;
		// Image newimg = img.getScaledInstance( NEW_WIDTH, NEW_HEIGHT,
		// java.awt.Image.SCALE_SMOOTH ) ;
		// buttonIcon = new ImageIcon( newimg );
		BufferedImage coveredSquareImage;
		try {
			coveredSquareImage = ImageIO.read(new File(COVERED_SQUARE_IMAGE));

			int width = coveredSquareImage.getWidth();
			int height = coveredSquareImage.getHeight();

			Dimension dimension = new Dimension(width, height);
			for (int i = 0; i < Board.ROWS; i++) {
				for (int j = 0; j < Board.COLS; j++) {
					JButton b1 = new JButton(buttonIcon);
					b1.setPreferredSize(dimension);
					b1.setName(i + " " + j);
					panel.add(b1);
				}
			}
			return panel;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static JMenuBar menuBuild() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game");
		menu.setMnemonic(KeyEvent.VK_G);
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("New game", KeyEvent.VK_N);
		menu.add(menuItem);

		menuItem = new JMenuItem("High scores", KeyEvent.VK_H);
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Exit", KeyEvent.VK_E);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));
		menu.add(menuItem);

		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(menu);

		menuItem = new JMenuItem("About", KeyEvent.VK_A);
		menu.add(menuItem);

		return menuBar;

	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}
}