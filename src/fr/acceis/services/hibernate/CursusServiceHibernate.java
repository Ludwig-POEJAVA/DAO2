package fr.acceis.services.hibernate;

import java.sql.SQLException;

import fr.acceis.services.interfaces.ICursusService;
import fr.acceis.services.model.Cursus;

public class CursusServiceHibernate extends GenericsInheritance<Cursus> implements ICursusService
{

	public CursusServiceHibernate()
	{
		super(Cursus.class);
	}

	public Cursus chercherParId(long idCursus) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

}
