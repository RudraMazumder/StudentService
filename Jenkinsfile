pipeline {
	agent any
	triggers {
   		 pollSCM('* * * * *') // every minute
  	}
	stages {
		stage("build") {
		
			steps {
			    echo 'Building..'
			    bat 'mvn clean package'
			}			
		}
		
		
		stage("test") {
				steps {
				   step([$class: 'JacocoPublisher', 
		                execPattern: '**/build/jacoco/*.exec',
		                classPattern: '**/build/classes',
		                sourcePattern: 'src/main/java',
		                exclusionPattern: 'src/test*'
                ])
			}
			
			post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    
                }
            }
		}
		
		stage("deploy") {
				steps {
			    echo 'Deploying..'
			}
		}
	}

}