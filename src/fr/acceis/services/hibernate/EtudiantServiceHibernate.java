package fr.acceis.services.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import fr.acceis.jpa.HibernateUtil;
import fr.acceis.services.interfaces.ICoursService;
import fr.acceis.services.interfaces.IEtudiantService;
import fr.acceis.services.interfaces.IMatiereService;
import fr.acceis.services.model.Cours;
import fr.acceis.services.model.Cursus;
import fr.acceis.services.model.Etudiant;
import fr.acceis.services.model.Matiere;

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
		//Session session = HibernateUtil.getSession();
		//return session.load(Etudiant.class, numeroEtudiant);
		return this.trouveParId(numeroEtudiant);
	}

	public List<Etudiant> listerEtudiantsParIdCours(long idCours) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{

		ICoursService coursService = new CoursServiceHibernate();
		Cours cours = coursService.chercherParId(idCours);

		if (cours == null)
		{
			System.out.println("debug : cours == null");
		}
		else
		{
			System.out.println("debug : cours != null");
		}

		System.out.println("debug : trouvé cours avec id " + idCours);
		System.out.println("debug : la matiere du cours est la matiere   " + cours.getMatiere().getNom());
		System.out.println("debug : la matiere du cours est la matiere avec id " + cours.getMatiere().getId());

		IMatiereService matiereService = new MatiereServiceHibernate();
		Matiere matiere = matiereService.chercherParId(cours.getMatiere().getId());

		System.out.println("debug : trouvé matiere avec id " + matiere.getId() + " et nom " + matiere.getNom());

		Collection<Cursus> cursuses = matiere.getCursus();

		List<Etudiant> etudiants = new ArrayList<Etudiant>();

		for (Cursus c: cursuses)
		{
			System.out.println("debug : \t\t\tun cursus nommé " + c.getNom());
			Collection<Etudiant> etudiantsDuCursus = c.getEtudiants();
			for (Etudiant e: etudiantsDuCursus)
			{
				System.out.println("debug : \t\t\t\tun étudiant nommé " + e.getPrenom() + " " + e.getNom());
				etudiants.add(e);
			}
		}

		return etudiants;
	}

}
