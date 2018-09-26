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
                 sh "sudo apt-get install wget"
                 sh "wget https://storage.googleapis.com/kubernetes-helm/helm-v2.10.0-linux-amd64.tar.gz > /dev/null 2>&1"
                 sh "tar -zxvf helm-v2.10.0-linux-amd64.tar.gz > /dev/null 2>&1"
                 sh "sudo mv linux-amd64/helm /usr/local/bin/helm > /dev/null 2>&1"
                 sh "helm init --upgrade"
                 sh "sleep 40"
                 sh "helm upgrade --install --force addrbook --set imageversion=$tag addressbook"            
              }
    }

}

return this
