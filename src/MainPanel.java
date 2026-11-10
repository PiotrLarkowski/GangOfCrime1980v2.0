import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public static int moneyAmount = 0;
    public static int respectAmount = 0;
    public static String familyNameString = "Giovani";

    public static String[] menuOptions = {
            "DZIELNICA",
            "MISJE I SKOKI",
            "CZARNY RYNEK",
            "MOJA RODZINA",
            "ARSENAŁ",
            "LEGALNE BIZNESY"
    };


    private static JLabel moneyLabel, respectLabel, familyNameLabel;

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

        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(middlePanel, BorderLayout.CENTER);

        KeyListener keyListener = new KeyListener();
        this.addKeyListener(keyListener);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }

    private static void leftPanelAdjustment(JPanel leftPanel){
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
        for (String option : menuOptions) {
            JButton menuButton = new JButton(option);

            menuButton.setFont(new Font("Courier New", Font.BOLD, 18));
            menuButton.setForeground(Color.WHITE);
            menuButton.setBackground(new Color(50, 50, 50));
            menuButton.setFocusPainted(false);
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuButton.setFocusable(false);

            menuButton.setMaximumSize(new Dimension(220, 40));

            leftPanel.add(menuButton);
            leftPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Odstęp między przyciskami
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

    public void addMoney(int amount) {
        this.moneyAmount += amount;
        this.moneyLabel.setText("GOTÓWKA: $" + this.moneyAmount);
    }
}
