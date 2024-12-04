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
                sh 'maven --version'
            }
        }
    }
}
