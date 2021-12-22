package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import Entity.Driver2;
import Entity.User;
import util.StringUtil;

public class UserUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField pickup;
	private JTextField download;
	private JButton call;
	private String userid;
	private String username;
	private JTextField peoplecount;
	private Driver2 driver_input=new Driver2();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					
//					UserUI frame = new UserUI();
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
	public UserUI(User userinput) {
		userid=userinput.getRk();
		username=userinput.getUn();
		
		
		{System.out.println(username);}	
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserUI.class.getResource("/icon/ID.png")));
		setTitle("\u6B22\u8FCE\u4F7F\u7528PIUPIU\u6253\u8F66\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 473);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(240, 240, 240));
		setJMenuBar(menuBar);
		
		JMenu mydeal = new JMenu("\u6211\u7684");
		mydeal.setIcon(new ImageIcon(UserUI.class.getResource("/icon/usersmall .png")));
		menuBar.add(mydeal);
		
		JMenuItem changeinfo = new JMenuItem("修改信息");
		changeinfo.setIcon(new ImageIcon(UserUI.class.getResource("/icon/myinfosmall.png")));
		changeinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TurnTochangeMyinfo(e,userinput);
			}
		});
		
		JMenuItem myinfo = new JMenuItem("我的信息");
		myinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TurnToMyinfo(e,userinput);
			}
		});
		myinfo.setIcon(new ImageIcon(UserUI.class.getResource("/icon/usersmall .png")));
		mydeal.add(myinfo);
		mydeal.add(changeinfo);
		
		JMenuItem RecentTrip = new JMenuItem("\u8FD1\u671F\u51FA\u884C");
		RecentTrip.setIcon(new ImageIcon(UserUI.class.getResource("/icon/dealsmall .png")));
		RecentTrip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TurnToMytrip(e,userinput);
			}
		});
		mydeal.add(RecentTrip);
		
		JMenuItem MyCommit = new JMenuItem("支付订单");
		MyCommit.setIcon(new ImageIcon(UserUI.class.getResource("/icon/commentsmall.png")));
		MyCommit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TurnToMycomment(e,userinput);
			}
		});
		mydeal.add(MyCommit);
		
		JMenu AboutUS = new JMenu("\u5173\u4E8E\u6211\u4EEC");
		AboutUS.setIcon(new ImageIcon(UserUI.class.getResource("/icon/aboutussmall.png")));
		menuBar.add(AboutUS);
		
		JMenuItem egg = new JMenuItem("\u5F69\u86CB");
		egg.setIcon(new ImageIcon(UserUI.class.getResource("/icon/eggsmall.png")));
		egg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eggperformed(e);
			}
		});
		AboutUS.add(egg);
		
		JMenu back = new JMenu("\u8FD4\u56DE");
		back.setIcon(new ImageIcon(UserUI.class.getResource("/icon/backsmall1.png")));
		menuBar.add(back);
		
		JMenuItem BackToLogin = new JMenuItem("\u8FD4\u56DE\u767B\u5F55\u754C\u9762");
		BackToLogin.setIcon(new ImageIcon(UserUI.class.getResource("/icon/backsmall2.png")));
		BackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backtologin(e);
			}
		});
		back.add(BackToLogin);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel adj = new JLabel("\u5C0A\u656C\u7684");
		adj.setFont(new Font("����", Font.BOLD, 30));
		adj.setBounds(10, 10, 107, 54);
		contentPane.add(adj);
		
		
		call = new JButton("\u786E\u8BA4\u6253\u8F66");
		call.setIcon(new ImageIcon(UserUI.class.getResource("/icon/find.png")));
		call.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallCar(e,userinput);
			}
		});
		call.setFont(new Font("����", Font.BOLD, 30));
		call.setBounds(137, 328, 344, 70);
		contentPane.add(call);
		
		JLabel upLabel = new JLabel("\u4E0A\u8F66\u5730\u70B9");
		upLabel.setIcon(new ImageIcon(UserUI.class.getResource("/icon/location.png")));
		upLabel.setFont(new Font("����", Font.BOLD, 30));
		upLabel.setBounds(112, 78, 160, 54);
		contentPane.add(upLabel);
		
		pickup = new JTextField();
		pickup.setHorizontalAlignment(SwingConstants.CENTER);
		pickup.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 30));
		pickup.setBounds(282, 77, 199, 43);
		contentPane.add(pickup);
		pickup.setColumns(10);
		
		JLabel downLabel = new JLabel("\u4E0B\u8F66\u5730\u70B9");
		downLabel.setIcon(new ImageIcon(UserUI.class.getResource("/icon/location.png")));
		downLabel.setFont(new Font("����", Font.BOLD, 30));
		downLabel.setBounds(112, 153, 160, 54);
		contentPane.add(downLabel);
		
		download = new JTextField();
		download.setHorizontalAlignment(SwingConstants.CENTER);
		download.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 30));
		download.setColumns(10);
		download.setBounds(282, 164, 199, 43);
		contentPane.add(download);
		
		JLabel usernametxt = new JLabel(username);
		usernametxt.setFont(new Font("Dialog", Font.BOLD, 30));
		usernametxt.setBounds(110, 10, 107, 54);
		contentPane.add(usernametxt);
		
		JLabel peoplenum = new JLabel("乘客人数");
		peoplenum.setIcon(new ImageIcon(UserUI.class.getResource("/icon/aboutus.png")));
		peoplenum.setFont(new Font("Dialog", Font.BOLD, 30));
		peoplenum.setBounds(112, 232, 160, 54);
		contentPane.add(peoplenum);
		
		peoplecount = new JTextField();
		peoplecount.setHorizontalAlignment(SwingConstants.CENTER);
		peoplecount.setFont(new Font("Dialog", Font.BOLD, 30));
		peoplecount.setColumns(10);
		peoplecount.setBounds(282, 243, 199, 43);
		contentPane.add(peoplecount);
		
	}
	/**
	 * 切换到评价界面
	 * @param e
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
	
	
	
	
	private void TurnToMycomment(ActionEvent e,User user) {
		String rowkey=user.getRk();
				
		try {
			if(user.is_have_score(rowkey)) {
			this.setVisible(true);
			usercomment frame = new usercomment(user);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null,"无未支付订单");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * 切换到历史出行界面
	 * @param e
	 */
	private void TurnToMytrip(ActionEvent e,User user1) {
		
		this.setVisible(true);
		usertrip frame = new usertrip(user1);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * 切换到个人信息界面
	 * @param e
	 */
	private void TurnToMyinfo(ActionEvent evt,User user1) {
		
		/**
		 * 传入user 实体
		 */
		
		this.setVisible(true);
		usershowinfo frame = new usershowinfo(user1);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	/**
	 * change info
	 * @param evt
	 * @param user
	 */
	
	private void TurnTochangeMyinfo(ActionEvent evt,User user) {
		
		this.setVisible(true);
		userchangeinfo frame = new userchangeinfo(user);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

	/**
	 * 打车事件触发
	 * @param e
	 */
	private void CallCar(ActionEvent e,User user) {
		String up_loc = this.pickup.getText();
		String down_loc = this.download.getText();
		String people_count=this.peoplecount.getText();
		String rowkey1=userid;
		if(StringUtil.isEmpty(up_loc)){
			JOptionPane.showMessageDialog(null,"上车地点不得为空！");
			return;
		}
		if(StringUtil.isEmpty(down_loc)){
			JOptionPane.showMessageDialog(null,"下车地点不得为空！");
			return;
		}
		if(up_loc.equals(down_loc)) {
			JOptionPane.showMessageDialog(null,"请输入不同的上车地点和下车地点");
			return;
		}
		
		call.setText("正在为您呼叫司机.....");
		
		/**
		 * 算法匹配就近司机
		 */
		String rowkey2 = null;
		try {
			rowkey2 = user.dache(rowkey1,up_loc,down_loc,people_count);
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		init();
		Table table = null;
		try {
			table = connection.getTable(TableName.valueOf("trip"));
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
        
        Get get_driver=new Get(Bytes.toBytes(rowkey2));
        Result result_driver = null;
		try {
			result_driver = table.get(get_driver);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        String driver_sex=Bytes.toString(result_driver.getValue("driv_info".getBytes(),"sex".getBytes()));
        String driver_phone=Bytes.toString(result_driver.getValue("driv_info".getBytes(),"phone".getBytes()));
        String driver_name=Bytes.toString(result_driver.getValue("driv_info".getBytes(),"name".getBytes()));
        //if rowkey2==null 需要排队

        
      
        //for (Cell cell:)
        //Driver driver_input=new Driver();
        
        driver_input.setRk(rowkey2);
        
        driver_input.setSex(driver_sex);
        driver_input.setPhone(driver_phone);
        driver_input.setUn(driver_name);
        
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
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
		this.setVisible(true);
		SuccessfullyCallDriver frame = new SuccessfullyCallDriver(driver_input);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		call.setText("打车");
		
	}

	/**
	 * 彩蛋
	 * @param e
	 */
	private void eggperformed(ActionEvent e) {
		this.setVisible(true);
		SurpriseEgg frame = new SurpriseEgg();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	/**
	 * 返回登陆界面
	 * @param evt
	 */
	private void backtologin(ActionEvent evt) {
		dispose();
		main123 frame = new main123();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
    
}
