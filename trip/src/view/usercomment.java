package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import Entity.*;
public class usercomment extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField commenttxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public Driver driv =new Driver();
	public JRadioButton pay1;
	public JRadioButton pay2;
	public JRadioButton pay3;
	public JRadioButton pay4;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					usercomment frame = new usercomment();
//					frame.setLocationRelativeTo(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public usercomment(User user) {
		driv.rk=user.rk;
		setIconImage(Toolkit.getDefaultToolkit().getImage(usercomment.class.getResource("/icon/commentsmall.png")));
		setTitle("\u8BA2\u5355\u8BC4\u5206");
		setBounds(100, 100, 712, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BC4\u5206(0~5):");
		lblNewLabel.setIcon(new ImageIcon(usercomment.class.getResource("/icon/comment.png")));
		lblNewLabel.setFont(new Font("����", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 415, 210, 52);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10,10, 680, 326);
		//scrollPane.setLayout(null);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBounds(0, 0, 680, 326);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"\u53F8\u673Aid", "\u53F8\u673A\u7535\u8BDD", "\u91D1\u989D", "\u884C\u7A0B\u8DDD\u79BB", "\u4E0B\u8F66\u5730\u70B9", "\u4E0A\u8F66\u5730\u70B9", "\u7C7B\u578B", "\u5F00\u59CB\u65F6\u95F4", "\u7ED3\u675F\u65F6\u95F4"
				}
			));
		table.getColumnModel().getColumn(1).setPreferredWidth(145);
		table.getColumnModel().getColumn(4).setPreferredWidth(105);
		table.getColumnModel().getColumn(5).setPreferredWidth(105);
		table.getColumnModel().getColumn(7).setPreferredWidth(150);
		table.getColumnModel().getColumn(8).setPreferredWidth(150);
		table.setAutoCreateRowSorter(true);
		table.setBounds(0, 0, 852, 326);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		this.fillTableInfoid();
		scrollPane.setViewportView(table);
		
		commenttxt = new JTextField();
		commenttxt.setFont(new Font("����", Font.BOLD, 30));
		commenttxt.setBounds(226, 416, 125, 52);
		contentPane.add(commenttxt);
		commenttxt.setColumns(10);
		
		JButton check = new JButton("\u786E\u5B9A");
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay(e,user);
			}
		});
		check.setIcon(new ImageIcon(usercomment.class.getResource("/icon/confine.png")));
		check.setFont(new Font("����", Font.BOLD, 30));
		check.setBounds(411, 415, 135, 52);
		contentPane.add(check);
		
		JLabel tripcost = new JLabel("订单金额：");
		tripcost.setIcon(new ImageIcon(usercomment.class.getResource("/icon/deal.png")));
		tripcost.setFont(new Font("����", Font.BOLD, 30));
		tripcost.setBounds(10, 353, 210, 52);
		contentPane.add(tripcost);
		
		JLabel tripicostxt = new JLabel(driv.money);
		tripicostxt.setFont(new Font("����", Font.BOLD, 30));
		tripicostxt.setHorizontalAlignment(SwingConstants.CENTER);
		tripicostxt.setBounds(204, 351, 125, 52);
		contentPane.add(tripicostxt);
		
		pay1 = new JRadioButton("支付宝");
		pay1.setSelected(true);
		buttonGroup.add(pay1);
		pay1.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		pay1.setBounds(405, 348, 125, 23);
		contentPane.add(pay1);
		
		pay3 = new JRadioButton("现金");
		buttonGroup.add(pay3);
		pay3.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		pay3.setBounds(542, 348, 125, 23);
		contentPane.add(pay3);
		
		pay2 = new JRadioButton("微信支付");
		buttonGroup.add(pay2);
		pay2.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		pay2.setBounds(405, 383, 125, 23);
		contentPane.add(pay2);
		
		pay4 = new JRadioButton("银联");
		buttonGroup.add(pay4);
		pay4.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		pay4.setBounds(542, 380, 125, 23);
		contentPane.add(pay4);
	}
	private void fillTableInfoid() {
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ArrayList<Vector> sites = null;
		try {
			sites = Driver.getDataTripPay("trip",driv.rk);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//JTable t=new JTable();
		
		for (Vector v:sites) { 
			dtm.addRow(v);
			System.out.println(v);
		}					
	}
	
	public void pay(ActionEvent e,User user) {
		String rowkey1=user.getRk();
		String score=commenttxt.getText();
		String payment_type;
		if(pay1.isSelected()) {
			payment_type="1";
			}
		else if(pay2.isSelected()) {
			payment_type="2";
			}
		else if(pay3.isSelected()) {
			payment_type="3";
			}
		else {
			payment_type="4";
			}
		try {
			user.have_score(rowkey1, payment_type, score);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,"支付成功");
		
		
	}
	
	
}
