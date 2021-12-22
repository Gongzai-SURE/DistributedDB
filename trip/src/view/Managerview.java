package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.table.DefaultTableModel;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import Entity.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Managerview extends JFrame {

	
	private JPanel contentPane;
	private JTable usertable;
	private JTextField usernametxt;
	private JTextField phonetxt;
	private JTextField useridtxt;
	private JTable drivertable;
	private JTextField drivernametxt;
	private JTextField driverphonetxt;
	private JTextField driveridtxt;
	private JTextField driverlocationtxt;
	private JTextField tripuseridtxt;
	private JTextField tripdriveridtxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public Adm adm=new Adm();
	public DrivTemp driv=new DrivTemp(); 
	private JTextField drivernametxt_1;
	private JTextField driverphonetxt_1;
	private JTextField driveridtxt_1;
	private JTextField driversextxt_1;
	private JTextField driversituationtxt_1;
	private JTable driverinfotable;
	private JTable triptable;
	private JTextField driversextxt_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Managerview frame = new Managerview();
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
	public Managerview() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Managerview.class.getResource("/icon/manager.png")));
		
		
		setTitle("Mr. Supreme Caretaker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 791);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 835, 719);
		contentPane.add(tabbedPane);
		
		JPanel Userpanel = new JPanel();
		tabbedPane.addTab("User Manage", null, Userpanel, null);
		Userpanel.setLayout(null);
		
		JScrollPane userscrollPane = new JScrollPane();
		userscrollPane.setBounds(10,10, 810, 326);
		//userscrollPane.setLayout(null);
		Userpanel.add(userscrollPane);

		usertable = new JTable();
		usertable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mousepressperformance(e);
			}
		});
		
		usertable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7528\u6237id", "\u59D3\u540D", "\u5BC6\u7801", "\u8054\u7CFB\u7535\u8BDD"
			}
		));
		usertable.getColumnModel().getColumn(3).setPreferredWidth(91);
		usertable.setBounds(0,0, 810, 326);
		this.fillTableuser();
		userscrollPane.setViewportView(usertable);
		
		
		JLabel username = new JLabel("\u7528\u6237\u59D3\u540D:");
		username.setIcon(new ImageIcon(Managerview.class.getResource("/icon/name.png")));
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 25));
		username.setBounds(10, 423, 165, 49);
		Userpanel.add(username);
		
		JLabel userid = new JLabel("\u7528\u6237 ID :");
		userid.setHorizontalAlignment(SwingConstants.CENTER);
		userid.setIcon(new ImageIcon(Managerview.class.getResource("/icon/ID.png")));
		userid.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 25));
		userid.setBounds(10, 364, 165, 49);
		Userpanel.add(userid);
		
		JLabel userphone = new JLabel("\u7528\u6237\u7535\u8BDD:");
		userphone.setIcon(new ImageIcon(Managerview.class.getResource("/icon/phone.png")));
		userphone.setHorizontalAlignment(SwingConstants.CENTER);
		userphone.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 25));
		userphone.setBounds(10, 482, 165, 49);
		Userpanel.add(userphone);
		
		usernametxt = new JTextField();
		usernametxt.setHorizontalAlignment(SwingConstants.CENTER);
		usernametxt.setText("\u606D\u4ED4");
		usernametxt.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 26));
		usernametxt.setBounds(198, 423, 139, 49);
		Userpanel.add(usernametxt);
		usernametxt.setColumns(10);
		
		phonetxt = new JTextField();
		phonetxt.setHorizontalAlignment(SwingConstants.CENTER);
		phonetxt.setText("13566822840");
		phonetxt.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 21));
		phonetxt.setColumns(10);
		phonetxt.setBounds(198, 482, 156, 49);
		Userpanel.add(phonetxt);
		
		useridtxt = new JTextField();
		useridtxt.setHorizontalAlignment(SwingConstants.CENTER);
		useridtxt.setText("user01");
		useridtxt.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 26));
		useridtxt.setColumns(10);
		useridtxt.setBounds(198, 364, 139, 44);
		Userpanel.add(useridtxt);
		
		JButton showallusers = new JButton("\u6240\u6709\u7528\u6237");
		showallusers.setIcon(new ImageIcon(Managerview.class.getResource("/icon/usermanage.png")));
		showallusers.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 20));
		showallusers.setBounds(616, 473, 165, 66);
		Userpanel.add(showallusers);
		
		JPanel Driverpanel = new JPanel();
		tabbedPane.addTab("Driver Manage", null, Driverpanel, null);
		Driverpanel.setLayout(null);
		
		JScrollPane driverscrollPane = new JScrollPane();
		driverscrollPane.setBounds(10,10, 810, 326);
