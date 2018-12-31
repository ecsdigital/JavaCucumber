FROM python:2.7-stretch

RUN apt-get update && apt-get install -yq \
    firefox-esr=60.4.0esr-1~deb9u1 \
    chromium=71.0.3578.80-1~deb9u1  \
    git-core=1:2.11.0-3+deb9u4 \
    xvfb=2:1.19.2-1+deb9u5 \
    xsel=1.2.0-2+b1 \
    unzip=6.0-21 \
    python-pytest=3.0.6-1 \
    libgconf2-4=3.2.6-4+b1 \
    libncurses5=6.0+20161126-1+deb9u2 \
    libxml2-dev=2.9.4+dfsg1-2.2+deb9u2 \
    libxslt-dev \
    libz-dev \
    xclip=0.12+svn84-4+b1 \
    curl xvfb chromium

# GeckoDriver v0.19.1
RUN wget -q "https://github.com/mozilla/geckodriver/releases/download/v0.19.1/geckodriver-v0.19.1-linux64.tar.gz" -O /tmp/geckodriver.tgz \
    && tar zxf /tmp/geckodriver.tgz -C /usr/bin/ \
    && rm /tmp/geckodriver.tgz 
# chromeDriver v2.35
RUN wget -q "https://chromedriver.storage.googleapis.com/2.35/chromedriver_linux64.zip" -O /tmp/chromedriver.zip \
    && unzip /tmp/chromedriver.zip -d /usr/bin/ \
    && rm /tmp/chromedriver.zip 

RUN  chmod 777 /usr/bin/chromedriver \
      && chmod 777 /usr/bin/geckodriver

FROM gradle:jdk10 as builder

#RUN apk update && \
#	apk add python py-pip curl unzip dbus-x11 ttf-freefont firefox-esr xvfb && \
#	pip install selenium && \
#	pip install pyvirtualdisplay

EXPOSE 4444 
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew build --stacktrace --debug
COPY . /app
WORKDIR /app
