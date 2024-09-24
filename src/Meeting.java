import java.time.Duration;
import java.time.LocalDateTime;

public class Meeting extends Event implements Completable
{
    private boolean complete;
    private LocalDateTime endDateTime;
    private String location;

    public Meeting(String name, LocalDateTime dateTime, LocalDateTime endDateTime, String location) //constructor
    {
        super(name, dateTime);
        this.endDateTime = endDateTime;
        this.location = location;
        this.complete = false;
    }

    public void complete() //complete = true
    {
        this.complete = true;
    }

    public boolean isComplete() //returns complete
    {
        return this.complete;
    }

    public LocalDateTime getEndTime() //returns endDateTime
    {
        return this.endDateTime;
    }

    public Duration getDuration() //returns duration in minutes
    {
        return Duration.between(super.getDateTime(), this.endDateTime);
    }

    public String getLocation() //returns location
    {
        return this.location;
    }

    public void setEndTime(LocalDateTime endDateTime) //sets the end of the meeting
    {
        this.endDateTime = endDateTime;
    }

    public void setLocation(String location) //sets location of meeting
    {
        this.location = location;
    }

}
