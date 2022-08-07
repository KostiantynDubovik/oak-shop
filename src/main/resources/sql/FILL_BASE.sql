INSERT INTO shop.hibernate_sequence (next_val) VALUES (1);

INSERT INTO shop.stores (STORE_ID, STORE_NAME) VALUES (-1, 'root');
INSERT INTO shop.stores (STORE_ID, STORE_NAME) VALUES (-2, 'alcatraz');
INSERT INTO shop.stores (STORE_ID, STORE_NAME) VALUES (-3, 'midnight');

INSERT INTO shop.languages (LANGUAGE_ID, LANGUAGE, COUNTRY, LOCALE) VALUES (-1, 'uk', 'UA', 'uk_UA');
INSERT INTO shop.languages (LANGUAGE_ID, LANGUAGE, COUNTRY, LOCALE) VALUES (-2, 'en', 'US', 'en_US');
INSERT INTO shop.languages (LANGUAGE_ID, LANGUAGE, COUNTRY, LOCALE) VALUES (-3, 'ru', 'RU', 'ru_RU');

INSERT INTO shop.store_languages (STORE_ID, LANGUAGE_ID) VALUES (-1, -1);
INSERT INTO shop.store_languages (STORE_ID, LANGUAGE_ID) VALUES (-1, -2);
INSERT INTO shop.store_languages (STORE_ID, LANGUAGE_ID) VALUES (-1, -3);
INSERT INTO shop.store_languages (STORE_ID, LANGUAGE_ID) VALUES (-2, -1);
INSERT INTO shop.store_languages (STORE_ID, LANGUAGE_ID) VALUES (-2, -2);
INSERT INTO shop.store_languages (STORE_ID, LANGUAGE_ID) VALUES (-2, -3);
INSERT INTO shop.store_languages (STORE_ID, LANGUAGE_ID) VALUES (-3, -1);
INSERT INTO shop.store_languages (STORE_ID, LANGUAGE_ID) VALUES (-3, -2);
INSERT INTO shop.store_languages (STORE_ID, LANGUAGE_ID) VALUES (-3, -3);

INSERT INTO shop.privileges (PRIVILEGE_ID, PRIVILEGE_NAME) VALUES (-1, 'STORE_READ');
INSERT INTO shop.privileges (PRIVILEGE_ID, PRIVILEGE_NAME) VALUES (-2, 'STORE_WRITE');
INSERT INTO shop.privileges (PRIVILEGE_ID, PRIVILEGE_NAME) VALUES (-3, 'APP_WRITE');

INSERT INTO shop.roles (ROLE_ID, ROLE_NAME) VALUES (-4, 'APP_ADMIN');
INSERT INTO shop.roles (ROLE_ID, ROLE_NAME) VALUES (-5, 'STORE_ADMIN');
INSERT INTO shop.roles (ROLE_ID, ROLE_NAME) VALUES (-6, 'USER');

INSERT INTO shop.roles_privileges (ROLE_ID, PRIVILEGE_ID) VALUES (-4, -1);
INSERT INTO shop.roles_privileges (ROLE_ID, PRIVILEGE_ID) VALUES (-4, -2);
INSERT INTO shop.roles_privileges (ROLE_ID, PRIVILEGE_ID) VALUES (-4, -3);
INSERT INTO shop.roles_privileges (ROLE_ID, PRIVILEGE_ID) VALUES (-5, -1);
INSERT INTO shop.roles_privileges (ROLE_ID, PRIVILEGE_ID) VALUES (-5, -2);
INSERT INTO shop.roles_privileges (ROLE_ID, PRIVILEGE_ID) VALUES (-6, -1);

INSERT INTO shop.users (USER_ID, STEAM_ID, BALANCE, STEAM_NICKNAME, STEAM_AVATAR_URL, STORE_ID, IS_ACTIVE) VALUES (-1, '76561198107293144', 999999999.00, '1BarracudA', 'https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/34/34a3382e56a9ca233c8e9123ff04e2633d2dd751.jpg', -1, true);
INSERT INTO shop.users (USER_ID, STEAM_ID, BALANCE, STEAM_NICKNAME, STEAM_AVATAR_URL, STORE_ID, IS_ACTIVE) VALUES (-2, '76561198121789747', 999999999.00, '1 из Легенд', 'https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/34/09645ed527d97552b7c48f15361fb123339e0b0b.jpg', -1, true);

INSERT INTO shop.users_roles (USER_ID, ROLE_ID) VALUES (-1, -4);
INSERT INTO shop.users_roles (USER_ID, ROLE_ID) VALUES (-2, -4);

INSERT INTO shop.items (ITEM_ID, ITEM_NAME, BUYABLE, IMAGE_URL, DELETABLE, STORE_ID) VALUES (-1, 'Belt', true, 'https://static.wikia.nocookie.net/dayz_gamepedia/images/5/5e/CivilianBelt.png/revision/latest/scale-to-width-down/300?cb=20200908173558', true, -2);
INSERT INTO shop.items (ITEM_ID, ITEM_NAME, BUYABLE, IMAGE_URL, DELETABLE, STORE_ID) VALUES (-2, 'Nylon Knife Sheath', true, 'https://static.wikia.nocookie.net/dayz_gamepedia/images/6/61/Sheath.png/revision/latest/scale-to-width-down/300?cb=20200908173200', true, -2);
INSERT INTO shop.items (ITEM_ID, ITEM_NAME, BUYABLE, IMAGE_URL, DELETABLE, STORE_ID) VALUES (-3, 'Holster', true, 'https://static.wikia.nocookie.net/dayz_gamepedia/images/4/41/GunHolster.png/revision/latest/scale-to-width-down/224?cb=20150226092659', true, -2);
INSERT INTO shop.items (ITEM_ID, ITEM_NAME, BUYABLE, IMAGE_URL, DELETABLE, STORE_ID) VALUES (-4, 'Canteen', true, 'https://static.wikia.nocookie.net/dayz_gamepedia/images/5/5e/Canteen.png/revision/latest/scale-to-width-down/163?cb=20180610230549', true, -2);