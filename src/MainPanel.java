import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public static int moneyAmount = 0;
    public static int respectAmount = 0;
    public static String familyNameString = "Giovani";

    public static int mainScreenPhase = 1;

    public static JButton
            bDzielnica = new JButton("1 - DZIELNICA"),
            bMisjeiSkoki = new JButton("<html><center>2 - MISJE<br>I SKOKI</center></html>"),
            bCzarnyRynek = new JButton("3 - CZARNY RYNEK"),
            bMojaRodzina = new JButton("4 - MOJA RODZINA"),
            bArsenal = new JButton("5 - ARSENAŁ"),
            bLegalneBiznesy = new JButton("<html><center>6 - LEGALNE<br>BIZNESY</center></html>");

    private static JLabel moneyLabel, respectLabel, familyNameLabel;
    private static JLabel temporary = new JLabel("1");

    public MainPanel(){
        this.setLayout(new BorderLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(screenSize);

        JPanel topPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel middlePanel = new JPanel();

        setPanels(topPanel, leftPanel, bottomPanel, middlePanel);

        topPanelAdjustment(topPanel);
        leftPanelAdjustment(leftPanel);
        centerPanelAdjustment(middlePanel);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(middlePanel, BorderLayout.CENTER);

        KeyListener keyListener = new KeyListener(this);
        this.addKeyListener(keyListener);

        changeGamePhase(1);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }

    private static void leftPanelAdjustment(JPanel leftPanel){
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        JButton[] menuButtons =
                {bDzielnica, bMisjeiSkoki, bCzarnyRynek, bMojaRodzina, bArsenal, bLegalneBiznesy};
        int phase = 1;
        for (JButton button : menuButtons) {
            int finalPhase = phase;
            button.addActionListener(e -> changeGamePhase(finalPhase));
            button.setFont(new Font("Courier New", Font.BOLD, 18));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(50, 50, 50));
            button.setFocusPainted(false);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFocusable(false);

            if(phase == 2 || phase == 6){
                button.setMaximumSize(new Dimension(220, 50));
            }else{
                button.setMaximumSize(new Dimension(220, 40));
            }

            phase++;

            leftPanel.add(button);
            leftPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        setLabelsFont(leftPanel);
    }


    private static void topPanelAdjustment(JPanel topPanel) {

        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 15));

        moneyLabel = new JLabel("GOTOWKA: " + moneyAmount + "              ");
        respectLabel = new JLabel("SZACUNEK: " + respectAmount + "              ");
        familyNameLabel = new JLabel("NAZWA RODZINY: " + familyNameString + "              ");

        topPanel.add(moneyLabel);
        topPanel.add(respectLabel);
        topPanel.add(familyNameLabel);

        setLabelsFont(topPanel);
    }

    private static void centerPanelAdjustment(JPanel centerPanel){

        temporary.setFont(new Font("Courier New",Font.BOLD,100));
        temporary.setForeground(Color.YELLOW);
        centerPanel.add(temporary);
    }

    public static void setLabelsFont(JPanel panel){
        setLabels(panel,new Font("Courier New",Font.BOLD,30));
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

    public static void changeGamePhase(int phase) {
        mainScreenPhase = phase;
        temporary.setText(String.valueOf(mainScreenPhase));

        JButton[] menuButtons = {
                bDzielnica, bMisjeiSkoki, bCzarnyRynek, bMojaRodzina, bArsenal, bLegalneBiznesy
        };

        for (int i = 0; i < menuButtons.length; i++) {
            if (i + 1 == phase) {
                menuButtons[i].setBackground(new Color(140, 100, 20));
                menuButtons[i].setForeground(Color.YELLOW);
            } else {
                menuButtons[i].setBackground(new Color(50, 50, 50));
                menuButtons[i].setForeground(Color.WHITE);
            }
        }
    }

    public void addMoney(int amount) {
        this.moneyAmount += amount;
        this.moneyLabel.setText("GOTÓWKA: $" + this.moneyAmount);
    }
}
