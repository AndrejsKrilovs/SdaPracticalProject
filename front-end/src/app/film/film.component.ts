import {Component, Input} from '@angular/core'
import { Router } from '@angular/router'
import { IFilm } from '../app.component'

@Component({
  selector: 'app-film',
  templateUrl: './film.component.html',
  styleUrls: ['./film.component.css']
})
export class FilmComponent {
  @Input() film: IFilm
  @Input() mainPage: boolean
  @Input() buttonValue: string

  constructor(private router: Router) {}

  onSelect(entity: IFilm): void {
    if (this.mainPage) {
      this.router.navigate([`film.svc/Film`, entity.film_id])
    } else {
      this.router.navigate([``])
    }
  }
}
