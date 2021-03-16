Prometheus. Grafana
Инструментировать сервис из прошлого задания метриками в формате Prometheus с помощью библиотеки для вашего фреймворка и ЯП.

Сделать дашборд в Графане, в котором были бы метрики с разбивкой по API методам:
1. Latency (response time) с квантилями по 0.5, 0.95, 0.99, max
2. RPS
3. Error Rate - количество 500ых ответов

Добавить в дашборд графики с метрикам в целом по сервису, взятые с nginx-ingress-controller:
1. Latency (response time) с квантилями по 0.5, 0.95, 0.99, max
2. RPS
3. Error Rate - количество 500ых ответов

Настроить алертинг в графане на Error Rate и Latency.

На выходе должно быть:
0) скриншоты дашборды с графиками в момент стресс-тестирования сервиса. Например, после 5-10 минут нагрузки.
1) json-дашборды.


Задание со звездочкой (+5 баллов)
Используя существующие системные метрики из кубернетеса, добавить на дашборд графики с метриками:
1. Потребление подами приложения памяти
2. Потребление подами приолжения CPU

Инструментировать базу данных с помощью экспортера для prometheus для этой БД.
Добавить в общий дашборд графики с метриками работы БД.

Альтернативное задание на 1 балл (если не хочется самому ставить prometheus в minikube) - https://www.katacoda.com/schetinnikov/scenarios/prometheus-client 



--------------------
- сделать дашборд с графиками метрик
- настроить алертинг
- нагрузочное тестирование


для microk8s нужно включить расширения storage и dns чтобы заработал постгрес

сборка
docker login -u guzarov
docker build -t guzarov/otus-arch:task3-app-v2 .
docker push  guzarov/otus-arch:task3-app-v2

установка чартов
helm repo add stable https://charts.helm.sh/stable
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update

установка СУБД из официальоного чарта с нужными значениями: 
helm install -f values.yaml task3-db bitnami/postgresql

установка прометея из официального чарта
helm install prom prometheus-community/kube-prometheus-stack -f prometheus.yaml --atomic

утсновка ingress nginx
helm install nginx ingress-nginx/ingress-nginx -f nginx-ingress.yaml --atomic

установка приложения вместе с job для первоначальной миграции из чарта: 
helm install -f values.yaml task3-app ./charts


проверка 
curl -H'Host: arch.homework' http://srv1/otusapp/guzarov/actuator/health

или (arch.homework в /etc/host)
newman run ../tests/task2-spring.postman_collection.json

проброс портов графаны и прометеуса
kubectl port-forward service/prom-kube-prometheus-stack-prometheus 9090
kubectl port-forward service/prom-grafana 9000:80

