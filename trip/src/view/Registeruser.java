package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entity.Driver;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Registeruser extends JFrame {

	private JPanel contentPane;
	private JTextField nametxt;
	private JTextField passwordtxt;
	private JTextField phonetxt;
	private Driver driv=new Driver();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registeruser frame = new Registeruser();
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
	public Registeruser() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registeruser.class.getResource("/icon/regist.png")));
		setTitle("\u7528\u6237\u6CE8\u518C");
		setBounds(100, 100, 536, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel name = new JLabel("\u59D3\u540D\uFF1A");
		name.setIcon(new ImageIcon(Registeruser.class.getResource("/icon/name.png")));
		name.setFont(new Font("����", Font.BOLD, 30));
		name.setBounds(78, 35, 138, 45);
		contentPane.add(name);
		
		nametxt = new JTextField();
		nametxt.setFont(new Font("����", Font.BOLD, 30));
		nametxt.setColumns(10);
		nametxt.setBounds(204, 35, 197, 45);
		contentPane.add(nametxt);
		
		JLabel password = new JLabel("\u5BC6\u7801\uFF1A");
		password.setIcon(new ImageIcon(Registeruser.class.getResource("/icon/password .png")));
		password.setFont(new Font("����", Font.BOLD, 30));
		password.setBounds(78, 90, 138, 45);
		contentPane.add(password);
		
		passwordtxt = new JTextField();
		passwordtxt.setFont(new Font("����", Font.BOLD, 30));
		passwordtxt.setColumns(10);
		passwordtxt.setBounds(204, 90, 197, 45);
		contentPane.add(passwordtxt);
		
		JLabel phone = new JLabel("\u7535\u8BDD\uFF1A");
		phone.setIcon(new ImageIcon(Registeruser.class.getResource("/icon/phone.png")));
		phone.setFont(new Font("����", Font.BOLD, 30));
		phone.setBounds(78, 145, 138, 45);
		contentPane.add(phone);
		
		phonetxt = new JTextField();
		phonetxt.setFont(new Font("����", Font.BOLD, 30));
		phonetxt.setColumns(10);
		phonetxt.setBounds(204, 145, 197, 45);
		contentPane.add(phonetxt);
		
		JButton ok = new JButton("\u786E\u8BA4");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name= nametxt.getText();
				String psw= new String(passwordtxt.getText());
				
				boolean i=false;
				String phone= new String(phonetxt.getText());
				try {
					i=driv.putrecorduser(name,psw,phone);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (i==true) {
					JOptionPane.showMessageDialog(null,"注册成功！");
				}
				else {
					JOptionPane.showMessageDialog(null,"用户名已存在");
				}
				
				
			
			}
		});
		ok.setIcon(new ImageIcon(Registeruser.class.getResource("/icon/confine.png")));
		ok.setFont(new Font("����", Font.BOLD, 30));
		ok.setBounds(96, 214, 138, 42);
		contentPane.add(ok);
		
		JButton reset = new JButton("\u91CD\u7F6E");
		reset.setIcon(new ImageIcon(Registeruser.class.getResource("/icon/reset.png")));
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		reset.setFont(new Font("����", Font.BOLD, 30));
		reset.setBounds(306, 214, 138, 42);
		contentPane.add(reset);
	}
	/**
	 * �����¼�����
	 * @param evt
	 */
	private void resetActionPerformed(ActionEvent evt) {
		this.nametxt.setText("");
		this.passwordtxt.setText("");
		this.phonetxt.setText("");
	}

}
