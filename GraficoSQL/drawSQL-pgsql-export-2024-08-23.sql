CREATE TABLE "Utente"(
    "NumeroTessera" BIGINT NOT NULL,
    "Nome" CHAR(255) NOT NULL,
    "Cognome" CHAR(255) NOT NULL,
    "DataDiNascita" BIGINT NOT NULL
);
ALTER TABLE
    "Utente" ADD PRIMARY KEY("NumeroTessera");
CREATE TABLE "Libro"(
    "CodiceISBN" BIGINT NOT NULL,
    "Autore" CHAR(255) NOT NULL,
    "Genere" CHAR(255) NOT NULL
);
ALTER TABLE
    "Libro" ADD PRIMARY KEY("CodiceISBN");
CREATE TABLE "Rivista"(
    "CodiceISBN" BIGINT NOT NULL,
    "Periodicità" CHAR(255) NOT NULL
);
ALTER TABLE
    "Rivista" ADD PRIMARY KEY("CodiceISBN");
CREATE TABLE "Elemento"(
    "CodiceISBN" BIGINT NOT NULL,
    "Titolo" CHAR(255) NOT NULL,
    "AnnoPubblicazione" BIGINT NOT NULL
);
ALTER TABLE
    "Elemento" ADD PRIMARY KEY("CodiceISBN");
CREATE TABLE "Prestito"(
    "IDPrestito" BIGINT NOT NULL,
    "NumeroTessera" BIGINT NOT NULL,
    "CodiceISBN" BIGINT NOT NULL,
    "DataInizioPrestito" BIGINT NOT NULL,
    "DataRestituzionePrevista" BIGINT NOT NULL,
    "DataRestituzioneEffettiva" BIGINT NOT NULL
);
ALTER TABLE
    "Prestito" ADD PRIMARY KEY("IDPrestito");
ALTER TABLE
    "Prestito" ADD PRIMARY KEY("NumeroTessera");
ALTER TABLE
    "Prestito" ADD PRIMARY KEY("CodiceISBN");
ALTER TABLE
    "Elemento" ADD CONSTRAINT "elemento_codiceisbn_foreign" FOREIGN KEY("CodiceISBN") REFERENCES "Libro"("CodiceISBN");
ALTER TABLE
    "Elemento" ADD CONSTRAINT "elemento_codiceisbn_foreign" FOREIGN KEY("CodiceISBN") REFERENCES "Prestito"("CodiceISBN");
ALTER TABLE
    "Prestito" ADD CONSTRAINT "prestito_numerotessera_foreign" FOREIGN KEY("NumeroTessera") REFERENCES "Utente"("NumeroTessera");
ALTER TABLE
    "Elemento" ADD CONSTRAINT "elemento_codiceisbn_foreign" FOREIGN KEY("CodiceISBN") REFERENCES "Rivista"("CodiceISBN");