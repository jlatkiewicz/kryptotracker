package dreamteam.kryptotracker.infrastructure.user;

import dreamteam.kryptotracker.domain.user.User;
import dreamteam.kryptotracker.domain.user.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MongoUserDAOTest {
    @Autowired
    private MongoUserDAO dao;

    @Test
    public void canRetrievedSavedUser() {
        User sampleUser = new User("username", "password", UserRole.USER);
        dao.add(sampleUser).block();

        dao.add(sampleUser);

        User fetchedUser = dao.findByUsername(sampleUser.getUsername()).block();

        assertEquals(sampleUser, fetchedUser);
    }
}