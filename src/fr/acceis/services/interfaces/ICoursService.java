package fr.acceis.services.interfaces;

import java.sql.SQLException;
import java.util.List;

import fr.acceis.services.model.Cours;

public interface ICoursService
{

	Cours chercherParId(long idCours) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

	List<Cours> listerParSalle(String nomSalle) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

}
