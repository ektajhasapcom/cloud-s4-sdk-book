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

    stage('Build and push image with Container Builder') {
      steps {
        container('docker') {
          sh "docker build -t java-image:v1 ."
        }
      }
    }
  }
}
