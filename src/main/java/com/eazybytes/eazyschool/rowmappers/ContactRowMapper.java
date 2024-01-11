package com.eazybytes.eazyschool.rowmappers;

import com.eazybytes.eazyschool.model.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setContactId(rs.getInt(1));
        contact.setName(rs.getString(2));
        contact.setMobileNum(rs.getString(3));
        contact.setEmail(rs.getString(4));
        contact.setSubject(rs.getString(5));
        contact.setMessage(rs.getString(6));
        contact.setStatus(rs.getString(7));
        contact.setCreatedAt(rs.getTimestamp(8).toLocalDateTime());
        contact.setCreatedBy(rs.getString(9));

        if(null != rs.getTimestamp(10)){
            contact.setUpdatedAt(rs.getTimestamp(10).toLocalDateTime());
        }

        contact.setUpdatedBy(rs.getString(11));

        return contact;
    }
}
