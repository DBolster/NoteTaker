package mainPack;

public class View {

	public View() {
		// empty constructor
	}

	public static void main(String[] args) {

		// This is the main class that chooses what functions to call based on arguments
		Controller mainControl = new Controller();
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
				System.out.println("Listing courses");
				mainControl.listCourses();
				break;
			case "-nn":
				if (args.length < 2) {
					System.out.println("Missing course name, type NoteTaker -h for help");
					break;
				} else {
					System.out.println("Generating new note");
					mainControl.createNote(args[1]);
					break;
				}
			case "-h":
				System.out.println("Help Options");
				mainControl.showHelp();
			default:
				System.out.println("Invalid option");
				break;

			}
		}
		System.exit(0);
	}
}