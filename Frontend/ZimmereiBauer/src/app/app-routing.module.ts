import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {AboutUsComponent} from './about-us/about-us.component';
import {ProductsServiceComponent} from './products-service/products-service.component';
import {ContactComponent} from './contact/contact.component';
import {HistoryComponent} from './history/history.component';


const routes: Routes = [
  { path: 'aboutUs', component: AboutUsComponent },
  { path: 'product-service', component: ProductsServiceComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'history', component: HistoryComponent },
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
