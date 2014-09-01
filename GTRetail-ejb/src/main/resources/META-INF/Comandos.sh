#Dump postgres DB
pg_dump --host localhost --port 5432 --username "retail" --role "retail" -W --format plain --blobs --encoding UTF8 --create --clean > NetBeansProjects/GTRetail-MVN/GTRetail-ejb/src/main/resources/META-INF/DDL.sql

#Dump data
pg_dump --column-inserts --data-only --host localhost --port 5432 --username "retail" --role "retail" -W > NetBeans\ Projects/GTRetail-MVN/GTRetail-ejb/src/main/resources/META-INF/Data.sql
