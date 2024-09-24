import java.util.Date;

public abstract class Event implements Comparable<Event>
{
    private String name; //name of the event
    private Date dateTime; //time and date the event starts

    public Event(String name, Date dateTime) {// Constructor
        this.name = name;
        this.dateTime = dateTime;
    }

    public String getName() //returns the name
    {
        return this.name;
    }

    public Date getDateTime() //returns dateTime
    {
        return dateTime;
    }

    public void setDateTime(Date dateTime) //sets the dateTime
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
