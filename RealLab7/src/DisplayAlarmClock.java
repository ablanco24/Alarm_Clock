import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class DisplayAlarmClock {

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayAlarmClock window = new DisplayAlarmClock();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DisplayAlarmClock() {

		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		AlarmClock alarm = new AlarmClock();
		alarm.setTime();
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHours = new JLabel(alarm.getHours());
		lblHours.setBounds(101, 46, 61, 63);
		frame.getContentPane().add(lblHours);
		
		JLabel lblMins = new JLabel(alarm.getMinutes());
		lblMins.setBounds(161, 46, 61, 63);
		frame.getContentPane().add(lblMins);
		

		JLabel lblAM_PM = new JLabel(alarm.getAM_PM());
		lblAM_PM.setBounds(234, 65, 61, 24);
		frame.getContentPane().add(lblAM_PM);
		
	
		JButton btnSetTime = new JButton("Set Time");
		btnSetTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alarm.setTime();
				lblMins.setText(alarm.getMinutes());
				lblHours.setText(alarm.getHours());
				lblAM_PM.setText(alarm.getAM_PM());
				
			}
		});
		btnSetTime.setBounds(6, 221, 117, 29);
		frame.getContentPane().add(btnSetTime);
		
		JLabel lblAlarmSet = new JLabel("The alarm is on: " + String.valueOf(alarm.getSet()));
		lblAlarmSet.setBounds(146, 193, 149, 16);
		frame.getContentPane().add(lblAlarmSet);
		
		JButton btnSetAlarm = new JButton("Set Alarm");
		btnSetAlarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alarm.setAlarm();
				lblAlarmSet.setText("The alarm is on: " + String.valueOf(alarm.getSet()));
			}
		});
		btnSetAlarm.setBounds(156, 221, 117, 29);
		frame.getContentPane().add(btnSetAlarm);
		
		JButton btnSoundAlarm = new JButton("Sound Alarm");
		btnSoundAlarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Snooze", "Off"};
				int i = JOptionPane.showOptionDialog(frame, "RING", "Alarm", JOptionPane.YES_NO_OPTION, 1, null, options, options[1]);
				if (i == JOptionPane.YES_OPTION)
				{
					alarm.snooze();
					System.out.println("Snoozed");
				}

				
				
			}
		});
		btnSoundAlarm.setBounds(327, 221, 117, 29);
		frame.getContentPane().add(btnSoundAlarm);
		
		JButton btnTick = new JButton("Tick");
		btnTick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alarm.tick();
				lblHours.setText(alarm.getHours());
				lblMins.setText(alarm.getMinutes());
				lblAM_PM.setText(alarm.getAM_PM());
				lblAlarmSet.setText("The alarm is on: " + String.valueOf(alarm.getSet()));
				alarm.alarm();
				
			}
		});
		btnTick.setBounds(101, 112, 117, 29);
		frame.getContentPane().add(btnTick);
		
		JButton btnToggleHour = new JButton("Toggle 12 Hour Mode");
		btnToggleHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!alarm.isTwentyFour())
				{
					alarm.toggleTypeClock(false);
					lblHours.setText(alarm.getHours());
					lblAM_PM.setText("");
				}
				else
				{
					alarm.toggleTypeClock(true);
					lblHours.setText(alarm.getHours());
					lblAM_PM.setText(alarm.getAM_PM());
				}
				
				
			}
		});
		btnToggleHour.setBounds(280, 64, 164, 29);
		frame.getContentPane().add(btnToggleHour);
		
		JButton btnToggle = new JButton("Toggle Alarm");
		btnToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alarm.toggleSet();
				lblAlarmSet.setText("The alarm is on: " + String.valueOf(alarm.getSet()));
				alarm.alarm();
			}
		});
		btnToggle.setBounds(146, 164, 117, 29);
		frame.getContentPane().add(btnToggle);
		
	
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);
			
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmInstructionsForSetting = new JMenuItem("Instructions For Setting Alarm");
		mntmInstructionsForSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, alarm.getHowTo(), "How to Work Alarm", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnHelp.add(mntmInstructionsForSetting);
		
		
	}
}
