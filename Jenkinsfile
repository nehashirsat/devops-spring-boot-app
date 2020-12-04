pipeline {
    agent { node { label 'springbootnode' } }
    tools {
        maven 'maven'
		'org.jenkinsci.plugins.docker.commons.tools.DockerTool' 'docker'
       }
    environment {
    DOCKER_CERT_PATH = credentials('DockerPipeline')
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                   
                '''
            }
        }
		stage('Clone') {
			steps {
            
					git credentialsId: 'GitHubID', url: 'https://github.com/deepakbedase123/devops_spring_boot_docker_jenkins'
			
			}
		}
		stage('Sonarqube') {
    environment {
			scannerHome = tool 'sonarqubescanner'
			}
        steps {
		withSonarQubeEnv('sonarqubeserver') {
		sh "${scannerHome}/bin/sonar-scanner"
		 sh 'mvn clean package sonar:sonar'
		
			}
    	timeout(time: 1, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
              }
		}
		
		}
		stage ('cleanandInstall and TestPackage & Publish Coverage') {
		
			steps {
                sh 'mvn -Dmaven.test.failure.ignore=true clean install test package' 
                
publishHTML (target: [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'target/site/jacoco/',reportFiles: 'index.html',reportName: "Coverage Report"])
		
		 echo "Running ${env.JOB_NAME} ${env.BUILD_ID} on ${env.JENKINS_URL}"
			}
            
        }
   
	 stage ('upload artifact to s3') {
		
			steps {
    
s3Upload consoleLogLevel: 'INFO', dontSetBuildResultOnFailure: false, dontWaitForConcurrentBuildCompletion: false, entries: [[bucket: 'deepak-spring-boot-jenkin', excludedFile: '/target', flatten: false, gzipFiles: false, keepForever: false, managedArtifacts: false, noUploadOnFailure: true, selectedRegion: 'us-east-1', showDirectlyInBrowser: false, sourceFile: '**/target/*.war', storageClass: 'STANDARD', uploadFromSlave: true, useServerSideEncryption: false]], pluginFailureResultConstraint: 'FAILURE', profileName: 'spring_boot_CI_CD', userMetadata: []
			}
            
        }
stage('Build & Push') {
  
 		steps {
 		    script{
 		        docker.build("543816070942.dkr.ecr.us-east-1.amazonaws.com/devops_springboot_app_prod_deepak:${env.BUILD_NUMBER}")
withDockerRegistry(credentialsId: 'ecr:us-east-1:AWSCredentials', url: 'https://543816070942.dkr.ecr.us-east-1.amazonaws.com') {
    
	docker.image("543816070942.dkr.ecr.us-east-1.amazonaws.com/devops_springboot_app_prod_deepak:${env.BUILD_NUMBER}").push()
}
}
      

    }
}
}
 post {	
        success {
         slackSend channel: 'jenkins_spring_boot_app_notification', message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}", teamDomain: 'devops-deepak', tokenCredentialId: 'SlackID'
        }
        failure {
            slackSend channel: 'jenkins_spring_boot_app_notification',  message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}", teamDomain: 'devops-deepak', tokenCredentialId: 'SlackID'
        }
        unstable {
           slackSend channel: 'jenkins_spring_boot_app_notification',  message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}", teamDomain: 'devops-deepak', tokenCredentialId: 'SlackID'
        }
	
    }
    
    
}
