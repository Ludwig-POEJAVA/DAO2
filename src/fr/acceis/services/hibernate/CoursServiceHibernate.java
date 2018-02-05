package fr.acceis.services.hibernate;

import java.sql.SQLException;
import java.util.List;

import fr.acceis.services.interfaces.ICoursService;
import fr.acceis.services.model.Cours;

public class CoursServiceHibernate extends GenericsInheritance<Cours> implements ICoursService
{

	public CoursServiceHibernate()
	{
		super(Cours.class);
	}

	public Cours chercherParId(long idCours) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{

		return this.trouveParId(idCours);
	}

	public List<Cours> listerParSalle(String nomSalle) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

}
