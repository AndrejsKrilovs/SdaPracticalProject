import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  menuItems = [
    `Webinars`, `Scheduler`, `My progress`, `Chat`
  ]

  @Output() selectedMenuEvent = new EventEmitter<String>();

  menuItemClick(ind: number): any {
    this.selectedMenuEvent.emit(this.menuItems[ind])
  }
}
