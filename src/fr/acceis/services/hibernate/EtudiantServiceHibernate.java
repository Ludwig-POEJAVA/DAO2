package fr.acceis.services.hibernate;

import java.sql.SQLException;
import java.util.List;

import fr.acceis.services.interfaces.IEtudiantService;
import fr.acceis.services.model.Etudiant;

public class EtudiantServiceHibernate implements IEtudiantService
{

	public List<Etudiant> lister() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Etudiant chercherParNumeroEtudiant(String numeroEtudiant) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public List<Etudiant> listerEtudiantsParIdCours(long idCours) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

}
