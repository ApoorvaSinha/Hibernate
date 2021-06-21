package com.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();

			Employee employee = new Employee();//transient
			//employee.setEmpId(101);
			employee.setName("Smith");
			employee.setCity("Mexico");
			
			Employee employee2 = new Employee();
			employee2.setName("Bill");
			employee2.setCity("Texas");
			
			
			session.save(employee2);
			System.out.println(session.save(employee));//persistent
			
			session.delete(session.get(Employee.class, 3));
			System.out.println(employee);

			transaction.commit();

			session.close();//detached
			factory.close();
			
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		} 
	}

}
