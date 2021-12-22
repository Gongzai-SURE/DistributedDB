package Entity;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.CompareOperator;
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
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;
import java.util.Vector;


public class Driver {
	public static String rk;//rowkey
    public static String un;//uesrname
    public static String psw;//password
    public static String phone;
    public static String tableName="trip";
    public static String temp="temp";
    public static Configuration configuration;
    public static Connection connection;
    public static Admin admin;
    public static Double score=0.;
    public static String tn="trip";
    public static String hdtype;
    public static String money;
    public static int num=1;
    public static int num1=8;
    public static double amount=0;
    public Driver() {
    	rk="pass1";
    	un=new String("Jack");
    } 
    
    public Driver(String rowkey,String username) {
    	rk=new String(rowkey);
    	un=new String(username);
    }
    
    //新增记录 注册司机 通过测试
    public static boolean putrecord(String un,String psw,String sex,String phone) throws IOException {
    	init();
  		//获取表对象
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		init();
       
        
        //创建scan对象
        Scan scan = new Scan();
  		@SuppressWarnings("deprecation")
		//Scan scan=new Scan();		
		//BinaryPrefixComparator匹配开头，前缀二进制比较器
  		QualifierFilter qualifierFilter = new QualifierFilter(CompareOperator.EQUAL,new BinaryComparator(Bytes.toBytes("name")));		
  		scan.setFilter(qualifierFilter);	
		rk=new String("");
  		//获取数据
  		ResultScanner resultscanner=table.getScanner(scan);		
  		//解析result
  		for (Result result:resultscanner) {
  			//打印数据			
  			List<Cell> cells=result.listCells();
  			for(Cell cell:cells) {
  				String namert=new String(CellUtil.cloneValue(cell));//rowkey
  				System.out.println(namert);
  				if(namert.contentEquals(un)) {
  					System.out.println("fit");
  					rk=new String(CellUtil.cloneRow(cell));
  					break;
  				}
  				//System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
//  				System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
  				
  			}           
  		}
  		if ((rk.contentEquals("")==false)) {
  			System.out.println("hell");
  			return false;
  		}
  		
  		
  		//插入temp对象
  		num=coporcessor2.getrowcount("temp");
  		num++;
  		String dirvrk="temp".concat(num+"");
//  		num++;
  		Put put=new Put(Bytes.toBytes(dirvrk));
//  		Put put=new Put(Bytes.toBytes("temp1"));
  		put.addColumn(Bytes.toBytes(temp), Bytes.toBytes("nametemp"), Bytes.toBytes(un));
  		put.addColumn(Bytes.toBytes(temp), Bytes.toBytes("password"), Bytes.toBytes(psw));
  		put.addColumn(Bytes.toBytes(temp), Bytes.toBytes("sex"), Bytes.toBytes(sex));
  		put.addColumn(Bytes.toBytes(temp), Bytes.toBytes("phone"), Bytes.toBytes(phone));
  		put.addColumn(Bytes.toBytes("temp"), Bytes.toBytes("state"), Bytes.toBytes("Y"));
  		System.out.println("ok");
  		table.put(put);
  		table.close();
  		close();
  		return true;
    }
    
