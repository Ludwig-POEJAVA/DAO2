package fr.acceis.services.hibernate;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import fr.acceis.jpa.HibernateUtil;
import fr.acceis.services.interfaces.IEtudiantService;
import fr.acceis.services.model.Etudiant;

public class EtudiantServiceHibernate extends GenericsInheritance<Etudiant> implements IEtudiantService
{

	public EtudiantServiceHibernate()
	{
		super(Etudiant.class);
	}

	public List<Etudiant> lister() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Session session = HibernateUtil.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Etudiant> query = criteriaBuilder.createQuery(Etudiant.class);
		Root<Etudiant> root = query.from(Etudiant.class);
		query.select(root);
		List<Etudiant> etudiants = session.createQuery(query).getResultList();

		return etudiants;
	}

	public Etudiant chercherParNumeroEtudiant(String numeroEtudiant) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Session session = HibernateUtil.getSession();
		return session.load(Etudiant.class, numeroEtudiant);
	}

	public List<Etudiant> listerEtudiantsParIdCours(long idCours) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

}
