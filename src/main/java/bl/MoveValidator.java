package bl;

import gui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class MoveValidator {
    public boolean checkMove(Color player, JLabel origin, Coords ori_coords, Coords dest_coords) {
        if (player == MainFrame.PLAYER_WHITE) {
            switch (origin.getText()) {
                case "B":
                    if (dest_coords.getCol() == ori_coords.getCol() &&
                            dest_coords.getRow() - ori_coords.getRow() == -1)
                        return hasClearPath(origin.getText(), ori_coords, dest_coords);
                    else if (dest_coords.getRow() - ori_coords.getRow() == -2 &&
                            dest_coords.getCol() == ori_coords.getCol() && ori_coords.getRow() == MainFrame.FIELD_SIZE - 2)
                        return hasClearPath(origin.getText(), ori_coords, dest_coords);
                    else if (Math.abs(dest_coords.getRow() - ori_coords.getRow()) == 1 &&
                            Math.abs(dest_coords.getCol() - ori_coords.getCol()) == 1 &&
                            MainFrame.getField()
                                    [dest_coords.getRow() + dest_coords.getRow() - ori_coords.getRow()]
                                    [dest_coords.getCol() + dest_coords.getCol() - ori_coords.getCol()]
                                    .getForeground() == MainFrame.PLAYER_BLACK
                    )
                        break;
                case "T":
                    if (dest_coords.getCol() == ori_coords.getCol() ||
                            dest_coords.getRow() == ori_coords.getRow())
                        return hasClearPath(origin.getText(), ori_coords, dest_coords);
                    break;
                case "S":
                    if (
                            (dest_coords.getRow() - ori_coords.getRow() == 2 ||
                                    dest_coords.getRow() - ori_coords.getRow() == -2)
                                    &&
                                    (dest_coords.getCol() - ori_coords.getCol() == 1 ||
                                            dest_coords.getCol() - ori_coords.getCol() == -1)
                    )
                        return true;
                    if (
                            (dest_coords.getRow() - ori_coords.getRow() == 1 ||
                                    dest_coords.getRow() - ori_coords.getRow() == -1)
                                    &&
                                    (dest_coords.getCol() - ori_coords.getCol() == 2 ||
                                            dest_coords.getCol() - ori_coords.getCol() == -2)
                    )
                        return true;
                    break;
                case "L":
                    if (Math.abs(dest_coords.getRow() - ori_coords.getRow()) ==
                            Math.abs(dest_coords.getCol() - ori_coords.getCol()))
                        return hasClearPath(origin.getText(), ori_coords, dest_coords);
                    break;
                case "D":
                    if (ori_coords.getCol() == dest_coords.getCol() ||
                            ori_coords.getRow() == dest_coords.getRow() ||
                            (Math.abs(dest_coords.getRow() - ori_coords.getRow()) ==
                                    Math.abs(dest_coords.getCol() - ori_coords.getCol())))
                        return hasClearPath(origin.getText(), ori_coords, dest_coords);
                case "K":
                    if ((ori_coords.getCol() == dest_coords.getCol() || ori_coords.getRow() == dest_coords.getRow())
                            && (Math.abs(ori_coords.getCol() - dest_coords.getCol()) == 1 ||
                            Math.abs(ori_coords.getRow() - dest_coords.getRow()) == 1))
                        return hasClearPath(origin.getText(), ori_coords, dest_coords);
            }
            return false;
        } else if (player == MainFrame.PLAYER_BLACK) {
            switch (origin.getText()) {
                case "B":
                    if (dest_coords.getCol() == ori_coords.getCol() &&
                            dest_coords.getRow() - ori_coords.getRow() == 1)
                        return hasClearPath(origin.getText(), ori_coords, dest_coords);
                    else if (dest_coords.getRow() - ori_coords.getRow() == 2 &&
                            dest_coords.getCol() == ori_coords.getCol() && ori_coords.getRow() == 1)
                        return hasClearPath(origin.getText(), ori_coords, dest_coords);
                    else if (Math.abs(dest_coords.getRow() - ori_coords.getRow()) == 1 &&
                            Math.abs(dest_coords.getCol() - ori_coords.getCol()) == 1 &&
                            MainFrame.getField()
                                    [dest_coords.getRow() + dest_coords.getRow() - ori_coords.getRow()]
                                    [dest_coords.getCol() + dest_coords.getCol() - ori_coords.getCol()]
                                    .getForeground() == MainFrame.PLAYER_WHITE
                    )
                        return true;
                    break;
                case "T":
                    if (dest_coords.getCol() == ori_coords.getCol() ||
                            dest_coords.getRow() == ori_coords.getRow())
                        return hasClearPath(origin.getText(), ori_coords, dest_coords);
                case "S":
                    if (
                            (dest_coords.getRow() - ori_coords.getRow() == 2 ||
                                    dest_coords.getRow() - ori_coords.getRow() == -2)
                                    &&
                                    (dest_coords.getCol() - ori_coords.getCol() == 1 ||
                                            dest_coords.getCol() - ori_coords.getCol() == -1)
                    )
                        return true;
                    if (
                            (dest_coords.getRow() - ori_coords.getRow() == 1 ||
                                    dest_coords.getRow() - ori_coords.getRow() == -1)
                                    &&
                                    (dest_coords.getCol() - ori_coords.getCol() == 2 ||
                                            dest_coords.getCol() - ori_coords.getCol() == -2)
                    )
                        return true;
                    break;
                case "L":
                    if (Math.abs(dest_coords.getRow() - ori_coords.getRow()) ==
                            Math.abs(dest_coords.getCol() - ori_coords.getCol()))
                        return hasClearPath(origin.getText(), ori_coords, dest_coords);
                    break;
                case "D":
                    if (ori_coords.getCol() == dest_coords.getCol() ||
                            ori_coords.getRow() == dest_coords.getRow() ||
                            (Math.abs(dest_coords.getRow() - ori_coords.getRow()) ==
                                    Math.abs(dest_coords.getCol() - ori_coords.getCol())))
                        return hasClearPath(origin.getText(), ori_coords, dest_coords);
                case "K":
                    if ((ori_coords.getCol() == dest_coords.getCol() || ori_coords.getRow() == dest_coords.getRow())
                            && (Math.abs(ori_coords.getCol() - dest_coords.getCol()) == 1 ||
                            Math.abs(ori_coords.getRow() - dest_coords.getRow()) == 1))
                        return hasClearPath(origin.getText(), ori_coords, dest_coords);
            }
            return false;
        }
        return false;
    }

    private boolean hasClearPath(String piece, Coords ori, Coords dest) {
        switch (piece) {
            case "B", "T":
                if (ori.getRow() == dest.getRow() || ori.getCol() == dest.getCol())
                    return checkStraightPath(ori, dest);
            case "L":
                if (Math.abs(ori.getRow() - dest.getRow()) == Math.abs(ori.getCol() - dest.getCol()))
                    return checkDiagonalPath(ori, dest);
            case "D", "K":
                return checkStraightPath(ori, dest) || checkDiagonalPath(ori, dest);
        }
        return false;
    }

    private boolean checkStraightPath(Coords ori, Coords dest) {
        var field = MainFrame.getField();
        boolean mayPass = false;

        //vertical upwards
        if (ori.getRow() >= dest.getRow()) {
            mayPass = true;
            for (int i = ori.getRow() - 1; i > dest.getRow() && mayPass; i--) {
                if (!field[i][ori.getCol()].getText().isEmpty())
                    mayPass = false;
            }
        }
        //vertical downwards
        else if (ori.getRow() <= dest.getRow()) {
            mayPass = true;
            for (int i = ori.getRow() + 1; i < dest.getRow() && mayPass; i++) {
                if (!field[i][ori.getCol()].getText().isEmpty()) {
                    mayPass = false;
                }
            }
        }
        //horizontal to the left
        else if (ori.getCol() >= dest.getCol()) {
            mayPass = true;
            for (int i = ori.getCol() - 1; i > dest.getCol() && mayPass; i--) {
                if (!field[ori.getRow()][i].getText().isEmpty()) {
                    mayPass = false;
                }
            }
        }
        //horizontal to the right
        else if (ori.getCol() <= dest.getCol()) {
            mayPass = true;
            for (int i = ori.getCol() + 1; i < dest.getCol() && mayPass; i++) {
                if (!field[ori.getRow()][i].getText().isEmpty()) {
                    mayPass = false;
                }
            }
        }
        return mayPass;
    }

    private boolean checkDiagonalPath(Coords ori, Coords dest) {
        var field = MainFrame.getField();
        boolean mayPass = false;

        //Q4
        if (ori.getRow() < dest.getRow() && ori.getCol() < dest.getCol()) {
            mayPass = true;
            for (int i = 1; i < dest.getRow() - ori.getRow() && mayPass; i++) {
                if (ori.getRow() + i < field.length && ori.getCol() + i < field.length)
                    if (!field[ori.getRow() + i][ori.getCol() + i].getText().isEmpty())
                        mayPass = false;
            }
        }
        //Q3
        else if (ori.getRow() < dest.getRow() && ori.getCol() > dest.getCol()) {
            mayPass = true;
            for (int i = 1; i < dest.getRow() - ori.getRow() && mayPass; i++) {
                if (ori.getRow() + i < field.length && ori.getCol() - i >= 0)
                    if (!field[ori.getRow() + i][ori.getCol() - i].getText().isEmpty())
                        mayPass = false;
            }
        }
        //Q2
        else if (ori.getRow() > dest.getRow() && ori.getCol() > dest.getCol()) {
            mayPass = true;
            for (int i = 1; i < ori.getRow() - dest.getRow() && mayPass; i++) {
                if (ori.getRow() - i >= 0 && ori.getCol() - i >= 0)
                    if (!field[ori.getRow() - i][ori.getCol() - i].getText().isEmpty())
                        mayPass = false;
            }
        }
        //Q1
        else if (ori.getRow() > dest.getRow() && ori.getCol() < dest.getCol()) {
            mayPass = true;
            for (int i = 1; i < ori.getRow() - dest.getRow() && mayPass; i++) {
                if (ori.getRow() + i < field.length && ori.getCol() - i >= 0)
                    if (!field[ori.getRow() + i][ori.getCol() - i].getText().isEmpty())
                        mayPass = false;
            }
        }
        return mayPass;
    }
}
