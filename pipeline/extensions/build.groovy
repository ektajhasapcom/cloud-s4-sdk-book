def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
     dockerExecute(script: this, dockerImage: 'ektajha/k8shelm:v1'){ 
        withCredentials([[
                $class: 'FileBinding',
                credentialsId: 'k8s-credentials',
                variable: 'KUBECONFIG'
            ]]){
                 sh "kubectl --kubeconfig=$KUBECONFIG"
                 sh "kubectl apply -f docker.yaml"
                 sh "sleep 40"
                 sh "kubectl get pods"
                 sh "docker version"           
              }
    }
     originalStage()
}

return this
