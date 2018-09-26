def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {

    def imageName = "ektajha/addressbooklatest"
    def  tag = "${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
    
    dockerExecute(script: this, dockerImage: 'docker/dind'){ 
        
         sh "docker version"
    }
     
    docker.withRegistry('https://registry.hub.docker.com', 'dockerCredentialId') {
        
    }
      
    originalStage()
    
}

return this
