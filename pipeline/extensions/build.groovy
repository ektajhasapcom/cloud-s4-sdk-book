def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
    
  podTemplate(label     : 'pod-hugo-app',
           containers: [containerTemplate(name: 'docker-cmds', image: 'docker:1.12.6', ttyEnabled: true, command: 'cat', 
		    envVars: [containerEnvVar(name: 'DOCKER_HOST', value: 'tcp://localhost:2375'),]),
		   containerTemplate(name: 'dind-daemon', image: 'docker:1.12.6-dind', ttyEnabled: true, command: 'cat')], 
		   volumes: [
                  emptyDirVolume(mountPath: '/var/lib/docker')]) {
             node('pod-hugo-app') {
                container(name: 'docker-cmds') {
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
