import javax.swing.*;

public class DiceImage extends JLabel {

    DiceImage(int diceScore, boolean darkMode){

        //DICES
        ImageIcon d1w = new ImageIcon("Dices\\d1w.png");
        ImageIcon d2w = new ImageIcon("Dices\\d2w.png");
        ImageIcon d3w = new ImageIcon("Dices\\d3w.png");
        ImageIcon d4w = new ImageIcon("Dices\\d4w.png");
        ImageIcon d5w = new ImageIcon("Dices\\d5w.png");
        ImageIcon d6w = new ImageIcon("Dices\\d6w.png");

        ImageIcon d1b = new ImageIcon("Dices\\d1b.png");
        ImageIcon d2b = new ImageIcon("Dices\\d2b.png");
        ImageIcon d3b = new ImageIcon("Dices\\d3b.png");
        ImageIcon d4b = new ImageIcon("Dices\\d4b.png");
        ImageIcon d5b = new ImageIcon("Dices\\d5b.png");
        ImageIcon d6b = new ImageIcon("Dices\\d6b.png");


        if (!darkMode){

            switch (diceScore){

                case 1:
                    this.setIcon(d1w);
                    break;
                case 2:
                    this.setIcon(d2w);
                    break;
                case 3:
                    this.setIcon(d3w);
                    break;
                case 4:
                    this.setIcon(d4w);
                    break;
                case 5:
                    this.setIcon(d5w);
                    break;
                case 6:
                    this.setIcon(d6w);
                    break;


            }

        } else {

            switch (diceScore){

                case 1:
                    this.setIcon(d1b);
                    break;
                case 2:
                    this.setIcon(d2b);
                    break;
                case 3:
                    this.setIcon(d3b);
                    break;
                case 4:
                    this.setIcon(d4b);
                    break;
                case 5:
                    this.setIcon(d5b);
                    break;
                case 6:
                    this.setIcon(d6b);
                    break;


            }

        }

    }

}
