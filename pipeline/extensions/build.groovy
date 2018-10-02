def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
     
     dockerExecute(script: this, dockerImage: 'lachlanevenson/k8s-kubectl'){ 
        withCredentials([[
                $class: 'FileBinding',
                credentialsId: 'k8s-credentials',
                variable: 'KUBECONFIG'
            ]]){
                 sh "kubectl --kubeconfig=$KUBECONFIG"
                 sh "kubectl delete -f build.yaml"
                 sh "kubectl apply -f build.yaml"           
              }
    }

}

return this
