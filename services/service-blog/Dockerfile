FROM java:8
COPY *.jar /app.jar
EXPOSE 8899
CMD ["--server.port=8899"]
ENTRYPOINT ["java", "-jar", "/app.jar"]
