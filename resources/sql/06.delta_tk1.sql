USE library;

--
-- Table structure for table `Reservations`
--

DROP TABLE IF EXISTS `Reservations`;

CREATE TABLE `Reservations` (
  `bookId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `reservationDate` datetime NOT NULL,
  `mailedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`bookId`, `userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


ALTER TABLE `Reservations`
ADD CONSTRAINT `Reservations_Books_FK`
FOREIGN KEY (`bookId`) REFERENCES `Books` (`id`);

ALTER TABLE `Reservations`
ADD CONSTRAINT `Reservations_Users_FK`
FOREIGN KEY (`userId`) REFERENCES `Users` (`id`);