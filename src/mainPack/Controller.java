package mainPack;

//import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
//import java.io.InputStreamReader;

public class Controller {

	final String fileName = "courses.txt";
	File myFile = new File(fileName);

	public Controller() {
		//File myFile = new File(fileName);
		if (!myFile.exists())
			try {
				myFile.createNewFile();
			} catch (IOException e1) {
				System.out.println("Cannot create file course.txt");
			}
	}

	public void addCourse(String course) {
		System.out.println("In course creation area");

		System.out.println("in dupe checker");
		String courseToMatch = "";
		//Scanner dupeScanner;
		try {
			Scanner dupeScanner = new Scanner(myFile);
			
			while (dupeScanner.hasNextLine()) {
				courseToMatch = dupeScanner.nextLine();
				if (course.equals(courseToMatch)) {
					System.out.println("Course already in file");
					return;
				}
			}
		} catch (FileNotFoundException e1) {
			System.out.println("File " + fileName + " not found");
		}
		// String courseToCheck = course + "\r\n";
		

		

		String courseNewLine;
		try (FileWriter fileWrite = new FileWriter(fileName, true)) {
			courseNewLine = course + "\r\n"; // add newline
			fileWrite.write(courseNewLine);
			System.out.println("Course " + course + " Added to courses.txt");
		} catch (IOException e) {
			System.out.println("IO Error, unable to add course, please check file courses.txt");
		}

		return;
	}

	public void listCourses() {
		System.out.println("Course listing area");
		return;
	}

	public void createNote(String string) {
		System.out.println("New note area");
		return;
	}

	public void showHelp() {
		System.out.println("help area");
		return;
	}

}
