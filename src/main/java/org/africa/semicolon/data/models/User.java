package org.africa.semicolon.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("collection = users")

public class User {
    @Id
    private String id;
    private String name;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String phone;
    private String password;
    private Role role;
    private Address addresses;

}
