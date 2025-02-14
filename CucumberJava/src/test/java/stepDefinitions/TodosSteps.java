package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;
import pages.TodosPage;
import utils.BaseTest;

public class TodosSteps {
	WebDriver driver;
	TodosPage todosPage;
	
	public TodosSteps()
	{this.driver=BaseTest.getDriver();}

	@Given("user is on Todos application")
	public void user_is_on_todo_page() {
		todosPage = new TodosPage(driver);
		driver.get("https://todomvc.com/examples/react/dist/#/");
		System.out.println("Todo Appplication");
	}

	@When("^user add a new Todo item - (.*)$")
	public void user_addNewTodo(String task) throws InterruptedException  {
		todosPage.enterTask(task);
	}
	@When("^user complete the Todo item - (.*)$")
	public void user_completeTodo(String task) throws InterruptedException
	{
		todosPage.completeTask(task);
		Thread.sleep(5000);
		System.out.println("--------" + task + " Completed Task---------");
	}
	
	@When("^user delete the Todo item - (.*)$")
	public void user_delteTodo(String task)
	{
		todosPage.deleteTask(task);
		System.out.println("To do item deleted");
	}
	
	
	@And("^user clicks on (.*) filter$")
	public void user_clicks_on_filter(String filter) {
		if(filter.equalsIgnoreCase("All"))
		{
		todosPage.clickAll();
		}
		else if (filter.equalsIgnoreCase("Active"))
		{
			todosPage.clickActive();
		}
		else if (filter.equalsIgnoreCase("Completed"))
		{
			todosPage.clickCompleted();
		}
		
	}
	
	@Then("^user should see (.*) in the todo list$")
	public void user_should_see_in_the_todo_list(String task) {
	//todosPage.selectTodoList(task);
	}
	




}
