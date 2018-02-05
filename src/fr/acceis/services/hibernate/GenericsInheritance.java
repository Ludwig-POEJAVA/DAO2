package fr.acceis.services.hibernate;

import java.io.Serializable;
import java.sql.SQLException;

import org.hibernate.Session;

import fr.acceis.jpa.HibernateUtil;

public abstract class GenericsInheritance<T>
{

	private Class<T> clazz;

	public GenericsInheritance(Class<T> clazz)
	{
		this.clazz = clazz;
	}

	@SuppressWarnings({"unchecked", "hiding" })
	public <T> T trouveParId(Object id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Session session = HibernateUtil.getSession();
		return (T) session.load(this.clazz, (Serializable) id);
	}
}
