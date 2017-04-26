package internalFrame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;

import com.henu.factory.DAOFactory;
import com.henu.tableRenderer.TableRenderer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

import tableModel.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JPasswordField;

public class UserManage extends JInternalFrame {

	private JTable table;
	DefaultTableModel tableModel;
	TableRowSorter<DefaultTableModel> sorter;
	private JTextField txtUserManageId;
	private JTextField txtUserManageName;
	private JTextField txtUserManagePower;
	private JComboBox cboUserManangeSelect = new JComboBox();
	private JPasswordField txtPsdUserManagePassword;

	public UserManage() {
		setClosable(true);
		setIconifiable(true);
		setVisible(true);
		setTitle("用户管理");
		setBounds(100, 100, 470, 430);

		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		String[] tableHeads = new String[] { "用户帐号", "用户姓名", "用户密码位数", "用户权限" };
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setColumnIdentifiers(tableHeads);

		sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		table.setRowSorter(sorter);
		TableRenderer renderer = new TableRenderer();
		table.setDefaultRenderer(Object.class, renderer);
		
		updateTable(tableModel);
		initComboBox();

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setAutoscrolls(true);

		JLabel lblUserManageSelect = new JLabel("选择需要管理的用户：");

		cboUserManangeSelect.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (cboUserManangeSelect.getSelectedIndex() == 0) {
						txtUserManageId.setText(null);
						txtUserManageName.setText(null);
						txtPsdUserManagePassword.setText(null);
						txtUserManagePower.setText(null);
						return;
					}
					doUserSelectAction();
				} else {
					return;
				}
			}

		});

		JButton btnUserManageDelete = new JButton("删除");
		btnUserManageDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboUserManangeSelect.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择需要删除的用户!");
					return;
				}
				
				int select = JOptionPane.showConfirmDialog(null, "你确定要删除么？", "提示", 
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				boolean flag = false;
				if(select == JOptionPane.YES_OPTION) {
					try {
						flag = DAOFactory.getIUserDAOInstance().doDelete(
								cboUserManangeSelect.getSelectedItem().toString().substring(0, 10));
						}catch(Exception ex) {
					ex.printStackTrace();
					}
				}else{
					return;
				}
				if(flag){
					JOptionPane.showMessageDialog(null, "删除成功!");
					initComboBox();
					cboUserManangeSelect.setSelectedIndex(0);
					updateTable(tableModel);
					doUserSelectAction();
					return;
				}else{
					JOptionPane.showMessageDialog(null, "删除失败！");
					return;
				}
				
			}
		});

		JLabel lblUserManageId = new JLabel("用户帐号：");

		txtUserManageId = new JTextField();
		txtUserManageId.setEditable(false);
		txtUserManageId.setColumns(10);

		JLabel lblUserManageName = new JLabel("用户姓名：");

		txtUserManageName = new JTextField();
		txtUserManageName.setEditable(false);
		txtUserManageName.setColumns(10);

		JLabel lblUserManagePower = new JLabel("用户权限：");

		txtUserManagePower = new JTextField();
		txtUserManagePower.setEditable(false);
		txtUserManagePower.setColumns(10);

		JLabel lblUserManagePassword = new JLabel("用户密码：");

		txtPsdUserManagePassword = new JPasswordField();
		txtPsdUserManagePassword.setEditable(false);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(cboUserManangeSelect, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblUserManageSelect, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(37)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblUserManageId)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtUserManageId, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblUserManageName)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtUserManageName))))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblUserManagePower)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtUserManagePower, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblUserManagePassword)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtPsdUserManagePassword)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnUserManageDelete)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUserManageSelect)
								.addComponent(lblUserManageId)
								.addComponent(txtUserManageId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(cboUserManangeSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblUserManagePower)
										.addComponent(txtUserManagePower, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblUserManageName)
										.addComponent(txtUserManageName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblUserManagePassword)
										.addComponent(txtPsdUserManagePassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnUserManageDelete)
							.addGap(26))))
		);
		getContentPane().setLayout(groupLayout);

	}

	// 根据所选item 进行操作
	private void doUserSelectAction() {
		String getid="";
		User user = new User();
		if (cboUserManangeSelect.getSelectedIndex()==0) {
			return;
		}
		try {
			getid =(cboUserManangeSelect.getSelectedItem()).toString().substring(0, 10);
			user = DAOFactory.getIUserDAOInstance().findById(getid);
			txtUserManageId.setText(user.getUserId());
			txtUserManageName.setText(user.getUserName());
			txtPsdUserManagePassword.setText(user.getUserPassword());
			if(user.getUserClassId().equals("usc01")){
				txtUserManagePower.setText("超级管理员");
			}else if(user.getUserClassId().equals("usc02")) {
				txtUserManagePower.setText("销售管理员");
			}else if(user.getUserClassId().equals("usc03")) {
				txtUserManagePower.setText("采购管理员");
			}else if(user.getUserClassId().equals("usc04")) {
				txtUserManagePower.setText("仓库管理员");
			}else if(user.getUserClassId().equals("usc05")) {
				txtUserManagePower.setText("未激活");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 下拉菜单刷新用户列表
	public void initComboBox() {
		cboUserManangeSelect.removeAllItems();
		cboUserManangeSelect.addItem("(请选择)");
		try {
			List<User> all = DAOFactory.getIUserDAOInstance().findAll();
			for (int i = 0; i < all.size(); i++) {
				if(!all.get(i).getUserClassId().equals("usc01")){				
					cboUserManangeSelect.addItem(all.get(i).getUserId() + "("
							+ all.get(i).getUserName() + ")");
					}				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//更新表格
	private void updateTable(DefaultTableModel tableModel) {		
		int num = tableModel.getRowCount();
		for (int i = 0; i < num; i++)
			tableModel.removeRow(0);
		try {
			List<User> all = DAOFactory.getIUserDAOInstance().findAll("");
			for (int i=0; i<all.size();i++) {			
				User user = all.get(i);
				Vector<String> rowData = new Vector<String>();
				rowData.add(user.getUserId());
				rowData.add(user.getUserName());
				rowData.add(String.valueOf((user.getUserPassword().length())));
				if(user.getUserClassId().equals("usc01")){
					rowData.add("超级管理员");
				}else if(user.getUserClassId().equals("usc02")) {
					rowData.add("销售管理员");
				}else if(user.getUserClassId().equals("usc03")) {
					rowData.add("采购管理员");
				}else if(user.getUserClassId().equals("usc04")) {
					rowData.add("仓库管理员");
				}else if(user.getUserClassId().equals("usc05")) {
					rowData.add("未激活");
				}
				tableModel.addRow(rowData);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
