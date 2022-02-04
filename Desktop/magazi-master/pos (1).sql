-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 16, 2020 at 05:28 PM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pos`
--
CREATE DATABASE IF NOT EXISTS `pos` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `pos`;

-- --------------------------------------------------------

--
-- Table structure for table `action`
--

CREATE TABLE IF NOT EXISTS `action` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `user` varchar(500) NOT NULL,
  `type_action` varchar(500) NOT NULL,
  `description` varchar(500) NOT NULL,
  `Date` varchar(500) NOT NULL,
  `Heure` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=59 ;

--
-- Dumping data for table `action`
--

INSERT INTO `action` (`id`, `user`, `type_action`, `description`, `Date`, `Heure`) VALUES
(38, 'achref', 'modifier', '619400178', '15/7/2020', '14:20:15'),
(39, 'achref', 'modifier', '619400178', '15/7/2020', '14:20:16'),
(40, 'achref', 'modifier', '619400178', '15/7/2020', '14:20:17'),
(41, 'achref', 'modifier', '619400178', '15/7/2020', '14:20:17'),
(42, 'achref', 'modifier', '619400178', '15/7/2020', '14:20:20'),
(43, 'achref', 'modifier', '619400178', '15/7/2020', '14:20:20'),
(44, 'achref', 'modifier', '619400178', '15/7/2020', '14:20:20'),
(45, 'achref', 'modifier', '619400178', '15-07-2020', '14:48'),
(46, 'amine', 'modifier', '619400178', '15-07-2020', '14:48'),
(47, 'amine', 'supprimer', '023698', '15-07-2020', '14:49'),
(48, 'achref', 'ajouter', '1222', '15-07-2020', '14:49'),
(49, 'amine', 'réduire stock', '1222, Quantité soustraite 50', '15-07-2020', '14:51'),
(50, 'amine', 'réduire stock', '1222, Quantité soustraite 40', '15-07-2020', '14:51'),
(51, 'amine', 'réduire stock', '61940018, Quantité soustraite 5', '15-07-2020', '21:01'),
(52, 'amine', 'réduire stock', '4004675000415, Quantité soustraite 2', '15-07-2020', '21:01'),
(53, 'amine', 'réduire stock', '619400178, Quantité soustraite 1', '15-07-2020', '21:01'),
(54, 'amine', 'ajouter', '1', '15-07-2020', '22:39'),
(55, 'amine', 'réduire stock', '1, Quantité soustraite 20', '15-07-2020', '22:40'),
(56, 'amine', 'ajouter', '89574', '15-07-2020', '22:43'),
(57, 'amine', 'réduire stock', '89574, Quantité soustraite 5', '15-07-2020', '22:44'),
(58, 'achref', 'remplir stock', '84165787, Quantité ajoutée 10', '15-07-2020', '22:53');

-- --------------------------------------------------------

--
-- Table structure for table `caisse`
--

CREATE TABLE IF NOT EXISTS `caisse` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(50) NOT NULL,
  `Barcode` varchar(500) NOT NULL,
  `Categorie` varchar(500) NOT NULL,
  `Quantite` varchar(11) NOT NULL,
  `Prix_vente` varchar(11) NOT NULL,
  `total_base` varchar(50) NOT NULL,
  `total` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `connexion`
--

CREATE TABLE IF NOT EXISTS `connexion` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(50) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Nom_PC` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=637 ;

--
-- Dumping data for table `connexion`
--

