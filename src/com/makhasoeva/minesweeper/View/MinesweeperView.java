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
import com.makhasoeva.minesweeper.mvc.View;
//import com.sun.java.util.jar.pack.Attribute;

/* UI.java requires no other files. */
public class MinesweeperView extends View<MinesweeperModel> {
    private JFrame frame = new JFrame();
    private final String MARKED_SQUARE_IMAGE = "resources/markedSquare.JPG";
    private final String COVERED_SQUARE_IMAGE = "resources/coveredSquare.JPG";

    private HashMap<ActionType, JMenuItem> actionsToMenuItems = new HashMap<>();

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

    public JFrame getFrame() {
        return frame;
    }

    private JButton[][] jButtons = null;
    private HashMap<JButton, GridPoint> buttonMinesPointHashMap = new HashMap<JButton, GridPoint>();
    private JPanel panel;

    private MinesweeperModel model = new MinesweeperModel();

    private MouseListener bl = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!model.isModified()) model.setModificationStatus(true);
            int x = buttonMinesPointHashMap.get((JButton) e.getSource()).getX();
            int y = buttonMinesPointHashMap.get((JButton) e.getSource()).getY();
            String name = ((JButton) e.getSource()).getName();

            txt.setText(name);
            if (SwingUtilities.isRightMouseButton(e)) {

                model.getBoard().markSquare(x, y);
                Square.States state = model.getBoard().getGrid()[x][y].getState();

                if (state == Square.States.MARKED) {
                    jButtons[x][y].setIcon(new ImageIcon(MARKED_SQUARE_IMAGE));
                }
                if (state == Square.States.COVERED) {
                    jButtons[x][y].setIcon(new ImageIcon(COVERED_SQUARE_IMAGE));
                }
            }
            if (SwingUtilities.isLeftMouseButton(e)) {
                try {
                    model.getBoard().uncoverSquare(x, y);


                } catch (ExplosionException e1) {
                    e1.printStackTrace();
                }
                Square.States state = model.getBoard().getGrid()[x][y].getState();
                if (state == Square.States.UNCOVERED) {
                    int neighbours = model.getBoard().getGrid()[x][y].getNeighbours();
                    jButtons[x][y].setIcon(Image.getImageForNeighboursNumber(neighbours));
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
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


    public MinesweeperView(JFrame jFrame, MinesweeperModel model) {
        setModel(model);
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
                    jButtons[i][j].addMouseListener(bl);
                    panel.add(jButtons[i][j]);
                    buttonMinesPointHashMap.put(jButtons[i][j], new GridPoint(i, j));
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
    public void drawModel(MinesweeperModel model) {

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
    public void initializeNewModel() {
        setModel(new MinesweeperModel());
    }

    @Override
    public boolean showConfirmationDialog() {
        return false;
    }
}