    //新增记录 注册用户 通过测试
    public static boolean putrecorduser(String un,String psw,String phone) throws IOException {
    	init();
  		//获取表对象
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		
  		 
        //创建scan对象
        Scan scan = new Scan();
  		@SuppressWarnings("deprecation")
		//Scan scan=new Scan();		
		//BinaryPrefixComparator匹配开头，前缀二进制比较器
  		QualifierFilter qualifierFilter = new QualifierFilter(CompareOperator.EQUAL,new BinaryComparator(Bytes.toBytes("name")));		
  		scan.setFilter(qualifierFilter);	
		rk=new String("");
  		//获取数据
  		ResultScanner resultscanner=table.getScanner(scan);		
  		//解析result
  		for (Result result:resultscanner) {
  			//打印数据			
  			List<Cell> cells=result.listCells();
  			for(Cell cell:cells) {
  				String namert=new String(CellUtil.cloneValue(cell));//rowkey
  				System.out.println(namert);
  				if(namert.contentEquals(un)) {
  					System.out.println("fit");
  					rk=new String(CellUtil.cloneRow(cell));
  					break;
  				}
  				//System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
//  				System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
  				
  			}           
  		}
  		if ((rk.contentEquals("")==false)) {
  			System.out.println("hell");
  			return false;
  		}
  		
  		
  		num1=coporcessor2.getrowcount("pass_info");
  		num1++;
  		String dirvrk="pass".concat(num1+"");
//  		num++;
  		Put put=new Put(Bytes.toBytes(dirvrk));
//  		Put put=new Put(Bytes.toBytes("temp1"));
  		put.addColumn(Bytes.toBytes("pass_info"), Bytes.toBytes("name"), Bytes.toBytes(un));
//  		put.addColumn(Bytes.toBytes("pass_info"), Bytes.toBytes("name"), Bytes.toBytes(un));
  		put.addColumn(Bytes.toBytes("pass_info"), Bytes.toBytes("password"), Bytes.toBytes(psw));
//  		put.addColumn(Bytes.toBytes(temp), Bytes.toBytes("sex"), Bytes.toBytes(sex));
  		put.addColumn(Bytes.toBytes("pass_info"), Bytes.toBytes("phone"), Bytes.toBytes(phone));
//  		put.addColumn(Bytes.toBytes("temp"), Bytes.toBytes("state"), Bytes.toBytes("Y"));
  		System.out.println("ok");
  		table.put(put);
  		table.close();
  		close();
		return true;
    }
    
    
    //支付方式
    public static ArrayList getDataTripPay(String tableName,String rowkey) throws IOException {
  		init();
  		//获取表对象
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		//创建get对象
  		Get get =new Get(Bytes.toBytes(rowkey));
  		get.addFamily(Bytes.toBytes("trip_info"));
  		ArrayList<Vector> sites = new ArrayList<Vector>();
  		//get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
  		
  		//获取数据
  		Result result=table.get(get);
  		
  		amount=0;
  		//解析result
  		
  		for (Cell cell:result.rawCells()) {
  			//打印数据
  			  Vector v=new Vector();
  			/*System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
              System.out.println("Timetamp:" + cell.getTimestamp() + " ");
              System.out.println("column Family:" + new String(CellUtil.cloneFamily(cell)) + " ");
              System.out.println("column Name:" + new String(CellUtil.cloneQualifier(cell)) + " ");*/
//              System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
  			  List<Cell> cells=result.listCells();
  			  
              String data=new String(CellUtil.cloneValue(cell));              
              JSONObject jsonObject=new JSONObject(data);              
              String state=jsonObject.getString("state");
              if(state.contentEquals("0")) {
            	  money=jsonObject.getString("total_amount");
                  String tripType=jsonObject.getString("trip_type");
                  String drivid=jsonObject.getString("Driver_ID");
                  String drivphone=jsonObject.getString("driv_phone");
                  String distance=jsonObject.getString("trip_distance");
           
                  String paystate=jsonObject.getString("pay_state");
                  String puloc=jsonObject.getString("PUSite");
                  String drloc=jsonObject.getString("DOStie");
                  String putime=jsonObject.getString("lpep_pickup_datetime");
                  String drtime=jsonObject.getString("lpep_dropoff_datetime");
//                  v.add(drivid);
//                  v.add(drivphone);
                  
                  if(paystate.contentEquals("N")) {
                	  System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
                	  v.add(drivid);
                      v.add(drivphone);
                      v.add(money);
//                	  v.add("已支付");
                	   v.add(distance);
                       v.add(puloc);
                       v.add(drloc);
                       if(tripType.contentEquals("1")) {
                         	System.out.println("短途订单");
                         	 v.add("短途");
                         }
                         else {
                         	System.out.println("长途订单");
                         	 v.add("长途");
                         }
                       v.add(putime);
                       v.add(drtime);
                       sites.add(v);
                    }
               
            	  
              }

  		}
  		amount = (double) Math.round(amount * 100) / 100;
  		System.out.println("总收入是："+amount);
  		table.close();
  		close();
  		return sites;
  	}
  	
    
    
    
    public static ArrayList getDataTrip(String tableName,String rowkey) throws IOException {
  		init();
  		//获取表对象
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		//创建get对象
  		Get get =new Get(Bytes.toBytes(rowkey));
  		get.addFamily(Bytes.toBytes("trip_info"));
  		ArrayList<Vector> sites = new ArrayList<Vector>();
  		//get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
  		
  		//获取数据
  		Result result=table.get(get);
  		
  		amount=0;
  		//解析result
  		
  		for (Cell cell:result.rawCells()) {
  			//打印数据
  			  Vector v=new Vector();
  			/*System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
              System.out.println("Timetamp:" + cell.getTimestamp() + " ");
              System.out.println("column Family:" + new String(CellUtil.cloneFamily(cell)) + " ");
              System.out.println("column Name:" + new String(CellUtil.cloneQualifier(cell)) + " ");*/
              System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
  			  List<Cell> cells=result.listCells();
  			  
              String data=new String(CellUtil.cloneValue(cell));
              JSONObject jsonObject=new JSONObject(data);
             
              String tripstate=jsonObject.getString("state");
              if(tripstate.contentEquals("0")) {
            	  String paystate=jsonObject.getString("pay_state");
                  if(paystate.contentEquals("Y")) {
                	  String money=jsonObject.getString("total_amount");
                      String tripType=jsonObject.getString("trip_type");
                      String passid=jsonObject.getString("VendorID");
                      String passphone=jsonObject.getString("pass_phone");                  
                      String puloc=jsonObject.getString("PUSite");
                      String drloc=jsonObject.getString("DOStie");
                      String putime=jsonObject.getString("lpep_pickup_datetime");
                      String drtime=jsonObject.getString("lpep_dropoff_datetime");
//                      v.add(drivid);
//                      v.add(drivphone);
                      v.add(passid);
                      v.add(passphone);
                      v.add(money);
                	  v.add("已支付");
                	  String pay_type=jsonObject.getString("payment_type");
                      String distance=jsonObject.getString("trip_distance");
                      v.add(distance);
                      v.add(puloc);
                      v.add(drloc);
                      if(tripType.contentEquals("1")) {
                        	System.out.println("短途订单");
                        	 v.add("短途");
                        }
                        else {
                        	System.out.println("长途订单");
                        	 v.add("长途");
                        }
                      v.add(putime);
                      v.add(drtime);
                      System.out.println("价格是："+jsonObject.getString("total_amount"));
                      System.out.println("乘客人数："+jsonObject.getString("passenger_count"));
                      System.out.println("评分是："+jsonObject.getString("score"));
//                      System.out.println("价格是："+tripType);
                      sites.add(v);
                      try {

                      	double value = Double.valueOf(money);
                          amount+=value;
                      } catch (NumberFormatException e) {
                          e.printStackTrace();
                      }
              }
              
                 
                }
             
//            
//              System.out.println("年龄："+jsonObject.getString("age"));
//              System.out.println("学到的技能："+jsonObject.getJSONArray("sex"));
  		}
  		amount = (double) Math.round(amount * 100) / 100;
  		System.out.println("总收入是："+amount);
  		table.close();
  		close();
  		return sites;
  	}
  	
