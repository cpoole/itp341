package itp431.poole.connor.finals.project.app;

import com.google.gson.annotations.*;

public class menuItem {
	@Expose private String type;
	@Expose private String title;
	@Expose private String description;
	@Expose private float price;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

}
