package com.example.atf.models.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="contact")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contact_id")
    private Long contactId;

    @NotNull
    @Column(name="contact_phone")
    private String contactPhone;

    @NotNull
    @ManyToOne
    @JoinColumn(name="contact_client_id")
    private ClientEntity contactClientId;

    @NotNull
    @ManyToOne
    @JoinColumn(name="contact_type_id")
    private ContactTypeEntity contactTypeId;
}
