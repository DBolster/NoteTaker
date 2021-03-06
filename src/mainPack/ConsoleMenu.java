package mainPack;

import java.util.Scanner;

public class ConsoleMenu {

	private StringBuilder sb;
	private ConsoleMethods nt;
	private static String courseFileName = ".courses";

	public ConsoleMenu() {

		nt = new ConsoleMethods(courseFileName);
		sb = new StringBuilder();
	}

	public void printMainMenu() {

		int numChoice;
		String choice, courseList;
		Scanner inputScanner = new Scanner(System.in);

		sb.append("NoteTaker Main Menu\n\n");
		sb.append("Please choose an option from 1 to 4, Q to quit\n\n");
		sb.append("1.New Note\n");
		sb.append("2.List Courses\n");
		sb.append("3.Add Course\n");
		sb.append("4.Purge Course List\n\n");

		System.out.println(sb.toString());

		choice = inputScanner.next();
		choice = choice.toUpperCase();
		choice = choice.trim();

		while (!choice.equals("Q") && !choice.equals("q")) {
			courseList = nt.listCourses();
			// DEBUG
			// System.out.println(courseList);
			try {
				numChoice = Integer.parseInt(choice);
				if (numChoice < 1 || numChoice > 4) {
					System.out.println("Please pick a number from 1 to 4");
				} else {
					switch (numChoice) {
					case 1:
						if (courseList.equals("")) {
							System.out.println("Course List is Empty, please add a course");
						} else {
							System.out.println("Your Courses Include:");
							System.out.println(courseList);
							System.out.println("Please type a course name to create a note:");
							choice = inputScanner.next();
							nt.newNote(choice);
						}
						break;
					case 2:
						if (courseList.equals("")) {
							System.out.println("Course List Empty");
						} else {
							System.out.println("Your course list:");
							System.out.println(courseList);
						}
						break;
					case 3:
						System.out.println("Please type the name of the course to add:");
						choice = inputScanner.next();
						nt.addCourse(choice);
						break;
					case 4:

						// String confirm;
						// Scanner confirmChoice = new Scanner(System.in);
						System.out.println("***Warning, this will delete your .courses.txt index file***");
						System.out.println("Press Y to continue or N to exit");
						choice = inputScanner.next();
						choice.trim();
						choice = choice.toUpperCase();
						while ((!choice.equals("Y")) && (!choice.equals("N"))) {
							System.out.println("Please enter Y or N:");
							choice = inputScanner.next();
							choice = choice.trim();
							choice = choice.toUpperCase();
						}

						switch (choice) {
						case "Y":
							boolean deleted = false;
							deleted = nt.purgeCourses();
							// myFile.delete();
							System.out.println(".courses.txt file deleted");
							break;
						case "N":
							System.out.println("exiting with no changes made");
							// confirmChoice.close();
							break;
						}
						// confirmChoice.close();
						// nt.purgeCourses();
						break;
					}
				}
			} catch (NumberFormatException ex) {
				System.out.println("The input must be a number from 1 to 4, please try again");
			} finally {
				System.out.println(sb.toString());
				// inputScanner.
				choice = inputScanner.next();
				choice = choice.toUpperCase();
				choice = choice.trim();

			} // finally
		} // while
		inputScanner.close();
		System.out.println("Exiting Program");
	} // method
} // class
