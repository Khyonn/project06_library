USE library;

--
-- Pour les tests TK2 :
--
INSERT INTO Users(id, firstName, lastName, password, email, userStatus, userType) VALUES
(-5, 'TK1_user5', 'user5', 'user5', 'user5', 'A', 'BASIC');


INSERT INTO Books(id, title, author, summary, bookStatus) VALUES
(-2, 'TK2_lv2', 'livre2', 'livre2', 'A'),
(-3, 'TK2_lv3', 'livre3', 'livre3', 'A');

INSERT INTO BookSamples(id, bookId, bookSampleStatus) VALUES
(-2, -2, 'A'),
(-3, -3, 'A');

INSERT INTO Loans(id, bookSampleId, borrowerId, startDate, returnDate, prolongationNumber) VALUES
(-2, -2, -5, '2019-10-24 17:29:00', NULL, 0), -- Ce pret est en retard
(-3, -3, -5, '2020-01-02 17:29:00', NULL, 0); -- Celui-ci non !