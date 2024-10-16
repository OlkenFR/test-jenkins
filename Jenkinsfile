pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                sh 'mvn -B clean package'
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
                sh "mvn jar:jar deploy:deploy"
            }
        }
    }
}