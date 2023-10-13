package edu.pro.crypto.testtaskcrypto.repository;
/*
  @author   george
  @project   test-task-crypto
  @class  UserRepository
  @version  1.0.0 
  @since 13.10.23 - 10.36
*/

import edu.pro.crypto.testtaskcrypto.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
