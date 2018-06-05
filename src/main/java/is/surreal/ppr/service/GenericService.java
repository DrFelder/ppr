package is.surreal.ppr.service;

import is.surreal.ppr.model.User;

import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();
}
