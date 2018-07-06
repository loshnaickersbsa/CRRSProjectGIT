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

public class PnlBuilding extends JPanel {
	private JTable tblBuildings;
	private JTextField txtDesc;

	/**
	 * Create the panel.
	 */
	public PnlBuilding() {
		setPreferredSize(new Dimension(900, 600));
		
		JPanel pnlTop = new JPanel();
		pnlTop.setBorder(new TitledBorder(null, "Buildings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
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
		
		JLabel lblDescription = new JLabel("Description");
		
		txtDesc = new JTextField();
		txtDesc.setColumns(100);
		
		JButton btnAdd = new JButton("Add");
		
		JButton btnUpdate = new JButton("Update");
		
		JButton btnDelete = new JButton("Delete");
		GroupLayout gl_pnlBottom = new GroupLayout(pnlBottom);
		gl_pnlBottom.setHorizontalGroup(
			gl_pnlBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBottom.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDescription)
					.addGap(46)
					.addComponent(txtDesc, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
					.addGap(61)
					.addGroup(gl_pnlBottom.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		);
		gl_pnlBottom.setVerticalGroup(
			gl_pnlBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBottom.createSequentialGroup()
					.addGroup(gl_pnlBottom.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlBottom.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_pnlBottom.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDescription)
								.addComponent(txtDesc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_pnlBottom.createSequentialGroup()
							.addGap(23)
							.addComponent(btnAdd)
							.addGap(18)
							.addComponent(btnUpdate)
							.addGap(18)
							.addComponent(btnDelete)))
					.addGap(34))
		);
		pnlBottom.setLayout(gl_pnlBottom);
		pnlTop.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnlTop.add(scrollPane);
		
		tblBuildings = new JTable();
		scrollPane.setViewportView(tblBuildings);
		setLayout(groupLayout);

	}
}
