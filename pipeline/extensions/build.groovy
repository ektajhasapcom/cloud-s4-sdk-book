def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
   stage(stageName) {
           withKubeConfig([credentialsId: 'k8s-credentials',
                    caCertificate: '<ca-certificate>',
                    serverUrl: '<api-server-address>',
                    contextName: '<context-name>'
                    ]) {
                      sh "kubectl get pods"
             }
    }
}

return this
