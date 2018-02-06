package fr.acceis.services.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.acceis.services.interfaces.ISalleService;
import fr.acceis.services.model.Salle;

public class SalleServiceJDBC implements ISalleService
{
	/* (non-Javadoc)
	 * @see fr.acceis.services.services.ISalleService#lister()
	 */
	public List<Salle> lister() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa", "");
		PreparedStatement stmt = connexion.prepareStatement("SELECT * FROM salle");

		ResultSet result = stmt.executeQuery();

		List<Salle> listeSalles = new ArrayList<Salle>();

		while (result.next())
		{
			String nom = result.getString("nom");

			Salle salle = new Salle();
			salle.setNom(nom);

			listeSalles.add(salle);
		}

		connexion.close();
		return listeSalles;
	}

}
