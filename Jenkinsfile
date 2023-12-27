pipeline {
    agent any
    tools {
        maven 'Maven3.9.1'
    }
    environment {
        DOCKER_HOST = '${env.DOCKER_HOST}'
        SONAR_HOST_URL = 'http://44.210.111.48'
        SONAR_LOGIN = credentials('sonarqube')
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
                            -Dsonar.login=${SONAR_LOGIN} 
                        """
                    }
                }
            }
        }
    }
}
