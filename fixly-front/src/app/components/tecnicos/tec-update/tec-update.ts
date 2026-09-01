import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormControl, Validators } from '@angular/forms';
import { TecnicoService } from '../../../services/tecnico-service';
import { Tecnico } from '../../../models/tecnicos';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-tec-update',
  standalone: false,
  templateUrl: './tec-update.html',
  styleUrl: './tec-update.css',
})
export class TecUpdate implements OnInit {

  tecnico: Tecnico = {
    id: '',
    nome: '',
    cpf: '',
    email: '',
    senha: '',
    perfis: [],
    dataCriacao: ''
  };

  nome: FormControl = new FormControl(null, Validators.minLength(3));
  cpf: FormControl = new FormControl(null, Validators.required);
  email: FormControl = new FormControl(null, Validators.email);
  senha: FormControl = new FormControl(null, Validators.minLength(3));

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any, 
    private service: TecnicoService, 
    private toast: ToastrService,
    private dialogRef: MatDialogRef<TecUpdate>
  ) { }

  ngOnInit(): void {
    // 1. Passa os dados recebidos para o nosso objeto
    this.tecnico = { ...this.data };
    
    // 2. Preenche os campos de texto na tela imediatamente
    this.nome.setValue(this.tecnico.nome);
    this.cpf.setValue(this.tecnico.cpf);
    this.email.setValue(this.tecnico.email);

    // 3. Limpa e converte os perfis para números inteiros (0, 1, 2)
    // O 'as any' impede que o TypeScript reclame caso a interface Tecnico exija strings
    this.tecnico.perfis = []; 
    if (this.data.perfis) {
      this.data.perfis.forEach((p: any) => {
        if (p === 'ADMIN' || p === 0 || p === '0') this.tecnico.perfis.push(0 as any);
        if (p === 'CLIENTE' || p === 1 || p === '1') this.tecnico.perfis.push(1 as any);
        if (p === 'TECNICO' || p === 2 || p === '2') this.tecnico.perfis.push(2 as any);
      });
    }
  }

  // Recebe o perfil, garante que é Número, e atualiza a lista
  addPerfil(perfil: any): void {
    const numPerfil = Number(perfil); // Força a conversão para número
    
    if (this.tecnico.perfis.includes(numPerfil as any)) {
      this.tecnico.perfis.splice(this.tecnico.perfis.indexOf(numPerfil as any), 1);
    } else {
      this.tecnico.perfis.push(numPerfil as any);
    }
  }

  // Verifica se o número do perfil está na lista para marcar o Checkbox
  hasPerfil(perfil: any): boolean {
    return this.tecnico.perfis.includes(Number(perfil) as any);
  }

  updateTec(): void {
    // Atualiza os campos editados pelo usuário
    this.tecnico.nome = this.nome.value;
    this.tecnico.cpf = this.cpf.value;
    this.tecnico.email = this.email.value;
    this.tecnico.senha = this.senha.value;

    this.service.updateTecnico(this.tecnico).subscribe(
      resposta => {
        this.toast.success('Técnico Atualizado com sucesso', 'Atualização de Cadastro');
        this.dialogRef.close(true); // Fecha o modal
      }, 
      ex => {
        if (ex.error.errors) {
          ex.error.errors.forEach((element: any) => {
            this.toast.error(element.message, element.error);
          });
        } else {
          this.toast.error(ex.error.message, ex.error.error);
        }
      }
    );
  }

  validarCampos(): boolean {
    return this.nome.valid && this.cpf.valid && this.email.valid;
  }
}