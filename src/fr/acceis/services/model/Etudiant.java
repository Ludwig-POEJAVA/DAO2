package fr.acceis.services.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Etudiant")
public class Etudiant
{
	private String	nom;
	private String	prenom;
	@Id
	private String	numeroEtudiant;

	@ManyToOne
	private Cursus cursus;

	public Etudiant()
	{
	}

	public String getNom()
	{
		return this.nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public String getNumeroEtudiant()
	{
		return this.numeroEtudiant;
	}

	public void setNumeroEtudiant(String numeroEtudiant)
	{
		this.numeroEtudiant = numeroEtudiant;
	}

	public Cursus getCursus()
	{
		return this.cursus;
	}

	public void setCursus(Cursus cursus)
	{
		this.cursus = cursus;
	}

	public String getPrenom()
	{
		return this.prenom;
	}

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}
}
