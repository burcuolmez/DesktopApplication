package src;
public class Address {
	private String address;
	private String city;
	private String town;
	private String neighborhood;

	public Address(String address,String city, String town, String neighborhood) {
		super();
		this.address=address;
		this.city = city;
		this.town = town;
		this.neighborhood = neighborhood;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Override
	public String toString() {
		return   address + ", city: " + city + ", town: " + town + ", neighborhood: " + neighborhood;
	}

	
}

