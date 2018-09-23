def  tag = "${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
def imageName = "ektajha/addressbooklatest"


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
                 sh "mvn clean install -Dmaven.test.failure.ignore=true"
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
    
      stage('Deploy') {
        steps {
             container('kubectl') {
                helm install --name addressbook  addressbook --set imagetag=${tag}
             }
          }
       }
   }
}
