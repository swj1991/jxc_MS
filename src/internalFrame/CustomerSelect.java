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

import tableModel.Customer;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class CustomerSelect extends JInternalFrame {
	private JTextField txtSeltCusName;
	private JTable table;
	DefaultTableModel tableModel;
	TableRowSorter<DefaultTableModel> sorter;


	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}
	public CustomerSelect() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setTitle("�˿Ͳ�ѯ");
		setBounds(100, 100, 640, 360);
		
		JLabel lblSeltCusName = new JLabel("��������Ҫ��ѯ�Ĺ˿������ؼ��֣�");
		
		txtSeltCusName = new JTextField();
		txtSeltCusName.setColumns(10);
		
		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		String[] tableHeads = new String[]{"�˿ͱ��", "�˿�����", "�˿͵�ַ", "��������",
				"E-Mail", "�绰����", "�����ʺ�", "��������", "������ҳ", "��ע��Ϣ"};
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setColumnIdentifiers(tableHeads);
		
		JButton btnSeltCusSelect = new JButton("��ѯ");
		btnSeltCusSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = tableModel.getRowCount();
				for (int i = 0; i < num; i++)
					tableModel.removeRow(0);
				String selectName = txtSeltCusName.getText();
				try {
					if(selectName.trim().length() == 0) {
						JOptionPane.showMessageDialog(null, "��������Ҫ��ѯ������!");
						return;
					}
					List<Customer> all = DAOFactory.getICustomerDAOInstance().findAll(selectName);
					
					int size = all.size();
					if(size == 0) {
						JOptionPane.showMessageDialog(null, "û���ҵ����ְ���  '" + selectName + "' �Ĺ˿�!");
						return;
					}
					
					JOptionPane.showMessageDialog(null, "�ҵ� " + size + " ���˿�");
					
					
					updateTable(all,tableModel);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnSeltCusSelectAll = new JButton("��ʾȫ���˿�");
		btnSeltCusSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSeltCusName.setText("");
				try {
					int num = tableModel.getRowCount();
					for (int i = 0; i < num; i++)
						tableModel.removeRow(0);
					List<Customer> all = DAOFactory.getICustomerDAOInstance().findAll("");
					int size = all.size();
					JOptionPane.showMessageDialog(null, "�ҵ� " + size + " ���˿�");
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
							.addComponent(txtSeltCusName, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(txtSeltCusName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSeltCusSelect)
						.addComponent(btnSeltCusSelectAll))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
	
	private void updateTable(List<Customer> all, DefaultTableModel tableModel) {		
		
		for (int i=0; i<all.size();i++) {
			Customer customer = all.get(i);
			Vector<String> rowData = new Vector<String>();
			rowData.add(customer.getCustomerId());
			rowData.add(customer.getCustomerName());
			rowData.add(customer.getCustomerAddress());
			rowData.add(customer.getCustomerPostCode());
			rowData.add(customer.getCustomerEmail());
			rowData.add(customer.getCustomerPhoneNumber());
			rowData.add(customer.getCustomerBankId());
			rowData.add(customer.getCustomerBankAddress());
			rowData.add(customer.getCustomerWebsite());
			rowData.add(customer.getCustomerRemark());
			tableModel.addRow(rowData);
		}
		
	}
	
}
