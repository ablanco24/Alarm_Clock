import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 * 
 * @author Andrew Blanco
 * @version 4/3/16
 * 
 *
 */
public class AlarmClock {

	private String hours;
	private int hoursInNum;
	private String minutes;
	private int minsInNum;
	private String alarmHours;
	private int alarmHrs;
	private String alarmMinutes;
	private int alarmMins;
	private String am_pm;
	private String alarmam_pm;
	private String howTo;
	private boolean isSet;
	private boolean isTwentyFour;
	
	//constructor, also contains the howTo message, initializes variables
	public AlarmClock()
	{
		hours = "0";
		minutes = "0";
		alarmHours = "0";
		alarmMinutes = "0";
		am_pm = "am";
		alarmam_pm = "0";
		isTwentyFour = false;
		isSet = false;
		howTo = "HOW TO SET ALARM \n1. Set your time in 12 hour mode, then alarm interface will pop up. \n2. Toggle which ever mode you wish to be in (24 or 12)" + 
			"\n3. Click 'Set Alarm' button, enter time you wish for it to go off. \n4. Click tick button and hte alarm will go off!";
	}

	//Sets the initial time and sets time when setTime button is clicked. 
	//Asks for it in 24HOur and 12 hour depending what mode you're in
	public void setTime()
	{
		Scanner in = new Scanner(System.in);
		
		//prompts user for input hours
		System.out.println("Enter the hour of day in 12-hour format: ");
		hours = in.next();
		hoursInNum = Integer.parseInt(hours);
		if(!isTwentyFour)
		{
			while(hoursInNum < 1 || hoursInNum > 12) 
			{
				hoursInNum = Integer.parseInt(hours);
				System.out.println("Enter the hour of day in 12-hour format: ");
				hours = in.next();
			}
		} 
		
		//prompts user for input minutes
		System.out.println("Enter the minutes in the day in 12-hour format: ");
		minutes = in.next();
		minsInNum = Integer.parseInt(minutes);
		if(!isTwentyFour)
		{
			while(minsInNum < 0 || minsInNum > 59)
			{
				minsInNum = Integer.parseInt(minutes);
				System.out.println("Enter the minutes in the day in 12-hour format: ");
				minutes = in.next();
			}
			System.out.println("Enter whether it is AM or PM: ");
			am_pm = in.next(); 
		}
		
	}
	
	//Sets the alarm time, when it will go off
	public void setAlarm()
	{
		Scanner in = new Scanner(System.in);
		
		//checks if is in 24 hour mode, asks for time to set alarm accordingly
		if (!isTwentyFour)
		{
			System.out.println("Enter hour for alarm to go off in 12 hour format:");
		}
		else
		{
			System.out.println("Enter hour for alarm to go off in 24 hour format:");
		}
		alarmHours = in.next();
		
		//makes it into an int
		alarmHrs = Integer.parseInt(alarmHours);
		
		//adds 0 to front of int, converts it back to string
		if (alarmHrs < 10)
		{
			alarmHours = "0" + alarmHrs;
		}
		else
		{
			alarmHours = Integer.toString(alarmHrs);
		}
		
		//checks if it is in 24 hour mode, asks fo minutes accordingly
		if (!isTwentyFour)
		{
			System.out.println("Enter minutes for alarm to go off in 12 hour format:");
		}
		else
		{
			System.out.println("Enter minutes for alarm to go off in 24 hour format:");
		}
		alarmMinutes = in.next();
		
		//converts it to an int
		alarmMins = Integer.parseInt(alarmMinutes);
		
		//converts back to string, adds 0 to front
		if (alarmMins < 10)
		{
			alarmMinutes = "0" + alarmMins;
		}
		else
		{
			alarmMinutes = Integer.toString(alarmMins);
		}
		
		//asks for am/pm if it is in 12 hour mode
		if (!isTwentyFour)
		{
			System.out.println("Enter AM or PM for the Alarm to go off:");
			alarmam_pm = in.next();
		}
		
		//sets alarm to on
		isSet = true;

	}
	
	//toggles mode of clock between 24 and 12 hour
	//@param isMilitary, recieved from gui to decide if it's in 24 hour mode or 24 hour mode
	public void toggleTypeClock(boolean isMilitary)
	{
		if (!isMilitary)
		{
			int hrs = Integer.parseInt(hours);
			
			//takes care of converstion when it is 12 am
			if(hrs == 12 && am_pm.equalsIgnoreCase("am"))
			{
				hrs = 0;
			}
			else if(am_pm.equalsIgnoreCase("am"))
			{
			}
			//adds 12 if it is not in the morning
			else
			{
				hrs += 12;
			}
			
			hours = Integer.toString(hrs);
			isMilitary = true;
			isTwentyFour = true;
		}
		else
		{
			int hrs = Integer.parseInt(hours);
			
			//takes care of special case when it is 12 am
			if(hrs == 0)
			{
				hrs += 12;
				am_pm = "am";
			}
			else if(hrs < 12)
			{
				am_pm = "am";
			}
			else
			{
				hrs -= 12;
				am_pm = "pm";
			}
			hours = Integer.toString(hrs);
			isMilitary = false;
			isTwentyFour = false;
		}
	}
	
	//toggles the alarm being on or off
	public void toggleSet()
	{
		if(isSet)
		{
			isSet = false;
		}
		else
		{
			isSet = true;
		}
	}
	
