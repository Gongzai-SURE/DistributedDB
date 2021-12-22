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

import Entity.Driver2;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.io.IOException;

public class SuccessfullyCallDriver extends JFrame {

	private JPanel contentPane;
	
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


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SuccessfullyCallDriver frame = new SuccessfullyCallDriver();
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
	public SuccessfullyCallDriver(Driver2 driver_input) {
		String drivername=driver_input.getUn();
		String driversex=driver_input.getSex();
		String driverphone=driver_input.getPhone();
		setIconImage(Toolkit.getDefaultToolkit().getImage(SuccessfullyCallDriver.class.getResource("/icon/car.png")));
		setTitle("\u53F8\u673A\u5E08\u5085\u6B63\u5728\u8D76\u6765....");
		setBounds(100, 100, 654, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Drivername = new JLabel("司机姓名:");
		Drivername.setHorizontalAlignment(SwingConstants.CENTER);
		Drivername.setIcon(new ImageIcon(SuccessfullyCallDriver.class.getResource("/icon/ID.png")));
		Drivername.setFont(new Font("����", Font.BOLD, 30));
		Drivername.setBounds(21, 37, 214, 65);
		contentPane.add(Drivername);
		
		JLabel Driversex = new JLabel("司机性别:");
		Driversex.setIcon(new ImageIcon(SuccessfullyCallDriver.class.getResource("/icon/name.png")));
		Driversex.setHorizontalAlignment(SwingConstants.CENTER);
		Driversex.setFont(new Font("����", Font.BOLD, 30));
		Driversex.setBounds(21, 112, 214, 65);
		contentPane.add(Driversex);
		
		JLabel connection = new JLabel("\u8054\u7CFB\u65B9\u5F0F:");
		connection.setHorizontalAlignment(SwingConstants.CENTER);
		connection.setIcon(new ImageIcon(SuccessfullyCallDriver.class.getResource("/icon/changephone.png")));
		connection.setFont(new Font("����", Font.BOLD, 30));
		connection.setBounds(21, 187, 214, 65);
		contentPane.add(connection);
		
		JLabel drivernametxt = new JLabel(drivername);
		drivernametxt.setHorizontalAlignment(SwingConstants.CENTER);
		drivernametxt.setFont(new Font("����", Font.BOLD, 30));
		drivernametxt.setBounds(245, 45, 191, 45);
		contentPane.add(drivernametxt);
		
		JLabel driversextxt = new JLabel(driversex);
		driversextxt.setHorizontalAlignment(SwingConstants.CENTER);
		driversextxt.setFont(new Font("����", Font.BOLD, 30));
		driversextxt.setBounds(245, 120, 191, 45);
		contentPane.add(driversextxt);
		
		JLabel driverphonetxt = new JLabel(driverphone);
		driverphonetxt.setHorizontalAlignment(SwingConstants.CENTER);
		driverphonetxt.setFont(new Font("����", Font.BOLD, 30));
		driverphonetxt.setBounds(245, 195, 240, 45);
		contentPane.add(driverphonetxt);
		
		
		
	}
}
