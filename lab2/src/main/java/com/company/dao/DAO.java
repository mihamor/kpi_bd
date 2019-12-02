package com.company.dao;

import com.company.model.*;

import java.sql.*;

import java.util.List;

public class DAO implements IDAO {

    IDAOImpl<User> usersDAOImpl;
    IDAOImpl<Rating> ratingDAOImpl;
    IDAOImpl<Answer> answerDAOImpl;
    IDAOImpl<Question> questionDAOImpl;
    IDAOImpl<Tag> tagDAOImpl;

    Connection connection;
    String url;
    String user;
    String password;

    public DAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void connect() throws SQLException{
        connection = DriverManager.getConnection(url, user, password);
        usersDAOImpl = new DAOImpl<>(User.class, connection);
        ratingDAOImpl = new DAOImpl<>(Rating.class, connection);
        answerDAOImpl = new DAOImpl<>(Answer.class, connection);
        questionDAOImpl = new DAOImpl<>(Question.class, connection);
        tagDAOImpl = new DAOImpl<>(Tag.class, connection);
    }

    public User getUser(Long id) throws SQLException {
        return usersDAOImpl.getEntity(id);
    }

    public User updateUser(User user) throws SQLException, IllegalAccessException {
        return usersDAOImpl.updateEntity(user);
    }

    public User deleteUser(Long id) throws SQLException {
        return usersDAOImpl.deleteEntity(id);
    }

    public User insertUser(User user) throws SQLException, IllegalAccessException {
        return usersDAOImpl.insertEntity(user);
    }

    public Rating updateRating(Rating rating) throws SQLException, IllegalAccessException {
        return ratingDAOImpl.updateEntity(rating);
    }

    public Rating deleteRating(Long id) throws SQLException {
        return ratingDAOImpl.deleteEntity(id);
    }

    public Rating insertRating(Rating rating) throws SQLException, IllegalAccessException {
        return ratingDAOImpl.insertEntity(rating);
    }

    public Answer updateAnswer(Answer answer) throws SQLException, IllegalAccessException {
        return answerDAOImpl.updateEntity(answer);
    }

    public Answer deleteAnswer(Long id) throws SQLException {
        return answerDAOImpl.deleteEntity(id);
    }

    public Answer insertAnswer(Answer answer) throws SQLException, IllegalAccessException {
        return answerDAOImpl.insertEntity(answer);
    }

    public List<User> getUserList() throws SQLException {
        return usersDAOImpl.getEntityList();
    }
    public Rating getRating(Long id) throws SQLException {
        return ratingDAOImpl.getEntity(id);
    }
    public List<Rating> getRatingList() throws SQLException {
        return ratingDAOImpl.getEntityList();
    }

    public Answer getAnswer(Long id) throws SQLException {
        return answerDAOImpl.getEntity(id);
    }

    public List<Answer> getAnswerList() throws SQLException {
        return answerDAOImpl.getEntityList();
    }

    public Question getQuestion(Long id) throws SQLException {
        return questionDAOImpl.getEntity(id);
    }

    public List<Question> getQuestionList() throws SQLException {
        return questionDAOImpl.getEntityList();
    }

    public Question updateQuestion(Question question) throws SQLException, IllegalAccessException {
        return questionDAOImpl.updateEntity(question);
    }

    public Question deleteQuestion(Long id) throws SQLException {
        return questionDAOImpl.deleteEntity(id);
    }

    public Question insertQuestion(Question question) throws SQLException, IllegalAccessException {
        return questionDAOImpl.insertEntity(question);
    }

    public Tag getTag(Long id) throws SQLException {
        return tagDAOImpl.getEntity(id);
    }

    public List<Tag> getTagList() throws SQLException {
        return tagDAOImpl.getEntityList();
    }

    public Tag updateTag(Tag tag) throws SQLException, IllegalAccessException {
        return tagDAOImpl.updateEntity(tag);
    }

    public Tag deleteTag(Long id) throws SQLException {
        return tagDAOImpl.deleteEntity(id);
    }

    public Tag insertTag(Tag tag) throws SQLException, IllegalAccessException {
        return tagDAOImpl.insertEntity(tag);
    }

    public List<Question> searchWord(String word, boolean including) throws SQLException {
        String sql = "SELECT qid, creation_date, uid,"
            + " ts_headline(essence, q, 'StartSel=<!>, StopSel=<!>') as essence,"
            + " ts_headline(description, q, 'StartSel=<!>, StopSel=<!>') as description"
            + " FROM public.questions , to_tsquery(?) as q"
            + " WHERE " + (including == true ? "" : "not")
            + " to_tsvector(description) || to_tsvector(essence) @@ q";

        PreparedStatement preparedStatement = connection.prepareStatement(
                sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY
        );
        preparedStatement.setString(1, word);
        ResultSet resultSet = preparedStatement.executeQuery();

        return questionDAOImpl.resultSetToList(resultSet);
    }

    public ResultSet joinedQuestionSearch(Boolean disabled, String description) throws SQLException {
        String sql = "SELECT * FROM questions INNER JOIN users ON users.uid = questions.uid "
        + "WHERE (disabled = ? AND description = ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(
                sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY
        );
        preparedStatement.setBoolean(1, disabled);
        preparedStatement.setString(2, description);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }
    public String getEntityErrorMessage() {
        return usersDAOImpl.getEntityErrorMessage() + ratingDAOImpl.getEntityErrorMessage();
    }
}
