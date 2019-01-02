FROM selenium/node-chrome:latest

USER root

RUN apt-get -qqy update \
  && apt-get -qqy --no-install-recommends install \
    openjdk-8-jdk

ENV GRADLE_VERSION=4.9
ENV PATH=$PATH:/app/gradle-$GRADLE_VERSION/bin

RUN wget https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip -O /tmp/gradle-$GRADLE_VERSION-bin.zip \
  && unzip /tmp/gradle-$GRADLE_VERSION-bin.zip -d /app

COPY . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle dependencyCheckAnalyze --info

CMD ["gradle","build","--info"]
