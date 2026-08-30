import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Auth } from '../../services/auth';
import { ToastrService  } from 'ngx-toastr';

@Component({
  selector: 'app-nav',
  standalone: false,
  templateUrl: './nav.html',
  styleUrl: './nav.css',
})
export class Nav implements OnInit {

  constructor(private router: Router, private auth: Auth, private toast: ToastrService ) {}

  ngOnInit(): void {}

  // localStorage.getItem('token') para ver no console
  logout() {
  this.router.navigate(['/login']);
  this.auth.logout();
  this.toast.info('Logout realizado com sucesso', 'Sucesso');
  }
}
