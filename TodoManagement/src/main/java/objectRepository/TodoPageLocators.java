package objectRepository;

import org.openqa.selenium.By;

public class TodoPageLocators {

    public static By txt_todo = By.id("todo-input");
    public static By lst_todoList = By.xpath("//*[@class='todo-list']/li");
    public static By lst_completedTasks = By.xpath("//*[@class='todo-list']/li[contains(@class,'completed')]");
    public static By lst_openTasks = By.xpath("//*[@class='todo-list']/li[not(contains(@class,'completed'))]");
    public static By btn_all = By.xpath("//a[text()='All']");
    public static By btn_active = By.xpath("//a[text()='Active']");
    public static By btn_completed = By.xpath("//a[text()='Completed']");
    public static By btn_clearCompleted = By.xpath("//button[text()='Clear completed']");
    public static By lbl_activeCounts = By.xpath("//*[@class='todo-count']");

    // Dynamic
    public static By getTaskByName(String taskName) {
        return By.xpath("//ul[@class='todo-list']/li/div/label[text()='" + taskName + "']");
    }

    public static By getCompletedTask(String taskName) {
        return By.xpath(
                "//ul[@class='todo-list']/li[contains(@class, 'completed')]/div/label[text()='" + taskName + "']");
    }

    public static By getTaskCheckbox(String taskName) {
        return By.xpath("//ul[@class='todo-list']/li/div/label[text()='" + taskName
                + "']/preceding-sibling::input[@class='toggle']");
    }

    public static By getTaskDeleteButton(String taskName) {
        return By.xpath("//ul[@class='todo-list']/li/div/label[text()='" + taskName
                + "']/following-sibling::*[@class='destroy']");
    }

    public static By getEditTask(String taskName) {
        return By.xpath("//ul[@class='todo-list']/li[div/label[text()='" + taskName + "']]/input[@class='edit']");
    }
}
