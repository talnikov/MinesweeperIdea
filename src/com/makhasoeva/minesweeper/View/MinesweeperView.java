package com.makhasoeva.minesweeper.View;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.makhasoeva.minesweeper.Model.Board;
import com.makhasoeva.minesweeper.Model.ExplosionException;
import com.makhasoeva.minesweeper.Model.NoSuchSquareException;
import com.makhasoeva.minesweeper.Model.Square;
//import com.sun.java.util.jar.pack.Attribute;

/* UI.java requires no other files. */
public class MinesweeperView extends JFrame {
    private final String MARKED_SQUARE_IMAGE = "resources/markedSquare.JPG";
    private final String COVERED_SQUARE_IMAGE = "resources/coveredSquare.JPG";
    private final String UNCOVERED_SQUARE_IMAGE = "resources/uncoveredEmptySquare.JPG";
    private final String ONE_NEIGHBOUR_IMAGE = "resources/oneNeighbour.JPG";
    private final String TWO_NEIGHBOURS_IMAGE = "resources/twoNeighbours.JPG";
    private final String THREE_NEIGHBOURS_IMAGE = "resources/threeNeighbours.JPG";

    private JTextField txt = new JTextField(5);
    private JButton[][] jButtons;
    private JPanel panel;

    private Board board = new Board();

    private MouseListener bl = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {

            String name = ((JButton) e.getSource()).getName();
            txt.setText(name);
            String[] i = name.split(" ");
            int x = Integer.parseInt(i[0]);
            int y = Integer.parseInt(i[1]);
            ImageIcon buttonIcon = null;
            if (SwingUtilities.isRightMouseButton(e)) {
                board.markSquare(x, y);
                if (board.getGrid()[x][y].getState() == Square.States.MARKED) {
                    buttonIcon = new ImageIcon(MARKED_SQUARE_IMAGE);
                    jButtons[x][y].setIcon(buttonIcon);
                }
                if (board.getGrid()[x][y].getState() == Square.States.COVERED) {
                    buttonIcon = new ImageIcon(COVERED_SQUARE_IMAGE);
                    jButtons[x][y].setIcon(buttonIcon);
                }
            }
            if (SwingUtilities.isLeftMouseButton(e)) {
                try {
                    board.uncoverSquare(x, y);
                } catch (ExplosionException e1) {
                    e1.printStackTrace();
                } catch (NoSuchSquareException e1) {
                    e1.printStackTrace();
                }
                if (board.getGrid()[x][y].getState() == Square.States.UNCOVERED) {
                    int neighbours = board.getGrid()[x][y].getNeighbours();
                    switch (neighbours){
                        case 0:
                            buttonIcon = new ImageIcon(UNCOVERED_SQUARE_IMAGE);
                            break;
                        case 1:
                            buttonIcon = new ImageIcon(ONE_NEIGHBOUR_IMAGE);
                            break;
                        case 2:
                            buttonIcon = new ImageIcon(TWO_NEIGHBOURS_IMAGE);
                            break;
                        case 3:
                            buttonIcon = new ImageIcon(THREE_NEIGHBOURS_IMAGE);
                            break;
                    }
                    jButtons[x][y].setIcon(buttonIcon);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int i;
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };


    public MinesweeperView() {
        // Create and set up the window.
        setName("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        setJMenuBar(menuBuild());
        gridBuild();
        add(panel, BorderLayout.CENTER);
        add(txt, BorderLayout.SOUTH);

        // Display the window.
        pack();
        setResizable(false);
        setVisible(true);
    }


    private void gridBuild() {
        panel = new JPanel();
        panel.setName("MainPanel");

        panel.setLayout(new GridLayout(Board.COLS, Board.ROWS));
        ImageIcon buttonIcon = new ImageIcon(COVERED_SQUARE_IMAGE);

        BufferedImage coveredSquareImage;
        try {
            coveredSquareImage = ImageIO.read(new File(COVERED_SQUARE_IMAGE));
            int width = coveredSquareImage.getWidth();
            int height = coveredSquareImage.getHeight();
            Dimension dimension = new Dimension(width, height);

            jButtons = new JButton[Board.ROWS][Board.COLS];

            for (int i = 0; i < Board.ROWS; i++) {
                jButtons[i] = new JButton[Board.ROWS];
                for (int j = 0; j < Board.COLS; j++) {
                    jButtons[i][j] = new JButton(buttonIcon);
                    jButtons[i][j].setName(i + " " + j);
                    jButtons[i][j].setPreferredSize(dimension);
                    jButtons[i][j].addMouseListener(bl);
                    panel.add(jButtons[i][j]);
                }
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return;
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
        Frame frame = new MinesweeperView();

//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                createAndShowGUI();
//            }
//        });
    }
}