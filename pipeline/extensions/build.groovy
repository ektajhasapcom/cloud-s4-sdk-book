def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {

    def imageName = "ektajha/addressbooklatest"
    def  tag = "${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
    
    originalStage()
    
     dockerExecute(script: this, dockerImage: 'ektajha/k8shelm:v1'){ 
        withCredentials([
            [$class: 'UsernamePasswordMultiBinding', 
            credentialsId: dockerCredentialId, 
            passwordVariable: 'password', usernameVariable: 'user']
              ]){
                  sh "docker login --username $user --password $password "
                  sh "docker build -t ${imageName}:${tag} ."
                  sh "docker push ${imageName}:${tag}"     
              }
    }
    
    
}

return this