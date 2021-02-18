package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Management {
	private static HashMap<Integer,Event> events = new HashMap<Integer,Event>();
	private static HashMap<String, Actor> actors = new HashMap<String, Actor>();
	public Management() {};
	
	public static HashMap<String, Actor> getActors() {
		return actors;
	}
	
	public void CreateEvents(JsonArray arr) {
		for (int i = 0; i < arr.size(); i++) {
			JsonObject obj = (JsonObject) arr.get(i);
			JsonObject format = (JsonObject) obj.get("format");
			JsonObject category = (JsonObject) obj.get("category");
			JsonObject venue = (JsonObject) obj.get("venue");
			JsonObject city = (JsonObject) venue.get("city");
			JsonObject district = (JsonObject) venue.get("district");
			JsonObject neighborhood = new JsonObject();
			if (!venue.get("neighborhood").toString().equals("null"))
				neighborhood = (JsonObject) venue.get("neighborhood");
			else
				neighborhood.add("name", null);
			Address address = new Address(venue.get("address").toString().replaceAll("\"",""), city.get("name").toString().replaceAll("\"",""),
					district.get("name").toString().replaceAll("\"",""), neighborhood.get("name").toString().replaceAll("\"",""));
			Phone phone;

			if (!obj.get("phone").toString().equals("null") && !venue.get("phone").toString().equals("")) {
				String code = obj.get("phone").toString().replaceAll("\"","");
			
				phone = new Phone(code.substring(0, 4), code.substring(4));
			} else {
				phone = new Phone(null, null);
			}
			Place place = new Place(venue.get("name").toString(), venue.get("about").toString(),
					venue.get("web_url").toString().replaceAll("\"",""), venue.get("facebook_url").toString().replaceAll("\"",""),
					venue.get("twitter_url").toString().replaceAll("\"",""), address);
			Event event = new Event(Integer.parseInt(obj.get("id").toString()), obj.get("name").toString().replaceAll("\"",""),
					obj.get("url").toString().replaceAll("\"",""), obj.get("content").toString().replaceAll("\"",""), true, place, obj.get("start").toString(),
					obj.get("end").toString(), format.get("name").toString().replaceAll("\"",""), category.get("name").toString().replaceAll("\"",""),
					obj.getAsJsonArray("tags"), phone);
	
			events.put(event.getEventID(),event);

		}

	}
	public void defaultAdmin() {
		Phone p = new Phone("555","5555555");
		Address ar = new Address("Address","İzmir","Buca","ValiRahmiBey");
		Actor a = new Admin("name","surname",p,ar,"email","admin","admin","admin");
		actors.put("admin", a);
	}

	public boolean signUP(String name, String surname, String email, String pCityCode, String pNumber, String nickname,
			String password) {
		if (actors.get(nickname) == null) {
			Phone p = new Phone(pCityCode, pNumber);
			Actor a = new User(name, surname, email, p, nickname, password);
			actors.put(nickname, a);
			return true;
		}
		else 
			return false;
		
	}

	public boolean Login(String nickname, String password) {
		if (actors.get(nickname)!=(null)) {
			if(password.equals(actors.get(nickname).getPassword())) 
			    return true;
			
			else
				return false;
		} else 
			return false;
		
	}

	
	
	public Set<Event> search(String name, String city, String tag){
		Set<Event> nameSearch = new HashSet();
		if(name!=null) {
			for(Event e : events.values()) {
				if(e.getEventName().equalsIgnoreCase(name)) {
					nameSearch.add(e);
				}	
			}
		}
		
		Set<Event> citySearch = new HashSet();
		if(city!=null) {
			for(Event e : events.values()) {
				if(e.getPlace().getAddress().getCity().equalsIgnoreCase(city))
					citySearch.add(e);
			}
		}
		
		Set<Event> tagSearch = new HashSet();
		if(tag!=null) {
			for(Event e : events.values()) {
				if(e.getCategory().equals(tag) || e.getFormat().equalsIgnoreCase(tag))
					tagSearch.add(e);
				for(String tags : e.getTags()) {
					if(tags.equalsIgnoreCase(tag))
						tagSearch.add(e);
				}
			}
		}
		if(!nameSearch.isEmpty()) {
			if(!citySearch.isEmpty())
				nameSearch.retainAll(citySearch);
			
			if(!tagSearch.isEmpty())
				nameSearch.retainAll(tagSearch);
			return nameSearch;
		}
		else if(!citySearch.isEmpty()) {
			if(!nameSearch.isEmpty())
				citySearch.retainAll(nameSearch);
			
			if(!tagSearch.isEmpty())
				citySearch.retainAll(tagSearch);
			return citySearch;
		}
		else if(!tagSearch.isEmpty()) {
			if(!nameSearch.isEmpty())
				tagSearch.retainAll(nameSearch);
			
			if(!citySearch.isEmpty())
				tagSearch.retainAll(citySearch);
			return tagSearch;
		}
		else
			return null;
	}
	
 
	
	public HashMap<Integer,Event> getEvents() {
		return events;
	}

}