INSERT INTO `connexion` (`Id`, `Login`, `Type`, `Date`, `Heure`, `Nom_PC`) VALUES
(168, 'bejaoui', 'Directeur', '2019-07-15', '12:57:00', 'DESKTOP-0ROSBKV'),
(169, 'bejaoui', 'Utilisateur', '2019-07-15', '12:58:55', 'DESKTOP-0ROSBKV'),
(170, 'admin', 'Utilisateur', '2019-07-15', '13:03:34', 'DESKTOP-0ROSBKV'),
(171, 'JELJELI', 'Directeur', '2019-07-21', '18:14:55', 'DESKTOP-0ROSBKV'),
(172, 'JELJELI', 'Directeur', '2019-07-21', '18:51:39', 'DESKTOP-0ROSBKV'),
(173, 'boughaba', 'Utilisateur', '2019-07-21', '19:14:29', 'DESKTOP-0ROSBKV'),
(174, 'admin', 'Utilisateur', '2019-07-23', '11:24:43', 'DESKTOP-0ROSBKV'),
(175, 'admin', 'Utilisateur', '2019-07-23', '11:31:22', 'DESKTOP-0ROSBKV'),
(176, 'admin', 'Utilisateur', '2019-07-23', '11:33:10', 'DESKTOP-0ROSBKV'),
(177, 'admin', 'Utilisateur', '2019-07-23', '12:58:11', 'DESKTOP-0ROSBKV'),
(178, 'admin', 'Utilisateur', '2019-07-23', '13:04:52', 'DESKTOP-0ROSBKV'),
(179, 'admin', 'Utilisateur', '2019-07-23', '13:08:24', 'DESKTOP-0ROSBKV'),
(180, 'admin', 'Utilisateur', '2019-07-23', '13:21:24', 'DESKTOP-0ROSBKV'),
(181, 'admin', 'Utilisateur', '2019-07-23', '13:49:30', 'DESKTOP-0ROSBKV'),
(182, 'admin', 'Utilisateur', '2019-07-23', '13:49:53', 'DESKTOP-0ROSBKV'),
(183, 'admin', 'Utilisateur', '2019-07-23', '13:55:01', 'DESKTOP-0ROSBKV'),
(184, 'admin', 'Utilisateur', '2019-07-23', '13:56:50', 'DESKTOP-0ROSBKV'),
(185, 'admin', 'Utilisateur', '2019-07-23', '14:00:31', 'DESKTOP-0ROSBKV'),
(186, 'admin', 'Utilisateur', '2019-07-23', '14:29:44', 'DESKTOP-0ROSBKV'),
(187, 'admin', 'Utilisateur', '2019-07-23', '15:35:20', 'DESKTOP-0ROSBKV'),
(188, 'admin', 'Utilisateur', '2019-07-23', '15:46:20', 'DESKTOP-0ROSBKV'),
(189, 'admin', 'Utilisateur', '2019-07-23', '15:47:17', 'DESKTOP-0ROSBKV'),
(190, 'admin', 'Utilisateur', '2019-07-23', '15:48:05', 'DESKTOP-0ROSBKV'),
(191, 'boughaba', 'Utilisateur', '2019-07-26', '07:44:02', 'DESKTOP-0ROSBKV'),
(192, 'admin', 'Utilisateur', '2019-08-01', '16:57:22', 'DESKTOP-0ROSBKV'),
(193, 'admin', 'Utilisateur', '2019-08-01', '17:03:48', 'DESKTOP-0ROSBKV'),
(194, 'admin', 'Utilisateur', '2019-08-01', '17:07:31', 'DESKTOP-0ROSBKV'),
(195, 'admin', 'Utilisateur', '2019-08-01', '17:09:33', 'DESKTOP-0ROSBKV'),
(196, 'admin', 'Utilisateur', '2019-08-01', '17:11:11', 'DESKTOP-0ROSBKV'),
(197, 'admin', 'Utilisateur', '2019-08-01', '17:20:48', 'DESKTOP-0ROSBKV'),
(198, 'admin', 'Utilisateur', '2019-08-01', '17:21:49', 'DESKTOP-0ROSBKV'),
(199, 'admin', 'Utilisateur', '2019-08-01', '17:32:08', 'DESKTOP-0ROSBKV'),
(200, 'admin', 'Utilisateur', '2019-08-01', '17:40:34', 'DESKTOP-0ROSBKV'),
(201, 'admin', 'Utilisateur', '2019-08-01', '17:41:35', 'DESKTOP-0ROSBKV'),
(202, 'admin', 'Utilisateur', '2019-08-01', '17:46:39', 'DESKTOP-0ROSBKV'),
(203, 'admin', 'Utilisateur', '2019-08-01', '17:50:13', 'DESKTOP-0ROSBKV'),
(204, 'admin', 'Utilisateur', '2019-08-01', '17:54:14', 'DESKTOP-0ROSBKV'),
(205, 'admin', 'Utilisateur', '2019-08-01', '18:08:24', 'DESKTOP-0ROSBKV'),
(206, 'admin', 'Utilisateur', '2019-08-01', '18:09:35', 'DESKTOP-0ROSBKV'),
(207, 'admin', 'Utilisateur', '2019-08-01', '18:16:15', 'DESKTOP-0ROSBKV'),
(208, 'admin', 'Utilisateur', '2019-08-01', '18:20:38', 'DESKTOP-0ROSBKV'),
(209, 'admin', 'Utilisateur', '2019-08-01', '18:21:29', 'DESKTOP-0ROSBKV'),
(210, 'admin', 'Utilisateur', '2019-08-01', '18:21:54', 'DESKTOP-0ROSBKV'),
(211, 'admin', 'Utilisateur', '2019-08-01', '18:23:49', 'DESKTOP-0ROSBKV'),
(212, 'admin', 'Utilisateur', '2019-08-01', '18:25:13', 'DESKTOP-0ROSBKV'),
(213, 'admin', 'Utilisateur', '2019-08-01', '18:25:53', 'DESKTOP-0ROSBKV'),
(214, 'admin', 'Utilisateur', '2019-08-01', '18:26:07', 'DESKTOP-0ROSBKV'),
(215, 'admin', 'Utilisateur', '2019-08-01', '18:26:49', 'DESKTOP-0ROSBKV'),
(216, 'admin', 'Utilisateur', '2019-08-01', '18:27:37', 'DESKTOP-0ROSBKV'),
(217, 'admin', 'Utilisateur', '2019-08-01', '18:28:17', 'DESKTOP-0ROSBKV'),
(218, 'admin', 'Utilisateur', '2019-08-01', '18:29:27', 'DESKTOP-0ROSBKV'),
(219, 'admin', 'Utilisateur', '2019-08-01', '18:29:54', 'DESKTOP-0ROSBKV'),
(220, 'admin', 'Utilisateur', '2019-08-01', '18:33:11', 'DESKTOP-0ROSBKV'),
(221, 'admin', 'Utilisateur', '2019-08-01', '18:46:38', 'DESKTOP-0ROSBKV'),
(222, 'admin', 'Utilisateur', '2019-08-01', '18:47:42', 'DESKTOP-0ROSBKV'),
(223, 'admin', 'Utilisateur', '2019-08-01', '18:48:51', 'DESKTOP-0ROSBKV'),
(224, 'admin', 'Utilisateur', '2019-08-01', '18:50:45', 'DESKTOP-0ROSBKV'),
(225, 'admin', 'Utilisateur', '2019-08-01', '19:51:55', 'DESKTOP-0ROSBKV'),
(226, 'admin', 'Utilisateur', '2019-08-01', '19:55:21', 'DESKTOP-0ROSBKV'),
(227, 'admin', 'Utilisateur', '2019-08-01', '19:56:18', 'DESKTOP-0ROSBKV'),
(228, 'admin', 'Utilisateur', '2019-08-01', '19:59:39', 'DESKTOP-0ROSBKV'),
(229, 'admin', 'Utilisateur', '2019-08-01', '20:00:22', 'DESKTOP-0ROSBKV'),
(230, 'admin', 'Utilisateur', '2019-08-01', '20:01:31', 'DESKTOP-0ROSBKV'),
(231, 'admin', 'Utilisateur', '2019-08-01', '20:09:15', 'DESKTOP-0ROSBKV'),
(232, 'admin', 'Utilisateur', '2019-08-01', '20:11:02', 'DESKTOP-0ROSBKV'),
(233, 'admin', 'Utilisateur', '2019-08-01', '20:12:10', 'DESKTOP-0ROSBKV'),
(234, 'admin', 'Utilisateur', '2019-08-01', '20:13:54', 'DESKTOP-0ROSBKV'),
(235, 'admin', 'Utilisateur', '2019-08-01', '20:14:07', 'DESKTOP-0ROSBKV'),
(236, 'admin', 'Utilisateur', '2019-08-01', '20:14:29', 'DESKTOP-0ROSBKV'),
(237, 'admin', 'Utilisateur', '2019-08-01', '20:16:06', 'DESKTOP-0ROSBKV'),
(238, 'admin', 'Utilisateur', '2019-08-01', '20:17:22', 'DESKTOP-0ROSBKV'),
(239, 'admin', 'Utilisateur', '2019-08-01', '20:17:35', 'DESKTOP-0ROSBKV'),
(240, 'admin', 'Utilisateur', '2019-08-01', '20:19:36', 'DESKTOP-0ROSBKV'),
(241, 'admin', 'Utilisateur', '2019-08-01', '20:20:18', 'DESKTOP-0ROSBKV'),
(242, 'admin', 'Utilisateur', '2019-08-01', '20:20:52', 'DESKTOP-0ROSBKV'),
(243, 'admin', 'Utilisateur', '2019-08-01', '20:23:08', 'DESKTOP-0ROSBKV'),
(244, 'admin', 'Utilisateur', '2019-08-01', '20:29:42', 'DESKTOP-0ROSBKV'),
(245, 'admin', 'Utilisateur', '2019-08-01', '20:31:23', 'DESKTOP-0ROSBKV'),
(246, 'admin', 'Utilisateur', '2019-08-01', '20:32:29', 'DESKTOP-0ROSBKV'),
(247, 'admin', 'Utilisateur', '2019-08-01', '20:33:04', 'DESKTOP-0ROSBKV'),
(248, 'admin', 'Utilisateur', '2019-08-01', '20:34:50', 'DESKTOP-0ROSBKV'),
(249, 'admin', 'Utilisateur', '2019-08-01', '20:35:53', 'DESKTOP-0ROSBKV'),
(250, 'admin', 'Utilisateur', '2019-08-01', '20:37:05', 'DESKTOP-0ROSBKV'),
(251, 'admin', 'Utilisateur', '2019-08-01', '20:37:45', 'DESKTOP-0ROSBKV'),
(252, 'admin', 'Utilisateur', '2019-08-01', '20:39:05', 'DESKTOP-0ROSBKV'),
(253, 'admin', 'Utilisateur', '2019-08-01', '20:42:08', 'DESKTOP-0ROSBKV'),
(254, 'admin', 'Utilisateur', '2019-08-01', '20:45:07', 'DESKTOP-0ROSBKV'),
(255, 'admin', 'Utilisateur', '2019-08-01', '20:46:34', 'DESKTOP-0ROSBKV'),
(256, 'admin', 'Utilisateur', '2019-08-01', '20:48:05', 'DESKTOP-0ROSBKV'),
(257, 'admin', 'Utilisateur', '2019-08-01', '20:48:44', 'DESKTOP-0ROSBKV'),
(258, 'admin', 'Utilisateur', '2019-08-01', '20:50:24', 'DESKTOP-0ROSBKV'),
(259, 'admin', 'Utilisateur', '2019-08-01', '20:51:52', 'DESKTOP-0ROSBKV'),
(260, 'admin', 'Utilisateur', '2019-08-01', '20:55:09', 'DESKTOP-0ROSBKV'),
(261, 'admin', 'Utilisateur', '2019-08-01', '20:55:50', 'DESKTOP-0ROSBKV'),
(262, 'admin', 'Utilisateur', '2019-08-01', '20:56:18', 'DESKTOP-0ROSBKV'),
(263, 'admin', 'Utilisateur', '2019-08-01', '20:57:09', 'DESKTOP-0ROSBKV'),
(264, 'admin', 'Utilisateur', '2019-08-01', '20:59:40', 'DESKTOP-0ROSBKV'),
(265, 'admin', 'Utilisateur', '2019-08-01', '21:01:31', 'DESKTOP-0ROSBKV'),
(266, 'admin', 'Utilisateur', '2019-08-01', '21:03:05', 'DESKTOP-0ROSBKV'),
(267, 'admin', 'Utilisateur', '2019-08-01', '21:04:45', 'DESKTOP-0ROSBKV'),
(268, 'admin', 'Utilisateur', '2019-08-01', '21:05:55', 'DESKTOP-0ROSBKV'),
(269, 'admin', 'Utilisateur', '2019-08-01', '21:08:00', 'DESKTOP-0ROSBKV'),
(270, 'admin', 'Utilisateur', '2019-08-01', '21:14:50', 'DESKTOP-0ROSBKV'),
(271, 'admin', 'Utilisateur', '2019-08-01', '21:15:26', 'DESKTOP-0ROSBKV'),
(272, 'admin', 'Utilisateur', '2019-08-08', '09:45:51', 'DESKTOP-0ROSBKV'),
(273, 'admin', 'Utilisateur', '2019-08-08', '10:33:26', 'DESKTOP-0ROSBKV'),
(274, 'admin', 'Utilisateur', '2019-08-08', '10:34:29', 'DESKTOP-0ROSBKV'),
(275, 'admin', 'Utilisateur', '2019-08-08', '10:38:13', 'DESKTOP-0ROSBKV'),
(276, 'admin', 'Utilisateur', '2019-08-08', '10:39:20', 'DESKTOP-0ROSBKV'),
(277, 'admin', 'Utilisateur', '2019-08-08', '10:42:15', 'DESKTOP-0ROSBKV'),
(278, 'admin', 'Utilisateur', '2019-08-08', '10:42:44', 'DESKTOP-0ROSBKV'),
(279, 'admin', 'Utilisateur', '2019-08-08', '10:44:12', 'DESKTOP-0ROSBKV'),
(280, 'admin', 'Utilisateur', '2019-08-08', '10:44:48', 'DESKTOP-0ROSBKV'),
(281, 'admin', 'Utilisateur', '2019-08-08', '10:45:12', 'DESKTOP-0ROSBKV'),
(282, 'admin', 'Utilisateur', '2019-08-08', '10:53:12', 'DESKTOP-0ROSBKV'),
(283, 'admin', 'Utilisateur', '2019-08-08', '10:55:17', 'DESKTOP-0ROSBKV'),
(284, 'admin', 'Utilisateur', '2019-08-08', '10:57:51', 'DESKTOP-0ROSBKV'),
(285, 'admin', 'Utilisateur', '2019-08-08', '11:11:59', 'DESKTOP-0ROSBKV'),
(286, 'admin', 'Utilisateur', '2019-08-08', '11:19:49', 'DESKTOP-0ROSBKV'),
(287, 'admin', 'Utilisateur', '2019-08-08', '11:28:50', 'DESKTOP-0ROSBKV'),
(288, 'admin', 'Utilisateur', '2019-08-08', '11:31:48', 'DESKTOP-0ROSBKV'),
(289, 'admin', 'Utilisateur', '2019-08-08', '11:35:21', 'DESKTOP-0ROSBKV'),
(290, 'admin', 'Utilisateur', '2019-08-08', '11:37:39', 'DESKTOP-0ROSBKV'),
(291, 'admin', 'Utilisateur', '2019-08-08', '11:45:46', 'DESKTOP-0ROSBKV'),
(292, 'admin', 'Utilisateur', '2019-08-08', '11:50:57', 'DESKTOP-0ROSBKV'),
(293, 'admin', 'Utilisateur', '2019-08-08', '11:53:51', 'DESKTOP-0ROSBKV'),
(294, 'admin', 'Utilisateur', '2019-08-08', '11:58:24', 'DESKTOP-0ROSBKV'),
(295, 'admin', 'Utilisateur', '2019-08-08', '12:03:23', 'DESKTOP-0ROSBKV'),
(296, 'admin', 'Utilisateur', '2019-08-08', '12:21:54', 'DESKTOP-0ROSBKV'),
(297, 'admin', 'Utilisateur', '2019-08-08', '12:24:31', 'DESKTOP-0ROSBKV'),
(298, 'admin', 'Utilisateur', '2019-08-08', '12:45:46', 'DESKTOP-0ROSBKV'),
(299, 'admin', 'Utilisateur', '2019-08-08', '12:59:00', 'DESKTOP-0ROSBKV'),
(300, 'admin', 'Utilisateur', '2019-08-08', '12:59:50', 'DESKTOP-0ROSBKV'),
(301, 'admin', 'Utilisateur', '2019-08-08', '16:53:55', 'DESKTOP-0ROSBKV'),
(302, 'admin', 'Utilisateur', '2019-08-08', '16:55:35', 'DESKTOP-0ROSBKV'),
(303, 'admin', 'Utilisateur', '2019-08-08', '16:56:25', 'DESKTOP-0ROSBKV'),
(304, 'admin', 'Utilisateur', '2019-08-08', '17:22:51', 'DESKTOP-0ROSBKV'),
(305, 'admin', 'Utilisateur', '2019-08-08', '17:24:17', 'DESKTOP-0ROSBKV'),
(306, 'admin', 'Utilisateur', '2019-08-08', '17:26:41', 'DESKTOP-0ROSBKV'),
(307, 'admin', 'Utilisateur', '2019-08-08', '17:27:52', 'DESKTOP-0ROSBKV'),
(308, 'admin', 'Utilisateur', '2019-08-08', '18:13:29', 'DESKTOP-0ROSBKV'),
(309, 'admin', 'Utilisateur', '2019-08-08', '18:32:11', 'DESKTOP-0ROSBKV'),
(310, 'admin', 'Utilisateur', '2019-08-08', '18:33:07', 'DESKTOP-0ROSBKV'),
(311, 'admin', 'Utilisateur', '2019-08-08', '18:38:52', 'DESKTOP-0ROSBKV'),
(312, 'admin', 'Utilisateur', '2019-08-08', '18:52:06', 'DESKTOP-0ROSBKV'),
(313, 'admin', 'Utilisateur', '2019-08-08', '18:53:07', 'DESKTOP-0ROSBKV'),
(314, 'admin', 'Utilisateur', '2019-08-08', '18:55:17', 'DESKTOP-0ROSBKV'),
(315, 'admin', 'Utilisateur', '2019-08-08', '19:01:49', 'DESKTOP-0ROSBKV'),
(316, 'admin', 'Utilisateur', '2019-08-08', '19:10:28', 'DESKTOP-0ROSBKV'),
(317, 'admin', 'Utilisateur', '2019-08-08', '19:32:10', 'DESKTOP-0ROSBKV'),
(318, 'admin', 'Utilisateur', '2019-08-08', '19:33:16', 'DESKTOP-0ROSBKV'),
(319, 'admin', 'Utilisateur', '2019-08-08', '19:42:33', 'DESKTOP-0ROSBKV'),
(320, 'admin', 'Utilisateur', '2019-08-09', '09:18:25', 'DESKTOP-0ROSBKV'),
(321, 'admin', 'Utilisateur', '2019-08-09', '09:23:12', 'DESKTOP-0ROSBKV'),
(322, 'admin', 'Utilisateur', '2019-08-09', '09:24:18', 'DESKTOP-0ROSBKV'),
(323, 'admin', 'Utilisateur', '2019-08-09', '10:22:52', 'DESKTOP-0ROSBKV'),
(324, 'admin', 'Utilisateur', '2019-08-09', '10:34:04', 'DESKTOP-0ROSBKV'),
(325, 'root', 'Directeur', '2019-08-09', '10:42:41', 'DESKTOP-0ROSBKV'),
(326, 'root', 'Directeur', '2019-08-09', '10:51:51', 'DESKTOP-0ROSBKV'),
(327, 'supadmin', 'Superviseur', '2019-08-09', '10:52:57', 'DESKTOP-0ROSBKV'),
(328, 'admin', 'Utilisateur', '2019-08-09', '10:56:34', 'DESKTOP-0ROSBKV'),
(329, 'admin', 'Utilisateur', '2019-08-09', '11:04:09', 'DESKTOP-0ROSBKV'),
(330, 'admin', 'Utilisateur', '2019-08-09', '11:32:36', 'DESKTOP-0ROSBKV'),
(331, 'admin', 'Utilisateur', '2019-08-09', '11:36:48', 'DESKTOP-0ROSBKV'),
(332, 'admin', 'Utilisateur', '2019-08-09', '13:27:59', 'DESKTOP-0ROSBKV'),
(333, 'admin', 'Utilisateur', '2019-08-09', '13:33:28', 'DESKTOP-0ROSBKV'),
(334, 'admin', 'Utilisateur', '2019-08-09', '13:34:37', 'DESKTOP-0ROSBKV'),
(335, 'admin', 'Utilisateur', '2019-08-09', '13:47:25', 'DESKTOP-0ROSBKV'),
(336, 'admin', 'Utilisateur', '2019-08-09', '13:48:03', 'DESKTOP-0ROSBKV'),
(337, 'admin', 'Utilisateur', '2019-08-09', '13:51:09', 'DESKTOP-0ROSBKV'),
(338, 'admin', 'Utilisateur', '2019-08-09', '14:03:34', 'DESKTOP-0ROSBKV'),
(339, 'admin', 'Utilisateur', '2019-08-09', '14:15:19', 'DESKTOP-0ROSBKV'),
(340, 'admin', 'Utilisateur', '2019-08-09', '14:20:12', 'DESKTOP-0ROSBKV'),
(341, 'admin', 'Utilisateur', '2019-08-09', '14:24:03', 'DESKTOP-0ROSBKV'),
(342, 'admin', 'Utilisateur', '2019-08-09', '14:44:28', 'DESKTOP-0ROSBKV'),
(343, 'admin', 'Utilisateur', '2019-08-09', '14:46:48', 'DESKTOP-0ROSBKV'),
(344, 'admin', 'Utilisateur', '2019-08-09', '15:01:32', 'DESKTOP-0ROSBKV'),
(345, 'admin', 'Utilisateur', '2019-08-09', '16:35:28', 'DESKTOP-0ROSBKV'),
(346, 'admin', 'Utilisateur', '2019-08-09', '16:48:35', 'DESKTOP-0ROSBKV'),
(347, 'admin', 'Utilisateur', '2019-08-09', '17:27:43', 'DESKTOP-0ROSBKV'),
(348, 'admin', 'Utilisateur', '2019-08-09', '17:43:36', 'DESKTOP-0ROSBKV'),
(349, 'admin', 'Utilisateur', '2019-08-09', '18:58:01', 'DESKTOP-0ROSBKV'),
(350, 'admin', 'Utilisateur', '2019-08-09', '19:26:54', 'DESKTOP-0ROSBKV'),
(351, 'admin', 'Utilisateur', '2019-08-09', '19:33:05', 'DESKTOP-0ROSBKV'),
(352, 'admin', 'Utilisateur', '2019-08-09', '19:41:33', 'DESKTOP-0ROSBKV'),
(353, 'admin', 'Utilisateur', '2019-08-09', '19:50:48', 'DESKTOP-0ROSBKV'),
(354, 'admin', 'Utilisateur', '2019-08-09', '19:59:01', 'DESKTOP-0ROSBKV'),
(355, 'admin', 'Utilisateur', '2019-08-16', '08:33:48', 'DESKTOP-0ROSBKV'),
(356, 'admin', 'Utilisateur', '2019-08-16', '10:17:03', 'DESKTOP-0ROSBKV'),
(357, 'admin', 'Utilisateur', '2019-08-16', '10:18:20', 'DESKTOP-0ROSBKV'),
(358, 'admin', 'Utilisateur', '2019-08-16', '11:31:39', 'DESKTOP-0ROSBKV'),
(359, 'admin', 'Utilisateur', '2019-08-16', '11:48:52', 'DESKTOP-0ROSBKV'),
(360, 'boughaba', 'Utilisateur', '2019-08-16', '12:24:01', 'DESKTOP-0ROSBKV'),
(361, 'zakraoui', 'Utilisateur', '2019-08-19', '10:51:03', 'DESKTOP-0ROSBKV'),
(362, 'admin', 'Utilisateur', '2019-08-19', '10:55:22', 'DESKTOP-0ROSBKV'),
(363, 'admin', 'Utilisateur', '2019-08-19', '11:42:48', 'DESKTOP-0ROSBKV'),
(364, 'JELJELI', 'Directeur', '2019-08-19', '11:45:49', 'DESKTOP-0ROSBKV'),
(365, 'su', 'Utilisateur', '2019-08-19', '11:47:28', 'DESKTOP-0ROSBKV'),
(366, 'admin', 'Utilisateur', '2019-08-20', '08:35:27', 'DESKTOP-0ROSBKV'),
(367, 'admin', 'Utilisateur', '2019-08-20', '08:37:10', 'DESKTOP-0ROSBKV'),
(368, 'su', 'Utilisateur', '2019-08-20', '08:40:10', 'DESKTOP-0ROSBKV'),
(369, 'root', 'Directeur', '2019-08-20', '08:42:09', 'DESKTOP-0ROSBKV'),
(370, 'supadmin', 'Superviseur', '2019-08-20', '08:42:40', 'DESKTOP-0ROSBKV'),
(371, 'su', 'Utilisateur', '2019-08-20', '08:47:31', 'DESKTOP-0ROSBKV'),
(372, 'su', 'Utilisateur', '2019-08-20', '09:17:29', 'DESKTOP-0ROSBKV'),
(373, 'su', 'Utilisateur', '2019-08-20', '09:53:59', 'DESKTOP-0ROSBKV'),
(374, 'su', 'Utilisateur', '2019-08-20', '09:58:51', 'DESKTOP-0ROSBKV'),
(375, 'su', 'Utilisateur', '2019-08-20', '10:05:06', 'DESKTOP-0ROSBKV'),
(376, 'su', 'Utilisateur', '2019-08-20', '10:07:08', 'DESKTOP-0ROSBKV'),
(377, 'su', 'Utilisateur', '2019-08-20', '10:16:01', 'DESKTOP-0ROSBKV'),
(378, 'su', 'Utilisateur', '2019-08-20', '10:19:02', 'DESKTOP-0ROSBKV'),
(379, 'admin', 'Utilisateur', '2019-08-20', '10:27:23', 'DESKTOP-0ROSBKV'),
(380, 'su', 'Utilisateur', '2019-08-20', '10:38:54', 'DESKTOP-0ROSBKV'),
(381, 'su', 'Utilisateur', '2019-08-20', '11:03:40', 'DESKTOP-0ROSBKV'),
(382, 'su', 'Utilisateur', '2019-08-20', '11:07:17', 'DESKTOP-0ROSBKV'),
(383, 'su', 'Utilisateur', '2019-08-20', '11:11:53', 'DESKTOP-0ROSBKV'),
(384, 'su', 'Utilisateur', '2019-08-20', '11:13:52', 'DESKTOP-0ROSBKV'),
(385, 'su', 'Utilisateur', '2019-08-20', '11:15:10', 'DESKTOP-0ROSBKV'),
(386, 'su', 'Utilisateur', '2019-08-20', '11:16:16', 'DESKTOP-0ROSBKV'),
(387, 'su', 'Utilisateur', '2019-08-20', '11:17:59', 'DESKTOP-0ROSBKV'),
(388, 'su', 'Utilisateur', '2019-08-20', '11:20:33', 'DESKTOP-0ROSBKV'),
(389, 'su', 'Utilisateur', '2019-08-20', '11:23:34', 'DESKTOP-0ROSBKV'),
(390, 'su', 'Utilisateur', '2019-08-20', '11:25:52', 'DESKTOP-0ROSBKV'),
(391, 'su', 'Utilisateur', '2019-08-20', '11:57:07', 'DESKTOP-0ROSBKV'),
(392, 'su', 'Utilisateur', '2019-08-21', '06:06:33', 'DESKTOP-0ROSBKV'),
(393, 'su', 'Utilisateur', '2019-08-21', '06:12:05', 'DESKTOP-0ROSBKV'),
(394, 'su', 'Utilisateur', '2019-08-21', '06:17:17', 'DESKTOP-0ROSBKV'),
(395, 'su', 'Utilisateur', '2019-08-21', '06:50:24', 'DESKTOP-0ROSBKV'),
(396, 'su', 'Utilisateur', '2019-08-21', '08:53:49', 'DESKTOP-0ROSBKV'),
(397, 'su', 'Utilisateur', '2019-08-21', '11:36:54', 'DESKTOP-0ROSBKV'),
(398, 'su', 'Utilisateur', '2019-08-21', '11:37:14', 'DESKTOP-0ROSBKV'),
(399, 'su', 'Utilisateur', '2019-08-22', '08:18:57', 'DESKTOP-0ROSBKV'),
(400, 'su', 'Utilisateur', '2019-08-22', '08:47:19', 'DESKTOP-0ROSBKV'),
(401, 'su', 'Utilisateur', '2019-08-23', '06:16:24', 'DESKTOP-0ROSBKV'),
(402, 'su', 'Utilisateur', '2019-08-23', '06:21:52', 'DESKTOP-0ROSBKV'),
(403, 'su', 'Utilisateur', '2019-08-23', '06:45:42', 'DESKTOP-0ROSBKV'),
(404, 'su', 'Utilisateur', '2019-08-23', '07:42:18', 'DESKTOP-0ROSBKV'),
(405, 'su', 'Utilisateur', '2019-08-24', '06:25:53', 'DESKTOP-0ROSBKV'),
(406, 'su', 'Utilisateur', '2019-08-26', '06:22:57', 'DESKTOP-0ROSBKV'),
(407, 'su', 'Utilisateur', '2019-08-26', '08:21:02', 'DESKTOP-0ROSBKV'),
(408, 'su', 'Utilisateur', '2019-08-26', '13:44:59', 'DESKTOP-0ROSBKV'),
(409, 'su', 'Utilisateur', '2019-08-27', '10:29:01', 'DESKTOP-0ROSBKV'),
(410, 'admin', 'Utilisateur', '2019-09-02', '10:36:31', 'DESKTOP-0ROSBKV'),
(411, 'admin', 'Utilisateur', '2019-09-02', '11:12:44', 'DESKTOP-0ROSBKV'),
(412, 'admin', 'Utilisateur', '2019-09-02', '11:18:17', 'DESKTOP-0ROSBKV'),
(413, 'admin', 'Utilisateur', '2019-09-02', '11:32:29', 'DESKTOP-0ROSBKV'),
(414, 'admin', 'Utilisateur', '2019-09-02', '11:43:50', 'DESKTOP-0ROSBKV'),
(415, 'root', 'Directeur', '2019-09-02', '11:44:31', 'DESKTOP-0ROSBKV'),
(416, 'admin', 'Utilisateur', '2019-09-02', '11:48:40', 'DESKTOP-0ROSBKV'),
(417, 'admin', 'Utilisateur', '2019-09-02', '12:06:52', 'DESKTOP-0ROSBKV'),
(418, 'admin', 'Utilisateur', '2019-09-09', '10:23:24', 'DESKTOP-0ROSBKV'),
(419, 'admin', 'Utilisateur', '2019-09-09', '10:34:19', 'DESKTOP-0ROSBKV'),
(420, 'su', 'Utilisateur', '2019-09-11', '08:01:09', 'DESKTOP-0ROSBKV'),
(421, 'su', 'Utilisateur', '2019-09-11', '12:44:33', 'DESKTOP-0ROSBKV'),
(422, 'su', 'Utilisateur', '2019-09-11', '12:45:19', 'DESKTOP-0ROSBKV'),
(423, 'su', 'Utilisateur', '2019-09-12', '06:04:10', 'DESKTOP-0ROSBKV'),
(424, 'su', 'Utilisateur', '2019-09-12', '11:49:45', 'DESKTOP-0ROSBKV'),
(425, 'su', 'Utilisateur', '2019-09-12', '12:03:56', 'DESKTOP-0ROSBKV'),
(426, 'su', 'Utilisateur', '2019-09-12', '13:25:07', 'DESKTOP-0ROSBKV'),
(427, 'su', 'Utilisateur', '2019-09-13', '10:22:18', 'DESKTOP-0ROSBKV'),
(428, 'su', 'Utilisateur', '2019-09-15', '14:23:25', 'DESKTOP-0ROSBKV'),
(429, 'su', 'Utilisateur', '2019-09-17', '09:55:30', 'DESKTOP-0ROSBKV'),
(430, 'admin', 'Utilisateur', '2019-09-17', '14:10:30', 'DESKTOP-0ROSBKV'),
(431, 'root', 'Directeur', '2019-09-17', '14:11:02', 'DESKTOP-0ROSBKV'),
(432, 'admin', 'Utilisateur', '2019-09-17', '14:15:23', 'DESKTOP-0ROSBKV'),
(433, 'admin', 'Utilisateur', '2019-09-17', '14:20:44', 'DESKTOP-0ROSBKV'),
(434, 'admin', 'Utilisateur', '2019-09-17', '14:22:59', 'DESKTOP-0ROSBKV'),
(435, 'admin', 'Utilisateur', '2019-09-17', '14:27:22', 'DESKTOP-0ROSBKV'),
(436, 'admin', 'Utilisateur', '2019-09-17', '14:46:24', 'DESKTOP-0ROSBKV'),
(437, 'admin', 'Utilisateur', '2019-09-17', '15:11:51', 'DESKTOP-0ROSBKV'),
(438, 'admin', 'Utilisateur', '2019-09-18', '14:52:46', 'DESKTOP-0ROSBKV'),
(439, 'admin', 'Utilisateur', '2019-09-18', '14:53:21', 'DESKTOP-0ROSBKV'),
(440, 'admin', 'Utilisateur', '2019-09-18', '14:57:44', 'DESKTOP-0ROSBKV'),
(441, 'admin', 'Utilisateur', '2019-09-18', '14:58:01', 'DESKTOP-0ROSBKV'),
(442, 'admin', 'Utilisateur', '2019-09-18', '14:59:24', 'DESKTOP-0ROSBKV'),
(443, 'admin', 'Utilisateur', '2019-09-18', '15:01:01', 'DESKTOP-0ROSBKV'),
(444, 'admin', 'Utilisateur', '2019-09-18', '15:03:22', 'DESKTOP-0ROSBKV'),
(445, 'admin', 'Utilisateur', '2019-09-18', '15:03:45', 'DESKTOP-0ROSBKV'),
(446, 'admin', 'Utilisateur', '2019-09-18', '15:04:57', 'DESKTOP-0ROSBKV'),
(447, 'admin', 'Utilisateur', '2019-09-18', '15:05:41', 'DESKTOP-0ROSBKV'),
(448, 'admin', 'Utilisateur', '2019-09-18', '15:08:36', 'DESKTOP-0ROSBKV'),
(449, 'admin', 'Utilisateur', '2019-09-18', '15:13:41', 'DESKTOP-0ROSBKV'),
(450, 'admin', 'Utilisateur', '2019-09-18', '15:22:41', 'DESKTOP-0ROSBKV'),
(451, 'admin', 'Utilisateur', '2019-09-18', '15:26:31', 'DESKTOP-0ROSBKV'),
(452, 'admin', 'Utilisateur', '2019-09-18', '15:29:53', 'DESKTOP-0ROSBKV'),
(453, 'admin', 'Utilisateur', '2019-09-18', '15:36:36', 'DESKTOP-0ROSBKV'),
(454, 'admin', 'Utilisateur', '2019-09-18', '15:37:21', 'DESKTOP-0ROSBKV'),
(455, 'admin', 'Utilisateur', '2019-09-18', '16:07:34', 'DESKTOP-0ROSBKV'),
(456, 'admin', 'Utilisateur', '2019-09-18', '16:11:49', 'DESKTOP-0ROSBKV'),
(457, 'admin', 'Utilisateur', '2019-09-18', '16:12:28', 'DESKTOP-0ROSBKV'),
(458, 'admin', 'Utilisateur', '2019-09-18', '16:13:29', 'DESKTOP-0ROSBKV'),
(459, 'admin', 'Utilisateur', '2019-09-18', '16:16:36', 'DESKTOP-0ROSBKV'),
(460, 'admin', 'Utilisateur', '2019-09-18', '16:17:43', 'DESKTOP-0ROSBKV'),
(461, 'admin', 'Utilisateur', '2019-09-18', '16:18:51', 'DESKTOP-0ROSBKV'),
(462, 'admin', 'Utilisateur', '2019-09-18', '16:20:51', 'DESKTOP-0ROSBKV'),
(463, 'admin', 'Utilisateur', '2019-09-18', '16:21:29', 'DESKTOP-0ROSBKV'),
(464, 'admin', 'Utilisateur', '2019-09-18', '16:22:17', 'DESKTOP-0ROSBKV'),
(465, 'admin', 'Utilisateur', '2019-09-18', '16:23:06', 'DESKTOP-0ROSBKV'),
(466, 'admin', 'Utilisateur', '2019-09-18', '16:24:11', 'DESKTOP-0ROSBKV'),
(467, 'admin', 'Utilisateur', '2019-09-18', '16:26:26', 'DESKTOP-0ROSBKV'),
(468, 'admin', 'Utilisateur', '2019-09-18', '16:27:39', 'DESKTOP-0ROSBKV'),
(469, 'admin', 'Utilisateur', '2019-09-20', '10:23:26', 'DESKTOP-0ROSBKV'),
(470, 'admin', 'Utilisateur', '2019-09-20', '10:36:42', 'DESKTOP-0ROSBKV'),
(471, 'admin', 'Utilisateur', '2019-09-20', '10:37:25', 'DESKTOP-0ROSBKV'),
(472, 'admin', 'Utilisateur', '2019-09-20', '10:41:21', 'DESKTOP-0ROSBKV'),
(473, 'admin', 'Utilisateur', '2019-09-20', '10:49:33', 'DESKTOP-0ROSBKV'),
(474, 'admin', 'Utilisateur', '2019-09-20', '10:49:55', 'DESKTOP-0ROSBKV'),
(475, 'admin', 'Utilisateur', '2019-09-20', '11:08:51', 'DESKTOP-0ROSBKV'),
(476, 'admin', 'Utilisateur', '2019-09-20', '11:12:29', 'DESKTOP-0ROSBKV'),
(477, 'admin', 'Utilisateur', '2019-09-20', '11:20:14', 'DESKTOP-0ROSBKV'),
(478, 'admin', 'Utilisateur', '2019-09-20', '11:28:58', 'DESKTOP-0ROSBKV'),
(479, 'admin', 'Utilisateur', '2019-09-20', '11:48:55', 'DESKTOP-0ROSBKV'),
(480, 'admin', 'Utilisateur', '2019-09-20', '12:02:56', 'DESKTOP-0ROSBKV'),
(481, 'admin', 'Utilisateur', '2019-09-26', '14:12:36', 'DESKTOP-0ROSBKV'),
(482, 'admin', 'Utilisateur', '2019-09-26', '14:16:30', 'DESKTOP-0ROSBKV'),
(483, 'admin', 'Utilisateur', '2019-09-27', '13:49:29', 'DESKTOP-0ROSBKV'),
(484, 'admin', 'Utilisateur', '2019-09-27', '13:51:11', 'DESKTOP-0ROSBKV'),
(485, 'admin', 'Utilisateur', '2019-09-27', '13:51:36', 'DESKTOP-0ROSBKV'),
(486, 'su', 'Utilisateur', '2019-09-27', '14:01:32', 'DESKTOP-0ROSBKV'),
(487, 'su', 'Utilisateur', '2019-09-27', '14:02:07', 'DESKTOP-0ROSBKV'),
(488, 'admin', 'Utilisateur', '2019-09-27', '14:08:22', 'DESKTOP-0ROSBKV'),
(489, 'admin', 'Utilisateur', '2019-09-27', '14:14:49', 'DESKTOP-0ROSBKV'),
(490, 'root', 'Directeur', '2019-09-27', '14:17:56', 'DESKTOP-0ROSBKV'),
(491, 'admin', 'Utilisateur', '2019-09-27', '14:28:21', 'DESKTOP-0ROSBKV'),
(492, 'admin', 'Utilisateur', '2019-09-27', '14:47:30', 'DESKTOP-0ROSBKV'),
(493, 'admin', 'Utilisateur', '2019-09-27', '14:48:14', 'DESKTOP-0ROSBKV'),
(494, 'admin', 'Utilisateur', '2019-09-27', '15:21:02', 'DESKTOP-0ROSBKV'),
(495, 'admin', 'Utilisateur', '2019-09-27', '15:21:29', 'DESKTOP-0ROSBKV'),
(496, 'admin', 'Utilisateur', '2019-09-27', '15:24:45', 'DESKTOP-0ROSBKV'),
(497, 'admin', 'Utilisateur', '2019-09-27', '15:53:32', 'DESKTOP-0ROSBKV'),
(498, 'admin', 'Utilisateur', '2019-09-27', '15:59:05', 'DESKTOP-0ROSBKV'),
(499, 'admin', 'Utilisateur', '2019-09-27', '16:12:09', 'DESKTOP-0ROSBKV'),
(500, 'admin', 'Utilisateur', '2019-09-27', '16:12:53', 'DESKTOP-0ROSBKV'),
(501, 'su', 'Utilisateur', '2019-09-28', '07:56:33', 'DESKTOP-0ROSBKV'),
(502, 'admin', 'Utilisateur', '2019-09-30', '10:51:11', 'DESKTOP-0ROSBKV'),
(503, 'admin', 'Utilisateur', '2019-09-30', '10:53:44', 'DESKTOP-0ROSBKV'),
(504, 'su', 'Utilisateur', '2019-09-30', '11:06:48', 'DESKTOP-0ROSBKV'),
(505, 'admin', 'Utilisateur', '2019-10-03', '15:28:54', 'DESKTOP-0ROSBKV'),
(506, 'admin', 'Utilisateur', '2019-10-03', '15:33:15', 'DESKTOP-0ROSBKV'),
(507, 'su', 'Utilisateur', '2019-10-22', '11:19:14', 'DESKTOP-0ROSBKV'),
(508, 'su', 'Utilisateur', '2019-10-22', '12:50:22', 'DESKTOP-0ROSBKV'),
(509, 'su', 'Utilisateur', '2019-10-29', '05:12:54', 'DESKTOP-0ROSBKV'),
(510, 'su', 'Utilisateur', '2019-10-29', '07:44:36', 'DESKTOP-0ROSBKV'),
(511, 'admin', 'Utilisateur', '2019-11-04', '08:19:47', 'DESKTOP-0ROSBKV'),
(512, 'admin', 'Utilisateur', '2019-11-04', '08:21:05', 'DESKTOP-0ROSBKV'),
(513, 'admin', 'Utilisateur', '2019-11-04', '08:27:34', 'DESKTOP-0ROSBKV'),
(514, 'admin', 'Utilisateur', '2019-11-04', '08:30:37', 'DESKTOP-0ROSBKV'),
(515, 'admin', 'Utilisateur', '2019-11-06', '09:27:08', 'DESKTOP-0ROSBKV'),
(516, 'admin', 'Utilisateur', '2019-11-06', '09:28:44', 'DESKTOP-0ROSBKV'),
(517, 'admin', 'Utilisateur', '2019-11-06', '09:29:28', 'DESKTOP-0ROSBKV'),
(518, 'admin', 'Utilisateur', '2019-11-06', '09:33:58', 'DESKTOP-0ROSBKV'),
(519, 'admin', 'Utilisateur', '2019-11-06', '09:35:40', 'DESKTOP-0ROSBKV'),
(520, 'admin', 'Utilisateur', '2019-11-06', '14:39:01', 'DESKTOP-0ROSBKV'),
(521, 'admin', 'Utilisateur', '2019-11-06', '15:18:29', 'DESKTOP-0ROSBKV'),
(522, 'admin', 'Utilisateur', '2019-11-07', '07:37:01', 'DESKTOP-0ROSBKV'),
(523, 'admin', 'Utilisateur', '2019-11-07', '08:06:31', 'DESKTOP-0ROSBKV'),
(524, 'admin', 'Utilisateur', '2019-12-10', '17:20:38', 'asuss-PC'),
(525, 'admin', 'Utilisateur', '2019-12-10', '17:21:32', 'asuss-PC'),
(526, 'admin', 'Utilisateur', '2019-12-11', '15:16:44', 'asuss-PC'),
(527, 'admin', 'Utilisateur', '2019-12-11', '15:25:45', 'asuss-PC'),
(528, 'admin', 'Utilisateur', '2019-12-11', '15:26:34', 'asuss-PC'),
(529, 'admin', 'Utilisateur', '2019-12-23', '15:29:40', 'asuss-PC'),
(530, 'admin', 'Utilisateur', '2019-12-23', '15:43:02', 'asuss-PC'),
(531, 'admin', 'Utilisateur', '2019-12-27', '10:36:01', 'asuss-PC'),
(532, 'admin', 'Utilisateur', '2019-12-27', '10:36:34', 'asuss-PC'),
(533, 'admin', 'Utilisateur', '2019-12-27', '10:37:25', 'asuss-PC'),
(534, 'admin', 'Utilisateur', '2019-12-27', '10:39:07', 'asuss-PC'),
(535, 'admin', 'Utilisateur', '2019-12-27', '10:44:20', 'asuss-PC'),
(536, 'admin', 'Utilisateur', '2019-12-27', '10:48:31', 'asuss-PC'),
(537, 'admin', 'Utilisateur', '2019-12-27', '11:03:47', 'asuss-PC'),
(538, 'admin', 'Utilisateur', '2019-12-27', '11:41:42', 'asuss-PC'),
(539, 'admin', 'Utilisateur', '2019-12-28', '09:44:14', 'asuss-PC'),
(540, 'admin', 'Utilisateur', '2019-12-28', '09:45:27', 'asuss-PC'),
(541, 'admin', 'Utilisateur', '2019-12-28', '09:47:11', 'asuss-PC'),
(542, 'admin', 'Utilisateur', '2019-12-28', '09:48:55', 'asuss-PC'),
(543, 'admin', 'Utilisateur', '2019-12-28', '09:49:26', 'asuss-PC'),
(544, 'admin', 'Utilisateur', '2019-12-28', '09:50:45', 'asuss-PC'),
(545, 'admin', 'Utilisateur', '2020-12-30', '09:58:17', 'asuss-PC'),
(546, 'admin', 'Utilisateur', '2020-12-30', '09:58:50', 'asuss-PC'),
(547, 'admin', 'Utilisateur', '2020-12-30', '10:04:36', 'asuss-PC'),
(548, 'admin', 'Utilisateur', '2020-12-30', '10:04:53', 'asuss-PC'),
(549, 'admin', 'Utilisateur', '2020-12-30', '11:59:56', 'asuss-PC'),
(550, 'amine', 'directeur', '2020-01-24', '08:58:36', 'asuss-PC'),
(551, 'amine', 'directeur', '2020-01-24', '08:59:20', 'asuss-PC'),
(552, 'amine', 'directeur', '2020-01-24', '09:40:05', 'asuss-PC'),
(553, 'amine', 'directeur', '2020-01-24', '14:19:56', 'asuss-PC'),
(554, 'amine', 'directeur', '2020-01-25', '09:38:41', 'asuss-PC'),
(555, 'amine', 'directeur', '2020-02-10', '16:56:10', 'asuss-PC'),
(556, 'achref', 'Utilisateur', '2020-02-27', '17:09:50', 'asuss-PC'),
(557, 'achref', 'Utilisateur', '2020-02-27', '17:12:32', 'asuss-PC'),
(558, 'achref', 'Utilisateur', '2020-03-05', '08:21:25', 'asuss-PC'),
(559, 'achref', 'Utilisateur', '2020-03-06', '10:44:18', 'asuss-PC'),
(560, 'achref', 'Utilisateur', '2020-03-06', '10:45:46', 'asuss-PC'),
(561, 'achref', 'Utilisateur', '2020-07-06', '19:23:16', 'DESKTOP-J1SBC65'),
(562, 'amine', 'directeur', '2020-07-06', '21:20:03', 'DESKTOP-J1SBC65'),
(563, 'amine', 'directeur', '2020-07-07', '18:42:34', 'DESKTOP-J1SBC65'),
(564, 'amine', 'directeur', '2020-07-07', '18:43:17', 'DESKTOP-J1SBC65'),
(565, 'amine', 'directeur', '2020-07-07', '21:39:04', 'DESKTOP-J1SBC65'),
(566, 'amine', 'directeur', '2020-07-07', '22:43:04', 'DESKTOP-J1SBC65'),
(567, 'amine', 'directeur', '2020-07-07', '22:49:26', 'DESKTOP-J1SBC65'),
(568, 'amine', 'directeur', '2020-07-07', '22:52:05', 'DESKTOP-J1SBC65'),
(569, 'amine', 'directeur', '2020-07-07', '22:52:50', 'DESKTOP-J1SBC65'),
(570, 'achref', 'Utilisateur', '2020-07-08', '18:00:56', 'DESKTOP-J1SBC65'),
(571, 'amine', 'directeur', '2020-07-08', '18:34:51', 'DESKTOP-J1SBC65'),
(572, 'amine', 'directeur', '2020-07-08', '19:08:08', 'DESKTOP-J1SBC65'),
(573, 'amine', 'directeur', '2020-07-08', '19:09:21', 'DESKTOP-J1SBC65'),
(574, 'amine', 'directeur', '2020-07-08', '19:12:12', 'DESKTOP-J1SBC65'),
(575, 'amine', 'directeur', '2020-07-08', '19:20:05', 'DESKTOP-J1SBC65'),
(576, 'achref', 'Utilisateur', '2020-07-08', '23:19:23', 'DESKTOP-J1SBC65'),
(577, 'amine', 'directeur', '2020-07-09', '01:06:28', 'DESKTOP-J1SBC65'),
(578, 'amine', 'directeur', '2020-07-09', '01:58:50', 'DESKTOP-J1SBC65'),
(579, 'amine', 'directeur', '2020-07-09', '02:36:27', 'DESKTOP-J1SBC65'),
(580, 'amine', 'directeur', '2020-07-09', '02:41:08', 'DESKTOP-J1SBC65'),
(581, 'amine', 'directeur', '2020-07-09', '02:55:22', 'DESKTOP-J1SBC65'),
(582, 'amine', 'directeur', '2020-07-09', '12:46:44', 'DESKTOP-J1SBC65'),
(583, 'amine', 'directeur', '2020-07-09', '13:37:53', 'DESKTOP-J1SBC65'),
(584, 'amine', 'directeur', '2020-07-11', '19:19:22', 'DESKTOP-J1SBC65'),
(585, 'amine', 'directeur', '2020-07-11', '19:56:22', 'DESKTOP-J1SBC65'),
(586, 'amine', 'directeur', '2020-07-11', '19:57:02', 'DESKTOP-J1SBC65'),
(587, 'amine', 'directeur', '2020-07-11', '19:57:36', 'DESKTOP-J1SBC65'),
(588, 'achref', 'Utilisateur', '2020-07-11', '22:35:37', 'DESKTOP-J1SBC65'),
(589, 'achref', 'Utilisateur', '2020-07-11', '22:37:46', 'DESKTOP-J1SBC65'),
(590, 'achref', 'Utilisateur', '2020-07-11', '22:43:34', 'DESKTOP-J1SBC65'),
(591, 'achref', 'Utilisateur', '2020-07-11', '22:45:25', 'DESKTOP-J1SBC65'),
(592, 'achref', 'Utilisateur', '2020-07-12', '00:14:23', 'DESKTOP-J1SBC65'),
(593, 'achref', 'Utilisateur', '2020-07-12', '00:15:48', 'DESKTOP-J1SBC65'),
(594, 'achref', 'Utilisateur', '2020-07-12', '00:19:55', 'DESKTOP-J1SBC65'),
(595, 'achref', 'Utilisateur', '2020-07-12', '00:22:04', 'DESKTOP-J1SBC65'),
(596, 'achref', 'Utilisateur', '2020-07-12', '00:23:05', 'DESKTOP-J1SBC65'),
(597, 'achref', 'Utilisateur', '2020-07-12', '01:21:45', 'DESKTOP-J1SBC65'),
(598, 'achref', 'Utilisateur', '2020-07-12', '01:29:58', 'DESKTOP-J1SBC65'),
(599, 'achref', 'Utilisateur', '2020-07-12', '01:40:35', 'DESKTOP-J1SBC65'),
(600, 'achref', 'Utilisateur', '2020-07-12', '08:55:21', 'DESKTOP-J1SBC65'),
(601, 'achref', 'Utilisateur', '2020-07-12', '08:58:23', 'DESKTOP-J1SBC65'),
(602, 'achref', 'Utilisateur', '2020-07-12', '09:06:58', 'DESKTOP-J1SBC65'),
(603, 'amine', 'directeur', '2020-07-12', '09:08:21', 'DESKTOP-J1SBC65'),
(604, 'achref', 'Utilisateur', '2020-07-12', '11:41:37', 'DESKTOP-J1SBC65'),
(605, 'amine', 'directeur', '2020-07-12', '11:48:22', 'DESKTOP-J1SBC65'),
(606, 'achref', 'Utilisateur', '2020-07-12', '12:46:24', 'DESKTOP-J1SBC65'),
(607, 'achref', 'Utilisateur', '2020-07-12', '12:50:53', 'DESKTOP-J1SBC65'),
(608, 'achref', 'Utilisateur', '2020-07-12', '12:53:47', 'DESKTOP-J1SBC65'),
(609, 'achref', 'Utilisateur', '2020-07-12', '13:20:50', 'DESKTOP-J1SBC65'),
(610, 'amine', 'directeur', '2020-07-12', '13:22:49', 'DESKTOP-J1SBC65'),
(611, 'achref', 'Utilisateur', '2020-07-14', '17:47:49', 'DESKTOP-J1SBC65'),
(612, 'amine', 'directeur', '2020-07-14', '20:02:51', 'DESKTOP-J1SBC65'),
(613, 'achref', 'Utilisateur', '2020-07-14', '20:56:09', 'DESKTOP-J1SBC65'),
(614, 'achref', 'Utilisateur', '2020-07-14', '21:19:40', 'DESKTOP-J1SBC65'),
(615, 'achref', 'Utilisateur', '2020-07-14', '21:20:34', 'DESKTOP-J1SBC65'),
(616, 'achref', 'Utilisateur', '2020-07-14', '21:21:20', 'DESKTOP-J1SBC65'),
(617, 'achref', 'Utilisateur', '2020-07-14', '21:22:17', 'DESKTOP-J1SBC65'),
(618, 'achref', 'Utilisateur', '2020-07-14', '21:24:56', 'DESKTOP-J1SBC65'),
(619, 'achref', 'Utilisateur', '2020-07-14', '21:28:16', 'DESKTOP-J1SBC65'),
(620, 'achref', 'Utilisateur', '2020-07-14', '21:29:28', 'DESKTOP-J1SBC65'),
(621, 'achref', 'Utilisateur', '2020-07-14', '21:32:10', 'DESKTOP-J1SBC65'),
(622, 'achref', 'Utilisateur', '2020-07-14', '21:36:31', 'DESKTOP-J1SBC65'),
(623, 'achref', 'Utilisateur', '2020-07-14', '21:40:19', 'DESKTOP-J1SBC65'),
(624, 'achref', 'Utilisateur', '2020-07-14', '21:42:55', 'DESKTOP-J1SBC65'),
(625, 'achref', 'Utilisateur', '2020-07-14', '22:39:41', 'DESKTOP-J1SBC65'),
(626, 'achref', 'Utilisateur', '2020-07-14', '23:20:44', 'DESKTOP-J1SBC65'),
(627, 'achref', 'Utilisateur', '2020-07-14', '23:22:12', 'DESKTOP-J1SBC65'),
(628, 'achref', 'Utilisateur', '2020-07-15', '00:04:36', 'DESKTOP-J1SBC65'),
(629, 'achref', 'Utilisateur', '2020-07-15', '13:53:31', 'DESKTOP-J1SBC65'),
(630, 'achref', 'Utilisateur', '2020-07-15', '13:56:40', 'DESKTOP-J1SBC65'),
(631, 'achref', 'Utilisateur', '2020-07-15', '14:48:22', 'DESKTOP-J1SBC65'),
(632, 'achref', 'Utilisateur', '2020-07-15', '14:49:23', 'DESKTOP-J1SBC65'),
(633, 'achref', 'Utilisateur', '2020-07-15', '22:53:19', 'DESKTOP-J1SBC65'),
(634, 'amine', 'directeur', '2020-07-15', '22:54:43', 'DESKTOP-J1SBC65'),
(635, 'achref', 'Utilisateur', '2020-07-15', '22:55:49', 'DESKTOP-J1SBC65'),
(636, 'amine', 'directeur', '2020-07-15', '22:59:33', 'DESKTOP-J1SBC65');

