# 第一阶段：使用适当的 Maven 镜像进行构建  
FROM maven

# 设置工作目录  
WORKDIR /app  

# 将 pom.xml 复制到工作目录  
COPY System/pom.xml .  

# 将源代码复制到工作目录  
COPY System/src ./src  

# 下载依赖并构建项目  
RUN mvn clean package -DskipTests  

# 第二阶段：使用 OpenJDK 运行时  
FROM openjdk:17  

# 设置工作目录  
WORKDIR /app  

# 从构建阶段复制生成的 JAR 文件  
COPY --from=build /app/target/*.jar app.jar  

# 设置容器启动时运行的命令  
CMD ["java", "-jar", "app.jar"]  
