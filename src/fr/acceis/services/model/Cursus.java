package fr.acceis.services.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Cursus")
public class Cursus
{
	private String nom;

	@ManyToMany
	private Collection<Matiere> matieres;

	@OneToMany(mappedBy = "cursus")
	private Collection<Etudiant> etudiants;

	@Id
	private long id;

	public Collection<Matiere> getMatieres()
	{
		return this.matieres;
	}

	public void setMatieres(Collection<Matiere> matieres)
	{
		this.matieres = matieres;
	}

	public String getNom()
	{
		return this.nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Collection<Etudiant> getEtudiants()
	{
		return this.etudiants;
	}

	public void setEtudiants(Collection<Etudiant> etudiants)
	{
		this.etudiants = etudiants;
	}

}
