import  javax.swing.*;
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

                //81 buttons are created which are sorted into a 9x9 grid
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
                        } else if (moveList != null && !g.isMovePossible(buttons.getA(), buttons.getB(), moveList, allSpaces[oldX][oldY].getCurrentPiece().getMoves(oldX,oldY,whitePiecesPositions,blackPiecesPositions).size()) && !allSpaces[buttons.getA()][buttons.getB()].getCurrentPiece().getType().equals("null")) {
                            oldX = buttons.getA();
                            oldY = buttons.getB();
                            moveList = allSpaces[oldX][oldY].getCurrentPiece().getMoves(oldX,oldY,whitePiecesPositions,blackPiecesPositions);

                        } else if (moveList == null && !allSpaces[buttons.getA()][buttons.getB()].getCurrentPiece().getType().equals("null")) {
                            oldX = buttons.getA();
                            oldY = buttons.getB();
                            moveList = allSpaces[oldX][oldY].getCurrentPiece().getMoves(oldX,oldY,whitePiecesPositions,blackPiecesPositions);
                        }

                        else if (allSpaces[buttons.getA()][buttons.getB()].getCurrentPiece().getType().equals("null")) {
                            moveList = null;

                        }


                    }
                });
            }
        }
        standardSetup();

    }
    //move function, moves a piece and executes all the necessary updating alongside it
    public void movePiece(piece movingPiece, int x, int y) {
        if (allSpaces[x][y].getCurrentPiece().getType().equals("king")) {
            gameEnd();
        }
        //removal of the original position of the moving piece from the positions list
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
        //removal of the taken piece's position from the positions list
        for (int i = 0; i < whitePiecesPositions.size(); i++) {
            if (whitePiecesPositions.get(i).firstHalf() == x && whitePiecesPositions.get(i).secondHalf() == y) {
                whitePiecesPositions.remove(i);
                break;
            }
        }

        for (int i = 0; i < blackPiecesPositions.size(); i++) {
            if (blackPiecesPositions.get(i).firstHalf() == x && blackPiecesPositions.get(i).secondHalf() == y) {
                blackPiecesPositions.remove(i);
                break;
            }
        }

        //adding the new position of the moved piece to the corresponding positions list
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
        //checks if the piece ended in an upgrade lane, if so, upgrades
        if (x < 3 && movingPiece.getOwner() == 0) {
            pieceUpgrade(allSpaces[x][y]);
        } else if (x > 5 && movingPiece.getOwner() == 1) {
            pieceUpgrade(allSpaces[x][y]);
        }
    }
    public void buttonUpdate(int x, int y) {
        if (allSpaces[x][y].getCurrentPiece().getType().equals("pawn")) {
            allButtons[x][y].setBackground(Color.BLACK);
            allButtons[x][y].setOpaque(true);
        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("null")) {
            allButtons[x][y].setBackground(Color.RED);
            allButtons[x][y].setOpaque(true);
        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("knight")) {
            allButtons[x][y].setBackground(Color.BLUE);
            allButtons[x][y].setOpaque(true);
        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("silver")) {
            allButtons[x][y].setBackground(Color.GRAY);
            allButtons[x][y].setOpaque(true);
        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("gold")) {
            allButtons[x][y].setBackground(Color.YELLOW);
            allButtons[x][y].setOpaque(true);
        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("king")) {
            allButtons[x][y].setBackground(Color.PINK);
            allButtons[x][y].setOpaque(true);
        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("lancer")) {
            allButtons[x][y].setBackground(Color.ORANGE);
            allButtons[x][y].setOpaque(true);
        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("rook")) {
            allButtons[x][y].setBackground(Color.DARK_GRAY);
            allButtons[x][y].setOpaque(true);
        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("bishop")) {
            allButtons[x][y].setBackground(Color.GREEN);
            allButtons[x][y].setOpaque(true);
        }
    }
    //make functions, create a piece on the corresponding coordinates, relfect it graphically, and log the coordinates into a list of positions
    public void makePawn(int owner, int x, int y) {
        allSpaces[x][y].changePiece(new Pawn(owner,x,y));
        buttonUpdate(x,y);
        pieceCreationLog(owner, x, y);
    }
    public void makeKnight(int owner, int x, int y) {
        allSpaces[x][y].changePiece(new Knight(owner,x,y));
        buttonUpdate(x,y);
        pieceCreationLog(owner, x, y);
    }
    public void makeSilverGeneral(int owner, int x, int y) {
        allSpaces[x][y].changePiece(new SilverGeneral(owner,x,y));
        buttonUpdate(x,y);
        pieceCreationLog(owner, x, y);
    }
    public void makeGoldenGeneral(int owner, int x, int y) {
        allSpaces[x][y].changePiece(new GoldenGeneral(owner,x,y));
        buttonUpdate(x,y);
        pieceCreationLog(owner, x, y);
    }
    public void makeKing(int owner, int x, int y) {
        allSpaces[x][y].changePiece(new King(owner,x,y));
        buttonUpdate(x,y);
        pieceCreationLog(owner, x, y);
    }
    public void makeLancer(int owner, int x, int y) {
        allSpaces[x][y].changePiece(new Lancer(owner,x,y));
        buttonUpdate(x,y);
        pieceCreationLog(owner, x, y);
    }
    public void makeRook(int owner, int x, int y) {
        allSpaces[x][y].changePiece(new Rook(owner,x,y));
        buttonUpdate(x,y);
        pieceCreationLog(owner, x, y);
    }
    public void makeBishop(int owner, int x, int y) {
        allSpaces[x][y].changePiece(new Bishop(owner,x,y));
        buttonUpdate(x,y);
        pieceCreationLog(owner, x, y);
    }
    //the standard starting pieces for a game of Shogi
    public void standardSetup() {
        for (int i = 0; i < 9; i++) {
            makePawn(0,6,i);
            makePawn(1,2,i);
        }
        makeRook(1,1,1);
        makeBishop(1,1,7);
        makeLancer(1,0,0);
        makeLancer(1,0,8);
        makeKnight(1,0,1);
        makeKnight(1,0,7);
        makeSilverGeneral(1,0,2);
        makeSilverGeneral(1,0,6);
        makeGoldenGeneral(1,0,3);
        makeGoldenGeneral(1,0,5);
        makeKing(1,0,4);

        makeRook(0,7,7);
        makeBishop(0,7,1);
        makeLancer(0,8,0);
        makeLancer(0,8,8);
        makeKnight(0,8,1);
        makeKnight(0,8,7);
        makeSilverGeneral(0,8,2);
        makeSilverGeneral(0,8,6);
        makeGoldenGeneral(0,8,3);
        makeGoldenGeneral(0,8,5);
        makeKing(0,8,4);
    }
    //upgrades the current piece of the space passed to it
    public void pieceUpgrade(Space p) {

        //a lot of pieces can not be upgraded, those are filtered here
        if (p.getCurrentPiece().getUpgradePiece().equals("non upgradable")) {
            return;

            //since the rook and bishop upgrade differently than all other pieces, they get a unique upgradePiece "unupgraded"
        } else if (p.getCurrentPiece().getUpgradePiece().equals("unupgraded")) {

            int input = JOptionPane.showConfirmDialog(null, "Upgrade this piece?", "Upgrade option", JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                p.getCurrentPiece().setUpgradePiece("upgraded");
            }
            //all the remainging pieces upgrade into golden generals, this also includes automatic upgrading when pieces can not move further without it
        } else if (p.getCurrentPiece().getUpgradePiece().equals("golden")) {
            if (p.getCurrentPiece().getOwner() == 0 && p.getCurrentPiece().getX() < 2) {
                if (p.getCurrentPiece().getType().equals("knight")) {
                    p.changePiece(new GoldenGeneral(p.getCurrentPiece().getOwner(), p.getCurrentPiece().getX(), p.getCurrentPiece().getY()));
                    buttonUpdate(p.getCurrentPiece().getX(),p.getCurrentPiece().getY());
                }
                if (p.getCurrentPiece().getX() < 1) {
                    if (p.getCurrentPiece().getType().equals("lancer")) {
                        p.changePiece(new GoldenGeneral(p.getCurrentPiece().getOwner(), p.getCurrentPiece().getX(), p.getCurrentPiece().getY()));
                        buttonUpdate(p.getCurrentPiece().getX(),p.getCurrentPiece().getY());
                    } else if (p.getCurrentPiece().getType().equals("pawn")) {
                        p.changePiece(new GoldenGeneral(p.getCurrentPiece().getOwner(), p.getCurrentPiece().getX(), p.getCurrentPiece().getY()));
                        buttonUpdate(p.getCurrentPiece().getX(),p.getCurrentPiece().getY());
                    }
                }
            } else if(p.getCurrentPiece().getOwner() == 1 && p.getCurrentPiece().getX() > 6) {
                if (p.getCurrentPiece().getType().equals("knight")) {
                    p.changePiece(new GoldenGeneral(p.getCurrentPiece().getOwner(), p.getCurrentPiece().getX(), p.getCurrentPiece().getY()));
                    buttonUpdate(p.getCurrentPiece().getX(),p.getCurrentPiece().getY());
                }
                if (p.getCurrentPiece().getX() > 7) {
                    if (p.getCurrentPiece().getType().equals("lancer")) {
                        p.changePiece(new GoldenGeneral(p.getCurrentPiece().getOwner(), p.getCurrentPiece().getX(), p.getCurrentPiece().getY()));
                        buttonUpdate(p.getCurrentPiece().getX(),p.getCurrentPiece().getY());
                    } else if (p.getCurrentPiece().getType().equals("pawn")) {
                        p.changePiece(new GoldenGeneral(p.getCurrentPiece().getOwner(), p.getCurrentPiece().getX(), p.getCurrentPiece().getY()));
                        buttonUpdate(p.getCurrentPiece().getX(),p.getCurrentPiece().getY());
                    }
                }
            } else {
            int input = JOptionPane.showConfirmDialog(null, "Upgrade this piece?", "Upgrade option", JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                p.changePiece(new GoldenGeneral(p.getCurrentPiece().getOwner(), p.getCurrentPiece().getX(), p.getCurrentPiece().getY()));
                buttonUpdate(p.getCurrentPiece().getX(),p.getCurrentPiece().getY());
            }
            }
        }
    }
    //creates a popup which announces which player won, then ends the game when clicked on
    public void gameEnd() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                allSpaces[i][j] = null;
            }
        }
        JFrame frame = new JFrame();
        JButton button = new JButton();
        int player = g.currentTurn + 1;

        button.setText("Player " + player + " wins!");
        button.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent e) {
                                          System.exit(0);
                                      }
                                  });
        frame.add(button);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void pieceCreationLog(int owner, int x, int y) {
        if (owner == 0) {
            whitePiecesPositions.add(new Coordinates(x,y));
        } else if (owner == 1) {
            blackPiecesPositions.add(new Coordinates(x,y));
        }
    }
}
