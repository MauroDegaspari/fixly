import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Index } from './pages/index/index';
import { Nav } from './components/nav/nav';
import { Home } from './components/home/home';

const routes: Routes = [
  {path:'', component: Nav, children:[ {path:'home', component: Home}]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
