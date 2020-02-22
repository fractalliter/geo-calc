package com.geocalc.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public interface IJsonParser {
	void filter(String latKey, String longKey, String outputKey, int distance) throws FileNotFoundException, IOException, ParseException;
}
