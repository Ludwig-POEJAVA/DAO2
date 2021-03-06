package fr.acceis.services.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.acceis.services.interfaces.IMatiereService;
import fr.acceis.services.model.Cours;
import fr.acceis.services.model.Matiere;
import fr.acceis.services.model.Professeur;

public class MatiereServiceJDBC implements IMatiereService
{
	/* (non-Javadoc)
	 * @see fr.acceis.services.services.IMatiereService#chercherParId(long)
	 */
	public Matiere chercherParId(long idMatiere) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa", "");

		Matiere resultat = null;

		PreparedStatement stmt = connexion.prepareStatement("SELECT nom FROM matiere WHERE matiere.id=?");
		stmt.setLong(1, idMatiere);
		ResultSet result = stmt.executeQuery();
		if (result.next())
		{
			String nomMatiere = result.getString("nom");
			resultat = new Matiere();
			resultat.setNom(nomMatiere);

			List<Cours> listCours = new ArrayList<Cours>();
			List<Professeur> professeurs = new ArrayList<Professeur>();

			Cours cours = new Cours();
			cours.setProfesseurs(professeurs);
			listCours.add(cours);

			resultat.setCours(listCours);

			stmt = connexion.prepareStatement("SELECT distinct professeur.nom as nomProfesseur, professeur.prenom as prenomProfesseur FROM professeur " + "INNER JOIN cours_professeur " + "ON professeur.id = cours_professeur.professeurs_id " + "INNER JOIN cours " + "ON cours.id = cours_professeur.cours_id " + "INNER JOIN matiere " + "ON matiere.id = cours.matiere_id " + "WHERE matiere.id=?");
			stmt.setLong(1, idMatiere);

			result = stmt.executeQuery();

			while (result.next())
			{
				String prenomProfesseur = result.getString("prenomProfesseur");
				String nomProfesseur = result.getString("nomProfesseur");

				Professeur professeur = new Professeur();
				professeur.setNom(nomProfesseur);
				professeur.setPrenom(prenomProfesseur);

				professeurs.add(professeur);
			}
		}

		connexion.close();

		return resultat;
	}

}
