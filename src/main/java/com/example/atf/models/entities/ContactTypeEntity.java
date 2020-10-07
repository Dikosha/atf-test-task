package com.example.atf.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="contact_type")
public class ContactTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contact_type_id")
    private Long contactTypeId;

    @Column(name="contact_type_name")
    private String contactTypeName;

}
