import {Component} from '@angular/core';
import {IFilm} from '../app.component';
import {ApiService} from '../api.service';

@Component({
  selector: 'app-film-list',
  templateUrl: './film-list.component.html',
  styleUrls: ['./film-list.component.css']
})
export class FilmListComponent {
  toggle = false;
  films: Array<IFilm> = [];
  constructor(private apiService: ApiService) {
    apiService.filmCollection()
      .subscribe(result => result.forEach(data => this.films.push(data)));
  }

  toggleFilms(): void {
    this.toggle = !this.toggle;
  }
}
