import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmSessionComponent } from './film-session.component';

describe('FilmSessionComponent', () => {
  let component: FilmSessionComponent;
  let fixture: ComponentFixture<FilmSessionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilmSessionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilmSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
