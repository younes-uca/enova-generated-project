/*
	Structure for the public.etablissement_ table : 
*/

CREATE TABLE public.etablissement_
(
    id_etablissement BIGINT NOT NULL,
    code             VARCHAR(255),
    actif            BOOLEAN DEFAULT false,
    hl7              VARCHAR(255),
    ordre            BIGINT  DEFAULT 0,
    createdby        VARCHAR(255),
    createdon        TIMESTAMP,
    updatedby        VARCHAR(255),
    updatedon        TIMESTAMP,
    PRIMARY KEY (id_etablissement)
);
CREATE SEQUENCE public.etablissement_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE public.etablissement_ IS 'Etablissement';
		COMMENT
ON COLUMN public.etablissement_.code IS 'code';
		COMMENT
ON COLUMN public.etablissement_.actif IS 'actif';
		COMMENT
ON COLUMN public.etablissement_.hl7 IS 'hl7';
		COMMENT
ON COLUMN public.etablissement_.ordre IS 'ordre';
		COMMENT
ON COLUMN public.etablissement_.createdby IS 'utilisateur de cr�ation';
		COMMENT
ON COLUMN public.etablissement_.createdon IS 'date de cr�ation';
		COMMENT
ON COLUMN public.etablissement_.updatedby IS 'utilisateur de modification';
		COMMENT
ON COLUMN public.etablissement_.updatedon IS 'date de modification';
		COMMENT
ON COLUMN etablissement_.id_etablissement IS 'identifiant etablissement';


/*
	Structure for the public.categorierole_ table : 
*/

CREATE TABLE public.categorierole_
(
    id_categorierole BIGINT NOT NULL,
    code             VARCHAR(255),
    libelle          VARCHAR(255),
    description      TEXT,
    actif            BOOLEAN DEFAULT false,
    hl7              VARCHAR(255),
    ordre            BIGINT  DEFAULT 0,
    createdby        VARCHAR(255),
    createdon        TIMESTAMP,
    updatedby        VARCHAR(255),
    updatedon        TIMESTAMP,
    PRIMARY KEY (id_categorierole)
);
CREATE SEQUENCE public.categorierole_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE public.categorierole_ IS 'Catégorie rôle';
		COMMENT
ON COLUMN public.categorierole_.code IS 'code';
		COMMENT
ON COLUMN public.categorierole_.libelle IS 'libelle';
		COMMENT
ON COLUMN public.categorierole_.description IS 'description';
		COMMENT
ON COLUMN public.categorierole_.actif IS 'actif';
		COMMENT
ON COLUMN public.categorierole_.hl7 IS 'hl7';
		COMMENT
ON COLUMN public.categorierole_.ordre IS 'ordre';
		COMMENT
ON COLUMN public.categorierole_.createdby IS 'utilisateur de cr�ation';
		COMMENT
ON COLUMN public.categorierole_.createdon IS 'date de cr�ation';
		COMMENT
ON COLUMN public.categorierole_.updatedby IS 'utilisateur de modification';
		COMMENT
ON COLUMN public.categorierole_.updatedon IS 'date de modification';
		COMMENT
ON COLUMN categorierole_.id_categorierole IS 'identifiant categorierole';


/*
	Structure for the public.categorieparametrage_ table : 
*/

CREATE TABLE public.categorieparametrage_
(
    id_categorieparametrage BIGINT NOT NULL,
    code                    VARCHAR(255),
    libelle                 VARCHAR(255),
    description             TEXT,
    PRIMARY KEY (id_categorieparametrage)
);
CREATE SEQUENCE public.categorieparametrage_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE public.categorieparametrage_ IS 'Cat�gorie param�trage';
		COMMENT
ON COLUMN public.categorieparametrage_.code IS 'code';
		COMMENT
ON COLUMN public.categorieparametrage_.libelle IS 'libelle';
		COMMENT
ON COLUMN public.categorieparametrage_.description IS 'description';
		COMMENT
ON COLUMN categorieparametrage_.id_categorieparametrage IS 'identifiant categorieparametrage';


/*
	Structure for the public.prescriptionradiotherapie_ table : 
*/


/*
	Structure for the public.patient_ table : 
*/

CREATE TABLE public.patient_
(
    id_patient       BIGINT NOT NULL,
    ipp              BIGINT DEFAULT 0,
    nom              VARCHAR(255),
    prenom           VARCHAR(255),
    sexe             VARCHAR(255),
    dateNaissance    DATE,
    etablissement_id BIGINT,
    FOREIGN KEY (etablissement_id) REFERENCES public.etablissement_ (id_etablissement),
    createdby        VARCHAR(255),
    createdon        TIMESTAMP,
    updatedby        VARCHAR(255),
    updatedon        TIMESTAMP,
    PRIMARY KEY (id_patient)
);
CREATE SEQUENCE public.patient_seq START WITH 1 INCREMENT BY 1;
CREATE INDEX idx_patient_etablissement_id ON public.patient_ (etablissement_id);
COMMENT
ON TABLE public.patient_ IS 'patient';
		COMMENT
ON COLUMN public.patient_.ipp IS 'ipp';
		COMMENT
ON COLUMN public.patient_.nom IS 'nom';
		COMMENT
ON COLUMN public.patient_.prenom IS 'prenom';
		COMMENT
ON COLUMN public.patient_.sexe IS 'sexe';
		COMMENT
ON COLUMN public.patient_.dateNaissance IS 'dateNaissance';
		COMMENT
ON COLUMN public.patient_.etablissement_id IS 'etablissement';
		COMMENT
ON COLUMN public.patient_.createdby IS 'utilisateur de cr�ation';
		COMMENT
ON COLUMN public.patient_.createdon IS 'date de cr�ation';
		COMMENT
ON COLUMN public.patient_.updatedby IS 'utilisateur de modification';
		COMMENT
ON COLUMN public.patient_.updatedon IS 'date de modification';
		COMMENT
ON COLUMN patient_.id_patient IS 'identifiant patient';


/*
	Structure for the public.seanceradiotherapie_ table : 
*/



/*
	Structure for the public.visee_ table : 
*/

