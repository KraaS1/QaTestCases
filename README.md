# qatestcases
Work with rest api of git 
Тест кейс 1: 
1. Выполнить rest-запрос и получить список репозиториев, название 
которых содержит Selenide, запрос должен иметь сортировку по 
звёздам на убывание, ответ на запрос сохранить

2. Перейти на github.com и выполнить поиск по тому же слову

3. Проверить, что первый элемент из ответа на запрос соответствует 
первому элементу в результатах поиска на UI, выполнить проверку 
по названию репозитория, короткому описанию, основному языку, 
лицензии и количеству звёзд.

4. Проверить, что общее количество найденных репозиториев на UI 
соответствует оному из запроса (519).

Тест кейс 2: 
1. Найти при помощи запроса и на UI репозиторий с наибольшим 
количеством звёзд

2. Убедиться, что в обеих случаях это один и тот же репозиторий (что 
нужно проверить описано в тест кейсе 1, пункт 3)

Тест кейс 3: 
1. Создать при помощи rest-запроса репозиторий в своём аккаунте 
(нужно завести рандомный аккаунт для этого задания, можно 
создать общий на всю группу)

2. Перейти на github.com, залогиниться под ранее созданным 
аккаунтом, проверить, что репозиторий создался и удалить его
