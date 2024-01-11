package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.Constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequestScope
@Slf4j
@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    public ContactService(){
        System.out.println("ContactServise is initialized ") ;
    }

    private int counter= 0;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public boolean saveMessageDeatails(Contact contact){
        boolean isSaved=false ;
        contact.setStatus(EazySchoolConstants.OPEN);
//        contact.setCreatedAt(LocalDateTime.now());
//        contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        Contact result =contactRepository.save(contact);
        if(result !=null && result.getContactId()>0){
            isSaved =true ;
        }
        return isSaved ;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir){
        int pageSize=5;
        Pageable pageable= PageRequest.of(pageNum-1,pageSize,
                sortDir.equals("asc")? Sort.by(sortField).ascending(): Sort.by(sortField).descending());

        Page<Contact> msgPage= contactRepository.findByStatus(EazySchoolConstants.OPEN,pageable);
        return msgPage;
    }

    public boolean updateMsgStatus(int contactId){
//        boolean isUpdated=false ;
//
//        Optional<Contact> contact= contactRepository.findById(contactId);
//        contact.ifPresent(consumer1->{
////            consumer1.setUpdatedAt(LocalDateTime.now());
////            consumer1.setUpdatedBy(updatedBy);
//            consumer1.setStatus(EazySchoolConstants.CLOSE);
//                });
//        contactRepository.save(contact.get());
        int row = contactRepository.updateStatusByid(EazySchoolConstants.CLOSE,contactId);
        return !(row>0) ;
    }

}