//		driverscrollPane.setLayout(null);
		Driverpanel.add(driverscrollPane);
		
//		drivertable.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//				mousepressperformance1(e);
//			}
//		});
		drivertable = new JTable();
		drivertable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mousepressperformance1(e);
			}
		});
		drivertable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u53F8\u673Aid", "\u59D3\u540D", "\u5BC6\u7801", "\u7535\u8BDD", "\u6027\u522B"
			}
		));
		drivertable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		drivertable.setBounds(0, 0, 810, 326);
		driverscrollPane.setViewportView(drivertable);
		
		JLabel drivername = new JLabel("\u53F8\u673A\u59D3\u540D:");
		drivername.setIcon(new ImageIcon(Managerview.class.getResource("/icon/name.png")));
		drivername.setHorizontalAlignment(SwingConstants.CENTER);
		drivername.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 25));
		drivername.setBounds(10, 421, 157, 49);
		Driverpanel.add(drivername);
		
		JLabel driverid = new JLabel("\u53F8\u673A ID :");
		driverid.setIcon(new ImageIcon(Managerview.class.getResource("/icon/ID.png")));
		driverid.setHorizontalAlignment(SwingConstants.CENTER);
		driverid.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 25));
		driverid.setBounds(10, 362, 157, 49);
		Driverpanel.add(driverid);
		
		JLabel driverphone = new JLabel("\u53F8\u673A\u7535\u8BDD:");
		driverphone.setIcon(new ImageIcon(Managerview.class.getResource("/icon/phone.png")));
		driverphone.setHorizontalAlignment(SwingConstants.CENTER);
		driverphone.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 25));
		driverphone.setBounds(10, 480, 157, 49);
		Driverpanel.add(driverphone);
		
		drivernametxt = new JTextField();
		drivernametxt.setHorizontalAlignment(SwingConstants.CENTER);
		drivernametxt.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 26));
		drivernametxt.setColumns(10);
		drivernametxt.setBounds(194, 431, 139, 39);
		Driverpanel.add(drivernametxt);
		
		driverphonetxt = new JTextField();
		driverphonetxt.setHorizontalAlignment(SwingConstants.CENTER);
		driverphonetxt.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 21));
		driverphonetxt.setColumns(10);
		driverphonetxt.setBounds(194, 490, 165, 39);
		Driverpanel.add(driverphonetxt);
		
		driveridtxt = new JTextField();
		driveridtxt.setHorizontalAlignment(SwingConstants.CENTER);
		driveridtxt.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 26));
		driveridtxt.setColumns(10);
		driveridtxt.setBounds(194, 372, 139, 39);
		Driverpanel.add(driveridtxt);
		
		JButton passdriver = new JButton("\u5BA1\u6838\u901A\u8FC7");
		passdriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				driv.rk=driveridtxt.getText();
