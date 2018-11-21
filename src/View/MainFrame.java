package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mainPack.GuiFunctions;

public class MainFrame extends JFrame {

	/**
	 * Variable declaration
	 */
	private JPanel jp_ButtonContainer;
	private JButton btnListCourses;
	private JButton btnAddCourse;
	private JButton btnPurgeCourses;
	private JButton btnHelp;
	private JPanel jp_InfoContainer;
	private JPanel jp_CourseList;
	private JList<String> jL_courseList;
	private JPanel jp_HelpPanel;
	private JPanel jp_Top;
	private JPanel jp_Bottom;
	private JPanel jp_Right;

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		jp_ButtonContainer = new JPanel();
		jp_ButtonContainer.setBackground(Color.ORANGE);
		contentPane.add(jp_ButtonContainer, BorderLayout.WEST);
		GridBagLayout gbl_jp_ButtonContainer = new GridBagLayout();
		gbl_jp_ButtonContainer.columnWidths = new int[] { 30, 30, 30 };
		gbl_jp_ButtonContainer.rowHeights = new int[] { 20, 20, 20 };
		gbl_jp_ButtonContainer.columnWeights = new double[] { 0.0, 0.0 };
		gbl_jp_ButtonContainer.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		jp_ButtonContainer.setLayout(gbl_jp_ButtonContainer);

		btnListCourses = new JButton("List Courses");
		btnListCourses.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnTaskActionPerformed(arg0);

			}

		});
		btnListCourses.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnListCourses = new GridBagConstraints();
		gbc_btnListCourses.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnListCourses.insets = new Insets(0, 0, 5, 0);
		gbc_btnListCourses.gridx = 1;
		gbc_btnListCourses.gridy = 0;
		jp_ButtonContainer.add(btnListCourses, gbc_btnListCourses);

		btnAddCourse = new JButton("Add Course");
		btnAddCourse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnAddCourse = new GridBagConstraints();
		gbc_btnAddCourse.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddCourse.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddCourse.gridx = 1;
		gbc_btnAddCourse.gridy = 1;
		jp_ButtonContainer.add(btnAddCourse, gbc_btnAddCourse);

		btnPurgeCourses = new JButton("Purge Courses");
		btnPurgeCourses.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnPurgeCourses = new GridBagConstraints();
		gbc_btnPurgeCourses.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPurgeCourses.insets = new Insets(0, 0, 5, 0);
		gbc_btnPurgeCourses.gridx = 1;
		gbc_btnPurgeCourses.gridy = 2;
		jp_ButtonContainer.add(btnPurgeCourses, gbc_btnPurgeCourses);

		btnHelp = new JButton("Help");
		btnHelp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnHelp = new GridBagConstraints();
		gbc_btnHelp.insets = new Insets(0, 0, 5, 0);
		gbc_btnHelp.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnHelp.gridx = 1;
		gbc_btnHelp.gridy = 3;
		jp_ButtonContainer.add(btnHelp, gbc_btnHelp);

		jp_InfoContainer = new JPanel();
		jp_InfoContainer.setBackground(Color.PINK);
		contentPane.add(jp_InfoContainer, BorderLayout.CENTER);
		jp_InfoContainer.setLayout(new CardLayout(0, 0));

		jp_CourseList = new JPanel();
		jp_CourseList.setBackground(Color.GRAY);
		jp_InfoContainer.add(jp_CourseList, "name_628967745091948");

		jL_courseList = new JList<String>();
		jp_CourseList.add(jL_courseList);

		jp_HelpPanel = new JPanel();
		jp_HelpPanel.setBackground(Color.RED);
		jp_InfoContainer.add(jp_HelpPanel, "name_628972159546938");

		jp_Top = new JPanel();
		FlowLayout flowLayout = (FlowLayout) jp_Top.getLayout();
		flowLayout.setVgap(10);
		jp_Top.setBackground(Color.GREEN);
		contentPane.add(jp_Top, BorderLayout.NORTH);

		jp_Bottom = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) jp_Bottom.getLayout();
		flowLayout_1.setVgap(10);
		jp_Bottom.setBackground(Color.CYAN);
		contentPane.add(jp_Bottom, BorderLayout.SOUTH);

		jp_Right = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) jp_Right.getLayout();
		flowLayout_2.setHgap(30);
		jp_Right.setBackground(Color.MAGENTA);
		contentPane.add(jp_Right, BorderLayout.EAST);
	}

	protected void btnTaskActionPerformed(ActionEvent arg0) {
		GuiFunctions guiControl = new GuiFunctions();
		List courseList = guiControl.createCourseList();
		DefaultListModel<String> listM = new DefaultListModel<String>();
		for (int i = 0; i < courseList.getItemCount(); i++) {
			listM.addElement(courseList.getItem(i));
		}
		jL_courseList.setModel(listM);
	}

}
