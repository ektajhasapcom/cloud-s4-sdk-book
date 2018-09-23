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
  serviceAccountName: cd-jenkins
  containers:
  - name: docker
    image: "docker.io/docker:dind"
  - name: kubectl
    image: gcr.io/cloud-builders/kubectl
    command:
    - cat
    tty: true
"""
}
   }
    options {
        timeout(time: 120, unit: 'MINUTES')
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
        skipDefaultCheckout()
    }
    stages {  
    
        
        stage('Docker Push') {
           container('docker') {
            steps {
                 withDockerRegistry([ credentialsId: "git", url: "https://hub.docker.com/" ]) {
                        // following commands will be executed within logged docker registry
                          sh 'docker push ektajha/mockserver:v10'
                 }
            }}
        }

    }
}
