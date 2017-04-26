package internalFrame;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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


import tableModel.Product;

import com.henu.factory.DAOFactory;
import javax.swing.JScrollPane;

public class ProductManage extends JInternalFrame {
	private JTextField txtProNameAdd;
	private JTextField txtProUnitAdd;
	private JTextField txtProInPriceAdd;
	private JTextField txtProOutPriceAdd;
	private JTextField txtProNameModify;
	private JTextField txtProUnitModify;
	private JTextField txtProInPriceModify;
	private JTextField txtProOutPriceModify;
    private JComboBox cboProSelectModify;
    private JTextArea txtaProRemarkModify;
    private JTextArea txtaProRemarkAdd;

	public ProductManage() {
		setIconifiable(true);
		setClosable(true);
		setTitle("商品管理");
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
		
		JPanel addProductPanel = new JPanel();
		tabbedPane.addTab("添加商品信息", null, addProductPanel, null);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "备注信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnProAddAdd = new JButton("添加");
		btnProAddAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtProNameAdd.getText().trim().length()==0
						|| txtProUnitAdd.getText().trim().length()==0
						|| txtProInPriceAdd.getText().trim().length()==0
						|| txtProOutPriceAdd.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(ProductManage.this,
							"请完成未填写的信息。", "用户添加", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try{
					Double.valueOf(txtProInPriceAdd.getText());
					Double.valueOf(txtProOutPriceAdd.getText());
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "价格必须输入数字！");
					return;
				}
				Product product = new Product();
				product.setProductId("SP"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
				product.setProductName(txtProNameAdd.getText());
				product.setProductUnit(txtProUnitAdd.getText());
				product.setProductInPrice(Double.parseDouble(txtProInPriceAdd.getText()));
				product.setProductOutPrice(Double.parseDouble(txtProOutPriceAdd.getText()));
				product.setProductRemark(txtaProRemarkAdd.getText());
				try {
					DAOFactory.getIProductDAOInstance().doCreate(product);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "添加成功！");
				txtProNameAdd.setText(null);
				txtProUnitAdd.setText(null);
				txtProInPriceAdd.setText(null);
				txtProOutPriceAdd.setText(null);
				txtaProRemarkAdd.setText(null);
				txtProNameAdd.requestFocusInWindow();
				//刷新数据
				initComboBox();
			}
		});
		
		JButton btnProClearAdd = new JButton("清空");
		btnProClearAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtProNameAdd.setText(null);
				txtProUnitAdd.setText(null);
				txtProInPriceAdd.setText(null);
				txtProOutPriceAdd.setText(null);
				txtaProRemarkAdd.setText(null);
				txtProNameAdd.requestFocusInWindow();
			}
		});
		GroupLayout gl_addProductPanel = new GroupLayout(addProductPanel);
		gl_addProductPanel.setHorizontalGroup(
			gl_addProductPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addProductPanel.createSequentialGroup()
					.addGap(98)
					.addComponent(btnProAddAdd)
					.addPreferredGap(ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
					.addComponent(btnProClearAdd)
					.addGap(93))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
		);
		gl_addProductPanel.setVerticalGroup(
			gl_addProductPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addProductPanel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_addProductPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnProAddAdd)
						.addComponent(btnProClearAdd))
					.addContainerGap())
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(2)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
		);
		
		txtaProRemarkAdd = new JTextArea();
		txtaProRemarkAdd.setLineWrap(true);
		txtaProRemarkAdd.setColumns(33);
		scrollPane.setViewportView(txtaProRemarkAdd);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblProNameAdd = new JLabel("商品名称：");
		JLabel lblProUnitAdd = new JLabel("计量单位：");
		JLabel lblProInPriceAdd = new JLabel("入库价格：");
		
		txtProNameAdd = new JTextField();
		txtProNameAdd.setColumns(10);
		
		txtProUnitAdd = new JTextField();
		txtProUnitAdd.setText("");
		txtProUnitAdd.setColumns(10);
		
		txtProInPriceAdd = new JTextField();
		txtProInPriceAdd.setText("");
		txtProInPriceAdd.setColumns(10);
		
		JLabel lblProOutPriceAdd = new JLabel("销售价格：");
		
		txtProOutPriceAdd = new JTextField();
		txtProOutPriceAdd.setText("");
		txtProOutPriceAdd.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblProInPriceAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtProInPriceAdd, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblProOutPriceAdd)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtProOutPriceAdd, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblProUnitAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtProUnitAdd, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblProNameAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtProNameAdd, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProNameAdd)
						.addComponent(txtProNameAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProUnitAdd)
						.addComponent(txtProUnitAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProInPriceAdd)
						.addComponent(txtProInPriceAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtProOutPriceAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProOutPriceAdd))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		addProductPanel.setLayout(gl_addProductPanel);
		
		JPanel modifyProductPanel = new JPanel();
		tabbedPane.addTab("更改/删除信息", null, modifyProductPanel, null);
		
		JButton btnProModifyModify = new JButton("更改");
		btnProModifyModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboProSelectModify.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择需要更改的商品!");
					return;
				}
				try{
					Double.valueOf(txtProInPriceModify.getText());
					Double.valueOf(txtProOutPriceModify.getText());
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "价格必须输入数字！");
					return;
				}
				Product product = new Product();
				product.setProductId(
						cboProSelectModify.getSelectedItem().toString().substring(0, 16));
				product.setProductName(txtProNameModify.getText());
				product.setProductUnit(txtProUnitModify.getText());
				product.setProductInPrice(Double.parseDouble(txtProInPriceModify.getText()));
				product.setProductOutPrice(Double.parseDouble(txtProOutPriceModify.getText()));
				product.setProductRemark(txtaProRemarkModify.getText());
				try {
					DAOFactory.getIProductDAOInstance().doUpdate(product);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "更新成功！");
				
				return;
			}
		});
		
		JButton btnProDeleteModify = new JButton("删除");
		btnProDeleteModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboProSelectModify.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择需要删除的商品!");
					return;
				}
				int select = JOptionPane.showConfirmDialog(null, "你确定要删除么？", "提示", 
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				boolean flag = false;
				if(select == JOptionPane.YES_OPTION) {
					try {
						flag = DAOFactory.getIProductDAOInstance().doDelete(
							cboProSelectModify.getSelectedItem().toString().substring(0, 16));
						}catch(Exception ex) {
					ex.printStackTrace();
					}
				}else{
					return;
				}
				if(flag){
					JOptionPane.showMessageDialog(null, "删除成功！");
					initComboBox();
					cboProSelectModify.setSelectedIndex(0);
					doProductSelectAction();
					
					return;
				}else{
					JOptionPane.showMessageDialog(null, "删除失败！");
					return;
				}
			}
		});
		
		JPanel panel_5 = new JPanel();
		
		JLabel lblProNameModify = new JLabel("商品名称：");
		
		txtProNameModify = new JTextField();
		txtProNameModify.setEditable(false);
		txtProNameModify.setColumns(10);
		
		JLabel lblProUnitModify = new JLabel("计量单位：");
		
		txtProUnitModify = new JTextField();
		txtProUnitModify.setText("");
		txtProUnitModify.setColumns(10);
		
		JLabel lblProInPriceModify = new JLabel("入库价格：");
		
		txtProInPriceModify = new JTextField();
		txtProInPriceModify.setText("");
		txtProInPriceModify.setColumns(10);
		
		JLabel lblProOutPriceModify = new JLabel("销售价格：");
		
		txtProOutPriceModify = new JTextField();
		txtProOutPriceModify.setText("");
		txtProOutPriceModify.setColumns(10);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(lblProInPriceModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtProInPriceModify, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblProOutPriceModify)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtProOutPriceModify, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(lblProUnitModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtProUnitModify, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(lblProNameModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtProNameModify, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProNameModify)
						.addComponent(txtProNameModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProUnitModify)
						.addComponent(txtProUnitModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProInPriceModify)
						.addComponent(txtProInPriceModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtProOutPriceModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProOutPriceModify))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "备注信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
		);
		
		txtaProRemarkModify = new JTextArea();
		txtaProRemarkModify.setLineWrap(true);
		txtaProRemarkModify.setColumns(33);
		scrollPane_1.setViewportView(txtaProRemarkModify);
		panel_6.setLayout(gl_panel_6);
		
		JLabel lblProSelectModify = new JLabel("选择商品：");
		
		cboProSelectModify = new JComboBox();
		initComboBox();
		cboProSelectModify.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){ 
					if(cboProSelectModify.getSelectedIndex()==0){
					txtProNameModify.setText(null);
					txtProUnitModify.setText(null);
					txtProInPriceModify.setText(null);
					txtProOutPriceModify.setText(null);
					txtaProRemarkModify.setText(null);
					return;
				}
				doProductSelectAction();
				}else{
					return;
				}
				}
			});
		GroupLayout gl_modifyProductPanel = new GroupLayout(modifyProductPanel);
		gl_modifyProductPanel.setHorizontalGroup(
			gl_modifyProductPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_modifyProductPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblProSelectModify)
					.addGap(18)
					.addComponent(cboProSelectModify, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
					.addComponent(btnProModifyModify)
					.addGap(18)
					.addComponent(btnProDeleteModify)
					.addGap(44))
				.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
				.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
		);
		gl_modifyProductPanel.setVerticalGroup(
			gl_modifyProductPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modifyProductPanel.createSequentialGroup()
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_modifyProductPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnProDeleteModify)
						.addComponent(btnProModifyModify)
						.addComponent(lblProSelectModify)
						.addComponent(cboProSelectModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		modifyProductPanel.setLayout(gl_modifyProductPanel);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
	}
	
	//根据所选item 进行操作
	private void doProductSelectAction() {
		String getid="";
		Product product = new Product();
		if (cboProSelectModify.getSelectedIndex()==0) {
			return;
		}
		try {
			getid =(cboProSelectModify.getSelectedItem()).toString().substring(0, 16);
			product = DAOFactory.getIProductDAOInstance().findById(getid);
			txtProNameModify.setText(product.getProductName());
			txtProUnitModify.setText(product.getProductUnit());
			txtProInPriceModify.setText(product.getProductInPrice()+"");
			txtProOutPriceModify.setText(product.getProductOutPrice()+"");
			txtaProRemarkModify.setText(product.getProductRemark());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//重新排序 实时更新
	public void initComboBox(){
		cboProSelectModify.removeAllItems();
		cboProSelectModify.addItem("(请选择)");
		try {
			List<Product> all = DAOFactory.getIProductDAOInstance().findAll("");
			for (int i=0; i<all.size();i++) {
				cboProSelectModify.addItem(all.get(i).getProductId()+
						"("+all.get(i).getProductName()+")");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
}
