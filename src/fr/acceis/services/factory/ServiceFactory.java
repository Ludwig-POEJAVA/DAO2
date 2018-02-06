package fr.acceis.services.factory;

public class ServiceFactory
{
	private static GenericServiceFactory factory = null;

	/**
	 * @param param
	 */
	public static GenericServiceFactory createServiceFactory(String param)
	{
		if (factory != null)
		{
			System.out.println("FAIL EXISTANT");
			//pas cool, factory déja créée
		}
		else if (param == null)
		{
			System.out.println("FAIL null");
			// pas cool, demande imprécise
		}
		else if (param.equals("JDBC"))
		{
			System.out.println("WIN 1");
			ServiceFactory.factory = new ServiceJDBCFactory();
		}
		else if (param.equals("Hibernate"))
		{
			System.out.println("WIN 2");
			ServiceFactory.factory = new ServiceHibernateFactory();
		}
		else
		{
			System.out.println("FAIL G");
			//pas cool, cas imprévu
		}
		return factory;
	}

	public static GenericServiceFactory getFactory()
	{
		return factory;
	}
}
