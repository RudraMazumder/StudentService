pipeline {
	agent any
	stages {
		stage("build") {
		
			steps {
			    echo 'Building..'
			    sh mvn clean install
			}			
		}
		
		stage("test") {
				steps {
				    echo 'Testing..'
			}
		}
		
		stage("Deploy") {
				steps {
			    echo 'Deploying..'
			}
		}
	}

}