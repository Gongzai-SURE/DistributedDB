package Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
//import org.apache.hadoop.hbase.CompareOperator;
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
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.util.Bytes;

public class coporcessor3 {
	public static Configuration configuration;
    public static Connection connection;
    public static Admin admin;

    public static void main(String[] args) throws IOException {
    	int o = gettripcount();
    }
    public static int gettripcount() throws IOException {
        init();
        Table table;
		table = connection.getTable(TableName.valueOf("trip"));
		Scan scan = new Scan();
        @SuppressWarnings("deprecation")
        org.apache.hadoop.hbase.filter.Filter familyFilter = new FamilyFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("driv_info")));
        scan.setFilter(familyFilter);  
        //获取数据
        ResultScanner resultscanner;
		resultscanner = table.getScanner(scan);
		ArrayList<Vector> sites = new ArrayList<Vector>();
        Vector v=new Vector();
        for (Result result:resultscanner) {
         //打印数据   
	         List<Cell> cells=result.listCells();
	         String rk="";
	         for(Cell cell:cells) {
	        	 String rk1=new String(CellUtil.cloneRow(cell));          
	        	 //System.out.println(new String(CellUtil.cloneQualifier(cell)) + ":" + new String(CellUtil.cloneValue(cell)));
	        	 if((rk1.contentEquals(rk))==false) {
	        		 //System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
	        		 rk=rk1;
	        		 v.add(new String(rk));
	        	 	}}
	         	sites.add(v);
	    }
        int i =0;
        Date start = new Date();
        for(int k=0;k<v.size();k++){
        	String rk = (String) v.get(k);
        	Get get = new Get(Bytes.toBytes(rk));
        	Result result;
			result = table.get(get);
			Map<byte[], byte[]> familyMap = result.getFamilyMap(Bytes.toBytes("trip_info"));
        	for(Map.Entry<byte[], byte[]> entry:familyMap.entrySet()){
        		//System.out.println(Bytes.toString(entry.getKey()));
            	i=i+1;}
	    }      
        Date end = new Date();
    	//System.out.println("clounmcount:" + i);
    	//System.out.println("timecost:" + (end.getTime() - start.getTime()));
        close();
        return i;
    }

    public static void init(){
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.rootdir","hdfs://localhost:8020/hbase");
        try{
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void close(){
        try{
            if(admin != null){
                admin.close();
            }
            if(null != connection){
                connection.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
      }
}
