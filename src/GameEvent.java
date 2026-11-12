public class GameEvent {
    private int eventShow; // 0 = niepokazany, 1 = pokazany
    private String eventDate;
    private String eventTitle;
    private String eventText;

    public GameEvent(int eventShow, String eventDate, String eventTitle, String eventText) {
        this.eventShow = eventShow;
        this.eventDate = eventDate;
        this.eventTitle = eventTitle;
        this.eventText = eventText;
    }

    public String getEventDate() {
        return eventDate;
    }

    public int getEventShow() {
        return eventShow;
    }

    public void setEventShow(int eventShow) {
        this.eventShow = eventShow;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventText() {
        return eventText;
    }

    public void setEventText(String eventText) {
        this.eventText = eventText;
    }
}