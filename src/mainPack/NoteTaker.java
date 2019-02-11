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

public class CmdFunctions implements courseFunctions {

	private String fileName = "";
	private File myFile = null;

	public CmdFunctions(String fileNameInput) {
		fileName = fileNameInput;
		this.myFile = new File(fileName);
	}

	@Override
	public int createCourseList() {
		// create .courses.txt file unless it already exists
		myFile = new File(fileName);
		if (!myFile.exists())
			try {
				myFile.createNewFile();
				if (!myFile.canWrite() && !myFile.canRead()) {
					System.out.println("Cannot read or write to file, please check permissions");
					return 1;
				}
			} catch (IOException e1) {
				System.out.println("Cannot create courses file");
				return 1;
			}
		return 1;
	}

	@Override
	public boolean findCourse(String course) {
		String courseToMatch = "";
		try (Scanner dupeScanner = new Scanner(myFile)) {
			while (dupeScanner.hasNextLine()) {
				courseToMatch = dupeScanner.nextLine();
				if (course.equals(courseToMatch)) {
					// System.out.println("Course found");
					return true;
				}
			}
		} catch (FileNotFoundException e1) {
			System.out.println("File " + fileName + " not found");
			return false;
		}
		return false;
	}

	@Override
	public int addCourse(String course) {
		// add course to .courses.txt file
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
			return 1;
		}
		return 0;
	}

	@Override
	public String listCourses() {
		// System.out.println("Courses listed in course file \".courses.txt\" ");
		// List courses in .courses.txt file

		StringBuilder sb = new StringBuilder();
		try (Scanner listScanner = new Scanner(myFile);) {

			while (listScanner.hasNextLine()) {
				sb.append(listScanner.nextLine() + "/n");
			}
		} catch (IOException ex) {
			System.out.println("Cannot read " + fileName + " file");
			return "";
			// System.exit(-1);
		}
		String courseList = sb.toString();
		return courseList;
	}

	@Override
	public int newNote(String courseNameParameter) {
		if (!this.findCourse(courseNameParameter)) {
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
				return 1;
			} else {
				try {
					courseNoteFile.createNewFile();
				} catch (IOException e) {
					System.out.println("IO Error Cannot create new course note");
					return 1;
				}
				if (courseNoteFile.canWrite() && courseNoteFile.canRead()) {

					// Write Date header to course file
					try (FileWriter fileWrite = new FileWriter(courseNoteFile, true)) {
						fileWrite.write(courseNoteNameString.replace(".txt", ""));
					} catch (IOException ex) {
						System.out.println("Cannot write to file " + courseNoteNameString);
						return 1;
					}

					// Open Notepad++, should change this to a param
					ProcessBuilder notePadPb = new ProcessBuilder("C:\\Program Files\\Notepad++\\Notepad++.exe",
							courseNameParameter + "\\" + courseNoteNameString);
					try {
						@SuppressWarnings("unused")
						Process notePadProcess = notePadPb.start();
					} catch (IOException e) {
						System.out.println("Cannot open notepad for editing, please open using another editor");
						return 1;
					}
				} else {
					System.out.println("Cannot read or write to file, please check permissions");
					return 1;
				}

			}

			return 0;
		} else {
			System.out.println("Course " + courseNameParameter + " not found, please add course");
			return 1;
		}
	}

	@Override
	public void showHelp() {
		// Display help options
		StringBuilder sb = new StringBuilder();
		sb.append("Program use:  NoteTaker -ac/lc/nn/h optional: <Course Name>/n");
		sb.append("-ac <Course Name>  Add course, adds a course to the course list/n");
		sb.append("Example: NoteTaker -ac COMP450/n");
		sb.append("-lc  List the courses in the course list, no additional parameters/n");
		sb.append("-nn <Course Name>  New note, creates a new text file datestamped with the course name/n");
		sb.append("Example: NoteTaker -nn COMP43/n");
		sb.append("-h  Displays this help page/n");
		sb.append("End of help page");
		System.out.println(sb.toString());
		return;
	}

	@Override
	public void purgeCourses() {

		String confirm;
		Scanner confirmChoice = new Scanner(System.in);
		System.out.println("***Warning, this will delete your .courses.txt index file***");
		System.out.println("Press Y to continue or N to exit");
		confirm = confirmChoice.next();
		confirm.trim();
		confirm = confirm.toUpperCase();
		while ((!confirm.equals("Y")) && (!confirm.equals("N"))) {
			System.out.println("Please enter Y or N:");
			confirm = confirmChoice.next();
			confirm.trim();
			confirm = confirm.toUpperCase();
		}

		switch (confirm) {
		case "Y":
			myFile.delete();
			System.out.println(".courses.txt file deleted");
			break;
		case "N":
			System.out.println("exiting with no changes made");
			confirmChoice.close();
		}
		confirmChoice.close();
	}
}