-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 18 Octobre 2017 à 09:20
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `gestion_pesage`
--
CREATE DATABASE IF NOT EXISTS `gestion_pesage` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `gestion_pesage`;

-- --------------------------------------------------------

--
-- Structure de la table `camion`
--

CREATE TABLE IF NOT EXISTS `camion` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(1000) NOT NULL,
  `Immatricule` varchar(50) NOT NULL,
  `Tare` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(1000) NOT NULL,
  `Code` varchar(100) NOT NULL,
  `Adresse` varchar(1000) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Structure de la table `client_valide_deux`
--

CREATE TABLE IF NOT EXISTS `client_valide_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` varchar(100) NOT NULL,
  `mouvement` varchar(50) NOT NULL,
  `Code_client` varchar(100) NOT NULL,
  `Nom_client` varchar(1000) NOT NULL,
  `Code_produit` varchar(50) NOT NULL,
  `Designation_produit` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Pesage` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Structure de la table `connexion`
--

CREATE TABLE IF NOT EXISTS `connexion` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(50) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Nom_PC` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=68 ;

-- --------------------------------------------------------

--
-- Structure de la table `cordonee`
--

CREATE TABLE IF NOT EXISTS `cordonee` (
  `Id` int(11) NOT NULL,
  `Societe` varchar(60) NOT NULL,
  `Adresse` varchar(50) NOT NULL,
  `TEL` varchar(50) NOT NULL,
  `Fax` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `cordonee`
--

INSERT INTO `cordonee` (`Id`, `Societe`, `Adresse`, `TEL`, `Fax`) VALUES
(1, 'Mestiri Pesage', 'Khzema sousse', '73000000', '7300000');

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE IF NOT EXISTS `fournisseur` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(1000) NOT NULL,
  `Code` varchar(100) NOT NULL,
  `Adresse` varchar(1000) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur_valide_deux`
--

CREATE TABLE IF NOT EXISTS `fournisseur_valide_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` varchar(100) NOT NULL,
  `mouvement` varchar(100) NOT NULL,
  `Code_fournisseur` varchar(1000) NOT NULL,
  `Nom_fournisseur` varchar(50) NOT NULL,
  `Code_produit` varchar(50) NOT NULL,
  `Designation_produit` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Pesage` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

-- --------------------------------------------------------

--
-- Structure de la table `impression_bon_deux`
--

CREATE TABLE IF NOT EXISTS `impression_bon_deux` (
  `Id` int(11) NOT NULL,
  `Numero` varchar(50) NOT NULL,
  `mouvement` varchar(50) NOT NULL,
  `Type` varchar(100) NOT NULL,
  `Code_type` varchar(1000) NOT NULL,
  `Nom_type` varchar(1000) NOT NULL,
  `Adresse_type` varchar(100) NOT NULL,
  `Code_produit` varchar(100) NOT NULL,
  `Designation_produit` varchar(100) NOT NULL,
  `Lot_produit` varchar(50) NOT NULL,
  `Prix_produit` varchar(50) NOT NULL,
  `Coefficient_produit` varchar(50) NOT NULL,
  `Date` varchar(1000) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Pesage` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `impression_bon_deux`
--

INSERT INTO `impression_bon_deux` (`Id`, `Numero`, `mouvement`, `Type`, `Code_type`, `Nom_type`, `Adresse_type`, `Code_produit`, `Designation_produit`, `Lot_produit`, `Prix_produit`, `Coefficient_produit`, `Date`, `Heure`, `Pesage`) VALUES
(0, '12', 'Entrant', 'Client', 'C002', 'Saif', 'Sousse', 'P002', 'Pomme de terre', '.', '.', '.', '2017-10-18', '10:14', ' 117.2');

-- --------------------------------------------------------

--
-- Structure de la table `mv_client_deux`
--

CREATE TABLE IF NOT EXISTS `mv_client_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_client` varchar(50) NOT NULL,
  `Nom_client` varchar(50) NOT NULL,
  `Adresse_client` varchar(50) NOT NULL,
  `Produit` varchar(50) NOT NULL,
  `Designation_produit` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Pesage` varchar(50) NOT NULL,
  `Totale_Entrer` varchar(50) NOT NULL,
  `Totale_Sortie` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  `Etat` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `mv_client_deux`
--

INSERT INTO `mv_client_deux` (`Id`, `Code_client`, `Nom_client`, `Adresse_client`, `Produit`, `Designation_produit`, `Date`, `Heure`, `Pesage`, `Totale_Entrer`, `Totale_Sortie`, `Date_debut`, `Date_fin`, `Etat`) VALUES
(1, 'C002', 'Saif', 'Sousse', 'P002', 'Pomme de terre', '2017-10-11', '15:50', '  24.4', '24.4', '24.4', '2016-01-01', '2080-01-01', 'Entrant'),
(2, 'C002', 'Saif', 'Sousse', 'P002', 'Pomme de terre', '2017-10-11', '15:51', '  24.4', '24.4', '24.4', '2016-01-01', '2080-01-01', 'Sortant');

-- --------------------------------------------------------

--
-- Structure de la table `mv_fournisseur_deux`
--

CREATE TABLE IF NOT EXISTS `mv_fournisseur_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_fournisseur` varchar(50) NOT NULL,
  `Nom_fournisseur` varchar(50) NOT NULL,
  `Adresse_fournisseur` varchar(50) NOT NULL,
  `Produit` varchar(50) NOT NULL,
  `Designation_produit` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Pesage` varchar(50) NOT NULL,
  `Totale_Entrer` varchar(50) NOT NULL,
  `Totale_Sortie` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  `Etat` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `mv_fournisseur_deux`
--

INSERT INTO `mv_fournisseur_deux` (`Id`, `Code_fournisseur`, `Nom_fournisseur`, `Adresse_fournisseur`, `Produit`, `Designation_produit`, `Date`, `Heure`, `Pesage`, `Totale_Entrer`, `Totale_Sortie`, `Date_debut`, `Date_fin`, `Etat`) VALUES
(1, 'F001', 'Kaies', 'Sousse', 'P001', 'Olive', '2017-10-11', '15:51', '  24.4', '24.4', '124.4', '2016-01-01', '2080-01-01', 'Entrant'),
(2, 'F001', 'Kaies', 'Sousse', 'P001', 'Olive', '2017-10-11', '15:54', ' 124.4', '24.4', '124.4', '2016-01-01', '2080-01-01', 'Sortant');

-- --------------------------------------------------------

--
-- Structure de la table `mv_produit_entrant_deux`
--

CREATE TABLE IF NOT EXISTS `mv_produit_entrant_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_type` varchar(50) NOT NULL,
  `Nom_type` varchar(50) NOT NULL,
  `Adresse_type` varchar(50) NOT NULL,
  `Produit` varchar(50) NOT NULL,
  `Designation_produit` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Pesage` varchar(50) NOT NULL,
  `Totale` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `mv_produit_entrant_deux`
--

INSERT INTO `mv_produit_entrant_deux` (`Id`, `Code_type`, `Nom_type`, `Adresse_type`, `Produit`, `Designation_produit`, `Date`, `Heure`, `Pesage`, `Totale`, `Date_debut`, `Date_fin`) VALUES
(1, 'C002', 'Saif', 'Sousse', 'P001', 'Olive', '2017-10-11', '15:44', '  24.4', '24.4', '2016-01-01', '2080-01-01');

-- --------------------------------------------------------

--
-- Structure de la table `mv_produit_sortant_deux`
--

CREATE TABLE IF NOT EXISTS `mv_produit_sortant_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_type` varchar(50) NOT NULL,
  `Nom_type` varchar(50) NOT NULL,
  `Adresse_type` varchar(50) NOT NULL,
  `Produit` varchar(50) NOT NULL,
  `Designation_produit` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Pesage` varchar(50) NOT NULL,
  `Totale` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `mv_produit_sortant_deux`
--

INSERT INTO `mv_produit_sortant_deux` (`Id`, `Code_type`, `Nom_type`, `Adresse_type`, `Produit`, `Designation_produit`, `Date`, `Heure`, `Pesage`, `Totale`, `Date_debut`, `Date_fin`) VALUES
(1, '0', '0', '0', '0', '0', '0', '0', '0', '00', '0', '0');

-- --------------------------------------------------------

--
-- Structure de la table `mv_transporteur_deux`
--

CREATE TABLE IF NOT EXISTS `mv_transporteur_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_transporteur` varchar(50) NOT NULL,
  `Nom_transporteur` varchar(50) NOT NULL,
  `Adresse_transporteur` varchar(50) NOT NULL,
  `Produit` varchar(50) NOT NULL,
  `Designation_produit` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Pesage` varchar(50) NOT NULL,
  `Totale_Entrer` varchar(50) NOT NULL,
  `Totale_Sortie` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  `Etat` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `mv_transporteur_deux`
--

INSERT INTO `mv_transporteur_deux` (`Id`, `Code_transporteur`, `Nom_transporteur`, `Adresse_transporteur`, `Produit`, `Designation_produit`, `Date`, `Heure`, `Pesage`, `Totale_Entrer`, `Totale_Sortie`, `Date_debut`, `Date_fin`, `Etat`) VALUES
(1, 'T001', 'Chafik', 'Sousse', 'P001', 'Olive', '2017-10-11', '15:51', ' 124.4', '0.0', '124.4', '2016-01-01', '2080-01-01', 'Sortant');

-- --------------------------------------------------------

--
-- Structure de la table `nbon_deux`
--

CREATE TABLE IF NOT EXISTS `nbon_deux` (
  `Id` int(11) NOT NULL,
  `Numero` varchar(1000) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `nbon_deux`
--

INSERT INTO `nbon_deux` (`Id`, `Numero`) VALUES
(1, '0');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code` varchar(100) NOT NULL,
  `Designation` varchar(1000) NOT NULL,
  `Lot` varchar(100) NOT NULL,
  `Prix` varchar(50) NOT NULL,
  `Coefficient` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Structure de la table `produit_entrant_deux`
--

CREATE TABLE IF NOT EXISTS `produit_entrant_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` varchar(50) NOT NULL,
  `Code_produit` varchar(100) NOT NULL,
  `Designation_produit` varchar(50) NOT NULL,
  `Code_type` varchar(100) NOT NULL,
  `Nom_type` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Pesage` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Structure de la table `produit_sortant_deux`
--

CREATE TABLE IF NOT EXISTS `produit_sortant_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` varchar(50) NOT NULL,
  `Code_produit` varchar(100) NOT NULL,
  `Designation_produit` varchar(50) NOT NULL,
  `Code_type` varchar(100) NOT NULL,
  `Nom_type` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Pesage` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

-- --------------------------------------------------------

--
-- Structure de la table `rapport_client_deux`
--

CREATE TABLE IF NOT EXISTS `rapport_client_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_client` varchar(50) NOT NULL,
  `Nom_client` varchar(50) NOT NULL,
  `Adresse_client` varchar(50) NOT NULL,
  `Code_Produit` varchar(50) NOT NULL,
  `Designation_Produit` varchar(50) NOT NULL,
  `Totale_Entrer` varchar(50) NOT NULL,
  `Totale_sortie` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `rapport_client_deux`
--

INSERT INTO `rapport_client_deux` (`Id`, `Code_client`, `Nom_client`, `Adresse_client`, `Code_Produit`, `Designation_Produit`, `Totale_Entrer`, `Totale_sortie`, `Date_debut`, `Date_fin`) VALUES
(1, 'C002', 'Saif', 'Sousse', 'P002', 'Pomme de terre', '24.4', '24.4', '2016-01-01', '2080-01-01');

-- --------------------------------------------------------

--
-- Structure de la table `rapport_fournisseur_deux`
--

CREATE TABLE IF NOT EXISTS `rapport_fournisseur_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_fournisseur` varchar(50) NOT NULL,
  `Nom_fournisseur` varchar(50) NOT NULL,
  `Adresse_fournisseur` varchar(50) NOT NULL,
  `Code_Produit` varchar(50) NOT NULL,
  `Designation_Produit` varchar(50) NOT NULL,
  `Totale_Entrer` varchar(50) NOT NULL,
  `Totale_sortie` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `rapport_fournisseur_deux`
--

INSERT INTO `rapport_fournisseur_deux` (`Id`, `Code_fournisseur`, `Nom_fournisseur`, `Adresse_fournisseur`, `Code_Produit`, `Designation_Produit`, `Totale_Entrer`, `Totale_sortie`, `Date_debut`, `Date_fin`) VALUES
(1, 'F001', 'Kaies', 'Sousse', 'P001', 'Olive', '24.4', '124.4', '2016-01-01', '2080-01-01'),
(2, 'F002', 'Hassen', '.', 'P001', 'Olive', '0.0', '124.4', '2016-01-01', '2080-01-01');

-- --------------------------------------------------------

--
-- Structure de la table `rapport_produit_entrant_deux`
--

CREATE TABLE IF NOT EXISTS `rapport_produit_entrant_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(50) NOT NULL,
  `Code_Type` varchar(50) NOT NULL,
  `Nom_Type` varchar(50) NOT NULL,
  `Adresse_Type` varchar(50) NOT NULL,
  `Code_Produit` varchar(50) NOT NULL,
  `Designation_Produit` varchar(50) NOT NULL,
  `Totale` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `rapport_produit_entrant_deux`
--

INSERT INTO `rapport_produit_entrant_deux` (`Id`, `Type`, `Code_Type`, `Nom_Type`, `Adresse_Type`, `Code_Produit`, `Designation_Produit`, `Totale`, `Date_debut`, `Date_fin`) VALUES
(1, 'Transporteur', 'T001', 'Chafik', 'Sousse', 'P001', 'Olive', '17.2', '2016-01-01', '2080-01-01'),
(2, 'Fournisseur', 'F002', 'Hassen', '.', 'P001', '#######', '100.0', '2016-01-01', '2080-01-01'),
(3, 'Transporteur', 'T001', 'Chafik', 'Sousse', 'P002', 'Pomme de terre', '24.4', '2016-01-01', '2080-01-01'),
(4, 'Client', 'C002', 'Saif', 'Sousse', 'P002', '#######', '141.59999', '2016-01-01', '2080-01-01');

-- --------------------------------------------------------

--
-- Structure de la table `rapport_produit_entrant_totale_deux`
--

CREATE TABLE IF NOT EXISTS `rapport_produit_entrant_totale_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_Produit` varchar(50) NOT NULL,
  `Designation_Produit` varchar(50) NOT NULL,
  `Totale` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `rapport_produit_entrant_totale_deux`
--

INSERT INTO `rapport_produit_entrant_totale_deux` (`Id`, `Code_Produit`, `Designation_Produit`, `Totale`, `Date_debut`, `Date_fin`) VALUES
(1, 'P001', 'Olive', '117.2', '2016-01-01', '2080-01-01'),
(2, 'P002', 'Pomme de terre', '166.0', '2016-01-01', '2080-01-01');

-- --------------------------------------------------------

--
-- Structure de la table `rapport_produit_sortant_deux`
--

CREATE TABLE IF NOT EXISTS `rapport_produit_sortant_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(50) NOT NULL,
  `Code_Type` varchar(50) NOT NULL,
  `Nom_Type` varchar(50) NOT NULL,
  `Adresse_Type` varchar(50) NOT NULL,
  `Code_Produit` varchar(50) NOT NULL,
  `Designation_Produit` varchar(50) NOT NULL,
  `Totale` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `rapport_produit_sortant_totale_deux`
--

CREATE TABLE IF NOT EXISTS `rapport_produit_sortant_totale_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_Produit` varchar(50) NOT NULL,
  `Designation_Produit` varchar(50) NOT NULL,
  `Totale` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `rapport_produit_sortant_totale_deux`
--

INSERT INTO `rapport_produit_sortant_totale_deux` (`Id`, `Code_Produit`, `Designation_Produit`, `Totale`, `Date_debut`, `Date_fin`) VALUES
(1, 'P002', 'Pomme de terre', '287.2', '2016-01-01', '2080-01-01');

-- --------------------------------------------------------

--
-- Structure de la table `rapport_transporteur_deux`
--

CREATE TABLE IF NOT EXISTS `rapport_transporteur_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_transporteur` varchar(50) NOT NULL,
  `Nom_transporteur` varchar(50) NOT NULL,
  `Adresse_transporteur` varchar(50) NOT NULL,
  `Code_Produit` varchar(50) NOT NULL,
  `Designation_Produit` varchar(50) NOT NULL,
  `Totale_Entrer` varchar(50) NOT NULL,
  `Totale_sortie` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `rapport_transporteur_deux`
--

INSERT INTO `rapport_transporteur_deux` (`Id`, `Code_transporteur`, `Nom_transporteur`, `Adresse_transporteur`, `Code_Produit`, `Designation_Produit`, `Totale_Entrer`, `Totale_sortie`, `Date_debut`, `Date_fin`) VALUES
(1, 'T001', 'Chafik', 'Sousse', 'P001', 'Olive', '0.0', '124.4', '2016-01-01', '2080-01-01');

-- --------------------------------------------------------

--
-- Structure de la table `recuperation_deux`
--

CREATE TABLE IF NOT EXISTS `recuperation_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Id_type` varchar(50) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Id_produit` varchar(50) NOT NULL,
  `Produit_etat` varchar(50) NOT NULL,
  `Numero` varchar(50) NOT NULL,
  `Code` varchar(50) NOT NULL,
  `Produit` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Pesage` varchar(50) NOT NULL,
  `Date_supression` varchar(50) NOT NULL,
  `Nom_machine` varchar(50) NOT NULL,
  `Date_recuperation` varchar(50) NOT NULL,
  `Nom_machine_recuperation` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

-- --------------------------------------------------------

--
-- Structure de la table `suppression_deux`
--

CREATE TABLE IF NOT EXISTS `suppression_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Id_type` varchar(50) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Id_produit` varchar(50) NOT NULL,
  `Produit_etat` varchar(50) NOT NULL,
  `Numero` varchar(50) NOT NULL,
  `Code` varchar(50) NOT NULL,
  `Produit` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Pesage` varchar(50) NOT NULL,
  `Date_supression` varchar(50) NOT NULL,
  `Nom_machine` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

-- --------------------------------------------------------

--
-- Structure de la table `transporteur`
--

CREATE TABLE IF NOT EXISTS `transporteur` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(1000) NOT NULL,
  `Code` varchar(100) NOT NULL,
  `Adresse` varchar(1000) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Structure de la table `transporteur_valide_deux`
--

CREATE TABLE IF NOT EXISTS `transporteur_valide_deux` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` varchar(100) NOT NULL,
  `mouvement` varchar(100) NOT NULL,
  `Code_transporteur` varchar(100) NOT NULL,
  `Nom_transporteur` varchar(50) NOT NULL,
  `Code_produit` varchar(50) NOT NULL,
  `Designation_produit` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Pesage` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Titre` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`Id`, `Login`, `Password`, `Titre`) VALUES
(1, 'admin', 'admin', 'Utilisateur'),
(2, 'root', 'root', 'Directeur'),
(3, '0', '0', 'Fenetre'),
(4, 'supadmin', 'supadmin', 'Superviseur');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
