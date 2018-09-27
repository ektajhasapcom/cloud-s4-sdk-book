def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
  
 sh "docker run -t -i --privileged  docker:dind sh"
 sh "docker version"
    
  podTemplate(label     : 'pod-hugo-app',
           containers: [
		   containerTemplate(name: 'dind-daemon', image: 'docker:dind', ttyEnabled: true, command: 'cat', privileged: true
		  )
	          
	       ], 
		   volumes: [emptyDirVolume(mountPath: '/var/lib/docker')]) {
             node('pod-hugo-app') {
                container(name: 'dind-daemon') {
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
