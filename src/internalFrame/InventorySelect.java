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
import tableModel.Inventory;
import tableModel.Product;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class InventorySelect extends JInternalFrame {
	private JTextField txtSeltInvName;
	private JTable table;
	DefaultTableModel tableModel;
	TableRowSorter<DefaultTableModel> sorter;
	Date date = new Date();
	String inventoryId = "KC" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}
	public InventorySelect() {
		updateInVentory();
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setTitle("���ͳ��");
		setBounds(100, 100, 640, 360);
		
		JLabel lblSeltCusName = new JLabel("��������Ҫͳ�Ƶ���Ʒ���ƹؼ��֣�");
		
		txtSeltInvName = new JTextField();
		txtSeltInvName.setColumns(10);
		
		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		String[] tableHeads = new String[]{"���ͳ�Ʊ��", "ͳ������", "��Ʒ���", "��Ʒ����",
				"��Ʒ���", "��Ʒ����"};
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setColumnIdentifiers(tableHeads);
		
		JButton btnSeltCusSelect = new JButton("��ѯ");
		btnSeltCusSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = tableModel.getRowCount();
				for (int i = 0; i < num; i++)
					tableModel.removeRow(0);
				String selectName = txtSeltInvName.getText();
				try {
					if(selectName.trim().length() == 0) {
						JOptionPane.showMessageDialog(null, "��������Ҫͳ�Ƶ���Ʒ����!");
						return;
					}
					List<List> all = DAOFactory.getIInventoryDAOInstance().findAll(selectName);
					
					int size = all.size();
					if(size == 0) {
						JOptionPane.showMessageDialog(null, "û���ҵ����ư���  '" + selectName + "' ����Ʒͳ����Ϣ!");
						return;
					}
					
					JOptionPane.showMessageDialog(null, "�ҵ� " + size + " ����Ϣ");
					updateTable(all,tableModel);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnSeltCusSelectAll = new JButton("��ʾȫ��ͳ����Ϣ");
		btnSeltCusSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSeltInvName.setText("");
				try {
					int num = tableModel.getRowCount();
					for (int i = 0; i < num; i++)
						tableModel.removeRow(0);
					List<List> all = DAOFactory.getIInventoryDAOInstance().findAll("");
					int size = all.size();
					JOptionPane.showMessageDialog(null, "�ҵ� " + size + " ����Ϣ");
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
							.addComponent(txtSeltInvName, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(txtSeltInvName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSeltCusSelect)
						.addComponent(btnSeltCusSelectAll))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
	
	private void updateInVentory() {
		try {
			List<Product> all = DAOFactory.getIProductDAOInstance().findAll("");
			Inventory inventory = new Inventory();
			for(int i = 0;i<all.size();i++) {
				String inventoryIdForProduct = inventoryId + i;
				inventory.setInventoryId(inventoryIdForProduct);
				inventory.setInventoryDate(date);
				inventory.setStorageId(null);
				inventory.setProductId(all.get(i).getProductId());
				inventory.setAmounts(all.get(i).getProductAmount());
				DAOFactory.getIInventoryDAOInstance().doCreate(inventory);
			}
			
			JOptionPane.showMessageDialog(null,"���¿��ͳ�������,���ѯ�����Ϣ!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private void updateTable(List<List> all, DefaultTableModel tableModel) {				
		for (int i=0; i<all.size();i++) {
			List<String> info = all.get(i);
			Vector<String> rowData = new Vector<String>();
			rowData.addAll(info);
			tableModel.addRow(rowData);
		}		
	}	
}
