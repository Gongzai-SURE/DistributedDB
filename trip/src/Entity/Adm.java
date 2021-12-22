package Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.json.JSONObject;

public class Adm {
	public static Configuration configuration;
    public static Connection connection;
    public static Admin admin;
    public static String rk;
    public static int num=7;
    public static String cf="trip_info";
    //judge table exist
	public static boolean isTableExist(String tableName) throws IOException {
		init();
		boolean exists = admin.tableExists(TableName.valueOf(tableName));
        //table.close();
        close();
		return exists;
	}
	
	//查询信息司机，通过测试
	public static ArrayList getDataAdmin3(String tableName,String etype) throws IOException {
			init();
			//获取表对象
			Table table = connection.getTable(TableName.valueOf(tableName));
			//创建scan对象
			Scan scan = new Scan();
			@SuppressWarnings("deprecation")
			Filter familyFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(etype.getBytes()));
			scan.setFilter(familyFilter);		
			//获取数据
			ResultScanner resultscanner=table.getScanner(scan);		
			//解析result
			//Vector v1=new Vector();
			ArrayList<Vector> sites = new ArrayList<Vector>();
			for (Result result:resultscanner) {
				//打印数据			
				List<Cell> cells=result.listCells();
				Vector v=new Vector();
				String cf="state";
				String rk="";
				for(Cell cell:cells) {
					String rk1=new String(CellUtil.cloneRow(cell));										
					System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
					if((rk1.contentEquals(rk))==false) {
						System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
						rk=rk1;
						v.add(new String(CellUtil.cloneRow(cell)));
						}
					v.add(new String(CellUtil.cloneValue(cell)));
					}
					
				
				sites.add(v);
				
				
			}
			familyFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator("state".getBytes()));
			scan.setFilter(familyFilter);		
			//获取数据
			resultscanner=table.getScanner(scan);		
			//解析result
			//Vector v1=new Vector();
			//ArrayList<Vector> sites = new ArrayList<Vector>();
			int number=0;
			for (Result result:resultscanner) {
				//打印数据			
				List<Cell> cells=result.listCells();
				Vector v=new Vector();
				String cf="state";
				String rk="";
				
				v=sites.get(number);
//				System.out.println(v);
				number++;
				for(Cell cell:cells) {
					
					String rk1=new String(CellUtil.cloneQualifier(cell));	
					String value= new String(CellUtil.cloneValue(cell));
					System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
					if((rk1.contentEquals("state"))) {
						System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
						if(value.contentEquals("Y")) {
							v.add("忙碌");
						}
						else {
							v.add("空闲");
						}
//						rk=rk1;
//						v.add(new String(CellUtil.cloneRow(cell)));
						}
					else {
						v.add(new String(CellUtil.cloneValue(cell)));
					}
					
					}
				System.out.println("插完");	
				System.out.println(v);
//				sites.add(v);
				
			}
			table.close();
			close();
			return sites;
			
		}
		
	
	//查询信息pass，通过测试
	public static ArrayList getDataAdmin2(String tableName,String etype) throws IOException {
		init();
		//获取表对象
		Table table = connection.getTable(TableName.valueOf(tableName));
		//创建scan对象
		Scan scan = new Scan();
		@SuppressWarnings("deprecation")
		Filter familyFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(etype.getBytes()));
		scan.setFilter(familyFilter);		
		//获取数据
		ResultScanner resultscanner=table.getScanner(scan);		
		//解析result
		//Vector v1=new Vector();
		ArrayList<Vector> sites = new ArrayList<Vector>();
		for (Result result:resultscanner) {
			//打印数据			
			List<Cell> cells=result.listCells();
			Vector v=new Vector();
			String cf="state";
			String rk="";
			for(Cell cell:cells) {
				String rk1=new String(CellUtil.cloneRow(cell));
				if (cf.contentEquals(new String(CellUtil.cloneQualifier(cell)))){
					if(new String(CellUtil.cloneValue(cell)).contentEquals("Y")) {
						System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
						sites.add(v);
						continue;
					}
					else {
						System.out.println("审核未通过");
						break;
					}
				}
				else {
				System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
				if((rk1.contentEquals(rk))==false) {
					System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
					rk=rk1;
					v.add(new String(CellUtil.cloneRow(cell)));
					}
				v.add(new String(CellUtil.cloneValue(cell)));
				}
				
			} 
			sites.add(v);
			
		}
		table.close();
		close();
		return sites;
		
	}
	
	//查询信息审核司机，通过测试
	public static ArrayList getDataAdmin(String tableName,String etype) throws IOException {
		init();
		//获取表对象
		Table table = connection.getTable(TableName.valueOf(tableName));
		//创建scan对象
		Scan scan = new Scan();
		@SuppressWarnings("deprecation")
		Filter familyFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(etype.getBytes()));
		scan.setFilter(familyFilter);		
		//获取数据
		ResultScanner resultscanner=table.getScanner(scan);		
		//解析result
		//Vector v1=new Vector();
		ArrayList<Vector> sites = new ArrayList<Vector>();
		for (Result result:resultscanner) {
			//打印数据			
			List<Cell> cells=result.listCells();
			Vector v=new Vector();
			String cf="state";
			String rk="";
			for(Cell cell:cells) {
				String rk1=new String(CellUtil.cloneRow(cell));
				if (cf.contentEquals(new String(CellUtil.cloneQualifier(cell)))){
					if(new String(CellUtil.cloneValue(cell)).contentEquals("Y")) {
						System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
						sites.add(v);
						continue;
					}
					else {
						System.out.println("审核未通过");
						break;
					}
				}
				else {
				System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
				if((rk1.contentEquals(rk))==false) {
					System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
					rk=rk1;
					v.add(new String(CellUtil.cloneRow(cell)));
					}
				v.add(new String(CellUtil.cloneValue(cell)));
				}
			} 
			
		}
		table.close();
		close();
		return sites;
		
	}
