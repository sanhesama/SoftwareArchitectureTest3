package com.wu.service;

import com.wu.entity.ContactPerson;

import java.util.List;

public interface ContactPersonService {
    List<ContactPerson> listContactPersons();

    ContactPerson getContactPersonById(Integer id);

    void deleteById(Integer id);

    void updateContactPerson(ContactPerson contactPerson);

    void addContactPerson(ContactPerson contactPerson);
}
