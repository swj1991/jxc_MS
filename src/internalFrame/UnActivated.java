package internalFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import com.henu.jxcFrame.JxcMain;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

public class UnActivated extends JFrame {

	private JPanel contentPane;
	
	public UnActivated() {
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("注册成功 等待激活");
		setBounds(100, 100, 487, 345);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		
		JLabel lblUnActBg = new JLabel();
		lblUnActBg.setIcon(new ImageIcon(UnActivated.class.getResource("/image/UnActivated.jpg")));
		int width = UnActivated.this.contentPane.getWidth();
		int height = UnActivated.this.contentPane.getHeight();
		lblUnActBg.setText("<html><body><image width='480' height='320' src=image/loginLogo.jpg'></img></body></html>");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(lblUnActBg, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(lblUnActBg, GroupLayout.PREFERRED_SIZE, 317, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}
}
