def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
    
  podTemplate(label:'pod-hugo-app',
           containers: [
		   containerTemplate(name: 'dind-daemon', image: 'docker:dind', privileged: true,
				    volumes: [secretVolume(secretName: 'docker-graph-storage', mountPath: '/var/lib/docker')])
	           ,
		   containerTemplate(name: 'docker-cmds', image: 'docker',   
		          envVars: [envVar(key: 'DOCKER_HOST', value: 'tcp://localhost:2375')]		    
	              )
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
