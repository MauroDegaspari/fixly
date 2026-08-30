import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Tecnico } from '../../../models/tecnicos';
import { TecnicoService } from '../../../services/tecnico-service';

@Component({
  selector: 'app-tec-list',
  standalone: false,
  templateUrl: './tec-list.html',
  styleUrl: './tec-list.css',
})

export class TecList implements AfterViewInit {

  ELEMENT_DATA: Tecnico[] = [
    


  ];

  displayedColumns: string[] = ['id', 'name','email', 'perfil', 'acao'];

  dataSource = new MatTableDataSource<Tecnico>(this.ELEMENT_DATA);

  constructor( private tecnicoService: TecnicoService) { }

  ngOnInit() {
    this.findAll();
  }
 
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  findAll() {
    this.tecnicoService.findAll().subscribe((resposta) => {
      this.ELEMENT_DATA = resposta;
      this.dataSource = new MatTableDataSource<Tecnico>(resposta);
      this.dataSource.paginator = this.paginator;
    }); 
  }

}