//				driv.getinfo();
				try {
					adm.pass("trip", driv.rk, driv);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,"审核成功，已通过");
			}
		});
		passdriver.setIcon(new ImageIcon(Managerview.class.getResource("/icon/pass.png")));
		passdriver.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 20));
		passdriver.setBounds(608, 409, 157, 66);
		Driverpanel.add(passdriver);
		
		JLabel driverlocation = new JLabel("司机性别：");
		driverlocation.setIcon(new ImageIcon(Managerview.class.getResource("/icon/location.png")));
		driverlocation.setHorizontalAlignment(SwingConstants.CENTER);
		driverlocation.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 25));
		driverlocation.setBounds(10, 539, 166, 49);
		Driverpanel.add(driverlocation);
		
		driverlocationtxt = new JTextField();
		driverlocationtxt.setHorizontalAlignment(SwingConstants.CENTER);
		driverlocationtxt.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 21));
		driverlocationtxt.setColumns(10);
		driverlocationtxt.setBounds(194, 549, 139, 39);
		Driverpanel.add(driverlocationtxt);
		
		JButton waitforpass = new JButton("\u5F85\u5BA1\u6838");
		waitforpass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTabletemp();
				//driverscrollPane.add(drivertable);
			}
		});
		waitforpass.setIcon(new ImageIcon(Managerview.class.getResource("/icon/waitforpass.png")));
		waitforpass.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 20));
		waitforpass.setBounds(397, 452, 157, 66);
		Driverpanel.add(waitforpass);
		
		/**
		 * 
		 */
		JButton nopassdriver = new JButton("审核未通过");
		nopassdriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				driv.rk=driveridtxt.getText();
				try {
					adm.nopass("trip",driv.rk, driv);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,"审核成功！");
			}
		});
		nopassdriver.setFont(new Font("Dialog", Font.BOLD, 20));
		nopassdriver.setBounds(608, 501, 157, 66);
		Driverpanel.add(nopassdriver);
		
		JPanel Driversearch = new JPanel();
		Driversearch.setLayout(null);
		tabbedPane.addTab("Driver info", null, Driversearch, null);
		
		JScrollPane driverscrollPane_1 = new JScrollPane();
		driverscrollPane_1.setBounds(10, 10, 810, 326);
		Driversearch.add(driverscrollPane_1);
		
		driverinfotable = new JTable();
		driverinfotable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				driverinfotablemousepressperformance(e);
			}
		});
		driverinfotable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u53F8\u673Aid", "\u59D3\u540D", "\u5BC6\u7801", "\u8054\u7CFB\u65B9\u5F0F", "\u8BC4\u5206", "\u8BA2\u5355\u6570", "\u6027\u522B", "\u4F4D\u7F6E/\u7EAC\u5EA6", "\u4F4D\u7F6E/\u7ECF\u5EA6", "\u72B6\u6001"
			}
		));
		driverinfotable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		driverscrollPane_1.setViewportView(driverinfotable);
		
		JLabel drivername_1 = new JLabel("\u53F8\u673A\u59D3\u540D:");
		drivername_1.setIcon(new ImageIcon(Managerview.class.getResource("/icon/name.png")));
		drivername_1.setHorizontalAlignment(SwingConstants.CENTER);
		drivername_1.setFont(new Font("Dialog", Font.BOLD, 25));
		drivername_1.setBounds(10, 421, 157, 49);
		Driversearch.add(drivername_1);
		
		JLabel driverid_1 = new JLabel("\u53F8\u673A ID :");
		driverid_1.setIcon(new ImageIcon(Managerview.class.getResource("/icon/ID.png")));
		driverid_1.setHorizontalAlignment(SwingConstants.CENTER);
		driverid_1.setFont(new Font("Dialog", Font.BOLD, 25));
		driverid_1.setBounds(10, 362, 157, 49);
		Driversearch.add(driverid_1);
		
		JLabel driverphone_1 = new JLabel("\u53F8\u673A\u7535\u8BDD:");
		driverphone_1.setIcon(new ImageIcon(Managerview.class.getResource("/icon/phone.png")));
		driverphone_1.setHorizontalAlignment(SwingConstants.CENTER);
		driverphone_1.setFont(new Font("Dialog", Font.BOLD, 25));
		driverphone_1.setBounds(10, 480, 157, 49);
		Driversearch.add(driverphone_1);
		
		drivernametxt_1 = new JTextField();
		drivernametxt_1.setHorizontalAlignment(SwingConstants.CENTER);
		drivernametxt_1.setFont(new Font("Dialog", Font.BOLD, 26));
		drivernametxt_1.setColumns(10);
		drivernametxt_1.setBounds(194, 431, 139, 39);
		Driversearch.add(drivernametxt_1);
		
		driverphonetxt_1 = new JTextField();
		driverphonetxt_1.setHorizontalAlignment(SwingConstants.CENTER);
		driverphonetxt_1.setFont(new Font("Dialog", Font.BOLD, 21));
		driverphonetxt_1.setColumns(10);
		driverphonetxt_1.setBounds(194, 490, 165, 39);
		Driversearch.add(driverphonetxt_1);
		
		driveridtxt_1 = new JTextField();
		driveridtxt_1.setHorizontalAlignment(SwingConstants.CENTER);
		driveridtxt_1.setFont(new Font("Dialog", Font.BOLD, 26));
		driveridtxt_1.setColumns(10);
		driveridtxt_1.setBounds(194, 372, 139, 39);
		Driversearch.add(driveridtxt_1);
		
		JButton driversearch = new JButton("\u53F8\u673A\u67E5\u8BE2");
		driversearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTabledriv();
