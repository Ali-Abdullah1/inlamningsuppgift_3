package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Brickor_15_Spel extends JFrame implements ActionListener {
    JButton[] buttons = new JButton[16];
    int[] puzzle = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
    JButton shuffle = new JButton("Shuffle");
    JPanel game = new JPanel();

    Brickor_15_Spel() {
        add(game);
        game.setLayout(new GridLayout(4, 4));

        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] != 0) {
                buttons[i] = new JButton(String.valueOf(puzzle[i]));
            } else {
                buttons[i] = new JButton(" ");
            }
            buttons[i].addActionListener(this);
            game.add(buttons[i]);
        }

        setLocationRelativeTo(null);
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        Brickor_15_Spel s = new Brickor_15_Spel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        int emptyIndex = -1;
        int clickedIndex = -1;

        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getText().equals(" ")) {
                emptyIndex = i;
            } else if (buttons[i] == clickedButton) {
                clickedIndex = i;
            }
        }
        if (isValidMove(emptyIndex, clickedIndex)) {
            swapButtons(clickedIndex, emptyIndex);
        }
    }

    private boolean isValidMove(int emptyIndex, int clickedIndex) {
        return (Math.abs(emptyIndex - clickedIndex) == 1 && (emptyIndex / 4 == clickedIndex / 4)) ||
                (Math.abs(emptyIndex - clickedIndex) == 4);
    }

    private void swapButtons(int from, int to) {
        String tempText = buttons[from].getText();
        buttons[from].setText(buttons[to].getText());
        buttons[to].setText(tempText);
    }
}
