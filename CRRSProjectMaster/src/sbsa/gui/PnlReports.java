package sbsa.gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.html.HTMLEditorKit;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;
import org.jdatepicker.*;
import org.jdatepicker.features.Feature3.DemoDateModel;

import sbsa.beans.*;
import sbsa.client.CRRSClient;

import java.util.*;



public class PnlReports extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextPane txtPreview;
	private JComboBox<String> cboReports;
	private JDatePicker fromDatePicker;
	private JDatePicker toDatePicker;
	private JButton btnGenerateReport;
	private JButton btnPrintReport;
	private CRRSClient client;

	/**
	 * Create the panel.
	 */
	public PnlReports() {
		initComponents();
		cboReports.addItem("Cancellations");
		cboReports.addItem("Percentage Occupation");
		cboReports.addItem("Equipment Requested");
		cboReports.addItem("Wait List");
		cboReports.setSelectedIndex(0);
		btnPrintReport.setEnabled(false);
		txtPreview.setEditorKit(new HTMLEditorKit());
		txtPreview.setContentType("text/html");
		initEvents();
		//fromDatePicker.set

		java.util.Date d = new java.util.Date();
		System.out.println( d.toString());
		try {
			client = new CRRSClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		String tmpDate = Calendar.getInstance().get(Calendar.YEAR)+ "-" + (Calendar.getInstance().get(Calendar.MONTH) +1) +"-" +  Calendar.getInstance().get(Calendar.DAY_OF_MONTH); 
		//System.out.println(tmpDate);
		fromDatePicker = new JDatePicker(new DemoDateModel(tmpDate));
		toDatePicker = new JDatePicker(new DemoDateModel(tmpDate));


		GroupLayout gl_pnlInformation = new GroupLayout(pnlInformation);
		gl_pnlInformation.setHorizontalGroup(
				gl_pnlInformation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlInformation.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_pnlInformation.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_pnlInformation.createSequentialGroup()
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
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		pnlInformation.setLayout(gl_pnlInformation);
		setLayout(groupLayout);

	}


	private void initEvents() {
		btnGenerateReport.addActionListener(this);
		btnPrintReport.addActionListener(this);
		cboReports.addActionListener(this);
		fromDatePicker.addActionListener(this);
		toDatePicker.addActionListener(this);
	}

	private void generateReport() {
		System.out.println(cboReports.getSelectedItem().toString());
		if (cboReports.getSelectedItem().toString().equals("Cancellations")) {
			loadCancellations();
		} else if (cboReports.getSelectedItem().toString().equals("Percentage Occupation")) {
			loadOccupation();
		} if (cboReports.getSelectedItem().toString().equals("Equipment Requested")) {
			loadEquipment();
		} if (cboReports.getSelectedItem().toString().equals("Wait List")) {
			loadWaitList();
		} 
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object source  = e.getSource();

		if (source == btnGenerateReport) {
			generateReport();
			btnPrintReport.setEnabled(true);
		} else if (source == btnPrintReport) {
			MessageFormat footer = new MessageFormat("Page {0,number,integer}");
			try {
				txtPreview.print(null, footer);
			} catch (PrinterException ex) {
				ex.printStackTrace();
			}
		} else {
			txtPreview.setText("");
			btnPrintReport.setEnabled(false);
		}

	}

	private void loadCancellations() {

		String strHTML = "";
		File f = new File("resources/boardroom.jpg");
		String filepath = f.getAbsoluteFile().toString();

		filepath = "file:/" + filepath.replace('\\', '/');




		String fromDate = fromDatePicker.getModel().getValue().toString();
		String toDate = toDatePicker.getModel().getValue().toString();


		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fromD = new Date();
		Date toD = new Date();

		try {
			fromD = format.parse(fromDate);
			toD = format.parse(toDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String  cancellations= String.valueOf(client.getReportCancellations(fromD, toD));


		strHTML ="<HTML><HEAD>";
		strHTML +="<STYLE>";
		strHTML +="Body {font-size: 10px;}";
		strHTML +="H1 {text-decoration: underline; font-size: 16px;}";
		strHTML +="table {font-family: arial, sans-serif;border-collapse: collapse; font-size: 10px;}";
		strHTML +=".table100 {width: 90%; border-width: 3px; border-style: solid; margin-left:auto;  margin-right:auto;}";
		strHTML +=".table80 { width: 70%; margin-left:auto;  margin-right:auto;}";
		strHTML +=".td30 {width: 30%; border-style: none;}";
		strHTML +=".td70 {width: 70%; border-style: none;}";
		strHTML +="td, th {border: 1px solid #bbbbbb; text-align: left;  padding: 8px;}";
		strHTML +="th {color: #ffffff; background: #6c7ae0; font-size: 9px;}";
		strHTML +="tr:nth-child(even) {background-color: #f5f5f5;}";
		strHTML +="</STYLE>";
		strHTML +="</HEAD><BODY>";
		strHTML +="<table class='table100' align=center><tr><td class='td30'>";
		strHTML +="<img src='" + filepath + "'></td><td class='td70'>";
		strHTML +="<H1>Number Of Cancellations</H1></td></tr></table>";
		strHTML +="<br><br>";
		strHTML +="<p style='text-align: center;'><font style='Font-weight: bold;'>Report Description: </font>Meeting cancellation between specified dates.</p>";
		strHTML +="<br><br>";
		strHTML +="<table class='table80' align=center>";
		strHTML +="	<tr>";
		strHTML +="		<th>";
		strHTML +="			From";
		strHTML +="		</th><th>";
		strHTML +="			To";
		strHTML +="		</th><th>";
		strHTML +="			Cancellations";
		strHTML +="		</th>";
		strHTML +="	</tr><tr>";
		strHTML +="		<td>";
		strHTML +="			"+fromDate;
		strHTML +="		</td><td>";
		strHTML +="			"+toDate;
		strHTML +="		</td><td>";
		strHTML +="			"+ cancellations;
		strHTML +="		</td>";
		strHTML +="	</tr>";
		strHTML +="</table>";
		//strHTML +="&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 1 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 2 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 3 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 4 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 5 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 6 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 7 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 8 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 9<br>";
		//strHTML +="123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890<br>";
		strHTML +="<br><br>";
		strHTML +="</BODY></HTML>";

		txtPreview.setText(strHTML);
	}

	private void loadOccupation() {

		String strHTML = "";
		File f = new File("resources/boardroom.jpg");
		String filepath = f.getAbsoluteFile().toString();

		filepath = "file:/" + filepath.replace('\\', '/');


		String fromDate = fromDatePicker.getModel().getValue().toString();
		String toDate = toDatePicker.getModel().getValue().toString();


		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fromD = new Date();
		Date toD = new Date();

		try {
			fromD = format.parse(fromDate);
			toD = format.parse(toDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<RoomOccupation>  ro = client.getReportRoomOccupation(fromD, toD);

		strHTML ="<HTML><HEAD>";
		strHTML +="<STYLE>";
		strHTML +="Body {font-size: 10px;}";
		strHTML +="H1 {text-decoration: underline; font-size: 16px;}";
		strHTML +="table {font-family: arial, sans-serif;border-collapse: collapse; font-size: 12px;}";
		strHTML +=".table100 {width: 90%; border-width: 3px; border-style: solid; margin-left:auto;  margin-right:auto;}";
		strHTML +=".table80 { width: 70%; margin-left:auto;  margin-right:auto;}";
		strHTML +=".td30 {width: 30%; border-style: none;}";
		strHTML +=".td70 {width: 70%; border-style: none;}";
		strHTML +="td, th {border: 1px solid #bbbbbb; text-align: left; padding: 8px; font-size: 8px;}";
		strHTML +="th {color: #ffffff; background: #6c7ae0; font-size: 9px;}";
		strHTML +="tr:nth-child(even) {background-color: #f5f5f5;}";
		strHTML +="</STYLE>";
		strHTML +="</HEAD><BODY>";
		strHTML +="<table class='table100'  align=center><tr><td class='td30'>";
		strHTML +="<img src='" + filepath + "'></td><td class='td70'>";
		strHTML +="<H1>Percentage Occupation</H1></td></tr></table>";
		strHTML +="<br>";
		strHTML +="<br>";
		strHTML +="<p style='text-align: center;'><font style='Font-weight: bold;'>Report Description: </font>Percentage occupation for all meeting rooms within a given time period.<br>";
		strHTML +="<br>";
		strHTML +="<font style='Font-weight: bold;'>From Date: </font>" + fromDate + "<br>";
		strHTML +="<font style='Font-weight: bold;'>To Date: </font>" + toDate + "<br></p>";
		strHTML +="<br>";
		strHTML +="<table class='table80'  align=center>";
		strHTML +="	<tr>";
		strHTML +="		<th>";
		strHTML +="			Building";
		strHTML +="		</th><th>";
		strHTML +="			Meeting Room";
		strHTML +="		</th><th>";
		strHTML +="			% Occupation";
		strHTML +="		</th>";
		strHTML +="	</tr>";
		if (ro != null) {
			if (ro.size() > 0 ) {
				for (int pos = 0; pos < ro.size() ; pos++) {
					strHTML +="	<tr>";
					strHTML +="		<td>";
					strHTML +="			" + ro.get(pos).getBuildingName();
					strHTML +="		</td><td>";
					strHTML +="			" + ro.get(pos).getRoomLocation();
					strHTML +="		</td><td>";
					strHTML +="			"+ ro.get(pos).getOccupation() +"%";
					strHTML +="		</td>";
				}
			} else {
				strHTML +="	<tr>";
				strHTML +="		<td>";
				strHTML +="			No";
				strHTML +="		</td><td>";
				strHTML +="			Records";
				strHTML +="		</td><td>";
				strHTML +="			Found";
				strHTML +="		</td>";
			}

		} else {
			strHTML +="	<tr>";
			strHTML +="		<td>";
			strHTML +="			No";
			strHTML +="		</td><td>";
			strHTML +="			Records";
			strHTML +="		</td><td>";
			strHTML +="			Found";
			strHTML +="		</td>";
		}
		strHTML +="	</tr>";
		strHTML +="</table>";
		strHTML +="<br>";
		strHTML +="<br>";
		strHTML +="<br>";
		strHTML +="</BODY></HTML>";

		txtPreview.setText(strHTML);
	}

	private void loadEquipment() {

		String strHTML = "";
		File f = new File("resources/boardroom.jpg");
		String filepath = f.getAbsoluteFile().toString();

		filepath = "file:/" + filepath.replace('\\', '/');




		String fromDate = fromDatePicker.getModel().getValue().toString();
		String toDate = toDatePicker.getModel().getValue().toString();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fromD = new Date();
		Date toD = new Date();

		try {
			fromD = format.parse(fromDate);
			toD = format.parse(toDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<Equipment>  eq = client.getReportEquipment(fromD, toD);

		strHTML ="<HTML><HEAD>";
		strHTML +="<STYLE>";
		strHTML +="Body {font-size: 10px;}";
		strHTML +="H1 {text-decoration: underline; font-size: 16px;}";
		strHTML +="table {font-family: arial, sans-serif;border-collapse: collapse; font-size: 12px;}";
		strHTML +=".table100 {width: 90%; border-width: 3px; border-style: solid; margin-left:auto;  margin-right:auto;}";
		strHTML +=".table80 { width: 70%; margin-left:auto;  margin-right:auto;}";
		strHTML +=".td30 {width: 30%; border-style: none;}";
		strHTML +=".td70 {width: 70%; border-style: none;}";
		strHTML +="td, th {border: 1px solid #bbbbbb; text-align: left; padding: 8px; font-size: 8px;}";
		strHTML +="th {color: #ffffff; background: #6c7ae0; font-size: 9px;}";
		strHTML +="tr:nth-child(even) {background-color: #f5f5f5;}";
		strHTML +="</STYLE>";
		strHTML +="</HEAD><BODY>";
		strHTML +="<table class='table100' align=center><tr><td class='td30'>";
		strHTML +="<img src='" + filepath + "'></td><td class='td70'>";
		strHTML +="<H1>Equipment Requested</H1></td></tr></table>";
		strHTML +="<br>";
		strHTML +="<br>";
		strHTML +="<p style='text-align: center;'><font style='Font-weight: bold;'>Report Description: </font>Equipment requested for meetings for a given period.<br>";
		strHTML +="<br>";
		strHTML +="<font style='Font-weight: bold;'>From Date: </font>" + fromDate + "<br>";
		strHTML +="<font style='Font-weight: bold;'>To Date: </font>" + toDate + "<br></p>";
		strHTML +="<br>";
		strHTML +="<table class='table80' align=center>";
		strHTML +="	<tr>";
		strHTML +="		<th>";
		strHTML +="			Equipment Description";
		strHTML +="		</th><th>";
		strHTML +="			Requested";
		strHTML +="		</th>";
		strHTML +="	</tr>";
		if (eq != null ) {
			if (eq.size() > 0) {
				for (int pos = 0; pos < eq.size(); pos++){
					strHTML +="	<tr>";
					strHTML +="		<td>";
					strHTML +="			 " + eq.get(pos).getEquipmentDesc();
					strHTML +="		</td><td>";
					strHTML +="			" + eq.get(pos).getQty();
					strHTML +="		</td>";
					strHTML +="	</tr>";
				}
			} else {
				strHTML +="	<tr>";
				strHTML +="		<td>";
				strHTML +="			 No Data";
				strHTML +="		</td><td>";
				strHTML +="			&nbsp" ;
				strHTML +="		</td>";
				strHTML +="	</tr>";
			}	
		} else {
			strHTML +="	<tr>";
			strHTML +="		<td>";
			strHTML +="			 No Records Found";
			strHTML +="		</td><td>";
			strHTML +="			&nbsp" ;
			strHTML +="		</td>";
			strHTML +="	</tr>";
		}
		strHTML +="</table>";
		strHTML +="<br>";
		strHTML +="<br>";
		strHTML +="<br>";
		strHTML +="</BODY></HTML>";

		txtPreview.setText(strHTML);
	}

	private void loadWaitList() {

		String strHTML = "";
		File f = new File("resources/boardroom.jpg");
		String filepath = f.getAbsoluteFile().toString();

		filepath = "file:/" + filepath.replace('\\', '/');

		String fromDate = fromDatePicker.getModel().getValue().toString();
		String toDate = toDatePicker.getModel().getValue().toString();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fromD = new Date();
		Date toD = new Date();

		try {
			fromD = format.parse(fromDate);
			toD = format.parse(toDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<Reservation> waiting = client.findWaitingReservations(fromD, toD);




		strHTML ="<HTML><HEAD>";
		strHTML +="<STYLE>";
		strHTML +="Body {font-size: 10px;}";
		strHTML +="H1 {text-decoration: underline; font-size: 16px;}";
		strHTML +="table {font-family: arial, sans-serif;border-collapse: collapse; font-size: 12px;}";
		strHTML +=".table100 {width: 90%; border-width: 3px; border-style: solid; margin-left:auto;  margin-right:auto;}";
		//strHTML +=".table80 { width: 90%; margin-left:auto;  margin-right:auto;}";
		strHTML +=".td30 {width: 30%; border-style: none;}";
		strHTML +=".td70 {width: 70%; border-style: none;}";
		strHTML +="td, th {border: 1px solid #bbbbbb; text-align: left;  padding: 8px; font-size: 8px;}";
		strHTML +="th {color: #ffffff; background: #6c7ae0; font-size: 9px;}";
		strHTML +="tr:nth-child(even) {background-color: #f5f5f5;}";
		strHTML +="</STYLE>";
		strHTML +="</HEAD><BODY>";
		strHTML +="<table class='table100' align=center><tr><td class='td30'>";
		strHTML +="<img src='" + filepath + "'></td><td class='td70'>";
		strHTML +="<H1>Waiting List</H1></td></tr></table>";
		strHTML +="<br>";
		strHTML +="<br>";
		strHTML +="<p style='text-align: center;'><font style='Font-weight: bold;'>Report Description: </font>All users added to a waiting list.<br>";
		strHTML +="<br>";
		strHTML +="<font style='Font-weight: bold;'>From Date: </font>" + fromDate + "<br>";
		strHTML +="<font style='Font-weight: bold;'>To Date: </font>" + toDate + "<br></p>";
		strHTML +="<br>";
		strHTML +="<table class='.table100' align=Center>";
		strHTML +="	<tr>";
		strHTML +="		<th>";
		strHTML +="			User";
		strHTML +="		</th><th>";
		strHTML +="			Meeting Room";
		strHTML +="		</th><th>";
		strHTML +="			Start Time";
		strHTML +="		</th><th>";
		strHTML +="			End Time<";
		strHTML +="		</th><th>";
		strHTML +="			Equipment";
		strHTML +="		</th>";
		strHTML +="	</tr>";
		for (int pos = 0 ; pos < waiting.size(); pos++) {
			strHTML +="	<<tr>";
			strHTML +="		<td>";
			strHTML +="			" + waiting.get(pos).getUser().getName();
			strHTML +="		</td><td>";
			strHTML +="			" + waiting.get(pos).getRoom().getBuilding().getBuildingName() + " - " + waiting.get(pos).getRoom().getRoomLocation() ;
			strHTML +="		</td><td>";
			strHTML +="			" + waiting.get(pos).getMeetingStart();
			strHTML +="		</td><td>";
			strHTML +="			" + waiting.get(pos).getMeetingEnd();
			strHTML +="		</td><td>";
			if (waiting.get(pos).getEquipment() != null ) {
				for (int count = 0 ; count < waiting.get(pos).getEquipment().size(); count++) {
					strHTML += waiting.get(pos).getEquipment().get(count).getEquipmentDesc() + " (";
					strHTML += waiting.get(pos).getEquipment().get(count).getQty() + ")<br>";
				}
			} else {
				strHTML+= "None";
			}
			strHTML +="		</td>";
			strHTML +="	</tr>";
		}
		strHTML +="</table>";
		strHTML +="<br>";
		strHTML +="<br>";
		strHTML +="<br>";
		strHTML +="</BODY></HTML>";

		txtPreview.setText(strHTML);
	}
	
	 private String resolveDate(Date d) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(d);
	        String outDate = String.valueOf(cal.get(Calendar.YEAR)) + "-";
	        if ((cal.get(Calendar.MONTH)) < 10 ) {
	               outDate += "0" + (cal.get(Calendar.MONTH)) + "-";
	        } else {
	               outDate +=  (cal.get(Calendar.MONTH)) + "-";
	        }

	        if (cal.get(Calendar.DAY_OF_MONTH) < 10 ) {
	               outDate += "0" + cal.get(Calendar.DAY_OF_MONTH);
	        } else {
	               outDate +=  cal.get(Calendar.DAY_OF_MONTH) ;
	        }
	        return outDate;
	 }
}
