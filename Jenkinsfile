pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/SarujanM/tempConverter.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco(execPattern: '**/target/jacoco.exec')
            }
        }
        stage('Docker Build') {
            steps {
                bat 'docker build -t sarujanm2/tempconverter:latest .'
            }
        }
        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
                    bat 'docker push sarujanm2/tempconverter:latest'
                }
            }
        }
    }
}