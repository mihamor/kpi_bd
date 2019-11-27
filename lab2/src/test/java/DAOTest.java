import com.company.dao.DAOImpl;
import com.company.model.*;
import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockFileDatabase;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.*;

public class DAOTest {

    Connection connection;
    DAOImpl<User> userDAO;
    DAOImpl<Rating> ratingDAO;
    DAOImpl<Answer> answerDao;

    @Before
    public void init() throws IOException {
        MockFileDatabase db = new MockFileDatabase(new File(System.getProperty("user.dir") + "/db.test"));
        connection = new MockConnection(db);

        userDAO = new DAOImpl<>(User.class, connection);
        ratingDAO = new DAOImpl<>(Rating.class, connection);
        answerDao = new DAOImpl<>(Answer.class, connection);
    }

    @Test
    public void getValidUserList() throws SQLException {
        List<User> users = userDAO.getEntityList();

        assertEquals(2, users.size());
        assertEquals("user1", users.get(0).getUsername());
        assertEquals("user2", users.get(1).getUsername());
    }

    @Test
    public void getValidCommentList() throws SQLException {
        List<Rating> comments = ratingDAO.getEntityList();

        assertEquals(2, comments.size());
        assertEquals("test1", comments.get(0).getAnswerId());
        assertEquals("test2", comments.get(1).getAnswerId());
    }

    @Test
    public void getValidAnswerList() throws SQLException {
        List<Answer> answers = answerDao.getEntityList();

        assertEquals(1, answers.size());
        assertEquals("cool", answers.get(0).getAnswerText());
    }

    @Test
    public void getValidUserById() throws SQLException {
        Long id = new Long(1);
        User user = userDAO.getEntity(id);

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("user1", user.getFullname());
        assertEquals("addr1", user.getUsername());
    }

    @Test
    public void wontFindNonExistingUserInDB() throws SQLException {
        Long id = new Long(3);
        User user = userDAO.getEntity(id);

        assertNull(user);
    }

    @Test
    public void getValidCommentById() throws SQLException {
        Long id = new Long(1);
        Rating comment = ratingDAO.getEntity(id);

        assertNotNull(comment);
        assertEquals(id, comment.getId());
        assertEquals("test1", comment.getAnswerId());
    }

    @Test
    public void getValidReviewById() throws SQLException {
        Long id = new Long(3);
        Answer answer = answerDao.getEntity(id);

        assertNotNull(answer);
        assertEquals(id, answer.getId());
        assertEquals("cool", answer.getAnswerText());
    }
}
