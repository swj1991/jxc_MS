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


import tableModel.InOrder;
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


public class StockManage extends JInternalFrame {
	private User user = Login.getUser();
	private JComboBox cboStocSelectSupplier = new JComboBox();
	private JComboBox cboStocSelectProduct = new JComboBox();	
	private JTextField txtStocId = new JTextField();	
	private JTextField txtStocDate = new JTextField();
	private JTextField txtStocUserName = new JTextField();
	private JTextField txtTotalPay = new JTextField();;
	private JTable table = new JTable();;
	private DefaultTableModel tableModel;
	private TableRowSorter<DefaultTableModel> sorter;
	private JTextField txtStocProductName = new JTextField();
	private JTextField txtStocProductUnit = new JTextField();
	private JTextField txtStocProductInPrice = new JTextField();
	private JTextField txtStocProductAmounts = new JTextField();
	private StockOrder stockOrder = new StockOrder();
	private InOrder inOrder = new InOrder();
	private Set<StockOrderDetail> stockOrderDetail = stockOrder.getStockOrderDetail();
	private double totalPay;
	private Date date = new Date();
	private String stockId = "CG" + new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	private String inOrderId = "RK" + new SimpleDateFormat("yyyyMMddHHmmss").format(date);		

	public StockManage() {
		setIconifiable(true);
		setClosable(true);
		setTitle("�ɹ���");
		setVisible(true);
		setBounds(48, 48, 640, 454);
		
		JPanel panel = new JPanel();
		
		JButton btnStocAdd = new JButton("���");
		btnStocAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboStocSelectSupplier.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ӧ��!");
					return;
				}
				
