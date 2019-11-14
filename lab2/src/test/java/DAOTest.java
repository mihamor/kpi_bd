import com.company.Main;
import com.company.dao.DAO;
import com.company.model.User;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static junit.framework.TestCase.*;

public class DAOTest {

    String url;
    String password;
    String username;

    @Before
    public void init() {
        Properties props = new Properties();
        Logger lgr = Logger.getLogger(Main.class.getName());

        try {
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/db.properties");
            props.load(in);
            in.close();
        } catch (IOException ex) {
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return;
        }

        url = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        password = props.getProperty("jdbc.password");
    }

    @Test
    public void findsHardcodedUserInDB() throws SQLException {
        User hardcodedUser = new User((long)1, "test", "test");
        DAO dao = new DAO(url, username, password);
        dao.connect();
        User foundUser = dao.getUser(hardcodedUser.getId());

        assertNotNull(foundUser);
        assertEquals(foundUser.getId(), hardcodedUser.getId());
        assertEquals(foundUser.getAddress(), hardcodedUser.getAddress());
        assertEquals(foundUser.getName(), hardcodedUser.getName());
    }

    @Test
    public void wontFindNonExistingUserInDB() throws SQLException {
        DAO dao = new DAO(url, username, password);
        dao.connect();
        User foundUser = dao.getUser((long)-1);

        assertNull(foundUser);
    }
}
