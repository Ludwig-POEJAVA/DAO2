package fr.acceis.services.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.acceis.services.hibernate.GenericsInheritance;
import fr.acceis.services.interfaces.IEtudiantService;
import fr.acceis.services.model.Cursus;
import fr.acceis.services.model.Etudiant;

public class EtudiantService extends GenericsInheritance<Etudiant> implements IEtudiantService
{

	public EtudiantService()
	{
		super(Etudiant.class);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.acceis.services.services.IEtudiantService#lister()
	 */
	public List<Etudiant> lister() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa", "");
		PreparedStatement stmt = connexion.prepareStatement("SELECT * FROM etudiant");

		ResultSet result = stmt.executeQuery();

		List<Etudiant> listeEtudiants = new ArrayList<Etudiant>();

		while (result.next())
		{
			String prenom = result.getString("prenom");
			String nom = result.getString("nom");
			String numeroEtudiant = result.getString("numeroEtudiant");

			Etudiant etudiant = new Etudiant();
			etudiant.setNom(nom);
			etudiant.setPrenom(prenom);
			etudiant.setNumeroEtudiant(numeroEtudiant);

			listeEtudiants.add(etudiant);
		}

		connexion.close();
		return listeEtudiants;
	}

	/* (non-Javadoc)
	 * @see fr.acceis.services.services.IEtudiantService#chercherParNumeroEtudiant(java.lang.String)
	 */
	public Etudiant chercherParNumeroEtudiant(String numeroEtudiant) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa", "");
		PreparedStatement stmt = connexion.prepareStatement("SELECT cursus.nom as nomCursus, etudiant.prenom as prenomEtudiant, etudiant.nom as nomEtudiant, etudiant.numeroEtudiant, cursus.id as cursusId FROM cursus " + "INNER JOIN etudiant " + "ON etudiant.cursus_id = cursus.id " + "WHERE etudiant.numeroEtudiant=?");
		stmt.setString(1, numeroEtudiant);

		ResultSet result = stmt.executeQuery();

		Etudiant etudiant = null;

		if (result.next())
		{
			String nomCursus = result.getString("nomCursus");
			String prenomEtudiant = result.getString("prenomEtudiant");
			String nomEtudiant = result.getString("nomEtudiant");
			long cursusId = result.getLong("cursusId");

			etudiant = new Etudiant();
			etudiant.setPrenom(prenomEtudiant);
			etudiant.setNom(nomEtudiant);
			etudiant.setNumeroEtudiant(numeroEtudiant);
			Cursus cursus = new Cursus();
			cursus.setId(cursusId);
			cursus.setNom(nomCursus);
			etudiant.setCursus(cursus);
		}
		System.out.println();

		connexion.close();

		return etudiant;
	}

	/* (non-Javadoc)
	 * @see fr.acceis.services.services.IEtudiantService#listerEtudiantsParIdCours(long)
	 */
	public List<Etudiant> listerEtudiantsParIdCours(long idCours) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa", "");
		PreparedStatement stmt = connexion.prepareStatement("SELECT etudiant.prenom as prenomEtudiant, etudiant.nom as nomEtudiant, etudiant.numeroEtudiant FROM cours " + "INNER JOIN matiere " + "ON matiere.id = cours.matiere_id " + "INNER JOIN cursus_matiere " + "ON cursus_matiere.matieres_id = matiere.id " + "INNER JOIN cursus " + "ON cursus.id = cursus_matiere.cursus_id " + "INNER JOIN etudiant " + "ON cursus.id = etudiant.cursus_id " + "WHERE cours.id=?");
		stmt.setLong(1, idCours);

		ResultSet result = stmt.executeQuery();

		List<Etudiant> resultat = new ArrayList<Etudiant>();

		while (result.next())
		{
			String prenomEtudiant = result.getString("prenomEtudiant");
			String nomEtudiant = result.getString("nomEtudiant");
			String numero = result.getString("numeroEtudiant");

			Etudiant etudiant = new Etudiant();
			etudiant.setNom(nomEtudiant);
			etudiant.setPrenom(prenomEtudiant);
			etudiant.setNumeroEtudiant(numero);

			resultat.add(etudiant);
		}

		connexion.close();

		return resultat;

	}

}
