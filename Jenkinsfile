pipeline {
    agent any
    tools {
        dockerTool 'docker'
        maven 'Maven3.9.1'
    }
    stages {
        stage('Git Clone') {
            steps {
                checkout scmGit(
                        branches: [[name: 'demo']],
                        userRemoteConfigs: [[url: 'https://github.com/vieer-v2solutions/microservices-project-testing-vieer.git']])
            }
        }
        stage('Maven Build') {
            steps {
                sh 'java --version'
                sh 'mvn -Dmaven.test.failure.ignore=true clean package'
            }
        }
        stage('Building Image') {
            steps {
                sh 'docker build . -t test'
            }
        }
    }
}
