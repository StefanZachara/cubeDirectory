create database card_directory character set utf8 collate utf8_polish_ci;

GRANT ALL PRIVILEGES ON card_directory.* TO card@localhost IDENTIFIED BY 'card';
GRANT ALL PRIVILEGES ON card_directory.* TO card@"%" IDENTIFIED BY 'card';

flush privileges;
