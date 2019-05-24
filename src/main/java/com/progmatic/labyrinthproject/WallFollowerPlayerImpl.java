/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;
import java.util.List;

/**
 *
 * @author progmatic
 */
public class WallFollowerPlayerImpl implements Player {

    private Direction lastMove;
    private boolean isFirstMove = true;

    public WallFollowerPlayerImpl() {
    }
    
    @Override
    public Direction nextMove(Labyrinth l) {
        List<Direction> possibleMoves = l.possibleMoves();
        if (isFirstMove){
            if (possibleMoves.contains(Direction.EAST)) {
                lastMove = Direction.EAST;
                isFirstMove = false;
                return Direction.EAST;
            } else if (possibleMoves.contains(Direction.NORTH)) {
                lastMove = Direction.NORTH;
                isFirstMove = false;
                return Direction.NORTH;
            } else {
                lastMove = Direction.WEST;
                isFirstMove = false;
                return Direction.WEST;
            }
        }
        if (lastMove == Direction.NORTH) {
            if (possibleMoves.contains(Direction.EAST)) {
                lastMove = Direction.EAST;
                return Direction.EAST;
            } else if (possibleMoves.contains(Direction.NORTH)) {
                lastMove = Direction.NORTH;
                return Direction.NORTH;
            } else if (possibleMoves.contains(Direction.WEST)) {
                lastMove = Direction.WEST;
                return Direction.WEST;
            } else {
                lastMove = Direction.SOUTH;
                return Direction.SOUTH;
            }
        }
        if (lastMove == Direction.EAST) {
            if (possibleMoves.contains(Direction.SOUTH)) {
                lastMove = Direction.SOUTH;
                return Direction.SOUTH;
            } else if (possibleMoves.contains(Direction.EAST)) {
                lastMove = Direction.EAST;
                return Direction.EAST;
            } else if (possibleMoves.contains(Direction.NORTH)) {
                lastMove = Direction.NORTH;
                return Direction.NORTH;
            } else {
                lastMove = Direction.WEST;
                return Direction.WEST;
            }
        }
        if (lastMove == Direction.SOUTH) {
            if (possibleMoves.contains(Direction.WEST)) {
                lastMove = Direction.WEST;
                return Direction.WEST;
            } else if (possibleMoves.contains(Direction.SOUTH)) {
                lastMove = Direction.SOUTH;
                return Direction.SOUTH;
            } else if (possibleMoves.contains(Direction.EAST)) {
                lastMove = Direction.EAST;
                return Direction.EAST;
            } else {
                lastMove = Direction.NORTH;
                return Direction.NORTH;
            }
        }
        if (lastMove == Direction.WEST) {
            if (possibleMoves.contains(Direction.NORTH)) {
                lastMove = Direction.NORTH;
                return Direction.NORTH;
            } else if (possibleMoves.contains(Direction.WEST)) {
                lastMove = Direction.WEST;
                return Direction.WEST;
            } else if (possibleMoves.contains(Direction.SOUTH)) {
                lastMove = Direction.SOUTH;
                return Direction.SOUTH;
            } else {
                lastMove = Direction.EAST;
                return Direction.EAST;
            }
        }
        return lastMove;
    }

}
