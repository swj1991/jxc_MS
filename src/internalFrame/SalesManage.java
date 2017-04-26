package internalFrame;


import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;


import tableModel.Customer;
import tableModel.InOrder;
import tableModel.OutOrder;
import tableModel.SalesOrder;
import tableModel.SalesOrderDetail;
import tableModel.User;
import tableModel.Supplier;
import tableModel.StockOrderDetail;
import tableModel.StockOrder;
import tableModel.Product;
import com.henu.login.Login;
import com.henu.tableRenderer.TableRenderer;
import com.henu.factory.DAOFactory;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ItemListener;


public class SalesManage extends JInternalFrame {
	private User user = Login.getUser();
	private JComboBox cboSalesSelectCustomer = new JComboBox();
	private JComboBox cboSalesSelectProduct = new JComboBox();	
	private JTextField txtSalesId = new JTextField();	
	private JTextField txtSalesDate = new JTextField();
	private JTextField txtSalesUserName = new JTextField();
	private JTextField txtTotalPay = new JTextField();;
	private JTable table = new JTable();;
	private DefaultTableModel tableModel;
	private TableRowSorter<DefaultTableModel> sorter;
	private JTextField txtSalesProductName = new JTextField();
	private JTextField txtSalesProductUnit = new JTextField();
	private JTextField txtSalesProductInPrice = new JTextField();
	private JTextField txtSalesProductAmounts = new JTextField();
	private SalesOrder salesOrder = new SalesOrder();
	private OutOrder outOrder = new OutOrder();
	private Set<SalesOrderDetail> salesOrderDetail = salesOrder.getSalesOrderDetail();
	private double totalPay;
	private Date date = new Date();
	private String salesId = "XS" + new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	private String outOrderId = "CK" + new SimpleDateFormat("yyyyMMddHHmmss").format(date);		

