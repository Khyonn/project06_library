USE library;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;

CREATE TABLE `Users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `userStatus` varchar(10) DEFAULT 'U',
  `userType` varchar(10) DEFAULT 'BASIC',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `Books`
--

DROP TABLE IF EXISTS `Books`;

CREATE TABLE `Books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `summary` longtext,
  `bookStatus` varchar(10) DEFAULT 'U',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `BookSamples`
--

DROP TABLE IF EXISTS `BookSamples`;

CREATE TABLE `BookSamples` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookSampleStatus` varchar(10) DEFAULT 'U',
  `bookId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `Loans`
--

DROP TABLE IF EXISTS `Loans`;

CREATE TABLE `Loans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookSampleId` int(11) NOT NULL,
  `borrowerId` int(11) NOT NULL,
  `startDate` datetime NOT NULL,
  `returnDate` datetime DEFAULT NULL,
  `prolongationNumber` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

