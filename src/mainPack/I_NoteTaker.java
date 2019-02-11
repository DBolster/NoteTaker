package mainPack;

public interface I_NoteTaker {

	public int createCourseList();

	public int newNote(String course);

	public String listCourses();

	public int addCourse(String course);

	public void purgeCourses();

	public boolean findCourse(String course);

	public void showHelp();

}
