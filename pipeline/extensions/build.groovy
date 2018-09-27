def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
    
  podTemplate(label:'pod-hugo-app',
           containers: [
		   containerTemplate(name: 'dind-daemon', image: 'docker:dind',ttyEnabled: true, privileged: true)
	           ], 
		   volumes: [emptyDirVolume(mountPath: '/var/lib/docker')]) {
             node('pod-hugo-app') {
                container('docker-cmds') {
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
