package src;
import java.util.ArrayList;

public class Comment {
	private static AI ai = new AI();
	private String comment;
	private String userNickname;
	private int eventID;
	private boolean displayeble;
	
	
	public Comment(String comment, String nickname, int eventID) {
		this.comment=comment;
		this.userNickname=nickname;
		this.eventID=eventID;
		this.displayeble=ai.curseDetector(comment);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public boolean isDisplayeble() {
		return displayeble;
	}

	public void setDisplayeble(boolean displayeble) {
		this.displayeble = displayeble;
	}

	@Override
	public String toString() {
		if(this.displayeble)
			return   userNickname +":    "+comment ;
		else 
			return "";
	}

	
	
	
	
}
