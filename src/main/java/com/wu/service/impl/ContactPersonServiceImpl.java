package com.wu.service.impl;

import com.wu.entity.ContactPerson;
import com.wu.mapper.ContactPersonMapper;
import com.wu.service.ContactPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContactPersonServiceImpl implements ContactPersonService {
    @Autowired
    private ContactPersonMapper contactPersonMapper;

    @Override
    public List<ContactPerson> listContactPersons() {
        List<ContactPerson> contactPersonList = this.contactPersonMapper.selectList(null);
        return contactPersonList;
    }

    @Override
    public ContactPerson getContactPersonById(Integer id) {
        ContactPerson contactPerson = this.contactPersonMapper.selectById(id);
        return contactPerson;
    }

    @Override
    public void deleteById(Integer id) {
        this.contactPersonMapper.deleteById(id);
    }

    @Override
    public void updateContactPerson(ContactPerson contactPerson) {
        this.contactPersonMapper.updateById(contactPerson);
    }

    @Override
    public void addContactPerson(ContactPerson contactPerson) {
        this.contactPersonMapper.insert(contactPerson);
    }
}
