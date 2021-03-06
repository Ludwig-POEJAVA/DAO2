package fr.acceis.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.acceis.jpa.HibernateUtil;
import fr.acceis.services.factory.GenericServiceFactory;
import fr.acceis.services.factory.ServiceFactory;
import fr.acceis.services.interfaces.ICoursService;
import fr.acceis.services.interfaces.ICursusService;
import fr.acceis.services.interfaces.IEtudiantService;
import fr.acceis.services.interfaces.IMatiereService;
import fr.acceis.services.interfaces.IProfesseurService;
import fr.acceis.services.interfaces.ISalleService;
import fr.acceis.services.model.Cours;
import fr.acceis.services.model.Cursus;
import fr.acceis.services.model.Etudiant;
import fr.acceis.services.model.Matiere;
import fr.acceis.services.model.Professeur;
import fr.acceis.services.model.Salle;

public class TestJdbc
{
	static GenericServiceFactory factory = ServiceFactory.createServiceFactory("Hibernate");

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception
	{
		try
		{

			listerEtudiants();
			listerProfesseurs();
			listerSalles();
			System.out.println("\t* Liste des cursus d'étudiants :");
			cursusEtudiant("21002127");
			cursusEtudiant("21002128");
			cursusEtudiant("21002129");
			cursusEtudiant("21002130");
			System.out.println("\t* Fin de liste des cursus d'étudiants :");
			System.out.println();
			System.out.println("\t* Liste des salles de cours :");
			for (int numCours = 59; numCours <= 83; numCours += 2)
			{
				salleCours(numCours);
			}
			System.out.println("\t* Fin de liste des salles de cours :");
			System.out.println();
			listerCoursSalle("i57");
			listerEtudiantsCours(67);
			listerProfesseursCursus(10);
			listerProfesseursMatiere(2);
			listerProfsEtudiant("21002127");/**/
			//		emploiDuTempsSalle("i52");
			//		emploiDuTempsEtudiant("21002128");
			//		emploiDuTempsProfesseur(55);
			System.out.println("ez pz lmn sqz");

		}
		catch (Exception e)
		{
			System.out.println("##################################");
			System.out.println("j'ai glissé chef");
			System.out.println("##################################");
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Fini à " + new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds());
			HibernateUtil.close();
		}
		HibernateUtil.close();
	}

	//	Liste les étudiants
	private static void listerEtudiants() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		IEtudiantService etudiantService = factory.getEtudiantService();
		List<Etudiant> listeEtudiants = etudiantService.lister();

		System.out.println("\t* Liste des étudiants :");
		for (Etudiant etudiant: listeEtudiants)
		{
			String prenom = etudiant.getPrenom();
			String nom = etudiant.getNom();
			String numeroEtudiant = etudiant.getNumeroEtudiant();
			System.out.println("\t\t" + prenom + " " + nom + " (" + numeroEtudiant + ")");
		}