  	//get 查询订单数据按年
  	public static ArrayList<Vector> getDataTripYear(String tableName,String rowkey,int year) throws IOException {
  		init();
  		//获取表对象
  		String year1= Integer.toString(year);
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		//创建get对象
  		Get get =new Get(Bytes.toBytes(rowkey));
  		get.addFamily(Bytes.toBytes("trip_info"));
  		ArrayList<Vector> sites = new ArrayList<Vector>();
  		//获取数据
  		Result result=table.get(get);
  		
  		amount=0;
  		//解析result
  		
  		for (Cell cell:result.rawCells()) {
  			//打印数据
  			  Vector v=new Vector();
              System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
  			  List<Cell> cells=result.listCells();
  			  
              String data=new String(CellUtil.cloneValue(cell));
              JSONObject jsonObject=new JSONObject(data);
              String tripstate=jsonObject.getString("state");
              if(tripstate.contentEquals("0")) {
            	  String paystate=jsonObject.getString("pay_state");
            	  String drtime=jsonObject.getString("lpep_dropoff_datetime");
                  if(paystate.contentEquals("Y")&& reStr(drtime,year1+"/")) {
                	  String money=jsonObject.getString("total_amount");
                      String tripType=jsonObject.getString("trip_type");
                      String passid=jsonObject.getString("VendorID");
                      String passphone=jsonObject.getString("pass_phone");                  
                      String puloc=jsonObject.getString("PUSite");
                      String drloc=jsonObject.getString("DOStie");
                      String putime=jsonObject.getString("lpep_pickup_datetime");
                      
//                      v.add(drivid);
//                      v.add(drivphone);
                      v.add(passid);
                      v.add(passphone);
                      v.add(money);
                	  v.add("已支付");
                	  String pay_type=jsonObject.getString("payment_type");
                      String distance=jsonObject.getString("trip_distance");
                      v.add(distance);
                      v.add(puloc);
                      v.add(drloc);
                      if(tripType.contentEquals("1")) {
                        	System.out.println("短途订单");
                        	 v.add("短途");
                        }
                        else {
                        	System.out.println("长途订单");
                        	 v.add("长途");
                        }
                      v.add(putime);
                      v.add(drtime);
                      System.out.println("价格是："+jsonObject.getString("total_amount"));
                      System.out.println("乘客人数："+jsonObject.getString("passenger_count"));
                      System.out.println("评分是："+jsonObject.getString("score"));
//                      System.out.println("价格是："+tripType);
                      sites.add(v);
                      try {

                      	double value = Double.valueOf(money);
                          amount+=value;
                      } catch (NumberFormatException e) {
                          e.printStackTrace();
                      }
  		}}}
  		amount = (double) Math.round(amount * 100) / 100;
  		System.out.println("总收入是："+amount);
  		table.close();
  		close();
  		return sites;
  	}					

