package sbsa.gui;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PnlEditReservation extends JPanel {
	private JTable tblConfirmedRes;
	private JTable tblWaitListed;
	private JTextField txtTitle;
	private JTextField txtRoom;
	private JTextField txtDate;
	private JTable tblAvailableEquipment;
	private JTable tblAddedEquipment;

	/**
	 * Create the panel.
	 */
	public PnlEditReservation() {
		setPreferredSize(new Dimension(890, 590));
		
		JPanel pnlConfirmedRes = new JPanel();
		pnlConfirmedRes.setBorder(new TitledBorder(null, "Confirmed Reservations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel pnlWaitListed = new JPanel();
		pnlWaitListed.setBorder(new TitledBorder(null, "Wait Listed", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel pnlEditRes = new JPanel();
		pnlEditRes.setBorder(new TitledBorder(null, "Edit/Cancel Reservation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlEditRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnlConfirmedRes, GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
						.addComponent(pnlWaitListed, GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addComponent(pnlConfirmedRes, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlWaitListed, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(pnlEditRes, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		
		JLabel lblMeetingTitle = new JLabel("Meeting Title: ");
		
		JLabel lblRoom = new JLabel("Room: ");
		
		JLabel lblDate = new JLabel("Date: ");
		
		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		
		txtRoom = new JTextField();
		txtRoom.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		
		JPanel pnlAvailableEquiment = new JPanel();
		pnlAvailableEquiment.setBorder(new TitledBorder(null, "Available Equipment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel pnlAddedEquipment = new JPanel();
		pnlAddedEquipment.setBorder(new TitledBorder(null, "Added Equipment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnRight = new JButton(" ");
		btnRight.setIcon(new ImageIcon(PnlRoom.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Outdent@2x-rtl.png")));

		
		JButton btnLeft = new JButton(" ");
		btnLeft.setIcon(new ImageIcon(PnlRoom.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Outdent@2x.png")));
		
		JButton btnUpdateReservation = new JButton("Update Reservation");
		btnUpdateReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnCancelReservation = new JButton("Cancel Reservation");

		GroupLayout gl_pnlEditRes = new GroupLayout(pnlEditRes);
		gl_pnlEditRes.setHorizontalGroup(
			gl_pnlEditRes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlEditRes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlEditRes.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlEditRes.createSequentialGroup()
							.addComponent(lblMeetingTitle)
							.addGap(18)
							.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addGap(88)
							.addComponent(lblRoom)
							.addGap(18)
							.addComponent(txtRoom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlEditRes.createSequentialGroup()
							.addComponent(pnlAvailableEquiment, 0, 0, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(gl_pnlEditRes.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnRight)
								.addComponent(btnLeft))))
					.addGap(18)
					.addGroup(gl_pnlEditRes.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlEditRes.createSequentialGroup()
							.addComponent(lblDate)
							.addGap(18)
							.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addGap(275))
						.addGroup(gl_pnlEditRes.createSequentialGroup()
							.addComponent(pnlAddedEquipment, GroupLayout.PREFERRED_SIZE, 506, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_pnlEditRes.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnUpdateReservation, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
						.addComponent(btnCancelReservation, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_pnlEditRes.setVerticalGroup(
			gl_pnlEditRes.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlEditRes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlEditRes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlEditRes.createSequentialGroup()
							.addGroup(gl_pnlEditRes.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMeetingTitle)
								.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRoom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRoom)
								.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDate))
							.addGroup(gl_pnlEditRes.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_pnlEditRes.createSequentialGroup()
									.addGap(60)
									.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_pnlEditRes.createSequentialGroup()
									.addGap(31)
									.addGroup(gl_pnlEditRes.createParallelGroup(Alignment.LEADING)
										.addComponent(pnlAddedEquipment, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
										.addComponent(pnlAvailableEquiment, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))))
							.addGap(13))
						.addGroup(gl_pnlEditRes.createSequentialGroup()
							.addGap(83)
							.addComponent(btnUpdateReservation)
							.addGap(18)
							.addComponent(btnCancelReservation)))
					.addGap(34))
		);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_pnlAddedEquipment = new GroupLayout(pnlAddedEquipment);
		gl_pnlAddedEquipment.setHorizontalGroup(
			gl_pnlAddedEquipment.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlAddedEquipment.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_pnlAddedEquipment.setVerticalGroup(
			gl_pnlAddedEquipment.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
		);
		
		tblAddedEquipment = new JTable();
		tblAddedEquipment.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Description", "Qty"
			}
		));
		scrollPane_3.setViewportView(tblAddedEquipment);
		pnlAddedEquipment.setLayout(gl_pnlAddedEquipment);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_pnlAvailableEquiment = new GroupLayout(pnlAvailableEquiment);
		gl_pnlAvailableEquiment.setHorizontalGroup(
			gl_pnlAvailableEquiment.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlAvailableEquiment.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
					.addGap(82))
		);
		gl_pnlAvailableEquiment.setVerticalGroup(
			gl_pnlAvailableEquiment.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
		);
		
		tblAvailableEquipment = new JTable();
		tblAvailableEquipment.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Description"
			}
		));
		scrollPane_2.setViewportView(tblAvailableEquipment);
		pnlAvailableEquiment.setLayout(gl_pnlAvailableEquiment);
		pnlEditRes.setLayout(gl_pnlEditRes);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton btnSelectWaitListed = new JButton("Select");
		GroupLayout gl_pnlWaitListed = new GroupLayout(pnlWaitListed);
		gl_pnlWaitListed.setHorizontalGroup(
			gl_pnlWaitListed.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlWaitListed.createSequentialGroup()
					.addGroup(gl_pnlWaitListed.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlWaitListed.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 1109, Short.MAX_VALUE))
						.addGroup(gl_pnlWaitListed.createSequentialGroup()
							.addGap(514)
							.addComponent(btnSelectWaitListed)))
					.addContainerGap())
		);
		gl_pnlWaitListed.setVerticalGroup(
			gl_pnlWaitListed.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlWaitListed.createSequentialGroup()
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addComponent(btnSelectWaitListed)
					.addContainerGap())
		);
		
		tblWaitListed = new JTable();
		tblWaitListed.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date/Time", "Meeting Title", "Room", "Room Equipment", "Added Equipment"
			}
		));
		tblWaitListed.getColumnModel().getColumn(0).setPreferredWidth(104);
		tblWaitListed.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblWaitListed.getColumnModel().getColumn(2).setPreferredWidth(122);
		tblWaitListed.getColumnModel().getColumn(4).setPreferredWidth(160);
		scrollPane_1.setViewportView(tblWaitListed);
		pnlWaitListed.setLayout(gl_pnlWaitListed);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton btnSelect = new JButton("Select");
		GroupLayout gl_pnlConfirmedRes = new GroupLayout(pnlConfirmedRes);
		gl_pnlConfirmedRes.setHorizontalGroup(
			gl_pnlConfirmedRes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlConfirmedRes.createSequentialGroup()
					.addGroup(gl_pnlConfirmedRes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlConfirmedRes.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1109, Short.MAX_VALUE))
						.addGroup(gl_pnlConfirmedRes.createSequentialGroup()
							.addGap(514)
							.addComponent(btnSelect)))
					.addContainerGap())
		);
		gl_pnlConfirmedRes.setVerticalGroup(
			gl_pnlConfirmedRes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlConfirmedRes.createSequentialGroup()
					.addGap(5)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSelect)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tblConfirmedRes = new JTable();
		tblConfirmedRes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date/Time", "Meeting Title", "Room", "Room Equipment", "Added Equipment"
			}
		));
		tblConfirmedRes.getColumnModel().getColumn(0).setPreferredWidth(104);
		tblConfirmedRes.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblConfirmedRes.getColumnModel().getColumn(2).setPreferredWidth(122);
		tblConfirmedRes.getColumnModel().getColumn(4).setPreferredWidth(160);
		scrollPane.setViewportView(tblConfirmedRes);
		pnlConfirmedRes.setLayout(gl_pnlConfirmedRes);
		setLayout(groupLayout);

	}
}
