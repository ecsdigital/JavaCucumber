pipeline {
    agent any

    environment {
        DOCKER_IMAGE_TAG = "javacucumber:build-${env.BUILD_ID}"
    }

    stages {
        stage('Build') {
            steps {
                script {
                    docker_image = docker.build("${env.DOCKER_IMAGE_TAG}", '.')
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    docker_image.inside() {
                        sh "echo 'Hello World'"
                    }
                    def methods = docker_image.methods.collect { it.name }
                    println methods.each { println it }
                }
            }
        }
    }
}