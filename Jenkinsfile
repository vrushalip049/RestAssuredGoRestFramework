pipeline 
{
    agent any
    
    tools{
    	maven 'maven'
        }
        
    stages 
    {
        stage('Build') 
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }
             
             
                
                
      stage('Regression API Automation Tests') {
    steps {
    catchError(buildResult:"SUCESS" ,stageResult:"FAILURE")
    {
    git 'https://github.com/vrushalip049/RestAssuredGoRestFramework'
    sh "mvn clean test -Dsurefire.suiteXmlFiles=src\test\resources\testrunner\testng_regression.xml"
		}
    }
}

	stage('Publish Regression Extent Report'){
            steps{
            	script{
            	allure([ 
            			includeProperties:false,
            				jdk: '',
            				Properties :[ ],
            				reportBuildPolicy : 'ALWAYS',
            				results :[ [ path :'/allure-results' ] ]
            				
            	] )
            	}
            	    }
            	}

		
		stage('Publish Regression Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: false, 
                                  reportDir: 'target', 
                                  reportFiles: 'APIExecutionReport.html', 
                                  reportName: 'API HTML Regression Extent Report', 
                                  reportTitles: ''])
            }
        }
        
        
         

         
    }
}