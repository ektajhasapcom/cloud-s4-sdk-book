def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
     
     def repositoryName = 'ektajha'
     def  appName = 'addressbook'
     def  imageTag = "docker.io/${repositoryName}/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}" 
     
     dockerExecute(script: this, dockerImage: 'ektajha/k8shelm:v1'){ 
        withCredentials([[
                $class: 'FileBinding',
                credentialsId: 'k8s-credentials',
                variable: 'KUBECONFIG'
            ]]){
                 sh "kubectl --kubeconfig=$KUBECONFIG"
                 sh "kubectl get pods"
                 sh "sed -i.bak 's#provideImageName#${imageTag}#' deployment.yaml"
                 sh "kubectl apply -f deployment.yaml -n qa"            
              }
    }

}

return this
