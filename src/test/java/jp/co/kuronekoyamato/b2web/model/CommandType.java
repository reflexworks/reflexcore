package jp.co.kuronekoyamato.b2web.model;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandType {

	public static void main(String[] args) throws IOException {

		System.out.println("=== TEST1(Groupが２つ） ===");
		String line = "/11223344-555/new";
		System.out.println("val:"+line);
		System.out.println("i:"+getcommand(line));

		line = "/11223344-/new";
		System.out.println("val:"+line);
		System.out.println("i:"+getcommand(line));
		
		line = "/11223344-/1234abc/edit";
		System.out.println("val:"+line);
		System.out.println("i:"+getcommand(line));

	}

	public static String postpattern[] = { 
		  "^/([0-9]{8})-(?:[0-9]{3}|)/([a-zA-Z0-9@]+)/edit$",
		  "^/([0-9]{8})-(?:[0-9]{3}|)/new$",
		};

	public static int getcommand(String src) {

		for (int i = 0; i < postpattern.length; i++) {
			Pattern pattern = Pattern.compile(postpattern[i]);
			Matcher matcher = pattern.matcher(src);

			if (matcher.find()) {
				return i;
			}
		}
		return -1;		
	}
	
	
}
