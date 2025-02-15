Feature: Todo Management in todoMVC

  Background: Launch Todo Management
    Given user is on TodoMVC: React application

  Scenario Outline: Add a new todo item
    When user add a new Todo item - <taskName>
    Then user should see the task <taskName> in the todo list

    Examples:
      | taskName    |
      | Read a Book |

  Scenario Outline: Mark a Todo as Completed
    And user add a new Todo item - <taskName>
    When user complete the Todo item - <taskName>
    Then user should see the task <taskName> marked as completed

    Examples:
      | taskName    |
      | Read a Book |

  Scenario Outline: Delete a Todo
    And user add a new Todo item - <taskName>
    When user deletes the Todo item - <taskName>

    #	Then user should not see the task <taskName> marked as deleted
    Examples:
      | taskName    |
      | Read a Book |

  Scenario Outline: Edit a Todo
    And user add a new Todo item - <taskName>
    When user edit the task <taskName> to <newTask>
    Then user should see the task <newTask> in the todo list

    Examples:
      | taskName    | newTask     |
      | Read a Book | Do exercise |

  Scenario Outline: Apply completed filter
    And user add a new Todo item - <taskName>
    And user add a new Todo item - 'Go to Gym'
    And user complete the Todo item - <taskName>
    When user clicks on 'Completed' filter
    Then user should see the task <taskName> marked as completed

    Examples:
      | taskName    |
      | Read a Book |