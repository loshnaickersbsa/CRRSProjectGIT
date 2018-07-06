package sbsa.gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class PnlUser extends JPanel {
	private JTable tblUsers;
	private JTextField txtUserID;
	private JTextField txtName;
	private JTextField txtTitle;
	private JTextField txtTelephone;
	private JTextField txtDepartment;
	private JCheckBox chkAdministrator;

	/**
	 * Create the panel.
	 */
	public PnlUser() {
		setPreferredSize(new Dimension(900, 600));
		
		JPanel pnlTop = new JPanel();
		pnlTop.setBorder(new TitledBorder(null, "Users", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel pnlBottom = new JPanel();
		pnlBottom.setBorder(new TitledBorder(null, "Detailed Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlBottom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnlTop, GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(pnlTop, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
					.addComponent(pnlBottom, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel lblUserID = new JLabel("User ID");
		JLabel lblName = new JLabel("Name");
		JLabel lblTitle = new JLabel("Title");
		JLabel lblTelephone = new JLabel("Telephone");
		JLabel lblDepartment = new JLabel("Department");
		JLabel lblAdministrator = new JLabel("Administrator");
		
		txtUserID = new JTextField();
		txtUserID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		
		txtTelephone = new JTextField();
		txtTelephone.setColumns(10);
		
		txtDepartment = new JTextField();
		txtDepartment.setColumns(10);
		
		chkAdministrator = new JCheckBox("");
		
		JButton btnAdd = new JButton("Add");
		
		JButton btnUpdate = new JButton("Update");
		
		JButton btnDelete = new JButton("Delete");
		GroupLayout gl_pnlBottom = new GroupLayout(pnlBottom);
		gl_pnlBottom.setHorizontalGroup(
			gl_pnlBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBottom.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlBottom.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUserID)
						.addComponent(lblName)
						.addComponent(lblTitle)
						.addComponent(lblTelephone)
						.addComponent(lblDepartment)
						.addComponent(lblAdministrator))
					.addGap(35)
					.addGroup(gl_pnlBottom.createParallelGroup(Alignment.LEADING)
						.addComponent(txtUserID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chkAdministrator)
						.addComponent(txtTelephone)
						.addComponent(txtTitle)
						.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
						.addComponent(txtDepartment))
					.addGap(77)
					.addGroup(gl_pnlBottom.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		);
		gl_pnlBottom.setVerticalGroup(
			gl_pnlBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBottom.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlBottom.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserID)
						.addComponent(txtUserID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlBottom.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(lblName)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlBottom.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle)
						.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlBottom.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelephone)
						.addComponent(txtTelephone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlBottom.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDepartment)
						.addComponent(txtDepartment, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlBottom.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAdministrator)
						.addComponent(chkAdministrator)))
				.addGroup(gl_pnlBottom.createSequentialGroup()
					.addGap(23)
					.addComponent(btnAdd)
					.addGap(18)
					.addComponent(btnUpdate)
					.addGap(18)
					.addComponent(btnDelete))
		);
		pnlBottom.setLayout(gl_pnlBottom);
		pnlTop.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnlTop.add(scrollPane);
		
		tblUsers = new JTable();
		scrollPane.setViewportView(tblUsers);
		setLayout(groupLayout);

	}
}
