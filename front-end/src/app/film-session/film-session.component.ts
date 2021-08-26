import { Component } from '@angular/core'
import { ActivatedRoute, Router } from '@angular/router'
import { IFilm,  IOrder,  IPlace, ISession } from '../app.component'
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

  order_button_disabled = true

  selected_film: IFilm = {
    film_id: 0,
    film_title: ``,
    film_picture: ``,
    film_length: ``
  }

  session_selected: ISession = {
    session_id: -1,
    date_time: ``,
    session_room: -1,
    session_price: -1,
    price_currency: ``
  }

  authorized_person = {
    name_surname: ``,
    personal_code: ``
  }

  order: IOrder

  constructor(private route: ActivatedRoute, private apiService: ApiService) {
    let filmIdentifier: number = Number.parseInt(this.route.snapshot.paramMap.get(`id`), 0)
    apiService.oneFilm(filmIdentifier).subscribe(result => this.selected_film = result.content.pop())
    apiService.sessionCollection(filmIdentifier, this.session_page_number).subscribe(result => {
      this.sessions = result.content
      this.session_page_number = result.metadata.page_number
      this.session_total_pages = result.metadata.total_pages
    })
  }

  pageUp(): void {
    this.session_selected = {
      session_id: -1,
      date_time: ``,
      session_room: -1,
      session_price: -1,
      price_currency: ``
    }

    this.selected_places.splice(0)
    this.session_page_number > this.session_total_pages - 1 ? this.session_total_pages : this.session_page_number++
    this.apiService.sessionCollection(this.selected_film.film_id, this.session_page_number).subscribe(result => {
      this.sessions = result.content
      this.session_page_number = result.metadata.page_number
    })
  }

  pageDown(): void {
    this.session_selected = {
      session_id: -1,
      date_time: ``,
      session_room: -1,
      session_price: -1,
      price_currency: ``
    }

    this.selected_places.splice(0)
    this.session_page_number <= 0 ? this.session_page_number = 0 : this.session_page_number--
    this.apiService.sessionCollection(this.selected_film.film_id, this.session_page_number).subscribe(result => {
      this.sessions = result.content
      this.session_page_number = result.metadata.page_number
    })
  }

  onSessionSelect(session: ISession): void {
    this.places.splice(0)
    this.selected_places.splice(0)
    this.session_selected.session_id = session.session_id

    Array.from(Array(30).keys()).map(place => { 
      return {
        room_number: session.session_room,
        place_availability: false,
        place_enable: false,
        place_seat: place
      }
    }).forEach(item => this.places.push(item))
  }

  onPlaceSelect(place: IPlace): void {
    if (!place.place_enable) {
      place.place_availability = !place.place_availability
      const ind: number = this.selected_places.indexOf(place)

      if (ind > -1)
        this.selected_places.splice(ind, 1)
      else
        this.selected_places.push(place)
    }
  }

  userCodeInput(event: any): void {
    const correctUserCode: RegExp = /[0-9]{6}-[0-9]{5}/
    if (correctUserCode.test(event.target.value))
      this.authorized_person.personal_code = event.target.value;

    this.order_button_disabled = !(this.authorized_person.name_surname.length > 0 &&
      this.authorized_person.personal_code.length === 12)
  }

  userNameInput(event: any): void {
    const correctUserName: RegExp = /[A-Z\u0410-\u042F]{1}[a-z\u0430-\u044F]{1,}\s[A-Z\u0410-\u042F]{1}[a-z\u0430-\u044F]{1,}/
    if (correctUserName.test(event.target.value))
      this.authorized_person.name_surname = event.target.value;

    this.order_button_disabled = !(this.authorized_person.name_surname.length > 0 &&
      this.authorized_person.personal_code.length === 12)
  }

  makeOrder(): void {
    const atributes = {
      film: this.selected_film.film_id,
      session: this.session_selected.session_id,
      places: this.selected_places.map(place => place.place_seat)
    }

    this.apiService.generatePreOrderData(atributes)
      .subscribe(result => this.order = result)
  }
}