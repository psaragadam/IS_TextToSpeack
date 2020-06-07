package com.is.textToSpeack.rest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.is.textToSpeack.model.TextToSpeachRequest;
import com.is.textToSpeack.service.FreettsToAudioService;
import com.is.textToSpeack.util.SpeechUtils;

/***
 * 
 * @author psaragadam
 *
 */
@RestController
public class TTSController {

	@Autowired
	SpeechUtils su;
	
	@Autowired
	FreettsToAudioService service;
	
	@GetMapping(value="/tts", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<String> processTTS(HttpServletResponse response, @RequestParam String speach){
		try {
			return service.processTextToSpeackAudioFile(response, new TextToSpeachRequest(speach));	
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PostMapping(value="/tts/audio", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<String> processTextToSpeackAudioFile(HttpServletResponse response, @RequestBody TextToSpeachRequest speachRequest){
		try {
			return service.processTextToSpeackAudioFile(response, speachRequest);	
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
