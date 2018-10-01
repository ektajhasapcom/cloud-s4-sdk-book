def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
      
     podTemplate(label     : 'build-node',
            containers: [containerTemplate(name: 'kubectl', image: 'ektajha/k8shelm:v1', ttyEnabled: true, command: 'cat',
            volumes: [secretVolume(secretName: 'kube-config', mountPath: '/root/.kube')]
            )]) {
             node('build-node') {
                container(name: 'kubectl') {
                    try {
                         sh "kubectl get pods"
                         sh "kubectl apply -f build.yaml"
                    } finally {
                        echo "Finally"
                     }
                }
            }
        }
    
}

return this
