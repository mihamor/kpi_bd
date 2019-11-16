import com.company.dao.DAO;
import com.company.dao.DAOImpl;
import com.company.model.Comment;
import com.company.model.Grade;
import com.company.model.Review;
import com.company.model.User;
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
    DAOImpl<Comment> commentDAO;
    DAOImpl<Review> reviewDAO;

    @Before
    public void init() throws IOException {
        MockFileDatabase db = new MockFileDatabase(new File(System.getProperty("user.dir") + "/db.test"));
        connection = new MockConnection(db);

        userDAO = new DAOImpl<>(User.class, connection);
        commentDAO = new DAOImpl<>(Comment.class, connection);
        reviewDAO = new DAOImpl<>(Review.class, connection);
    }

    @Test
    public void getValidUserList() throws SQLException {
        List<User> users = userDAO.getEntityList();

        assertEquals(2, users.size());
        assertEquals("user1", users.get(0).getName());
        assertEquals("user2", users.get(1).getName());
    }

    @Test
    public void getValidCommentList() throws SQLException {
        List<Comment> comments = commentDAO.getEntityList();

        assertEquals(2, comments.size());
        assertEquals("test1", comments.get(0).getContent());
        assertEquals("test2", comments.get(1).getContent());
    }

    @Test
    public void getValidReviewList() throws SQLException {
        List<Review> reviews = reviewDAO.getEntityList();

        assertEquals(1, reviews.size());
        assertEquals(Grade.GOOD.getValue(), reviews.get(0).getGrade().getValue());
    }

    @Test
    public void getValidUserById() throws SQLException {
        Long id = new Long(1);
        User user = userDAO.getEntity(id);

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("user1", user.getName());
        assertEquals("addr1", user.getAddress());
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
        Comment comment = commentDAO.getEntity(id);

        assertNotNull(comment);
        assertEquals(id, comment.getId());
        assertEquals("test1", comment.getContent());
    }

    @Test
    public void getValidReviewById() throws SQLException {
        Long id = new Long(3);
        Review review = reviewDAO.getEntity(id);

        assertNotNull(review);
        assertEquals(id, review.getId());
        assertEquals(Grade.GOOD.getValue(), review.getGrade().getValue());
    }
}
