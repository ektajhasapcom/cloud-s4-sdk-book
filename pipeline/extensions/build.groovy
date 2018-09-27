def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
   
    
  podTemplate(label     : 'pod-hugo-app',
           containers: [
		   containerTemplate(name: 'dind-daemon', image: 'docker:1.12.6-dind', ttyEnabled: true, command: 'cat', privileged: true,
				    volumes: [hostPathVolume(hostPath: '/var/lib/docker', mountPath: '/var/lib/docker')],
				    ports: [portMapping(containerPort: 2375, hostPort: 2375)])
	           ,
		   containerTemplate(name: 'docker-cmds', image: 'docker', ttyEnabled: true, command: 'cat', 
		          envVars: [envVar(key: 'DOCKER_HOST', value: 'tcp://localhost:2375')]		    
	              )
	           ], 
		   volumes: [hostPathVolume(hostPath: '/var/lib/docker', mountPath: '/var/lib/docker')]) {
             node('pod-hugo-app') {
                container(name: 'docker-cmds') {
                    try {
			sh "sleep 60"
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
