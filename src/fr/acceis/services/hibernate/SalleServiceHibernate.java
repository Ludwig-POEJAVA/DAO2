package fr.acceis.services.hibernate;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import fr.acceis.jpa.HibernateUtil;
import fr.acceis.services.interfaces.ISalleService;
import fr.acceis.services.model.Salle;

public class SalleServiceHibernate extends GenericsInheritance<Salle> implements ISalleService
{

	public SalleServiceHibernate()
	{
		super(Salle.class);
	}

	public List<Salle> lister() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Session session = HibernateUtil.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Salle> query = criteriaBuilder.createQuery(Salle.class);
		Root<Salle> root = query.from(Salle.class);
		query.select(root);
		List<Salle> salles = session.createQuery(query).getResultList();

		return salles;
	}

}
