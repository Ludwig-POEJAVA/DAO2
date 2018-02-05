package fr.acceis.services.interfaces;

import java.sql.SQLException;

import fr.acceis.services.model.Cursus;

public interface ICursusService
{

	Cursus chercherParId(long idCursus) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

}
