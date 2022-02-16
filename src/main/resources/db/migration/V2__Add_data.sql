INSERT INTO car (gos_number, car_type, model, driver_id)
VALUES ('A111AA777', 'Грузовой', 'ГАЗ', null),
       ('A222AA777', 'Грузовой', 'ЗИЛ', null),
       ('A333AA777', 'Легковой', 'Волга', null),
       ('A444AA777', 'Легковой', 'Лада', null),
       ('A555AA777', 'Автобус', 'ПАЗ', null),
       ('A666AA777', 'Автобус', 'Икарус', null);

INSERT INTO driving_license (id, categories, number, validity_period)
VALUES (1, 'B,C', 11111,TO_DATE('01-12-2025','dd-MM-yyyy')),
       (2, 'C,D', 22222,TO_DATE('05-12-2022','dd-MM-yyyy'));

INSERT INTO driver (id, name, driving_license_id)
VALUES (1, 'Ivanov Ivan', 1),
       (2, 'Petrov Petr', 2);



