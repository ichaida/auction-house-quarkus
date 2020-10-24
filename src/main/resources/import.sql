INSERT INTO AuctionHouse(id, name) VALUES (1, 'UKAH');
INSERT INTO Auction(id, name, startTime, endTime, status, startingPrice, currentPrice, auctionHouse_id) VALUES (nextval('hibernate_sequence'), 'Racer', make_timestamp(2020, 11, 13, 8, 05, 0), make_timestamp(2020, 11, 29, 12, 10, 0), 'NOT_STARTED', 46.75, 46.75, 1);
INSERT INTO Auction(id, name, startTime, endTime, status, startingPrice, currentPrice, auctionHouse_id) VALUES (nextval('hibernate_sequence'), 'Nugat', make_timestamp(2020, 10, 25, 8, 15, 0), make_timestamp(2020, 10, 29, 10, 10, 0), 'RUNNING', 398.5, 506.5, 1);
INSERT INTO Auction(id, name, startTime, endTime, status, startingPrice, currentPrice, auctionHouse_id) VALUES (nextval('hibernate_sequence'), 'Malaga', make_timestamp(2020, 9, 16, 8, 0, 0), make_timestamp(2020, 10, 1, 05, 00, 0), 'TERMINATED', 103.5, 470.5, 1);

INSERT INTO AuctionHouse(id, name) VALUES (2, 'Chorus');
INSERT INTO Auction(id, name, startTime, endTime, status, startingPrice, currentPrice, auctionHouse_id) VALUES (nextval('hibernate_sequence'), 'Kifu', make_timestamp(2020, 10, 20, 8, 15, 0), make_timestamp(2020, 11, 30, 10, 10, 0), 'RUNNING', 73.5, 155.5, 2);
INSERT INTO Auction(id, name, startTime, endTime, status, startingPrice, currentPrice, auctionHouse_id) VALUES (nextval('hibernate_sequence'), 'SmT', make_timestamp(2020, 10, 25, 8, 15, 0), make_timestamp(2020, 10, 29, 10, 10, 0), 'DELETED', 10.5, 20.0, 2);
INSERT INTO Auction(id, name, startTime, endTime, status, startingPrice, currentPrice, auctionHouse_id) VALUES (nextval('hibernate_sequence'), 'Breeze', make_timestamp(2020, 10, 25, 8, 15, 0), make_timestamp(2020, 10, 29, 10, 10, 0), 'RUNNING', 89.5, 99.5, 2);


INSERT INTO AuctionHouse(id, name) VALUES (3, 'Mia');
INSERT INTO Auction(id, name, startTime, endTime, status, startingPrice, currentPrice, auctionHouse_id) VALUES (nextval('hibernate_sequence'), 'Loriat', make_timestamp(2020, 10, 25, 8, 15, 0), make_timestamp(2020, 10, 29, 10, 10, 0), 'RUNNING', 103.5, 206.5, 3);
INSERT INTO Auction(id, name, startTime, endTime, status, startingPrice, currentPrice, auctionHouse_id) VALUES (nextval('hibernate_sequence'), 'Witcher', make_timestamp(2020, 8, 1, 8, 15, 0), make_timestamp(2020, 9, 30, 10, 10, 0), 'TERMINATED', 309.5, 206.5, 3);
INSERT INTO Auction(id, name, startTime, endTime, status, startingPrice, currentPrice, auctionHouse_id) VALUES (nextval('hibernate_sequence'), 'Hue', make_timestamp(2020, 12, 5, 7, 0, 0), make_timestamp(2021, 1, 10, 10, 10, 0), 'NOT_STARTED', 59.5, 59.5, 3);
