package main.view;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;

import javax.swing.JLabel;

public class ClockDisplay {

	private   static ClockDisplay sharedClock;
	private boolean isEnabled;
	private double interval;
	private int hour, minute, second;
	private JLabel timeLabel;
	public static ClockDisplay getInstance()
	{
		if(sharedClock==null)
		{
			sharedClock= new ClockDisplay();			

			String timeString = "00:00:00";
			sharedClock.timeLabel = new JLabel(timeString);
			//sharedClock.setX(188);
			//sharedClock.setY(5);
			sharedClock.timeLabel.setForeground(Color.blue);
			GameBoard.getGameBoard().add(sharedClock.timeLabel);	
			sharedClock.timeLabel.setVisible(false);

		}
		return sharedClock;
	}
	private void updateClock(){


		interval += 0.01;
		DecimalFormat dtime = new DecimalFormat("#.###"); 
		interval= Double.valueOf(dtime.format(interval));

		String strHour=(sharedClock.getHour()<=9?("0"+sharedClock.getHour()):sharedClock.getHour()).toString();
		String strMinute= (sharedClock.getMinute()<=9?("0"+sharedClock.getMinute()):sharedClock.getMinute()).toString();
		String strSecond= (sharedClock.getSecond()<=9?("0"+sharedClock.getSecond()):sharedClock.getSecond()).toString();
		timeLabel.setText(strHour+":"+strMinute+":"+strSecond);

		if(interval%1.0 == 0.0){
			second+=1;
			sharedClock.setSecond(second);
			if (sharedClock.second%60 == 0 && sharedClock.minute!=59){
				minute+=1;
				second=0;
				sharedClock.setMinute(minute);
				sharedClock.setSecond(second);
				interval=0.0;
			}else if(sharedClock.second%60==0 && minute%59==0){
				hour+=1;
				minute=0;
				second=0;
				setHour(hour);
				setMinute(minute);
				setSecond(second);
			}
		}

	}

	public void draw(Graphics g) {
		if(sharedClock.isEnabled())
			sharedClock.updateClock();
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		sharedClock.timeLabel.setVisible(isEnabled());
	}

	public double getInterval() {
		return interval;
	}

	public void setInterval(double interval) {
		this.interval = interval;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

}
