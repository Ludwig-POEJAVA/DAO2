package fr.acceis.services.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Horaire")
public class Horaire
{
	@Id
	private long	id;
	private Date	debut;
	private Date	fin;

	@OneToMany
	private Collection<Creneau> creneaux;

	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Date getDebut()
	{
		return this.debut;
	}

	public void setDebut(Date debut)
	{
		this.debut = debut;
	}

	public Date getFin()
	{
		return this.fin;
	}

	public void setFin(Date fin)
	{
		this.fin = fin;
	}

	public Collection<Creneau> getCreneaux()
	{
		return this.creneaux;
	}

	public void setCreneaux(Collection<Creneau> creneaux)
	{
		this.creneaux = creneaux;
	}

}
