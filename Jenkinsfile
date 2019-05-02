pipeline {
    agent { dockerfile true }
    stages {
        stage('Build') {
            steps {
                script {
                    docker_image = docker.build("${env.DOCKER_IMAGE_TAG}", '-f ./Dockerfile .')
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    docker_image.withRun("") {
                        sh 'hostname'
                    }
                }
            }
        }
    }
}