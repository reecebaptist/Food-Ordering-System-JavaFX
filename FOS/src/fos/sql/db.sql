SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE TABLE `users` (
  `fname` varchar(10) NOT NULL,
  `lname` varchar(10) NOT NULL,
  `email` varchar(35) NOT NULL PRIMARY KEY,
  `password` varchar(16) NOT NULL,
  `street` varchar(10) NOT NULL,
  `city` varchar(15) NOT NULL,
  `state` varchar(20) NOT NULL,
   `phoneno` varchar(25) NOT NULL
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users` VALUES('Reece','DSouza','reece@gmail.com','password','Silvergate','Mangalore','Karnataka','9994710295');
INSERT INTO `users` VALUES('Reece','DSouza','3','4','Silvergate','Mangalore','Karnataka','9994710295');


CREATE TABLE `feedback` (
  `id` varchar(3) NOT NULL PRIMARY KEY,
  `user` varchar(20) NOT NULL,
  `feedback` varchar(500) NOT NULL
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `cart` (
  `billno` int(5) NOT NULL,
  `buyerid` varchar(30) NOT NULL,
  `itemid` varchar(50) NOT NULL,
  `itemname` varchar(50) NOT NULL,
  `cost` int(5) NOT NULL,
   `quantity` int(5) NOT NULL,
  `status` int(2) NOT NULL
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1; 

