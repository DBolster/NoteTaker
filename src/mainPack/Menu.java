package mainPack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Menu {

	private PrintWriter pw;
	private StringBuilder sb;
	private String choice, courseFileName;
	private int numChoice;
	private NoteTaker nt;

	public Menu() {
		courseFileName = ".courses";
		nt = new NoteTaker(courseFileName);
		pw = new PrintWriter(System.out, true);
		sb = new StringBuilder();
	}

	public void showMainMenu() {
		sb.append("NoteTaker Main Menu/n/n");
		sb.append("Please choose an option from 1 to 4, Q to quit/n/n");
		sb.append("1.New Note/n");
		sb.append("2.List Courses");
		sb.append("3.Add Course");
		sb.append("4.Purge Course List/n/n");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		do {

			pw.print(sb.toString());
			try {
				choice = br.readLine();
			} catch (IOException e) {
				System.out.println("IO Error");
				// e.printStackTrace();
			}
			numChoice = Integer.parseInt(choice.trim());
			if (numChoice < 1 || numChoice > 4) {
				System.out.println("Please pick a number from 1 to 4");
			} else {
				switch (numChoice) {
				case 1:
					courseChoiceMenu();
					break;
				case 2:
					System.out.println(nt.listCourses());
					break;
				case 3:
					courseChoiceMenu();
					break;
				case 4:
					nt.purgeCourses();
					break;

				}
			}

		} while (choice != "Q" || choice != "q");
		pw.close();
	}

	public void courseChoiceMenu() {

	}

}
