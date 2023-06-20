import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { TodoService } from '../service/todo-service.service';

@Component({
  selector: 'app-todo-dialog',
  templateUrl: './todo-dialog.component.html',
  styleUrls: ['./todo-dialog.component.css']
})
export class TodoDialogComponent implements OnInit {

  title : string;
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<TodoDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data,
    private todoService: TodoService) {

      this.form = fb.group({
        title: [this.title, Validators.required]
    });
    }

  ngOnInit() {
  }

/**
 * The "close" function closes the current dialog window.
 */
  close() {
    this.dialogRef.close();
  }

/**
 * The function saves a new todo item and closes the dialog box.
 */
  save() {
    this.title = this.form.get('title').value;
    if (this.title) {
      this.todoService.saveNewTodo(this.title).subscribe(

        response => {
          console.log(response)
        }
      )
    }
    this.dialogRef.close();
    window.location.reload();
  }

}
