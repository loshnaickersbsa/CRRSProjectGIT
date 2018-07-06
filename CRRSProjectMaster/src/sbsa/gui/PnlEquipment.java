package sbsa.gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextPane;
import java.awt.Font;

//
public class PnlEquipment extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblEquipment;
	private JTextField txtDesc;
	private JPanel pnlDetailedInfo;
	private JLabel lblDescription;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JPanel pnlEquipmentList;
	private JScrollPane scrPaneForTable;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PnlEquipment() {
		pnlGUI();
		pnlListeners();
	}

	private void pnlListeners() {
		// TODO Auto-generated method stub
		
	}

	private void pnlGUI() {


		setPreferredSize(new Dimension(900, 600));
		
		pnlDetailedInfo = new JPanel();
		pnlDetailedInfo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Detailed Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		lblDescription = new JLabel("Description");
		
		txtDesc = new JTextField();
		txtDesc.setPreferredSize(new Dimension(30, 22));
		txtDesc.setColumns(100);
		
		btnAdd = new JButton("Add");
		btnAdd.setAlignmentX(1.0f);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setPreferredSize(new Dimension(120, 25));
		btnUpdate.setAlignmentX(1.0f);
		
		btnDelete = new JButton("Delete");
		btnDelete.setAlignmentX(1.0f);
		GroupLayout gl_panel_1 = new GroupLayout(pnlDetailedInfo);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDescription)
					.addGap(18)
					.addComponent(txtDesc, GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
					.addGap(79)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
					.addGap(0))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescription)
						.addComponent(txtDesc, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(77, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnAdd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDelete)
					.addGap(11))
		);
		pnlDetailedInfo.setLayout(gl_panel_1);
		
		pnlEquipmentList = new JPanel();
		pnlEquipmentList.setBorder(new TitledBorder(null, "Equipment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlEquipmentList, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
						.addComponent(pnlDetailedInfo, GroupLayout.PREFERRED_SIZE, 888, Short.MAX_VALUE))
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pnlEquipmentList, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlDetailedInfo, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		scrPaneForTable = new JScrollPane();
		scrPaneForTable.setBorder(null);
		
		tblEquipment = new JTable();
		tblEquipment.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"ID", "Description"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblEquipment.getColumnModel().getColumn(0).setPreferredWidth(153);
		tblEquipment.getColumnModel().getColumn(1).setPreferredWidth(708);
		scrPaneForTable.setViewportView(tblEquipment);
		
		JLabel lblFilterDescription = new JLabel("filter: description");
		lblFilterDescription.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_pnlEquipmentList = new GroupLayout(pnlEquipmentList);
		gl_pnlEquipmentList.setHorizontalGroup(
			gl_pnlEquipmentList.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlEquipmentList.createSequentialGroup()
					.addGroup(gl_pnlEquipmentList.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlEquipmentList.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrPaneForTable, GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE))
						.addGroup(gl_pnlEquipmentList.createSequentialGroup()
							.addGap(24)
							.addComponent(lblFilterDescription)
							.addGap(29)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(63))
		);
		gl_pnlEquipmentList.setVerticalGroup(
			gl_pnlEquipmentList.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnlEquipmentList.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlEquipmentList.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFilterDescription))
					.addGap(19)
					.addComponent(scrPaneForTable, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
		);
		pnlEquipmentList.setLayout(gl_pnlEquipmentList);
		setLayout(groupLayout);

		
		
	}
}
