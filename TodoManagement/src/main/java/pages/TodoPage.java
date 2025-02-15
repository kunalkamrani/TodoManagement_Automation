package pages;

import objectRepository.TodoPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TodoPage {

    private final WebDriver driver;
    private WebDriverWait wait;

    public TodoPage(WebDriver driver) {
        this.driver = driver;
    }


    public void enterTask(String todoTask) throws InterruptedException {
        WebElement todoBox = driver.findElement(TodoPageLocators.txt_todo);
        todoBox.sendKeys(todoTask);
        Thread.sleep(2000);
        todoBox.sendKeys(Keys.ENTER);
    }

    public List<WebElement> getAllTasks() {
        return driver.findElements(TodoPageLocators.lst_todoList);
    }

    public void completeTask(String taskName) {
        WebElement checkbox = driver.findElement(TodoPageLocators.getTaskCheckbox(taskName));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void deleteTask(String taskName) {
        WebElement task = driver.findElement(TodoPageLocators.getTaskByName(taskName));
        WebElement deleteBtn = driver.findElement(TodoPageLocators.getTaskDeleteButton(taskName));
        Actions actions = new Actions(driver);
        actions.moveToElement(task).perform();
        deleteBtn.click();
    }

    public boolean isTaskCompleted(String taskName) {
        return !driver.findElements(TodoPageLocators.getCompletedTask(taskName)).isEmpty();
    }

    public int getCompletedTaskCount() {
        return driver.findElements(TodoPageLocators.lst_completedTasks).size();
    }

    public int getOpenTaskCount() {
        return driver.findElements(TodoPageLocators.lst_openTasks).size();
    }

    public void clearCompltedTasks() {
        driver.findElement(TodoPageLocators.btn_clearCompleted).click();
    }

    public boolean isTaskPresent(String taskName) {
        return !driver.findElements(TodoPageLocators.getTaskByName(taskName)).isEmpty();
    }

    public boolean isTaskDeleted(String taskName) {
        return driver.findElements(TodoPageLocators.getTaskByName(taskName)).isEmpty();
    }

    public void clickAll() {
        WebElement allBtn = driver.findElement(TodoPageLocators.btn_all);
        allBtn.isDisplayed();
        allBtn.click();
    }

    public void clickActive() {
        WebElement activeBtn = driver.findElement(TodoPageLocators.btn_active);
        activeBtn.isDisplayed();
        activeBtn.click();
    }

    public void clickCompleted() {
        WebElement completedBtn = driver.findElement(TodoPageLocators.btn_completed);
        completedBtn.isDisplayed();
        completedBtn.click();
    }

    public void clickClearCompleted() {
        WebElement clearCompletedBtn = driver.findElement(TodoPageLocators.btn_clearCompleted);
        clearCompletedBtn.isDisplayed();
        clearCompletedBtn.click();
    }

    public void editTask(String oldTask, String newTask) {
        for(WebElement task : getAllTasks()){
            if (task.getText().equalsIgnoreCase(oldTask))
            {
                Actions actions = new Actions(driver);
                actions.doubleClick(task).perform();
                WebElement editInput = task.findElement(By.xpath("//input"));
                editInput.clear();
                editInput.sendKeys(newTask + "\n");
            }
        }

    }

    public int getActiveTaskCount() {
        return Integer.parseInt(driver.findElement(TodoPageLocators.lbl_activeCounts).getText().split(" ")[0]);
    }

    public void addMultipleTasks(List<String> tasks) throws InterruptedException {
        for (String task : tasks) {
            enterTask(task);
        }
    }
}

