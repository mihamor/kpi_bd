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

    public DAO() {
        usersDAOImpl = new DAOImpl<>(User.class);
        ratingDAOImpl = new DAOImpl<>(Rating.class);
        answerDAOImpl = new DAOImpl<>(Answer.class);
        questionDAOImpl = new DAOImpl<>(Question.class);
        tagDAOImpl = new DAOImpl<>(Tag.class);
    }

    public User getUser(Long id) { return usersDAOImpl.getEntity(id); }

    public User updateUser(User user)  { return usersDAOImpl.updateEntity(user); }

    public User deleteUser(Long id)  {
        return usersDAOImpl.deleteEntity(id);
    }

    public User insertUser(User user)  { return usersDAOImpl.insertEntity(user); }
    public List<User> insertUserList(List<User> users) { return  usersDAOImpl.insertEntityList(users); }

    public Rating updateRating(Rating rating)  { return ratingDAOImpl.updateEntity(rating); }

    public Rating deleteRating(Long id)  {
        return ratingDAOImpl.deleteEntity(id);
    }

    public Rating insertRating(Rating rating)  { return ratingDAOImpl.insertEntity(rating); }

    public Answer updateAnswer(Answer answer)  { return answerDAOImpl.updateEntity(answer); }

    public Answer deleteAnswer(Long id)  {
        return answerDAOImpl.deleteEntity(id);
    }

    public Answer insertAnswer(Answer answer)  { return answerDAOImpl.insertEntity(answer); }

    public List<User> getUserList()  {
        return usersDAOImpl.getEntityList();
    }

    public Rating getRating(Long id)  {
        return ratingDAOImpl.getEntity(id);
    }

    public List<Rating> getRatingList()  {
        return ratingDAOImpl.getEntityList();
    }

    public Answer getAnswer(Long id)  {
        return answerDAOImpl.getEntity(id);
    }

    public List<Answer> getAnswerList()  {
        return answerDAOImpl.getEntityList();
    }

    public Question getQuestion(Long id)  {
        return questionDAOImpl.getEntity(id);
    }

    public List<Question> getQuestionList()  {
        return questionDAOImpl.getEntityList();
    }

    public Question updateQuestion(Question question)  { return questionDAOImpl.updateEntity(question); }

    public Question deleteQuestion(Long id)  {
        return questionDAOImpl.deleteEntity(id);
    }

    public Question insertQuestion(Question question)  { return questionDAOImpl.insertEntity(question); }

    public Tag getTag(Long id)  {
        return tagDAOImpl.getEntity(id);
    }

    public List<Tag> getTagList()  {
        return tagDAOImpl.getEntityList();
    }

    public Tag updateTag(Tag tag)  { return tagDAOImpl.updateEntity(tag); }

    public Tag deleteTag(Long id)  {
        return tagDAOImpl.deleteEntity(id);
    }

    public Tag insertTag(Tag tag)  { return tagDAOImpl.insertEntity(tag); }

    public List<Question> searchWord(String word, boolean including)  {
//        String sql = "SELECT qid, creation_date, uid,"
//            + " ts_headline(essence, q, 'StartSel=<!>, StopSel=<!>') as essence,"
//            + " ts_headline(description, q, 'StartSel=<!>, StopSel=<!>') as description"
//            + " FROM public.questions , plainto_tsquery(?) as q"
//            + " WHERE " + (including == true ? "" : "not")
//            + " to_tsvector(description) || to_tsvector(essence) @@ q";
//
//        PreparedStatement preparedStatement = connection.prepareStatement(
//                sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY
//        );
//        preparedStatement.setString(1, word);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        return questionDAOImpl.resultSetToList(resultSet);
        return  null;
    }

    public ResultSet joinedQuestionSearch(Boolean disabled, String description)  {
//        String sql = "SELECT * FROM questions INNER JOIN users ON users.uid = questions.uid "
//        + "WHERE (disabled = ? AND description = ?)";
//
//        PreparedStatement preparedStatement = connection.prepareStatement(
//                sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY
//        );
//        preparedStatement.setBoolean(1, disabled);
//        preparedStatement.setString(2, description);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        return resultSet;
        return null;
    }
    public String getEntityErrorMessage() {
        return usersDAOImpl.getEntityErrorMessage() + ratingDAOImpl.getEntityErrorMessage();
    }
}
