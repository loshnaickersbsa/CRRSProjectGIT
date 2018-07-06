package sbsa.gui;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Calendar;
import org.jdatepicker.JDatePicker;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDatePicker;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class PnlReservation extends JPanel {
	private JTextField txtTitle;
	private JTable tblAvailableRms;
	private JTable tblAvailableEq;
	private JTable tblAddedEq;

	/**
	 * Create the panel.
	 */
	public PnlReservation() {
		setPreferredSize(new Dimension(900, 600));
		
		JPanel pnlReservation = new JPanel();
		pnlReservation.setPreferredSize(new Dimension(890, 600));
		pnlReservation.setBorder(new TitledBorder(null, "Reservation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel pnlAvailableRms = new JPanel();
		pnlAvailableRms.setPreferredSize(new Dimension(890, 200));
		pnlAvailableRms.setBorder(new TitledBorder(null, "Available Rooms", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		tblAvailableRms = new JTable();
		tblAvailableRms.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Room", "Location"
			}
		));
		
		tblAvailableRms.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblAvailableRms.getColumnModel().getColumn(1).setPreferredWidth(300);
		scrollPane.setViewportView(tblAvailableRms);
		GroupLayout gl_pnlAvailableRms = new GroupLayout(pnlAvailableRms);
		gl_pnlAvailableRms.setHorizontalGroup(
			gl_pnlAvailableRms.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlAvailableRms.createSequentialGroup()
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_pnlAvailableRms.setVerticalGroup(
			gl_pnlAvailableRms.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlAvailableRms.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		pnlAvailableRms.setLayout(gl_pnlAvailableRms);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(pnlAvailableRms, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(pnlReservation, 0, 0, Short.MAX_VALUE)
							.addGap(26))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(43)
					.addComponent(pnlReservation, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(pnlAvailableRms, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel lblTitle = new JLabel("Meeting title: ");
		
		txtTitle = new JTextField();
		txtTitle.setColumns(50);
					
		JLabel lblAttendees = new JLabel("No. of attendees: ");
		
		JSpinner spnAttendees = new JSpinner();
		
		JLabel lblStartDate = new JLabel("Earliest start date: ");
		
		JLabel lblEndDate = new JLabel("Latest end date: ");
		
		JLabel lblDuration = new JLabel("Duration: ");
		
		JSpinner spnDays = new JSpinner();
		
		JSpinner spnHours = new JSpinner();
		spnHours.setModel(new SpinnerNumberModel(0, 0, 24, 1));
		
		JSpinner spnMins = new JSpinner();
		spnMins.setModel(new SpinnerNumberModel(0, 0, 30, 30));
		
		JLabel lblDays = new JLabel("Days:");
		
		JLabel lblHours = new JLabel("Hours: ");
		
		JLabel lblMinutes = new JLabel("Minutes: ");
		
		JButton btnSearch = new JButton("Search");
		
		JButton btnReset = new JButton("Reset");
		
		JDateChooser dtChsStart = new JDateChooser();
		
		JDateChooser dtChsEnd = new JDateChooser();
		
		JPanel pnlAvailableEq = new JPanel();
		pnlAvailableEq.setBorder(new TitledBorder(null, "Available Equipment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel pnlAddedEq = new JPanel();
		pnlAddedEq.setBorder(new TitledBorder(null, "Added Equipment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnRight = new JButton("");
		btnRight.setIcon(new ImageIcon(PnlRoom.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Outdent@2x-rtl.png")));
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnLeft = new JButton("");
		btnLeft.setIcon(new ImageIcon(PnlRoom.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Outdent@2x.png")));
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_pnlAddedEq = new GroupLayout(pnlAddedEq);
		gl_pnlAddedEq.setHorizontalGroup(
			gl_pnlAddedEq.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlAddedEq.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_pnlAddedEq.setVerticalGroup(
			gl_pnlAddedEq.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlAddedEq.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		tblAddedEq = new JTable();
		tblAddedEq.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Equipment Description", "Qty"
			}
		));
		scrollPane_2.setViewportView(tblAddedEq);
		pnlAddedEq.setLayout(gl_pnlAddedEq);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_pnlAvailableEq = new GroupLayout(pnlAvailableEq);
		gl_pnlAvailableEq.setHorizontalGroup(
			gl_pnlAvailableEq.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlAvailableEq.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_pnlAvailableEq.setVerticalGroup(
			gl_pnlAvailableEq.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlAvailableEq.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tblAvailableEq = new JTable();
		tblAvailableEq.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Equipment Description"
			}
		));
		scrollPane_1.setViewportView(tblAvailableEq);
		pnlAvailableEq.setLayout(gl_pnlAvailableEq);
		pnlReservation.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlReservation.add(lblAttendees);
		pnlReservation.add(lblDuration);
		pnlReservation.add(txtTitle);
		pnlReservation.add(spnAttendees);
		pnlReservation.add(lblStartDate);
		pnlReservation.add(lblHours);
		pnlReservation.add(spnHours);
		pnlReservation.add(lblDays);
		pnlReservation.add(spnDays);
		pnlReservation.add(dtChsStart);
		pnlReservation.add(lblEndDate);
		pnlReservation.add(dtChsEnd);
		pnlReservation.add(lblMinutes);
		pnlReservation.add(spnMins);
		pnlReservation.add(btnLeft);
		pnlReservation.add(btnRight);
		pnlReservation.add(pnlAddedEq);
		pnlReservation.add(pnlAvailableEq);
		pnlReservation.add(lblTitle);
		pnlReservation.add(btnSearch);
		pnlReservation.add(btnReset);
		setLayout(groupLayout);
		
	}
}
