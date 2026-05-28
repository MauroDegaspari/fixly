import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-tec-list',
  standalone: false,
  templateUrl: './tec-list.html',
  styleUrl: './tec-list.css',
})

export class TecList implements AfterViewInit {

  displayedColumns: string[] = ['position', 'name','email', 'perfil', 'symbol'];

  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

}

export interface PeriodicElement {
  name: string;
  email: string;
  position: number;
  perfil: number;
  symbol: string;
}


const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', email: 'Hyoga@email.com', perfil: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', email: 'Hyoga@email.com', perfil: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', email: 'Hyoga@email.com',perfil: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', email: 'Hyoga@email.com', perfil: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', email: 'Hyoga@email.com', perfil: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', email: 'Hyoga@email.com', perfil: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', email: 'Hyoga@email.com', perfil: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', email: 'Hyoga@email.com', perfil: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', email: 'Hyoga@email.com', perfil: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', email: 'Hyoga@email.com', perfil: 20.1797, symbol: 'Ne'},
  {position: 11, name: 'Sodium', email: 'Hyoga@email.com', perfil: 22.9897, symbol: 'Na'},
  {position: 12, name: 'Magnesium', email: 'Hyoga@email.com', perfil: 24.305, symbol: 'Mg'},
  {position: 13, name: 'Aluminum', email: 'Hyoga@email.com', perfil: 26.9815, symbol: 'Al'},
  {position: 14, name: 'Silicon', email: 'Hyoga@email.com', perfil: 28.0855, symbol: 'Si'},
  {position: 15, name: 'Phosphorus', email: 'Hyoga@email.com', perfil: 30.9738, symbol: 'P'},
  {position: 16, name: 'Sulfur', email: 'Hyoga@email.com', perfil: 32.065, symbol: 'S'},
  {position: 17, name: 'Chlorine', email: 'Hyoga@email.com', perfil: 35.453, symbol: 'Cl'},

];
