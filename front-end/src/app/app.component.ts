import { Component } from '@angular/core'

export interface IMetadata {
  offset: number
  page_number: number
  total_pages: number
  total_elements: number
}

export interface IFilm {
  film_id: number
  film_title: string
  film_picture: string
  film_length: string
}

export interface IFilmResponse {
  metadata: IMetadata
  content: Array<IFilm>
}

// export interface ISession {
//   id: number;
//   date_time: string;
//   room: number;
//   price: number;
//   film_id: number;
// }

// export interface ISeat {
//   room_number: number;
//   place_number: number;
//   available: boolean;
//   enabled: boolean;
// }

// export interface IOrder {
//   name_surname: string;
//   personal_code: string
//   session: number;
//   places: string;
//   generation_time: string;
//   total_price: number;
// }

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-end';
}
