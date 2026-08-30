import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Index } from './pages/index/index';
import { Nav } from './components/sidnav/nav';
import { Home } from './components/home/home';
import { TecList } from './components/tecnicos/tec-list/tec-list';
import { Login } from './components/login/login';
import { authGuard } from './auth/auth-guard';
import { TecCreate } from './components/tecnicos/tec-create/tec-create';

const routes: Routes = [
  {path:'login', component:Login}, 

  {path:'', component: Nav, canActivate: [authGuard], children:[
       // rota padrão
     { path: '', redirectTo: 'home', pathMatch: 'full' },
     { path:'home', component: Home },
     
     // rota para a página de técnicos
     { path: 'tecnico', component: TecList },
     { path: 'tecnico/create', component: TecCreate },
     
    ]}
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
