pipeline {
    agent any
    tools {
        jdk 'java-21'
    }
    stages {
        stage("Build") {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }
        stage("Run Gatling") {
            steps {
                sh 'mvn gatling:test'
            }
            post {
                always {
                    gatlingArchive()
                }
            }
        }
        stage('Deploy') {
            steps {
                bat "mvn jar:jar deploy:deploy"
            }
        }
    }
}