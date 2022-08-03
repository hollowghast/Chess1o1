package gui;

import bl.Coords;
import bl.MoveValidator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoveListener extends MouseAdapter {
    private Color activePlayer = MainFrame.PLAYER_WHITE;
    private final Border clicked;

    private JLabel origin;
    private JLabel destination;
    private final MoveValidator moveValidator;

    public MoveListener() {
        clicked = new LineBorder(activePlayer, 2, false);
        moveValidator = new MoveValidator();
    }

    public void setInitialOrigin(JLabel init){
        init.setBorder(clicked);
        origin = init;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && origin == null && e.getComponent().getForeground() == activePlayer) {
            origin = (JLabel) e.getComponent();
            origin.setBorder(clicked);
        } else if (e.getButton() == MouseEvent.BUTTON1 && origin != null && e.getComponent().getForeground() == activePlayer) {
            origin.setBorder(MainFrame.DEFAULT_SOLID_BORDER);
            origin = (JLabel) e.getComponent();
            origin.setBorder(clicked);
        } else if (e.getButton() == MouseEvent.BUTTON1 && origin != null && e.getComponent().getForeground() != activePlayer) {
            destination = (JLabel) e.getComponent();
            Coords ori_coords = findCoords(origin);
            Coords dest_coords = findCoords(destination);
            if (moveValidator.checkMove(activePlayer, origin, ori_coords, dest_coords)) {
                JLabel[][] field = MainFrame.getField();

                Color tmp = destination.getBackground();
                destination.setBackground(origin.getBackground());
                origin.setBackground(tmp);

                MainFrame.getField()[dest_coords.getRow()][dest_coords.getCol()] = origin;
                MainFrame.getField()[ori_coords.getRow()][ori_coords.getCol()] = destination;

                origin.setBorder(MainFrame.DEFAULT_SOLID_BORDER);

                destination.getParent().repaint();

                origin = null;
                destination = null;
                activePlayer = activePlayer == MainFrame.PLAYER_WHITE ? MainFrame.PLAYER_BLACK : MainFrame.PLAYER_WHITE;
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && origin == null && e.getComponent().getForeground() == activePlayer) {
            origin = (JLabel) e.getComponent();
            origin.setBorder(clicked);
        } else if (e.getButton() == MouseEvent.BUTTON1 && origin != null && e.getComponent().getForeground() == activePlayer) {
            origin.setBorder(MainFrame.DEFAULT_SOLID_BORDER);
            origin = (JLabel) e.getComponent();
            origin.setBorder(clicked);
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && origin != null && e.getComponent().getForeground() != activePlayer) {
            destination = (JLabel) e.getComponent();
            Coords ori_coords = findCoords(origin);
            Coords dest_coords = findCoords(destination);
            if (moveValidator.checkMove(activePlayer, origin, ori_coords, dest_coords)) {
                JLabel[][] field = MainFrame.getField();

                Color tmp = destination.getBackground();
                destination.setBackground(origin.getBackground());
                origin.setBackground(tmp);

                //for capturing opposing pieces
                destination.setText("");

                origin.setBorder(MainFrame.DEFAULT_SOLID_BORDER);

                MainFrame.getField()[dest_coords.getRow()][dest_coords.getCol()] = origin;
                MainFrame.getField()[ori_coords.getRow()][ori_coords.getCol()] = destination;


                destination.getParent().repaint();

                origin = null;
                destination = null;
                activePlayer = activePlayer == MainFrame.PLAYER_WHITE ? MainFrame.PLAYER_BLACK : MainFrame.PLAYER_WHITE;
            }

        }
    }

    private Coords findCoords(JLabel l) {
        JLabel[][] field = MainFrame.getField();
        Coords result = null;

        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[0].length; col++) {
                if (field[row][col] == l) {
                    result = new Coords(row, col);
                }
            }
        }
        return result;
    }


}