	public SalesManage() {
		setIconifiable(true);
		setClosable(true);
		setTitle("���۵�");
		setVisible(true);
		setBounds(48, 48, 640, 454);
		
		JPanel panel = new JPanel();
		
		JButton btnSalesAdd = new JButton("���");
		btnSalesAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboSalesSelectCustomer.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "��ѡ��˿�!");
					return;
				}
				
				if(cboSalesSelectProduct.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "��ѡ����Ʒ!");
					return;
				}
				if(txtSalesProductAmounts.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "��������Ʒ��Ŀ!");
					txtSalesProductAmounts.requestFocusInWindow();
					return;
				}
					
				String productId = (cboSalesSelectProduct.getSelectedItem()).toString().substring(0, 16);
				String productName = txtSalesProductName.getText();
				String productUnit = txtSalesProductUnit.getText();
				Double productInPrice = Double.parseDouble(txtSalesProductInPrice.getText());
				Integer productAmount; 
				
				try {
					productAmount = Integer.parseInt(txtSalesProductAmounts.getText());
					totalPay += productInPrice * productAmount;
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "��������ȷ����Ʒ��Ŀ!");
					txtSalesProductAmounts.setText("");
					txtSalesProductAmounts.requestFocusInWindow();
					return;
				}
				
				SalesOrderDetail stockdetail = new SalesOrderDetail(salesId,productId,productAmount);
				salesOrderDetail.add(stockdetail);
				
				Vector<Object> rowData = new Vector<Object>();
				rowData.add(productId);
				rowData.add(productName);
				rowData.add(productUnit);
				rowData.add(productInPrice);
				rowData.add(productAmount);
				tableModel.addRow(rowData);
				txtTotalPay.setText("" + totalPay);
				JOptionPane.showMessageDialog(null, "�ɹ�����һ��������Ϣ!");
				txtSalesProductAmounts.setText("");
				}
		});
		
		
		JLabel lblStocId = new JLabel("���۵��ţ�");	
		txtSalesId.setText(salesId);
		txtSalesId.setEditable(false);
		txtSalesId.setColumns(10);
		
		JLabel lblStocSupplier = new JLabel("ѡ��˿ͣ�");		
		cboSalesSelectCustomer.setPreferredSize(new Dimension(160, 21));
		initComboBox();

		JLabel lblStocDate = new JLabel("����ʱ�䣺");
		txtSalesDate = new JTextField();
		txtSalesDate.setEditable(false);
		txtSalesDate.setText(new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(date));
		txtSalesDate.setColumns(10);
		
		JLabel lblStocUserName = new JLabel("�����ˣ�");
		txtSalesUserName = new JTextField();
		txtSalesUserName.setEditable(false);
		txtSalesUserName.setText(user.getUserName());
		txtSalesUserName.setColumns(10);

		
		
		initTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		JLabel lblTotalPay = new JLabel("�ܽ�");
				
		txtTotalPay.setEditable(false);
		txtTotalPay.setColumns(10);
		
		JLabel lblStocProduct = new JLabel("ѡ�����۵���Ʒ��");
		
		cboSalesSelectProduct.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){ 
					if(cboSalesSelectProduct.getSelectedIndex()==0){						
						txtSalesProductName.setText(null);
						txtSalesProductUnit.setText(null);
						txtSalesProductInPrice.setText(null);
						txtSalesProductAmounts.setText(null);
						return;
					}
				doProductSelectAction();
				}else{
					return;
				}
				}
			});
		
		JLabel lblStocProductName = new JLabel("��Ʒ���ƣ�");
		
		txtSalesProductName.setEditable(false);
		txtSalesProductName.setColumns(10);
		
		JLabel lblStocProductUnit = new JLabel("��Ʒ���");
		
		txtSalesProductUnit.setEditable(false);
		txtSalesProductUnit.setColumns(10);
		
		JLabel lblStocProductInPrice = new JLabel("���ۼ۸�");
		
		txtSalesProductInPrice.setEditable(false);
		txtSalesProductInPrice.setColumns(10);
		
		JLabel lblStocProductAmounts = new JLabel("����������");
		
		txtSalesProductAmounts.setColumns(10);
		
		JButton btnSalesSubmit = new JButton("�ύ");
		btnSalesSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = false;
				boolean inFlag = false;
				if(table.getRowCount() == 0){
					JOptionPane.showMessageDialog(null, "�����������Ϣ!");
					return;
				}

				String supplierId =(cboSalesSelectCustomer.getSelectedItem()).toString().substring(0, 16);
				salesOrder.setSalesOrderId(salesId);		
				salesOrder.setSalesOrderDate(date);		
				salesOrder.setUserId(user.getUserId());	
				salesOrder.setCustomerId(supplierId);		
				salesOrder.setAmountPay(totalPay);		
				salesOrder.setSalesOrderDetail(salesOrderDetail);
		
				try {
					flag = DAOFactory.getISalesOrderDAOInstance().doCreate(salesOrder);					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(flag == true) {
					JOptionPane.showMessageDialog(null, "������Ϣ�ύ�ɹ�!");
					outOrder.setOutOrderID(outOrderId);
					outOrder.setSalesOrderID(salesId);
					outOrder.setPayStatus(false);
					try {
						inFlag = DAOFactory.getIOutOrderDAOInstance().doCreate(outOrder);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(inFlag == true){
						JOptionPane.showMessageDialog(null, "���ⵥ���ύ,����ϵ�ֿ�����Ŵ���!");
						setVisible(false);
						return;
					}else {
						JOptionPane.showMessageDialog(null, "���ⵥ�����ύʧ��!");
						return;
					}										
				}else {
					JOptionPane.showMessageDialog(null, "������Ϣ�ύʧ��!");
					return;
				}
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStocProduct)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(182)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStocProductUnit)
								.addComponent(lblStocProductName)
								.addComponent(lblStocProductAmounts)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTotalPay)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTotalPay, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
						.addComponent(cboSalesSelectProduct, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(txtSalesProductName, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(191, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(txtSalesProductUnit, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblStocProductInPrice)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtSalesProductInPrice, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtSalesProductAmounts, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addGap(57)
							.addComponent(btnSalesAdd)
							.addGap(18)
							.addComponent(btnSalesSubmit)
							.addGap(25))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStocProductName)
								.addComponent(txtSalesProductName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStocProductUnit)
								.addComponent(txtSalesProductUnit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStocProductInPrice)
								.addComponent(txtSalesProductInPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(1))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblStocProduct)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cboSalesSelectProduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(17)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStocProductAmounts)
								.addComponent(txtSalesProductAmounts, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTotalPay)
								.addComponent(txtTotalPay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSalesAdd)
								.addComponent(btnSalesSubmit))))
					.addGap(39))
		);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblStocDate)
							.addGap(18)
							.addComponent(txtSalesDate, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
							.addGap(18, 18, Short.MAX_VALUE)
							.addComponent(lblStocUserName)
							.addGap(18)
							.addComponent(txtSalesUserName, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblStocId)
							.addGap(18)
							.addComponent(txtSalesId, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblStocSupplier)
							.addGap(18)
							.addComponent(cboSalesSelectCustomer, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)))
					.addGap(35))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStocId)
						.addComponent(txtSalesId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cboSalesSelectCustomer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStocSupplier))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStocDate)
						.addComponent(txtSalesDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSalesUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStocUserName))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		addInternalFrameListener(new initTasks());

	}


	
	//������ѡitem ���в���
	private void doProductSelectAction() {
		String getid="";
		Product product = new Product();
		if (cboSalesSelectProduct.getSelectedIndex()==0) {
			return;
		}
		try {
			getid =(cboSalesSelectProduct.getSelectedItem()).toString().substring(0, 16);
			product = DAOFactory.getIProductDAOInstance().findById(getid);
			txtSalesProductName.setText(product.getProductName());
			txtSalesProductUnit.setText(product.getProductUnit());
			txtSalesProductInPrice.setText(product.getProductInPrice()+"");
			txtSalesProductAmounts.setText("");
			txtSalesProductAmounts.requestFocusInWindow();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//��ʼ����Ӧ�̺���Ʒ�����˵�
	public void initComboBox(){
		cboSalesSelectCustomer.removeAllItems();
		cboSalesSelectProduct.removeAllItems();
		cboSalesSelectCustomer.addItem("(��ѡ��)");
		cboSalesSelectProduct.addItem("(��ѡ��)");
		try {
			List<Customer> allCustomer = DAOFactory.getICustomerDAOInstance().findAll("");
			for (int i=0; i<allCustomer.size();i++) {
				cboSalesSelectCustomer.addItem(allCustomer.get(i).getCustomerId()+
						"("+allCustomer.get(i).getCustomerName()+")");
			}
			
			List<Product> allProduct = DAOFactory.getIProductDAOInstance().findAll("");
			for (int j=0; j<allProduct.size();j++) {
				cboSalesSelectProduct.addItem(allProduct.get(j).getProductId() + 
						"(" + allProduct.get(j).getProductName() + ")");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	//��ʼ�����
	private void initTable() {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		String[] columnNames = {"��Ʒ���", "��Ʒ����","��Ʒ���", "��Ʒ�۸�", "��Ʒ����"};
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setColumnIdentifiers(columnNames);
		
		sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		table.setRowSorter(sorter);
		TableRenderer renderer = new TableRenderer();
		table.setDefaultRenderer(Object.class, renderer);
	}

	private final class initTasks extends InternalFrameAdapter {
		public void internalFrameActivated(InternalFrameEvent e) {
			super.internalFrameActivated(e);
		}
		

	}
}
