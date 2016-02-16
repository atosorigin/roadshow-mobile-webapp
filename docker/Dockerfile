FROM tomcat:7-jre8

MAINTAINER "Darlington LDC"

ADD settings.xml /usr/local/tomcat/conf/
ADD tomcat-users.xml /usr/local/tomcat/conf/

COPY roadshow-1.0.war /usr/local/tomcat/webapps/roadshow.war

EXPOSE 8080
