-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 10 mars 2022 à 00:56
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `getaway`
--

-- --------------------------------------------------------

--
-- Structure de la table `activite`
--

CREATE TABLE `activite` (
  `RefAct` int(11) NOT NULL,
  `Nom` varchar(50) NOT NULL,
  `Descrip` varchar(50) NOT NULL,
  `Duree` varchar(50) NOT NULL,
  `NbrPlace` int(11) NOT NULL,
  `Date` varchar(100) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Location` varchar(50) NOT NULL,
  `Prix` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `activite`
--

INSERT INTO `activite` (`RefAct`, `Nom`, `Descrip`, `Duree`, `NbrPlace`, `Date`, `Type`, `Location`, `Prix`) VALUES
(4, 'takwira', 'wow', '2H3', 19, '12-03-2022', 'sport', 'ariana', 255),
(5, 'sky', 'wow', '2H4', 10, '13-03-2022', 'diver', 'arianaa', 255),
(6, 'foot', 'match ', '2H3', 20, '12-03-2022', 'bla', 'ariana', 255),
(7, 'yoga', 'match ', '2H3', 20, '12-03-2022', 'bla', 'ariana', 255),
(8, 'handball', 'wow', '2H3', 20, '12-03-2022', 'sport', 'tunis', 255);

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `nom`, `prenom`, `email`, `adresse`, `password`) VALUES
(2, 'hello', 'zeineb', 'ziouba@gmail.com', 'tt', 'QVZkBomDLSbitS4C9lGaUA=='),
(4, 'Zeineb', 'mbarki', 'ziouba@gmail.com', 'Manouba', 'QVZkBomDLSbitS4C9lGaUA=='),
(5, 'admin', 'admin', 'ziouba@gmail.com', 'esprit', 'LWZYuvWBFO2yRhcTzP+/4Q==');

-- --------------------------------------------------------

--
-- Structure de la table `agent-aerien`
--

CREATE TABLE `agent-aerien` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nomAgence` varchar(255) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `agent-aerien`
--

INSERT INTO `agent-aerien` (`id`, `nom`, `prenom`, `email`, `nomAgence`, `password`) VALUES
(1, 'TUNISA', 'aaaaaaa', 'qatar@gmail.com', 'eeee', 'elVgegqD2MGnZW6jV+CBncRYYp8Y52vFNrzD0/GaKtU='),
(6, 'agent', 'agent', 'qatar@gmail.com', 'eeee', '2RIUU/AMP3ZM2a2VsPb+3w==');

-- --------------------------------------------------------

--
-- Structure de la table `avion`
--

CREATE TABLE `avion` (
  `id_avion` int(11) NOT NULL,
  `nbr_place` int(11) NOT NULL,
  `id_agence` int(11) NOT NULL,
  `nom_avion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `avion`
--

INSERT INTO `avion` (`id_avion`, `nbr_place`, `id_agence`, `nom_avion`) VALUES
(1, 77, 1, ''),
(2, 40, 6, 'tayraa'),
(3, 40, 6, 'tunsiiara');

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `RefAvis` int(11) NOT NULL,
  `Message` varchar(250) NOT NULL,
  `Date` date NOT NULL,
  `Id` int(11) NOT NULL,
  `RefActivite` int(11) NOT NULL,
  `Rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `avis`
--

INSERT INTO `avis` (`RefAvis`, `Message`, `Date`, `Id`, `RefActivite`, `Rating`) VALUES
(2, 'Satisfait', '2022-03-09', 8, 4, 4),
(3, 'Satisfait', '2022-03-09', 8, 4, 3),
(4, 'Satisfait', '2022-03-09', 8, 4, 2);

-- --------------------------------------------------------

--
-- Structure de la table `categorievoy`
--

CREATE TABLE `categorievoy` (
  `idcat` int(11) NOT NULL,
  `nomcat` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorievoy`
--

INSERT INTO `categorievoy` (`idcat`, `nomcat`) VALUES
(1, 'omra'),
(5, 'honney moon1'),
(7, 'fin dannee');

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE `category` (
  `id_categ` int(11) NOT NULL,
  `nom_categ` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`id_categ`, `nom_categ`) VALUES
(1, 'villa'),
(2, 'hotel'),
(3, 'caravane'),
(5, '1'),
(6, '1');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `etat` int(11) NOT NULL,
  `securityQ` varchar(50) NOT NULL,
  `answer` varchar(50) NOT NULL,
  `NumTel` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `nom`, `prenom`, `email`, `password`, `etat`, `securityQ`, `answer`, `NumTel`) VALUES
(1, 'mbarki', 'zeineb', 'ziouba@gmail.com', '22', 1, '', '', '0'),
(4, 'testaaa', 'pp', 'eee@gmail.com', 'elVgegqD2MGnZW6jV+CBncRYYp8Y52vFNrzD0/GaKtU=', 1, 'pays de ton reve', 'aaaaaaaaa', '5555555'),
(5, 'zouba', 'a', 'zeineb@gmail.com', '3GqMJFrd+MP4lHwtVGW+l9zCp5iONod8sr4JykXWf7fv90ygL0H/SxU0qnpXuST9', 1, 'pays de ton reve', 'zzz', '58421018'),
(6, 'client', 'client', 'client@gmail.com', 'qL/IsoptV244jtzEuKoXlg==', 1, 'votre premiere voiture', 'audio', '58421017'),
(8, 'client', 'client', 'client@gmail.com', 'QVZkBomDLSbitS4C9lGaUA==', 1, 'pays de ton reve', 'tunis', '58421017');

-- --------------------------------------------------------

--
-- Structure de la table `hebergement`
--

CREATE TABLE `hebergement` (
  `referance` int(11) NOT NULL,
  `offreur_id` int(11) DEFAULT NULL,
  `paye` varchar(15) DEFAULT NULL,
  `adress` varchar(50) DEFAULT NULL,
  `prix` float DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `photo` varchar(999) DEFAULT NULL,
  `date_start` date DEFAULT NULL,
  `date_end` date DEFAULT NULL,
  `contact` int(11) DEFAULT NULL,
  `nbr_detoile` int(11) DEFAULT NULL,
  `nbr_suite` int(11) DEFAULT NULL,
  `nbr_parking` int(11) DEFAULT NULL,
  `model_caravane` varchar(15) DEFAULT NULL,
  `id_categ` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hebergement`
--

INSERT INTO `hebergement` (`referance`, `offreur_id`, `paye`, `adress`, `prix`, `description`, `photo`, `date_start`, `date_end`, `contact`, `nbr_detoile`, `nbr_suite`, `nbr_parking`, `model_caravane`, `id_categ`) VALUES
(1, 1, 'tunis', 'tunis', 2555, 'aaaaa', 'a', '2022-02-01', '2022-02-16', 4, 4, 4, 22, '22', 1),
(2, 1, 'tunis', 'soussa', 123, 'aaaaaaaaaa', 'aaa', '2022-03-03', '2022-04-08', 4, 4, 4, 22, '22', 1),
(3, 1, 'tunis', 'tunis', 2555, 'aaaaa', 'a', '2022-03-09', '2022-03-31', 4, 4, 22, 4, '22', 1),
(4, 1, 'tunis', 'soussa', 123, 'aaaaaaaaaa', 'aaa', '2022-03-08', '2022-03-20', 4, 4, 4, 4, '', 1),
(5, 2, 'tunis', 'ariana', 217, '112265', '', '2022-03-07', '2022-04-17', 2555520, 22, 22, 5, '1', 1),
(6, 2, 'tunis', 'ariana', 255, '112265', 'https://media.istockphoto.com/photos/downtown-cleveland-hotel-entrance-and-waiting-taxi-cab-picture-id472899538?b=1&k=20&m=472899538&s=170667a&w=0&h=oGDM26vWKgcKA3ARp2da-H4St2dMEhJg23TTBeJgPDE=', '2022-03-07', '2022-04-17', 2555520, 22, 5, 5, '1', 1);

-- --------------------------------------------------------

--
-- Structure de la table `offreur`
--

CREATE TABLE `offreur` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `numtl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `offreur`
--

INSERT INTO `offreur` (`id`, `nom`, `prenom`, `email`, `password`, `numtl`) VALUES
(1, 'mbarki', 'zeineb', 'zouba@gmail.com', 'elVgegqD2MGnZW6jV+CBncRYYp8Y52vFNrzD0/GaKtU=', 252525252),
(2, 'offreur', 'offreur', 'of@of11.com', 'QVZkBomDLSbitS4C9lGaUA==', 25252525);

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE `paiement` (
  `id` int(11) NOT NULL,
  `modalite_paiement` varchar(30) NOT NULL,
  `montant` float NOT NULL,
  `date` date NOT NULL,
  `id_reservation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `paiement`
--

INSERT INTO `paiement` (`id`, `modalite_paiement`, `montant`, `date`, `id_reservation`) VALUES
(76, 'Cache', 250, '2022-03-09', 129),
(77, 'Cache', 0, '2022-03-09', 130),
(78, 'Cache', 246, '2022-03-09', 131),
(79, 'Cache', 2000, '2022-03-09', 132),
(80, 'Cache', 2500, '2022-03-09', 133),
(82, 'Cache', 35000, '2022-03-09', 135),
(83, 'Carte bancaire', 35000, '2022-03-09', 136),
(84, 'Cheque', 210000, '2022-03-09', 137),
(85, 'Cache', 2295, '2022-03-09', 138);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `idR` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  `objet` varchar(100) NOT NULL,
  `description` varchar(200) NOT NULL,
  `etat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`idR`, `idClient`, `objet`, `description`, `etat`) VALUES
(18, 8, 'vol ', 'fetetni tayara', '1');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `date_reservation` date NOT NULL,
  `nbr_place` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `id_client` int(11) NOT NULL,
  `id_voyage` int(11) DEFAULT NULL,
  `id_activite` int(11) DEFAULT NULL,
  `id_vol` int(11) DEFAULT NULL,
  `id_hebergement` int(11) DEFAULT NULL,
  `etat` varchar(30) NOT NULL,
  `type` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id`, `date_reservation`, `nbr_place`, `date_debut`, `date_fin`, `id_client`, `id_voyage`, `id_activite`, `id_vol`, `id_hebergement`, `etat`, `type`) VALUES
(129, '2022-03-09', 1, '2020-05-22', '2020-05-22', 8, NULL, NULL, 7, NULL, 'Approuve', 'Vol'),
(130, '2022-03-09', 2, '2020-05-22', '2020-05-22', 8, NULL, NULL, 6, NULL, 'Annule', 'Vol'),
(131, '2022-03-09', 0, '2022-03-09', '2022-03-11', 8, NULL, NULL, NULL, 4, 'Approuve', 'Hebergement'),
(132, '2022-03-09', 4, '2020-05-22', '2020-05-22', 8, NULL, NULL, 6, NULL, 'Approuve', 'Vol'),
(133, '2022-03-09', 5, '2020-05-22', '2020-05-22', 8, NULL, NULL, 6, NULL, 'Approuve', 'Vol'),
(135, '2022-03-09', 1, '2022-03-05', '2022-03-10', 8, 70, NULL, NULL, NULL, 'Approuve', 'Voyage'),
(136, '2022-03-09', 1, '2022-03-05', '2022-03-10', 8, 70, NULL, NULL, NULL, 'Approuve', 'Voyage'),
(137, '2022-03-09', 6, '2022-03-05', '2022-03-10', 8, 69, NULL, NULL, NULL, 'Approuve', 'Voyage'),
(138, '2022-03-09', 10, '0018-09-12', '0018-09-12', 8, NULL, 5, NULL, NULL, 'Approuve', 'vol');

-- --------------------------------------------------------

--
-- Structure de la table `vol`
--

CREATE TABLE `vol` (
  `id_vol` int(11) NOT NULL,
  `date_depart` datetime NOT NULL,
  `date_arrivee` datetime NOT NULL,
  `ville_depart` varchar(60) NOT NULL,
  `ville_arrivee` varchar(50) NOT NULL,
  `nbr_placedispo` int(11) NOT NULL,
  `id_avion` int(11) NOT NULL,
  `prix` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `vol`
--

INSERT INTO `vol` (`id_vol`, `date_depart`, `date_arrivee`, `ville_depart`, `ville_arrivee`, `nbr_placedispo`, `id_avion`, `prix`) VALUES
(6, '2020-05-22 12:11:12', '2020-05-22 17:11:15', 'tunis', 'Paris', 71, 2, 500),
(7, '2020-05-22 12:11:12', '2020-05-22 18:11:15', 'tunis', 'london', 79, 3, 250);

-- --------------------------------------------------------

--
-- Structure de la table `voyageorganise`
--

CREATE TABLE `voyageorganise` (
  `idVoy` int(11) NOT NULL,
  `villeDepart` varchar(30) NOT NULL,
  `villeDest` varchar(30) NOT NULL,
  `dateDepart` varchar(20) NOT NULL,
  `dateArrive` varchar(20) NOT NULL,
  `nbrPlace` int(11) NOT NULL,
  `idCat` int(11) NOT NULL,
  `prix` float NOT NULL,
  `description` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `voyageorganise`
--

INSERT INTO `voyageorganise` (`idVoy`, `villeDepart`, `villeDest`, `dateDepart`, `dateArrive`, `nbrPlace`, `idCat`, `prix`, `description`) VALUES
(65, 'tunis', 'usa', '2022-03-05', '2022-03-10', 41, 1, 35000, ''),
(67, 'tunis', 'hinde', '05-03-2022', '10-03-2022', 41, 7, 35000, 'adadadfad'),
(68, 'tunis', 'usa', '2022-03-05', '2022-03-10', 41, 5, 35000, 'adadadfad'),
(69, 'tunis', 'turky', '2022-03-05', '2022-03-10', 35, 5, 35000, 'adadadfad'),
(70, 'tunis', 'qatar', '2022-03-05', '2022-03-10', 39, 5, 35000, 'adadadfad');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `activite`
--
ALTER TABLE `activite`
  ADD PRIMARY KEY (`RefAct`);

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `agent-aerien`
--
ALTER TABLE `agent-aerien`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `avion`
--
ALTER TABLE `avion`
  ADD PRIMARY KEY (`id_avion`),
  ADD KEY `id_agence` (`id_agence`);

--
-- Index pour la table `avis`
--
ALTER TABLE `avis`
  ADD PRIMARY KEY (`RefAvis`),
  ADD KEY `fk_idavis` (`Id`),
  ADD KEY `frk_act` (`RefActivite`);

--
-- Index pour la table `categorievoy`
--
ALTER TABLE `categorievoy`
  ADD PRIMARY KEY (`idcat`);

--
-- Index pour la table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id_categ`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `hebergement`
--
ALTER TABLE `hebergement`
  ADD PRIMARY KEY (`referance`),
  ADD KEY `fk_off` (`offreur_id`),
  ADD KEY `fk_categ` (`id_categ`);

--
-- Index pour la table `offreur`
--
ALTER TABLE `offreur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_reservation` (`id_reservation`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`idR`),
  ADD KEY `idClient` (`idClient`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_client` (`id_client`),
  ADD KEY `fk_voyage` (`id_voyage`),
  ADD KEY `fk_act` (`id_activite`),
  ADD KEY `id_vol` (`id_vol`),
  ADD KEY `fk_heb` (`id_hebergement`);

--
-- Index pour la table `vol`
--
ALTER TABLE `vol`
  ADD PRIMARY KEY (`id_vol`),
  ADD KEY `id_avion` (`id_avion`);

--
-- Index pour la table `voyageorganise`
--
ALTER TABLE `voyageorganise`
  ADD PRIMARY KEY (`idVoy`),
  ADD KEY `idCat` (`idCat`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `activite`
--
ALTER TABLE `activite`
  MODIFY `RefAct` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `agent-aerien`
--
ALTER TABLE `agent-aerien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `avion`
--
ALTER TABLE `avion`
  MODIFY `id_avion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `avis`
--
ALTER TABLE `avis`
  MODIFY `RefAvis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `categorievoy`
--
ALTER TABLE `categorievoy`
  MODIFY `idcat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `category`
--
ALTER TABLE `category`
  MODIFY `id_categ` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `hebergement`
--
ALTER TABLE `hebergement`
  MODIFY `referance` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `offreur`
--
ALTER TABLE `offreur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `paiement`
--
ALTER TABLE `paiement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `idR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=139;

--
-- AUTO_INCREMENT pour la table `vol`
--
ALTER TABLE `vol`
  MODIFY `id_vol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `voyageorganise`
--
ALTER TABLE `voyageorganise`
  MODIFY `idVoy` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `avion`
--
ALTER TABLE `avion`
  ADD CONSTRAINT `avion_ibfk_1` FOREIGN KEY (`id_agence`) REFERENCES `agent-aerien` (`id`);

--
-- Contraintes pour la table `avis`
--
ALTER TABLE `avis`
  ADD CONSTRAINT `fk_idavis` FOREIGN KEY (`Id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `frk_act` FOREIGN KEY (`RefActivite`) REFERENCES `activite` (`RefAct`);

--
-- Contraintes pour la table `hebergement`
--
ALTER TABLE `hebergement`
  ADD CONSTRAINT `fk_categ` FOREIGN KEY (`id_categ`) REFERENCES `category` (`id_categ`),
  ADD CONSTRAINT `fk_off` FOREIGN KEY (`offreur_id`) REFERENCES `offreur` (`id`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk_act` FOREIGN KEY (`id_activite`) REFERENCES `activite` (`RefAct`),
  ADD CONSTRAINT `fk_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `fk_heb` FOREIGN KEY (`id_hebergement`) REFERENCES `hebergement` (`referance`),
  ADD CONSTRAINT `fk_voyage` FOREIGN KEY (`id_voyage`) REFERENCES `voyageorganise` (`idVoy`),
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`id_vol`) REFERENCES `vol` (`id_vol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