  	//get 查询订单数据按month
  	public static ArrayList<Vector> getDataTripMonth(String tableName,String rowkey,int year,int month) throws IOException {
  		init();
  		//获取表对象
  		String year1= Integer.toString(year);
  		String month1= Integer.toString(month);
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		//创建get对象
  		Get get =new Get(Bytes.toBytes(rowkey));
  		get.addFamily(Bytes.toBytes("trip_info"));
  		ArrayList<Vector> sites = new ArrayList<Vector>();
  		//获取数据
  		Result result=table.get(get);
  		
  		amount=0;
  		//解析result
  		
  		for (Cell cell:result.rawCells()) {
  			//打印数据
  			  Vector v=new Vector();
              System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
  			  List<Cell> cells=result.listCells();
  			  
              String data=new String(CellUtil.cloneValue(cell));
              JSONObject jsonObject=new JSONObject(data);
              String tripstate=jsonObject.getString("state");
              if(tripstate.contentEquals("0")) {
            	  String paystate=jsonObject.getString("pay_state");
            	  String drtime=jsonObject.getString("lpep_dropoff_datetime");
                  if(paystate.contentEquals("Y")&&reStr(drtime,year1+"/"+month1+"/")) {
                	  String money=jsonObject.getString("total_amount");
                      String tripType=jsonObject.getString("trip_type");
                      String passid=jsonObject.getString("VendorID");
                      String passphone=jsonObject.getString("pass_phone");                  
                      String puloc=jsonObject.getString("PUSite");
                      String drloc=jsonObject.getString("DOStie");
                      String putime=jsonObject.getString("lpep_pickup_datetime");
                      
//                      v.add(drivid);
//                      v.add(drivphone);
                      v.add(passid);
                      v.add(passphone);
                      v.add(money);
                	  v.add("已支付");
                	  String pay_type=jsonObject.getString("payment_type");
                      String distance=jsonObject.getString("trip_distance");
                      v.add(distance);
                      v.add(puloc);
                      v.add(drloc);
                      if(tripType.contentEquals("1")) {
                        	System.out.println("短途订单");
                        	 v.add("短途");
                        }
                        else {
                        	System.out.println("长途订单");
                        	 v.add("长途");
                        }
                      v.add(putime);
                      v.add(drtime);
                      System.out.println("价格是："+jsonObject.getString("total_amount"));
                      System.out.println("乘客人数："+jsonObject.getString("passenger_count"));
                      System.out.println("评分是："+jsonObject.getString("score"));
//                      System.out.println("价格是："+tripType);
                      sites.add(v);
                      try {

                      	double value = Double.valueOf(money);
                          amount+=value;
                      } catch (NumberFormatException e) {
                          e.printStackTrace();
                      }
  		}}}
  		amount = (double) Math.round(amount * 100) / 100;
  		System.out.println("总收入是："+amount);
  		table.close();
  		close();
  		return sites;
  	}					
  	
    
    public static ArrayList getDataTripUser(String tableName,String rowkey) throws IOException {
  		init();
  		//获取表对象
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		//创建get对象
  		Get get =new Get(Bytes.toBytes(rowkey));
  		get.addFamily(Bytes.toBytes("trip_info"));
  		ArrayList<Vector> sites = new ArrayList<Vector>();
  		//get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
  		
  		//获取数据
  		Result result=table.get(get);
  		
  		amount=0;
  		//解析result
  		
  		for (Cell cell:result.rawCells()) {
  			//打印数据
  			  Vector v=new Vector();
  			/*System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
              System.out.println("Timetamp:" + cell.getTimestamp() + " ");
              System.out.println("column Family:" + new String(CellUtil.cloneFamily(cell)) + " ");
              System.out.println("column Name:" + new String(CellUtil.cloneQualifier(cell)) + " ");*/
              System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
  			  List<Cell> cells=result.listCells();
  			  
              String data=new String(CellUtil.cloneValue(cell));
              JSONObject jsonObject=new JSONObject(data);

              //reStr(drtime,year1+"/")
              String state=jsonObject.getString("state");
              if(state.contentEquals("0")) {
            	  money=jsonObject.getString("total_amount");
                  String tripType=jsonObject.getString("trip_type");
                  String drivid=jsonObject.getString("Driver_ID");
                  String drivphone=jsonObject.getString("driv_phone");
                  String distance=jsonObject.getString("trip_distance");
           
                  String paystate=jsonObject.getString("pay_state");
                  String puloc=jsonObject.getString("PUSite");
                  String drloc=jsonObject.getString("DOStie");
                  String putime=jsonObject.getString("lpep_pickup_datetime");
                  String drtime=jsonObject.getString("lpep_dropoff_datetime");
//                  v.add(drivid);
//                  v.add(drivphone);
                //reStr(drtime,year1+"/")
                  if(paystate.contentEquals("Y")) {
                	  String tripscore=jsonObject.getString("score");
                	  System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
                	  v.add(drivid);
                      v.add(drivphone);
                      v.add(money);
                      v.add("已支付");
                      v.add(tripscore);
                	  
                	   v.add(distance);
                	   
                       v.add(puloc);
                       v.add(drloc);
                       if(tripType.contentEquals("1")) {
                         	System.out.println("短途订单");
                         	 v.add("短途");
                         }
                         else {
                         	System.out.println("长途订单");
                         	 v.add("长途");
                         }
                       try {

                         	double value = Double.valueOf(money);
                             amount+=value;
                         } catch (NumberFormatException e) {
                             e.printStackTrace();
                         }
                       v.add(putime);
                       v.add(drtime);
                       sites.add(v);
                  }
                	 
                  }
              
             
//              System.out.println("年龄："+jsonObject.getString("age"));
//              System.out.println("学到的技能："+jsonObject.getJSONArray("sex"));
  		}
  		amount = (double) Math.round(amount * 100) / 100;
  		System.out.println("总收入是："+amount);
  		table.close();
  		close();
  		return sites;
  	}
  	
