-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema watchpartydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `watchpartydb` ;

-- -----------------------------------------------------
-- Schema watchpartydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `watchpartydb` DEFAULT CHARACTER SET utf8 ;
USE `watchpartydb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `venue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `venue` ;

CREATE TABLE IF NOT EXISTS `venue` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NULL,
  `address_id` INT NOT NULL,
  `website_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_location_address_idx` (`address_id` ASC),
  CONSTRAINT `fk_location_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NULL,
  `first_name` VARCHAR(45) NULL,
  `photo_url` VARCHAR(2000) NULL,
  `role` VARCHAR(45) NULL,
  `enabled` TINYINT NOT NULL,
  `address_id` INT NOT NULL,
  `create_date` DATE NULL,
  `update_date` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `team` ;

CREATE TABLE IF NOT EXISTS `team` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `logo_url` VARCHAR(2000) NULL,
  `team_website_url` VARCHAR(2000) NULL,
  `sport` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `party`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `party` ;

CREATE TABLE IF NOT EXISTS `party` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `party_date` DATE NULL,
  `start_time` TIME NULL,
  `description` TEXT NULL,
  `completed` TINYINT NULL,
  `enabled` TINYINT NULL,
  `venue_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `create_date` DATE NULL,
  `update_date` DATE NULL,
  `team_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_party_location1_idx` (`venue_id` ASC),
  INDEX `fk_party_user1_idx` (`user_id` ASC),
  INDEX `fk_party_team1_idx` (`team_id` ASC),
  CONSTRAINT `fk_party_location1`
    FOREIGN KEY (`venue_id`)
    REFERENCES `venue` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_party_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_party_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `party_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `party_comment` ;

CREATE TABLE IF NOT EXISTS `party_comment` (
  `id` INT NOT NULL,
  `comment` TEXT NULL,
  `photo_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NULL,
  `create_date` DATE NULL,
  `update_date` DATE NULL,
  `party_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `in_reply_to_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_party_comment_party1_idx` (`party_id` ASC),
  INDEX `fk_party_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_party_comment_party_comment1_idx` (`in_reply_to_id` ASC),
  CONSTRAINT `fk_party_comment_party1`
    FOREIGN KEY (`party_id`)
    REFERENCES `party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_party_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_party_comment_party_comment1`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `party_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_comment` ;

CREATE TABLE IF NOT EXISTS `user_comment` (
  `id` INT NOT NULL,
  `comment` TEXT NULL,
  `photo_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NULL,
  `create_date` DATE NULL,
  `update_date` DATE NULL,
  `user_id` INT NOT NULL,
  `in_reply_to_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_user_comment_user_comment1_idx` (`in_reply_to_id` ASC),
  CONSTRAINT `fk_user_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_comment_user_comment1`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `user_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `direct_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `direct_message` ;

CREATE TABLE IF NOT EXISTS `direct_message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `create_date` DATE NULL,
  `content` TEXT NULL,
  `sender_id` INT NOT NULL,
  `recipient_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_direct_message_user1_idx` (`sender_id` ASC),
  INDEX `fk_direct_message_user2_idx` (`recipient_id` ASC),
  CONSTRAINT `fk_direct_message_user1`
    FOREIGN KEY (`sender_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_direct_message_user2`
    FOREIGN KEY (`recipient_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `friend_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `friend_status` ;

CREATE TABLE IF NOT EXISTS `friend_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `friend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `friend` ;

CREATE TABLE IF NOT EXISTS `friend` (
  `user_id` INT NOT NULL,
  `friend_id` INT NOT NULL,
  `friend_status_id` INT NOT NULL,
  INDEX `fk_friend_user1_idx` (`user_id` ASC),
  INDEX `fk_friend_user2_idx` (`friend_id` ASC),
  PRIMARY KEY (`user_id`, `friend_id`),
  INDEX `fk_friend_friend_status1_idx` (`friend_status_id` ASC),
  CONSTRAINT `fk_friend_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_friend_user2`
    FOREIGN KEY (`friend_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_friend_friend_status1`
    FOREIGN KEY (`friend_status_id`)
    REFERENCES `friend_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `party_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `party_rating` ;

CREATE TABLE IF NOT EXISTS `party_rating` (
  `rating` INT NULL,
  `comment` VARCHAR(280) NULL,
  `party_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `create_date` DATE NULL,
  `last_update` DATE NULL,
  INDEX `fk_party_rating_party1_idx` (`party_id` ASC),
  INDEX `fk_party_rating_user1_idx` (`user_id` ASC),
  PRIMARY KEY (`party_id`, `user_id`),
  CONSTRAINT `fk_party_rating_party1`
    FOREIGN KEY (`party_id`)
    REFERENCES `party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_party_rating_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `venue_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `venue_rating` ;

