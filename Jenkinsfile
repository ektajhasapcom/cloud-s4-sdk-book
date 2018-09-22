#!/usr/bin/env groovy

final def pipelineSdkVersion = 'master'

pipeline {
    agent {
    kubernetes {
      label 'jenkinsslave'
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
labels:
  component: ci
spec:
  # Use service account that can deploy to all namespaces
  serviceAccountName: default
  containers:
  - name: docker
    image: docker:edge
    command:
    - cat
    tty: true
  - name: kubectl
    image: lachlanevenson/k8s-kubectl
    command:
    - cat
    tty: true
"""
}
    }
    
     tools {
        maven 'M3'
    }
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
    
        
        stage('Docker Push') {
            steps {
                 withDockerRegistry([ credentialsId: "docker", url: "https://hub.docker.com/" ]) {
                        // following commands will be executed within logged docker registry
                          sh 'docker push ektajha/mockserver:v10'
                 }
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
