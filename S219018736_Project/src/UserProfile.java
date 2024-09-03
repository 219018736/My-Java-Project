
public class UserProfile implements Comparable<UserProfile>, java.io.Serializable{

	private static final long serialVersionUID = 1L;
	//Declaring my variables
	private String name;
	private String surname;
	private int funds;
	private int latitude;
	private int longitude;
	private int numPeople;
	
	public UserProfile(int latitude,int lonitude,int numPeople,String name,String surname,int funds) {
		this.name = name;
		this.surname = surname;
		this.funds = funds;
		this.latitude = latitude;
		this.numPeople = numPeople;
	}
	
	//sets and gets for creating of user profile
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setSurname(String surname) {
		this.surname= surname;
	}
	public String getSurname() {
		return surname;
	}
	
	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}
	public int getNumPeople() {
		return numPeople;
	}
	
	public void setFunds(int funds) {
		this.funds = funds;
	}
	public int getFunds() {
		return funds;
	}
	
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public int getLatitude() {
		return latitude;
	}
	
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	public int getLongitude() {
		return longitude;
	}
	@Override
	public int compareTo(UserProfile o) {
		
		if(o.getName() == this.getName() && o.getLongitude() == this.getLongitude() && 
			o.getLatitude() == this.getLatitude() && o.getName() == this.getName() && o.getSurname() == this.getSurname() && 
			o.getFunds() == this.getFunds() && o.getNumPeople() == this.getNumPeople()) {
			 return 0;
			}else {
				return -1;
		}
	}
	
	@Override
	public String toString() {
	  return name;
	}
}
