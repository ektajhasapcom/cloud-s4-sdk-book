#!/usr/bin/env groovy

final def pipelineSdkVersion = 'master'

pipeline {
    agent {
    kubernetes {
      label 'jenkins-jenkins-slave '
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
  - name: gcloud
    image: "docker.io/dockerkyma/java-address-book-example:v10"
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
        stage('Init') {
            steps {
                library "s4sdk-pipeline-library@${pipelineSdkVersion}"
                stageInitS4sdkPipeline script: this
                abortOldBuilds script: this
            }
        }
        
         stage('Deploy Canary') {
      // Canary branch
      when { branch 'google-next' }
      steps {
        container('kubectl') {
          // Change deployed image in canary to the one we just built
          sh("sed -i.bak 'docker.io/dockerkyma/java-address-book-example:v10' ./k8s/canary/*.yaml")
          sh("kubectl --namespace=production apply -f k8s/services/")
          sh("kubectl --namespace=production apply -f k8s/canary/")
          sh("echo http://`kubectl --namespace=production get service/${feSvcName} -o jsonpath='{.status.loadBalancer.ingress[0].ip}'` > ${feSvcName}")
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
