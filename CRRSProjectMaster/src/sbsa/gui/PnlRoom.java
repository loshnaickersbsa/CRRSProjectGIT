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
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import java.lang.*;

public class PnlRoom extends JPanel {
	private JTable tblRooms;
	private JTextField txtLocation;
	private JTable tblSelect;
	private JTable tblAdded;

	/**
	 * Create the panel.
	 */
	public PnlRoom() {
		setPreferredSize(new Dimension(900, 600));

		JPanel pnlTop = new JPanel();
		pnlTop.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Rooms",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel pnlBottom = new JPanel();
		pnlBottom.setBorder(
				new TitledBorder(null, "Detailed Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlTop, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(pnlBottom, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
					.addContainerGap())
		);

		JLabel lblBuilding = new JLabel("Building");
		JLabel lblLocation = new JLabel("Location");
		JLabel lblSeats = new JLabel("Seats");

		txtLocation = new JTextField();
		txtLocation.setColumns(10);

		JButton btnAdd = new JButton("Add");

		JButton btnUpdate = new JButton("Update");

		JButton btnDelete = new JButton("Delete");
		
		JComboBox cmbBuilding = new JComboBox();
		
		JSpinner spnSeats = new JSpinner();
		spnSeats.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		JScrollPane scrSelect = new JScrollPane();
		scrSelect.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Available Equipment", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrSelect.setPreferredSize(new Dimension(200, 60));
		
		JScrollPane scrAdded = new JScrollPane();
		scrAdded.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Room Equipment", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrAdded.setPreferredSize(new Dimension(200, 60));
		
		JButton btnRight = new JButton("");
		btnRight.setIcon(new ImageIcon(PnlRoom.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Outdent@2x-rtl.png")));
		
		JButton btnLeft = new JButton("");
		btnLeft.setIcon(new ImageIcon(PnlRoom.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Outdent@2x.png")));
		GroupLayout gl_pnlBottom = new GroupLayout(pnlBottom);
		gl_pnlBottom.setHorizontalGroup(
			gl_pnlBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBottom.createSequentialGroup()
					.addGroup(gl_pnlBottom.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlBottom.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_pnlBottom.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBuilding)
								.addComponent(lblLocation)
								.addComponent(lblSeats))
							.addGap(59)
							.addGroup(gl_pnlBottom.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlBottom.createSequentialGroup()
									.addGroup(gl_pnlBottom.createParallelGroup(Alignment.LEADING)
										.addComponent(cmbBuilding, 0, 623, Short.MAX_VALUE)
										.addComponent(txtLocation, GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE))
									.addGap(77))
								.addGroup(gl_pnlBottom.createSequentialGroup()
									.addComponent(spnSeats, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(gl_pnlBottom.createSequentialGroup()
							.addGap(21)
							.addComponent(scrSelect, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnlBottom.createParallelGroup(Alignment.LEADING)
								.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrAdded, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
							.addGap(26)))
					.addGroup(gl_pnlBottom.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		);
		gl_pnlBottom.setVerticalGroup(
			gl_pnlBottom.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlBottom.createSequentialGroup()
					.addGroup(gl_pnlBottom.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlBottom.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_pnlBottom.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBuilding)
								.addComponent(cmbBuilding, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnlBottom.createParallelGroup(Alignment.BASELINE, false)
								.addComponent(lblLocation)
								.addComponent(txtLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_pnlBottom.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlBottom.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_pnlBottom.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_pnlBottom.createSequentialGroup()
											.addGroup(gl_pnlBottom.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblSeats)
												.addComponent(spnSeats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGap(18)
											.addComponent(scrSelect, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
										.addGroup(gl_pnlBottom.createSequentialGroup()
											.addGap(36)
											.addComponent(scrAdded, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
								.addGroup(Alignment.TRAILING, gl_pnlBottom.createSequentialGroup()
									.addGap(107)
									.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
									.addGap(14))))
						.addGroup(gl_pnlBottom.createSequentialGroup()
							.addGap(23)
							.addComponent(btnAdd)
							.addGap(18)
							.addComponent(btnUpdate)
							.addGap(18)
							.addComponent(btnDelete)))
					.addContainerGap())
				.addGroup(gl_pnlBottom.createSequentialGroup()
					.addContainerGap(107, Short.MAX_VALUE)
					.addComponent(btnRight)
					.addGap(84))
		);
		
		tblAdded = new JTable();
		tblAdded.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Equipment Description", "Qty"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblAdded.getColumnModel().getColumn(0).setPreferredWidth(300);
		tblAdded.getColumnModel().getColumn(1).setPreferredWidth(30);
		//Renders integers with JSpinner controls
		tblAdded.setDefaultRenderer(Integer.class, new IntegerSpinnerRenderer());
		scrAdded.setViewportView(tblAdded);
		
		tblSelect = new JTable();
		tblSelect.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Equipment Description"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblSelect.getColumnModel().getColumn(0).setPreferredWidth(300);
		scrSelect.setViewportView(tblSelect);
		pnlBottom.setLayout(gl_pnlBottom);
		pnlTop.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		pnlTop.add(scrollPane);

		tblRooms = new JTable();
		scrollPane.setViewportView(tblRooms);
		setLayout(groupLayout);
		
		DefaultTableModel model = (DefaultTableModel) tblAdded.getModel();
		Object[] objects = new Object[] {new String("Some Equipment"), new Integer(20)};
		model.addRow(objects);

	}
}
