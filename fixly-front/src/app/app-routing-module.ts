import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Index } from './pages/index/index';
import { Nav } from './components/sidnav/nav';
import { Home } from './components/home/home';
import { TecList } from './components/tecnicos/tec-list/tec-list';

const routes: Routes = [
  {path:'', component: Nav, children:[
     { path:'home', component: Home },
     { path: 'tecnico', component: TecList },
       // rota padrão
      { path: '', redirectTo: 'home', pathMatch: 'full' }
    ]}
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
