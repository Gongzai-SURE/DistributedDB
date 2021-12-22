package Entity;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
//import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
//import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.regex.*;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.util.Scanner;


public class Driver2 {
	public static String rk;//rowkey
    public static String un;//uesrname
    public static String psw;//password
    public static String sex;
    public static String phone;
    public static Configuration configuration;
    public static Connection connection;
    public static Admin admin;
    public Driver2(String rowkey,String username) {
    	rk=new String(rowkey);
    	un=new String(username);
    }
    public static String getRk() {
		return rk;
	}
	public static void setRk(String rk) {
		Driver2.rk = rk;
	}
	public static String getUn() {
		return un;
	}
	public static void setUn(String un) {
		Driver2.un = un;
	}
	public static String getPsw() {
		return psw;
	}
	public static void setPsw(String psw) {
		Driver2.psw = psw;
	}
	public Driver2(String rowkey,String un,String sex,String phone) {
    	rk=new String(rowkey);
    	un=new String(un);
    	sex=new String(sex);
    	phone=new String(phone);
    }

    public static String getSex() {
		return sex;
	}
	public static void setSex(String sex) {
		Driver2.sex = sex;
	}
	public static String getPhone() {
		return phone;
	}
	public static void setPhone(String phone) {
		Driver2.phone = phone;
	}
	public Driver2() {}
  //get 查询订单数据
  	public static void getDataTrip(String tableName,String rowkey,String cf,String cn) throws IOException {
  		init();
  		//获取表对象
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		//创建get对象
  		Get get =new Get(Bytes.toBytes(rowkey));
  		get.addFamily(Bytes.toBytes(cf));
  		
  		//get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
  		
  		//获取数据
  		Result result=table.get(get);
  		
  		double amount=0;
  		//解析result
  		for (Cell cell:result.rawCells()) {
  			//打印数据
  			/*System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
              System.out.println("Timetamp:" + cell.getTimestamp() + " ");
              System.out.println("column Family:" + new String(CellUtil.cloneFamily(cell)) + " ");
              System.out.println("column Name:" + new String(CellUtil.cloneQualifier(cell)) + " ");*/
//              System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
              String data=new String(CellUtil.cloneValue(cell));
              JSONObject jsonObject=new JSONObject(data);
              String money=jsonObject.getString("total_amount");
              String tripType=jsonObject.getString("trip_type");
              String payment=jsonObject.getString("payment_type");
              System.out.println("价格是："+jsonObject.getString("total_amount"));
              System.out.println("乘客人数："+jsonObject.getString("passenger_count"));
              System.out.println("评分是："+jsonObject.getString("score"));
//              System.out.println("价格是："+tripType);
              if(tripType.contentEquals("1")) {
              	System.out.println("短途订单");
              }
              else {
              	System.out.println("长途订单");
              }
              switch(payment.charAt(0)){
              case '1' :
              	System.out.println("支付方式：支付宝"); 
              	break;
              case '2' :
              	System.out.println("支付方式：微信"); 
              	break;
              case '3' :
              	System.out.println("支付方式：现金");      
              	break;
              case '4' :
              	System.out.println("支付方式：银联");
              	break;
             
              default : //可选
              	System.out.println("支付方式：支付宝");   
          }
              
              
              
              try {

              	double value = Double.valueOf(money);
                  amount+=value;
              } catch (NumberFormatException e) {
                  e.printStackTrace();
              }
             
//              System.out.println("年龄："+jsonObject.getString("age"));
//              System.out.println("学到的技能："+jsonObject.getJSONArray("sex"));
  		}
  		amount = (double) Math.round(amount * 100) / 100;
  		System.out.println("总收入是："+amount);
  		table.close();
  		close();
  	}
  	
