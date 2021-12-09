pipeline {
	agent any
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
		
		stage("Deploy") {
				steps {
			    echo 'Deploying..'
			}
		}
	}

}