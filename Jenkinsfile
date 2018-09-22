#!/usr/bin/env groovy

final def pipelineSdkVersion = 'master'

pipeline {
    agent any	
    options {
        timeout(time: 120, unit: 'MINUTES')
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
        skipDefaultCheckout()
    }
    stages {
        stage('Init') {
            steps {
                library "s4sdk-pipeline-library@${pipelineSdkVersion}"
                stageInitS4sdkPipeline script: this
                abortOldBuilds script: this
            }
        }
    }
    post {
        success{
            echo "Thats how simple it is to use CI/CD with SAP S/4HANA Cloud SDK."
            echo "Please bring the code SAP-GN-ITWASFUN to us and win a Goodie."
        }
        always {
            script {
                if (commonPipelineEnvironment.configuration.skipping.SEND_NOTIFICATION) {
                    postActionSendNotification script: this
                }
            }
        }
        failure { deleteDir() }
    }
}
