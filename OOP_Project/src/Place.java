package src;
public class Place {
	private String name;
	private String about;
	private String webURL;
	private String facebookURL;
	private String twitterURL;
	private Address address;
	
	
	public Place(String name, String about, String webURL, String facebookURL, String twitterURL,
			Address address) {
		this.name = name;
		this.about = about;
		this.webURL = webURL;
		this.facebookURL = facebookURL;
		this.twitterURL = twitterURL;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getWebURL() {
		return webURL;
	}
	public void setWebURL(String webURL) {
		this.webURL = webURL;
	}
	public String getFacebookURL() {
		return facebookURL;
	}
	public void setFacebookURL(String facebookURL) {
		this.facebookURL = facebookURL;
	}
	public String getTwitterURL() {
		return twitterURL;
	}
	public void setTwitterURL(String twitterURL) {
		this.twitterURL = twitterURL;
	}

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return  name + ", about: " + about + ", webURL: " + webURL + ", facebookURL: " + facebookURL
				+ ", twitterURL: " + twitterURL + ", address: " + address.toString() ;
	}
	
	
}
