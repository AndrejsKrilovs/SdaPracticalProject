import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {FilmListComponent} from './film-list/film-list.component';
import {FilmSessionComponent} from './film-session/film-session.component';

const appRoutes: Routes = [
  { path: '', component: FilmListComponent },
  { path: 'film.svc/Film/:id', component: FilmSessionComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
