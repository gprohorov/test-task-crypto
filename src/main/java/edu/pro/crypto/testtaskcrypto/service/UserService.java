package edu.pro.crypto.testtaskcrypto.service;
import edu.pro.crypto.testtaskcrypto.model.User;
import edu.pro.crypto.testtaskcrypto.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
  @author   george
  @project   test-task-crypto
  @class  UserService
  @version  1.0.0 
  @since 13.10.23 - 10.38
*/
@Service
public class UserService {

    @Value("telegram.bot.back-end.number-of-users")
    private String limit;

    private final UserRepository repository;
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    void init() {
        repository.saveAll(
                List.of(
                        new User("1", "John","john.lennon@beatles.com"),
                        new User("2", "Paul","paul.mccartney@beatles.com"),
                        new User("3", "George","george.harrison@beatles.com"),
                        new User("3", "Ringo","ringo.star@beatles.com")
                )
        );
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User create(User user) throws Exception {
      if (repository.count() <= Long.valueOf(limit)) {
            return repository.save(user);
        } else {
          throw new Exception("Bot is busy");
      }
    }

    public User get(String id) {
      return repository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Not found"));
    }
    public User update(User user) {
        return repository.save(user);
    }
    public void delete(String id) {
        repository.deleteById(id);
    }

}
