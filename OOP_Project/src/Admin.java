package src;

import java.util.ArrayList;

public class Admin extends Actor{
    Management m = new Management();
	
	public Admin(String name, String surname, Phone phone, Address address, String email, String type, String nickname,
			String password) {
		super.name = name;
		super.surname = surname;
		super.phone = phone;
		super.address = address;
		super.email = email;
		super.type = type;
		super.nickname = nickname;
		super.password = password;
	}
	/*
	public void deleteComment(String nickname, int eventID) {
		for(Event e :m.getEvents().values()) {
			if (e.getComments().size() != 0) {
				for (Comment c : e.getComments()) {
					if (c.getUserNickname().equals(nickname) && c.getEventID() == eventID) {
						e.getComments().remove(c);
					}
				}
			}
		}
	}
	*/
	public ArrayList<Comment> showAllComments() {
		ArrayList<Comment> com = new ArrayList<Comment>();
		for(Event e :m.getEvents().values()) {
			if(e.getComments().size()!=0) {
				for (Comment c :e.getComments()) {
					com.add(c);
				}
			}
		}
		return com;
	}
	public void blockUser(String nickname) {
		User u = (User) m.getActors().get(nickname);
		u.setBlockked(true);
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
	
	public ArrayList<User> getNotifications() {
		ArrayList<User> users = new ArrayList<User>();
		for(Event e :m.getEvents().values()) {
			if(e.getComments().size()!=0) {
				for (Comment c :e.getComments()) {
					if (!c.isDisplayeble()) {
						users.add((User) m.getActors().get(c.getUserNickname()));
					}
				
				}
			}
		}
		return users;
	}
	
	
}
