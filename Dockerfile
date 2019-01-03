FROM selenium/node-chrome:latest

USER root

ARG GRADLE_SOURCE=/home/gradle/src

RUN apt-get -qqy update \
  && apt-get -qqy --no-install-recommends install \
    openjdk-8-jdk

COPY . ${GRADLE_SOURCE}
WORKDIR ${GRADLE_SOURCE}

RUN ${GRADLE_SOURCE}/gradlew dependencyCheckAnalyze --info

CMD ["./gradlew","build","--info"]
