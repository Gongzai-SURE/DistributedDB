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
import java.util.List;
//import java.util.Scanner;


public class Test {
	public static Configuration configuration;
    public static Connection connection;
    public static Admin admin;
    public static String rk;
    //judge table exist
	public static boolean isTableExist(String tableName) throws IOException {
		init();
		boolean exists = admin.tableExists(TableName.valueOf(tableName));
        //table.close();
        close();
		return exists;
	}
	
	public static void getDataAdmin(String tableName,String etype) throws IOException {
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
		for (Result result:resultscanner) {
			//打印数据			
			List<Cell> cells=result.listCells();
			for(Cell cell:cells) {
				System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
				System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
			}           
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
    
    public static boolean login(String cf,String un,String psw) throws IOException {
        init();
        // 获取表
        Table table = connection.getTable(TableName.valueOf("trip"));
        
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
  		if (rk.contentEquals("")) {
  			System.out.println("hell");
  			return false;
  		}
  		System.out.println(rk);
  	//创建get对象
  			Get get =new Get(Bytes.toBytes(rk));
  			
  			get.addColumn(Bytes.toBytes(cf), Bytes.toBytes("password"));
  			
  			//获取数据
  			Result result=table.get(get);
  			
  			
  			//解析result
  			for (Cell cell:result.rawCells()) {
  				//打印数据
//  				System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
//  	            System.out.println("Timetamp:" + cell.getTimestamp() + " ");
//  	            System.out.println("column Family:" + new String(CellUtil.cloneFamily(cell)) + " ");
//  	            System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
//  	            System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
  	            String data=new String(CellUtil.cloneValue(cell));
  	            System.out.println(data);
  	            if(data.contentEquals(psw)) {
  	            	return true;
  	            }
  			}  		

        table.close();
        close();
        return false;
    }
    
   
    public static boolean reStr(String content,String year){
        
        String pattern = ".*"+year+".*";
   
        boolean isMatch = Pattern.matches(pattern, content);
        //System.out.println("字符串中是否包含了 '2019' 子字符串? " + isMatch);
        return isMatch;
     }       
    
    
    public static void main(String[] args) throws IOException {
        //司机
        boolean i =isTableExist("trip");
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

//	public int isDrivPass() {
//		// TODO Auto-generated method stub
//		init();
//        // 获取表
//        Table table = connection.getTable(TableName.valueOf("trip"));
//        
//        //创建scan对象
//        @SuppressWarnings("deprecation")
//		Scan scan = new Scan();
//  		@SuppressWarnings("deprecation")
//		//Scan scan=new Scan();		
//		//BinaryPrefixComparator匹配开头，前缀二进制比较器
//  		QualifierFilter qualifierFilter = new QualifierFilter(CompareOperator.EQUAL,new BinaryComparator(Bytes.toBytes("nametemp")));		
//  		scan.setFilter(qualifierFilter);	
//		rk=new String("");
//  		//获取数据
//  		ResultScanner resultscanner=table.getScanner(scan);		
//  		//解析result
//  		for (Result result:resultscanner) {
//  			//打印数据			
//  			List<Cell> cells=result.listCells();
//  			for(Cell cell:cells) {
//  				String namert=new String(CellUtil.cloneValue(cell));//rowkey
//  				if(namert.contentEquals(un)) {
//  					rk=new String(CellUtil.cloneRow(cell));
//  					break;
//  				}
//  				//System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
////  				System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
//  				
//  			}           
//  		}
//		return 0;
//	}
	public int isDrivPass(String un) throws IOException {
        init();
        // 获取表
        Table table = connection.getTable(TableName.valueOf("trip"));
        
        //创建scan对象
        Scan scan = new Scan();
  		@SuppressWarnings("deprecation")
		//Scan scan=new Scan();		
		//BinaryPrefixComparator匹配开头，前缀二进制比较器
  		QualifierFilter qualifierFilter = new QualifierFilter(CompareOperator.EQUAL,new BinaryComparator(Bytes.toBytes("nametemp")));		
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
  				if(namert.contentEquals(un)) {
  					rk=new String(CellUtil.cloneRow(cell));
  					break;
  				}
  				//System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
//  				System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
  				
  			}           
  		}
  		if (rk.contentEquals("")) {
  			System.out.println("hell");
  			return 0;
  		}
  		System.out.println(rk);
  	//创建get对象
  			Get get =new Get(Bytes.toBytes(rk));
  			
  			get.addColumn(Bytes.toBytes("temp"), Bytes.toBytes("state"));
  			
  			//获取数据
  			Result result=table.get(get);
  			
  			
  			//解析result
  			for (Cell cell:result.rawCells()) {
  				//打印数据
//  				System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
//  	            System.out.println("Timetamp:" + cell.getTimestamp() + " ");
//  	            System.out.println("column Family:" + new String(CellUtil.cloneFamily(cell)) + " ");
//  	            System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
//  	            System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
  	            String data=new String(CellUtil.cloneValue(cell));
  	            
  	            if(data.contentEquals("N")) {
  	            	return 2;
  	            }
  	            else {
  	            	return 1;
  	            }
  			}  		

        table.close();
        close();
        return 0;
    }
    
}
