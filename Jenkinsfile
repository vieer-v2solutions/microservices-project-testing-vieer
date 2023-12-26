pipeline {
    agent any
    tools {
        maven 'Maven3.9.1'
    }
    environment {
        SONAR_HOST_URL = 'http://44.210.111.48'
        SONAR_LOGIN = credentials('sonarqube')
        SONAR_PROJECT_KEY = 'hotelMicroservice'
        SONAR_PROJECT_NAME = 'hotelMicroservice'
        SONAR_SCANNER_VERSION = '4.6.0.2311'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'Hotel-Microservice', url: 'https://github.com/vieer-v2solutions/microservices-project-testing-vieer.git'
            }
        }
        stage('Build') {

            steps {
                script {
                    sh 'java --version'
                    sh 'mvn -Dmaven.test.failure.ignore=true clean package'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('SonarQube') {
                        sh """
                            mvn sonar:sonar \
                            -Dsonar.host.url=${SONAR_HOST_URL} \
                            -Dsonar.login=${SONAR_LOGIN} \
                            -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                            -Dsonar.projectName=${SONAR_PROJECT_NAME} \
                            -Dsonar.scanner.version=${SONAR_SCANNER_VERSION}
                        """
                    }
                }
            }
        }
    }
}
