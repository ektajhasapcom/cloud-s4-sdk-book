def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
    
  podTemplate(label     : 'pod-hugo-app',
            containers: [containerTemplate(name: 'docker', image: 'docker', ttyEnabled: true, command: 'cat')]) {
             node('pod-hugo-app') {
                container(name: 'docker') {
                    try {
                        sh "docker version"
                        sh "docker build ."
                    } finally {
                        echo "Finally"
                     }
                }
            }
        }
}

return this
