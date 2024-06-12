FROM postgres:16.3

ENV POSTGRES_PASSWORD=password
COPY ./10-postgresql-init.sql /docker-entrypoint-initdb.d/10-postgresql-init.sql

