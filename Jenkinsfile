def  tag = "${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
def imageName = "ektajha/addressbooklatest"

final def pipelineSdkVersion = 'master'


pipeline {
  agent any
  
  options {
        timeout(time: 120, unit: 'MINUTES')
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
        skipDefaultCheckout()
  }

  
  tools {
        maven 'M3'
  }

  stages {   
    
       stage('Init') {
          steps {
                checkout scm
          }
        }
    
      stage('Build') {
             steps {
                 library "s4sdk-pipeline-library@${pipelineSdkVersion}"
                 library "piper-library-os@${pipelineSdkVersion}"
                 stageBuild script: this
             }
      }
    
      stage('Build and push image with Container Builder') {
        steps {
           container('docker') {
              sh "docker login --username ektajha --password Mapapaji@99"
             sh "docker build -t ${imageName}:${tag} ."
              sh "docker push ${imageName}:${tag} "
           }
         }
       }
    
      stage('Deploy to Production') {
        steps {
             container('helm') {
                sh "helm init --upgrade"
                sh "sleep 40"
                sh "helm upgrade --install --force addrbook --set imageversion=$tag addressbook"
             }
          }
      }
      
    
      stage('Deployed result') {
        steps {
             container('kubectl') {
                sh "kubectl get pods"
             }
          }
       }
   }
}
