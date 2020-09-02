import {Component} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {IFilm, ISeat, ISession} from '../app.component';
import {ApiService} from '../api.service';
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-film-session',
  templateUrl: './film-session.component.html',
  styleUrls: ['./film-session.component.css']
})
export class FilmSessionComponent {
  sessions: Array<ISession> = [];
  seats: Array<ISeat> = [];

  selectedSeats: number = 0;
  currentPage: number = 0;

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

  order: any = {
    generationTime: '',
    film: null,
    session: 0,
    places: [],
    totalPrice: 0
  };

  constructor(private route: ActivatedRoute, private apiService: ApiService, public datePipe: DatePipe) {
    this.selectedFilm.id = Number.parseInt(this.route.snapshot.paramMap.get('id'), 0);
    apiService.oneFilm(this.selectedFilm.id)
      .subscribe(result => this.selectedFilm = result);

    apiService.sessionCollection(this.selectedFilm.id, this.currentPage)
      .subscribe(result => result.forEach(data => this.sessions.push(data)));
  }

  private init(): void {
      this.sessions.splice(0);
      this.selectedSession.id = 0;
  }

  onSessionSelect(session: ISession): void {
    this.seats.splice(0);
    this.seats.forEach(s => s.available = false);
    this.selectedSession = session;

    this.apiService.showSeats(this.selectedSession.room)
      .subscribe(result =>
        result.forEach(data => this.seats.push(data))
      );
  }

  onSeatSelect(event: any, seat: ISeat): void {
    const selectedNumber: number = Number.parseInt(event.target.text, 0);
    this.seats.forEach(_seat => {
        if(_seat == seat) {
          _seat.available = !_seat.available;
          seat = _seat;

          if(seat.available) {
            ++ this.selectedSeats;
            this.order.places.push(seat);
          } else if(!seat.available) {
            const index = this.order.places.indexOf(seat);
            this.order.places.splice(index, 1);
            -- this.selectedSeats;
          }
        }
      });
  }

  pageUp(): void {
    this.init();
    this.currentPage > 0 ? this.currentPage = 1 : this.currentPage ++ ;
    this.apiService.sessionCollection(this.selectedFilm.id, this.currentPage)
      .subscribe(result => result.forEach(data => this.sessions.push(data)));
  }

  pageDown(): void {
    this.init();
    this.currentPage <= 0 ? this.currentPage = 0 : this.currentPage -- ;
    this.apiService.sessionCollection(this.selectedFilm.id, this.currentPage)
      .subscribe(result => result.forEach(data => this.sessions.push(data)));
  }

  makeOrder(): void {
    this.order.generationTime = this.datePipe
      .transform(new Date(), 'dd.MM.yyyy hh:mm:ss');

    this.order.session = this.selectedSession;
    this.order.film = this.selectedFilm;
    this.order.totalPrice = this.selectedSeats * this.selectedSession.price;

    this.order.places
      .forEach(element => {
        this.apiService.updateSeats(element).subscribe()
      });
  }
}