  	//get 查询订单数据按年
  	public static ArrayList<Vector> getDataTripYearUser(String tableName,String rowkey,int year) throws IOException {
  		init();
  		//获取表对象
  		String year1= Integer.toString(year);
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		//创建get对象
  		Get get =new Get(Bytes.toBytes(rowkey));
  		get.addFamily(Bytes.toBytes("trip_info"));
  		ArrayList<Vector> sites = new ArrayList<Vector>();
  		//获取数据
  		Result result=table.get(get);
  		
  		amount=0;
  		//解析result
  		
  		for (Cell cell:result.rawCells()) {
  			//打印数据
  			  Vector v=new Vector();
              System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
  			  List<Cell> cells=result.listCells();
  			  
              String data=new String(CellUtil.cloneValue(cell));
              JSONObject jsonObject=new JSONObject(data);
            //reStr(drtime,year1+"/")
              String state=jsonObject.getString("state");
              if(state.contentEquals("0")) {
            	  money=jsonObject.getString("total_amount");
                  String tripType=jsonObject.getString("trip_type");
                  String drivid=jsonObject.getString("Driver_ID");
                  String drivphone=jsonObject.getString("driv_phone");
                  String distance=jsonObject.getString("trip_distance");
           
                  String paystate=jsonObject.getString("pay_state");
                  String puloc=jsonObject.getString("PUSite");
                  String drloc=jsonObject.getString("DOStie");
                  String putime=jsonObject.getString("lpep_pickup_datetime");
                  String drtime=jsonObject.getString("lpep_dropoff_datetime");
//                  v.add(drivid);
//                  v.add(drivphone);
                //reStr(drtime,year1+"/")
                  if(paystate.contentEquals("Y") && reStr(drtime,year1+"/")) {
                	  String tripscore=jsonObject.getString("score");
                	  System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
                	  v.add(drivid);
                      v.add(drivphone);
                      v.add(money);
                      v.add("已支付");
                      v.add(tripscore);
                	  
                	   v.add(distance);
                	   
                       v.add(puloc);
                       v.add(drloc);
                       if(tripType.contentEquals("1")) {
                         	System.out.println("短途订单");
                         	 v.add("短途");
                         }
                         else {
                         	System.out.println("长途订单");
                         	 v.add("长途");
                         }
                       try {

                         	double value = Double.valueOf(money);
                             amount+=value;
                         } catch (NumberFormatException e) {
                             e.printStackTrace();
                         }
                       v.add(putime);
                       v.add(drtime);
                       sites.add(v);
                  }
                	 
                  }
              
             
//              System.out.println("年龄："+jsonObject.getString("age"));
//              System.out.println("学到的技能："+jsonObject.getJSONArray("sex"));
  		}
  		amount = (double) Math.round(amount * 100) / 100;
  		System.out.println("总收入是："+amount);
  		table.close();
  		close();
  		return sites;
  	}					

