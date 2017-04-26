package internalFrame;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;
import tableModel.User;

import com.henu.factory.DAOFactory;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JScrollPane;

public class PowerManage extends JInternalFrame {
	private JTextField txtPowUserId;
	private JTextField txtPowUserName;
	private JTextField txtPowUserPhone;
	private JTextField txtPowUserCradNumber;
	private JTextField txtPowUserAddress;
	private JComboBox cboPowUserSelect;
	private JComboBox cboPowPowerSelect;
	private JTextArea txtaPowUserRemark;
	
	public PowerManage() {
		setVisible(true);
		setResizable(false);
		setIconifiable(true);
		setClosable(true);
		setTitle("权限管理");
		setBounds(56, 24, 480, 340);
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
		);
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "备注信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblPowUserSelect = new JLabel("选择用户：");
		
		cboPowUserSelect = new JComboBox();
		initComboUserBox();
		cboPowUserSelect.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){ 
					if(cboPowUserSelect.getSelectedIndex()==0){
						txtPowUserId.setText(null);
						txtPowUserName.setText(null);
						txtPowUserPhone.setText(null);
						txtPowUserCradNumber.setText(null);
						txtPowUserAddress.setText(null);
						cboPowPowerSelect.setSelectedIndex(0);
					return;
				}
				doUserSelectAction();
				}else{
					return;
				}
        }

		});
		
		JButton btnPowModify = new JButton("更改");
		btnPowModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userclassid="";
				if(cboPowUserSelect.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择需要更改的用户!");
					return;
				}
				if(cboPowPowerSelect.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null, "请选择一个类别做更改");
					return;
				}else if(cboPowPowerSelect.getSelectedIndex()==1){
					userclassid = "usc02";
				}else if(cboPowPowerSelect.getSelectedIndex()==2){
					userclassid = "usc03";
				}else if(cboPowPowerSelect.getSelectedIndex()==3){
					userclassid = "usc04";
				}else if(cboPowPowerSelect.getSelectedIndex()==4){
					userclassid = "usc05";
				}
				User user = new User();
				
				try {
					user = DAOFactory.getIUserDAOInstance().findById(txtPowUserId.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				user.setUserClassId(userclassid);
				try {
					DAOFactory.getIUserDAOInstance().doUpdate(user);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "更新成功！");
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPowUserSelect)
					.addContainerGap(400, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(cboPowUserSelect, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
					.addComponent(btnPowModify)
					.addGap(51))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblPowUserSelect)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboPowUserSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnPowModify))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(2)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
		);
		
		txtaPowUserRemark = new JTextArea();
		txtaPowUserRemark.setLineWrap(true);
		txtaPowUserRemark.setColumns(33);
		scrollPane.setViewportView(txtaPowUserRemark);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblPowUserId = new JLabel("用户帐号：");
		
		txtPowUserId = new JTextField();
		txtPowUserId.setEditable(false);
		txtPowUserId.setColumns(10);
		
		JLabel lblPowUserName = new JLabel("用户姓名：");
		
		txtPowUserName = new JTextField();
		txtPowUserName.setEditable(false);
		txtPowUserName.setText("");
		txtPowUserName.setColumns(10);
		
		txtPowUserPhone = new JTextField();
		txtPowUserPhone.setEditable(false);
		txtPowUserPhone.setText("");
		txtPowUserPhone.setColumns(10);
		
		txtPowUserCradNumber = new JTextField();
		txtPowUserCradNumber.setEditable(false);
		txtPowUserCradNumber.setColumns(10);
		
		JLabel lblPowUserPhone = new JLabel("用户电话：");
		
		JLabel lblPowUserCardNumber = new JLabel("身份证号：");
		
		JLabel lblPowUserAddress = new JLabel("用户地址：");
		
		txtPowUserAddress = new JTextField();
		txtPowUserAddress.setEditable(false);
		txtPowUserAddress.setText("");
		txtPowUserAddress.setColumns(10);
		
		JLabel lblPowPowerSelect = new JLabel("更改权限：");
		
		cboPowPowerSelect = new JComboBox();
		cboPowPowerSelect.addItem("请选择");
		cboPowPowerSelect.addItem("采购管理员");
		cboPowPowerSelect.addItem("销售管理员");
		cboPowPowerSelect.addItem("仓库管理员");
		cboPowPowerSelect.addItem("未激活");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblPowUserId, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(txtPowUserId, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblPowUserName, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPowUserName, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblPowUserPhone, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
							.addComponent(txtPowUserPhone, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblPowUserCardNumber, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtPowUserCradNumber, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblPowUserAddress, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtPowUserAddress, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPowPowerSelect)
								.addComponent(cboPowPowerSelect, 0, 81, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(lblPowUserId))
						.addComponent(txtPowUserId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPowUserName)
						.addComponent(txtPowUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPowUserPhone)
						.addComponent(txtPowUserPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPowUserCardNumber)
						.addComponent(txtPowUserCradNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPowPowerSelect))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPowUserAddress)
								.addComponent(txtPowUserAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(cboPowPowerSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(9, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
	
	//根据所选item 进行操作
	private void doUserSelectAction() {
		String getid="";
		User user = new User();
		if (cboPowUserSelect.getSelectedIndex()==0) {
			return;
		}
		try {
			getid =(cboPowUserSelect.getSelectedItem()).toString().substring(0, 10);
			user = DAOFactory.getIUserDAOInstance().findById(getid);
			txtPowUserId.setText(user.getUserId());
			txtPowUserName.setText(user.getUserName());
			txtPowUserPhone.setText(user.getUserPhoneNumber());
			txtPowUserCradNumber.setText(user.getUserCardNumber());
			txtPowUserAddress.setText(user.getUserAddress());
			if(user.getUserClassId().equals("usc02")){
				cboPowPowerSelect.setSelectedIndex(1);
			}else if(user.getUserClassId().equals("usc03")){
				cboPowPowerSelect.setSelectedIndex(2);
			}else if(user.getUserClassId().equals("usc04")){
				cboPowPowerSelect.setSelectedIndex(3);
			}else if(user.getUserClassId().equals("usc05")){
				cboPowPowerSelect.setSelectedIndex(4);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//重新排序 实时更新
	public void initComboUserBox(){
		cboPowUserSelect.removeAllItems();
		cboPowUserSelect.addItem("(请选择)");
		try {
			List<User> all = DAOFactory.getIUserDAOInstance().findAll("");
			for (int i=0; i<all.size();i++) {
				if(!all.get(i).getUserClassId().equals("usc01")){				
				cboPowUserSelect.addItem(all.get(i).getUserId()+
						"("+all.get(i).getUserName()+")");
				}
				}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
