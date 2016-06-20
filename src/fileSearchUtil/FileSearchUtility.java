package fileSearchUtil;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSearchUtility {
	 List<String> listF = new ArrayList<String>();
	 List<String> listD = new ArrayList<String>();
	 List<String> listFD = new ArrayList<String>();
	 
	 public String[] findAllFiles(Path dirPath) throws IOException {
		 DirectoryStream<Path> ds = Files.newDirectoryStream(dirPath);
		 for (Path path: ds) {
			 if(path.toFile().isFile()) {
				 listF.add(path.toFile().getAbsolutePath());
			 }
			 else {
				 findAllFiles(path);
			 }
		 }
		 String[] files = listF.toArray(new String[] { } );
		 return files;
	 }
	 
	 public String[] findAllDirectories(Path dirPath) throws IOException {
		 DirectoryStream<Path> ds = Files.newDirectoryStream(dirPath);
		 for (Path path: ds) {
			 if(path.toFile().isDirectory()) {
				 listD.add(path.toFile().getAbsolutePath());
				 findAllDirectories(path);
			 }
		 }
		 String[] files = listD.toArray(new String[] { } );
		 return files;
	 }
	 
	 public String[] findAllFilesAndDirectories(Path dirPath) throws IOException {
		 DirectoryStream<Path> ds = Files.newDirectoryStream(dirPath);
		 listFD.addAll(listF);
		 listFD.addAll(listD);
		 String[] files = listFD.toArray(new String[] { } );
		 return files;
	 }
	 
	 public String[] findFilesByPattern(String patternString, Path dirPath) throws IOException {
		Pattern pattern = Pattern.compile(patternString);
		
		DirectoryStream<Path> ds = Files.newDirectoryStream(dirPath);
		for (Path path: ds) {
			Matcher matcher = pattern.matcher(path.toFile().toString());
			 if(path.toFile().isFile() && matcher.matches()) {
				 listF.add(path.toFile().getAbsolutePath());
			 } else if(path.toFile().isDirectory()) {
				 findFilesByPattern(patternString, path);
			 }
		}
		String[] files = listF.toArray(new String[] { } );
		return files;
	 }
	 
	 public String[] findFilesByLastChange(String date, Path dirPath) throws IOException {
		 List<String> list = new ArrayList<String>();
		 Path dir = dirPath;
		 
		 DirectoryStream<Path> ds = Files.newDirectoryStream(dir);
		 for (Path file: ds) {
			 if(file.toFile().isDirectory()) {
				 findAllFiles(file);
			 }
			 else {
				 list.add(file.toFile().getPath());
			 }
		 }
		 String[] files = list.toArray(new String[] { } );
		 return files;
	 }
}
