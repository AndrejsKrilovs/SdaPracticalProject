import { Component } from '@angular/core'

export interface IMetadata {
  offset: number
  page_number: number
  total_pages: number
  total_elements: number
}

export interface IResponse<T> {
  metadata: IMetadata
  content: Array<T>
}

export interface IFilm {
  film_id: number
  film_title: string
  film_picture: string
  film_length: string
}

export interface ISession {
  session_id: number
  date_time: string
  session_room: number
  session_price: number
  price_currency: string
}

export interface IPlace {
  room_number: number
  place_seat: number
  place_availability: boolean
  place_enable: boolean
}

export interface IOrder {
  name_surname: string
  personal_code: string
  film_name: string
  session_date: string
  room_number: number
  total_price: number
  price_currency: string
  places: string
  order_generated_date: string
}

export interface IFilmResponse extends IResponse<IFilm> { }
export interface ISessionResponse extends IResponse<ISession> { }
export interface IPlaceResponse extends IResponse<IPlace> { }

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-end';
}
