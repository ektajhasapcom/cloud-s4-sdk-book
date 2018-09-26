def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {

    def imageName = "ektajha/addressbooklatest"
    def  tag = "${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
    
   sh "docker run --privileged --name df-docker -d docker:17.06.0-dind"

   sh "docker run --rm -it --link df-docker:docker docker:17.06.0 sh"
   sh "docker version"
  sh "docker-compose --version"
  sh "docker run --rm -it ubuntu bash"
  sh "docker run --rm -it --link df-docker:docker teracy/ubuntu:16.04-dind-17.06.0-ce bash"
  sh "docker run --rm -it alpine sh"
    
    dockerExecute(script: this, dockerImage: 'teracy/ubuntu:16.04-dind-latest'){ 
        
         sh "docker version"
    }
     
    docker.withRegistry('https://registry.hub.docker.com', 'dockerCredentialId') {
        
    }
      
    originalStage()
    
}

return this
