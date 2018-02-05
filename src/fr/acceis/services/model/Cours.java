package fr.acceis.services.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Cours")
public class Cours
{
	@Id
	private long id;
	//TODO
	@Transient
	private Matiere matiere;
	//TODO
	@OneToOne
	private Creneau creneau;
	//TODO
	@Transient
	private Collection<Professeur> professeurs;

	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Matiere getMatiere()
	{
		return this.matiere;
	}

	public void setMatiere(Matiere matiere)
	{
		this.matiere = matiere;
	}

	public Collection<Professeur> getProfesseurs()
	{
		return this.professeurs;
	}

	public void setProfesseurs(Collection<Professeur> professeurs)
	{
		this.professeurs = professeurs;
	}

	public Creneau getCreneau()
	{
		return this.creneau;
	}

	public void setCreneau(Creneau creneau)
	{
		this.creneau = creneau;
	}

}
