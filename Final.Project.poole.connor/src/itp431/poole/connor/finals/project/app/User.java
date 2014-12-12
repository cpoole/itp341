package itp431.poole.connor.finals.project.app;

import com.google.gson.annotations.*;

public class User {
	@Expose private int NumPunches;
	@Expose private String guid;
	
	public User(){
		
	}
	public User(String numPunches){
		this.NumPunches = Integer.parseInt(numPunches);
	}
	public int getNumPunches() {
		return NumPunches;
	}
	public void setNumPunches(int numPunches) {
		NumPunches = numPunches;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
}