  	//get 查询订单数据按month
  	public static ArrayList<Vector> getDataTripMonthUser(String tableName,String rowkey,int year,int month) throws IOException {
  		init();
  		//获取表对象
  		String year1= Integer.toString(year);
  		String month1= Integer.toString(month);
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		//创建get对象
  		Get get =new Get(Bytes.toBytes(rowkey));
  		get.addFamily(Bytes.toBytes("trip_info"));
  		ArrayList<Vector> sites = new ArrayList<Vector>();
  		//获取数据
  		Result result=table.get(get);
  		
  		amount=0;
  		//解析result
//  		reStr(drtime,year1+"/"+month1+"/")
  		for (Cell cell:result.rawCells()) {
  			//打印数据
  			  Vector v=new Vector();
              System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
  			  List<Cell> cells=result.listCells();
  			String data=new String(CellUtil.cloneValue(cell));
            JSONObject jsonObject=new JSONObject(data);
          //reStr(drtime,year1+"/")
            String state=jsonObject.getString("state");
            if(state.contentEquals("0")) {
          	  money=jsonObject.getString("total_amount");
                String tripType=jsonObject.getString("trip_type");
                String drivid=jsonObject.getString("Driver_ID");
                String drivphone=jsonObject.getString("driv_phone");
                String distance=jsonObject.getString("trip_distance");
         
                String paystate=jsonObject.getString("pay_state");
                String puloc=jsonObject.getString("PUSite");
                String drloc=jsonObject.getString("DOStie");
                String putime=jsonObject.getString("lpep_pickup_datetime");
                String drtime=jsonObject.getString("lpep_dropoff_datetime");
//                v.add(drivid);
//                v.add(drivphone);
              //reStr(drtime,year1+"/")
                if(paystate.contentEquals("Y") &&reStr(drtime,year1+"/"+month1+"/")) {
              	  String tripscore=jsonObject.getString("score");
              	  System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
              	  v.add(drivid);
                    v.add(drivphone);
                    v.add(money);
                    v.add("已支付");
                    v.add(tripscore);
              	  
              	   v.add(distance);
              	   
                     v.add(puloc);
                     v.add(drloc);
                     if(tripType.contentEquals("1")) {
                       	System.out.println("短途订单");
                       	 v.add("短途");
                       }
                       else {
                       	System.out.println("长途订单");
                       	 v.add("长途");
                       }
                     try {

                       	double value = Double.valueOf(money);
                           amount+=value;
                       } catch (NumberFormatException e) {
                           e.printStackTrace();
                       }
                     v.add(putime);
                     v.add(drtime);
                     sites.add(v);
                }
              	 
                }
            
           
//            System.out.println("年龄："+jsonObject.getString("age"));
//            System.out.println("学到的技能："+jsonObject.getJSONArray("sex"));
		}
  		amount = (double) Math.round(amount * 100) / 100;
  		System.out.println("总收入是："+amount);
  		table.close();
  		close();
  		return sites;
  	}					
  	
  	
  	//获取信息
  	public static void getDatainfo() throws IOException {
  		init();
  		//获取表对象
  		Table table = connection.getTable(TableName.valueOf(tn));
  		//创建get对象
  		Get get =new Get(Bytes.toBytes(rk));
  		//get.addFamily(Bytes.toBytes("driv_info"));
  		
  		get.addColumn(Bytes.toBytes("driv_info"), Bytes.toBytes("score"));
  		
  		//获取数据
  		Result result=table.get(get);
  		
  		
  		//解析result
  		for (Cell cell:result.rawCells()) {
  			//打印数据
//  			System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
//              System.out.println("Timetamp:" + cell.getTimestamp() + " ");
//              System.out.println("column Family:" + new String(CellUtil.cloneFamily(cell)) + " ");
              
//              System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
//              String cln=new String(CellUtil.cloneQualifier(cell));
//              if (cln.contentEquals("score")) {
            System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
            String sc=new String(CellUtil.cloneValue(cell));
            score=Double.valueOf(sc.toString());
              
              
  		}
  	//创建get对象
  		Get get1 =new Get(Bytes.toBytes(rk));
  		//get.addFamily(Bytes.toBytes("driv_info"));
  		
//  		get.addColumn(Bytes.toBytes("driv_info"), Bytes.toBytes("score"));
  		get1.addColumn(Bytes.toBytes("driv_info"), Bytes.toBytes("phone"));
  		
  		//获取数据
  		result=table.get(get1);
  		
  		
  		//解析result
  		for (Cell cell:result.rawCells()) {
  			//打印数据
//  			System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
//              System.out.println("Timetamp:" + cell.getTimestamp() + " ");
//              System.out.println("column Family:" + new String(CellUtil.cloneFamily(cell)) + " ");
              
//              System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
//              String cln=new String(CellUtil.cloneQualifier(cell));
//              if (cln.contentEquals("score")) {
            System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
            phone=new String(CellUtil.cloneValue(cell));
//            score=Double.valueOf(sc.toString());
              
              
  		}
  		
  		if(score>4.8) {
  			hdtype="金牌驾驶员";
  		}
  		else if(score>4.5) {
  			hdtype="王牌驾驶员";
  		}
  		else {
  			hdtype="普通驾驶员，服务有待提升哦";
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
        getDatainfo();
        //修改密码
        
        
        //管理员
        //查询个人信息
//        getDataAdmin("trip","driv_info");//加过滤器
        //test-login
        //i=login("xiaoming","12345681");
        
                                                                                                                                                                                           
        //System.out.println(i);

    }

}
