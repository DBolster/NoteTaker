package mainPack;

import java.util.Scanner;

public class ConsoleMenu {

	private StringBuilder sb;
	private String choice, courseFileName;
	private int numChoice;
	private ConsoleMethods nt;
	boolean validIO = false;

	public ConsoleMenu() {
		courseFileName = ".courses";
		nt = new ConsoleMethods(courseFileName);
		sb = new StringBuilder();
	}

	public void showMainMenu() {
		sb.append("NoteTaker Main Menu\n\n");
		sb.append("Please choose an option from 1 to 4, Q to quit\n\n");
		sb.append("1.New Note\n");
		sb.append("2.List Courses\n");
		sb.append("3.Add Course\n");
		sb.append("4.Purge Course List\n\n");

		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner inputScanner = new Scanner(System.in);

		System.out.println(sb.toString());

		choice = inputScanner.next();
		choice = choice.toUpperCase();
		choice.trim();

		// while (!validIO) {
		// try {
		// choice = br.readLine();
		// choice = choice.trim();
		// validIO = true;
		// } catch (IOException ex) {
		// System.out.println("Input Error, please try again");
		// }
		// }

		while (!choice.equals("Q") && !choice.equals("q")) {
			try {
				numChoice = Integer.parseInt(choice);
				if (numChoice < 1 || numChoice > 4) {
					System.out.println("Please pick a number from 1 to 4");
				} else {
					switch (numChoice) {
					case 1:
						System.out.println("Your Courses Include:");
						System.out.println(nt.listCourses());
						System.out.println("Please type a course name to create a note:");
						choice = inputScanner.next();
						nt.newNote(choice);
						break;
					case 2:
						System.out.println(nt.listCourses());
						break;
					case 3:
						System.out.println("Please type the name of the course to add:");
						choice = inputScanner.next();
						nt.addCourse(choice);
						break;
					case 4:
						nt.purgeCourses();
						break;
					}
				}
			} catch (NumberFormatException ex) {
				System.out.println("The input must be a number from 1 to 4, please try again");
			} finally {
				System.out.println(sb.toString());
				choice = inputScanner.next();
			} // finally
		} // while
		inputScanner.close();
		System.out.println("Exiting Program");
	} // showMainMenu method
} // class
