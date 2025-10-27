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
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Prepare Artifacts') {
            steps {
                sh '''
                rm -rf deploy
                mkdir -p deploy/static/images
                mkdir -p deploy/templates

                cp Dockerfile deploy/
                cp target/demo-0.0.1-SNAPSHOT.jar deploy/app.jar

                # ✅ images 복사
                cp -R src/main/resources/static/images/ deploy/static/images/
                cp -R src/main/resources/templates/ deploy/templates/
                '''
            }
        }

        stage('Copy To Remote') {
            steps {
                sshagent(credentials: ['7c9eb59f-8c52-4c9c-bcd1-fa48dacd7fc8']) {
                    sh '''
                    ssh -o StrictHostKeyChecking=no ec2-user@52.79.236.237 "
                        sudo rm -rf /hom


