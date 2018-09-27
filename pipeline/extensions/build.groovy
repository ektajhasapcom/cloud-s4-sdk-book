def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
    
  podTemplate(label     : 'pod-hugo-app',
           containers: [
		   containerTemplate(name: 'dind-daemon', image: 'docker:dind', workingDir: '/root/', ttyEnabled: true, command: 'cat', privileged: true
		  )
	          
	       ],
	       volumes: [emptyDirVolume(mountPath: '/var/lib/docker', memory: false)]
	     
	     ) {
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
