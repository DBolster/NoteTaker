package mainPack;

//import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Scanner;

public class Controller {

	final String fileName = ".courses";
	File myFile = null;

	public Controller() {
		//create .courses.txt file unless it already exists
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
		//check for duplicate courses
		String courseToMatch = "";
		try {
			Scanner dupeScanner = new Scanner(myFile);

			while (dupeScanner.hasNextLine()) {
				courseToMatch = dupeScanner.nextLine();
				if (course.equals(courseToMatch)) {
					System.out.println("Course of same name already registered, course not added");
					System.exit(-1);
				}
			}

			dupeScanner.close();
		} catch (FileNotFoundException e1) {
			System.out.println("File " + fileName + " not found");
			System.exit(-1);
		}
		
		//add course to .courses.txt file
		String courseNewLine;
		try (FileWriter fileWrite = new FileWriter(fileName, true)) {
			courseNewLine = course + "\r\n"; // add newline
			fileWrite.write(courseNewLine);
			System.out.println("Course " + course + " added to courses file");
			System.out.println("Course " + course + " directory created");
			File courseDir = new File(course);
			courseDir.mkdir();
		} catch (IOException e) {
			System.out.println("IO Error, unable to add course, please check courses file");
			System.exit(-1);
		}

		return;
	}

	public void listCourses() {
		System.out.println("Courses listed in course file \".courses.txt\" ");
		//List courses in .courses.txt file
		try {
			Scanner listScanner = new Scanner(myFile);

			while (listScanner.hasNextLine()) {
				String courseName = listScanner.nextLine();
				System.out.println(courseName);
			}
			listScanner.close();
		} catch (IOException ex) {
			System.out.println("Cannot read .courses.txt file");
			System.exit(-1);
		}

	}

	public void newNote(String courseNameParameter) {

		boolean exists = false;
		//Scan file for existing notes
		try {
			Scanner dupeScanner = new Scanner(myFile);

			while (dupeScanner.hasNextLine()) {
				String existingCourseName = dupeScanner.nextLine();
				if (courseNameParameter.equals(existingCourseName)) {
					exists = true;
					break;
				}
			}

			dupeScanner.close();
		} catch (FileNotFoundException e1) {
			System.out.println("File " + fileName + " not found");
			System.exit(-1);
		}

		if (exists) {
			// Set Course Note name
			Instant timeInstant = Instant.now();
			Locale locale = new Locale("eng", "CA");
			ZoneId timeZoneId = ZoneId.of("America/Toronto");
			ZonedDateTime zoneDateTime = timeInstant.atZone(timeZoneId);
			DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
					.withLocale(locale);
			String currentDateTime = zoneDateTime.format(dateTimeFormat);

			String courseNoteNameString = courseNameParameter + "_" + currentDateTime;
			courseNoteNameString = courseNoteNameString.replace(" ", "_");
			courseNoteNameString = courseNoteNameString.replace(",", "");
			courseNoteNameString = courseNoteNameString.replace(":", "_");
			courseNoteNameString += ".txt";
			System.out.println("creating directory");
			System.out.println("creating file");
			File courseNoteFile = new File(courseNameParameter, courseNoteNameString);

			// Create Note File
			if (courseNoteFile.exists()) {
				System.out.println("Course note by this name already exits");
				System.exit(-1);
			} else {
				try {
					courseNoteFile.createNewFile();
				} catch (IOException e) {
					System.out.println("IO Error Cannot create new course note");
					System.exit(-1);
				}
				if (courseNoteFile.canWrite() && courseNoteFile.canRead()) {

					// Write Date header to course file
					try (FileWriter fileWrite = new FileWriter(courseNoteFile, true)) {
						fileWrite.write(courseNoteNameString.replace(".txt", ""));
					} catch (IOException ex) {
						System.out.println("Cannot write to file " + courseNoteNameString);
						System.exit(-1);
					}

					// Open Notepad
					ProcessBuilder notePadPb = new ProcessBuilder("C:\\Program Files\\Notepad++\\Notepad++.exe",
							courseNameParameter + "\\" + courseNoteNameString);
					try {
						@SuppressWarnings("unused")
						Process notePadProcess = notePadPb.start();
					} catch (IOException e) {
						System.out.println("Cannot open notepad for editing, please open using another editor");
						System.exit(-1);
					}
				} else {
					System.out.println("Cannot read or write to file, please check permissions");
					System.exit(-1);
				}

			}

			return;
		} else {
			System.out.println("Course " + courseNameParameter + " not found, please add course");
			System.exit(-1);
		}
	}

	public void showHelp() {
		//Display help options
		System.out.println("Program use:  NoteTaker -ac/lc/nn/h optional: <Course Name> ");
		System.out.println("-ac <Course Name>  Add course, adds a course to the course list");
		System.out.println("Example: NoteTaker -ac COMP450");
		System.out.println("-lc  List the courses in the course list, no additional parameters");
		System.out.println("-nn <Course Name>  New note, creates a new text file datestamped with the course name");
		System.out.println("Example: NoteTaker -nn COMP43");
		System.out.println("-h  Displays this help page");
		System.out.println("End of help page");

		return;
	}

}
