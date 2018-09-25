def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
     def  tag = "${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
     def imageName = "ektajha/addressbooklatest"
     
     dockerExecute(script: this, dockerImage: 'lachlanevenson/k8s-kubectl'){ 
        withCredentials([[
                $class: 'FileBinding',
                credentialsId: 'k8s-credentials',
                variable: 'KUBECONFIG'
            ]]){
                 sh "kubectl --kubeconfig=$KUBECONFIG"
                 sh "kubectl get pods"
                 sh  "kubectl apply -f deployment.yaml"
              }
    }

}

return this
