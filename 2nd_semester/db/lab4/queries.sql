-- Сделать запрос для получения атрибутов из указанных таблиц, применив фильтры по указанным условиям:
-- Таблицы: Н_ЛЮДИ, Н_ВЕДОМОСТИ.
-- Вывести атрибуты: Н_ЛЮДИ.ИМЯ, Н_ВЕДОМОСТИ.ДАТА.
-- Фильтры (AND):
-- a) Н_ЛЮДИ.ИД < 142095.
-- b) Н_ВЕДОМОСТИ.ИД < 1457443.
-- c) Н_ВЕДОМОСТИ.ИД < 1250972.
-- Вид соединения: RIGHT JOIN.

SELECT nl.ИМЯ, nv.ДАТА
FROM Н_ЛЮДИ nl
RIGHT JOIN Н_ВЕДОМОСТИ nv ON nv.ЧЛВК_ИД = nl.ИД
WHERE nl.ИД < 142095
    AND nv.ИД < 1457443
    AND nv.ИД < 1250972;

-- Сделать запрос для получения атрибутов из указанных таблиц, применив фильтры по указанным условиям:
-- Таблицы: Н_ЛЮДИ, Н_ОБУЧЕНИЯ, Н_УЧЕНИКИ.
-- Вывести атрибуты: Н_ЛЮДИ.ФАМИЛИЯ, Н_ОБУЧЕНИЯ.НЗК, Н_УЧЕНИКИ.ГРУППА.
-- Фильтры: (AND)
-- a) Н_ЛЮДИ.ИД = 100865.
-- b) Н_ОБУЧЕНИЯ.НЗК = 933232.
-- Вид соединения: LEFT JOIN.

SELECT nl.ФАМИЛИЯ, o.НЗК, nu.ГРУППА
FROM Н_ЛЮДИ nl
LEFT JOIN Н_ОБУЧЕНИЯ o ON o.ЧЛВК_ИД = nl.ИД
LEFT JOIN Н_УЧЕНИКИ nu ON nu.ЧЛВК_ИД = nl.ИД
WHERE nl.ИД = 100865
    AND o.НЗК = '933232';

-- EXPLAIN ANALYZE

EXPLAIN ANALYZE SELECT nl.ИМЯ, nv.ДАТА
FROM Н_ЛЮДИ nl
RIGHT JOIN Н_ВЕДОМОСТИ nv ON nv.ЧЛВК_ИД = nl.ИД
WHERE nl.ИД < 142095
    AND nv.ИД < 1457443
    AND nv.ИД < 1250972;

EXPLAIN ANALYZE SELECT nl.ФАМИЛИЯ, o.НЗК, nu.ГРУППА
FROM Н_ЛЮДИ nl
LEFT JOIN Н_ОБУЧЕНИЯ o ON o.ЧЛВК_ИД = nl.ИД
LEFT JOIN Н_УЧЕНИКИ nu ON nu.ЧЛВК_ИД = nl.ИД
WHERE nl.ИД = 100865
    AND o.НЗК = '933232';
