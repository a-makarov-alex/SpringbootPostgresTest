#запускаем контейнер с базой
docker run --name some-postgres --volume db-data:/var/lib/postgresql/data -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=second -p 5434:5432 postgres:12-alpine