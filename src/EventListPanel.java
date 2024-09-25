import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EventListPanel extends JPanel {
    private final ArrayList<Event> events; // List of events
    private final JPanel displayPanel; // Holds EventPanels corresponding to events
    private final JComboBox<String> sortDropDown; // Dropdown for sorting events
    //private final JCheckBox filterDisplayCompleted; // Checkbox to filter completed events
    private final JCheckBox filterDisplayCompleted; // To filter completed tasks
    private final JCheckBox filterDisplayDeadlines; // To filter deadlines
    private final JCheckBox filterDisplayMeetings; // To filter meetings

    public EventListPanel() {
        events = new ArrayList<>();
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();// Holds the controls for the event display
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

        // Sort dropdown
        sortDropDown = new JComboBox<>(new String[]{"Sort by Name", "Sort by Date", "Reverse Name" ,"Reverse Date"});
        sortDropDown.addActionListener(_ -> sortEvents());
        controlPanel.add(sortDropDown);

        // filter completed events
        filterDisplayCompleted = new JCheckBox("Show Incomplete Tasks");
        filterDisplayCompleted.addActionListener(_ -> filterEvents());
        controlPanel.add(filterDisplayCompleted);

        // filter deadlines
        filterDisplayDeadlines = new JCheckBox("Show Non-Deadlines");
        filterDisplayDeadlines.addActionListener(_ -> filterEvents());
        controlPanel.add(filterDisplayDeadlines);

        // filter meetings
        filterDisplayMeetings = new JCheckBox("Show Non-Meetings");
        filterDisplayMeetings.addActionListener(_ -> filterEvents());
        controlPanel.add(filterDisplayMeetings);

        // Add event button
        // Button to open AddEvent Modal
        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(_ -> openAddEventModal());
        controlPanel.add(addEventButton);

        add(controlPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
    }

    // Sort events based on selected criteria
    private void sortEvents() {
        String sortOption = (String) sortDropDown.getSelectedItem();
        switch (sortOption) {
            case "Sort by name":
                events.sort(Comparator.comparing(Event::getName));
                break;
            case "Sort by dateTime":
                events.sort(Comparator.comparing(Event::getDateTime));
                break;
            case "Sort by reverseName":
                events.sort(Comparator.comparing(Event::getName).reversed());
                break;
            case "Sort by reverseDate":
                events.sort(Comparator.comparing(Event::getDateTime).reversed());
            case null:
                break;
            default:
                throw new IllegalStateException(STR."Unexpected value: \{sortOption}");
        }
        updateDisplayPanel();
    }

    // filter
    private void filterEvents() {
        List<Event> filteredEvents = events.stream()
                // filter completed
                .filter(event -> !(event instanceof Completable) ||
                        !filterDisplayCompleted.isSelected() ||
                        !((Completable) event).isComplete())
                // filter deadlines
                .filter(event -> !(event instanceof Deadline) ||
                        !filterDisplayDeadlines.isSelected())
                // filter meetings
                .filter(event -> !(event instanceof Meeting) ||
                        !filterDisplayMeetings.isSelected())
                .collect(Collectors.toList());

        updateDisplayPanel(filteredEvents);
    }


    // open AddEventModal to add event
    private void openAddEventModal() {
        AddEventModal modal = new AddEventModal(this);
        modal.setVisible(true);
    }

    // add event
    public void addEvent(Event event) {
        events.add(event);
        updateDisplayPanel();
    }

    // update display panel
    private void updateDisplayPanel() {
        displayPanel.removeAll();
        for (Event event : events) {
            EventPanel eventPanel = new EventPanel(event);
            displayPanel.add(eventPanel);
        }
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    // overloaded method for more specific events
    private void updateDisplayPanel(List<Event> eventList) {
        displayPanel.removeAll();
        for (Event event : eventList) {
            EventPanel eventPanel = new EventPanel(event);
            displayPanel.add(eventPanel);
        }
        displayPanel.revalidate();
        displayPanel.repaint();
    }
}
