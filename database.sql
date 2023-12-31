
SET FOREIGN_KEY_CHECKS=0;

-- Table structure for user

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UserID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserName` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- Table structure for user_account
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `UserID` int(10) unsigned NOT NULL,
  `UserName` varchar(255) DEFAULT NULL,
  `Gender` char(1) NOT NULL DEFAULT '',
  `Image` longblob,
  `ImageString` varchar(255) DEFAULT '',
  `Status` char(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`UserID`),
  CONSTRAINT `user_account_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `chat_history` (
    `ChatID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `FromUserID` INT(10) UNSIGNED NOT NULL,
    `ToUserID` INT(10) UNSIGNED NOT NULL,
    `Message` TEXT NOT NULL COLLATE 'utf8_general_ci',
    `Timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ChatID`) USING BTREE,
    INDEX `fk_from_user` (`FromUserID`) USING BTREE,
    INDEX `fk_to_user` (`ToUserID`) USING BTREE,
    CONSTRAINT `fk_to_user` FOREIGN KEY (`ToUserID`) REFERENCES `user_account` (`UserID`) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT `fk_from_user` FOREIGN KEY (`FromUserID`) REFERENCES `user_account` (`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=0;

