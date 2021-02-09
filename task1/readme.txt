
Основы работы с Kubernetes (часть 2)

- Создать минимальный сервис, который
    1) отвечает на порту 8000
    2) имеет http-метод
    GET /health/
    RESPONSE: {"status": "OK"}

- Cобрать локально образ приложения в докер.
- Запушить образ в dockerhub

- Написать манифесты для деплоя в k8s для этого сервиса.
    Манифесты должны описывать сущности Deployment, Service, Ingress.
    В Deployment могут быть указаны Liveness, Readiness пробы.
    Количество реплик должно быть не меньше 2. Image контейнера должен быть указан с Dockerhub.

    В Ingress-е должно быть правило, которое форвардит все запросы с /otusapp/{student name}/* на сервис с rewrite-ом пути. 
    Где {student name} - это имя студента.
    Хост в ингрессе должен быть arch.homework. В итоге после применения манифестов GET запрос на http://arch.homework/otusapp/{student name}/health должен отдавать {“status”: “OK”}.

- На выходе предоставить
    0) ссылку на github c манифестами. Манифесты должны лежать в одной директории, так чтобы можно было их все применить одной командой kubectl apply -f .
    1) url, по которому можно будет получить ответ от сервиса (либо тест в postmanе).

-------------

структура каталогов:
    app-go/ - сервис на go
    app-quarkus/ - сервис на java+quarkus, не использует из-за неприемлемого времени цикла сборки
    deploy/ - дескрипторы развертывания в k8s
    tests/ - тесты postman

команды
- сборка docker image
docker build -t guzarov/otus-arch:class5-quarkus ./app-quarkus
docker build -t guzarov/otus-arch:class5-go ./app-go

- загрузка в dokcerhub
docker login -u guzarov
docker push guzarov/otus-arch:class5-quarkus
docker push guzarov/otus-arch:class5-go

- настрйока кубернетис в doker desktop (однократно)
kubectl create namespace class5
kubectl config set-context --current --namespace=class5
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
helm install ingress-nginx ingress-nginx/ingress-nginx

- деплой сервиса в кубернетис
kubectl apply -f ./deploy/

- проверка
curl -H'Host: arch.homework' http://localhost/otusapp/guzarov/health

- запуск тестов postman
newman run ./tests/class5-quarkus.postman_collection.json


- разное
kubectl delete all --all
kubectl delete ingress
helm uninstall ingress-nginx
kubectl run -it --rm busybox --image=busybox



