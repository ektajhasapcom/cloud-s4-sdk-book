def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {

    def imageName = "ektajha/addressbooklatest"
    def  tag = "${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
    
    originalStage()
     
     sh "docker run -v /var/run/docker.sock:/var/run/docker.sock -t docker:dind"
    
     withCredentials([usernamePassword(credentialsId: 'dockerCredentialId', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                  sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                  sh "docker build -t ${imageName}:${tag} ."
                  sh "docker push ${imageName}:${tag}"     
     }
    
}

return this
