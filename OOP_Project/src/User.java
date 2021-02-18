package src;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

public class User extends Actor {

	AI ai = new AI();
	Set<Event> interest;
	Queue calendar;
	boolean blockked;
	

	public User(String name, String surname, String email, Phone phone,String nickname, String password) {
		super.name = name;
		super.surname = surname;
		super.email = email;
		super.phone = phone;;
		this.calendar = new Queue(101);
		super.nickname = nickname;
		super.password = password;
		super.type="user";
		this.blockked=false;
		interest = new HashSet<>();
	}

	public boolean isBlockked() {
		return blockked;
	}

	public void setBlockked(boolean blockked) {
		this.blockked = blockked;
	}

	@Override
	public String getName() {
	
		return super.getName();
	}


	@Override
	public void setName(String name) {
	
		super.setName(name);
	}


	@Override
	public String getSurname() {
	
		return super.getSurname();
	}


	@Override
	public void setSurname(String surname) {
	
		super.setSurname(surname);
	}


	@Override
	public Phone getPhone() {
	
		return super.getPhone();
	}


	@Override
	public void setPhone(Phone phone) {
		
		super.setPhone(phone);
	}


	@Override
	public Address getAddress() {
	
		return super.getAddress();
	}


	@Override
	public void setAddress(Address address) {
	
		super.setAddress(address);
	}


	@Override
	public String getEmail() {
		
		return super.getEmail();
	}


	@Override
	public void setEmail(String email) {
		
		super.setEmail(email);
	}


	@Override
	public String getType() {
		
		return super.getType();
	}


	@Override
	public void setType(String type) {
		
		super.setType(type);
	}


	@Override
	public String getNickname() {
	
		return super.getNickname();
	}


	@Override
	public void setNickname(String nickname) {
		
		super.setNickname(nickname);
	}


	@Override
	public String getPassword() {
		
		return super.getPassword();
	}


	@Override
	public void setPassword(String password) {
		
		super.setPassword(password);
	}




	@Override
	public boolean Logout() {
	
		return super.Logout();
	}


	@Override
	public void addComment(String comment, String nickname, Integer eventID) {
	
		super.addComment(comment, nickname, eventID);
	}


	public Queue getCalender() {
		return calendar;
	}



	public void addCalendar(Event event) throws QueueFull, QueueEmpty {
		boolean flag=true;

		if (calendar.isFull()) {
			calendar.dequeue();
		}
		for(Object e :calendar.values()) {
			if(e!=null) {
				if( (Integer) e==event.getEventID()) {
					flag=false; 
					break;
				}
			}
		
		}

		if (flag) 
			calendar.enqueue(event.getEventID());
		else
			JOptionPane.showMessageDialog(null, "Event has been already added to your calendar.");
	}
	
	public Set<Event> getInterest() {
		if(this.calendar.values().length!=0) {
			this.interest=ai.createInterests(this.calendar);
			return this.interest;
			
		}
		else {
			JOptionPane.showMessageDialog(null, "Your calendar is empty");
			return null;
		}
	}
	
	

	

}
