package com.henu.login;

import internalFrame.CustomerManage;
import internalFrame.Register;
import internalFrame.UnActivated;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.henu.jxcFrame.JxcMain;
import com.henu.login.LoginStyle;
import com.henu.dao.*;
import com.henu.factory.DAOFactory;

import tableModel.User;

public class Login extends JFrame {
	private JLabel loginUserLabel;
	private JLabel loginPasswordLabel;
	private JButton loginExitButton;
	private JButton loginLoginButton;
	private JPasswordField userPasswordField = new JPasswordField();
	private JTextField userNameField = new JTextField();
	private static User user;
	
	public Login() {
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("登录进销存管理系统");
		this.setLocationRelativeTo(null);
		JPanel panel = new LoginStyle();
		panel.setLayout(null);
		getContentPane().add(panel);
		setBounds(300, 200, 430, 320);
		loginUserLabel = new JLabel();
		loginUserLabel.setText("用户名：");
		loginUserLabel.setBounds(100, 135, 200, 18);
		panel.add(loginUserLabel);
		
		userNameField.setBounds(150, 135, 200, 18);
		userNameField.requestFocusInWindow();
		panel.add(userNameField);
		loginPasswordLabel = new JLabel();
		loginPasswordLabel.setText("密  码：");
		loginPasswordLabel.setBounds(100, 165, 200, 18);
		panel.add(loginPasswordLabel);
		userPasswordField.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == 10)
					loginLoginButton.doClick();
			}
		});
		userPasswordField.setBounds(150, 165, 200, 18);
		panel.add(userPasswordField);
		loginLoginButton = new JButton();
		loginLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String userId = userNameField.getText();
				String userPassword = String.valueOf(userPasswordField.getPassword());
				//用户登陆信息验证
				if(userId.trim().length() != 10) {
					JOptionPane.showMessageDialog(Login.this,
							"请输入10位用户名!", "登录提示", JOptionPane.ERROR_MESSAGE);
					userNameField.requestFocusInWindow();
					return;
				}
				if(userPassword.trim().length() == 0) {
					JOptionPane.showMessageDialog(Login.this,
							"请输入密码!", "登录提示", JOptionPane.ERROR_MESSAGE);
					userPasswordField.requestFocusInWindow();
					return;
				}
				//验证用户是否存在
				try {
					List<User> all = DAOFactory.getIUserDAOInstance().findAll();
					boolean isIdOk = false;
					boolean isPsdOk = false;
					int index;
					//验证用户名是否存在
					for (index = 0;index<all.size();index++) {
						if(userId.equals(all.get(index).getUserId())) {
							isIdOk = true;
							break;
						}
					}
					if(!isIdOk) {
						JOptionPane.showMessageDialog(null,"用户名不存在,请重新输入!");
						userNameField.setText("");
						userPasswordField.setText("");
						userNameField.requestFocusInWindow();
						return;
					}
					//验证密码是否正确					
					if(!userPassword.equals(all.get(index).getUserPassword())) {
						JOptionPane.showMessageDialog(null,"密码不正确,请重新输入!");
						userPasswordField.setText("");
						userPasswordField.requestFocusInWindow();
						return;
					}else
						isPsdOk = true;
					
					if(isIdOk & isPsdOk) {
						JOptionPane.showMessageDialog(null,"登录成功!");								
					}
					
					//获取全局用户变量
					user = DAOFactory.getIUserDAOInstance().getUser(userId, userPassword);
					if(user.getUserClassId().equals("usc05")){
						setVisible(false);
						new UnActivated();
					}else {
						setVisible(false);
						new JxcMain();
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
//					JOptionPane.showMessageDialog(null,"数据库连接失败!");
//					return;
					e1.printStackTrace();
				}											
			}
		});
		loginLoginButton.setText("登录");
		loginLoginButton.setBounds(180, 195, 60, 18);
		panel.add(loginLoginButton);
		loginExitButton = new JButton();
		loginExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				new Register();
				setVisible(false);
			}
		});
		loginExitButton.setText("注册");
		loginExitButton.setBounds(260, 195, 60, 18);
		panel.add(loginExitButton);
		
	}
	
	public static User getUser() {
		return user;
	}
	public static void setUser(User user) {
		Login.user = user;
	}

}
