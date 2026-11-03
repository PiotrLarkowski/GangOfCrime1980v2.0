import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel(){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height));

        JPanel topPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel middlePanel = new JPanel();

        setPanels(topPanel, leftPanel, bottomPanel, middlePanel);

        topPanelAdjustment(topPanel);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(middlePanel, BorderLayout.CENTER);

        KeyListener keyListener = new KeyListener();
        this.addKeyListener(keyListener);

        this.setFocusable(true);
        this.setVisible(true);
    }

    private static void topPanelAdjustment(JPanel topPanel) {
        int moneyAmount = 0;
        int respectAmount = 0;
        String familyNameString = "Giovani";
        JLabel money = new JLabel("GOTOWKA: " + moneyAmount + "              ");
        JLabel respect = new JLabel("SZACUNEK: " + respectAmount + "              ");
        JLabel familyName = new JLabel("NAZWA RODZINY: " + familyNameString + "              ");

        topPanel.add(money);
        topPanel.add(respect);
        topPanel.add(familyName);

        setLabels(topPanel,new Font("Courier New",Font.BOLD,30));
    }

    public static void setLabels(JPanel panel, Font fontName) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(fontName);
                comp.setForeground(Color.YELLOW);
            }
        }
    }

    private static void setPanels(JPanel topPanel, JPanel leftPanel, JPanel bottomPanel, JPanel middlePanel) {
        topPanel.setBackground(new Color(40, 40, 40));
        leftPanel.setBackground(new Color(30, 30, 30));
        bottomPanel.setBackground(new Color(20, 20, 20));
        middlePanel.setBackground(new Color(15, 15, 15));

        topPanel.setPreferredSize(new Dimension(0, 60));
        leftPanel.setPreferredSize(new Dimension(250, 0));
        bottomPanel.setPreferredSize(new Dimension(0, 50));
    }


}
