package com.company.view;

import com.company.model.*;

import java.util.List;
import java.util.Scanner;

public class View {
    public View() {}

    public void clearScreen() {
        try {
            Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public int getOptionInput() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public void askId() {
        System.out.println("Enter id: ");
    }

    public void showDeleted() {
        System.out.println("Deleted entity");
    }

    public void showMenu() {
        System.out.println("1. Get list of...");
        System.out.println("2. Get instance by id of...");
        System.out.println("3. Insert...");
        System.out.println("4. Update...");
        System.out.println("5. Delete...");
        System.out.println("6. Full text search...");
        System.out.println("7. Search...");
        System.out.println("8. Exit");
    }

    public void showEntities() {
        System.out.println("1. User");
        System.out.println("2. Answer");
        System.out.println("3. Question");
        System.out.println("4. Rating");
        System.out.println("5. Tag");
    }

    public void showUser(User user) {
        System.out.println("--------------");
        System.out.println("id: " + user.getId());
        System.out.println("username: " + user.getUsername());
        System.out.println("fullname: " + user.getFullname());
        System.out.println("passhash: " + user.getPasshash());
    }

    public void showQuestion(Question question) {
        System.out.println("--------------");
        System.out.println("id: " + question.getId());
        System.out.println("essence: " + question.getEssence());
        System.out.println("description: " + question.getDescription());
        System.out.println("creation_date: " + question.getCreationDate().toLocalizedPattern());
        System.out.println("uid: " + question.getUserId());
    }

    public void showAnswer(Answer answer) {
        System.out.println("--------------");
        System.out.println("id: " + answer.getId());
        System.out.println("username: " + answer.getAnswerText());
        System.out.println("creation_date: " + answer.getCreationDate().toLocalizedPattern());
        System.out.println("qid: " + answer.getQuestionId());
        System.out.println("uid: " + answer.getUserId());
    }

    public void showRating(Rating rating) {
        System.out.println("--------------");
        System.out.println("id: " + rating.getId());
        System.out.println("essence: " + rating.getQuantity());
        System.out.println("aid: " + rating.getAnswerId());
        System.out.println("uid: " + rating.getUserId());
    }

    public void showTag(Tag tag) {
        System.out.println("--------------");
        System.out.println("id: " + tag.getId());
        System.out.println("name: " + tag.getName());
        System.out.println("description: " + tag.getDescription());
    }


    public void showUsers(List<User> users) {
        for (User user: users) {
            showUser(user);
        }
    }

    public void showAnswers(List<Answer> answers) {
        for (Answer answer: answers) {
            showAnswer(answer);
        }
    }

    public void showQuestions(List<Question> questions) {
        for (Question question: questions) {
            showQuestion(question);
        }
    }

    public void showRatings(List<Rating> ratings) {
        for (Rating rating: ratings) {
            showRating(rating);
        }
    }

    public void showTags(List<Tag> tags) {
        for (Tag tag: tags) {
            showTag(tag);
        }
    }

    public String getString(String name) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + name + ": ");
        return sc.nextLine();
    }

    public Long getLong(String name) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + name + ": ");
        return sc.nextLong();
    }
}
