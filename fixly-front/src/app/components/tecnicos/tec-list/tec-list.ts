import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Tecnico } from '../../../models/tecnicos';

@Component({
  selector: 'app-tec-list',
  standalone: false,
  templateUrl: './tec-list.html',
  styleUrl: './tec-list.css',
})

export class TecList implements AfterViewInit {

  ELEMENT_DATA: Tecnico[] = [
    
    {
      id: 1,
      nome: 'Mauro Degaspari',
      cpf:'000000000',
      email: 'mauro_degaspari@hotmail.com',
      senha: 'teste',
      perfis: ['0'],
      dataCriacao: '27/09/2020' 
    }

  ];

  displayedColumns: string[] = ['id', 'name','email', 'perfil', 'acao'];

  dataSource = new MatTableDataSource<Tecnico>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

}

