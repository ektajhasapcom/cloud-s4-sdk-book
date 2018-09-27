def call(Closure originalStage, String stageName, Map stageConfiguration, Map generalConfiguration) {
	
sh "docker run --privileged --name some-docker -d docker:dind"
sh "docker version"
    
 podTemplate(label     : 'pod-hugo-app',
           containers: [
		   containerTemplate(name: 'dind-daemon', image: 'docker:dind', privileged: true)
	          
	       ]) {
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