//				driverscrollPane.add(driverinfotable);
			}
		});
		driversearch.setIcon(new ImageIcon(Managerview.class.getResource("/icon/find.png")));
		driversearch.setFont(new Font("Dialog", Font.BOLD, 20));
		driversearch.setBounds(423, 362, 157, 66);
		Driversearch.add(driversearch);
		
		JLabel driverlocation_1 = new JLabel("司机位置");
		driverlocation_1.setIcon(new ImageIcon(Managerview.class.getResource("/icon/location.png")));
		driverlocation_1.setHorizontalAlignment(SwingConstants.CENTER);
		driverlocation_1.setFont(new Font("Dialog", Font.BOLD, 25));
		driverlocation_1.setBounds(10, 539, 157, 49);
		Driversearch.add(driverlocation_1);
		
		JLabel driversituation_1 = new JLabel("\u53F8\u673A\u72B6\u6001:");
		driversituation_1.setIcon(new ImageIcon(Managerview.class.getResource("/icon/situation.png")));
		driversituation_1.setHorizontalAlignment(SwingConstants.CENTER);
		driversituation_1.setFont(new Font("Dialog", Font.BOLD, 25));
		driversituation_1.setBounds(10, 597, 157, 49);
		Driversearch.add(driversituation_1);
		
		driversextxt_1 = new JTextField();
		driversextxt_1.setHorizontalAlignment(SwingConstants.CENTER);
		driversextxt_1.setFont(new Font("Dialog", Font.BOLD, 21));
		driversextxt_1.setColumns(10);
		driversextxt_1.setBounds(194, 549, 79, 39);
		Driversearch.add(driversextxt_1);
		
		driversituationtxt_1 = new JTextField();
		driversituationtxt_1.setHorizontalAlignment(SwingConstants.CENTER);
		driversituationtxt_1.setFont(new Font("Dialog", Font.BOLD, 21));
		driversituationtxt_1.setColumns(10);
		driversituationtxt_1.setBounds(194, 604, 139, 39);
		Driversearch.add(driversituationtxt_1);
		
		driversextxt_2 = new JTextField();
		driversextxt_2.setHorizontalAlignment(SwingConstants.CENTER);
		driversextxt_2.setFont(new Font("Dialog", Font.BOLD, 21));
		driversextxt_2.setColumns(10);
		driversextxt_2.setBounds(268, 549, 79, 39);
		Driversearch.add(driversextxt_2);
		
		JPanel Trippanel = new JPanel();
		tabbedPane.addTab("Trip Manage", null, Trippanel, null);
		Trippanel.setLayout(null);
		
		JScrollPane tripscrollPane = new JScrollPane();
		tripscrollPane.setBounds(10,10, 810, 426);
		//tripscrollPane.setLayout(null);
		Trippanel.add(tripscrollPane);
		
		triptable = new JTable();
		triptable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		triptable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u53F8\u673Aid", "\u4E58\u5BA2id", "\u91D1\u989D", "\u652F\u4ED8\u72B6\u6001", "\u8BC4\u5206", "\u4E0A\u8F66\u5730\u70B9", "\u4E0B\u8F66\u5730\u70B9", "\u7C7B\u578B", "\u5F00\u59CB\u65F6\u95F4", "\u7ED3\u675F\u65F6\u95F4"
			}
		));
		triptable.getColumnModel().getColumn(8).setPreferredWidth(150);
		triptable.getColumnModel().getColumn(9).setPreferredWidth(150);
		tripscrollPane.setViewportView(triptable);
		
		JLabel tripuserid = new JLabel("\u7528\u6237 ID :");
		tripuserid.setIcon(new ImageIcon(Managerview.class.getResource("/icon/user.png")));
		tripuserid.setHorizontalAlignment(SwingConstants.CENTER);
		tripuserid.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 17));
		tripuserid.setBounds(500, 471, 125, 49);
		Trippanel.add(tripuserid);
		
		JLabel tripdriverid = new JLabel("\u53F8\u673A ID :");
		tripdriverid.setIcon(new ImageIcon(Managerview.class.getResource("/icon/driver.png")));
		tripdriverid.setHorizontalAlignment(SwingConstants.CENTER);
		tripdriverid.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 17));
		tripdriverid.setBounds(500, 524, 125, 49);
		Trippanel.add(tripdriverid);
		
		tripuseridtxt = new JTextField();
		tripuseridtxt.setHorizontalAlignment(SwingConstants.CENTER);
		tripuseridtxt.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 30));
		tripuseridtxt.setBounds(635, 480, 134, 39);
		Trippanel.add(tripuseridtxt);
		tripuseridtxt.setColumns(10);
		
		tripdriveridtxt = new JTextField();
		tripdriveridtxt.setHorizontalAlignment(SwingConstants.CENTER);
		tripdriveridtxt.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 30));
		tripdriveridtxt.setColumns(10);
		tripdriveridtxt.setBounds(635, 539, 134, 39);
		Trippanel.add(tripdriveridtxt);
		
		JButton findbyid = new JButton("id\u67E5\u627E");
		findbyid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTableInfoid();
