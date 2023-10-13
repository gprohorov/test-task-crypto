package edu.pro.crypto.testtaskcrypto.model;
/*
  @author   george
  @project   test-task-crypto
  @class  User
  @version  1.0.0 
  @since 13.10.23 - 10.22
*/

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Data
@NoArgsConstructor
@Getter
@Setter
@Document
public class User {
    private String id;
    private String userName;;
    private String email;;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public User(String id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
