pipeline {
    agent any

     tools {
        jdk 'jdk17'
        maven 'maven3'
    }

    stages {
        stage('Which Java?') {
            steps {
                sh 'java --version'
            }
        }

        stage('Which Maven?') {
          agent {
              docker {
                  image 'maven:3.9.5-eclipse-temurin-17-alpine'
                  args '-v /root/.m2:/root/.m2'
              }
          }
          steps {
              sh 'mvn --version'
          }
        }

        stage('Code Checkout') {
            steps {
                git branch: 'main', changelog: false, poll: false, url: 'https://github.com/dmoges/jenkins-test'
            }
        }

       // stage('OWASP Dependency-Check Vulnerabilities'){
       //     steps{
       //         dependencyCheck additionalArguments: '''
       //           -o './'
       //           -s './'
       //           -f 'HTML'
       //           --prettyPrint''', odcInstallation: 'OWASP Dependency-Check Vulnerabilities'
       //         dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
       //     }
       // }


        stage('Build and Test') {
          agent {
              docker {
                  image 'maven:3.9.5-eclipse-temurin-17-alpine'
                  reuseNode true
              }
          }
          steps {
            sh 'ls -ltr'
            // build the project and create a JAR file
            sh './mvnw clean package'
          }
        }

        stage('Static Code Analysis') {
          agent {
              docker {
                  image 'maven:3.9.5-eclipse-temurin-17-alpine'
                  reuseNode true
                  args '-v /root/.m2:/root/.m2'
              }
          }
          environment {
            SONAR_URL = "http://192.168.1.10:9001"
          }
          steps {
            withCredentials([string(credentialsId: 'token-spring-boot-app', variable: 'SONAR_AUTH_TOKEN')]) {
              sh 'echo "sonar token - $SONAR_AUTH_TOKEN"'
              sh 'mvn sonar:sonar -Dsonar.login=$SONAR_AUTH_TOKEN -Dsonar.host.url=${SONAR_URL}'
            }
          }
        }

        // run sonarqube test
        //stage('Run Sonarqube') {
        //  agent {
        //      docker {
        //          image 'maven:3.9.5-eclipse-temurin-17-alpine'
        //          args '-v /root/.m2:/root/.m2'
        //          reuseNode true
        //      }
        //  }
        //    environment {
        //        SCANNER_HOME = tool 'spring-boot-app-test';
        //    }

            //steps {
            //  withSonarQubeEnv(credentialsId: 'token-spring-boot-app', installationName: 'SonarQube') {
            //    sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.11.0.3922:sonar'
            //  }
            //}

          //  steps {
          //      withSonarQubeEnv(credentialsId: 'token-spring-boot-app', installationName: 'SonarQube') {
          //          sh '''
          //              $SCANNER_HOME/bin/sonar-scanner \
          //              -Dsonar.projectKey=EKART \
          //              -Dsonar.projectName=EKART \
          //              -Dsonar.java.binaries=./target
          //          '''
          //      }
          //  }

        //}
    }
}
