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
		isValidFile(files);
		for (File file : files.listFiles()) {
			if(file.isFile()) {
				list.add(file.getAbsolutePath());
			}
			if(file.isDirectory()) {
				findAllFiles(file.getPath());
			}
		}
		return list.toArray(new String[] {});
	}
	
	public String[] findAllDirectories(String path) throws FileNotFoundException {
		File files = new File(path);
		isValidFile(files);
		for (File file : files.listFiles()) {
			if(file.isDirectory()) {
				list.add(file.getAbsolutePath());
			}
			if(file.isDirectory()) {
				findAllDirectories(file.getPath());
			}
		}
		return list.toArray(new String[] {});
	}
	
	public String[] findAllFilesAndDirectories(String path) throws FileNotFoundException {
		File files = new File(path);
		isValidFile(files);
		for (File file : files.listFiles()) {
			if(file.isFile()) {
				list.add(file.getAbsolutePath());
			} 
			if(file.isDirectory()){
				findAllFilesAndDirectories(file.getPath());
				list.add(file.getAbsolutePath());
			}
		}
		return list.toArray(new String[] {});
	}
	
	public String[] findFilesByPattern(String pattern, String path) throws FileNotFoundException {
		File files = new File(path);
		isValidFile(files);
		for (File file : files.listFiles()) {
			if(file.isFile() && file.getName().matches(pattern)) {
				list.add(file.getAbsolutePath());
			}
			if(file.isDirectory()){
				findFilesByPattern(pattern, file.getPath());
			}
		}
		return list.toArray(new String[] {});
	}
	
	public String[] findFilesByLastChange(Date date, String path) throws FileNotFoundException {
		File files = new File(path);
		isValidFile(files);
		for (File file : files.listFiles()) {
			if(file.isFile() && file.lastModified() > date.getTime()) {
				list.add(file.getAbsolutePath());
			}
			if(file.isDirectory()){
				findFilesByLastChange(date, file.getPath());
			}
		}
		return list.toArray(new String[] {});
	}
	
	private void isValidFile(File files) throws FileNotFoundException {
		if(!files.exists()) {
			throw new FileNotFoundException("File not found");
		}
	}
	
	
	
}
