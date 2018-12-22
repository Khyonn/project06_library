--
-- Table constraints for table `Users`
--

ALTER TABLE `Users`
ADD CONSTRAINT `Users_Email_UQ` UNIQUE (`email`);


--
-- Table constraints for table `BookSamples`
--

ALTER TABLE `BookSamples`
ADD CONSTRAINT `BookSamples_Books_FK`
FOREIGN KEY (`bookId`) REFERENCES `Books` (`id`);


--
-- Table constraints for table `Loans`
--

ALTER TABLE `Loans`
ADD CONSTRAINT `Loans_BookSamples_FK`
FOREIGN KEY (`bookSampleId`) REFERENCES `BookSamples` (`id`);

ALTER TABLE `Loans`
ADD CONSTRAINT `Loans_Users_FK`
FOREIGN KEY (`borrowerId`) REFERENCES `Users` (`id`);