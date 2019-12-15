package com.company.controller;

import com.company.dao.IDAO;
import com.company.model.*;
import com.company.view.View;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private View view;
    private IDAO dao;

    public Controller(View view, IDAO dao) {
        this.view = view;
        this.dao = dao;
    }

    public void mainMenu() throws SQLException, IllegalAccessException {
        boolean exit = false;
        while (!exit) {
            view.clearScreen();
            view.showMenu();
            int operation = view.getOptionInput();
            int entity = 0;
            switch (operation) {
                case 0: {
                    view.clearScreen();
                    view.showEntities();
                    entity = view.getOptionInput();
                    view.clearScreen();
                    randomize(entity);
                    break;
                }
                case 1: {
                    view.clearScreen();
                    view.showEntities();
                    entity = view.getOptionInput();
                    view.clearScreen();
                    selectAll(entity);
                    break;
                }
                case 2: {
                    view.clearScreen();
                    view.showEntities();
                    entity = view.getOptionInput();
                    view.clearScreen();
                    view.askId();
                    long id = view.getOptionInput();
                    selectById(id, entity);
                    break;
                }
                case 3: {
                    view.clearScreen();
                    view.showEntities();
                    entity = view.getOptionInput();
                    view.clearScreen();
                    insert(entity);
                    break;
                }
                case 4: {
                    view.clearScreen();
                    view.showEntities();
                    entity = view.getOptionInput();
                    view.clearScreen();
                    update(entity);
                    break;
                }
                case 5: {
                    view.clearScreen();
                    view.showEntities();
                    entity = view.getOptionInput();
                    view.clearScreen();
                    view.askId();
                    long id = view.getOptionInput();
                    delete(id, entity);
                    break;
                }
                case 6: {
                    view.clearScreen();
                    List<Question> questions = dao.searchWord(
                            view.getString("word"),
                            view.getBoolean("including(true/false)"));
                    view.showQuestions(questions);
                    break;
                }
                case 7: {
                    view.clearScreen();
                    ResultSet resultSet = dao.joinedQuestionSearch(
                            view.getBoolean("user disabled(true/false)"),
                            view.getString("description")
                    );
                    view.joinedSearchResult(resultSet);
                    break;
                }
                case 8: {
                    exit = true;
                    break;
                }
            }
        }
    }

    private void randomize(int entity) {
        switch (entity) {
            case 1: {
                long quantity = view.getLong("Enter quantity");
                List<User> users = new ArrayList<>();
                for (int i = 0; i < quantity; i++) {
                    User user = new User(
                            null,
                            generateRandomString(10),
                            generateRandomString(10),
                            generateRandomString(10),
                            generateRandomBoolean()
                    );
                    users.add(user);
                }

                List<User> insertedUsers = dao.insertUserList(users);
                view.clearScreen();
                view.showUsers(insertedUsers);
                break;
            }
            case 2: {
                Answer answer = new Answer(
                    null,
                    Timestamp.from(Instant.now()),
                    new Long(1),
                    new Long(1),
                    generateRandomString(10)
                );
                Answer insertedAnswer = dao.insertAnswer(answer);
                view.clearScreen();
                view.showAnswer(insertedAnswer);
                break;
            }
            case 3: {
                Question question = new Question(
                    null,
                    new Long(1),
                    Timestamp.from(Instant.now()),
                    generateRandomString(10),
                    generateRandomString(10)
                );
                Question insertedQuestion = dao.insertQuestion(question);
                view.clearScreen();
                view.showQuestion(insertedQuestion);
                break;
            }
            case 4: {
                Rating rating = new Rating(
                    null,
                    1L,
                    1L,
                    (long) random.nextInt()
                );
                Rating insertedRating = dao.insertRating(rating);
                view.clearScreen();
                view.showRating(insertedRating);
                break;
            }
            case 5: {
                Tag tag = new Tag(
                    null,
                    generateRandomString(10),
                    generateRandomString(10)
                );
                Tag insertedTag = dao.insertTag(tag);
                view.clearScreen();
                view.showTag(insertedTag);
                break;
            }
        }
    }

    void selectAll(int entity) throws SQLException {
        switch (entity) {
            case 1: {
                List<User> users = dao.getUserList();
                view.clearScreen();
                view.showUsers(users);
                break;
            }
            case 2: {
                List<Answer> answers = dao.getAnswerList();
                view.clearScreen();
                view.showAnswers(answers);
                break;
            }
            case 3: {
                List<Question> questions = dao.getQuestionList();
                view.clearScreen();
                view.showQuestions(questions);
                break;
            }
            case 4: {
                List<Rating> ratings = dao.getRatingList();
                view.clearScreen();
                view.showRatings(ratings);
                break;
            }
            case 5: {
                List<Tag> tags = dao.getTagList();
                view.clearScreen();
                view.showTags(tags);
                break;
            }
        }
    }
    void selectById(Long id, int entity) throws SQLException {
        switch (entity) {
            case 1: {
                User user = dao.getUser(id);
                view.clearScreen();
                view.showUser(user);
                break;
            }
            case 2: {
                Answer answer = dao.getAnswer(id);
                view.clearScreen();
                view.showAnswer(answer);
                break;
            }
            case 3: {
                Question question = dao.getQuestion(id);
                view.clearScreen();
                view.showQuestion(question);
                break;
            }
            case 4: {
                Rating rating = dao.getRating(id);
                view.clearScreen();
                view.showRating(rating);
                break;
            }
            case 5: {
                Tag tag = dao.getTag(id);
                view.clearScreen();
                view.showTag(tag);
                break;
            }
        }
    }

    void delete(Long id, int entity) throws SQLException {
        view.showDeleted();
        switch (entity) {
            case 1: {
                User user = dao.deleteUser(id);
                view.clearScreen();
                view.showUser(user);
                break;
            }
            case 2: {
                Answer answer = dao.deleteAnswer(id);
                view.clearScreen();
                view.showAnswer(answer);
                break;
            }
            case 3: {
                Question question = dao.deleteQuestion(id);
                view.clearScreen();
                view.showQuestion(question);
                break;
            }
            case 4: {
                Rating rating = dao.deleteRating(id);
                view.clearScreen();
                view.showRating(rating);
                break;
            }
            case 5: {
                Tag tag = dao.deleteTag(id);
                view.clearScreen();
                view.showTag(tag);
                break;
            }
        }
    }
    void insert(int entity) throws SQLException, IllegalAccessException {
        switch (entity) {
            case 1: {
                User user = new User(
                    null,
                    view.getString("username"),
                    view.getString("fullname"),
                    view.getString("passhash"),
                    view.getBoolean("disabled")
                );
                User insertedUser = dao.insertUser(user);
                view.clearScreen();
                view.showUser(insertedUser);
                break;
            }
            case 2: {
                Answer answer = new Answer(
                    null,
                    Timestamp.from(Instant.now()),
                    view.getLong("userId"),
                    view.getLong("questionId"),
                    view.getString("answer text")
                );
                Answer insertedAnswer = dao.insertAnswer(answer);
                view.clearScreen();
                view.showAnswer(insertedAnswer);
                break;
            }
            case 3: {
                Question question = new Question(
                    null,
                    view.getLong("userId"),
                    Timestamp.from(Instant.now()),
                    view.getString("essence"),
                    view.getString("description")
                );
                Question insertedQuestion = dao.insertQuestion(question);
                view.clearScreen();
                view.showQuestion(insertedQuestion);
                break;
            }
            case 4: {
                Rating rating = new Rating(
                    null,
                    view.getLong("userId"),
                    view.getLong("answerId"),
                    view.getLong("quantity")
                );
                Rating insertedRating = dao.insertRating(rating);
                view.clearScreen();
                view.showRating(insertedRating);
                break;
            }
            case 5: {
                Tag tag = new Tag(
                    null,
                    view.getString("name"),
                    view.getString("description")
                );
                Tag insertedTag = dao.insertTag(tag);
                view.clearScreen();
                view.showTag(insertedTag);
                break;
            }
        }
    }
    void update(int entity) throws SQLException, IllegalAccessException {
        switch (entity) {
            case 1: {
                User user = new User(
                    view.getLong("id"),
                    view.getString("username"),
                    view.getString("fullname"),
                    view.getString("passhash"),
                    view.getBoolean("disabled")
                );
                User updatedUser = dao.updateUser(user);
                view.clearScreen();
                view.showUser(updatedUser);
                break;
            }
            case 2: {
                Answer answer = new Answer(
                    view.getLong("id"),
                    Timestamp.from(Instant.now()),
                    view.getLong("userId"),
                    view.getLong("questionId"),
                    view.getString("answer text")
                );
                Answer updatedAnswer = dao.updateAnswer(answer);
                view.clearScreen();
                view.showAnswer(updatedAnswer);
                break;
            }
            case 3: {
                Question question = new Question(
                    view.getLong("id"),
                    view.getLong("userId"),
                    Timestamp.from(Instant.now()),
                    view.getString("essence"),
                    view.getString("description")
                );
                Question updatedQuestion = dao.updateQuestion(question);
                view.clearScreen();
                view.showQuestion(updatedQuestion);
                break;
            }
            case 4: {
                Rating rating = new Rating(
                    view.getLong("id"),
                    view.getLong("userId"),
                    view.getLong("questionId"),
                    view.getLong("quantity")
                );
                Rating updatedRating = dao.updateRating(rating);
                view.clearScreen();
                view.showRating(updatedRating);
                break;
            }
            case 5: {
                Tag tag = new Tag(
                    view.getLong("id"),
                    view.getString("name"),
                    view.getString("description")
                );
                Tag updatedTag = dao.updateTag(tag);
                view.clearScreen();
                view.showTag(updatedTag);
                break;
            }
        }
    }

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();



    private static Boolean generateRandomBoolean() {
        return random.nextBoolean();
    }

    private static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            // debug
            // System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

            sb.append(rndChar);

        }
        return sb.toString();
    }


}