CREATE TABLE public.visee_
(
    id_visee         BIGINT NOT NULL,
    code             VARCHAR(255),
    description      TEXT,
    libelle          VARCHAR(255),
    actif            BOOLEAN DEFAULT false,
    hl7              VARCHAR(255),
    ordre            BIGINT  DEFAULT 0,
    etablissement_id BIGINT,
    FOREIGN KEY (etablissement_id) REFERENCES public.etablissement_ (id_etablissement),
    createdby        VARCHAR(255),
    createdon        TIMESTAMP,
    updatedby        VARCHAR(255),
    updatedon        TIMESTAMP,
    PRIMARY KEY (id_visee)
);
CREATE SEQUENCE public.visee_seq START WITH 1 INCREMENT BY 1;
CREATE INDEX idx_visee_etablissement_id ON public.visee_ (etablissement_id);
COMMENT
ON TABLE public.visee_ IS 'SituationMatrimonial';
		COMMENT
ON COLUMN public.visee_.code IS 'code';
		COMMENT
ON COLUMN public.visee_.description IS 'description';
		COMMENT
ON COLUMN public.visee_.libelle IS 'libelle';
		COMMENT
ON COLUMN public.visee_.actif IS 'actif';
		COMMENT
ON COLUMN public.visee_.hl7 IS 'hl7';
		COMMENT
ON COLUMN public.visee_.ordre IS 'ordre';
		COMMENT
ON COLUMN public.visee_.etablissement_id IS 'etablissement';
		COMMENT
ON COLUMN public.visee_.createdby IS 'utilisateur de cr�ation';
		COMMENT
ON COLUMN public.visee_.createdon IS 'date de cr�ation';
		COMMENT
ON COLUMN public.visee_.updatedby IS 'utilisateur de modification';
		COMMENT
ON COLUMN public.visee_.updatedon IS 'date de modification';
		COMMENT
ON COLUMN visee_.id_visee IS 'identifiant visee';


/*
	Structure for the public.role_ table : 
*/

CREATE TABLE public.role_
(
    id_role          BIGINT NOT NULL,
    libelle          VARCHAR(255),
    description      TEXT,
    domaine          INTEGER DEFAULT 1,
    categorierole_id BIGINT,
    FOREIGN KEY (categorierole_id) REFERENCES public.categorierole_ (id_categorierole),
    PRIMARY KEY (id_role)
);
CREATE SEQUENCE public.role_seq START WITH 1 INCREMENT BY 1;
CREATE INDEX idx_role_categorierole_id ON public.role_ (categorierole_id);
COMMENT
ON TABLE public.role_ IS 'role';
		COMMENT
ON COLUMN public.role_.libelle IS 'libelle';
		COMMENT
ON COLUMN public.role_.description IS 'description';
		COMMENT
ON COLUMN public.role_.domaine IS 'domaine';
		COMMENT
ON COLUMN public.role_.categorierole_id IS 'categorieRole';
		COMMENT
ON COLUMN role_.id_role IS 'identifiant role';


/*
	Structure for the public.personnel_ table : 
*/

CREATE TABLE public.personnel_
(
    id_personnel     BIGINT NOT NULL,
    nom              VARCHAR(255),
    prenom           VARCHAR(255),
    cin              VARCHAR(255),
    adresse          TEXT,
    email            VARCHAR(255),
    telephone        VARCHAR(255),
    mobile           VARCHAR(255),
    expertise        BOOLEAN DEFAULT false,
    etablissement_id BIGINT,
    FOREIGN KEY (etablissement_id) REFERENCES public.etablissement_ (id_etablissement),
    createdby        VARCHAR(255),
    createdon        TIMESTAMP,
    updatedby        VARCHAR(255),
    updatedon        TIMESTAMP,
    PRIMARY KEY (id_personnel)
);
CREATE SEQUENCE public.personnel_seq START WITH 1 INCREMENT BY 1;
CREATE INDEX idx_personnel_etablissement_id ON public.personnel_ (etablissement_id);
COMMENT
ON TABLE public.personnel_ IS 'Utilisateurs';
		COMMENT
ON COLUMN public.personnel_.nom IS 'nom';
		COMMENT
ON COLUMN public.personnel_.prenom IS 'prenom';
		COMMENT
ON COLUMN public.personnel_.cin IS 'cin';
		COMMENT
ON COLUMN public.personnel_.adresse IS 'adresse';
		COMMENT
ON COLUMN public.personnel_.email IS 'email';
		COMMENT
ON COLUMN public.personnel_.telephone IS 'telephone';
		COMMENT
ON COLUMN public.personnel_.mobile IS 'mobile';
		COMMENT
ON COLUMN public.personnel_.expertise IS 'expertise';
		COMMENT
ON COLUMN public.personnel_.etablissement_id IS 'etablissement';
		COMMENT
ON COLUMN public.personnel_.createdby IS 'utilisateur de cr�ation';
		COMMENT
ON COLUMN public.personnel_.createdon IS 'date de cr�ation';
		COMMENT
ON COLUMN public.personnel_.updatedby IS 'utilisateur de modification';
		COMMENT
ON COLUMN public.personnel_.updatedon IS 'date de modification';
		COMMENT
ON COLUMN personnel_.id_personnel IS 'identifiant personnel';


/*
	Structure for the public.frequenceradiotherapie_ table : 
*/

CREATE TABLE public.frequenceradiotherapie_
(
    id_frequenceradiotherapie BIGINT NOT NULL,
    code                      VARCHAR(255),
    libelle                   VARCHAR(255),
    description               TEXT,
    actif                     BOOLEAN DEFAULT false,
    hl7                       VARCHAR(255),
    ordre                     BIGINT  DEFAULT 0,
    etablissement_id          BIGINT,
    FOREIGN KEY (etablissement_id) REFERENCES public.etablissement_ (id_etablissement),
    createdby                 VARCHAR(255),
    createdon                 TIMESTAMP,
    updatedby                 VARCHAR(255),
    updatedon                 TIMESTAMP,
    PRIMARY KEY (id_frequenceradiotherapie)
);
CREATE SEQUENCE public.frequenceradiotherapie_seq START WITH 1 INCREMENT BY 1;
CREATE INDEX idx_frequenceRadiotherapie_etablissement_id ON public.frequenceradiotherapie_ (etablissement_id);
COMMENT
ON TABLE public.frequenceradiotherapie_ IS 'StatutBeneficiaire';
		COMMENT
ON COLUMN public.frequenceradiotherapie_.code IS 'code';
		COMMENT
ON COLUMN public.frequenceradiotherapie_.libelle IS 'libelle';
		COMMENT
ON COLUMN public.frequenceradiotherapie_.description IS 'description';
		COMMENT
