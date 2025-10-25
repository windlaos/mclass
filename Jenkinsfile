pipeline {
    agent any

    tools {
        maven 'maven 3.9.11'
        jdk 'JAVA_HOME'
        git 'git'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Prepare Artifacts') {
            steps {
                sh '''
                rm -rf deploy
                mkdir -p deploy/static
                cp Dockerfile deploy/
                cp target/demo-0.0.1-SNAPSHOT.jar deploy/app.jar
                cp -R src/main/resources/static/images deploy/static/
                '''
            }
        }

        stage('Copy To Remote') {
            steps {
                sshagent(credentials: ['7c9eb59f-8c52-4c9c-bcd1-fa48dacd7fc8']) {
                    sh '''
                    ssh -o StrictHostKeyChecking=no ec2-user@52.79.236.237 '
                        sudo rm -rf /home/ec2-user/deploy &&
                        mkdir -p /home/ec2-user/deploy &&
                        sudo chown -R ec2-user:ec2-user /home/ec2-user/deploy
                    '

                    scp -o StrictHostKeyChecking=no -r deploy/* ec2-user@52.79.236.237:/home/ec2-user/deploy/
                    '''
                }
            }
        }

        stage('Docker Remote Deploy') {
            steps {
                sshagent(credentials: ['7c9eb59f-8c52-4c9c-bcd1-fa48dacd7fc8']) {
                    sh '''
                    ssh -o StrictHostKeyChecking=no ec2-user@52.79.236.237 '
                        cd /home/ec2-user/deploy &&
                        docker rm -f springboot-container || true &&
                        docker build -t demo-app . &&
                        docker run -d --name springboot-container \
                            -p 8080:8080 \
                            -e SPRING_PROFILES_ACTIVE=prod \
                            demo-app &&
                        sudo systemctl restart nginx
                    '
                    '''
                }
            }
        }
    }
}
