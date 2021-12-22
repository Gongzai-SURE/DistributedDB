package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import Entity.User;
import util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class usershowinfo extends JFrame {

	private JPanel contentPane;
	private JTextField phonetxt;
	private JTextField drivernametxt;
	private JTextField passwordtxt;
	private String username;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					userinfo frame = new userinfo();
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
	public usershowinfo(User user) {
		username=user.getUn();
		String userpsw=user.getPsw();
		String userphone=user.getPhone();
		System.out.println(userphone);
		setIconImage(Toolkit.getDefaultToolkit().getImage(usershowinfo.class.getResource("/icon/usermanage.png")));
		setTitle("\u6211\u7684\u4FE1\u606F");
		setBounds(100, 100, 650, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		phonetxt = new JTextField();
		phonetxt.setHorizontalAlignment(SwingConstants.CENTER);
		phonetxt.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		phonetxt.setColumns(10);
		phonetxt.setBounds(289, 193, 255, 47);
		phonetxt.setText(userphone);
		contentPane.add(phonetxt);
		
		JLabel myConnection = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		myConnection.setHorizontalAlignment(SwingConstants.CENTER);
		myConnection.setFont(new Font("����", Font.BOLD, 30));
		myConnection.setBounds(46, 193, 208, 47);
		contentPane.add(myConnection);
		
		JLabel myname = new JLabel("我的姓名：");
		myname.setHorizontalAlignment(SwingConstants.CENTER);
		myname.setFont(new Font("����", Font.BOLD, 30));
		myname.setBounds(46, 79, 208, 47);
		contentPane.add(myname);
		
		drivernametxt = new JTextField();
		drivernametxt.setHorizontalAlignment(SwingConstants.CENTER);
		drivernametxt.setFont(new Font("����", Font.BOLD, 30));
		drivernametxt.setColumns(10);
		drivernametxt.setBounds(289, 79, 168, 47);
		drivernametxt.setText(username);
		contentPane.add(drivernametxt);
		
		JLabel myPassword = new JLabel("\u6211\u7684\u5BC6\u7801\uFF1A");
		myPassword.setHorizontalAlignment(SwingConstants.CENTER);
		myPassword.setFont(new Font("����", Font.BOLD, 30));
		myPassword.setBounds(46, 136, 208, 47);
		contentPane.add(myPassword);
		
		passwordtxt = new JTextField();
		passwordtxt.setHorizontalAlignment(SwingConstants.CENTER);
		passwordtxt.setFont(new Font("����", Font.BOLD, 30));
		passwordtxt.setColumns(10);
		passwordtxt.setBounds(289, 136, 168, 47);
		passwordtxt.setText(userpsw);
		contentPane.add(passwordtxt);
		
		JButton btnNewButton = new JButton("返回");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();	
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 30));
		btnNewButton.setBounds(173, 276, 188, 63);
		contentPane.add(btnNewButton);
	}
	
	/**
	 * 连接到数据库
	 */
	public static Configuration configuration;
    public static Connection connection;
    public static Admin admin;

    public static void init() {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.rootdir", "hdfs://localhost:9000/hbase");
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    public static void close() {
        try {
            if (admin != null) {
                admin.close();
            }
            if (null != connection) {
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
