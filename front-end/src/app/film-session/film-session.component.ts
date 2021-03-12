import { Component } from '@angular/core'
import { ActivatedRoute, Router } from '@angular/router'
import { IFilm, ISession } from '../app.component'
import { ApiService } from '../api.service'

@Component({
  selector: 'app-film-session',
  templateUrl: './film-session.component.html',
  styleUrls: ['./film-session.component.css']
})
export class FilmSessionComponent {
  sessions: Array<ISession> = [];
  session_page_number: number = 0;
  session_total_pages: number = 0;

  selectedFilm: IFilm = {
    film_id: 0,
    film_title: ``,
    film_picture: ``,
    film_length: ``
  }

  constructor(private route: ActivatedRoute, private apiService: ApiService) {
    let filmIdentifier: number = Number.parseInt(this.route.snapshot.paramMap.get(`id`), 0)
    apiService.oneFilm(filmIdentifier).subscribe(result => this.selectedFilm = result.content.pop())
    apiService.sessionCollection(filmIdentifier, this.session_page_number).subscribe(result => {
      this.sessions = result.content
      this.session_page_number = result.metadata.page_number
      this.session_total_pages = result.metadata.total_pages
    })
  }

  pageUp(): void {
    this.sessions.splice(0);
    this.session_page_number > this.session_total_pages - 1 ? this.session_total_pages : this.session_page_number++
    this.apiService.sessionCollection(this.selectedFilm.film_id, this.session_page_number).subscribe(result => {
      this.sessions = result.content
      this.session_page_number = result.metadata.page_number
    })
  }

  pageDown(): void {
    this.sessions.splice(0)
    this.session_page_number <= 0 ? this.session_page_number = 0 : this.session_page_number--
    this.apiService.sessionCollection(this.selectedFilm.film_id, this.session_page_number).subscribe(result => {
      this.sessions = result.content
      this.session_page_number = result.metadata.page_number
    })
  }

  onSessionSelect(session: ISession): void {
    console.log(session)
    // this.seats.splice(0);
    // this.selected_seats.splice(0);
    // this.seats.forEach(s => s.available = false);
    // this.selectedSession = session;

    // this.apiService.showSeats(this.selectedSession.room)
    //   .subscribe(result =>
    //     result.forEach(data => this.seats.push(data))
    //   );
  }


  // selected_seats: Array<number> = [];
  // seats: Array<ISeat> = [];
  // name_surname: string = '';
  // personal_code: string = '';
  // buttonDisabled: boolean = true;

  // selectedSession: ISession = {
  //   id: 0,
  //   date_time: '',
  //   room: 0,
  //   price: 0,
  //   film_id: 0
  // };

  // order: IOrder = {
  //   name_surname: '',
  //   personal_code: '',
  //   session: 0,
  //   places: '',
  //   generation_time: '',
  //   total_price: 0
  // }

  // constructor(
  //   private route: ActivatedRoute,
  //   private apiService: ApiService,
  //   private datePipe: DatePipe,
  //   private router: Router
  // ) {
  //   this.selectedFilm.id = Number.parseInt(this.route.snapshot.paramMap.get('id'), 0);
  //   apiService.oneFilm(this.selectedFilm.id)
  //     .subscribe(result => this.selectedFilm = result);

  //   apiService.sessionCollection(this.selectedFilm.id, this.current_page)
  //     .subscribe(result => result.forEach(data => this.sessions.push(data)));
  // }


  // onSeatSelect(event: any, seat: ISeat): void {
  //   const selectedNumber: number = Number.parseInt(event.target.text, 0);
  //   this.seats.forEach(_seat => {
  //       if(_seat == seat && !_seat.enabled) {
  //         _seat.available = !_seat.available;
  //         seat = _seat;

  //         if(seat.available) {
  //           this.selected_seats.push(seat.place_number);
  //         } else if(!seat.available) {
  //             const ind = this.selected_seats.indexOf(seat.place_number);
  //             this.selected_seats.splice(ind, 1);
  //         }
  //       }
  //     });
  // }

  // makeOrder(): void {
  //   this.order.session = this.selectedSession.id;
  //   this.order.places = this.selected_seats.join(',\xa0');
  //   this.order.total_price = this.selected_seats.length * this.selectedSession.price;
  //   this.order.generation_time = this.datePipe.transform(new Date(), 'dd.MM.yyyy hh:mm:ss');
  //   this.order.name_surname = this.name_surname;
  //   this.order.personal_code = this.personal_code;
  // }

  // approveOrder(): void {
  //   this.seats.forEach(seat => this.apiService.updateSeat(seat).subscribe());
  //   this.apiService.addOrder(this.order).subscribe();
  //   this.invalidateOrder();
  //   this.router.navigate([``]).then(() => console.log());
  // }

  // userCodeInput(event: any): void {
  //   this.personal_code = '';
  //   const correctUserCode: RegExp = /[0-9]{6}-[0-9]{5}/;
  //   if(correctUserCode.test(event.target.value)) {
  //     this.personal_code = event.target.value;
  //   };

  //   this.buttonDisabled = !(this.name_surname.length > 0 && this.personal_code.length === 12);
  // }

  // userNameInput(event: any): void {
  //   this.name_surname = '';
  //   const correctUserName: RegExp = /[A-Z]{1}[a-z]{1,}\s[A-Z]{1}[a-z]{1,}/;
  //   if(correctUserName.test(event.target.value)) {
  //     this.name_surname = event.target.value;
  //   };

  //   this.buttonDisabled = !(this.name_surname.length > 0 && this.personal_code.length === 12);
  // }

  // private init(): void {
  //   this.sessions.splice(0);
  //   this.selected_seats.splice(0);
  //   this.selectedSession.id = 0;
  //   this.name_surname = '';
  //   this.personal_code = '';
  // }

  // private invalidateOrder(): void {
  //   this.order = {
  //     name_surname: '',
  //     personal_code: '',
  //     session: 0,
  //     places: '',
  //     generation_time: '',
  //     total_price: 0
  //   }
  // }
}