//	v.add(new String(CellUtil.cloneValue(cell)));
	
	 //通过审核,测试成功
  	public static void pass(String tableName,String rowkey,DrivTemp driv) throws IOException {
  		init();
  		//获取表对象
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		//创建get对象
  		Delete delete=new Delete(Bytes.toBytes(rowkey));
  		
  		//get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
  		
  		//删除对象
  		table.delete(delete);
  		
  		//插入对象driv实体
  		num=coporcessor2.getrowcount("driv_info");
  		num++;
  		System.out.println("rowcount:" + num);
  		String dirvrk="driv".concat(num+"");
  		
  		Put put=new Put(Bytes.toBytes(dirvrk));
  		put.addColumn(Bytes.toBytes("driv_info"), Bytes.toBytes("name"), Bytes.toBytes(driv.un));
  		put.addColumn(Bytes.toBytes("driv_info"), Bytes.toBytes("password"), Bytes.toBytes(driv.psw));
  		put.addColumn(Bytes.toBytes("driv_info"), Bytes.toBytes("sex"), Bytes.toBytes(driv.sex));
  		put.addColumn(Bytes.toBytes("driv_info"), Bytes.toBytes("phone"), Bytes.toBytes(driv.phone));
  		put.addColumn(Bytes.toBytes("driv_info"), Bytes.toBytes("score"), Bytes.toBytes(driv.score));
  		put.addColumn(Bytes.toBytes("driv_info"), Bytes.toBytes("score_num"), Bytes.toBytes(driv.score_num));
  		put.addColumn(Bytes.toBytes("state"), Bytes.toBytes("state"), Bytes.toBytes(driv.state));
  		put.addColumn(Bytes.toBytes("state"), Bytes.toBytes("latitude"), Bytes.toBytes(driv.latitude));
  		put.addColumn(Bytes.toBytes("state"), Bytes.toBytes("longitude"), Bytes.toBytes(driv.longitude));

  		System.out.println("ok");
  		table.put(put);
  		table.close();
  		close();	  		
  	
  	}
  	
  	 //未通过审核
  	public static void nopass(String tableName,String rowkey,DrivTemp driv) throws IOException {
  		init();
  		//获取表对象
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		
  		Put put=new Put(Bytes.toBytes(rowkey));
  		put.addColumn(Bytes.toBytes("temp"), Bytes.toBytes("state"), Bytes.toBytes("N"));

  		System.out.println("nopass ok");
  		table.put(put);
  		table.close();
  		close();	  		
  	
  	}
  	
    //get 按id查询订单数据
  	public static ArrayList getDataTrip(String tableName,String rowkey) throws IOException {
  		init();
  		//获取表对象
  		Table table = connection.getTable(TableName.valueOf(tableName));
  		//创建get对象
  		Get get =new Get(Bytes.toBytes(rowkey));
  		get.addFamily(Bytes.toBytes(cf));
  		ArrayList<Vector> sites = new ArrayList<Vector>();
  		//get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
  		
  		//获取数据
  		Result result=table.get(get);
  		
  		double amount=0;
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
//              String passphone=jsonObject.getString("pass_phone");
              String drivid=jsonObject.getString("Driver_ID");
//              String drivphone=jsonObject.getString("driv_phone");
//              String paystate=jsonObject.getString("pay_state");
              String puloc=jsonObject.getString("PUSite");
              String drloc=jsonObject.getString("DOStie");
              String putime=jsonObject.getString("lpep_pickup_datetime");
              String score=jsonObject.getString("score");
              String drtime=jsonObject.getString("lpep_dropoff_datetime");
              v.add(drivid);
//              v.add(drivphone);
              v.add(passid);
//              v.add(passphone);
              v.add(money);
              if(paystate.contentEquals("Y")) {
            	  v.add("已支付");
                }
              else {
                	v.add("未支付");
                }
              v.add(score);
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
//              System.out.println("价格是："+tripType);
              sites.add(v);
              try {

              	double value = Double.valueOf(money);
                  amount+=value;
              } catch (NumberFormatException e) {
                  e.printStackTrace();
              }
                  }}
