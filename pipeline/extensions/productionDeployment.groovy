def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
     dockerExecute(script: this, dockerImage: 'lachlanevenson/k8s-kubectl'){ 
       sh "USER root"
       sh "rm ~/.kube/config"
       sh  "cp config ~/.kube/config"
       sh "kubectl config get-contexts"             
        sh "kubectl get pods"
    }
}

return this
