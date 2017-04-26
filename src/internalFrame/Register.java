package internalFrame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;

import tableModel.Check;
import tableModel.User;
import com.henu.factory.DAOFactory;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField txtRegUserId;
	private JTextField txtRegUserAddress;
	private JTextField txtRegUserName;
	private JTextField txtRegUserPhone;
	private JTextField txtRegUserCardNumber;
	private JPasswordField psdTxtRegUserPassword;
	private JTextArea txtaRegUserRemark;
	
	public Register() {
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("用户注册");
		setBounds(100, 100, 480, 340);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JButton btnRegRegist = new JButton("注册");
		btnRegRegist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				checkUserId();
				boolean flag = false;
				String userId = txtRegUserId.getText();
				String userName = txtRegUserName.getText();
				String userPassword = String.valueOf(psdTxtRegUserPassword.getPassword());
				String userClassId = "usc05";
				String userPhoneNumber = txtRegUserPhone.getText();
				String userAddress = txtRegUserAddress.getText();
				String userCardNumber = txtRegUserCardNumber.getText();
				String userRemark = txtaRegUserRemark.getText();
				
				if(userId.trim().length()!= 10 ||
						userName.trim().length()==0
						|| userPassword.trim().length()==0
						|| userPhoneNumber.trim().length()==0
						|| userAddress.trim().length()==0
						|| userCardNumber.trim().length()==0) {
					JOptionPane.showMessageDialog(Register.this,
							"请完成未填写的信息。", "用户添加", JOptionPane.ERROR_MESSAGE);	
					return;
				}
				
				if(!Check.checkPhoneNumber(txtRegUserPhone.getText())){
					JOptionPane.showMessageDialog(Register.this,
							"请输入正确的联系方式!固定电话按区号-号码输入!", "用户添加", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!Check.checkIDCard(txtRegUserCardNumber.getText())){
					JOptionPane.showMessageDialog(Register.this,
							"请输入正确的身份信息!", "用户添加", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				User user = new User(userId, userName, userPassword, userClassId, userPhoneNumber, 
						userAddress, userCardNumber, userRemark);
				
				try {
					flag = DAOFactory.getIUserDAOInstance().doCreate(user);	
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(flag) {
					JOptionPane.showMessageDialog(null, userName + " 注册成功！");
				}
				
				setVisible(false);
				new UnActivated();
			}
		});
		
		JButton btnRegClear = new JButton("清空");
		btnRegClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRegUserId.setText("");
				psdTxtRegUserPassword.setText("");
				txtRegUserAddress.setText("");
				txtRegUserName.setText("");
				txtRegUserPhone.setText("");
				txtRegUserCardNumber.setText("");
				txtaRegUserRemark.setText("");
				txtRegUserId.requestFocusInWindow();
			}
		});
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblRegUserId = new JLabel("登录帐号：");
		
		txtRegUserId = new JTextField();
		txtRegUserId.setColumns(10);
		txtRegUserId.requestFocusInWindow();
		
		JLabel lblRegUserPassword = new JLabel("登录密码：");
		
		JLabel lblRegUserAddress = new JLabel("用户地址：");
		
		txtRegUserAddress = new JTextField();
		txtRegUserAddress.setText("");
		txtRegUserAddress.setColumns(10);
		
		JLabel lblRegUserName = new JLabel("用户姓名：");
		
		txtRegUserName = new JTextField();
		txtRegUserName.setText("");
		txtRegUserName.setColumns(10);
		
		JLabel lblRegUserPhone = new JLabel("用户电话：");
		
		txtRegUserPhone = new JTextField();
		txtRegUserPhone.setText("");
		txtRegUserPhone.setColumns(10);
		
		JLabel lblRegUserCardNumber = new JLabel("身份证号：");
		
		txtRegUserCardNumber = new JTextField();
		txtRegUserCardNumber.setColumns(10);
		
		JButton btnCheckUser = new JButton("验证用户");
		btnCheckUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				checkUserId();						
			}
		});
		
		psdTxtRegUserPassword = new JPasswordField();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRegUserId)
										.addComponent(lblRegUserPassword))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(psdTxtRegUserPassword, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtRegUserId, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblRegUserName)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtRegUserName, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblRegUserPhone)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtRegUserPhone, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(btnCheckUser)
									.addGap(34))))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblRegUserAddress)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtRegUserAddress))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblRegUserCardNumber)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtRegUserCardNumber, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRegUserId)
						.addComponent(txtRegUserId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCheckUser))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRegUserPassword)
						.addComponent(psdTxtRegUserPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRegUserName)
						.addComponent(txtRegUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRegUserPhone)
						.addComponent(txtRegUserPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRegUserCardNumber)
						.addComponent(txtRegUserCardNumber, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRegUserAddress)
						.addComponent(txtRegUserAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "备注信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
		);
		
		txtaRegUserRemark = new JTextArea();
		scrollPane.setViewportView(txtaRegUserRemark);
		panel_2.setLayout(gl_panel_2);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(98)
					.addComponent(btnRegRegist)
					.addPreferredGap(ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
					.addComponent(btnRegClear)
					.addGap(93))
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRegRegist)
						.addComponent(btnRegClear))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		contentPane.setLayout(gl_contentPane);
	}
	//验证ID的可用性
	public void checkUserId(){
		String userId = txtRegUserId.getText();
		if(userId.trim().length() != 10) {
			JOptionPane.showMessageDialog(null,"用户名必须为10位的有效字符!");
			return;
		}			
		try {
			List<User> all = DAOFactory.getIUserDAOInstance().findAll();
			boolean isExist = false;
			for (int i=0; i<all.size();i++) {
				if(userId.equals(all.get(i).getUserId())) {
					isExist = true;
					break;
				}				
			}
			if(isExist) {
				JOptionPane.showMessageDialog(null,"用户名已被注册请重新输入!");
				txtRegUserId.setText("");
				psdTxtRegUserPassword.setText("");
				txtRegUserAddress.setText("");
				txtRegUserName.setText("");
				txtRegUserPhone.setText("");
				txtRegUserCardNumber.setText("");
				txtaRegUserRemark.setText("");
				txtRegUserId.requestFocusInWindow();
				return;
			}else {
				JOptionPane.showMessageDialog(null,"用户名可用!");
				psdTxtRegUserPassword.requestFocusInWindow();
			}
				
				
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
