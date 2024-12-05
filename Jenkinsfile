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
                scannerHome = tool 'SonarQube';
            }
            steps {
              withSonarQubeEnv(credentialsId: 'a token for a spring boot application used for CI/CD testing', installationName: 'SonarQube') {
                sh "${scannerHome}/bin/sonar-scanner"
              }
            }
        }
    }
}