  	//get 查询订单数据按年
  	public static void getDataTripYear(String tableName,String rowkey,String cf,String year) throws IOException {
  		init();
  		//获取表对象
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		//创建get对象
  		Get get =new Get(Bytes.toBytes(rowkey));
  		get.addFamily(Bytes.toBytes(cf));
  		
  		//get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
  		
  		//获取数据
  		Result result=table.get(get);
  		
  		double amount=0;
  		//解析result
  		for (Cell cell:result.rawCells()) {
  			//打印数据
  			
              String data=new String(CellUtil.cloneValue(cell));
              JSONObject jsonObject=new JSONObject(data);
              String money=jsonObject.getString("total_amount");
              String time=jsonObject.getString("lpep_dropoff_datetime");            
              String tripType=jsonObject.getString("trip_type");
              String payment=jsonObject.getString("payment_type");
              if(reStr(time,year)) {
              	  System.out.println("价格是："+jsonObject.getString("total_amount"));
                    System.out.println("乘客人数："+jsonObject.getString("passenger_count"));
                    System.out.println("评分是："+jsonObject.getString("score"));
//                    System.out.println("价格是："+tripType);
                    if(tripType.contentEquals("1")) {
                    	System.out.println("短途订单");
                    }
                    else {
                    	System.out.println("长途订单");
                    }
                    switch(payment.charAt(0)){
                    case '1' :
                    	System.out.println("支付方式：支付宝"); 
                    	break;
                    case '2' :
                    	System.out.println("支付方式：微信"); 
                    	break;
                    case '3' :
                    	System.out.println("支付方式：现金");      
                    	break;
                    case '4' :
                    	System.out.println("支付方式：银联");
                    	break;
                   
                    default : //可选
                    	System.out.println("支付方式：支付宝");   
                }
                    
                    
                    
                    try {

                    	double value = Double.valueOf(money);
                        amount+=value;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
              }

  		}
  		amount = (double) Math.round(amount * 100) / 100;
  		System.out.println("总收入是："+amount);
  		table.close();
  		close();
  	}					

  	//get 查询订单数据按month
  	public static void getDataTripMonth(String tableName,String rowkey,String cf,String year,String month) throws IOException {
  		init();
  		//获取表对象
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		//创建get对象
  		Get get =new Get(Bytes.toBytes(rowkey));
  		get.addFamily(Bytes.toBytes(cf));
  	
  		//获取数据
  		Result result=table.get(get);
  		
  		double amount=0;
  		//解析result
  		for (Cell cell:result.rawCells()) {
  			//打印数据
  			
              String data=new String(CellUtil.cloneValue(cell));
              System.out.println(data);
              JSONObject jsonObject=new JSONObject(data);
              String money=jsonObject.getString("total_amount");
              String time=jsonObject.getString("lpep_dropoff_datetime");            
              String tripType=jsonObject.getString("trip_type");
              String payment=jsonObject.getString("payment_type");
              if(reStr(time,year+"/"+month)) {
              	  System.out.println("价格是："+jsonObject.getString("total_amount"));
                    System.out.println("乘客人数："+jsonObject.getString("passenger_count"));
                    System.out.println("评分是："+jsonObject.getString("score"));
//                    System.out.println("价格是："+tripType);
                    if(tripType.contentEquals("1")) {
                    	System.out.println("短途订单");
                    }
                    else {
                    	System.out.println("长途订单");
                    }
                    switch(payment.charAt(0)){
                    case '1' :
                    	System.out.println("支付方式：支付宝"); 
                    	break;
                    case '2' :
                    	System.out.println("支付方式：微信"); 
                    	break;
                    case '3' :
                    	System.out.println("支付方式：现金");      
                    	break;
                    case '4' :
                    	System.out.println("支付方式：银联");
                    	break;
                   
                    default : //可选
                    	System.out.println("支付方式：支付宝");   
                }
                    
                    
                    
                    try {

                    	double value = Double.valueOf(money);
                        amount+=value;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
              }

  		}
  		amount = (double) Math.round(amount * 100) / 100;
  		System.out.println("总收入是："+amount);
  		table.close();
  		close();
  	}					
  	
  	public static void getDatainfo(String tableName,String rowkey,String cf,String cn) throws IOException {
  		init();
  		//获取表对象
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		//创建get对象
  		Get get =new Get(Bytes.toBytes(rowkey));
  		get.addFamily(Bytes.toBytes(cf));
  		
  		//get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
  		
  		//获取数据
  		Result result=table.get(get);
  		
  		
  		//解析result
  		for (Cell cell:result.rawCells()) {
  			//打印数据
//  			System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
//              System.out.println("Timetamp:" + cell.getTimestamp() + " ");
//              System.out.println("column Family:" + new String(CellUtil.cloneFamily(cell)) + " ");
              System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
//              System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
              String data=new String(CellUtil.cloneValue(cell));
             
  		}
  		
  		table.close();
  		close();
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
    public static boolean reStr(String content,String year){
        
        String pattern = ".*"+year+".*";
   
        boolean isMatch = Pattern.matches(pattern, content);
        //System.out.println("字符串中是否包含了 '2019' 子字符串? " + isMatch);
        return isMatch;
     }       
    
    
    
  //完成订单
    public void over_trip(String rowkey2) throws IOException{
        init();
        Table table=connection.getTable(TableName.valueOf("trip"));
//        System.out.println("请输入乘客ID");
//        Scanner scan4 = new Scanner(System.in);
//        String rowkey1 = scan4.nextLine();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");//设置日期格式
        String down_t=df.format(new Date());//down_time
        
        Get get=new Get(Bytes.toBytes(rowkey2));

        String driver_name=null,driver_phone=null,user_name=null,user_phone=null;
        String pass_id=null,up_time=null,up_loc=null,up_longitude=null,up_latitude=null,down_loc=null,down_longitude=null,down_latitude=null,people_count=null,distance=null,driver_id=null;
        get.addFamily(Bytes.toBytes("trip_info"));
        Result result = table.get(get);
        String cn=null;
        //遍历每个trip 找到state为1的订单
        for (Cell cell:result.rawCells()){
            String data=new String(CellUtil.cloneValue(cell));
            JSONObject jsonObject=new JSONObject(data);
            String state=jsonObject.getString("state");
            if(state.equals("1")){
            cn=new String(CellUtil.cloneQualifier(cell));
            pass_id=jsonObject.getString("VendorID");
            user_name=jsonObject.getString("pass_name");
            user_phone=jsonObject.getString("pass_phone");
            up_time=jsonObject.getString("lpep_pickup_datetime");
            up_loc=jsonObject.getString("PUSite");
            up_longitude=jsonObject.getString("PULongitude");
            up_latitude=jsonObject.getString("PULatitude");
            down_loc=jsonObject.getString("DOStie");
            down_longitude=jsonObject.getString("DOLongitude");
            down_latitude=jsonObject.getString("DOLatitude");
            people_count=jsonObject.getString("passenger_count");
            distance=jsonObject.getString("trip_distance");
            driver_id=jsonObject.getString("Driver_ID");
            driver_name=jsonObject.getString("driv_name");
            driver_phone=jsonObject.getString("driv_phone");
            }

        }
        Float num_distance=Float.valueOf(distance);
        Float total_amount=num_distance*5/10;
        String trip_type=null;
        if(num_distance<50){
            trip_type="1";
        }
        else trip_type="0";
        String value="{\"VendorID\":\""+pass_id+"\",\"pass_name\":\""+user_name+"\",\"pass_phone\":\""+user_phone+"\",\"lpep_pickup_datetime\":\""+up_time+"\",\"lpep_dropoff_datetime\":\""+down_t+"\",\"state\":\"0\",\"PUSite\":\""+up_loc+"\",\"PULongitude\":\""+up_longitude+"\",\"PULatitude\":\""+up_latitude+"\",\"DOStie\":\""+down_loc+"\",\"DOLongitude\":\""+down_longitude+"\",\"DOLatitude\":\""+down_latitude+"\",\"passenger_count\":\""+people_count+"\",\"trip_distance\":\""+distance+"\",\"total_amount\":\""+total_amount+"\",\"trip_type\":\""+trip_type+"\",\"Driver_ID\":\""+driver_id+"\",\"driv_name\":\""+driver_name+"\",\"driv_phone\":\""+driver_phone+"\",\"pay_state\":\""+"N"+"\"}";  
        String rowkey1="pass"+pass_id;
        Put put1=new Put(Bytes.toBytes(rowkey1));
        put1.addColumn("trip_info".getBytes(), cn.getBytes(), Bytes.toBytes(value));
        Put put2=new Put(Bytes.toBytes(rowkey2));
        put2.addColumn("trip_info".getBytes(), cn.getBytes(), Bytes.toBytes(value));
        Put put3=new Put(Bytes.toBytes(rowkey2));
        put3.addColumn("state".getBytes(), "state".getBytes(), Bytes.toBytes("N"));
        table.put(put1);
        table.put(put2);
        table.put(put3);
        System.out.println("over");
        table.close();
        close();

    }
    
    //确认订单获取信息
    public ArrayList<String> getorderdata(String rowkey) throws IOException {
        init();
        Table table = connection.getTable(TableName.valueOf("trip"));
        // 获取用户输入的rowkey
//        System.out.println("请输入乘客ID");
//        Scanner scan4 = new Scanner(System.in);
//        String rowkey = scan4.nextLine();
        Get get = new Get(Bytes.toBytes(rowkey));
        //Result result = table.get(get);
        // 获取rowkey
        //String row = Bytes.toString(result.getRow());
        get.addColumn(Bytes.toBytes("trip_info"),Bytes.toBytes("trip36"));

        Result result = table.get(get);
        
        
        // 指定列族以及列打印列当中的数据出来
//        double amount=0;
		//解析result
        ArrayList<String> sites = new ArrayList<String>();
		for (Cell cell:result.rawCells()) {
            String data=new String(CellUtil.cloneValue(cell));
            JSONObject jsonObject=new JSONObject(data);
//            String money=jsonObject.getString("total_amount");
//            String tripType=jsonObject.getString("trip_type");
//            String payment=jsonObject.getString("payment_type");
//            String driver=jsonObject.getString("Driver_ID");
            //ArrayList<String> sites = new ArrayList<String>();
            String up_loc=jsonObject.getString("PUSite");
            sites.add(up_loc);
            String down_loc=jsonObject.getString("DOStie");
            sites.add(down_loc);
            String pass_name=jsonObject.getString("pass_name");
            sites.add(pass_name);
            String pass_phone=jsonObject.getString("pass_phone");
            sites.add(pass_phone);
            String distance=jsonObject.getString("trip_distance");
            Float num_distance=Float.valueOf(distance);
            Float total_amount=num_distance*50/100;
            String total_str=String.valueOf(total_amount);
            sites.add(total_str);
            sites.add(distance);
            //System.out.println("价格是："+jsonObject.getString("total_amount"));
//            System.out.println("乘车人数："+jsonObject.getString("passenger_count"));
//            System.out.println("评分是："+jsonObject.getString("score"));
//            System.out.println("司机编号是："+jsonObject.getString("Driver_ID"));
//            System.out.println("价格是："+tripType);
              
           
//            System.out.println("年龄："+jsonObject.getString("age"));
//            System.out.println("学到的技能："+jsonObject.getJSONArray("sex"));
        }
        table.close();
        close();
        return sites;
    
    }
    
    
    
    public boolean is_over_trip(String rowkey2) throws IOException{
        init();
        Table table=connection.getTable(TableName.valueOf("trip"));
//        System.out.println("请输入乘客ID");
//        Scanner scan4 = new Scanner(System.in);
//        String rowkey1 = scan4.nextLine();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");//设置日期格式
        String down_t=df.format(new Date());//down_time
        
        Get get=new Get(Bytes.toBytes(rowkey2));

        String driver_name=null,driver_phone=null,user_name=null,user_phone=null;
        String pass_id=null,up_time=null,up_loc=null,up_longitude=null,up_latitude=null,down_loc=null,down_longitude=null,down_latitude=null,people_count=null,distance=null,driver_id=null;
        get.addFamily(Bytes.toBytes("trip_info"));
        Result result = table.get(get);
        String cn=null;
        //遍历每个trip 找到state为1的订单
        for (Cell cell:result.rawCells()){
            String data=new String(CellUtil.cloneValue(cell));
            JSONObject jsonObject=new JSONObject(data);
            String state=jsonObject.getString("state");
            if(state.equals("1")){
            cn=new String(CellUtil.cloneQualifier(cell));
            pass_id=jsonObject.getString("VendorID");
            user_name=jsonObject.getString("pass_name");
            user_phone=jsonObject.getString("pass_phone");
            up_time=jsonObject.getString("lpep_pickup_datetime");
            up_loc=jsonObject.getString("PUSite");
            up_longitude=jsonObject.getString("PULongitude");
            up_latitude=jsonObject.getString("PULatitude");
            down_loc=jsonObject.getString("DOStie");
            down_longitude=jsonObject.getString("DOLongitude");
            down_latitude=jsonObject.getString("DOLatitude");
            people_count=jsonObject.getString("passenger_count");
            distance=jsonObject.getString("trip_distance");
            driver_id=jsonObject.getString("Driver_ID");
            driver_name=jsonObject.getString("driv_name");
            driver_phone=jsonObject.getString("driv_phone");
            }

        }
        if (cn.isEmpty()) {
        	return false;
        }
    
        table.close();
        close();
        return true;

    }
    public static void main(String[] args) throws IOException {
        //司机
       // boolean i =isTableExist("trip");
        //查询订单普通
        //getDataTrip("trip","driv1","trip_info","trip1");
        //查询订单按年
        //getDataTripYear("trip","driv1","trip_info","2019");
      //查询订单按月
        //getDataTripMonth("trip","driv1","trip_info","2019","1");
        //司机信息显示
        //getDatainfo("trip","driv1","driv_info","trip1");
        //修改密码
        
        
        //管理员
        //查询个人信息
//        getDataAdmin("trip","driv_info");//加过滤器
        //test-login
        //i=login("xiaoming","12345681");
        
                                                                                                                                                                                           
        //System.out.println(i);

    }

}
