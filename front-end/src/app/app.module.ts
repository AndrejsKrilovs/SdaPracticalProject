import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FilmComponent} from './film/film.component';
import {FilmSessionComponent} from './film-session/film-session.component';

import {RouterModule} from '@angular/router';
import {FilmListComponent} from './film-list/film-list.component';
import {HttpClientModule} from '@angular/common/http';
import {DatePipe} from "@angular/common";

@NgModule({
  declarations: [
    AppComponent,
    FilmComponent,
    FilmSessionComponent,
    FilmListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
