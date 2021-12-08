--
-- PostgreSQL database dump
--

-- Dumped from database version 13.4
-- Dumped by pg_dump version 13.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: profile_diet_calculation; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.profile_diet_calculation (id, basic_metabolism, bmi, bmi_result_type, suggested_calories, suggested_carbohydrates, suggested_fats, suggested_proteins) VALUES ('ed93e3c9-2c2d-4f79-a7d6-2b074f344172', 1453.927, 20.61925365062196, 'HEALTHY', 2355.36174, 386.73032624999996, 65.426715, 54.9);


--
-- Data for Name: profile_card; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('64e91bd8-b579-4ac0-9d60-1ba8930eb71a', 'MAN', 'LOW', 22, 'KEEP_WEIGHT', 1.72, 60, 85, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('a9868a66-c2a2-47d3-871e-31d270248f0e', 'MAN', 'LOW', 22, 'KEEP_WEIGHT', 1.72, 60, 78, NULL);

INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('e5f678f0-38b3-4f31-b532-ee10048d6ac0',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('276194ee-9347-4027-8904-6e3a556ddde9',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('342b424f-869b-4706-b449-bc316edc28d7',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('a26cd6af-f59e-4086-bba3-ae1ac0be078b',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('167262d7-2c9a-4965-a26b-5337994a69ec',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('2e7d4f83-6963-4f01-9d4d-ce5c66886fd2',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('46209978-c1a1-482c-9ee8-71102807f5f3',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('8543e4b6-0e47-452d-a194-ab3a388b739e',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('6318aba5-4ca8-41f2-9a35-8019ec3c9f7a',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('9e9cbf35-bbc3-4df3-9805-2fe3d0f06a27',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('aa4e2211-f962-41b5-95bd-61fa32196ab4',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('151e93c8-3808-4f18-b759-b9a25ec3ccf4',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('92233451-a0d5-4ae5-bb0f-e0b4f077095c',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('ba2c3aff-a5d8-4000-bb62-c0eb4121ae0a',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('2397d23c-a7aa-4299-8e41-010278637bee',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('2cceb846-bbff-4ee0-9b51-bfc7eb91eb8a',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('b26d7ca9-552e-4f77-b4d7-23d570a21fd5',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('534ceba9-2ac8-4ed4-9432-be61a9f3468c',  NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('2c604432-1e97-4bf3-b762-5908d5b49172', 'WOMAN', 'LOW', 22, 'KEEP_WEIGHT', 1.72, 60, 61, 'ed93e3c9-2c2d-4f79-a7d6-2b074f344172');
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('4d601119-2cd6-4887-929c-7daa06227df9', NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height, training_activity_time_per_week, weight, diet_calculations_id) VALUES ('b13476fa-b896-455a-878e-46a50dfdddbc', NULL, NULL, 0, NULL, 0, 0, 0, NULL);


--
-- Data for Name: wb_user; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('0169fd42-1995-4ceb-84ff-6fc304bab9f3', 'a@', '$2a$10$33i2x/aPDlEm/ptrUFkYyugXMv0TrJAjw90UpS3vXBlLSqTACyj0a', NULL);
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('e14681f6-2af5-4f3d-810a-9bb1fb884ad4', 'c@', '$2a$10$C7JGP2omNqPLEJMHZUhzn.5rdRmal.2EOGveBozdc4Sm2ycWaYL5q', NULL);
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('0f5849bf-0947-4f8f-96fe-dd1f3d216254', 'd@', '$2a$10$snIJuIoNt5yYfSYMP5il2eJuZVXjTtqDzmCdZSvoAFlJZM9eno4wm', NULL);
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('1d4a32c8-b08c-496d-95eb-82614c6cf618', 'b@@@', '$2a$10$iGcMx6lyK70guGYYe4yRwOAJ.aoaIQN8C3Fti/ZM/j4LiI04mN2.y', NULL);
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('ff84295f-5c04-49ad-9976-7b737133fc80', 'maja@', '$2a$10$3KKwwON02oqDFmPAu.Ixku5YCmD507KZd22FcVSOz.gXA.KsPeOd2', '2021-12-07 10:38:27.763');
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('77a13c0f-21e6-45ce-8c4b-8d795626c920', 'kajtek@', '$2a$10$IJDlgwKRRlKETeF9d/igTeEbQj0i896zz3G4fjQ73S5vDwbLJC5y6', '2021-11-26 01:50:26.45');
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('667562d7-cdd2-4ba8-80d5-ec3de30824bf', 'kacper@', '$2a$10$x3/GxqUv8RHazaubONVGmum2aO4BACHFJCguHgm0ZGpjlsN1t4J6a', '2021-12-07 02:43:13.602');
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('72af8979-4261-4d2f-8258-1e03de343b16', 'x@', '$2a$10$qugxJGxUE/LJzhVib.hyBur/yDwRBvJYyfJVHWYUtj6yAPJF1LLK6', '2021-11-23 10:30:56.149');
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('56d02039-3902-482e-b25b-58b01ce430ce', 'kaja@', '$2a$10$jvtdalw8kM0KtN7RatEkUujN7Wx5MZQAP2ETSdo/BNE8LtMMZyoki', '2021-12-07 03:36:09.343');
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('04efc2d0-96d9-4e1f-ae10-395c1947e7b4', 'jan@', '$2a$10$CPXA5N2Oj7CpRcTu8jn1XOAn70QYuEbK5pN0ZwwG5VRgNgKOJ55U.', '2021-11-25 23:23:43.225');
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('1fba890a-d714-41c2-9bae-81d5648c0da3', 'ccc@', '$2a$10$Pgb7RY/2TJU.ap5MqWMA2uV6qweWPzGj5k3zitlgHfF8.evGjyFVO', NULL);
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('b3723ffb-8f29-4b25-9a3e-1cd846fcbe9b', 'marcin@', '$2a$10$AwnkHkNLh4xSYUVzQ5NfM.XI1YALFe98u0WC.vfjmc8GAN6Q9Y2AO', '2021-12-07 10:41:12.366');
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('a1aeb823-0672-452b-a1ac-8dc06b74b095', 'kamil@', '$2a$10$kUjNib1smFbvwkli.9kg5ulEqMe8ET9alf711J8UBUS7m2E7yRUtO', '2021-11-29 16:29:51.309');
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('60784494-bdfb-41b6-bf64-605762d4250b', 'kinga@', '$2a$10$q/9W/VExGyTlDEh7pSac7O0H4/NPc.k3F2FaVBdKXOuGPpkGoigky', '2021-12-05 23:48:50.259');
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('c8922775-9fec-4955-bbc9-4dea4de29701', 'aaaaaa@', '$2a$10$EY/HC6kOJpF7Y3MtNrViN.6aoeccl0wOXcB7UA/FzNdOkCAM.njfS', NULL);
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('2bd04c24-4eec-4d6d-9bc6-490b3d79ec3a', 'admin@', '$2a$10$xteqgE3bayDDv86rxUodhOiIAw.Hq/puNGL/V33uI0obKbwSm7W5m', '2021-12-07 10:39:38.821');
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('fd13fb01-3bf6-46d3-a420-5d6605d9b6b1', 'b@', '$2a$10$lzc5lnxuEYWzugIBHMoo/O6K2QkSSFhbME0jIqkuol9kSU5SDak26', '2021-11-23 10:29:01.134');
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('2b79bda3-85bd-4992-9cdf-e9f508325aee', 'klaudia@', '$2a$10$orp9YehaOu/CcLOG8f14/.7vXpukAWqMJ8ePNcOfAJEc9k4HC8BKm', '2021-12-08 01:04:44.881');
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('a112b0cc-27f1-47c4-8ebc-8aad8d26f65b', 'albert@', '$2a$10$PGvA16TNjbrvui5LauahruVWsJ63FC.UTvBRvRdukMfwLJa1oxEki', '2021-12-07 01:41:02.324');
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('d4415db0-9504-4fec-a3f3-5c6eb368add0', 'konrad@', '$2a$10$R87xF5gbVCWyKH1hF3Om9exCBNBqB3fOsbtgxSiFBCid/8mmm2K9m', '2021-12-07 03:58:32.073');
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('6c208bfb-ccd9-4b6e-9b63-e47548cc4af9', 'konrad@pwr.com', '$2a$10$R87xF5gbVCWyKH1hF3Om9exCBNBqB3fOsbtgxSiFBCid/8mmm2K9m', '2021-12-06 03:58:32.073');
INSERT INTO public.wb_user (id, email, password, last_request_time) VALUES ('0aa2a88c-4c3c-4a7f-a2f7-763b3fb30681', 'janusz@pwr.com', '$2a$10$R87xF5gbVCWyKH1hF3Om9exCBNBqB3fOsbtgxSiFBCid/8mmm2K9m', '2021-12-06 03:58:32.073');


--
-- Data for Name: profile; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES ('MAN', '2021-11-11 00:00:00', NULL, 'VEGETARIAN', 'PILATES', 'Konrad', 'Lisiura', '2021-11-14-01-12-42-161_doggo.jpg', '6c208bfb-ccd9-4b6e-9b63-e47548cc4af9', '64e91bd8-b579-4ac0-9d60-1ba8930eb71a', '2021-12-07 03:58:32.073');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES ('MAN', '2021-11-11 00:00:00', NULL, 'VEGETARIAN', 'PILATES', 'Janusz', 'Kowalski', '2021-11-14-01-12-42-161_doggo.jpg', '0aa2a88c-4c3c-4a7f-a2f7-763b3fb30681', 'a9868a66-c2a2-47d3-871e-31d270248f0e', '2021-12-07 03:58:32.073');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0169fd42-1995-4ceb-84ff-6fc304bab9f3', 'e5f678f0-38b3-4f31-b532-ee10048d6ac0', NULL);
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES ('MAN', '2021-12-07 00:00:00', NULL, 'NONE', 'PILATES', 'Marcin', 'M', NULL, 'b3723ffb-8f29-4b25-9a3e-1cd846fcbe9b', 'b13476fa-b896-455a-878e-46a50dfdddbc', '2021-12-07 10:41:12.366');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES ('MAN', '2021-11-12 00:00:00', NULL, NULL, NULL, 'Kacper', 'Dubiel', NULL, 'e14681f6-2af5-4f3d-810a-9bb1fb884ad4', '342b424f-869b-4706-b449-bc316edc28d7', NULL);
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0f5849bf-0947-4f8f-96fe-dd1f3d216254', 'a26cd6af-f59e-4086-bba3-ae1ac0be078b', NULL);
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES (NULL, NULL, NULL, 'NONE', 'NONE', NULL, NULL, NULL, '72af8979-4261-4d2f-8258-1e03de343b16', 'aa4e2211-f962-41b5-95bd-61fa32196ab4', '2021-11-23 10:30:56.149');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES ('WOMAN', '2021-12-02 00:00:00', 'Hej
To ja Kaja', 'NONE', 'CARDIO', 'Kaja', 'Kajak', '2021-12-07-03-35-34-575_marchewki.jfif', '56d02039-3902-482e-b25b-58b01ce430ce', '4d601119-2cd6-4887-929c-7daa06227df9', '2021-12-07 03:36:09.343');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1d4a32c8-b08c-496d-95eb-82614c6cf618', '46209978-c1a1-482c-9ee8-71102807f5f3', NULL);
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES ('MAN', '2021-11-11 00:00:00', NULL, 'VEGETARIAN', 'PILATES', 'Konrad', 'Liuras', '2021-11-14-01-12-42-161_doggo.jpg', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', '276194ee-9347-4027-8904-6e3a556ddde9', '2021-12-07 03:58:32.073');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES (NULL, NULL, NULL, 'NONE', 'NONE', NULL, NULL, NULL, '1fba890a-d714-41c2-9bae-81d5648c0da3', '151e93c8-3808-4f18-b759-b9a25ec3ccf4', NULL);
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES ('WOMAN', '1999-02-11 00:00:00', 'Hej, 
sporo gotuję, chętnie podzielę się swoimi przepisami :)
', 'GLUTEN_FREE', 'NONE', 'Klaudia', 'Laskowska2', '2021-12-07-10-29-24-768_dogoo3.jpg', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '2c604432-1e97-4bf3-b762-5908d5b49172', '2021-12-08 01:04:44.881');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES (NULL, NULL, NULL, 'NONE', 'NONE', NULL, NULL, NULL, 'c8922775-9fec-4955-bbc9-4dea4de29701', '92233451-a0d5-4ae5-bb0f-e0b4f077095c', NULL);
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES ('WOMAN', '2021-11-25 00:00:00', NULL, 'NONE', 'NONE', 'Kinga', 'Meduza', NULL, '60784494-bdfb-41b6-bf64-605762d4250b', '2cceb846-bbff-4ee0-9b51-bfc7eb91eb8a', '2021-12-05 23:48:50.259');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES ('MAN', '2021-11-23 00:00:00', 'Cześć, to ja, Kacper', 'VEGAN', 'WEIGHT_TRAINING', 'Kacper', 'Dubiel', '2021-11-30-03-32-03-259_dogoo3.jpg', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', '8543e4b6-0e47-452d-a194-ab3a388b739e', '2021-12-07 02:43:13.602');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES ('MAN', '2021-11-25 00:00:00', NULL, 'NONE', 'NONE', 'Albert', 'Koper', NULL, 'a112b0cc-27f1-47c4-8ebc-8aad8d26f65b', '2397d23c-a7aa-4299-8e41-010278637bee', '2021-12-07 01:41:02.324');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES ('MAN', '2021-11-26 00:00:00', NULL, 'NONE', 'NONE', 'Kajetan', 'Kajtek', NULL, '77a13c0f-21e6-45ce-8c4b-8d795626c920', '534ceba9-2ac8-4ed4-9432-be61a9f3468c', '2021-11-26 01:50:26.45');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES ('MAN', '2021-11-25 00:00:00', NULL, 'NONE', 'NONE', 'Jan', 'Jankowski', NULL, '04efc2d0-96d9-4e1f-ae10-395c1947e7b4', 'ba2c3aff-a5d8-4000-bb62-c0eb4121ae0a', '2021-11-25 23:23:43.225');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'fd13fb01-3bf6-46d3-a420-5d6605d9b6b1', '9e9cbf35-bbc3-4df3-9805-2fe3d0f06a27', '2021-11-23 10:29:01.134');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES ('WOMAN', '2021-11-14 00:00:00', NULL, NULL, NULL, 'Maja', 'Jureńczyk', '2021-11-14-01-45-58-613_doggo2.jpg', 'ff84295f-5c04-49ad-9976-7b737133fc80', '2e7d4f83-6963-4f01-9d4d-ce5c66886fd2', '2021-12-07 10:38:27.763');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2bd04c24-4eec-4d6d-9bc6-490b3d79ec3a', '167262d7-2c9a-4965-a26b-5337994a69ec', '2021-12-07 10:39:38.821');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name, profile_img_path, profile_user_id, profile_card_id, last_request_time) VALUES ('MAN', '2021-11-25 00:00:00', NULL, 'NONE', 'NONE', 'Kamil', 'Kreda', NULL, 'a1aeb823-0672-452b-a1ac-8dc06b74b095', 'b26d7ca9-552e-4f77-b4d7-23d570a21fd5', '2021-11-29 16:29:51.309');


--
-- Data for Name: activity_goal; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.activity_goal (activity_goal_id, goal_creation_date, goal_target_date, goal_type, numeric_value, text_value, owner) VALUES (1, '2021-12-06 01:08:38.643', '2021-12-31 01:08:30.83', 'MAINTAIN_WEIGHT', 0, '', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.activity_goal (activity_goal_id, goal_creation_date, goal_target_date, goal_type, numeric_value, text_value, owner) VALUES (2, '2021-12-06 01:11:19.682', '2021-12-31 01:11:14.144', 'LOSE_WEIGHT', 0.8, '', '2b79bda3-85bd-4992-9cdf-e9f508325aee');


--
-- Data for Name: ailment; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.ailment (id, change_in_calories, change_in_proteins_gram_per_kilogram, description, is_low_glycemic_index_recommended, name, type) VALUES ('8b21563c-0876-4f5c-8298-0e3549fda439', 200, 20, 'Rak', true, 'Nowotwór', 'ILLNESS');
INSERT INTO public.ailment (id, change_in_calories, change_in_proteins_gram_per_kilogram, description, is_low_glycemic_index_recommended, name, type) VALUES ('9477a9ad-78fe-4334-b179-6f93a1eaa059', 100, 0, 'od 1 do 3 miesiąca ciąży', false, 'Ciąża - I trymestr', 'PHYSICAL_CONDITION');
INSERT INTO public.ailment (id, change_in_calories, change_in_proteins_gram_per_kilogram, description, is_low_glycemic_index_recommended, name, type) VALUES ('d4c90b37-edca-4878-9b61-9050242e2a51', 200, 0, 'od 3 do 6 miesiąca ciąży', false, 'Ciąża - II trymestr', 'PHYSICAL_CONDITION');
INSERT INTO public.ailment (id, change_in_calories, change_in_proteins_gram_per_kilogram, description, is_low_glycemic_index_recommended, name, type) VALUES ('76e13d3c-d7b3-411f-a620-3435bc49bbe0', 300, 0, 'od 6 do 9 miesiąca ciąży', false, 'Ciąża - III trymestr', 'PHYSICAL_CONDITION');
INSERT INTO public.ailment (id, change_in_calories, change_in_proteins_gram_per_kilogram, description, is_low_glycemic_index_recommended, name, type) VALUES ('7cd6715a-76bc-4929-be1d-ded9f9ce7f51', 0, 0, 'Cukrzyca typu II', true, 'Cukrzyca typu II', 'ILLNESS');
INSERT INTO public.ailment (id, change_in_calories, change_in_proteins_gram_per_kilogram, description, is_low_glycemic_index_recommended, name, type) VALUES ('45848702-83b6-454a-b877-42d5aa609354', 0, 0, 'bez glutenu', false, 'Nietolerancja glutenu', 'ALLERGY');


--
-- Data for Name: dietician_profile; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.dietician_profile (user_id) VALUES ('2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.dietician_profile (user_id) VALUES ('667562d7-cdd2-4ba8-80d5-ec3de30824bf');
INSERT INTO public.dietician_profile (user_id) VALUES ('ff84295f-5c04-49ad-9976-7b737133fc80');


--
-- Data for Name: nutrition_label; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.nutrition_label (nutrition_label_id, active, description, name, creator_user_id) VALUES ('db2da3d0-67c5-4f4d-8e4b-98ee2c487273', true, 'Trudno', 'Dla miłośników marchewek', '2b79bda3-85bd-4992-9cdf-e9f508325aee');


--
-- Data for Name: ailment_nutrition_label; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.ailment_nutrition_label (nutrition_label_id, ailment_id) VALUES ('db2da3d0-67c5-4f4d-8e4b-98ee2c487273', '45848702-83b6-454a-b877-42d5aa609354');


--
-- Data for Name: sport_label; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.sport_label (sport_label_id, name) VALUES (1, 'Legs');
INSERT INTO public.sport_label (sport_label_id, name) VALUES (2, 'Chest');
INSERT INTO public.sport_label (sport_label_id, name) VALUES (3, 'Abs');
INSERT INTO public.sport_label (sport_label_id, name) VALUES (4, 'Shoulders');
INSERT INTO public.sport_label (sport_label_id, name) VALUES (5, 'Arms');
INSERT INTO public.sport_label (sport_label_id, name) VALUES (6, 'Back');
INSERT INTO public.sport_label (sport_label_id, name) VALUES (7, 'Calves');


--
-- Data for Name: ailment_sport_label; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--



--
-- Data for Name: diet; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.diet (id, connected_with_macros, description, name) VALUES ('b6c95aae-814e-4fa5-9d79-8706848cffa9', true, 'Wysokotlłuszczowa', 'Ketogeniczna');
INSERT INTO public.diet (id, connected_with_macros, description, name) VALUES ('23cbf638-db50-45f6-b575-2cad9b59b703', true, 'Niskowęglowodanowa', 'Paleo');
INSERT INTO public.diet (id, connected_with_macros, description, name) VALUES ('957139fb-9892-4e80-9577-5f9ccbb793c2', false, 'Bez mięsa', 'Wegetariańska');
INSERT INTO public.diet (id, connected_with_macros, description, name) VALUES ('1c852c03-835e-4adf-bbc1-045191721074', false, 'Bez produktów pochodzenia zwierzęcego ', 'Wegańska');


--
-- Data for Name: nutrition_plan; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.nutrition_plan (id, generation_date, main, name, owner_id, diet_id, suggested_id) VALUES ('57ddf132-6d3a-4343-abcd-f623d306e031', '2021-12-06 20:00:56.496', false, 'Drugi', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.nutrition_plan (id, generation_date, main, name, owner_id, diet_id, suggested_id) VALUES ('fd02b06f-beff-46f2-928e-adbfe6313be2', '2021-12-06 18:36:24.985', true, 'Mój nowy plan', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee');


--
-- Data for Name: trainer_profile; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.trainer_profile (user_id) VALUES ('667562d7-cdd2-4ba8-80d5-ec3de30824bf');
INSERT INTO public.trainer_profile (user_id) VALUES ('d4415db0-9504-4fec-a3f3-5c6eb368add0');


--
-- Data for Name: training_plan_request; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--



--
-- Data for Name: training_plan; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request) VALUES (2, '2021-11-29 00:00:00', 'Nowy plan', 'SCRATCH', 'ff84295f-5c04-49ad-9976-7b737133fc80', 'ff84295f-5c04-49ad-9976-7b737133fc80', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request) VALUES (3, '2021-12-06 00:00:00', 'Randomly generated plan', 'STARTED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request) VALUES (1, '2021-12-13 00:00:00', 'Nowy plan', 'PLANNED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request) VALUES (4, '2021-12-06 00:00:00', 'Nowy plan', 'PLANNED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL);


--
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (3, '2021-11-12 15:43:24.009', false, false, 'Hejka', '2021-11-12-15-51-13-856_doggo2.jpg', 'e14681f6-2af5-4f3d-810a-9bb1fb884ad4', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (11, '2021-11-14 03:29:11.369', false, false, 'Klaudia1', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (38, '2021-11-14 22:44:23.076', false, false, 'Klaudia 11', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 1, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (39, '2021-11-15 22:47:08.295', false, false, 'Siemka', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 2, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (44, '2021-11-22 02:09:46.311', true, false, 'nowy', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 1, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (45, '2021-11-22 02:10:43.937', true, true, 'udostepniam nowy', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 44, 1, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (46, '2021-11-22 02:11:22.292', true, true, 'udostepniam udostepnienie nowego', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 45, 1, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (47, '2021-11-22 02:26:24.39', true, true, 'udostepniam udostepnienie nowego', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 46, 2, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (48, '2021-11-22 02:27:40.649', true, true, 'udostepniam udostepnienie nowego 2', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 46, 1, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (49, '2021-11-22 02:28:52.032', true, true, 'udostepniam udostepnienie nowego 2', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 48, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (42, '2021-11-22 01:36:10.603', true, true, 'udostępniam posta 39', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (13, '2021-11-14 04:37:52.321', false, false, 'Klaudia3 3 3
', '2021-11-14-20-33-12-141_doggo.jpg', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (50, '2021-11-22 02:48:05.841', true, false, 'Nowy baza', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 13, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (51, '2021-11-22 02:48:17.878', true, false, 'udostepniam udostepnienie nowego 2', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (52, '2021-11-22 02:48:17.88', true, true, 'udostepniam udostepnienie nowego 2', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 51, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (68, '2021-11-22 04:00:49.541', true, true, '2', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 50, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (66, '2021-11-22 03:53:06.062', true, true, 'aaa', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 50, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (65, '2021-11-22 03:50:38.691', true, true, 'b', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 50, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (55, '2021-11-22 02:55:26.788', true, true, 'udostepniam 50', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 50, 2, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (57, '2021-11-22 02:55:54.635', true, true, 'udostepniam 56', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 50, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (56, '2021-11-22 02:55:40.674', true, true, 'udostepniam 55', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 50, 1, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (59, '2021-11-22 02:58:43.988', true, true, 'udostepniam 58', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 50, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (61, '2021-11-22 03:28:12.527', true, false, 'Nowy', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 2, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (63, '2021-11-22 03:28:56.663', true, true, 'udostepniam 61', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 61, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (62, '2021-11-22 03:28:46.559', true, true, 'udostepniam 61', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 61, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (70, '2021-11-22 20:26:33.706', true, true, 'a', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 50, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (71, '2021-11-22 20:30:34.299', false, false, 'Nowy pościk', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 11, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (76, '2021-11-22 22:20:50.214', true, true, 'a', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 71, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (75, '2021-11-22 22:20:31.567', true, true, 'aaabbb', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 71, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (74, '2021-11-22 22:20:24.234', true, true, 'aaa', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 71, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (72, '2021-11-22 20:31:40.542', true, true, 'l', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 71, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (79, '2021-11-22 22:32:33.06', true, true, 'ab', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 71, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (78, '2021-11-22 22:32:24.485', true, true, 'a', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 71, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (81, '2021-11-22 22:35:40.823', true, true, 'bbb', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (84, '2021-11-22 22:36:49.196', true, true, 'c', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 38, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (83, '2021-11-22 22:36:42.531', true, true, 'b', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (82, '2021-11-22 22:36:34.55', true, true, 'a', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 71, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (85, '2021-11-22 22:53:14.473', true, true, 'aaaa', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 71, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (91, '2021-11-24 03:09:54.449', false, false, 'Nowy post 1', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (90, '2021-11-24 03:05:55.319', false, false, 'Nowy post', '2021-11-24-03-05-55-360_corgi-puppies.jpg', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 1, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (96, '2021-11-30 03:00:02.476', true, false, 'Hej
', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 1, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (97, '2021-11-30 03:00:10.818', true, true, 'Aaa', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 96, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (1, '2021-11-09 09:08:42.799', false, false, 'Konrad0', '2021-11-09-09-15-12-805_doggo2.jpg', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (5, '2021-11-12 16:33:11.78', false, false, 'Klaudia-1', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (89, '2021-11-24 03:00:22.996', true, false, 'Przepis na pizzę:

500 kcal- 45g B, 14g T, 47g W

Składniki:
• jajko
• 125g twarogu chudego
• 45g mąki
• sól
• 2 łyżki koncentratu pomidorowego
• dodatki wedle uznania (u mnie kukurydza, pieczarki i cukinia)
• 30g mozarelli do posypania

Wykonanie:
1. Twaróg zagniatamy widelcem z mąką, jajkiem i solą.
2. Wykładamy na papier do pieczenia i za pomocą łyżki formujemy dość cienki placek.
3. Wstawiamy do piekarnika na 10-15 minut, 180 stopni.
4. Wyjmujemy z piekarnika i przekłuwamy widelcem bąble z powietrza powstałe na środku placka.
5. Rozsmarowujemy przecier pomidorowy i dodajemy dodatki.
6. Posypujemy mozarella i wkładamy do piekarnika na kolejne 10 minut, 180 stopni.
Smacznego 🍕😋', '2021-11-24-03-03-42-231_pizza-pepperoni.jpeg', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 1, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (94, '2021-11-24 03:10:55.964', true, true, 'Udostępnienie posta', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 89, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (87, '2021-11-23 02:54:56.982', false, false, 'To ja Kacper', NULL, '667562d7-cdd2-4ba8-80d5-ec3de30824bf', NULL, 3, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (92, '2021-11-24 03:09:58.352', false, false, 'Nowy post 2', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 2, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (100, '2021-11-30 03:28:28.442', false, false, 'Cześć, to ja Maja', NULL, 'ff84295f-5c04-49ad-9976-7b737133fc80', NULL, 1, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (2, '2021-11-09 09:15:22.4', true, false, 'Nowy post', NULL, 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (17, '2021-11-14 19:47:54.469', false, false, 'Konrad3', NULL, 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (30, '2021-11-14 22:06:13.79', true, false, 'Klaudia17', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (27, '2021-11-14 21:59:43.497', true, false, 'Klaudia14', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (24, '2021-11-14 20:55:33.292', false, false, 'Klaudia5', '2021-11-14-20-56-14-013_doggo.jpg', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (7, '2021-11-14 01:56:47.5', true, false, 'Mój nowy pościk z dzisiaj', NULL, 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (26, '2021-11-14 21:00:31.823', true, false, 'Klaudia13', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (20, '2021-11-14 20:33:50.469', true, false, 'Moje myśli dwie', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (4, '2021-11-12 15:51:25.531', false, false, 'Drugi post z fotą', '2021-11-12-15-51-41-797_doggo.jpg', 'e14681f6-2af5-4f3d-810a-9bb1fb884ad4', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (25, '2021-11-14 20:59:01.487', true, false, 'Klaudia12', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (19, '2021-11-14 19:48:03.457', false, false, 'Konrad5', NULL, 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (16, '2021-11-14 19:46:10.536', false, false, 'Konrad 2', NULL, 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (21, '2021-11-14 20:43:17.551', true, false, 'Klaudia8', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (15, '2021-11-14 19:46:07.032', false, false, 'Konrad 1', NULL, 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (12, '2021-11-14 03:29:12.296', false, false, 'Klaudia2', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (14, '2021-11-14 04:37:57.464', true, false, 'Klauida7
', '2021-11-14-16-48-25-821_doggo.jpg', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (29, '2021-11-14 22:06:09.536', true, false, 'Klaudia16', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (8, '2021-11-14 03:29:07.595', true, false, 'Klaudia1', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (23, '2021-11-14 20:45:47.745', true, false, 'Klaudia10', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (10, '2021-11-14 03:29:10.38', true, false, 'Klaudia3', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (41, '2021-11-20 03:14:51.452', true, false, 'Nowy', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (9, '2021-11-14 03:29:08.878', true, false, 'Klaudia2', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (33, '2021-11-14 22:15:08.439', true, false, 'Klaudia 20', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (28, '2021-11-14 22:00:39.947', false, false, 'Klaudia6', '2021-11-14-22-05-21-594_doggo2.jpg', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (40, '2021-11-19 17:38:48.929', true, false, 'jakaś treśćxxxx', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (36, '2021-11-14 22:37:28.783', true, false, 'Klaudia 9', '2021-11-15-22-46-58-091_doggo.jpg', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (22, '2021-11-14 20:44:42.697', false, false, 'Klaudia4', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (18, '2021-11-14 19:47:57.561', false, false, 'Konrad4', NULL, 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (35, '2021-11-14 22:26:13.543', true, false, 'Klaudia lat 8', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (32, '2021-11-14 22:15:01.323', true, false, 'Klaudia 19', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (37, '2021-11-14 22:44:16.109', false, false, 'Klaudia 10', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (34, '2021-11-14 22:24:20.131', true, false, 'Klaudia 7', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (31, '2021-11-14 22:06:22.731', true, false, 'Klaudia 18', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (43, '2021-11-22 01:37:44.785', true, true, 'udostępniam posta 42-39', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 42, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (53, '2021-11-22 02:52:45.906', true, false, 'udostepniam udostepnienie nowego 2', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (54, '2021-11-22 02:52:45.913', true, true, 'udostepniam udostepnienie nowego 2', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 53, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (86, '2021-11-23 01:01:47.884', true, false, 'aaa
bbb', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (88, '2021-11-23 09:00:58.817', false, false, 'Nowy
Post
:))))))', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (60, '2021-11-22 02:58:55.408', true, true, 'udostepniam 59', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 50, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (69, '2021-11-22 16:56:56.314', true, true, 'Nowy', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 50, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (67, '2021-11-22 03:53:12.404', true, true, 'aaaz', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 50, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (64, '2021-11-22 03:49:39.906', true, true, 'a', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 50, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (58, '2021-11-22 02:58:25.812', true, true, 'udostepniam 50', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 50, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (6, '2021-11-12 16:33:20.202', false, false, 'Klaudia0', '2021-11-12-16-33-20-280_doggo2.jpg', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (99, '2021-11-30 03:24:57.072', true, false, 'Przepis na pizzę:

500 kcal- 45g B, 14g T, 47g W

Składniki:
• jajko
• 125g twarogu chudego
• 45g mąki
• sól
• 2 łyżki koncentratu pomidorowego
• dodatki wedle uznania (u mnie kukurydza, pieczarki i cukinia)
• 30g mozarelli do posypania

Wykonanie:
1. Twaróg zagniatamy widelcem z mąką, jajkiem i solą.
2. Wykładamy na papier do pieczenia i za pomocą łyżki formujemy dość cienki placek.
3. Wstawiamy do piekarnika na 10-15 minut, 180 stopni.
4. Wyjmujemy z piekarnika i przekłuwamy widelcem bąble z powietrza powstałe na środku placka.
5. Rozsmarowujemy przecier pomidorowy i dodajemy dodatki.
6. Posypujemy mozarella i wkładamy do piekarnika na kolejne 10 minut, 180 stopni.
Smacznego 🍕😋', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (98, '2021-11-30 03:21:56.378', true, false, 'Przepis na pizzę:

500 kcal- 45g B, 14g T, 47g W

Składniki:
• jajko
• 125g twarogu chudego
• 45g mąki
• sól
• 2 łyżki koncentratu pomidorowego
• dodatki wedle uznania (u mnie kukurydza, pieczarki i cukinia)
• 30g mozarelli do posypania

Wykonanie:
1. Twaróg zagniatamy widelcem z mąką, jajkiem i solą.
2. Wykładamy na papier do pieczenia i za pomocą łyżki formujemy dość cienki placek.
3. Wstawiamy do piekarnika na 10-15 minut, 180 stopni.
4. Wyjmujemy z piekarnika i przekłuwamy widelcem bąble z powietrza powstałe na środku placka.
5. Rozsmarowujemy przecier pomidorowy i dodajemy dodatki.
6. Posypujemy mozarella i wkładamy do piekarnika na kolejne 10 minut, 180 stopni.
Smacznego 🍕😋', '2021-11-30-03-21-56-402_pizza.jpg', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (73, '2021-11-22 20:31:50.486', true, true, 'lmmm', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 71, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (77, '2021-11-22 22:25:18.437', true, true, 'a', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 71, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (80, '2021-11-22 22:35:33.495', true, true, 'aaaa', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 71, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (95, '2021-11-24 03:11:24.058', false, true, 'Udostępnienie posta :)', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 90, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (101, '2021-11-30 23:42:19.151', true, false, 'Heja', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 1, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (102, '2021-11-30 23:42:28.457', true, true, 'a
b', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 101, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (103, '2021-12-02 22:37:46.957', true, false, 'vvhjbhbj', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (104, '2021-12-06 01:49:38.774', true, true, '', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 92, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (93, '2021-11-24 03:10:04.209', true, false, 'Nowy post 3a', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (117, '2021-12-06 15:48:47.305', true, false, 'bbb', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (116, '2021-12-06 15:48:39.572', true, false, 'aaa',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (115, '2021-12-06 15:48:33.596', true, false, 'zx',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (114, '2021-12-06 15:47:31.738', true, false, 'xxx',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (113, '2021-12-06 15:47:22.865', true, false, 'abc',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (112, '2021-12-06 15:35:09.232', true, false, 'lll',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (111, '2021-12-06 15:34:21.877', true, false, 'nnn',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (110, '2021-12-06 15:33:21.826', true, false, '.',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (109, '2021-12-06 15:32:02.431', true, false, 'Hej',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (108, '2021-12-06 15:27:45.89', true, false, 'Elko',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (107, '2021-12-06 15:21:21.87', true, false, 'Cześć',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (124, '2021-12-06 19:29:51.282', true, true, 'plan treningowy',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, 1);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (120, '2021-12-06 18:38:21.317', true, true, 'udostepniam plan żywieniowy', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, 'fd02b06f-beff-46f2-928e-adbfe6313be2', NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (119, '2021-12-06 17:52:54.93', true, true, 'udostepniam plan treningowy',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, 1);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (105, '2021-12-06 01:51:17.731', true, true, '', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 92, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (121, '2021-12-06 18:53:30.486', true, true, 'aaa', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 87, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (106, '2021-12-06 01:52:28.546', false, true, 'aaa', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 87, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (118, '2021-12-06 15:56:36.952', false, false, 'Cześć wszystkim!', NULL, 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL, 2, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (123, '2021-12-06 18:58:22.911', true, true, 'aaa', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 118, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (122, '2021-12-06 18:53:39.515', true, true, 'aaa', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 118, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (126, '2021-12-06 19:55:15.643', true, true, 'xxx',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, 4);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (141, '2021-12-07 10:32:38.675', false, true, 'Udostępnienie', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 100, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (130, '2021-12-07 01:32:40.387', true, false, '1', NULL, 'a112b0cc-27f1-47c4-8ebc-8aad8d26f65b', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (128, '2021-12-06 20:14:30.926', true, true, 'zywieniowy', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 1, 'fd02b06f-beff-46f2-928e-adbfe6313be2', NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (129, '2021-12-06 21:04:28.68', true, true, 'idk', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 128, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (127, '2021-12-06 19:55:44.722', true, true, 'w',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, 3);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (125, '2021-12-06 19:34:31.179', true, true, 'aktualny plan',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, 3);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (132, '2021-12-07 02:50:03.821', true, false, 'Nowy post',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (131, '2021-12-07 02:43:40.447', true, false, 'Nowy post',  NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (133, '2021-12-07 02:53:43.392', true, true, 'Udostepniam post', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 87, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (134, '2021-12-07 02:54:20.674', true, false, 'Przepis na pizzę:

500 kcal- 45g B, 14g T, 47g W

Składniki:
• jajko
• 125g twarogu chudego
• 45g mąki
• sól
• 2 łyżki koncentratu pomidorowego
• dodatki wedle uznania (u mnie kukurydza, pieczarki i cukinia)
• 30g mozarelli do posypania

Wykonanie:
1. Twaróg zagniatamy widelcem z mąką, jajkiem i solą.
2. Wykładamy na papier do pieczenia i za pomocą łyżki formujemy dość cienki placek.
3. Wstawiamy do piekarnika na 10-15 minut, 180 stopni.
4. Wyjmujemy z piekarnika i przekłuwamy widelcem bąble z powietrza powstałe na środku placka.
5. Rozsmarowujemy przecier pomidorowy i dodajemy dodatki.
6. Posypujemy mozarella i wkładamy do piekarnika na kolejne 10 minut, 180 stopni.
Smacznego 🍕😋', '2021-12-07-02-54-20-818_pizza.jpg', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (135, '2021-12-07 03:36:09.221', false, false, 'Cześć, to ja Kaja', NULL, '56d02039-3902-482e-b25b-58b01ce430ce', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (137, '2021-12-07 03:48:52.2', true, false, 'a', '2021-12-07-03-48-52-362_marchewki.jfif', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (138, '2021-12-07 03:54:49.017', true, true, 'Mój plan treningowy', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, 1);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (136, '2021-12-07 03:47:57.202', true, true, 'Udostepnienie planu żywieniowego', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, 'fd02b06f-beff-46f2-928e-adbfe6313be2', NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (139, '2021-12-07 09:45:33.736', true, true, '', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, 3);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (140, '2021-12-07 10:30:21.474', false, false, 'Przepis na pizzę: nowy

500 kcal- 45g B, 14g T, 47g W

Składniki:
• jajko
• 125g twarogu chudego
• 45g mąki
• sól
• 2 łyżki koncentratu pomidorowego
• dodatki wedle uznania (u mnie kukurydza, pieczarki i cukinia)
• 30g mozarelli do posypania

Wykonanie:
1. Twaróg zagniatamy widelcem z mąką, jajkiem i solą.
2. Wykładamy na papier do pieczenia i za pomocą łyżki formujemy dość cienki placek.
3. Wstawiamy do piekarnika na 10-15 minut, 180 stopni.
4. Wyjmujemy z piekarnika i przekłuwamy widelcem bąble z powietrza powstałe na środku placka.
5. Rozsmarowujemy przecier pomidorowy i dodajemy dodatki.
6. Posypujemy mozarella i wkładamy do piekarnika na kolejne 10 minut, 180 stopni.
Smacznego 🍕😋', 'pizza.jpg', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, NULL);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (142, '2021-12-07 10:35:45.546', false, true, 'Plan treningowy', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, NULL, 3);
INSERT INTO public.post (post_id, added_date, is_deleted, is_sharing, post_content, post_img_path, creator_id, original_post_id, sharing_counter, original_nutrition_plan_id, original_training_plan_id) VALUES (143, '2021-12-07 10:36:15.351', false, true, 'Plan żywieniowy', NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, 0, 'fd02b06f-beff-46f2-928e-adbfe6313be2', NULL);


--
-- Data for Name: comment; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (2, '2021-11-19 16:45:10.972', 'jakaś treść komentarza 2', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (55, '2021-11-30 03:30:20.787', 'O, hejka', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', 100, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (56, '2021-11-30 03:30:25.458', '1', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', 100, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (57, '2021-11-30 03:30:25.801', '2', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', 100, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (58, '2021-11-30 03:30:26.246', '3', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', 100, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (14, '2021-11-20 03:13:15.202', 'To ja Konrad', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', 39, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (15, '2021-11-20 03:20:44.895', 'a', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 38, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (16, '2021-11-20 03:36:29.712', 'Długi komentarz jakiś idk cos pisze pisze pisze', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (19, '2021-11-20 03:41:49.681', 'Cześć Konrad, miłego śpiulkolotu', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (18, '2021-11-20 03:41:21.354', 'Elko', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (17, '2021-11-20 03:40:55.56', 'Nowy', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (13, '2021-11-20 01:40:08.323', 'Hejka', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (12, '2021-11-20 01:13:40.094', 'aaa', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (6, '2021-11-20 01:12:19.342', 'aaa', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (9, '2021-11-20 01:12:35.991', 'ababa', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (8, '2021-11-20 01:12:30.974', 'aaa', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (7, '2021-11-20 01:12:23.61', 'aaa', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (4, '2021-11-19 17:11:58.675', 'komentarz 4 aktualizacja', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (10, '2021-11-20 01:12:43.427', 'aaa', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (20, '2021-11-20 04:05:49.687', '1', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (21, '2021-11-20 04:05:50.591', '2', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (22, '2021-11-20 04:05:50.953', '3', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (23, '2021-11-20 04:05:51.255', '4', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (24, '2021-11-20 04:05:51.58', '5', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (27, '2021-11-20 04:05:52.757', '8', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (25, '2021-11-20 04:05:51.866', '6', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (11, '2021-11-20 01:13:12.416', 'bbb', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (26, '2021-11-20 04:05:52.218', '7', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (59, '2021-11-30 03:30:27.353', '4', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', 100, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (60, '2021-11-30 03:30:39.082', ':)', 'ff84295f-5c04-49ad-9976-7b737133fc80', 100, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (28, '2021-11-21 23:21:23.027', 'Siema siema siema siema co tam hip', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (61, '2021-11-30 23:42:24.458', 'a', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 101, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (62, '2021-12-02 22:37:52.786', 'kkk', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 103, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (29, '2021-11-21 23:33:47.311', 'abbbbb', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (30, '2021-11-22 01:04:07.408', 'Elko', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (31, '2021-11-22 01:04:10.562', 'Elko', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (32, '2021-11-22 03:16:30.641', 'Hej', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 59, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (33, '2021-11-22 03:24:52.479', 'b', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 38, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (34, '2021-11-22 03:24:55.627', 'c', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 38, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (35, '2021-11-22 03:24:56.731', 'd', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 38, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (36, '2021-11-22 03:24:58.027', 'e', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 38, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (37, '2021-11-22 16:47:08.966', 'a', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 68, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (38, '2021-11-22 16:48:19.179', 'b', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 68, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (39, '2021-11-22 19:01:08.608', 'Elko', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 28, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (40, '2021-11-22 22:54:16.757', 'a', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 85, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (41, '2021-11-23 02:55:03.106', ':)', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', 87, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (42, '2021-11-24 03:04:43.876', 'Super, na pewno skorzystam!', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 89, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (43, '2021-11-24 03:06:35.483', 'Ekstra!', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', 89, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (44, '2021-11-24 03:08:09.501', 'Nowy komentarz 1', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 89, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (45, '2021-11-24 03:08:12.249', 'Nowy komentarz 2', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 89, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (46, '2021-11-24 03:08:16.532', 'Nowy komentarz 3', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 89, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (47, '2021-11-24 03:08:22.501', 'Nowy komentarz 4', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 89, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (48, '2021-11-27 02:47:48.511', 'aaaaaaaa', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 95, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (50, '2021-11-27 02:47:50.685', 'v', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 95, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (51, '2021-11-27 02:47:51.162', 'd', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 95, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (52, '2021-11-27 02:47:51.548', 'r', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 95, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (53, '2021-11-27 02:47:52.238', 'e', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 95, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (49, '2021-11-27 02:47:49.889', 'b', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 95, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (54, '2021-11-30 03:00:06.996', ',', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 96, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (63, '2021-12-06 01:49:34.231', 'aaa', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 92, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (64, '2021-12-06 01:51:14.973', 'a', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 92, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (65, '2021-12-06 01:52:18.194', ':)a', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 87, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (74, '2021-12-07 10:32:20.646', 'Nowy komenarz', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 100, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (66, '2021-12-07 02:52:46.28', 'Cześć Kacper!', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 121, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (67, '2021-12-07 02:52:51.88', '1', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 121, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (68, '2021-12-07 02:52:52.887', '2', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 121, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (72, '2021-12-07 02:52:54.225', '6', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 121, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (73, '2021-12-07 02:52:54.534', '7', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 121, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (71, '2021-12-07 02:52:53.821', '5', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 121, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (70, '2021-12-07 02:52:53.499', '4', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 121, true);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted) VALUES (69, '2021-12-07 02:52:53.177', '3', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 121, true);


--
-- Data for Name: conversations; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.conversations (id, connection_type, is_read_by_first_user, is_read_by_second_user, last_message_date, first_profile_profile_user_id, second_profile_profile_user_id) VALUES ('ba147596-35d1-4c94-be11-c07265fdb760', 1, true, false, '2021-11-24 19:28:33.363', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '667562d7-cdd2-4ba8-80d5-ec3de30824bf');
INSERT INTO public.conversations (id, connection_type, is_read_by_first_user, is_read_by_second_user, last_message_date, first_profile_profile_user_id, second_profile_profile_user_id) VALUES ('f9e733cf-3590-4aeb-8749-88468ee3ce61', 0, true, true, NULL, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 'ff84295f-5c04-49ad-9976-7b737133fc80');
INSERT INTO public.conversations (id, connection_type, is_read_by_first_user, is_read_by_second_user, last_message_date, first_profile_profile_user_id, second_profile_profile_user_id) VALUES ('aedff5fd-7c43-4bbe-8b1e-a9621fae2bf8', 0, false, true, '2021-11-30 03:50:57.917', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.conversations (id, connection_type, is_read_by_first_user, is_read_by_second_user, last_message_date, first_profile_profile_user_id, second_profile_profile_user_id) VALUES ('0010d845-9816-48ad-93f1-ec4fefea12b8', 0, true, false, '2021-12-07 02:58:03.57', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 'd4415db0-9504-4fec-a3f3-5c6eb368add0');


--
-- Data for Name: diet_calc_meal_suggestions; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories, diet_calculation_id) VALUES ('c200b9b0-ac38-4335-99e4-f147c3b9c8ef', 'ANY_RECOMMENDED', 'BREAKFAST', 588.840435, 'ed93e3c9-2c2d-4f79-a7d6-2b074f344172');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories, diet_calculation_id) VALUES ('d21388b6-3b9c-4563-aff6-079288760e67', 'ANY_RECOMMENDED', 'LUNCH', 235.536174, 'ed93e3c9-2c2d-4f79-a7d6-2b074f344172');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories, diet_calculation_id) VALUES ('27f0c463-d5b9-4be8-93f3-2bb221faf21b', 'ANY_RECOMMENDED', 'DINNER', 942.144696, 'ed93e3c9-2c2d-4f79-a7d6-2b074f344172');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories, diet_calculation_id) VALUES ('a9153474-ba6c-490f-8e24-cd20272cf248', 'ANY_RECOMMENDED', 'SNACK', 235.536174, 'ed93e3c9-2c2d-4f79-a7d6-2b074f344172');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories, diet_calculation_id) VALUES ('10616a82-5e39-432c-b6d7-b854de6b3ade', 'ANY_RECOMMENDED', 'SUPPER', 353.30426099999994, 'ed93e3c9-2c2d-4f79-a7d6-2b074f344172');


--
-- Data for Name: diet_nutrition_label; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.diet_nutrition_label (nutrition_label_id, diet_id) VALUES ('db2da3d0-67c5-4f4d-8e4b-98ee2c487273', '1c852c03-835e-4adf-bbc1-045191721074');


--
-- Data for Name: dish; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.dish (id, active, created_date, derived_caffeine, derived_calories, derived_carbohydrates, derived_cholesterol, derived_fats, derived_fiber, derived_proteins, derived_salt, derived_saturated_fats, derived_sugar, description, draft, glycemic_index_level, img_dish_path, name, recipe, creator_id) VALUES ('2ea31743-56d8-454a-adba-19b331f7b96d', true, '2021-12-07', 0, 1650, 40, 0, 10, 17.000000000000004, 50, 10.5, 2, 250, 'Bardzo dobre danie', false, 'LOW', '2021-12-06-18-36-07-254_marchewki.jfif', 'Kolorowe marchewki', 'Obierz i zjedz', '2b79bda3-85bd-4992-9cdf-e9f508325aee');


--
-- Data for Name: dish_meal_type; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.dish_meal_type (id, meal_type, dish_id) VALUES ('aa5b3139-5b2d-424e-85c8-28b0faa20b8c', 'DINNER', '2ea31743-56d8-454a-adba-19b331f7b96d');


--
-- Data for Name: dish_nutrition_label; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.dish_nutrition_label (dish_id, nutrition_label_id) VALUES ('2ea31743-56d8-454a-adba-19b331f7b96d', 'db2da3d0-67c5-4f4d-8e4b-98ee2c487273');


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.product (id, caffeine_per_hundred_grams, calories_per_hundred_grams, carbohydrates_per_hundred_grams, cholesterol_per_hundred_grams, fats_per_hundred_grams, fiber_per_hundred_grams, glycemic_index_level, name, proteins_per_hundred_grams, salt_per_hundred_grams, saturated_fats_per_hundred_grams, sugars_per_hundred_grams) VALUES ('f95222ef-770a-4c31-a6a7-c887068516aa', 0, 33, 0.8, 0, 0.2, 0.34, 'VERY_LOW', 'Marchewka', 1, 0.21, 0.04, 5);


--
-- Data for Name: dish_product_detail; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.dish_product_detail (id, amount_of_product, measure_type, derived_caffeine, derived_calories, derived_carbohydrates, derived_cholesterol, derived_fats, derived_fiber, derived_proteins, derived_salt, derived_saturated_fats, derived_sugar, product_id, dish_id) VALUES ('3cfcd5e3-3b82-42ed-8755-0d8b7cd2e431', 5, 'KILO_GRAM', 0, 1650, 40, 0, 10, 17.000000000000004, 50, 10.5, 2, 250, 'f95222ef-770a-4c31-a6a7-c887068516aa', '2ea31743-56d8-454a-adba-19b331f7b96d');


--
-- Data for Name: doctor_profile; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.doctor_profile (user_id) VALUES ('667562d7-cdd2-4ba8-80d5-ec3de30824bf');
INSERT INTO public.doctor_profile (user_id) VALUES ('2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.doctor_profile (user_id) VALUES ('ff84295f-5c04-49ad-9976-7b737133fc80');


--
-- Data for Name: doctor_specialization; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.doctor_specialization (id, name) VALUES ('4ff1b72e-3c04-4f85-b613-112071cffa7b', 'Laryngolog');
INSERT INTO public.doctor_specialization (id, name) VALUES ('c8020cc6-0e4d-426b-8b7d-c2a5f76e55f5', 'Dermatolog');
INSERT INTO public.doctor_specialization (id, name) VALUES ('095c524f-7357-4930-9c3b-c47666c37bb9', 'Kardiolog');


--
-- Data for Name: doctors_specializations; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.doctors_specializations (user_id, specialization_id) VALUES ('667562d7-cdd2-4ba8-80d5-ec3de30824bf', '4ff1b72e-3c04-4f85-b613-112071cffa7b');
INSERT INTO public.doctors_specializations (user_id, specialization_id) VALUES ('2b79bda3-85bd-4992-9cdf-e9f508325aee', '4ff1b72e-3c04-4f85-b613-112071cffa7b');
INSERT INTO public.doctors_specializations (user_id, specialization_id) VALUES ('2b79bda3-85bd-4992-9cdf-e9f508325aee', 'c8020cc6-0e4d-426b-8b7d-c2a5f76e55f5');
INSERT INTO public.doctors_specializations (user_id, specialization_id) VALUES ('ff84295f-5c04-49ad-9976-7b737133fc80', '4ff1b72e-3c04-4f85-b613-112071cffa7b');
INSERT INTO public.doctors_specializations (user_id, specialization_id) VALUES ('ff84295f-5c04-49ad-9976-7b737133fc80', '095c524f-7357-4930-9c3b-c47666c37bb9');


--
-- Data for Name: exercise; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (45, NULL, 'OTHER', '<p>On your back, legs extended straight up, reach toward your toes with your hands and lift your shoulder blades off the ground and back.</p>', false, 0, 'Crunches With Legs Up', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (65, NULL, 'OTHER', '<p>En face</p>
<ol>
<li>Tirer</li>
<li>relacher doucement</li>
</ol>', false, 0, 'Extenseurs - Face', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (66, NULL, 'OTHER', '<p>Attach a rope to a pulley station set at about chest level.</p>
<p>Step back so you''re supporting the weight with arms completely outstretched and assume a staggered (one foot forward) stance. Bend the knees slightly for a stable base.</p>
<p>Retract the scapulae (squeeze your partner''s finger with your shoulder blades) and pull the center of the rope slightly up towards the face. A good cue is to think about pulling the ends of the rope apart, not just pulling back.</p>
<p>As you near your face, externally rotate so your knuckles are facing the ceiling.</p>
<p>Hold for one second at the top position and slowly lower.</p>', false, 0, 'Facepull', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (67, NULL, 'OTHER', '<p>-Laying on the back, lift your straightened legs from the ground at a 45 degree angle. </p>
<p>-As your Left foot travels downward and nearly touches the floor, your Right foot should seek to reach a 90 degree angle, or as close to one as possible.</p>
<p>-Bring your R foot down until it nearly touches the floor, and bring your L foot upwards.  Maintain leg rigidity throughout the exercise.  Your head should stay off the ground, supported by tightened upper abdominals.</p>
<p>-(L up R down, L down R up, x2)  ^v, v^, ^v, v^ = 1 rep</p>
<p>-Primarily works the Rectus Abdominus, the hip flexors and the lower back. Secondarily works the Obliques.  Emphasis placed on the lower quadrant of the abs.</p>
<p> </p>', false, 0, 'Flutter Kicks', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (68, NULL, 'OTHER', '', false, 0, 'Fly With Cable', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (69, NULL, 'OTHER', '<p>Take two dumbbells and lay on a bench, make sure the feet are firmly on the ground and your back is not arched, but has good contact with the bench. The arms are stretched in front of you, about shoulder wide. Bend now the arms a bit and let them down with a half-circle movement to the side. Without changing the angle of the elbow bring them in a fluid movement back up.</p>', false, 0, 'Fly With Dumbbells', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (70, NULL, 'OTHER', '<p>The exercise is the same as with a regular bench:</p>
<p>Take two dumbbells and lay on a bench, make sure the feet are firmly on the ground and your back is not arched, but has good contact with the bench. The arms are stretched in front of you, about shoulder wide. Bend now the arms a bit and let them down with a half-circle movement to the side. Without changing the angle of the elbow bring them in a fluid movement back up.</p>', false, 0, 'Fly With Dumbbells, Decline Bench', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (61, NULL, 'OTHER', '<p>.</p>', false, 6.5, 'Dumbbell Lunges Standing', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (63, NULL, 'OTHER', '', false, 6.5, 'Dumbbells on Scott Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (74, NULL, 'OTHER', '<p>To execute the exercise, the lifter stands with their feet shoulder width apart and weights or resistance handles held by their side with a pronated (overhand) grip.</p>
<p>The movement is to bring the arms up in front of the body to eye level and with only a slight bend in the elbow. This isolates the anterior deltoid muscle (front of the shoulder) and uses the anterior deltoid to lift the weight.</p>
<p>When lifting it is important to keep the body still so the anterior deltoid is fully utilised; if the weight cannot be lifted by standing still then it is too heavy and a lower weight is needed. It is important to keep a slight bend in the elbow when lifting as keeping the elbow locked will add stress to the elbow joint and could cause injury.</p>
<p>A neutral grip, similar to that used in the hammer curl, can also be used. With this variation the weight is again raised to eye level, but out to a 45 degree angle from the front of the body. This may be beneficial for those with shoulder injuries, particularly those related to the rotator cuff.</p>', false, 0, 'Front Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (75, NULL, 'OTHER', '<p>Squats</p>', false, 0, 'Front Squats', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (76, NULL, 'OTHER', '<p>(A) Get in high plank position on your hands and toes.(B) Shift your weight to your left hand as you turn your body to the right; bend your right leg behind you and extend your right arm up. Return to the center and repeat on the opposite side. Continue, alternating sides.<strong>Make it easier:</strong> Don’t raise your arm after you bend your leg behind you.<strong>Make it harder:</strong> Balance with your arm and leg extended for two counts.</p>', false, 0, 'Full Sit Outs', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (77, NULL, 'OTHER', '<p>Lie on you back with your hips and knees flexed, feet on the ground. From this position, raise your butt off of the ground to a height where your body makes a straight line from your knees to your shoulders. To make the exercise more intense, you can add weight by letting a barbell rest on your hips as you complete the motion, or you can put your feet on a slightly higher surface such as a step or a bench.</p>', false, 0, 'Glute Bridge', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (78, NULL, 'OTHER', '', false, 0, 'Good Mornings', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (79, NULL, 'OTHER', '<p>Hold two dumbbells and sit on a bench with a straight back, the shoulders are slightly rolled backwards. Your pals point to your body. Bend the arms and bring the weight up with a fast movement. Don''t rotate your hands, as with the curls. Without any pause bring the dumbbell down with a slow, controlled movement.</p>
<p>Don''t swing your body during the exercise, the biceps should do all the work here. The elbows are at your side and don''t move.</p>', false, 0, 'Hammercurls', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (80, NULL, 'OTHER', '<p>Take a cable in your hands (palms parallel, point to each other), the body is straight. Bend the arms and bring the weight up with a fast movement. Without any pause bring it back down with a slow, controlled movement, but don''t stretch completely your arms.</p>
<p>Don''t swing your body during the exercise, the biceps should do all the work here. The elbows are at your side and don''t move.</p>', false, 0, 'Hammercurls on Cable', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (81, NULL, 'OTHER', '<p>chrome Hand Flex Grip to build up forearms muscles</p>', false, 0, 'Hand Grip', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (82, NULL, 'OTHER', 'The handstand push-up (press-up) - also called the vertical push-up (press-up) or the inverted push-up (press-up) also called commandos- is a type of push-up exercise where the body is positioned in a handstand. For a true handstand, the exercise is performed free-standing, held in the air. To prepare the strength until one has built adequate balance, the feet are often placed against a wall, held by a partner, or secured in some other way from falling. Handstand pushups require significant strength, as well as balance and control if performed free-standing.', false, 0, 'Handstand Pushup', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (83, NULL, 'OTHER', '<p>Hanging from bar or straps, bring legs up with knees extended or flexed</p>', false, 0, 'Hanging Leg Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (84, NULL, 'OTHER', '<p>Grab two cables stand in the middle so both have tension and hold</p>', false, 0, 'Hercules Pillars', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (85, NULL, 'OTHER', '<p>-Start with legs slightly wider than shoulder width</p>
<p>-Drop into a bodyweight squat</p>
<p>-As you hit the bottom of the squat, explode upwards into a jump while simultaneously tucking your knees into your chest midflight.  Remain tucked until the apex of your jump.</p>
<p>-Land on both feet, making sure your knees are not locked so as to avoid excessive strain upon your joints.  Collect yourself into the next rep as quickly but under control as possible.</p>', false, 0, 'High Knee Jumps', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (86, NULL, 'OTHER', '<p>-Start with legs at a comfortable standing width</p>
<p>-Run in place, bringing knees to or above waist level</p>', false, 0, 'High Knees', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (87, NULL, 'OTHER', '<p>Use a light barbell, perform explosive lift up starting from underneath knee cap level. Lift/raise explosively using hips, at shoulder level. Tempo: 2111</p>', false, 0, 'High Pull', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (88, NULL, 'OTHER', '<p>Start with your feet shoulder width apart and arms slightly behind your back.</p>
<p>As you descend towards the floor, raise your heels off the ground, while keeping your back as vertical  as possible. </p>
<p>Upon attaining the bottom position, touch the hands to the heels.</p>
<p>Then stand up ending with the heels on the ground, arms extended in front of the chest then rowing into the start position.</p>', false, 0, 'Hindu Squats', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (89, NULL, 'OTHER', 'Lying down on your back, with your feet flat on the floor. Raise your hips up evenly as high as you can and hold for as long as you can.', false, 0, 'Hip Raise, Lying', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (90, NULL, 'OTHER', '<p>The bar should go directly on your upper thigh, directly below your crotch. Your feet should be directly under your knees. Push your hips up so that you form a straight line from your knees to your shoulders. Use a pad for comfort.</p>', false, 0, 'Hip Thrust', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (91, NULL, 'OTHER', '<p>Get on a mat and lie on your back. Contract your abs, stretch your raise and legs and raise them (your head and shoulders are also be raised). Make sure your lower back remains in contact with the mat.</p>', false, 0, 'Hollow Hold', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (92, NULL, 'OTHER', '<p>Lay on the hyperextension pad with the belly button just at the leading edge, the upper body can hang freely. Tense your whole back''s muscles and bring your upper body up till it is horizontal, but not more. Go slowly down and don''t relax your muscles.</p>', false, 0, 'Hyperextensions', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (93, NULL, 'OTHER', '<p>To do slowly, tempo is 4010</p>', false, 0, 'Incline Bench Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (94, NULL, 'OTHER', '<p>Use inclined bench. Hold dumbbells straight out to your sides, elbows slightly bent. Bring arms together above you, keeping angle of elbows fixed.</p>', false, 0, 'Incline Dumbbell Flye', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (95, NULL, 'OTHER', '<ul>
<li>Bench should be angled anywhere from 30 to 45 degrees</li>
<li>Be sure to press dumbbells straight upward (perpendicular to the floor)</li>
</ul>', false, 0, 'Incline Dumbbell Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (96, NULL, 'OTHER', '<ol>
<li>Using a neutral grip, lean into an incline bench.</li>
<li>Take a dumbbell in each hand with a neutral grip, beginning with the arms straight. This will be your starting position.</li>
<li>Retract the shoulder blades and flex the elbows to row the dumbbells to your side.</li>
<li>Pause at the top of the motion, and then return to the starting position.</li>
</ol>', false, 0, 'Incline Dumbbell Row', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (97, NULL, 'OTHER', '<p>Perform the plank with legs elevated, feet on a gymball. Once stabilised, slowly move one foot sideways off the ball, then make it touch the floor, then come back to starting position. Alternate with the other foot.</p>
<p>This is a core exercise.</p>', false, 0, 'Incline Plank With Alternate Floor Touch', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (98, NULL, 'OTHER', '<p>Regular push with a 30 degree incline.</p>', false, 0, 'Incline Pushups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (99, NULL, 'OTHER', '<p>Assume push-up position, with hands slightly wider than shoulder width.</p>
<p>Shift body weight as far as possible to one side, allowing the elbow on that side to flex. </p>
<p>Reverse the motion, moving completely over to the other side.</p>
<p>Return to the starting position, and repeat for the desired number of repetitions.</p>', false, 0, 'Isometric Wipers', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (100, NULL, 'OTHER', '<p>Get your shoes on, go outside and start running at a moderate pace.</p>', false, 0, 'Jogging', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (101, NULL, 'OTHER', '<p>A jumping jack or star jump, also called side-straddle hop in the US military, is a physical jumping exercise performed by jumping to a position with the legs spread wide and the hands going overhead, sometimes in a clap, and then returning to a position with the feet together and the arms at the sides</p>', false, 0, 'Jumping Jacks', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (102, NULL, 'OTHER', '<p>Hold the kettlebell securely in both hands. Keep your back flat throughout the move, avoiding any rounding of the spine.Keeping your knees "soft", hinge your hips backwards, letting the kettlebell swing between your knees.</p>
<p>You want to bend from the hips as far as you can <em>without letting your back round forwards</em>. Then, snap your hips forwards quickly and standing up straight, locking your body in an upright posture.</p>
<p>The speed you do this will cause your arms and the kettlebell to swing up in front of you. Don''t try to <em>lift</em> the kettlebell with your arms. The snapping forwards of your hips will cause the kettlebell to swing forwards through momentum. Depending on the weight of the kettlebell and the speed of your hip movement, your arms will swing up to about shoulder height. At the top of this swing, let your hips hinge backwards again as the kettlebell swings back down to between your legs and the start of the next repetition.</p>', false, 0, 'Kettlebell Swings', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (103, NULL, 'OTHER', '<p>.</p>', false, 0, 'Lateral Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (104, NULL, 'OTHER', '<p>.</p>', false, 0, 'Lateral Raises on Cable, One Armed', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (105, NULL, 'OTHER', '<p>-(1) Perform a lateral raise, pausing at the top of the lift (2).</p>
<p>-Instead of lowering the weight, bring it to the front of your body so that you appear to be at the top position of a front raise.  You will do this by using a Pec Fly motion, maintaining straight arms. (3)</p>
<p>-Now lower the weight to your quadriceps, or, in other words, lower the dumbbells as though you are completing a Front Raise repetition. (4)</p>
<p>-Reverse the motion:  Perform a front raise (5), at the apex of the lift use a Reverse Fly motion to position the weights at the top of a Lateral Raise (6), and finally, lower the weights until your palms are essentially touching the sides of your thighs (7).  THIS IS ONE REP.</p>
<p>(1) l  <em>front view  </em>(2) -l- <em> FV  </em>  (3) l-  <em>side view</em>   (4) l  <em>SV/FV</em>   (5) l-  <em>SV  </em> (6) -l-  <em>FV  </em>  (7)  l  <em>FV/SV</em></p>', false, 0, 'Lateral-to-Front Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (106, NULL, 'OTHER', '<p>Lean Back, Pull into chest</p>', false, 0, 'Lat Pull Down (Leaning Back)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (107, NULL, 'OTHER', '<p>Pull bar down to strenum and keep straight back.</p>', false, 0, 'Lat Pull Down (Straight Back)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (108, NULL, 'OTHER', 'The leg curl, also known as the hamstring curl, is an isolation exercise that targets the hamstring muscles. The exercise involves flexing the lower leg against resistance towards the buttocks. Other exercises that can be used to strengthen the hamstrings are the glute-ham raise and the deadlift.', false, 0, 'Leg Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (109, NULL, 'OTHER', '<p>Lay on a bench and put your calves behind the leg holder (better if they are hold on around the lower calves). Hold a grip on the bars to make sure the body is firmly in place. Bend your legs bringing the weight up, go slowly back. During the exercise the body should not move, all work is done by the legs.</p>', false, 0, 'Leg Curls (laying)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (110, NULL, 'OTHER', '', false, 0, 'Leg Curls (sitting)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (111, NULL, 'OTHER', '', false, 0, 'Leg Curls (standing)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (112, NULL, 'OTHER', 'The leg extension is a resistance weight training exercise that targets the quadriceps muscle in the legs. The exercise is done using a machine called the Leg Extension Machine. There are various manufacturers of these machines and each one is slightly different. Most gym and weight rooms will have the machine in their facility. The leg extension is an isolated exercise targeting one specific muscle group, the quadriceps. It should not be considered as a total leg workout, such as the squat or deadlift. The exercise consists of bending the leg at the knee and extending the legs, then lowering them back to the original position.', false, 0, 'Leg Extension', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (113, NULL, 'OTHER', 'The leg press is a weight training exercise in which the individual pushes a weight or resistance away from them using their legs.', false, 0, 'Leg Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (114, NULL, 'OTHER', '<p>The exercise is very similar to the wide leg press:</p>
<p>Sit on the machine and put your feet on the platform so far apart  that you could just put another foot in between them. The feet are parallel and point up.</p>
<p>Lower the weight so much, that the knees form a right angle. Push immediately the platform up again, without any pause. When in the lower position, the knees point a bit outwards and the movement should be always fluid.</p>', false, 0, 'Leg Presses (narrow)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (115, NULL, 'OTHER', '<p>Sit on the machine and put your feet on the platform, a bit more than shoulder wide. The feet are turned outwards by a few degrees.</p>
<p>Lower the weight so much, that the knees form a right angle. Push immediately the platform up again, without any pause. When in the lower position, the knees point a bit outwards and the movement should be always fluid.</p>', false, 0, 'Leg Presses (wide)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (116, NULL, 'OTHER', '', false, 0, 'Leg Press on Hackenschmidt Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (117, NULL, 'OTHER', 'The leg raise is a strength training exercise which targets the iliopsoas (the anterior hip flexors). Because the abdominal muscles are used isometrically to stabilize the body during the motion, leg raises are also often used to strengthen the rectus abdominis muscle and the internal and external oblique muscles.', false, 0, 'Leg Raise', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (118, NULL, 'OTHER', '<p>Lay down on a bench and hold onto the recliner with your hands to keep you stable. Hold your legs straight and lift them till they make an angle of about 45°. Make a short pause of 1 sec. and go slowly down to the initial position. To increase the intensity you can make a longer pause of 7 sec. every 5th time.</p>', false, 0, 'Leg Raises, Lying', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (119, NULL, 'OTHER', '<p>Put your forearms on the pads on the leg raise machine, the body is hanging freely. Lift now your legs with a fast movement as high as you can, make a short pause of 1sec at the top, and bring them down again. Make sure that during the exercise your body does not swing, only the legs should move.</p>', false, 0, 'Leg Raises, Standing', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (120, NULL, 'OTHER', '<p>Be sure to adjust seat height so that the handles are towards the bottom of your pectorals.</p>', false, 0, 'Leverage Machine Chest Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (121, NULL, 'OTHER', '<p>Adjust seat height so that the handles are at the bottom of your pectorals or just below.</p>', false, 0, 'Leverage Machine Iso Row', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (122, NULL, 'OTHER', '<p>Hold the L position for as long as possible</p>', false, 0, 'L Hold', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (123, NULL, 'OTHER', '<p>Sit down, put your feet on the supporting points and grab the bar with a wide grip. Pull the weight with a rapid movement towards your belly button, not upper. Keep your arms and elbows during the movement close to your body. Your shoulders are pulled together. Let the weight slowly down till your arms are completely stretched.</p>', false, 0, 'Long-Pulley (low Row)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (124, NULL, 'OTHER', '<p>The exercise is the same as the regular long pulley, but with a narrow grip:</p>
<p>Sit down, put your feet on the supporting points and grab the bar with a wide grip. Pull the weight with a rapid movement towards your belly button, not upper. Keep your arms and elbows during the movement close to your body. Your shoulders are pulled together. Let the weight slowly down till your arms are completely stretched.</p>', false, 0, 'Long-Pulley, Narrow', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (125, NULL, 'OTHER', '<p>Unrack the bar and set your stance wide, beyond your hips.  Push your hips back and sit down to a box that takes you below parallel.  Sit completely down, do not touch and go.  Then explosively stand up.  Stay tight in your upper back and torso throughout the movement.</p>', false, 0, 'Low Box Squat - Wide Stance', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (126, NULL, 'OTHER', '<p>This is an excercise for problems with the levator muscles. Primary  Infraspinatus, secondary Teres Minor.</p>
<p>Lying on side. Keep elbow on waist and in 90 dgr angle. Rotate towards stomach. Add weight as fit.</p>', false, 0, 'Lying Rotator Cuff Exercise', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (127, NULL, 'OTHER', '', false, 0, 'MGM Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (128, NULL, 'OTHER', '<p>On an SZ-bar grip your hands on the outside of each bend and stand with your arms straight down, palms facing your legs. Pull the bar (bending your arms at the elbow) to your chest, and the push the bar above your head (arms as straight as possible). Return the bar to your chest by dropping your arms at the elbows. Return the bar to it''s origional position (stand with your arms straight down, palms facing your legs.)</p>', false, 0, 'Military Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (129, NULL, 'OTHER', '<p>The body is then explosively pulled up by the arms in a radial pull-up, with greater speed than a regular pull-up. When the bar approaches the upper chest, the wrists are swiftly flexed to bring the forearms above the bar. The body is leaned forward, and the elbows are straightened by activating the triceps. The routine is considered complete when the bar is at the level of the waist and the arms are fully straight.</p>
<p>To dismount, the arms are bent at the elbow, and the body is lowered to the floor, and the exercise can be repeated.</p>
<p>As a relatively advanced exercise, muscle-ups are typically first learned with an assistive kip. The legs swing (kip) up and provide momentum to assist in the explosive upward force needed to ascend above the bar. More advanced athletes can perform a strict variation of the muscle-up which is done slowly, without any kip. This variation begins with a still dead hang and uses isometric muscle contraction to ascend above the bar in a slow, controlled fashion.</p>', false, 0, 'Muscle up', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (130, NULL, 'OTHER', '<p>Sit yourself on the decline bench and fix your legs. Cross your arms over the chest and bring with a rolling movement your upper body up, go now without a pause and with a slow movement down again. Don''t let your head move during the exercise.</p>', false, 0, 'Negative Crunches', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (131, NULL, 'OTHER', '<p>Hands at shoulder height, curl arms in toward head, then back out.</p>', false, 0, 'Overhand Cable Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (132, NULL, 'OTHER', '<p>The barbell is held overhead in a wide-arm snatch grip; however, it is also possible to use a closer grip if balance allows.</p>', false, 0, 'Overhead Squat', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (133, NULL, 'OTHER', '<p>Lower the bar to your chest and pause (but do not rest) there for 2 seconds. Press back up. use the same weight you would on bench press, but perform only single reps. Total the number of reps you did in one set of bench press (if you did 3 sets of 8 do 8 sinlge pause bench reps.</p>', false, 0, 'Pause Bench', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (134, NULL, 'OTHER', '<p>Back excercise with a barbell with a starting position which is in a bent over position with the back paralell to the ground. The barbell is on the ground at chest level.For the movement grab the barbell at shoulder width grip and pull towards your chest without losing the bent over position and without moving anything but your arms</p>', false, 0, 'Pendelay Rows', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (135, NULL, 'OTHER', '<p>Push up with perfect push up</p>', false, 0, 'Perfect Push Up', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (136, NULL, 'OTHER', '<p>Push Up performed from a pike position (optional to have feet elevated).</p>', false, 0, 'Pike Push Ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (137, NULL, 'OTHER', '<p>One legged squat</p>', false, 0, 'Pistol Squat', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (138, NULL, 'OTHER', '<p>Get into a position on the floor supporting your weight on your forearms and toes. Arms are bent and directly below the shoulder. </p>
<p>Keep your body straight at all times and hold this position as long as possible. To increase difficulty an arm or leg can be raised while performing this exercise.  </p>', false, 0, 'Plank', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (139, NULL, 'OTHER', '<p>Olympic weight lifting</p>', false, 0, 'Power Clean', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (140, NULL, 'OTHER', '<p> Place the EZ curl bar on the rest handles in front of the preacher bench. Lean over the bench and grab the EZ curl bar with palms up. Sit down on the preacher bench seat so your upper arms rest on top of the pad and your chest is pressed against the pad. Lower the weight until your elbows are extended and arms are straight. Bring the weights back up to the starting point by contracting biceps. Repeat</p>', false, 0, 'Preacher Curls', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (141, NULL, 'OTHER', '<p>Lying on stomach with head on towel.</p>
<p>Stretch arms straight out to your sides.</p>
<p>Slowly lift your arms, pulling your shoulderblades together, hold for 3 seconds.</p>
<p> </p>', false, 0, 'Prone Scapular Retraction - Arms at Side', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (142, NULL, 'OTHER', '<p>Grab the pull up bar with a wide grip, the body is hanging freely. Keep your chest out and pull yourself up till your chin reaches the bar or it touches your neck, if you want to pull behind you. Go with a slow and controlled movement down, always keeping the chest out.</p>', false, 0, 'Pull-ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (143, NULL, 'OTHER', '', false, 0, 'Pull Ups on Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (144, NULL, 'OTHER', '<p>olympic weight lifting</p>', false, 0, 'Push Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (145, NULL, 'OTHER', 'A push-up (or press-up if the hands are wider than shoulders placing more emphasis on the pectoral muscles) is a common calisthenics exercise beginning from the prone position. By raising and lowering the body using the arms, push-ups exercise the pectoral muscles, triceps, and anterior deltoids, with ancillary benefits to the rest of the deltoids, serratus anterior, coracobrachialis and the midsection as a whole. Push-ups are a basic exercise used in civilian athletic training or physical education and commonly in military physical training. They are also a common form of punishment used in the military, school sport, or some martial arts disciplines.', false, 0, 'Pushups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (146, NULL, 'OTHER', '<p>Start with your body streched, your hands are shoulder-wide appart on the ground. Push yourself off the ground till you strech your arms. The back is always straight and as well as the neck (always look to the ground). Lower yourself to the initial position and repeat.</p>', false, 0, 'Push Ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (147, NULL, 'OTHER', '<p>Deadlift to be done using a Smith machine or a free rack. Bar or barbell hould be just right under the knee cap level. Lift using the glutes and through the heels, then come back to starting postion with a control movement of 2 seconds.</p>
<p>This exercise targets mainly the lower back and glutes.</p>', false, 0, 'Rack Deadlift', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (148, NULL, 'OTHER', '<p>Seated on a bench bWith the dumbbells on the floor bend over at 45 Degrees and then slowly raise each dumbbell to shoulder height and hold for a couple seconds before lowering to the starting position. </p>
<p> </p>', false, 0, 'Rear Delt Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (149, NULL, 'OTHER', '<p>Get into pushup position gripping some dumbbells. Perform one pushup, then drive your left elbo up, bringing the dumbell up to your body. Return the dumbell to starting position. </p>
<p>Perform another pushup and then row with the other arm to complete one rep.</p>', false, 0, 'Renegade Row', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (150, NULL, 'OTHER', '<p>Hold bar with reverse (or "overhand") grip, palms facing the floor.</p>', false, 0, 'Reverse Bar Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (151, NULL, 'OTHER', 'The reverse-grip barbell curl is a variation on the biceps curl where the palms face downward. The switch from an underhand to an overhand grip brings the forearm and brachialis muscles more into the exercise.', false, 0, 'Reverse Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (152, NULL, 'OTHER', '<p>Upper chest focuses exercise that also works triceps</p>', false, 0, 'Reverse Grip Bench Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (153, NULL, 'OTHER', '<p>Plank with stomach towards ceiling</p>', false, 0, 'Reverse Plank', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (154, NULL, 'OTHER', '<p>Dips peformed on gymnastic rings.</p>', false, 0, 'Ring Dips', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (155, NULL, 'OTHER', '<p>Crunches on roman chair</p>', false, 0, 'Roman Chair', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (156, NULL, 'OTHER', '<p>DL from top to pos 2: https://www.youtube.com/watch?v=WtWtjViRsKo</p>', false, 0, 'Romanian Deadlift', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (157, NULL, 'OTHER', 'In strength training, rowing (or a row, usually preceded by a qualifying adjective — for instance a seated row) is an exercise where the purpose is to strengthen the muscles that draw the rower''s arms toward the body (latissimus dorsi) as well as those that retract the scapulae (trapezius and rhomboids) and those that support the spine (erector spinae). When done on a rowing machine, rowing also exercises muscles that extend and support the legs (quadriceps and thigh muscles). In all cases, the abdominal and lower back muscles must be used in order to support the body and prevent back injury.', false, 0, 'Row', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (158, NULL, 'OTHER', '', false, 0, 'Rowing, Lying on Bench', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (159, NULL, 'OTHER', '', false, 0, 'Rowing, Seated', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (160, NULL, 'OTHER', '<p>The execution of this exercise is very similar to the regular bent over rowing, only that the bar is fixed here.</p>
<p>Grab the barbell with a wide grip (slightly more than shoulder wide) and lean forward. Your upper body is not quite parallel to the floor, but forms a slight angle. The chest''s out during the whole exercise. Pull now the barbell with a fast movement towards your belly button, not further up. Go slowly down to the initial position. Don''t swing with your body and keep your arms next to your body.</p>', false, 0, 'Rowing, T-bar', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (161, NULL, 'OTHER', '<p>Running or jogging outside in a park, on the tracks,...</p>', false, 0, 'Run', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (162, NULL, 'OTHER', '<p>Run and do some interval trainings such as hill repat, fartlek,..</p>', false, 0, 'Run - Interval Training ', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (163, NULL, 'OTHER', '<p>Run on a treadmill</p>', false, 0, 'Run - Treadmill', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (164, NULL, 'OTHER', '<p>To perform the Russian twist one sits on the floor and bends both knees while feet are be kept together and held slightly above the ground (or put under a stable surface). Ideally, the torso is kept straight with the back kept off the ground at a 45 degree angle with arms held together away from the body in a straight fashion and hands kept locked together like a ball or one can hold a weight to increase the difficulty. Next, the arms should be swung from one side to another in a twisting motion, with each swing to a side counting as one repetition. The slower one moves the arms from side to side, the harder the exercise becomes, working the abdomen that much better. When moving one''s arms during the exercise, it is crucial to not stop between repetitions or else one will lose the effect of working the abdomen. As with all exercise, consistent breathing in and out during the exercise is important as one must ensure proper oxygen flow.</p>', false, 0, 'Russian Twists', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (165, NULL, 'OTHER', '<p>Scissors is an abdominal exercise that strengthens the transverse abdominals, helping flatten your belly and strengthen your entire core. Scissors is not only a core strength move, but it is also a great stretch for your hamstrings and your lower back. Everyone is looking for new ways to work the core, to flatten the belly and to improve flexibility. If you learn how to do Scissors you will get everything rolled together in one move.</p>', false, 0, 'Scissors', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (166, NULL, 'OTHER', '<p>Sit down on a back (better with back support). Take a dumbbell firmly with both hands and hold it with extended arms over your head. With your palms facing upward and holding the weight of the dumbbell, slowly lower the weight behind your head.</p>
<p> </p>', false, 0, 'Seated Triceps Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (187, NULL, 'OTHER', '<p>Stand with your feet at hip width and your shins against the bar. Grasp the bar at double shoulder width and, keeping your lower back flat, drive your heels into the floor to begin lifting the bar. When it''s above your knees, explosively extend your hips and shrug your shoulders. Let the momentum carry the weight overhead.</p>', false, 0, 'Snach', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (167, NULL, 'OTHER', '<ol>
<li>Attach a single handle to a low cable.</li>
<li>After selecting the correct weight, stand a couple feet back with a wide-split stance. Your arm should be extended and your shoulder forward. This will be your starting position.</li>
<li>Perform the movement by retracting the shoulder and flexing the elbow. As you pull, supinate the wrist, turning the palm upward as you go.</li>
<li>After a brief pause, return to the starting position.</li>
</ol>', false, 0, 'Shotgun Row', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (168, NULL, 'OTHER', 'The shoulder fly (also known as a lateral raise) works the deltoid muscle of the shoulder. The movement starts with the arms straight, and the hands holding weights at the sides or in front of the body. Body is in a slight forward-leaning position with hips and knees bent a little. Arms are kept straight or slightly bent, and raised through an arc of movement in the coronal plane that terminates when the hands are at approximately shoulder height. Weights are lowered to the starting position, completing one rep. When using a cable machine the individual stands with the coronal plane in line with the pulley, which is at or near the ground.[9] The exercise can be completed one shoulder at a time (with the other hand used to stabilize the body against the weight moved), or with both hands simultaneously if two parallel pulleys are available.', false, 0, 'Shoulder Fly', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (169, NULL, 'OTHER', '<p>Sit on a bench, the back rest should be almost vertical. Take a barbell with a shoulder wide grip and bring it up to chest height. Press the weight up, but don''t stretch the arms completely. Go slowly down and repeat.</p>', false, 0, 'Shoulder Press, Barbell', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (170, NULL, 'OTHER', '<p>Sit on a bench, the back rest should be almost vertical. Take two dumbbells and bring them up to shoulder height, the palms and the elbows point during the whole exercise to the front. Press the weights up, at the highest point they come very near but don''t touch. Go slowly down and repeat.</p>', false, 0, 'Shoulder Press, Dumbbells', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (171, NULL, 'OTHER', '', false, 0, 'Shoulder Press, on Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (172, NULL, 'OTHER', '<p>The exercise is basically the same as with a free barbell:</p>
<p>Sit on a bench, the back rest should be almost vertical. Take a bar with a shoulder wide grip and bring it down to chest height. Press the weight up, but don''t stretch the arms completely. Go slowly down and repeat.</p>', false, 0, 'Shoulder Press, on Multi Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (173, NULL, 'OTHER', 'The shoulder shrug (usually called simply the shrug) is an exercise in weight training used to develop the upper trapezius muscle. The lifter stands erect, hands about shoulder width apart, and raises the shoulders as high as possible, and then lowers them, while not bending the elbows, or moving the body at all. The lifter may not have as large a range of motion as in a normal shrug done for active flexibility. It is usually considered good form if the slope of the shoulders is horizontal in the elevated position.', false, 0, 'Shoulder Shrug', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (174, NULL, 'OTHER', '<p>Take a barbell and stand with a straight body, the arms are hanging freely in front of you. Lift from this position the shoulders as high as you can, but don''t bend the arms during the movement. On the highest point, make a short pause of 1 or 2 seconds before returning slowly to the initial position.</p>
<p>When training with a higher weight, make sure that you still do the whole movement!</p>', false, 0, 'Shrugs, Barbells', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (175, NULL, 'OTHER', '<p>Stand with straight body, the hands are hanging freely on the side and hold each a dumbbell. Lift from this position the shoulders as high as you can, but don''t bend the arms during the movement. On the highest point, make a short pause of 1 or 2 seconds before returning slowly to the initial position.</p>
<p>When training with a higher weight, make sure that you still do the whole movement!</p>', false, 0, 'Shrugs, Dumbbells', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (176, NULL, 'OTHER', '<p>Hold weight in one hand. Bend side ways to the knee. Pull upo to upright position using your obliquus.</p>', false, 0, 'Side Crunch', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (177, NULL, 'OTHER', '<p>AKA dumbbell side bends. Stand in line with the hips with slightly bent knees, maintain the natural curvature of the spine, hand stretched by the body, grip the barbell with one hand. <em> </em>Make slow and controlled torso side flexions till you reach the angle of approximately 45°.</p>', false, 0, 'Side Dumbbell Trunk Flexion', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (178, NULL, 'OTHER', '<p>With a weight in one hand, lie on your side opposite the weight. Keep your knees slightly bent. Hold your elbow against your side, and extend your upper arm straight ahead of you. While continuing to hold your elbow against your side, rotate your upper arm 90 degrees upwards.</p>
<p>It is helpful to place a towel under your armpit to help with the form on this exercise. Placing a support under your head for the duration of the exercise is also a good idea.</p>', false, 0, 'Side-lying External Rotation', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (179, NULL, 'OTHER', '<p>Works your obliques and helps stabilize your spine. Lie on your side and support your body between your forearm and knee to your feet.</p>', false, 0, 'Side Plank', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (180, NULL, 'OTHER', '<p>Stand up or sit , keep both weights in front against legs or at side. Keep arms at around a 90 degree angle. Lift elbows up slowly and squeese traps when at topmost position. Lower the weights slowly back to starting position.</p>
<p>2 seconds up, 2 seconds down</p>', false, 0, 'Side Raise', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (181, NULL, 'OTHER', '<p>-start in push up position</p>
<p>-lean the body weight to the right side, and complete a push up with the chest over the right hand</p>
<p>-come back to the centered position</p>
<p>-on rep 2, lean to the left side</p>', false, 0, 'Side to Side Push Ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (182, NULL, 'OTHER', '<p>Sit on the preacher curl bench and perform a bicep curl with a dumbbell in one hand. Your other hand can be at rest, or beneath your curling arm''s elbow.</p>', false, 0, 'Single-arm Preacher Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (183, NULL, 'OTHER', '<p>Sit on a bench for calf raises and check that the feet are half free and that you can completely stretch the calf muscles down. Pull your calves up, going as far (up) as you can. Make at the highest point a short pause of 1 or 2 seconds and go down.</p>', false, 0, 'Sitting Calf Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (184, NULL, 'OTHER', '<p>Sit on a mat, your calves are resting on a bench, the knees make a right angle. Hold your hands behind your neck. Go now up with a rolling movement of your back, you should feel how the individual vertebrae lose contact with the mat. At the highest point, contract your abs as much as you can and hold there for 2 sec. Go now down, unrolling your back.</p>
<p> </p>', false, 0, 'Sit-ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (185, NULL, 'OTHER', '<p>Do a single, double footed jump for each swing of the rope.</p>
<p>Work on a smooth, rhythmical movement, bouncing lightly on the balls of your feet.</p>', false, 0, 'Skipping - Standard', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (186, NULL, 'OTHER', '<p>Perform a standard bench press on the smith machine, but have your hands on the bar about shoulder width apart, and keep your elbows close to your body.</p>', false, 0, 'Smith Machine Close-grip Bench Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (188, NULL, 'OTHER', '<p>Deadlift with short (&lt;1min) rest between sets.</p>', false, 0, 'Speed Deadlift', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (189, NULL, 'OTHER', '<p>Lie on your back with your legs straight and arms at your sides, keeping your elbows bent at 90 degrees. As you sit up, twist your upper body to the left and bring your left knee toward your right elbow while you swing your left arm back. Lower your body to the starting position, and repeat to your right. That''s 1 rep.</p>', false, 0, 'Splinter Sit-ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (190, NULL, 'OTHER', '<p>Jump wide, then close</p>', false, 0, 'Squat Jumps', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (191, NULL, 'OTHER', '<p>Make sure you have put the barbell at a height where you can comfortably take it out and put it back in. Take it out and make yourself ready:</p>
<ul>
<li>The bar is somewhat lower than your shoulders</li>
<li>The feet are quite apart and point out</li>
<li>The head is in your neck and looks up</li>
<li>The chest is out</li>
</ul>
<p>Go now slowly down, till your thighs are parallel with the floor, not lower. The knees point outwards, your butt, out. Make a small pause of 1 second and with as much energy as you can, push the weight up. Make a pause of 2 seconds and repeat.</p>', false, 0, 'Squats', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (192, NULL, 'OTHER', 'The burpee, or squat thrust, is a full body exercise used in strength training and as an aerobic exercise. The basic movement is performed in four steps and known as a four-count burpee: Begin in a standing position. Move into a squat position with your hands on the ground. (count 1) Kick your feet back into a plank position, while keeping your arms extended. (count 2) Immediately return your feet into squat position. (count 3) Stand up from the squat position (count 4)', false, 0, 'Squat Thrust', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (193, NULL, 'OTHER', 'Stand holding dumbbells at shoulder width apart. Face forearm upward and keep upper arm still while raising each dumbbell up to your shoulder.', false, 0, 'Standing Bicep Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (194, NULL, 'OTHER', '<p>Get onto the calf raises machine, you should able to completely push your calves down. Stand straight, don''t make a hollow back and don''t bend your legs. Pull yourself up as high as you can. Make a small pause of 1 - 2 seconds and go slowly down.</p>', false, 0, 'Standing Calf Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (195, NULL, 'OTHER', '<p>Grab a wrist roller tool with both hands while standing with your feet about shoulder width apart. If your gym does not have a wrist roller tool, you can easily put one together. All you need is a 5 or 10 pound weight plate, a strong thin rope about 3 feet long and a 6-8 inch stick or bar. Securely fasten the rope to the middle of the bar/stick and tie the other end of the rope to the weight plate. To begin this exercise, grab the bar/stick with both hands using an overhand grip. Extend both arms straight out in front of you, parallel to the floor. Next, roll the weight up from the floor by rapidly twisting the bar/stick with your hands and wrists. Once the weight reaches the top, slowly lower the plate back to the floor by reversing the motion of your hands and wrists. Repeat (if you can!).</p>', false, 0, 'Standing Rope Forearm', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (196, NULL, 'OTHER', '<p>Ride a Stationary Bike with various tensions.</p>', false, 0, 'Stationary Bike', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (197, NULL, 'OTHER', '<ul>
<li>Keep legs straight</li>
<li>Keep back straight</li>
</ul>', false, 0, 'Stiff-legged Deadlifts', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (198, NULL, 'OTHER', '<p>Use the straight bar attachment on a high pulley. Grasp the two ends of the bar with your palms facing downward and your arms straight out in front of you. Pull your hands down towards your hips, while keeping your arms straight, then raise them back up to the starting position.</p>', false, 0, 'Straight-arm Pull Down (bar Attachment)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (199, NULL, 'OTHER', '<p>Use the rope attachment on a high pulley. Grasp the two ends of the rope with your arms straight out in front of you. Pull your hands down towards your hips, while keeping your arms straight, then raise them back up to the starting position.</p>', false, 0, 'Straight-arm Pull Down (rope Attachment)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (200, NULL, 'OTHER', '<ol>
<li>Begin with a bar loaded on the ground. Approach the bar so that the bar intersects the middle of the feet. The feet should be set very wide, near the collars. Bend at the hips to grip the bar. The arms should be directly below the shoulders, inside the legs, and you can use a pronated grip, a mixed grip, or hook grip. Relax the shoulders, which in effect lengthens your arms.</li>
<li>Take a breath, and then lower your hips, looking forward with your head with your chest up. Drive through the floor, spreading your feet apart, with your weight on the back half of your feet. Extend through the hips and knees.</li>
<li>As the bar passes through the knees, lean back and drive the hips into the bar, pulling your shoulder blades together.</li>
<li>Return the weight to the ground by bending at the hips and controlling the weight on the way down.</li>
</ol>', false, 0, 'Sumo Deadlift', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (201, NULL, 'OTHER', '<p>Stand with your feet wider than your shoulders, with your toes pointed out at a 45 degree angle and barbell on your shoulder.</p>
<p>While keeping your back straight, descend slowly by bending at the knees and hips as if you are sitting down (squatting).</p>
<p>Lower yourself until your quadriceps and hamstrings are parallel to the floor.</p>
<p>Return to the starting position by pressing upwards and extending your legs while maintaining an equal distribution of weight on your forefoot and heel.</p>', false, 0, 'Sumo Squats', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (202, NULL, 'OTHER', '<p>Lay flat on your stomach with your arms extended in front of you on the ground as your legs are lying flat. Lift both your arms and legs at the same time, as if you were flying, and contract the lower back. Make sure that you are breathing and, depending on your fitness level, hold the movement for at least two to five seconds per repetition.</p>', false, 0, 'Superman', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (203, NULL, 'OTHER', '<ol>
<li>Start by doing a front squat</li>
<li>At the top position, push the bar above your head (similar to a press)</li>
<li>Lower the bar to the shoulders</li>
</ol>', false, 0, 'Thruster', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (204, NULL, 'OTHER', '<ol>
<li>Start with a dumbbell in each hand and your palms facing your torso. Keep your back straight with a slight bend in the knees and bend forward at the waist. Your torso should be almost parallel to the floor. Make sure to keep your head up. Your upper arms should be close to your torso and parallel to the floor. Your forearms should be pointed towards the floor as you hold the weights. There should be a 90-degree angle formed between your forearm and upper arm. This is your starting position.</li>
<li>Now, while keeping your upper arms stationary, exhale and use your triceps to lift the weights until the arm is fully extended. Focus on moving the forearm.</li>
<li>After a brief pause at the top contraction, inhale and slowly lower the dumbbells back down to the starting position.</li>
<li>Repeat the movement for the prescribed amount of repetitions.</li>
</ol>
<p><strong>Variations:</strong> This exercise can be executed also one arm at a time much like the one arm rows are performed.</p>
<p>Also, if you like the one arm variety, you can use a low pulley handle instead of a dumbbell for better peak contraction. In this case, the palms should be facing up (supinated grip) as opposed to the torso (neutral grip).</p>', false, 0, 'Tricep Dumbbell Kickback', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (205, NULL, 'OTHER', '<p>Činka v jedné ruce v úrovni ramene. Zvedat do výšky napnuté paže a zpět. Provádět pomalu.</p>', false, 0, 'Triceps Bench Press One Barbell', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (207, NULL, 'OTHER', '<p>Grab the cable, stand with your feet shoulder wide, keep your back straight and lean forward a little. Push the bar down, making sure the elbows don''t move during the exercise. Rotate your hands outwards at the very end and go back to the initial position without pause.</p>', false, 0, 'Triceps Extensions on Cable', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (208, NULL, 'OTHER', '<p>Grab the bar, stand with your feet shoulder wide, keep your back straight and lean forward a little. Push the bar down, making sure the elbows don''t move during the exercise. Without pause go back to the initial position.</p>', false, 0, 'Triceps Extensions on Cable With Bar', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (209, NULL, 'OTHER', '<p>.</p>', false, 0, 'Triceps Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (210, NULL, 'OTHER', '<p>Seated trunk rotation with cable </p>', false, 0, 'Trunk Rotation With Cable ', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (211, NULL, 'OTHER', '<p>Starting on back, move to the standing position with dumbbell in one hand.  Switch hands between reps.</p>', false, 0, 'Turkish Get-Up', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (212, NULL, 'OTHER', '<p>Grip the pull-down bar with your palms facing you and your hands closer than shoulder-width apart. Lean back slightly and keep your back straight. Pull the bar down towards your chest, pulling your shoulders back slightly at the end of the motion.</p>', false, 0, 'Underhand Lat Pull Down', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (213, NULL, 'OTHER', '<p>Full workout of the upper arms body n chest. Focusing on the core</p>', false, 0, 'Upper Body', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (214, NULL, 'OTHER', '<p>Exercise for upper external oblique muscles</p>', false, 0, 'Upper External Oblique', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (215, NULL, 'OTHER', '<p>The movements are basically the same as with an SZ-bar, but you use the bar on the multi press:</p>
<p>Stand straight, your feet are shoulder-width apart. Hold the bar with an overhand grip on your thighs, the arms are stretched. Lift the bar close to the body till your chin. The elbows point out so that at the highest point they form a V. Make here a short pause before going slowly down and repeating the movement.</p>', false, 0, 'Upright Row, on Multi Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (216, NULL, 'OTHER', '<p>Stand straight, your feet are shoulder-width apart. Hold the SZ-bar with an overhand grip on your thighs, the arms are stretched. Lift the bar close to the body till your chin. The elbows point out so that at the highest point they form a V. Make here a short pause before going slowly down and repeating the movement.</p>', false, 0, 'Upright Row, SZ-bar', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (217, NULL, 'OTHER', '<p>Feet apart at shoulder width. Nice and Slow!</p>', false, 0, 'Upright Row w/ Dumbbells', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (218, NULL, 'OTHER', '<p>Pulldowns using close grip v-bar.</p>', false, 0, 'V-Bar Pulldown', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (219, NULL, 'OTHER', '<p>Handstand against a wall for support (chest facing wall).</p>', false, 0, 'Wall Handstand', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (220, NULL, 'OTHER', '<p>Pushup against a wall</p>', false, 0, 'Wall Pushup', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (221, NULL, 'OTHER', '<p>Stand with heels,  shoulders, back of head,  and hips touching the wall. Start with biceps straight out and elbows at a 90 degree angle. Straighten the arms while remaining againstthe wall without arching the back off of the wall, mimicking a shoulder press movement. </p>', false, 0, 'Wall Slides', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (222, NULL, 'OTHER', '<p>Find a nice flat piece of wall and stand with your back leaning against the wall. Slowly slide down the wall while moving your feet away from it, until your thighs are parallel to the ground and both your knees and your hips are bent at a 90° angle. Cross your arms in front of your chest and hold this position for 30 seconds.</p>
<p>Variant: put a big inflated rubber ball (like a small basketball) between your knees and squeeze the ball while holding the squat position</p>', false, 0, 'Wall Squat', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (223, NULL, 'OTHER', '<p>Seated with your back against a wall, put the bottoms of your feet together and add weight plates to your knees to deepen the butterfly stretch. Hold this for at least a minute or until youve comfortably elongated the groin and hip flexor. </p>', false, 0, 'Weighted Butterfly Stretch', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (224, NULL, 'OTHER', '<p>Box step-ups w/ barbell, 45''s on each side</p>', false, 0, 'Weighted Step', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (225, NULL, 'OTHER', '<p>box step ups w/ barbell and 45''s on each side</p>', false, 0, 'Weighted Step-ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (226, NULL, 'OTHER', '<p>Lat pulldowns with a wide grip on the bar.</p>', false, 0, 'Wide-grip Pulldown', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (29, NULL, 'OTHER', '<p>The movement is the same as with a regular butterfly, only that the grip is narrow:</p>
<p>Sit on the butterfly machine, the feet have a good contact with the floor, the upper arms are parallel to the floor. Press your arms together till the handles are practically together (but aren''t!). Go slowly back. The weights should stay all the time in the air.</p>', false, 4.8, 'Butterfly Narrow Grip', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (26, NULL, 'OTHER', '<p>1. Find a stable implement (a bench or a chair) and place the left foot hip-distance in front of the right one. Your core should be engaged, your shoulders should be back and your eyes and chest should face forward.</p>
<p>2. Raise the right leg off the ground and pulace it on the bench behind you.</p>
<p>3. Check your balance and distance between the feet. Your feet should not be on the same line as that will make it difficult to balance.</p>
<p>4. Bend your left knee and lower downard. Maintain position and push upward. The right knee is used only for balance and should bend by itself.</p>
<p>5. Switch the feet and repeat</p>
<p>The exercise can be done without weights, with dumbbells or a barbell.</p>', false, 4.8, 'Bulgarian Split Squat', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (31, NULL, 'OTHER', '<p>Begin with cables at about shoulder height, one in each hand. Take a step forward so that one foot is in front of the other, for stability, and so that there is tension on the cables. Bring hands together in front of you. Try to make your hands overlap (so that the cables cross) a few inches.</p>', false, 4.8, 'Cable Cross-over', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (71, NULL, 'OTHER', '<p>Start at a standing position. Lift and move one foot forward until your front calf and thigh are at 90 degrees to one another. At the same time, bend your back leg forward while keeping the back foot in place. Return to starting position. Repeat with other leg.</p>', false, 4.8, 'Forward Lunge', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (30, NULL, 'OTHER', '', false, 4.8, 'Butterfly Reverse', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (25, NULL, 'OTHER', '<p>Stand with feet slightly wider than shoulder-width apart, while standing as tall as you can.</p>
<p>Grab a weight plate and hold it out in front of your body with arms straight out. Keep your core tight and stand with a natural arch in your back.</p>
<p>Now, push hips back and bend knees down into a squat as far as you can. Hold for a few moments and bring yourself back up to the starting position.</p>', false, 4.8, 'Braced Squat', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (27, NULL, 'OTHER', '<p>Jump, lay down on your chest, do a pushup then jump, repeat</p>', false, 4.8, 'Burpees', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (51, NULL, 'OTHER', '<p>Lay down on a decline bench, the bar should be directly above your eyes, the knees are somewhat angled and the feet are firmly on the floor. Concentrate, breath deeply and grab the bar more than shoulder wide. Bring it slowly down till it briefly touches your chest at the height of your nipples. Push the bar up.</p>', false, 6.5, 'Decline Bench Press Barbell', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (24, NULL, 'OTHER', '<ol>
<li>Assume a plank position on the ground. You should be supporting your bodyweight on your toes and forearms, keeping your torso straight. Your forearms should be shoulder-width apart. This will be your starting position.</li>
<li>Pressing your palms firmly into the ground, extend through the elbows to raise your body from the ground. Keep your torso rigid as you perform the movement.</li>
<li>Slowly lower your forearms back to the ground by allowing the elbows to flex.</li>
<li>Repeat as needed.</li>
</ol>', false, 4.8, 'Body-Ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (21, NULL, 'OTHER', '<p>Hold two barbells, the arms are streched, the hands are on your side, the palms face inwards. Bend the arms and bring the weight with a fast movement up. At the same time, rotate your arms by 90 degrees at the very beginning of the movement. At the highest point, rotate a little the weights further outwards. Without a pause, bring them down, slowly.</p>
<p>Don''t allow your body to swing during the exercise, all work is done by the biceps, which are the only mucles that should move (pay attention to the elbows).</p>', false, 4.8, 'Biceps Curls With Dumbbell', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (23, NULL, 'OTHER', '<p>Stand around 30 - 40cm away from the cable, the feet are firmly on the floor. Take the bar and lift the weight with a fast movements. Lower the weight as with the dumbbell curls slowly and controlled.</p>', false, 4.8, 'Biceps Curl With Cable', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (20, NULL, 'OTHER', '<p>Hold the Barbell shoulder-wide, the back is straight, the shoulders slightly back, the arms are streched. Bend the arms, bringing the weight up, with a fast movement. Without pausing, let down the bar with a slow and controlled movement.</p>
<p>Don''t allow your body to swing during the exercise, all work is done by the biceps, which are the only mucles that should move (pay attention to the elbows).</p>', false, 4.8, 'Biceps Curls With Barbell', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (60, NULL, 'OTHER', '<p>With elbows back to sides, raise one dumbbell and rotate forearm until forearm is vertical and palm faces shoulder. Lower to original position and repeat with opposite arm. Continue to alternate between sides.</p>', false, 6.5, 'Dumbbell Incline Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (49, NULL, 'OTHER', '<p>Deadhang performed on an edge either with or without added weight (adujst edge or weight to adjust difficulty)</p>', false, 6.5, 'Deadhang', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (62, NULL, 'OTHER', '<p>Take two dumbbells in your hands, stand straight, feet about shoulder wide. Take one long step so that the front knee is approximately forming a right angle. The back leg is streched, the knee is low but doesn''t touch the ground. "Complete" the step by standing up and repeat the movement with the other leg.</p>', false, 6.5, 'Dumbbell Lunges Walking', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (38, NULL, 'OTHER', '<p>Like pull-ups but with a reverse grip</p>', false, 4.8, 'Chin-ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (48, NULL, 'OTHER', 'Lie on your back, with your hips and knees bent to 90°. Raise both arms toward the ceiling. Pull your lower back to the floor to eliminate the gap. Start by pressing one leg out, and tapping the heel to the floor. "As you extend one leg, exhale as much as you can, keeping your lower back glued to the floor," Dunham says. When you can’t exhale any more, pull your knee back to the starting position. Make this more difficult by holding weight in your hands, or by lowering opposite arm and leg.', false, 6.5, 'Deadbug', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (64, NULL, 'OTHER', '<p>Position one dumbbell over head with both hands under inner plate (heart shaped grip).</p>
<p>With elbows over head, lower forearm behind upper arm by flexing elbows. Flex wrists at bottom to avoid hitting dumbbell on back of neck. Raise dumbbell over head by extending elbows while hyperextending wrists. Return and repeat.</p>', false, 6.5, 'Dumbbell Triceps Extension', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (57, NULL, 'OTHER', '<p>Put two benches so far appart, that you can hold onto one with your hands and are just able to reach the other with your feet. The legs stay during the exercise completely stretched. With your elbows facing back, bend them as much as you can. Push yourself up, but don''t stretch out the arms.</p>', false, 6.5, 'Dips Between Two Benches', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (59, NULL, 'OTHER', '<p>Grasp dumbbell with both hands at the sides of the upper plates. Hold dumbbell in front of chest, close to torso. Place feet about shoulderwide apart, keep knees slightly bent.</p>
<p>Squat down until thighs are parallel to floor. Keep back straight, bend and move hips backward to keep knees above feet. Return, keep knees slightly flexed. Repeat.</p>
<p>Keep bodyweight on heels and look ahead or slightly above to keep back straight.</p>', false, 6.5, 'Dumbbell Goblet Squat', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (56, NULL, 'OTHER', 'A dip is an upper-body strength exercise. Narrow, shoulder-width dips primarily train the triceps, with major synergists being the anterior deltoid, the pectoralis muscles (sternal, clavicular, and minor), and the rhomboid muscles of the back (in that order).[1] Wide arm training places additional emphasis on the pectoral muscles, similar in respect to the way a wide grip bench press would focus more on the pectorals and less on the triceps.', false, 6.5, 'Dips', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (72, NULL, 'OTHER', '<p>Hold the dumbbells and lay down on a flat bench in such a way that around 1/4 of your head is over the edge. Stretch your arms with the weights and bend them so that the dumbbells are lowered (make sure they don''t touch each other). Just before they touch your forehead, push them up.</p>
<p>Pay attention to your elbows and arms: only the triceps are doing the work, the rest of the arms should not move.</p>', false, 6.5, 'French Press (skullcrusher) Dumbbells', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (47, NULL, 'OTHER', 'Cycling, also called bicycling or biking, is the use of bicycles for transport, recreation, exercise or sport. People engaged in cycling are referred to as cyclists, bicyclists, or bikers. Apart from two-wheeled bicycles, cycling also includes the riding of unicycles, tricycles, quadracycles, recumbent and similar human-powered vehicles.', false, 6.5, 'Cycling', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (46, NULL, 'OTHER', '<p>Bicipiti su panca a 45°. Il movimento deve essere completo</p>', false, 6.5, 'Curl su Panca a 45°', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (53, NULL, 'OTHER', '<p>With your feet raised approximately 30cm on a platform, align your shoulders, elbows and hands, then perform regular pushups. This emphasises the clavicular fibers of the pectoralis major.</p>', false, 6.5, 'Decline Pushups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (50, NULL, 'OTHER', '<p>Stand firmly, with your feet slightly more than shoulder wide apart. Stand directly behind the bar where it should barely touch your shin, your feet pointing a bit out. Bend down with a straight back, the knees also pointing somewhat out. Grab the bar with a shoulder wide grip, one underhand, one reverse grip.</p>
<p>Pull the weight up. At the highest point make a slight hollow back and pull the bar back. Hold 1 or 2 seconds that position. Go down, making sure the back is not bent. Once down you can either go back again as soon as the weights touch the floor, or make a pause, depending on the weight.</p>', false, 6.5, 'Deadlifts', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (36, NULL, 'OTHER', '<p>Place yourself on the machine with your back firmly against the backrest, the feet are on the platform for calf raises. Check that the feet are half free and that you can completely stretch the calf muscles down.</p>
<p>With straight knees pull up your weight as much as you can. Go with a fluid movement down till the calves are completely stretched. Repeat.</p>', false, 4.8, 'Calf Raises on Hackenschmitt Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (55, NULL, 'OTHER', '<p>You sit at the bench press device, back slightly tilted to the back. The bar should be about 20 cm in front of you. Then you push the bar and take it back again, as you would with a bench press.</p>
<p>In this position you strain your chest muscles a lot less, which is nice if you want to train, but your chest hasn''t recovered yet.</p>
<p>Here''s a link to a girl on a machine specialized for this exercise, to give a better description than my failing words above.</p>
<p>http://www.schnell-online.de/db_imgs/products/img/t-80400.jpg</p>', false, 6.5, 'Diagonal Shoulder Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (52, NULL, 'OTHER', '<p>Take two dumbbells and sit on a decline bench, the feet are firmly on the floor, the head is resting the bench. Hold the weights next to the chest, at the height of your nipples and press them up till the arms are stretched. Let the weight slowly and controlled down.</p>', false, 6.5, 'Decline Bench Press Dumbbell', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (54, NULL, 'OTHER', '<p>Preparation</p>
<p>Stand on weight plate, bumper plate, or shallow elevated platform with loaded bar above feet. Squat down and grasp bar with shoulder width or slightly wider overhand or mixed grip.</p>
<p> </p>
<p>Execution</p>
<p>Lift bar by extending hips and knees to full extension. Pull shoulders back at top of lift if rounded. Return weights to floor by bending hips back while allowing knees to bend forward, keeping back straight and knees pointed same direction as feet. Repeat.</p>
<p> </p>
<p>Comments</p>
<p>Throughout lift, keep hips low, shoulders high, arms and back straight. Knees should point same direction as feet throughout movement. Keep bar close to body to improve mechanical leverage. Grip strength and strength endurance often limit ability to perform multiple reps at heavy resistances. Gym chalk, wrist straps, grip work, and mixed grip can be used to enhance grip. Mixed grip indicates one hand holding with overhand grip and other hand holding with underhand grip. Lever barbell jack can be used to lift barbell from floor for easier loading and unloading of weight plates.</p>
<p>Barbell Deficit Deadlift emphasizes building strength through lowest portion of Deadlift. Target muscle is exercised isometrically. Heavy barbell deadlifts significantly engages Latissmus Dorsi. See Barbell Deficit Deadlift under Gluteus Maximus. Also see Deadlift Analysis.</p>', false, 6.5, 'Deficit Deadlift', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (32, NULL, 'OTHER', '<p>Steps:</p>
<ol>
<li>Start off placing an extension band around a post or in a secure position where it will not release and is at elbow level.</li>
<li>Position yourself to the side of the band and with your hand that is opposite of the band, reach out and grab the handle.</li>
<li>Bring the band to your chest keeping your elbow bent in a 90 degree angle then slowly rotate your arm in a backhand motion so that the band externally rotates out</li>
<li>Continue out as far as possible so that you feel a stretch in your shoulders, hold for a count and then return back to the starting position.</li>
<li>Repeat for as many reps and sets as desired.</li>
</ol>', false, 4.8, 'Cable External Rotation', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (41, NULL, 'OTHER', '<p>Grasp a moderately weighted dumbbell so your palms are flat against the underside of the top plates and your thumbs are around the bar. Lie on your back across a flat bench so only your upper back and shoulders are in contact with the bench. Your feet should be set about shoulder-width apart and your head should hang slightly downward. With the dumbbell supported at arm''s length directly about your chest, bend your arms about 15 degrees and keep them bent throughout the movement. Slowly lower the dumbbell backward and downward in a semicircle arc to as low a position as is comfortably possible. Raise it slowly back along the same arc to the starting point, and repeat for the required number of repetitions.</p>', false, 4.8, 'Cross-Bench Dumbbell Pullovers', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (43, NULL, 'OTHER', '<p>The procedure is very similar as for regular crunches, only with the additional weight of the machine. Sit on the machine, put both feet firmly on the ground. Grab the to the weights, cables, etc. and do a rolling motion forwards (the spine should ideally lose touch vertebra by vertebra). Slowly return to the starting position. </p>', false, 4.8, 'Crunches on Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (40, NULL, 'OTHER', '<p>Grip the pull-down bar with your hands closer than shoulder width apart, with your palms facing away from you. Lean back slightly. Pull the bar down towards your chest, keeping your elbows close to your sides as you come down. Pull your shoulders back at the end of the motion.</p>', false, 4.8, 'Close-grip Lat Pull Down', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (33, NULL, 'OTHER', '<p>Set cable pulley slightly lower than chest height. Keep body facing forward with hips stable.  Grab the pulley handle, fully extend your arms and bring your arms forward and across your body. Hold for 1 second at the end of the movement and slowly return to starting position.</p>', false, 4.8, 'Cable Woodchoppers', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (58, NULL, 'OTHER', '<p>Sit on bench. Grasp dumbbell between feet. Place back of upper arm to inner thigh. Lean into leg to raise elbow slightly.</p>', false, 6.5, 'Dumbbell Concentration Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (206, NULL, 'OTHER', '<p>lift on parallel bars hold for 1 second, and lower slowly and control for 4 seconds, then come back with no rest (tempo: 4010)</p>', false, 3, 'Triceps Dips', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (16, NULL, 'OTHER', '<p>Sit on bench while holding weights. Bend forward as far as possible, with arms slightly bent at the elbow. Perform a lateral raise while maintaining the bend in your elbow.</p>', false, 3, 'Bent-over Lateral Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (17, NULL, 'OTHER', '<p>The bent over lateral raise directly hits the rear deltoids (Shoulders). Considered an isolation exercise, it also exercises the trapezius muscles, which are considered secondary muscles. This exercise also hits the side deltoids as well.</p>









<p>1. In a seated position with your feet firmly planted on the floor, grasp two dumbbells with both hands.</p>
<p>2. Bend over until your body is roughly parallel to the ground or slightly above. Your chest should almost be touching your thighs.</p>
<p>3. In a controlled fashion, raise the dumbbells with both hands using your shoulders, out and away from the body. You should raise the dumbbells to each side until they are parallel with the upper back.</p>
<p>4. Pause for a moment and slowly lower the weight to the initial position.</p>








', false, 3, 'Bent Over Laterals', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (18, NULL, 'OTHER', '<p>Grab the barbell with a wide grip (slightly more than shoulder wide) and lean forward. Your upper body is not quite parallel to the floor, but forms a slight angle. The chest''s out during the whole exercise.</p>
<p>Pull now the barbell with a fast movement towards your belly button, not further up. Go slowly down to the initial position. Don''t swing with your body and keep your arms next to your body.</p>', false, 3, 'Bent Over Rowing', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (35, NULL, 'OTHER', 'Calf raises are a method of exercising the gastrocnemius, tibialis posterior and soleus muscles of the lower leg. The movement performed is plantar flexion, a.k.a. ankle extension.', false, 4.8, 'Calf Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (39, NULL, 'OTHER', '<p>Perform a typical bench press, but hold the bar with your hands approximately shoulder-width apart and keep your elbows close to your body.</p>', false, 4.8, 'Close-grip Bench Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (19, NULL, 'OTHER', '<p>The same as <em>regular</em> rowing, but holding a reversed grip (your palms pointing forwards):</p>
<p>Grab the barbell with a wide grIp (slightly more than shoulder wide) and lean forward. Your upper body is not quite parallel to the floor, but forms a slight angle. The chest''s out during the whole exercise.</p>
<p>Pull now the barbell with a fast movement towards your belly button, not further up. Go slowly down to the initial position. Don''t swing with your body and keep your arms next to your body.</p>', false, 4, 'Bent Over Rowing Reverse', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (44, NULL, 'OTHER', '<p>Take the cable on your hands and hold it next to your temples. Knee down and hold your upper body straight and bend forward. Go down with a fast movement, rolling your back in (your ellbows point to your knees). Once down, go slowly back to the initial position.</p>', false, 4.8, 'Crunches With Cable', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (42, NULL, 'OTHER', '<p>Lay down on your back a soft surface, the feet are on the floor. Ask a partner or use some other help (barbell, etc.) to keep them fixed, your hands are behind your head. From this position move your upper body up till your head or elbows touch your knees. Do this movement by rolling up your back.</p>', false, 4.8, 'Crunches', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (14, NULL, 'OTHER', '<ol>
<li>Holding a barbell with a pronated grip (palms facing down), bend your knees slightly and bring your torso forward, by bending at the waist, while keeping the back straight until it is almost parallel to the floor. Tip: Make sure that you keep the head up. The barbell should hang directly in front of you as your arms hang perpendicular to the floor and your torso. This is your starting position.</li>
<li>Now, while keeping the torso stationary, breathe out and lift the barbell to you. Keep the elbows close to the body and only use the forearms to hold the weight. At the top contracted position, squeeze the back muscles and hold for a brief pause.</li>
<li>Then inhale and slowly lower the barbell back to the starting position.</li>
<li>Repeat for the recommended amount of repetitions.</li>
</ol>', false, 3, 'Bent Over Barbell Row', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (15, NULL, 'OTHER', '<p>With dumbbells in hand, bend at the hip until hands hang just below the knees (similar to straight-legged-deadlift starting position). Keep upper body angle constant while contracting your lats to pull you ellbows back pinching the shoulder blades at the top. Try not to stand up with every rep, check hands go below knees on every rep.</p>', false, 3, 'Bentover Dumbbell Rows', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (1, NULL, 'OTHER', '<p>Two Handed Russian Style Kettlebell swing</p>', false, 3, '2 Handed Kettlebell Swing', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (2, NULL, 'OTHER', '<p>Very common shoulder exercise.</p>
<p> </p>
<p>As shown here: https://www.youtube.com/watch?v=vj2w851ZHRM</p>', false, 3, 'Arnold Shoulder Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (3, NULL, 'OTHER', '<p>Grab dumbbells and extend arms to side and hold as long as you can</p>', false, 3, 'Axe Hold', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (4, NULL, 'OTHER', '<p>Place a barbell in a rack just below shoulder-height. Dip under the bar to put it behind the neck across the top of the back, and grip the bar with the hands wider than shoulder-width apart. Lift the chest up and squeeze the shoulder blades together to keep the straight back throughout the entire movement. Stand up to bring the bar off the rack and step backwards, then place the feet so that they are a little wider than shoulder-width apart. Sit back into hips and keep the back straight and the chest up, squatting down so the hips are below the knees. From the bottom of the squat, press feet into the ground and push hips forward to return to the top of the standing position.</p>', false, 3, 'Back Squat', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (5, NULL, 'OTHER', '<p>Place a barbell on the floor at your feet.</p>
<p>Bending at the waist, grip the barbell with a shoulder with overhand grip.</p>
<p>With a slow controlled motion, roll the bar out so that your back is straight.</p>
<p>Roll back up raising your hips and butt as you return to the starting position.</p>', false, 3, 'Barbell Ab Rollout', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (8, NULL, 'OTHER', '<p>Position barbell overhead with narrow overhand grip.</p>
<p>Lower forearm behind upper arm with elbows remaining overhead. Extend forearm overhead. Lower and repeat.</p>', false, 3, 'Barbell Triceps Extension', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (9, NULL, 'OTHER', '<p>-Rest your weight on your palms and the balls of your feet, not dissimilar to normal pushup position</p>
<p>-Move by stepping with your R palm and L foot, then your L palm and R foot.  Basically, walk like a lumbering bear.</p>
<p>-Move as fast as you can.  Measure your reps/sets in either distance (i.e. 40 yards) or time (i.e. 45 seconds)</p>
<p>-Works your Pecs, Deltoids, Triceps, Traps, Lats, Abs and Lower Back, Hip Flexors, Quads, Glutes and Calves</p>', false, 3, 'Bear Walk', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (10, NULL, 'OTHER', '<p>Lay down on a bench, the bar should be directly above your eyes, the knees are somewhat angled and the feet are firmly on the floor. Concentrate, breath deeply and grab the bar more than shoulder wide. Bring it slowly down till it briefly touches your chest at the height of your nipples. Push the bar up.</p>
<p>If you train with a high weight it is advisable to have a <em>spotter</em> that can help you up if you can''t lift the weight on your own.</p>
<p>With the width of the grip you can also control which part of the chest is trained more:</p>
<ul>
<li>wide grip: outer chest muscles</li>
<li>narrow grip: inner chest muscles and triceps</li>
</ul>', false, 3, 'Bench Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (11, NULL, 'OTHER', '<p>The movement is very similar to benchpressing with a barbell, however, the weight is brought down to the chest at a lower point.</p>
<p>Hold two dumbbells and lay down on a bench. Hold the weights next to the chest, at the height of your nipples and press them up till the arms are stretched. Let the weight slowly and controlled down.</p>', false, 3, 'Benchpress Dumbbells', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (12, NULL, 'OTHER', '<p>Lay down on a bench, the bar is directly over your eyes, the knees form a slight angle and the feet are firmly on the ground. Hold the bar with a narrow grip (around 20cm.). Lead the weight slowly down till the arms are parallel to the floor (elbow: right angle), press then the bar up. When bringing the bar down, don''t let it down on your nipples as with the regular bench pressing, but somewhat lower.</p>', false, 3, 'Bench Press Narrow Grip', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (13, NULL, 'OTHER', '<p>Bend over slightly while holding two dumbbells.  Pull the dumbbells up to your chest, keeping your elbows as high as you can.</p>', false, 3, 'Bent High Pulls', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (6, NULL, 'OTHER', '<p>Perform leg squats with barbell behind your legs</p>', false, 3, 'Barbell Hack Squats', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (7, NULL, 'OTHER', '<p>Put barbell on the back of your shoulders. Stand upright, then take the first step forward. Step should bring you forward so that your supporting legs knee can touch the floor. Then stand back up and repeat with the other leg.</p>
<p>Remember to keep good posture.</p>', false, 3, 'Barbell Lunges', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (28, NULL, 'OTHER', '<p>Sit on the butterfly machine, the feet have a good contact with the floor, the upper arms are parallel to the floor. Press your arms together till the handles are practically together (but aren''t!). Go slowly back. The weights should stay all the time in the air.</p>', false, 4.8, 'Butterfly', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (34, NULL, 'OTHER', '<p>Put  the balls of your feet on an extended leg press pad.  Use your calves to press the weight by flexing your feet/toes into a pointed position, and releasing back into a relaxed position.</p>
<p>This exercise builds mass and strength in the Gastrocnemius and Soleus muscles as well, if not better, than any calf exercise.</p>', false, 4.8, 'Calf Press Using Leg Press Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (37, NULL, 'OTHER', 'The chin-up (also known as a chin or chinup) is a strength training exercise. People frequently do this exercise with the intention of strengthening muscles such as the latissimus dorsi and biceps, which extend the shoulder and flex the elbow, respectively. In this maneuver, the palms are faced towards the body. It is a form of pull-up in which the range of motion is established in relation to a person''s chin.', false, 4.8, 'Chin Up', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (73, NULL, 'OTHER', '<p>Hold the SZ-bar and lay down on a flat bench in such a way that around 1/4 of your head is over the edge. Stretch your arms with the bar and bend them so that the bar is lowered. Just before it touches your forehead, push it up.</p>
<p>Pay attention to your elbows and arms: only the triceps are doing the work, the rest of the arms should not move.</p>', false, 6.5, 'French Press (skullcrusher) SZ-bar', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name, creator, path_to_video_instruction, is_deleted) VALUES (22, NULL, 'OTHER', '<p>Hold the SZ-bar shoulder-wide, the back is straight, the shoulders slightly back, the arms are streched. Bend the arms, bringing the weight up, with a fast movement. Without pausing, let down the bar with a slow and controlled movement.</p>
<p>Don''t allow your body to swing during the exercise, all work is done by the biceps, which are the only mucles that should move (pay attention to the elbows).</p>', false, 4.8, 'Biceps Curls With SZ-bar', NULL, NULL, false);


--
-- Data for Name: training; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted) VALUES (1, 'Opis', 'Wskazówki', false, 'Trening', 'MEDIUM', '2b79bda3-85bd-4992-9cdf-e9f508325aee', false);
INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted) VALUES (2, 'aaa', 'aaa', false, 'Drugi trening', 'MEDIUM', '2b79bda3-85bd-4992-9cdf-e9f508325aee', false);


--
-- Data for Name: exercise_in_training; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id) VALUES (1, 20, 2, 1800, 60, 1);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id) VALUES (2, 20, 2, 1800, 18, 2);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id) VALUES (5, 20, 2, 1800, 7, 2);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id) VALUES (6, 20, 2, 1800, 3, 2);


--
-- Data for Name: exercise_labels; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (1, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (2, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (3, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (4, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (5, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (6, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (7, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (8, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (9, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (10, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (11, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (12, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (13, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (14, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (15, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (16, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (17, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (18, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (19, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (20, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (21, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (22, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (23, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (24, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (25, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (26, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (27, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (28, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (29, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (30, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (31, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (32, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (33, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (34, 7);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (35, 7);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (36, 7);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (37, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (38, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (39, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (40, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (41, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (42, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (43, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (44, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (45, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (46, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (47, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (48, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (49, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (50, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (51, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (52, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (53, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (54, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (55, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (56, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (56, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (57, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (58, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (59, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (60, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (61, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (62, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (63, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (64, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (65, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (66, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (67, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (68, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (69, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (70, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (71, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (72, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (73, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (74, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (75, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (76, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (77, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (78, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (79, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (80, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (81, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (82, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (83, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (84, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (85, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (86, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (87, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (88, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (89, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (90, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (91, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (92, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (93, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (94, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (95, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (96, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (97, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (98, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (99, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (100, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (101, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (102, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (103, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (104, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (105, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (106, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (107, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (108, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (109, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (110, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (111, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (112, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (113, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (114, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (115, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (116, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (117, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (118, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (119, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (120, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (121, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (122, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (123, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (124, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (125, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (126, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (127, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (128, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (128, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (129, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (130, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (131, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (132, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (133, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (134, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (135, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (136, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (137, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (138, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (139, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (140, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (141, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (142, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (143, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (144, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (145, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (146, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (147, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (148, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (149, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (150, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (151, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (152, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (153, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (154, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (155, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (156, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (157, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (158, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (159, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (160, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (161, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (162, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (163, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (164, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (165, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (166, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (167, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (168, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (169, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (170, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (171, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (172, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (173, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (174, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (175, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (176, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (177, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (178, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (179, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (180, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (181, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (182, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (183, 7);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (184, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (185, 7);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (186, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (187, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (188, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (189, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (190, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (191, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (192, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (193, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (194, 7);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (195, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (196, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (197, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (198, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (199, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (200, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (201, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (202, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (203, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (204, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (205, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (206, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (207, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (208, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (209, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (210, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (211, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (212, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (213, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (214, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (215, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (216, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (217, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (218, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (219, 4);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (220, 5);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (221, 6);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (222, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (223, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (224, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (225, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id) VALUES (226, 6);


--
-- Data for Name: follow; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.follow (follow_id, added_date, followed_id, follower_id) VALUES (20, '2021-11-29 19:41:24.699', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 'd4415db0-9504-4fec-a3f3-5c6eb368add0');
INSERT INTO public.follow (follow_id, added_date, followed_id, follower_id) VALUES (24, '2021-12-06 04:54:06.083', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.follow (follow_id, added_date, followed_id, follower_id) VALUES (25, '2021-12-07 01:36:16.01', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', 'a112b0cc-27f1-47c4-8ebc-8aad8d26f65b');
INSERT INTO public.follow (follow_id, added_date, followed_id, follower_id) VALUES (26, '2021-12-07 01:36:35.854', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 'a112b0cc-27f1-47c4-8ebc-8aad8d26f65b');
INSERT INTO public.follow (follow_id, added_date, followed_id, follower_id) VALUES (27, '2021-12-07 01:46:11.439', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.follow (follow_id, added_date, followed_id, follower_id) VALUES (31, '2021-12-07 10:31:38.929', 'ff84295f-5c04-49ad-9976-7b737133fc80', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.follow (follow_id, added_date, followed_id, follower_id) VALUES (32, '2021-12-07 10:34:19.987', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 'ff84295f-5c04-49ad-9976-7b737133fc80');


--
-- Data for Name: likes; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (114, '2021-11-14 19:44:23.879', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 1);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (115, '2021-11-14 19:44:25.211', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 2);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (116, '2021-11-14 19:44:28.307', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 14);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (5, '2021-11-13 23:00:10.879', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', 6);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (118, '2021-11-14 19:46:15.586', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', 15);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (119, '2021-11-14 19:46:17.415', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', 16);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (120, '2021-11-14 19:47:33.51', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 16);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (121, '2021-11-14 19:48:09.48', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', 17);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (122, '2021-11-14 19:48:10.657', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', 18);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (123, '2021-11-14 19:48:12.559', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', 19);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (124, '2021-11-14 20:15:54.431', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 17);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (131, '2021-11-15 22:46:44.066', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 24);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (132, '2021-11-19 17:39:53.534', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 40);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (79, '2021-11-14 01:26:39.11', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 5);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (84, '2021-11-14 01:39:48.349', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 6);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (137, '2021-11-22 01:04:16.16', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 39);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (138, '2021-11-22 03:16:16.587', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 60);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (139, '2021-11-22 03:17:05.514', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 59);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (90, '2021-11-14 03:25:16.249', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 7);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (144, '2021-11-22 17:00:38.34', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 13);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (145, '2021-11-22 19:01:05.592', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 28);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (147, '2021-11-23 02:54:58.666', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', 87);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (149, '2021-11-24 03:06:01.241', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 90);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (152, '2021-11-24 03:42:40.775', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 89);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (158, '2021-11-30 01:43:04.931', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', 6);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (159, '2021-11-30 03:00:03.707', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 96);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (161, '2021-11-30 03:30:40.886', 'ff84295f-5c04-49ad-9976-7b737133fc80', 100);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (162, '2021-11-30 03:30:42.24', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', 100);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (163, '2021-11-30 23:42:22.509', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 101);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (164, '2021-12-02 22:37:49.609', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 103);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (165, '2021-12-05 23:44:00.454', '60784494-bdfb-41b6-bf64-605762d4250b', 95);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (166, '2021-12-06 01:49:35.754', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 92);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (167, '2021-12-06 01:51:12.021', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 95);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (169, '2021-12-06 01:52:34.478', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 106);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (170, '2021-12-06 21:02:50.772', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 128);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (176, '2021-12-06 21:36:14.048', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 87);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (182, '2021-12-07 02:54:29.591', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 134);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id) VALUES (184, '2021-12-07 10:31:56.329', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 100);


--
-- Data for Name: measure_types; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--



--
-- Data for Name: measures; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--



--
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('c345c1f6-b9c9-4419-8b0b-2d7a180fdb6b', '2021-11-24 19:28:07.846', 'Siema', 0, 'ba147596-35d1-4c94-be11-c07265fdb760', '667562d7-cdd2-4ba8-80d5-ec3de30824bf');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('1e02d3ed-8ae0-4f4c-a09d-470162e95335', '2021-11-24 19:28:10.524', 'Co tam?', 0, 'ba147596-35d1-4c94-be11-c07265fdb760', '667562d7-cdd2-4ba8-80d5-ec3de30824bf');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('e4445f89-f41d-477f-805b-88d2b848a5ae', '2021-11-24 19:28:33.363', 'Fajno a tam?', 0, 'ba147596-35d1-4c94-be11-c07265fdb760', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('10644812-80dd-407d-8c30-e67dae9bd7ec', '2021-11-29 03:07:44.8', 'Siema', 0, '0010d845-9816-48ad-93f1-ec4fefea12b8', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('dc0f8c11-0a25-4fa6-9fa2-5882d676010e', '2021-11-29 03:28:45.263', 'Cześć', 0, '0010d845-9816-48ad-93f1-ec4fefea12b8', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('2c21713e-6b51-499d-9c08-6123631f0584', '2021-11-29 03:28:46.823', 'Co tam', 0, '0010d845-9816-48ad-93f1-ec4fefea12b8', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('d7e2f442-9ca3-4a30-a98c-139f15e80293', '2021-11-29 03:28:48.123', 'Jak tam', 0, '0010d845-9816-48ad-93f1-ec4fefea12b8', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('44130f45-49c5-4ebf-ba36-8f32ea8cbadb', '2021-11-29 03:28:50.03', 'się masz', 0, '0010d845-9816-48ad-93f1-ec4fefea12b8', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('c6e94f9c-8554-4e67-b34a-77718a3a18d0', '2021-11-29 03:28:51.014', '.', 0, '0010d845-9816-48ad-93f1-ec4fefea12b8', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('4d3366f3-f22c-4c34-9032-afbf5490c61e', '2021-11-29 03:28:51.509', '.', 0, '0010d845-9816-48ad-93f1-ec4fefea12b8', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('0992dfb6-f1c6-4e59-91aa-fc112d44596b', '2021-11-29 03:28:52.425', '.', 0, '0010d845-9816-48ad-93f1-ec4fefea12b8', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('fecbb85d-3df2-409f-8b4e-02e5bda17482', '2021-11-29 03:28:53.73', '.', 0, '0010d845-9816-48ad-93f1-ec4fefea12b8', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('56a118e5-80e3-413d-a305-f69a6dff4246', '2021-11-29 03:28:54.389', '.', 0, '0010d845-9816-48ad-93f1-ec4fefea12b8', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('b65ee802-bced-441d-86d1-1be4d5d6bd29', '2021-11-29 04:04:37.291', 'Hej Klaudia', 0, 'aedff5fd-7c43-4bbe-8b1e-a9621fae2bf8', '667562d7-cdd2-4ba8-80d5-ec3de30824bf');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('16cfd5e6-2a3b-4ed8-ad02-ffb3f06749aa', '2021-11-30 08:19:40.971', '2021-11-30-08-19-41-166_pizza.jpg', 1, '0010d845-9816-48ad-93f1-ec4fefea12b8', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('aadff8d6-15f2-4c73-9e6e-6cbec62c0cce', '2021-12-07 02:57:48.969', ':)', 0, '0010d845-9816-48ad-93f1-ec4fefea12b8', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id) VALUES ('7ca89c27-7dfc-4acb-8992-9e8f8d08da26', '2021-12-07 02:58:03.57', '2021-12-07-02-58-03-721_marchewki.jfif', 1, '0010d845-9816-48ad-93f1-ec4fefea12b8', '2b79bda3-85bd-4992-9cdf-e9f508325aee');


--
-- Data for Name: nutrition_plan_position; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.nutrition_plan_position (id, meal_type, week_day, dish_id, nutrition_plan_id) VALUES ('0b6326e5-e671-4a15-a915-c682ba5b2801', 'BREAKFAST', 'MONDAY', '2ea31743-56d8-454a-adba-19b331f7b96d', 'fd02b06f-beff-46f2-928e-adbfe6313be2');
INSERT INTO public.nutrition_plan_position (id, meal_type, week_day, dish_id, nutrition_plan_id) VALUES ('c1d1de15-767a-48e6-9f31-d4f52efe2ab3', 'DINNER', 'MONDAY', '2ea31743-56d8-454a-adba-19b331f7b96d', 'fd02b06f-beff-46f2-928e-adbfe6313be2');
INSERT INTO public.nutrition_plan_position (id, meal_type, week_day, dish_id, nutrition_plan_id) VALUES ('f4408c76-2f59-4266-ac36-1435bb58390b', 'SUPPER', 'MONDAY', '2ea31743-56d8-454a-adba-19b331f7b96d', 'fd02b06f-beff-46f2-928e-adbfe6313be2');
INSERT INTO public.nutrition_plan_position (id, meal_type, week_day, dish_id, nutrition_plan_id) VALUES ('69f1d0b2-5c25-430d-bdc1-c1a1d8c7e89a', 'LUNCH', 'TUESDAY', '2ea31743-56d8-454a-adba-19b331f7b96d', 'fd02b06f-beff-46f2-928e-adbfe6313be2');
INSERT INTO public.nutrition_plan_position (id, meal_type, week_day, dish_id, nutrition_plan_id) VALUES ('28179126-7b4f-48bc-99af-2ce3d362a7cf', 'SNACK', 'WEDNESDAY', '2ea31743-56d8-454a-adba-19b331f7b96d', 'fd02b06f-beff-46f2-928e-adbfe6313be2');
INSERT INTO public.nutrition_plan_position (id, meal_type, week_day, dish_id, nutrition_plan_id) VALUES ('06c443f1-11f0-4704-8a29-5bd51df031ed', 'LUNCH', 'THURSDAY', '2ea31743-56d8-454a-adba-19b331f7b96d', 'fd02b06f-beff-46f2-928e-adbfe6313be2');
INSERT INTO public.nutrition_plan_position (id, meal_type, week_day, dish_id, nutrition_plan_id) VALUES ('c0ad7079-16f4-4368-82a3-0dfcb82c963d', 'SUPPER', 'FRIDAY', '2ea31743-56d8-454a-adba-19b331f7b96d', 'fd02b06f-beff-46f2-928e-adbfe6313be2');


--
-- Data for Name: opinion; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.opinion (opinion_id, opinion_content, rating, giver_id, receiver_id, added_date, is_deleted) VALUES (2, 'Ujdzie', 4, '667562d7-cdd2-4ba8-80d5-ec3de30824bf', 'ff84295f-5c04-49ad-9976-7b737133fc80', '2021-12-01 02:38:19.781', false);
INSERT INTO public.opinion (opinion_id, opinion_content, rating, giver_id, receiver_id, added_date, is_deleted) VALUES (5, '', 2, '2b79bda3-85bd-4992-9cdf-e9f508325aee', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', '2021-12-02 03:20:33.845', false);
INSERT INTO public.opinion (opinion_id, opinion_content, rating, giver_id, receiver_id, added_date, is_deleted) VALUES (6, '', 2, 'd4415db0-9504-4fec-a3f3-5c6eb368add0', 'ff84295f-5c04-49ad-9976-7b737133fc80', '2021-12-02 03:28:42.831', false);
INSERT INTO public.opinion (opinion_id, opinion_content, rating, giver_id, receiver_id, added_date, is_deleted) VALUES (1, '', 3, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 'ff84295f-5c04-49ad-9976-7b737133fc80', '2021-12-02 15:44:43.868', false);
INSERT INTO public.opinion (opinion_id, opinion_content, rating, giver_id, receiver_id, added_date, is_deleted) VALUES (7, 'aaa
aaa
aaa
aaaa
bbb', 3, 'd4415db0-9504-4fec-a3f3-5c6eb368add0', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '2021-12-06 15:56:05.739', false);
INSERT INTO public.opinion (opinion_id, opinion_content, rating, giver_id, receiver_id, added_date, is_deleted) VALUES (8, '', 3, 'a112b0cc-27f1-47c4-8ebc-8aad8d26f65b', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', '2021-12-07 01:35:45.651', false);
INSERT INTO public.opinion (opinion_id, opinion_content, rating, giver_id, receiver_id, added_date, is_deleted) VALUES (9, '', 5, '2b79bda3-85bd-4992-9cdf-e9f508325aee', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', '2021-12-07 01:44:33.514', true);
INSERT INTO public.opinion (opinion_id, opinion_content, rating, giver_id, receiver_id, added_date, is_deleted) VALUES (3, 'Dobry dietetyk, polecam', 4, '667562d7-cdd2-4ba8-80d5-ec3de30824bf', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '2021-12-01 02:54:12.026', false);
INSERT INTO public.opinion (opinion_id, opinion_content, rating, giver_id, receiver_id, added_date, is_deleted) VALUES (4, 'Spoko', 3, 'ff84295f-5c04-49ad-9976-7b737133fc80', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '2021-12-01 02:54:44.923', false);


--
-- Data for Name: product_macro_detail; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.product_macro_detail (id, amount_of_element_per_hundred_grams, measure_type, detailed_macro_type, product_id) VALUES ('b0913f49-c6d0-4bcf-8e66-ef5a163e860d', 0, 'GRAM', 'ANIMAL_PROTEINS', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_macro_detail (id, amount_of_element_per_hundred_grams, measure_type, detailed_macro_type, product_id) VALUES ('8464f847-d626-4794-8302-747019fe62f4', 1, 'GRAM', 'VEGETABLE_PROTEINS', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_macro_detail (id, amount_of_element_per_hundred_grams, measure_type, detailed_macro_type, product_id) VALUES ('87c0cbf2-da32-463d-8d35-d7c2444ea979', 0, 'GRAM', 'MONOUNSATURATED_FATS', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_macro_detail (id, amount_of_element_per_hundred_grams, measure_type, detailed_macro_type, product_id) VALUES ('749d91d8-8146-4bd7-ad13-c1ec42466dde', 0.12, 'GRAM', 'POLYUNSATURATED_FATS', 'f95222ef-770a-4c31-a6a7-c887068516aa');


--
-- Data for Name: product_mineral_detail; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.product_mineral_detail (id, amount_of_element_per_hundred_grams, measure_type, mineral_type, product_id) VALUES ('2b938367-3510-4262-af2c-bccb75d9f01c', 0.34, 'MILLI_GRAM', 'ZINC', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_mineral_detail (id, amount_of_element_per_hundred_grams, measure_type, mineral_type, product_id) VALUES ('b617fe96-a79e-43a8-8afc-83c5d94e31f1', 32, 'MILLI_GRAM', 'PHOSPHORUS', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_mineral_detail (id, amount_of_element_per_hundred_grams, measure_type, mineral_type, product_id) VALUES ('3791ad59-f6c5-40ee-acb0-937e5da67ef8', 3, 'MICRO_GRAM', 'IODINE', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_mineral_detail (id, amount_of_element_per_hundred_grams, measure_type, mineral_type, product_id) VALUES ('89caf60f-80f3-42e7-a893-ead30919c60d', 16, 'MILLI_GRAM', 'MAGNESIUM', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_mineral_detail (id, amount_of_element_per_hundred_grams, measure_type, mineral_type, product_id) VALUES ('f4ca568d-f203-4848-8c72-1288d382b387', 0.1, 'MILLI_GRAM', 'COPPER', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_mineral_detail (id, amount_of_element_per_hundred_grams, measure_type, mineral_type, product_id) VALUES ('10e5c484-062e-4964-a016-7b5a55fb582f', 282, 'MILLI_GRAM', 'POTASSIUM', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_mineral_detail (id, amount_of_element_per_hundred_grams, measure_type, mineral_type, product_id) VALUES ('74f024f3-6887-491c-b08e-f5c13e12391e', 0, 'MICRO_GRAM', 'SELENIUM', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_mineral_detail (id, amount_of_element_per_hundred_grams, measure_type, mineral_type, product_id) VALUES ('66a0cf1d-ba5e-493b-b252-3d3068696245', 82, 'MILLI_GRAM', 'SODIUM', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_mineral_detail (id, amount_of_element_per_hundred_grams, measure_type, mineral_type, product_id) VALUES ('cf3b8d77-ec7f-4ceb-8731-05d1b10736c8', 36, 'MILLI_GRAM', 'CALCIUM', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_mineral_detail (id, amount_of_element_per_hundred_grams, measure_type, mineral_type, product_id) VALUES ('5439df35-b285-4229-b47f-4ad62e8cb939', 0.5, 'MILLI_GRAM', 'IRON', 'f95222ef-770a-4c31-a6a7-c887068516aa');


--
-- Data for Name: product_vitamin_detail; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.product_vitamin_detail (id, amount_of_element_per_hundred_grams, measure_type, vitamin_type, product_id) VALUES ('ba1b968c-5e0f-4438-8432-40922e7976ae', 1656, 'MICRO_GRAM', 'A', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_vitamin_detail (id, amount_of_element_per_hundred_grams, measure_type, vitamin_type, product_id) VALUES ('5456bf6e-dbcf-4be1-b699-f82afdbf6e72', 0.05, 'MILLI_GRAM', 'B1', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_vitamin_detail (id, amount_of_element_per_hundred_grams, measure_type, vitamin_type, product_id) VALUES ('47691449-bab2-4af2-a6bf-6d5f87277632', 0.05, 'MILLI_GRAM', 'B2', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_vitamin_detail (id, amount_of_element_per_hundred_grams, measure_type, vitamin_type, product_id) VALUES ('1fca555d-26bf-46ee-a481-d5852912b02b', 0, 'MILLI_GRAM', 'B5', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_vitamin_detail (id, amount_of_element_per_hundred_grams, measure_type, vitamin_type, product_id) VALUES ('1106323d-e23f-4f2a-a7f4-721ae45c9dbb', 0.16, 'MILLI_GRAM', 'B6', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_vitamin_detail (id, amount_of_element_per_hundred_grams, measure_type, vitamin_type, product_id) VALUES ('5724499c-4a5c-466f-9d40-a671f0dfdec1', 0, 'MILLI_GRAM', 'B12', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_vitamin_detail (id, amount_of_element_per_hundred_grams, measure_type, vitamin_type, product_id) VALUES ('feb0ef56-f27e-429a-b502-585fce8d64c2', 0.3, 'MILLI_GRAM', 'C', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_vitamin_detail (id, amount_of_element_per_hundred_grams, measure_type, vitamin_type, product_id) VALUES ('c5f31352-77d3-4299-937f-aead3896191d', 0, 'MICRO_GRAM', 'D', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_vitamin_detail (id, amount_of_element_per_hundred_grams, measure_type, vitamin_type, product_id) VALUES ('b80f88ab-cdab-4892-b3fe-8cfc6161e0d1', 0.51, 'MILLI_GRAM', 'E', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_vitamin_detail (id, amount_of_element_per_hundred_grams, measure_type, vitamin_type, product_id) VALUES ('f70dfca8-8d92-422a-83da-c90293fe1518', 0.45, 'MILLI_GRAM', 'PP', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_vitamin_detail (id, amount_of_element_per_hundred_grams, measure_type, vitamin_type, product_id) VALUES ('e1c46e44-bb9b-44ec-a538-675a6988d597', 0.3, 'MICRO_GRAM', 'K', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_vitamin_detail (id, amount_of_element_per_hundred_grams, measure_type, vitamin_type, product_id) VALUES ('f9cc47e1-5f8e-4d7e-87c9-79593f8cfcb1', 32, 'MICRO_GRAM', 'FOLIC_ACID', 'f95222ef-770a-4c31-a6a7-c887068516aa');
INSERT INTO public.product_vitamin_detail (id, amount_of_element_per_hundred_grams, measure_type, vitamin_type, product_id) VALUES ('417ad74f-73d2-4440-8bd6-ea50abc48078', 0, 'MICRO_GRAM', 'BIOTIN', 'f95222ef-770a-4c31-a6a7-c887068516aa');


--
-- Data for Name: profile_card_ailment; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.profile_card_ailment (ailment_id, profile_card_id) VALUES ('2c604432-1e97-4bf3-b762-5908d5b49172', '45848702-83b6-454a-b877-42d5aa609354');


--
-- Data for Name: profile_connections; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date, connected_with_profile_user_id, profile_profile_user_id) VALUES ('6e3bb546-894c-4e16-bd7d-f780ef9d8b15', '2021-11-24 19:28:00.309', 1, true, '2021-11-24 19:27:38.181', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date, connected_with_profile_user_id, profile_profile_user_id) VALUES ('985ab7e0-1fcc-48b9-b693-7ee9073967a5', NULL, 1, false, '2021-11-28 23:11:48.998', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 'ff84295f-5c04-49ad-9976-7b737133fc80');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date, connected_with_profile_user_id, profile_profile_user_id) VALUES ('583ee97b-eb52-4b42-92c2-1db19b3109e9', NULL, 0, false, '2021-11-29 03:57:42.404', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 'a1aeb823-0672-452b-a1ac-8dc06b74b095');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date, connected_with_profile_user_id, profile_profile_user_id) VALUES ('e1686a94-421c-488a-9006-fcc5d8b98f07', '2021-11-30 00:59:59.665', 0, true, '2021-11-30 00:59:25.752', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date, connected_with_profile_user_id, profile_profile_user_id) VALUES ('1842d487-e403-4450-b9e3-930cf6f5e216', NULL, 0, false, '2021-11-30 03:20:03.792', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date, connected_with_profile_user_id, profile_profile_user_id) VALUES ('91e92613-b9ac-436f-a1fb-c59b214e1e7b', '2021-12-06 21:59:21.07', 3, true, '2021-12-06 21:59:05.145', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date, connected_with_profile_user_id, profile_profile_user_id) VALUES ('10e7c9c1-3547-47f0-ad85-7e6e86ea29d5', '2021-12-07 10:33:36.809', 0, true, '2021-12-07 10:32:57.584', 'ff84295f-5c04-49ad-9976-7b737133fc80', '2b79bda3-85bd-4992-9cdf-e9f508325aee');


--
-- Data for Name: report; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.report (id, derived_calories, derived_carbohydrates, derived_fats, derived_proteins, report_date, owner_profile_id, derived_caffeine, derived_cholesterol, derived_fiber, derived_salt, derived_saturated_fats, derived_sugar) VALUES ('39dd2a4d-077c-45d6-a121-e6587b59943c', 0, 0, 0, 0, '2021-11-14', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.report (id, derived_calories, derived_carbohydrates, derived_fats, derived_proteins, report_date, owner_profile_id, derived_caffeine, derived_cholesterol, derived_fiber, derived_salt, derived_saturated_fats, derived_sugar) VALUES ('362b7c68-fc0f-45d4-94ca-e6bdaaffeb63', 0, 0, 0, 0, '2021-11-22', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL, NULL, NULL, NULL, NULL, NULL);


--
-- Data for Name: report_dish_detail; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--



--
-- Data for Name: sport_report; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.sport_report (id, report_date, owner_profile_id) VALUES ('79c18645-b6c1-4097-9f81-da9c49d5e919', '2021-12-10', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.sport_report (id, report_date, owner_profile_id) VALUES ('4bb2debc-d6af-4c32-a0e6-c688cd6a2023', '2021-12-09', '2b79bda3-85bd-4992-9cdf-e9f508325aee');
INSERT INTO public.sport_report (id, report_date, owner_profile_id) VALUES ('b35aa44e-62a7-4319-8079-e406ce2570de', '2021-12-11', '2b79bda3-85bd-4992-9cdf-e9f508325aee');


--
-- Data for Name: report_exercise; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--



--
-- Data for Name: report_product_detail; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--



--
-- Data for Name: report_training; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id) VALUES (1, 3600, '2021-11-10 08:00:00', '79c18645-b6c1-4097-9f81-da9c49d5e919', 1);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id) VALUES (2, 3600, '2021-11-09 08:00:00', '4bb2debc-d6af-4c32-a0e6-c688cd6a2023', 1);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id) VALUES (3, 3600, '2021-11-11 08:00:00', 'b35aa44e-62a7-4319-8079-e406ce2570de', 1);


--
-- Data for Name: role_request; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (50, '2021-11-24 20:06:25.044', '', 'brak', 'ROLE_DOCTOR', 'CANCELLED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 'c8020cc6-0e4d-426b-8b7d-c2a5f76e55f5');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (1, '2021-11-12 16:36:42.246', '', '2021-11-12-16-36-42-223_kiara_usg.pdf', 'ROLE_DIETICIAN', 'CANCELLED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (2, '2021-11-12 16:36:54.747', '', '2021-11-12-16-36-54-720_rozklad_zajec_473026.pdf', 'ROLE_DIETICIAN', 'CANCELLED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (5, '2021-11-12 16:38:50.273', '', '2021-11-12-16-38-50-262_Design Sprint 2.0.pdf', 'ROLE_TRAINER', 'ACCEPTED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (63, '2021-11-27 03:27:00.04', '', '2021-11-27-03-27-00-031_M_240-odblokowany - umowa zlecenia-wyp.pdf', 'ROLE_DOCTOR', 'CANCELLED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 'c8020cc6-0e4d-426b-8b7d-c2a5f76e55f5');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (8, '2021-11-12 16:48:59.34', '', '2021-11-12-16-48-59-330_Design Sprint 2.0.pdf', 'ROLE_DIETICIAN', 'ACCEPTED', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (6, '2021-11-12 16:48:48.571', '', '2021-11-12-16-48-48-556_Design Sprint 2.0.pdf', 'ROLE_DIETICIAN', 'CANCELLED', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (7, '2021-11-12 16:48:52.885', '', '2021-11-12-16-48-52-872_Design Sprint 2.0.pdf', 'ROLE_DIETICIAN', 'CANCELLED', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (56, '2021-11-24 20:18:21.682', '', '2021-11-24-20-18-21-671_kiara_usg.pdf', 'ROLE_DIETICIAN', 'CANCELLED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (11, '2021-11-12 17:31:52.987', '', '2021-11-12-17-31-52-978_Design Sprint 2.0.pdf', 'ROLE_DIETICIAN', 'ACCEPTED', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (10, '2021-11-12 17:31:31.572', '', '2021-11-12-17-31-31-557_Design Sprint 2.0.pdf', 'ROLE_DIETICIAN', 'CANCELLED', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (12, '2021-11-12 17:47:50.354', '', '2021-11-12-17-47-50-335_Design Sprint 2.0.pdf', 'ROLE_TRAINER', 'ACCEPTED', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (9, '2021-11-12 16:52:40.744', 'Bo nie', '2021-11-12-16-52-40-728_Design Sprint 2.0.pdf', 'ROLE_DOCTOR', 'REJECTED', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (14, '2021-11-12 18:09:00.198', '', '2021-11-12-18-09-00-186_Design Sprint 2.0.pdf', 'ROLE_DOCTOR', 'ACCEPTED', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (13, '2021-11-12 18:08:55.453', '', '2021-11-12-18-08-55-437_Design Sprint 2.0.pdf', 'ROLE_DOCTOR', 'CANCELLED', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (15, '2021-11-12 18:10:30.742', '', '2021-11-12-18-10-30-729_Design Sprint 2.0.pdf', 'ROLE_TRAINER', 'ACCEPTED', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (17, '2021-11-14 22:46:03.645', '', '2021-11-14-22-46-03-633_kiara_usg.pdf', 'ROLE_DIETICIAN', 'ACCEPTED', 'ff84295f-5c04-49ad-9976-7b737133fc80', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (16, '2021-11-14 22:45:57.053', '', '2021-11-14-22-45-57-033_kiara_usg.pdf', 'ROLE_DIETICIAN', 'CANCELLED', 'ff84295f-5c04-49ad-9976-7b737133fc80', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (18, '2021-11-14 22:50:39.056', '', '2021-11-14-22-50-39-040_kiara_usg.pdf', 'ROLE_TRAINER', 'ACCEPTED', 'ff84295f-5c04-49ad-9976-7b737133fc80', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (55, '2021-11-24 20:18:16.137', '', '2021-11-24-20-18-16-129_kiara_usg.pdf', 'ROLE_DOCTOR', 'REJECTED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '095c524f-7357-4930-9c3b-c47666c37bb9');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (19, '2021-11-15 22:48:24.325', 'Bo nie', '2021-11-15-22-48-24-314_kiara_usg.pdf', 'ROLE_DOCTOR', 'REJECTED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (22, '2021-11-23 02:54:28.361', '', '2021-11-23-02-54-28-353_kiara_usg.pdf', 'ROLE_DIETICIAN', 'ACCEPTED', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (20, '2021-11-23 01:05:43.66', 'Dokument nie potwierdza kwalifikacji', '2021-11-23-01-05-43-645_kiara_usg.pdf', 'ROLE_DOCTOR', 'REJECTED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (25, '2021-11-23 03:18:43.233', '', '2021-11-23-03-18-43-217_kiara_usg.pdf', 'ROLE_DIETICIAN', 'CANCELLED', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (62, '2021-11-27 01:53:05.404', '', '2021-11-27-01-53-05-394_M_240-odblokowany - umowa zlecenia-wyp.pdf', 'ROLE_TRAINER', 'PENDING', 'a1aeb823-0672-452b-a1ac-8dc06b74b095', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (64, '2021-11-30 03:04:20.257', '', '2021-11-30-03-04-20-247_M_240-odblokowany - umowa zlecenia-wyp.pdf', 'ROLE_DOCTOR', 'CANCELLED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '095c524f-7357-4930-9c3b-c47666c37bb9');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (32, '2021-11-24 02:50:41.696', '', '2021-11-24-02-11-18-755_Vinted-Etykieta-373662462.pdf', 'ROLE_DOCTOR', 'ACCEPTED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '4ff1b72e-3c04-4f85-b613-112071cffa7b');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (30, '2021-11-24 00:25:59.71', '', '2021-11-24-00-25-59-701_Vinted-Etykieta-373662462.pdf', 'ROLE_DOCTOR', 'CANCELLED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '4ff1b72e-3c04-4f85-b613-112071cffa7b');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (26, '2021-11-23 03:19:13.837', '', '2021-11-23-03-19-13-825_kiara_usg.pdf', 'ROLE_TRAINER', 'ACCEPTED', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (23, '2021-11-23 02:56:46.757', '', '2021-11-23-02-56-46-746_kiara_usg.pdf', 'ROLE_TRAINER', 'CANCELLED', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (24, '2021-11-23 02:56:50.854', '', '2021-11-23-02-56-50-846_kiara_usg.pdf', 'ROLE_DOCTOR', 'ACCEPTED', '667562d7-cdd2-4ba8-80d5-ec3de30824bf', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (31, '2021-11-24 00:26:46.81', 'Bo nie', '2021-11-24-00-26-46-804_Vinted-Etykieta-373662462.pdf', 'ROLE_DOCTOR', 'REJECTED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '095c524f-7357-4930-9c3b-c47666c37bb9');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (3, '2021-11-12 16:37:06.536', '', 'b', 'ROLE_TRAINER', 'CANCELLED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (57, '2021-11-24 20:24:41.733', '', 'c', 'ROLE_DIETICIAN', 'ACCEPTED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (61, '2021-11-27 01:38:38.625', '', 'a', 'ROLE_DIETICIAN', 'CANCELLED', 'a1aeb823-0672-452b-a1ac-8dc06b74b095', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (28, '2021-11-23 20:46:03.951', '', '2021-11-23-20-46-03-943_Vinted-Etykieta-373662462.pdf', 'ROLE_DOCTOR', 'PENDING', 'ff84295f-5c04-49ad-9976-7b737133fc80', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (68, '2021-11-30 03:16:36.957', '', '2021-11-30-03-16-36-950_M_240-odblokowany - umowa przelewu-wyp.pdf', 'ROLE_DIETICIAN', 'ACCEPTED', 'ff84295f-5c04-49ad-9976-7b737133fc80', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (66, '2021-11-30 03:15:19.425', '', '2021-11-30-03-15-19-418_M_240-odblokowany - umowa zlecenia-wyp.pdf', 'ROLE_TRAINER', 'ACCEPTED', 'd4415db0-9504-4fec-a3f3-5c6eb368add0', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (65, '2021-11-30 03:04:34.391', '', 'd', 'ROLE_DOCTOR', 'ACCEPTED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 'c8020cc6-0e4d-426b-8b7d-c2a5f76e55f5');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (41, '2021-11-24 19:37:19.478', '', '2021-11-24-19-37-19-471_kiara_usg.pdf', 'ROLE_DIETICIAN', 'CANCELLED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 'c8020cc6-0e4d-426b-8b7d-c2a5f76e55f5');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (40, '2021-11-24 19:37:12.806', '', '2021-11-24-19-37-12-794_kiara_usg.pdf', 'ROLE_DOCTOR', 'CANCELLED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', 'c8020cc6-0e4d-426b-8b7d-c2a5f76e55f5');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (29, '2021-11-24 00:25:42.547', '', '2021-11-24-00-25-42-538_Vinted-Etykieta-373662462.pdf', 'ROLE_DOCTOR', 'CANCELLED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '095c524f-7357-4930-9c3b-c47666c37bb9');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (27, '2021-11-23 19:50:03.775', '', '2021-11-23-19-50-03-767_Vinted-Etykieta-367241019.pdf', 'ROLE_DOCTOR', 'CANCELLED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '095c524f-7357-4930-9c3b-c47666c37bb9');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (21, '2021-11-23 01:09:23.697', '', '2021-11-23-01-09-23-686_kiara_usg.pdf', 'ROLE_DIETICIAN', 'CANCELLED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (69, '2021-12-07 03:03:48.77', 'Dokument nie potwierdza specjalizacji', 'kiara_usg.pdf', 'ROLE_DOCTOR', 'REJECTED', '2b79bda3-85bd-4992-9cdf-e9f508325aee', '095c524f-7357-4930-9c3b-c47666c37bb9');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (73, '2021-12-07 03:05:22.165', '', '2021-12-07-03-05-22-149_M_240-odblokowany - umowa zlecenia-wyp.pdf', 'ROLE_DOCTOR', 'ACCEPTED', 'ff84295f-5c04-49ad-9976-7b737133fc80', '4ff1b72e-3c04-4f85-b613-112071cffa7b');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (72, '2021-12-07 03:05:09.112', '', '2021-12-07-03-05-09-099_M_240-odblokowany - umowa przelewu.pdf', 'ROLE_DOCTOR', 'CANCELLED', 'ff84295f-5c04-49ad-9976-7b737133fc80', '4ff1b72e-3c04-4f85-b613-112071cffa7b');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (76, '2021-12-07 10:38:18.755', '', 'M_240-odblokowany - umowa zlecenia-wyp.pdf', 'ROLE_TRAINER', 'CANCELLED', 'ff84295f-5c04-49ad-9976-7b737133fc80', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (75, '2021-12-07 10:37:57.168', '', '2021-12-07-10-37-57-144_M_240-odblokowany - umowa zlecenia-wyp.pdf', 'ROLE_DOCTOR', 'ACCEPTED', 'ff84295f-5c04-49ad-9976-7b737133fc80', '095c524f-7357-4930-9c3b-c47666c37bb9');
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id, specialization_id) VALUES (74, '2021-12-07 10:37:47.196', '', '2021-12-07-10-37-47-180_M_240-odblokowany - umowa zlecenia-wyp.pdf', 'ROLE_DOCTOR', 'CANCELLED', 'ff84295f-5c04-49ad-9976-7b737133fc80', '095c524f-7357-4930-9c3b-c47666c37bb9');


--
-- Data for Name: settings; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--



--
-- Data for Name: sport_label_ailment; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--



--
-- Data for Name: training_position; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id, training_plan_id) VALUES (1, 'MORNING', '2021-12-07 00:00:00', NULL, 1, 3);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id, training_plan_id) VALUES (2, 'MORNING', '2021-12-07 00:00:00', NULL, 1, 3);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id, training_plan_id) VALUES (3, 'MORNING', '2021-12-09 00:00:00', NULL, 1, 3);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id, training_plan_id) VALUES (5, 'MORNING', '2021-12-09 00:00:00', NULL, 1, 3);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id, training_plan_id) VALUES (7, 'MORNING', '2021-12-10 00:00:00', NULL, 1, 3);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id, training_plan_id) VALUES (8, 'MORNING', '2021-12-10 00:00:00', NULL, 1, 3);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id, training_plan_id) VALUES (9, 'MORNING', '2021-12-10 00:00:00', NULL, 1, 3);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id, training_plan_id) VALUES (6, 'MORNING', '2021-12-10 00:00:00', 'COMPLETED', 1, 3);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id, training_plan_id) VALUES (4, 'MORNING', '2021-12-09 00:00:00', 'COMPLETED', 1, 3);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id, training_plan_id) VALUES (10, 'MORNING', '2021-12-11 00:00:00', 'COMPLETED', 1, 3);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id, training_plan_id) VALUES (11, 'NOON', '2021-12-13 00:00:00', NULL, 1, 1);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id, training_plan_id) VALUES (12, 'AFTERNOON', '2021-12-06 00:00:00', NULL, 2, 4);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id, training_plan_id) VALUES (13, 'NIGHT', '2021-12-07 00:00:00', NULL, 1, 4);


--
-- Data for Name: wb_role; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.wb_role (role_id, authority) VALUES ('1daa90c5-4eb1-4193-8bb6-632270e8b046', 'ROLE_ADMIN');
INSERT INTO public.wb_role (role_id, authority) VALUES ('41b186d8-c24e-4dd5-9bec-f75a26a53b55', 'ROLE_BASIC_USER');
INSERT INTO public.wb_role (role_id, authority) VALUES ('591228eb-c877-42ee-8c4b-a207b4a86d6e', 'ROLE_DOCTOR');
INSERT INTO public.wb_role (role_id, authority) VALUES ('1298606e-b743-4e91-a5de-16d267746344', 'ROLE_DIETICIAN');
INSERT INTO public.wb_role (role_id, authority) VALUES ('608c0f7f-d893-4c0e-949a-745dd2e8af32', 'ROLE_TRAINER');

--
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--
INSERT INTO public.user_roles (id, role_id) VALUES ('6c208bfb-ccd9-4b6e-9b63-e47548cc4af9', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('0aa2a88c-4c3c-4a7f-a2f7-763b3fb30681', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('0aa2a88c-4c3c-4a7f-a2f7-763b3fb30681', '608c0f7f-d893-4c0e-949a-745dd2e8af32');

INSERT INTO public.user_roles (id, role_id) VALUES ('2bd04c24-4eec-4d6d-9bc6-490b3d79ec3a', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('2bd04c24-4eec-4d6d-9bc6-490b3d79ec3a', '1daa90c5-4eb1-4193-8bb6-632270e8b046');
INSERT INTO public.user_roles (id, role_id) VALUES ('d4415db0-9504-4fec-a3f3-5c6eb368add0', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('ff84295f-5c04-49ad-9976-7b737133fc80', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('1d4a32c8-b08c-496d-95eb-82614c6cf618', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('667562d7-cdd2-4ba8-80d5-ec3de30824bf', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('fd13fb01-3bf6-46d3-a420-5d6605d9b6b1', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('72af8979-4261-4d2f-8258-1e03de343b16', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('1fba890a-d714-41c2-9bae-81d5648c0da3', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('2b79bda3-85bd-4992-9cdf-e9f508325aee', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('667562d7-cdd2-4ba8-80d5-ec3de30824bf', '591228eb-c877-42ee-8c4b-a207b4a86d6e');
INSERT INTO public.user_roles (id, role_id) VALUES ('2b79bda3-85bd-4992-9cdf-e9f508325aee', '591228eb-c877-42ee-8c4b-a207b4a86d6e');
INSERT INTO public.user_roles (id, role_id) VALUES ('c8922775-9fec-4955-bbc9-4dea4de29701', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('2b79bda3-85bd-4992-9cdf-e9f508325aee', '1298606e-b743-4e91-a5de-16d267746344');
INSERT INTO public.user_roles (id, role_id) VALUES ('04efc2d0-96d9-4e1f-ae10-395c1947e7b4', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('a112b0cc-27f1-47c4-8ebc-8aad8d26f65b', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('60784494-bdfb-41b6-bf64-605762d4250b', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('a1aeb823-0672-452b-a1ac-8dc06b74b095', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('77a13c0f-21e6-45ce-8c4b-8d795626c920', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('667562d7-cdd2-4ba8-80d5-ec3de30824bf', '1298606e-b743-4e91-a5de-16d267746344');
INSERT INTO public.user_roles (id, role_id) VALUES ('ff84295f-5c04-49ad-9976-7b737133fc80', '1298606e-b743-4e91-a5de-16d267746344');
INSERT INTO public.user_roles (id, role_id) VALUES ('667562d7-cdd2-4ba8-80d5-ec3de30824bf', '608c0f7f-d893-4c0e-949a-745dd2e8af32');
INSERT INTO public.user_roles (id, role_id) VALUES ('d4415db0-9504-4fec-a3f3-5c6eb368add0', '608c0f7f-d893-4c0e-949a-745dd2e8af32');
INSERT INTO public.user_roles (id, role_id) VALUES ('ff84295f-5c04-49ad-9976-7b737133fc80', '591228eb-c877-42ee-8c4b-a207b4a86d6e');
INSERT INTO public.user_roles (id, role_id) VALUES ('56d02039-3902-482e-b25b-58b01ce430ce', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');
INSERT INTO public.user_roles (id, role_id) VALUES ('b3723ffb-8f29-4b25-9a3e-1cd846fcbe9b', '41b186d8-c24e-4dd5-9bec-f75a26a53b55');


--
-- Name: activity_goal_activity_goal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.activity_goal_activity_goal_id_seq', 2, true);


--
-- Name: comment_comment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.comment_comment_id_seq', 74, true);


--
-- Name: exercise_exercise_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.exercise_exercise_id_seq', 226, true);


--
-- Name: exercise_in_training_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.exercise_in_training_id_seq', 6, true);


--
-- Name: follow_follow_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.follow_follow_id_seq', 32, true);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.hibernate_sequence', 3, true);


--
-- Name: likes_like_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.likes_like_id_seq', 184, true);


--
-- Name: opinion_opinion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.opinion_opinion_id_seq', 9, true);


--
-- Name: post_post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.post_post_id_seq', 143, true);


--
-- Name: role_request_role_req_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.role_request_role_req_id_seq', 76, true);


--
-- Name: sport_label_ailment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.sport_label_ailment_id_seq', 1, false);


--
-- Name: sport_label_sport_label_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.sport_label_sport_label_id_seq', 7, true);


--
-- Name: training_plan_training_plan_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.training_plan_training_plan_id_seq', 4, true);


--
-- Name: training_position_training_position_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.training_position_training_position_id_seq', 13, true);


--
-- Name: training_training_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.training_training_id_seq', 2, true);


--
-- PostgreSQL database dump complete
--

