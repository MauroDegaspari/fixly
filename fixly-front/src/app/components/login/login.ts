import { Component, OnInit } from '@angular/core';
import { Credenciais } from '../../models/credenciais';
import { FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Auth } from '../../services/auth';

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

constructor(private toastr: ToastrService, private service: Auth){}

ngOnInit(): void{}


 logar() {
  this.service.authenticate(this.credenciais).subscribe(
    (response) => {
      this.toastr.success('Login realizado com sucesso!', 'Sucesso');
      console.log(response);
    },
    (error) => {
      this.toastr.error('Erro ao realizar login', 'Erro');
      console.log(error);
    }
  );

}

 validarCampos(): boolean {
    if(this.email.valid && this.senha.valid) {
       return true ;
    }else{
       return false;
    }
 }


}
