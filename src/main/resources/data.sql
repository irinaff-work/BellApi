

INSERT INTO Country ( id, citizenship_code, citizenship_name) VALUES (1, '643','Российская Федерация');

INSERT INTO Country ( id, citizenship_code, citizenship_name) VALUES (2, '112','Республика Беларусь');

INSERT INTO Document_type (id, doc_code, doc_name) VALUES (1, '21','Паспорт гражданина РФ');

INSERT INTO Document_type (id, doc_code, doc_name) VALUES (2, '03','Свидетельство о рождении');

INSERT INTO Document (id, doc_type_id, doc_number, doc_date) VALUES (1, 1, '4562126712', '2013-03-12');

INSERT INTO Document (id, doc_type_id, doc_number, doc_date) VALUES (2, 1, '4789678133', '2015-09-25');

INSERT INTO Organization (id, name, full_name,inn, kpp, address, phone, is_active)
VALUES (1, 'Рога и Копыта', 'ООО Рога и Копыта и товарищи','123456789012','123456789','г.Мшанск, ул. Ленина, д.2, строение 7',
        '89177523456', TRUE);

INSERT INTO Organization (id, name, full_name, inn, kpp, address, is_active)
VALUES (2, 'Рога и Копыта', 'ООО Салют', '123456789012', '123456789', 'г.Большая Поляна, ул. Батырская, д.1', TRUE);

INSERT INTO Office (id, org_id, name, address, phone, is_active) VALUES (1, 1, 'Офис 1',
         'г.Мшанск, ул. Ленина, д.2, строение 7, к.2', '89177523456', TRUE );

INSERT INTO Office (id, org_id, name, address, is_active) VALUES (2, 2, 'Офис 2',
         'г.Большая Поляна, ул. Батырская, д.1, к.2', TRUE );

INSERT INTO User (id, office_id, doc_id, country_id, first_name, last_name, middle_name, phone, position, is_identified)
VALUES (1, 1, 1, 1, 'Владимир','Железняк','Сергеевич','89177523111','менеджер',TRUE );

INSERT INTO User (id, office_id, doc_id, country_id, first_name, last_name, middle_name, phone, position, is_identified)
VALUES (2, 2, 2, 1, 'Николай','Медведков','Алексеевич','89177523111','секретарь',TRUE );