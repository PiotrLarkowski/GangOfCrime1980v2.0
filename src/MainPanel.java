import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainPanel extends JPanel {

    public static int currentDay = 1;
    public static int currentMonth = 1;
    public static int currentYear = 1944;
    public static String currentDateString = currentDay +"-"+currentMonth+"-"+currentYear;

    private static ArrayList<GameEvent> gameEvents = new ArrayList<>();

    public static int moneyAmount = 0;
    public static int respectAmount = 0;
    public static String familyNameString = "Giovani";

    public static int mainScreenPhase = 1;

    public static JButton
            bDzielnica = new JButton("<html><center>1 - BUDYNKI <br> DZIELNICA</center></html>"),
            bPersonel = new JButton("<html><center>2 - LUDZIE <br> PRESONEL</center></html>"),
            bMisjeiSkoki = new JButton("<html><center>3 - MISJE<br>I AKCJE</center></html>"),
            bCzarnyRynek = new JButton("4 - CZARNY RYNEK"),
            bMojaRodzina = new JButton("5 - MOJA RODZINA"),
            bArsenal = new JButton("6 - ARSENAŁ"),
            bLegalneBiznesy = new JButton("<html><center>7 - LEGALNE<br>BIZNESY</center></html>");

    public static JButton nextTurn = new JButton("Zakoncz dzien");

    private static JLabel moneyLabel, respectLabel, familyNameLabel;
    private static JLabel temporary = new JLabel("1");
    private static JLabel currentTime = new JLabel("Data: " + currentDateString);

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
        bottomPanelAdjustment(bottomPanel);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(middlePanel, BorderLayout.CENTER);

        KeyListener keyListener = new KeyListener(this);
        this.addKeyListener(keyListener);

        createAllGameEvents();
        changeGamePhase(1);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);

    }

    private void createAllGameEvents() {
        String[] eventTabDates ={
          "1-1-1944",
          "1-1-1944"
        };
        String[] eventTabTitle ={
                "Prolog",
                "Skromne poczatki"
        };
        String[] eventTabText ={
                "Witaj w grze Gang Of Crime 1980! Twoim glownym zadaniem bedzie zarobic do roku 1980 1 000 000 000$"
                + " Co jakis czas w zaleznosci od bierzacej daty beda pojawiac sie wydarzenia ktore beda mialy"
                + " wplyw na Twoja rozgrywke wiec sledz je pilnie! W kazdy dzien mozesz wykonac okreslona ilosc ruchow."
                + " Jak skoncza Ci sie mozliwe ruchy zakoncz dzien przy pomocy przycisku na dole ekranu."
                + " Zycze szczecia w podziemnym swiecie. I baw sie dobrze!",

                "Zdobadz 1 000 000$ do konca 1945 roku"
        };
        for (int i = 0; i < eventTabText.length; i++) {
            GameEvent gameEvent = new GameEvent(0,eventTabDates[i], eventTabTitle[i],eventTabText[i]);
            gameEvents.add(gameEvent);
        }
    }

    private static void leftPanelAdjustment(JPanel leftPanel){
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        JButton[] menuButtons =
                {bDzielnica, bPersonel, bMisjeiSkoki, bCzarnyRynek, bMojaRodzina, bArsenal, bLegalneBiznesy};
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

            if(phase == 1 || phase == 2 || phase == 3 || phase == 7){
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

    private static void bottomPanelAdjustment(JPanel bottomPanel) {
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));

        bottomPanel.add(Box.createHorizontalGlue());

        nextTurn.addActionListener(e -> nextTurn());
        nextTurn.setFont(new Font("Courier New", Font.BOLD, 18));
        nextTurn.setForeground(Color.WHITE);
        nextTurn.setBackground(new Color(50, 50, 50));
        nextTurn.setFocusPainted(false);
        nextTurn.setFocusable(false);
        nextTurn.setPreferredSize(new Dimension(240, 45));
        nextTurn.setMaximumSize(new Dimension(240, 45)); // Wymusza stały rozmiar w BoxLayout
        bottomPanel.add(nextTurn);

        bottomPanel.add(Box.createRigidArea(new Dimension(500, 0)));

        currentTime.setFont(new Font("Courier New", Font.BOLD, 28));
        currentTime.setForeground(Color.YELLOW);
        bottomPanel.add(currentTime);

        bottomPanel.add(Box.createHorizontalGlue());
    }

    private static void nextTurn() {
        resetVariables();
        currentDay++;
    }

    private static void resetVariables() {
        //TODO mozliwosc wykonywania misji raz na dzien, wykonywanie innych dziennych czynnosci
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
                bDzielnica, bPersonel, bMisjeiSkoki, bCzarnyRynek, bMojaRodzina, bArsenal, bLegalneBiznesy
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

    public static void checkTodayEvent(String date) {
        for (GameEvent gameEvent : gameEvents) {
            if (gameEvent.getEventDate().equals(date) && gameEvent.getEventShow() == 0) {
                gameEvent.setEventShow(1);
                showEventDialog(gameEvent);
            }
        }
    }

    static void showEventDialog(GameEvent event) {
        Window parentWindow = SwingUtilities.getWindowAncestor(currentTime);
        Frame parentFrame = (parentWindow instanceof Frame) ? (Frame) parentWindow : null;

        AtomicBoolean exitBool = new AtomicBoolean(false);

        JDialog eventDialog = new JDialog(parentFrame, event.getEventTitle(), true);
        eventDialog.setUndecorated(true);
        eventDialog.setSize(650, 420);
        eventDialog.setLocationRelativeTo(parentFrame);

        JPanel contentPanel = new JPanel(new BorderLayout(15, 15));
        contentPanel.setBackground(new Color(35, 15, 15));
        contentPanel.setBorder(BorderFactory.createLineBorder(new Color(180, 130, 30), 3));

        JLabel titleLabel = new JLabel(event.getEventTitle(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 26));
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 15, 5, 15));

        JTextArea textArea = new JTextArea(event.getEventText());
        textArea.setFont(new Font("Courier New", Font.PLAIN, 18));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(new Color(35, 15, 15));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        JPanel btnPanel = new JPanel();

        if(event.getEventTitle().equals("Zamknij")){
            JButton yesBtn = new JButton("TAK");
            yesBtn.setFont(new Font("Courier New", Font.BOLD, 18));
            yesBtn.setBackground(new Color(140, 100, 20));
            yesBtn.setForeground(Color.YELLOW);
            yesBtn.setFocusPainted(false);
            yesBtn.setPreferredSize(new Dimension(200, 40));
            yesBtn.addActionListener(e -> {
                exitBool.set(true);
                eventDialog.dispose();
            });
            btnPanel.setBackground(new Color(35, 15, 15));
            btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
            btnPanel.add(yesBtn);
            eventDialog.getRootPane().setDefaultButton(yesBtn);

            JButton noBtn = new JButton("NIE");
            noBtn.setFont(new Font("Courier New", Font.BOLD, 18));
            noBtn.setBackground(new Color(140, 100, 20));
            noBtn.setForeground(Color.YELLOW);
            noBtn.setFocusPainted(false);
            noBtn.setPreferredSize(new Dimension(200, 40));
            noBtn.addActionListener(e -> eventDialog.dispose());
            btnPanel.setBackground(new Color(35, 15, 15));
            btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
            btnPanel.add(noBtn);
        }else {
            JButton closeBtn = new JButton("ZROZUMIALEM");
            closeBtn.setFont(new Font("Courier New", Font.BOLD, 18));
            closeBtn.setBackground(new Color(140, 100, 20));
            closeBtn.setForeground(Color.YELLOW);
            closeBtn.setFocusPainted(false);
            closeBtn.setPreferredSize(new Dimension(200, 40));
            closeBtn.addActionListener(e -> eventDialog.dispose());
            btnPanel.setBackground(new Color(35, 15, 15));
            btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
            btnPanel.add(closeBtn);

            eventDialog.getRootPane().setDefaultButton(closeBtn);
        }

        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(textArea, BorderLayout.CENTER);
        contentPanel.add(btnPanel, BorderLayout.SOUTH);

        eventDialog.add(contentPanel);
        eventDialog.setVisible(true);
        if(exitBool.get()){
            System.exit(0);
        }
    }

    public int getMainScreenPhase(){
        return(mainScreenPhase);
    }
    public void addMoney(int amount) {
        this.moneyAmount += amount;
        this.moneyLabel.setText("GOTÓWKA: $" + this.moneyAmount);
    }
}
