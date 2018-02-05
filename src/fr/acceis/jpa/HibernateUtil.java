package fr.acceis.jpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{

	private static Session session;

	private static SessionFactory sessionFactory;

	public static Session getSession()
	{
		return getSession("/resources/hibernate.cfg.xml");
	}

	public static Session getResetSession()
	{
		return getSession("/resources/hibernate.cfg-reset.xml");
	}

	public static Session getSession(String configFile)
	{
		if (session == null)
		{
			sessionFactory = new Configuration().configure(configFile).buildSessionFactory();
			session = sessionFactory.openSession();
		}
		return session;
	}

	public static void close()
	{
		if (sessionFactory != null)
		{
			sessionFactory.close();
		}
	}

}
