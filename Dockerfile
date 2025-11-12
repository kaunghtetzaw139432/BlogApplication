# 1. Base image
FROM openjdk:17-jdk

# 2. Set working directory inside container
WORKDIR /app

# 3. Copy JAR file from host into container
COPY target/BLOG_APPLICATION-1.0.0.jar /app/BLOG_APPLICATION.jar

# 4. Run the JAR file
ENTRYPOINT ["java","-jar","BLOG_APPLICATION.jar"]

EXPOSE 8080
