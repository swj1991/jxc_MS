package internalFrame;

//import internalFrame.shangPinGuanLi.ShangPinTianJiaPanel;





import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;


import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tableModel.Customer;
import tableModel.Check;
import com.henu.factory.DAOFactory;
import javax.swing.ScrollPaneConstants;



public class CustomerManage extends JInternalFrame {
	private JTextField txtCusNameAdd;
	private JTextField txtCusTelAdd;
	private JTextField txtCusAddressAdd;
	private JTextField txtCusEmailAdd;
	private JTextField txtCusPsotCodeAdd;
	private JTextField txtCusWebsiteAdd;
	private JTextField txtCusBankAddressAdd;
	private JTextField txtCusBankIdAdd;
	private JTextField txtCusNameModify;
	private JTextField txtCusTelModify;
	private JTextField txtCusAddressModify;
	private JTextField txtCusPostCodeModify;
	private JTextField txtCusWebsiteModify;
	private JTextField txtCusEmailModify;
	private JTextField txtCusBankAddressModify;
	private JTextField txtCusBankIdModify;
	private JComboBox cboCusSelectModify;
	private JTextArea txtaCusRemarkAdd;
	private JTextArea txtaCusRemarkModify;
	/**
	 * 
	 */
	public CustomerManage() {
		setIconifiable(true);
		setClosable(true);
		setTitle("顾客管理");
		setBounds(56, 24, 480, 340);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
		);
		
