node {
	environment {
		registry = "docker_hub_account/repository_name"
		registryCredential = 'dockerhub'
	}
	
	jdk = tool name: 'LocalJavaHome'
	env.JAVA_HOME = "${jdk}"

	echo "jdk installation path is: ${jdk}"

	// next 2 are equivalents
	//bat "${jdk}/bin/java -version"

	// note that simple quote strings are not evaluated by Groovy
	// substitution is done by shell script using environment
	//bat '$JAVA_HOME/bin/java -version'
  
    // reference to maven
    // ** NOTE: This 'LocalMaven' Maven tool must be configured in the Jenkins Global Configuration.   
    def mvnHome = tool 'LocalMaven'

    // holds reference to docker image
    def dockerImage
    // ip address of the docker private repository(nexus)
    
    def dockerRepoUrl = "https://hub.docker.com/repository/docker/mahasarathi/tutorial"
    def dockerImageName = "SpringBoot_BOQN_Demo"
    def dockerImageTag = "${dockerRepoUrl}/${dockerImageName}:v1"
    
    stage('Clone Repo') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/mahasarathi/tutorial.git'
      // Get the Maven tool.
      // ** NOTE: This 'LocalMaven' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'LocalMaven'
	  JAVA_HOME = tool 'LocalJavaHome'
    }    
  
    stage('Build Project') {
      // build project via maven
      bat "L:/SPRING/TOOLS/apache-maven-3.6.3/bin/mvn -f SpringBoot_BOQN_Demo/pom.xml -Dmaven.test.failure.ignore clean package"
    }
	
	stage('Publish Tests Results'){
      parallel(
        publishJunitTestsResultsToJenkins: {
          echo "Publish junit Tests Results"
		  junit '**/target/surefire-reports/TEST-*.xml'
		  archiveArtifacts '**/target/*.jar'
        },
        publishJunitTestsResultsToSonar: {
          echo "This is branch master"
      })
    }
		
    stage('Build Docker Image') {
		bat "dir"
	  
		// script {
			// def registryCredential = 'dockerhub'
			// https://hub.docker.com/r/mahasarathi/tutorial
			// def registry = "mahasarathi/tutorial"
			// echo "Building dcoker image 1"
			//dockerImage = docker.build(registry,  '-f ./Dockerfile .')
			// dockerImage.push()
			// withDockerRegistry([ credentialsId: "3c173434-827e-4cf6-a570-53978da9dfe0", url: "${dockerRepoUrl}" ]) {
			  // bat 'docker tag ${dockerImageName} ${dockerImageTag}'
			  // bat 'docker push ${dockerImageTag}'
			  // dockerImage.push()
			// }
			//docker.withRegistry( 'https://hub.docker.com/r/mahasarathi/tutorial', registryCredential ) {
				// dockerImage.push()
			//}
			echo "Building dcoker image completed 1"
		//}
		
		/*docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {

			def customImage = docker.build("tutorial:prod_01")

			// Push the container to the custom Registry
			customImage.push()
		}*/
		
		// steps {
			bat "docker build -t tutorial:prod_01 ."
		// }
    }
   
    stage('Deploy Docker Image'){      
      // deploy docker image to nexus

		echo "Docker Image Tag Name: ${dockerImageTag}"

        // withDockerRegistry([ credentialsId: "3c173434-827e-4cf6-a570-53978da9dfe0", url: "${dockerRepoUrl}" ]) {
          // bat 'docker tag ${dockerImageName} ${dockerImageTag}'
		  // bat 'docker push ${dockerImageTag}'
        // }
  
      // bat '"C:/Program Files/Docker/Docker/docker" login -u mahasarathi -p Hari25*03 ${dockerRepoUrl}'
	  // bat '"C:/Program Files/Docker/Docker/docker" login -u mahasarathi -p Hari25*03 docker.io'
      // bat '"C:/Program Files/Docker/Docker/docker" tag ${dockerImageName} ${dockerImageTag}'
      // bat '"C:/Program Files/Docker/Docker/docker" push ${dockerImageTag}'
    }
	
	stage("Deploy Docker Image In Local") {
		// steps {
			// bat "docker stop tutorial"
			// bat "docker rm tutorial"
			// bat "docker run -d --rm -p 7765:7070 --name tutorial tutorial:prod_01"
			bat "docker run -d --rm -p 8083:8083 --name tutorial tutorial:prod_01"
		// }
	}
}