package itp341.poole.connor.a5.app;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

import android.content.Context;

public class Note {
	@Expose private String title;
	@Expose private String content;
	@Expose private Date ISO8061StringModified;
	@Expose private Date ISO8061StringCreated;
	public static final String ISO_DATE_FORMAT = "yyy-MM-dd'T'HH:mm:ss.SSSZ";
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDateCreated(){
		return ISO8061StringCreated;
	}
	public void setDateCreated(Date date){
		this.ISO8061StringCreated = date;
	}
	public Date getDateModified(){
		return ISO8061StringModified;
	}
	public void setDateModified(Date date){
		this.ISO8061StringModified = date;
	}
}
