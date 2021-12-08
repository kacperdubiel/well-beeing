SET
statement_timeout = 0;
SET
lock_timeout = 0;
SET
idle_in_transaction_session_timeout = 0;
SET
client_encoding = 'UTF8';
SET
standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET
check_function_bodies = false;
SET
xmloption = content;
SET
client_min_messages = warning;
SET
row_security = off;

--
-- Data for Name: profile_diet_calculation; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.profile_diet_calculation (id, basic_metabolism, bmi, bmi_result_type, suggested_calories,
                                             suggested_carbohydrates, suggested_fats, suggested_proteins)
VALUES ('0767bc4b-8045-4b2f-8168-fdc50cdf9926', 2453.51, 38.30316958728334, 'OBESE', 2927.4756, 332.90167500000007,
        81.31876666666668, 216);
INSERT INTO public.profile_diet_calculation (id, basic_metabolism, bmi, bmi_result_type, suggested_calories,
                                             suggested_carbohydrates, suggested_fats, suggested_proteins)
VALUES ('58761e6f-583c-44a4-8702-e7bf2a8123fa', 2453.51, 38.30316958728334, 'OBESE', 3172.826600000001,
        378.9049875000002, 88.13407222222224, 216);
INSERT INTO public.profile_diet_calculation (id, basic_metabolism, bmi, bmi_result_type, suggested_calories,
                                             suggested_carbohydrates, suggested_fats, suggested_proteins)
VALUES ('5821968a-88b5-487a-9b5e-504bc3a9f97e', 2453.51, 38.30316958728334, 'OBESE', 3172.826600000001,
        378.9049875000002, 88.13407222222224, 216);
INSERT INTO public.profile_diet_calculation (id, basic_metabolism, bmi, bmi_result_type, suggested_calories,
                                             suggested_carbohydrates, suggested_fats, suggested_proteins)
VALUES ('602b5483-c15f-49e2-8414-687fd82039e9', 1618.5099999999998, 24.677021338365513, 'HEALTHY', 2127.7649999999994,
        276.5559374999999, 59.104583333333316, 122.4);
INSERT INTO public.profile_diet_calculation (id, basic_metabolism, bmi, bmi_result_type, suggested_calories,
                                             suggested_carbohydrates, suggested_fats, suggested_proteins)
VALUES ('5f8f33c4-a4a7-468e-89ee-985f542147c6', 1414.996, 20.307412205454995, 'HEALTHY', 1836.6439599999999,
        239.9707425, 51.01788777777777, 104.4);
INSERT INTO public.profile_diet_calculation (id, basic_metabolism, bmi, bmi_result_type, suggested_calories,
                                             suggested_carbohydrates, suggested_fats, suggested_proteins)
VALUES ('915d918b-de42-44eb-b254-ef22efdfe019', 1987.71, 27.450604870886398, 'OVERWEIGHT', 2699.5986000000003,
        351.37473750000004, 74.98885000000001, 154.8);


--
-- Data for Name: profile_card; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height,
                                 training_activity_time_per_week, weight, diet_calculations_id)
VALUES ('2ed66237-5d17-4895-8f02-3f9d41955957', NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height,
                                 training_activity_time_per_week, weight, diet_calculations_id)
VALUES ('8cdb789d-8e03-493f-8e14-4119586c9852', NULL, NULL, 0, NULL, 0, 0, 0, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height,
                                 training_activity_time_per_week, weight, diet_calculations_id)
VALUES ('f7bff977-1394-45e1-8f9e-f254467e167d', NULL, NULL, 21, NULL, 0, 0, 120, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height,
                                 training_activity_time_per_week, weight, diet_calculations_id)
VALUES ('5b49e68e-43d2-11ec-81d3-0242ac130003', 'MAN', 'MEDIUM', 22, 'LOSE_WEIGHT', 1.85, 30, 85, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height,
                                 training_activity_time_per_week, weight, diet_calculations_id)
VALUES ('1234789d-8e03-493f-8e14-4119586c9852', 'MAN', 'HIGH', 34, NULL, 1.55, 0, 70, NULL);
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height,
                                 training_activity_time_per_week, weight, diet_calculations_id)
VALUES ('77f3277e-30e7-4d11-8b19-c5323035fef5', 'MAN', 'LOW', 31, 'LOSE_WEIGHT', 1.66, 0, 68,
        '602b5483-c15f-49e2-8414-687fd82039e9');
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height,
                                 training_activity_time_per_week, weight, diet_calculations_id)
VALUES ('614e1e97-aa66-4952-98ee-f049a9f74177', 'WOMAN', 'LOW', 23, 'LOSE_WEIGHT', 1.69, 5, 58,
        '5f8f33c4-a4a7-468e-89ee-985f542147c6');
INSERT INTO public.profile_card (id, esex, activity_level, age, diet_goal, height,
                                 training_activity_time_per_week, weight, diet_calculations_id)
VALUES ('28253bd8-ae57-4372-9de8-22a179f37d9c', 'MAN', 'MEDIUM', 21, 'LOSE_WEIGHT', 1.77, 30, 86,
        '915d918b-de42-44eb-b254-ef22efdfe019');


--
-- Data for Name: wb_user; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.wb_user (id, email, password, last_request_time)
VALUES ('2627a231-319d-47a7-87c4-291be6c5f117', 'kliuras@gmail.com',
        '$2a$10$lLx9jWjkyL8BItGQ0LuOX.5DDdi9h7vt6V0W8mDPp4dPv5AceqqOK', NULL);
INSERT INTO public.wb_user (id, email, password, last_request_time)
VALUES ('8389d13a-be54-4186-a27a-905258dcaba2', 'k.liurass@gmail.com',
        '$2a$10$JMYtjCMNyynd08YDPMF/zOdZuL0FqQPzBAajn0DVPZkPJsPE1hlHO', NULL);
INSERT INTO public.wb_user (id, email, password, last_request_time)
VALUES ('ba85c50b-0b36-417b-a3d1-6f6a5022e597', 'ooo@ooo.pl',
        '$2a$10$gRBExQM2JCC/6aEge4ZzEeuHMyD4nwY3YOVi4q3bmvtw1BGH3RTxa', NULL);
INSERT INTO public.wb_user (id, email, password, last_request_time)
VALUES ('b1bd6581-ff3f-470f-906b-2899540c930d', 'justyna@pwr.com',
        '$2a$10$oo9ut1UYlKXEBWasaA5DpOdm8hSLT90oYJXI3fFIrU4NclJ/ODkye', '2021-12-03 16:59:48.497');
INSERT INTO public.wb_user (id, email, password, last_request_time)
VALUES ('ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 'konrad@pwr.com',
        '$2a$10$fu4HqNUISU55KeqUagJ0leh1NQsrHkkqGigiZcaEUbJin3J1NjhCi', '2021-12-03 17:00:13.273');
INSERT INTO public.wb_user (id, email, password, last_request_time)
VALUES ('68514957-19f4-4764-a978-58d175948d7f', 'kordian@pwr.com',
        '$2a$10$/512kQeB7tYx1tluZHApQuivHm3UrRzoSvBSPlPfby3KRMsWpaNfW', '2021-12-03 17:01:18.616');
INSERT INTO public.wb_user (id, email, password, last_request_time)
VALUES ('573a6160-a438-4a33-b456-11cc39f9adcf', 'krystian@pwr.com',
        '$2a$10$6371LpevFU4BChgAO3m5M.7/5e.ZR9JoUjmfBcJsKzSjAqbmIEnSi', '2021-12-02 02:05:25.336');
INSERT INTO public.wb_user (id, email, password, last_request_time)
VALUES ('dc722668-12a5-4c90-92bc-f348a3bf92c4', 'janusz@pwr.com',
        '$2a$10$mPUr.heBgnUpDt7fYIcd1uU6MjEKCBMB/rnAJEeyyu7R/4zhXcS/6', '2021-12-03 15:50:30.138');


--
-- Data for Name: profile; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name,
                            profile_img_path, profile_user_id, profile_card_id, last_request_time)
VALUES ('WOMAN', '2021-11-07 00:00:00', '😎☀', 'NONE', 'NONE', 'Justyna', 'Nowak',
        '2021-11-30-00-04-56-851_profil2.jpg', 'b1bd6581-ff3f-470f-906b-2899540c930d',
        '614e1e97-aa66-4952-98ee-f049a9f74177', '2021-12-03 16:59:48.497');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name,
                            profile_img_path, profile_user_id, profile_card_id, last_request_time)
VALUES ('MAN', '2021-11-17 00:00:00', 'Heeej ;)', 'NONE', 'NONE', 'Krystian', 'Janowski',
        '2021-11-30-00-04-00-837_prfileDoctor.jpg', '573a6160-a438-4a33-b456-11cc39f9adcf',
        '1234789d-8e03-493f-8e14-4119586c9852', '2021-12-02 02:05:25.336');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name,
                            profile_img_path, profile_user_id, profile_card_id, last_request_time)
VALUES ('MAN', '2021-11-10 00:00:00', 'To ja :)', 'NONE', 'CARDIO', 'Konrad', 'Liuras',
        '2021-11-30-00-04-41-221_profil4.jpg', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        '28253bd8-ae57-4372-9de8-22a179f37d9c', '2021-12-03 17:00:13.273');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name,
                            profile_img_path, profile_user_id, profile_card_id, last_request_time)
VALUES ('MAN', '2021-11-07 00:00:00', 'Jestem trenerem personalnym oraz lekarzem!', 'NONE', 'CARDIO', 'Janusz',
        'Wiśniewski', '2021-11-30-00-04-24-167_profil5.jpg', 'dc722668-12a5-4c90-92bc-f348a3bf92c4',
        '77f3277e-30e7-4d11-8b19-c5323035fef5', '2021-12-03 15:50:30.138');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name,
                            profile_img_path, profile_user_id, profile_card_id, last_request_time)
VALUES ('MAN', '2021-11-29 00:00:00', 'Jestem trenerem!', 'NONE', 'NONE', 'Kordian', 'Lisiura',
        '2021-11-30-00-06-30-216_trainer.jpg', '68514957-19f4-4764-a978-58d175948d7f',
        '5b49e68e-43d2-11ec-81d3-0242ac130003', '2021-12-03 17:01:18.616');
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name,
                            profile_img_path, profile_user_id, profile_card_id, last_request_time)
VALUES ('MAN', '2021-11-07 00:00:00', NULL, 'NONE', 'NONE', 'Jan', 'Nowak', NULL,
        'ba85c50b-0b36-417b-a3d1-6f6a5022e597', '8cdb789d-8e03-493f-8e14-4119586c9852', NULL);
INSERT INTO public.profile (esex, birthday, description, e_nutrition_tag, e_sport_tag, first_name, last_name,
                            profile_img_path, profile_user_id, profile_card_id, last_request_time)
VALUES ('MAN', '2021-11-07 00:00:00', NULL, 'NONE', 'NONE', 'Marcin', 'Kowalski', NULL,
        '8389d13a-be54-4186-a27a-905258dcaba2', '2ed66237-5d17-4895-8f02-3f9d41955957', NULL);


--
-- Data for Name: activity_goal; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.activity_goal (activity_goal_id, goal_creation_date, goal_target_date, goal_type, numeric_value,
                                  text_value, owner)
