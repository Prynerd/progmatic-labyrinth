package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {

    private Coordinate playerCoordinate;
    private CellType[][] labyrinth;

    public LabyrinthImpl() {

    }

    @Override
    public void loadLabyrinthFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int width = Integer.parseInt(sc.nextLine());
            int height = Integer.parseInt(sc.nextLine());
            labyrinth = new CellType[height][width];
            for (int hh = 0; hh < height; hh++) {
                String line = sc.nextLine();
                for (int ww = 0; ww < width; ww++) {
                    switch (line.charAt(ww)) {
                        case 'W':
                            this.labyrinth[hh][ww] = CellType.WALL;
                            break;
                        case 'E':
                            this.labyrinth[hh][ww] = CellType.END;
                            break;
                        case 'S':
                            this.labyrinth[hh][ww] = CellType.START;
                            playerCoordinate = new Coordinate(hh, ww);
                            break;
                        case ' ':
                            this.labyrinth[hh][ww] = CellType.EMPTY;
                            break;
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public int getWidth() {

        if (this.labyrinth == null) {
            return -1;
        }
        return this.labyrinth[0].length;
    }

    @Override
    public int getHeight() {
        if (this.labyrinth == null) {
            return -1;
        }
        return this.labyrinth.length;
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {
        if (c.getCol() > this.labyrinth.length - 1 || c.getRow() > this.labyrinth[0].length - 1 || c.getCol() < 0 || c.getRow() < 0) {
            throw new CellException(c, "Nincs ilyen koordináta!");
        }
        return this.labyrinth[c.getCol()][c.getRow()];
    }

    @Override
    public void setSize(int width, int height) {
        this.labyrinth = new CellType[height][width];
        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[i].length; j++) {
                this.labyrinth[i][j] = CellType.EMPTY;
            }
        }
    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {
        if (c.getCol() > getHeight() || c.getRow() > getWidth()) {
            throw new CellException(c, "Nincs ilyen koordináta!");
        }
        this.labyrinth[c.getCol()][c.getRow()] = type;
        if (type == CellType.START) {
            playerCoordinate = new Coordinate(c.getCol(), c.getRow());
        }
    }

    @Override
    public Coordinate getPlayerPosition() {
        return playerCoordinate;
    }

    @Override
    public boolean hasPlayerFinished() {
        if (this.labyrinth[playerCoordinate.getCol()][playerCoordinate.getRow()] == CellType.END) {
            return true;
        }
        return false;
    }

    @Override
    public List<Direction> possibleMoves() {
        List<Direction> possibleMovesList = new ArrayList<>();
        Coordinate c1 = new Coordinate(playerCoordinate.getCol() - 1, playerCoordinate.getRow());
        Coordinate c2 = new Coordinate(playerCoordinate.getCol() + 1, playerCoordinate.getRow());
        Coordinate c3 = new Coordinate(playerCoordinate.getCol(), playerCoordinate.getRow() - 1);
        Coordinate c4 = new Coordinate(playerCoordinate.getCol(), playerCoordinate.getRow() + 1);
        if (c1.getCol() < getHeight()-1 && c1.getRow() < getWidth()-1) {
            if (this.labyrinth[c1.getCol()][c1.getRow()] == CellType.EMPTY) {
                possibleMovesList.add(Direction.NORTH);
            }
        }
        if (c2.getCol() < getHeight()-1 && c2.getRow() < getWidth()-1) {
            if (this.labyrinth[c2.getCol()][c2.getRow()] == CellType.EMPTY) {
                possibleMovesList.add(Direction.SOUTH);
            }
        }
        if (c3.getCol() < getHeight()-1 && c3.getRow() < getWidth()-1) {
            if (this.labyrinth[c3.getCol()][c3.getRow()] == CellType.EMPTY) {
                possibleMovesList.add(Direction.WEST);
            }
        }
        if (c4.getCol() < getHeight()-1 && c4.getRow() < getWidth()-1) {
            if (this.labyrinth[c4.getCol()][c4.getRow()] == CellType.EMPTY) {
                possibleMovesList.add(Direction.EAST);
            }
        }
        return possibleMovesList;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {
        List<Direction> possibleMoves = possibleMoves();
        if (possibleMoves.contains(direction)) {
            switch (direction) {
                case EAST:
                    playerCoordinate = new Coordinate(playerCoordinate.getCol(), playerCoordinate.getRow() + 1);
                    break;
                case NORTH:
                    playerCoordinate = new Coordinate(playerCoordinate.getCol() + 1, playerCoordinate.getRow());
                    break;
                case SOUTH:
                    playerCoordinate = new Coordinate(playerCoordinate.getCol() - 1, playerCoordinate.getRow());
                    break;
                case WEST:
                    playerCoordinate = new Coordinate(playerCoordinate.getCol(), playerCoordinate.getRow() - 1);
                    break;
            }
        } else {
            throw new InvalidMoveException();
        }
//
//        if (playerCoordinate.getCol() > getHeight() || playerCoordinate.getRow() > getWidth()) {
//
//        }
    }

}
