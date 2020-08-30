import {Component} from '@angular/core';
import {IFilm} from '../app.component';
import {ApiService} from '../api.service';

@Component({
  selector: 'app-film-list',
  templateUrl: './film-list.component.html',
  styleUrls: ['./film-list.component.css']
})
export class FilmListComponent {
  films: Array<IFilm> = [];
  toggle: boolean = false;
  currentPage: number = 0;

  constructor(private apiService: ApiService) {
    apiService.filmCollection(this.currentPage)
      .subscribe(result => result.forEach(data => this.films.push(data)));
  }

  toggleFilms(): void {
    this.toggle = !this.toggle;
  }

  pageUp() {
    this.films = [];
    this.currentPage > 11 ? 11 : this.currentPage ++;
     this.apiService
       .filmCollection(this.currentPage)
       .subscribe(result => result.forEach(data => this.films.push(data)));
  }

  pageDown() {
    this.films= [];
    this.currentPage < 1 ? 0 : this.currentPage --;
    this.apiService
      .filmCollection(this.currentPage)
      .subscribe(result => result.forEach(data => this.films.push(data)));
     }
}
