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


---
-- Pour les tests TK1 :
---
INSERT INTO Users(id, firstName, lastName, password, email, userStatus, userType) VALUES
(-1, 'TK1 _ Prénom test', 'Nom test', 'password', 'test@test.fr', 'A', 'BASIC'),
(-2, 'TK1 _ Prénom test2', 'Nom test2', 'password', 'test2@test.fr', 'A', 'BASIC');

INSERT INTO Books(id, title, author, summary, bookStatus) VALUES
(-1, 'TK1 _ Titre livre', 'TK1 _ auteur', 'TK1 _ résumé', 'A');

INSERT INTO BookSamples(id, bookId, bookSampleStatus) VALUES
(-1, -1, 'A'),
(-2, -1, 'A');

INSERT INTO Loans(id, bookSampleId, borrowerId, startDate, returnDate, prolongationNumber) VALUES
(-1, -1, -1, '2019-10-19 17:29:00', NULL, 0);

INSERT INTO Reservations(bookId, userId, reservationDate, mailedDate) VALUES
(-1, -1, '2019-11-15 17:29:00', NULL);
