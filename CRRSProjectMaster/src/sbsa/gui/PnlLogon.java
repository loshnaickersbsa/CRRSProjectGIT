package sbsa.gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import sbsa.beans.User;
import sbsa.client.CRRSClient;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.ComponentOrientation;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

//
public class PnlLogon extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnLogon;
	private JPanel pnlLogon;
	private JPanel panel;
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JPasswordField txtPasswordField;
	private JTextField txtUserName;
	private CRRSClient client;
	private User loggedIn;
	private MainFrame main;
	private JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public PnlLogon() {
		
		//this = client;
		//this.main = main;
		
		pnlGUI();
		pnlListeners();
	}

	private void pnlListeners() {
		btnLogon.addActionListener(this);
	}

	private void pnlGUI() {


		setPreferredSize(new Dimension(900, 600));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		pnlLogon = new JPanel();
		pnlLogon.setBackground(Color.WHITE);
		pnlLogon.setForeground(Color.WHITE);
		pnlLogon.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Welcome", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setForeground(Color.WHITE);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Logon", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		btnLogon = new JButton("Logon");
		btnLogon.setBorder(new LineBorder(new Color(51, 153, 255), 1, true));
		btnLogon.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		btnLogon.addActionListener(this);
		
		btnLogon.setAlignmentX(1.0f);
		
		lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtPasswordField = new JPasswordField();
		txtPasswordField.setBorder(new LineBorder(new Color(51, 153, 255), 1, true));
		txtPasswordField.setColumns(15);
		txtPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtUserName = new JTextField();
		txtUserName.setBorder(new LineBorder(new Color(51, 153, 255), 1, true));
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUserName.setColumns(15);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblUserName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(95)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLogon, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(txtPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(350, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(54)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserName)
						.addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(txtPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(59)
					.addComponent(btnLogon)
					.addContainerGap(97, Short.MAX_VALUE))
		);
		gl_panel.setHonorsVisibility(false);
		panel.setLayout(gl_panel);
		add(pnlLogon);
		pnlLogon.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 200));
		pnlLogon.add(panel);
		
		lblNewLabel = new JLabel("");
		pnlLogon.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(PnlLogon.class.getResource("/sbsa/utils/calendar.jpg")));

		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = null;
		
		source = e.getSource();
		
		if (source == btnLogon)
		{
			String password = new String(txtPasswordField.getPassword());
			
			MainFrame.currentUser = MainFrame.client.login(txtUserName.getText(), password);
			
			System.out.println(MainFrame.currentUser + " output from logon");
			
			if ( MainFrame.currentUser == null)
			{
				MainFrame.lblStatusError.setText("login details incorrect!");
			}
			else
			{
				MainFrame.lblStatusError.setText("");
				MainFrame.pnlMain.removeAll();
				MainFrame.pnlMain.repaint();
				MainFrame.mnbBar.setVisible(true);	
				MainFrame.lblUserValue.setText(MainFrame.currentUser.getPersonID());
			}
			
		}
		
	}
}
