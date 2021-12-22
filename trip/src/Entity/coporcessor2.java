package Entity;

import java.io.IOException;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.hbase.client.coprocessor.LongColumnInterpreter;
import org.apache.hadoop.hbase.util.Bytes;

public class coporcessor2 {
	public static Configuration configuration;
    public static Connection connection;
    public static Admin admin;

    public static void main(String[] args) throws IOException {
    	try {
			int k=getrowcount("pass_info");
			System.out.println("rowcount:" + k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    public static int getrowcount(String familyname){	
        init();
        try {
			Table table = connection.getTable(TableName.valueOf("trip"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}	        
        AggregationClient aggregationClient = new AggregationClient(configuration);
        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes(familyname),Bytes.toBytes(""));
        Date start = new Date();
    	long rowCount = 0;
		try {
			rowCount = aggregationClient.rowCount(TableName.valueOf("trip"), new LongColumnInterpreter(), scan);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Date end = new Date();
        System.out.println("timecost:" + (end.getTime() - start.getTime()));
        close();
        return (int) rowCount;
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
//disable 'trip'
// alter 'trip', METHOD => 'table_att','coprocessor'=>'|org.apache.hadoop.hbase.coprocessor.AggregateImplementation||'
//enable 'trip'