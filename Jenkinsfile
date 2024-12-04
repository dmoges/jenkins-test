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

        stage('OWASP Dependency Check'){
            steps{
                dependencyCheck additionalArguments: '--scan ./ --format HTML ', odcInstallation: 'OWASP Dependency-Check Vulnerabilities'
                dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
            }
        }
    }
}
