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


--
-- Pour les tests TK1 :
--

INSERT INTO Users(id, firstName, lastName, password, email, userStatus, userType) VALUES
(-1, 'TK1_user1', 'user1', 'user1', 'user1', 'A', 'BASIC'),
(-2, 'TK1_user2', 'user2', 'user2', 'user2', 'A', 'BASIC'),
(-3, 'TK1_user3', 'user3', 'user3', 'user3', 'A', 'BASIC'),
(-4, 'TK1_user4', 'user4', 'user4', 'user4', 'A', 'BASIC');

INSERT INTO Books(id, title, author, summary, bookStatus) VALUES
(-1, 'TK1_lv1', 'livre1', 'livre1', 'A');

INSERT INTO BookSamples(id, bookId, bookSampleStatus) VALUES
(-1, -1, 'A');

INSERT INTO Loans(id, bookSampleId, borrowerId, startDate, returnDate, prolongationNumber) VALUES
(-1, -1, -2, '2019-12-10 17:29:00', NULL, 0);
