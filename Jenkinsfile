pipeline {
	agent any
	triggers {
   		 pollSCM('* * * * *') // every minute
  	}
	stages {
		stage("build") {
		
			steps {
			    echo 'Building..'
			    bat 'mvn clean package -DskipTests'
			}			
		}
		
		
		stage("test") {
				steps {
				    echo 'Testing..'
				    bat 'mvn clean test'
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