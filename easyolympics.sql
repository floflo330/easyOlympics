-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 30 mai 2024 à 22:51
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `easyolympics`
--

-- --------------------------------------------------------

--
-- Structure de la table `athletes`
--

CREATE TABLE `athletes` (
  `idAthlete` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `idCountry` int(11) DEFAULT NULL,
  `birthDate` date NOT NULL,
  `sex` enum('M','F') NOT NULL,
  `idSport` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `athletes`
--

INSERT INTO `athletes` (`idAthlete`, `name`, `surname`, `email`, `idCountry`, `birthDate`, `sex`, `idSport`) VALUES
(1, 'Zinedine', 'Zidane', 'zinedine.zidane@example.com', 1, '1972-06-23', 'M', 4),
(2, 'Marie-José', 'Pérec', 'marie-jose.perec@example.com', 1, '1968-05-09', 'F', 1),
(3, 'Tony', 'Parker', 'tony.parker@example.com', 1, '1982-05-17', 'M', 5),
(4, 'Laure', 'Manaudou', 'laure.manaudou@example.com', 1, '1986-10-09', 'F', 2),
(5, 'Sebastian', 'Vettel', 'sebastian.vettel@example.com', 3, '1987-07-03', 'M', 21),
(6, 'Steffi', 'Graf', 'steffi.graf@example.com', 3, '1969-06-14', 'F', 6),
(7, 'Michael', 'Schumacher', 'michael.schumacher@example.com', 3, '1969-01-03', 'M', 21),
(8, 'Dirk', 'Nowitzki', 'dirk.nowitzki@example.com', 3, '1978-06-19', 'M', 5),
(10, 'Sofia', 'Goggia', 'sofia.goggia@example.com', 5, '1992-11-15', 'F', 15),
(11, 'Alessandro', 'Del Piero', 'alessandro.delpiero@example.com', 5, '1974-11-09', 'M', 4),
(12, 'Federica', 'Pellegrini', 'federica.pellegrini@example.com', 5, '1988-08-05', 'F', 2),
(13, 'Raphaël', 'Varane', 'raphael.varane@example.com', 1, '1993-04-25', 'M', 4),
(14, 'Marion', 'Bartoli', 'marion.bartoli@example.com', 1, '1984-10-02', 'F', 6),
(15, 'Nicolas', 'Anelka', 'nicolas.anelka@example.com', 1, '1979-03-14', 'M', 4),
(16, 'Sarah', 'Abitbol', 'sarah.abitbol@example.com', 1, '1975-08-08', 'F', 13),
(17, 'Franck', 'Ribéry', 'franck.ribery@example.com', 1, '1983-04-07', 'M', 4),
(18, 'Camille', 'Lacourt', 'camille.lacourt@example.com', 1, '1985-04-22', 'M', 2),
(19, 'Mélina', 'Robert-Michon', 'melina.robert@example.com', 1, '1979-07-18', 'F', 19),
(20, 'Benoît', 'Caranobe', 'benoit.caranobe@example.com', 1, '1980-08-24', 'M', 3),
(21, 'Manon', 'Brunet', 'manon.brunet@example.com', 1, '1996-09-19', 'F', 14),
(23, 'Isabelle', 'Delobel', 'isabelle.delobel@example.com', 1, '1978-12-17', 'F', 17),
(24, 'Loïc', 'Piétri', 'loic.pietri@example.com', 1, '1990-09-30', 'M', 9),
(25, 'Laury', 'Thilleman', 'laury.thilleman@example.com', 1, '1991-07-30', 'F', 7),
(26, 'Antoine', 'Dénériaz', 'antoine.deneriaz@example.com', 1, '1976-06-18', 'M', 15),
(27, 'Céline', 'Goberville', 'celine.goberville@example.com', 1, '1986-10-20', 'F', 19),
(28, 'Luc', 'Abalo', 'luc.abalo@example.com', 1, '1984-09-06', 'M', 12),
(29, 'Nathalie', 'Péchalat', 'nathalie.pechalat@example.com', 1, '1983-12-22', 'F', 17),
(30, 'Fabien', 'Gilot', 'fabien.gilot@example.com', 1, '1984-04-27', 'M', 2),
(31, 'Sophie', 'Cluzel', 'sophie.cluzel@example.com', 1, '1960-12-07', 'F', 11),
(32, 'Lucas', 'Poletti', 'lucas.poletti@example.com', 1, '1998-02-14', 'M', 18),
(33, 'Paula', 'Rietzler', 'paula.rietzler@example.com', 3, '1994-08-10', 'F', 15),
(34, 'Fabian', 'Hambüchen', 'fabian.hambuechen@example.com', 3, '1987-10-25', 'M', 3),
(35, 'Laura', 'Dahlmeier', 'laura.dahlmeier@example.com', 3, '1993-08-22', 'F', 20),
(36, 'Thomas', 'Röhler', 'thomas.rohler@example.com', 3, '1991-09-30', 'M', 19),
(37, 'Heike', 'Drechsler', 'heike.drechsler@example.com', 3, '1964-12-16', 'F', 1),
(38, 'Felix', 'Loch', 'felix.loch@example.com', 3, '1989-07-24', 'M', 24),
(39, 'Kerstin', 'Szymkowiak', 'kerstin.szymkowiak@example.com', 3, '1985-04-05', 'F', 21),
(40, 'Robert', 'Harting', 'robert.harting@example.com', 3, '1984-10-18', 'M', 1),
(41, 'Angelique', 'Kerber', 'angelique.kerber@example.com', 3, '1988-01-18', 'F', 6),
(42, 'Eric', 'Frenzel', 'eric.frenzel@example.com', 3, '1988-11-21', 'M', 24),
(43, 'Maria', 'Höfl-Riesch', 'maria.hofl@example.com', 3, '1984-11-24', 'F', 2),
(44, 'Magdalena', 'Neuner', 'magdalena.neuner@example.com', 3, '1987-02-09', 'F', 19),
(45, 'Max', 'Hopp', 'max.hopp@example.com', 3, '1996-08-29', 'M', 14),
(46, 'Annika', 'Beck', 'annika.beck@example.com', 3, '1994-02-16', 'F', 6),
(47, 'Fritz', 'Fischer', 'fritz.fischer@example.com', 3, '1956-02-16', 'M', 24),
(48, 'Katharina', 'Witt', 'katharina.witt@example.com', 3, '1965-12-03', 'F', 11),
(49, 'Tobias', 'Angerer', 'tobias.angerer@example.com', 3, '1977-04-12', 'M', 2),
(50, 'Nico', 'Rosberg', 'nico.rosberg@example.com', 3, '1985-06-27', 'M', 21),
(52, 'Jens', 'Lehmann', 'jens.lehmann@example.com', 3, '1969-11-10', 'M', 4),
(93, 'Francesco', 'Totti', 'francesco.totti@example.com', 5, '1976-09-27', 'M', 4),
(94, 'Valentina', 'Vezzali', 'valentina.vezzali@example.com', 5, '1974-02-14', 'F', 14),
(95, 'Gianluigi', 'Buffon', 'gianluigi.buffon@example.com', 5, '1978-01-28', 'M', 4),
(96, 'Carolina', 'Kostner', 'carolina.kostner@example.com', 5, '1987-02-08', 'F', 17),
(97, 'Fabio', 'Fognini', 'fabio.fognini@example.com', 5, '1987-05-24', 'M', 6),
(100, 'Andrea', 'Cassara', 'andrea.cassara@example.com', 5, '1982-11-14', 'M', 14),
(101, 'Laura', 'Papi', 'laura.papi@example.com', 5, '1973-06-03', 'F', 13),
(102, 'Davide', 'Astori', 'davide.astori@example.com', 5, '1987-01-07', 'M', 4),
(103, 'Jessica', 'Rossi', 'jessica.rossi@example.com', 5, '1992-02-07', 'F', 20),
(104, 'Fabrizio', 'Donato', 'fabrizio.donato@example.com', 5, '1976-08-14', 'M', 1),
(105, 'Antonietta', 'Di Martino', 'antonietta.dimartino@example.com', 5, '1978-04-08', 'F', 19),
(106, 'Giacomo', 'Agostini', 'giacomo.agostini@example.com', 5, '1942-06-16', 'M', 22),
(107, 'Carlotta', 'Ferlito', 'carlotta.ferlito@example.com', 5, '1995-02-15', 'F', 3),
(108, 'Luca', 'Parmitano', 'luca.parmitano@example.com', 5, '1976-09-27', 'M', 24),
(109, 'Elisabetta', 'Preziosa', 'elisabetta.preziosa@example.com', 5, '1993-10-08', 'F', 3),
(110, 'Simone', 'Biles', 'simone.biles@example.com', 5, '1997-03-14', 'F', 3),
(111, 'Vittorio', 'Brumotti', 'vittorio.brumotti@example.com', 5, '1980-06-22', 'M', 18),
(208, 'Teiva', 'Trillard', 'tt@lol.lol', 1, '2002-03-13', 'F', 2),
(209, 'Kim', 'Jun', 'kim@northkorea.nc', 17, '2024-05-16', 'M', 14),
(210, 'Donald', 'Trump', 'donald.trump@maga.us', 6, '2024-05-15', 'M', 14),
(211, 'Emmanuel', 'Macron', 'e.macron@france.fr', 1, '2024-05-06', 'M', 14),
(212, 'Recep Tayiip', 'Erdogan', 'erdogan@turk.tu', 24, '2005-05-17', 'M', 14),
(213, 'Justin', 'Trudeau', 'j.trudeau@canada.ca', 7, '2024-05-21', 'M', 14),
(214, 'Florent', 'LEmaigre-Dubreuil', 'florent@gmail.com', 1, '2002-08-30', 'F', 14),
(215, 'Antoine', 'Debin', 'ad@gamil.com', 1, '2012-05-25', 'M', 6),
(216, 'Casper', 'Ruud', 'casp@tennis.com', 20, '2013-05-23', 'M', 6),
(217, 'Dutron', 'Jacques', 'jd@gmail.com', 22, '2024-04-30', 'M', 26),
(218, 'Nationale', 'Police', 'p@n.com', 20, '2024-04-29', 'M', 26);

-- --------------------------------------------------------

--
-- Structure de la table `countries`
--

CREATE TABLE `countries` (
  `idCountry` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `countries`
--

INSERT INTO `countries` (`idCountry`, `name`) VALUES
(1, 'France'),
(3, 'Allemagne'),
(4, 'Pays-Bas'),
(5, 'Italie'),
(6, 'États-Unis'),
(7, 'Canada'),
(8, 'Brésil'),
(9, 'Royaume-Uni'),
(10, 'Japon'),
(11, 'Chine'),
(12, 'Australie'),
(13, 'Espagne'),
(14, 'Russie'),
(15, 'Inde'),
(16, 'Mexique'),
(17, 'Corée du Sud'),
(18, 'Afrique du Sud'),
(19, 'Argentine'),
(20, 'Suède'),
(21, 'Norvège'),
(22, 'Suisse'),
(23, 'Grèce'),
(24, 'Turquie'),
(25, 'Égypte'),
(34, 'Irlande'),
(54, 'Irlande');

-- --------------------------------------------------------

--
-- Structure de la table `events`
--

CREATE TABLE `events` (
  `idEvent` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `place` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `idSport` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `events`
--

INSERT INTO `events` (`idEvent`, `name`, `place`, `date`, `time`, `idSport`) VALUES
(1, 'Quart de finale BasketBall Hommes', 'Arena Paris Sud', '2024-07-31', '18:00:00', 5),
(3, 'Finale Tennis', 'Stade Roland Garros', '2024-05-31', '16:00:00', 6),
(5, 'Quart de Finale Gymnastique', 'Arena Porte de la chapelle', '2024-05-25', '09:00:00', 3),
(6, 'Nage Libre POULE 1', 'ARENA SEINE', '2024-05-23', '15:00:00', 2),
(8, 'Tournoi d\'escrime', 'Arena Trocadero', '2024-07-31', '16:00:00', 14),
(11, 'Finale Tennis Hommes', 'Stade Roland Garros', '2024-07-30', '15:00:00', 6),
(12, 'Croisiere', 'Tahiti', '2024-05-31', '10:00:00', 26);

-- --------------------------------------------------------

--
-- Structure de la table `events_athletes`
--

CREATE TABLE `events_athletes` (
  `idAthlete` int(11) NOT NULL,
  `idEvent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `events_athletes`
--

INSERT INTO `events_athletes` (`idAthlete`, `idEvent`) VALUES
(3, 1),
(8, 1),
(12, 6),
(14, 3),
(26, 3),
(26, 11),
(34, 5),
(41, 3),
(43, 6),
(49, 6),
(109, 5),
(208, 6),
(209, 8),
(210, 8),
(211, 8),
(212, 8),
(213, 8),
(216, 11),
(217, 12),
(218, 12);

-- --------------------------------------------------------

--
-- Structure de la table `medals`
--

CREATE TABLE `medals` (
  `idMedal` int(11) NOT NULL,
  `rank` enum('Gold','Silver','Bronze') NOT NULL,
  `idCountry` int(11) DEFAULT NULL,
  `idEvent` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `medals`
--

INSERT INTO `medals` (`idMedal`, `rank`, `idCountry`, `idEvent`) VALUES
(1, 'Gold', 3, 1),
(2, 'Gold', 8, 3),
(3, 'Gold', 3, 5),
(4, 'Silver', 10, 6),
(5, 'Gold', 21, 5),
(6, 'Gold', 8, 1),
(7, 'Gold', 3, 6),
(8, 'Silver', 3, 6),
(9, 'Gold', 3, 6),
(10, 'Silver', 3, 6),
(11, 'Gold', 1, 8),
(12, 'Silver', 6, 8),
(13, 'Bronze', 7, 8),
(14, 'Gold', 1, 8),
(15, 'Silver', 24, 8),
(16, 'Gold', 1, 1),
(17, 'Silver', 3, 1),
(18, 'Gold', 17, 8),
(19, 'Silver', 6, 8),
(20, 'Gold', 22, 12),
(21, 'Silver', 20, 12);

-- --------------------------------------------------------

--
-- Structure de la table `results`
--

CREATE TABLE `results` (
  `idResult` int(11) NOT NULL,
  `idAthlete` int(11) DEFAULT NULL,
  `idEvent` int(11) DEFAULT NULL,
  `score` varchar(100) DEFAULT NULL,
  `time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `results`
--

INSERT INTO `results` (`idResult`, `idAthlete`, `idEvent`, `score`, `time`) VALUES
(1, 208, 6, '', '00:00:12'),
(2, 43, 6, '', '00:00:13'),
(3, 49, 6, '', '00:00:14'),
(4, 208, 6, '', '00:00:12'),
(5, 43, 6, '', '00:00:13'),
(6, 49, 6, '', '00:00:14'),
(7, 43, 6, '', '00:00:00'),
(8, 49, 6, '', '00:00:00'),
(9, 43, 6, '', '00:00:00'),
(10, 49, 6, '', '00:00:00'),
(11, 43, 6, '', '00:00:00'),
(12, 49, 6, '', '00:00:00'),
(13, 211, 8, '', '00:00:00'),
(14, 210, 8, '', '00:00:00'),
(15, 213, 8, '', '00:00:00'),
(16, 211, 8, '1', '00:00:00'),
(17, 212, 8, '0', '00:00:00'),
(18, 3, 1, '3', '00:00:00'),
(19, 8, 1, '2', '00:00:00'),
(20, 209, 8, '', '00:00:00'),
(21, 210, 8, '', '00:00:00'),
(22, 216, 11, '6-6-6', '00:00:00'),
(23, 216, 11, '6-6-6', '00:00:00'),
(24, 216, 11, '666', '00:00:00'),
(25, 216, 11, '666', '00:00:00'),
(26, 216, 11, '', '00:00:00'),
(27, 217, 12, '', '00:00:30'),
(28, 218, 12, '', '00:00:36');

-- --------------------------------------------------------

--
-- Structure de la table `sports`
--

CREATE TABLE `sports` (
  `idSport` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `sports`
--

INSERT INTO `sports` (`idSport`, `name`) VALUES
(1, 'Athlétisme'),
(2, 'Natation'),
(3, 'Gymnastique'),
(4, 'Football'),
(5, 'Basketball'),
(6, 'Tennis'),
(7, 'Cyclisme'),
(8, 'Boxe'),
(9, 'Judo'),
(10, 'Lutte'),
(11, 'Haltérophilie'),
(12, 'Handball'),
(13, 'Volleyball'),
(14, 'Escrime'),
(15, 'Badminton'),
(16, 'Baseball'),
(17, 'Softball'),
(18, 'Rugby'),
(19, 'Tir à l\'arc'),
(20, 'Canoë-kayak'),
(21, 'Taekwondo'),
(22, 'Tennis de table'),
(23, 'Triathlon'),
(24, 'Plongeon'),
(25, 'Water-polo'),
(26, 'Voile'),
(27, 'Équitation'),
(28, 'Hockey sur gazon'),
(29, 'Escalade'),
(30, 'Surf'),
(31, 'Skateboard'),
(32, 'Karate'),
(33, 'Golf'),
(34, 'Pentathlon moderne'),
(38, 'Biathlon');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `idUser` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`idUser`, `name`, `surname`, `email`, `password`) VALUES
(1, 'Lemaigre', 'Florent', 'florent.lema@sfr.fr', 'motdepasse'),
(2, 'Test', 'test', 'test', 'test');

-- --------------------------------------------------------

--
-- Structure de la table `venues`
--

CREATE TABLE `venues` (
  `idVenue` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `athletes`
--
ALTER TABLE `athletes`
  ADD PRIMARY KEY (`idAthlete`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `unique_athlete_name` (`surname`),
  ADD KEY `idCountry` (`idCountry`),
  ADD KEY `idSport` (`idSport`),
  ADD KEY `idx_athlete_name` (`name`),
  ADD KEY `idx_athlete_surname` (`surname`);

--
-- Index pour la table `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`idCountry`);

--
-- Index pour la table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`idEvent`),
  ADD UNIQUE KEY `unique_event_name` (`name`),
  ADD KEY `idSport` (`idSport`),
  ADD KEY `idx_event_date` (`date`),
  ADD KEY `idx_event_name` (`name`);

--
-- Index pour la table `events_athletes`
--
ALTER TABLE `events_athletes`
  ADD PRIMARY KEY (`idAthlete`,`idEvent`),
  ADD KEY `idEvent` (`idEvent`);

--
-- Index pour la table `medals`
--
ALTER TABLE `medals`
  ADD PRIMARY KEY (`idMedal`),
  ADD KEY `idEvent` (`idEvent`),
  ADD KEY `medals_ibfk_1` (`idCountry`);

--
-- Index pour la table `results`
--
ALTER TABLE `results`
  ADD PRIMARY KEY (`idResult`),
  ADD KEY `idAthlete` (`idAthlete`),
  ADD KEY `idEvent` (`idEvent`);

--
-- Index pour la table `sports`
--
ALTER TABLE `sports`
  ADD PRIMARY KEY (`idSport`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`idUser`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Index pour la table `venues`
--
ALTER TABLE `venues`
  ADD PRIMARY KEY (`idVenue`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `athletes`
--
ALTER TABLE `athletes`
  MODIFY `idAthlete` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=219;

--
-- AUTO_INCREMENT pour la table `countries`
--
ALTER TABLE `countries`
  MODIFY `idCountry` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT pour la table `events`
--
ALTER TABLE `events`
  MODIFY `idEvent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `medals`
--
ALTER TABLE `medals`
  MODIFY `idMedal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `results`
--
ALTER TABLE `results`
  MODIFY `idResult` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT pour la table `sports`
--
ALTER TABLE `sports`
  MODIFY `idSport` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `venues`
--
ALTER TABLE `venues`
  MODIFY `idVenue` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `athletes`
--
ALTER TABLE `athletes`
  ADD CONSTRAINT `athletes_ibfk_1` FOREIGN KEY (`idCountry`) REFERENCES `countries` (`idCountry`) ON DELETE CASCADE,
  ADD CONSTRAINT `athletes_ibfk_2` FOREIGN KEY (`idSport`) REFERENCES `sports` (`idSport`) ON DELETE SET NULL;

--
-- Contraintes pour la table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `events_ibfk_1` FOREIGN KEY (`idSport`) REFERENCES `sports` (`idSport`) ON DELETE CASCADE;

--
-- Contraintes pour la table `events_athletes`
--
ALTER TABLE `events_athletes`
  ADD CONSTRAINT `events_athletes_ibfk_1` FOREIGN KEY (`idAthlete`) REFERENCES `athletes` (`idAthlete`) ON DELETE CASCADE,
  ADD CONSTRAINT `events_athletes_ibfk_2` FOREIGN KEY (`idEvent`) REFERENCES `events` (`idEvent`) ON DELETE CASCADE;

--
-- Contraintes pour la table `medals`
--
ALTER TABLE `medals`
  ADD CONSTRAINT `medals_ibfk_1` FOREIGN KEY (`idCountry`) REFERENCES `countries` (`idCountry`) ON DELETE CASCADE,
  ADD CONSTRAINT `medals_ibfk_2` FOREIGN KEY (`idEvent`) REFERENCES `events` (`idEvent`) ON DELETE CASCADE;

--
-- Contraintes pour la table `results`
--
ALTER TABLE `results`
  ADD CONSTRAINT `results_ibfk_1` FOREIGN KEY (`idAthlete`) REFERENCES `athletes` (`idAthlete`) ON DELETE CASCADE,
  ADD CONSTRAINT `results_ibfk_2` FOREIGN KEY (`idEvent`) REFERENCES `events` (`idEvent`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
