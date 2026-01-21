-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: mysql1
-- Generation Time: Jan 18, 2026 at 11:34 PM
-- Server version: 8.0.44
-- PHP Version: 8.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tablevoitures`
--

-- --------------------------------------------------------

--
-- Table structure for table `voitures`
--

CREATE TABLE `voitures` (
  `id` int NOT NULL,
  `modele` varchar(50) NOT NULL,
  `marque` varchar(50) NOT NULL,
  `ventes` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `voitures`
--

INSERT INTO `voitures` (`id`, `modele`, `marque`, `ventes`) VALUES
(1, 'e-2008', 'Peugeot', 8231),
(2, 'Model Y', 'Tesla', 19206),
(3, 'ID 3', 'Volkswagen', 5153),
(4, 'e-C3', 'Citroen', 2500),
(5, 'Megane E-Tech', 'Renault', 9528),
(6, 'Scenic E-Tech', 'Renault', 23050),
(7, 'iX 1', 'BMW', 9306),
(8, 'Enyaq', 'Skoda', 4392),
(9, 'Sealion 7', 'BYD', 2305),
(10, 'EX90', 'Volvo', 1956),
(11, 'Modele E-sport', 'Tesla', 12345),
(12, 'XT-50', 'Volvo', 11000),
(13, 'EX-8', 'Mazda', 14999),
(16, 'Rx7', 'Mazda', 1234);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `voitures`
--
ALTER TABLE `voitures`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `voitures`
--
ALTER TABLE `voitures`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
