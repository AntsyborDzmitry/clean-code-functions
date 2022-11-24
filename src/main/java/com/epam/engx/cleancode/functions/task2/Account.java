package com.epam.engx.cleancode.functions.task2;

import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Level;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.NotActivUserException;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Review;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.User;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public abstract class Account implements User {

    private Map<Integer, Level> levelMap = new TreeMap<>();

    public Level getActivityLevel() {
        validateIfUserActive();
        int totalAnswersQuantity = calculateTotalNumberOfReviewAnswers(getAllReviews());
        return getLevelByReviewAnswers(totalAnswersQuantity);

    }

    private void validateIfUserActive() {
        if (isNotActiveUser()) {
            throw new NotActivUserException();
        }
    }

    private boolean isNotActiveUser() {
        return isNotRegistered() || hasNoVisits();
    }

    private boolean isNotRegistered() {
        return !isRegistered();
    }

    private boolean hasNoVisits() {
        return getVisitNumber() <= 0;
    }

    private int calculateTotalNumberOfReviewAnswers(List<Review> reviews) {
        int totalAnswersQuantity = 0;
        for (Review review : reviews) {
            totalAnswersQuantity += getReviewAnswers(review);
        }
        return totalAnswersQuantity;
    }

    private int getReviewAnswers(Review review) {
        return review.getAnswers().size();
    }

    private Level getLevelByReviewAnswers(int userAnswersNumber) {
        for (Integer answerLimitNumber : getAllLimitsAnswer()) {
            if (userAnswersNumber >= answerLimitNumber) {
                return getLevelByLimit(answerLimitNumber);
            }
        }
        return Level.defaultLevel();
    }

    private Level getLevelByLimit(Integer answerLimitNumber) {
        return this.levelMap.get(answerLimitNumber);
    }

    private Set<Integer> getAllLimitsAnswer() {
        return this.levelMap.keySet();
    }

    public void setLevelMap(Map<Integer, Level> levelMap) {
        this.levelMap = levelMap;
    }
}
