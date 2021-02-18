package src;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AI {

	Management m = new Management();
    Set<Event> interest ;
    public Set<Event> createInterests(Queue q) {
    	interest = new HashSet<Event>();
    	for(Object o : q.values()) {
    		if(o!=null) {
    			for(String s : m.getEvents().get(o).getTags()) {
        			for(Event e : m.search(null, m.getEvents().get(o).getPlace().getAddress().getCity(), s)) {
        				interest.add(e);
        			}
        		}
    		}
    	}
    	return interest;
    }
    
    public boolean curseDetector(String comment) {
    	Boolean found=false;
    	ArrayList<String> curseWords = new ArrayList<String>();
    	curseWords.add("stupid");
    	curseWords.add("idiot");
    	curseWords.add("silly");
        
    	for (String s : curseWords ) {
    		if(!found)
    			found = Arrays.asList(comment.split(" ")).contains(s);
    		
		}
    	
    	return !found;
       
    	
    }


}
