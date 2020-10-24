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
    this.films.splice(0);
    apiService.filmCollection(this.currentPage)
      .subscribe(result => result.forEach(data => this.films.push(data)));
  }

  toggleFilms(): void {
    this.toggle = !this.toggle;
  }

  pageUp() {
    this.films.splice(0);
    this.currentPage > 11 ? 11 : this.currentPage ++;
     this.apiService
       .filmCollection(this.currentPage)
       .subscribe(result => result.forEach(data => this.films.push(data)));
  }

  pageDown() {
    this.films.splice(0);
    this.currentPage < 1 ? 0 : this.currentPage --;
    this.apiService
      .filmCollection(this.currentPage)
      .subscribe(result => result.forEach(data => this.films.push(data)));
     }

  filterInput(event: any): void {
    this.currentPage = 0;
    console.log(event.target.value);
    // this.films.slice(0);
    // this.apiService
    //   .filterFilm(event.target.value, this.currentPage)
    //   .subscribe(result => result.forEach(data => console.log(data)));
  }
}
