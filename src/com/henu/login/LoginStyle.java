package com.henu.login;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LoginStyle extends JPanel {
	protected ImageIcon logoIcon;
	public int width,height;
	public LoginStyle() {
		super();
		logoIcon = new ImageIcon(LoginStyle.class.getResource("/image/loginLogo.jpg"));
		height = logoIcon.getIconHeight();
		setSize(width, height);
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = logoIcon.getImage();
		g.drawImage(img, 0, 0,getParent());
	}

}
