import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddEventModal extends JDialog {
    private final JTextField nameField; // Field to enter event name
    private final JTextField dateTimeField; // Field to enter event date and time
    private final JTextField endTimeField; // Field for meeting end time
    private final JTextField locationField; // Field for meeting location
    private final EventListPanel eventListPanel; // Reference to the EventListPanel

    static final int WIDTH = 600;
    static final int HEIGHT = 600;

    static final int ROWS = 5;
    static final int COLS = 2;

    public AddEventModal(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;

        setTitle("Add Event");
        setSize(WIDTH, HEIGHT);
        setModal(true);
        setLayout(new GridLayout(ROWS, COLS));

        nameField = new JTextField();
        dateTimeField = new JTextField();
        endTimeField = new JTextField();
        locationField = new JTextField();

        add(new JLabel("Event Name:"));
        add(nameField);
        add(new JLabel("Start Date & Time (yyyy-MM-dd HH:mm):"));
        add(dateTimeField);
        add(new JLabel("End Date & Time (only if Meeting):"));
        add(endTimeField);
        add(new JLabel("Location (only if Meeting):"));
        add(locationField);

        // add the event button
        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                addEvent();
            }
        });
        add(addButton);
    }

    // create and add the event
    private void addEvent() {
        String name = nameField.getText();
        LocalDateTime start = LocalDateTime.parse(dateTimeField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        if (!endTimeField.getText().isEmpty() && !locationField.getText().isEmpty()) {
            LocalDateTime end = LocalDateTime.parse(endTimeField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            String location = locationField.getText();
            Meeting meeting = new Meeting(name, start, end, location);
            eventListPanel.addEvent(meeting);
        } else {
            Deadline deadline = new Deadline(name, start);
            eventListPanel.addEvent(deadline);
        }

        dispose(); // close modal
    }
}
