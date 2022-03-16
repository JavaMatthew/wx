FROM openjdk:8-alpine
ARG JAR_FILE
ENV MEM 512m
ENV CONFIG ""
WORKDIR /opt
RUN sed -i "s/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g" /etc/apk/repositories
RUN apk add tzdata \
    && cp /usr/share/zoneinfo/Asia/Tokyo /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone \
    && apk del tzdata
ADD target/${JAR_FILE} app.jar
ENTRYPOINT java -Xms${MEM} -Xmx${MEM} -jar app.jar ${CONFIG}
