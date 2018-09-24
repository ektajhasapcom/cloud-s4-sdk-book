def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    export ON_K8S=true"
    dockerExecuteOnKubernetes(script: script, containerMap: ['lachlanevenson/k8s-kubectl': 'kubectl', 'devth/helm': 'helm']){
       container('kubectl')  {
              withCredentials([[$class: 'UsernamePasswordMultiBinding', 
                        credentialsId: 'kubeconfig']]) {
                    
                    sh "kubectl get pods"
                }
       }
       container('helm'){
         sh "helm init --upgrade"
         sh "sleep 40"
         sh "helm upgrade --install --force addrbook --set imageversion=v1 addressbook"
       }
   }
}

return this