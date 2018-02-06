package fr.acceis.services.factory;

import fr.acceis.services.hibernate.CoursServiceHibernate;
import fr.acceis.services.hibernate.CursusServiceHibernate;
import fr.acceis.services.hibernate.EtudiantServiceHibernate;
import fr.acceis.services.hibernate.MatiereServiceHibernate;
import fr.acceis.services.hibernate.ProfesseurServiceHibernate;
import fr.acceis.services.hibernate.SalleServiceHibernate;
import fr.acceis.services.interfaces.ICoursService;
import fr.acceis.services.interfaces.ICursusService;
import fr.acceis.services.interfaces.IEtudiantService;
import fr.acceis.services.interfaces.IMatiereService;
import fr.acceis.services.interfaces.IProfesseurService;
import fr.acceis.services.interfaces.ISalleService;

public class ServiceHibernateFactory extends GenericServiceFactory
{
	@Override
	public ICoursService getCoursService()
	{
		return new CoursServiceHibernate();
	}

	@Override
	public ICursusService getCursusService()
	{
		return new CursusServiceHibernate();
	}

	@Override
	public IEtudiantService getEtudiantService()
	{
		return new EtudiantServiceHibernate();
	}

	@Override
	public IMatiereService getMatiereService()
	{
		return new MatiereServiceHibernate();
	}

	@Override
	public IProfesseurService getProfesseurService()
	{
		return new ProfesseurServiceHibernate();
	}

	@Override
	public ISalleService getSalleService()
	{
		return new SalleServiceHibernate();
	}
}