-- --------------------------------------------------------

--
-- Table structure for table `connexion_fail`
--

CREATE TABLE IF NOT EXISTS `connexion_fail` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Heure` varchar(50) NOT NULL,
  `Nom_PC` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `connexion_fail`
--

INSERT INTO `connexion_fail` (`Id`, `Login`, `Password`, `Date`, `Heure`, `Nom_PC`) VALUES
(1, 'amine', '', '2020-05-04', '12:12:17', 'asuss-PC'),
(2, 'amine', '', '2020-06-27', '11:04:36', 'asuss-PC'),
(3, 'amine', '', '2020-07-06', '12:27:20', 'asuss-PC'),
(4, 'amine', '', '2020-07-06', '13:31:31', 'asuss-PC'),
(5, 'amine', '', '2020-07-06', '13:41:42', 'asuss-PC'),
(6, 'achref', '', '2020-07-06', '19:22:53', 'DESKTOP-J1SBC65'),
(7, 'amine', '', '2020-07-06', '19:23:50', 'DESKTOP-J1SBC65'),
(8, 'amine', '', '2020-07-06', '21:23:49', 'DESKTOP-J1SBC65'),
(9, 'amine', '', '2020-07-06', '22:03:06', 'DESKTOP-J1SBC65'),
(10, 'amine', '', '2020-07-07', '20:55:53', 'DESKTOP-J1SBC65'),
(11, 'amine', '', '2020-07-07', '21:10:47', 'DESKTOP-J1SBC65'),
(12, 'amine', '', '2020-07-07', '21:38:54', 'DESKTOP-J1SBC65'),
(13, '', '', '2020-07-08', '19:55:18', 'DESKTOP-J1SBC65'),
(14, '', '', '2020-07-09', '01:14:45', 'DESKTOP-J1SBC65'),
(15, '', '', '2020-07-09', '01:40:15', 'DESKTOP-J1SBC65'),
(16, '', '', '2020-07-09', '02:03:17', 'DESKTOP-J1SBC65'),
(17, '', '', '2020-07-09', '02:22:24', 'DESKTOP-J1SBC65'),
(18, '', '', '2020-07-09', '02:22:26', 'DESKTOP-J1SBC65'),
(19, '', '', '2020-07-12', '12:32:17', 'DESKTOP-J1SBC65'),
(20, 'amine', 'amine', '2020-07-14', '20:26:17', 'DESKTOP-J1SBC65');

-- --------------------------------------------------------

--
-- Table structure for table `historique`
--

CREATE TABLE IF NOT EXISTS `historique` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(50) NOT NULL,
  `Barcode` varchar(500) NOT NULL,
  `Categorie` varchar(500) NOT NULL,
  `Quantite` int(11) NOT NULL,
  `Prix_vente` varchar(50) NOT NULL,
  `total_base` varchar(50) NOT NULL,
  `total` varchar(50) NOT NULL,
  `Date_Sortie` varchar(50) NOT NULL,
  `Heure_Sortie` varchar(50) NOT NULL,
  `user` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `historique`
--

INSERT INTO `historique` (`Id`, `Nom`, `Barcode`, `Categorie`, `Quantite`, `Prix_vente`, `total_base`, `total`, `Date_Sortie`, `Heure_Sortie`, `user`) VALUES
(1, '20 Mars Pqt', '61940017', 'tabac', 3, '3.500', '9.9', '10.5', '14-07-2020', '19:55', 'amine'),
(2, '20 Mars Pqt', '61940017', 'tabac', 3, '3.500', '9.9', '10.5', '14-07-2020', '20:00', 'amine'),
(3, 'prince', '01222', 'gouter', 1, '0.500', '0.400', '0.500', '14-07-2020', '20:01', 'amine'),
(4, 'camel bleu pqt', '84165787', 'tabac', 1, '6.900', '6.000', '6.900', '14-07-2020', '20:01', 'amine'),
(5, 'Break', '023698', 'gouter', 1, '1.200', '0.900', '1.200', '14-07-2020', '20:13', 'amine'),
(6, 'Break', '023698', 'gouter', 3, '1.200', '2.7', '3.6', '14-07-2020', '20:13', 'amine'),
(7, 'Break', '023698', 'gouter', 1, '1.200', '0.900', '1.200', '14-07-2020', '23:56', 'amine'),
(8, '20 Mars Pqt', '61940018', 'tabac', 2, '3.500', '6.6', '7', '15-07-2020', '18:56', 'amine'),
(9, 'camel bleu pqt', '84165787', 'tabac', 6, '6.900', '36', '41.4', '15-07-2020', '22:36', 'amine'),
(10, 'camel bleu pqt', '84165787', 'tabac', 6, '6.900', '36', '41.4', '15-07-2020', '22:36', 'amine'),
(11, 'gateau', '89574', 'gouter', 3, '3.100', '6', '9.3', '15-07-2020', '22:44', 'amine');

-- --------------------------------------------------------

--
-- Table structure for table `license`
--

CREATE TABLE IF NOT EXISTS `license` (
  `Id` int(50) NOT NULL AUTO_INCREMENT,
  `Code_licence` varchar(50) NOT NULL,
  `Jour` varchar(50) NOT NULL,
  `Mois` varchar(50) NOT NULL,
  `Annee` varchar(50) NOT NULL,
  `Test` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `license`
--

INSERT INTO `license` (`Id`, `Code_licence`, `Jour`, `Mois`, `Annee`, `Test`) VALUES
(1, 'WG5FXMZ', '19', '3', '2021', '1');

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `Barcode` varchar(500) NOT NULL,
  `Categorie` varchar(50) NOT NULL,
  `Prix_vente` varchar(50) NOT NULL,
  `Prix_base` varchar(50) NOT NULL,
  `Max` int(11) NOT NULL,
  `Min` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`Id`, `nom`, `Barcode`, `Categorie`, `Prix_vente`, `Prix_base`, `Max`, `Min`) VALUES
(3, '20 Mars Pqt', '61940018', 'tabac', '3.500', '3.300', 50, 0),
(4, '20 Mars cigarette', '619400178', 'tabac', '0.251', '0.2', 50, 0),
(5, 'camel bleu pqt', '84165787', 'tabac', '6.900', '6.000', 1500, 0),
(10, 'stylo noir', '4004675000415', 'accessoire', '3.100', '2.500', 90, 0),
(16, 'Break', '1222', 'gouter', '0.450', '0.350', 100, 0),
(17, 'baguette', '1', 'pates', '0.200', '0.180', 40, 0),
(18, 'gateau', '89574', 'gouter', '3.100', '2.000', 10, 0);

-- --------------------------------------------------------

--
-- Table structure for table `produit_stock`
--

CREATE TABLE IF NOT EXISTS `produit_stock` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `Barcode` varchar(500) NOT NULL,
  `Categorie` varchar(50) NOT NULL,
  `Prix_vente` varchar(50) NOT NULL,
  `Prix_base` varchar(50) NOT NULL,
  `Max` int(11) NOT NULL,
  `Min` int(11) NOT NULL,
  `Quantite` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `produit_stock`
--

INSERT INTO `produit_stock` (`Id`, `nom`, `Barcode`, `Categorie`, `Prix_vente`, `Prix_base`, `Max`, `Min`, `Quantite`) VALUES
(3, '20 Mars Pqt', '61940018', 'tabac', '3.500', '3.300', 50, 0, 49),
(4, '20 Mars cigarette', '619400178', 'tabac', '0.251', '0.2', 50, 0, 49),
(5, 'camel bleu pqt', '84165787', 'tabac', '6.900', '6.000', 1500, 0, 513),
(10, 'stylo noir', '4004675000415', 'accessoire', '3.100', '2.500', 90, 0, 35),
(16, 'Break', '1222', 'gouter', '0.450', '0.350', 100, 0, 40),
(17, 'baguette', '1', 'pates', '0.200', '0.180', 40, 0, 20),
(18, 'gateau', '89574', 'gouter', '3.100', '2.000', 10, 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `stock_historique`
--

CREATE TABLE IF NOT EXISTS `stock_historique` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `User` varchar(500) NOT NULL,
  `Nom` varchar(500) NOT NULL,
  `Barcode` varchar(500) NOT NULL,
  `Quantité_precedente` int(50) NOT NULL,
  `Quantité_ajoutée` int(50) NOT NULL,
  `Quantité_totale` int(50) NOT NULL,
  `Date_ajout` varchar(100) NOT NULL,
  `Heure_ajout` varchar(500) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Barcode` (`Barcode`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `stock_historique`
--

INSERT INTO `stock_historique` (`Id`, `User`, `Nom`, `Barcode`, `Quantité_precedente`, `Quantité_ajoutée`, `Quantité_totale`, `Date_ajout`, `Heure_ajout`) VALUES
(9, 'amine', 'Break', '1222', 0, 50, 50, '15-07-2020', '14:51'),
(10, 'amine', '20 Mars Pqt', '61940018', 44, 5, 49, '15-07-2020', '21:01'),
(11, 'amine', 'stylo noir', '4004675000415', 33, 2, 35, '15-07-2020', '21:01'),
(12, 'amine', '20 Mars cigarette', '619400178', 48, 1, 49, '15-07-2020', '21:01'),
(13, 'amine', 'baguette', '1', 0, 20, 20, '15-07-2020', '22:40'),
(14, 'amine', 'gateau', '89574', 0, 5, 5, '15-07-2020', '22:44'),
(15, 'achref', 'camel bleu pqt', '84165787', 503, 10, 513, '15-07-2020', '22:53');

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`Id`, `Login`, `Password`, `type`) VALUES
(1, 'amine', '1', 'directeur'),
(2, 'achref', '0', 'Utilisateur'),
(3, 'mahmoud', '123', 'directeur');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
