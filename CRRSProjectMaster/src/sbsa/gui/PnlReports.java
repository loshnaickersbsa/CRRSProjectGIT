package sbsa.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.text.MessageFormat;

import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;
import org.jdatepicker.*;



public class PnlReports extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextPane txtPreview;
	private JComboBox<String> cboReports;
	private JDatePicker fromDatePicker;
	private JDatePicker toDatePicker;
	private JButton btnGenerateReport;
	private JButton btnPrintReport;

	/**
	 * Create the panel.
	 */
	public PnlReports() {
		initComponents();
		
		cboReports.addItem("Cancellations");
		cboReports.addItem("Percentage Occupation");
		cboReports.addItem("Equipment Requested");
		cboReports.addItem("Wait List");
		
		
	}
	
	private void initComponents() {
		
		JPanel pnlInformation = new JPanel();
		pnlInformation.setBorder(new TitledBorder(null, "Report Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel pnlPreview = new JPanel();
		pnlPreview.setBorder(new TitledBorder(null, "Print Preview", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlPreview.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrScroll = new JScrollPane();
		pnlPreview.add(scrScroll);
		
		txtPreview = new JTextPane();
		txtPreview.setFont(new Font("Arial", Font.PLAIN, 10));
		txtPreview.setEditable(false);
		txtPreview.setContentType("text/html");
		scrScroll.setViewportView(txtPreview);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlInformation, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlPreview, GroupLayout.PREFERRED_SIZE, 590, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlPreview, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
						.addComponent(pnlInformation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE))
					.addGap(11))
		);
		
		JLabel lblReport = new JLabel("Report :");
		
		cboReports = new JComboBox<String>();
		
		JLabel lblFrom = new JLabel("From :");
		lblFrom.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel lblTo = new JLabel("To :");
		lblTo.setHorizontalAlignment(SwingConstants.TRAILING);
		
		btnGenerateReport = new JButton("Generate Report");
		
		btnPrintReport = new JButton("Print Report");
		
		fromDatePicker = new JDatePicker();
		toDatePicker = new JDatePicker();
		
		
		GroupLayout gl_pnlInformation = new GroupLayout(pnlInformation);
		gl_pnlInformation.setHorizontalGroup(
			gl_pnlInformation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlInformation.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlInformation.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlInformation.createSequentialGroup()
							.addGap(58)
							.addGroup(gl_pnlInformation.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnGenerateReport, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPrintReport, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
							.addGap(64))
						.addGroup(gl_pnlInformation.createSequentialGroup()
							.addComponent(lblTo, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(toDatePicker, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_pnlInformation.createSequentialGroup()
							.addComponent(lblFrom, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fromDatePicker, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_pnlInformation.createSequentialGroup()
							.addComponent(lblReport)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboReports, 0, 208, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_pnlInformation.setVerticalGroup(
			gl_pnlInformation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlInformation.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_pnlInformation.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReport)
						.addComponent(cboReports, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(gl_pnlInformation.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFrom)
						.addComponent(fromDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(52)
					.addGroup(gl_pnlInformation.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTo)
						.addComponent(toDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(118)
					.addComponent(btnGenerateReport)
					.addGap(37)
					.addComponent(btnPrintReport)
					.addContainerGap(157, Short.MAX_VALUE))
		);
		pnlInformation.setLayout(gl_pnlInformation);
		setLayout(groupLayout);

	}
	
	private void generateReport() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source  = e.getSource();
		
		if (source == btnGenerateReport) {
			generateReport();
		} if (source == btnPrintReport) {
			MessageFormat footer = new MessageFormat("Page {0,number,integer}");
			try {
				txtPreview.print(null, footer);
			} catch (PrinterException ex) {
				ex.printStackTrace();
			}
		}
		
	}
}
