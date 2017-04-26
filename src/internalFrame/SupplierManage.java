package internalFrame;



import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
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



import tableModel.Check;
import tableModel.Supplier;

import com.henu.factory.DAOFactory;
import javax.swing.JScrollPane;

public class SupplierManage extends JInternalFrame {
	private JTextField txtSupNameAdd;
	private JTextField txtSupTelAdd;
	private JTextField txtSupAddressAdd;
	private JTextField txtSupEmailAdd;
	private JTextField txtSupPsotCodeAdd;
	private JTextField txtSupWebsiteAdd;
	private JTextField txtSupBankAddressAdd;
	private JTextField txtSupBankIdAdd;
	private JTextField txtSupNameModify;
	private JTextField txtSupTelModify;
	private JTextField txtSupAddressModify;
	private JTextField txtSupPostCodeModify;
	private JTextField txtSupWebsiteModify;
	private JTextField txtSupEmailModify;
	private JTextField txtSupBankAddressModify;
	private JTextField txtSupBankIdModify;
	private JComboBox cboSupSelectModify ;
	private JTextArea txtaSupRemarkModify;
	private JTextArea txtaSupRemarkAdd;

	public SupplierManage() {
		setIconifiable(true);
		setClosable(true);
		setTitle("供应商管理");
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
		
		JPanel addSupplierPanel = new JPanel();
		tabbedPane.addTab("添加供应商信息", null, addSupplierPanel, null);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "备注信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnSupAddAdd = new JButton("添加");
		btnSupAddAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSupNameAdd.getText().trim().length()==0
						|| txtSupTelAdd.getText().trim().length()==0
						|| txtSupAddressAdd.getText().trim().length()==0
						|| txtSupEmailAdd.getText().trim().length()==0
						|| txtSupPsotCodeAdd.getText().trim().length()==0
						|| txtSupWebsiteAdd.getText().trim().length()==0
						|| txtSupBankAddressAdd.getText().trim().length()==0
						|| txtSupBankIdAdd.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(SupplierManage.this,
							"请完成未填写的信息。", "用户添加", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//check
				if(!Check.checkPhoneNumber(txtSupTelAdd.getText())){
					JOptionPane.showMessageDialog(SupplierManage.this,
							"请输入正确的联系方式!固定电话按区号-号码输入!", "用户添加", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!Check.checkEmail(txtSupEmailAdd.getText())){
					JOptionPane.showMessageDialog(SupplierManage.this,
							"请输入正确的邮箱地址!", "用户添加", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!Check.checkPostcode(txtSupPsotCodeAdd.getText())){
					JOptionPane.showMessageDialog(SupplierManage.this,
							"请输入正确的邮政编码!", "用户添加", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Supplier supplier = new Supplier();
				supplier.setSupplierId("GY"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
				supplier.setSupplierName(txtSupNameAdd.getText());
				supplier.setSupplierPhoneNumber(txtSupTelAdd.getText());
				supplier.setSupplierAddress(txtSupAddressAdd.getText());
				supplier.setSupplierEmail(txtSupEmailAdd.getText());
				supplier.setSupplierPostCode(txtSupPsotCodeAdd.getText());
				supplier.setSupplierWebsite(txtSupWebsiteAdd.getText());
				supplier.setSupplierBankAddress(txtSupBankAddressAdd.getText());
				supplier.setSupplierBankId(txtSupBankIdAdd.getText());
				supplier.setSupplierRemark(txtaSupRemarkAdd.getText());
				try {
					DAOFactory.getISupplierDAOInstance().doCreate(supplier);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "添加成功！");
				txtSupNameAdd.setText(null);
				txtSupTelAdd.setText(null);
				txtSupAddressAdd.setText(null);
				txtSupEmailAdd.setText(null);
				txtSupPsotCodeAdd.setText(null);
				txtSupWebsiteAdd.setText(null);
				txtSupBankAddressAdd.setText(null);
				txtSupBankIdAdd.setText(null);
				txtaSupRemarkAdd.setText(null);
				txtSupNameAdd.requestFocusInWindow();
				//刷新数据
				initComboBox();
				
			}
		});
		
		JButton btnSupClearAdd = new JButton("清空");
		btnSupClearAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSupNameAdd.setText(null);
				txtSupTelAdd.setText(null);
				txtSupAddressAdd.setText(null);
				txtSupEmailAdd.setText(null);
				txtSupPsotCodeAdd.setText(null);
				txtSupWebsiteAdd.setText(null);
				txtSupBankAddressAdd.setText(null);
				txtSupBankIdAdd.setText(null);
				txtaSupRemarkAdd.setText(null);
				txtSupNameAdd.requestFocusInWindow();
			}
		});
		GroupLayout gl_addSupplierPanel = new GroupLayout(addSupplierPanel);
		gl_addSupplierPanel.setHorizontalGroup(
			gl_addSupplierPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addSupplierPanel.createSequentialGroup()
					.addGap(98)
					.addComponent(btnSupAddAdd)
					.addPreferredGap(ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
					.addComponent(btnSupClearAdd)
					.addGap(93))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
		);
		gl_addSupplierPanel.setVerticalGroup(
			gl_addSupplierPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addSupplierPanel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_addSupplierPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSupAddAdd)
						.addComponent(btnSupClearAdd))
					.addContainerGap())
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
		);
		
		txtaSupRemarkAdd = new JTextArea();
		txtaSupRemarkAdd.setLineWrap(true);
		txtaSupRemarkAdd.setColumns(33);
		scrollPane.setViewportView(txtaSupRemarkAdd);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblSupNameAdd = new JLabel("供应商姓名：");
		JLabel lblSupAddressAdd = new JLabel("发货地址：");
		JLabel lblSupPostCodeAdd = new JLabel("邮政编码：");
		JLabel lblSupEmailAdd = new JLabel("E-Mail：");
		JLabel lblSupTelAdd = new JLabel("电话：");
		
		txtSupNameAdd = new JTextField();
		txtSupNameAdd.setColumns(10);
		
		txtSupTelAdd = new JTextField();
		txtSupTelAdd.setColumns(10);
		
		txtSupAddressAdd = new JTextField();
		txtSupAddressAdd.setText("");
		txtSupAddressAdd.setColumns(10);
		
		txtSupEmailAdd = new JTextField();
		txtSupEmailAdd.setColumns(10);
		
		txtSupPsotCodeAdd = new JTextField();
		txtSupPsotCodeAdd.setText("");
		txtSupPsotCodeAdd.setColumns(10);
		
		JLabel lblSupWebsiteAdd = new JLabel("个人主页：");
		
		txtSupWebsiteAdd = new JTextField();
		txtSupWebsiteAdd.setText("");
		txtSupWebsiteAdd.setColumns(10);
		
		JLabel lblSupBankAddressAdd = new JLabel("开户银行：");
		
		txtSupBankAddressAdd = new JTextField();
		txtSupBankAddressAdd.setColumns(10);
		
		JLabel lblSupBankIdAdd = new JLabel("银行帐号：");
		
		txtSupBankIdAdd = new JTextField();
		txtSupBankIdAdd.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblSupNameAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSupNameAdd, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblSupTelAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSupTelAdd, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblSupAddressAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSupAddressAdd, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblSupPostCodeAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSupPsotCodeAdd, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblSupWebsiteAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSupWebsiteAdd, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblSupEmailAdd)
							.addGap(18)
							.addComponent(txtSupEmailAdd, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblSupBankAddressAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSupBankAddressAdd, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblSupBankIdAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSupBankIdAdd, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupNameAdd)
						.addComponent(lblSupTelAdd)
						.addComponent(txtSupNameAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSupTelAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupAddressAdd)
						.addComponent(txtSupAddressAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupPostCodeAdd)
						.addComponent(txtSupPsotCodeAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSupWebsiteAdd)
						.addComponent(txtSupWebsiteAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupEmailAdd)
						.addComponent(txtSupEmailAdd, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupBankAddressAdd)
						.addComponent(txtSupBankAddressAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSupBankIdAdd)
						.addComponent(txtSupBankIdAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		addSupplierPanel.setLayout(gl_addSupplierPanel);
		
		JPanel modifySupplierPanel = new JPanel();
		tabbedPane.addTab("更改/删除信息", null, modifySupplierPanel, null);
		
		JButton btnSupModifyModify = new JButton("更改");
		btnSupModifyModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboSupSelectModify.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择需要删除的供应商!");
					return;
				}
				Supplier supplier = new Supplier();
				supplier.setSupplierId(
						cboSupSelectModify.getSelectedItem().toString().substring(0, 16));
				supplier.setSupplierName(txtSupNameModify.getText());
				supplier.setSupplierPhoneNumber(txtSupTelModify.getText());
				supplier.setSupplierAddress(txtSupAddressModify.getText());
				supplier.setSupplierPostCode(txtSupPostCodeModify.getText());
				supplier.setSupplierWebsite(txtSupWebsiteModify.getText());
				supplier.setSupplierEmail(txtSupEmailModify.getText());
				supplier.setSupplierBankAddress(txtSupBankAddressModify.getText());
				supplier.setSupplierBankId(txtSupBankIdModify.getText());
				supplier.setSupplierRemark(txtaSupRemarkModify.getText());
				try {
					DAOFactory.getISupplierDAOInstance().doUpdate(supplier);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "更新成功!");
				
				return;
			}
		});
		
		JButton btnSupDeleteModify = new JButton("删除");
		btnSupDeleteModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboSupSelectModify.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择需要删除的供应商!");
					return;
				}
				int select = JOptionPane.showConfirmDialog(null, "你确定要删除么？", "提示", 
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				boolean flag = false;
				if(select == JOptionPane.YES_OPTION) {
					try {
						flag = DAOFactory.getISupplierDAOInstance().doDelete(
							cboSupSelectModify.getSelectedItem().toString().substring(0, 16));
						}catch(Exception ex) {
					ex.printStackTrace();
					}
				}else{
					return;
				}
				if(flag){
					JOptionPane.showMessageDialog(null, "删除成功！");
					initComboBox();
					cboSupSelectModify.setSelectedIndex(0);
					doSupplierSelectAction();
					
					return;
				}else{
					JOptionPane.showMessageDialog(null, "删除失败！");
					return;
				}
			}
		});
		
		JPanel panel_3 = new JPanel();
		
		JLabel lblSupNameModify = new JLabel("供应商姓名：");
		
		txtSupNameModify = new JTextField();
		txtSupNameModify.setEditable(false);
		txtSupNameModify.setColumns(10);
		
		JLabel lblSupTelModify = new JLabel("电话：");
		
		txtSupTelModify = new JTextField();
		txtSupTelModify.setColumns(10);
		
		JLabel lblSupAddressModify = new JLabel("发货地址：");
		
		txtSupAddressModify = new JTextField();
		txtSupAddressModify.setText("");
		txtSupAddressModify.setColumns(10);
		
		JLabel lblSupPostCodeModify = new JLabel("邮政编码：");
		
		txtSupPostCodeModify = new JTextField();
		txtSupPostCodeModify.setText("");
		txtSupPostCodeModify.setColumns(10);
		
		JLabel lblSupWebsiteModify = new JLabel("个人主页：");
		
		txtSupWebsiteModify = new JTextField();
		txtSupWebsiteModify.setText("");
		txtSupWebsiteModify.setColumns(10);
		
		JLabel lblSupEmailModify = new JLabel("E-Mail：");
		
		txtSupEmailModify = new JTextField();
		txtSupEmailModify.setColumns(10);
		
		JLabel lblSupBankAddressModify = new JLabel("开户银行：");
		
		txtSupBankAddressModify = new JTextField();
		txtSupBankAddressModify.setColumns(10);
		
		JLabel lblSupBankIdModify = new JLabel("银行帐号：");
		
		txtSupBankIdModify = new JTextField();
		txtSupBankIdModify.setColumns(10);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 459, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblSupNameModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSupNameModify, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblSupTelModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSupTelModify, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblSupAddressModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSupAddressModify, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblSupPostCodeModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSupPostCodeModify, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblSupWebsiteModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSupWebsiteModify, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblSupEmailModify)
							.addGap(18)
							.addComponent(txtSupEmailModify, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblSupBankAddressModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSupBankAddressModify, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblSupBankIdModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSupBankIdModify, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 152, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupNameModify)
						.addComponent(lblSupTelModify)
						.addComponent(txtSupNameModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSupTelModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupAddressModify)
						.addComponent(txtSupAddressModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupPostCodeModify)
						.addComponent(txtSupPostCodeModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSupWebsiteModify)
						.addComponent(txtSupWebsiteModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupEmailModify)
						.addComponent(txtSupEmailModify, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupBankAddressModify)
						.addComponent(txtSupBankAddressModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSupBankIdModify)
						.addComponent(txtSupBankIdModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "\u5907\u6CE8\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
		);
		
		txtaSupRemarkModify = new JTextArea();
		txtaSupRemarkModify.setLineWrap(true);
		txtaSupRemarkModify.setColumns(33);
		scrollPane_1.setViewportView(txtaSupRemarkModify);
		panel_4.setLayout(gl_panel_4);
		
		JLabel lblSupSelectModify = new JLabel("选择供应商：");
		
		cboSupSelectModify = new JComboBox();
		initComboBox();
		cboSupSelectModify.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){ 
					if(cboSupSelectModify.getSelectedIndex()==0){
					txtSupNameModify.setText(null);
					txtSupTelModify.setText(null);
					txtSupAddressModify.setText(null);
					txtSupPostCodeModify.setText(null);
					txtSupWebsiteModify.setText(null);
					txtSupEmailModify.setText(null);
					txtSupBankAddressModify.setText(null);
					txtSupBankIdModify.setText(null);
					txtaSupRemarkModify.setText(null);
					return;
				}
				doSupplierSelectAction();
				}else{
					return;
				}
				}
			});
		GroupLayout gl_modifySupplierPanel = new GroupLayout(modifySupplierPanel);
		gl_modifySupplierPanel.setHorizontalGroup(
			gl_modifySupplierPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_modifySupplierPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblSupSelectModify)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cboSupSelectModify, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addComponent(btnSupModifyModify)
					.addGap(18)
					.addComponent(btnSupDeleteModify)
					.addGap(29))
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
				.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
		);
		gl_modifySupplierPanel.setVerticalGroup(
			gl_modifySupplierPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modifySupplierPanel.createSequentialGroup()
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_modifySupplierPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSupDeleteModify)
						.addComponent(btnSupModifyModify)
						.addComponent(lblSupSelectModify)
						.addComponent(cboSupSelectModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		modifySupplierPanel.setLayout(gl_modifySupplierPanel);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
	}
	
	//根据所选item
	private void doSupplierSelectAction() {
		String getid="";
		Supplier supplier = new Supplier();
		if (cboSupSelectModify.getSelectedIndex()==0) {
			return;
		}
		try {
			getid =(cboSupSelectModify.getSelectedItem()).toString().substring(0, 16);
			supplier = DAOFactory.getISupplierDAOInstance().findById(getid);
			txtSupNameModify.setText(supplier.getSupplierName());
			txtSupTelModify.setText(supplier.getSupplierPhoneNumber());
			txtSupAddressModify.setText(supplier.getSupplierAddress());
			txtSupPostCodeModify.setText(supplier.getSupplierPostCode());
			txtSupWebsiteModify.setText(supplier.getSupplierWebsite());
			txtSupEmailModify.setText(supplier.getSupplierEmail());
			txtSupBankAddressModify.setText(supplier.getSupplierBankAddress());
			txtSupBankIdModify.setText(supplier.getSupplierBankId());
			txtaSupRemarkModify.setText(supplier.getSupplierRemark());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//重新排序 实时更新
	public void initComboBox(){
		cboSupSelectModify.removeAllItems();
		cboSupSelectModify.addItem("(请选择)");
		try {
			List<Supplier> all = DAOFactory.getISupplierDAOInstance().findAll("");
			for (int i=0; i<all.size();i++) {
				cboSupSelectModify.addItem(all.get(i).getSupplierId()+
						"("+all.get(i).getSupplierName()+")");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
}
