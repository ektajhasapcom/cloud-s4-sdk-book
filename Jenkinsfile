


pipeline {
  agent any
  
  tools {
        maven 'M3'
  }

  stages {    
    
       stage('Deploy') {
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
                  - name: kubectl
                    image: lachlanevenson/k8s-kubectl
                    command:
                    - cat
                   tty: true
  
                   """
                  }
               }
        steps {
             container('kubectl') {
               sh "kubectl get pods"
             }
          }
       }
    
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
              sh "docker build -t java-image:v1 ."
              sh "docker tag java-image:v1 ektajha/java-image:v1"
              sh "docker push ektajha/java-image:v1"
           }
         }
       }
   }
}