//				driverscrollPane.add(triptable);
			}
		});
		findbyid.setIcon(new ImageIcon(Managerview.class.getResource("/icon/find.png")));
		findbyid.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 30));
		findbyid.setBounds(576, 599, 193, 78);
		Trippanel.add(findbyid);
		
		JButton findbytime = new JButton("查找全部");
		findbytime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTableInfoAll() ;
			}
		});
		findbytime.setIcon(new ImageIcon(Managerview.class.getResource("/icon/find.png")));
		findbytime.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 30));
		findbytime.setBounds(105, 503, 212, 78);
		Trippanel.add(findbytime);
		
		JButton back = new JButton("\u70B9\u6211\u8FD4\u56DE\u767B\u9646\u754C\u9762");
		back.setIcon(new ImageIcon(Managerview.class.getResource("/icon/back.png")));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backtologin(e);
			}
		});
		back.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 41));
		tabbedPane.addTab("Back", null, back, null);
		
	}
	private void driverinfotablemousepressperformance(MouseEvent e) {
		// TODO Auto-generated method stub
		int row=driverinfotable.getSelectedRow();
		driveridtxt_1.setText((String)driverinfotable.getValueAt(row, 0));
//		driveridtxt.setText((String)drivertable.getValueAt(row, 0));
		drivernametxt_1.setText((String)driverinfotable.getValueAt(row, 1));
		driverphonetxt_1.setText((String)driverinfotable.getValueAt(row, 3));
		driversextxt_1.setText((String)driverinfotable.getValueAt(row, 7));
		driversextxt_2.setText((String)driverinfotable.getValueAt(row, 8));
		driversituationtxt_1.setText((String)driverinfotable.getValueAt(row, 9));
	}

	/**
	 * 琛ㄦ牸琛岀偣鍑诲鐞嗕簨浠�
	 * @param e
	 */
	private void mousepressperformance(MouseEvent e) {
		int row=usertable.getSelectedRow();
		useridtxt.setText((String)usertable.getValueAt(row, 0));
		usernametxt.setText((String)usertable.getValueAt(row, 1));
		phonetxt.setText((String)usertable.getValueAt(row, 4));
	}
	private void mousepressperformance1(MouseEvent e) {
		int row=drivertable.getSelectedRow();
		driv.un=new String((String) drivertable.getValueAt(row, 1));
		driv.psw=(String)drivertable.getValueAt(row, 2);
		driv.sex=(String)drivertable.getValueAt(row, 4);
		driv.phone=(String)drivertable.getValueAt(row, 3);
		
		driveridtxt.setText((String)drivertable.getValueAt(row, 0));
		drivernametxt.setText((String)drivertable.getValueAt(row, 1));
		driverphonetxt.setText((String)drivertable.getValueAt(row, 3));
		driverlocationtxt.setText((String)drivertable.getValueAt(row, 4));
	}

	private void backtologin(ActionEvent e) {
		dispose();
		main123 frame = new main123();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	//user
	private void fillTableuser() {
		DefaultTableModel dtm=(DefaultTableModel) usertable.getModel();
		dtm.setRowCount(0);
		ArrayList<Vector> sites = null;
		try {
			sites = adm.getDataAdmin2("trip","pass_info");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Vector v:sites) { 
			dtm.addRow(v);
		}							
	}
	//driver
	private void fillTabledriv() {
		DefaultTableModel dtm=(DefaultTableModel) driverinfotable.getModel();
		dtm.setRowCount(0);
		ArrayList<Vector> sites = null;
		try {
			sites = adm.getDataAdmin3("trip","driv_info");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Vector v:sites) { 
			dtm.addRow(v);
		}							
	}
	//审核
	private void fillTabletemp() {
		DefaultTableModel dtm=(DefaultTableModel) drivertable.getModel();
		dtm.setRowCount(0);
		ArrayList<Vector> sites = null;
//		String[] col=new String[] {"id","name","password","phone","sex"};
		try {
			sites = adm.getDataAdmin("trip","temp");
			
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
		
	//trip id
		private void fillTableInfoid() {
			DefaultTableModel dtm=(DefaultTableModel) triptable.getModel();
			dtm.setRowCount(0);
			ArrayList<Vector> sites = null;
//			String[] col=new String[] {"id","name","password","phone","sex"};
			String triprk = null;
			if(!tripuseridtxt.getText().isEmpty()){
				String tripid=tripuseridtxt.getText();
				triprk="pass".concat(tripid);
			}
			else if(!tripdriveridtxt.getText().isEmpty()){
				String tripid=tripdriveridtxt.getText();
				triprk="driv".concat(tripid);
			}
			else {
				JOptionPane.showMessageDialog(null,"司机或乘客id不得全为空");//后续可修改为查询全部
				return;
			}
			try {
				sites = adm.getDataTrip("trip",triprk);
				
				
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
		//trip id
		private void fillTableInfoAll() {
			DefaultTableModel dtm=(DefaultTableModel) triptable.getModel();
			dtm.setRowCount(0);
			ArrayList<Vector> sites = null;
//			String[] col=new String[] {"id","name","password","phone","sex"};
			String triprk = null;

			try {
				sites = adm.getDataTripAll("trip");
				
				
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


}

