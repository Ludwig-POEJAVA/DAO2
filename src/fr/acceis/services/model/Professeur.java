package fr.acceis.services.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Professeur")
public class Professeur
{
	@Id
	private long	id;
	private String	nom;
	private String	prenom;

	@ManyToMany
	private Collection<Cours> cours;

	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getNom()
	{
		return this.nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public String getPrenom()
	{
		return this.prenom;
	}

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	public Collection<Cours> getCours()
	{
		return this.cours;
	}

	public void setCours(Collection<Cours> cours)
	{
		this.cours = cours;
	}

}
