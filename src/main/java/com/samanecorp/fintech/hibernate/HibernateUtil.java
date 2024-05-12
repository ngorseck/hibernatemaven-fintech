package com.samanecorp.fintech.hibernate;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.samanecorp.fintech.config.PropertiesReader;
import com.samanecorp.fintech.entities.DepartmentEntity;
import com.samanecorp.fintech.entities.DeptEmployeeEntity;
import com.samanecorp.fintech.entities.EmployeeEntity;
import com.samanecorp.fintech.entities.UserEntity;


public class HibernateUtil {
	private static SessionFactory sessionFactory;

	private HibernateUtil() {
		
	}
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				PropertiesReader reader = new PropertiesReader("database.properties"); 
								
				Configuration configuration = new Configuration();

 				Properties settings = new Properties();
				settings.put(AvailableSettings.DRIVER, "com.mysql.jdbc.Driver");
				settings.put(AvailableSettings.URL,
						"jdbc:mysql://localhost:3306/mydatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
				settings.put(AvailableSettings.USER, reader.getProperty("db.username"));
				settings.put(AvailableSettings.PASS, reader.getProperty("db.password"));
				settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
				//cette ligne est très importante
				settings.put(AvailableSettings.HBM2DDL_AUTO, "update");
				
				settings.put(AvailableSettings.SHOW_SQL, "true");
				settings.put(AvailableSettings.FORMAT_SQL, "true");

				settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				configuration.setProperties(settings);
				configuration.addAnnotatedClass(DepartmentEntity.class);
				configuration.addAnnotatedClass(EmployeeEntity.class);
				configuration.addAnnotatedClass(DeptEmployeeEntity.class);
				configuration.addAnnotatedClass(UserEntity.class);
 
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
 				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				
 				return sessionFactory;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}