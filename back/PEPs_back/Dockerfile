# 1. Use an existing image with Java 17 and Tomcat 9 (Matches your stack)
FROM tomcat:9.0-jdk17-openjdk-slim

# 2. Remove default Tomcat apps (optional, keeps it clean)
RUN rm -rf /usr/local/tomcat/webapps/*

# 3. Copy the WAR file into the Tomcat webapps folder
# We rename it to 'ROOT.war' so the app is available at "/" instead of "/PEPs_back"
COPY target/PEPs_back-0.1.war /usr/local/tomcat/webapps/ROOT.war

# 4. Expose port 8080 (Tomcat default)
EXPOSE 8080

# 5. Start Tomcat
CMD ["catalina.sh", "run"]