import { Component, OnInit } from '@angular/core';
import { Todo } from '../model/todo/todo';
import { TodoService } from '../service/todo-service.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { TodoDialogComponent } from '../todo-dialog/todo-dialog.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  todoList: Todo[];

  constructor(
    private todoService: TodoService,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.retrieveAllTodos();
  }

/**
 * This function opens a dialog box for creating a new Todo item.
 */
  openDialogForNewTodo(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      todo: new Todo()
    };
    this.dialog.open(TodoDialogComponent, dialogConfig)
  }


/**
 * This function deletes a todo item by calling a service and reloading the page.
 * @param {number} id - The `id` parameter is a number that represents the unique identifier of a todo
 * item that needs to be deleted. This function calls the `deleteTodo` method of a `todoService` object
 * to delete the todo item with the specified `id`. Once the deletion is successful, the function logs
 * the
 */
  deleteTodo(id: number) {
      this.todoService.deleteTodo(id).subscribe(

        response => {
          console.log(response)
        }
      )
    window.location.reload();
  }

/**
 * This function retrieves all todos from a todo service and assigns them to a todo list.
 */
  private retrieveAllTodos(): void {
    this.todoService.retrieveAllTodos().subscribe(

      response => {
        this.todoList = response;
      }
    )
  }

}
