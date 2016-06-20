package fileSearchUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FileSearchUtil {
	List<String> list = new ArrayList<>();
	
	public String[] findAllFiles(String path) throws FileNotFoundException {
		File files = new File(path);
		for (File file : files.listFiles()) {
			if(file.exists()) {
				if(file.isFile()) {
					list.add(file.getAbsolutePath());
				}
				if(file.isDirectory()) {
					findAllFiles(file.getPath());
				}
			} else {
				throw new FileNotFoundException("File not found");
			}
		}
		return list.toArray(new String[] {} );
	}
	
	public String[] findAllDirectories(String path) {
		
		
		return null;
	}
	
	public String[] findAllFilesAndDirectories(String path) {
		
		
		return null;
	}
	
	public String[] findFilesByPattern(String pattern, String path) {
		
		
		return null;
	}
	
	public String[] findFilesByLastChange(Date date, String path) {
		
		
		return null;
	}
	
}
