/* Resets the movie, franchise and character table as well as restarting their respective identifier to count from 1. :*/
TRUNCATE TABLE movie, franchise, character;

ALTER SEQUENCE character_char_id_seq RESTART WITH 1;
ALTER SEQUENCE movie_movie_id_seq RESTART WITH 1;
ALTER SEQUENCE franchise_franchise_id_seq RESTART WITH 1;

/* Inserting records into movie table with movies belonging to either the Marvel Cinematic Universe (MCU) or the DC extended Universe (DCEU). :*/

INSERT INTO movie (title, genre, release_year, director, image_url, trailer_url)
VALUES (
           'Avengers',
           'Superhero Action',
           2012,
           'Joss Whedon',
           'https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FThe_Avengers_%25282012_film%2529&psig=AOvVaw3qJKYV_t8FwfnLGV4p51-L&ust=1693397080785000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCMiK2O3pgYEDFQAAAAAdAAAAABAE',
           'https://www.youtube.com/watch?v=eOrNdBpGMv8'
       );

INSERT INTO movie (title, genre, release_year, director, image_url, trailer_url)
VALUES (
           'Avengers 2 ',
           'Superhero Action',
           2015,
           'Joss Whedon',
           'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.imdb.com%2Ftitle%2Ftt2395427%2F&psig=AOvVaw20Fb5X8ToRV7B627XC07TB&ust=1693397234594000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCPCsg7fqgYEDFQAAAAAdAAAAABAF',
           'https://www.youtube.com/watch?v=tmeOjFno6Do'
       );

INSERT INTO movie (title, genre, release_year, director, image_url, trailer_url)
VALUES (
           'Avengers Endgame',
           'Superhero Action',
           2019,
           'Russo brothers',
           'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.disney.dk%2Ffilm%2Favengers-infinity-war&psig=AOvVaw3ZaNna7GdyMd7XU5hb6K5k&ust=1693397287848000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCIjO0dDqgYEDFQAAAAAdAAAAABAE',
           'https://www.youtube.com/watch?v=6ZfuNTqbHE8'
       );

INSERT INTO movie (title, genre, release_year, director, image_url, trailer_url)
VALUES (
           'Justice League',
           'Superhero Action',
           2017,
           'Zack Snyder',
           'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.imdb.com%2Ftitle%2Ftt0974015%2F&psig=AOvVaw0jJ7ocnmHwsOsuVFcq7ReB&ust=1693397372863000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCLDS7PjqgYEDFQAAAAAdAAAAABAE',
           'https://www.youtube.com/watch?v=3cxixDgHUYw'
       );

/* Inserting MCU & DCEU movie franchise records into the franchise table. :*/

INSERT INTO franchise (name, description) VAlUES ('MCU', 'This is all the marvel movies and series. Much pow wow.');

INSERT INTO franchise (name, description) VAlUES ('DCEU', 'This is all the DC movies. Much sadness.');

/* Inserting character records from the MCU or DCEU movie franchises into the character table.
*  Some records are purposely missing values that other records don't in order to test the behaviour of different kind of records.
:*/

INSERT INTO character (char_name, alias, gender, image_url) VAlUES ('Captain America', 'Steve Rogers', 'MALE','');

INSERT INTO character (char_name, alias, gender, image_url) VAlUES ('Iron Man', 'Tony Stark', 'MALE', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.temashop.dk%2Firon-man-new-dlx-costume&psig=AOvVaw1xjNQjWjaIobGY0NF4D6ZI&ust=1693398208963000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCPCzw4fugYEDFQAAAAAdAAAAABAE');

INSERT INTO character (char_name, alias, gender, image_url) VAlUES ('Captain Marvel', 'Carol Danvers', 'FEMALE', '');

INSERT INTO character (char_name, alias, gender, image_url) VAlUES ('Superman', 'Clark Kent', 'MALE', '');