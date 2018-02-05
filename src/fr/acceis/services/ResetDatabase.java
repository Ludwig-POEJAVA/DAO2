package fr.acceis.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ResetDatabase
{

	public final static String[] QUERIES = {
			"alter table Cours drop constraint FKekqdly915eghj1mg00at20fvi",
			"alter table Cours drop constraint FKjqljrhsw7w5ink90h11k164hl",
			"alter table Cours_Professeur drop constraint FKimlgjw45fcp3l8dwt4qslqpnu",
			"alter table Cours_Professeur drop constraint FKgty3anmvupajiadrg066696ms",
			"alter table Creneau drop constraint FKfgc1qhpqgmgova791xlt9903b",
			"alter table Creneau drop constraint FKkbylq59mewy9nfrqyg8iakmh8",
			"alter table Creneau drop constraint FKpes1f8jiq8cqjgv4jp0r7v8og",
			"alter table Cursus_Matiere drop constraint FKgbt914p37ud6ix31s12ksax5y",
			"alter table Cursus_Matiere drop constraint FKchxfuu56w3h6w3c41s1txt4i4",
			"alter table Etudiant drop constraint FKhu3psvc6h2dpfxw76cw5s6xst", "drop table Cours if exists",
			"drop table Cours_Professeur if exists", "drop table Creneau if exists", "drop table Cursus if exists",
			"drop table Cursus_Matiere if exists", "drop table Etudiant if exists", "drop table Horaire if exists",
			"drop table Matiere if exists", "drop table Professeur if exists", "drop table Salle if exists",
			"drop sequence hibernate_sequence if exists",
			"create sequence hibernate_sequence start with 1 increment by 1",
			"create table Cours (id bigint not null, creneau_id bigint, matiere_id bigint, primary key (id))",
			"create table Cours_Professeur (cours_id bigint not null, professeurs_id bigint not null)",
			"create table Creneau (id bigint not null, cours_id bigint, horaire_id bigint, salle_id bigint, primary key (id))",
			"create table Cursus (id bigint not null, nom varchar(255), primary key (id))",
			"create table Cursus_Matiere (Cursus_id bigint not null, matieres_id bigint not null)",
			"create table Etudiant (numeroEtudiant varchar(255) not null, nom varchar(255), prenom varchar(255), cursus_id bigint, primary key (numeroEtudiant))",
			"create table Horaire (id bigint not null, debut timestamp, fin timestamp, primary key (id))",
			"create table Matiere (id bigint not null, nom varchar(255), primary key (id))",
			"create table Professeur (id bigint not null, nom varchar(255), prenom varchar(255), primary key (id))",
			"create table Salle (id bigint not null, nom varchar(255), primary key (id))",
			"alter table Cours add constraint FKekqdly915eghj1mg00at20fvi foreign key (creneau_id) references Creneau",
			"alter table Cours add constraint FKjqljrhsw7w5ink90h11k164hl foreign key (matiere_id) references Matiere",
			"alter table Cours_Professeur add constraint FKimlgjw45fcp3l8dwt4qslqpnu foreign key (professeurs_id) references Professeur",
			"alter table Cours_Professeur add constraint FKgty3anmvupajiadrg066696ms foreign key (cours_id) references Cours",
			"alter table Creneau add constraint FKfgc1qhpqgmgova791xlt9903b foreign key (cours_id) references Cours",
			"alter table Creneau add constraint FKkbylq59mewy9nfrqyg8iakmh8 foreign key (horaire_id) references Horaire",
			"alter table Creneau add constraint FKpes1f8jiq8cqjgv4jp0r7v8og foreign key (salle_id) references Salle",
			"alter table Cursus_Matiere add constraint FKgbt914p37ud6ix31s12ksax5y foreign key (matieres_id) references Matiere",
			"alter table Cursus_Matiere add constraint FKchxfuu56w3h6w3c41s1txt4i4 foreign key (Cursus_id) references Cursus",
			"alter table Etudiant add constraint FKhu3psvc6h2dpfxw76cw5s6xst foreign key (cursus_id) references Cursus",
			"INSERT INTO MATIERE VALUES(1,'G\u00e9nie Logiciel Appliqu\u00e9')",
			"INSERT INTO MATIERE VALUES(2,'S\u00e9curit\u00e9 des R\u00e9seaux')",
			"INSERT INTO MATIERE VALUES(3,'D\u00e9tection d''intrusion')",
			"INSERT INTO MATIERE VALUES(4,'Cryptographie')", "INSERT INTO MATIERE VALUES(5,'Audits intrusifs')",
			"INSERT INTO MATIERE VALUES(6,'UML')", "INSERT INTO MATIERE VALUES(7,'Bases de donn\u00e9es')",
			"INSERT INTO MATIERE VALUES(8,'D\u00e9veloppement PHP')", "INSERT INTO MATIERE VALUES(9,'Angular JS')",
			"INSERT INTO CURSUS VALUES(10,'Master SSI')", "INSERT INTO CURSUS VALUES(11,'Master GL')",
			"INSERT INTO HORAIRE VALUES(12,'2017-07-31 08:00:00.000000','2017-07-31 10:00:00.000000')",
			"INSERT INTO HORAIRE VALUES(13,'2017-07-31 10:00:00.000000','2017-07-31 14:00:00.000000')",
			"INSERT INTO HORAIRE VALUES(14,'2017-07-31 14:00:00.000000','2017-07-31 16:00:00.000000')",
			"INSERT INTO HORAIRE VALUES(15,'2017-07-31 16:00:00.000000','2017-07-31 18:00:00.000000')",
			"INSERT INTO HORAIRE VALUES(16,'2017-07-31 18:00:00.000000','2017-07-31 20:00:00.000000')",
			"INSERT INTO HORAIRE VALUES(17,'2017-08-01 08:00:00.000000','2017-08-01 10:00:00.000000')",
			"INSERT INTO HORAIRE VALUES(18,'2017-08-01 10:00:00.000000','2017-08-01 12:00:00.000000')",
			"INSERT INTO SALLE VALUES(19,'i50')", "INSERT INTO SALLE VALUES(20,'i51')",
			"INSERT INTO SALLE VALUES(21,'i52')", "INSERT INTO SALLE VALUES(22,'i53')",
			"INSERT INTO SALLE VALUES(23,'i54')", "INSERT INTO SALLE VALUES(24,'i55')",
			"INSERT INTO SALLE VALUES(25,'i56')", "INSERT INTO SALLE VALUES(26,'i57')",
			"INSERT INTO CRENEAU VALUES(27,NULL,13,20)", "INSERT INTO CRENEAU VALUES(28,NULL,14,21)",
			"INSERT INTO CRENEAU VALUES(29,NULL,15,22)", "INSERT INTO CRENEAU VALUES(30,NULL,16,23)",
			"INSERT INTO CRENEAU VALUES(31,NULL,17,24)", "INSERT INTO CRENEAU VALUES(32,NULL,18,25)",
			"INSERT INTO CRENEAU VALUES(33,NULL,12,26)", "INSERT INTO CRENEAU VALUES(34,NULL,13,19)",
			"INSERT INTO CRENEAU VALUES(35,NULL,14,20)", "INSERT INTO CRENEAU VALUES(36,NULL,15,21)",
			"INSERT INTO CRENEAU VALUES(37,NULL,16,22)", "INSERT INTO CRENEAU VALUES(38,NULL,17,23)",
			"INSERT INTO CRENEAU VALUES(39,NULL,18,24)", "INSERT INTO CRENEAU VALUES(40,NULL,12,25)",
			"INSERT INTO CRENEAU VALUES(41,NULL,13,26)", "INSERT INTO CRENEAU VALUES(42,NULL,14,19)",
			"INSERT INTO CRENEAU VALUES(43,NULL,15,20)", "INSERT INTO CRENEAU VALUES(44,NULL,16,21)",
			"INSERT INTO CRENEAU VALUES(45,NULL,17,22)", "INSERT INTO CRENEAU VALUES(46,NULL,18,23)",
			"INSERT INTO CRENEAU VALUES(47,NULL,12,24)", "INSERT INTO CRENEAU VALUES(48,NULL,13,25)",
			"INSERT INTO CRENEAU VALUES(49,NULL,14,26)", "INSERT INTO CRENEAU VALUES(50,NULL,15,19)",
			"INSERT INTO CRENEAU VALUES(51,NULL,16,20)", "INSERT INTO CRENEAU VALUES(52,NULL,17,21)",
			"INSERT INTO CRENEAU VALUES(53,NULL,18,22)", "INSERT INTO PROFESSEUR VALUES(54,'Alvarez','Miguel')",
			"INSERT INTO PROFESSEUR VALUES(55,'Adebisi','Simon')",
			"INSERT INTO PROFESSEUR VALUES(56,'Schillinger','Vernon')",
			"INSERT INTO PROFESSEUR VALUES(57,'Hill','Augustus')", "INSERT INTO COURS VALUES(58,27,2)",
			"INSERT INTO COURS VALUES(59,28,3)", "INSERT INTO COURS VALUES(60,29,4)",
			"INSERT INTO COURS VALUES(61,30,5)", "INSERT INTO COURS VALUES(62,31,6)",
			"INSERT INTO COURS VALUES(63,32,7)", "INSERT INTO COURS VALUES(64,33,8)",
			"INSERT INTO COURS VALUES(65,34,9)", "INSERT INTO COURS VALUES(66,35,1)",
			"INSERT INTO COURS VALUES(67,36,2)", "INSERT INTO COURS VALUES(68,37,3)",
			"INSERT INTO COURS VALUES(69,38,4)", "INSERT INTO COURS VALUES(70,39,5)",
			"INSERT INTO COURS VALUES(71,40,6)", "INSERT INTO COURS VALUES(72,41,7)",
			"INSERT INTO COURS VALUES(73,42,8)", "INSERT INTO COURS VALUES(74,43,9)",
			"INSERT INTO COURS VALUES(75,44,1)", "INSERT INTO COURS VALUES(76,45,2)",
			"INSERT INTO COURS VALUES(77,46,3)", "INSERT INTO COURS VALUES(78,47,4)",
			"INSERT INTO COURS VALUES(79,48,5)", "INSERT INTO COURS VALUES(80,49,6)",
			"INSERT INTO COURS VALUES(81,50,7)", "INSERT INTO COURS VALUES(82,51,8)",
			"INSERT INTO COURS VALUES(83,52,9)", "INSERT INTO COURS VALUES(84,53,1)",
			"INSERT INTO ETUDIANT VALUES('21002127','Freamon','Lester',10)",
			"INSERT INTO ETUDIANT VALUES('21002128','McNulty','James',11)",
			"INSERT INTO ETUDIANT VALUES('21002129','Morland','William',11)",
			"INSERT INTO ETUDIANT VALUES('21002130','Daniels','Cedric',10)",
			"UPDATE CRENEAU SET cours_id=58 where id=27", "UPDATE CRENEAU SET cours_id=59 where id=28",
			"UPDATE CRENEAU SET cours_id=60 where id=29", "UPDATE CRENEAU SET cours_id=61 where id=30",
			"UPDATE CRENEAU SET cours_id=62 where id=31", "UPDATE CRENEAU SET cours_id=63 where id=32",
			"UPDATE CRENEAU SET cours_id=64 where id=33", "UPDATE CRENEAU SET cours_id=65 where id=34",
			"UPDATE CRENEAU SET cours_id=66 where id=35", "UPDATE CRENEAU SET cours_id=67 where id=36",
			"UPDATE CRENEAU SET cours_id=68 where id=37", "UPDATE CRENEAU SET cours_id=69 where id=38",
			"UPDATE CRENEAU SET cours_id=70 where id=39", "UPDATE CRENEAU SET cours_id=71 where id=40",
			"UPDATE CRENEAU SET cours_id=72 where id=41", "UPDATE CRENEAU SET cours_id=73 where id=42",
			"UPDATE CRENEAU SET cours_id=74 where id=43", "UPDATE CRENEAU SET cours_id=75 where id=44",
			"UPDATE CRENEAU SET cours_id=76 where id=45", "UPDATE CRENEAU SET cours_id=77 where id=46",
			"UPDATE CRENEAU SET cours_id=78 where id=47", "UPDATE CRENEAU SET cours_id=79 where id=48",
			"UPDATE CRENEAU SET cours_id=80 where id=49", "UPDATE CRENEAU SET cours_id=81 where id=50",
			"UPDATE CRENEAU SET cours_id=82 where id=51", "UPDATE CRENEAU SET cours_id=83 where id=52",
			"UPDATE CRENEAU SET cours_id=84 where id=53", "INSERT INTO CURSUS_MATIERE VALUES(10,1)",
			"INSERT INTO CURSUS_MATIERE VALUES(10,2)", "INSERT INTO CURSUS_MATIERE VALUES(10,3)",
			"INSERT INTO CURSUS_MATIERE VALUES(10,4)", "INSERT INTO CURSUS_MATIERE VALUES(10,5)",
			"INSERT INTO CURSUS_MATIERE VALUES(11,1)", "INSERT INTO CURSUS_MATIERE VALUES(11,6)",
			"INSERT INTO CURSUS_MATIERE VALUES(11,7)", "INSERT INTO CURSUS_MATIERE VALUES(11,8)",
			"INSERT INTO CURSUS_MATIERE VALUES(11,9)", "INSERT INTO COURS_PROFESSEUR VALUES(58,55)",
			"INSERT INTO COURS_PROFESSEUR VALUES(59,56)", "INSERT INTO COURS_PROFESSEUR VALUES(60,57)",
			"INSERT INTO COURS_PROFESSEUR VALUES(61,54)", "INSERT INTO COURS_PROFESSEUR VALUES(62,55)",
			"INSERT INTO COURS_PROFESSEUR VALUES(63,56)", "INSERT INTO COURS_PROFESSEUR VALUES(64,57)",
			"INSERT INTO COURS_PROFESSEUR VALUES(65,54)", "INSERT INTO COURS_PROFESSEUR VALUES(66,55)",
			"INSERT INTO COURS_PROFESSEUR VALUES(67,56)", "INSERT INTO COURS_PROFESSEUR VALUES(68,57)",
			"INSERT INTO COURS_PROFESSEUR VALUES(69,54)", "INSERT INTO COURS_PROFESSEUR VALUES(70,55)",
			"INSERT INTO COURS_PROFESSEUR VALUES(71,56)", "INSERT INTO COURS_PROFESSEUR VALUES(72,57)",
			"INSERT INTO COURS_PROFESSEUR VALUES(73,54)", "INSERT INTO COURS_PROFESSEUR VALUES(74,55)",
			"INSERT INTO COURS_PROFESSEUR VALUES(75,56)", "INSERT INTO COURS_PROFESSEUR VALUES(76,57)",
			"INSERT INTO COURS_PROFESSEUR VALUES(77,54)", "INSERT INTO COURS_PROFESSEUR VALUES(78,55)",
			"INSERT INTO COURS_PROFESSEUR VALUES(79,56)", "INSERT INTO COURS_PROFESSEUR VALUES(80,57)",
			"INSERT INTO COURS_PROFESSEUR VALUES(81,54)", "INSERT INTO COURS_PROFESSEUR VALUES(82,55)",
			"INSERT INTO COURS_PROFESSEUR VALUES(83,56)", "INSERT INTO COURS_PROFESSEUR VALUES(84,57)",
	};

	public static void main(String[] args) throws Exception
	{
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa", "");
		Statement stmt = connexion.createStatement();

		for (String query: QUERIES)
		{
			System.out.println(query);
			stmt.executeUpdate(query);
		}

		stmt.close();
		connexion.close();

	}

}