ON COLUMN public.frequenceradiotherapie_.actif IS 'actif';
		COMMENT
ON COLUMN public.frequenceradiotherapie_.hl7 IS 'hl7';
		COMMENT
ON COLUMN public.frequenceradiotherapie_.ordre IS 'ordre';
		COMMENT
ON COLUMN public.frequenceradiotherapie_.etablissement_id IS 'etablissement';
		COMMENT
ON COLUMN public.frequenceradiotherapie_.createdby IS 'utilisateur de cr�ation';
		COMMENT
ON COLUMN public.frequenceradiotherapie_.createdon IS 'date de cr�ation';
		COMMENT
ON COLUMN public.frequenceradiotherapie_.updatedby IS 'utilisateur de modification';
		COMMENT
ON COLUMN public.frequenceradiotherapie_.updatedon IS 'date de modification';
		COMMENT
ON COLUMN frequenceradiotherapie_.id_frequenceradiotherapie IS 'identifiant frequenceradiotherapie';


/*
	Structure for the public.protocoleinclusion_ table : 
*/

CREATE TABLE public.protocoleinclusion_
(
    id_protocoleinclusion BIGINT NOT NULL,
    code                  VARCHAR(255),
    libelle               VARCHAR(255),
    status                VARCHAR(50) DEFAULT 'PRESCRIPTION',
    etablissement_id      BIGINT,
    FOREIGN KEY (etablissement_id) REFERENCES public.etablissement_ (id_etablissement),
    createdby             VARCHAR(255),
    createdon             TIMESTAMP,
    updatedby             VARCHAR(255),
    updatedon             TIMESTAMP,
    PRIMARY KEY (id_protocoleinclusion)
);
CREATE SEQUENCE public.protocoleinclusion_seq START WITH 1 INCREMENT BY 1;
CREATE INDEX idx_protocoleInclusion_etablissement_id ON public.protocoleinclusion_ (etablissement_id);
COMMENT
ON TABLE public.protocoleinclusion_ IS 'protocoleInclusion';
		COMMENT
ON COLUMN public.protocoleinclusion_.code IS 'code';
		COMMENT
ON COLUMN public.protocoleinclusion_.libelle IS 'libelle';
		COMMENT
ON COLUMN public.protocoleinclusion_.status IS 'status';
		COMMENT
ON COLUMN public.protocoleinclusion_.etablissement_id IS 'etablissement';
		COMMENT
ON COLUMN public.protocoleinclusion_.createdby IS 'utilisateur de cr�ation';
		COMMENT
ON COLUMN public.protocoleinclusion_.createdon IS 'date de cr�ation';
		COMMENT
ON COLUMN public.protocoleinclusion_.updatedby IS 'utilisateur de modification';
		COMMENT
ON COLUMN public.protocoleinclusion_.updatedon IS 'date de modification';
		COMMENT
ON COLUMN protocoleinclusion_.id_protocoleinclusion IS 'identifiant protocoleinclusion';


/*
	Structure for the public.profil_ table : 
*/

CREATE TABLE public.profil_
(
    id_profil   BIGINT NOT NULL,
    code        VARCHAR(255),
    libelle     VARCHAR(255),
    description TEXT,
    createdby   VARCHAR(255),
    createdon   TIMESTAMP,
    updatedby   VARCHAR(255),
    updatedon   TIMESTAMP,
    PRIMARY KEY (id_profil)
);
CREATE SEQUENCE public.profil_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE public.profil_ IS 'profil';
		COMMENT
ON COLUMN public.profil_.code IS 'code';
		COMMENT
ON COLUMN public.profil_.libelle IS 'libelle';
		COMMENT
ON COLUMN public.profil_.description IS 'description';
		COMMENT
ON COLUMN public.profil_.createdby IS 'utilisateur de cr�ation';
		COMMENT
ON COLUMN public.profil_.createdon IS 'date de cr�ation';
		COMMENT
ON COLUMN public.profil_.updatedby IS 'utilisateur de modification';
		COMMENT
ON COLUMN public.profil_.updatedon IS 'date de modification';
		COMMENT
ON COLUMN profil_.id_profil IS 'identifiant profil';


/*
	Structure for the public.modaliteradiotherapie_ table : 
*/

CREATE TABLE public.modaliteradiotherapie_
(
    id_modaliteradiotherapie BIGINT NOT NULL,
    code                     VARCHAR(255),
    libelle                  VARCHAR(255),
    description              TEXT,
    actif                    BOOLEAN DEFAULT false,
    hl7                      VARCHAR(255),
    ordre                    BIGINT  DEFAULT 0,
    etablissement_id         BIGINT,
    FOREIGN KEY (etablissement_id) REFERENCES public.etablissement_ (id_etablissement),
    createdby                VARCHAR(255),
    createdon                TIMESTAMP,
    updatedby                VARCHAR(255),
    updatedon                TIMESTAMP,
    PRIMARY KEY (id_modaliteradiotherapie)
);
CREATE SEQUENCE public.modaliteradiotherapie_seq START WITH 1 INCREMENT BY 1;
CREATE INDEX idx_modaliteRadiotherapie_etablissement_id ON public.modaliteradiotherapie_ (etablissement_id);
COMMENT
ON TABLE public.modaliteradiotherapie_ IS 'Mutualiste';
		COMMENT
ON COLUMN public.modaliteradiotherapie_.code IS 'code';
		COMMENT
ON COLUMN public.modaliteradiotherapie_.libelle IS 'libelle';
		COMMENT
ON COLUMN public.modaliteradiotherapie_.description IS 'description';
		COMMENT
ON COLUMN public.modaliteradiotherapie_.actif IS 'actif';
		COMMENT
ON COLUMN public.modaliteradiotherapie_.hl7 IS 'hl7';
		COMMENT
ON COLUMN public.modaliteradiotherapie_.ordre IS 'ordre';
		COMMENT
ON COLUMN public.modaliteradiotherapie_.etablissement_id IS 'etablissement';
		COMMENT
ON COLUMN public.modaliteradiotherapie_.createdby IS 'utilisateur de cr�ation';
		COMMENT
ON COLUMN public.modaliteradiotherapie_.createdon IS 'date de cr�ation';
		COMMENT
ON COLUMN public.modaliteradiotherapie_.updatedby IS 'utilisateur de modification';
		COMMENT
ON COLUMN public.modaliteradiotherapie_.updatedon IS 'date de modification';
		COMMENT
