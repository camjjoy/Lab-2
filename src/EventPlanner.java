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
        Deadline deadline = new Deadline("Project Submission", LocalDateTime.of(2024, 10, 1, 23, 59));
        Meeting meeting = new Meeting("Team Meeting", LocalDateTime.of(2024, 9, 30, 10, 0),
                LocalDateTime.of(2024, 9, 30, 11, 0), "Conference Room");

        // Add events to the event list panel
        eventListPanel.addEvent(deadline);
        eventListPanel.addEvent(meeting);
    }
}