		System.out.println("\t* Fin de la liste des étudiants :");
		System.out.println();
	}

	//	Liste les professeurs
	private static void listerProfesseurs() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		IProfesseurService professeurService = factory.getProfesseurService();
		List<Professeur> listeProfesseurs = professeurService.lister();

		System.out.println("\t* Liste des professeurs :");
		for (Professeur professeur: listeProfesseurs)
		{
			String prenom = professeur.getPrenom();
			String nom = professeur.getNom();
			System.out.println("\t\tPr. " + prenom + " " + nom);
		}
		System.out.println("\t* Fin de la liste des professeurs :");
		System.out.println();

	}

	//	Liste les salles
	private static void listerSalles() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		ISalleService salleService = factory.getSalleService();
		List<Salle> listeSalles = salleService.lister();

		System.out.println("\t* Liste des salles :");
		for (Salle salle: listeSalles)
		{
			String nom = salle.getNom();
			System.out.println("\t\tSalle " + nom);
		}
		System.out.println("\t* Fin de la liste des salles :");
		System.out.println();
	}

	//	Affiche le nom du cursus d'un étudiant
	private static void cursusEtudiant(String numeroEtudiant) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		IEtudiantService etudiantService = factory.getEtudiantService();
		Etudiant etudiant = etudiantService.chercherParNumeroEtudiant(numeroEtudiant);

		if (etudiant != null)
		{
			String nomCursus = etudiant.getCursus().getNom();
			String prenomEtudiant = etudiant.getPrenom();
			String nomEtudiant = etudiant.getNom();
			String numero = etudiant.getNumeroEtudiant();
			System.out.println("\t\t" + prenomEtudiant + " " + nomEtudiant + " (" + numero + ") est inscrit dans le cursus " + nomCursus);
		}

	}

	//	Affiche le nom de la salle dans laquelle a lieu un cours
	private static void salleCours(long idCours) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//ICoursService coursService = new CoursServiceHibernate();
		ICoursService coursService = factory.getCoursService();
		Cours cours = coursService.chercherParId(idCours);

		if (cours != null)
		{
			String nom = cours.getCreneau().getSalle().getNom();
			System.out.println("\t\tLe cours " + idCours + " aura lieu en salle " + nom);
		}
	}

	//	Affiche le nom des cours ayant lieu dans une salle
	private static void listerCoursSalle(String nomSalle) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		ICoursService coursService = factory.getCoursService();
		List<Cours> listeCours = coursService.listerParSalle(nomSalle);

		SimpleDateFormat formater = new SimpleDateFormat("dd\\MM\\yyyy à HH:mm");
		System.out.println("\t* Les cours suivants ont lieu en salle " + nomSalle + " :\n");

		for (Cours cours: listeCours)
		{
			String nomMatiere = cours.getMatiere().getNom();
			String prenomProfesseur = cours.getProfesseurs().iterator().next().getPrenom();
			String nomProfesseur = cours.getProfesseurs().iterator().next().getNom();
			Date horaireDebut = cours.getCreneau().getHoraire().getDebut();
			Date horaireFin = cours.getCreneau().getHoraire().getFin();
			System.out.println("\t\t------------------------------");
			System.out.println("\t\tCours de " + nomMatiere);
			System.out.println("\t\tAssuré par Pr. " + prenomProfesseur + " " + nomProfesseur);
			System.out.println("\t\tEn salle " + nomSalle);
			System.out.println("\t\tDébut : " + formater.format(horaireDebut));
			System.out.println("\t\tFin : " + formater.format(horaireFin));
			System.out.println("\t\t------------------------------");
		}
		System.out.println("\t* Fin de la liste des cours");
		System.out.println();

	}

	//	Affiche le nom des étudiants qui assistent à un cours
	private static void listerEtudiantsCours(long idCours) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		IEtudiantService etudiantService = factory.getEtudiantService();
		List<Etudiant> etudiants = etudiantService.listerEtudiantsParIdCours(idCours);

		System.out.println("\t* Les étudiants suivants assisteront au cours " + idCours + " :");
		for (Etudiant etudiant: etudiants)
		{
			String prenomEtudiant = etudiant.getPrenom();
			String nomEtudiant = etudiant.getNom();
			String numero = etudiant.getNumeroEtudiant();

			System.out.println("\t\t" + prenomEtudiant + " " + nomEtudiant + " (" + numero + ")");
		}
		System.out.println("\t* Fin de la liste des étudiants");
		System.out.println();
	}

	//	Affiche le nom des professeurss qui enseignent dans un cursus
	private static void listerProfesseursCursus(long idCursus) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		ICursusService cursusService = factory.getCursusService();
		Cursus cursus = cursusService.chercherParId(idCursus);

		System.out.println("* Les professeurs suivants enseignent au sein du cursus " + cursus.getNom() + " :");
		for (Matiere matiere: cursus.getMatieres())
		{
			for (Cours cours: matiere.getCours())
			{
				for (Professeur professeur: cours.getProfesseurs())
				{
					String prenomProfesseur = professeur.getPrenom();
					String nomProfesseur = professeur.getNom();
					System.out.println("Pr. " + prenomProfesseur + " " + nomProfesseur);
				}
			}
		}

		System.out.println("* Fin de la liste des professeurs");
		System.out.println();
	}

	//	Affiche le nom des professeurs qui enseignent une matière
	private static void listerProfesseursMatiere(long idMatiere) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		IMatiereService matiereService = factory.getMatiereService();
		Matiere matiere = matiereService.chercherParId(idMatiere);

		String nomMatiere = matiere.getNom();
		System.out.println("* Les professeurs suivants enseignent la matière \"" + nomMatiere + "\" :");
		for (Cours cours: matiere.getCours())
		{
			for (Professeur professeur: cours.getProfesseurs())
			{
				String prenomProfesseur = professeur.getPrenom();
				String nomProfesseur = professeur.getNom();
				System.out.println("Pr. " + prenomProfesseur + " " + nomProfesseur);
			}
		}
		System.out.println("* Fin de la liste des professeurs");
		System.out.println();
	}

	//	Affiche des profs qui enseignent à un étudiant
	private static void listerProfsEtudiant(String numeroEtudiant) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		IEtudiantService etudiantService = factory.getEtudiantService();
		Etudiant etudiant = etudiantService.chercherParNumeroEtudiant(numeroEtudiant);
		if (etudiant != null)
		{
			long idCursus = etudiant.getCursus().getId();
			Cursus cursus = factory.getCursusService().chercherParId(idCursus);

			System.out.println("* Les professeurs suivants enseignent à l'étudiant " + etudiant.getPrenom() + " " + etudiant.getNom() + " :");
			for (Matiere matiere: cursus.getMatieres())
			{
				for (Cours cours: matiere.getCours())
				{
					for (Professeur professeur: cours.getProfesseurs())
					{
						String prenomProfesseur = professeur.getPrenom();
						String nomProfesseur = professeur.getNom();
						System.out.println("Pr. " + prenomProfesseur + " " + nomProfesseur);
					}
				}
			}

			System.out.println("* Fin de la liste des professeurs");
			System.out.println();
		}
	}

	//	Affiche l'emploi du temps d'une salle
	@SuppressWarnings("unused")
	private static void emploiDuTempsSalle(String nomSalle) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa", "");
		PreparedStatement stmt = connexion.prepareStatement("SELECT matiere.nom as nomMatiere, professeur.nom as nomProfesseur, professeur.prenom as prenomProfesseur, salle.nom as nomSalle, horaire.debut as horaireDebut, horaire.fin as horaireFin FROM horaire " + "INNER JOIN creneau " + "ON creneau.horaire_id = horaire.id " + "INNER JOIN salle " + "ON salle.id = creneau.salle_id " + "INNER JOIN cours " + "ON cours.id = creneau.cours_id " + "INNER JOIN matiere " + "ON matiere.id = cours.matiere_id " + "INNER JOIN cours_professeur " + "ON cours_professeur.cours_id = cours.id " + "INNER JOIN professeur " + "ON professeur.id = cours_professeur.professeurs_id " + "WHERE salle.nom=?");
		stmt.setString(1, nomSalle);

		ResultSet result = stmt.executeQuery();

		SimpleDateFormat formater = new SimpleDateFormat("dd\\MM\\yyyy à HH:mm");
		System.out.println("Emploi du temps de la salle " + nomSalle + " :\n");

		while (result.next())
		{
			String nomMatiere = result.getString("nomMatiere");
			String prenomProfesseur = result.getString("prenomProfesseur");
			String nomProfesseur = result.getString("nomProfesseur");
			Time horaireDebut = result.getTime("horaireDebut");
			Time horaireFin = result.getTime("horaireFin");
			System.out.println("------------------------------");
			System.out.println("Cours de " + nomMatiere);
			System.out.println("Assuré par Pr. " + prenomProfesseur + " " + nomProfesseur);
			System.out.println("En salle " + nomSalle);
			System.out.println("Début : " + formater.format(horaireDebut));
			System.out.println("Fin : " + formater.format(horaireFin));
			System.out.println("------------------------------");
		}
		System.out.println("Fin de l'emploi du temps");
		System.out.println();

		connexion.close();
	}

	//	Affiche l'emploi du temps d'un étudiant
	@SuppressWarnings("unused")
	private static void emploiDuTempsEtudiant(String numeroEtudiant) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa", "");

		String prenomEtudiant = "";
		String nomEtudiant = "";

		PreparedStatement stmt = connexion.prepareStatement("SELECT nom, prenom, numeroEtudiant FROM etudiant WHERE numeroEtudiant=?");
		stmt.setString(1, numeroEtudiant);
		ResultSet result = stmt.executeQuery();
		while (result.next())
		{
			nomEtudiant = result.getString("nom");
			prenomEtudiant = result.getString("prenom");
		}

		stmt = connexion.prepareStatement("SELECT matiere.nom as nomMatiere, professeur.nom as nomProfesseur, professeur.prenom as prenomProfesseur, salle.nom as nomSalle, horaire.debut as horaireDebut, horaire.fin as horaireFin, salle.nom as nomSalle FROM horaire " + "INNER JOIN creneau " + "ON creneau.horaire_id = horaire.id " + "INNER JOIN salle " + "ON salle.id = creneau.salle_id " + "INNER JOIN cours " + "ON cours.id = creneau.cours_id " + "INNER JOIN matiere " + "ON matiere.id = cours.matiere_id " + "INNER JOIN cours_professeur " + "ON cours_professeur.cours_id = cours.id " + "INNER JOIN professeur " + "ON professeur.id = cours_professeur.professeurs_id " + "INNER JOIN cursus_matiere " + "ON cursus_matiere.matieres_id = matiere.id " + "INNER JOIN cursus " + "ON cursus.id = cursus_matiere.cursus_id " + "INNER JOIN etudiant " + "ON cursus.id = etudiant.cursus_id " + "WHERE etudiant.numeroEtudiant=?");
		stmt.setString(1, numeroEtudiant);

		result = stmt.executeQuery();

		SimpleDateFormat formater = new SimpleDateFormat("dd\\MM\\yyyy à HH:mm");
		System.out.println("Emploi du temps de l'étudiant " + prenomEtudiant + " " + nomEtudiant + " (" + numeroEtudiant + ") : \n");

		while (result.next())
		{
			String nomMatiere = result.getString("nomMatiere");
			String nomSalle = result.getString("nomSalle");
			String prenomProfesseur = result.getString("prenomProfesseur");
			String nomProfesseur = result.getString("nomProfesseur");
			Time horaireDebut = result.getTime("horaireDebut");
			Time horaireFin = result.getTime("horaireFin");
			System.out.println("------------------------------");
			System.out.println("Cours de " + nomMatiere);
			System.out.println("Assuré par Pr. " + prenomProfesseur + " " + nomProfesseur);
			System.out.println("En salle " + nomSalle);
			System.out.println("Début : " + formater.format(horaireDebut));
			System.out.println("Fin : " + formater.format(horaireFin));
			System.out.println("------------------------------");
		}

		System.out.println("Fin de l'emploi du temps");
		System.out.println();

		connexion.close();
	}

	//	Affiche l'emploi du temps d'un professeur
	@SuppressWarnings("unused")
	private static void emploiDuTempsProfesseur(long idProfesseur) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa", "");

		String prenomProfesseur = "";
		String nomProfesseur = "";

		PreparedStatement stmt = connexion.prepareStatement("SELECT nom, prenom FROM professeur WHERE id=?");
		stmt.setLong(1, idProfesseur);
		ResultSet result = stmt.executeQuery();
		while (result.next())
		{
			nomProfesseur = result.getString("nom");
			prenomProfesseur = result.getString("prenom");
		}

		stmt = connexion.prepareStatement("SELECT matiere.nom as nomMatiere, salle.nom as nomSalle, horaire.debut as horaireDebut, horaire.fin as horaireFin, salle.nom as nomSalle FROM horaire " + "INNER JOIN creneau " + "ON creneau.horaire_id = horaire.id " + "INNER JOIN salle " + "ON salle.id = creneau.salle_id " + "INNER JOIN cours " + "ON cours.id = creneau.cours_id " + "INNER JOIN matiere " + "ON matiere.id = cours.matiere_id " + "INNER JOIN cours_professeur " + "ON cours_professeur.cours_id = cours.id " + "INNER JOIN professeur " + "ON professeur.id = cours_professeur.professeurs_id " + "WHERE professeur.id=?");
		stmt.setLong(1, idProfesseur);

		result = stmt.executeQuery();

		SimpleDateFormat formater = new SimpleDateFormat("dd\\MM\\yyyy à HH:mm");
		System.out.println("Emploi du temps du Pr. " + prenomProfesseur + " " + nomProfesseur + ") : \n");

		while (result.next())
		{
			String nomMatiere = result.getString("nomMatiere");
			String nomSalle = result.getString("nomSalle");
			Time horaireDebut = result.getTime("horaireDebut");
			Time horaireFin = result.getTime("horaireFin");
			System.out.println("------------------------------");
			System.out.println("Cours de " + nomMatiere);
			System.out.println("En salle " + nomSalle);
			System.out.println("Début : " + formater.format(horaireDebut));
			System.out.println("Fin : " + formater.format(horaireFin));
			System.out.println("------------------------------");
		}

		System.out.println("Fin de l'emploi du temps");
		System.out.println();

		connexion.close();
	}

}
