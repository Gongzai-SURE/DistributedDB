package Entity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
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

import dbutil.con;

public class User {
	public static String rk;//rowkey
    public static String un;//uesrname
    public static String psw;//password
    public static String phone;
    public Driver driv =new Driver();
    
    
    public static String getPhone() {
		return phone;
	}
	public static void setPhone(String phone) {
		User.phone = phone;
	}
	public static String getRk() {
		return rk;
	}
	public static void setRk(String rk) {
		User.rk = rk;
	}
	public static String getUn() {
		return un;
	}
	public static void setUn(String un) {
		User.un = un;
	}
	public static String getPsw() {
		return psw;
	}
	public static void setPsw(String psw) {
		User.psw = psw;
	}
	public User(String rowkey,String username,String psw,String phone) {
    	rk=new String(rowkey);
    	un=new String(username);
    	psw=new String(psw);
    	phone=new String(phone);
    	
    }
	
    public User() {}
    
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

    
    
    public String dache(String rowkey1,String up_loc,String down_loc,String people_count) throws IOException{
        init();
        Table table = connection.getTable(TableName.valueOf("trip"));
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");//设置日期格式
        String t=df.format(new Date());//time
        Scan scan5 = new Scan();
        scan5.addFamily("state".getBytes());
        ResultScanner scanner = table.getScanner(scan5);
        //ResultScanner resultScanner = table.getScanner(scan5);
        int x1=0,x2=0,x3=0,x4=0;
        //上车地点
        if(up_loc.equals("龙翔桥")){
            x1=82;
            x2=49;
        }
        else if(up_loc.equals("浙江工业大学")){
            x1=223;
            x2=155;
        }
        else if (up_loc.equals("滨江科技城")){
            x1=91;
            x2=32;
        }
        else if (up_loc.equals("龙湖天街")){
            x1=130;
            x2=89;
        }
        else if (up_loc.equals("朝晖校区")){
            x1=42;
            x2=123;
        }
        else{
            System.out.println("no place");
        }
        //下车地点
        if(down_loc.equals("龙翔桥")){
            x3=82;
            x4=49;
        }
        else if(down_loc.equals("浙江工业大学")){
            x3=223;
            x4=155;
        }
        else if (down_loc.equals("滨江科技城")){
            x3=91;
            x4=32;
        }
        else if (down_loc.equals("龙湖天街")){
            x3=130;
            x4=89;
        }
        else if (down_loc.equals("朝晖校区")){
            x3=42;
            x4=123;
        }
        else{
            System.out.println("no place");
        }
        double distance=99999;
        String rowkey2=null;
        //int best_y1=0,best_y2=0;
        //String dr;
        for (Result result : scanner) {
            //System.out.println(Bytes.toString(result.getValue("state".getBytes(), "state".getBytes())));
            //System.out.println(Bytes.toString(result.getValue("state".getBytes(), "longitude".getBytes())));
            //System.out.println(Bytes.toString(result.getValue("state".getBytes(), "latitude".getBytes())));
            String state=Bytes.toString(result.getValue("state".getBytes(), "state".getBytes()));
            if(state.equals("N")){
            // 获取rowkey
            String x=Bytes.toString(result.getRow());
            // 指定司机的当前位置数据
            Float y1=Float.valueOf(Bytes.toString(result.getValue("state".getBytes(), "latitude".getBytes())));
            Float y2=Float.valueOf(Bytes.toString(result.getValue("state".getBytes(), "longitude".getBytes())));
            System.out.println(y1);
            System.out.println(y2);
            Double d=Math.sqrt((x1-y1)*(x1-y1)+(x2-y2)*(x2-y2));
            if(d<distance){
                distance=d;
                rowkey2=x;
                //best_y1=y1;
                //best_y2=y2;
            }
        }
        }
        //if rowkey2==null 需要排队

        String distance1=String.format("%.2f", distance);
        String regEx="[^0-9]";  
        Pattern p = Pattern.compile(regEx);  
        Matcher m = p.matcher(rowkey2);  
        String driver_id=m.replaceAll("").trim();
        //System.out.println(x);
        Matcher m2 = p.matcher(rowkey1);  
        String pass_id=m2.replaceAll("").trim();
        
        Get get_user=new Get(Bytes.toBytes(rowkey1));
        Result result_user = null;
		result_user = table.get(get_user);
		String user_phone=Bytes.toString(result_user.getValue("pass_info".getBytes(),"phone".getBytes()));
        String user_name=Bytes.toString(result_user.getValue("pass_info".getBytes(),"name".getBytes()));

        
        Get get_driver=new Get(Bytes.toBytes(rowkey2));
        Result result_driver = null;
		result_driver = table.get(get_driver);
        //String driver_sex=Bytes.toString(result_driver.getValue("driv_info".getBytes(),"sex".getBytes()));
        String driver_phone=Bytes.toString(result_driver.getValue("driv_info".getBytes(),"phone".getBytes()));
        String driver_name=Bytes.toString(result_driver.getValue("driv_info".getBytes(),"name".getBytes()));
        
        String value="{\"VendorID\":\""+pass_id+"\",\"pass_name\":\""+user_name+"\",\"pass_phone\":\""+user_phone+"\",\"lpep_pickup_datetime\":\""+t+"\",\"state\":\"1\",\"PUSite\":\""+up_loc+"\",\"PULongitude\":\""+x1+"\",\"PULatitude\":\""+x2+"\",\"DOStie\":\""+down_loc+"\",\"DOLongitude\":\""+x3+"\",\"DOLatitude\":\""+x4+"\",\"passenger_count\":\""+people_count+"\",\"trip_distance\":\""+distance1+"\",\"Driver_ID\":\""+driver_id+"\",\"driv_name\":\""+driver_name+"\",\"driv_phone\":\""+driver_phone+"\"}";

        Put put1=new Put(Bytes.toBytes(rowkey1));
        int num=coporcessor3.gettripcount();
        num++;
        String rktrip="trip".concat(num+"");
        put1.addColumn("trip_info".getBytes(), rktrip.getBytes(), Bytes.toBytes(value));
        Put put2=new Put(Bytes.toBytes(rowkey2));
        put2.addColumn("trip_info".getBytes(), rktrip.getBytes(), Bytes.toBytes(value));
        Put put3=new Put(Bytes.toBytes(rowkey2));
        put3.addColumn("state".getBytes(), "state".getBytes(), Bytes.toBytes("Y"));
        table.put(put1);
        table.put(put2);
        table.put(put3);
        table.close();
        close();
        return rowkey2;

        //for (Cell cell:)

    }
    
    
    public void have_score(String rowkey1,String payment_type,String score) throws IOException{
        init();
        // 获取表
        Table table = connection.getTable(TableName.valueOf("trip"));
         
        Get get=new Get(Bytes.toBytes(rowkey1));
        String pass_id=null,up_time=null,up_loc=null,up_longitude=null,up_latitude=null,down_loc=null,down_longitude=null,down_latitude=null,people_count=null,distance=null,driver_id=null;
        String driver_name=null,driver_phone=null,trip_type=null,down_time=null,user_name=null,user_phone=null;
        get.addFamily(Bytes.toBytes("trip_info"));

        Result result = table.get(get);
        String cn=null;
        //遍历每个trip 找到state为1的订单
        for (Cell cell:result.rawCells()){
            String data=new String(CellUtil.cloneValue(cell));
            JSONObject jsonObject=new JSONObject(data);
            String state=jsonObject.getString("pay_state");
            if(state.equals("N")){
            cn=new String(CellUtil.cloneQualifier(cell));
            pass_id=jsonObject.getString("VendorID");
            user_name=jsonObject.getString("pass_name");
            user_phone=jsonObject.getString("pass_phone");
            up_time=jsonObject.getString("lpep_pickup_datetime");
            down_time=jsonObject.getString("lpep_dropoff_datetime");
            up_loc=jsonObject.getString("PUSite");
            up_longitude=jsonObject.getString("PULongitude");
            up_latitude=jsonObject.getString("PULatitude");
            down_loc=jsonObject.getString("DOStie");
            down_longitude=jsonObject.getString("DOLongitude");
            down_latitude=jsonObject.getString("DOLatitude");
            people_count=jsonObject.getString("passenger_count");
            distance=jsonObject.getString("trip_distance");
            //fare_amount=jsonObject.getString("fare_amount");
            //extra=jsonObject.getString("extra");
            //mta_tax=jsonObject.getString("mta_tax");
            //tolls_amount=jsonObject.getString("tolls_amount");
            //improvement_surcharge=jsonObject.getString("improvement_surcharge");
            trip_type=jsonObject.getString("trip_type");
            driver_id=jsonObject.getString("Driver_ID");
            driver_name=jsonObject.getString("driv_name");
            driver_phone=jsonObject.getString("driv_phone");
            }
      

        }
        Float num_distance=Float.valueOf(distance);
//        Float num_extra=Float.valueOf(extra);
//        Float num_mta_tax=Float.valueOf(mta_tax);
//        Float num_tolls_amount=Float.valueOf(tolls_amount);
//        Float num_improvement_surcharge=Float.valueOf(improvement_surcharge);
//        Float num_tip_amount=Float.valueOf(tip_amount);

        Float total_amount=num_distance*5/10;
        String payment_num=null;
        if(payment_type.equals("支付宝")){
            payment_num="1";
        }
        else if(payment_type.equals("微信")){
            payment_num="2";
        }
        else if(payment_type.equals("现金")){
            payment_num="3";
        }
        else if(payment_type.equals("银联")){
            payment_num="4";
        }

        String value="{\"VendorID\":\""+pass_id+"\",\"pass_name\":\""+user_name+"\",\"pass_phone\":\""+user_phone+"\",\"lpep_pickup_datetime\":\""+up_time+"\",\"lpep_dropoff_datetime\":\""+down_time+"\",\"state\":\"0\",\"PUSite\":\""+up_loc+"\",\"PULongitude\":\""+up_longitude+"\",\"PULatitude\":\""+up_latitude+"\",\"DOStie\":\""+down_loc+"\",\"DOLongitude\":\""+down_longitude+"\",\"DOLatitude\":\""+down_latitude+"\",\"passenger_count\":\""+people_count+"\",\"trip_distance\":\""+distance+"\",\"total_amount\""+total_amount+"\",\"pyament_type\":\""+payment_num+"\",\"trip_type\":\""+trip_type+"\",\"Driver_ID\":\""+driver_id+"\",\"driv_name\":\""+driver_name+"\",\"driv_phone\":\""+driver_phone+"\",\"pay_state\":\"Y\""+"\"}";  
        
        String rowkey2="driv"+driver_id;
        Put put1=new Put(Bytes.toBytes(rowkey1));
        put1.addColumn("trip_info".getBytes(), cn.getBytes(), Bytes.toBytes(value));
        Put put2=new Put(Bytes.toBytes(rowkey2));
        put2.addColumn("trip_info".getBytes(), cn.getBytes(), Bytes.toBytes(value));


        Get get2=new Get(Bytes.toBytes(rowkey2));
        get2.addFamily(Bytes.toBytes("driv_info"));
        Result result2=table.get(get2);

        String avg_score=Bytes.toString(result2.getValue("driv_info".getBytes(),"score".getBytes()));
        String score_people=Bytes.toString(result2.getValue("driv_info".getBytes(),"score_num".getBytes()));
        
        Float num_avg_score=Float.valueOf(avg_score);
        Float num_score_people=Float.valueOf(score_people);
        Float num_score=Float.valueOf(score);

        Float all_score=num_avg_score*num_score_people+num_score;
        Float new_score_people=num_score_people+1;
        Float new_avg=all_score/new_score_people;
        String str_score_people=String.valueOf(new_score_people);
        String str_avg=String.valueOf(new_avg);
        

        Put put3=new Put(Bytes.toBytes(rowkey2));
        put3.addColumn("driv_info".getBytes(), "score".getBytes(), Bytes.toBytes(str_avg));
        Put put4=new Put(Bytes.toBytes(rowkey2));
        put4.addColumn("driv_info".getBytes(), "score_num".getBytes(), Bytes.toBytes(str_score_people));
        
        
        table.put(put1);
        table.put(put2);
        table.put(put3);
        table.put(put4);
        table.close();
        close();
    }

    
    public boolean is_have_score(String rowkey1) throws IOException{
        init();
        // 获取表
        Table table = connection.getTable(TableName.valueOf("trip"));
         
        Get get=new Get(Bytes.toBytes(rowkey1));
        String pass_id=null,up_time=null,up_loc=null,up_longitude=null,up_latitude=null,down_loc=null,down_longitude=null,down_latitude=null,people_count=null,distance=null,driver_id=null;
        String driver_name=null,driver_phone=null,trip_type=null,down_time=null,user_name=null,user_phone=null;
        get.addFamily(Bytes.toBytes("trip_info"));

        Result result = table.get(get);
        String cn="";
        //遍历每个trip 找到state为1的订单
        for (Cell cell:result.rawCells()){
            String data=new String(CellUtil.cloneValue(cell));
            JSONObject jsonObject=new JSONObject(data);
            String state=jsonObject.getString("pay_state");
            if(state.equals("N")){
            cn=new String(CellUtil.cloneQualifier(cell));
            pass_id=jsonObject.getString("VendorID");
            user_name=jsonObject.getString("pass_name");
            user_phone=jsonObject.getString("pass_phone");
            up_time=jsonObject.getString("lpep_pickup_datetime");
            down_time=jsonObject.getString("lpep_dropoff_datetime");
            up_loc=jsonObject.getString("PUSite");
            up_longitude=jsonObject.getString("PULongitude");
            up_latitude=jsonObject.getString("PULatitude");
            down_loc=jsonObject.getString("DOStie");
            down_longitude=jsonObject.getString("DOLongitude");
            down_latitude=jsonObject.getString("DOLatitude");
            people_count=jsonObject.getString("passenger_count");
            distance=jsonObject.getString("trip_distance");
            //fare_amount=jsonObject.getString("fare_amount");
            //extra=jsonObject.getString("extra");
            //mta_tax=jsonObject.getString("mta_tax");
            //tolls_amount=jsonObject.getString("tolls_amount");
            //improvement_surcharge=jsonObject.getString("improvement_surcharge");
            trip_type=jsonObject.getString("trip_type");
            driver_id=jsonObject.getString("Driver_ID");
            driver_name=jsonObject.getString("driv_name");
            driver_phone=jsonObject.getString("driv_phone");
            }
            if(cn.isEmpty()) {
            	return false;
            }

        }
        
//        Float num_extra=Float.valueOf(extra);
//        Float num_mta_tax=Float.valueOf(mta_tax);
//        Float num_tolls_amount=Float.valueOf(tolls_amount);
//        Float num_improvement_surcharge=Float.valueOf(improvement_surcharge);
//        Float num_tip_amount=Float.valueOf(tip_amount);

        


        close();
		return true;
    }


    
}
