package com.efacademy.learning.portal.controller;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efacademy.learning.portal.dto.FavouriteDto;
import com.efacademy.learning.portal.service.FavouriteService;

@RestController
@RequestMapping("/favourite")
public class FavouriteController {

	@Autowired
	private FavouriteService favouriteService;
	private static final String MESSAGE_KEY = "message";

	public FavouriteController(FavouriteService favouriteService) {
		this.favouriteService = favouriteService;
	}

	@PostMapping("/make")
	public ResponseEntity<?> makeFavourite(@Valid @RequestBody FavouriteDto favouriteDto) {
		try {
			favouriteService.makeFavourite(favouriteDto);
			return new ResponseEntity<>(Collections.singletonMap(MESSAGE_KEY, "Course added to favourites."),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap(MESSAGE_KEY, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (InternalError e) {
			return new ResponseEntity<>(Collections.singletonMap(MESSAGE_KEY, "An unexpected error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/remove")
	public ResponseEntity<?> removeFavourite(@Valid @RequestBody FavouriteDto favouriteDto) {
		try {
			favouriteService.removeFavourite(favouriteDto);
			return new ResponseEntity<>(Collections.singletonMap(MESSAGE_KEY, "Course removed from favourites"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap(MESSAGE_KEY, "Something went wrong"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
