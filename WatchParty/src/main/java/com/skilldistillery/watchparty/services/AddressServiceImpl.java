package com.skilldistillery.watchparty.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.watchparty.entities.Address;
import com.skilldistillery.watchparty.repositories.AddressRepository;
import com.skilldistillery.watchparty.repositories.UserRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Address> index() {
		return addressRepo.findAll();
	}

	@Override
	public Address show( int aid) {
		Optional<Address> addressOption = addressRepo.findById(aid);
		if (addressOption.isPresent()) {
			Address address = addressOption.get();
			return address;
		}
		return null;
	}

	@Override
	public Address create( Address address) {
		
			return addressRepo.saveAndFlush(address);
	
	}

	@Override
	public Address update( int aid, Address address) {
		Address existing = addressRepo.findById( aid).get();
		if (existing != null) {
			existing.setCity(address.getCity());
			existing.setState(address.getState());
			existing.setStreet(address.getStreet());
			existing.setZip(address.getZip());
			addressRepo.saveAndFlush(existing);
		}
		return existing;
	}

	@Override
	public boolean destroy( int aid) {
		boolean deleted = false;
		Address address = addressRepo.findById(aid).get();
		if (address != null) {
			address.setEnabled(false);
			deleted = true;
		}
		return deleted;
	}

}