		JPanel addCustomerPanel = new JPanel();
		tabbedPane.addTab("添加顾客信息", null, addCustomerPanel, null);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "备注信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnCusAddAdd = new JButton("添加");
		btnCusAddAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCusNameAdd.getText().trim().length()==0
						|| txtCusTelAdd.getText().trim().length()==0
						|| txtCusAddressAdd.getText().trim().length()==0
						|| txtCusEmailAdd.getText().trim().length()==0
						|| txtCusPsotCodeAdd.getText().trim().length()==0
						|| txtCusWebsiteAdd.getText().trim().length()==0
						|| txtCusBankAddressAdd.getText().trim().length()==0
						|| txtCusBankIdAdd.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(CustomerManage.this,
							"请完成未填写的信息!", "用户添加", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//check
				if(!Check.checkPhoneNumber(txtCusTelAdd.getText())){
					JOptionPane.showMessageDialog(CustomerManage.this,
							"请输入正确的联系方式!固定电话按区号-号码输入!", "用户添加", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!Check.checkEmail(txtCusEmailAdd.getText())){
					JOptionPane.showMessageDialog(CustomerManage.this,
							"请输入正确的邮箱地址!", "用户添加", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!Check.checkPostcode(txtCusPsotCodeAdd.getText())){
					JOptionPane.showMessageDialog(CustomerManage.this,
							"请输入正确的邮政编码!", "用户添加", JOptionPane.ERROR_MESSAGE);
					return;
					
				//end check
				}
				
				Customer customer = new Customer();
				customer.setCustomerId("KH"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
				customer.setCustomerName(txtCusNameAdd.getText());
				customer.setCustomerPhoneNumber(txtCusTelAdd.getText());
				customer.setCustomerAddress(txtCusAddressAdd.getText());
				customer.setCustomerEmail(txtCusEmailAdd.getText());
				customer.setCustomerPostCode(txtCusPsotCodeAdd.getText());
				customer.setCustomerWebsite(txtCusWebsiteAdd.getText());
				customer.setCustomerBankAddress(txtCusBankAddressAdd.getText());
				customer.setCustomerBankId(txtCusBankIdAdd.getText());
				customer.setCustomerRemark(txtaCusRemarkAdd.getText());
				try {
					DAOFactory.getICustomerDAOInstance().doCreate(customer);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "添加成功!");
				txtCusNameAdd.setText(null);
				txtCusTelAdd.setText(null);
				txtCusAddressAdd.setText(null);
				txtCusEmailAdd.setText(null);
				txtCusPsotCodeAdd.setText(null);
				txtCusWebsiteAdd.setText(null);
				txtCusBankAddressAdd.setText(null);
				txtCusBankIdAdd.setText(null);
				txtaCusRemarkAdd.setText(null);
				txtCusNameAdd.requestFocusInWindow();
				//刷新数据
				initComboBox();
				
				
			}
		});
		
		JButton btnCusClearAdd = new JButton("清空");
		btnCusClearAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCusNameAdd.setText(null);
				txtCusTelAdd.setText(null);
				txtCusAddressAdd.setText(null);
				txtCusEmailAdd.setText(null);
				txtCusPsotCodeAdd.setText(null);
				txtCusWebsiteAdd.setText(null);
				txtCusBankAddressAdd.setText(null);
				txtCusBankIdAdd.setText(null);
				txtaCusRemarkAdd.setText(null);
				txtCusNameAdd.requestFocusInWindow();
			}
		});
		GroupLayout gl_addCustomerPanel = new GroupLayout(addCustomerPanel);
		gl_addCustomerPanel.setHorizontalGroup(
			gl_addCustomerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addCustomerPanel.createSequentialGroup()
					.addGap(98)
					.addComponent(btnCusAddAdd)
					.addPreferredGap(ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
					.addComponent(btnCusClearAdd)
					.addGap(93))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
		);
		gl_addCustomerPanel.setVerticalGroup(
			gl_addCustomerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addCustomerPanel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_addCustomerPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCusAddAdd)
						.addComponent(btnCusClearAdd))
					.addContainerGap())
		);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
		);
		
		txtaCusRemarkAdd = new JTextArea();
		txtaCusRemarkAdd.setLineWrap(true);
		txtaCusRemarkAdd.setColumns(33);
		scrollPane.setViewportView(txtaCusRemarkAdd);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblCusNameAdd = new JLabel("顾客姓名：");
		JLabel lblCusAddressAdd = new JLabel("发货地址：");
		JLabel lblCusPostCodeAdd = new JLabel("邮政编码：");
		JLabel lblCusEmailAdd = new JLabel("E-Mail：");
		JLabel lblCusTelAdd = new JLabel("电话：");
		
		txtCusNameAdd = new JTextField();
		txtCusNameAdd.setColumns(10);
		
		txtCusTelAdd = new JTextField();
		txtCusTelAdd.setColumns(10);
		
		txtCusAddressAdd = new JTextField();
		txtCusAddressAdd.setText("");
		txtCusAddressAdd.setColumns(10);
		
		txtCusEmailAdd = new JTextField();
		txtCusEmailAdd.setColumns(10);
		
		txtCusPsotCodeAdd = new JTextField();
		txtCusPsotCodeAdd.setText("");
		txtCusPsotCodeAdd.setColumns(10);
		
		JLabel lblCusWebsiteAdd = new JLabel("个人主页：");
		
		txtCusWebsiteAdd = new JTextField();
		txtCusWebsiteAdd.setText("");
		txtCusWebsiteAdd.setColumns(10);
		
		JLabel lblCusBankAddressAdd = new JLabel("开户银行：");
		
		txtCusBankAddressAdd = new JTextField();
		txtCusBankAddressAdd.setColumns(10);
		
		JLabel lblCusBankIdAdd = new JLabel("银行帐号：");
		
		txtCusBankIdAdd = new JTextField();
		txtCusBankIdAdd.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCusNameAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCusNameAdd, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCusTelAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCusTelAdd, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCusAddressAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCusAddressAdd, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCusPostCodeAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCusPsotCodeAdd, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCusWebsiteAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCusWebsiteAdd, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCusEmailAdd)
							.addGap(18)
							.addComponent(txtCusEmailAdd, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCusBankAddressAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCusBankAddressAdd, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCusBankIdAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCusBankIdAdd, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCusNameAdd)
						.addComponent(lblCusTelAdd)
						.addComponent(txtCusNameAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCusTelAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCusAddressAdd)
						.addComponent(txtCusAddressAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCusPostCodeAdd)
						.addComponent(txtCusPsotCodeAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCusWebsiteAdd)
						.addComponent(txtCusWebsiteAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCusEmailAdd)
						.addComponent(txtCusEmailAdd, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCusBankAddressAdd)
						.addComponent(txtCusBankAddressAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCusBankIdAdd)
						.addComponent(txtCusBankIdAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		addCustomerPanel.setLayout(gl_addCustomerPanel);
		
		JPanel modifyCustomerPanel = new JPanel();
		tabbedPane.addTab("更改/删除信息", null, modifyCustomerPanel, null);
		
	
		
		//动态获取
		
		JButton btnCusModifyModify = new JButton("更改");
		btnCusModifyModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboCusSelectModify.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择需要更改的顾客!");
					return;
				}
				Customer customer = new Customer();
				customer.setCustomerId(
						cboCusSelectModify.getSelectedItem().toString().substring(0, 16));
				customer.setCustomerName(txtCusNameModify.getText());
				customer.setCustomerPhoneNumber(txtCusTelModify.getText());
				customer.setCustomerAddress(txtCusAddressModify.getText());
				customer.setCustomerPostCode(txtCusPostCodeModify.getText());
				customer.setCustomerWebsite(txtCusWebsiteModify.getText());
				customer.setCustomerEmail(txtCusEmailModify.getText());
				customer.setCustomerBankAddress(txtCusBankAddressModify.getText());
				customer.setCustomerBankId(txtCusBankIdModify.getText());
				customer.setCustomerRemark(txtaCusRemarkModify.getText());
				try {
					DAOFactory.getICustomerDAOInstance().doUpdate(customer);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "更新成功！");
				return;
			}
		});
		
		JButton btnCusDeleteModify = new JButton("删除");
		btnCusDeleteModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboCusSelectModify.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择需要删除的顾客!");
					return;
				}
				int select = JOptionPane.showConfirmDialog(null, "你确定要删除么?", "提示", 
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				boolean flag = false;
				if(select == JOptionPane.YES_OPTION) {
					try {
						flag = DAOFactory.getICustomerDAOInstance().doDelete(
							cboCusSelectModify.getSelectedItem().toString().substring(0, 16));
						}catch(Exception ex) {
					ex.printStackTrace();
					}
				}else{
					return;
				}
				if(flag){
					JOptionPane.showMessageDialog(null, "删除成功!");
					initComboBox();
					cboCusSelectModify.setSelectedIndex(0);
					doCustomerSelectAction();
					return;
				}else{
					JOptionPane.showMessageDialog(null, "删除失败!");
					return;
				}

				
			}
		});
		
		JPanel panel_3 = new JPanel();
		
		JLabel lblCusNameModify = new JLabel("顾客姓名：");
		
		txtCusNameModify = new JTextField();
		txtCusNameModify.setEditable(false);
		txtCusNameModify.setColumns(10);
		
		JLabel lblCusTelModify = new JLabel("电话：");
		
		txtCusTelModify = new JTextField();
		txtCusTelModify.setColumns(10);
		
		JLabel lblCusAddressModify = new JLabel("发货地址：");
		
		txtCusAddressModify = new JTextField();
		txtCusAddressModify.setText("");
		txtCusAddressModify.setColumns(10);
		
		JLabel lblCusPostCodeModify = new JLabel("邮政编码：");
		
		txtCusPostCodeModify = new JTextField();
		txtCusPostCodeModify.setText("");
		txtCusPostCodeModify.setColumns(10);
		
		JLabel lblCusWebsiteModify = new JLabel("个人主页：");
		
		txtCusWebsiteModify = new JTextField();
		txtCusWebsiteModify.setText("");
		txtCusWebsiteModify.setColumns(10);
		
		JLabel lblCusEmailModify = new JLabel("E-Mail：");
		
		txtCusEmailModify = new JTextField();
		txtCusEmailModify.setColumns(10);
		
		JLabel lblCusBankAddressModify = new JLabel("开户银行：");
		
		txtCusBankAddressModify = new JTextField();
		txtCusBankAddressModify.setColumns(10);
		
		JLabel lblCusBankIdModify = new JLabel("银行帐号：");
		
		txtCusBankIdModify = new JTextField();
		txtCusBankIdModify.setColumns(10);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 459, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblCusNameModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCusNameModify, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCusTelModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCusTelModify, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblCusAddressModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCusAddressModify, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblCusPostCodeModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCusPostCodeModify, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCusWebsiteModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCusWebsiteModify, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblCusEmailModify)
							.addGap(18)
							.addComponent(txtCusEmailModify, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblCusBankAddressModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCusBankAddressModify, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCusBankIdModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCusBankIdModify, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 152, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCusNameModify)
						.addComponent(lblCusTelModify)
						.addComponent(txtCusNameModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCusTelModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCusAddressModify)
						.addComponent(txtCusAddressModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCusPostCodeModify)
						.addComponent(txtCusPostCodeModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCusWebsiteModify)
						.addComponent(txtCusWebsiteModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCusEmailModify)
						.addComponent(txtCusEmailModify, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCusBankAddressModify)
						.addComponent(txtCusBankAddressModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCusBankIdModify)
						.addComponent(txtCusBankIdModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "备注信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(2)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
		);
		
		txtaCusRemarkModify = new JTextArea();
		txtaCusRemarkModify.setLineWrap(true);
		txtaCusRemarkModify.setColumns(33);
		scrollPane_1.setViewportView(txtaCusRemarkModify);
		panel_4.setLayout(gl_panel_4);
		
		JLabel lblCusSelectModify = new JLabel("选择客户：");
		
		cboCusSelectModify = new JComboBox();
		initComboBox();
		cboCusSelectModify.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){ 
					if(cboCusSelectModify.getSelectedIndex()==0){
					txtCusNameModify.setText(null);
					txtCusTelModify.setText(null);
					txtCusAddressModify.setText(null);
					txtCusPostCodeModify.setText(null);
					txtCusWebsiteModify.setText(null);
					txtCusEmailModify.setText(null);
					txtCusBankAddressModify.setText(null);
					txtCusBankIdModify.setText(null);
					txtaCusRemarkModify.setText(null);
					return;
				}
				doCustomerSelectAction();
				}else{
					return;
				}
        }

		});

		cboCusSelectModify.setToolTipText("");
		GroupLayout gl_modifyCustomerPanel = new GroupLayout(modifyCustomerPanel);
		gl_modifyCustomerPanel.setHorizontalGroup(
			gl_modifyCustomerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_modifyCustomerPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblCusSelectModify)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cboCusSelectModify, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addComponent(btnCusModifyModify)
					.addGap(18)
					.addComponent(btnCusDeleteModify)
					.addGap(29))
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
				.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
		);
		gl_modifyCustomerPanel.setVerticalGroup(
			gl_modifyCustomerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modifyCustomerPanel.createSequentialGroup()
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_modifyCustomerPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCusDeleteModify)
						.addComponent(btnCusModifyModify)
						.addComponent(lblCusSelectModify)
						.addComponent(cboCusSelectModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		modifyCustomerPanel.setLayout(gl_modifyCustomerPanel);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
	}
	
	//根据所选item 进行操作
	private void doCustomerSelectAction() {
		String getid="";
		Customer customer = new Customer();
		if (cboCusSelectModify.getSelectedIndex()==0) {
			return;
		}
		try {
			getid =(cboCusSelectModify.getSelectedItem()).toString().substring(0, 16);
			customer = DAOFactory.getICustomerDAOInstance().findById(getid);
			txtCusNameModify.setText(customer.getCustomerName());
			txtCusTelModify.setText(customer.getCustomerPhoneNumber());
			txtCusAddressModify.setText(customer.getCustomerAddress());
			txtCusPostCodeModify.setText(customer.getCustomerPostCode());
			txtCusWebsiteModify.setText(customer.getCustomerWebsite());
			txtCusEmailModify.setText(customer.getCustomerEmail());
			txtCusBankAddressModify.setText(customer.getCustomerBankAddress());
			txtCusBankIdModify.setText(customer.getCustomerBankId());
			txtaCusRemarkModify.setText(customer.getCustomerRemark());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//重新排序 实时更新
	public void initComboBox(){
		cboCusSelectModify.removeAllItems();
		cboCusSelectModify.addItem("(请选择)");
		try {
			List<Customer> all = DAOFactory.getICustomerDAOInstance().findAll("");
			for (int i=0; i<all.size();i++) {
				cboCusSelectModify.addItem(all.get(i).getCustomerId()+
						"("+all.get(i).getCustomerName()+")");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
}
