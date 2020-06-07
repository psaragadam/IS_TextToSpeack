# IS_TextToSpeech

Get code for Text to Speech by following command

> git clone https://github.com/psaragadam/IS_TextToSpeech.git

Build code

> mvn clean install

> mvn spring-boot:run


To execute API, Please execute following curl url:

curl -X POST \
  http://localhost:8080/tts/audio \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 83bfd2a0-79c9-f93d-9527-e1e89a3744fa' \
  -d '{
	"speachText":"Hello World!"
}

We have to pass text which we will get as wav file.
