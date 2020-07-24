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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.fundoonotes.dto.LabelDto;
import com.bridgelabz.fundoonotes.dto.LabelUpdate;
import com.bridgelabz.fundoonotes.model.LabelData;
import com.bridgelabz.fundoonotes.model.NoteData;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.LabelService;

@RestController
@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="label-service")
public class LabelController {
	@Autowired
	private LabelService service;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private ModelMapper mapper;
	String userUrl="http://localhost:8080/user/singleUser/";
	String noteUrl="http://localhost:8082/note/noteById/";
	/* API for creating label */
	@PostMapping("/label/create/")
	public ResponseEntity<Response> createLabel(@RequestBody LabelDto label, @RequestHeader("token") String token) {
		Response response=restTemplate.getForEntity("http://localhost:8080/user/singleUser/"+token,Response.class).getBody();	
		User user=new User();
	    user=mapper.map(response.getObj(), User.class);
		service.createLabel(label, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("label created ", 200, label));

	}

//	/* API for creating and map label */
//	@PostMapping("/label/createandmap")
//	public ResponseEntity<Response> createandmapLabel(@RequestBody LabelDto label, @RequestHeader("token") String token) {
//		User user=restTemplate.getForObject(userUrl+token, User.class);
//		service.createLabelAndMAp(label, user);
//		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("label created ", 200, label));
//
//	}

//	/* API for add label */
//	@PostMapping("/label/addlabel")
//	public ResponseEntity<Response> addlabel(@RequestParam("labelId") Long labelId,
//			@RequestHeader("token") String token, @RequestParam("noteId") Long noteId) {
//		User user=restTemplate.getForObject(userUrl+token, User.class);
//		NoteData note= restTemplate.getForObject("http://localhost:8082/note/noteById/"+noteId +"/"+token, NoteData.class);
//		service.addLabel(labelId, user, note);
//		return ResponseEntity.status(HttpStatus.OK).body(new Response("label added ", 200));
//
//	}
//
//	/* API for removing label */
//	@PutMapping("/label/remove")
//	public ResponseEntity<Response> removelabel(@RequestParam("labelId") Long labelId,
//			@RequestHeader("token") String token, @RequestParam("noteId") Long noteId) {
//		User user=restTemplate.getForObject(userUrl+token, User.class);
//		NoteData note= restTemplate.getForObject(noteUrl+noteId +"/"+token, NoteData.class);
//		service.removeLabel(labelId, user, note);
//		return ResponseEntity.status(HttpStatus.OK).body(new Response("label removed ", 200));
//
//	}

	/* API for updating label */
	@PutMapping("/label/update")
	public ResponseEntity<Response> updateLabel(@RequestBody LabelUpdate label, @RequestHeader("token") String token) {
		Response response=restTemplate.getForEntity("http://localhost:8080/user/singleUser/"+token,Response.class).getBody();	
		User user=new User();
	    user=mapper.map(response.getObj(), User.class);		service.edit(label, user);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("label updated ", 200, label));

	}

	/* API for deleting label */
	@DeleteMapping("/label/delete")
	public ResponseEntity<Response> deleteLabel(@RequestBody LabelUpdate label, @RequestHeader("token") String token) {
		Response response=restTemplate.getForEntity("http://localhost:8080/user/singleUser/"+token,Response.class).getBody();	
		User user=new User();
	    user=mapper.map(response.getObj(), User.class);		service.deleteLabel(label, user);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("label deleted ", 200, label));

	}

	/* API for getting all label */
	@GetMapping("/label/getlabels")
	public ResponseEntity<Response> getLabels(@RequestHeader("token") String token) {
		Response response=restTemplate.getForEntity("http://localhost:8080/user/singleUser/"+token,Response.class).getBody();	
		User user=new User();
	    user=mapper.map(response.getObj(), User.class);
	    List<LabelData> labels = service.getAllLabels(user);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("getting all labels  ", 200, labels));

	}

	/* API for getting all labelNotes */
	@GetMapping("/label/getlabelsnotes")
	public ResponseEntity<Response> getNotes(@RequestHeader("token") String token,
			@RequestParam("labelId") Long labelId) {
		Response response=restTemplate.getForEntity("http://localhost:8080/user/singleUser/"+token,Response.class).getBody();	
		User user=new User();
	    user=mapper.map(response.getObj(), User.class);		List<NoteData> list = service.getNotes(user, labelId);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("getting labelnotes  ", 200, list));

	}
}
