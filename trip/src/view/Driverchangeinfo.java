package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import Entity.Driver;
import Entity.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Driverchangeinfo extends JFrame {

	private JPanel contentPane;
	private JTextField driverconnectiontxt;
	private JTextField drivernametxt;
	private JTextField driverpasswordtxt;
	private String driver_id;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Driverchangeinfo frame = new Driverchangeinfo();
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
	public Driverchangeinfo(Driver driv) {
		driver_id=driv.rk;
		String driver_psw=driv.psw;
		String driver_name=driv.un;
		String driver_phone=driv.phone;
		setIconImage(Toolkit.getDefaultToolkit().getImage(Driverchangeinfo.class.getResource("/icon/name.png")));
		setTitle("\u6211\u7684\u4FE1\u606F");
		setBounds(100, 100, 678, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		driverconnectiontxt = new JTextField();
		driverconnectiontxt.setFont(new Font("Dialog", Font.BOLD, 30));
		driverconnectiontxt.setColumns(10);
		driverconnectiontxt.setBounds(276, 211, 318, 61);
		driverconnectiontxt.setText(driver_phone);
		contentPane.add(driverconnectiontxt);
		
		JLabel myConnection = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		myConnection.setIcon(new ImageIcon(Driverchangeinfo.class.getResource("/icon/phone.png")));
		myConnection.setHorizontalAlignment(SwingConstants.CENTER);
		myConnection.setFont(new Font("����", Font.BOLD, 30));
		myConnection.setBounds(33, 193, 208, 81);
		contentPane.add(myConnection);
		
		JLabel myname = new JLabel("我的姓名：");
		myname.setIcon(new ImageIcon(Driverchangeinfo.class.getResource("/icon/name.png")));
		myname.setHorizontalAlignment(SwingConstants.CENTER);
		myname.setFont(new Font("����", Font.BOLD, 30));
		myname.setBounds(33, 12, 208, 61);
		contentPane.add(myname);
		
		drivernametxt = new JTextField();
		drivernametxt.setFont(new Font("����", Font.BOLD, 30));
		drivernametxt.setColumns(10);
		drivernametxt.setBounds(276, 19, 181, 54);
		drivernametxt.setText(driver_name);
		contentPane.add(drivernametxt);
		
		JLabel myPassword = new JLabel("\u6211\u7684\u5BC6\u7801\uFF1A");
		myPassword.setIcon(new ImageIcon(Driverchangeinfo.class.getResource("/icon/password .png")));
		myPassword.setHorizontalAlignment(SwingConstants.CENTER);
		myPassword.setFont(new Font("����", Font.BOLD, 30));
		myPassword.setBounds(33, 116, 208, 61);
		contentPane.add(myPassword);
		
		driverpasswordtxt = new JTextField();
		driverpasswordtxt.setFont(new Font("����", Font.BOLD, 30));
		driverpasswordtxt.setColumns(10);
		driverpasswordtxt.setBounds(276, 121, 181, 56);
		driverpasswordtxt.setText(driver_psw);
		contentPane.add(driverpasswordtxt);
		
		JButton changepassword = new JButton("\u4FEE\u6539\u5BC6\u7801");
		changepassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changepsw(e,driv);
			}
		});
		changepassword.setIcon(new ImageIcon(Driverchangeinfo.class.getResource("/icon/fix.png")));
		changepassword.setFont(new Font("����", Font.BOLD, 30));
		changepassword.setBounds(33, 302, 193, 56);
		contentPane.add(changepassword);
		
		JButton changephone = new JButton("\u66F4\u6362\u7535\u8BDD");
		changephone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changephone(e,driv);
			}
		});
		changephone.setIcon(new ImageIcon(Driverchangeinfo.class.getResource("/icon/changephone.png")));
		changephone.setFont(new Font("����", Font.BOLD, 30));
		changephone.setBounds(33, 368, 193, 56);
		contentPane.add(changephone);
	}
	
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
    
	
	
	public void changepsw(ActionEvent e,Driver driver) {
		// 获取表
				init();
		        Table table = null;
				try {
					table = connection.getTable(TableName.valueOf("trip"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        // 获取要输入的信息

		        String new_data = driverpasswordtxt.getText();

		        Get get=new Get(Bytes.toBytes(driver_id));
		        //get.addFamily(Bytes.toBytes("pass_info"));
		        try {
					Result result=table.get(get);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		        Put put=new Put(driver_id.getBytes());
		        put.addColumn("driv_info".getBytes(), "password".getBytes(), Bytes.toBytes(new_data));
		        try {
					table.put(put);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        try {
					table.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        close();
		        JOptionPane.showMessageDialog(null,"修改成功");

	}
	
	public void changephone(ActionEvent e,Driver driver) {
		// 获取表
				init();
		        Table table = null;
				try {
					table = connection.getTable(TableName.valueOf("trip"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        // 获取要输入的信息

		        String new_data = driverconnectiontxt.getText();

		        Get get=new Get(Bytes.toBytes(driver_id));
		        //get.addFamily(Bytes.toBytes("pass_info"));
		        try {
					Result result=table.get(get);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		        Put put=new Put(driver_id.getBytes());
		        put.addColumn("driv_info".getBytes(), "phone".getBytes(), Bytes.toBytes(new_data));
		        try {
					table.put(put);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        try {
					table.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        close();
		        JOptionPane.showMessageDialog(null,"修改成功");

	}
	
	
}
