package com.example.SpringbootApp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringbootApp.modelo.RolUserVO;
import com.example.SpringbootApp.modelo.UserVO;
import com.example.SpringbootApp.servicio.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController
@RequestMapping("/UserController")
public class UserController {

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Insert user in the database
	 *
	 * @param nif       the nif of the User
	 * @param name      the name of the user
	 * @param lastName  the last name of the user
	 * @param dateBirth the date birth of the user
	 * @return the response entity if the user is inserted
	 */
	@PostMapping("${endpoints.userController.insertUser}")
	@ResponseBody
	public ResponseEntity<Optional<UserVO>> insertUser(@RequestParam(name = "nif") final String nif,
			@RequestParam(name = "name") final String name, @RequestParam(name = "lastName") final String lastName,
			@RequestParam(name = "dateBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate dateBirth) {

		UserVO user = new UserVO(nif, name, lastName, dateBirth, new ArrayList<RolUserVO>());

		Optional<UserVO> usuSaved = Optional.of(userService.save(user));

		if (usuSaved.isPresent()) {
			return new ResponseEntity<Optional<UserVO>>(usuSaved, HttpStatus.OK);
		} else {
			return new ResponseEntity<Optional<UserVO>>(usuSaved, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Update user.
	 *
	 * @param nif       the nif
	 * @param name      the name
	 * @param lastName  the last name
	 * @param dateBirth the date birth
	 * @return the response entity
	 */
	@PostMapping("${endpoints.userController.updateUser}")
	@ResponseBody
	public ResponseEntity<Optional<UserVO>> updateUser(@RequestParam(name = "nif") final String nif,
			@RequestParam(name = "name") final String name, @RequestParam(name = "lastName") final String lastName,
			@RequestParam(name = "dateBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate dateBirth) {

		Optional<UserVO> user = userService.findUserByNif(nif);
		if (user.isPresent()) {
			user.get().setLastName(name);
			user.get().setLastName(lastName);
			user.get().setDateBirth(dateBirth);

			Optional<UserVO> usuSaved = Optional.of(userService.save(user.get()));

			return new ResponseEntity<Optional<UserVO>>(usuSaved, HttpStatus.OK);
		} else {
			return new ResponseEntity<Optional<UserVO>>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Gets the user filtered by nif.
	 *
	 * @param nif the nif
	 * @return the user by nif
	 */
	@GetMapping("${endpoints.userController.getUserByNif}/{nif}")
	public ResponseEntity<Optional<UserVO>> getUserByNif(@PathVariable final String nif) {

		Optional<UserVO> user = userService.findUserByNif(nif);

		if (user.isPresent()) {
			return new ResponseEntity<Optional<UserVO>>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<Optional<UserVO>>(user, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("${endpoints.userController.getUserByNameAndLastName}")
	@ResponseBody
	public ResponseEntity<Optional<List<UserVO>>> getUserByNameAndLastName(
			@RequestParam(name = "name") final String name, @RequestParam(name = "lastName") final String lastName) {

		Optional<List<UserVO>> userList = userService.findUsersByNameAndLastName(name, lastName);

		if (userList.isPresent()) {
			return new ResponseEntity<Optional<List<UserVO>>>(userList, HttpStatus.OK);
		} else {
			return new ResponseEntity<Optional<List<UserVO>>>(userList, HttpStatus.NOT_FOUND);
		}

	}

}
