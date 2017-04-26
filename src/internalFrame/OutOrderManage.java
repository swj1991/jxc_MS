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
import tableModel.OutOrder;
import tableModel.Product;
import tableModel.SalesOrder;
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

public class OutOrderManage extends JInternalFrame {
	private User user = Login.getUser();
	private JTextField txtOutOrderId;
	private JTextField txtOutOrderSalesId;
	private JTextField txtOutOrderDate;
	private JTextField txtOutOrderUserName;
	private JComboBox cboNeedTake;
	private JComboBox cboOutOrderAddress;
    private JComboBox cboOutOrderPayStatus;
	private JTextArea txtaOutOrderRemark;
	private OutOrder outorder = new OutOrder();
	private SalesOrder salesOrder = new SalesOrder();	
	private Date date = new Date();
	
	public OutOrderManage() {
		setVisible(true);
		setResizable(false);
		setIconifiable(true);
		setClosable(true);
		setTitle("出库单");
		setBounds(56, 24, 480, 340);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "备注信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblInOrderSelect = new JLabel("选择待处理出库单：");
		//////////////////////////////////
		cboNeedTake = new JComboBox();
		initcboNeedTake();
		cboNeedTake.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){ 
					if(cboNeedTake.getSelectedIndex()==0){
						txtOutOrderId.setText(null);
						txtOutOrderSalesId.setText(null);
						txtOutOrderDate.setText(null);
						txtOutOrderUserName.setText(null);
						txtaOutOrderRemark.setText(null);
						return;
					}
					doOutOrderSelectAction();
					
					}else{
						return;
					}
			}
		});
		
		JButton btnInOrderModify = new JButton("更改");
		btnInOrderModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboNeedTake.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null, "请选择一个待处理出库单！");
					return;
				}
				try {
					outorder = DAOFactory.getIOutOrderDAOInstance().findById(txtOutOrderId.getText());
					if(cboOutOrderPayStatus.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(null, "请选择支付销售单!");
						return;
					}
					if(cboOutOrderAddress.getSelectedIndex()==0){
						JOptionPane.showMessageDialog(null, "请选择一个仓库地址！");	
						return;
					}else{
						//获取inorder
						outorder.setSalesOrderID(txtOutOrderSalesId.getText());
						outorder.setStorageID(cboOutOrderAddress.getSelectedItem().toString().substring(0 ,16));
						outorder.setOutDate(date);
						outorder.setUserID(user.getUserId());
						outorder.setOutOrderRemark(txtaOutOrderRemark.getText());
					}
					
					//更新入库单
					try {
						DAOFactory.getIOutOrderDAOInstance().doUpdate(outorder);
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "更新失败！");
						outorder.setPayStatus(false);
						return;
					}
					salesOrder = DAOFactory.getISalesOrderDAOInstance().findById(outorder.getSalesOrderID());
					Set<SalesOrderDetail> salesOrderDetail = salesOrder.getSalesOrderDetail();
					Iterator<SalesOrderDetail> iter = salesOrderDetail.iterator();
					Product product = new Product();
					String productId;
					int amount;
					int productAmount;
					String productInfo = "";
					boolean isOK = true;
					//验证库存
					while(iter.hasNext()) {
						SalesOrderDetail salesDetail = iter.next();
						productId = salesDetail.getProductId();
						amount = salesDetail.getAmounts();
						product = DAOFactory.getIProductDAOInstance().findById(productId);
						productAmount = product.getProductAmount();
						if(productAmount < amount) {
							isOK = false;
							break;
						}
					}
					
					if(isOK == false) {
						//统计缺货信息
						Iterator<SalesOrderDetail> iter_1 = salesOrderDetail.iterator();
						while(iter_1.hasNext()) {
							SalesOrderDetail salesDetail = iter_1.next();
							productId = salesDetail.getProductId();
							amount = salesDetail.getAmounts();
							product = DAOFactory.getIProductDAOInstance().findById(productId);
							productAmount = product.getProductAmount();
							int lackAmount = amount - productAmount;
							String productStr = product.getProductId() + "(" + product.getProductName() +") 缺: " + 
							lackAmount + " " + product.getProductUnit() + "\n";
							if(lackAmount > 0) {
								productInfo += productStr;
							}
						}
						JOptionPane.showMessageDialog(null,productInfo,"缺货提示",JOptionPane.INFORMATION_MESSAGE);
						outorder.setPayStatus(false);
					}else if(isOK == true) {
						//更改信息
						if(cboOutOrderPayStatus.getSelectedIndex()==1){
							outorder.setPayStatus(true);
						}
						Iterator<SalesOrderDetail> iter_2 = salesOrderDetail.iterator();
						while(iter_2.hasNext()) {
							SalesOrderDetail salesDetail = iter_2.next();
							productId = salesDetail.getProductId();
							amount = salesDetail.getAmounts();
							product = DAOFactory.getIProductDAOInstance().findById(productId);
							productAmount = product.getProductAmount();
							int newAmount = productAmount - amount;
						
							product.setProductAmount(newAmount);
							DAOFactory.getIProductDAOInstance().doUpdate(product);
						}
						JOptionPane.showMessageDialog(null, "更新成功！");
						initcboNeedTake();	
						cboOutOrderAddress.setSelectedIndex(0);
						cboOutOrderPayStatus.setSelectedIndex(0);
					}
				
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
		
		txtaOutOrderRemark = new JTextArea();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtaOutOrderRemark, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(txtaOutOrderRemark, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblInOrderId = new JLabel("出库单编号：");
		
		txtOutOrderId = new JTextField();
		txtOutOrderId.setEditable(false);
		txtOutOrderId.setColumns(10);
		
		JLabel lblInOrderStocId = new JLabel("销售单编号：");
		
		txtOutOrderSalesId = new JTextField();
		txtOutOrderSalesId.setEditable(false);
		txtOutOrderSalesId.setColumns(10);
		
		JLabel lblInOrderDate = new JLabel("处理日期：");
		
		txtOutOrderDate = new JTextField();
		txtOutOrderDate.setEditable(false);
		txtOutOrderDate.setColumns(10);
		
		JLabel lblInOrderAddress = new JLabel("仓库地址：");
		
		cboOutOrderAddress = new JComboBox();
		initcboOutOrderAddress();
		
		JLabel lblInOrderUserName = new JLabel("负责人：");
		
		txtOutOrderUserName = new JTextField();
		txtOutOrderUserName.setEditable(false);
		txtOutOrderUserName.setColumns(10);
		
		JLabel lblInOrderPayStatus = new JLabel("支付状态：");
		
		cboOutOrderPayStatus = new JComboBox();
		cboOutOrderPayStatus.addItem("未付款");
		cboOutOrderPayStatus.addItem("已付款");
		cboOutOrderPayStatus.setSelectedIndex(0);
		
		
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
								.addComponent(txtOutOrderUserName)
								.addComponent(txtOutOrderId, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblInOrderStocId)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtOutOrderSalesId, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblInOrderAddress)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboOutOrderAddress, 0, 155, Short.MAX_VALUE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblInOrderDate)
							.addGap(18)
							.addComponent(txtOutOrderDate, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblInOrderPayStatus)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboOutOrderPayStatus, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInOrderId)
						.addComponent(txtOutOrderId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInOrderStocId)
						.addComponent(txtOutOrderSalesId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInOrderAddress)
						.addComponent(cboOutOrderAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInOrderUserName)
						.addComponent(txtOutOrderUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInOrderDate)
						.addComponent(txtOutOrderDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInOrderPayStatus)
						.addComponent(cboOutOrderPayStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(86, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
	
	//根据所选item 进行操作
	private void doOutOrderSelectAction() {
		String getid="";
		if (cboNeedTake.getSelectedIndex()==0) {
			return;
		}
		try {
			getid =(cboNeedTake.getSelectedItem()).toString();
			outorder = DAOFactory.getIOutOrderDAOInstance().findById(getid);
			txtOutOrderId.setText(outorder.getOutOrderID());
			txtOutOrderSalesId.setText(outorder.getSalesOrderID());
			txtOutOrderDate.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			txtOutOrderUserName.setText(user.getUserName());
		
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
			List<OutOrder> all = DAOFactory.getIOutOrderDAOInstance().findAll("");
			for (int i=0; i<all.size();i++) {
				if(all.get(i).isPayStatus()==false){
					cboNeedTake.addItem(all.get(i).getOutOrderID());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void initcboOutOrderAddress(){
		cboOutOrderAddress.removeAllItems();
		cboOutOrderAddress.addItem("(请选择)");
		try {
			List<Storage> all = DAOFactory.getIStorageDAOInstance().findAll("");
			for (int i=0; i<all.size();i++) {
				
				cboOutOrderAddress.addItem(all.get(i).getStorageId()+
						"("+all.get(i).getStorageName()+")");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
}
