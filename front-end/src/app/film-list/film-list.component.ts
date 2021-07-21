import { Component } from '@angular/core'
import { ApiService } from '../api.service'
import { IFilm, IMetadata } from '../app.component'

@Component({
  selector: 'app-film-list',
  templateUrl: './film-list.component.html',
  styleUrls: ['./film-list.component.css']
})
export class FilmListComponent {
  page_number: number = 0
  total_pages: number = 0
  films: Array<IFilm> = []

  constructor(private apiService: ApiService) {
    apiService.filmCollection(this.page_number).subscribe(result => {
      this.films = result.content
      this.page_number = result.metadata.page_number
      this.total_pages = result.metadata.total_pages
    })
  }

  pageUp(): void {
    this.films.splice(0)
    this.page_number > this.total_pages - 1 ? this.total_pages : this.page_number++
    this.apiService.filmCollection(this.page_number).subscribe(result => this.films = result.content)
  }

  pageDown(): void {
    this.films.splice(0)
    this.page_number <= 0 ? this.page_number = 0 : this.page_number--
    this.apiService.filmCollection(this.page_number).subscribe(result => this.films = result.content)
  }
}
