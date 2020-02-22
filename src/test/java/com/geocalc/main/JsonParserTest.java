package com.geocalc.main;

import org.junit.Before;
import org.junit.Test;

public class JsonParserTest {
	private final static String jsonDir = "./resources/staff.json";
	private final static String destDir = "C:\\Users\\elias.rahmani\\Desktop\\Java\\InvitedPeople.json";
	private final static double distance = 41.567467957853815;
	private final static double originLat = 35.745108;
	private final static double originLong = 51.451365;
	private JsonParser jp;
	
	@Before
	public void beforeAllTest() {
		jp = new JsonParser(jsonDir, destDir, originLat, originLong);
	}
	
	@Test
	public void filterTest() {
		
	}
	
}