ON COLUMN modaliteradiotherapie_.id_modaliteradiotherapie IS 'identifiant modaliteradiotherapie';


/*
	Structure for the public.organe_ table : 
*/

CREATE TABLE public.organe_
(
    id_organe        BIGINT NOT NULL,
    code             VARCHAR(255),
    libelle          VARCHAR(255),
    etablissement_id BIGINT,
    FOREIGN KEY (etablissement_id) REFERENCES public.etablissement_ (id_etablissement),
    createdby        VARCHAR(255),
    createdon        TIMESTAMP,
    updatedby        VARCHAR(255),
    updatedon        TIMESTAMP,
    PRIMARY KEY (id_organe)
);
CREATE SEQUENCE public.organe_seq START WITH 1 INCREMENT BY 1;
CREATE INDEX idx_organe_etablissement_id ON public.organe_ (etablissement_id);
COMMENT
ON TABLE public.organe_ IS 'organe';
		COMMENT
ON COLUMN public.organe_.code IS 'code';
		COMMENT
ON COLUMN public.organe_.libelle IS 'libelle';
		COMMENT
ON COLUMN public.organe_.etablissement_id IS 'etablissement';
		COMMENT
ON COLUMN public.organe_.createdby IS 'utilisateur de cr�ation';
		COMMENT
ON COLUMN public.organe_.createdon IS 'date de cr�ation';
		COMMENT
ON COLUMN public.organe_.updatedby IS 'utilisateur de modification';
		COMMENT
ON COLUMN public.organe_.updatedon IS 'date de modification';
		COMMENT
ON COLUMN organe_.id_organe IS 'identifiant organe';


/*
	Structure for the public.utilisateur_ table : 
*/

CREATE TABLE public.utilisateur_
(
    id_utilisateur   BIGINT NOT NULL,
    nom              VARCHAR(255),
    prenom           VARCHAR(255),
    cin              VARCHAR(255),
    adresse          TEXT,
    email            VARCHAR(255),
    telephone        VARCHAR(255),
    mobile           VARCHAR(255),
    expertise        BOOLEAN DEFAULT false,
    username         VARCHAR(255),
    password         TEXT,
    enabled          BOOLEAN DEFAULT false,
    etablissement_id BIGINT,
    FOREIGN KEY (etablissement_id) REFERENCES public.etablissement_ (id_etablissement),
    profil_id        BIGINT,
    FOREIGN KEY (profil_id) REFERENCES public.profil_ (id_profil),
    createdby        VARCHAR(255),
    createdon        TIMESTAMP,
    updatedby        VARCHAR(255),
    updatedon        TIMESTAMP,
    PRIMARY KEY (id_utilisateur)
);
CREATE SEQUENCE public.utilisateur_seq START WITH 1 INCREMENT BY 1;
CREATE INDEX idx_utilisateur_etablissement_id ON public.utilisateur_ (etablissement_id);
CREATE INDEX idx_utilisateur_profil_id ON public.utilisateur_ (profil_id);
COMMENT
ON TABLE public.utilisateur_ IS 'Utilisateurs';
		COMMENT
ON COLUMN public.utilisateur_.nom IS 'nom';
		COMMENT
ON COLUMN public.utilisateur_.prenom IS 'prenom';
		COMMENT
ON COLUMN public.utilisateur_.cin IS 'cin';
		COMMENT
ON COLUMN public.utilisateur_.adresse IS 'adresse';
		COMMENT
ON COLUMN public.utilisateur_.email IS 'email';
		COMMENT
ON COLUMN public.utilisateur_.telephone IS 'telephone';
		COMMENT
ON COLUMN public.utilisateur_.mobile IS 'mobile';
		COMMENT
ON COLUMN public.utilisateur_.expertise IS 'expertise';
		COMMENT
ON COLUMN public.utilisateur_.username IS 'username';
		COMMENT
ON COLUMN public.utilisateur_.password IS 'password';
		COMMENT
ON COLUMN public.utilisateur_.enabled IS 'enabled';
		COMMENT
ON COLUMN public.utilisateur_.etablissement_id IS 'etablissement';
		COMMENT
ON COLUMN public.utilisateur_.profil_id IS 'profil';
		COMMENT
ON COLUMN public.utilisateur_.createdby IS 'utilisateur de cr�ation';
		COMMENT
ON COLUMN public.utilisateur_.createdon IS 'date de cr�ation';
		COMMENT
ON COLUMN public.utilisateur_.updatedby IS 'utilisateur de modification';
		COMMENT
ON COLUMN public.utilisateur_.updatedon IS 'date de modification';
		COMMENT
ON COLUMN utilisateur_.id_utilisateur IS 'identifiant utilisateur';


/*
	Structure for the public.parametrage_ table : 
*/

CREATE TABLE public.parametrage_
(
    id_parametrage   BIGINT NOT NULL,
    code             VARCHAR(255),
    valeur           VARCHAR(255),
    typeValeur       VARCHAR(50) DEFAULT 'TEXT',
    description      TEXT,
    categorierole_id BIGINT,
    FOREIGN KEY (categorierole_id) REFERENCES public.categorieparametrage_ (id_categorieparametrage),
    etablissement_id BIGINT,
    FOREIGN KEY (etablissement_id) REFERENCES public.etablissement_ (id_etablissement),
    createdby        VARCHAR(255),
    createdon        TIMESTAMP,
    updatedby        VARCHAR(255),
    updatedon        TIMESTAMP,
    PRIMARY KEY (id_parametrage)
);
CREATE SEQUENCE public.parametrage_seq START WITH 1 INCREMENT BY 1;
CREATE INDEX idx_parametrage_categorierole_id ON public.parametrage_ (categorierole_id);
CREATE INDEX idx_parametrage_etablissement_id ON public.parametrage_ (etablissement_id);
COMMENT
ON TABLE public.parametrage_ IS 'Param�trage';
		COMMENT
ON COLUMN public.parametrage_.code IS 'code';
		COMMENT
ON COLUMN public.parametrage_.valeur IS 'valeur';
		COMMENT
ON COLUMN public.parametrage_.typeValeur IS 'typeValeur';
		COMMENT
ON COLUMN public.parametrage_.description IS 'description';
		COMMENT
ON COLUMN public.parametrage_.categorierole_id IS 'categorieRole';
		COMMENT
ON COLUMN public.parametrage_.etablissement_id IS 'etablissement';
		COMMENT
