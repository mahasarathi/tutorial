node {
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
    }    
  
    stage('Build Project') {
      // build project via maven
      bat "L:\SPRING\TOOLS\apache-maven-3.6.3\bin\mvn -f SpringBoot_BOQN_Demo/pom.xml -Dmaven.test.failure.ignore clean package"
    }
	
	stage('Publish Tests Results'){
      parallel(
        publishJunitTestsResultsToJenkins: {
          echo "Publish junit Tests Results"
		  junit '**/target/surefire-reports/TEST-*.xml'
		  archive 'target/*.jar'
        },
        publishJunitTestsResultsToSonar: {
          echo "This is branch b"
      })
    }
		
    stage('Build Docker Image') {
      // build docker image
      // bat "whoami"
      // bat "ls -all /var/run/docker.sock"
      // bat '"C:/Program Files/Git/usr/bin/mv" ./SpringBoot_BOQN_Demo/target/SpringBoot_BOQN_Demo*.jar ./data'
      steps{
        script {
          dockerImage = docker.build("SpringBoot_BOQN_Demo")
        }
      }
      dockerImage = "C:\Program Files\Docker\Docker\docker" build
    }
   
    stage('Deploy Docker Image'){
      
      // deploy docker image to nexus

      echo "Docker Image Tag Name: ${dockerImageTag}"

      bat '"C:\Program Files\Docker\Docker\docker" login -u mahasarathi -p Hari25*03 ${dockerRepoUrl}'
      bat '"C:\Program Files\Docker\Docker\docker" tag ${dockerImageName} ${dockerImageTag}'
      bat '"C:\Program Files\Docker\Docker\docker" push ${dockerImageTag}'
    }
}