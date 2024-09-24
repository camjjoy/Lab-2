import java.time.LocalDateTime;

public class Deadline extends Event implements Completable
{
    private boolean complete;

    public Deadline(String name, LocalDateTime dateTime) //constructor
    {
        super(name, dateTime);
    }


    public void complete() //sets complete to true
    {
        this.complete = true;
    }

    public boolean isComplete() //returns complete
    {
        return this.complete;
    }

}
