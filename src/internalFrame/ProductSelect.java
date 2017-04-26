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

import tableModel.Product;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class ProductSelect extends JInternalFrame {
	private JTextField txtSeltProName;
	private JTable table;
	DefaultTableModel tableModel;
	TableRowSorter<DefaultTableModel> sorter;


	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}
	public ProductSelect() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setTitle("��Ʒ��ѯ");
		setBounds(100, 100, 640, 360);
		
		JLabel lblSeltCusName = new JLabel("��������Ҫ��ѯ����Ʒ���ƹؼ��֣�");
		
		txtSeltProName = new JTextField();
		txtSeltProName.setColumns(10);
		
		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		String[] tableHeads = new String[]{"��Ʒ���", "��Ʒ����", "��Ʒ���", "�����۸�",
				"���ۼ۸�", "��Ʒ����", "��ע��Ϣ"};
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setColumnIdentifiers(tableHeads);
		
		JButton btnSeltProSelect = new JButton("��ѯ");
		btnSeltProSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = tableModel.getRowCount();
				for (int i = 0; i < num; i++)
					tableModel.removeRow(0);
				String selectName = txtSeltProName.getText();
				try {
					if(selectName.trim().length() == 0) {
						JOptionPane.showMessageDialog(null, "��������Ҫ��ѯ����Ʒ����!");
						return;
					}
					List<Product> all = DAOFactory.getIProductDAOInstance().findAll(selectName);
					
					int size = all.size();
					if(size == 0) {
						JOptionPane.showMessageDialog(null, "û���ҵ����ư���  '" + selectName + "' ����Ʒ!");
						return;
					}
					
					JOptionPane.showMessageDialog(null, "�ҵ� " + size + " ����Ʒ");
					
					
					updateTable(all,tableModel);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnSeltProSelectAll = new JButton("��ʾȫ����Ʒ");
		btnSeltProSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSeltProName.setText("");
				try {
					int num = tableModel.getRowCount();
					for (int i = 0; i < num; i++)
						tableModel.removeRow(0);
					List<Product> all = DAOFactory.getIProductDAOInstance().findAll("");
					int size = all.size();
					JOptionPane.showMessageDialog(null, "�ҵ� " + size + " ����Ʒ");
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
							.addComponent(txtSeltProName, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSeltProSelect)
							.addPreferredGap(ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
							.addComponent(btnSeltProSelectAll)
							.addGap(30))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSeltCusName)
						.addComponent(txtSeltProName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSeltProSelect)
						.addComponent(btnSeltProSelectAll))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
	
	private void updateTable(List<Product> all, DefaultTableModel tableModel) {		
		
		for (int i=0; i<all.size();i++) {
			Product product = all.get(i);
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(product.getProductId());
			rowData.add(product.getProductName());
			rowData.add(product.getProductUnit());
			rowData.add(product.getProductInPrice());
			rowData.add(product.getProductOutPrice());			
			rowData.add(product.getProductAmount());
			rowData.add(product.getProductRemark());
			tableModel.addRow(rowData);
		}
		
	}
	
}
