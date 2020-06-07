package com.is.textToSpeack.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioFileFormat.Type;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.is.textToSpeack.model.TextToSpeachRequest;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;

/***
 * 
 * @author psaragadam
 *
 */
@Service
public class FreettsToAudioService {

	public ResponseEntity<String> processTextToSpeackAudioFile(HttpServletResponse response,
			TextToSpeachRequest speachRequest) throws IOException {
		AudioPlayer audioPlayer = null;
		String voiceName = "kevin16";

		System.out.println();
		System.out.println("Using voice: " + voiceName);

		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice voice = voiceManager.getVoice(voiceName);

		if (voice == null) {
			System.err.println("Cannot find a voice named " + voiceName + ".  Please specify a different voice.");
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}

		voice.allocate();
		audioPlayer = new SingleFileAudioPlayer("voice_speech", Type.WAVE);
		voice.setAudioPlayer(audioPlayer);
		voice.speak(speachRequest.getSpeachText());
		voice.deallocate();
		audioPlayer.close();

		File file = new File("voice_speech.wav");

		/*
		 * HttpHeaders header = new HttpHeaders();
		 * header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
		 * header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		 * header.add("Pragma", "no-cache"); header.add("Expires", "0");
		 */
		Path path = Paths.get(file.getAbsolutePath());

		response.setHeader("Content-Type", "application/zip");
		response.addHeader("Content-Disposition", "attachment; filename=voice_speech.wav");
		try {
			Files.copy(path, response.getOutputStream());
			response.getOutputStream().flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			file.deleteOnExit();	
		}
		return ResponseEntity.ok().contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream")).body("File availible to downloaded");

	}
}
