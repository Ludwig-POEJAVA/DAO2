package fr.acceis.services.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.acceis.services.interfaces.ICursusService;
import fr.acceis.services.model.Cours;
import fr.acceis.services.model.Cursus;
import fr.acceis.services.model.Matiere;
import fr.acceis.services.model.Professeur;

public class CursusService implements ICursusService {

	/* (non-Javadoc)
	 * @see fr.acceis.services.services.ICursusService#chercherParId(long)
	 */
	public Cursus chercherParId(long idCursus) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa",  "");

		Cursus cursus = null;

		PreparedStatement stmt = connexion.prepareStatement("SELECT nom FROM cursus WHERE cursus.id=?");
		stmt.setLong(1, idCursus);
		ResultSet result = stmt.executeQuery();
		if (result.next()) { 
			cursus = new Cursus();
			cursus.setNom(result.getString("nom"));
			
			List<Matiere> matieres = new ArrayList<Matiere>();
			List<Cours> listCours = new ArrayList<Cours>();
			List<Professeur> professeurs = new ArrayList<Professeur>();
			
			Matiere matiere = new Matiere();
			Cours cours = new Cours();
			cours.setProfesseurs(professeurs);
			listCours.add(cours);

			matiere.setCours(listCours);
			matieres.add(matiere);
			
			stmt = connexion.prepareStatement(
					"SELECT distinct professeur.nom as nomProfesseur, professeur.prenom as prenomProfesseur FROM professeur "
							+ "INNER JOIN cours_professeur "
							+ "ON professeur.id = cours_professeur.professeurs_id "
							+ "INNER JOIN cours "
							+ "ON cours.id = cours_professeur.cours_id "
							+ "INNER JOIN matiere "
							+ "ON matiere.id = cours.matiere_id "
							+ "INNER JOIN cursus_matiere "
							+ "ON cursus_matiere.matieres_id = matiere.id "
							+ "INNER JOIN cursus "
							+ "ON cursus.id = cursus_matiere.cursus_id "
							+ "WHERE cursus.id=?"
					);
			stmt.setLong(1, idCursus);

			result = stmt.executeQuery();

			while(result.next()){
				String prenomProfesseur = result.getString("prenomProfesseur");
				String nomProfesseur = result.getString("nomProfesseur");
				
				Professeur professeur = new Professeur();
				professeur.setNom(nomProfesseur);
				professeur.setPrenom(prenomProfesseur);
				
				professeurs.add(professeur);
			}
			
			cursus.setMatieres(matieres);
		}

		connexion.close();
		
		return cursus;

	}
	
}
