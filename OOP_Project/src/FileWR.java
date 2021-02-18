package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class FileWR {
	Management m = new Management();
	public void fileWrite() {
		Management m = new Management();
		JSONObject all = new JSONObject();
		JSONArray users = new JSONArray();
		JSONArray comments = new JSONArray();
		JSONArray rates = new JSONArray();
		JSONArray calender = new JSONArray();
		JSONObject obj ;
		for(Actor a : m.getActors().values()) {
			if(a.getType().equals("user")) {
				User u = (User) a;
				obj =  new JSONObject();
				obj.put("name",u.getName());
				obj.put("surname", u.getSurname());
				obj.put("email", u.getEmail());
				obj.put("phone", u.getPhone().toString());
				for(Object o :  u.getCalender().values()) {
					if(o!=null) {
						calender.add(o);
					}
				}
				obj.put("calender", calender);
				obj.put("nickname", u.getNickname());
				obj.put("password", u.getPassword());
				obj.put("blocked", u.isBlockked());
				users.add(obj);
			}
		}
		for(Event e : m.getEvents().values()) {
			for(Comment c : e.getComments()) {
				obj =  new JSONObject();
				obj.put("nickname", c.getUserNickname());
				obj.put("eventID",c.getEventID());
				obj.put("comment",c.getComment());
				comments.add(obj);
			}
			obj =  new JSONObject();
			obj.put("eventRate", e.getRate());
			obj.put("eventRateCount" , e.getRateCount());
			obj.put("eventID", e.getEventID());
			rates.add(obj);
		}
		all.put("users", users);
		all.put("comments",comments);
		all.put("rates", rates);
		 try (FileWriter file = new FileWriter("events.json")) {
			 
	            file.write(all.toJSONString());
	            file.flush();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	public void FileRead() throws IOException, QueueFull, QueueEmpty {
		try {
			File f = new File("events.json");
			BufferedReader in = new BufferedReader(new FileReader(f));
					String inputLine;
					String content ="";
					while ((inputLine = in.readLine()) != null) {
					    content+=(inputLine);
					}
			Gson g=new Gson();
			JsonObject values=g.fromJson(content, JsonObject.class);
			JsonArray comments= values.getAsJsonArray("comments");
			JsonArray rates=values.getAsJsonArray("rates");
			JsonArray users=values.getAsJsonArray("users");
			JsonArray admins= values.getAsJsonArray("admins");
			JsonObject obj;
			for (int i = 0; i < comments.size(); i++) {
				obj=(JsonObject) comments.get(i);
				Comment c = new Comment(obj.get("comment").getAsString(),obj.get("nickname").getAsString(),obj.get("eventID").getAsInt());
				if(m.getEvents().get(obj.get("eventID").getAsInt())!=null)
					m.getEvents().get(obj.get("eventID").getAsInt()).getComments().add(c);
			}
			for (int i = 0; i < rates.size(); i++) {
				obj=(JsonObject) rates.get(i);
				int eventID= obj.get("eventID").getAsInt();
				int rateCount= obj.get("eventRateCount").getAsInt();
				double rate= obj.get("eventRate").getAsInt();
				if(m.getEvents().get(eventID)!=null) {
					m.getEvents().get(eventID).setRate(rate);
					m.getEvents().get(eventID).setRateCount(rateCount);
				}		
			}
			for (int i = 0; i < users.size(); i++) {
				obj=(JsonObject) users.get(i);
				String password = obj.get("password").getAsString();
				boolean blockked =  obj.get("blocked").getAsBoolean();
				String phone =  obj.get("phone").getAsString();
				String surname = obj.get("surname").getAsString();
				String nickname = obj.get("nickname").getAsString();
				String email = obj.get("email").getAsString();
				String name = obj.get("name").getAsString();
				m.signUP(name, surname, email, phone.substring(0,3), phone.substring(3), nickname, password);
				JsonArray calender =  obj.get("calender").getAsJsonArray();
				User u = (User) m.getActors().get(nickname);
				u.setBlockked(blockked);
				for (int j = 0; j < calender.size(); j++) {
					int value=calender.get(j).getAsInt();
					if(m.getEvents().get(value)!=null)
						u.addCalendar(m.getEvents().get(value));
				}
			}
		} catch (Exception e) {
			
		}
		
		
		
	}
	
}

