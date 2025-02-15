package stepDefinitions;

import io.cucumber.java.Before;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;
import pages.TodoPage;
import base.BaseTest;

public class TodosSteps {
	WebDriver driver;
	TodoPage todoPage;

    @Before(order =1)
    public void initializeTest()
    {
        driver=BaseTest.getDriver();
        todoPage = new TodoPage(driver);
    }

	@Given("user is on (.*) application")
	public void user_is_on_todo_page(String appName) {
	Assert.assertEquals("driver.getTitle()",appName);
	}

	@When("^user add a new Todo item - (.*)$")
	public void user_addNewTodo(String task) throws InterruptedException {
		todoPage.enterTask(task);
	}

	@When("^user complete the Todo item - (.*)$")
	public void user_completeTodo(String task) throws InterruptedException {
		todoPage.completeTask(task);
	}

	@When("^user deletes the Todo item - (.*)$")
	public void user_deleteTodo(String task) {
		todoPage.deleteTask(task);
		System.out.println("To do item deleted");
	}

	@And("^user clicks on (.*) filter$")
	public void user_clicks_on_filter(String filter) {
		if (filter.equalsIgnoreCase("All")) {
			todoPage.clickAll();
		} else if (filter.equalsIgnoreCase("Active")) {
			todoPage.clickActive();
		} else if (filter.equalsIgnoreCase("Completed")) {
			todoPage.clickCompleted();
		}

	}

	@Then("^user should see the task (.*) in the todo list$")
	public void user_should_see_in_the_todo_list(String task) {
		Assert.assertTrue(todoPage.isTaskPresent(task));
	}

	@Then("^user should see the task (.*) marked as completed")
	public void user_should_see_todo_completed(String task) {
		Assert.assertTrue(todoPage.isTaskCompleted(task));
	}

	@Then("^user should not see the task (.*) marked as deleted")
	public void user_should_see_todo_deleted(String task) {
		Assert.assertTrue(todoPage.isTaskDeleted(task));
	}
	
	@When("^user edit the task (.*) to (.*)$")
	public void user_edit_the_task(String oldTask, String newTask)
	{
		todoPage.editTask(oldTask, newTask);
	}

}
