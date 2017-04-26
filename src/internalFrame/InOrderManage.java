package internalFrame;



import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import tableModel.InOrder;
import tableModel.Product;
import tableModel.SalesOrderDetail;
import tableModel.StockOrder;
import tableModel.StockOrderDetail;
import tableModel.Storage;
import tableModel.User;

import com.henu.factory.DAOFactory;
import com.henu.login.Login;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class InOrderManage extends JInternalFrame {
	private User user = Login.getUser();
	private JTextField txtInOrderId;
	private JTextField txtInOrderStocId;
	private JTextField txtInOrderDate;
	private JTextField txtInOrderUserName;
	private JComboBox cboNeedTake;
	private JComboBox cboInOrderAddress;
    private JComboBox cboInOrderPayStatus;
	private JTextArea txtaInOrderRemark;
	private InOrder inorder = new InOrder();
	private StockOrder stockOrder = new StockOrder();	
	private Date date = new Date();
	public InOrderManage() {
		setVisible(true);
		setResizable(false);
		setIconifiable(true);
		setClosable(true);
		setTitle("入库单");
		setBounds(56, 24, 480, 340);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "备注信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblInOrderSelect = new JLabel("选择待处理入库单：");
		//////////////////////////////////
		cboNeedTake = new JComboBox();
		initcboNeedTake();
		cboNeedTake.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){ 
					if(cboNeedTake.getSelectedIndex()==0){
						txtInOrderId.setText(null);
						txtInOrderStocId.setText(null);
						txtInOrderDate.setText(null);
						txtInOrderUserName.setText(null);
						txtaInOrderRemark.setText(null);
						return;
					}
					doInOrderSelectAction();
					
				}else{
					return;
				}
			}
		});
		
		JButton btnInOrderModify = new JButton("更改");
		btnInOrderModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboNeedTake.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null, "请选择一个待处理入库单！");
					return;
				}
				try {
					inorder = DAOFactory.getIInOrderDAOInstance().findById(txtInOrderId.getText());
					if(cboInOrderPayStatus.getSelectedIndex()==1){
						inorder.setPayStatus(true);
					}
					if(cboInOrderPayStatus.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(null, "请选择支付采购单!");
						return;
					}
					if(cboInOrderAddress.getSelectedIndex()==0){
						JOptionPane.showMessageDialog(null, "请选择一个仓库地址！");	
						return;
					}else{
						//获取inorder
						inorder.setStockOrderID(txtInOrderStocId.getText());
						inorder.setStorageID(cboInOrderAddress.getSelectedItem().toString().substring(0 ,16));
						inorder.setInDate(date);
						inorder.setUserID(user.getUserId());
						inorder.setInOrderRemark(txtaInOrderRemark.getText());
					}
					
					//更新入库单
					try {
						DAOFactory.getIInOrderDAOInstance().doUpdate(inorder);
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "更新失败！");
						return;
					}
					stockOrder = DAOFactory.getIStockOrderDAOInstance().findById(inorder.getStockOrderID());
					Set<StockOrderDetail> stockOrderDetail = stockOrder.getStockOrderDetail();
					Iterator<StockOrderDetail> iter = stockOrderDetail.iterator();
					Product product = new Product();
					String productId;
					int amount;
					
					while(iter.hasNext()) {
						StockOrderDetail stockDetail = iter.next();
						productId = stockDetail.getProductId();
						amount = stockDetail.getAmounts();
						product = DAOFactory.getIProductDAOInstance().findById(productId);
						int productAmount = product.getProductAmount() + amount;
						product.setProductAmount(productAmount);
						DAOFactory.getIProductDAOInstance().doUpdate(product);
					}
					JOptionPane.showMessageDialog(null, "更新成功！");
					initcboNeedTake();		
					cboInOrderAddress.setSelectedIndex(0);
					cboInOrderPayStatus.setSelectedIndex(0);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInOrderSelect)
					.addContainerGap(346, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(cboNeedTake, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
					.addComponent(btnInOrderModify)
					.addGap(41))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblInOrderSelect)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboNeedTake, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnInOrderModify))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		
		txtaInOrderRemark = new JTextArea();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtaInOrderRemark, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(txtaInOrderRemark, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblInOrderId = new JLabel("入库单编号：");
		
		txtInOrderId = new JTextField();
		txtInOrderId.setEditable(false);
		txtInOrderId.setColumns(10);
		
		JLabel lblInOrderStocId = new JLabel("采购单编号：");
		
		txtInOrderStocId = new JTextField();
		txtInOrderStocId.setEditable(false);
		txtInOrderStocId.setColumns(10);
		
		JLabel lblInOrderDate = new JLabel("处理日期：");
		
		txtInOrderDate = new JTextField();
		txtInOrderDate.setEditable(false);
		txtInOrderDate.setColumns(10);
		
		JLabel lblInOrderAddress = new JLabel("仓库地址：");
		
		cboInOrderAddress = new JComboBox();
		initcboInOrderAddress();
		
		JLabel lblInOrderUserName = new JLabel("负责人：");
		
		txtInOrderUserName = new JTextField();
		txtInOrderUserName.setEditable(false);
		txtInOrderUserName.setColumns(10);
		
		JLabel lblInOrderPayStatus = new JLabel("支付状态：");
		
		cboInOrderPayStatus = new JComboBox();
		cboInOrderPayStatus.addItem("未付款");
		cboInOrderPayStatus.addItem("已付款");
		cboInOrderPayStatus.setSelectedIndex(0);
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblInOrderId)
								.addComponent(lblInOrderUserName))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtInOrderUserName)
								.addComponent(txtInOrderId, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblInOrderStocId)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtInOrderStocId, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblInOrderAddress)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboInOrderAddress, 0, 155, Short.MAX_VALUE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblInOrderDate)
							.addGap(18)
							.addComponent(txtInOrderDate, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblInOrderPayStatus)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboInOrderPayStatus, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInOrderId)
						.addComponent(txtInOrderId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInOrderStocId)
						.addComponent(txtInOrderStocId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInOrderAddress)
						.addComponent(cboInOrderAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInOrderUserName)
						.addComponent(txtInOrderUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInOrderDate)
						.addComponent(txtInOrderDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInOrderPayStatus)
						.addComponent(cboInOrderPayStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(86, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
	
	//根据所选item 进行操作
	private void doInOrderSelectAction() {
		String getid="";
		if (cboNeedTake.getSelectedIndex()==0) {
			
			return;
		}
		try {
			getid =(cboNeedTake.getSelectedItem()).toString();
			inorder = DAOFactory.getIInOrderDAOInstance().findById(getid);
			txtInOrderId.setText(inorder.getInOrderID());
			txtInOrderStocId.setText(inorder.getStockOrderID());
			txtInOrderDate.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			txtInOrderUserName.setText(user.getUserName());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//重新排序 实时更新
	public void initcboNeedTake(){
		cboNeedTake.removeAllItems();
		cboNeedTake.addItem("(请选择)");
		try {
			List<InOrder> all = DAOFactory.getIInOrderDAOInstance().findAll("");
			for (int i=0; i<all.size();i++) {
				if(all.get(i).isPayStatus()==false){
					cboNeedTake.addItem(all.get(i).getInOrderID());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			}
		}
	
	public void initcboInOrderAddress(){
		cboInOrderAddress.removeAllItems();
		cboInOrderAddress.addItem("(请选择)");
		try {
			List<Storage> all = DAOFactory.getIStorageDAOInstance().findAll("");
			for (int i=0; i<all.size();i++) {
				
				cboInOrderAddress.addItem(all.get(i).getStorageId()+
						"("+all.get(i).getStorageName()+")");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
	}
	
}
