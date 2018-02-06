package fr.acceis.services.factory;

import fr.acceis.services.interfaces.ICoursService;
import fr.acceis.services.interfaces.ICursusService;
import fr.acceis.services.interfaces.IEtudiantService;
import fr.acceis.services.interfaces.IMatiereService;
import fr.acceis.services.interfaces.IProfesseurService;
import fr.acceis.services.interfaces.ISalleService;

abstract public class GenericServiceFactory
{
	public abstract ICoursService getCoursService();

	public abstract ICursusService getCursusService();

	public abstract IEtudiantService getEtudiantService();

	public abstract IMatiereService getMatiereService();

	public abstract IProfesseurService getProfesseurService();

	public abstract ISalleService getSalleService();

	//public abstract IHoraireService getHoraireService();

	//public abstract ICreneauFactory getEtudiantService();

}
