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
  nr: number;
  available: boolean;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-end';
}
