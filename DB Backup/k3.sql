-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 04, 2019 at 03:02 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `k3`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `UID` varchar(10) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `SID` int(2) NOT NULL,
  `SALARY` double(10,2) NOT NULL,
  `CONTACT` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`UID`, `NAME`, `SID`, `SALARY`, `CONTACT`) VALUES
('BT-01AD', 'TANVIR TANJUM SHOURAV', 1, 200000.00, 1515217821),
('BT-01EP', 'RAHAT', 2, 45000.00, 1735493564),
('BT-01SM', 'LIMON', 3, 18000.00, 1724567812),
('BT-02AD', 'ZISHAD LIMON', 1, 150000.00, 1545217865),
('BT-02EP', 'ZISHAD', 2, 70000.00, 1687795146),
('BT-02SM', 'HEMAL', 3, 5000.00, 1825478354),
('BT-03AD', 'SHOURAV', 1, 120000.00, 1515217521),
('BT-03SM', 'HASAN', 3, 12000.00, 1552321780),
('BT-04AD', 'RAHATUL MAKSUD', 1, 120000.00, 1758954125),
('BT-04EP', 'AHMED INTIAZ', 2, 15021.00, 1574325599),
('BT-04SM', 'MAHMUD', 3, 15000.00, 1720014352),
('BT-05SM', 'IQRAM', 3, 14000.00, 1682210475),
('BT-06SM', 'BRINTO', 3, 19000.00, 1515247821),
('BT-07SM', 'AHNAF', 3, 15000.00, 1545247854),
('BT-08SM', 'RUSSEL', 3, 12000.00, 1745789875),
('BT-09SM', 'AMOR', 3, 15000.00, 1578963012);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `UID` varchar(10) NOT NULL,
  `SID` int(2) NOT NULL,
  `PASS` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`UID`, `SID`, `PASS`) VALUES
('BT-01AD', 1, '12345'),
('BT-01EP', 2, '12345'),
('BT-01SM', 3, '12345'),
('BT-02AD', 1, '15699849'),
('BT-02EP', 2, '10563670'),
('BT-02SM', 3, '4444'),
('BT-03AD', 1, '10194073'),
('BT-03SM', 3, '15150019'),
('BT-04AD', 1, '12345'),
('BT-04EP', 2, '10006598'),
('BT-04SM', 3, '12944915'),
('BT-05SM', 3, '19841661'),
('BT-06SM', 3, '13233212'),
('BT-07SM', 3, '19002308'),
('BT-08SM', 3, '12345'),
('BT-09SM', 3, '12345');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `PID` varchar(10) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `TYPE` varchar(50) NOT NULL,
  `QUANTITY` int(7) NOT NULL,
  `BUYPRICE` double(8,2) NOT NULL,
  `SELLPRICE` double(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`PID`, `NAME`, `TYPE`, `QUANTITY`, `BUYPRICE`, `SELLPRICE`) VALUES
('L101F', 'Fujitsu RD-91', 'Laptop', 55, 34000.00, 38000.00),
('L101H', 'HP-envy-x360', 'Laptop', 40, 80000.00, 100000.00),
('L101P', 'Eapson L-101', 'Printer', 1000, 8500.00, 10000.00),
('L101R', 'ROG-118', 'Gaming Laptop', 95, 150000.00, 180000.00),
('L102F', 'Fujitsu RD-92', 'Laptop', 90, 25000.00, 30000.00);

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `CID` varchar(10) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `CONTACT` int(11) NOT NULL,
  `PID` varchar(10) NOT NULL,
  `QUANTITY` int(7) NOT NULL,
  `SELLPRICE` double(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`CID`, `NAME`, `CONTACT`, `PID`, `QUANTITY`, `SELLPRICE`) VALUES
('1478b', 'ZAMEE', 1552478000, 'L102F', 10, 260000.00),
('L101F', 'Alam', 15, 'L101F', 25, 875000.00),
('L101FF', 'Alam', 1515217821, 'L101F', 8, 288000.00),
('L101Fsx', 'Alam', 1547845214, 'L101F', 10, 360000.00),
('L101H', 'Ahmad', 1515217821, 'L101H', 10, 800010.00),
('PCus1', 'Rifat', 1772582922, 'L101F', 10, 38000.00),
('PCus2', 'Brinto', 918881212, 'L101F', 2, 70000.00),
('PCus3', 'Ahmed', 115424754, 'L101F', 3, 114000.00),
('PCus4', 'Limon', 1515247524, 'L101F', 105, 38000.00),
('PCus5', 'Azad', 1784254765, 'L101R', 10, 150000.00),
('PCus6', 'Alam', 1752464782, 'L101R', 5, 180000.00),
('PCus7', 'Rafat', 1538475412, 'L101R', 5, 170000.00),
('XYZ', 'DEWAN', 1525431845, 'L101F', 12, 420000.00);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `SID` int(2) NOT NULL,
  `DESIGNATION` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`SID`, `DESIGNATION`) VALUES
(1, 'ADMIN'),
(2, 'MANAGER'),
(3, 'SALESMAN');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`UID`),
  ADD UNIQUE KEY `CONTACT` (`CONTACT`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`UID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`PID`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`CID`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`SID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
