pipeline {
    agent any

    tools {
        maven 'maven 3.9.11'
    }

    environment {
        DOCKER_IMAGE = "demo-app"
        CONTAINER_NAME = "springboot-container"
        REMOTE_USER = "ec2-user"
        REMOTE_HOST = "52.79.236.237"
        REMOTE_DIR = "/home/ec2-user/deploy"
        SSH_CREDENTIALS_ID = "7c9eb59f-8c52-4c9c-bcd1-fa48dacd7fc8"
    }

    stages {

        stage('Git Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Copy to Remote Server') {
            steps {
                sshagent (credentials: [env.SSH_CREDENTIALS_ID]) {
                    sh """
                    ssh -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null ${REMOTE_USER}@${REMOTE_HOST} "rm -rf ${REMOTE_DIR} && mkdir -p ${REMOTE_DIR}"
                    scp -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null -r . ${REMOTE_USER}@${REMOTE_HOST}:${REMOTE_DIR}/
                    """
                }
            }
        }

        stage('Remote Docker Build & Deploy') {
            steps {
                sshagent (credentials: [env.SSH_CREDENTIALS_ID]) {
                    sh """
                    ssh -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null ${REMOTE_USER}@${REMOTE_HOST} "
                        cd ${REMOTE_DIR}
                        docker rm -f ${CONTAINER_NAME} || true
                        docker build -t ${DOCKER_IMAGE} .
                        docker run -d --name ${CONTAINER_NAME} \\
                            -p 80:80 \\
                            -e SPRING_PROFILES_ACTIVE=prod \\
                            ${DOCKER_IMAGE}
                    "
                    """
                }
            }
        }
    }
}