				if(cboStocSelectProduct.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "��ѡ����Ʒ!");
					return;
				}
				if(txtStocProductAmounts.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "��������Ʒ��Ŀ!");
					txtStocProductAmounts.requestFocusInWindow();
					return;
				}
					
				String productId = (cboStocSelectProduct.getSelectedItem()).toString().substring(0, 16);
				String productName = txtStocProductName.getText();
				String productUnit = txtStocProductUnit.getText();
				Double productInPrice = Double.parseDouble(txtStocProductInPrice.getText());
				Integer productAmount; 
				
				try {
					productAmount = Integer.parseInt(txtStocProductAmounts.getText());
					totalPay += productInPrice * productAmount;
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "��������ȷ����Ʒ��Ŀ!");
					txtStocProductAmounts.setText("");
					txtStocProductAmounts.requestFocusInWindow();
					return;
				}
				
				StockOrderDetail stockdetail = new StockOrderDetail(stockId,productId,productAmount);
				stockOrderDetail.add(stockdetail);
				
				Vector<Object> rowData = new Vector<Object>();
				rowData.add(productId);
				rowData.add(productName);
				rowData.add(productUnit);
				rowData.add(productInPrice);
				rowData.add(productAmount);
				tableModel.addRow(rowData);
				txtTotalPay.setText("" + totalPay);
				JOptionPane.showMessageDialog(null, "�ɹ�����һ���ɹ���Ϣ!");
				txtStocProductAmounts.setText("");
				}
		});
		
		
		JLabel lblStocId = new JLabel("�������ţ�");	
		txtStocId.setText(stockId);
		txtStocId.setEditable(false);
		txtStocId.setColumns(10);
		
		JLabel lblStocSupplier = new JLabel("ѡ��Ӧ�̣�");		
		cboStocSelectSupplier.setPreferredSize(new Dimension(160, 21));
		initComboBox();

		JLabel lblStocDate = new JLabel("����ʱ�䣺");
		txtStocDate = new JTextField();
		txtStocDate.setEditable(false);
		txtStocDate.setText(new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(date));
		txtStocDate.setColumns(10);
		
		JLabel lblStocUserName = new JLabel("�����ˣ�");
		txtStocUserName = new JTextField();
		txtStocUserName.setEditable(false);
		txtStocUserName.setText(user.getUserName());
		txtStocUserName.setColumns(10);

		
		
		initTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		JLabel lblTotalPay = new JLabel("�ܽ�");
				
		txtTotalPay.setEditable(false);
		txtTotalPay.setColumns(10);
		
		JLabel lblStocProduct = new JLabel("ѡ��ɹ�����Ʒ��");
		
		cboStocSelectProduct.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){ 
					if(cboStocSelectProduct.getSelectedIndex()==0){						
						txtStocProductName.setText(null);
						txtStocProductUnit.setText(null);
						txtStocProductInPrice.setText(null);
						txtStocProductAmounts.setText(null);
						return;
					}
				doProductSelectAction();
				}else{
					return;
				}
				}
			});
		
		JLabel lblStocProductName = new JLabel("��Ʒ���ƣ�");
		
		txtStocProductName.setEditable(false);
		txtStocProductName.setColumns(10);
		
		JLabel lblStocProductUnit = new JLabel("��Ʒ���");
		
		txtStocProductUnit.setEditable(false);
		txtStocProductUnit.setColumns(10);
		
		JLabel lblStocProductInPrice = new JLabel("�����۸�");
		
		txtStocProductInPrice.setEditable(false);
		txtStocProductInPrice.setColumns(10);
		
		JLabel lblStocProductAmounts = new JLabel("�ɹ�������");
		
		txtStocProductAmounts.setColumns(10);
		
		JButton btnStocSubmit = new JButton("�ύ");
		btnStocSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = false;
				boolean inFlag = false;
				if(table.getRowCount() == 0){
					JOptionPane.showMessageDialog(null, "����Ӳɹ���Ϣ!");
					return;
				}

				String supplierId =(cboStocSelectSupplier.getSelectedItem()).toString().substring(0, 16);
				stockOrder.setStockOrderId(stockId);		
				stockOrder.setStockOrderDate(date);		
				stockOrder.setUserId(user.getUserId());	
				stockOrder.setSupplierId(supplierId);		
				stockOrder.setAmountPay(totalPay);		
				stockOrder.setStockOrderDetail(stockOrderDetail);
		
				try {
					flag = DAOFactory.getIStockOrderDAOInstance().doCreate(stockOrder);					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(flag == true) {
					JOptionPane.showMessageDialog(null, "�ɹ���Ϣ�ύ�ɹ�!");
					inOrder.setInOrderID(inOrderId);
					inOrder.setStockOrderID(stockId);
					inOrder.setPayStatus(false);
					try {
						inFlag = DAOFactory.getIInOrderDAOInstance().doCreate(inOrder);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(inFlag == true){
						JOptionPane.showMessageDialog(null, "��ⵥ���ύ,����ϵ�ֿ�����Ŵ���!");
						setVisible(false);
						return;
					}else {
						JOptionPane.showMessageDialog(null, "��ⵥ�����ύʧ��!");
						return;
					}										
				}else {
					JOptionPane.showMessageDialog(null, "�ɹ���Ϣ�ύʧ��!");
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
						.addComponent(cboStocSelectProduct, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(txtStocProductName, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(191, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(txtStocProductUnit, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblStocProductInPrice)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtStocProductInPrice, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtStocProductAmounts, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addGap(57)
							.addComponent(btnStocAdd)
							.addGap(18)
							.addComponent(btnStocSubmit)
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
								.addComponent(txtStocProductName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStocProductUnit)
								.addComponent(txtStocProductUnit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStocProductInPrice)
								.addComponent(txtStocProductInPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(1))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblStocProduct)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cboStocSelectProduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(17)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStocProductAmounts)
								.addComponent(txtStocProductAmounts, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTotalPay)
								.addComponent(txtTotalPay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnStocAdd)
								.addComponent(btnStocSubmit))))
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
							.addComponent(txtStocDate, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
							.addGap(18, 18, Short.MAX_VALUE)
							.addComponent(lblStocUserName)
							.addGap(18)
							.addComponent(txtStocUserName, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblStocId)
							.addGap(18)
							.addComponent(txtStocId, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblStocSupplier)
							.addGap(18)
							.addComponent(cboStocSelectSupplier, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)))
					.addGap(35))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStocId)
						.addComponent(txtStocId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cboStocSelectSupplier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStocSupplier))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStocDate)
						.addComponent(txtStocDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtStocUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
		if (cboStocSelectProduct.getSelectedIndex()==0) {
			return;
		}
		try {
			getid =(cboStocSelectProduct.getSelectedItem()).toString().substring(0, 16);
			product = DAOFactory.getIProductDAOInstance().findById(getid);
			txtStocProductName.setText(product.getProductName());
			txtStocProductUnit.setText(product.getProductUnit());
			txtStocProductInPrice.setText(product.getProductInPrice()+"");
			txtStocProductAmounts.setText("");
			txtStocProductAmounts.requestFocusInWindow();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//��ʼ����Ӧ�̺���Ʒ�����˵�
	public void initComboBox(){
		cboStocSelectSupplier.removeAllItems();
		cboStocSelectProduct.removeAllItems();
		cboStocSelectSupplier.addItem("(��ѡ��)");
		cboStocSelectProduct.addItem("(��ѡ��)");
		try {
			List<Supplier> allSupplier = DAOFactory.getISupplierDAOInstance().findAll("");
			for (int i=0; i<allSupplier.size();i++) {
				cboStocSelectSupplier.addItem(allSupplier.get(i).getSupplierId()+
						"("+allSupplier.get(i).getSupplierName()+")");
			}
			
			List<Product> allProduct = DAOFactory.getIProductDAOInstance().findAll("");
			for (int j=0; j<allProduct.size();j++) {
				cboStocSelectProduct.addItem(allProduct.get(j).getProductId() + 
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
