import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        MainPanel mainPanel = new MainPanel();
        frame.add(mainPanel);

        frame.setVisible(true);

        SwingUtilities.invokeLater(() -> {
            MainPanel.checkTodayEvent(MainPanel.currentDateString);
        });
    }
}