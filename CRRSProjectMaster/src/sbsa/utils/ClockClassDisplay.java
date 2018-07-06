package sbsa.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import javax.swing.*;


public class ClockClassDisplay implements Runnable {

	private JLabel pLabel;
	private Thread thread; 
	private boolean run;  


	public ClockClassDisplay(JLabel pLabel) {
		super();
		run=true;
		this.pLabel = pLabel;		
		thread = new Thread(this);
		//thread.start();
	}


	public Thread getT ()
	{
		System.out.println("test get");
		return this.thread;
	}

	public void killT (Thread t)
	{
		this.run=false;
	}



	protected JLabel getpLabel() {
		return pLabel;
	}




	public void run() {

		//System.out.println(LocalDateTime.now());
		while (run)
		{
			//System.out.println();

			LocalDateTime lDT= LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
			String text = lDT.format(formatter);
			//LocalDateTime parsedDateTime = LocalDateTime.parse(text, formatter);

			pLabel.setText( text ); // the setText causes the object to automatically refresh the data on the screen

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JLabel inLabel = new JLabel("blank");
		ClockClassDisplay ctest = new ClockClassDisplay(inLabel);
		ctest.run();
		inLabel=ctest.getpLabel();
		System.out.println(inLabel.getText());

	}

}
