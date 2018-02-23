package warsztaty.spring.ailleron.services;

import org.springframework.stereotype.Service;
import warsztaty.spring.ailleron.exceptions.UserExistsException;
import warsztaty.spring.ailleron.exceptions.UserNotFoundException;
import warsztaty.spring.ailleron.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> users;

    private Optional<User> findUserByName(String name) {
        return users.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst();
    }

    private Optional<User> findUserById(long id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    public UserService() {
        users = new ArrayList<>();
        if (users.isEmpty()) {
            users.add(new User(1L, "Adam", "Małysz", 45));
            users.add(new User(2L, "Kamil", "Stoch", 29));
            users.add(new User(3L, "Noriaki", "Kasai", 99));
        }
    }

    public Optional<User> getUserByName(String name) throws UserNotFoundException {
        Optional<User> user = findUserByName(name);
        if (user.isPresent()) {
            return user;
        }
        throw new UserNotFoundException("User (name " + name + ") not found!");
    }

    public long addUser(User user) throws UserExistsException {
        Optional<User> existingUser = findUserByName(user.getName());
        if (existingUser.isPresent()) {
            throw new UserExistsException("User (name: " + user.getName() + ") already exists!");
        }
        user.setId(Long.valueOf(users.size() + 1));
        users.add(user);
        return user.getId();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User modifyUser(User user) throws UserNotFoundException {
        Optional<User> existingUser = findUserById(user.getId());
        if (!existingUser.isPresent()) {
            throw new UserNotFoundException("User (id: " + user.getId() + ") not found!");
        }
        users.remove(user);
        users.add(user);
        return user;
    }

    private void deleteUserById(long id) throws UserNotFoundException {
        Optional<User> existingUser = findUserById(id);
        if (!existingUser.isPresent()) {
            throw new UserNotFoundException("User (id: " + id + ") not found!");
        }
        users.remove(existingUser.get());
    }

    public void deleteUser(long userId) throws UserNotFoundException {
        deleteUserById(userId);
    }

    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = findUserById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserNotFoundException("User (id: " + id + ") not found!");
    }
}