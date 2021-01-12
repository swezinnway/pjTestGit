package com.bestbright.hibernate.onetomany;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.bestbright.hibernate.onetomany.model.Department;
import com.bestbright.hibernate.onetomany.model.Employee;
import com.bestbright.hibernate.onetomany.model.Exam;

/**
 * Hello world!
 *
 */
public class App 
{
	static Session sessionObj;
    static SessionFactory sessionFactoryObj;
    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
    	try {
    		StandardServiceRegistry standardRegistry = 
    		new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    		Metadata metaData = 
    		new MetadataSources(standardRegistry).getMetadataBuilder().build();
    		sessionFactoryObj = metaData.getSessionFactoryBuilder().build();
    		} catch (Throwable th) {

    			System.err.println("Enitial SessionFactory creation failed" + th);
    			throw new ExceptionInInitializerError(th);

    		}
        return sessionFactoryObj;
    }
 
    public static void main( String[] args )
    {
    	System.out.println(".......Hibernate One To Many Mapping Example.......\n");
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();
          /*  Exam e1=new Exam("MidTerm Exam",100);
            sessionObj.save(e1);
            */
            Department d1 = new Department("IT Department");
            Employee e2 = new Employee("Ei","Ei","09799");  
           
            Employee e1 = new Employee("Soe","Soe","09256195"); 
            Set<Employee> empList=new HashSet<Employee>();
            empList.add(e1);
            empList.add(e2);
            d1.setEmployeeList(empList);
            sessionObj.save(d1);
            
            /*Employee e1 = new Employee("Thiri","Taw","09256195",d1);  
         
            sessionObj.save(e1);
 
            Employee e2 = new Employee("Mi","Mi","09799",d1);  
           
            sessionObj.save(e2);
            */
 
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
 
            System.out.println("\n.......Records Saved Successfully To The Database.......");
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }
}
