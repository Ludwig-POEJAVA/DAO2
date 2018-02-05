package fr.acceis.services.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.acceis.services.interfaces.IProfesseurService;
import fr.acceis.services.model.Professeur;

public class ProfesseurService implements IProfesseurService {

	/* (non-Javadoc)
	 * @see fr.acceis.services.services.IProfesseurService#lister()
	 */
	public List<Professeur> lister() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa",  "");
		PreparedStatement stmt = connexion.prepareStatement("SELECT * FROM professeur");

		ResultSet result = stmt.executeQuery();

		List<Professeur> listeProfesseurs = new ArrayList<Professeur>();

		while(result.next()){
			String prenom = result.getString("prenom");
			String nom = result.getString("nom");

			Professeur professeur = new Professeur();
			professeur.setNom(nom);
			professeur.setPrenom(prenom);

			listeProfesseurs.add(professeur);
		}

		connexion.close();
		return listeProfesseurs;
	}

}
