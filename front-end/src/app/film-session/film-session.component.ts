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
  sessions: Array<ISession> = [];
  currentPage: number = 0;
  selectedSeats: number = 0;

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


  constructor(private route: ActivatedRoute, private apiService: ApiService) {
    this.selectedFilm.id = Number.parseInt(this.route.snapshot.paramMap.get('id'), 0);
    apiService.oneFilm(this.selectedFilm.id).subscribe(result => this.selectedFilm = result);
    apiService.sessionCollection(this.selectedFilm.id, this.currentPage)
      .subscribe(result => result.forEach(data => this.sessions.push(data)));
  }

  private init(): void {
      this.sessions.splice(0);
      this.selectedSession.id = 0;
      this.selectedSeats = 0;
  }

  onSessionSelect(session: ISession): void {
    this.selectedSeats = 0;
    this.seats.forEach(s => s.available = false);
    this.selectedSession = session;
  }

  onSeatSelect(event: any, seat: ISeat): void {
    const selectedNumber: number = Number.parseInt(event.target.text, 0);
    this.seats.forEach(_seat => {
      if(_seat == seat) {
        _seat.available = !_seat.available;
        _seat.available ? this.selectedSeats++ : this.selectedSeats--;
      }
    });
  }

  pageUp() {
    this.init();
    this.currentPage > 0 ? 0 : this.currentPage ++ ;
    this.apiService.sessionCollection(this.selectedFilm.id, this.currentPage)
      .subscribe(result => result.forEach(data => this.sessions.push(data)));
  }

  pageDown() {
    this.init();
    this.currentPage <= 0 ? 0 : this.currentPage -- ;
    this.apiService.sessionCollection(this.selectedFilm.id, this.currentPage)
      .subscribe(result => result.forEach(data => this.sessions.push(data)));
  }
}