VALUES (0, '2021-11-24 00:00:00', '2021-12-01 00:00:00', 'LOSE_WEIGHT', 0.7, NULL,
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.activity_goal (activity_goal_id, goal_creation_date, goal_target_date, goal_type, numeric_value,
                                  text_value, owner)
VALUES (2, '2021-11-29 06:32:33.936', '2022-01-19 06:15:54.563', 'LOSE_WEIGHT', 0.8, 'Nic',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.activity_goal (activity_goal_id, goal_creation_date, goal_target_date, goal_type, numeric_value,
                                  text_value, owner)
VALUES (3, '2021-11-30 02:06:20.463', '2021-12-16 02:06:19.405', 'LOSE_WEIGHT', 0.6, '',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.activity_goal (activity_goal_id, goal_creation_date, goal_target_date, goal_type, numeric_value,
                                  text_value, owner)
VALUES (4, '2021-11-30 02:54:12.922', '2021-12-27 02:54:10.06', 'LOSE_WEIGHT', 0.7, '',
        'b1bd6581-ff3f-470f-906b-2899540c930d');
INSERT INTO public.activity_goal (activity_goal_id, goal_creation_date, goal_target_date, goal_type, numeric_value,
                                  text_value, owner)
VALUES (5, '2021-12-03 14:18:16.716', '2021-12-15 14:18:03.838', 'MAINTAIN_WEIGHT', 0, '',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');


--
-- Data for Name: ailment; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.ailment (id, change_in_calories, change_in_proteins_gram_per_kilogram, description,
                            is_low_glycemic_index_recommended, name, type)
VALUES ('d8ed7492-79fa-454d-83fb-702bb41be2f3', 0, 0, 'Choroba', true, 'Cukrzyca', 'ILLNESS');
INSERT INTO public.ailment (id, change_in_calories, change_in_proteins_gram_per_kilogram, description,
                            is_low_glycemic_index_recommended, name, type)
VALUES ('6ae9b375-67a4-4c8c-ab35-a1b0de4f14c0', 0, 0, 'Choroba', false, 'Choroba 1', 'ILLNESS');
INSERT INTO public.ailment (id, change_in_calories, change_in_proteins_gram_per_kilogram, description,
                            is_low_glycemic_index_recommended, name, type)
VALUES ('4537f410-3dbe-4919-b2fe-750fff2739df', 0, 0, 'Choroba', false, 'Choroba 2', 'ILLNESS');


--
-- Data for Name: dietician_profile; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.dietician_profile (user_id)
VALUES ('ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');


--
-- Data for Name: nutrition_label; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: ailment_nutrition_label; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: sport_label; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.sport_label (sport_label_id, name)
VALUES (1, 'Obciąża nogi');
INSERT INTO public.sport_label (sport_label_id, name)
VALUES (2, 'Obciąża ręce');
INSERT INTO public.sport_label (sport_label_id, name)
VALUES (3, 'Regenruje nogi');
INSERT INTO public.sport_label (sport_label_id, name)
VALUES (4, 'Wzmacnia kondycję');
INSERT INTO public.sport_label (sport_label_id, name)
VALUES (481, 'Legs');
INSERT INTO public.sport_label (sport_label_id, name)
VALUES (482, 'Chest');
INSERT INTO public.sport_label (sport_label_id, name)
VALUES (483, 'Abs');
INSERT INTO public.sport_label (sport_label_id, name)
VALUES (484, 'Shoulders');
INSERT INTO public.sport_label (sport_label_id, name)
VALUES (485, 'Arms');
INSERT INTO public.sport_label (sport_label_id, name)
VALUES (486, 'Back');
INSERT INTO public.sport_label (sport_label_id, name)
VALUES (487, 'Calves');
INSERT INTO public.sport_label (sport_label_id, name)
VALUES (506, 'Json1');
INSERT INTO public.sport_label (sport_label_id, name)
VALUES (503, 'Nowaa');
INSERT INTO public.sport_label (sport_label_id, name)
VALUES (504, 'New label');
INSERT INTO public.sport_label (sport_label_id, name)
VALUES (505, 'Wzmacnia ABS');


--
-- Data for Name: ailment_sport_label; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.post (post_id, added_date, post_content, post_img_path, creator_id, is_deleted,
                         is_sharing, original_post_id, sharing_counter)
VALUES (2, '2021-11-09 09:11:08.879', 'Post ze zdjęciem', '2021-11-15-20-42-31-465_krasnal.jpg',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', false, false, NULL, 0);
INSERT INTO public.post (post_id, added_date, post_content, post_img_path, creator_id, is_deleted,
                         is_sharing, original_post_id, sharing_counter)
VALUES (9, '2021-11-15 20:41:05.513', '6', '2021-11-09-09-30-31-082_dog-3.jpg',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', false, false, NULL, 0);
INSERT INTO public.post (post_id, added_date, post_content, post_img_path, creator_id, is_deleted,
                         is_sharing, original_post_id, sharing_counter)
VALUES (1, '2021-11-09 09:10:58.475', 'Nowy post', '2021-11-09-09-23-32-906_dog-1.jpg',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', false, false, NULL, 0);
INSERT INTO public.post (post_id, added_date, post_content, post_img_path, creator_id, is_deleted,
                         is_sharing, original_post_id, sharing_counter)
VALUES (10, '2021-11-18 21:08:47.561', 'Siema', NULL, '68514957-19f4-4764-a978-58d175948d7f', false,
        false, NULL, 0);
INSERT INTO public.post (post_id, added_date, post_content, post_img_path, creator_id, is_deleted,
                         is_sharing, original_post_id, sharing_counter)
VALUES (7, '2021-11-15 20:40:05.447', 'Test 4', '2021-11-15-20-40-05-526_krasnal.jpg',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', false, false, NULL, 0);
INSERT INTO public.post (post_id, added_date, post_content, post_img_path, creator_id, is_deleted,
                         is_sharing, original_post_id, sharing_counter)
VALUES (6, '2021-11-15 20:39:55.847', 'Test 3', NULL, 'dc722668-12a5-4c90-92bc-f348a3bf92c4', false,
        false, NULL, 0);
INSERT INTO public.post (post_id, added_date, post_content, post_img_path, creator_id, is_deleted,
                         is_sharing, original_post_id, sharing_counter)
VALUES (3, '2021-11-09 09:29:41.698', 'Nowy post ze zdjęciem', '2021-11-09-09-30-31-081_dog-3.jpg',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', true, false, NULL, 0);
INSERT INTO public.post (post_id, added_date, post_content, post_img_path, creator_id, is_deleted,
                         is_sharing, original_post_id, sharing_counter)
VALUES (5, '2021-11-15 20:39:44.409', 'Już dziś wieczorem podeślę wam nowy plan treningowy!

Czekajcie!', NULL, 'dc722668-12a5-4c90-92bc-f348a3bf92c4', false, false, NULL, 0);
INSERT INTO public.post (post_id, added_date, post_content, post_img_path, creator_id, is_deleted,
                         is_sharing, original_post_id, sharing_counter)
VALUES (8, '2021-11-15 20:40:57.817', '5', NULL, 'dc722668-12a5-4c90-92bc-f348a3bf92c4', false, false, NULL, 1);
INSERT INTO public.post (post_id, added_date, post_content, post_img_path, creator_id, is_deleted,
                         is_sharing, original_post_id, sharing_counter)
VALUES (11, '2021-11-25 22:00:50.049', 'Udostępniam 5', NULL, 'dc722668-12a5-4c90-92bc-f348a3bf92c4',
        false, true, 8, 0);
INSERT INTO public.post (post_id, added_date, post_content, post_img_path, creator_id, is_deleted,
                         is_sharing, original_post_id, sharing_counter)
VALUES (4, '2021-11-15 20:39:22.758', 'Zapraszam na moje treningi', NULL,
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', true, false, NULL, 0);
INSERT INTO public.post (post_id, added_date, post_content, post_img_path, creator_id, is_deleted,
                         is_sharing, original_post_id, sharing_counter)
VALUES (12, '2021-11-30 00:12:56.743', 'Cześć wszystkim!
', NULL, '68514957-19f4-4764-a978-58d175948d7f', false, false, NULL, 0);


--
-- Data for Name: comment; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted)
VALUES (1, '2021-11-25 22:01:06.961', 'Super 5! Pjona', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', 8, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted)
VALUES (2, '2021-11-25 22:01:12.787', '😊😊😊', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', 8, false);
INSERT INTO public.comment (comment_id, added_date, com_content, commenter_id, post_id, is_deleted)
VALUES (3, '2021-11-30 03:38:12.201', 'Cześć Kordian!', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 12, false);


--
-- Data for Name: conversations; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.conversations (id, connection_type, is_read_by_first_user, is_read_by_second_user, last_message_date,
                                  first_profile_profile_user_id, second_profile_profile_user_id)
VALUES ('57b4fa54-2158-4dc8-81b3-e6ffe6bf9c1f', 1, true, true, '2021-11-30 00:32:20.443',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.conversations (id, connection_type, is_read_by_first_user, is_read_by_second_user, last_message_date,
                                  first_profile_profile_user_id, second_profile_profile_user_id)
VALUES ('1cd05662-d200-4200-bb85-371be9c945bb', 0, true, true, '2021-11-30 03:28:44.018',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.conversations (id, connection_type, is_read_by_first_user, is_read_by_second_user, last_message_date,
                                  first_profile_profile_user_id, second_profile_profile_user_id)
VALUES ('a5ddb009-71e5-44a7-a724-f01eb38bee3b', 3, true, true, '2021-11-30 03:45:40.425',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.conversations (id, connection_type, is_read_by_first_user, is_read_by_second_user, last_message_date,
                                  first_profile_profile_user_id, second_profile_profile_user_id)
VALUES ('3d1e6247-3358-4dcc-bcca-9c3b9999f696', 0, false, true, '2021-12-03 17:00:12.469',
        'b1bd6581-ff3f-470f-906b-2899540c930d', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');


--
-- Data for Name: diet; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: diet_calc_meal_suggestions; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories,
                                               diet_calculation_id)
VALUES ('45341278-32a3-4147-a11a-378ad997107e', 5, 'BREAKFAST', 531.9412499999999,
        '602b5483-c15f-49e2-8414-687fd82039e9');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories,
                                               diet_calculation_id)
VALUES ('d4ba4fad-a970-4039-806a-50f2606d174b', 5, 'LUNCH', 212.77649999999994, '602b5483-c15f-49e2-8414-687fd82039e9');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories,
                                               diet_calculation_id)
VALUES ('852a15f5-303b-48d0-8bb0-35ca864ed000', 5, 'DINNER', 851.1059999999998, '602b5483-c15f-49e2-8414-687fd82039e9');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories,
                                               diet_calculation_id)
VALUES ('65e5d8a9-ed35-4efb-b764-2b15593d06e5', 5, 'SNACK', 212.77649999999994, '602b5483-c15f-49e2-8414-687fd82039e9');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories,
                                               diet_calculation_id)
VALUES ('b1edb9bf-174e-4386-9c62-2b55e294a91a', 5, 'SUPPER', 319.1647499999999, '602b5483-c15f-49e2-8414-687fd82039e9');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories,
                                               diet_calculation_id)
VALUES ('b0c693a2-a849-49c5-a4d4-2b7055c87a08', 5, 'BREAKFAST', 459.16098999999997,
        '5f8f33c4-a4a7-468e-89ee-985f542147c6');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories,
                                               diet_calculation_id)
VALUES ('849b2d77-b9ee-471f-b58c-f8be2c97a8ef', 5, 'LUNCH', 183.664396, '5f8f33c4-a4a7-468e-89ee-985f542147c6');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories,
                                               diet_calculation_id)
VALUES ('a2d445f5-d984-4942-b74c-5c06a531ec58', 5, 'DINNER', 734.657584, '5f8f33c4-a4a7-468e-89ee-985f542147c6');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories,
                                               diet_calculation_id)
VALUES ('dd902be8-0741-48db-af97-c256b529c662', 5, 'SNACK', 183.664396, '5f8f33c4-a4a7-468e-89ee-985f542147c6');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories,
                                               diet_calculation_id)
VALUES ('e3d3775c-8353-463d-b43d-55a5010fb583', 5, 'SUPPER', 275.49659399999996,
        '5f8f33c4-a4a7-468e-89ee-985f542147c6');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories,
                                               diet_calculation_id)
VALUES ('9f3d18c8-bc3c-44fe-94db-7b47ec88ce19', 1, 'BREAKFAST', 674.8996500000001,
        '915d918b-de42-44eb-b254-ef22efdfe019');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories,
                                               diet_calculation_id)
VALUES ('2dd754e6-81bf-4829-b80f-2e7fdd1b23e4', 1, 'LUNCH', 269.95986000000005, '915d918b-de42-44eb-b254-ef22efdfe019');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories,
                                               diet_calculation_id)
VALUES ('1d87debb-a3c7-4e62-b34e-20711e0b6653', 1, 'DINNER', 1079.8394400000002,
        '915d918b-de42-44eb-b254-ef22efdfe019');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories,
                                               diet_calculation_id)
VALUES ('24726a14-a5c8-4407-9197-031b06b6544e', 1, 'SNACK', 269.95986000000005, '915d918b-de42-44eb-b254-ef22efdfe019');
INSERT INTO public.diet_calc_meal_suggestions (id, glycemic_index_level, meal_type, number_of_calories,
                                               diet_calculation_id)
VALUES ('ad832378-0ad2-44dd-8c49-ab1987457851', 1, 'SUPPER', 404.93979, '915d918b-de42-44eb-b254-ef22efdfe019');


--
-- Data for Name: diet_nutrition_label; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: dish; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: dish_meal_type; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: dish_nutrition_label; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: dish_product_detail; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: doctor_profile; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.doctor_profile (user_id)
VALUES ('573a6160-a438-4a33-b456-11cc39f9adcf');
INSERT INTO public.doctor_profile (user_id)
VALUES ('dc722668-12a5-4c90-92bc-f348a3bf92c4');


--
-- Data for Name: doctor_specialization; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.doctor_specialization (id, name)
VALUES ('94903fc3-2845-4d4f-8be1-1256b3147e5d', 'Dermatolog');
INSERT INTO public.doctor_specialization (id, name)
VALUES ('262677a8-6b6f-424b-b41e-4fd25ced6b08', 'Kardiolog');


--
-- Data for Name: doctors_specializations; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.doctors_specializations (user_id, specialization_id)
VALUES ('dc722668-12a5-4c90-92bc-f348a3bf92c4', '94903fc3-2845-4d4f-8be1-1256b3147e5d');
INSERT INTO public.doctors_specializations (user_id, specialization_id)
VALUES ('dc722668-12a5-4c90-92bc-f348a3bf92c4', '262677a8-6b6f-424b-b41e-4fd25ced6b08');
INSERT INTO public.doctors_specializations (user_id, specialization_id)
VALUES ('573a6160-a438-4a33-b456-11cc39f9adcf', '262677a8-6b6f-424b-b41e-4fd25ced6b08');


--
-- Data for Name: exercise; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (452, NULL, 'OTHER', '<p>Lying on stomach with head on towel.</p>
<p>Stretch arms straight out to your sides.</p>
<p>Slowly lift your arms, pulling your shoulderblades together, hold for 3 seconds.</p>
<p> </p>', false, 3, 'Prone Scapular Retraction - Arms at Side', NULL, NULL, true);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (81, 'ASDASDAsdasdas', 'STRENGTH', 'dasdsadasdas', false, 4.5, 'Squat', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (80, 'Nowe ćwiczenie z opisem Nowe ćwiczenie z opisem bezs', 'STRENGTH',
        'Nowe ćwiczenie z opisem Nowe ćwiczenie z opisem Nowew', false, 4.5, 'Nowe ćwiczeniewr',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (84, 'Opis', 'STRENGTH', 'Instr', false, 4, 'Testowe', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (83, 'Nowe ćwiczenie bez opisu 2', 'CARDIO', 'Instrukcja do nowego ćwiczenia', false, 13, 'Nowe',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (543, 'Regen', 'OTHER', 'Nogi', false, 3, 'Not deleted', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (82, 'aaa', 'STRENGTH', 'abc', false, 3.8, 'Pompki', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (79, NULL, 'OTHER', NULL, false, 5.5, 'Exercise_2', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (78, NULL, 'OTHER', NULL, false, 3.4, 'Exercise_8', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (317, NULL, 'OTHER', '<p>Perform leg squats with barbell behind your legs</p>', false, 2.5,
        'Barbell Hack Squats', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (458, NULL, 'OTHER', '<p>Deadlift to be done using a Smith machine or a free rack. Bar or barbell hould be just right under the knee cap level. Lift using the glutes and through the heels, then come back to starting postion with a control movement of 2 seconds.</p>
<p>This exercise targets mainly the lower back and glutes.</p>', false, 0, 'Rack Deadlift', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (449, NULL, 'OTHER', '<p>Get into a position on the floor supporting your weight on your forearms and toes. Arms are bent and directly below the shoulder. </p>
<p>Keep your body straight at all times and hold this position as long as possible. To increase difficulty an arm or leg can be raised while performing this exercise.  </p>',
        false, 0, 'Plank', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (448, NULL, 'OTHER', '<p>One legged squat</p>', false, 0, 'Pistol Squat', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (463, NULL, 'OTHER', '<p>Upper chest focuses exercise that also works triceps</p>', false, 7.4,
        'Reverse Grip Bench Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (462, NULL, 'OTHER',
        'The reverse-grip barbell curl is a variation on the biceps curl where the palms face downward. The switch from an underhand to an overhand grip brings the forearm and brachialis muscles more into the exercise.',
        false, 5.6, 'Reverse Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (453, NULL, 'OTHER',
        '<p>Grab the pull up bar with a wide grip, the body is hanging freely. Keep your chest out and pull yourself up till your chin reaches the bar or it touches your neck, if you want to pull behind you. Go with a slow and controlled movement down, always keeping the chest out.</p>',
        false, 6, 'Pull-ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (387, NULL, 'OTHER',
        '<p>(A) Get in high plank position on your hands and toes.(B) Shift your weight to your left hand as you turn your body to the right; bend your right leg behind you and extend your right arm up. Return to the center and repeat on the opposite side. Continue, alternating sides.<strong>Make it easier:</strong> Don’t raise your arm after you bend your leg behind you.<strong>Make it harder:</strong> Balance with your arm and leg extended for two counts.</p>',
        false, 5.6, 'Full Sit Outs', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (450, NULL, 'OTHER', '<p>Olympic weight lifting</p>', false, 5.6, 'Power Clean', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (505, NULL, 'OTHER',
        '<p>Get onto the calf raises machine, you should able to completely push your calves down. Stand straight, don''t make a hollow back and don''t bend your legs. Pull yourself up as high as you can. Make a small pause of 1 - 2 seconds and go slowly down.</p>',
        false, 0, 'Standing Calf Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (399, NULL, 'OTHER', '<p>Start with your feet shoulder width apart and arms slightly behind your back.</p>
<p>As you descend towards the floor, raise your heels off the ground, while keeping your back as vertical  as possible. </p>
<p>Upon attaining the bottom position, touch the hands to the heels.</p>
<p>Then stand up ending with the heels on the ground, arms extended in front of the chest then rowing into the start position.</p>',
        false, 7.4, 'Hindu Squats', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (540, 'Nowe ćwiczenie z Gliwic', 'STRENGTH', 'Tylko instrukcja wideo', false, 3.7, 'Gliwice',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', '2021-11-25-19-25-51-461_gliwice.mp4', false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (454, NULL, 'OTHER', '', false, 5.6, 'Pull Ups on Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (509, NULL, 'OTHER',
        '<p>Use the straight bar attachment on a high pulley. Grasp the two ends of the bar with your palms facing downward and your arms straight out in front of you. Pull your hands down towards your hips, while keeping your arms straight, then raise them back up to the starting position.</p>',
        false, 0, 'Straight-arm Pull Down (bar Attachment)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (508, NULL, 'OTHER', '<ul>
<li>Keep legs straight</li>
<li>Keep back straight</li>
</ul>', false, 0, 'Stiff-legged Deadlifts', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (542, '2', 'CARDIO', '3', false, 1, '1', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', NULL, true);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (507, NULL, 'OTHER', '<p>Ride a Stationary Bike with various tensions.</p>', false, 0, 'Stationary Bike', NULL,
        NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (506, NULL, 'OTHER',
        '<p>Grab a wrist roller tool with both hands while standing with your feet about shoulder width apart. If your gym does not have a wrist roller tool, you can easily put one together. All you need is a 5 or 10 pound weight plate, a strong thin rope about 3 feet long and a 6-8 inch stick or bar. Securely fasten the rope to the middle of the bar/stick and tie the other end of the rope to the weight plate. To begin this exercise, grab the bar/stick with both hands using an overhand grip. Extend both arms straight out in front of you, parallel to the floor. Next, roll the weight up from the floor by rapidly twisting the bar/stick with your hands and wrists. Once the weight reaches the top, slowly lower the plate back to the floor by reversing the motion of your hands and wrists. Repeat (if you can!).</p>',
        false, 0, 'Standing Rope Forearm', NULL, '2021-11-12-17-38-20-050_exercise_3_rope (1).mp4', false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (497, NULL, 'OTHER',
        '<p>Perform a standard bench press on the smith machine, but have your hands on the bar about shoulder width apart, and keep your elbows close to your body.</p>',
        false, 0, 'Smith Machine Close-grip Bench Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (496, NULL, 'OTHER', '<p>Do a single, double footed jump for each swing of the rope.</p>
<p>Work on a smooth, rhythmical movement, bouncing lightly on the balls of your feet.</p>', false, 0,
        'Skipping - Standard', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (511, NULL, 'OTHER', '<ol>
<li>Begin with a bar loaded on the ground. Approach the bar so that the bar intersects the middle of the feet. The feet should be set very wide, near the collars. Bend at the hips to grip the bar. The arms should be directly below the shoulders, inside the legs, and you can use a pronated grip, a mixed grip, or hook grip. Relax the shoulders, which in effect lengthens your arms.</li>
<li>Take a breath, and then lower your hips, looking forward with your head with your chest up. Drive through the floor, spreading your feet apart, with your weight on the back half of your feet. Extend through the hips and knees.</li>
<li>As the bar passes through the knees, lean back and drive the hips into the bar, pulling your shoulder blades together.</li>
<li>Return the weight to the ground by bending at the hips and controlling the weight on the way down.</li>
</ol>', false, 0, 'Sumo Deadlift', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (510, NULL, 'OTHER',
        '<p>Use the rope attachment on a high pulley. Grasp the two ends of the rope with your arms straight out in front of you. Pull your hands down towards your hips, while keeping your arms straight, then raise them back up to the starting position.</p>',
        false, 0, 'Straight-arm Pull Down (rope Attachment)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (501, NULL, 'OTHER', '<p>Jump wide, then close</p>', false, 0, 'Squat Jumps', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (500, NULL, 'OTHER',
        '<p>Lie on your back with your legs straight and arms at your sides, keeping your elbows bent at 90 degrees. As you sit up, twist your upper body to the left and bring your left knee toward your right elbow while you swing your left arm back. Lower your body to the starting position, and repeat to your right. That''s 1 rep.</p>',
        false, 0, 'Splinter Sit-ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (499, NULL, 'OTHER', '<p>Deadlift with short (&lt;1min) rest between sets.</p>', false, 0, 'Speed Deadlift',
        NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (498, NULL, 'OTHER',
        '<p>Stand with your feet at hip width and your shins against the bar. Grasp the bar at double shoulder width and, keeping your lower back flat, drive your heels into the floor to begin lifting the bar. When it''s above your knees, explosively extend your hips and shrug your shoulders. Let the momentum carry the weight overhead.</p>',
        false, 0, 'Snach', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (489, NULL, 'OTHER', '<p>With a weight in one hand, lie on your side opposite the weight. Keep your knees slightly bent. Hold your elbow against your side, and extend your upper arm straight ahead of you. While continuing to hold your elbow against your side, rotate your upper arm 90 degrees upwards.</p>
<p>It is helpful to place a towel under your armpit to help with the form on this exercise. Placing a support under your head for the duration of the exercise is also a good idea.</p>',
        false, 0, 'Side-lying External Rotation', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (488, NULL, 'OTHER',
        '<p>AKA dumbbell side bends. Stand in line with the hips with slightly bent knees, maintain the natural curvature of the spine, hand stretched by the body, grip the barbell with one hand. <em> </em>Make slow and controlled torso side flexions till you reach the angle of approximately 45°.</p>',
        false, 0, 'Side Dumbbell Trunk Flexion', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (503, NULL, 'OTHER',
        'The burpee, or squat thrust, is a full body exercise used in strength training and as an aerobic exercise. The basic movement is performed in four steps and known as a four-count burpee: Begin in a standing position. Move into a squat position with your hands on the ground. (count 1) Kick your feet back into a plank position, while keeping your arms extended. (count 2) Immediately return your feet into squat position. (count 3) Stand up from the squat position (count 4)',
        false, 0, 'Squat Thrust', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (502, NULL, 'OTHER', '<p>Make sure you have put the barbell at a height where you can comfortably take it out and put it back in. Take it out and make yourself ready:</p>
<ul>
<li>The bar is somewhat lower than your shoulders</li>
<li>The feet are quite apart and point out</li>
<li>The head is in your neck and looks up</li>
<li>The chest is out</li>
</ul>
<p>Go now slowly down, till your thighs are parallel with the floor, not lower. The knees point outwards, your butt, out. Make a small pause of 1 second and with as much energy as you can, push the weight up. Make a pause of 2 seconds and repeat.</p>',
        false, 0, 'Squats', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (493, NULL, 'OTHER',
        '<p>Sit on the preacher curl bench and perform a bicep curl with a dumbbell in one hand. Your other hand can be at rest, or beneath your curling arm''s elbow.</p>',
        false, 0, 'Single-arm Preacher Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (492, NULL, 'OTHER', '<p>-start in push up position</p>
<p>-lean the body weight to the right side, and complete a push up with the chest over the right hand</p>
<p>-come back to the centered position</p>
<p>-on rep 2, lean to the left side</p>', false, 0, 'Side to Side Push Ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (541, 'Opis', 'CARDIO', 'Instrukcja wideo', false, 12, 'Gliwice 2', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', NULL,
        false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (544, 'Wzmacniające ćwiczenie testowe na brzuch', 'STRENGTH', 'W wideo', false, 3.7, 'Ćwiczenie testowe',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', '2021-11-25-21-59-09-984_exercise_1 (1).mp4', false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (314, NULL, 'OTHER', '<p>Grab dumbbells and extend arms to side and hold as long as you can</p>', false, 2,
        'Axe Hold', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (315, NULL, 'OTHER',
        '<p>Place a barbell in a rack just below shoulder-height. Dip under the bar to put it behind the neck across the top of the back, and grip the bar with the hands wider than shoulder-width apart. Lift the chest up and squeeze the shoulder blades together to keep the straight back throughout the entire movement. Stand up to bring the bar off the rack and step backwards, then place the feet so that they are a little wider than shoulder-width apart. Sit back into hips and keep the back straight and the chest up, squatting down so the hips are below the knees. From the bottom of the squat, press feet into the ground and push hips forward to return to the top of the standing position.</p>',
        false, 2, 'Back Squat', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (451, NULL, 'OTHER',
        '<p> Place the EZ curl bar on the rest handles in front of the preacher bench. Lean over the bench and grab the EZ curl bar with palms up. Sit down on the preacher bench seat so your upper arms rest on top of the pad and your chest is pressed against the pad. Lower the weight until your elbows are extended and arms are straight. Bring the weights back up to the starting point by contracting biceps. Repeat</p>',
        false, 7.4, 'Preacher Curls', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (432, NULL, 'OTHER',
        '<p>Adjust seat height so that the handles are at the bottom of your pectorals or just below.</p>', false, 7.4,
        'Leverage Machine Iso Row', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (408, NULL, 'OTHER', '<p>Perform the plank with legs elevated, feet on a gymball. Once stabilised, slowly move one foot sideways off the ball, then make it touch the floor, then come back to starting position. Alternate with the other foot.</p>
<p>This is a core exercise.</p>', false, 6, 'Incline Plank With Alternate Floor Touch', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (400, NULL, 'OTHER',
        'Lying down on your back, with your feet flat on the floor. Raise your hips up evenly as high as you can and hold for as long as you can.',
        false, 3, 'Hip Raise, Lying', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (406, NULL, 'OTHER', '<ul>
<li>Bench should be angled anywhere from 30 to 45 degrees</li>
<li>Be sure to press dumbbells straight upward (perpendicular to the floor)</li>
</ul>', false, 7.4, 'Incline Dumbbell Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (393, NULL, 'OTHER',
        'The handstand push-up (press-up) - also called the vertical push-up (press-up) or the inverted push-up (press-up) also called commandos- is a type of push-up exercise where the body is positioned in a handstand. For a true handstand, the exercise is performed free-standing, held in the air. To prepare the strength until one has built adequate balance, the feet are often placed against a wall, held by a partner, or secured in some other way from falling. Handstand pushups require significant strength, as well as balance and control if performed free-standing.',
        false, 4.3, 'Handstand Pushup', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (412, NULL, 'OTHER',
        '<p>A jumping jack or star jump, also called side-straddle hop in the US military, is a physical jumping exercise performed by jumping to a position with the legs spread wide and the hands going overhead, sometimes in a clap, and then returning to a position with the feet together and the arms at the sides</p>',
        false, 6, 'Jumping Jacks', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (437, NULL, 'OTHER', '<p>This is an excercise for problems with the levator muscles. Primary  Infraspinatus, secondary Teres Minor.</p>
<p>Lying on side. Keep elbow on waist and in 90 dgr angle. Rotate towards stomach. Add weight as fit.</p>', false, 7.4,
        'Lying Rotator Cuff Exercise', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (405, NULL, 'OTHER',
        '<p>Use inclined bench. Hold dumbbells straight out to your sides, elbows slightly bent. Bring arms together above you, keeping angle of elbows fixed.</p>',
        false, 5.6, 'Incline Dumbbell Flye', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (545, 'A', 'STRENGTH', 'B', false, 23, 'Test PU', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (444, NULL, 'OTHER',
        '<p>Lower the bar to your chest and pause (but do not rest) there for 2 seconds. Press back up. use the same weight you would on bench press, but perform only single reps. Total the number of reps you did in one set of bench press (if you did 3 sets of 8 do 8 sinlge pause bench reps.</p>',
        false, 7.4, 'Pause Bench', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (443, NULL, 'OTHER',
        '<p>The barbell is held overhead in a wide-arm snatch grip; however, it is also possible to use a closer grip if balance allows.</p>',
        false, 5.6, 'Overhead Squat', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (390, NULL, 'OTHER', '<p>Hold two dumbbells and sit on a bench with a straight back, the shoulders are slightly rolled backwards. Your pals point to your body. Bend the arms and bring the weight up with a fast movement. Don''t rotate your hands, as with the curls. Without any pause bring the dumbbell down with a slow, controlled movement.</p>
<p>Don''t swing your body during the exercise, the biceps should do all the work here. The elbows are at your side and don''t move.</p>',
        false, 6, 'Hammercurls', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (435, NULL, 'OTHER', '<p>The exercise is the same as the regular long pulley, but with a narrow grip:</p>
<p>Sit down, put your feet on the supporting points and grab the bar with a wide grip. Pull the weight with a rapid movement towards your belly button, not upper. Keep your arms and elbows during the movement close to your body. Your shoulders are pulled together. Let the weight slowly down till your arms are completely stretched.</p>',
        false, 6, 'Long-Pulley, Narrow', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (360, NULL, 'OTHER',
        '<p>Deadhang performed on an edge either with or without added weight (adujst edge or weight to adjust difficulty)</p>',
        false, 0, 'Deadhang', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (436, NULL, 'OTHER',
        '<p>Unrack the bar and set your stance wide, beyond your hips.  Push your hips back and sit down to a box that takes you below parallel.  Sit completely down, do not touch and go.  Then explosively stand up.  Stay tight in your upper back and torso throughout the movement.</p>',
        false, 5.6, 'Low Box Squat - Wide Stance', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (395, NULL, 'OTHER', '<p>Grab two cables stand in the middle so both have tension and hold</p>', false, 4.3,
        'Hercules Pillars', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (384, NULL, 'OTHER', '<p>Hold the SZ-bar and lay down on a flat bench in such a way that around 1/4 of your head is over the edge. Stretch your arms with the bar and bend them so that the bar is lowered. Just before it touches your forehead, push it up.</p>
<p>Pay attention to your elbows and arms: only the triceps are doing the work, the rest of the arms should not move.</p>',
        false, 0, 'French Press (skullcrusher) SZ-bar', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (414, NULL, 'OTHER', '<p>.</p>', false, 7.4, 'Lateral Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (398, NULL, 'OTHER',
        '<p>Use a light barbell, perform explosive lift up starting from underneath knee cap level. Lift/raise explosively using hips, at shoulder level. Tempo: 2111</p>',
        false, 5.6, 'High Pull', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (388, NULL, 'OTHER',
        '<p>Lie on you back with your hips and knees flexed, feet on the ground. From this position, raise your butt off of the ground to a height where your body makes a straight line from your knees to your shoulders. To make the exercise more intense, you can add weight by letting a barbell rest on your hips as you complete the motion, or you can put your feet on a slightly higher surface such as a step or a bench.</p>',
        false, 7.4, 'Glute Bridge', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (546, 'Some description', 'CARDIO', 'Some instruction', false, 5.4, 'Exercise from JSON', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (391, NULL, 'OTHER', '<p>Take a cable in your hands (palms parallel, point to each other), the body is straight. Bend the arms and bring the weight up with a fast movement. Without any pause bring it back down with a slow, controlled movement, but don''t stretch completely your arms.</p>
<p>Don''t swing your body during the exercise, the biceps should do all the work here. The elbows are at your side and don''t move.</p>',
        false, 2.7, 'Hammercurls on Cable', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (411, NULL, 'OTHER', '<p>Get your shoes on, go outside and start running at a moderate pace.</p>', false, 3,
        'Jogging', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (410, NULL, 'OTHER', '<p>Assume push-up position, with hands slightly wider than shoulder width.</p>
<p>Shift body weight as far as possible to one side, allowing the elbow on that side to flex. </p>
<p>Reverse the motion, moving completely over to the other side.</p>
<p>Return to the starting position, and repeat for the desired number of repetitions.</p>', false, 7.4,
        'Isometric Wipers', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (401, NULL, 'OTHER',
        '<p>The bar should go directly on your upper thigh, directly below your crotch. Your feet should be directly under your knees. Push your hips up so that you form a straight line from your knees to your shoulders. Use a pad for comfort.</p>',
        false, 6, 'Hip Thrust', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (434, NULL, 'OTHER',
        '<p>Sit down, put your feet on the supporting points and grab the bar with a wide grip. Pull the weight with a rapid movement towards your belly button, not upper. Keep your arms and elbows during the movement close to your body. Your shoulders are pulled together. Let the weight slowly down till your arms are completely stretched.</p>',
        false, 3, 'Long-Pulley (low Row)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (439, NULL, 'OTHER',
        '<p>On an SZ-bar grip your hands on the outside of each bend and stand with your arms straight down, palms facing your legs. Pull the bar (bending your arms at the elbow) to your chest, and the push the bar above your head (arms as straight as possible). Return the bar to your chest by dropping your arms at the elbows. Return the bar to it''s origional position (stand with your arms straight down, palms facing your legs.)</p>',
        false, 6, 'Military Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (438, NULL, 'OTHER', '', false, 3, 'MGM Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (429, NULL, 'OTHER',
        '<p>Lay down on a bench and hold onto the recliner with your hands to keep you stable. Hold your legs straight and lift them till they make an angle of about 45°. Make a short pause of 1 sec. and go slowly down to the initial position. To increase the intensity you can make a longer pause of 7 sec. every 5th time.</p>',
        false, 3, 'Leg Raises, Lying', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (428, NULL, 'OTHER',
        'The leg raise is a strength training exercise which targets the iliopsoas (the anterior hip flexors). Because the abdominal muscles are used isometrically to stabilize the body during the motion, leg raises are also often used to strengthen the rectus abdominis muscle and the internal and external oblique muscles.',
        false, 7.4, 'Leg Raise', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (426, NULL, 'OTHER', '<p>Sit on the machine and put your feet on the platform, a bit more than shoulder wide. The feet are turned outwards by a few degrees.</p>
<p>Lower the weight so much, that the knees form a right angle. Push immediately the platform up again, without any pause. When in the lower position, the knees point a bit outwards and the movement should be always fluid.</p>',
        false, 5.6, 'Leg Presses (wide)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (376, NULL, 'OTHER', '<p>En face</p>
<ol>
<li>Tirer</li>
<li>relacher doucement</li>
</ol>', false, 0, 'Extenseurs - Face', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (397, NULL, 'OTHER', '<p>-Start with legs at a comfortable standing width</p>
<p>-Run in place, bringing knees to or above waist level</p>', false, 0, 'High Knees', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (396, NULL, 'OTHER', '<p>-Start with legs slightly wider than shoulder width</p>
<p>-Drop into a bodyweight squat</p>
<p>-As you hit the bottom of the squat, explode upwards into a jump while simultaneously tucking your knees into your chest midflight.  Remain tucked until the apex of your jump.</p>
<p>-Land on both feet, making sure your knees are not locked so as to avoid excessive strain upon your joints.  Collect yourself into the next rep as quickly but under control as possible.</p>',
        false, 0, 'High Knee Jumps', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (385, NULL, 'OTHER', '<p>To execute the exercise, the lifter stands with their feet shoulder width apart and weights or resistance handles held by their side with a pronated (overhand) grip.</p>
<p>The movement is to bring the arms up in front of the body to eye level and with only a slight bend in the elbow. This isolates the anterior deltoid muscle (front of the shoulder) and uses the anterior deltoid to lift the weight.</p>
<p>When lifting it is important to keep the body still so the anterior deltoid is fully utilised; if the weight cannot be lifted by standing still then it is too heavy and a lower weight is needed. It is important to keep a slight bend in the elbow when lifting as keeping the elbow locked will add stress to the elbow joint and could cause injury.</p>
<p>A neutral grip, similar to that used in the hammer curl, can also be used. With this variation the weight is again raised to eye level, but out to a 45 degree angle from the front of the body. This may be beneficial for those with shoulder injuries, particularly those related to the rotator cuff.</p>',
        false, 0, 'Front Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (389, NULL, 'OTHER', '', false, 3, 'Good Mornings', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (386, NULL, 'OTHER', '<p>Squats</p>', false, 0, 'Front Squats', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (404, NULL, 'OTHER', '<p>To do slowly, tempo is 4010</p>', false, 0, 'Incline Bench Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (403, NULL, 'OTHER',
        '<p>Lay on the hyperextension pad with the belly button just at the leading edge, the upper body can hang freely. Tense your whole back''s muscles and bring your upper body up till it is horizontal, but not more. Go slowly down and don''t relax your muscles.</p>',
        false, 0, 'Hyperextensions', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (402, NULL, 'OTHER',
        '<p>Get on a mat and lie on your back. Contract your abs, stretch your raise and legs and raise them (your head and shoulders are also be raised). Make sure your lower back remains in contact with the mat.</p>',
        false, 0, 'Hollow Hold', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (416, NULL, 'OTHER', '<p>-(1) Perform a lateral raise, pausing at the top of the lift (2).</p>
<p>-Instead of lowering the weight, bring it to the front of your body so that you appear to be at the top position of a front raise.  You will do this by using a Pec Fly motion, maintaining straight arms. (3)</p>
<p>-Now lower the weight to your quadriceps, or, in other words, lower the dumbbells as though you are completing a Front Raise repetition. (4)</p>
<p>-Reverse the motion:  Perform a front raise (5), at the apex of the lift use a Reverse Fly motion to position the weights at the top of a Lateral Raise (6), and finally, lower the weights until your palms are essentially touching the sides of your thighs (7).  THIS IS ONE REP.</p>
<p>(1) l  <em>front view  </em>(2) -l- <em> FV  </em>  (3) l-  <em>side view</em>   (4) l  <em>SV/FV</em>   (5) l-  <em>SV  </em> (6) -l-  <em>FV  </em>  (7)  l  <em>FV/SV</em></p>',
        false, 5.6, 'Lateral-to-Front Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (431, NULL, 'OTHER',
        '<p>Be sure to adjust seat height so that the handles are towards the bottom of your pectorals.</p>', false,
        5.6, 'Leverage Machine Chest Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (430, NULL, 'OTHER',
        '<p>Put your forearms on the pads on the leg raise machine, the body is hanging freely. Lift now your legs with a fast movement as high as you can, make a short pause of 1sec at the top, and bring them down again. Make sure that during the exercise your body does not swing, only the legs should move.</p>',
        false, 6, 'Leg Raises, Standing', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (421, NULL, 'OTHER', '', false, 7.4, 'Leg Curls (sitting)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (420, NULL, 'OTHER',
        '<p>Lay on a bench and put your calves behind the leg holder (better if they are hold on around the lower calves). Hold a grip on the bars to make sure the body is firmly in place. Bend your legs bringing the weight up, go slowly back. During the exercise the body should not move, all work is done by the legs.</p>',
        false, 5.6, 'Leg Curls (laying)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (419, NULL, 'OTHER',
        'The leg curl, also known as the hamstring curl, is an isolation exercise that targets the hamstring muscles. The exercise involves flexing the lower leg against resistance towards the buttocks. Other exercises that can be used to strengthen the hamstrings are the glute-ham raise and the deadlift.',
        false, 6, 'Leg Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (549, 'Some description', 'CARDIO', 'Instr 3', false, 4.4, 'Exercise from JSON 6', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (547, 'Desc 1', 'CARDIO', 'Instr 1', false, 5.4, 'Exercise from JSON 0', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (548, 'Desc 2', 'OTHER', 'Instr 2', false, 3.6, 'Exercise from JSON 5', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (550, 'Desc 4', 'CARDIO', 'Instr 4', false, 7.2, 'Exercise from JSON 7', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (413, NULL, 'OTHER', '<p>Hold the kettlebell securely in both hands. Keep your back flat throughout the move, avoiding any rounding of the spine.Keeping your knees "soft", hinge your hips backwards, letting the kettlebell swing between your knees.</p>
<p>You want to bend from the hips as far as you can <em>without letting your back round forwards</em>. Then, snap your hips forwards quickly and standing up straight, locking your body in an upright posture.</p>
<p>The speed you do this will cause your arms and the kettlebell to swing up in front of you. Don''t try to <em>lift</em> the kettlebell with your arms. The snapping forwards of your hips will cause the kettlebell to swing forwards through momentum. Depending on the weight of the kettlebell and the speed of your hip movement, your arms will swing up to about shoulder height. At the top of this swing, let your hips hinge backwards again as the kettlebell swings back down to between your legs and the start of the next repetition.</p>',
        false, 3.8, 'Kettlebell Swings', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (425, NULL, 'OTHER', '<p>The exercise is very similar to the wide leg press:</p>
<p>Sit on the machine and put your feet on the platform so far apart  that you could just put another foot in between them. The feet are parallel and point up.</p>
<p>Lower the weight so much, that the knees form a right angle. Push immediately the platform up again, without any pause. When in the lower position, the knees point a bit outwards and the movement should be always fluid.</p>',
        false, 0, 'Leg Presses (narrow)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (424, NULL, 'OTHER',
        'The leg press is a weight training exercise in which the individual pushes a weight or resistance away from them using their legs.',
        false, 0, 'Leg Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (427, NULL, 'OTHER', '', false, 0, 'Leg Press on Hackenschmidt Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (417, NULL, 'OTHER', '<p>Lean Back, Pull into chest</p>', false, 5.6, 'Lat Pull Down (Leaning Back)', NULL, NULL,
        false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (418, NULL, 'OTHER', '<p>Pull bar down to strenum and keep straight back.</p>', false, 7.4,
        'Lat Pull Down (Straight Back)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (423, NULL, 'OTHER',
        'The leg extension is a resistance weight training exercise that targets the quadriceps muscle in the legs. The exercise is done using a machine called the Leg Extension Machine. There are various manufacturers of these machines and each one is slightly different. Most gym and weight rooms will have the machine in their facility. The leg extension is an isolated exercise targeting one specific muscle group, the quadriceps. It should not be considered as a total leg workout, such as the squat or deadlift. The exercise consists of bending the leg at the knee and extending the legs, then lowering them back to the original position.',
        false, 6, 'Leg Extension', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (422, NULL, 'OTHER', '', false, 3, 'Leg Curls (standing)', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (475, NULL, 'OTHER',
        '<p>To perform the Russian twist one sits on the floor and bends both knees while feet are be kept together and held slightly above the ground (or put under a stable surface). Ideally, the torso is kept straight with the back kept off the ground at a 45 degree angle with arms held together away from the body in a straight fashion and hands kept locked together like a ball or one can hold a weight to increase the difficulty. Next, the arms should be swung from one side to another in a twisting motion, with each swing to a side counting as one repetition. The slower one moves the arms from side to side, the harder the exercise becomes, working the abdomen that much better. When moving one''s arms during the exercise, it is crucial to not stop between repetitions or else one will lose the effect of working the abdomen. As with all exercise, consistent breathing in and out during the exercise is important as one must ensure proper oxygen flow.</p>',
        false, 0, 'Russian Twists', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (479, NULL, 'OTHER',
        'The shoulder fly (also known as a lateral raise) works the deltoid muscle of the shoulder. The movement starts with the arms straight, and the hands holding weights at the sides or in front of the body. Body is in a slight forward-leaning position with hips and knees bent a little. Arms are kept straight or slightly bent, and raised through an arc of movement in the coronal plane that terminates when the hands are at approximately shoulder height. Weights are lowered to the starting position, completing one rep. When using a cable machine the individual stands with the coronal plane in line with the pulley, which is at or near the ground.[9] The exercise can be completed one shoulder at a time (with the other hand used to stabilize the body against the weight moved), or with both hands simultaneously if two parallel pulleys are available.',
        false, 0, 'Shoulder Fly', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (478, NULL, 'OTHER', '<ol>
<li>Attach a single handle to a low cable.</li>
<li>After selecting the correct weight, stand a couple feet back with a wide-split stance. Your arm should be extended and your shoulder forward. This will be your starting position.</li>
<li>Perform the movement by retracting the shoulder and flexing the elbow. As you pull, supinate the wrist, turning the palm upward as you go.</li>
<li>After a brief pause, return to the starting position.</li>
</ol>', false, 0, 'Shotgun Row', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (525, NULL, 'OTHER', '<p>Exercise for upper external oblique muscles</p>', false, 0, 'Upper External Oblique',
        NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (551, 'Desc 1', 'CARDIO', 'Instr 1', false, 5.4, 'Exercise from JSON 1', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (552, 'Desc 2', 'OTHER', 'Instr 2', false, 3.6, 'Exercise from JSON 2', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (553, 'Some description', 'CARDIO', 'Instr 3', false, 4.4, 'Exercise from JSON 3', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (554, 'Desc 4', 'CARDIO', 'Instr 4', false, 7.2, 'Exercise from JSON 4', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (440, NULL, 'OTHER', '<p>The body is then explosively pulled up by the arms in a radial pull-up, with greater speed than a regular pull-up. When the bar approaches the upper chest, the wrists are swiftly flexed to bring the forearms above the bar. The body is leaned forward, and the elbows are straightened by activating the triceps. The routine is considered complete when the bar is at the level of the waist and the arms are fully straight.</p>
<p>To dismount, the arms are bent at the elbow, and the body is lowered to the floor, and the exercise can be repeated.</p>
<p>As a relatively advanced exercise, muscle-ups are typically first learned with an assistive kip. The legs swing (kip) up and provide momentum to assist in the explosive upward force needed to ascend above the bar. More advanced athletes can perform a strict variation of the muscle-up which is done slowly, without any kip. This variation begins with a still dead hang and uses isometric muscle contraction to ascend above the bar in a slow, controlled fashion.</p>',
        false, 0, 'Muscle up', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (441, NULL, 'OTHER',
        '<p>Sit yourself on the decline bench and fix your legs. Cross your arms over the chest and bring with a rolling movement your upper body up, go now without a pause and with a slow movement down again. Don''t let your head move during the exercise.</p>',
        false, 0, 'Negative Crunches', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (445, NULL, 'OTHER',
        '<p>Back excercise with a barbell with a starting position which is in a bent over position with the back paralell to the ground. The barbell is on the ground at chest level.For the movement grab the barbell at shoulder width grip and pull towards your chest without losing the bent over position and without moving anything but your arms</p>',
        false, 0, 'Pendelay Rows', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (433, NULL, 'OTHER', '<p>Hold the L position for as long as possible</p>', false, 3.8, 'L Hold', NULL, NULL,
        false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (447, NULL, 'OTHER', '<p>Push Up performed from a pike position (optional to have feet elevated).</p>', false, 0,
        'Pike Push Ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (446, NULL, 'OTHER', '<p>Push up with perfect push up</p>', false, 0, 'Perfect Push Up', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (468, NULL, 'OTHER',
        'In strength training, rowing (or a row, usually preceded by a qualifying adjective — for instance a seated row) is an exercise where the purpose is to strengthen the muscles that draw the rower''s arms toward the body (latissimus dorsi) as well as those that retract the scapulae (trapezius and rhomboids) and those that support the spine (erector spinae). When done on a rowing machine, rowing also exercises muscles that extend and support the legs (quadriceps and thigh muscles). In all cases, the abdominal and lower back muscles must be used in order to support the body and prevent back injury.',
        false, 3.8, 'Row', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (467, NULL, 'OTHER', '<p>DL from top to pos 2: https://www.youtube.com/watch?v=WtWtjViRsKo</p>', false, 0,
        'Romanian Deadlift', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (466, NULL, 'OTHER', '<p>Crunches on roman chair</p>', false, 0, 'Roman Chair', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (457, NULL, 'OTHER',
        '<p>Start with your body streched, your hands are shoulder-wide appart on the ground. Push yourself off the ground till you strech your arms. The back is always straight and as well as the neck (always look to the ground). Lower yourself to the initial position and repeat.</p>',
        false, 3, 'Push Ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (456, NULL, 'OTHER',
        'A push-up (or press-up if the hands are wider than shoulders placing more emphasis on the pectoral muscles) is a common calisthenics exercise beginning from the prone position. By raising and lowering the body using the arms, push-ups exercise the pectoral muscles, triceps, and anterior deltoids, with ancillary benefits to the rest of the deltoids, serratus anterior, coracobrachialis and the midsection as a whole. Push-ups are a basic exercise used in civilian athletic training or physical education and commonly in military physical training. They are also a common form of punishment used in the military, school sport, or some martial arts disciplines.',
        false, 6, 'Pushups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (471, NULL, 'OTHER', '<p>The execution of this exercise is very similar to the regular bent over rowing, only that the bar is fixed here.</p>
<p>Grab the barbell with a wide grip (slightly more than shoulder wide) and lean forward. Your upper body is not quite parallel to the floor, but forms a slight angle. The chest''s out during the whole exercise. Pull now the barbell with a fast movement towards your belly button, not further up. Go slowly down to the initial position. Don''t swing with your body and keep your arms next to your body.</p>',
        false, 0, 'Rowing, T-bar', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (470, NULL, 'OTHER', '', false, 7.4, 'Rowing, Seated', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (461, NULL, 'OTHER', '<p>Hold bar with reverse (or "overhand") grip, palms facing the floor.</p>', false, 3.8,
        'Reverse Bar Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (559, 'Desc 1', 'CARDIO', 'Instr 1', false, 5.4, 'Exercise Import 1', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (560, 'Desc 2', 'OTHER', 'Instr 2', false, 3.6, 'Exercise Import 2', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (561, 'Some description', 'CARDIO', 'Instr 3', false, 4.4, 'Exercise Import 3', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (562, 'Desc 4', 'CARDIO', 'Instr 4', false, 7.2, 'Exercise Import 4', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (394, NULL, 'OTHER', '<p>Hanging from bar or straps, bring legs up with knees extended or flexed</p>', false,
        5.8, 'Hanging Leg Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (316, NULL, 'CARDIO', '<p>Place a barbell on the floor at your feet.</p>
<p>Bending at the waist, grip the barbell with a shoulder with overhand grip.</p>
<p>With a slow controlled motion, roll the bar out so that your back is straight.</p>
Roll back up raising your hips and butt as you return to the starting position.', false, 2, 'Barbell Ab Rollout', NULL,
        '2021-11-12-17-38-20-050_exercise_3_rope (1).mp4', false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (473, NULL, 'OTHER', '<p>Run and do some interval trainings such as hill repat, fartlek,..</p>', false, 0,
        'Run - Interval Training ', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (472, NULL, 'OTHER', '<p>Running or jogging outside in a park, on the tracks,...</p>', false, 0, 'Run', NULL,
        NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (477, NULL, 'OTHER', '<p>Sit down on a back (better with back support). Take a dumbbell firmly with both hands and hold it with extended arms over your head. With your palms facing upward and holding the weight of the dumbbell, slowly lower the weight behind your head.</p>
<p> </p>', false, 0, 'Seated Triceps Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (476, NULL, 'OTHER',
        '<p>Scissors is an abdominal exercise that strengthens the transverse abdominals, helping flatten your belly and strengthen your entire core. Scissors is not only a core strength move, but it is also a great stretch for your hamstrings and your lower back. Everyone is looking for new ways to work the core, to flatten the belly and to improve flexibility. If you learn how to do Scissors you will get everything rolled together in one move.</p>',
        false, 0, 'Scissors', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (474, NULL, 'OTHER', '<p>Run on a treadmill</p>', false, 0, 'Run - Treadmill', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (465, NULL, 'OTHER', '<p>Dips peformed on gymnastic rings.</p>', false, 0, 'Ring Dips', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (464, NULL, 'OTHER', '<p>Plank with stomach towards ceiling</p>', false, 0, 'Reverse Plank', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (460, NULL, 'OTHER', '<p>Get into pushup position gripping some dumbbells. Perform one pushup, then drive your left elbo up, bringing the dumbell up to your body. Return the dumbell to starting position. </p>
<p>Perform another pushup and then row with the other arm to complete one rep.</p>', false, 0, 'Renegade Row', NULL,
        NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (459, NULL, 'OTHER', '<p>Seated on a bench bWith the dumbbells on the floor bend over at 45 Degrees and then slowly raise each dumbbell to shoulder height and hold for a couple seconds before lowering to the starting position. </p>
<p> </p>', false, 0, 'Rear Delt Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (504, NULL, 'OTHER',
        'Stand holding dumbbells at shoulder width apart. Face forearm upward and keep upper arm still while raising each dumbbell up to your shoulder.',
        false, 0, 'Standing Bicep Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (520, NULL, 'OTHER', '<p>.</p>', false, 0, 'Triceps Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (392, NULL, 'OTHER', '<p>chrome Hand Flex Grip to build up forearms muscles</p>', false, 4.3, 'Hand Grip', NULL,
        NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (409, NULL, 'OTHER', '<p>Regular push with a 30 degree incline.</p>', false, 5.6, 'Incline Pushups', NULL, NULL,
        false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (415, NULL, 'OTHER', '<p>.</p>', false, 3, 'Lateral Raises on Cable, One Armed', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (379, NULL, 'OTHER', '', false, 4.3, 'Fly With Cable', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (455, NULL, 'OTHER', '<p>olympic weight lifting</p>', false, 7.4, 'Push Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (313, NULL, 'OTHER', '<p>Very common shoulder exercise.</p>
<p> </p>
<p>As shown here: https://www.youtube.com/watch?v=vj2w851ZHRM</p>', false, 4, 'Arnold Shoulder Press', NULL, NULL,
        false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (407, NULL, 'OTHER', '<ol>
<li>Using a neutral grip, lean into an incline bench.</li>
<li>Take a dumbbell in each hand with a neutral grip, beginning with the arms straight. This will be your starting position.</li>
<li>Retract the shoulder blades and flex the elbows to row the dumbbells to your side.</li>
<li>Pause at the top of the motion, and then return to the starting position.</li>
</ol>', false, 3, 'Incline Dumbbell Row', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (442, NULL, 'OTHER', '<p>Hands at shoulder height, curl arms in toward head, then back out.</p>', false, 3.8,
        'Overhand Cable Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (469, NULL, 'OTHER', '', false, 5.6, 'Rowing, Lying on Bench', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (491, NULL, 'OTHER', '<p>Stand up or sit , keep both weights in front against legs or at side. Keep arms at around a 90 degree angle. Lift elbows up slowly and squeese traps when at topmost position. Lower the weights slowly back to starting position.</p>
<p>2 seconds up, 2 seconds down</p>', false, 0, 'Side Raise', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (490, NULL, 'OTHER',
        '<p>Works your obliques and helps stabilize your spine. Lie on your side and support your body between your forearm and knee to your feet.</p>',
        false, 0, 'Side Plank', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (481, NULL, 'OTHER',
        '<p>Sit on a bench, the back rest should be almost vertical. Take two dumbbells and bring them up to shoulder height, the palms and the elbows point during the whole exercise to the front. Press the weights up, at the highest point they come very near but don''t touch. Go slowly down and repeat.</p>',
        false, 0, 'Shoulder Press, Dumbbells', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (480, NULL, 'OTHER',
        '<p>Sit on a bench, the back rest should be almost vertical. Take a barbell with a shoulder wide grip and bring it up to chest height. Press the weight up, but don''t stretch the arms completely. Go slowly down and repeat.</p>',
        false, 0, 'Shoulder Press, Barbell', NULL, '2021-11-12-18-05-10-377_exercise_1 (1).mp4', false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (495, NULL, 'OTHER', '<p>Sit on a mat, your calves are resting on a bench, the knees make a right angle. Hold your hands behind your neck. Go now up with a rolling movement of your back, you should feel how the individual vertebrae lose contact with the mat. At the highest point, contract your abs as much as you can and hold there for 2 sec. Go now down, unrolling your back.</p>
<p> </p>', false, 0, 'Sit-ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (494, NULL, 'OTHER',
        '<p>Sit on a bench for calf raises and check that the feet are half free and that you can completely stretch the calf muscles down. Pull your calves up, going as far (up) as you can. Make at the highest point a short pause of 1 or 2 seconds and go down.</p>',
        false, 0, 'Sitting Calf Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (485, NULL, 'OTHER', '<p>Take a barbell and stand with a straight body, the arms are hanging freely in front of you. Lift from this position the shoulders as high as you can, but don''t bend the arms during the movement. On the highest point, make a short pause of 1 or 2 seconds before returning slowly to the initial position.</p>
<p>When training with a higher weight, make sure that you still do the whole movement!</p>', false, 0,
        'Shrugs, Barbells', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (484, NULL, 'OTHER',
        'The shoulder shrug (usually called simply the shrug) is an exercise in weight training used to develop the upper trapezius muscle. The lifter stands erect, hands about shoulder width apart, and raises the shoulders as high as possible, and then lowers them, while not bending the elbows, or moving the body at all. The lifter may not have as large a range of motion as in a normal shrug done for active flexibility. It is usually considered good form if the slope of the shoulders is horizontal in the elevated position.',
        false, 0, 'Shoulder Shrug', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (483, NULL, 'OTHER', '<p>The exercise is basically the same as with a free barbell:</p>
<p>Sit on a bench, the back rest should be almost vertical. Take a bar with a shoulder wide grip and bring it down to chest height. Press the weight up, but don''t stretch the arms completely. Go slowly down and repeat.</p>',
        false, 0, 'Shoulder Press, on Multi Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (482, NULL, 'OTHER', '', false, 0, 'Shoulder Press, on Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (487, NULL, 'OTHER',
        '<p>Hold weight in one hand. Bend side ways to the knee. Pull upo to upright position using your obliquus.</p>',
        false, 0, 'Side Crunch', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (486, NULL, 'OTHER', '<p>Stand with straight body, the hands are hanging freely on the side and hold each a dumbbell. Lift from this position the shoulders as high as you can, but don''t bend the arms during the movement. On the highest point, make a short pause of 1 or 2 seconds before returning slowly to the initial position.</p>
<p>When training with a higher weight, make sure that you still do the whole movement!</p>', false, 0,
        'Shrugs, Dumbbells', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (537, NULL, 'OTHER', '<p>Lat pulldowns with a wide grip on the bar.</p>', false, 0, 'Wide-grip Pulldown', NULL,
        NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (536, NULL, 'OTHER', '<p>box step ups w/ barbell and 45''s on each side</p>', false, 0, 'Weighted Step-ups',
        NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (539, '123', 'STRENGTH', '123', false, 3, '1 Handed Kettlebell Swing', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (538, 'Aaa', 'STRENGTH', 'Bbb', false, 5, 'Ćwiczenie na nogi', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', NULL,
        false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (529, NULL, 'OTHER', '<p>Pulldowns using close grip v-bar.</p>', false, 0, 'V-Bar Pulldown', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (528, NULL, 'OTHER', '<p>Feet apart at shoulder width. Nice and Slow!</p>', false, 0, 'Upright Row w/ Dumbbells',
        NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (533, NULL, 'OTHER', '<p>Find a nice flat piece of wall and stand with your back leaning against the wall. Slowly slide down the wall while moving your feet away from it, until your thighs are parallel to the ground and both your knees and your hips are bent at a 90° angle. Cross your arms in front of your chest and hold this position for 30 seconds.</p>
<p>Variant: put a big inflated rubber ball (like a small basketball) between your knees and squeeze the ball while holding the squat position</p>',
        false, 0, 'Wall Squat', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (532, NULL, 'OTHER',
        '<p>Stand with heels,  shoulders, back of head,  and hips touching the wall. Start with biceps straight out and elbows at a 90 degree angle. Straighten the arms while remaining againstthe wall without arching the back off of the wall, mimicking a shoulder press movement. </p>',
        false, 0, 'Wall Slides', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (531, NULL, 'OTHER', '<p>Pushup against a wall</p>', false, 0, 'Wall Pushup', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (530, NULL, 'OTHER', '<p>Handstand against a wall for support (chest facing wall).</p>', false, 0,
        'Wall Handstand', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (521, NULL, 'OTHER', '<p>Seated trunk rotation with cable </p>', false, 0, 'Trunk Rotation With Cable ', NULL,
        NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (535, NULL, 'OTHER', '<p>Box step-ups w/ barbell, 45''s on each side</p>', false, 0, 'Weighted Step', NULL, NULL,
        false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (534, NULL, 'OTHER',
        '<p>Seated with your back against a wall, put the bottoms of your feet together and add weight plates to your knees to deepen the butterfly stretch. Hold this for at least a minute or until youve comfortably elongated the groin and hip flexor. </p>',
        false, 0, 'Weighted Butterfly Stretch', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (524, NULL, 'OTHER', '<p>Full workout of the upper arms body n chest. Focusing on the core</p>', false, 0,
        'Upper Body', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (523, NULL, 'OTHER',
        '<p>Grip the pull-down bar with your palms facing you and your hands closer than shoulder-width apart. Lean back slightly and keep your back straight. Pull the bar down towards your chest, pulling your shoulders back slightly at the end of the motion.</p>',
        false, 0, 'Underhand Lat Pull Down', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (522, NULL, 'OTHER',
        '<p>Starting on back, move to the standing position with dumbbell in one hand.  Switch hands between reps.</p>',
        false, 0, 'Turkish Get-Up', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (513, NULL, 'OTHER',
        '<p>Lay flat on your stomach with your arms extended in front of you on the ground as your legs are lying flat. Lift both your arms and legs at the same time, as if you were flying, and contract the lower back. Make sure that you are breathing and, depending on your fitness level, hold the movement for at least two to five seconds per repetition.</p>',
        false, 0, 'Superman', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (512, NULL, 'OTHER', '<p>Stand with your feet wider than your shoulders, with your toes pointed out at a 45 degree angle and barbell on your shoulder.</p>
<p>While keeping your back straight, descend slowly by bending at the knees and hips as if you are sitting down (squatting).</p>
<p>Lower yourself until your quadriceps and hamstrings are parallel to the floor.</p>
<p>Return to the starting position by pressing upwards and extending your legs while maintaining an equal distribution of weight on your forefoot and heel.</p>',
        false, 0, 'Sumo Squats', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (527, NULL, 'OTHER',
        '<p>Stand straight, your feet are shoulder-width apart. Hold the SZ-bar with an overhand grip on your thighs, the arms are stretched. Lift the bar close to the body till your chin. The elbows point out so that at the highest point they form a V. Make here a short pause before going slowly down and repeating the movement.</p>',
        false, 0, 'Upright Row, SZ-bar', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (526, NULL, 'OTHER', '<p>The movements are basically the same as with an SZ-bar, but you use the bar on the multi press:</p>
<p>Stand straight, your feet are shoulder-width apart. Hold the bar with an overhand grip on your thighs, the arms are stretched. Lift the bar close to the body till your chin. The elbows point out so that at the highest point they form a V. Make here a short pause before going slowly down and repeating the movement.</p>',
        false, 0, 'Upright Row, on Multi Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (517, NULL, 'OTHER',
        '<p>lift on parallel bars hold for 1 second, and lower slowly and control for 4 seconds, then come back with no rest (tempo: 4010)</p>',
        false, 0, 'Triceps Dips', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (516, NULL, 'OTHER',
        '<p>Činka v jedné ruce v úrovni ramene. Zvedat do výšky napnuté paže a zpět. Provádět pomalu.</p>', false, 0,
        'Triceps Bench Press One Barbell', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (515, NULL, 'OTHER', '<ol>
<li>Start with a dumbbell in each hand and your palms facing your torso. Keep your back straight with a slight bend in the knees and bend forward at the waist. Your torso should be almost parallel to the floor. Make sure to keep your head up. Your upper arms should be close to your torso and parallel to the floor. Your forearms should be pointed towards the floor as you hold the weights. There should be a 90-degree angle formed between your forearm and upper arm. This is your starting position.</li>
<li>Now, while keeping your upper arms stationary, exhale and use your triceps to lift the weights until the arm is fully extended. Focus on moving the forearm.</li>
<li>After a brief pause at the top contraction, inhale and slowly lower the dumbbells back down to the starting position.</li>
<li>Repeat the movement for the prescribed amount of repetitions.</li>
</ol>
<p><strong>Variations:</strong> This exercise can be executed also one arm at a time much like the one arm rows are performed.</p>
<p>Also, if you like the one arm variety, you can use a low pulley handle instead of a dumbbell for better peak contraction. In this case, the palms should be facing up (supinated grip) as opposed to the torso (neutral grip).</p>',
        false, 0, 'Tricep Dumbbell Kickback', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (514, NULL, 'OTHER', '<ol>
<li>Start by doing a front squat</li>
<li>At the top position, push the bar above your head (similar to a press)</li>
<li>Lower the bar to the shoulders</li>
</ol>', false, 0, 'Thruster', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (312, NULL, 'OTHER', '<p>Two Handed Russian Style Kettlebell swing</p>', false, 3, '2 Handed Kettlebell Swing',
        NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (519, NULL, 'OTHER',
        '<p>Grab the bar, stand with your feet shoulder wide, keep your back straight and lean forward a little. Push the bar down, making sure the elbows don''t move during the exercise. Without pause go back to the initial position.</p>',
        false, 0, 'Triceps Extensions on Cable With Bar', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (518, NULL, 'OTHER',
        '<p>Grab the cable, stand with your feet shoulder wide, keep your back straight and lean forward a little. Push the bar down, making sure the elbows don''t move during the exercise. Rotate your hands outwards at the very end and go back to the initial position without pause.</p>',
        false, 0, 'Triceps Extensions on Cable', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (345, NULL, 'OTHER', '<p>Put  the balls of your feet on an extended leg press pad.  Use your calves to press the weight by flexing your feet/toes into a pointed position, and releasing back into a relaxed position.</p>
<p>This exercise builds mass and strength in the Gastrocnemius and Soleus muscles as well, if not better, than any calf exercise.</p>',
        false, 3.7, 'Calf Press Using Leg Press Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (344, NULL, 'OTHER',
        '<p>Set cable pulley slightly lower than chest height. Keep body facing forward with hips stable.  Grab the pulley handle, fully extend your arms and bring your arms forward and across your body. Hold for 1 second at the end of the movement and slowly return to starting position.</p>',
        false, 6.3, 'Cable Woodchoppers', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (349, NULL, 'OTHER', '<p>Like pull-ups but with a reverse grip</p>', false, 9.1, 'Chin-ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (348, NULL, 'OTHER',
        'The chin-up (also known as a chin or chinup) is a strength training exercise. People frequently do this exercise with the intention of strengthening muscles such as the latissimus dorsi and biceps, which extend the shoulder and flex the elbow, respectively. In this maneuver, the palms are faced towards the body. It is a form of pull-up in which the range of motion is established in relation to a person''s chin.',
        false, 8.4, 'Chin Up', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (347, NULL, 'OTHER', '<p>Place yourself on the machine with your back firmly against the backrest, the feet are on the platform for calf raises. Check that the feet are half free and that you can completely stretch the calf muscles down.</p>
<p>With straight knees pull up your weight as much as you can. Go with a fluid movement down till the calves are completely stretched. Repeat.</p>',
        false, 9.2, 'Calf Raises on Hackenschmitt Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (346, NULL, 'OTHER',
        'Calf raises are a method of exercising the gastrocnemius, tibialis posterior and soleus muscles of the lower leg. The movement performed is plantar flexion, a.k.a. ankle extension.',
        false, 9.4, 'Calf Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (337, NULL, 'OTHER', '<p>1. Find a stable implement (a bench or a chair) and place the left foot hip-distance in front of the right one. Your core should be engaged, your shoulders should be back and your eyes and chest should face forward.</p>
<p>2. Raise the right leg off the ground and pulace it on the bench behind you.</p>
<p>3. Check your balance and distance between the feet. Your feet should not be on the same line as that will make it difficult to balance.</p>
<p>4. Bend your left knee and lower downard. Maintain position and push upward. The right knee is used only for balance and should bend by itself.</p>
<p>5. Switch the feet and repeat</p>
<p>The exercise can be done without weights, with dumbbells or a barbell.</p>', false, 4.7, 'Bulgarian Split Squat',
        NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (336, NULL, 'OTHER', '<p>Stand with feet slightly wider than shoulder-width apart, while standing as tall as you can.</p>
<p>Grab a weight plate and hold it out in front of your body with arms straight out. Keep your core tight and stand with a natural arch in your back.</p>
<p>Now, push hips back and bend knees down into a squat as far as you can. Hold for a few moments and bring yourself back up to the starting position.</p>',
        false, 4.7, 'Braced Squat', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (351, NULL, 'OTHER',
        '<p>Grip the pull-down bar with your hands closer than shoulder width apart, with your palms facing away from you. Lean back slightly. Pull the bar down towards your chest, keeping your elbows close to your sides as you come down. Pull your shoulders back at the end of the motion.</p>',
        false, 4.3, 'Close-grip Lat Pull Down', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (350, NULL, 'OTHER',
        '<p>Perform a typical bench press, but hold the bar with your hands approximately shoulder-width apart and keep your elbows close to your body.</p>',
        false, 9.9, 'Close-grip Bench Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (341, NULL, 'OTHER', '', false, 6.3, 'Butterfly Reverse', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (340, NULL, 'OTHER', '<p>The movement is the same as with a regular butterfly, only that the grip is narrow:</p>
<p>Sit on the butterfly machine, the feet have a good contact with the floor, the upper arms are parallel to the floor. Press your arms together till the handles are practically together (but aren''t!). Go slowly back. The weights should stay all the time in the air.</p>',
        false, 6.3, 'Butterfly Narrow Grip', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (339, NULL, 'OTHER',
        '<p>Sit on the butterfly machine, the feet have a good contact with the floor, the upper arms are parallel to the floor. Press your arms together till the handles are practically together (but aren''t!). Go slowly back. The weights should stay all the time in the air.</p>',
        false, 6.3, 'Butterfly', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (338, NULL, 'OTHER', '<p>Jump, lay down on your chest, do a pushup then jump, repeat</p>', false, 4.7, 'Burpees',
        NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (329, NULL, 'OTHER', '<p>Grab the barbell with a wide grip (slightly more than shoulder wide) and lean forward. Your upper body is not quite parallel to the floor, but forms a slight angle. The chest''s out during the whole exercise.</p>
<p>Pull now the barbell with a fast movement towards your belly button, not further up. Go slowly down to the initial position. Don''t swing with your body and keep your arms next to your body.</p>',
        false, 2.7, 'Bent Over Rowing', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (328, NULL, 'OTHER', '<p>The bent over lateral raise directly hits the rear deltoids (Shoulders). Considered an isolation exercise, it also exercises the trapezius muscles, which are considered secondary muscles. This exercise also hits the side deltoids as well.</p>









<p>1. In a seated position with your feet firmly planted on the floor, grasp two dumbbells with both hands.</p>
<p>2. Bend over until your body is roughly parallel to the ground or slightly above. Your chest should almost be touching your thighs.</p>
<p>3. In a controlled fashion, raise the dumbbells with both hands using your shoulders, out and away from the body. You should raise the dumbbells to each side until they are parallel with the upper back.</p>
<p>4. Pause for a moment and slowly lower the weight to the initial position.</p>








', false, 6, 'Bent Over Laterals', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (343, NULL, 'OTHER', '<p>Steps:</p>
<ol>
<li>Start off placing an extension band around a post or in a secure position where it will not release and is at elbow level.</li>
<li>Position yourself to the side of the band and with your hand that is opposite of the band, reach out and grab the handle.</li>
<li>Bring the band to your chest keeping your elbow bent in a 90 degree angle then slowly rotate your arm in a backhand motion so that the band externally rotates out</li>
<li>Continue out as far as possible so that you feel a stretch in your shoulders, hold for a count and then return back to the starting position.</li>
<li>Repeat for as many reps and sets as desired.</li>
</ol>', false, 6.3, 'Cable External Rotation', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (342, NULL, 'OTHER',
        '<p>Begin with cables at about shoulder height, one in each hand. Take a step forward so that one foot is in front of the other, for stability, and so that there is tension on the cables. Bring hands together in front of you. Try to make your hands overlap (so that the cables cross) a few inches.</p>',
        false, 5.8, 'Cable Cross-over', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (333, NULL, 'OTHER', '<p>Hold the SZ-bar shoulder-wide, the back is straight, the shoulders slightly back, the arms are streched. Bend the arms, bringing the weight up, with a fast movement. Without pausing, let down the bar with a slow and controlled movement.</p>
<p>Don''t allow your body to swing during the exercise, all work is done by the biceps, which are the only mucles that should move (pay attention to the elbows).</p>',
        false, 4.7, 'Biceps Curls With SZ-bar', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (332, NULL, 'OTHER', '<p>Hold two barbells, the arms are streched, the hands are on your side, the palms face inwards. Bend the arms and bring the weight with a fast movement up. At the same time, rotate your arms by 90 degrees at the very beginning of the movement. At the highest point, rotate a little the weights further outwards. Without a pause, bring them down, slowly.</p>
<p>Don''t allow your body to swing during the exercise, all work is done by the biceps, which are the only mucles that should move (pay attention to the elbows).</p>',
        false, 6.7, 'Biceps Curls With Dumbbell', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (331, NULL, 'OTHER', '<p>Hold the Barbell shoulder-wide, the back is straight, the shoulders slightly back, the arms are streched. Bend the arms, bringing the weight up, with a fast movement. Without pausing, let down the bar with a slow and controlled movement.</p>
<p>Don''t allow your body to swing during the exercise, all work is done by the biceps, which are the only mucles that should move (pay attention to the elbows).</p>',
        false, 2.7, 'Biceps Curls With Barbell', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (330, NULL, 'OTHER', '<p>The same as <em>regular</em> rowing, but holding a reversed grip (your palms pointing forwards):</p>
<p>Grab the barbell with a wide grIp (slightly more than shoulder wide) and lean forward. Your upper body is not quite parallel to the floor, but forms a slight angle. The chest''s out during the whole exercise.</p>
<p>Pull now the barbell with a fast movement towards your belly button, not further up. Go slowly down to the initial position. Don''t swing with your body and keep your arms next to your body.</p>',
        false, 2.7, 'Bent Over Rowing Reverse', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (321, NULL, 'OTHER', '<p>Lay down on a bench, the bar should be directly above your eyes, the knees are somewhat angled and the feet are firmly on the floor. Concentrate, breath deeply and grab the bar more than shoulder wide. Bring it slowly down till it briefly touches your chest at the height of your nipples. Push the bar up.</p>
<p>If you train with a high weight it is advisable to have a <em>spotter</em> that can help you up if you can''t lift the weight on your own.</p>
<p>With the width of the grip you can also control which part of the chest is trained more:</p>
<ul>
<li>wide grip: outer chest muscles</li>
<li>narrow grip: inner chest muscles and triceps</li>
</ul>', false, 3, 'Bench Press', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (320, NULL, 'OTHER', '<p>-Rest your weight on your palms and the balls of your feet, not dissimilar to normal pushup position</p>
<p>-Move by stepping with your R palm and L foot, then your L palm and R foot.  Basically, walk like a lumbering bear.</p>
<p>-Move as fast as you can.  Measure your reps/sets in either distance (i.e. 40 yards) or time (i.e. 45 seconds)</p>
<p>-Works your Pecs, Deltoids, Triceps, Traps, Lats, Abs and Lower Back, Hip Flexors, Quads, Glutes and Calves</p>',
        false, 0.9, 'Bear Walk', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (355, NULL, 'OTHER',
        '<p>Take the cable on your hands and hold it next to your temples. Knee down and hold your upper body straight and bend forward. Go down with a fast movement, rolling your back in (your ellbows point to your knees). Once down, go slowly back to the initial position.</p>',
        false, 4.3, 'Crunches With Cable', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (335, NULL, 'OTHER', '<ol>
<li>Assume a plank position on the ground. You should be supporting your bodyweight on your toes and forearms, keeping your torso straight. Your forearms should be shoulder-width apart. This will be your starting position.</li>
<li>Pressing your palms firmly into the ground, extend through the elbows to raise your body from the ground. Keep your torso rigid as you perform the movement.</li>
<li>Slowly lower your forearms back to the ground by allowing the elbows to flex.</li>
<li>Repeat as needed.</li>
</ol>', false, 4.7, 'Body-Ups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (334, NULL, 'OTHER',
        '<p>Stand around 30 - 40cm away from the cable, the feet are firmly on the floor. Take the bar and lift the weight with a fast movements. Lower the weight as with the dumbbell curls slowly and controlled.</p>',
        false, 0, 'Biceps Curl With Cable', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (325, NULL, 'OTHER', '<ol>
<li>Holding a barbell with a pronated grip (palms facing down), bend your knees slightly and bring your torso forward, by bending at the waist, while keeping the back straight until it is almost parallel to the floor. Tip: Make sure that you keep the head up. The barbell should hang directly in front of you as your arms hang perpendicular to the floor and your torso. This is your starting position.</li>
<li>Now, while keeping the torso stationary, breathe out and lift the barbell to you. Keep the elbows close to the body and only use the forearms to hold the weight. At the top contracted position, squeeze the back muscles and hold for a brief pause.</li>
<li>Then inhale and slowly lower the barbell back to the starting position.</li>
<li>Repeat for the recommended amount of repetitions.</li>
</ol>', false, 4.7, 'Bent Over Barbell Row', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (324, NULL, 'OTHER',
        '<p>Bend over slightly while holding two dumbbells.  Pull the dumbbells up to your chest, keeping your elbows as high as you can.</p>',
        false, 6.7, 'Bent High Pulls', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (323, NULL, 'OTHER',
        '<p>Lay down on a bench, the bar is directly over your eyes, the knees form a slight angle and the feet are firmly on the ground. Hold the bar with a narrow grip (around 20cm.). Lead the weight slowly down till the arms are parallel to the floor (elbow: right angle), press then the bar up. When bringing the bar down, don''t let it down on your nipples as with the regular bench pressing, but somewhat lower.</p>',
        false, 14, 'Bench Press Narrow Grip', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (322, NULL, 'STRENGTH', '<p>The movement is very similar to benchpressing with a barbell, however, the weight is brought down to the chest at a lower point.</p>
<p>Hold two dumbbells and lay down on a bench. Hold the weights next to the chest, at the height of your nipples and press them up till the arms are stretched. Let the weight slowly and controlled down.</p>',
        false, 2, 'Benchpress Dumbbells', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (377, NULL, 'OTHER', '<p>Attach a rope to a pulley station set at about chest level.</p>
<p>Step back so you''re supporting the weight with arms completely outstretched and assume a staggered (one foot forward) stance. Bend the knees slightly for a stable base.</p>
<p>Retract the scapulae (squeeze your partner''s finger with your shoulder blades) and pull the center of the rope slightly up towards the face. A good cue is to think about pulling the ends of the rope apart, not just pulling back.</p>
<p>As you near your face, externally rotate so your knuckles are facing the ceiling.</p>
<p>Hold for one second at the top position and slowly lower.</p>', false, 0, 'Facepull', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (327, NULL, 'OTHER',
        '<p>Sit on bench while holding weights. Bend forward as far as possible, with arms slightly bent at the elbow. Perform a lateral raise while maintaining the bend in your elbow.</p>',
        false, 2.7, 'Bent-over Lateral Raises', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (326, NULL, 'OTHER',
        '<p>With dumbbells in hand, bend at the hip until hands hang just below the knees (similar to straight-legged-deadlift starting position). Keep upper body angle constant while contracting your lats to pull you ellbows back pinching the shoulder blades at the top. Try not to stand up with every rep, check hands go below knees on every rep.</p>',
        false, 6.7, 'Bentover Dumbbell Rows', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (381, NULL, 'OTHER', '<p>The exercise is the same as with a regular bench:</p>
<p>Take two dumbbells and lay on a bench, make sure the feet are firmly on the ground and your back is not arched, but has good contact with the bench. The arms are stretched in front of you, about shoulder wide. Bend now the arms a bit and let them down with a half-circle movement to the side. Without changing the angle of the elbow bring them in a fluid movement back up.</p>',
        false, 5.8, 'Fly With Dumbbells, Decline Bench', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (380, NULL, 'OTHER',
        '<p>Take two dumbbells and lay on a bench, make sure the feet are firmly on the ground and your back is not arched, but has good contact with the bench. The arms are stretched in front of you, about shoulder wide. Bend now the arms a bit and let them down with a half-circle movement to the side. Without changing the angle of the elbow bring them in a fluid movement back up.</p>',
        false, 4.3, 'Fly With Dumbbells', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (378, NULL, 'OTHER', '<p>-Laying on the back, lift your straightened legs from the ground at a 45 degree angle. </p>
<p>-As your Left foot travels downward and nearly touches the floor, your Right foot should seek to reach a 90 degree angle, or as close to one as possible.</p>
<p>-Bring your R foot down until it nearly touches the floor, and bring your L foot upwards.  Maintain leg rigidity throughout the exercise.  Your head should stay off the ground, supported by tightened upper abdominals.</p>
<p>-(L up R down, L down R up, x2)  ^v, v^, ^v, v^ = 1 rep</p>
<p>-Primarily works the Rectus Abdominus, the hip flexors and the lower back. Secondarily works the Obliques.  Emphasis placed on the lower quadrant of the abs.</p>
<p> </p>', false, 2.7, 'Flutter Kicks', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (369, NULL, 'OTHER',
        '<p>Sit on bench. Grasp dumbbell between feet. Place back of upper arm to inner thigh. Lean into leg to raise elbow slightly.</p>',
        false, 4.3, 'Dumbbell Concentration Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (368, NULL, 'OTHER',
        '<p>Put two benches so far appart, that you can hold onto one with your hands and are just able to reach the other with your feet. The legs stay during the exercise completely stretched. With your elbows facing back, bend them as much as you can. Push yourself up, but don''t stretch out the arms.</p>',
        false, 4.3, 'Dips Between Two Benches', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (383, NULL, 'OTHER', '<p>Hold the dumbbells and lay down on a flat bench in such a way that around 1/4 of your head is over the edge. Stretch your arms with the weights and bend them so that the dumbbells are lowered (make sure they don''t touch each other). Just before they touch your forehead, push them up.</p>
<p>Pay attention to your elbows and arms: only the triceps are doing the work, the rest of the arms should not move.</p>',
        false, 0, 'French Press (skullcrusher) Dumbbells', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (382, NULL, 'OTHER',
        '<p>Start at a standing position. Lift and move one foot forward until your front calf and thigh are at 90 degrees to one another. At the same time, bend your back leg forward while keeping the back foot in place. Return to starting position. Repeat with other leg.</p>',
        false, 4.3, 'Forward Lunge', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (373, NULL, 'OTHER',
        '<p>Take two dumbbells in your hands, stand straight, feet about shoulder wide. Take one long step so that the front knee is approximately forming a right angle. The back leg is streched, the knee is low but doesn''t touch the ground. "Complete" the step by standing up and repeat the movement with the other leg.</p>',
        false, 0, 'Dumbbell Lunges Walking', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (372, NULL, 'OTHER', '<p>.</p>', false, 0, 'Dumbbell Lunges Standing', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (371, NULL, 'OTHER',
        '<p>With elbows back to sides, raise one dumbbell and rotate forearm until forearm is vertical and palm faces shoulder. Lower to original position and repeat with opposite arm. Continue to alternate between sides.</p>',
        false, 4.3, 'Dumbbell Incline Curl', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (370, NULL, 'OTHER', '<p>Grasp dumbbell with both hands at the sides of the upper plates. Hold dumbbell in front of chest, close to torso. Place feet about shoulderwide apart, keep knees slightly bent.</p>
<p>Squat down until thighs are parallel to floor. Keep back straight, bend and move hips backward to keep knees above feet. Return, keep knees slightly flexed. Repeat.</p>
<p>Keep bodyweight on heels and look ahead or slightly above to keep back straight.</p>', false, 5.8,
        'Dumbbell Goblet Squat', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (361, NULL, 'OTHER', '<p>Stand firmly, with your feet slightly more than shoulder wide apart. Stand directly behind the bar where it should barely touch your shin, your feet pointing a bit out. Bend down with a straight back, the knees also pointing somewhat out. Grab the bar with a shoulder wide grip, one underhand, one reverse grip.</p>
<p>Pull the weight up. At the highest point make a slight hollow back and pull the bar back. Hold 1 or 2 seconds that position. Go down, making sure the back is not bent. Once down you can either go back again as soon as the weights touch the floor, or make a pause, depending on the weight.</p>',
        false, 2.7, 'Deadlifts', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (375, NULL, 'OTHER', '<p>Position one dumbbell over head with both hands under inner plate (heart shaped grip).</p>
<p>With elbows over head, lower forearm behind upper arm by flexing elbows. Flex wrists at bottom to avoid hitting dumbbell on back of neck. Raise dumbbell over head by extending elbows while hyperextending wrists. Return and repeat.</p>',
        false, 0, 'Dumbbell Triceps Extension', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (374, NULL, 'OTHER', '', false, 0, 'Dumbbells on Scott Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (365, NULL, 'OTHER', '<p>Preparation</p>
<p>Stand on weight plate, bumper plate, or shallow elevated platform with loaded bar above feet. Squat down and grasp bar with shoulder width or slightly wider overhand or mixed grip.</p>
<p> </p>
<p>Execution</p>
<p>Lift bar by extending hips and knees to full extension. Pull shoulders back at top of lift if rounded. Return weights to floor by bending hips back while allowing knees to bend forward, keeping back straight and knees pointed same direction as feet. Repeat.</p>
<p> </p>
<p>Comments</p>
<p>Throughout lift, keep hips low, shoulders high, arms and back straight. Knees should point same direction as feet throughout movement. Keep bar close to body to improve mechanical leverage. Grip strength and strength endurance often limit ability to perform multiple reps at heavy resistances. Gym chalk, wrist straps, grip work, and mixed grip can be used to enhance grip. Mixed grip indicates one hand holding with overhand grip and other hand holding with underhand grip. Lever barbell jack can be used to lift barbell from floor for easier loading and unloading of weight plates.</p>
<p>Barbell Deficit Deadlift emphasizes building strength through lowest portion of Deadlift. Target muscle is exercised isometrically. Heavy barbell deadlifts significantly engages Latissmus Dorsi. See Barbell Deficit Deadlift under Gluteus Maximus. Also see Deadlift Analysis.</p>',
        false, 4.3, 'Deficit Deadlift', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (364, NULL, 'OTHER',
        '<p>With your feet raised approximately 30cm on a platform, align your shoulders, elbows and hands, then perform regular pushups. This emphasises the clavicular fibers of the pectoralis major.</p>',
        false, 5.8, 'Decline Pushups', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (363, NULL, 'OTHER',
        '<p>Take two dumbbells and sit on a decline bench, the feet are firmly on the floor, the head is resting the bench. Hold the weights next to the chest, at the height of your nipples and press them up till the arms are stretched. Let the weight slowly and controlled down.</p>',
        false, 4.3, 'Decline Bench Press Dumbbell', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (362, NULL, 'OTHER',
        '<p>Lay down on a decline bench, the bar should be directly above your eyes, the knees are somewhat angled and the feet are firmly on the floor. Concentrate, breath deeply and grab the bar more than shoulder wide. Bring it slowly down till it briefly touches your chest at the height of your nipples. Push the bar up.</p>',
        false, 4.3, 'Decline Bench Press Barbell', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (353, NULL, 'OTHER',
        '<p>Lay down on your back a soft surface, the feet are on the floor. Ask a partner or use some other help (barbell, etc.) to keep them fixed, your hands are behind your head. From this position move your upper body up till your head or elbows touch your knees. Do this movement by rolling up your back.</p>',
        false, 2.7, 'Crunches', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (352, NULL, 'OTHER',
        '<p>Grasp a moderately weighted dumbbell so your palms are flat against the underside of the top plates and your thumbs are around the bar. Lie on your back across a flat bench so only your upper back and shoulders are in contact with the bench. Your feet should be set about shoulder-width apart and your head should hang slightly downward. With the dumbbell supported at arm''s length directly about your chest, bend your arms about 15 degrees and keep them bent throughout the movement. Slowly lower the dumbbell backward and downward in a semicircle arc to as low a position as is comfortably possible. Raise it slowly back along the same arc to the starting point, and repeat for the required number of repetitions.</p>',
        false, 4.3, 'Cross-Bench Dumbbell Pullovers', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (367, NULL, 'OTHER',
        'A dip is an upper-body strength exercise. Narrow, shoulder-width dips primarily train the triceps, with major synergists being the anterior deltoid, the pectoralis muscles (sternal, clavicular, and minor), and the rhomboid muscles of the back (in that order).[1] Wide arm training places additional emphasis on the pectoral muscles, similar in respect to the way a wide grip bench press would focus more on the pectorals and less on the triceps.',
        false, 2.7, 'Dips', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (366, NULL, 'OTHER', '<p>You sit at the bench press device, back slightly tilted to the back. The bar should be about 20 cm in front of you. Then you push the bar and take it back again, as you would with a bench press.</p>
<p>In this position you strain your chest muscles a lot less, which is nice if you want to train, but your chest hasn''t recovered yet.</p>
<p>Here''s a link to a girl on a machine specialized for this exercise, to give a better description than my failing words above.</p>
<p>http://www.schnell-online.de/db_imgs/products/img/t-80400.jpg</p>', false, 0, 'Diagonal Shoulder Press', NULL, NULL,
        false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (357, NULL, 'OTHER', '<p>Bicipiti su panca a 45°. Il movimento deve essere completo</p>', false, 4.3,
        'Curl su Panca a 45°', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (356, NULL, 'OTHER',
        '<p>On your back, legs extended straight up, reach toward your toes with your hands and lift your shoulder blades off the ground and back.</p>',
        false, 5.8, 'Crunches With Legs Up', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (354, NULL, 'OTHER',
        '<p>The procedure is very similar as for regular crunches, only with the additional weight of the machine. Sit on the machine, put both feet firmly on the ground. Grab the to the weights, cables, etc. and do a rolling motion forwards (the spine should ideally lose touch vertebra by vertebra). Slowly return to the starting position. </p>',
        false, 4.3, 'Crunches on Machine', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (359, NULL, 'OTHER',
        'Lie on your back, with your hips and knees bent to 90°. Raise both arms toward the ceiling. Pull your lower back to the floor to eliminate the gap. Start by pressing one leg out, and tapping the heel to the floor. "As you extend one leg, exhale as much as you can, keeping your lower back glued to the floor," Dunham says. When you can’t exhale any more, pull your knee back to the starting position. Make this more difficult by holding weight in your hands, or by lowering opposite arm and leg.',
        false, 0, 'Deadbug', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (358, NULL, 'OTHER',
        'Cycling, also called bicycling or biking, is the use of bicycles for transport, recreation, exercise or sport. People engaged in cycling are referred to as cyclists, bicyclists, or bikers. Apart from two-wheeled bicycles, cycling also includes the riding of unicycles, tricycles, quadracycles, recumbent and similar human-powered vehicles.',
        false, 0, 'Cycling', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (318, NULL, 'OTHER', '<p>Put barbell on the back of your shoulders. Stand upright, then take the first step forward. Step should bring you forward so that your supporting legs knee can touch the floor. Then stand back up and repeat with the other leg.</p>
<p>Remember to keep good posture.</p>', false, 4.7, 'Barbell Lunges', NULL, NULL, false);
INSERT INTO public.exercise (exercise_id, description, type, instruction, is_private, metabolic_eqv_of_task, name,
                             creator, path_to_video_instruction, is_deleted)
VALUES (319, NULL, 'OTHER', '<p>Position barbell overhead with narrow overhand grip.</p>
<p>Lower forearm behind upper arm with elbows remaining overhead. Extend forearm overhead. Lower and repeat.</p>',
        false, 12, 'Barbell Triceps Extension', NULL, NULL, false);


--
-- Data for Name: training; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted)
VALUES (79, NULL, NULL, false, 'Trening C', 'MEDIUM', NULL, false);
INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted)
VALUES (84, 'Trening B - opis', 'Wskazówki od trenera', false, 'Trening B', 'HARD',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', false);
INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted)
VALUES (92, 'Wymagający trening', 'Trener nie dołączył wskazówek', false, 'Trening 32', 'HARD',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', true);
INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted)
VALUES (83, 'Nowy trening HAS', 'Nowe ćwiczenie z opisem Nowe ćwiczenie z opisem Nowe ćwiczenie z a', false,
        'Trening HAS', 'HARD', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', false);
INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted)
VALUES (82, 'Nowe ćwiczenie z opisem Nowe ćwiczenie z opisem Nowe ćwiczenie z opisem ',
        'Nowe ćwiczenie z opisem Nowe ćwiczenie z opisem Nowe ćwiczenie z opisem ', false, 'Trening XGF', 'MEDIUM',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', false);
INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted)
VALUES (81,
        'Nowe ćwiczenie z opisem Nowe ćwiczenie z opisem Nowe ćwiczenie z opisem Nowe ćwiczenie z opisem Nowe ćwiczenie z opisem ',
        'Nowe ćwiczenie z opisem Nowe ćwiczenie z opisem Nowe ćwiczenie z opisem Nowe ćwiczenie z opisem ', false,
        'Trening Konrada 2', 'MEDIUM', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', false);
INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted)
VALUES (80, NULL, NULL, false, 'Training_B', 'HARD', NULL, false);
INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted)
VALUES (91, 'Trening dla prawdziwych kozaków!', 'Brak', false, 'Siła i moc', 'MEDIUM',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', false);
INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted)
VALUES (90, 'Niezwykle wymagający trening na mięśnie dwugłowe ramienia', 'Brak', false, 'Potężny biceps', 'HARD',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', false);
INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted)
VALUES (89, 'Aab', 'BBc', false, 'Stamina bump', 'MEDIUM', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', false);
INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted)
VALUES (93, 'Abc', 'Trening Abc', false, 'Abc Trening', 'MEDIUM', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', false);
INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted)
VALUES (94, 'New', 'Test Training', false, 'New Test Training', 'MEDIUM', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        false);
INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted)
VALUES (87, 'Taki trening sobie jest', 'Taki trening sobie jest', false, 'Trening ABS', 'MEDIUM',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', false);
INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted)
VALUES (96, 'Opis', 'Wskazówki', false, 'Test new filters', 'MEDIUM', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', false);
INSERT INTO public.training (training_id, description, instruction, is_private, name, difficulty, creator, is_deleted)
VALUES (95, 'a', 'a', false, 'Trenera', 'MEDIUM', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', false);


--
-- Data for Name: exercise_in_training; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (174, 20, 2, 900, 315, 95);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (176, 15, 2, 900, 354, 95);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (159, 20, 2, 900, 318, 87);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (160, 15, 2, 3600, 328, 82);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (161, 15, 1, 240, 326, 81);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (111, 20, 4, 5, 78, 83);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (116, 23, 2, 5, 82, 83);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (115, 20, 2, 5, 83, 83);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (123, 25, 2, 1800, 328, 83);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (124, 20, 2, 1800, 328, 89);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (128, 20, 2, 1800, 539, 89);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (129, 20, 2, 2100, 313, 83);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (130, 25, 4, 2400, 81, 80);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (104, 20, 1, 1220, 79, 80);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (106, 10, 4, 540, 80, 83);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (107, 15, 2, 2400, 79, 83);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (163, 15, 2, 1740, 313, 81);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (103, 15, 1, 760, 78, 79);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (164, 15, 1, 1200, 335, 87);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (165, 15, 1, 600, 345, 87);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (138, 15, 2, 900, 321, 90);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (139, 15, 2, 900, 316, 91);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (140, 10, 2, 600, 321, 91);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (108, 20, 1, 1400, 81, 84);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (166, 20, 1, 720, 324, 96);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (105, 20, 2, 1250, 79, 79);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (167, 15, 2, 600, 338, 96);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (110, 12, 1, 480, 78, 84);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (109, 20, 1, 1600, 83, 84);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (142, 20, 2, 1800, 325, 93);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (143, 20, 2, 900, 316, 93);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (144, 20, 2, 1800, 324, 94);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (168, 20, 1, 900, 324, 95);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (169, 20, 1, 900, 314, 95);
INSERT INTO public.exercise_in_training (id, reps, series, time_in_seconds, exercise_id, training_id)
VALUES (172, 20, 1, 900, 313, 95);


--
-- Data for Name: exercise_labels; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (83, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (84, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (328, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (328, 487);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (328, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (328, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (322, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (322, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (312, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (313, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (314, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (315, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (317, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (318, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (319, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (320, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (323, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (324, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (325, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (326, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (327, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (328, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (329, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (330, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (331, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (332, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (333, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (334, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (335, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (336, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (337, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (338, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (339, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (340, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (341, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (342, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (343, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (344, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (345, 487);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (346, 487);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (347, 487);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (348, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (349, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (350, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (351, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (352, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (353, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (354, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (355, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (356, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (357, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (358, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (359, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (360, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (361, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (362, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (363, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (364, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (365, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (366, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (367, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (367, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (368, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (369, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (370, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (371, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (372, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (373, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (374, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (375, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (376, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (377, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (378, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (379, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (380, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (381, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (382, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (383, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (384, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (385, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (386, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (387, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (388, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (389, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (390, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (391, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (392, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (393, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (394, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (395, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (396, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (397, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (398, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (399, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (400, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (401, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (402, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (403, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (404, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (405, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (406, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (407, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (408, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (409, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (410, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (411, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (412, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (413, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (414, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (415, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (416, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (417, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (418, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (419, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (420, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (421, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (422, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (423, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (424, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (425, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (426, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (427, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (428, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (429, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (430, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (431, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (432, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (433, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (434, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (435, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (436, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (437, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (438, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (439, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (439, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (440, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (441, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (442, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (443, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (444, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (445, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (446, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (447, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (448, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (449, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (450, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (451, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (452, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (453, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (454, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (455, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (456, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (457, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (458, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (459, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (460, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (461, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (462, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (463, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (464, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (465, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (466, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (467, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (468, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (469, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (470, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (471, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (472, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (473, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (474, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (475, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (476, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (477, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (478, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (479, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (480, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (481, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (482, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (483, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (484, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (485, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (486, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (487, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (488, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (489, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (490, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (491, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (492, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (493, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (494, 487);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (495, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (496, 487);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (497, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (498, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (499, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (500, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (501, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (502, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (503, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (504, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (505, 487);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (506, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (507, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (508, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (509, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (510, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (511, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (512, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (513, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (514, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (515, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (516, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (517, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (518, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (519, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (520, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (521, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (522, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (523, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (524, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (525, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (526, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (527, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (528, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (529, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (530, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (531, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (532, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (533, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (534, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (535, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (536, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (537, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (538, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (321, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (321, 487);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (321, 486);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (316, 487);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (316, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (316, 483);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (316, 484);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (82, 1);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (540, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (541, 482);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (542, 503);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (543, 3);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (544, 505);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (539, 2);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (539, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (545, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (546, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (547, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (547, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (551, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (551, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (551, 506);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (552, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (553, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (554, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (559, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (559, 485);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (559, 506);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (560, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (561, 481);
INSERT INTO public.exercise_labels (exercise_id, sport_label_id)
VALUES (562, 481);


--
-- Data for Name: follow; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.follow (follow_id, added_date, followed_id, follower_id)
VALUES (1, '2021-11-30 03:27:02.566', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.follow (follow_id, added_date, followed_id, follower_id)
VALUES (2, '2021-12-02 19:50:10.074', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');


--
-- Data for Name: likes; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (2, '2021-11-15 20:43:13.061', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 9);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (3, '2021-11-15 20:43:14.035', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 8);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (4, '2021-11-15 20:43:15.941', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 7);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (6, '2021-11-15 20:43:38.063', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', 5);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (7, '2021-11-15 20:43:51.631', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', 2);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (11, '2021-11-22 18:45:46.73', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 6);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (12, '2021-11-23 11:22:09.983', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', 10);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (13, '2021-11-25 22:00:54.292', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', 11);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (14, '2021-11-25 22:01:26.994', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', 9);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (15, '2021-11-25 22:01:55.539', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', 8);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (16, '2021-11-25 22:05:40.478', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', 7);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (17, '2021-11-25 22:11:33.994', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', 6);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (18, '2021-11-25 22:14:59.286', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', 4);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (20, '2021-11-29 23:36:55.264', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 2);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (21, '2021-11-29 23:41:33.506', '68514957-19f4-4764-a978-58d175948d7f', 10);
INSERT INTO public.likes (like_id, added_date, liker_id, post_id)
VALUES (22, '2021-11-30 03:38:03.878', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 12);


--
-- Data for Name: measure_types; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.measure_types (id, max_value, min_value, name, unit, "precision")
VALUES ('bbae77c8-2eb9-4277-bb78-4095ee93f3fa', 500, 0, 'Poziom cukru', 'mg/dL', 2);
INSERT INTO public.measure_types (id, max_value, min_value, name, unit, "precision")
VALUES ('1d132c88-0f4d-43d1-bc27-99b4fd793546', 250, 0, 'Tętno', 'udzerzeń/min', 2);
INSERT INTO public.measure_types (id, max_value, min_value, name, unit, "precision")
VALUES ('c3465abd-7805-4226-b1e8-4fd018a4797c', 45, 25, 'Temperatura', '°C', 2);


--
-- Data for Name: measures; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.measures (id, measure_date, value, measure_type_id, owner_profile_user_id)
VALUES ('420c631a-5f69-4681-bd72-88d0efaba7a4', '2021-11-18 02:43:59.162', 97, '1d132c88-0f4d-43d1-bc27-99b4fd793546',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.measures (id, measure_date, value, measure_type_id, owner_profile_user_id)
VALUES ('f6232158-8fbb-4d29-8d5f-d7240b9326e2', '2021-11-18 02:44:21.668', 36.8, 'c3465abd-7805-4226-b1e8-4fd018a4797c',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.measures (id, measure_date, value, measure_type_id, owner_profile_user_id)
VALUES ('4585f2ce-d252-4eef-855d-55801f01dfe7', '2021-11-22 18:45:09.42', 67, '1d132c88-0f4d-43d1-bc27-99b4fd793546',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.measures (id, measure_date, value, measure_type_id, owner_profile_user_id)
VALUES ('b6c56a42-5622-4ab1-8e46-10d2fcc6a7ce', '2021-11-25 21:41:26.233', 36, 'c3465abd-7805-4226-b1e8-4fd018a4797c',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.measures (id, measure_date, value, measure_type_id, owner_profile_user_id)
VALUES ('1ab750ed-93e5-4be2-8701-c36a549f0207', '2021-11-10 10:41:00', 37, 'c3465abd-7805-4226-b1e8-4fd018a4797c',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.measures (id, measure_date, value, measure_type_id, owner_profile_user_id)
VALUES ('d9e5c003-8d35-422f-8395-a9721e4110cd', '2021-11-25 21:41:45.457', 110, '1d132c88-0f4d-43d1-bc27-99b4fd793546',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');


--
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id)
VALUES ('4a0c7680-7602-4039-abae-36a2d5f118c5', '2021-11-09 09:22:11.381', 'Cześć Konrad!', 0,
        '57b4fa54-2158-4dc8-81b3-e6ffe6bf9c1f', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id)
VALUES ('0ed2ba27-cd6b-448a-abf6-bcbec6056a03', '2021-11-09 10:01:06.101', 'Cześć', 0,
        '57b4fa54-2158-4dc8-81b3-e6ffe6bf9c1f', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id)
VALUES ('a9c7f59f-fc40-4c27-9c4c-8990373e1631', '2021-11-09 10:03:02.473', 'Hej', 0,
        '57b4fa54-2158-4dc8-81b3-e6ffe6bf9c1f', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id)
VALUES ('81b93f95-4216-49e0-9c4b-fa0cf0d583c1', '2021-11-15 20:00:01.521', 'Cześć Konrad!', 0,
        'a5ddb009-71e5-44a7-a724-f01eb38bee3b', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id)
VALUES ('9c59855b-0b3c-41b9-ae7f-ab53b2a3c816', '2021-11-15 20:01:12.357', 'Dzień dobry panie Januszu', 0,
        'a5ddb009-71e5-44a7-a724-f01eb38bee3b', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id)
VALUES ('c599cdb7-7844-4de5-a101-43aca64be109', '2021-11-19 17:53:52.435', 'Siema Janusz', 0,
        '57b4fa54-2158-4dc8-81b3-e6ffe6bf9c1f', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id)
VALUES ('7253f846-110f-49d6-a0a6-df3442c12e6f', '2021-11-19 17:53:55.616', 'Jak zdrowie?', 0,
        '57b4fa54-2158-4dc8-81b3-e6ffe6bf9c1f', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id)
VALUES ('a317fc8a-3512-4e3a-86a1-876428281f65', '2021-11-22 18:46:08.103', 'Elo', 0,
        '57b4fa54-2158-4dc8-81b3-e6ffe6bf9c1f', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id)
VALUES ('8f81f88f-8839-473c-839f-7c4eb93d4f11', '2021-11-30 00:32:20.443',
        'Cześć Konrad, byłem na długim wyjeździe służbowym. Jak mogę Ci pomóc? Jak zdrowie?', 0,
        '57b4fa54-2158-4dc8-81b3-e6ffe6bf9c1f', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id)
VALUES ('80c58215-3d42-4b54-95e8-be398fd11990', '2021-11-30 03:28:39.706', '2021-11-30-03-28-40-151_profile.jpg', 1,
        '1cd05662-d200-4200-bb85-371be9c945bb', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id)
VALUES ('6291f6db-cb1c-4de3-ae98-083f319f25ad', '2021-11-30 03:28:44.018', 'Hej!', 0,
        '1cd05662-d200-4200-bb85-371be9c945bb', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id)
VALUES ('981c5752-8ec4-4913-b4ba-2ec29bbca314', '2021-11-30 03:43:34.123', '2021-11-30-03-43-34-481_profile.jpg', 1,
        'a5ddb009-71e5-44a7-a724-f01eb38bee3b', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id)
VALUES ('7d734311-36aa-4f9c-b4fb-61c75e6504fa', '2021-11-30 03:45:40.425', '2021-11-30-03-45-40-717_dog-3.jpg', 1,
        'a5ddb009-71e5-44a7-a724-f01eb38bee3b', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id)
VALUES ('b4e147ed-67b9-46a9-ac0c-99b245a0a14e', '2021-12-03 16:59:48.049', 'Heeeej 😊', 0,
        '3d1e6247-3358-4dcc-bcca-9c3b9999f696', 'b1bd6581-ff3f-470f-906b-2899540c930d');
INSERT INTO public.messages (id, create_date, data, message_type, conversation_id, sender_profile_user_id)
VALUES ('5bc63af6-cb14-44ae-aa08-f23a05d09fb9', '2021-12-03 17:00:12.469', '2021-12-03-17-00-12-951_krasnal.jpg', 1,
        '3d1e6247-3358-4dcc-bcca-9c3b9999f696', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');


--
-- Data for Name: nutrition_plan; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.nutrition_plan (id, generation_date, main, name, diet_id, owner_id,
                                   suggested_id)
VALUES ('9e3da908-3e71-4744-83d7-8c2d5d2ac50a', '2021-11-30 03:18:22.286', true, 'Nowy plan', NULL,
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');


--
-- Data for Name: nutrition_plan_position; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: opinion; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.opinion (opinion_id, opinion_content, rating, giver_id, receiver_id, added_date, is_deleted)
VALUES (1, 'Nie jest zły, ale znam lepszych :P', 3, 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', '2021-12-03 16:47:14.889', false);


--
-- Data for Name: product_macro_detail; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: product_mineral_detail; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: product_vitamin_detail; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: profile_card_ailment; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.profile_card_ailment (ailment_id, profile_card_id)
VALUES ('28253bd8-ae57-4372-9de8-22a179f37d9c', '6ae9b375-67a4-4c8c-ab35-a1b0de4f14c0');
INSERT INTO public.profile_card_ailment (ailment_id, profile_card_id)
VALUES ('28253bd8-ae57-4372-9de8-22a179f37d9c', '4537f410-3dbe-4919-b2fe-750fff2739df');
INSERT INTO public.profile_card_ailment (ailment_id, profile_card_id)
VALUES ('28253bd8-ae57-4372-9de8-22a179f37d9c', 'd8ed7492-79fa-454d-83fb-702bb41be2f3');


--
-- Data for Name: profile_card_disliked_product; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: profile_connections; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date,
                                        connected_with_profile_user_id, profile_profile_user_id)
VALUES ('86dea206-c1b3-4bef-83a4-7ff5f8222d41', NULL, 1, false, '2021-11-09 09:21:27.368',
        '573a6160-a438-4a33-b456-11cc39f9adcf', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date,
                                        connected_with_profile_user_id, profile_profile_user_id)
VALUES ('e24d39ca-320a-4c8c-841e-f823741909c1', '2021-11-09 10:00:34.337', 1, true, '2021-11-09 09:59:51.349',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date,
                                        connected_with_profile_user_id, profile_profile_user_id)
VALUES ('9c0d7db7-6eba-4e37-934a-8f7a736507b1', '2021-11-15 19:59:45.216', 3, true, '2021-11-14 04:46:18.665',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date,
                                        connected_with_profile_user_id, profile_profile_user_id)
VALUES ('37caa2c4-4de0-4611-bb55-952be0ecfb0f', NULL, 3, false, '2021-11-15 20:42:06.488',
        '68514957-19f4-4764-a978-58d175948d7f', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date,
                                        connected_with_profile_user_id, profile_profile_user_id)
VALUES ('77816968-3d2a-4c59-a62f-8b7c841011ee', NULL, 3, false, '2021-11-18 02:31:53.402',
        '68514957-19f4-4764-a978-58d175948d7f', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date,
                                        connected_with_profile_user_id, profile_profile_user_id)
VALUES ('0bf26190-809c-44af-a539-68a628864861', NULL, 3, false, '2021-11-23 10:37:58.13',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date,
                                        connected_with_profile_user_id, profile_profile_user_id)
VALUES ('856f2fab-b7ad-44ff-839d-2cc4a43c9a87', NULL, 2, false, '2021-11-25 21:57:38.409',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date,
                                        connected_with_profile_user_id, profile_profile_user_id)
VALUES ('304ee26c-6974-4524-9ba5-ab1e173660b0', NULL, 3, false, '2021-11-30 00:11:29.404',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', '68514957-19f4-4764-a978-58d175948d7f');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date,
                                        connected_with_profile_user_id, profile_profile_user_id)
VALUES ('1589ba75-42bd-4d6d-8759-402d51ed2442', '2021-11-30 07:51:13.5', 0, true, '2021-11-30 07:50:56.836',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date,
                                        connected_with_profile_user_id, profile_profile_user_id)
VALUES ('d72004e8-47c2-4c15-844b-ec87385b85a8', NULL, 3, false, '2021-11-30 19:43:34.878',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', 'b1bd6581-ff3f-470f-906b-2899540c930d');
INSERT INTO public.profile_connections (id, accept_date, connection_type, is_accepted, request_date,
                                        connected_with_profile_user_id, profile_profile_user_id)
VALUES ('80e68455-77ba-49fb-9e1b-a65779d294b0', '2021-12-03 16:59:35.521', 0, true, '2021-12-03 16:59:15.577',
        'b1bd6581-ff3f-470f-906b-2899540c930d', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');


--
-- Data for Name: report; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.report (id, derived_caffeine, derived_calories, derived_carbohydrates, derived_cholesterol,
                           derived_fats, derived_fiber, derived_proteins, derived_salt, derived_saturated_fats,
                           derived_sugar, report_date, owner_profile_id)
VALUES ('d0342dba-a11f-4adf-9dbc-6d58b29b96e9', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2021-11-25',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.report (id, derived_caffeine, derived_calories, derived_carbohydrates, derived_cholesterol,
                           derived_fats, derived_fiber, derived_proteins, derived_salt, derived_saturated_fats,
                           derived_sugar, report_date, owner_profile_id)
VALUES ('259879a0-b53b-49db-9487-2c5e4e6dce5b', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2021-12-03',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');


--
-- Data for Name: report_dish_detail; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: sport_report; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.sport_report (id, report_date, owner_profile_id)
VALUES ('e16be77a-4a9f-4564-aef3-e530a812ff2b', '2021-11-13', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.sport_report (id, report_date, owner_profile_id)
VALUES ('8515ece3-d809-4677-b54d-a269b679a907', '2021-11-15', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.sport_report (id, report_date, owner_profile_id)
VALUES ('128d8914-9d49-49f1-8d29-b0e86e191b1f', '2021-11-15', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.sport_report (id, report_date, owner_profile_id)
VALUES ('2900391d-abc5-40f2-b19e-9d094ea94e74', '2021-11-18', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.sport_report (id, report_date, owner_profile_id)
VALUES ('b03fe81b-fc11-4d65-9138-9a6de8bcaeb9', '2021-11-18', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.sport_report (id, report_date, owner_profile_id)
VALUES ('10661ae3-8669-4e90-b42a-d8f3ab1f1066', '2021-11-21', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.sport_report (id, report_date, owner_profile_id)
VALUES ('3ecf35e4-e2ba-4ab8-aa71-2a4ff8bc4d8c', '2021-11-25', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.sport_report (id, report_date, owner_profile_id)
VALUES ('3b515e9a-a4d2-4a8a-9ee9-23773bae3d04', '2021-11-30', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.sport_report (id, report_date, owner_profile_id)
VALUES ('ca7a2d2d-aecd-4392-8ecf-9a40492a6bc2', '2021-11-30', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.sport_report (id, report_date, owner_profile_id)
VALUES ('413593eb-5ece-40c2-9fab-e128958769c0', '2021-12-01', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.sport_report (id, report_date, owner_profile_id)
VALUES ('cb25debf-6d92-4038-afd7-aad1d5ea2c96', '2021-12-01', 'b1bd6581-ff3f-470f-906b-2899540c930d');
INSERT INTO public.sport_report (id, report_date, owner_profile_id)
VALUES ('941c237b-bf93-4466-9992-8ff5f9d8043d', '2021-11-30', 'b1bd6581-ff3f-470f-906b-2899540c930d');
INSERT INTO public.sport_report (id, report_date, owner_profile_id)
VALUES ('9f737ee2-62de-4658-9b87-16303645f303', '2021-12-01', 'dc722668-12a5-4c90-92bc-f348a3bf92c4');
INSERT INTO public.sport_report (id, report_date, owner_profile_id)
VALUES ('14c8ccd9-1b29-4e5f-b85c-bf3a820d62b8', '2021-11-22', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');
INSERT INTO public.sport_report (id, report_date, owner_profile_id)
VALUES ('d4802e50-62f8-41b2-adae-149f5f20b38a', '2021-12-03', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed');


--
-- Data for Name: report_exercise; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (17, '2021-11-13 15:00:00', 3200, 539, 'e16be77a-4a9f-4564-aef3-e530a812ff2b');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (19, '2021-11-13 14:06:00', 2500, 82, 'e16be77a-4a9f-4564-aef3-e530a812ff2b');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (22, '2021-11-18 12:00:00', 3600, 317, '2900391d-abc5-40f2-b19e-9d094ea94e74');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (23, '2021-11-18 12:00:00', 3600, 322, '2900391d-abc5-40f2-b19e-9d094ea94e74');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (24, '2021-11-18 12:00:00', 3600, 318, 'b03fe81b-fc11-4d65-9138-9a6de8bcaeb9');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (29, '2021-11-15 12:00:00', 3600, 328, '8515ece3-d809-4677-b54d-a269b679a907');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (44, '2021-11-15 15:00:00', 3600, 314, '128d8914-9d49-49f1-8d29-b0e86e191b1f');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (45, '2021-11-25 15:05:00', 3600, 336, '3ecf35e4-e2ba-4ab8-aa71-2a4ff8bc4d8c');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (50, '2021-11-21 16:00:00', 3600, 336, '10661ae3-8669-4e90-b42a-d8f3ab1f1066');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (59, '2021-11-30 12:00:00', 15, 316, 'ca7a2d2d-aecd-4392-8ecf-9a40492a6bc2');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (61, '2021-11-30 12:00:00', 900, 314, 'ca7a2d2d-aecd-4392-8ecf-9a40492a6bc2');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (63, '2021-11-25 12:00:00', 4320, 317, '3ecf35e4-e2ba-4ab8-aa71-2a4ff8bc4d8c');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (64, '2021-12-01 12:00:00', 1800, 313, '9f737ee2-62de-4658-9b87-16303645f303');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (65, '2021-12-01 15:00:00', 1200, 548, '9f737ee2-62de-4658-9b87-16303645f303');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (66, '2021-11-22 16:00:00', 1080, 315, '14c8ccd9-1b29-4e5f-b85c-bf3a820d62b8');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (69, '2021-11-30 17:05:00', 900, 553, '3b515e9a-a4d2-4a8a-9ee9-23773bae3d04');
INSERT INTO public.report_exercise (id, exercising_time, seconds, exercise_id, report_id)
VALUES (70, '2021-12-01 12:00:00', 3600, 314, '413593eb-5ece-40c2-9fab-e128958769c0');


--
-- Data for Name: report_product_detail; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: report_training; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (21, 2800, '2021-11-13 19:00:00', 'e16be77a-4a9f-4564-aef3-e530a812ff2b', 84);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (25, 3600, '2021-11-15 15:00:00', '128d8914-9d49-49f1-8d29-b0e86e191b1f', 79);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (26, 3600, '2021-11-15 15:00:00', '128d8914-9d49-49f1-8d29-b0e86e191b1f', 80);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (27, 3600, '2021-11-15 15:00:00', '128d8914-9d49-49f1-8d29-b0e86e191b1f', 83);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (28, 3600, '2021-11-15 15:00:00', '128d8914-9d49-49f1-8d29-b0e86e191b1f', 91);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (46, 1800, '2021-11-25 18:00:00', '3ecf35e4-e2ba-4ab8-aa71-2a4ff8bc4d8c', 84);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (49, 3480, '2021-10-30 12:00:00', '3b515e9a-a4d2-4a8a-9ee9-23773bae3d04', 84);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (52, 3600, '2021-11-21 15:00:00', '10661ae3-8669-4e90-b42a-d8f3ab1f1066', 94);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (54, 3600, '2021-10-30 16:00:00', 'ca7a2d2d-aecd-4392-8ecf-9a40492a6bc2', 94);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (56, 3000, '2021-11-01 08:00:00', '413593eb-5ece-40c2-9fab-e128958769c0', 91);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (57, 7200, '2021-11-01 08:00:00', 'cb25debf-6d92-4038-afd7-aad1d5ea2c96', 89);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (58, 7200, '2021-10-30 08:00:00', '941c237b-bf93-4466-9992-8ff5f9d8043d', 89);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (60, 1800, '2021-11-30 12:00:00', 'ca7a2d2d-aecd-4392-8ecf-9a40492a6bc2', 90);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (62, 1800, '2021-11-30 15:04:00', 'ca7a2d2d-aecd-4392-8ecf-9a40492a6bc2', 95);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (67, 3000, '2021-11-22 12:00:00', '14c8ccd9-1b29-4e5f-b85c-bf3a820d62b8', 91);
INSERT INTO public.report_training (id, seconds, training_time, report_id, training_id)
VALUES (68, 600, '2021-12-01 16:00:00', '413593eb-5ece-40c2-9fab-e128958769c0', 84);


--
-- Data for Name: role_request; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id,
                                 specialization_id)
VALUES (5, '2021-11-09 08:48:35.824', '', '2021-11-09-08-48-35-788_246752_Liuras_Konrad_Raport.pdf', 'ROLE_DOCTOR',
        'CANCELLED', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id,
                                 specialization_id)
VALUES (3, '2021-11-09 08:48:21.971', '', '2021-11-09-08-48-21-949_246752_Liuras_Konrad_Raport.pdf', 'ROLE_TRAINER',
        'CANCELLED', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id,
                                 specialization_id)
VALUES (4, '2021-11-09 08:48:29.556', '', '2021-11-09-08-48-29-541_Liuras-Konrad-Sprawozdanie-AG.pdf', 'ROLE_TRAINER',
        'CANCELLED', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id,
                                 specialization_id)
VALUES (2, '2021-11-09 09:31:54.004', 'Bo nie', '2021-11-09-08-45-16-526_246752_Liuras_Konrad_Raport.pdf',
        'ROLE_DOCTOR', 'REJECTED', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id,
                                 specialization_id)
VALUES (1, '2021-11-09 08:43:43.194', '', '2021-11-09-08-43-43-173_Liuras-Konrad-Sprawozdanie-AG.pdf', 'ROLE_DIETICIAN',
        'CANCELLED', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id,
                                 specialization_id)
VALUES (6, '2021-11-09 09:32:14.035', '', '2021-11-09-09-32-14-022_Liuras-Konrad-Sprawozdanie-AG.pdf', 'ROLE_DIETICIAN',
        'CANCELLED', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', NULL);
INSERT INTO public.role_request (role_req_id, added_date, comment, document_img_path, role, status, submitter_id,
                                 specialization_id)
VALUES (7, '2021-11-25 21:49:47.074', '', '2021-11-25-21-49-47-035_po_65_2020-z.pdf', 'ROLE_DIETICIAN', 'ACCEPTED',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', NULL);


--
-- Data for Name: settings; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--


--
-- Data for Name: sport_label_ailment; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.sport_label_ailment (id, impact_weight, is_positive, ailment_id, sport_label_id)
VALUES (1, 0.6, false, 'd8ed7492-79fa-454d-83fb-702bb41be2f3', 503);
INSERT INTO public.sport_label_ailment (id, impact_weight, is_positive, ailment_id, sport_label_id)
VALUES (2, 0.3, false, '6ae9b375-67a4-4c8c-ab35-a1b0de4f14c0', 504);
INSERT INTO public.sport_label_ailment (id, impact_weight, is_positive, ailment_id, sport_label_id)
VALUES (3, 0.1, false, '6ae9b375-67a4-4c8c-ab35-a1b0de4f14c0', 505);
INSERT INTO public.sport_label_ailment (id, impact_weight, is_positive, ailment_id, sport_label_id)
VALUES (4, 0.4, false, '4537f410-3dbe-4919-b2fe-750fff2739df', 505);
INSERT INTO public.sport_label_ailment (id, impact_weight, is_positive, ailment_id, sport_label_id)
VALUES (5, 0.2, false, '4537f410-3dbe-4919-b2fe-750fff2739df', 486);


--
-- Data for Name: trainer_profile; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.trainer_profile (user_id)
VALUES ('68514957-19f4-4764-a978-58d175948d7f');
INSERT INTO public.trainer_profile (user_id)
VALUES ('dc722668-12a5-4c90-92bc-f348a3bf92c4');


--
-- Data for Name: training_plan_request; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.training_plan_request (request_id, message, request_date, request_status, submitter_profile_user_id,
                                          trainer_user_id, beginning_date)
VALUES (40, 'Nowa prośba', '2021-11-22 15:33:20.725', 'SEEN', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', '2021-12-06 00:00:00');
INSERT INTO public.training_plan_request (request_id, message, request_date, request_status, submitter_profile_user_id,
                                          trainer_user_id, beginning_date)
VALUES (42, 'Wiad', '2021-11-22 15:57:03.427', 'DENIED', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', '2021-12-13 00:00:00');
INSERT INTO public.training_plan_request (request_id, message, request_date, request_status, submitter_profile_user_id,
                                          trainer_user_id, beginning_date)
VALUES (35, 'Please ', '2021-11-21 21:49:09.39', 'DENIED', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', '2021-12-13 00:00:00');
INSERT INTO public.training_plan_request (request_id, message, request_date, request_status, submitter_profile_user_id,
                                          trainer_user_id, beginning_date)
VALUES (36, 'Please do', '2021-11-21 21:50:09.648', 'SUBMITTED', 'dc722668-12a5-4c90-92bc-f348a3bf92c4',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', '2021-12-13 00:00:00');
INSERT INTO public.training_plan_request (request_id, message, request_date, request_status, submitter_profile_user_id,
                                          trainer_user_id, beginning_date)
VALUES (33, 'Please make a training plan for meeeee!', '2021-11-21 21:42:29.459', 'ACCEPTED',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', '2021-12-13 00:00:00');
INSERT INTO public.training_plan_request (request_id, message, request_date, request_status, submitter_profile_user_id,
                                          trainer_user_id, beginning_date)
VALUES (34, 'Please make a training plan for meeeee!', '2021-11-21 21:47:07.083', 'DENIED',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', '2021-12-13 00:00:00');
INSERT INTO public.training_plan_request (request_id, message, request_date, request_status, submitter_profile_user_id,
                                          trainer_user_id, beginning_date)
VALUES (38, 'Proszę o plan', '2021-11-21 23:13:27.751', 'ACCEPTED', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', '2021-12-13 00:00:00');
INSERT INTO public.training_plan_request (request_id, message, request_date, request_status, submitter_profile_user_id,
                                          trainer_user_id, beginning_date)
VALUES (37, 'Please make plan for me', '2021-11-21 21:54:58.194', 'DENIED', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', '2021-12-13 00:00:00');
INSERT INTO public.training_plan_request (request_id, message, request_date, request_status, submitter_profile_user_id,
                                          trainer_user_id, beginning_date)
VALUES (39, 'Nowa prośba', '2021-11-21 23:17:01.894', 'DENIED', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', '2021-12-13 00:00:00');
INSERT INTO public.training_plan_request (request_id, message, request_date, request_status, submitter_profile_user_id,
                                          trainer_user_id, beginning_date)
VALUES (41, 'Wiad', '2021-11-22 15:54:32.386', 'ACCEPTED', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', '2021-12-13 00:00:00');
INSERT INTO public.training_plan_request (request_id, message, request_date, request_status, submitter_profile_user_id,
                                          trainer_user_id, beginning_date)
VALUES (55, 'Proszę o utworzenie planu treningowego, mam czas na treningi w poniedziałek, środę i piątek. Pozdrawiam:)',
        '2021-11-30 02:05:14.674', 'DENIED', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', '2021-12-06 00:00:00');
INSERT INTO public.training_plan_request (request_id, message, request_date, request_status, submitter_profile_user_id,
                                          trainer_user_id, beginning_date)
VALUES (71, 'Lekki plan dla utrzymania kondycji ;)', '2021-12-03 15:33:07.77', 'ACCEPTED',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 'dc722668-12a5-4c90-92bc-f348a3bf92c4', '2021-12-27 00:00:00');


--
-- Data for Name: training_plan; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (39, '2021-11-29 00:00:00', 'Nowy plan', 'SCRATCH', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (105, '2021-12-06 00:00:00', 'Nowy plan', 'SCRATCH', '68514957-19f4-4764-a978-58d175948d7f',
        '68514957-19f4-4764-a978-58d175948d7f', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (118, '2021-12-20 00:00:00', 'Randomly generated plan', 'PLANNED', 'dc722668-12a5-4c90-92bc-f348a3bf92c4',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (119, '2021-12-27 01:00:00', 'Nowy plan od trenera', 'PLANNED', 'dc722668-12a5-4c90-92bc-f348a3bf92c4',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 71);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (107, '2021-12-20 00:00:00', 'Randomly generated plan', 'PLANNED', 'dc722668-12a5-4c90-92bc-f348a3bf92c4',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (120, '2021-12-27 01:00:00', 'Nowy plan od trenera', 'PLANNED', 'dc722668-12a5-4c90-92bc-f348a3bf92c4',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 71);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (1, '2021-11-01 00:00:00', 'Nowy plan', 'PLANNED', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (5, '2021-11-29 00:00:00', 'Nowy plan', 'STARTED', 'dc722668-12a5-4c90-92bc-f348a3bf92c4',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (2, '2021-11-08 00:00:00', 'Nowy plan', 'PLANNED', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (109, '2021-11-29 00:00:00', 'Randomly generated plan', 'PLANNED', 'b1bd6581-ff3f-470f-906b-2899540c930d',
        'b1bd6581-ff3f-470f-906b-2899540c930d', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (110, '2021-11-29 00:00:00', 'Randomly generated plan', 'PLANNED', 'b1bd6581-ff3f-470f-906b-2899540c930d',
        'b1bd6581-ff3f-470f-906b-2899540c930d', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (111, '2021-11-29 00:00:00', 'Randomly generated plan', 'STARTED', 'b1bd6581-ff3f-470f-906b-2899540c930d',
        'b1bd6581-ff3f-470f-906b-2899540c930d', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (40, '2021-11-29 00:00:00', 'Nowy plan od trenera', 'STARTED', 'dc722668-12a5-4c90-92bc-f348a3bf92c4',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (113, '2021-12-06 00:00:00', 'Randomly generated plan', 'PLANNED', 'dc722668-12a5-4c90-92bc-f348a3bf92c4',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (112, '2021-11-22 00:00:00', 'Nowy plan', 'SCRATCH', 'dc722668-12a5-4c90-92bc-f348a3bf92c4',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (117, '2021-11-29 00:00:00', 'Nowy plan', 'SCRATCH', 'b1bd6581-ff3f-470f-906b-2899540c930d',
        'b1bd6581-ff3f-470f-906b-2899540c930d', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (108, '2021-11-29 00:00:00', 'Nowy plan', 'STARTED', 'dc722668-12a5-4c90-92bc-f348a3bf92c4',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (54, '2021-11-22 00:00:00', 'Nowy plan', 'STARTED', 'dc722668-12a5-4c90-92bc-f348a3bf92c4',
        'dc722668-12a5-4c90-92bc-f348a3bf92c4', NULL);
INSERT INTO public.training_plan (training_plan_id, beginning_date, details, status, creator, owner, request)
VALUES (3, '2021-11-22 00:00:00', 'Nowy plan', 'STARTED', 'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed',
        'ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', NULL);


--
-- Data for Name: training_position; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (2, 'NOON', '2021-11-17 04:45:00', NULL, 80, 1);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (1, 'NOON', '2021-11-16 04:45:00', 'COMPLETED', 84, 1);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (3, 'MORNING', '2021-11-22 04:46:12', 'COMPLETED', 79, 1);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (5, 'NOON', '2021-11-02 05:27:11', 'COMPLETED', 80, 1);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (4, 'MORNING', '2021-11-25 04:46:12', 'COMPLETED', 83, 1);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (6, 'EVENING', '2021-11-09 00:00:00', 'COMPLETED', 80, 2);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (11, 'MORNING', '2021-11-24 04:46:12', 'COMPLETED', 83, 2);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (7, 'AFTERNOON', '2021-11-24 00:00:00', 'COMPLETED', 79, 3);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (287, 'AFTERNOON', '2021-11-30 00:00:00', NULL, 83, 39);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (296, 'AFTERNOON', '2021-11-30 00:00:00', NULL, 87, 108);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (327, 'MORNING', '2021-12-21 00:00:00', NULL, 81, 118);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (328, 'MORNING', '2021-12-21 00:00:00', NULL, 79, 118);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (329, 'MORNING', '2021-12-22 00:00:00', NULL, 95, 118);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (330, 'MORNING', '2021-12-22 00:00:00', NULL, 95, 118);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (331, 'MORNING', '2021-12-23 00:00:00', NULL, 81, 118);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (332, 'MORNING', '2021-12-23 00:00:00', NULL, 87, 118);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (333, 'MORNING', '2021-12-23 00:00:00', NULL, 81, 118);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (334, 'MORNING', '2021-12-25 00:00:00', NULL, 95, 118);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (335, 'MORNING', '2021-12-25 00:00:00', NULL, 79, 118);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (19, 'AFTERNOON', '2021-11-25 00:00:00', 'COMPLETED', 91, 3);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (20, 'AFTERNOON', '2021-11-24 00:00:00', 'SCRATCH', 91, 3);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (336, 'MORNING', '2021-12-25 00:00:00', NULL, 81, 118);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (337, 'MORNING', '2021-12-25 00:00:00', NULL, 79, 118);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (288, 'MORNING', '2021-12-07 00:00:00', NULL, 90, 105);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (297, 'MORNING', '2021-11-30 00:00:00', NULL, 82, 109);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (298, 'MORNING', '2021-11-30 00:00:00', NULL, 82, 109);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (299, 'MORNING', '2021-12-01 00:00:00', NULL, 82, 109);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (300, 'MORNING', '2021-12-01 00:00:00', NULL, 82, 109);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (301, 'MORNING', '2021-12-02 00:00:00', NULL, 82, 109);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (302, 'MORNING', '2021-12-02 00:00:00', NULL, 82, 109);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (303, 'MORNING', '2021-12-02 00:00:00', NULL, 82, 109);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (304, 'MORNING', '2021-11-30 00:00:00', NULL, 94, 110);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (305, 'MORNING', '2021-11-30 00:00:00', NULL, 93, 110);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (306, 'MORNING', '2021-12-01 00:00:00', NULL, 94, 110);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (307, 'MORNING', '2021-12-01 00:00:00', NULL, 93, 110);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (308, 'MORNING', '2021-12-02 00:00:00', NULL, 93, 110);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (309, 'MORNING', '2021-12-02 00:00:00', NULL, 93, 110);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (310, 'MORNING', '2021-12-02 00:00:00', NULL, 94, 110);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (311, 'MORNING', '2021-11-30 00:00:00', NULL, 84, 111);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (314, 'MORNING', '2021-12-01 00:00:00', NULL, 84, 111);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (315, 'MORNING', '2021-12-02 00:00:00', NULL, 84, 111);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (316, 'MORNING', '2021-12-02 00:00:00', NULL, 89, 111);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (317, 'MORNING', '2021-12-02 00:00:00', NULL, 89, 111);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (313, 'MORNING', '2021-12-01 00:00:00', 'COMPLETED', 89, 111);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (312, 'MORNING', '2021-11-30 00:00:00', 'COMPLETED', 89, 111);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (338, 'AFTERNOON', '2021-12-28 01:00:00', NULL, 89, 119);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (289, 'AFTERNOON', '2021-11-30 00:00:00', 'COMPLETED', 94, 5);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (318, 'MORNING', '2021-12-07 00:00:00', NULL, 84, 113);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (319, 'MORNING', '2021-12-07 00:00:00', NULL, 94, 113);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (320, 'MORNING', '2021-12-08 00:00:00', NULL, 94, 113);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (321, 'MORNING', '2021-12-08 00:00:00', NULL, 84, 113);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (322, 'MORNING', '2021-12-08 00:00:00', NULL, 84, 113);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (323, 'MORNING', '2021-12-09 00:00:00', NULL, 94, 113);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (324, 'MORNING', '2021-12-09 00:00:00', NULL, 94, 113);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (325, 'MORNING', '2021-12-09 00:00:00', NULL, 84, 113);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (339, 'MORNING', '2021-12-27 01:00:00', NULL, 96, 120);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (290, 'MORNING', '2021-12-20 00:00:00', NULL, 84, 107);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (291, 'MORNING', '2021-12-22 00:00:00', NULL, 89, 107);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (292, 'MORNING', '2021-12-22 00:00:00', NULL, 89, 107);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (293, 'MORNING', '2021-12-22 00:00:00', NULL, 84, 107);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (294, 'MORNING', '2021-12-24 00:00:00', NULL, 89, 107);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (295, 'MORNING', '2021-12-24 00:00:00', NULL, 89, 107);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (26, 'NOON', '2021-11-30 00:00:00', 'COMPLETED', 84, 40);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (25, 'MORNING', '2021-12-01 00:00:00', 'COMPLETED', 91, 40);
INSERT INTO public.training_position (training_position_id, time_of_day, training_date, status, training_id,
                                      training_plan_id)
VALUES (326, 'AFTERNOON', '2021-11-29 00:00:00', NULL, 84, 117);


--
-- Data for Name: wb_role; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.wb_role (role_id, authority)
VALUES ('73b0f88c-160a-4d1f-b93b-933b9e5d0202', 'ROLE_TRAINER');
INSERT INTO public.wb_role (role_id, authority)
VALUES ('d4357c92-52c9-4c59-9fff-71a930da6a40', 'ROLE_BASIC_USER');
INSERT INTO public.wb_role (role_id, authority)
VALUES ('2a812d8f-4f94-431e-a8b0-6002baaadcdb', 'ROLE_ADMIN');
INSERT INTO public.wb_role (role_id, authority)
VALUES ('9ba9d34c-4967-4584-8dfa-05d4ac03f7c9', 'ROLE_DOCTOR');
INSERT INTO public.wb_role (role_id, authority)
VALUES ('9ba9d38c-4967-4584-8dfa-05d4ac03f7c9', 'ROLE_DIETICIAN');


--
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: wellbeeing
--

INSERT INTO public.user_roles (id, role_id)
VALUES ('2627a231-319d-47a7-87c4-291be6c5f117', 'd4357c92-52c9-4c59-9fff-71a930da6a40');
INSERT INTO public.user_roles (id, role_id)
VALUES ('573a6160-a438-4a33-b456-11cc39f9adcf', 'd4357c92-52c9-4c59-9fff-71a930da6a40');
INSERT INTO public.user_roles (id, role_id)
VALUES ('8389d13a-be54-4186-a27a-905258dcaba2', 'd4357c92-52c9-4c59-9fff-71a930da6a40');
INSERT INTO public.user_roles (id, role_id)
VALUES ('ba85c50b-0b36-417b-a3d1-6f6a5022e597', 'd4357c92-52c9-4c59-9fff-71a930da6a40');
INSERT INTO public.user_roles (id, role_id)
VALUES ('dc722668-12a5-4c90-92bc-f348a3bf92c4', 'd4357c92-52c9-4c59-9fff-71a930da6a40');
INSERT INTO public.user_roles (id, role_id)
VALUES ('68514957-19f4-4764-a978-58d175948d7f', '73b0f88c-160a-4d1f-b93b-933b9e5d0202');
INSERT INTO public.user_roles (id, role_id)
VALUES ('68514957-19f4-4764-a978-58d175948d7f', '2a812d8f-4f94-431e-a8b0-6002baaadcdb');
INSERT INTO public.user_roles (id, role_id)
VALUES ('ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', 'd4357c92-52c9-4c59-9fff-71a930da6a40');
INSERT INTO public.user_roles (id, role_id)
VALUES ('573a6160-a438-4a33-b456-11cc39f9adcf', '9ba9d34c-4967-4584-8dfa-05d4ac03f7c9');
INSERT INTO public.user_roles (id, role_id)
VALUES ('dc722668-12a5-4c90-92bc-f348a3bf92c4', '9ba9d34c-4967-4584-8dfa-05d4ac03f7c9');
INSERT INTO public.user_roles (id, role_id)
VALUES ('b1bd6581-ff3f-470f-906b-2899540c930d', 'd4357c92-52c9-4c59-9fff-71a930da6a40');
INSERT INTO public.user_roles (id, role_id)
VALUES ('dc722668-12a5-4c90-92bc-f348a3bf92c4', '73b0f88c-160a-4d1f-b93b-933b9e5d0202');
INSERT INTO public.user_roles (id, role_id)
VALUES ('ebb5b5c4-0fd1-4aca-b8b8-5b6cd35b06ed', '9ba9d38c-4967-4584-8dfa-05d4ac03f7c9');
INSERT INTO public.user_roles (id, role_id)
VALUES ('68514957-19f4-4764-a978-58d175948d7f', 'd4357c92-52c9-4c59-9fff-71a930da6a40');


--
-- Name: activity_goal_activity_goal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.activity_goal_activity_goal_id_seq', 5, true);


--
-- Name: comment_comment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.comment_comment_id_seq', 3, true);


--
-- Name: exercise_exercise_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.exercise_exercise_id_seq', 562, true);


--
-- Name: exercise_in_training_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.exercise_in_training_id_seq', 176, true);


--
-- Name: follow_follow_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.follow_follow_id_seq', 2, true);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.hibernate_sequence', 71, true);


--
-- Name: likes_like_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.likes_like_id_seq', 22, true);


--
-- Name: opinion_opinion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.opinion_opinion_id_seq', 1, true);


--
-- Name: post_post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.post_post_id_seq', 12, true);


--
-- Name: role_request_role_req_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.role_request_role_req_id_seq', 7, true);


--
-- Name: sport_label_ailment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.sport_label_ailment_id_seq', 4, true);


--
-- Name: sport_label_sport_label_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.sport_label_sport_label_id_seq', 506, true);


--
-- Name: training_plan_training_plan_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.training_plan_training_plan_id_seq', 120, true);


--
-- Name: training_position_training_position_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.training_position_training_position_id_seq', 339, true);


--
-- Name: training_training_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wellbeeing
--

SELECT pg_catalog.setval('public.training_training_id_seq', 96, true);


--
-- PostgreSQL database dump complete
--

