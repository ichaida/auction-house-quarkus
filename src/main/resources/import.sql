INSERT INTO auction_house(id, name) VALUES (1, 'UKAH');
INSERT INTO auctions(id, name, start_time, end_time, status, starting_price, current_price, house_id) VALUES (nextval('hibernate_sequence'), 'Racer', make_timestamp(2020, 11, 13, 8, 05, 0), make_timestamp(2020, 11, 29, 12, 10, 0), 'NOT_STARTED', 46.75, 46.75, 1);
INSERT INTO auctions(id, name, start_time, end_time, status, starting_price, current_price, house_id) VALUES (nextval('hibernate_sequence'), 'Nugat', make_timestamp(2020, 10, 25, 8, 15, 0), make_timestamp(2021, 10, 29, 10, 10, 0), 'RUNNING', 398.5, 506.5, 1);
INSERT INTO auctions(id, name, start_time, end_time, status, starting_price, current_price, house_id) VALUES (nextval('hibernate_sequence'), 'Malaga', make_timestamp(2020, 9, 16, 8, 0, 0), make_timestamp(2020, 10, 1, 05, 00, 0), 'TERMINATED', 103.5, 470.5, 1);

INSERT INTO auction_house(id, name) VALUES (2, 'Chorus');
INSERT INTO auctions(id, name, start_time, end_time, status, starting_price, current_price, house_id) VALUES (nextval('hibernate_sequence'), 'Kifu', make_timestamp(2020, 10, 20, 8, 15, 0), make_timestamp(2020, 11, 30, 10, 10, 0), 'RUNNING', 73.5, 155.5, 2);
INSERT INTO auctions(id, name, start_time, end_time, status, starting_price, current_price, house_id) VALUES (nextval('hibernate_sequence'), 'SmT', make_timestamp(2020, 10, 25, 8, 15, 0), make_timestamp(2020, 10, 29, 10, 10, 0), 'DELETED', 10.5, 20.0, 2);
INSERT INTO auctions(id, name, start_time, end_time, status, starting_price, current_price, house_id) VALUES (nextval('hibernate_sequence'), 'Breeze', make_timestamp(2020, 10, 25, 8, 15, 0), make_timestamp(2020, 10, 29, 10, 10, 0), 'RUNNING', 89.5, 99.5, 2);


INSERT INTO auction_house(id, name) VALUES (3, 'Mia');
INSERT INTO auctions(id, name, start_time, end_time, status, starting_price, current_price, house_id) VALUES (nextval('hibernate_sequence'), 'Loriat', make_timestamp(2020, 10, 25, 8, 15, 0), make_timestamp(2020, 10, 29, 10, 10, 0), 'RUNNING', 103.5, 206.5, 3);
INSERT INTO auctions(id, name, start_time, end_time, status, starting_price, current_price, house_id) VALUES (nextval('hibernate_sequence'), 'Witcher', make_timestamp(2020, 8, 1, 8, 15, 0), make_timestamp(2020, 9, 30, 10, 10, 0), 'TERMINATED', 309.5, 206.5, 3);
INSERT INTO auctions(id, name, start_time, end_time, status, starting_price, current_price, house_id) VALUES (nextval('hibernate_sequence'), 'Hue', make_timestamp(2020, 12, 5, 7, 0, 0), make_timestamp(2021, 1, 10, 10, 10, 0), 'NOT_STARTED', 59.5, 59.5, 3);

INSERT INTO users(id, first_name, last_name, username) VALUES (1, 'Ismail', 'Chaida', 'ichaida');
INSERT INTO users(id, first_name, last_name, username) VALUES (2, 'John', 'Doe', 'jdoe');

INSERT INTO bids(id, bid_amount, auction_id, user_id) VALUES (1, 470.5, 3, 1);
INSERT INTO bids(id, bid_amount, auction_id, user_id) VALUES (2, 445, 3, 2);
