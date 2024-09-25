import javax.swing.*;
import java.time.LocalDateTime;


public class EventPlanner
{
    static final int WIDTH = 600;
    static final int HEIGHT = 600;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        EventListPanel eventlistPanel = new EventListPanel();
        frame.add(eventlistPanel);

        addDefaultEvents(eventlistPanel);
        frame.add(eventlistPanel);
        frame.setSize(WIDTH,HEIGHT);
        frame.setVisible(true);
    }
    public static void addDefaultEvents(EventListPanel eventListPanel)
    {
        // Create default events
        Deadline deadline = new Deadline("Lab 2 Due", LocalDateTime.of(2024, 9, 25, 2, 59));
        Meeting meeting = new Meeting("Software Development Class", LocalDateTime.of(2024, 9, 25, 3, 0),
                LocalDateTime.of(2024, 9, 25, 4, 0), "CSCI 339");

        // Add events to the event list panel
        eventListPanel.addEvent(deadline);
        eventListPanel.addEvent(meeting);
    }
}
