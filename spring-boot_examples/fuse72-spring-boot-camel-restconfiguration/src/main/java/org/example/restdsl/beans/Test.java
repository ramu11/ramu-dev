package org.example.restdsl.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Test {
	
	

	public static void main(String[] args) {
		
		File file = new File("standalonelogs.txt");  
		System.out.println(file.length());
		try {
			String content = new String(Files.readAllBytes(Paths.get("standalonelogs.txt")));
			System.out.println(content);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
