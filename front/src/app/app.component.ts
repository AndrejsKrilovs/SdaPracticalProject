import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  selectedMenu: string = ``

  menuItem(event: String): any {
    this.selectedMenu = event.trim()
  }
}