ON COLUMN public.parametrage_.createdby IS 'utilisateur de cr�ation';
		COMMENT
ON COLUMN public.parametrage_.createdon IS 'date de cr�ation';
		COMMENT
ON COLUMN public.parametrage_.updatedby IS 'utilisateur de modification';
		COMMENT
ON COLUMN public.parametrage_.updatedon IS 'date de modification';
		COMMENT
ON COLUMN parametrage_.id_parametrage IS 'identifiant parametrage';


/*
	Structure for the public.profil_roles_ table : 
*/

CREATE TABLE public.profil_roles_
(
    id_profil BIGINT,
    id_role   BIGINT,
    FOREIGN KEY (id_profil) REFERENCES public.profil_ (id_profil),
    FOREIGN KEY (id_role) REFERENCES public.role_ (id_role),
    PRIMARY KEY (id_profil, id_role)
);
COMMENT
ON COLUMN public.profil_roles_.id_profil IS 'Identifiant profil';
		COMMENT
ON COLUMN public.profil_roles_.id_role IS 'Identifiant role';

/**************************  tables tables historique des modifications *************************************/
CREATE SCHEMA historique;
COMMENT
ON schema historique  IS 'schema tables historique des modifications';


/*
	Structure for the historique.etablissement_hist table : 
*/

CREATE TABLE historique.etablissement_hist
(
    id_histetablissement BIGINT NOT NULL,
    objectName           VARCHAR(255),
    data                 TEXT,
    userId               BIGINT DEFAULT 0,
    username             VARCHAR(255),
    actionType           VARCHAR(255),
    objectId             BIGINT DEFAULT 0,
    date                 TIMESTAMP,
    PRIMARY KEY (id_histetablissement)
);
CREATE SEQUENCE historique.histetablissement_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE historique.etablissement_hist IS 'HistEtablissement';
		COMMENT
ON COLUMN historique.etablissement_hist.objectName IS 'objectName';
		COMMENT
ON COLUMN historique.etablissement_hist.data IS 'data';
		COMMENT
ON COLUMN historique.etablissement_hist.userId IS 'userId';
		COMMENT
ON COLUMN historique.etablissement_hist.username IS 'username';
		COMMENT
ON COLUMN historique.etablissement_hist.actionType IS 'actionType';
		COMMENT
ON COLUMN historique.etablissement_hist.objectId IS 'objectId';
		COMMENT
ON COLUMN historique.etablissement_hist.date IS 'date';

/*
	Structure for the historique.prescriptionradiotherapie_hist table : 
*/

CREATE TABLE historique.prescriptionradiotherapie_hist
(
    id_histprescriptionradiotherapie BIGINT NOT NULL,
    objectName                       VARCHAR(255),
    data                             TEXT,
    userId                           BIGINT DEFAULT 0,
    username                         VARCHAR(255),
    actionType                       VARCHAR(255),
    objectId                         BIGINT DEFAULT 0,
    date                             TIMESTAMP,
    PRIMARY KEY (id_histprescriptionradiotherapie)
);
CREATE SEQUENCE historique.histprescriptionradiotherapie_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE historique.prescriptionradiotherapie_hist IS 'HistPrescriptionRadiotherapie';
		COMMENT
ON COLUMN historique.prescriptionradiotherapie_hist.objectName IS 'objectName';
		COMMENT
ON COLUMN historique.prescriptionradiotherapie_hist.data IS 'data';
		COMMENT
ON COLUMN historique.prescriptionradiotherapie_hist.userId IS 'userId';
		COMMENT
ON COLUMN historique.prescriptionradiotherapie_hist.username IS 'username';
		COMMENT
ON COLUMN historique.prescriptionradiotherapie_hist.actionType IS 'actionType';
		COMMENT
ON COLUMN historique.prescriptionradiotherapie_hist.objectId IS 'objectId';
		COMMENT
ON COLUMN historique.prescriptionradiotherapie_hist.date IS 'date';

/*
	Structure for the historique.patient_hist table : 
*/

CREATE TABLE historique.patient_hist
(
    id_histpatient BIGINT NOT NULL,
    objectName     VARCHAR(255),
    data           TEXT,
    userId         BIGINT DEFAULT 0,
    username       VARCHAR(255),
    actionType     VARCHAR(255),
    objectId       BIGINT DEFAULT 0,
    date           TIMESTAMP,
    PRIMARY KEY (id_histpatient)
);
CREATE SEQUENCE historique.histpatient_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE historique.patient_hist IS 'HistPatient';
		COMMENT
ON COLUMN historique.patient_hist.objectName IS 'objectName';
		COMMENT
ON COLUMN historique.patient_hist.data IS 'data';
		COMMENT
ON COLUMN historique.patient_hist.userId IS 'userId';
		COMMENT
ON COLUMN historique.patient_hist.username IS 'username';
		COMMENT
ON COLUMN historique.patient_hist.actionType IS 'actionType';
		COMMENT
ON COLUMN historique.patient_hist.objectId IS 'objectId';
		COMMENT
ON COLUMN historique.patient_hist.date IS 'date';

/*
	Structure for the historique.seanceradiotherapie_hist table : 
*/

CREATE TABLE historique.seanceradiotherapie_hist
(
    id_histseanceradiotherapie BIGINT NOT NULL,
    objectName                 VARCHAR(255),
    data                       TEXT,
    userId                     BIGINT DEFAULT 0,
    username                   VARCHAR(255),
    actionType                 VARCHAR(255),
    objectId                   BIGINT DEFAULT 0,
    date                       TIMESTAMP,
    PRIMARY KEY (id_histseanceradiotherapie)
);
CREATE SEQUENCE historique.histseanceradiotherapie_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE historique.seanceradiotherapie_hist IS 'HistSeanceRadiotherapie';
		COMMENT
ON COLUMN historique.seanceradiotherapie_hist.objectName IS 'objectName';
		COMMENT
ON COLUMN historique.seanceradiotherapie_hist.data IS 'data';
		COMMENT
ON COLUMN historique.seanceradiotherapie_hist.userId IS 'userId';
		COMMENT
ON COLUMN historique.seanceradiotherapie_hist.username IS 'username';
		COMMENT
ON COLUMN historique.seanceradiotherapie_hist.actionType IS 'actionType';
		COMMENT
ON COLUMN historique.seanceradiotherapie_hist.objectId IS 'objectId';
		COMMENT
ON COLUMN historique.seanceradiotherapie_hist.date IS 'date';

