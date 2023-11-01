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
-- Table `sport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sport` ;

CREATE TABLE IF NOT EXISTS `sport` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `team` ;

CREATE TABLE IF NOT EXISTS `team` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `logo_url` VARCHAR(2000) NULL,
  `team_website_url` VARCHAR(2000) NULL,
  `sport_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_team_sport1_idx` (`sport_id` ASC),
  CONSTRAINT `fk_team_sport1`
    FOREIGN KEY (`sport_id`)
    REFERENCES `sport` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  `image_url` VARCHAR(2000) NULL,
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
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NULL,
  `photo_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NULL,
  `create_date` DATE NULL,
  `update_date` DATE NULL,
  `party_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `in_reply_to_id` INT NULL,
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
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NULL,
  `photo_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NULL,
  `create_date` DATE NULL,
  `update_date` DATE NULL,
  `user_id` INT NOT NULL,
  `in_reply_to_id` INT NULL,
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
-- Table `favorite_team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `favorite_team` ;

CREATE TABLE IF NOT EXISTS `favorite_team` (
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
-- Table `party_goer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `party_goer` ;

CREATE TABLE IF NOT EXISTS `party_goer` (
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
  PRIMARY KEY (`user_id`, `friend_id`),
  INDEX `fk_user_has_user_user2_idx` (`friend_id` ASC),
  INDEX `fk_user_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_friend_friend_status1_idx` (`friend_status_id` ASC),
  CONSTRAINT `fk_user_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2`
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
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (1, '123 All Star St', 'Sport City', 'USA', '00001', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (2, '1938 E Pembroke Ave', 'Hampton', 'Va', '23663', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (3, '1 Stadium Dr', 'Lincoln', 'Ne', '68588', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (4, '585 South Boulder Rd', 'Louisville', 'Co', '80027', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (5, '9660 E Arapahoe Rd', 'Greenwood Village', 'Co', '80112', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (6, '1247 Bannock St', 'Denver', 'Co', '80204', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (7, '614 W Gray St', 'Houston', 'Tx', '77019', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (8, '200 Inverness Drive W, Suite 100', 'Englewood', 'Co', '80112', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (9, '2588 N Gregg Ave', 'Fayetteville', 'Ar', '72703', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (10, '1434 Blake St', 'Denver', 'Co', '80202', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (11, '7400 E Orchard Rd #1450n', 'Greenwood Village', 'Co', '80111', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (12, '2121 George Halas Dr NW', 'Canton', 'Oh', '44708', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (13, '7400 E Orchard Rd #1450n', 'Centennial', 'Co', '80016', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (14, '7400 E Orchard Rd #1450n', 'Cypress', 'Tx', '77429', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (15, '7400 E Orchard Rd #1450n', 'Denver', 'Co', '80014', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (16, '7400 E Orchard Rd #1450n', 'Phoenix', 'Az', '85226', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (17, '7400 E Orchard Rd #1450n', 'Mountain Home', 'Ar', '72635', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (18, '7400 E Orchard Rd #1450n', 'Bixby', 'Ok', '74008', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (19, '7400 E Orchard Rd #1450n', 'Honolulu', 'Hi', '96795', 1);
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`, `enabled`) VALUES (20, '5600 N 7th St #100', 'Phoenix', 'Az', '85014', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `venue`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `venue` (`id`, `name`, `phone`, `description`, `image_url`, `enabled`, `address_id`, `website_url`) VALUES (1, 'Josh\'s Bar & Grill', '(757) 723-8003', 'Low-key local bar with traditional frub such as burgers & sandwhiches, with pool, darts, & TV sports.', 'https://assets.simpleviewinc.com/simpleview/image/upload/c_fill,h_600,q_80,w_876/v1/crm/virginia/25623_1229792352620_1463438435_30494261_5821657_n_1fb801f3-5056-a36a-073c3ef09df7289d.jpg', 1, 2, 'https://www.facebook.com/profile.php?id=100063541541147');
INSERT INTO `venue` (`id`, `name`, `phone`, `description`, `image_url`, `enabled`, `address_id`, `website_url`) VALUES (2, 'Mudrock\'s Tap & Tavern', '(720) 890-7900', 'Family-owned haunt with wings & other bar food, plus many regional craft beers & TVs showing sports.', 'https://media-cdn.grubhub.com/image/upload/d_search:browse-images:default.jpg/w_300,q_100,fl_lossy,dpr_2.0,c_fit,f_auto,h_300/clkulfsegdmpnkinexho', 1, 4, 'https://mudrockstapandtavern.com/');
INSERT INTO `venue` (`id`, `name`, `phone`, `description`, `image_url`, `enabled`, `address_id`, `website_url`) VALUES (3, 'The Sportsbook Bar & Grill', '(303) 799-1300', 'American pub fare & drinks in an upbeat space with TVs, plus games like shuffleboard, darts & pool.', 'https://media-cdn.grubhub.com/image/upload/d_search:browse-images:default.jpg/w_300,q_100,fl_lossy,dpr_2.0,c_fit,f_auto,h_300/cibf2hsk2zoujgazvluo', 1, 5, 'https://thesbbar.com/the-sportsbook-bar-grill-dtc/');
INSERT INTO `venue` (`id`, `name`, `phone`, `description`, `image_url`, `enabled`, `address_id`, `website_url`) VALUES (4, 'Cap City Tavern', '(720) 931-8888', 'Warm, family-owned locale offering sports on TV, a full bar & an extensive menu of American food.', 'https://vikingsterritory.com/wp-content/uploads/2015/02/cap_city_tavern.jpg', 1, 6, 'http://capcitytavern.com/');
INSERT INTO `venue` (`id`, `name`, `phone`, `description`, `image_url`, `enabled`, `address_id`, `website_url`) VALUES (5, 'PJ\'s Sports Bar', '(713) 520-1748', 'Old-school neighborhood watering hole where the big game is on TV & there\'s karaoke Friday nights.', 'https://pjssportsbar.com/pjlogo1.gif', 1, 7, 'https://pjs-sports-bar.business.site/');
INSERT INTO `venue` (`id`, `name`, `phone`, `description`, `image_url`, `enabled`, `address_id`, `website_url`) VALUES (6, 'The Breckenridge Brewery Ale & Game House', '(970) 453-1550', 'The Breckenridge Brewery Ale & Game House is an indoor/outdoor restaurant and beer garden serving up bites, local beers and unique cocktails.', 'https://upload.wikimedia.org/wikipedia/en/6/6c/Breckenridge_Brewery_logo.png', 1, 8, 'https://www.breckbrewinverness.com/');
INSERT INTO `venue` (`id`, `name`, `phone`, `description`, `image_url`, `enabled`, `address_id`, `website_url`) VALUES (7, 'On the Mark Sports Bar & Grill', '(479) 575-0123', 'Great place to watch your favorite sports, wait staff are very friendly, good service good food great drinks and live music on the outdoor deck', 'https://s3-media0.fl.yelpcdn.com/bphoto/yOh4Hvwc4-hsAzKAXeadhA/348s.jpg', 1, 9, 'https://www.tripadvisor.com/Restaurant_Review-g31589-d4686875-Reviews-On_the_Mark-Fayetteville_Arkansas.html');
INSERT INTO `venue` (`id`, `name`, `phone`, `description`, `image_url`, `enabled`, `address_id`, `website_url`) VALUES (8, 'Society Sports and Spirits', '(720) 517-7303', 'Laid-back sports bar offering draft brews, cocktails & pub fare in a small brick-lined space.', 'https://static.spotapps.co/website_images/ab_websites/136895_website/logo.png', 1, 10, 'https://societydenver.com/');
INSERT INTO `venue` (`id`, `name`, `phone`, `description`, `image_url`, `enabled`, `address_id`, `website_url`) VALUES (9, 'Bevvy Uptown', '(602) 568-0155', 'Restaurant specializing in burgers, tacos, salads, flatbreads & appetizers with happy hour & brunch.', 'https://bevvyaz.com/wp-content/uploads/2020/11/BEVVYLOGOWEBSITE-1_00f000d61_1365@2x_00f000d61_1506.png', 1, 20, 'https://bevvyaz.com/');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (1, 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'admin@admin.com', 'James', 'https://fanapeel.com/wp-content/uploads/logo_-university-of-nebraska-cornhuskers-herbie-husker.png', 'admin', 1, 1, '2023-10-24', '2023-10-24');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (2, 'thejet', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'thejet@unl.edu', 'Johnny', 'https://gpblackhistorymuseum.org/wp-content/uploads/2019/01/Johnny-Jett-Roggers.jpg', 'standard', 1, 3, '2023-10-24', '2023-10-24');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (3, 'thegoat', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'tb@gmail.com', 'Tom', 'https://thewildcattribune.com/13928/sports/tom-brady-the-journey-of-the-undisputed-goat-and-what-the-future-holds-for-the-nfl/#photo', 'standard', 0, 12, '2023-11-01', '2023-11-01');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (4, 'thomas', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'thomas@sd.com', 'Thomas', 'https://media.licdn.com/dms/image/D5603AQHyZXWsNmTbaA/profile-displayphoto-shrink_200_200/0/1698533189263?e=1704326400&v=beta&t=IAH0Ucs9ZXwYAkaTyNlmxfw8AAMsrPg-pfUmrAonTYU', 'standard', 1, 11, '2023-11-01', '2023-11-01');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (5, 'chia', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'chia@sd.com', 'Chiaya', 'https://media.licdn.com/dms/image/D5603AQE-OHkebV82Hg/profile-displayphoto-shrink_200_200/0/1698174349446?e=1704326400&v=beta&t=xQOeVLLVoy2dSH4RHiTde38iIzVHopYYvHCkG9MmiGU', 'standard', 1, 6, '2023-11-01', '2023-11-01');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (6, 'reid', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'reid@sd.com', 'Reid', 'https://media.licdn.com/dms/image/C5603AQGqzZiNM7FsAg/profile-displayphoto-shrink_800_800/0/1516526982038?e=1704326400&v=beta&t=drKSnKL0GdXdGuhp91hm-FmU7u2C-O7WiONlSR6Mw_o', 'standard', 1, 9, '2023-11-01', '2023-11-01');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (7, 'codysaur', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'codysaur@sd.com', 'Codysaur', 'https://media.licdn.com/dms/image/D4D03AQF-Cx4924YK8w/profile-displayphoto-shrink_200_200/0/1670715057542?e=1704326400&v=beta&t=2zIzUMXe_em-R95HnhUh5JAz6ppeuQbh40mawGoxGuo', 'standard', 1, 17, '2023-11-01', '2023-11-01');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (8, 'maggiesaur', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'maggie@sd.com', 'Maggiesaur', 'https://media.licdn.com/dms/image/D5603AQELG8ajzJNK0Q/profile-displayphoto-shrink_200_200/0/1696257719241?e=1704326400&v=beta&t=leENXXIRF_TLz2o5m8LBLg5lVhwtLeI4rvXiPcxsN_A', 'standard', 1, 15, '2023-11-01', '2023-11-01');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (9, 'casey', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'casey@sd.com', 'Casey', 'https://media.licdn.com/dms/image/D4E03AQFyAk-uc5KjaA/profile-displayphoto-shrink_200_200/0/1697047638417?e=1704326400&v=beta&t=2sfoTQPm20jtXXd7nRnA6vBrEUhqJKNnmJ_JotdYDxg', 'standard', 1, 16, '2023-11-01', '2023-11-01');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (10, 'sawyer', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'sawyer@sd.com', 'Sawyer', 'https://files.slack.com/files-tmb/T052X7BAZ-F063VQW8FBN-10c0f13f2b/1000000252_480.png', 'standard', 1, 14, '2023-11-01', '2023-11-01');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (11, 'sdrob', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'rob@sd.com', 'Rob', 'https://media.licdn.com/dms/image/C4E03AQHFQsJazXpfSg/profile-displayphoto-shrink_200_200/0/1517747650500?e=1704326400&v=beta&t=7z_k6dwSiubKBXRehgt9MP69BAdhtgsbYLkHLsPMlZk', 'standard', 1, 11, '2023-11-01', '2023-11-01');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (12, 'sddee', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'dee@sd.com', 'Denise', 'https://media.licdn.com/dms/image/C5603AQFHcdr52IZRfA/profile-displayphoto-shrink_800_800/0/1517663730442?e=1704326400&v=beta&t=k2OYZOkK67grO5pKq9INTNGfwBOJNMsRC6PBE5sJOGw', 'standard', 1, 11, '2023-11-01', '2023-11-01');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (13, 'sdwill', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'will@sd.com', 'Will', 'https://media.licdn.com/dms/image/C5603AQEBJwFVonEv7A/profile-displayphoto-shrink_200_200/0/1647388327774?e=1704326400&v=beta&t=-XfLjgkauU14qXArUN7vSu_sm7YVzAW1SHWbM4Rp9zM', 'standard', 1, 11, '2023-11-01', '2023-11-01');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (14, 'sdjeremy', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'jeremy@sd.com', 'Jeremy', 'https://media.licdn.com/dms/image/C4E03AQGjwpf3Xsprbw/profile-displayphoto-shrink_200_200/0/1532485247024?e=1704326400&v=beta&t=XAIYxE0VfTu74jDHCZU964DkdxkM_lw-dnjJUksgmRQ', 'standard', 1, 11, '2023-11-01', '2023-11-01');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (15, 'sdjohn', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'john@sd.com', 'John', 'https://media.licdn.com/dms/image/D4E35AQHZlkZL6VwU-Q/profile-framedphoto-shrink_200_200/0/1636852604128?e=1699455600&v=beta&t=wybm0UVErtEjkIBSQtfsOyCNQutvP1Zhrpc0XH8gfCI', 'standard', 1, 19, '2023-11-01', '2023-11-01');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (16, 'sdbriana', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'briana@sd.com', 'Briana', 'https://media.licdn.com/dms/image/C4E03AQFznC6GE8KDGg/profile-displayphoto-shrink_200_200/0/1537839742449?e=1704326400&v=beta&t=nQuzTo1EIaS1n63TYwLqQ8ITERfuE3CLPlSqtDfAMjI', 'standard', 1, 11, '2023-11-01', '2023-11-01');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (17, 'sdbruce', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'bruce@sd.com', 'Bruce', 'https://media.licdn.com/dms/image/C4D03AQF_9I_xsCjdTA/profile-displayphoto-shrink_200_200/0/1516238624824?e=1704326400&v=beta&t=e6JFtWvCxcbpUfKMjWZr_lfqZIi7CHju14SI1txeF4U', 'standard', 1, 11, '2023-11-01', '2023-11-01');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `photo_url`, `role`, `enabled`, `address_id`, `create_date`, `update_date`) VALUES (18, 'sdanthony', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'anthony@sd.com', 'Anthony', 'https://media.licdn.com/dms/image/C4E03AQEvtAjjQLPslA/profile-displayphoto-shrink_200_200/0/1660262980722?e=1704326400&v=beta&t=RPKSBln-RxHaA7Fcma6KEfD6py3lwWQ0Eoj7X1siRsU', 'standard', 1, 18, '2023-11-01', '2023-11-01');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sport`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `sport` (`id`, `name`) VALUES (1, 'College Football');
INSERT INTO `sport` (`id`, `name`) VALUES (2, 'Football');
INSERT INTO `sport` (`id`, `name`) VALUES (3, 'College Womens Volleyball');
INSERT INTO `sport` (`id`, `name`) VALUES (4, 'Baseball');
INSERT INTO `sport` (`id`, `name`) VALUES (5, 'College Baseball');
INSERT INTO `sport` (`id`, `name`) VALUES (6, 'Basketball');
INSERT INTO `sport` (`id`, `name`) VALUES (7, 'College Basketball');
INSERT INTO `sport` (`id`, `name`) VALUES (8, 'Nascar');
INSERT INTO `sport` (`id`, `name`) VALUES (9, 'Olympics');
INSERT INTO `sport` (`id`, `name`) VALUES (10, 'Soccer');
INSERT INTO `sport` (`id`, `name`) VALUES (11, 'Womens Basket Ball');
INSERT INTO `sport` (`id`, `name`) VALUES (12, 'Rugby');
INSERT INTO `sport` (`id`, `name`) VALUES (13, 'Golf');
INSERT INTO `sport` (`id`, `name`) VALUES (14, 'Billiards');
INSERT INTO `sport` (`id`, `name`) VALUES (15, 'College Soccer');
INSERT INTO `sport` (`id`, `name`) VALUES (16, 'College Womens Basketball');
INSERT INTO `sport` (`id`, `name`) VALUES (17, 'College Womens Softball');
INSERT INTO `sport` (`id`, `name`) VALUES (18, 'College Womens Soccer');
INSERT INTO `sport` (`id`, `name`) VALUES (19, 'Hockey');
INSERT INTO `sport` (`id`, `name`) VALUES (20, 'Special Events');

COMMIT;


-- -----------------------------------------------------
-- Data for table `team`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (1, 'Nebraska CornHuskers Football', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg/987px-Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg.png', 'https://huskers.com/sports/football', 1);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (2, 'Nebraska CornHuskers Baseball', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg/987px-Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg.png', 'https://huskers.com/sports/baseball', 5);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (3, 'Nebraska CornHuskers Men\'s Basketball', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg/987px-Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg.png', 'https://huskers.com/sports/mens-basketball', 7);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (4, 'Nebraska CornHuskers Volleyball', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg/987px-Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg.png', 'https://huskers.com/sports/volleyball', 3);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (5, 'Nebraska CornHuskers Women\'s Basketball', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg/987px-Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg.png', 'https://huskers.com/sports/womens-basketball', 16);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (6, 'Nebraska CornHuskers Softball', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg/987px-Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg.png', 'https://huskers.com/sports/softball', 17);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (7, 'Nebraska CornHuskers Women\'s Soccer', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg/987px-Nebraska_Cornhuskers_logo%2C_1992%E2%80%932003.svg.png', 'https://huskers.com/sports/soccer', 18);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (8, 'Colorado Buffaloes Football', 'https://upload.wikimedia.org/wikipedia/en/thumb/d/d3/Colorado_Buffaloes_logo.svg/1200px-Colorado_Buffaloes_logo.svg.png', 'https://cubuffs.com/sports/football', 1);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (9, 'Colorado Buffaloes Baseball', 'https://upload.wikimedia.org/wikipedia/en/thumb/d/d3/Colorado_Buffaloes_logo.svg/1200px-Colorado_Buffaloes_logo.svg.png', 'https://cubuffs.com/sports/baseball', 5);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (10, 'Colorado Buffaloes Men\'s Basketball', 'https://upload.wikimedia.org/wikipedia/en/thumb/d/d3/Colorado_Buffaloes_logo.svg/1200px-Colorado_Buffaloes_logo.svg.png', 'https://cubuffs.com/sports/mens-basketball', 7);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (11, 'Colorado Buffaloes Volleyball', 'https://upload.wikimedia.org/wikipedia/en/thumb/d/d3/Colorado_Buffaloes_logo.svg/1200px-Colorado_Buffaloes_logo.svg.png', 'https://cubuffs.com/sports/womens-volleyball', 3);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (12, 'Colorado Buffaloes Women\'s Basketball', 'https://upload.wikimedia.org/wikipedia/en/thumb/d/d3/Colorado_Buffaloes_logo.svg/1200px-Colorado_Buffaloes_logo.svg.png', 'https://cubuffs.com/sports/womens-basketball', 16);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (13, 'Colorado Buffaloes Softball', 'https://upload.wikimedia.org/wikipedia/en/thumb/d/d3/Colorado_Buffaloes_logo.svg/1200px-Colorado_Buffaloes_logo.svg.png', 'https://cubuffs.com/sports/softball', 17);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (14, 'Colorado Buffaloes Women\'s Soccer', 'https://upload.wikimedia.org/wikipedia/en/thumb/d/d3/Colorado_Buffaloes_logo.svg/1200px-Colorado_Buffaloes_logo.svg.png', 'https://cubuffs.com/sports/womens-soccer', 18);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (15, 'Colorado Rockies', 'https://www.mlbstatic.com/team-logos/share/115.jpg', 'https://www.mlb.com/rockies', 4);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (16, 'Colorado Avalanche', 'https://upload.wikimedia.org/wikipedia/en/thumb/4/45/Colorado_Avalanche_logo.svg/1200px-Colorado_Avalanche_logo.svg.png', 'https://www.nhl.com/avalanche/', 19);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (17, 'Colorado Rapids', 'https://1000logos.net/wp-content/uploads/2020/09/Colorado-Rapids-logo.png', 'https://www.coloradorapids.com/', 10);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (18, 'Denver Broncos', 'https://deluxe.scene7.com/is/image/deluxecorp/six-reasons-denver-broncos-logo-design-works_hero?$deluxe_param$&wid=900', 'https://www.denverbroncos.com/', 2);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (19, 'Denver Nuggets', 'https://b.fssta.com/uploads/application/nba/team-logos/Nuggets.png', 'https://www.nba.com/nuggets', 6);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (20, 'Colorado State Rams Football', 'https://brand.colostate.edu/wp-content/uploads/sites/47/2019/01/Rams-Logo-Reversed-one-color-768x769.jpeg', 'https://csurams.com/sports/football', 1);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (21, 'Colorado State Rams Men\'s Basketball', 'https://brand.colostate.edu/wp-content/uploads/sites/47/2019/01/Rams-Logo-Reversed-one-color-768x769.jpeg', 'https://csurams.com/sports/mens-basketball', 7);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (22, 'Colorado State Rams Volleyball', 'https://brand.colostate.edu/wp-content/uploads/sites/47/2019/01/Rams-Logo-Reversed-one-color-768x769.jpeg', 'https://csurams.com/sports/womens-volleyball', 3);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (23, 'Colorado State Rams Women\'s Basketball', 'https://brand.colostate.edu/wp-content/uploads/sites/47/2019/01/Rams-Logo-Reversed-one-color-768x769.jpeg', 'https://csurams.com/sports/womens-basketball', 16);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (24, 'Colorado State Rams Softball', 'https://brand.colostate.edu/wp-content/uploads/sites/47/2019/01/Rams-Logo-Reversed-one-color-768x769.jpeg', 'https://csurams.com/sports/softball', 17);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (25, 'Colorado State Rams Women\'s Soccer', 'https://brand.colostate.edu/wp-content/uploads/sites/47/2019/01/Rams-Logo-Reversed-one-color-768x769.jpeg', 'https://csurams.com/sports/womens-soccer', 18);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (26, 'Baltimore Ravens ', 'https://static.www.nfl.com/t_q-best/league/api/clubs/logos/BAL', 'https://www.baltimoreravens.com/', 2);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (27, 'Seattle Seahawks', 'https://static.www.nfl.com/t_q-best/league/api/clubs/logos/SEA', 'https://www.seahawks.com/', 2);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (28, 'Dallas Mavericks', 'https://www.mavs.com/wp-content/uploads/2019/09/Logo_Release.png', 'https://www.mavs.com/', 6);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (29, 'Skill Distillery', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.switchup.org%2Fbootcamps%2Fskill-distillery&psig=AOvVaw2NRKl4bXz7aYZt7rFQHu7S&ust=1698933554649000&source=images&cd=vfe&opi=89978449&ved=https://skilldistillery.com/', 'https://www.googleadservices.com/pagead/aclk?sa=L&ai=DChcSEwiJ-9PP-6KCAxUBU3IKHd87DgEYABAAGgJxdQ&gclid=CjwKCAjw7oeqBhBwEiwALyHLMzIw44l3ycUlbhe1m7su81ImCjjjQ7YYPK4p8ADHuEiLzuQ44CVWQRoCyasQAvD_BwE&ohost=www.google.com&cid=CAESVuD2pXLuuzYRHhv8sXKc4G5syrTMZmFVW3I6z3c1-cG-eF368cXOBaVjXNZ8FdEUIHTr6FLyI_Mlqctd7BtXhhIumOQkwvGrij3Hhx6vaNfpYK3gJHnw&sig=AOD64_2g3U0mB9OU5woD5yf_11V1PKgXEA&q&adurl&ved=2ahUKEwj1lMvP-6KCAxVrEVkFHWlnBS4Q0Qx6BAgGEAE', 20);
INSERT INTO `team` (`id`, `name`, `logo_url`, `team_website_url`, `sport_id`) VALUES (30, 'Arizona Diamondbacks', 'https://www.mustangsnation.com/wp-content/uploads/2021/07/Arizona-Diamondbacks-Logo-PNG-715x715-1.jpg', 'https://www.mlb.com/dbacks/', 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `party`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `party` (`id`, `title`, `party_date`, `start_time`, `description`, `completed`, `enabled`, `venue_id`, `user_id`, `create_date`, `update_date`, `team_id`, `image_url`) VALUES (1, 'Huskers vs Northwestern', '2023-10-21', '133000', 'Nebraska seeks to avenge last seasons opening game loss in Ireland, and get back to back Big Ten wins ', 1, 1, 1, 1, '2023-10-01', '2023-10-01', 1, 'https://gray-koln-prod.cdn.arcpublishing.com/resizer/4jMoVkE2sfw-0WMJTrVMk53PBI0=/1200x675/smart/filters:quality(85)/cloudfront-us-east-1.images.arcpublishing.com/gray/DBWR2S6HI5BCHAWNJPKAR3XER4.jpg');
INSERT INTO `party` (`id`, `title`, `party_date`, `start_time`, `description`, `completed`, `enabled`, `venue_id`, `user_id`, `create_date`, `update_date`, `team_id`, `image_url`) VALUES (2, 'Huskers vs Purdue', '2023-10-28', '133000', 'Nebraska goes for 3 strait Big 10 wins and tries to get one win closer to that all important 6 win mark on the season come watch your Huskers take on Purdue Pete\'s Boilermakers ', 1, 1, 1, 1, '2023-10-24', '2023-10-24', 1, 'https://gray-koln-prod.cdn.arcpublishing.com/resizer/KXnrPMr7FNT4VSSdtrxoMNCD4N4=/1200x675/smart/filters:quality(85)/cloudfront-us-east-1.images.arcpublishing.com/gray/XF42WISYSNEUVPTBECMIFX3MMM.jpg');
INSERT INTO `party` (`id`, `title`, `party_date`, `start_time`, `description`, `completed`, `enabled`, `venue_id`, `user_id`, `create_date`, `update_date`, `team_id`, `image_url`) VALUES (3, 'Huskers @ Michigan State', '2023-11-04', '120000', 'Nebraska travels to East Lansing trying to reach the post season for the first time in the last 6 years. GBR!', 0, 1, 1, 1, '2023-11-01', '2023-11-01', 1, 'https://www.1011now.com/2023/10/30/huskers-travel-east-lansing-take-michigan-state-spartans-saturday/');
INSERT INTO `party` (`id`, `title`, `party_date`, `start_time`, `description`, `completed`, `enabled`, `venue_id`, `user_id`, `create_date`, `update_date`, `team_id`, `image_url`) VALUES (4, 'Huskers Vs Maryland', '2023-11-11', '120000', 'Nebraska returns home to take on the fighting Terripins', 0, 1, 1, 1, '2023-11-01', '2023-11-01', 1, 'https://gray-koln-prod.cdn.arcpublishing.com/resizer/cGbsczd5k3eSGE_U2Y_I9n7T2dg=/1200x675/smart/filters:quality(85)/cloudfront-us-east-1.images.arcpublishing.com/gray/2QXN23PYJBBSVJJXYOL7T6IOBU.png');
INSERT INTO `party` (`id`, `title`, `party_date`, `start_time`, `description`, `completed`, `enabled`, `venue_id`, `user_id`, `create_date`, `update_date`, `team_id`, `image_url`) VALUES (5, 'Huskers @ Wisconsin', '2023-11-18', '120000', 'Nebraska heads to Camp Randell to take on the Badgers', 0, 1, 1, 1, '2023-11-01', '2023-11-01', 1, 'https://gray-koln-prod.cdn.arcpublishing.com/resizer/Pr1UP9fNrKjNNevm5SSdn5e-b7I=/1200x675/smart/filters:quality(85)/cloudfront-us-east-1.images.arcpublishing.com/gray/IM4FMCK3UBAFZJ5OZPRUMD2SKI.jpg');
INSERT INTO `party` (`id`, `title`, `party_date`, `start_time`, `description`, `completed`, `enabled`, `venue_id`, `user_id`, `create_date`, `update_date`, `team_id`, `image_url`) VALUES (6, 'Huskers Vs Iowa', '2023-11-24', '120000', 'In the FINAL regular season game of the year  the Huskers try to retain posession of the Hero\'s Cup and down the Hawkeyes for a second consecutive year', 0, 1, 1, 1, '2023-11-01', '2023-11-01', 1, 'https://en.wikipedia.org/wiki/File:The_Heroes_Game_logo.svg');
INSERT INTO `party` (`id`, `title`, `party_date`, `start_time`, `description`, `completed`, `enabled`, `venue_id`, `user_id`, `create_date`, `update_date`, `team_id`, `image_url`) VALUES (7, 'Seahawks @ Ravens', '2023-11-05', '130000', 'Seahawks Travel to the east coast to take on the Ravens in Baltimore', 0, 1, 5, 10, '2023-11-01', '2023-11-01', 27, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fseahawkswire.usatoday.com%2F2023%2F11%2F01%2Fseahawks-ravens-tv-map-game-day-info-week-9%2F&psig=AOvVaw3uvaC0vfP4gZtAK5F15BRq&ust=1698947185251000&source=images&cd=vfe&opi=89978449&ved=https://seahawkswire.usatoday.com/wp-content/uploads/sites/61/2023/11/W9_SEA@BAL.png?w=1000&h=600&crop=1');
INSERT INTO `party` (`id`, `title`, `party_date`, `start_time`, `description`, `completed`, `enabled`, `venue_id`, `user_id`, `create_date`, `update_date`, `team_id`, `image_url`) VALUES (8, 'Ravens Vs Seahawks', '2023-11-05', '130000', 'The Ravens are riding high in first place, with perhaps one of the most complete rosters in football. It\'s going to be one hell of a challenge for the Seahawks to take them down at home.  ', 0, 1, 9, 9, '2023-11-01', '2023-11-01', 26, 'https://www.youtube.com/watch/yOFdbfe2aCA');

COMMIT;


-- -----------------------------------------------------
-- Data for table `party_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `party_comment` (`id`, `comment`, `photo_url`, `enabled`, `create_date`, `update_date`, `party_id`, `user_id`, `in_reply_to_id`) VALUES (1, 'Great food and people and we won, woohoo! Who could ask for more???', NULL, 1, '2023-10-24', '2023-10-24', 1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `user_comment` (`id`, `comment`, `photo_url`, `enabled`, `create_date`, `update_date`, `user_id`, `in_reply_to_id`) VALUES (1, 'Hey Johnny was great to see you! GBR!', NULL, 1, '2023-10-24', '2023-10-24', 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `direct_message`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `direct_message` (`id`, `create_date`, `content`, `sender_id`, `recipient_id`) VALUES (1, '2023-10-01', 'Hey James, I\'ll bring the Hardware to the NW Party!!!', 2, 1);
INSERT INTO `direct_message` (`id`, `create_date`, `content`, `sender_id`, `recipient_id`) VALUES (2, '2023-11-01', 'Did you make Codysaur, in the Poke`mon project?', 7, 8);
INSERT INTO `direct_message` (`id`, `create_date`, `content`, `sender_id`, `recipient_id`) VALUES (3, '2023-11-01', 'No! I thought it was James...', 8, 7);
INSERT INTO `direct_message` (`id`, `create_date`, `content`, `sender_id`, `recipient_id`) VALUES (4, '2023-11-01', 'James said it wasn\'t him. He thought I made it!!!', 7, 8);

COMMIT;


-- -----------------------------------------------------
-- Data for table `party_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `party_rating` (`rating`, `comment`, `party_id`, `user_id`, `create_date`, `last_update`) VALUES (5, 'Food was great and Huskers won!!!  GBR!', 1, 1, '2023-10-24', '2023-10-24');

COMMIT;


-- -----------------------------------------------------
-- Data for table `venue_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `venue_rating` (`rating`, `comment`, `venue_id`, `user_id`, `create_date`, `update_date`) VALUES (5, 'One of my favorite places to go, best burgers in town and $2 TACOS on Tuesday!!!!', 1, 1, '2023-10-01', '2023-10-01');

COMMIT;


-- -----------------------------------------------------
-- Data for table `favorite_team`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `favorite_team` (`user_id`, `team_id`) VALUES (1, 1);
INSERT INTO `favorite_team` (`user_id`, `team_id`) VALUES (2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `party_goer`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `party_goer` (`party_id`, `user_id`) VALUES (1, 2);
INSERT INTO `party_goer` (`party_id`, `user_id`) VALUES (2, 2);
INSERT INTO `party_goer` (`party_id`, `user_id`) VALUES (1, 1);
INSERT INTO `party_goer` (`party_id`, `user_id`) VALUES (2, 1);
INSERT INTO `party_goer` (`party_id`, `user_id`) VALUES (3, 1);
INSERT INTO `party_goer` (`party_id`, `user_id`) VALUES (3, 2);
INSERT INTO `party_goer` (`party_id`, `user_id`) VALUES (4, 1);
INSERT INTO `party_goer` (`party_id`, `user_id`) VALUES (4, 2);
INSERT INTO `party_goer` (`party_id`, `user_id`) VALUES (5, 1);
INSERT INTO `party_goer` (`party_id`, `user_id`) VALUES (5, 2);
INSERT INTO `party_goer` (`party_id`, `user_id`) VALUES (6, 1);
INSERT INTO `party_goer` (`party_id`, `user_id`) VALUES (6, 2);
INSERT INTO `party_goer` (`party_id`, `user_id`) VALUES (7, 10);
INSERT INTO `party_goer` (`party_id`, `user_id`) VALUES (8, 9);

COMMIT;


-- -----------------------------------------------------
-- Data for table `friend_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `friend_status` (`id`, `name`) VALUES (1, 'pending');
INSERT INTO `friend_status` (`id`, `name`) VALUES (2, 'accepted');
INSERT INTO `friend_status` (`id`, `name`) VALUES (3, 'declined');

COMMIT;


-- -----------------------------------------------------
-- Data for table `friend`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchpartydb`;
INSERT INTO `friend` (`user_id`, `friend_id`, `friend_status_id`) VALUES (1, 2, 2);

COMMIT;

