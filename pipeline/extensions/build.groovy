def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
   
    
    dockerExecute(script: this, dockerImage: 'ektajha/dd:v1'){ 
         sh "service --version"
         sh "sudo service docker restart"
         sh "docker version"
    }
    
    originalStage()
}

return this
