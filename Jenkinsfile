#!/usr/bin/env groovy

final def pipelineSdkVersion = 'master'

pipeline {
    agent any
    
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
        
        
    podTemplate(label: 'jenkins-pipeline', containers: [
    containerTemplate(name: 'docker', image: 'docker:1.12.6', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'golang', image: 'golang:1.8.3', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'helm', image: 'lachlanevenson/k8s-helm:v2.6.0', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'kubectl', image: 'lachlanevenson/k8s-kubectl:v1.4.8', command: 'cat', ttyEnabled: true)
],
volumes:[
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
])
        
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
