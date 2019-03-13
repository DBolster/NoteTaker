package mainPack;

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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConsoleMethods implements Interface_NoteTaker {

	private String fileName = "";
	private File myFile = null;

	public ConsoleMethods(String fileNameInput) {
		fileName = fileNameInput;
		// this.myFile = new File(fileName);
		createCourseFile();
	}

	@Override
	public int createCourseFile() {
		// create .courses file unless it already exists
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
		return 0;
	}

	@Override
	public boolean findCourse(String course) {
		String courseToMatch = "";
		try (Scanner dupeScanner = new Scanner(myFile)) {
			while (dupeScanner.hasNextLine()) {
				courseToMatch = dupeScanner.nextLine();
				if (course.equals(courseToMatch)) {
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
		String courseList;
		StringBuilder sb = new StringBuilder();
		try (Scanner listScanner = new Scanner(myFile);) {
			while (listScanner.hasNextLine()) {
				sb.append(listScanner.nextLine() + "\n");
			}
			if (sb.length() == 0) {
				courseList = "";
			} else {
				courseList = sb.toString();
			}
		} catch (IOException ex) {
			System.out.println("Cannot read " + fileName + " file");
			courseList = "";
		}
		return courseList;
	}

	@Override
	public int newNote(String courseNameParameter) {
		if (this.findCourse(courseNameParameter)) {
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

					// Open Text Editor
					// System.out.println(setTextEditor());
					String processPath = setTextEditor();
					ProcessBuilder notePadPb = new ProcessBuilder(processPath,
							courseNameParameter + "\\" + courseNoteNameString);
					try {
						@SuppressWarnings("unused")
						Process notePadProcess = notePadPb.start();
					} catch (IOException e) {
						System.out.println(
								"Cannot open text editor for editing, please open note using another editor and check noteTaker.xml");
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
	public boolean purgeCourses() {

		return myFile.delete();

	}

	@Override
	public String setTextEditor() {
		// Get the path of the text editor to use from the xml file
		String filePath = "noteTaker.xml";
		String textEditorPath = "";
		try {
			File textEditorXML = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbuilder;
			dbuilder = dbFactory.newDocumentBuilder();
			Document doc = dbuilder.parse(textEditorXML);
			NodeList nodeList = doc.getElementsByTagName("TextEditor");
			Node node = nodeList.item(0);
			Element element = (Element) node;
			textEditorPath = element.getElementsByTagName("Path").item(0).getTextContent();
			// textEditorPath = nodeList.item(1).getNodeValue();
			textEditorPath = textEditorPath.replace("\\", "\\\\");
			// DEBUG
			// System.out.println(textEditorPath);
			//
		} catch (ParserConfigurationException e) {
			System.out.println("Parser config error");
			// e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("SAX exception");
			// e.printStackTrace();
		} catch (FileNotFoundException ex) {
			System.out.println("File not found exception in setTextEditor function");
			// ex.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO exception in setTextEditor");
			// e.printStackTrace();
		}
		return textEditorPath;
	}
}
