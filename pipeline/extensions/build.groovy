def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {

      def repositoryName = 'ektajha'
      def  appName = 'addressbook'
      def  imageTag = "docker.io/${repositoryName}/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"  
      
      def buildName = "build-${env.BUILD_NUMBER}"  
     
     dockerExecute(script: this, dockerImage: 'ektajha/k8shelm:v1'){ 
        withCredentials([[
                $class: 'FileBinding',
                credentialsId: 'k8s-credentials',
                variable: 'KUBECONFIG'
            ]]){
                 sh "kubectl --kubeconfig=$KUBECONFIG" 
                 sh "sed -i.bak 's#provideImageName#${imageTag}#' build.yaml"
                 sh "sed -i.bak 's#buildName#${buildName}#' build.yaml"         
                 sh "kubectl apply -f build.yaml -n qa"            
              }
    }

}

return this
