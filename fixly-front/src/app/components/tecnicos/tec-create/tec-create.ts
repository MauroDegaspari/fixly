import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { TecnicoService } from '../../../services/tecnico-service';
import { Tecnico } from '../../../models/tecnicos';
import { ToastrService } from 'ngx-toastr';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-tec-create',
  standalone: false,
  templateUrl: './tec-create.html',
  styleUrl: './tec-create.css',
})
export class TecCreate {

tecnico:Tecnico = {
  id: '',
  nome: '',
  cpf: '',
  email: '',
  senha: '',
  perfis:[],
  dataCriacao: ''
}


nome: FormControl = new FormControl(null, Validators.minLength(3));
cpf: FormControl = new FormControl(null, Validators.required);
email: FormControl = new FormControl(null, Validators.email);
senha: FormControl = new FormControl(null, Validators.minLength(3));

constructor ( private sercive: TecnicoService, private toast: ToastrService, private dialogRef: MatDialogRef<TecCreate>) {}



createTec(): void{
  this.sercive.createTecnico(this.tecnico).subscribe(resposta =>{
  this.toast.success('Técnico cadastrado com sucesso', 'Cadastro:');
  this.dialogRef.close(true); // Fecha o modal
  
  
  }, ex =>  {

    if (ex.error.errors){
        ex.error.errors.forEach(element => {
        this.toast.error(element.messager, element.error);
      });
    } else{
      this.toast.error(ex.error.messager, ex.error.error);
    }
    
  });
  
}

  addPerfil(perfis:any): void{
    
    if (this.tecnico.perfis.includes(perfis)){
      this.tecnico.perfis.splice(this.tecnico.perfis.indexOf(perfis), 1);
      
    } else {
      this.tecnico.perfis.push(perfis);
      
    }
  }
  validarCampos(): boolean{
   return this.nome.valid && this.cpf.valid && this.email.valid && this.senha.valid
}

}
