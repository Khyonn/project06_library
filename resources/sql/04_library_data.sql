USE library;

INSERT INTO Users(firstName, lastName, password, email, userStatus, userType) VALUES
('Nathanaël', 'Morel', 'lws1234', 'contact@contact.fr', 'A', 'ADMIN'),
('Kévin', 'Martin', 'password', 'kevin.martin@contact.fr', 'U', 'ADMIN'),
('Florian', 'Plop', 'password', 'florian.plop@contact.fr', 'A', 'BASIC'),
('Desact', 'Desact', 'password', 'desact@contact.fr', 'U', 'BASIC');

INSERT INTO Books(title, author, summary, bookStatus) VALUES
('Fake title', 'Fake author', 'Fake summary', 'A'),
('Bleach [Tome 1] - The Death and the strawberry', 'Tite Kubo', 'Adolescent de quinze ans, Ichigo Kurosaki possède un don particulier : celui de voir les esprits. Un jour, il croise la route d''une belle Shinigami (un être spirituel) en train de pourchasser une "âme perdue", un esprit maléfique qui hante notre monde et n''arrive pas à trouver le repos.', 'A'),
('One piece [Tome 1]', 'Eiichiro Oda', 'Nous sommes à l''ère des pirates. Luffy, un garçon espiègle, rêve de devenir le roi des pirates en trouvant le "One Piece", un fabuleux trésor. Par mégarde, Luffy a avalé un jour un fruit démoniaque qui l''a transformé en homme caoutchouc. Depuis, il est capable de contorsionner son corps élastique dans tous les sens, mais il a perdu la faculté de nager. Avec l''aide de ses précieux amis, dont le fidèle Shanks, il va devoir affronter de redoutables pirates dans des aventures toujours plus rocambolesques.', 'U');

INSERT INTO BookSamples(bookId, bookSampleStatus) VALUES
(1, 'A'),
(2, 'A'),
(2, 'U'),
(3, 'A'),
(3, 'U'),
(2, 'A'),
(2, 'A'),
(2, 'A'),
(2, 'A'),
(2, 'A');

INSERT INTO Loans(bookSampleId, borrowerId, startDate, returnDate, prolongationNumber) VALUES 
(7, 3, '2019-11-29 17:29:00', NULL, 0),
(8, 3, '2019-11-29 17:29:00', '2019-11-30 17:29:00', 0),
(9, 1, '2019-11-29 17:29:00', NULL, 0),
(10, 3, '2019-11-29 17:29:00', NULL, 0);

