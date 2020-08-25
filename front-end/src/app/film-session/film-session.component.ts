import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {IFilm, ISeat, ISession} from '../app.component';
import {ApiService} from '../api.service';

@Component({
  selector: 'app-film-session',
  templateUrl: './film-session.component.html',
  styleUrls: ['./film-session.component.css']
})
export class FilmSessionComponent {
  jsonResult: string;
  sessions: Array<ISession> = [];
  selectedSession: ISession = {
    id: 0,
    date_time: null,
    room: 0,
    price: 0,
    film_id: 0
  };
  selectedFilm: IFilm = {
    id: 0,
    length: '',
    picture: '',
    title: ''
  };
  seats: Array<ISeat> = [
    {nr: 1, available: false}, {nr: 2, available: false}, {nr: 3, available: false}, {nr: 4, available: false}, {nr: 5, available: false},
    {nr: 6, available: false}, {nr: 7, available: false}, {nr: 8, available: false}, {nr: 9, available: false}, {nr: 10, available: false},
    {nr: 11, available: false}, {nr: 12, available: false}, {nr: 13, available: false}, {nr: 14, available: false},
    {nr: 15, available: false}, {nr: 16, available: false}, {nr: 17, available: false}, {nr: 18, available: false},
    {nr: 19, available: false}, {nr: 20, available: false}, {nr: 21, available: false}, {nr: 22, available: false},
    {nr: 23, available: false}, {nr: 24, available: false}, {nr: 25, available: false}, {nr: 26, available: false},
    {nr: 27, available: false}, {nr: 28, available: false}, {nr: 29, available: false}, {nr: 30, available: false},
    {nr: 31, available: false}, {nr: 32, available: false}, {nr: 33, available: false}, {nr: 34, available: false},
    {nr: 35, available: false}, {nr: 36, available: false}, {nr: 37, available: false}, {nr: 38, available: false},
    {nr: 39, available: false}, {nr: 40, available: false}, {nr: 41, available: false}, {nr: 42, available: false}
  ];
  selectedSeats: Array<number> = [];
  constructor(private route: ActivatedRoute, private apiService: ApiService) {
    const filmId = Number.parseInt(this.route.snapshot.paramMap.get('id'), 0);
    apiService.oneFilm(filmId).subscribe(result => this.selectedFilm = result);
    apiService.sessionCollection(filmId)
      .subscribe(result => result.forEach(data => this.sessions.push(data)));
  }
  onSessionSelect(session: ISession): void {
    this.selectedSession = session;
    this.jsonResult = JSON.stringify(this.selectedFilm) + `\n` + JSON.stringify(this.selectedSession);
  }
  onSeatSelect(event: any, seat: ISeat): void {
    const selectedNumber: number = Number.parseInt(event.target.text, 0);
    const ind: number = this.selectedSeats.indexOf(selectedNumber);
    if (ind !== -1) {
      this.selectedSeats.splice(ind, 1);
    } else {
      this.selectedSeats.push(selectedNumber);
    }
    seat.available = !seat.available;
  }
}
