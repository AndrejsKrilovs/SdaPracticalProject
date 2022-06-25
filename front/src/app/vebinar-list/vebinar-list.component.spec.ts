import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VebinarListComponent } from './vebinar-list.component';

describe('VebinarListComponent', () => {
  let component: VebinarListComponent;
  let fixture: ComponentFixture<VebinarListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VebinarListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VebinarListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
