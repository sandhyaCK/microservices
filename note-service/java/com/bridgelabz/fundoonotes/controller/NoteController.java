package com.bridgelabz.fundoonotes.controller;

/*
 *  author : Sandhya
 */

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.dto.NoteUpdate;
import com.bridgelabz.fundoonotes.dto.ReminderDto;
import com.bridgelabz.fundoonotes.model.NoteData;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.NoteService;

@RestController
@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="note-service")
public class NoteController {
	@Autowired
	private NoteService service;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private ModelMapper mapper;
//	@Autowired
//	WebClient.Builder webClientBuilder;
	String url="http://localhost:8080/user/singleUser/";
	/* API for creating a Note */
	@PostMapping("note/create/")
	public ResponseEntity<Response> create(@RequestBody NoteDto information, @RequestHeader("token") String token) {
		System.out.println("@@@@@@");
	Response response=restTemplate.getForEntity(url+token,Response.class).getBody();
	User user=new User();
	    user=mapper.map(response.getObj(), User.class);		
		NoteData note=service.createNote(information, user);	
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note created", 200, note));
	}

	/* API for updating a Note */
	@PutMapping("note/update")
	public ResponseEntity<Response> update(@RequestBody NoteUpdate note, @RequestHeader("token") String token) {
		Response response=restTemplate.getForEntity(url+token,Response.class).getBody();
		User user=new User();
		    user=mapper.map(response.getObj(), User.class);
		NoteData noteData=service.updateNote(note, user);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("note updated ", 201, noteData));
	}

	/* API for pin a Note */
	@PutMapping("note/pin/{id}")
	public ResponseEntity<Response> pin(@PathVariable Long id, @RequestHeader("token") String token) {
		Response response=restTemplate.getForEntity(url+token,Response.class).getBody();
		User user=new User();
		    user=mapper.map(response.getObj(), User.class);		service.pinNote(id, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note pined", 200));
	}

	/* API for archieve a Note */
	@PutMapping("note/archieve/{id}")
	public ResponseEntity<Response> archieve(@PathVariable Long id, @RequestHeader("token") String token) {
		Response response=restTemplate.getForEntity(url+token,Response.class).getBody();
		User user=new User();
		    user=mapper.map(response.getObj(), User.class);		service.archieveNote(id, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note archieved", 200));
	}

	/* API for deleting a note */
	@DeleteMapping("note/delete/{id}")
	public ResponseEntity<Response> delete(@PathVariable Long id, @RequestHeader("token") String token) {
		Response response=restTemplate.getForEntity(url+token,Response.class).getBody();
		User user=new User();
		    user=mapper.map(response.getObj(), User.class);		service.deleteNote(id, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note deleted", 200));
	}

//	/* API for permanently deleting a Note */
//	@DeleteMapping("note/deletepermanentally/{id}")
//	public ResponseEntity<Response> deletePermanentally(@PathVariable Long id, @RequestHeader("token") String token) {
//		User user=restTemplate.getForObject("http://localhost:8080/user/singleUser/"+token, User.class);
//		service.deleteNotePermanently(id, user);
//		return ResponseEntity.status(HttpStatus.OK).body(new Response("note deleted", 200));
//	}

	/* API for updating color to a Note */
	@PostMapping("note/addcolour")
	public ResponseEntity<Response> addColour(@RequestParam("noteId") Long noteId,
			@RequestParam("colour") String colour, @RequestHeader("token") String token) {
		Response response=restTemplate.getForEntity(url+token,Response.class).getBody();
		User user=new User();
		    user=mapper.map(response.getObj(), User.class);		service.addColour(noteId, user, colour);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("note colour changed", 200));

	}

	/* API for getting all archieve notes Notes */
	@GetMapping("note/getArchieve/{id}")
	public ResponseEntity<Response> getArchieve(@RequestHeader("token") String token) {
		Response response=restTemplate.getForEntity(url+token,Response.class).getBody();
		User user=new User();
		    user=mapper.map(response.getObj(), User.class);		List<NoteData> list = service.getarchieved(user);
		return ResponseEntity.status(HttpStatus.OK).body(new Response(" archieved notes", 200, list));
	}

	/* API for getting all trashed Notes */
	@GetMapping("note/gettrashed/{id}")
	public ResponseEntity<Response> getTrashed(@RequestHeader("token") String token) {
		Response response=restTemplate.getForEntity(url+token,Response.class).getBody();
		User user=new User();
		    user=mapper.map(response.getObj(), User.class);
		    List<NoteData> list = service.gettrashednotes(user);
		return ResponseEntity.status(HttpStatus.OK).body(new Response(" trashed notes", 200, list));
	}

//	/* API for getting all Notes */
//	@GetMapping("note/getallnotes/{id}")
//	public ResponseEntity<Response> getAllNotes(@RequestHeader("token") String token) {
//		User user=restTemplate.getForObject("http://localhost:8080/user/singleUser/"+token, User.class);
//		List<NoteData> list = service.getAllNotes(user);
//		return ResponseEntity.status(HttpStatus.OK).body(new Response(" All notes", 200, list));
//	}

	/* API for getting all Pinned Notes */
	@GetMapping("note/getPinned/{id}")
	public ResponseEntity<Response> getPinned(@RequestHeader("token") String token) {
		Response response=restTemplate.getForEntity(url+token,Response.class).getBody();
		User user=new User();
		    user=mapper.map(response.getObj(), User.class);		List<NoteData> list = service.getPinneded(user);
		return ResponseEntity.status(HttpStatus.OK).body(new Response(" pinned notes", 200, list));
	}

	/* API for adding remainder to Notes */
	@GetMapping("note/addremainder/{id}")
	public ResponseEntity<Response> addRemainder(@RequestHeader("token") String token,
			@RequestParam("noteId") Long noteId, @RequestBody ReminderDto remainder) {
		Response response=restTemplate.getForEntity(url+token,Response.class).getBody();
		User user=new User();
		    user=mapper.map(response.getObj(), User.class);		service.addReminder(noteId, user, remainder);
		return ResponseEntity.status(HttpStatus.OK).body(new Response(" reminderAdded notes", 200,remainder));
	}

	/* API for removing remainder Notes */
	@GetMapping("note/removeRemainder/{id}")
	public ResponseEntity<Response> removeRemainder(@RequestHeader("token") String token,
			@RequestParam("noteId") Long noteId, @RequestBody ReminderDto remainder) {
		Response response=restTemplate.getForEntity(url+token,Response.class).getBody();
		User user=new User();
		    user=mapper.map(response.getObj(), User.class);		service.removeReminder(noteId, user, remainder);
		return ResponseEntity.status(HttpStatus.OK).body(new Response(" trashed notes", 200));
	}
	@GetMapping("note/noteById/{id}/{token}")
	public ResponseEntity<Response> getNoteById(@PathVariable("token") String token,
			@PathVariable("noteId") Long noteId) {
		Response response=restTemplate.getForEntity(url+token,Response.class).getBody();
		User user=new User();
		    user=mapper.map(response.getObj(), User.class);		service.getNoteById(noteId, user);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("note is....", 200));
	}
}
