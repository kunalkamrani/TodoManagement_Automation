package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import pages.TodoPage;

import java.util.List;

public class TodosSteps {
    WebDriver driver;
    TodoPage todoPage;

    @Before(order = 1)
    public void initializeTest() {
        driver = BaseTest.getDriver();
        todoPage = new TodoPage(driver);
    }

    @Given("^user is on (.*) application$")
    public void todoManagement(String appName) {
        Assert.assertEquals(driver.getTitle(), appName);
    }

    @When("^user add a new Todo item - (.*)$")
    public void addNewTodo(String task) throws InterruptedException {
        todoPage.enterTask(task);
    }

    @When("^user complete the Todo item - (.*)$")
    public void completeTask(String task) throws InterruptedException {
        todoPage.completeTask(task);
    }

    @When("^user click on clear completed tasks$")
    public void clearCompletedTask() {
        todoPage.clickClearCompleted();
    }

    @When("^user deletes the Todo item - (.*)$")
    public void deleteTask(String task) {
        todoPage.deleteTask(task);
        System.out.println("To do item deleted");
    }

    @And("^user click on (.*) filter$")
    public void selectFilter(String filter) {
        if (filter.equalsIgnoreCase("All")) {
            todoPage.clickAll();
        } else if (filter.equalsIgnoreCase("Active")) {
            todoPage.clickActive();
        } else if (filter.equalsIgnoreCase("Completed")) {
            todoPage.clickCompleted();
        }

    }

    @Then("^user should see the task (.*) in the todo list$")
    public void verifyTodoList(String task) {
        Assert.assertTrue(todoPage.isTaskPresent(task));
    }

    @Then("^user should see the task (.*) marked as completed")
    public void verifyCompletedTasks(String task) {
        Assert.assertTrue(todoPage.isTaskCompleted(task));
    }

    @Then("^user should not see the task (.*) marked as deleted")
    public void verifyDeletedTasks(String task) {
        //  Assert.assertFalse(todoPage.isTaskDeleted(task));

    }

    @When("^user edit the task (.*) to (.*)$")
    public void editTask(String oldTask, String newTask) {
        todoPage.editTask(oldTask, newTask);
    }

    @Then("^user should see (.*) active tasks$")
    public void verifyActiveTaskCount(int expectedCount) {
        int actualCount = todoPage.getActiveTaskCount();
        Assert.assertEquals(expectedCount, actualCount);
    }

    @And("^user adds multiple tasks")
    public void addMultipleTasks(List<String> tasks) throws InterruptedException {
        todoPage.addMultipleTasks(tasks);
    }
}
