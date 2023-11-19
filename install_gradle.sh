# Download and install Gradle
mkdir -p /opt
sudo wget https://services.gradle.org/distributions/gradle-8.4-bin.zip
sudo chmod +x gradle-8.4-bin.zip
sudo unzip -d /opt/gradle gradle-8.4-bin.zip
export GRADLE_HOME=/opt/gradle/gradle-8.4
export PATH=$PATH:/opt/gradle/gradle-8.4/bin