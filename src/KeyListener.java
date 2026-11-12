import javax.swing.*;
import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
    MainPanel mainPanel;
    public KeyListener(MainPanel mainPanel){
        this.mainPanel = mainPanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_ESCAPE) {
            exitGame();
            return;
        }

        if (keyCode >= KeyEvent.VK_TAB) {
            int currentPhase = mainPanel.getMainScreenPhase();
            if(currentPhase <7)
                currentPhase++;
            else
                currentPhase = 1;
            MainPanel.changeGamePhase(currentPhase);
        }

        if (keyCode >= KeyEvent.VK_1 && keyCode <= KeyEvent.VK_6) {
            int phase = keyCode - KeyEvent.VK_0;
            MainPanel.changeGamePhase(phase);
        }

        if (keyCode >= KeyEvent.VK_NUMPAD1 && keyCode <= KeyEvent.VK_NUMPAD6) {
            int phase = keyCode - KeyEvent.VK_NUMPAD0;
            MainPanel.changeGamePhase(phase);
        }
    }

    private void exitGame() {
        int decyzja = JOptionPane.showConfirmDialog(
                null,
                "Czy na pewno chcesz zamknąć?",
                "Zamknij",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (decyzja == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
