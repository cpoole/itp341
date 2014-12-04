package itp341.poole.connor.a5.app;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.ParseException;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ISOtimeDeserializer implements JsonDeserializer<Date> {
	private static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	@Override
	public Date deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		Date date= null;
		SimpleDateFormat format = new SimpleDateFormat(ISO_DATE_FORMAT);
		try{
			date = format.parse(json.getAsString());
		}catch (ParseException e){
			date = null;
		}
		return date;
	}
}
