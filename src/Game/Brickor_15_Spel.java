package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Brickor_15_Spel extends JFrame implements ActionListener {
    JButton[] buttons = new JButton[16];
    int [] puzzle = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
    JButton shuffle = new JButton("Shuffle");
    JPanel game = new JPanel();

    Brickor_15_Spel() {
        add(game);
        game.setLayout(new GridLayout(4, 4));

        for(int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] !=0){
                buttons[i] = new JButton(String.valueOf(puzzle[i]));
            } else {
                buttons[i] = new JButton(" ");
            }
            buttons[i].addActionListener(this);
            game.add(buttons[i]);
        }

        setLocationRelativeTo(null);
        setSize(600,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public static void main(String[] args){
        Brickor_15_Spel s = new Brickor_15_Spel();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
