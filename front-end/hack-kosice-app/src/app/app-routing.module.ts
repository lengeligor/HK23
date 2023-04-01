import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BalancePreviewComponent } from './balance-preview/balance-preview.component';
import { MainContentComponent } from './main-content/main-content.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {path: 'dashboard', component: MainContentComponent}, 
  {path: 'balance-preview', component: BalancePreviewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
