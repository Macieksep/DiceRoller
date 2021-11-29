import javax.swing.*;

public class DiceWindow extends JFrame {

    DiceWindow(){

        //WINDOW ICON
        ImageIcon d6w = new ImageIcon("Dices\\d6w.png");

        //WINDOW SETTINGS
        this.setBounds(300,300,500,265);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("Dice Roller");
        this.setIconImage(d6w.getImage());

    }

}
