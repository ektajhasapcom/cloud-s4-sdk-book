FROM openjdk:10

ENV PATH /usr/local/tomee/bin:$PATH
RUN mkdir -p /usr/local/tomee

WORKDIR /usr/local/tomee

RUN set -x \
	&& curl -fSL https://repo.maven.apache.org/maven2/org/apache/tomee/apache-tomee/7.1.0/apache-tomee-7.1.0-webprofile.tar.gz.asc -o tomee.tar.gz.asc \
	&& curl -fSL https://repo.maven.apache.org/maven2/org/apache/tomee/apache-tomee/7.1.0/apache-tomee-7.1.0-webprofile.tar.gz -o tomee.tar.gz \
	&& tar -zxf tomee.tar.gz \
	&& mv apache-tomee-webprofile-7.1.0/* /usr/local/tomee \
	&& rm -Rf apache-tomee-webprofile-7.1.0 \
	&& rm bin/*.bat \
	&& rm tomee.tar.gz*

COPY application/target/address-manager-application.war /usr/local/tomee/webapps/address-manager-application.war

CMD ["catalina.sh", "run"]
