package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class SurpriseEgg extends JFrame {

	private JPanel contentPane;
	private Icon icon1;
	private Icon icon2;
	private Icon icon3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SurpriseEgg frame = new SurpriseEgg();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SurpriseEgg() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SurpriseEgg.class.getResource("/icon/comment.png")));
		setForeground(new Color(204, 255, 255));
		setTitle("\u611F\u8C22\u4F7F\u7528PIUPIU");
		setBounds(100, 100, 652, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QIU");
		lblNewLabel.setIcon(new ImageIcon(SurpriseEgg.class.getResource("/icon/user.png")));
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 40));
		lblNewLabel.setBounds(453, 223, 102, 47);
		contentPane.add(lblNewLabel);
		
		JLabel leepic = new JLabel("");
		leepic.setIcon(new ImageIcon(SurpriseEgg.class.getResource("/icon/icon3.jpg")));
		leepic.setBounds(233, 276, 226, 182);
		contentPane.add(leepic);
		
		JLabel lblNewLabel_2 = new JLabel("LEE");
		lblNewLabel_2.setIcon(new ImageIcon(SurpriseEgg.class.getResource("/icon/UI.png")));
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 40));
		lblNewLabel_2.setBounds(264, 223, 102, 47);
		contentPane.add(lblNewLabel_2);
		
		JLabel baopic = new JLabel("");
		baopic.setHorizontalAlignment(SwingConstants.CENTER);
		baopic.setIcon(new ImageIcon(SurpriseEgg.class.getResource("/icon/icon2.png")));
		
		baopic.setBounds(10, 34, 203, 161);
		contentPane.add(baopic);
		
		JLabel qiupic = new JLabel("");
		qiupic.setIcon(new ImageIcon(SurpriseEgg.class.getResource("/icon/icon1.png")));
		qiupic.setBounds(405, 34, 203, 161);
		contentPane.add(qiupic);
		
		JLabel lblNewLabel_2_1 = new JLabel("BAO");
		lblNewLabel_2_1.setIcon(new ImageIcon(SurpriseEgg.class.getResource("/icon/driver.png")));
		lblNewLabel_2_1.setFont(new Font("宋体", Font.BOLD, 40));
		lblNewLabel_2_1.setBounds(63, 223, 109, 47);
		contentPane.add(lblNewLabel_2_1);
		
		JButton back = new JButton("\u8FD4\u56DE");
		back.setIcon(new ImageIcon(SurpriseEgg.class.getResource("/icon/back.png")));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backtoaccount(e);
			}
		});
		back.setFont(new Font("宋体", Font.BOLD, 30));
		back.setBounds(487, 414, 141, 44);
		contentPane.add(back);
		
		
		
	}

	private void backtoaccount(ActionEvent e) {
		this.setVisible(false);	
	}

}
