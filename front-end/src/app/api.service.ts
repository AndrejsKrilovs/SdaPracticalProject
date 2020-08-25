import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private filmURL = `//localhost:8080/api/film.svc`;
  private sessionURL = `//localhost:8080/api/session.svc`;
  constructor(private httpClient: HttpClient) { }
  public filmCollection(): Observable<any> {
    return this.httpClient.get(this.filmURL + `/Films`);
  }
  public oneFilm(id: number): Observable<any> {
    return this.httpClient.get(this.filmURL + `/Film(` + id + `)`);
  }
  public sessionCollection(id: number): Observable<any> {
    return this.httpClient.get(this.sessionURL + `/Sessions(` + id + `)`);
  }
}
