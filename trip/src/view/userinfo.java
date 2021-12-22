package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class userinfo extends JFrame {

	private JPanel contentPane;
	private JTextField phonetxt;
	private JTextField drivernametxt;
	private JTextField passwordtxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userinfo frame = new userinfo();
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
	public userinfo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(userinfo.class.getResource("/icon/usermanage.png")));
		setTitle("\u6211\u7684\u4FE1\u606F");
		setBounds(100, 100, 650, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel myID = new JLabel("\u6211\u7684ID\u53F7\uFF1A");
		myID.setHorizontalAlignment(SwingConstants.CENTER);
		myID.setFont(new Font("宋体", Font.BOLD, 30));
		myID.setBounds(46, 22, 208, 47);
		contentPane.add(myID);
		
		JLabel myIDtxt = new JLabel("ID");
		myIDtxt.setFont(new Font("宋体", Font.BOLD, 30));
		myIDtxt.setBounds(289, 22, 168, 47);
		contentPane.add(myIDtxt);
		
		phonetxt = new JTextField();
		phonetxt.setColumns(10);
		phonetxt.setBounds(289, 193, 168, 47);
		contentPane.add(phonetxt);
		
		JLabel myConnection = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		myConnection.setHorizontalAlignment(SwingConstants.CENTER);
		myConnection.setFont(new Font("宋体", Font.BOLD, 30));
		myConnection.setBounds(46, 193, 208, 47);
		contentPane.add(myConnection);
		
		JLabel myname = new JLabel("\u53F8\u673A\u59D3\u540D\uFF1A");
		myname.setHorizontalAlignment(SwingConstants.CENTER);
		myname.setFont(new Font("宋体", Font.BOLD, 30));
		myname.setBounds(46, 79, 208, 47);
		contentPane.add(myname);
		
		drivernametxt = new JTextField();
		drivernametxt.setFont(new Font("宋体", Font.BOLD, 30));
		drivernametxt.setColumns(10);
		drivernametxt.setBounds(289, 79, 168, 47);
		contentPane.add(drivernametxt);
		
		JLabel myPassword = new JLabel("\u6211\u7684\u5BC6\u7801\uFF1A");
		myPassword.setHorizontalAlignment(SwingConstants.CENTER);
		myPassword.setFont(new Font("宋体", Font.BOLD, 30));
		myPassword.setBounds(46, 136, 208, 47);
		contentPane.add(myPassword);
		
		passwordtxt = new JTextField();
		passwordtxt.setFont(new Font("宋体", Font.BOLD, 30));
		passwordtxt.setColumns(10);
		passwordtxt.setBounds(289, 136, 168, 47);
		contentPane.add(passwordtxt);
		
		JButton changepassword = new JButton("\u4FEE\u6539\u5BC6\u7801");
		changepassword.setFont(new Font("宋体", Font.BOLD, 30));
		changepassword.setBounds(46, 283, 193, 56);
		contentPane.add(changepassword);
		
		JButton changephone = new JButton("\u66F4\u6362\u7535\u8BDD");
		changephone.setFont(new Font("宋体", Font.BOLD, 30));
		changephone.setBounds(46, 349, 193, 56);
		contentPane.add(changephone);
	}
}
