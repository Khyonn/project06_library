USE library;

--
-- Table structure for table `Reservations`
--

DROP TABLE IF EXISTS `UserOptions`;

CREATE TABLE `UserOptions` (
  `userId` int(11) NOT NULL,
  `warnedBeforeLoanPeremption` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


ALTER TABLE `UserOptions`
ADD CONSTRAINT `UserOptions_Users_FK`
FOREIGN KEY (`userId`) REFERENCES `Users` (`id`);

INSERT INTO UserOptions(userId, warnedBeforeLoanPeremption)
SELECT id, 1
FROM Users;