package com.coding.app.restcontrollers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(name = "/api/voyage", produces = MediaType.APPLICATION_XML_VALUE, value = "/reader")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ModelReader {

	@GetMapping
	public String gettModel() throws IOException {
		File file = new File("D:\\face.xml");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String xml = "";
		Stream<String> lines = reader.lines();
		List<String> linesList = lines.toList();
		for (String line : linesList) {
			xml += line;
		}
		reader.close();
		return xml;

	}
}
