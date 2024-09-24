import java.util.Date;

public class Meeting extends Event implements Completable
{
    private boolean complete;
    private Date endDateTime;
    private String location;

    public Meeting(String name, Date dateTime, Date endDateTime, String location) //constructor
    {
        super(name, dateTime);
        this.endDateTime = endDateTime;
        this.location = location;
    }

    public void complete() //complete = true
    {
        this.complete = true;
    }

    public boolean isComplete() //returns complete
    {
        return this.complete;
    }

    public Date getEndTime() //returns endDateTime
    {
        return this.endDateTime;
    }

    public int getDuration() //returns duration in minutes
    {
        long durationInMillis = endDateTime.getTime() - super.getDateTime().getTime();
        return (int) (durationInMillis / (1000 * 60)); // Convert to minutes
    }

    public String getLocation() //returns location
    {
        return this.location;
    }

    public void setEndTime(Date endDateTime) //sets the end of the meeting
    {
        this.endDateTime = endDateTime;
    }

    public void setLocation(String location) //sets location of meeting
    {
        this.location = location;
    }

}
