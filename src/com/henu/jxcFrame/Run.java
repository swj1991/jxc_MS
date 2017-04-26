package com.henu.jxcFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.henu.login.Login;

public class Run {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Login();
			}
		});
	}
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

