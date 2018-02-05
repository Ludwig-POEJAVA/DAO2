package fr.acceis.services.interfaces;

import java.sql.SQLException;

import fr.acceis.services.model.Matiere;

public interface IMatiereService
{

	Matiere chercherParId(long idMatiere) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

}
