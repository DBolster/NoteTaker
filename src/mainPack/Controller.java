package mainPack;

//import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
//import java.io.InputStreamReader;

public class Controller {

	final String fileName = ".courses";
	File myFile = null;

	public Controller() {
		// URI courseListURI = null;
		// URI courseListURI = null;
		// try {
		// courseListURI = new URI("file:///" + fileName);
		// } catch (URISyntaxException e) {
		// System.out.println("URI syntax error");
		// }
		// myFile = new File(courseListURI.toString());
		myFile = new File(fileName);
		if (!myFile.exists())
			try {
				myFile.createNewFile();
				if (!myFile.canWrite() && !myFile.canRead()) {
					System.out.println("Cannot read or write to file, please check permissions");
					System.exit(-1);
				}
			} catch (IOException e1) {
				System.out.println("Cannot create courses file");
				System.exit(-1);
			}
	}

	public void addCourse(String course) {
		System.out.println("In course creation area");

		System.out.println("in dupe checker");
		String courseToMatch = "";
		// Scanner dupeScanner;
		try {
			Scanner dupeScanner = new Scanner(myFile);

			while (dupeScanner.hasNextLine()) {
				courseToMatch = dupeScanner.nextLine();
				if (course.equals(courseToMatch)) {
					System.out.println("Course already in file");
					System.exit(-1);
				}
			}
		} catch (FileNotFoundException e1) {
			System.out.println("File " + fileName + " not found");
			System.exit(-1);
		}
		// String courseToCheck = course + "\r\n";

		String courseNewLine;
		try (FileWriter fileWrite = new FileWriter(fileName, true)) {
			courseNewLine = course + "\r\n"; // add newline
			fileWrite.write(courseNewLine);
			System.out.println("Course " + course + " Added to courses file");
		} catch (IOException e) {
			System.out.println("IO Error, unable to add course, please check courses file");
			System.exit(-1);
		}

		return;
	}

	public void listCourses() {
		System.out.println("Course listing area");
		return;
	}

	public void createNote(String courseName) {

		boolean exists = false;
		// Check for course in courses.txt
		try {
			Scanner dupeScanner = new Scanner(myFile);
			while (dupeScanner.hasNextLine()) {
				String existingCourseName = dupeScanner.nextLine();
				if (courseName.equals(existingCourseName)) {
					exists = true;
					break;
				}
			}
		} catch (FileNotFoundException e1) {
			System.out.println("File " + fileName + " not found");
			System.exit(-1);
		}

		if (exists) {
			// Set Course Note name
			Calendar date = Calendar.getInstance();
			//use getDisplayName() here
			String courseNoteName = courseName + "_"+ date.getDisplayName(Calendar.DAY_OF_MONTH, Calendar.SHORT_FORMAT, Calendar.) + 
					"_" + date.get(Calendar.MONTH) + "_" + date.get(Calendar.HOUR) + 
					date.get(Calendar.MINUTE) + date.get);
			//courseNoteName = courseNoteName.replace(" ", "");
			//URI courseNoteURI = null;

//			try {
//				courseNoteURI = new URI("file:///" + courseNoteName);
//			} catch (URISyntaxException e1) {
//				System.out.println("URI syntax error");
//				System.exit(-1);
//			}
			File courseNotes = new File(courseNoteName + ".txt");

			// Create Note File
			if (!courseNotes.exists()) {
				try {
					courseNotes.createNewFile();
				} catch (IOException e) {
					System.out.println("IO Error Cannot create new course note");
					System.exit(-1);
				}
				if (courseNotes.canWrite() && courseNotes.canRead()) {

					// Write Date header to course file
					try (FileWriter fileWrite = new FileWriter(courseNotes, true)) {
						fileWrite.write(courseNoteName);
					} catch (IOException ex) {
						System.out.println("Cannot write to file " + courseNoteName);
						System.exit(-1);
					}
				} else {
					System.out.println("Cannot read or write to file, please check permissions");
					System.exit(-1);
				}
			} else {
				System.out.println("Course note by this name already exits");
				System.exit(-1);
			}

			return;
		} else {
			System.out.println("Course " + courseName + " not found, please add course");
			System.exit(-1);
		}
	}

	public void showHelp() {
		System.out.println("Program use:  NoteTaker -ac/lc/nn/h optional: <Course Name> ");
		System.out.println("-ac <Course Name>  Add course, adds a course to the course list");
		System.out.println("Example: NoteTaker -ac COMP450");
		System.out.println("-lc        		   List the courses in the course list, no additional parameters");
		System.out.println("-nn <Course Name>  New note, creates a new text file datestamped with the course name");
		System.out.println("Example: NoteTaker -nn COMP43");
		System.out.println("-h                 Displays this help page");
		System.out.println("End of help page");

		return;
	}

}
