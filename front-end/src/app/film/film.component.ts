import {Component, Input} from '@angular/core';
import {IFilm} from '../app.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-film',
  templateUrl: './film.component.html',
  styleUrls: ['./film.component.css']
})
export class FilmComponent {
  @Input() film: IFilm;
  @Input() mainPage: boolean;
  @Input() buttonValue: string;

  constructor(private router: Router) {}

  onSelect(entity: IFilm): void {
    if (this.mainPage) {
      this.router
        .navigate([`film.svc/Film`, entity.id])
        .then(() => console.log());
    } else {
      this.router
        .navigate([``])
        .then(() => console.log());
    }
  }
}
