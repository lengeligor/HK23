import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BalancePreviewComponent } from './balance-preview/balance-preview.component';
import { MainContentComponent } from './main-content/main-content.component';
import { FinacialExpertsComponent } from './finacial-experts/finacial-experts.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {path: 'dashboard', component: MainContentComponent}, 
  {path: 'balance-preview', component: BalancePreviewComponent},
  {path: 'financial-experts', component: FinacialExpertsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
