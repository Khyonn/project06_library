USE library;

-- Ajout des Misérables (utilisable)
INSERT INTO Books(title, author, summary, bookStatus) VALUES
('Les Misérables',
'Victor Hugo',
'Dans la France chaotique du XIXe siècle, Jean Valjean sort de prison. Personne ne tend la main à cet ancien détenu hormis un homme d’église, qui le guide sur la voie de la bonté. Valjean décide alors de vouer sa vie à la défense des miséreux. Son destin va croiser le chemin de Fantine, une mère célibataire prête à tout pour le bonheur de sa fille. Celui des Thénardier, famille cruelle et assoiffée d’argent. Et celui de Javert, inspecteur de police dont l’obsession est de le renvoyer en prison !',
'A');

-- Ajout de deux exemplaires des Misérables (utilisables)
INSERT INTO BookSamples(bookId, bookSampleStatus) VALUES (4, 'A'), (4, 'A');

-- Affectation à 'Florian Plop' des exemplaire avec le deuxième en retard
INSERT INTO Loans(bookSampleId, borrowerId, startDate, returnDate, prolongationNumber) VALUES
(11, 3, '2019-12-22 17:29:00', NULL, 0),
(12, 3, '2019-12-22 00:00:00', NULL, 0);

