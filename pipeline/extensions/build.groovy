def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
    dockerExecute(script: this, dockerImage: 'gitlab/gitlab-ce:nightly'){ 
        
         sh "docker version"
    }
     
    docker.withRegistry('https://registry.hub.docker.com', 'dockerCredentialId') {
        
    }
      
    originalStage()
    
}

return this
