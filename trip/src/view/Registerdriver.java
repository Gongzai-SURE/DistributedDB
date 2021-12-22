package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import Entity.*;
public class Registerdriver extends JFrame {

	private JPanel contentPane;
	private JTextField nametxt;
	private JTextField passwordtxt;
	private JLabel phone;
	private JTextField phonetxt;
	private JButton ok;
	private JButton reset;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Driver driv=new Driver();
		/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registerdriver frame = new Registerdriver();
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
	public Registerdriver() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registerdriver.class.getResource("/icon/regist.png")));
		setTitle("\u53F8\u673A\u6CE8\u518C");
		setBounds(100, 100, 548, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel name = new JLabel("\u59D3\u540D\uFF1A");
		name.setIcon(new ImageIcon(Registerdriver.class.getResource("/icon/name.png")));
		name.setFont(new Font("����", Font.BOLD, 30));
		name.setBounds(77, 33, 138, 45);
		contentPane.add(name);
		
		nametxt = new JTextField();
		nametxt.setFont(new Font("����", Font.BOLD, 30));
		nametxt.setBounds(198, 33, 197, 45);
		contentPane.add(nametxt);
		nametxt.setColumns(10);
		
		JLabel password = new JLabel("\u5BC6\u7801\uFF1A");
		password.setIcon(new ImageIcon(Registerdriver.class.getResource("/icon/password .png")));
		password.setFont(new Font("����", Font.BOLD, 30));
		password.setBounds(77, 88, 138, 45);
		contentPane.add(password);
		
		passwordtxt = new JTextField();
		passwordtxt.setFont(new Font("����", Font.BOLD, 30));
		passwordtxt.setColumns(10);
		passwordtxt.setBounds(198, 88, 197, 45);
		contentPane.add(passwordtxt);
		
		phone = new JLabel("\u7535\u8BDD\uFF1A");
		phone.setIcon(new ImageIcon(Registerdriver.class.getResource("/icon/phone.png")));
		phone.setFont(new Font("����", Font.BOLD, 30));
		phone.setBounds(77, 143, 138, 45);
		contentPane.add(phone);
		
		phonetxt = new JTextField();
		phonetxt.setFont(new Font("����", Font.BOLD, 30));
		phonetxt.setColumns(10);
		phonetxt.setBounds(198, 143, 197, 45);
		contentPane.add(phonetxt);
		
		JRadioButton malerb = new JRadioButton("  male");
		buttonGroup.add(malerb);
		malerb.setFont(new Font("Bodoni MT", Font.BOLD, 30));
		malerb.setBounds(198, 216, 121, 23);
		contentPane.add(malerb);
		
		JRadioButton femalerb = new JRadioButton("  female");
		buttonGroup.add(femalerb);
		femalerb.setFont(new Font("Bodoni MT", Font.BOLD, 30));
		femalerb.setBounds(313, 216, 149, 23);
		contentPane.add(femalerb);
		
		ok = new JButton("\u786E\u8BA4");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name= nametxt.getText();
				String psw= new String(passwordtxt.getText());
				String sex;
				if(malerb.isSelected()) {
					sex= new String("male");
				}
				else {
					sex= new String("female");
				}
				
				String phone= new String(phonetxt.getText());
				boolean i=false;
				try {
					i=driv.putrecord(name,psw,sex,phone);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (i==true) {
					JOptionPane.showMessageDialog(null,"注册成功！等待管理员审核");
				}
				else {
					JOptionPane.showMessageDialog(null,"用户名已存在");
				}
				
				
			}
		});
		ok.setIcon(new ImageIcon(Registerdriver.class.getResource("/icon/confine.png")));
		ok.setFont(new Font("����", Font.BOLD, 30));
		ok.setBounds(116, 272, 143, 53);
		contentPane.add(ok);
		
		reset = new JButton("\u91CD\u7F6E");
		reset.setIcon(new ImageIcon(Registerdriver.class.getResource("/icon/reset.png")));
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		reset.setFont(new Font("����", Font.BOLD, 30));
		reset.setBounds(303, 272, 138, 53);
		contentPane.add(reset);
		
		
		
		JLabel sex = new JLabel("\u6027\u522B\uFF1A");
		sex.setIcon(new ImageIcon(Registerdriver.class.getResource("/icon/aboutus.png")));
		sex.setFont(new Font("����", Font.BOLD, 30));
		sex.setBounds(77, 204, 138, 45);
		contentPane.add(sex);
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
