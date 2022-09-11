package ru.practicum.laterwithspringboot.user;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest
public class UserServiceTest {
    private final EntityManager em;
    private final UserService userService;

    @Test
    public void checkSaveUser() {
        User user = makeUser("some@email.com", "Пётр Иванов");
        userService.saveUser(user);

        TypedQuery<User> query = em.createQuery("Select u from User u where u.email = :email", User.class);
        User userFromDb = query.setParameter("email", user.getEmail()).getSingleResult();

        assertThat(userFromDb.getId(), notNullValue());
        assertThat(userFromDb.getName(), equalTo(user.getName()));
        assertThat(userFromDb.getEmail(), equalTo(user.getEmail()));
    }

    @Test
    public void checkGetAllUsers() {
        User user1 = makeUser("some1@email.com", "Пётр Иванов");
        User user2 = makeUser("some2@email.com", "Иван Петров");

        userService.saveUser(user1);
        userService.saveUser(user2);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);
        TypedQuery<User> allQuery = em.createQuery(all);
        List<User> allUsersFromDb = allQuery.getResultList();

        assertThat(allUsersFromDb.size(), equalTo(2));
        assertThat(allUsersFromDb.get(0).getName(), equalTo(user1.getName())); // ?
        assertThat(allUsersFromDb.get(1).getName(), equalTo(user2.getName()));

    }

    private User makeUser(String email, String name) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        return user;
    }
}
