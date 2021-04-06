import javax.imageio.ImageIO;
import  javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;


public class Board {
    JButton[][] allButtons = new TheButtons[9][9];
    Space[][] allSpaces = new Space[9][9];

    private ArrayList<Coordinates> moveList = null;
    GameRules g = new GameRules();

    private int oldX;
    private int oldY;

    ArrayList<Coordinates> whitePiecesPositions = new ArrayList<>();
    ArrayList<Coordinates> blackPiecesPositions = new ArrayList<>();

    ArrayList<piece> capturedByWhite = new ArrayList<>();
    ArrayList<piece> capturedByBlack = new ArrayList<>();

    int whiteTime;
    int blackTime;
    boolean timerless = false;
    Image img = null;

    public Board(int timeOne, int timeTwo, int player) {
        whiteTime = timeOne;
        blackTime = timeTwo;
        if (player == 1) {
            g.setCurrentTurn(1);
        }
        CountTimer tim = new CountTimer(whiteTime,blackTime,g.getCurrentTurn());
        if (whiteTime == 0 || blackTime == 0) {
            timerless = true;
        }
        tim.start();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int l = i + 1;
                int k = j + 1;

                //81 buttons are created which are sorted into a 9x9 grid
                TheButtons buttons = new TheButtons(i, j);
                allButtons[i][j] = buttons;
                allSpaces[i][j] = new Space();
                allSpaces[i][j].changePiece(new NullPiece(i,j));
                //The left-click mouse event listener
                buttons.addActionListener(e -> {
                    if ((tim.getWhiteTime() <= 0 || tim.getBlackTime() <= 0) && !timerless) {
                        gameEnd(true);
                    }

                    if (moveList != null && g.isMovePossible(buttons.getA(), buttons.getB(), moveList, allSpaces[oldX][oldY].getCurrentPiece().getMoves(oldX,oldY,whitePiecesPositions,blackPiecesPositions).size()) && allSpaces[oldX][oldY].getCurrentPiece().getOwner() == g.getCurrentTurn()) {
                        movePiece(allSpaces[oldX][oldY].getCurrentPiece(),buttons.getA(), buttons.getB());
                        moveList = null;
                        if (g.getCurrentTurn() == 0) {
                            g.setCurrentTurn(1);
                            tim.changeTurn();
                        } else if (g.getCurrentTurn() == 1) {
                            g.setCurrentTurn(0);
                            tim.changeTurn();
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


                });
                //the right-click mouse event listener
                buttons.addMouseListener(new MouseAdapter(){

                    @Override
                    public void mouseReleased(MouseEvent e) {

                        if (SwingUtilities.isRightMouseButton(e)) {
                            if ((tim.getWhiteTime() <= 0 || tim.getBlackTime() <= 0) && !timerless) {
                                gameEnd(true);
                            }
                            int turn = g.getCurrentTurn();
                            if (turn == 0 && capturedByWhite.size() == 0) {
                                JFrame frame = new JFrame();
                                JButton button = new JButton();

                                button.setText("There are no pieces to place");
                                button.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                                    }
                                });
                                frame.add(button);
                                frame.pack();
                                frame.setLocationRelativeTo(null);
                                frame.setVisible(true);
                            } else if (turn == 1 && capturedByBlack.size() == 0) {
                                JFrame frame = new JFrame();
                                JButton button = new JButton();

                                button.setText("There are no pieces to place");
                                button.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                                    }
                                });
                                frame.add(button);
                                frame.pack();
                                frame.setLocationRelativeTo(null);
                                frame.setVisible(true);
                            } else {
                            JPanel panel = new JPanel();
                            panel.add(new JLabel("Choose which captured piece to place"));
                            DefaultComboBoxModel model = new DefaultComboBoxModel();
                            if (turn == 0) {
                                for (int i = 0; i < capturedByWhite.size(); i++){
                                    model.addElement(capturedByWhite.get(i).getType());
                                }
                            } else if (turn == 1) {
                                for (int i = 0; i < capturedByBlack.size(); i++){
                                    model.addElement(capturedByBlack.get(i).getType());
                                }
                            }
                            JComboBox comboBox = new JComboBox(model);
                            panel.add(comboBox);
                            int result = JOptionPane.showConfirmDialog(null, panel, "Choose a piece", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                switch (comboBox.getSelectedItem().toString()) {
                                    case "pawn":
                                    makePawn(turn, buttons.getA(), buttons.getB());
                                        break;
                                    case "bishop":
                                        makeBishop(turn, buttons.getA(), buttons.getB());
                                        break;
                                    case "gold":
                                        makeGoldenGeneral(turn, buttons.getA(), buttons.getB());
                                        break;
                                    case "silver":
                                        makeSilverGeneral(turn, buttons.getA(), buttons.getB());
                                        break;
                                    case "knight":
                                        makeKnight(turn, buttons.getA(), buttons.getB());
                                        break;
                                    case "lancer":
                                        makeLancer(turn, buttons.getA(), buttons.getB());
                                        break;
                                    case "rook":
                                        makeRook(turn, buttons.getA(), buttons.getB());
                                        break;
                                }

                                if (g.getCurrentTurn() == 0) {
                                    for (int k = 0; k < capturedByWhite.size(); k++) {
                                        if (capturedByWhite.get(k).getType().equals(comboBox.getSelectedItem())) {
                                            capturedByWhite.remove(k);
                                            break;
                                        };
                                    }
                                    g.setCurrentTurn(1);
                                    tim.changeTurn();
                                } else if (g.getCurrentTurn() == 1) {
                                    for (int k = 0; k < capturedByBlack.size(); k++) {
                                        if (capturedByBlack.get(k).getType().equals(comboBox.getSelectedItem())) {
                                            capturedByBlack.remove(k);
                                            break;
                                        };
                                    }
                                    g.setCurrentTurn(0);
                                    tim.changeTurn();
                                }
                            }
                            }
                            System.out.println("RIGHT CLICK");
                            System.out.println(capturedByWhite.get(0).getType());

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
            gameEnd(false);
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
        //Adding the taken piece to the list of captured pieces

            if (allSpaces[x][y].getCurrentPiece().getOwner() == 0) {
                capturedByBlack.add(allSpaces[x][y].getCurrentPiece());
            } else if (allSpaces[x][y].getCurrentPiece().getOwner() == 1) {
                capturedByWhite.add(allSpaces[x][y].getCurrentPiece());
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
    public void buttonUpdate(int x, int y)  {
        if (allSpaces[x][y].getCurrentPiece().getType().equals("pawn")) {

            try {
                if (allSpaces[x][y].getCurrentPiece().getOwner() == 0) {
                    img = ImageIO.read(getClass().getResource("images/pawnW.png"));
                } else if (allSpaces[x][y].getCurrentPiece().getOwner() == 1) {
                    img = ImageIO.read(getClass().getResource("images/pawnB.png"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            allButtons[x][y].setIcon(new ImageIcon(img));


        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("null")) {

            allButtons[x][y].setIcon(null);

        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("knight")) {

            try {
                if (allSpaces[x][y].getCurrentPiece().getOwner() == 0) {
                    img = ImageIO.read(getClass().getResource("images/knightW.png"));
                } else if (allSpaces[x][y].getCurrentPiece().getOwner() == 1) {
                    img = ImageIO.read(getClass().getResource("images/knightB.png"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            allButtons[x][y].setIcon(new ImageIcon(img));
        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("silver")) {

            try {
                if (allSpaces[x][y].getCurrentPiece().getOwner() == 0) {
                    img = ImageIO.read(getClass().getResource("images/silverW.png"));
                } else if (allSpaces[x][y].getCurrentPiece().getOwner() == 1) {
                    img = ImageIO.read(getClass().getResource("images/silverB.png"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            allButtons[x][y].setIcon(new ImageIcon(img));
        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("gold")) {

            try {
                if (allSpaces[x][y].getCurrentPiece().getOwner() == 0) {
                    img = ImageIO.read(getClass().getResource("images/goldW.png"));
                } else if (allSpaces[x][y].getCurrentPiece().getOwner() == 1) {
                    img = ImageIO.read(getClass().getResource("images/goldB.png"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            allButtons[x][y].setIcon(new ImageIcon(img));
        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("king")) {

            try {
                if (allSpaces[x][y].getCurrentPiece().getOwner() == 0) {
                    img = ImageIO.read(getClass().getResource("images/kingW.png"));
                } else if (allSpaces[x][y].getCurrentPiece().getOwner() == 1) {
                    img = ImageIO.read(getClass().getResource("images/kingB.png"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            allButtons[x][y].setIcon(new ImageIcon(img));
        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("lancer")) {

            try {
                if (allSpaces[x][y].getCurrentPiece().getOwner() == 0) {
                    img = ImageIO.read(getClass().getResource("images/lancerW.png"));
                } else if (allSpaces[x][y].getCurrentPiece().getOwner() == 1) {
                    img = ImageIO.read(getClass().getResource("images/lancerB.png"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            allButtons[x][y].setIcon(new ImageIcon(img));
        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("rook")) {

            try {
                if (allSpaces[x][y].getCurrentPiece().getOwner() == 0) {
                    if (allSpaces[x][y].getCurrentPiece().getUpgradePiece().equals("upgraded")) {
                        img = ImageIO.read(getClass().getResource("images/rookUW.png"));
                    } else {
                        img = ImageIO.read(getClass().getResource("images/rookW.png"));
                    }
                } else if (allSpaces[x][y].getCurrentPiece().getOwner() == 1) {
                    if (allSpaces[x][y].getCurrentPiece().getUpgradePiece().equals("upgraded")) {
                        img = ImageIO.read(getClass().getResource("images/rookUB.png"));
                    } else {
                        img = ImageIO.read(getClass().getResource("images/rookB.png"));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            allButtons[x][y].setIcon(new ImageIcon(img));
        } else if (allSpaces[x][y].getCurrentPiece().getType().equals("bishop")) {

            try {
                if (allSpaces[x][y].getCurrentPiece().getOwner() == 0) {
                    if (allSpaces[x][y].getCurrentPiece().getUpgradePiece().equals("upgraded")) {
                        img = ImageIO.read(getClass().getResource("images/bishopUW.png"));
                    } else {
                        img = ImageIO.read(getClass().getResource("images/bishopW.png"));
                    }
                } else if (allSpaces[x][y].getCurrentPiece().getOwner() == 1) {
                    if (allSpaces[x][y].getCurrentPiece().getUpgradePiece().equals("upgraded")) {
                        img = ImageIO.read(getClass().getResource("images/bishopUB.png"));
                    } else {
                        img = ImageIO.read(getClass().getResource("images/bishopB.png"));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            allButtons[x][y].setIcon(new ImageIcon(img));
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
                buttonUpdate(p.getCurrentPiece().getX(), p.getCurrentPiece().getY());
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
                } else {
                    int input = JOptionPane.showConfirmDialog(null, "Upgrade this piece?", "Upgrade option", JOptionPane.YES_NO_OPTION);
                    if (input == 0) {
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
                } else {
                    int input = JOptionPane.showConfirmDialog(null, "Upgrade this piece?", "Upgrade option", JOptionPane.YES_NO_OPTION);
                    if (input == 0) {
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
    public void gameEnd(boolean timeOut) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                allSpaces[i][j] = null;
            }
        }
        JFrame frame = new JFrame();
        JButton button = new JButton();
        int player = g.getCurrentTurn() + 1;
        if (timeOut) {
            if (player == 1) {
                player++;
            } else if (player == 2) {
                player--;
            }
            button.setText("The time ran out! Player " + player + " wins!");
        } else {
            button.setText("Player " + player + " wins!");
        }

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
