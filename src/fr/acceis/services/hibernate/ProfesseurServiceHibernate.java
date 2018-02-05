package fr.acceis.services.hibernate;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import fr.acceis.jpa.HibernateUtil;
import fr.acceis.services.interfaces.IProfesseurService;
import fr.acceis.services.model.Professeur;

public class ProfesseurServiceHibernate extends GenericsInheritance<Professeur> implements IProfesseurService
{

	public ProfesseurServiceHibernate()
	{
		super(Professeur.class);
	}

	public List<Professeur> lister() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Session session = HibernateUtil.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Professeur> query = criteriaBuilder.createQuery(Professeur.class);
		Root<Professeur> root = query.from(Professeur.class);
		query.select(root);
		List<Professeur> professeurs = session.createQuery(query).getResultList();

		return professeurs;
	}

}
