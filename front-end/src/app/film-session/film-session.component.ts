import { Component } from '@angular/core'
import { ActivatedRoute, Router } from '@angular/router'
import { IFilm, IPerson, IPlace, ISession } from '../app.component'
import { ApiService } from '../api.service'

@Component({
  selector: 'app-film-session',
  templateUrl: './film-session.component.html',
  styleUrls: ['./film-session.component.css']
})
export class FilmSessionComponent {
  sessions: Array<ISession> = []
  places: Array<IPlace> = []
  selectedPlaces: Array<IPlace> = []

  session_page_number: number = 0
  session_total_pages: number = 0

  sessionSelected: boolean = false
  orderButtonDisabled = true

  selectedFilm: IFilm = {
    film_id: 0,
    film_title: ``,
    film_picture: ``,
    film_length: ``
  }

  authorizedPerson: IPerson = {
    nameSurname: ``,
    personalCode: ``
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
    this.sessionSelected = false
    this.session_page_number > this.session_total_pages - 1 ? this.session_total_pages : this.session_page_number++
    this.apiService.sessionCollection(this.selectedFilm.film_id, this.session_page_number).subscribe(result => {
      this.sessions = result.content
      this.session_page_number = result.metadata.page_number
    })
  }

  pageDown(): void {
    this.sessionSelected = false
    this.session_page_number <= 0 ? this.session_page_number = 0 : this.session_page_number--
    this.apiService.sessionCollection(this.selectedFilm.film_id, this.session_page_number).subscribe(result => {
      this.sessions = result.content
      this.session_page_number = result.metadata.page_number
    })
  }

  onSessionSelect(session: ISession): void {
    this.sessionSelected = true
    this.selectedPlaces.splice(0)
    this.apiService.showSeats(session.session_id).subscribe(result => this.places = result.content)
  }

  onPlaceSelect(place: IPlace): void {
    if (place.enable) {
      place.place_availability = !place.place_availability
      const ind: number = this.selectedPlaces.indexOf(place)

      if (ind > -1)
        this.selectedPlaces.splice(ind, 1)
      else
        this.selectedPlaces.push(place)

      console.log(this.selectedPlaces)
    }
  }

  userCodeInput(event: any): void {
    const correctUserCode: RegExp = /[0-9]{6}-[0-9]{5}/
    if (correctUserCode.test(event.target.value))
      this.authorizedPerson.personalCode = event.target.value;

    this.orderButtonDisabled = !(this.authorizedPerson.nameSurname.length > 0 &&
      this.authorizedPerson.personalCode.length === 12)
  }

  userNameInput(event: any): void {
    const correctUserName: RegExp = /[A-Z\u0410-\u042F]{1}[a-z\u0430-\u044F]{1,}\s[A-Z\u0410-\u042F]{1}[a-z\u0430-\u044F]{1,}/
    if (correctUserName.test(event.target.value))
      this.authorizedPerson.nameSurname = event.target.value;

    this.orderButtonDisabled = !(this.authorizedPerson.nameSurname.length > 0 &&
      this.authorizedPerson.personalCode.length === 12)
  }
}