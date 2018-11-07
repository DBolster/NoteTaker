package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import mainPack.CmdManager;

import javax.swing.border.CompoundBorder;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		
		JPanel jp_ButtonContainer = new JPanel();
		jp_ButtonContainer.setBackground(Color.ORANGE);
		contentPane.add(jp_ButtonContainer, BorderLayout.WEST);
		GridBagLayout gbl_jp_ButtonContainer = new GridBagLayout();
		gbl_jp_ButtonContainer.columnWidths = new int[] {30, 30, 30};
		gbl_jp_ButtonContainer.rowHeights = new int[] {20, 20, 20};
		gbl_jp_ButtonContainer.columnWeights = new double[]{0.0, 0.0};
		gbl_jp_ButtonContainer.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		jp_ButtonContainer.setLayout(gbl_jp_ButtonContainer);
		
		JButton btnListCourses = new JButton("List Courses");
		btnListCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnListCourses.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnListCourses = new GridBagConstraints();
		gbc_btnListCourses.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnListCourses.insets = new Insets(0, 0, 5, 0);
		gbc_btnListCourses.gridx = 1;
		gbc_btnListCourses.gridy = 0;
		jp_ButtonContainer.add(btnListCourses, gbc_btnListCourses);
		
		JButton btnAddCourse = new JButton("Add Course");
		btnAddCourse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnAddCourse = new GridBagConstraints();
		gbc_btnAddCourse.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddCourse.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddCourse.gridx = 1;
		gbc_btnAddCourse.gridy = 1;
		jp_ButtonContainer.add(btnAddCourse, gbc_btnAddCourse);
		
		JButton btnPurgeCourses = new JButton("Purge Courses");
		btnPurgeCourses.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnPurgeCourses = new GridBagConstraints();
		gbc_btnPurgeCourses.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPurgeCourses.insets = new Insets(0, 0, 5, 0);
		gbc_btnPurgeCourses.gridx = 1;
		gbc_btnPurgeCourses.gridy = 2;
		jp_ButtonContainer.add(btnPurgeCourses, gbc_btnPurgeCourses);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnHelp = new GridBagConstraints();
		gbc_btnHelp.insets = new Insets(0, 0, 5, 0);
		gbc_btnHelp.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnHelp.gridx = 1;
		gbc_btnHelp.gridy = 3;
		jp_ButtonContainer.add(btnHelp, gbc_btnHelp);
		
		JPanel jp_InfoContainer = new JPanel();
		jp_InfoContainer.setBackground(Color.PINK);
		contentPane.add(jp_InfoContainer, BorderLayout.CENTER);
		jp_InfoContainer.setLayout(new CardLayout(0, 0));
		
		JPanel jp_CourseList = new JPanel();
		jp_CourseList.setBackground(Color.GRAY);
		jp_InfoContainer.add(jp_CourseList, "name_628967745091948");
		
		JList jList_courseList = new JList();
		jList_courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jp_CourseList.add(jList_courseList);
		
		JPanel jp_HelpPanel = new JPanel();
		jp_HelpPanel.setBackground(Color.RED);
		jp_InfoContainer.add(jp_HelpPanel, "name_628972159546938");
		
		JPanel jp_Top = new JPanel();
		FlowLayout flowLayout = (FlowLayout) jp_Top.getLayout();
		flowLayout.setVgap(10);
		jp_Top.setBackground(Color.GREEN);
		contentPane.add(jp_Top, BorderLayout.NORTH);
		
		JPanel jp_Bottom = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) jp_Bottom.getLayout();
		flowLayout_1.setVgap(10);
		jp_Bottom.setBackground(Color.CYAN);
		contentPane.add(jp_Bottom, BorderLayout.SOUTH);
		
		JPanel jp_Right = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) jp_Right.getLayout();
		flowLayout_2.setHgap(30);
		jp_Right.setBackground(Color.MAGENTA);
		contentPane.add(jp_Right, BorderLayout.EAST);
	}

}
