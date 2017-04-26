package internalFrame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.henu.factory.DAOFactory;
import com.henu.tableRenderer.TableRenderer;

import tableModel.Supplier;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class SupplierSelect extends JInternalFrame {
	private JTextField txtSeltSupName;
	private JTable table;
	DefaultTableModel tableModel;
	TableRowSorter<DefaultTableModel> sorter;


	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}
	public SupplierSelect() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setTitle("供应商查询");
		setBounds(100, 100, 640, 360);
		
		JLabel lblSeltCusName = new JLabel("请输入需要查询的客户名称关键字：");
		
		txtSeltSupName = new JTextField();
		txtSeltSupName.setColumns(10);
		
		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		String[] tableHeads = new String[]{"供应商编号", "供应商名称", "供应商地址", "邮政编码",
				"E-Mail", "电话号码", "银行帐号", "开户银行", "个人主页", "备注信息"};
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setColumnIdentifiers(tableHeads);
		
		JButton btnSeltCusSelect = new JButton("查询");
		btnSeltCusSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = tableModel.getRowCount();
				for (int i = 0; i < num; i++)
					tableModel.removeRow(0);
				String selectName = txtSeltSupName.getText();
				try {
					if(selectName.trim().length() == 0) {
						JOptionPane.showMessageDialog(null, "请输入需要查询的名称!");
						return;
					}
					List<Supplier> all = DAOFactory.getISupplierDAOInstance().findAll(selectName);
					
					int size = all.size();
					if(size == 0) {
						JOptionPane.showMessageDialog(null, "没有找到名字包含  '" + selectName + "' 的供应商!");
						return;
					}
					
					JOptionPane.showMessageDialog(null, "找到 " + size + " 名供应商");
					
					
					updateTable(all,tableModel);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnSeltCusSelectAll = new JButton("显示全部供应商");
		btnSeltCusSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSeltSupName.setText("");
				try {
					int num = tableModel.getRowCount();
					for (int i = 0; i < num; i++)
						tableModel.removeRow(0);
					List<Supplier> all = DAOFactory.getISupplierDAOInstance().findAll("");
					int size = all.size();
					JOptionPane.showMessageDialog(null, "找到 " + size + " 名供应商");
					updateTable(all,tableModel);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		table.setRowSorter(sorter);
		TableRenderer renderer = new TableRenderer();
		table.setDefaultRenderer(Object.class, renderer);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setAutoscrolls(true);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSeltCusName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSeltSupName, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSeltCusSelect)
							.addPreferredGap(ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
							.addComponent(btnSeltCusSelectAll)
							.addGap(30))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSeltCusName)
						.addComponent(txtSeltSupName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSeltCusSelect)
						.addComponent(btnSeltCusSelectAll))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
	
	private void updateTable(List<Supplier> all, DefaultTableModel tableModel) {		
		
		for (int i=0; i<all.size();i++) {
			Supplier supplier = all.get(i);
			Vector<String> rowData = new Vector<String>();
			rowData.add(supplier.getSupplierId());
			rowData.add(supplier.getSupplierName());
			rowData.add(supplier.getSupplierAddress());
			rowData.add(supplier.getSupplierPostCode());
			rowData.add(supplier.getSupplierEmail());
			rowData.add(supplier.getSupplierPhoneNumber());
			rowData.add(supplier.getSupplierBankId());
			rowData.add(supplier.getSupplierBankAddress());
			rowData.add(supplier.getSupplierWebsite());
			rowData.add(supplier.getSupplierRemark());
			tableModel.addRow(rowData);
		}
		System.out.println("");
		
	}
	
}
