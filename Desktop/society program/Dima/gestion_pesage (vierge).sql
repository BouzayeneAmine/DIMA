-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Ven 30 Juin 2017 à 13:14
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `client_valide`
--

CREATE TABLE IF NOT EXISTS `client_valide` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` varchar(100) NOT NULL,
  `Code` varchar(100) NOT NULL,
  `Produit` varchar(1000) NOT NULL,
  `Immatricule` varchar(50) NOT NULL,
  `Date_premier` varchar(50) NOT NULL,
  `Heure_premier` varchar(50) NOT NULL,
  `Premier_pesage` varchar(50) NOT NULL,
  `Date_deuxieme` varchar(50) NOT NULL,
  `Heure_deuxieme` varchar(50) NOT NULL,
  `Deuxieme_pesage` varchar(50) NOT NULL,
  `Masse_nette` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
-- Structure de la table `en_circulation`
--

CREATE TABLE IF NOT EXISTS `en_circulation` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` varchar(1000) NOT NULL,
  `Immatricule` varchar(50) NOT NULL,
  `Type` varchar(1000) NOT NULL,
  `Code_Type` varchar(100) NOT NULL,
  `Code_Produit` varchar(100) NOT NULL,
  `Date_entree` varchar(50) NOT NULL,
  `Heure_entree` varchar(50) NOT NULL,
  `Premier_pesage` varchar(50) NOT NULL,
  `Client_Nom` varchar(50) NOT NULL,
  `Client_Adresse` varchar(50) NOT NULL,
  `Produit_des` varchar(50) NOT NULL,
  `Produit_lot` varchar(50) NOT NULL,
  `Produi_prix` varchar(50) NOT NULL,
  `Produit_coef` varchar(50) NOT NULL,
  `Cam_type` varchar(50) NOT NULL,
  `cam_tare` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur_valide`
--

CREATE TABLE IF NOT EXISTS `fournisseur_valide` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` varchar(100) NOT NULL,
  `Code` varchar(100) NOT NULL,
  `Produit` varchar(1000) NOT NULL,
  `Immatricule` varchar(50) NOT NULL,
  `Date_premier` varchar(50) NOT NULL,
  `Heure_premier` varchar(50) NOT NULL,
  `Premier_pesage` varchar(50) NOT NULL,
  `Date_deuxieme` varchar(50) NOT NULL,
  `Heure_deuxieme` varchar(50) NOT NULL,
  `Deuxieme_pesage` varchar(50) NOT NULL,
  `Masse_nette` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `impression_bon`
--

