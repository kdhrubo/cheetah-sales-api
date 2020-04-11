package com.cheetahapps.sales.note;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.controller.AbstractBaseController;

@RestController
@RequestMapping("/notes")
public class NoteController extends AbstractBaseController<Note, String> {

	private final NoteBusinessDelegate noteBusinessDelegate;

	@Autowired
	public NoteController(NoteBusinessDelegate noteBusinessDelegate) {
		super(noteBusinessDelegate);
		this.noteBusinessDelegate = noteBusinessDelegate;
	}
	
	@GetMapping("/q")
	public List<Note> searchAll(@RequestParam("rsql") String rsql) {
		return noteBusinessDelegate.searchAll(rsql);
	}

}