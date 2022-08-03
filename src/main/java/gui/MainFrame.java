package gui;

import chess1o1.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalTime;
import java.util.Objects;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


/*
 T | S | L | K | D | L | S | T |
 B | B | B | B | B | B | B | B |
   |   |   |   |   |   |   |   |
   |   |   |   |   |   |   |   |
   |   |   |   |   |   |   |   |
   |   |   |   |   |   |   |   |
 B | B | B | B | B | B | B | B |
 T | S | L | D | K | L | S | T |

 */

public class MainFrame extends JFrame
{
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static final int FIELD_SIZE = 8;

    public static final Color PLAYER_WHITE = Color.WHITE;
    public static final Color PLAYER_BLACK = Color.BLACK;

    private static final JLabel [][] FIELD = new JLabel [FIELD_SIZE][FIELD_SIZE];
    private final MoveListener moveListener = new MoveListener();

    public final JPanel plMain = new JPanel(){
        @Override
        public void repaint() {
            //this.remove(dest);
            //this.add(ori, index);
            if(FIELD != null && FIELD[0][0] != null){
                this.removeAll();
                for(int i = 0; i < FIELD.length; i++){
                    for(int j = 0; j < FIELD[0].length; j++){
                        this.add(FIELD[i][j]);
                    }
                }
            }
            super.repaint();
        }
    };
    public static final Border DEFAULT_SOLID_BORDER = new LineBorder(Color.BLACK, 1, false);

    public MainFrame(String title) {
        super(title);
        init();
        moveListener.setInitialOrigin(FIELD[FIELD.length-2][0]);
    }

    private void init(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH/3, SCREEN_WIDTH/3)); //for a square board
        this.setMinimumSize(new Dimension(SCREEN_WIDTH/10, SCREEN_WIDTH/10));
        this.setContentPane(plMain);

        plMain.setLayout(new GridLayout());
        GridLayout gl = (java.awt.GridLayout) plMain.getLayout();
        gl.setColumns(8);
        gl.setRows(8);

        for (int row = 0; row < FIELD_SIZE; row++)
            for (int col = 0; col < FIELD_SIZE; col++){
                FIELD[row][col] = new JLabel(/*"" + (row*8+col)*/);
                plMain.add(FIELD[row][col]);
                FIELD[row][col].setFont(Font.decode("Monospace bold"));

                if(row == 0 || row == 1) {
                    FIELD[row][col].setForeground(PLAYER_BLACK);
                }
                else if (row == FIELD_SIZE-1 || row == FIELD_SIZE-2){
                    FIELD[row][col].setForeground(PLAYER_WHITE);
                }
                else{
                    FIELD[row][col].setForeground(Color.yellow);

                }

                if (row == 0 || row == FIELD_SIZE-1)
                    switch(col){
                        case 0,FIELD_SIZE-1: FIELD[row][col].setText("T");
                            break;
                        case 1,FIELD_SIZE-2: FIELD[row][col].setText("S");
                            break;
                        case 2,FIELD_SIZE-3: FIELD[row][col].setText("L");
                            break;
                    }
                if(row == 0 && col == 3) FIELD[row][col].setText("K");
                else if(row == 7 && col == 3) FIELD[row][col].setText("D");
                else if(row == 7 && col == 4) FIELD[row][col].setText("K");
                else if(row == 0 && col == 4) FIELD[row][col].setText("D");

                if (row == 1 || row == 6) FIELD[row][col].setText("B");

                FIELD[row][col].setHorizontalAlignment(SwingConstants.CENTER);
                FIELD[row][col].setVerticalAlignment(SwingConstants.CENTER);
                FIELD[row][col].setBorder(DEFAULT_SOLID_BORDER);
                FIELD[row][col].setOpaque(true);

                //FIELD[row][col].setIcon(new ImageIcon("")); //change letter to icon -> DAL?
                if(row % 2 == 0 && col % 2 == 0)
                    FIELD[row][col].setBackground(new Color(130, 130, 130));
                else if (row % 2 == 1 && col % 2 == 0)
                    FIELD[row][col].setBackground(new Color(50, 50, 50));
                else if (row % 2 == 0 && col % 2 == 1)
                    FIELD[row][col].setBackground(new Color(50, 50, 50));
                else if (row % 2 == 1 && col % 2 == 1)
                    FIELD[row][col].setBackground(new Color(130, 130, 130));

                FIELD[row][col].addMouseListener(moveListener);
            }

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        plMain.setSize(this.getSize());
        this.pack();
        this.setVisible(true);
    }

    public static JLabel[][] getField() {
        return FIELD;
    }
}
