package mainPack;

//import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStreamReader;

public class Controller {

	public Controller() {
		String fileName = "courses.txt";
		File myFile = new File(fileName);
		if (!myFile.exists())
			try {
				myFile.createNewFile();
			} catch (IOException e1) {
				System.out.println("Cannot create file course.txt");
			}
	}

	public void addCourse(String course) {
		System.out.println("In course creation area");
		// InputStreamReader myInput = new InputStreamReader(System.in);
		// BufferedReader myReader = new BufferedReader(myInput);
		String courseNewLine;
		try (FileWriter fileWrite = new FileWriter("courses.txt", true)) {
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
