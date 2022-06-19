package listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class driverListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("db드라이버 로딩....");
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} 
    }
	
}
