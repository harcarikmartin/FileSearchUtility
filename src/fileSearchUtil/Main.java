package fileSearchUtil;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
	private static final String PATH = "C:\\Users\\Študent\\workspace\\HelloWorld";
	
	public static void main(String[] args) throws FileNotFoundException {
		FileSearchUtil fsu = new FileSearchUtil();
		
//		for (String string : fsu.findAllFiles(PATH)) {
//			System.out.println(string);
//		}
		System.out.println(Arrays.toString(fsu.findAllFiles(PATH)).replace(',', '\n'));
		
		
	}

}
