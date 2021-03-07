import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board {
    JButton allButtons[][] = new TheButtons[9][9];
    Space allSpaces[][] = new Space[9][9];

    private ArrayList<Coordinates> moveList = null;
    GameRules g = new GameRules();

    private int oldX;
    private int oldY;

    ArrayList<Coordinates> whitePiecesPositions = new ArrayList<Coordinates>();
    ArrayList<Coordinates> blackPiecesPositions = new ArrayList<Coordinates>();


    public Board() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int l = i + 1;
                int k = j + 1;

                //vytvoří se 81 tlačítek, která jsou rozmístěna na plochu v 9x9 síti
                TheButtons buttons = new TheButtons(i, j);
                allButtons[i][j] = buttons;
                allSpaces[i][j] = new Space();
                allSpaces[i][j].changePiece(new NullPiece(i,j));
                buttons.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (moveList != null && g.isMovePossible(buttons.getA(), buttons.getB(), moveList, allSpaces[oldX][oldY].getCurrentPiece().getMoves(oldX,oldY,whitePiecesPositions,blackPiecesPositions).size()) && allSpaces[oldX][oldY].getCurrentPiece().getOwner() == g.currentTurn) {
                            movePiece(allSpaces[oldX][oldY].getCurrentPiece(),buttons.getA(), buttons.getB());
                            moveList = null;
                            if (g.currentTurn == 0) {
                                g.currentTurn = 1;
                            } else if (g.currentTurn == 1) {
                                g.currentTurn = 0;
                            }
                        } else if (moveList != null && !g.isMovePossible(buttons.getA(), buttons.getB(), moveList, allSpaces[oldX][oldY].getCurrentPiece().getMoves(oldX,oldY,whitePiecesPositions,blackPiecesPositions).size()) && allSpaces[buttons.getA()][buttons.getB()].getCurrentPiece().getType() != "null") {
                            oldX = buttons.getA();
                            oldY = buttons.getB();
                            moveList = allSpaces[oldX][oldY].getCurrentPiece().getMoves(oldX,oldY,whitePiecesPositions,blackPiecesPositions);

                        } else if (moveList == null && allSpaces[buttons.getA()][buttons.getB()].getCurrentPiece().getType() != "null") {
                            oldX = buttons.getA();
                            oldY = buttons.getB();
                            moveList = allSpaces[oldX][oldY].getCurrentPiece().getMoves(oldX,oldY,whitePiecesPositions,blackPiecesPositions);
                        }

                        else if (allSpaces[buttons.getA()][buttons.getB()].getCurrentPiece().getType() == "null") {
                            moveList = null;

                        }


                    }
                });
            }
        }
        makePawn(0,6,5);
        makePawn(0,5,5);
        makePawn(1,4,3);
        makePawn(1,5,3);
        makePawn(0, 2,2);

        System.out.println(whitePiecesPositions.get(0).firstHalf() + " " +  whitePiecesPositions.get(0).secondHalf());
        System.out.println(whitePiecesPositions.get(1).firstHalf() + " " +  whitePiecesPositions.get(1).secondHalf());
    }

    public void movePiece(piece movingPiece, int x, int y) {
        if (movingPiece.getOwner() == 0) {
            for (int i = 0; i < whitePiecesPositions.size(); i++) {
                if (whitePiecesPositions.get(i).firstHalf() == movingPiece.getX() && whitePiecesPositions.get(i).secondHalf() == movingPiece.getY()) {
                    whitePiecesPositions.remove(i);
                    break;
                }
            }
        } else if (movingPiece.getOwner() == 1) {
            for (int i = 0; i < blackPiecesPositions.size(); i++) {
                if (blackPiecesPositions.get(i).firstHalf() == movingPiece.getX() && blackPiecesPositions.get(i).secondHalf() == movingPiece.getY()) {
                    blackPiecesPositions.remove(i);
                    break;
                }
            }
        }
        if (movingPiece.getOwner() == 0) {
            whitePiecesPositions.add(new Coordinates(x,y));
        } else if (movingPiece.getOwner() == 1) {
            blackPiecesPositions.add(new Coordinates(x,y));
        }
        allSpaces[movingPiece.getX()][movingPiece.getY()].changePiece(new NullPiece(movingPiece.getX(),movingPiece.getY()));
        buttonUpdate(movingPiece.getX(),movingPiece.getY());
        movingPiece.moveTo(x,y);
        allSpaces[x][y].changePiece(movingPiece);
        buttonUpdate(x,y);
    }
    public void buttonUpdate(int x, int y) {
        if (allSpaces[x][y].getCurrentPiece().getType() != "null") {
            allButtons[x][y].setBackground(Color.BLACK);
            allButtons[x][y].setOpaque(true);
        } else if (allSpaces[x][y].getCurrentPiece().getType() == "null") {
            allButtons[x][y].setBackground(Color.RED);
            allButtons[x][y].setOpaque(true);
        }
    }
    public void makePawn(int owner, int x, int y) {
        allSpaces[x][y].changePiece(new Pawn(owner,x,y));
        buttonUpdate(x,y);
        pieceCreationLog(owner, x, y);
    }
    public void pieceCreationLog(int owner, int x, int y) {
        if (owner == 0) {
            whitePiecesPositions.add(new Coordinates(x,y));
        } else if (owner == 1) {
            blackPiecesPositions.add(new Coordinates(x,y));
        }
    }
}
