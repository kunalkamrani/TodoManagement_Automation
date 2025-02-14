package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class TodosPage {

	private WebDriver driver;

	private By txt_todo = By.id("todo-input");
	private By lst_todoList = By.xpath("//*[@class='todo-list']/li");
	private By lst_completedTasks = By.xpath("//*[@class='todo-list']/li[contains(@class,'completed')]");
	private By lst_openTasks = By.xpath("//*[@class='todo-list']/li[not(contains(@class,'completed'))]");
	private By btn_all = By.xpath("//a[text()='All']");
	private By btn_active = By.xpath("//a[text()='Active']");
	private By btn_completed = By.xpath("//a[text()='Completed']");
	private By btn_clearCompleted = By.xpath("//button[text()='Clear completed']");

	// Dynamic
	private By getTaskByName(String taskName) {
		return By.xpath("//ul[@class='todo-list']/li/div/label[text()='" + taskName + "']");
	}

	private By getCompletedTask(String taskName) {
		return By.xpath(
				"//ul[@class='todo-list']/li[contains(@class, 'completed')]/div/label[text()='" + taskName + "']");
	}

	private By getTaskCheckbox(String taskName) {
		return By.xpath("//ul[@class='todo-list']/li/div/label[text()='" + taskName
				+ "']/preceding-sibling::input[@class='toggle']");
	}

	private By getTaskDeleteButton(String taskName) {
		return By.xpath("//ul[@class='todo-list']/li/div/label[text()='" + taskName
				+ "']/following-sibling::*[@class='destroy']");
	}

	private By getEditTask(String taskName) {
		return By.xpath("//ul[@class='todo-list']/li[div/label[text()='" + taskName + "']]/input[@class='edit']");
	}

	public TodosPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterTask(String todoTask) throws InterruptedException {
		WebElement todoBox = driver.findElement(txt_todo);
		todoBox.sendKeys(todoTask);
		Thread.sleep(2000);
		todoBox.sendKeys(Keys.ENTER);
	}

	public List<WebElement> getAllTasks() {
		return driver.findElements(lst_todoList);
	}

	public void completeTask(String taskName) {
		WebElement checkbox = driver.findElement(getTaskCheckbox(taskName));
		if (!checkbox.isSelected()) {
			checkbox.click();
		}
	}

	public void deleteTask(String taskName) {
		WebElement task = driver.findElement(getTaskByName(taskName));
		WebElement deleteBtn = driver.findElement(getTaskDeleteButton(taskName));
		Actions actions = new Actions(driver);
		actions.moveToElement(task).perform();
		deleteBtn.click();
	}

	public boolean isTaskCompleted(String taskName) {
		return !driver.findElements(getCompletedTask(taskName)).isEmpty();
	}

	public int getCompletedTaskCount() {
		return driver.findElements(lst_completedTasks).size();
	}

	public int getOpenTaskCount() {
		return driver.findElements(lst_openTasks).size();
	}

	public void clearCompltedTasks() {
		driver.findElement(btn_clearCompleted).click();
	}

	public boolean isTaskPresent(String taskName) {
		return !driver.findElements(getTaskByName(taskName)).isEmpty();
	}

	public boolean isTaskDeleted(String taskName) {
		return driver.findElements(getTaskByName(taskName)).isEmpty();
	}

	public void clickAll() {
		WebElement allBtn = driver.findElement(btn_all);
		allBtn.isDisplayed();
		allBtn.click();
	}

	public void clickActive() {
		WebElement activeBtn = driver.findElement(btn_active);
		activeBtn.isDisplayed();
		activeBtn.click();
	}

	public void clickCompleted() {
		WebElement completedBtn = driver.findElement(btn_completed);
		completedBtn.isDisplayed();
		completedBtn.click();
	}

	public void clickClearCompleted() {
		WebElement clearCompletedBtn = driver.findElement(btn_clearCompleted);
		clearCompletedBtn.isDisplayed();
		clearCompletedBtn.click();
	}

	public void editTask(String oldTask, String newTask) {
		WebElement task = driver.findElement(getTaskByName(oldTask));
		Actions actions = new Actions(driver);
		actions.doubleClick(task).perform();
		task.clear();
		task.sendKeys(newTask + "\n");
	}
}
