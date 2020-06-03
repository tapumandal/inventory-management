FROM openjdk:8
ADD target/ims-server-app.jar ims-server-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ims-server-app.jar"]

## MySql pull then run with credentials
# docker run --name mysql-server -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=ims-db -e MYSQL_USER=ims-user -e MYSQL_PASSWORD=password -d mysql:5.6
#
# docker run --net=my-network --name mysql-server -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=ims-db -e MYSQL_USER=ims-user -e MYSQL_PASSWORD
#=password -d mysql:5.6

## phpmyadmin pull then run with mysql server linked
# docker run --name myphpmyadmin -d --link mysql-server:db -p 8001:80 phpmyadmin/phpmyadmin

## Spring app Build
# docker build . -t ims-server-app
## App Run and link with mysql server
# docker run -p 8080:8080 --name ims-server-app  --link mysql-server:mysql -d ims-server-app

#RUN AND BUILD TOGETHER WITHN SPECIFIC NETWORKS
#docker build -t ims-server-app . && docker run --net=my-network -p 8080:8080 --name ims-server-app  --link mysql-server:mysql -d
#ims-server-app

#docker build -t ims-server-app . && docker run -p 8080:8080 --name ims-server-app --link mysql-server:mysql ims-server-app