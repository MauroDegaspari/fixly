import { Component, OnInit } from '@angular/core';
import { Credenciais } from '../../models/credenciais';
import { FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Auth } from '../../services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login implements OnInit {

credenciais: Credenciais = {
  email: '',
  senha: ''
}

email = new FormControl(null, Validators.email);
senha = new FormControl(null, Validators.minLength(3));

constructor(private toastr: ToastrService,
            private service: Auth,
            private router: Router
          ){}

ngOnInit(): void{}


 logar() {
    this.service.authenticate(this.credenciais).subscribe( resposta => {
    this.service.successfulLogin(resposta.headers.get('Authorization').substring(7));
    this.router.navigate(['']);
    } , () => {
    this.toastr.error('Email ou senha inválidos', 'Falha no login');
    });
  }

 validarCampos(): boolean {
    if(this.email.valid && this.senha.valid) {
       return true ;
    }else{
       return false;
    }
 }


}
