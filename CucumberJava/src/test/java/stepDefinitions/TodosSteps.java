package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;
import pages.TodosPage;
import utils.BaseTest;

public class TodosSteps {
	WebDriver driver;
	TodosPage todosPage;

	public TodosSteps() {
		this.driver = BaseTest.getDriver();
	}

	@Given("user is on Todos application")
	public void user_is_on_todo_page() {
		todosPage = new TodosPage(driver);
		driver.get("https://todomvc.com/examples/react/dist/#/");
		System.out.println("Todo Appplication");
	}

	@When("^user add a new Todo item - (.*)$")
	public void user_addNewTodo(String task) throws InterruptedException {
		todosPage.enterTask(task);
	}

	@When("^user complete the Todo item - (.*)$")
	public void user_completeTodo(String task) throws InterruptedException {
		todosPage.completeTask(task);
	}

	@When("^user deletes the Todo item - (.*)$")
	public void user_delteTodo(String task) {
		todosPage.deleteTask(task);
		System.out.println("To do item deleted");
	}

	@And("^user clicks on (.*) filter$")
	public void user_clicks_on_filter(String filter) {
		if (filter.equalsIgnoreCase("All")) {
			todosPage.clickAll();
		} else if (filter.equalsIgnoreCase("Active")) {
			todosPage.clickActive();
		} else if (filter.equalsIgnoreCase("Completed")) {
			todosPage.clickCompleted();
		}

	}

	@Then("^user should see the task (.*) in the todo list$")
	public void user_should_see_in_the_todo_list(String task) {
		Assert.assertTrue(todosPage.isTaskPresent(task));
	}

	@Then("^user should see the task (.*) marked as completed")
	public void user_should_see_todo_completed(String task) {
		Assert.assertTrue(todosPage.isTaskCompleted(task));
	}

	@Then("^user should not see the task (.*) marked as deleted")
	public void user_should_see_todo_deleted(String task) {
		Assert.assertTrue(todosPage.isTaskDeleted(task));
	}
	
	@When("^user edit the task (.*) to (.*)$")
	public void user_edit_the_task(String oldTask, String newTask)
	{
		todosPage.editTask(oldTask, newTask);
	}

}
