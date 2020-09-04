import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {IFilm, ISeat, ISession, IOrder} from '../app.component';
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

//   order: IOrder = {
//     generationTime: '',
//     filmName: null,
//     sessionDate: null,
//     places: [],
//     totalPrice: 0
//   };

  constructor(
    private route: ActivatedRoute,
    private apiService: ApiService,
    private datePipe: DatePipe,
    private router: Router
  ) {
    this.selectedFilm.id = Number.parseInt(this.route.snapshot.paramMap.get('id'), 0);
    apiService.oneFilm(this.selectedFilm.id)
      .subscribe(result => this.selectedFilm = result);

    apiService.sessionCollection(this.selectedFilm.id, this.currentPage)
      .subscribe(result => result.forEach(data => this.sessions.push(data)));
  }

  private init(): void {
      this.selectedSeats = 0;
      this.sessions.splice(0);
   //   this.order.places.splice(0);
      this.selectedSession.id = 0;
  }

  onSessionSelect(session: ISession): void {
    this.selectedSeats = 0;
    this.seats.splice(0);
    //this.order.places.splice(0);
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
        if(_seat == seat && !_seat.enabled) {
          _seat.available = !_seat.available;
          seat = _seat;

          if(seat.available) {
            ++ this.selectedSeats;
    //        this.order.places.push(seat.place_number);
          } else if(!seat.available) {
//               const index = this.order.places.indexOf(seat);
//               this.order.places.splice(index, 1);
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
  //  this.order.filmName = this.selectedFilm.title;
  //  this.order.sessionDate = this.selectedSession.date_time;
    //this.order.roomNumber = Number.parseInt(this.selectedSession.room, 0);
  //  this.order.totalPrice = this.selectedSeats * this.selectedSession.price;
  //  this.order.generationTime = this.datePipe
  //    .transform(new Date(), 'dd.MM.yyyy hh:mm:ss');
  }

  approveOrder(): void {
//     this.order.places
//       .forEach(element => {
//         this.apiService.updateSeats(element).subscribe()
//     });
//     this.router
//       .navigate([``])
//       .then(() => console.log());
  }
}
