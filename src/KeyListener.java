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

        else if (keyCode == KeyEvent.VK_S) {
            int currentPhase = mainPanel.getMainScreenPhase();
            if(currentPhase <7)
                currentPhase++;
            else
                currentPhase = 1;
            MainPanel.changeGamePhase(currentPhase);
        }

        else if (keyCode == KeyEvent.VK_W) {
            int currentPhase = mainPanel.getMainScreenPhase();
            if(currentPhase >1)
                currentPhase--;
            else
                currentPhase = 7;
            MainPanel.changeGamePhase(currentPhase);
        }

        if (keyCode >= KeyEvent.VK_1 && keyCode <= KeyEvent.VK_6) {
            int phase = keyCode - KeyEvent.VK_0;
            MainPanel.changeGamePhase(phase);
        }

        if (keyCode >= KeyEvent.VK_NUMPAD1 && keyCode <= KeyEvent.VK_NUMPAD6) {
            int phase = keyCode - KeyEvent.VK_NUMPAD0;
            MainPanel.changeGamePhase(phase);
        }else{

        }
    }

    private void exitGame() {
        GameEvent gameEvent = new GameEvent(0,"1-1-1944","Zamknij","Czy na pewno chcesz zakonczyn gre?");
        mainPanel.showEventDialog(gameEvent);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
