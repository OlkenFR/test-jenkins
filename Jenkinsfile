pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                bat 'mvn -B clean package'
            }
        }
        stage("Run Gatling") {
            steps {
                bat 'mvn gatling:test'
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