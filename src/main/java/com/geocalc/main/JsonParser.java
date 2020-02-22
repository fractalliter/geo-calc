package com.geocalc.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser implements IJsonParser {
	
	private final String fileDir;
	private final String destinationDir;
	private final double latitude;
	private final double longitude;
	
	public JsonParser(String fileDir, String destinationDir, double latitude, double longitude) {
		this.fileDir = fileDir;
		this.destinationDir = destinationDir;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@SuppressWarnings("unchecked")
	public void filter(String latKey, String longKey, String outputKey, int distance) throws FileNotFoundException, IOException, ParseException {

			JSONObject geo = (JSONObject) readFile(this.fileDir);
			JSONArray people = (JSONArray) geo.get(outputKey);
			JSONObject newGeo = new JSONObject();
			
			newGeo.put(outputKey, filterJson(people, latKey, longKey, distance));

			print(newGeo, this.destinationDir);
	}
	
	@SuppressWarnings("unchecked")
	private JSONArray filterJson(JSONArray people, String latKey, String longKey, int  distance) {
		JSONArray invitedPeople = new JSONArray();
		for (int i = 0; i < people.size(); i++) {
			Map<?, ?> person = (Map<?, ?>) people.get(i);
			Double lat = (Double) person.get(latKey);
			Double lon = (Double) person.get(longKey);
			
			if (distance(this.latitude, this.longitude, lat, lon) <= distance)
				invitedPeople.add(person);
		}
		return invitedPeople;
	}

	private static void print(JSONObject obj, String dir) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(dir);
		pw.write(obj.toJSONString());
		pw.flush();
		pw.close();
	}
	
	private static Object readFile(String dir) throws FileNotFoundException, IOException, ParseException {
		return new JSONParser().parse(new FileReader(dir));
	}

	private static double distance(double originLat, double originLon, double destLat, double destLon) {
		if ((originLat == destLon) && (originLon == destLon)) {
			return 0;
		} else {
			double theta = originLon - destLon;
			double dist = Math.sin(Math.toRadians(originLat)) * Math.sin(Math.toRadians(destLat))
					+ Math.cos(Math.toRadians(originLat)) * Math.cos(Math.toRadians(destLat)) * Math.cos(Math.toRadians(theta));
			dist = Math.toDegrees(Math.acos(dist)) * 60 * 1.1515 * 1.609344;
			return (dist);
		}
	}
}
