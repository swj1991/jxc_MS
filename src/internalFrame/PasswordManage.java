package internalFrame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import com.henu.factory.DAOFactory;
import com.henu.login.Login;

import tableModel.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class PasswordManage extends JInternalFrame {
	private User user = Login.getUser();
	private JTextField txtPsdName;
	private JTextField txtPsdId;
	private JPasswordField psdPsdOld;
	private JPasswordField psdPsdNew;
	private JPasswordField psdPsdAgain;
	
	public PasswordManage() {
		setVisible(true);
		setResizable(false);
		setIconifiable(true);
		setClosable(true);
		setTitle("�������");
		
		setBounds(100, 100, 240, 261);
		
		JLabel lblPsdName = new JLabel("�û�������");
		
		txtPsdName = new JTextField();
		txtPsdName.setEditable(false);
		txtPsdName.setColumns(10);
		
		JLabel lblPsdId = new JLabel("��¼�ʺţ�");
		
		txtPsdId = new JTextField();
		txtPsdId.setEditable(false);
		txtPsdId.setColumns(10);
		
		JLabel lblPsdOldPsd = new JLabel("�����룺");
		
		JLabel lblPsdNewPsd = new JLabel("�����룺");
		
		JLabel lblPsdAgainPsd = new JLabel("�ٴ����������룺");
		
		//�����¼����Ϣ
		txtPsdName.setText(user.getUserName());
		txtPsdId.setText(user.getUserId());
		
		JButton btnPsdClear = new JButton("���");
		btnPsdClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				psdPsdOld.setText("");
				psdPsdNew.setText("");
				psdPsdAgain.setText("");
				psdPsdOld.requestFocusInWindow();
			}
		});
		
		JButton btnPsdModify = new JButton("����");
		btnPsdModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldPassword = String.valueOf(psdPsdOld.getPassword());
				String newPassword = String.valueOf(psdPsdNew.getPassword());
				String againPassword = String.valueOf(psdPsdAgain.getPassword());
				boolean flag = false;
				//������֤
				if(oldPassword.trim().length() == 0) {
					JOptionPane.showMessageDialog(null,
							"����д������!", "�����޸�", JOptionPane.ERROR_MESSAGE);	
					psdPsdOld.setText("");
					psdPsdOld.requestFocusInWindow();
					return;
				}
				if(newPassword.trim().length() == 0) {
					JOptionPane.showMessageDialog(null,
							"����д������!", "�����޸�", JOptionPane.ERROR_MESSAGE);	
					psdPsdNew.setText("");
					psdPsdNew.requestFocusInWindow();
					return;
				}
				if(againPassword.trim().length() == 0) {
					JOptionPane.showMessageDialog(null,
							"���ٴ���д������!", "�����޸�", JOptionPane.ERROR_MESSAGE);	
					psdPsdAgain.setText("");
					psdPsdAgain.requestFocusInWindow();
					return;
				}
				if(!newPassword.equals(againPassword)) {
					JOptionPane.showMessageDialog(null,
							"�������������ͬ!", "�����޸�", JOptionPane.ERROR_MESSAGE);	
					psdPsdNew.setText("");
					psdPsdAgain.setText("");
					psdPsdNew.requestFocusInWindow();
					return;
				}
				if(!oldPassword.equals(user.getUserPassword())) {
					JOptionPane.showMessageDialog(null,
							"�����벻��ȷ,��������д!", "�����޸�", JOptionPane.ERROR_MESSAGE);	
					psdPsdOld.setText("");
					psdPsdNew.setText("");
					psdPsdAgain.setText("");
					psdPsdOld.requestFocusInWindow();
					return;
				}
				
				user.setUserPassword(newPassword);
				try {
					flag = DAOFactory.getIUserDAOInstance().doUpdate(user);
					if(flag) {
						JOptionPane.showMessageDialog(null, user.getUserName() + " ������ĳɹ�,���μ�������!");
					}else
						JOptionPane.showMessageDialog(null, 
								"ϵͳ����,�Ժ�����","�����޸�", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
				
			}
		});
		
		psdPsdOld = new JPasswordField();
		
		psdPsdNew = new JPasswordField();
		
		psdPsdAgain = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(23)
									.addComponent(btnPsdModify)
									.addGap(18)
									.addComponent(btnPsdClear))
								.addComponent(lblPsdAgainPsd)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPsdNewPsd)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(psdPsdNew, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPsdName)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtPsdName, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPsdId)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtPsdId, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(lblPsdOldPsd)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(psdPsdOld, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(62)
							.addComponent(psdPsdAgain, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPsdName)
						.addComponent(txtPsdName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPsdId)
						.addComponent(txtPsdId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPsdOldPsd)
						.addComponent(psdPsdOld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPsdNewPsd)
						.addComponent(psdPsdNew, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPsdAgainPsd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(psdPsdAgain, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPsdModify)
						.addComponent(btnPsdClear))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
}
