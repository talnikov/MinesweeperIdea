package com.makhasoeva.minesweeper.View;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.makhasoeva.minesweeper.Model.*;
import com.makhasoeva.minesweeper.mvc.Controller;
import com.makhasoeva.minesweeper.mvc.Model;
import com.makhasoeva.minesweeper.mvc.SquareHandler;
import com.makhasoeva.minesweeper.mvc.View;
//import com.sun.java.util.jar.pack.Attribute;

/* UI.java requires no other files. */
public class MinesweeperView implements View {
    private JFrame frame = new JFrame();
    private final String MARKED_SQUARE_IMAGE = "resources/markedSquare.JPG";
    private final String COVERED_SQUARE_IMAGE = "resources/coveredSquare.JPG";

    private HashMap<ActionType, JMenuItem> actionsToMenuItems = new HashMap<>();
    private SquareHandler squareHandler;

    private enum Image {
        ZERO(0, "resources/uncoveredEmptySquare.JPG"),
        ONE(1, "resources/oneNeighbour.JPG"),
        TWO(2, "resources/twoNeighbours.JPG"),
        THREE(3, "resources/threeNeighbours.JPG"),
        FOUR(4, "resources/fourNeighbours.JPG"),
        FIVE(5, "resources/fiveNeighbours.JPG"),
        SIX(6, "resources/sixNeighbours.JPG"),
        SEVEN(7, "resources/sevenNeighbours.JPG"),
        EIGHT(8, "resources/eightNeighbours.JPG");

        private final int neighbors;
        private final String path;

        Image(int i, String s) {
            neighbors = i;
            path = s;
        }

        public String getPath() {
            return path;
        }

        public static ImageIcon getImageForNeighboursNumber(int num) {
            for (Image img : Image.values()) {
                if (img.neighbors == num) {
                    return new ImageIcon(img.getPath());
                }
            }
            return null;
        }
    }

    private JTextField txt = new JTextField(5);

    private JButton[][] jButtons = null;
    private JPanel panel;

    private class ButtonClickListener extends MouseAdapter {

        final int buttonCoordI;
        final int buttonCoordJ;

        private ButtonClickListener(int buttonCoordI, int buttonCoordJ) {
            this.buttonCoordI = buttonCoordI;
            this.buttonCoordJ = buttonCoordJ;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            String name = ((JButton) e.getSource()).getName();
            txt.setText(name);

            if (SwingUtilities.isRightMouseButton(e)) {
                if (squareHandler != null) {
                    squareHandler.onSquareMarked(buttonCoordI, buttonCoordJ);
                }
            }
            if (SwingUtilities.isLeftMouseButton(e)) {
                if (squareHandler != null) {
                    squareHandler.onSquareOpened(buttonCoordI, buttonCoordJ);
                }
            }
        }
    };


    public MinesweeperView(JFrame jFrame) {
        //initializeNewModel();
        // Create and set up the window.
        jFrame.setName("Minesweeper");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setLayout(new BorderLayout());
        jFrame.setJMenuBar(menuBuild());
        gridBuild();
        jFrame.add(panel, BorderLayout.CENTER);
        jFrame.add(txt, BorderLayout.SOUTH);

        // Display the window.
        jFrame.pack();
        jFrame.setResizable(false);
        jFrame.setVisible(true);
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
                    jButtons[i][j].addMouseListener(new ButtonClickListener(i, j));
                    panel.add(jButtons[i][j]);
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return;
    }

    private JMenuBar menuBuild() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        menu.setMnemonic(KeyEvent.VK_G);
        menuBar.add(menu);


        JMenuItem menuItemNewGame = new JMenuItem("New game", KeyEvent.VK_N);
        menu.add(menuItemNewGame);
        actionsToMenuItems.put(ActionType.NewGame, menuItemNewGame);

        JMenuItem highScoresMenuItem = new JMenuItem("High scores", KeyEvent.VK_H);
        menu.add(highScoresMenuItem);
        actionsToMenuItems.put(ActionType.HighScore, highScoresMenuItem);

        menu.addSeparator();

        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                ActionEvent.CTRL_MASK));
        menu.add(exitMenuItem);
        actionsToMenuItems.put(ActionType.Exit, exitMenuItem);

        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(menu);

        JMenuItem aboutMenuItem = new JMenuItem("About", KeyEvent.VK_A);
        menu.add(aboutMenuItem);
        actionsToMenuItems.put(ActionType.About, aboutMenuItem);

        return menuBar;
    }

    @Override
    public void drawModel(Model model) {
        for (int i = 0; i < Board.ROWS; i++) {
            for (int j = 0; j < Board.COLS; j++) {
                JButton btn = jButtons[i][j];
                switch (model.getSquareState(i, j)) {
                    case COVERED:
                        btn.setIcon(new ImageIcon(COVERED_SQUARE_IMAGE));
                        break;
                    case UNCOVERED:
                        btn.setIcon(Image.getImageForNeighboursNumber(model.getSquareNeighbors(i, j)));
                        break;
                    case MARKED:
                        btn.setIcon(new ImageIcon(MARKED_SQUARE_IMAGE));
                        break;
                }
            }
        }
    }

    @Override
    public void addViewHandler(ActionType actionType, final Runnable action) {
        actionsToMenuItems.get(actionType).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (action != null) {
                        action.run();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            }
        });
    }

    @Override
    public void setSquareHandler(SquareHandler handler) {
        this.squareHandler = handler;
    }
}