CREATE TABLE IF NOT EXISTS `venue_rating` (
  `rating` INT NULL,
  `comment` VARCHAR(280) NULL,
  `venue_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `create_date` DATE NULL,
  `update_date` DATE NULL,
  INDEX `fk_location_rating_location1_idx` (`venue_id` ASC),
  INDEX `fk_location_rating_user1_idx` (`user_id` ASC),
  PRIMARY KEY (`venue_id`, `user_id`),
  CONSTRAINT `fk_location_rating_location1`
    FOREIGN KEY (`venue_id`)
    REFERENCES `venue` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_rating_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_team` ;

CREATE TABLE IF NOT EXISTS `user_has_team` (
  `user_id` INT NOT NULL,
  `team_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `team_id`),
  INDEX `fk_user_has_team_team1_idx` (`team_id` ASC),
  INDEX `fk_user_has_team_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_team_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_team_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `party_goers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `party_goers` ;

CREATE TABLE IF NOT EXISTS `party_goers` (
  `party_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`party_id`, `user_id`),
  INDEX `fk_party_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_party_has_user_party1_idx` (`party_id` ASC),
  CONSTRAINT `fk_party_has_user_party1`
    FOREIGN KEY (`party_id`)
    REFERENCES `party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_party_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS sportsfan@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'sportsfan'@'localhost' IDENTIFIED BY 'sportsfan';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'sportsfan'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (1, '533 Old Point Ave', 'Hampton', 'Va', '23663', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (2, '1938 E Pembroke Ave', 'Hampton', 'Va', '23663', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `venue`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `venue` (`id`, `name`, `phone`, `description`, `image_url`, `enabled`, `address_id`, `website_url`) VALUES (1, 'Josh\'s Bar & Grill', '(757) 723-8003', 'Low-key local bar with traditional frub such as burgers & sandwhiches, with pool, darts, & TV sports.', 'https://assets.simpleviewinc.com/simpleview/image/upload/c_fill,h_600,q_80,w_876/v1/crm/virginia/25623_1229792352620_1463438435_30494261_5821657_n_1fb801f3-5056-a36a-073c3ef09df7289d.jpg', 1, 2, 'https://www.facebook.com/profile.php?id=100063541541147');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (1, 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'admin@admin.com', 'James', 'https://fanapeel.com/wp-content/uploads/logo_-university-of-nebraska-cornhuskers-herbie-husker.png', 'admin', 1, 1, '2023-10-24', '2023-10-24');

COMMIT;


-- -----------------------------------------------------
-- Data for table `team`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport`) VALUES (1, 'Nebraska CornHuskers', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg/987px-Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg.png', 'https://huskers.com/sports/football', 'College Football');

COMMIT;


-- -----------------------------------------------------
-- Data for table `party`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `party` (`id`, `title`, `party_date`, `start_time`, `description`, `completed`, `enabled`, `venue_id`, `user_id`, `create_date`, `update_date`, `team_id`) VALUES (1, 'Huskers vs Northwestern', '2023-10-21', '1330', 'Nebraska seeks to avenge last seasons opening game loss in Ireland, and get back to back Big Ten wins ', 1, 1, 1, 1, '2023-10-01', '2023-10-01', 1);
INSERT INTO `party` (`id`, `title`, `party_date`, `start_time`, `description`, `completed`, `enabled`, `venue_id`, `user_id`, `create_date`, `update_date`, `team_id`) VALUES (2, 'Huskers vs Purdue', '2023-10-28', '1330', 'Nebraska goes for 3 strait Big 10 wins and tries to get one win closer to that all important 6 win mark on the season come watch your Huskers take on Purdue Pete\'s Boilermakers ', 0, 1, 1, 1, '2023-10-24', '2023-10-24', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `venue_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `venue_rating` (`rating`, `comment`, `venue_id`, `user_id`, `create_date`, `update_date`) VALUES (5, 'One of my favorite places to go, best burgers in town and $2 TACOS on Tuesday!!!!', 1, 1, '2023-10-01', '2023-10-01');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_team`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `user_has_team` (`user_id`, `team_id`) VALUES (1, 1);

COMMIT;

