FROM postgres:12-alpine
COPY init.sql /docker-entrypoint-initdb.d/
RUN chmod a+x /docker-entrypoint-initdb.d/init.sql