/*
	Structure for the historique.visee_hist table : 
*/

CREATE TABLE historique.visee_hist
(
    id_histvisee BIGINT NOT NULL,
    objectName   VARCHAR(255),
    data         TEXT,
    userId       BIGINT DEFAULT 0,
    username     VARCHAR(255),
    actionType   VARCHAR(255),
    objectId     BIGINT DEFAULT 0,
    date         TIMESTAMP,
    PRIMARY KEY (id_histvisee)
);
CREATE SEQUENCE historique.histvisee_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE historique.visee_hist IS 'HistVisee';
		COMMENT
ON COLUMN historique.visee_hist.objectName IS 'objectName';
		COMMENT
ON COLUMN historique.visee_hist.data IS 'data';
		COMMENT
ON COLUMN historique.visee_hist.userId IS 'userId';
		COMMENT
ON COLUMN historique.visee_hist.username IS 'username';
		COMMENT
ON COLUMN historique.visee_hist.actionType IS 'actionType';
		COMMENT
ON COLUMN historique.visee_hist.objectId IS 'objectId';
		COMMENT
ON COLUMN historique.visee_hist.date IS 'date';

/*
	Structure for the historique.personnel_hist table : 
*/

CREATE TABLE historique.personnel_hist
(
    id_histpersonnel BIGINT NOT NULL,
    objectName       VARCHAR(255),
    data             TEXT,
    userId           BIGINT DEFAULT 0,
    username         VARCHAR(255),
    actionType       VARCHAR(255),
    objectId         BIGINT DEFAULT 0,
    date             TIMESTAMP,
    PRIMARY KEY (id_histpersonnel)
);
CREATE SEQUENCE historique.histpersonnel_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE historique.personnel_hist IS 'HistPersonnel';
		COMMENT
ON COLUMN historique.personnel_hist.objectName IS 'objectName';
		COMMENT
ON COLUMN historique.personnel_hist.data IS 'data';
		COMMENT
ON COLUMN historique.personnel_hist.userId IS 'userId';
		COMMENT
ON COLUMN historique.personnel_hist.username IS 'username';
		COMMENT
ON COLUMN historique.personnel_hist.actionType IS 'actionType';
		COMMENT
ON COLUMN historique.personnel_hist.objectId IS 'objectId';
		COMMENT
ON COLUMN historique.personnel_hist.date IS 'date';

/*
	Structure for the historique.frequenceradiotherapie_hist table : 
*/

CREATE TABLE historique.frequenceradiotherapie_hist
(
    id_histfrequenceradiotherapie BIGINT NOT NULL,
    objectName                    VARCHAR(255),
    data                          TEXT,
    userId                        BIGINT DEFAULT 0,
    username                      VARCHAR(255),
    actionType                    VARCHAR(255),
    objectId                      BIGINT DEFAULT 0,
    date                          TIMESTAMP,
    PRIMARY KEY (id_histfrequenceradiotherapie)
);
CREATE SEQUENCE historique.histfrequenceradiotherapie_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE historique.frequenceradiotherapie_hist IS 'HistFrequenceRadiotherapie';
		COMMENT
ON COLUMN historique.frequenceradiotherapie_hist.objectName IS 'objectName';
		COMMENT
ON COLUMN historique.frequenceradiotherapie_hist.data IS 'data';
		COMMENT
ON COLUMN historique.frequenceradiotherapie_hist.userId IS 'userId';
		COMMENT
ON COLUMN historique.frequenceradiotherapie_hist.username IS 'username';
		COMMENT
ON COLUMN historique.frequenceradiotherapie_hist.actionType IS 'actionType';
		COMMENT
ON COLUMN historique.frequenceradiotherapie_hist.objectId IS 'objectId';
		COMMENT
ON COLUMN historique.frequenceradiotherapie_hist.date IS 'date';

/*
	Structure for the historique.protocoleinclusion_hist table : 
*/

CREATE TABLE historique.protocoleinclusion_hist
(
    id_histprotocoleinclusion BIGINT NOT NULL,
    objectName                VARCHAR(255),
    data                      TEXT,
    userId                    BIGINT DEFAULT 0,
    username                  VARCHAR(255),
    actionType                VARCHAR(255),
    objectId                  BIGINT DEFAULT 0,
    date                      TIMESTAMP,
    PRIMARY KEY (id_histprotocoleinclusion)
);
CREATE SEQUENCE historique.histprotocoleinclusion_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE historique.protocoleinclusion_hist IS 'HistProtocoleInclusion';
		COMMENT
ON COLUMN historique.protocoleinclusion_hist.objectName IS 'objectName';
		COMMENT
ON COLUMN historique.protocoleinclusion_hist.data IS 'data';
		COMMENT
ON COLUMN historique.protocoleinclusion_hist.userId IS 'userId';
		COMMENT
ON COLUMN historique.protocoleinclusion_hist.username IS 'username';
		COMMENT
ON COLUMN historique.protocoleinclusion_hist.actionType IS 'actionType';
		COMMENT
ON COLUMN historique.protocoleinclusion_hist.objectId IS 'objectId';
		COMMENT
ON COLUMN historique.protocoleinclusion_hist.date IS 'date';

/*
	Structure for the historique.profil_hist table : 
*/

CREATE TABLE historique.profil_hist
(
    id_histprofil BIGINT NOT NULL,
    objectName    VARCHAR(255),
    data          TEXT,
    userId        BIGINT DEFAULT 0,
    username      VARCHAR(255),
    actionType    VARCHAR(255),
    objectId      BIGINT DEFAULT 0,
    date          TIMESTAMP,
    PRIMARY KEY (id_histprofil)
);
CREATE SEQUENCE historique.histprofil_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE historique.profil_hist IS 'HistProfil';
		COMMENT
ON COLUMN historique.profil_hist.objectName IS 'objectName';
		COMMENT
ON COLUMN historique.profil_hist.data IS 'data';
		COMMENT
ON COLUMN historique.profil_hist.userId IS 'userId';
		COMMENT
ON COLUMN historique.profil_hist.username IS 'username';
		COMMENT
ON COLUMN historique.profil_hist.actionType IS 'actionType';
		COMMENT
ON COLUMN historique.profil_hist.objectId IS 'objectId';
		COMMENT
ON COLUMN historique.profil_hist.date IS 'date';

/*
	Structure for the historique.modaliteradiotherapie_hist table : 
*/

