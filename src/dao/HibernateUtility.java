package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtility {
	protected static Configuration configuration = new Configuration().configure();
	protected static ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	protected static SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	protected static Session session;
}
