import { Component } from '@angular/core'
import { ActivatedRoute, Router } from '@angular/router'
import { IFilm, IPlace, ISession } from '../app.component'
import { ApiService } from '../api.service'

@Component({
  selector: 'app-film-session',
  templateUrl: './film-session.component.html',
  styleUrls: ['./film-session.component.css']
})
export class FilmSessionComponent {
  sessions: Array<ISession> = []
  places: Array<IPlace> = []
  selected_places: Array<IPlace> = []
  session_page_number: number = 0
  session_total_pages: number = 0
  sessionSelected: boolean = false

  selectedFilm: IFilm = {
    film_id: 0,
    film_title: ``,
    film_picture: ``,
    film_length: ``
  }

  constructor(private route: ActivatedRoute, private apiService: ApiService) {
    let filmIdentifier: number = Number.parseInt(this.route.snapshot.paramMap.get(`id`), 0)
    apiService.oneFilm(filmIdentifier).subscribe(result => this.selectedFilm = result.content.pop())
    apiService.sessionCollection(filmIdentifier, this.session_page_number).subscribe(result => {
      this.sessions = result.content
      this.session_page_number = result.metadata.page_number
      this.session_total_pages = result.metadata.total_pages
    })
  }

  pageUp(): void {
    this.session_page_number > this.session_total_pages - 1 ? this.session_total_pages : this.session_page_number++
    this.apiService.sessionCollection(this.selectedFilm.film_id, this.session_page_number).subscribe(result => {
      this.sessions = result.content
      this.session_page_number = result.metadata.page_number
    })
  }

  pageDown(): void {
    this.session_page_number <= 0 ? this.session_page_number = 0 : this.session_page_number--
    this.apiService.sessionCollection(this.selectedFilm.film_id, this.session_page_number).subscribe(result => {
      this.sessions = result.content
      this.session_page_number = result.metadata.page_number
    })
  }

  onSessionSelect(session: ISession): void {
    this.apiService.showSeats(session.session_id).subscribe(result => this.places = result.content)
    this.sessionSelected = true
  }

  onPlaceSelect(place: IPlace): void {
    if (place.enable)
      place.place_availability = !place.place_availability
    if (place.enable && place.place_availability)
      this.selected_places.push(place)
    if (place.enable && !place.place_availability) {
      const ind = this.selected_places.indexOf(place)
      this.selected_places.splice(ind, 1)
    }
  }
}