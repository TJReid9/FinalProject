package com.skilldistillery.watchparty.services;

import java.util.List;

import com.skilldistillery.watchparty.entities.Address;

public interface AddressService {

    public List<Address> index();

    public Address show( int aid);

    public Address create( Address address);

    public Address update( int aid, Address address);

    public boolean destroy( int aid);
    
}
