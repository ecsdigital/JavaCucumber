pipeline {
    agent { dockerfile true }
    stages {
        stage('Test') {
            steps {
                sh 'hostname'
                sh 'ls -al /home/gradle/src/build'
            }
        }
    }
}