CREATE TABLE historique.modaliteradiotherapie_hist
(
    id_histmodaliteradiotherapie BIGINT NOT NULL,
    objectName                   VARCHAR(255),
    data                         TEXT,
    userId                       BIGINT DEFAULT 0,
    username                     VARCHAR(255),
    actionType                   VARCHAR(255),
    objectId                     BIGINT DEFAULT 0,
    date                         TIMESTAMP,
    PRIMARY KEY (id_histmodaliteradiotherapie)
);
CREATE SEQUENCE historique.histmodaliteradiotherapie_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE historique.modaliteradiotherapie_hist IS 'HistModaliteRadiotherapie';
		COMMENT
ON COLUMN historique.modaliteradiotherapie_hist.objectName IS 'objectName';
		COMMENT
ON COLUMN historique.modaliteradiotherapie_hist.data IS 'data';
		COMMENT
ON COLUMN historique.modaliteradiotherapie_hist.userId IS 'userId';
		COMMENT
ON COLUMN historique.modaliteradiotherapie_hist.username IS 'username';
		COMMENT
ON COLUMN historique.modaliteradiotherapie_hist.actionType IS 'actionType';
		COMMENT
ON COLUMN historique.modaliteradiotherapie_hist.objectId IS 'objectId';
		COMMENT
ON COLUMN historique.modaliteradiotherapie_hist.date IS 'date';

/*
	Structure for the historique.categorierole_hist table : 
*/

CREATE TABLE historique.categorierole_hist
(
    id_histcategorierole BIGINT NOT NULL,
    objectName           VARCHAR(255),
    data                 TEXT,
    userId               BIGINT DEFAULT 0,
    username             VARCHAR(255),
    actionType           VARCHAR(255),
    objectId             BIGINT DEFAULT 0,
    date                 TIMESTAMP,
    PRIMARY KEY (id_histcategorierole)
);
CREATE SEQUENCE historique.histcategorierole_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE historique.categorierole_hist IS 'HistCategorieRole';
		COMMENT
ON COLUMN historique.categorierole_hist.objectName IS 'objectName';
		COMMENT
ON COLUMN historique.categorierole_hist.data IS 'data';
		COMMENT
ON COLUMN historique.categorierole_hist.userId IS 'userId';
		COMMENT
ON COLUMN historique.categorierole_hist.username IS 'username';
		COMMENT
ON COLUMN historique.categorierole_hist.actionType IS 'actionType';
		COMMENT
ON COLUMN historique.categorierole_hist.objectId IS 'objectId';
		COMMENT
ON COLUMN historique.categorierole_hist.date IS 'date';

/*
	Structure for the historique.organe_hist table : 
*/

CREATE TABLE historique.organe_hist
(
    id_historgane BIGINT NOT NULL,
    objectName    VARCHAR(255),
    data          TEXT,
    userId        BIGINT DEFAULT 0,
    username      VARCHAR(255),
    actionType    VARCHAR(255),
    objectId      BIGINT DEFAULT 0,
    date          TIMESTAMP,
    PRIMARY KEY (id_historgane)
);
CREATE SEQUENCE historique.historgane_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE historique.organe_hist IS 'HistOrgane';
		COMMENT
ON COLUMN historique.organe_hist.objectName IS 'objectName';
		COMMENT
ON COLUMN historique.organe_hist.data IS 'data';
		COMMENT
ON COLUMN historique.organe_hist.userId IS 'userId';
		COMMENT
ON COLUMN historique.organe_hist.username IS 'username';
		COMMENT
ON COLUMN historique.organe_hist.actionType IS 'actionType';
		COMMENT
ON COLUMN historique.organe_hist.objectId IS 'objectId';
		COMMENT
ON COLUMN historique.organe_hist.date IS 'date';

/*
	Structure for the historique.utilisateur_hist table : 
*/

CREATE TABLE historique.utilisateur_hist
(
    id_histutilisateur BIGINT NOT NULL,
    objectName         VARCHAR(255),
    data               TEXT,
    userId             BIGINT DEFAULT 0,
    username           VARCHAR(255),
    actionType         VARCHAR(255),
    objectId           BIGINT DEFAULT 0,
    date               TIMESTAMP,
    PRIMARY KEY (id_histutilisateur)
);
CREATE SEQUENCE historique.histutilisateur_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE historique.utilisateur_hist IS 'HistUtilisateur';
		COMMENT
ON COLUMN historique.utilisateur_hist.objectName IS 'objectName';
		COMMENT
ON COLUMN historique.utilisateur_hist.data IS 'data';
		COMMENT
ON COLUMN historique.utilisateur_hist.userId IS 'userId';
		COMMENT
ON COLUMN historique.utilisateur_hist.username IS 'username';
		COMMENT
ON COLUMN historique.utilisateur_hist.actionType IS 'actionType';
		COMMENT
ON COLUMN historique.utilisateur_hist.objectId IS 'objectId';
		COMMENT
ON COLUMN historique.utilisateur_hist.date IS 'date';



