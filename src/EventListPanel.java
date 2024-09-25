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
    private final JCheckBox filterDisplayCompleted; // Checkbox to filter completed events

    public EventListPanel() {
        events = new ArrayList<>();
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();// Holds the controls for the event display
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

        // Sort dropdown
        sortDropDown = new JComboBox<>(new String[]{"Sort by Name", "Sort by Date", "Reverse Name" ,"Reverse Date"});
        sortDropDown.addActionListener(e -> sortEvents());
        controlPanel.add(sortDropDown);

        // Filter checkbox for completed events
        filterDisplayCompleted = new JCheckBox("Show Incomplete Tasks");
        filterDisplayCompleted.addActionListener(e -> filterEvents());
        controlPanel.add(filterDisplayCompleted);

        // Add event button
        // Button to open AddEvent Modal
        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> openAddEventModal());
        controlPanel.add(addEventButton);

        add(controlPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
    }

    // Sort events based on selected criteria
    private void sortEvents() {
        String sortOption = (String) sortDropDown.getSelectedItem();
        switch (sortOption) {
            case "Sort by Name":
                events.sort(Comparator.comparing(Event::getName));
                break;
            case "Sort by Date":
                events.sort(Comparator.comparing(Event::getDateTime));
                break;
            case "Reverse Name":
                events.sort(Comparator.comparing(Event::getName).reversed());
                break;
            case "Reverse Date":
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
                .filter(event -> !(event instanceof Completable) ||
                        !filterDisplayCompleted.isSelected() ||
                        !((Completable) event).isComplete())
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
