package src;
public class DateFormat {
   
	private String day;
    private String month;
    private String year;
    private String clock;
    public DateFormat(String day, String month, String year, String clock) {
        this.day=day;
        this.month=month;
        this.year=year;
        this.clock=clock;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getClock() {
        return clock;
    }
    public void setClock(String clock) {
        this.clock = clock;
    }
    @Override
   	public String toString() {
   		return  day + "/" + month + "/" + year + ", time: " + clock ;
   	}

}