	/**
	 * snoozes the alarm, will add 9 minutes to alarm mins and update the hours accordingly 
	 */
	public void snooze()
	{
		//converts the alarms hours and minutes to ints so it's manageable to add/subtract time
		alarmHrs = Integer.parseInt(alarmHours);
		alarmMins = Integer.parseInt(alarmMinutes);
		
		//adds 9 minutes if it doesnt go into the next hour
		if((alarmMins + 9) < 60)
		{
			alarmMins += 9;
		}
		//adds 9 minutes if it does go into the next hour
		else
		{
			alarmMins += 9;
			alarmMins -= 60;
			alarmHrs += 1;
			//converts alarm time to am/pm if the hour goes past 12
			if ((alarmHrs + 1) == 13)
			{
				if (alarmam_pm.equalsIgnoreCase("am"))
				{
					alarmam_pm = "pm";
				}
				else if(alarmam_pm.equalsIgnoreCase("pm"))
				{
					alarmam_pm = "am";
				}
			}
		}
		
		//converts from being 13 oclock in 12 hour mode
		if(!isTwentyFour)
		{
			if(alarmHrs > 12)
			{
				alarmHrs -= 12;
				
			}
		}
		
		//adds 0 to front of minutes if it is 1 digit long, converts minutes back to string
		if (alarmMins < 10)
		{
			alarmMinutes = "0" + alarmMins;
		}
		else
		{
			alarmMinutes = Integer.toString(alarmMins);
		}
		
		//ads 0 to front of hours if it is 1 digit long, converts minutes back to string
		if (alarmHrs < 10)
		{
			alarmHours = "0" + alarmHrs;
		}
		else
		{
			alarmHours = Integer.toString(alarmHrs);
		}
		
	}
	
	/**
	 * checks if it is time for the alarm to go off
	 */
	public void alarm()
	{
		//makes sure the alarm is set
		if(isSet)
		{
			//checks if the hours match
			if (alarmHours.equalsIgnoreCase(hours))
			{
				//checks if minutes match
				if (alarmMinutes.equalsIgnoreCase(minutes))
				{
					/**
					 * checks if it is 24 hour mode, if it is, it starts the alarm, 
					 * if it isn't it checks to make sure am/pm match, then sounds alarm
					 * 
					 */
					if(isTwentyFour)
					{
						Object[] options = {"Snooze", "Off"};
						int i = JOptionPane.showOptionDialog(null, "RING", "Alarm", JOptionPane.YES_NO_OPTION, 1, null, options, options[1]);
						if (i == JOptionPane.YES_OPTION)
						{
							snooze();
							System.out.println("Snoozed");
						}
						else if (i == JOptionPane.NO_OPTION)
						{
							isSet = false;
							System.out.println("Alarm is Off");
						}
					}
					else if(alarmam_pm.equalsIgnoreCase(am_pm))
					{
						Object[] options = {"Snooze", "Off"};
						int i = JOptionPane.showOptionDialog(null, "RING", "Alarm", JOptionPane.YES_NO_OPTION, 1, null, options, options[1]);
						if (i == JOptionPane.YES_OPTION)
						{
							snooze();
							System.out.println("Snoozed");
						}
						else if (i == JOptionPane.NO_OPTION)
						{
							isSet = false;
							System.out.println("Alarm is Off");
						}
					}
				}
			}
		}
	}
	
	/**
	 * Ticks clock forward 1 minute
	 */
	public void tick()
	{
		//Converts time from string to ints
		hoursInNum = Integer.parseInt(hours);
		minsInNum = Integer.parseInt(minutes);
		
		//adds 1 minute
		minsInNum++; 
		
		//sets minutes to 0 when it hits 60, adds 1 to hour
		if(minsInNum == 60)
		{
			hoursInNum++;
			minsInNum = 0;
			
		}
		//if it is in twenty four hour mode, takes away am/pm
		if(isTwentyFour)
		{
			am_pm = "";
		}
		//determines whether or not it should be am or pm
		else
		{
			if(hoursInNum > 12)
			{
				hoursInNum = 1;
			}
			
			if (hoursInNum == 12 && minsInNum == 0)
			{
				if(am_pm.equalsIgnoreCase("am"))
				{
					am_pm = "pm";
				}
				else
				{
					am_pm = "am";
				}
			}
			
		}
		//puts ints back into strings for printing
		hours = "";
		minutes = "";
		if(hoursInNum < 10)
		{
			
			hours = "0";
		}
		if(minsInNum < 10)
		{	
			minutes = "0";
		}
		
		hours += hoursInNum;
		minutes += minsInNum;
		
	}
	
	
	//@return hours returns current hour
	public String getHours()
	{
		return hours;
	}
	
	//@return minutes returns current minute
	public String getMinutes()
	{
		return minutes;
	}
	
	//@return am_pm returns if it's am/pm
	public String getAM_PM()
	{
		return am_pm;
	}
	
	//@return isSet determines if alarm is set
	public boolean getSet()
	{
		return isSet;
	}
	
	//@return alarmHours the alarm hour it is set to go off
	public String getAlarmHours()
	{
		return alarmHours;
	}
	
	//@return alarmMinutes the alarm minute it is set to go off
	public String getAlarmMinutes()
	{
		return alarmMinutes;
	}
	
	//@return howTo the howTo to set the alarm
	public String getHowTo()
	{
		return howTo;
	}
	
	//@return returns if it is twenty four hour mode or not
	public boolean isTwentyFour()
	{
		return isTwentyFour;
	}
	
	
	
	
	
}
