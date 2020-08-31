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
  seats: Array<ISeat> = [];

  currentPage = 0;
  selectedSeats = 0;

  selectedSession: ISession = {
    id: 0,
    date_time: '',
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

  constructor(private route: ActivatedRoute, private apiService: ApiService) {
    this.selectedFilm.id = Number.parseInt(this.route.snapshot.paramMap.get('id'), 0);
    apiService.oneFilm(this.selectedFilm.id)
      .subscribe(result => this.selectedFilm = result);

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
    this.seats.splice(0);
    this.seats.forEach(s => s.available = false);
    this.selectedSession = session;

    this.apiService.showSeats(this.selectedSession.room)
      .subscribe(result => result.forEach(data => this.seats.push(data)));
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
    this.currentPage > 0 ? this.currentPage = 1 : this.currentPage ++ ;
    this.apiService.sessionCollection(this.selectedFilm.id, this.currentPage)
      .subscribe(result => result.forEach(data => this.sessions.push(data)));
  }

  pageDown() {
    this.init();
    this.currentPage <= 0 ? this.currentPage = 0 : this.currentPage -- ;
    this.apiService.sessionCollection(this.selectedFilm.id, this.currentPage)
      .subscribe(result => result.forEach(data => this.sessions.push(data)));
  }
}
