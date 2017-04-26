package com.henu.jxcFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import tableModel.User;
import com.henu.login.Login;
import internalFrame.*;

public class JxcMain extends JFrame {
	private JDesktopPane jxcMainDesktopPane;
	private JFrame jxcMainFrame;
	private JLabel jxcMainbgLabel;
	private JMenuBar menuBar;
	private static User user = Login.getUser();
	
	public JxcMain() {
		jxcMainFrame = new JFrame("企业进销存管理系统");
		jxcMainFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
		jxcMainFrame.addComponentListener(new FrameListener());
		jxcMainFrame.getContentPane().setLayout(new BorderLayout());
		jxcMainFrame.setBounds(0, 0, 800, 600);
		jxcMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jxcMainbgLabel = new JLabel();// 背景标签
		jxcMainbgLabel.setVerticalAlignment(SwingConstants.TOP);
		jxcMainbgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		updateBackImage(); // 更新或初始化背景图片
		jxcMainDesktopPane = new JDesktopPane();
		jxcMainDesktopPane.add(jxcMainbgLabel, new Integer(Integer.MIN_VALUE));
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		//添加Menu
		JMenu menu = new JMenu("基本信息管理");
		menuBar.add(menu);
		
		JMenu menu_5 = new JMenu("顾客管理");
		menu.add(menu_5);
		
		JMenuItem menuItem = new JMenuItem("添加顾客信息");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jxcMainDesktopPane.add(new CustomerManage());
			}
		});
		menu_5.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("更改/删除信息");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jxcMainDesktopPane.add(new CustomerManage());
				//缺少跳转
				
			}
		});
		menu_5.add(menuItem_1);
		
		JMenu menu_6 = new JMenu("供应商管理");
		menu.add(menu_6);
		
		JMenuItem menuItem_2 = new JMenuItem("添加供应商信息");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jxcMainDesktopPane.add(new SupplierManage());
			}
		});
		menu_6.add(menuItem_2);
		
		JMenuItem menuItem_14 = new JMenuItem("更改/删除信息");
		menuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jxcMainDesktopPane.add(new SupplierManage());
				//缺少跳转
			}
		});
		menu_6.add(menuItem_14);
		
		JMenu menu_7 = new JMenu("商品管理");
		menu.add(menu_7);
		
		JMenuItem menuItem_15 = new JMenuItem("添加商品信息");
		menuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jxcMainDesktopPane.add(new ProductManage());
			}
		});
		menu_7.add(menuItem_15);
		
		JMenuItem menuItem_16 = new JMenuItem("更改/删除信息");
		menuItem_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jxcMainDesktopPane.add(new ProductManage());
				//缺少跳转
			}
		});
		menu_7.add(menuItem_16);
		
		JMenu menu_8 = new JMenu("仓库管理");
		menu.add(menu_8);
		
		JMenuItem menuItem_17 = new JMenuItem("添加仓库信息");
		menuItem_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jxcMainDesktopPane.add(new StorageManage());
			}
		});
		menu_8.add(menuItem_17);
		
		JMenuItem menuItem_18 = new JMenuItem("更改/删除信息");
		menuItem_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jxcMainDesktopPane.add(new StorageManage());
				//缺少跳转
			}
		});
		menu_8.add(menuItem_18);
		
		JMenu menu_1 = new JMenu("部门操作管理");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_3 = new JMenuItem("采购管理");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!user.getUserClassId().equals("usc02")) {
					JOptionPane.showMessageDialog(jxcMainFrame, 
							"你没有使用此功能的权限!", "权限提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				jxcMainDesktopPane.add(new StockManage());
			}
		});
		menu_1.add(menuItem_3);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("销售管理");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!user.getUserClassId().equals("usc03")) {
					JOptionPane.showMessageDialog(jxcMainFrame, 
							"你没有使用此功能的权限!", "权限提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				jxcMainDesktopPane.add(new SalesManage());
			}
		});
		menu_1.add(mntmNewMenuItem);
		
		JMenu menu_4 = new JMenu("仓库管理");
		menu_1.add(menu_4);
		
		JMenuItem menuItem_4 = new JMenuItem("入库单");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!user.getUserClassId().equals("usc04")) {
					JOptionPane.showMessageDialog(jxcMainFrame, 
							"你没有使用此功能的权限!", "权限提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				jxcMainDesktopPane.add(new InOrderManage());
			}
		});
		menu_4.add(menuItem_4);
		
		JMenuItem menuItem_11 = new JMenuItem("出库单");
		menuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!user.getUserClassId().equals("usc04")) {
					JOptionPane.showMessageDialog(jxcMainFrame, 
							"你没有使用此功能的权限!", "权限提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				jxcMainDesktopPane.add(new OutOrderManage());
			}
		});
		menu_4.add(menuItem_11);
		
		JMenuItem menuItem_12 = new JMenuItem("库存统计");
		menuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!user.getUserClassId().equals("usc04")) {
					JOptionPane.showMessageDialog(jxcMainFrame, 
							"你没有使用此功能的权限!", "权限提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				jxcMainDesktopPane.add(new InventorySelect());
			}
		});
		menu_4.add(menuItem_12);
		
		JMenu menu_2 = new JMenu("查询统计");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_5 = new JMenuItem("顾客查询");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jxcMainDesktopPane.add(new CustomerSelect());
			}
		});
		menu_2.add(menuItem_5);
		
		JMenuItem menuItem_6 = new JMenuItem("商品查询");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jxcMainDesktopPane.add(new ProductSelect());
			}
		});
		menu_2.add(menuItem_6);
		
		JMenuItem menuItem_7 = new JMenuItem("供应商查询");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jxcMainDesktopPane.add(new SupplierSelect());
			}
		});
		menu_2.add(menuItem_7);
		
		JMenuItem menuItem_8 = new JMenuItem("销售查询");
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jxcMainDesktopPane.add(new SalesSelect());
			}
		});
		menu_2.add(menuItem_8);
		
		JMenuItem menuItem_9 = new JMenuItem("采购查询");
		menuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jxcMainDesktopPane.add(new StockSelect());
			}
		});
		menu_2.add(menuItem_9);
		
		JMenuItem menuItem_13 = new JMenuItem("排行查询");
		menuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jxcMainDesktopPane.add(new SalesRanking());
			}
		});
		menu_2.add(menuItem_13);
		
		JMenu menu_9 = new JMenu("系统管理");
		menuBar.add(menu_9);
		
		JMenuItem menuItem_19 = new JMenuItem("用户管理");
		menuItem_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!user.getUserClassId().equals("usc01")) {
					JOptionPane.showMessageDialog(jxcMainFrame, 
							"你没有使用此功能的权限!", "权限提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				jxcMainDesktopPane.add(new UserManage());
			}
		});
		menu_9.add(menuItem_19);
		
		JMenuItem menuItem_20 = new JMenuItem("密码管理");
		menuItem_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jxcMainDesktopPane.add(new PasswordManage());
			}
		});
		menu_9.add(menuItem_20);
		
		JMenuItem menuItem_21 = new JMenuItem("权限管理");
		menuItem_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!user.getUserClassId().equals("usc01")) {
					JOptionPane.showMessageDialog(jxcMainFrame, 
							"你没有使用此功能的权限!", "权限提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				jxcMainDesktopPane.add(new PowerManage());
			}
		});
		menu_9.add(menuItem_21);
		
		JMenu menu_3 = new JMenu("帮助");
		menuBar.add(menu_3);
		
		JMenuItem menuItem_10 = new JMenuItem("未知");
		menuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menu_3.add(menuItem_10);
		jxcMainFrame.getContentPane().add(jxcMainDesktopPane);
		jxcMainFrame.getContentPane().add(menuBar, BorderLayout.NORTH);
		jxcMainFrame.setVisible(true);
		jxcMainFrame.setLocationRelativeTo(null);
	}
	
	private void updateBackImage() {
		if (jxcMainbgLabel != null) {
			int width = JxcMain.this.jxcMainFrame.getWidth();
			int height = JxcMain.this.jxcMainFrame.getHeight();
			jxcMainbgLabel.setSize(width, height);
			jxcMainbgLabel.setText("<html><body><image width='" + width
					+ "' height='" + height + "' src="
					+ JxcMain.this.getClass().getResource("/image/welcome.jpg")
					+ "'></img></body></html>");
		}
	}
	// 窗体监听器
	private final class FrameListener extends ComponentAdapter {
		public void componentResized(final ComponentEvent e) {
			updateBackImage();
		}
	}

}
