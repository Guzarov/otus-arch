Инфраструктурные паттерны
Сделать простейший RESTful CRUD по созданию, удалению, просмотру и обновлению пользователей.
Пример API - https://app.swaggerhub.com/apis/otus55/users/1.0.0

Добавить базу данных для приложения.
Конфигурация приложения должна хранится в Configmaps.
Доступы к БД должны храниться в Secrets.
Первоначальные миграции должны быть оформлены в качестве Job-ы, если это требуется.
Ingress-ы должны также вести на url arch.homework/ (как и в прошлом задании)

На выходе должны быть предоставлена
1) ссылка на директорию в github, где находится директория с манифестами кубернетеса
2) инструкция по запуску приложения.
- команда установки БД из helm, вместе с файлом values.yaml.
- команда применения первоначальных миграций
- команда kubectl apply -f, которая запускает в правильном порядке манифесты кубернетеса
3) Postman коллекция, в которой будут представлены примеры запросов к сервису на создание, получение, 
изменение и удаление пользователя. Важно: в postman коллекции использовать базовый url - arch.homework.


Задание со звездочкой:
+3 балла за шаблонизацию приложения в helm 3 чартах
+2 балла за использование официального helm чарта для БД


docker login -u guzarov
docker biuld . -t guzarov/otus-arch:task2-app-v1
docker biuld . -t guzarov/otus-arch:task2-initdb
docker push guzarov/otus-arch:task2-app-v1
docker push guzarov/otus-arch:task2-initdb


helm install -f values.yaml task2-db bitnami/postgresql
helm install -f values.yaml task2-app .



helm delete task2-db



docker run --rm -p 5432:5432 --name my-postgres -e POSTGRES_PASSWORD=mysecretpassword postgres
psql -h localhost -U postgres
