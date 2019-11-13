import com.company.dao.DAO;
import com.company.model.User;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class DAOTest {
    @Test
    public void findsHardcodedUserInDB() {
        User hardcodedUser = new User((long)1, "test", "test");
        DAO dao = new DAO();
        User foundUser = dao.getUser(hardcodedUser.getId());

        assertNotNull(foundUser);
        assertSame(foundUser.getId(), hardcodedUser.getId());
        assertSame(foundUser.getAddress(), hardcodedUser.getAddress(), "the same address");
        assertSame(foundUser.getName(), hardcodedUser.getName(), "the same name");
    }

    @Test
    public void wontFindNonExistingUserInDB() {
        DAO dao = new DAO();
        User foundUser = dao.getUser((long)-1);

        assertNull(foundUser);
    }
}
