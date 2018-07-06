package sbsa.gui;

/**
 * change losh
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.io.IOException;
import java.time.Clock;

import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;

import sbsa.beans.User;
import sbsa.client.CRRSClient;
import sbsa.utils.ClockClassDisplay;

public class MainFrame extends JFrame {

	/**
	 * test 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JPanel contentPane;
	protected static JMenuBar mnbBar;
	private static JMenu mnuFile;
	private static JMenuItem mnitmExit;
	private static JMenu mnuReservation;
	private static JMenuItem mniCreateReservation;
	private static JMenuItem mniDeleteReservation;
	private static JMenuItem mniUpdateReservation;
	private static JMenu mnuEdit;
	private static JMenuItem mniEquipment;
	private static JMenuItem mniBuilding;
	private static JMenuItem mniUsers;
	private static JMenuItem mniRooms;

	private JLabel lblDate;
	private JLabel lblDateValue;
	private FlowLayout fl_pnlStatus;


	private PnlLogon pnlLogon;
	private PnlReservation pnlReservation;

	private JPanel pnlStatus;

	protected static JPanel pnlMain;
	protected static JLabel lblUserValue;

	protected static JLabel lblStatusError;
	protected static CRRSClient client;
	protected static JLabel lblUser;
	protected static User currentUser;
	
	protected static MainFrame mainFrame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				mainFrame=null;
				try {
					mainFrame = new MainFrame();
					mainFrame.setVisible(true);
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
		
		mainFrameGUI();
		
		pnlMain = new JPanel();

		contentPane.add(pnlMain, BorderLayout.CENTER);
		
		lblStatusError = new JLabel("_");
		try {
			client = new CRRSClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			lblStatusError.setText(e.toString());
		}
		
		currentUser=null;
		
		pnlLogon = new PnlLogon();
		
		pnlMain.setLayout(new GridLayout(0, 1, 0, 0));
		pnlMain.add(pnlLogon);
		
		ClockClassDisplay clock = new ClockClassDisplay(lblDateValue);
		
		lblStatusError.setVerticalAlignment(SwingConstants.BOTTOM);
		lblStatusError.setMaximumSize(new Dimension(35, 10));
		lblStatusError.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStatusError.setForeground(Color.WHITE);
		lblStatusError.setFont(new Font("Tahoma", Font.BOLD, 15));
	
		pnlStatus.add(lblStatusError);
		
		Thread threadC = clock.getT();
		threadC.start();
		
	}

	private void mainFrameGUI() {
		
		
		setPreferredSize(new Dimension(900, 600));
		setBounds(new Rectangle(0, 0, 900, 800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1288, 740);
		
		mnbBar = new JMenuBar();
		setJMenuBar(mnbBar);
		
		mnuFile = new JMenu("File");
		mnbBar.add(mnuFile);
		
		mnitmExit = new JMenuItem("Exit");
		mnuFile.add(mnitmExit);
		
		mnuReservation = new JMenu("Reservation");
		mnbBar.add(mnuReservation);
		
		mniCreateReservation = new JMenuItem("New");
		mnuReservation.add(mniCreateReservation);
		
		mniDeleteReservation = new JMenuItem("Cancel");
		mnuReservation.add(mniDeleteReservation);
		
		mniUpdateReservation = new JMenuItem("View & Edit");
		mnuReservation.add(mniUpdateReservation);
		
		mnuEdit = new JMenu("Edit");
		
		mnbBar.setVisible(false);
		
		mnbBar.add(mnuEdit);
		
		
		
		mniUsers = new JMenuItem("Users");
		mniEquipment = new JMenuItem("Equipment");
		mniRooms = new JMenuItem("Rooms");
		mniBuilding = new JMenuItem("Building");
		
		mnuEdit.add(mniUsers);
		mnuEdit.add(mniEquipment);
		mnuEdit.add(mniRooms);
		mnuEdit.add(mniBuilding);
		
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, 900, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pnlStatus = new JPanel();
		pnlStatus.setAlignmentX(Component.LEFT_ALIGNMENT);
		pnlStatus.setBorder(new LineBorder(SystemColor.menu, 3, true));
		pnlStatus.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		pnlStatus.setBackground(new Color(51, 153, 255));
		//pnlStatus.setForeground(Color.WHITE);
		//pnlStatus.setBorder(new LineBorder(UIManager.getColor("Tree.selectionForeground"), 3, true));
		contentPane.add(pnlStatus, BorderLayout.SOUTH);
		lblDate = new JLabel("Date :");
		lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDate.setMaximumSize(new Dimension(35, 10));
		lblDate.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDate.setForeground(SystemColor.text);
		
		lblUserValue = new JLabel("<None>");
		lblUserValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserValue.setHorizontalTextPosition(SwingConstants.LEFT);
		lblUserValue.setMinimumSize(new Dimension(35, 10));
		lblUserValue.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblUserValue.setMaximumSize(new Dimension(35, 10));
		lblUserValue.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblUserValue.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUserValue.setForeground(Color.WHITE);
		lblUserValue.setFont(new Font("Tahoma", Font.BOLD, 15));
		fl_pnlStatus = new FlowLayout(FlowLayout.LEADING, 50, 0);
		fl_pnlStatus.setAlignOnBaseline(true);
		pnlStatus.setLayout(fl_pnlStatus);
		
		lblUser = new JLabel("User :");
		lblUser.setHorizontalTextPosition(SwingConstants.LEFT);
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setMaximumSize(new Dimension(35, 10));
		lblUser.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUser.setForeground(SystemColor.text);
		pnlStatus.add(lblUser);
		pnlStatus.add(lblUserValue);
		pnlStatus.add(lblDate);
		
		lblDateValue = new JLabel("");
		lblDateValue.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDateValue.setMinimumSize(new Dimension(122, 10));
		lblDateValue.setMaximumSize(new Dimension(122, 10));
		lblDateValue.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDateValue.setForeground(Color.WHITE);
		lblDateValue.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		pnlStatus.add(lblDateValue);
	}
}
