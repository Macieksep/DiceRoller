import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class DiceRoller {

    public static void main(String[] args) {

        //VARIABLES
        final int[] dices = {1};
        final int[] maxScore = {6};
        final boolean[] darkMode = {false};

        //WINDOW
        DiceWindow win = new DiceWindow();

        //ELEMENTS
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem darkModeItem = new JMenuItem("Dark mode");
            optionsMenu.add(darkModeItem);
            menuBar.add(optionsMenu);

        JSlider diceAmmout = new JSlider(1,6);
            diceAmmout.setBackground(Color.lightGray);
            diceAmmout.setMajorTickSpacing(1);
            diceAmmout.setPaintTicks(true);
            diceAmmout.setPaintLabels(true);

        JSlider diceMaxScore = new JSlider(1,6, 6);
            diceMaxScore.setBackground(Color.lightGray);
            diceMaxScore.setMajorTickSpacing(1);
            diceMaxScore.setPaintTicks(true);
            diceMaxScore.setPaintLabels(true);

        JButton roll = new JButton("Roll!");
            roll.setFocusable(false);
            roll.setBackground(Color.WHITE);

        JLabel outcome = new JLabel("Roll to start");
        JLabel dicesLabel = new JLabel("Dices:");
        JLabel scoreLabel = new JLabel("Max points:");

        GridBagConstraints gbc = new GridBagConstraints();

        //PANELS
        JPanel dicePanel = new JPanel();
        JPanel outcomePanel = new JPanel();
        JPanel lowerPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel();
        JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        //STARTING BG COLORS
        dicePanel.setBackground(Color.lightGray);
        outcomePanel.setBackground(Color.lightGray);
        lowerPanel.setBackground(Color.lightGray);
        buttonPanel.setBackground(Color.lightGray);
        mainPanel.setBackground(Color.lightGray);

        //ACTION & CHANGE LISTENERS
        diceAmmout.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                dicePanel.removeAll();
                dicePanel.revalidate();
                dicePanel.repaint();

                dices[0] = diceAmmout.getValue();

                for (int i = 0; i < dices[0]; i++){
                    dicePanel.add(new DiceImage(maxScore[0], darkMode[0]));
                }

                win.revalidate();

            }
        });
        diceMaxScore.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                maxScore[0] = diceMaxScore.getValue();

                dicePanel.removeAll();
                dicePanel.revalidate();
                dicePanel.repaint();

                for (int i = 0; i < dices[0]; i++){
                    dicePanel.add(new DiceImage(maxScore[0], darkMode[0]));
                }

                win.revalidate();

            }
        });
        roll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //SFX PLAY
                try {
                    SfxPlay();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                }

                int wholeOutcome = 0;
                int currentDiceScore;

                dicePanel.removeAll();
                dicePanel.revalidate();
                dicePanel.repaint();

                for (int i = 0; i < dices[0]; i++) {

                    currentDiceScore = Roll(maxScore[0]);

                    wholeOutcome += currentDiceScore;

                    dicePanel.add(new DiceImage(currentDiceScore, darkMode[0]));

                }

                outcome.setText("Score: " + String.valueOf(wholeOutcome));

                win.revalidate();
            }
        });
        darkModeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (darkMode[0]){

                    darkMode[0] = false;
                    dicePanel.setBackground(Color.lightGray);
                    outcomePanel.setBackground(Color.lightGray);
                    lowerPanel.setBackground(Color.lightGray);
                    buttonPanel.setBackground(Color.lightGray);
                    mainPanel.setBackground(Color.lightGray);

                    diceAmmout.setBackground(Color.lightGray);
                    diceMaxScore.setBackground(Color.lightGray);

                    roll.setBackground(Color.WHITE);
                    roll.setForeground(Color.black);

                    darkModeItem.setText("Dark Mode");

                } else {

                    darkMode[0] = true;
                    dicePanel.setBackground(Color.gray);
                    outcomePanel.setBackground(Color.gray);
                    lowerPanel.setBackground(Color.gray);
                    buttonPanel.setBackground(Color.gray);
                    mainPanel.setBackground(Color.gray);

                    diceAmmout.setBackground(Color.gray);
                    diceMaxScore.setBackground(Color.gray);

                    roll.setBackground(Color.darkGray);
                    roll.setForeground(Color.white);

                    darkModeItem.setText("Light Mode");

                }

                dicePanel.removeAll();
                dicePanel.revalidate();
                dicePanel.repaint();

                for (int i = 0; i < dices[0]; i++){
                    dicePanel.add(new DiceImage(maxScore[0], darkMode[0]));
                }

                win.revalidate();

            }
        });

        //PANEL ITEMS
        {
            outcomePanel.add(outcome);

                gbc.weightx = 1;
            lowerPanel.add(dicesLabel, gbc);

                gbc.gridx =1;
            lowerPanel.add(scoreLabel, gbc);

                gbc.gridx =0;
                gbc.gridy =1;
            lowerPanel.add(diceAmmout, gbc);

                gbc.gridx =1;
                gbc.gridy =1;
            lowerPanel.add(diceMaxScore, gbc);

            buttonPanel.add(roll);

            mainPanel.add(dicePanel);
            mainPanel.add(outcomePanel);
            mainPanel.add(lowerPanel);
            mainPanel.add(buttonPanel);
        }

        //WINDOW ITEMS
        win.add(mainPanel);
        win.setJMenuBar(menuBar);
        diceAmmout.setValue(1);
        win.revalidate();

    }

    //DICE ROLL OUTCOME
    static int Roll(int maxScore){

        Random random = new Random();

        return random.nextInt(maxScore)+1;
    }

    //MUSIC PLAYER
    static void SfxPlay() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        File sfx = new File("sfx\\diceSFX.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sfx);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();

    }

}

/*

    Title: Dice Roller

            Author: Maciej Sepeta

            Title: Dice Roller with GUI

            ver. 0.1.1

            Nowości:
            0.1.0

 */
