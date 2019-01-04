FROM ecsdigital/selenium-jdk-gradle-dependencycheck

ARG GRADLE_SOURCE=/home/gradle/src

COPY . ${GRADLE_SOURCE}
WORKDIR ${GRADLE_SOURCE}

CMD ["gradle","build","--info"]
