package com.geocalc.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

/**
 * Goe calc app supposed to invite people inside 50 km geo spatial into the
 * company party
 */
public class GeoCalcApp {
	public static void main(String[] args) {
		
		final String jsonDir = "./resources/staff.json";
		final String destDir = "C:\\Users\\elias.rahmani\\Desktop\\Java\\InvitedPeople.json";
		IJsonParser jp = new JsonParser(jsonDir, destDir, 35.745108, 51.451365);
		try {
			jp.filter("lat", "long", "people", 50);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Can't read file");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Can't parse file");
			e.printStackTrace();
		}
	}

}
