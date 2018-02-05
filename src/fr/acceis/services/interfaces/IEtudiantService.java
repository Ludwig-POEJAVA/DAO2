package fr.acceis.services.interfaces;

import java.sql.SQLException;
import java.util.List;

import fr.acceis.services.model.Etudiant;

public interface IEtudiantService
{

	List<Etudiant> lister() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

	Etudiant chercherParNumeroEtudiant(String numeroEtudiant) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

	List<Etudiant> listerEtudiantsParIdCours(long idCours) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

}
