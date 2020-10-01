import { Component } from '@angular/core';

export interface IFilm {
  id: number;
  title: string;
  picture: string;
  length: string;
}

export interface ISession {
  id: number;
  date_time: string;
  room: number;
  price: number;
  film_id: number;
}

export interface ISeat {
  room_number: number;
  place_number: number;
  available: boolean;
  enabled: boolean;
}

export interface IOrder {
  name_surname: string;
  personal_code: string
  session: number;
  places: string;
  generation_time: string;
  total_price: number;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-end';
}
