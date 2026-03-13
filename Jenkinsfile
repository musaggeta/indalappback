pipeline {
    agent any

    tools {
        maven 'mvn'
    }

    stages {
        stage('Github') {
            steps {
                git branch: 'main', url: 'https://github.com/musaggeta/IndalApp.git'
            }
        }

        stage('Inspect Workspace') {
            steps {
                bat 'dir'
                bat 'dir /s /b pom.xml'
            }
        }

        stage('Clean') {
            steps {
                dir('indalappback') {
                    bat 'mvn clean'
                }
            }
        }

        stage('Compile') {
            steps {
                dir('indalappback') {
                    bat 'mvn compile'
                }
            }
        }

        stage('Test') {
            steps {
                dir('indalappback') {
                    bat 'mvn test'
                }
            }
        }

        stage('Package') {
            steps {
                dir('indalappback') {
                    bat 'mvn package'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline ejecutado correctamente: backend compilado, testeado y empaquetado.'
        }
        failure {
            echo 'El pipeline falló. Revisa los logs del build.'
        }
    }
}
