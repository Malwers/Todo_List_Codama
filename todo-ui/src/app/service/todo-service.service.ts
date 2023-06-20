import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Todo } from '../model/todo/todo';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  private todoAppUrl = environment.todoAppUrl

  constructor(private http: HttpClient) { }

/**
 * This function retrieves all todos from a specified URL using HTTP GET request and returns an
 * observable of type Todo array.
 * @returns An Observable of an array of Todo objects is being returned. The data is retrieved from the
 * specified URL using an HTTP GET request.
 */
  retrieveAllTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(this.todoAppUrl + '/todos/');
  }

/**
 * This function retrieves a Todo object by its ID through an HTTP GET request.
 * @param {String} id - The `id` parameter is a string representing the unique identifier of a specific
 * todo item that needs to be retrieved.
 * @returns An Observable of type Todo is being returned. The method retrieves a Todo object from the
 * server by making an HTTP GET request to the specified URL.
 */
  retrieveTodoById(id: String): Observable<Todo> {
    return this.http.get<Todo>(this.todoAppUrl + '/todos/' + id);
  }

/**
 * This function saves a new todo item with a given title to a server using HTTP POST request.
 * @param {string} title - A string representing the title of the new todo item that needs to be saved.
 * @returns An Observable of type string is being returned.
 */
  saveNewTodo(title: string): Observable<string> {
    let headers = new HttpHeaders({'Content-Type': 'application/json' });
    let options = { headers: headers };
    let jsonObject = this.prepareTitleJsonObject(title);
    return this.http.post<string>(
      this.todoAppUrl + '/todos/',
      jsonObject,
      options
    );
  }

/**
 * This function sends an HTTP DELETE request to delete a todo item with a specific ID and returns an
 * Observable that emits a string.
 * @param {number} id - The id parameter is a number that represents the unique identifier of a todo
 * item that needs to be deleted.
 * @returns An Observable of type string is being returned.
 */
  deleteTodo(id: number): Observable<string> {
    return this.http.delete<string>(
      this.todoAppUrl + '/todos/' + id,
    );
  }

/**
 * This function prepares a JSON object with a given title and returns it as a string.
 * @param {string} title - The `title` parameter is a string that represents the title of an object.
 * This function takes this title and creates a JSON object with a key of "title" and a value of the
 * provided title. The resulting JSON object is then returned as a string.
 * @returns A JSON string representation of an object with a single property "title" whose value is the
 * input string "title".
 */
  private prepareTitleJsonObject(title: string) {
    const object = {
      title: title
    }
    return JSON.stringify(object);
  }

}
