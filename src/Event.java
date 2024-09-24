import java.time.LocalDateTime;

public abstract class Event implements Comparable<Event>
{
    private String name; //name of the event
    private LocalDateTime dateTime; //time and date the event starts

    public Event(String name, LocalDateTime dateTime) {// Constructor
        this.name = name;
        this.dateTime = dateTime;
    }

    public String getName() //returns the name
    {
        return this.name;
    }

    public LocalDateTime getDateTime() //returns dateTime
    {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) //sets the dateTime
    {
        this.dateTime = dateTime;
    }

    public void setName(String name) //sets name of event
    {
        this.name = name;
    }

    public int compareTo(Event e) //compares the date of this Event to incoming event
    {
        return this.dateTime.compareTo(e.getDateTime());
    }
}
