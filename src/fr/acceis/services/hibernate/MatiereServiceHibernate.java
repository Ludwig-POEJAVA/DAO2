package fr.acceis.services.hibernate;

import java.sql.SQLException;

import fr.acceis.services.interfaces.IMatiereService;
import fr.acceis.services.model.Matiere;

public class MatiereServiceHibernate extends GenericsInheritance<Matiere> implements IMatiereService
{

	public MatiereServiceHibernate()
	{
		super(Matiere.class);
	}

	public Matiere chercherParId(long idMatiere) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		return this.trouveParId(idMatiere);
	}

}
