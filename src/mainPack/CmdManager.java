package mainPack;

public class CmdManager {

	public CmdManager() {
		// empty constructor
	}

	public static void main(String[] args) {

		// This is the main class that chooses what functions to call based on arguments
		CmdFunctions mainControl = new CmdFunctions();
		// args -nc
		// lc
		// nn
		//
		if (args.length > 2) {
			System.out.println("Too many arguments, for help type NoteTaker -h");
		} else if (args.length < 1) {
			System.out.println("Not enough arguments, please refer to help");
			mainControl.showHelp();
		} else {
			switch (args[0]) {
			case "-ac":
				if (args.length < 2) {
					System.out.println("Missing course name, type NoteTaker -h for help");
					break;
				} else {
					System.out.println("Creating new course");
					mainControl.addCourse(args[1]);
					break;
				}
			case "-lc":
				if (args.length > 1) {
					System.out.println("Too many arguments, type -h for help");
					break;
				} else {
					System.out.println("Listing courses");
					mainControl.listCourses();
					break;
				}
			case "-purge":
				if (args.length > 1) {
					System.out.println("Too many arguments, type -h for help");
					break;
				} else {
					mainControl.purgeCourseFile();
					break;
				}
			case "-nn":
				if (args.length < 2) {
					System.out.println("Missing course name, type NoteTaker -h for help");
					break;
				} else {
					System.out.println("Generating new note");
					mainControl.newNote(args[1]);
					break;
				}
			case "-h":
				if (args.length > 1) {
					System.out.println("Too many arguments, type -h for help");
				} else {
					System.out.println("Help Options");
					mainControl.showHelp();
					break;
				}
			default:
				System.out.println("Invalid option");
				break;

			}
		}
		System.exit(0);
	}
}