CREATE TABLE IF NOT EXISTS `impression_bon` (
  `Id` int(11) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Code_type` varchar(100) NOT NULL,
  `Nom_type` varchar(1000) NOT NULL,
  `Adresse_type` varchar(1000) NOT NULL,
  `Code_produit` varchar(100) NOT NULL,
  `Designation_produit` varchar(100) NOT NULL,
  `Lot_produit` varchar(100) NOT NULL,
  `Prix_produit` varchar(50) NOT NULL,
  `Coefficient_produit` varchar(50) NOT NULL,
  `Camion_Immatricule` varchar(50) NOT NULL,
  `Camion_type` varchar(1000) NOT NULL,
  `Camion_tare` varchar(50) NOT NULL,
  `Premier_pesage` varchar(50) NOT NULL,
  `Premier_date` varchar(50) NOT NULL,
  `Premier_Heure` varchar(50) NOT NULL,
  `Deuxieme_pesage` varchar(50) NOT NULL,
  `Deuxieme_date` varchar(50) NOT NULL,
  `Deuxieme_heure` varchar(50) NOT NULL,
  `Masse_nette` varchar(50) NOT NULL,
  `Numero` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `impression_bon`
--

INSERT INTO `impression_bon` (`Id`, `Type`, `Code_type`, `Nom_type`, `Adresse_type`, `Code_produit`, `Designation_produit`, `Lot_produit`, `Prix_produit`, `Coefficient_produit`, `Camion_Immatricule`, `Camion_type`, `Camion_tare`, `Premier_pesage`, `Premier_date`, `Premier_Heure`, `Deuxieme_pesage`, `Deuxieme_date`, `Deuxieme_heure`, `Masse_nette`, `Numero`) VALUES
(0, 'Client', 'C0011', 'Anis', 'Msaken', 'G010', 'Sable', '20', '3', '15', '25TUN1818', 'MAN ', '5000', ' 0.380', '28/01/2017', '09:39', ' 0.610', '28/01/2017', '09:57', '0.230', '1');

-- --------------------------------------------------------

--
-- Structure de la table `mv_client`
--

CREATE TABLE IF NOT EXISTS `mv_client` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_client` varchar(50) NOT NULL,
  `Nom_client` varchar(50) NOT NULL,
  `Adresse_client` varchar(50) NOT NULL,
  `Produit` varchar(50) NOT NULL,
  `Designation_produit` varchar(50) NOT NULL,
  `Immatricule` varchar(50) NOT NULL,
  `Date_premier` varchar(50) NOT NULL,
  `Premier_pesage` varchar(50) NOT NULL,
  `Date_deuxieme` varchar(50) NOT NULL,
  `Deuxieme_pesage` varchar(50) NOT NULL,
  `Masse_nette` varchar(50) NOT NULL,
  `Totale_Entrer` varchar(50) NOT NULL,
  `Totale_Sortie` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  `Etat` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `mv_client`
--

INSERT INTO `mv_client` (`Id`, `Code_client`, `Nom_client`, `Adresse_client`, `Produit`, `Designation_produit`, `Immatricule`, `Date_premier`, `Premier_pesage`, `Date_deuxieme`, `Deuxieme_pesage`, `Masse_nette`, `Totale_Entrer`, `Totale_Sortie`, `Date_debut`, `Date_fin`, `Etat`) VALUES
(1, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');

-- --------------------------------------------------------

--
-- Structure de la table `mv_fournisseur`
--

CREATE TABLE IF NOT EXISTS `mv_fournisseur` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_fournisseur` varchar(50) NOT NULL,
  `Nom_fournisseur` varchar(50) NOT NULL,
  `Adresse_fournisseur` varchar(50) NOT NULL,
  `Produit` varchar(50) NOT NULL,
  `Designation_produit` varchar(50) NOT NULL,
  `Immatricule` varchar(50) NOT NULL,
  `Date_premier` varchar(50) NOT NULL,
  `Premier_pesage` varchar(50) NOT NULL,
  `Date_deuxieme` varchar(50) NOT NULL,
  `Deuxieme_pesage` varchar(50) NOT NULL,
  `Masse_nette` varchar(50) NOT NULL,
  `Totale_Entrer` varchar(50) NOT NULL,
  `Totale_Sortie` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  `Etat` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `mv_fournisseur`
--

INSERT INTO `mv_fournisseur` (`Id`, `Code_fournisseur`, `Nom_fournisseur`, `Adresse_fournisseur`, `Produit`, `Designation_produit`, `Immatricule`, `Date_premier`, `Premier_pesage`, `Date_deuxieme`, `Deuxieme_pesage`, `Masse_nette`, `Totale_Entrer`, `Totale_Sortie`, `Date_debut`, `Date_fin`, `Etat`) VALUES
(1, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');

-- --------------------------------------------------------

--
-- Structure de la table `mv_produit_entrant`
--

CREATE TABLE IF NOT EXISTS `mv_produit_entrant` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_type` varchar(50) NOT NULL,
  `Nom_type` varchar(50) NOT NULL,
  `Adresse_type` varchar(50) NOT NULL,
  `Produit` varchar(50) NOT NULL,
  `Designation_produit` varchar(50) NOT NULL,
  `Immatricule` varchar(50) NOT NULL,
  `Date_premier` varchar(50) NOT NULL,
  `Premier_pesage` varchar(50) NOT NULL,
  `Date_deuxieme` varchar(50) NOT NULL,
  `Deuxieme_pesage` varchar(50) NOT NULL,
  `Masse_nette` varchar(50) NOT NULL,
  `Totale` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `mv_produit_entrant`
--

INSERT INTO `mv_produit_entrant` (`Id`, `Code_type`, `Nom_type`, `Adresse_type`, `Produit`, `Designation_produit`, `Immatricule`, `Date_premier`, `Premier_pesage`, `Date_deuxieme`, `Deuxieme_pesage`, `Masse_nette`, `Totale`, `Date_debut`, `Date_fin`) VALUES
(1, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '00', '0');

-- --------------------------------------------------------

--
-- Structure de la table `mv_produit_sortant`
--

CREATE TABLE IF NOT EXISTS `mv_produit_sortant` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_type` varchar(50) NOT NULL,
  `Nom_type` varchar(50) NOT NULL,
  `Adresse_type` varchar(50) NOT NULL,
  `Produit` varchar(50) NOT NULL,
  `Designation_produit` varchar(50) NOT NULL,
  `Immatricule` varchar(50) NOT NULL,
  `Date_premier` varchar(50) NOT NULL,
  `Premier_pesage` varchar(50) NOT NULL,
  `Date_deuxieme` varchar(50) NOT NULL,
  `Deuxieme_pesage` varchar(50) NOT NULL,
  `Masse_nette` varchar(50) NOT NULL,
  `Totale` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `mv_produit_sortant`
--

INSERT INTO `mv_produit_sortant` (`Id`, `Code_type`, `Nom_type`, `Adresse_type`, `Produit`, `Designation_produit`, `Immatricule`, `Date_premier`, `Premier_pesage`, `Date_deuxieme`, `Deuxieme_pesage`, `Masse_nette`, `Totale`, `Date_debut`, `Date_fin`) VALUES
(1, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '00', '0', '0');

-- --------------------------------------------------------

--
-- Structure de la table `mv_transporteur`
--

CREATE TABLE IF NOT EXISTS `mv_transporteur` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_transporteur` varchar(50) NOT NULL,
  `Nom_transporteur` varchar(50) NOT NULL,
  `Adresse_transporteur` varchar(50) NOT NULL,
  `Produit` varchar(50) NOT NULL,
  `Designation_produit` varchar(50) NOT NULL,
  `Immatricule` varchar(50) NOT NULL,
  `Date_premier` varchar(50) NOT NULL,
  `Premier_pesage` varchar(50) NOT NULL,
  `Date_deuxieme` varchar(50) NOT NULL,
  `Deuxieme_pesage` varchar(50) NOT NULL,
  `Masse_nette` varchar(50) NOT NULL,
  `Totale_Entrer` varchar(50) NOT NULL,
  `Totale_Sortie` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  `Etat` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `mv_transporteur`
--

INSERT INTO `mv_transporteur` (`Id`, `Code_transporteur`, `Nom_transporteur`, `Adresse_transporteur`, `Produit`, `Designation_produit`, `Immatricule`, `Date_premier`, `Premier_pesage`, `Date_deuxieme`, `Deuxieme_pesage`, `Masse_nette`, `Totale_Entrer`, `Totale_Sortie`, `Date_debut`, `Date_fin`, `Etat`) VALUES
(1, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '00', '0', '0', '0', '0');

-- --------------------------------------------------------

--
-- Structure de la table `nbon`
--

CREATE TABLE IF NOT EXISTS `nbon` (
  `Id` int(11) NOT NULL,
  `Numero` varchar(1000) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `nbon`
--

INSERT INTO `nbon` (`Id`, `Numero`) VALUES
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `produit_entrant`
--

CREATE TABLE IF NOT EXISTS `produit_entrant` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` varchar(50) NOT NULL,
  `Code_produit` varchar(100) NOT NULL,
  `Code_type` varchar(100) NOT NULL,
  `Immatricule` varchar(50) NOT NULL,
  `Date_entree` varchar(50) NOT NULL,
  `Heure_entree` varchar(50) NOT NULL,
  `Premier_pesage` varchar(50) NOT NULL,
  `Date_sortie` varchar(50) NOT NULL,
  `Heure_sortie` varchar(50) NOT NULL,
  `Deuxieme_pesage` varchar(50) NOT NULL,
  `Masse_nette` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `produit_sortant`
--

CREATE TABLE IF NOT EXISTS `produit_sortant` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` varchar(50) NOT NULL,
  `Code_produit` varchar(100) NOT NULL,
  `Code_type` varchar(100) NOT NULL,
  `Immatricule` varchar(50) NOT NULL,
  `Date_entree` varchar(50) NOT NULL,
  `Heure_entree` varchar(50) NOT NULL,
  `Premier_pesage` varchar(50) NOT NULL,
  `Date_sortie` varchar(50) NOT NULL,
  `Heure_sortie` varchar(50) NOT NULL,
  `Deuxieme_pesage` varchar(50) NOT NULL,
  `Masse_nette` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `rapport_client`
--

CREATE TABLE IF NOT EXISTS `rapport_client` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_client` varchar(50) NOT NULL,
  `Nom_client` varchar(50) NOT NULL,
  `Adresse` varchar(50) NOT NULL,
  `Code_Produit` varchar(50) NOT NULL,
  `Designation_Produit` varchar(50) NOT NULL,
  `Totale_Entrer` varchar(50) NOT NULL,
  `Totale_sortie` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `rapport_client`
--

INSERT INTO `rapport_client` (`Id`, `Code_client`, `Nom_client`, `Adresse`, `Code_Produit`, `Designation_Produit`, `Totale_Entrer`, `Totale_sortie`, `Date_debut`, `Date_fin`) VALUES
(1, '0', '0', '0', '0', '0', '0', '0', '00', '0');

-- --------------------------------------------------------

--
-- Structure de la table `rapport_fournisseur`
--

CREATE TABLE IF NOT EXISTS `rapport_fournisseur` (
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `rapport_fournisseur`
--

INSERT INTO `rapport_fournisseur` (`Id`, `Code_fournisseur`, `Nom_fournisseur`, `Adresse_fournisseur`, `Code_Produit`, `Designation_Produit`, `Totale_Entrer`, `Totale_sortie`, `Date_debut`, `Date_fin`) VALUES
(1, '0', '0', '0', '0', '0', '0', '0', '0', '0');

-- --------------------------------------------------------

--
-- Structure de la table `rapport_produit_entrant`
--

CREATE TABLE IF NOT EXISTS `rapport_produit_entrant` (
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `rapport_produit_entrant`
--

INSERT INTO `rapport_produit_entrant` (`Id`, `Type`, `Code_Type`, `Nom_Type`, `Adresse_Type`, `Code_Produit`, `Designation_Produit`, `Totale`, `Date_debut`, `Date_fin`) VALUES
(1, '0', '0', '0', '0', '0', '0', '0', '0', '0');

-- --------------------------------------------------------

--
-- Structure de la table `rapport_produit_entrant_totale`
--

CREATE TABLE IF NOT EXISTS `rapport_produit_entrant_totale` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_Produit` varchar(50) NOT NULL,
  `Designation_Produit` varchar(50) NOT NULL,
  `Totale` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `rapport_produit_sortant`
--

CREATE TABLE IF NOT EXISTS `rapport_produit_sortant` (
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `rapport_produit_sortant`
--

INSERT INTO `rapport_produit_sortant` (`Id`, `Type`, `Code_Type`, `Nom_Type`, `Adresse_Type`, `Code_Produit`, `Designation_Produit`, `Totale`, `Date_debut`, `Date_fin`) VALUES
(1, '0', '0', '0', '0', '0', '0', '0', '0', '0');

-- --------------------------------------------------------

--
-- Structure de la table `rapport_produit_sortant_totale`
--

CREATE TABLE IF NOT EXISTS `rapport_produit_sortant_totale` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code_Produit` varchar(50) NOT NULL,
  `Designation_Produit` varchar(50) NOT NULL,
  `Totale` varchar(50) NOT NULL,
  `Date_debut` varchar(50) NOT NULL,
  `Date_fin` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `rapport_transporteur`
--

CREATE TABLE IF NOT EXISTS `rapport_transporteur` (
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
-- Contenu de la table `rapport_transporteur`
--

INSERT INTO `rapport_transporteur` (`Id`, `Code_transporteur`, `Nom_transporteur`, `Adresse_transporteur`, `Code_Produit`, `Designation_Produit`, `Totale_Entrer`, `Totale_sortie`, `Date_debut`, `Date_fin`) VALUES
(1, '0', '0', '0', '0', '0', '0', '0', '0', '0');

-- --------------------------------------------------------

--
-- Structure de la table `recuperation`
--

CREATE TABLE IF NOT EXISTS `recuperation` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Id_type` varchar(50) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Id_produit` varchar(50) NOT NULL,
  `Produit_etat` varchar(50) NOT NULL,
  `Numero` varchar(50) NOT NULL,
  `Code` varchar(50) NOT NULL,
  `Produit` varchar(50) NOT NULL,
  `Immatricule` varchar(50) NOT NULL,
  `Date_premier` varchar(50) NOT NULL,
  `Heure_premier` varchar(50) NOT NULL,
  `Premier_pesage` varchar(50) NOT NULL,
  `Date_deuxieme` varchar(50) NOT NULL,
  `Heure_deuxieme` varchar(50) NOT NULL,
  `Deuxieme_pesage` varchar(50) NOT NULL,
  `Masse_nette` varchar(50) NOT NULL,
  `Date_supression` varchar(50) NOT NULL,
  `Nom_machine` varchar(50) NOT NULL,
  `Date_recuperation` varchar(50) NOT NULL,
  `Nom_machine_recuperation` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `suppression`
--

CREATE TABLE IF NOT EXISTS `suppression` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Id_type` varchar(50) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Id_produit` varchar(50) NOT NULL,
  `Produit_etat` varchar(50) NOT NULL,
  `Numero` varchar(50) NOT NULL,
  `Code` varchar(50) NOT NULL,
  `Produit` varchar(50) NOT NULL,
  `Immatricule` varchar(50) NOT NULL,
  `Date_premier` varchar(50) NOT NULL,
  `Heure_premier` varchar(50) NOT NULL,
  `Premier_pesage` varchar(50) NOT NULL,
  `Date_deuxieme` varchar(50) NOT NULL,
  `Heure_deuxieme` varchar(50) NOT NULL,
  `Deuxieme_pesage` varchar(50) NOT NULL,
  `Masse_nette` varchar(50) NOT NULL,
  `Date_supression` varchar(50) NOT NULL,
  `Nom_machine` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `transporteur_valide`
--

CREATE TABLE IF NOT EXISTS `transporteur_valide` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` varchar(100) NOT NULL,
  `Code` varchar(100) NOT NULL,
  `Produit` varchar(100) NOT NULL,
  `Immatricule` varchar(50) NOT NULL,
  `Date_premier` varchar(50) NOT NULL,
  `Heure_premier` varchar(50) NOT NULL,
  `Premier_pesage` varchar(50) NOT NULL,
  `Date_deuxieme` varchar(50) NOT NULL,
  `Heure_deuxieme` varchar(50) NOT NULL,
  `Deuxieme_pesage` varchar(50) NOT NULL,
  `Masse_nette` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
