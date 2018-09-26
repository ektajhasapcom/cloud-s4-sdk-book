def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
   
    sh "docker run --privileged -t -i docker:dind"
    
    dockerExecute(script: this, dockerImage: 'ektajha/dd:v1'){ 

         sh "docker version"
    }
    
    originalStage()
}

return this
