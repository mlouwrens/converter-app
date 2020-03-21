converter-ws - Web service that converts metric to imperial
============================================================================
1. build using 'mvn install'
2. build docker image using 'docker build -t com.local.app/converter-ws .'
3. start docker image using 'docker run -p 8080:8080 com.local.app/converter-ws'
4. connect to ws via postman using http://localhost:8080


converter-fe - Front-End that requires web service for conversions
============================================================================
1. build docker image using 'docker build -t converter-fe .'
2. start docker image using 'docker run -p 8081:8081 converter-fe'
3. connect to fe via browser using http://127.0.0.1:8081