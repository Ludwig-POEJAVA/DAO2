package fr.acceis.services.factory;

import fr.acceis.services.JDBC.CoursServiceJDBC;
import fr.acceis.services.JDBC.CursusServiceJDBC;
import fr.acceis.services.JDBC.EtudiantServiceJDBC;
import fr.acceis.services.JDBC.MatiereServiceJDBC;
import fr.acceis.services.JDBC.ProfesseurServiceJDBC;
import fr.acceis.services.JDBC.SalleServiceJDBC;
import fr.acceis.services.interfaces.ICoursService;
import fr.acceis.services.interfaces.ICursusService;
import fr.acceis.services.interfaces.IEtudiantService;
import fr.acceis.services.interfaces.IMatiereService;
import fr.acceis.services.interfaces.IProfesseurService;
import fr.acceis.services.interfaces.ISalleService;

public class ServiceJDBCFactory extends GenericServiceFactory
{
	@Override
	public ICoursService getCoursService()
	{
		return new CoursServiceJDBC();
	}

	@Override
	public ICursusService getCursusService()
	{
		return new CursusServiceJDBC();
	}

	@Override
	public IEtudiantService getEtudiantService()
	{
		return new EtudiantServiceJDBC();
	}

	@Override
	public IMatiereService getMatiereService()
	{
		return new MatiereServiceJDBC();
	}

	@Override
	public IProfesseurService getProfesseurService()
	{
		return new ProfesseurServiceJDBC();
	}

	@Override
	public ISalleService getSalleService()
	{
		return new SalleServiceJDBC();
	}
}
