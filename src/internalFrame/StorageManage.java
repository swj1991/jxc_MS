package internalFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;


import tableModel.Customer;
import tableModel.Storage;

import com.henu.factory.DAOFactory;
import javax.swing.JScrollPane;

public class StorageManage extends JInternalFrame {
	private JTextField txtStorNameAdd;
	private JTextField txtStorNameModify;
	private JComboBox cboStorSelectModify;
	private JTextArea txtaStorModify;
	private JTextArea txtaStorAddressAdd;

	public StorageManage() {
		setIconifiable(true);
		setClosable(true);
		setTitle("�ֿ����");
		setBounds(56, 24, 228, 251);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
		);
		
		JPanel addStoragePanel = new JPanel();
		tabbedPane.addTab("��Ӳֿ���Ϣ", null, addStoragePanel, null);
		
		JLabel lblStroNameAdd = new JLabel("�ֿ����ƣ�");
		
		txtStorNameAdd = new JTextField();
		txtStorNameAdd.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "�ֿ��ַ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnStorAddAdd = new JButton("���");
		btnStorAddAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtStorNameAdd.getText().trim().length()==0
						|| txtaStorAddressAdd.getText().trim().length()==0
						) {
					JOptionPane.showMessageDialog(StorageManage.this,
							"�����δ��д����Ϣ��", "�û����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Storage storage = new Storage();
				storage.setStorageId("CK"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
				storage.setStorageName(txtStorNameAdd.getText());
				storage.setStorageAddress(txtaStorAddressAdd.getText());
				try {
					DAOFactory.getIStorageDAOInstance().doCreate(storage);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "��ӳɹ���");
				txtStorNameAdd.setText(null);
				txtaStorAddressAdd.setText(null);
				txtStorNameAdd.requestFocusInWindow();
				initComboBox();
		    }
				
		});
		JButton btnStorClearAdd = new JButton("���");
		btnStorClearAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtStorNameAdd.setText(null);
				txtaStorAddressAdd.setText(null);
				txtStorNameAdd.requestFocusInWindow();
			}
		});
		
		GroupLayout gl_addStoragePanel = new GroupLayout(addStoragePanel);
		gl_addStoragePanel.setHorizontalGroup(
			gl_addStoragePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_addStoragePanel.createSequentialGroup()
					.addGap(26)
					.addComponent(btnStorAddAdd)
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addComponent(btnStorClearAdd)
					.addGap(30))
				.addGroup(Alignment.LEADING, gl_addStoragePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_addStoragePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
						.addGroup(gl_addStoragePanel.createSequentialGroup()
							.addComponent(lblStroNameAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtStorNameAdd, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_addStoragePanel.setVerticalGroup(
			gl_addStoragePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addStoragePanel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_addStoragePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStroNameAdd)
						.addComponent(txtStorNameAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_addStoragePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStorAddAdd)
						.addComponent(btnStorClearAdd))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
		);
		
		txtaStorAddressAdd = new JTextArea();
		txtaStorAddressAdd.setLineWrap(true);
		txtaStorAddressAdd.setColumns(13);
		scrollPane.setViewportView(txtaStorAddressAdd);
		panel_2.setLayout(gl_panel_2);
		addStoragePanel.setLayout(gl_addStoragePanel);
		
		JPanel modifyStoragePanel = new JPanel();
		tabbedPane.addTab("����/ɾ����Ϣ", null, modifyStoragePanel, null);
		
		JButton btnStorModifyModify = new JButton("����");
		btnStorModifyModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboStorSelectModify.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "��ѡ����Ҫ���ĵĲֿ�!");
					return;
				}
				Storage storage = new Storage();
				if (txtStorNameModify.getText().trim().length()==0
						|| txtaStorModify.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(StorageManage.this,
							"�����δ��д����Ϣ��", "�û�����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				storage.setStorageId(
						cboStorSelectModify.getSelectedItem().toString().substring(0, 16));
				storage.setStorageName(txtStorNameModify.getText());
				storage.setStorageAddress(txtaStorModify.getText());

				try {
					DAOFactory.getIStorageDAOInstance().doUpdate(storage);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "���³ɹ���");
				return;
			}
		});
		
		JButton btnStorDeleteModify = new JButton("ɾ��");
		btnStorDeleteModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboStorSelectModify.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ���Ĳֿ�!");
					return;
				}
				int select = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ��ô��", "��ʾ", 
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				boolean flag = false;
				if(select == JOptionPane.YES_OPTION) {
					try {
						flag = DAOFactory.getIStorageDAOInstance().doDelete(
								cboStorSelectModify.getSelectedItem().toString().substring(0, 16));
						}catch(Exception ex) {
					ex.printStackTrace();
					}
				}else{
					return;
				}
				if(flag){
					JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
					initComboBox();
					cboStorSelectModify.setSelectedIndex(0);
					doStorageSelectAction();
					
					return;
				}else{
					JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
					return;
				}

				
			}
		});
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "�ֿ��ַ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
		);
		
		txtaStorModify = new JTextArea();
		txtaStorModify.setLineWrap(true);
		txtaStorModify.setColumns(13);
		scrollPane_1.setViewportView(txtaStorModify);
		panel_4.setLayout(gl_panel_4);
		
		JLabel lblStorNameModify = new JLabel("�ֿ����ƣ�");
		
		txtStorNameModify = new JTextField();
		txtStorNameModify.setColumns(10);
		
		JLabel lblStorSelectAdd = new JLabel("ѡ��ֿ⣺");
		
		cboStorSelectModify = new JComboBox();
		initComboBox();
		cboStorSelectModify.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){ 
					if(cboStorSelectModify.getSelectedIndex()==0){
						txtStorNameModify.setText(null);
						txtaStorModify.setText(null);
						}
					doStorageSelectAction();
					}else{
						return;
						}
				}
			});
		GroupLayout gl_modifyStoragePanel = new GroupLayout(modifyStoragePanel);
		gl_modifyStoragePanel.setHorizontalGroup(
			gl_modifyStoragePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_modifyStoragePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_modifyStoragePanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_modifyStoragePanel.createSequentialGroup()
							.addComponent(btnStorModifyModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnStorDeleteModify))
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
						.addGroup(gl_modifyStoragePanel.createSequentialGroup()
							.addComponent(lblStorNameModify)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtStorNameModify, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
						.addGroup(gl_modifyStoragePanel.createSequentialGroup()
							.addComponent(lblStorSelectAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboStorSelectModify, 0, 49, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_modifyStoragePanel.setVerticalGroup(
			gl_modifyStoragePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modifyStoragePanel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_modifyStoragePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStorNameModify)
						.addComponent(txtStorNameModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_modifyStoragePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStorSelectAdd)
						.addComponent(cboStorSelectModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_modifyStoragePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStorModifyModify)
						.addComponent(btnStorDeleteModify))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		modifyStoragePanel.setLayout(gl_modifyStoragePanel);
		getContentPane().setLayout(groupLayout);
		setVisible(true);

	}
	
	private void doStorageSelectAction() {
		String getid="";
		Storage storage = new Storage();
		if (cboStorSelectModify.getSelectedIndex()==0) {
			return;
		}
		try {
			getid =(cboStorSelectModify.getSelectedItem()).toString().substring(0, 16);
			storage = DAOFactory.getIStorageDAOInstance().findById(getid);
			txtStorNameModify.setText(storage.getStorageName());
			txtaStorModify.setText(storage.getStorageAddress());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�������� ʵʱ����
	public void initComboBox(){
		cboStorSelectModify.removeAllItems();
		cboStorSelectModify.addItem("(��ѡ��)");
		try {
			List<Storage> all = DAOFactory.getIStorageDAOInstance().findAll("");
			for (int i=0; i<all.size();i++) {
				cboStorSelectModify.addItem(all.get(i).getStorageId()+
						"("+all.get(i).getStorageName()+")");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
}
