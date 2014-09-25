#Dump postgres DB
pg_dump --host localhost --port 5432 --username "retail" --role "retail" -W --format plain --blobs --encoding UTF8 --create --clean > NetBeansProjects/GTRetail-MVN/GTRetail-ejb/src/main/resources/META-INF/DDL.sql

#Dump data
pg_dump --column-inserts --data-only --host localhost --port 5432 --username "retail" --role "retail" -W > NetBeansProjects/GTRetail-MVN/GTRetail-ejb/src/main/resources/META-INF/Data.sql

#Dump for PGDiff
pg_dump -h prod.server.com -s -f original.sql cool-app-db
pg_dump -h dev.server.com -s -f new.sql cool-app-db

#Diff
java -jar apgdiff-2.4.jar --ignore-start-with original.sql new.sql > upgrade.sql