CREATE TABLE public.prescriptionradiotherapie_
(
    id                          BIGINT NOT NULL,
    datePrescription            TIMESTAMP,
    fractionnement              INTEGER DEFAULT 0,
    dateSouhaiteDebutTraitement TIMESTAMP,
    observation                 TEXT,
    protocoleinclusion_id       BIGINT,
    FOREIGN KEY (protocoleinclusion_id) REFERENCES public.protocoleinclusion_ (id_protocoleinclusion),
    visee_id                    BIGINT,
    FOREIGN KEY (visee_id) REFERENCES public.visee_ (id_visee),
    medecinprescripteur_id      BIGINT,
    FOREIGN KEY (medecinprescripteur_id) REFERENCES public.personnel_ (id_personnel),
    patient_id                  BIGINT,
    FOREIGN KEY (patient_id) REFERENCES public.patient_ (id_patient),
    organe_id                   BIGINT,
    FOREIGN KEY (organe_id) REFERENCES public.organe_ (id_organe),
    modaliteradiotherapie_id    BIGINT,
    FOREIGN KEY (modaliteradiotherapie_id) REFERENCES public.modaliteradiotherapie_ (id_modaliteradiotherapie),
    frequenceradiotherapie_id   BIGINT,
    FOREIGN KEY (frequenceradiotherapie_id) REFERENCES public.frequenceradiotherapie_ (id_frequenceradiotherapie),
    etablissement_id            BIGINT,
    FOREIGN KEY (etablissement_id) REFERENCES public.etablissement_ (id_etablissement),
    createdby                   VARCHAR(255),
    createdon                   TIMESTAMP,
    updatedby                   VARCHAR(255),
    updatedon                   TIMESTAMP,
    PRIMARY KEY (id)
);
CREATE SEQUENCE public.prescriptionradiotherapie_seq START WITH 1 INCREMENT BY 1;
CREATE INDEX idx_prescriptionRadiotherapie_protocoleinclusion_id ON public.prescriptionradiotherapie_ (protocoleinclusion_id);
CREATE INDEX idx_prescriptionRadiotherapie_visee_id ON public.prescriptionradiotherapie_ (visee_id);
CREATE INDEX idx_prescriptionRadiotherapie_medecinprescripteur_id ON public.prescriptionradiotherapie_ (medecinprescripteur_id);
CREATE INDEX idx_prescriptionRadiotherapie_patient_id ON public.prescriptionradiotherapie_ (patient_id);
CREATE INDEX idx_prescriptionRadiotherapie_organe_id ON public.prescriptionradiotherapie_ (organe_id);
CREATE INDEX idx_prescriptionRadiotherapie_modaliteradiotherapie_id ON public.prescriptionradiotherapie_ (modaliteradiotherapie_id);
CREATE INDEX idx_prescriptionRadiotherapie_frequenceradiotherapie_id ON public.prescriptionradiotherapie_ (frequenceradiotherapie_id);
CREATE INDEX idx_prescriptionRadiotherapie_etablissement_id ON public.prescriptionradiotherapie_ (etablissement_id);
COMMENT
ON TABLE public.prescriptionradiotherapie_ IS 'prescriptionRadiotherapie';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.datePrescription IS 'datePrescription';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.fractionnement IS 'fractionnement';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.dateSouhaiteDebutTraitement IS 'dateSouhaiteDebutTraitement';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.observation IS 'observation';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.protocoleinclusion_id IS 'protocoleInclusion';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.visee_id IS 'visee';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.medecinprescripteur_id IS 'medecinPrescripteur';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.patient_id IS 'patient';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.organe_id IS 'organe';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.modaliteradiotherapie_id IS 'modaliteRadiotherapie';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.frequenceradiotherapie_id IS 'frequenceRadiotherapie';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.etablissement_id IS 'etablissement';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.createdby IS 'utilisateur de cr�ation';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.createdon IS 'date de cr�ation';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.updatedby IS 'utilisateur de modification';
		COMMENT
ON COLUMN public.prescriptionradiotherapie_.updatedon IS 'date de modification';
		COMMENT
ON COLUMN prescriptionradiotherapie_.id IS 'identifiant prescriptionradiotherapie';


CREATE TABLE public.seanceradiotherapie_
(
    id                           BIGINT NOT NULL,
    dateDebut                    TIMESTAMP,
    dateFin                      TIMESTAMP,
    marquePresence               BOOLEAN DEFAULT false,
    prescriptionradiotherapie_id BIGINT,
    FOREIGN KEY (prescriptionradiotherapie_id) REFERENCES public.prescriptionradiotherapie_ (id),
    etablissement_id             BIGINT,
    FOREIGN KEY (etablissement_id) REFERENCES public.etablissement_ (id_etablissement),
    createdby                    VARCHAR(255),
    createdon                    TIMESTAMP,
    updatedby                    VARCHAR(255),
    updatedon                    TIMESTAMP,
    PRIMARY KEY (id)
);
CREATE SEQUENCE public.seanceradiotherapie_seq START WITH 1 INCREMENT BY 1;
CREATE INDEX idx_seanceRadiotherapie_prescriptionradiotherapie_id ON public.seanceradiotherapie_ (prescriptionradiotherapie_id);
CREATE INDEX idx_seanceRadiotherapie_etablissement_id ON public.seanceradiotherapie_ (etablissement_id);
COMMENT
ON TABLE public.seanceradiotherapie_ IS 'seanceRadiotherapie';
		COMMENT
ON COLUMN public.seanceradiotherapie_.dateDebut IS 'dateDebut';
		COMMENT
ON COLUMN public.seanceradiotherapie_.dateFin IS 'dateFin';
		COMMENT
ON COLUMN public.seanceradiotherapie_.marquePresence IS 'marquePresence';
		COMMENT
ON COLUMN public.seanceradiotherapie_.prescriptionradiotherapie_id IS 'prescriptionRadiotherapie';
		COMMENT
ON COLUMN public.seanceradiotherapie_.etablissement_id IS 'etablissement';
		COMMENT
ON COLUMN public.seanceradiotherapie_.createdby IS 'utilisateur de cr�ation';
		COMMENT
ON COLUMN public.seanceradiotherapie_.createdon IS 'date de cr�ation';
		COMMENT
ON COLUMN public.seanceradiotherapie_.updatedby IS 'utilisateur de modification';
		COMMENT
ON COLUMN public.seanceradiotherapie_.updatedon IS 'date de modification';
		COMMENT
ON COLUMN seanceradiotherapie_.id IS 'identifiant seanceradiotherapie';


/*
	Structure for the historique.parametrage_hist table : 
*/

CREATE TABLE historique.parametrage_hist
(
    id_histparametrage BIGINT NOT NULL,
    objectName         VARCHAR(255),
    data               TEXT,
    userId             BIGINT DEFAULT 0,
    username           VARCHAR(255),
    actionType         VARCHAR(255),
    objectId           BIGINT DEFAULT 0,
    date               TIMESTAMP,
    PRIMARY KEY (id_histparametrage)
);
CREATE SEQUENCE historique.histparametrage_seq START WITH 1 INCREMENT BY 1;
COMMENT
ON TABLE historique.parametrage_hist IS 'HistParametrage';
		COMMENT
ON COLUMN historique.parametrage_hist.objectName IS 'objectName';
		COMMENT
ON COLUMN historique.parametrage_hist.data IS 'data';
		COMMENT
ON COLUMN historique.parametrage_hist.userId IS 'userId';
		COMMENT
ON COLUMN historique.parametrage_hist.username IS 'username';
		COMMENT
ON COLUMN historique.parametrage_hist.actionType IS 'actionType';
		COMMENT
ON COLUMN historique.parametrage_hist.objectId IS 'objectId';
		COMMENT
ON COLUMN historique.parametrage_hist.date IS 'date';

ALTER SEQUENCE profil_seq RESTART WITH 1;