pipeline {
    agent { dockerfile true }
    stages {
        stage('Test') {
            steps {
                sh 'java --version'
                sh 'ls -al /home/gradle/src/build'
            }
        }
    }
}