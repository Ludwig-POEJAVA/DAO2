package fr.acceis.services.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.acceis.services.hibernate.GenericsInheritance;
import fr.acceis.services.interfaces.ICoursService;
import fr.acceis.services.model.Cours;
import fr.acceis.services.model.Creneau;
import fr.acceis.services.model.Horaire;
import fr.acceis.services.model.Matiere;
import fr.acceis.services.model.Professeur;
import fr.acceis.services.model.Salle;

public class CoursService extends GenericsInheritance<Cours> implements ICoursService
{

	public CoursService()
	{
		super(Cours.class);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.acceis.services.services.ICoursService#chercherParId(long)
	 */
	public Cours chercherParId(long idCours) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa", "");
		PreparedStatement stmt = connexion.prepareStatement("SELECT nom FROM salle " + "INNER JOIN creneau " + "ON creneau.salle_id = salle.id " + "INNER JOIN cours " + "ON cours.id = creneau.cours_id " + "WHERE cours.id=?");
		stmt.setLong(1, idCours);

		ResultSet result = stmt.executeQuery();

		Cours cours = null;

		if (result.next())
		{
			String nom = result.getString("nom");

			cours = new Cours();
			Salle salle = new Salle();
			salle.setNom(nom);

			Creneau creneau = new Creneau();
			creneau.setSalle(salle);

			cours.setCreneau(creneau);
		}

		connexion.close();

		return cours;
	}

	/* (non-Javadoc)
	 * @see fr.acceis.services.services.ICoursService#listerParSalle(java.lang.String)
	 */
	public List<Cours> listerParSalle(String nomSalle) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa", "");
		PreparedStatement stmt = connexion.prepareStatement("SELECT matiere.nom as nomMatiere, professeur.nom as nomProfesseur, professeur.prenom as prenomProfesseur, salle.nom as nomSalle, horaire.debut as horaireDebut, horaire.fin as horaireFin FROM horaire " + "INNER JOIN creneau " + "ON creneau.horaire_id = horaire.id " + "INNER JOIN salle " + "ON salle.id = creneau.salle_id " + "INNER JOIN cours " + "ON cours.id = creneau.cours_id " + "INNER JOIN matiere " + "ON matiere.id = cours.matiere_id " + "INNER JOIN cours_professeur " + "ON cours_professeur.cours_id = cours.id " + "INNER JOIN professeur " + "ON professeur.id = cours_professeur.professeurs_id " + "WHERE salle.nom=?");
		stmt.setString(1, nomSalle);

		ResultSet result = stmt.executeQuery();

		List<Cours> resultat = new ArrayList<Cours>();

		while (result.next())
		{
			String nomMatiere = result.getString("nomMatiere");
			String prenomProfesseur = result.getString("prenomProfesseur");
			String nomProfesseur = result.getString("nomProfesseur");
			Date horaireDebut = result.getDate("horaireDebut");
			Date horaireFin = result.getDate("horaireFin");

			Matiere matiere = new Matiere();
			matiere.setNom(nomMatiere);

			List<Professeur> professeurs = new ArrayList<Professeur>();
			Professeur professeur = new Professeur();
			professeur.setNom(nomProfesseur);
			professeur.setPrenom(prenomProfesseur);
			professeurs.add(professeur);

			Creneau creneau = new Creneau();
			Horaire horaire = new Horaire();
			horaire.setDebut(horaireDebut);
			horaire.setFin(horaireFin);
			creneau.setHoraire(horaire);

			Cours cours = new Cours();
			cours.setCreneau(creneau);
			cours.setMatiere(matiere);
			cours.setProfesseurs(professeurs);

			resultat.add(cours);
		}
		connexion.close();

		return resultat;

	}

}
