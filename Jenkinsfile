pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK21'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/your-repo/your-project.git', branch: 'evy', credentialsId: 'evy'
            }
        }
        stage('Build & Test') {
            steps {
                script {
                    bat 'mvn clean test'
                }
            }
        }
        stage('Generate Allure Report') {
            when {
                expression {
                    currentBuild.result == 'SUCCESS' || currentBuild.result == 'UNSTABLE'
                }
            }
            steps {
                script {
                    bat 'mvn allure:report'
                }
            }
        }
        stage('Publish Allure Report') {
            when {
                expression {
                    currentBuild.result == 'SUCCESS' || currentBuild.result == 'UNSTABLE'
                }
            }
            steps {
                allure includeProperties: false, results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'  // Publish JUnit test results
            allure includeProperties: false, results: [[path: 'target/allure-results']]  // Always attempt to publish the Allure report
        }
    }
}
