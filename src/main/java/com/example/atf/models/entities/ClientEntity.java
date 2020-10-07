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
@Table(name="client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="client_id")
    private Long clientId;

    @NotNull
    @Column(name="client_name")
    private String clientName;

    @NotNull
    @Column(name="client_surname")
    private String clientSurname;

    @Column(name="client_patronymic")
    private String clientPatronymic;

}
