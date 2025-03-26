package application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class QuestionListTest {

    /*
     Clears the static question list before each test to ensure a clean test environment.
     */
    @BeforeEach
    public void clearQuestions() throws Exception {
        Field field = QuestionList.class.getDeclaredField("questionAnswers");
        field.setAccessible(true);
        ((List<?>) field.get(null)).clear();
    }

    /*
     Tests that a question can be added and retrieved correctly.
     */
    @Test
    public void testAddQuestion() throws Exception {
        User user = new User("alice", "pass", "user");
        QuestionList.addQuestion("Is the sky blue?", user);

        List<?> questionAnswers = getQuestionAnswers();
        assertEquals(1, questionAnswers.size());

        Object qa = questionAnswers.get(0);
        String questionText = (String) qa.getClass().getMethod("getQuestion").invoke(qa);
        assertEquals("Is the sky blue?", questionText);
    }

    /*
      Tests adding an answer to an existing question.
     */
    @Test
    public void testAddAnswer() throws Exception {
        QuestionList.addQuestion("Who is your favorite singer?", new User("bob", "123", "user"));
        Object qa = getQuestionAnswers().get(0);

        qa.getClass().getMethod("addAnswer", String.class).invoke(qa, "I love Taylor Swift");

        List<?> answers = (List<?>) qa.getClass().getMethod("getAnswers").invoke(qa);
        assertEquals(1, answers.size());

        Object answer = answers.get(0);
        String text = (String) answer.getClass().getMethod("getText").invoke(answer);
        assertEquals("I love Taylor Swift", text);
    }

    /*
      Tests editing an existing answer's text.
     */
    @Test
    public void testEditAnswer() throws Exception {
        QuestionList.addQuestion("How many letters in the alphabet?", new User("charlie", "abc", "user"));
        Object qa = getQuestionAnswers().get(0);

        qa.getClass().getMethod("addAnswer", String.class).invoke(qa, "There are 26");
        qa.getClass().getMethod("editAnswer", int.class, String.class).invoke(qa, 0, "The alphabet has 26 letters");

        List<?> answers = (List<?>) qa.getClass().getMethod("getAnswers").invoke(qa);
        Object answer = answers.get(0);
        String updated = (String) answer.getClass().getMethod("getText").invoke(answer);
        assertEquals("The alphabet has 26 letters", updated);
    }

    /*
      Tests the behavior of checking if a question is unanswered before and after adding an answer.
     */
    @Test
    public void testUnansweredQuestion() throws Exception {
        QuestionList.addQuestion("Unanswered question?", new User("dan", "qwerty", "user"));
        Object qa = getQuestionAnswers().get(0);

        boolean isUnanswered = (boolean) qa.getClass().getMethod("isUnanswered").invoke(qa);
        assertTrue(isUnanswered);

        qa.getClass().getMethod("addAnswer", String.class).invoke(qa, "Here's an answer");

        boolean isAnsweredNow = !(boolean) qa.getClass().getMethod("isUnanswered").invoke(qa);
        assertTrue(isAnsweredNow);
    }

    /*
     Tests editing the text of an existing question.
     */
    @Test
    public void testEditQuestionText() throws Exception {
        QuestionList.addQuestion("Old question?", new User("eve", "xyz", "user"));
        Object qa = getQuestionAnswers().get(0);

        qa.getClass().getMethod("setQuestion", String.class).invoke(qa, "New revised question?");
        String updated = (String) qa.getClass().getMethod("getQuestion").invoke(qa);
        assertEquals("New revised question?", updated);
    }

    /*
     Helper method to retrieve the internal static question list via reflection.

     */
    private List<?> getQuestionAnswers() throws Exception {
        Field field = QuestionList.class.getDeclaredField("questionAnswers");
        field.setAccessible(true);
        return (List<?>) field.get(null);
    }
}
