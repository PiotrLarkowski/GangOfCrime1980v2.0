import javax.swing.*;
import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode== KeyEvent.VK_ESCAPE){
            int decyzja = JOptionPane.showConfirmDialog(null, "Czy napewno chcesz zamknac?", "Zamknij",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(decyzja == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
