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

        // run sonarqube test
        stage('Run Sonarqube') {
            environment {
                scannerHome = tool 'spring-boot-app-test';
            }
            steps {
              withSonarQubeEnv(credentialsId: 'token-spring-boot-app', installationName: 'SonarQube') {
                sh 'mvn sonar:sonar'
              }
            }
        }
    }
}
