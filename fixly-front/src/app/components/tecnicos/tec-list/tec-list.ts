import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Tecnico } from '../../../models/tecnicos';
import { TecnicoService } from '../../../services/tecnico-service';
import { MatDialog } from '@angular/material/dialog';
import { TecCreate } from '../tec-create/tec-create';
import { TecUpdate } from '../tec-update/tec-update';

@Component({
  selector: 'app-tec-list',
  standalone: false,
  templateUrl: './tec-list.html',
  styleUrl: './tec-list.css',
})

export class TecList implements AfterViewInit {

  linhaEmDestaque: any = null;

  ELEMENT_DATA: Tecnico[] = [];

  displayedColumns: string[] = ['id', 'name','email', 'cpf', 'acao'];

  dataSource = new MatTableDataSource<Tecnico>(this.ELEMENT_DATA);

  constructor( private tecnicoService: TecnicoService, private dialog: MatDialog) { }

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

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
   }

     createTecnicoModal() {
    const dialogRef = this.dialog.open(TecCreate, {
      width: '500px', // Você pode ajustar a largura do modal aqui
      disableClose: true // Opcional: impede de fechar ao clicar fora do modal
    });
    dialogRef.afterClosed().subscribe(resultado => {
      if (resultado) {
        this.findAll();
      }
    });
  }

  editarTecnicoModal(element: any) {
    const dialogRef = this.dialog.open(TecUpdate, {
      width: '900px',
      maxWidth: '900px',
      data: element // Envia todos os dados da linha, incluindo o ID
    });
    dialogRef.afterClosed().subscribe(resultado => {
      if (resultado) {
        this.linhaEmDestaque = resultado; // Armazena o ID do técnico atualizado  
        this.findAll(); 
        setTimeout(() => {
          this.linhaEmDestaque = null;
        }, 3000);
      }
  });
}

  deletarTecnico(element: any) {
    console.log('Deletar técnico:', element);
    // Adicione a lógica de exclusão aqui
  }
}

