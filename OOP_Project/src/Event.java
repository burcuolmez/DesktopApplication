package src;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;
public class Event {
	
	private int eventID;
    private String eventName;
    private String url;
    private String content;
    private double rate;
    private int rateCount;
    private boolean status;
    private Place place;
    private DateFormat startDate;
    private DateFormat endDate;
    private String format;
    private String category;
    private Phone phone;
    ArrayList<String> tags;
    ArrayList<Comment> comments;
    
	public Event(int eventID, String eventName, String url, String content, boolean status, Place place, String startDate,
			String endDate, String format, String category , JsonArray tags, Phone phone) {

		this.eventID = eventID;
		this.eventName = eventName;
		this.url = url;
		this.content = content;
		this.status = status;
		this.place = place;
		this.startDate = new DateFormat(startDate.substring(9,11), startDate.substring(6,8), startDate.substring(1,5), startDate.substring(12,17));
		this.endDate = new DateFormat(endDate.substring(9,11), endDate.substring(6,8), endDate.substring(1,5), endDate.substring(12,17));
		this.format = format;
		this.category = category;
		this.tags = new ArrayList<String>();
		for (int i = 0; i < tags.size(); i++) {
			JsonObject obj = (JsonObject) tags.get(i);
			this.tags.add(obj.get("name").toString());
		}
		this.phone=phone;
		this.comments = new ArrayList<Comment>();
		this.rate=0;
		this.rateCount=0;

	}
	public void updateRate(int r) {
        rate=((rate * rateCount)+r)/(rateCount+1);
        rateCount++;
    }
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    
   
	
	@Override
	public String toString() {
		return  eventName + "\n" + url + "\n" + content
				+ "rate: " + rate + "    " + rateCount +"people has rated\n"  + "location: " + place.toString()
				+ "\nDate: " + startDate.toString() + "--" + endDate.toString() + "\nformat: " + format + ", category: " + category
				+ "\n Phone Number: " + phone.toString() + "\ntags=" + tags + "\ncomments:" + comments.toString() ;
	}



	
	public String countDown() {
	
		String StartDateDay=this.getStartDate().getDay();
		String StartDateMonth=this.getStartDate().getMonth();
		int c = Integer.parseInt(StartDateMonth)-1;
		StartDateMonth= String.valueOf(c);
		String Startyear=this.getStartDate().getYear();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(StartDateDay));
        cal.set(Calendar.MONTH, Integer.parseInt(StartDateMonth));
        cal.set(Calendar.YEAR, Integer.parseInt(Startyear));
        Date firstDate = cal.getTime();
        
        
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        Date secondDate = calendar.getTime();
      

        long diff =firstDate.getTime()- secondDate.getTime();

        return ("Days: " + diff / 1000 / 60 / 60 / 24+" for: "+this.eventName);

	

	}
	
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public int getRateCount() {
		return rateCount;
	}
	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	public DateFormat getStartDate() {
		return startDate;
	}
	public void setStartDate(DateFormat startDate) {
		this.startDate = startDate;
	}
	public DateFormat getEndDate() {
		return endDate;
	}
	public void setEndDate(DateFormat endDate) {
		this.endDate = endDate;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	public ArrayList<Comment> getComments() {
		return comments;
	}
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	



}
