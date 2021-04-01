import { Injectable } from '@angular/core'
import { HttpClient, HttpParams } from '@angular/common/http'
import { Observable } from 'rxjs'
import { IFilmResponse, IOrder, IPlace, IPlaceResponse, ISessionResponse } from './app.component'

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private filmURL = `//localhost:8080/api/film.svc`
  private sessionURL = `//localhost:8080/api/session.svc`
  private placeURL = `//localhost:8080/api/place.svc`
  private orderURL = `//localhost:8080/api/order.svc`

  constructor(private httpClient: HttpClient) { }

  public filmCollection(offset: number): Observable<IFilmResponse> {
    const params = new HttpParams().set(`offset`, offset.toString())
    return this.httpClient.get<IFilmResponse>(this.filmURL + `/Films`, { params })
  }

  public oneFilm(id: number): Observable<IFilmResponse> {
    return this.httpClient.get<IFilmResponse>(this.filmURL + `/Film(` + id + `)`)
  }

  public sessionCollection(film_id: number, offset: number): Observable<ISessionResponse> {
    const params = new HttpParams().set(`offset`, offset.toString()).set(`film_id`, film_id.toString())
    return this.httpClient.get<ISessionResponse>(this.sessionURL + `/Sessions`, { params })
  }

  public showSeats(session_id: number): Observable<IPlaceResponse> {
    const params = new HttpParams().set(`session_id`, session_id.toString())
    return this.httpClient.get<IPlaceResponse>(this.placeURL + `/Places`, { params })
  }

  public updatePlace(seat: IPlace): Observable<IPlace> {
    return this.httpClient.put<IPlace>(this.placeURL + `/Place`, seat)
  }

  public generatePreOrderData(order_data: any): Observable<IOrder> {
    return this.httpClient.post<IOrder>(this.orderURL + `/Calculation`, order_data)
  }
}
