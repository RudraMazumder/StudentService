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
		}
		
		stage("Deploy") {
				steps {
			    echo 'Deploying..'
			}
		}
	}

}