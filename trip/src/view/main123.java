package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;
import org.json.JSONObject;

import Entity.Test;
import Entity.User;
import dbutil.con;
import util.StringUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class main123 extends JFrame {

	private JPanel contentPane;
	private JTextField accounttxt;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public static Configuration configuration;
    public static Connection connection;
    public static Admin admin;
	public User user_input=new User();
	private Test user=new Test();
	
	
	
	
	/**
	 * 管理员账号
	 */
	private String ManagerAccount = "admin";
	private String Managerpassword = "123";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		                if ("Nimbus".equals(info.getName())) {
		                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		                    break;
		                }}
					main123 frame = new main123();
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
	public main123() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(main123.class.getResource("/icon/situation.png")));
		setTitle("PIUPIU\u51FA\u884C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u6237:");
		lblNewLabel.setIcon(new ImageIcon(main123.class.getResource("/icon/account.png")));
		lblNewLabel.setFont(new Font("����", Font.BOLD, 40));
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801:");
		lblNewLabel_1.setIcon(new ImageIcon(main123.class.getResource("/icon/password .png")));
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 40));
		
		accounttxt = new JTextField();
		accounttxt.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 30));
		accounttxt.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 30));
		
		JRadioButton userchoice = new JRadioButton("\u7528\u6237");
		userchoice.setHorizontalAlignment(SwingConstants.CENTER);
		buttonGroup.add(userchoice);
		userchoice.setFont(new Font("����", Font.PLAIN, 30));
		
		JRadioButton driverchoice = new JRadioButton("\u53F8\u673A");
		buttonGroup.add(driverchoice);
		driverchoice.setFont(new Font("����", Font.PLAIN, 30));
		
		JRadioButton mangerchoice = new JRadioButton("\u7BA1\u7406\u5458");
		buttonGroup.add(mangerchoice);
		mangerchoice.setFont(new Font("����", Font.PLAIN, 30));
		
		JButton login = new JButton("\u767B\u5F55");
		login.setIcon(new ImageIcon(main123.class.getResource("/icon/login.png")));
		
		
		/**
		 * 登录按钮确认执行动作
		 */
		login.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				if(userchoice.isSelected()) {
					try {
						login(e);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				else if(driverchoice.isSelected()) {
					loginDriverAction(e);
					}
				else {
					loginManagerAction(e);
					}
				
			}
		});
		
		login.setFont(new Font("����", Font.PLAIN, 35));
		/**
		 * 注册按钮执行动作
		 */
		JButton resign = new JButton("\u6CE8\u518C");
		resign.setIcon(new ImageIcon(main123.class.getResource("/icon/regist.png")));
		resign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userchoice.isSelected()) {
					resignUserAction(e);
					}
				else if(driverchoice.isSelected()) {
					resignDriverAction(e);
					}
				else {
					JOptionPane.showMessageDialog(null,"身份选择不得为管理员或空");
					return;
				}
			}
		});
		resign.setFont(new Font("����", Font.PLAIN, 35));
		
		/**
		 * 重置按钮处理动作
		 */
		JButton reset = new JButton("\u91CD\u7F6E");
		reset.setIcon(new ImageIcon(main123.class.getResource("/icon/reset.png")));
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		reset.setFont(new Font("����", Font.PLAIN, 35));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(137)
							.addComponent(userchoice)
							.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(login, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
									.addComponent(resign, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
									.addGap(28))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(53)
									.addComponent(driverchoice, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(reset, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
								.addComponent(mangerchoice, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(accounttxt, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))))
					.addGap(67))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(122)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(accounttxt)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userchoice)
						.addComponent(mangerchoice, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(driverchoice, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(login)
						.addComponent(reset, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(resign, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addGap(107))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * 司机注册事件处理
	 * @param e
	 */
	
	private void resignDriverAction(ActionEvent e) {
		this.setVisible(true);
		Registerdriver frame = new Registerdriver();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	/**
	 * 用户注册处理事件
	 * @param e
	 */
	private void resignUserAction(ActionEvent e) {
		this.setVisible(true);
		Registeruser frame = new Registeruser();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * 管理员登陆事件处理
	 * @param e
	 */
	private void loginManagerAction(ActionEvent e) {
		String account = this.accounttxt.getText();
		String password = new String(this.passwordField.getPassword());
		if(StringUtil.isEmpty(account)){
			JOptionPane.showMessageDialog(null,"管理员账户不得为空！");
			return;
		}
		if(StringUtil.isEmpty(password)){
			JOptionPane.showMessageDialog(null,"管理员密码不得为空！");
			return;
		}
		
		{System.out.println(account);
		System.out.println(password);}
		boolean i=(account.equals(ManagerAccount) && password.equals(this.Managerpassword));
		{System.out.println(i);}
		if(i) {
			dispose();
			Managerview frame = new Managerview();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			}
		else {
			JOptionPane.showMessageDialog(null, "管理员账号密码错误！");
		}
	}

	/**
	 * 司机登陆事件处理
	 * @param e
	 */
	private void loginDriverAction(ActionEvent evt) {
		String un = this.accounttxt.getText();
		String psw= new String(this.passwordField.getPassword());
		System.out.println("driver test");
		if(StringUtil.isEmpty(un)){
			JOptionPane.showMessageDialog(null,"用户账户不得为空！");
			return;
		}
		if(StringUtil.isEmpty(psw)){
			JOptionPane.showMessageDialog(null,"用户密码不得为空！");
			return;
		}
		try {
			if(user.login("driv_info",un, psw)) {
				dispose();
				new DriverUI(user.rk,un,psw).setVisible(true);
			}
			else if(user.isDrivPass(un)==1) {
				JOptionPane.showMessageDialog(null,"审核中");
				return;
			}
			else if(user.isDrivPass(un)==2) {
				JOptionPane.showMessageDialog(null,"抱歉，审核未通过");
				return;
			}
			else {
				JOptionPane.showMessageDialog(null,"用户名或密码错误");
				return;
			}
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

	/**
	 * 用户登陆事件处理
	 * @param evt
	 * @throws IOException 
	 */
//	private void loginUserAction(ActionEvent evt) throws IOException {
//		String account = this.accounttxt.getText();
//		String password = new String(this.passwordField.getPassword());
//		
//		if(StringUtil.isEmpty(account)){
//			JOptionPane.showMessageDialog(null,"用户账户不得为空！");
//			return;
//		}
//		if(StringUtil.isEmpty(password)){
//			JOptionPane.showMessageDialog(null,"用户密码不得为空！");
//			return;
//		}
//		try {
//			if (login("pass_info", account, password) != null) {
//				
//				dispose();
//				UserUI frame = new UserUI(user_input);
//				frame.setLocationRelativeTo(null);
//				frame.setVisible(true);
//		}
//			else {
//				JOptionPane.showMessageDialog(null,"用户名或密码错误");
//				return;
//			}
//		}catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}

	/**
	 * 重置事件处理
	 * @param e
	 */
	private void resetActionPerformed(ActionEvent evt) {
		
		this.accounttxt.setText("");
		this.passwordField.setText("");
	}
	
    
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
     * 用户登陆处理
     * @param evt
     * @throws IOException
     */
    public  void login(ActionEvent evt) throws IOException {
    	String un = this.accounttxt.getText();
		String psw = new String(this.passwordField.getPassword());
		if(StringUtil.isEmpty(un)){
			JOptionPane.showMessageDialog(null,"用户账户不得为空！");
			return;
		}
		if(StringUtil.isEmpty(psw)){
			JOptionPane.showMessageDialog(null,"用户密码不得为空！");
			return;
		}
		try {
			if(user.login("pass_info",un, psw)) {
				dispose();
				User new_user=new User();
				new_user.setRk(user.rk);
				String rowkey=user.rk;
				init();
				Table table = connection.getTable(TableName.valueOf("trip"));
		        // 获取用户输入的rowkey
//		        System.out.println("请输入乘客ID");
//		        Scanner scan4 = new Scanner(System.in);
		        Get get = new Get(Bytes.toBytes(rowkey));
		        //Result result = table.get(get);
		        // 获取rowkey
		        //String row = Bytes.toString(result.getRow());
		        //get.addColumn(Bytes.toBytes("pass_info"),Bytes.toBytes("phone"));

		        Result result = table.get(get);
		        
		        String user_phone=Bytes.toString(result.getValue("pass_info".getBytes(),"phone".getBytes()));
                new_user.setPhone(user_phone);
				new_user.setUn(un);
				new_user.setPsw(psw);
				new UserUI(new_user).setVisible(true);
				table.close();
				close();
			}
//			else if(user.isDrivPass(un)==1) {
//				JOptionPane.showMessageDialog(null,"审核中");
//				return;
//			}
//			else if(user.isDrivPass(un)==2) {
//				JOptionPane.showMessageDialog(null,"抱歉，审核未通过");
//				return;
//			}h
			else {
				JOptionPane.showMessageDialog(null,"用户名或密码错误");
				return;
			}
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				
				e1.printStackTrace();
			}
    }
	
	
}