//              System.out.println("年龄："+jsonObject.getString("age"));
//              System.out.println("学到的技能："+jsonObject.getJSONArray("sex"));
  		}
  		amount = (double) Math.round(amount * 100) / 100;
  		System.out.println("总收入是："+amount);
  		table.close();
  		close();
  		return sites;
  	}
  	
    //get 查询all订单数据
   	public static ArrayList getDataTripAll(String tableName) throws IOException {
   		init();
   		//获取表对象
   		Table table = connection.getTable(TableName.valueOf(tableName));
   		//创建get对象
   		Scan scan = new Scan();
		@SuppressWarnings("deprecation")
		Filter familyFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator("trip_info".getBytes()));
		scan.setFilter(familyFilter);		
		//获取数据
		ResultScanner resultscanner=table.getScanner(scan);		
		//解析result
		//Vector v1=new Vector();
		ArrayList<Vector> sites = new ArrayList<Vector>();
   		//get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
   		
		for (Result result:resultscanner) {
			//打印数据			
			List<Cell> cells=result.listCells();
			
			String cf="state";
			String rk="";
			
			
			for(Cell cell:cells) {
//				System.out.println(new String(CellUtil.cloneValue(cell)));
				String rk1=new String(CellUtil.cloneRow(cell));
				System.out.println(rk1);
				if(reStr(rk1,"pass")) {
					Vector v=new Vector();
					String data=new String(CellUtil.cloneValue(cell));
		               JSONObject jsonObject=new JSONObject(data);
		               String tripstate=jsonObject.getString("state");
		               if(tripstate.contentEquals("0")) {
		             	  String paystate=jsonObject.getString("pay_state");
		                   if(paystate.contentEquals("Y")) {
		               String money=jsonObject.getString("total_amount");
		               String tripType=jsonObject.getString("trip_type");
		               String passid=jsonObject.getString("VendorID");
//		               String passphone=jsonObject.getString("pass_phone");
		               String drivid=jsonObject.getString("Driver_ID");
//		               String drivphone=jsonObject.getString("driv_phone");
//		               String paystate=jsonObject.getString("pay_state");
		               String puloc=jsonObject.getString("PUSite");
		               String drloc=jsonObject.getString("DOStie");
		               String putime=jsonObject.getString("lpep_pickup_datetime");
		               String drtime=jsonObject.getString("lpep_dropoff_datetime");
		               String score=jsonObject.getString("score");
		               v.add(drivid);
//		               v.add(drivphone);
		               v.add(passid);
//		               v.add(passphone);
		               v.add(money);
		               if(paystate.contentEquals("Y")) {
		             	  v.add("已支付");
		                 }
		               else {
		                 	v.add("未支付");
		                 }
		               v.add(score);
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
//		               System.out.println("价格是："+tripType);
		               sites.add(v);
		               System.out.println(v);	
//				sites.add(v);
				}
		               }}
			
			
		}
   		
   		double amount=0;
   		//解析result
   	
              
//               System.out.println("年龄："+jsonObject.getString("age"));
//               System.out.println("学到的技能："+jsonObject.getJSONArray("sex"));
   		}
//   		amount = (double) Math.round(amount * 100) / 100;
//   		System.out.println("总收入是："+amount);
   		table.close();
   		close();
   		return sites;
   	}
   	
  	

    public static boolean reStr(String content,String year){
        
        String pattern = ".*"+year+".*";
   
        boolean isMatch = Pattern.matches(pattern, content);
        //System.out.println("字符串中是否包含了 '2019' 子字符串? " + isMatch);
        return isMatch;
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
    
   

    public static void main(String[] args) throws IOException {
        //司机
        boolean i =isTableExist("trip");
        //查询订单普通
//        getDataTrip("trip","driv1");
        //查询订单按年
        //getDataTripYear("trip","driv1","trip_info","2019");
      //查询订单按月
        //getDataTripMonth("trip","driv1","trip_info","2019","1");
        //司机信息显示
//        getDataAdmin3("trip","driv_info");
        //修改密码
        getDataTripAll("trip");
        
        //管理员
        //查询个人信息
        //getDataAdmin("trip","temp");//加过滤器
        //test-login
        //i=login("xiaoming","12345681");
        
                                                                                                                                                                                           
        //System.out.println(i);

    }

}
