package com.skilldistillery.watchparty.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.watchparty.entities.Address;
import com.skilldistillery.watchparty.services.AddressService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class AddressController {

	@Autowired
	private AddressService addressService;

	private String username = "shaun";

	@GetMapping("watchparties/addresses")
	public List<Address> index(HttpServletRequest req, HttpServletResponse res) {
		return addressService.index();
	}

//  GET address/{addId}
	@GetMapping("watchparties/addresses/{addId}")
	public Address show(HttpServletRequest req, HttpServletResponse res,@PathVariable int addId) {
		Address address = addressService.show( addId);
		if (address == null) {
			res.setStatus(404);
		}
		return address;
	}

//  POST address
	@PostMapping("watchparties/addresses")
	public Address create(HttpServletRequest req, HttpServletResponse res,@RequestBody Address address) {
		Address createdAddress = null;
		try {
			createdAddress = addressService.create( address);
			res.setStatus(200);
			res.setHeader("Location", req.getRequestURL().append("/").append(createdAddress.getId()).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(400);
		}
		
		
		return createdAddress;
	}

//  PUT address/{addId}
	@PutMapping("watchparties/addresses/{addId}")
	public Address update(HttpServletRequest req, HttpServletResponse res,@PathVariable int addId,@RequestBody Address address) {
		Address updated = null;
		try {
			updated = addressService.update( addId, address);
			if( updated == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(400);
		}
		return updated;
	}

//  DELETE address/{addId}
	@DeleteMapping("watchparties/addresses/{addId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int addId) {

		if (addressService.destroy( addId)) {
			res.setStatus(204);
		}
		else {
			res.setStatus(404);
		}
	}
}
