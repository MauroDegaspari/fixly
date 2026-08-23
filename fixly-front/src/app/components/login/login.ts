import { Component, OnInit } from '@angular/core';
import { Credenciais } from '../../models/credenciais';
import { FormControl, Validators } from '@angular/forms';

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

constructor(){}

ngOnInit(): void{

}

 validarCampos(): boolean {
    if(this.email.valid && this.senha.valid) {
       return true ;
    }else{
       return false;
    }
 }


}
