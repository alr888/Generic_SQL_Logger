import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Properties;

//import java.io.FileInputStream;
//import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class EIPSDRLogger {
	
	final static Logger logger = Logger.getLogger(EIPSDRLogger.class);

	public static void main(String args[]) throws Exception {
		// TODO Auto-generated method stub
		
		 Connection conn1 = null;
		 
		 Properties prop = new Properties();
		 
		 Properties propil = new Properties();
		 //InputStream input = null;

		 EIPSDRLogger obj = new EIPSDRLogger();

		 
		 
		 
		 
		 try { 
			 

			 
//			 String basePath = EIPSDRLogger.class.getResource("/").getPath();
//			 InputStream in = new FileInputStream(basePath + "config.properties");
			 InputStream in = EIPSDRLogger.class.getResourceAsStream("config.properties");
			 InputStream il = EIPSDRLogger.class.getResourceAsStream("log4j.properties");
			 prop.load(in);
			 propil.load(il);
			 String values = prop.getProperty("values");
			 String db = prop.getProperty("db");
			 //String table =  prop.getProperty("table");
			 String sqlstart =  prop.getProperty("sqlstart");
			 String sqlend =  prop.getProperty("sqlend");
		
			 PropertyConfigurator.configure(propil);
			 
			 //System.out.println(sqlstart + sqlend);
			 
			 
			 int index;

		        for (index = 0; index < args.length; ++index)
		        {
		            System.out.println("args[" + index + "]: " + args[index]);
		            if (args[index].length() > 0) {
		            	values = values + args[index];
		            }
		            if (args[3].length() > 0) {
			        	obj.runMe(args[3]);
		            } else {
		            	obj.runMe("No filename parameter provided.");
		            }
		            
		        }
			 
		      
			 
			    Class.forName("oracle.jdbc.OracleDriver");
			    String url = "jdbc:oracle:thin:"  + db + "";
	            conn1 = DriverManager.getConnection(url);
			    if (conn1 != null) {
	                System.out.println("Connected with connection #1: " + values);
	            }
	            Statement st = conn1.createStatement(); 
	            
	            String SQL = sqlstart + values + sqlend;
	            /*
	            if (values.length() > 0 && sqlend.length() > 0 ) {
	            	st.executeUpdate(sqlstart + values + sqlend);
	            } else {
	            	st.executeUpdate(sqlstart);
	            }
	            */
	            
	            System.out.println(SQL);
	            
	        	st.executeUpdate(SQL);
	            
	            //st.executeUpdate(sqlstart + values + sqlend);
	            //st.executeUpdate( "INSERT INTO " + table + " VALUES ('" + user + "')"); 
	            st.executeUpdate("commit"); 
	            
		 	}	 catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	            logger.error("Sorry, something wrong class not found!", ex);
	        } catch (SQLException ex) {
	        	ex.printStackTrace();
	            logger.error("Sorry SQL error, something wrong!", ex);
	        } finally {
	            try {
	                if (conn1 != null && !conn1.isClosed()) {
	                    conn1.close();
	                }
	        
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                logger.error("Sorry SQL Connection error, something wrong!", ex);
	            }
	        }
		
		
	}
	
	private void runMe(String parameter){

	/*
		if(logger.isDebugEnabled()){
			logger.debug("This is debug : " + parameter);
		}

		if(logger.isInfoEnabled()){
			logger.info("This is info : " + parameter);
		}
    */
		logger.info("SDRLogger recorded delivery of : " + parameter);
	//	logger.error("This is error : " + parameter);
	//	logger.fatal("This is fatal : " + parameter);

	}
   
}
