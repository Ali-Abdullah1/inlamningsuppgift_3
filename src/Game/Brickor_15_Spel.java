package Game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Brickor_15_Spel extends JFrame implements ActionListener {
    JButton[] buttons = new JButton[16];
    int[] puzzle = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
    JButton shuffle = new JButton("Blanda");
    JButton solve = new JButton("Lös");
    JPanel game = new JPanel();

    Brickor_15_Spel() {
        setTitle("Ett 15-Spel");
        game.setLayout(new GridLayout(4, 4));

        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] != 0) {
                buttons[i] = new JButton(String.valueOf(puzzle[i]));
            } else {
                buttons[i] = new JButton("");
            }

            Font buttonFont = buttons[i].getFont();
            buttons[i].setFont(buttonFont.deriveFont(40f));
            buttons[i].setBackground(new Color(255, 248, 220));
            buttons[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            buttons[i].setBorder(new LineBorder(Color.BLACK));
            game.setBorder(new LineBorder(Color.RED, 4));

            buttons[i].addActionListener(this);
            game.add(buttons[i]);
        }

        add(game, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.add(shuffle);
        panel.add(solve);
        add(panel, BorderLayout.SOUTH);

        setSize(800, 600);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        shuffle.addActionListener(e -> shufflePuzzleNumbers());
        solve.addActionListener(e -> solvePuzzle());
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
            if (buttons[i].getText().equals("")) {
                emptyIndex = i;
            } else if (buttons[i] == clickedButton) {
                clickedIndex = i;
            }
        }
        if (isValidMove(emptyIndex, clickedIndex)) {
            swapButtons(clickedIndex, emptyIndex);
            if (hasWon()) {
                JOptionPane.showMessageDialog(this, "Du har vunnit!");
            }
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

    private boolean hasWon() {
        for (int i = 0; i < puzzle.length - 1; i++) {
            String buttonText = buttons[i].getText();
            int expectedValue = i + 1;

            if (!buttonText.equals(Integer.toString(expectedValue))) {
                return false;
            }
        }
        if (!buttons[15].getText().equals("")) {
            return false;
        }
        return true;
    }

    private void shufflePuzzleNumbers() {
        Random r = new Random();
        ArrayList<Integer> availableNumbers = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            availableNumbers.add(i);
        }

        for (int i = 0; i < puzzle.length; i++) {
            if (i != 15) {
                int randomIndex = r.nextInt(availableNumbers.size());
                int randomNumber = availableNumbers.get(randomIndex);

                buttons[i].setText(String.valueOf(randomNumber));
                availableNumbers.remove(randomIndex);
                puzzle[i] = randomNumber;
            } else {
                buttons[i].setText("");
                puzzle[i] = 0;
            }
        }
    }

    private void solvePuzzle() {
        for (int i = 0; i < puzzle.length; i++) {
            if (i != 15) {
                buttons[i].setText(String.valueOf(i + 1));
                puzzle[i] = i + 1;
            } else {
                buttons[i].setText("");
                puzzle[i] = 0;
            }
        }
    }
}