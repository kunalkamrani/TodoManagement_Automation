Feature: Todo Management in todoMVC

  Background: Launch Todo Management
    Given user is on TodoMVC: React application

  Scenario Outline: Add a new todo item
    When user add a new Todo item - <taskName>
    Then user should see the task <taskName> in the todo list
    And user should see <taskCount> active tasks
    Examples:
      | taskName    | taskCount |
      | Read a Book | 1         |
      | Go for Walk | 1         |

  Scenario Outline: Completed a Todo
    And user add a new Todo item - <taskName>
    When user complete the Todo item - <taskName>
    Then user should see the task <taskName> marked as completed
    And user should see <taskCount> active tasks

    Examples:
      | taskName    | taskCount |
      | Read a Book | 1         |

  Scenario Outline: Delete a Todo
    And user adds multiple tasks
      | Read a Book   |
      | Plan vacation |
    When user deletes the Todo item - <taskName>
    Then user should see <taskCount> active tasks

    Examples:
      | taskName      | taskCount |
      | Plan vacation | 1         |

  Scenario Outline: Edit a Todo task
    And user adds multiple tasks
      | Read a Book   |
      | Plan vacation |
    When user edit the task <taskName> to <newTask>
    Then user should see the task <newTask> in the todo list

    Examples:
      | taskName    | newTask     |
      | Read a Book | Do exercise |

  Scenario Outline: Apply completed filter
    And user adds multiple tasks
      | Read a Book   |
      | Plan vacation |
    And user complete the Todo item - <taskName>
    When user click on 'Completed' filter
    Then user should see the task <taskName> marked as completed
    And user should see <taskCount> active tasks

    Examples:
      | taskName    | taskCount |
      | Read a Book | 1         |

  Scenario Outline: Apply Active filter
    And user adds multiple tasks
      | Go for Walk   |
      | Read a Book   |
      | Plan vacation |
    And user complete the Todo item - <taskName>
    When user click on 'Active' filter
    Then user should not see the task <taskName> marked as deleted
    And user should see <taskCount> active tasks

    Examples:
      | taskName    | taskCount |
      | Read a Book | 2         |

  Scenario Outline: Apply All tasks
    And user adds multiple tasks
      | Go for Walk   |
      | Read a Book   |
      | Plan vacation |
    And user complete the Todo item - <taskName>
    When user click on 'All' filter
    Then user should see the task <taskName> in the todo list
    And user should see <taskCount> active tasks

    Examples:
      | taskName    | taskCount |
      | Read a Book | 2         |


  Scenario Outline: Clear Completed Tasks
    And user adds multiple tasks
      | Go for Walk   |
      | Read a Book   |
      | Plan vacation |
    And user complete the Todo item - <taskName>
    And   user should see the task <taskName> in the todo list
    When user click on clear completed tasks
    Then user should see <taskCount> active tasks

    Examples:
      | taskName    | taskCount |
      | Read a Book | 2         |