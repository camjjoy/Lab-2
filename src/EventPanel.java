import javax.swing.*;
import java.time.format.DateTimeFormatter;

public class EventPanel extends JPanel
{
    private Event event; //the event that this panel displays
    private JButton completeButton; //button that completes event

    public EventPanel(Event event) //creates EventPanel for this.event
    {
        this.event = event;

        JLabel nameLabel = new JLabel("Name: " + event.getName());
        JLabel timeLabel = new JLabel(String.format("Time: " + event.getDateTime()));

        add(nameLabel); //add name of meeting
        add(timeLabel); //add time of meeting

        //if applicable
        if (event instanceof Meeting meeting)
        {
            JLabel locationLabel = new JLabel("Location: " + meeting.getLocation());
            JLabel durationLabel = new JLabel("Duration: " + meeting.getDuration());

            add(locationLabel); //add location of meeting (if applicable)
            add(durationLabel); //add duration of meeting (if applicable)
        }

        if (event instanceof Completable completable)
        {
            JLabel completionLabel = new JLabel("Complete: " + completable.isComplete());
            add(completionLabel); //add completion status of event

            completeButton = new JButton("Complete");
            completeButton.addActionListener(e -> {
                completable.complete();
                setCompletionStatus(completionLabel);
            });

            if (!completable.isComplete())
            {
                add(completeButton);
            }
        }
    }
    private void setCompletionStatus(JLabel completionLabel)
    {
        if (event instanceof Completable completable)
        {
            if (completable.isComplete())
            {
                completionLabel.setText("Complete");
            } else
            {
                completionLabel.setText("Incomplete");
            }
        }
    }
}
